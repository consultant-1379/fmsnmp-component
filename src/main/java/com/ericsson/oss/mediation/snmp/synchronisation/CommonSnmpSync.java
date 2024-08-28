package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adventnet.snmp.snmp2.SnmpAPI;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.SnmpVarBind;
import com.ericsson.oss.mediation.cache.model.AlarmTableEntry;
import com.ericsson.oss.mediation.cacheapibean.MibCachingInterfaceBean;
import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.AlarmAttributeConstants;
import com.ericsson.oss.mediation.translator.model.Constants;
import com.ericsson.oss.mediation.translator.model.CreateDateAndTime;
import com.ericsson.oss.mediation.translator.model.EventIdAttrs;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.model.EventTimeAttrs;
import com.ericsson.oss.mediation.translator.model.EventTypeAttrs;
import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;
import com.ericsson.oss.mediation.translator.model.MoInstanceAttributes;
import com.ericsson.oss.mediation.translator.model.NodeMiscAttrs;
import com.ericsson.oss.mediation.translator.model.PerceivedSeverityAttrs;
import com.ericsson.oss.mediation.translator.model.ProbableCauseAttrs;
import com.ericsson.oss.mediation.translator.model.SpecificProblemAttrs;
import com.ericsson.snmp.cache.lookup.CacheBeanLookUp;
import com.ericsson.oss.mediation.translator.constant.TranslationConstant;

public class CommonSnmpSync {


	private static final Logger LOGGER = LoggerFactory
			.getLogger(CommonSnmpSync.class);
	SnmpAPI apiSynch = null;

	private int netraAlarmSeverity;
	NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
	DoSnmpWalkToNode doSnmpWalkToNode = null;


	private String axd301FaultIdSev="",ituAlarmEventTypeoid = "";;


	Map<String, Map<String, String>> snmpWalkAttributes = new HashMap<String, Map<String, String>>();


	private MibCachingInterfaceBean aauServices = CacheBeanLookUp.getMIBCacheBean_Reference();

	private final SNMPManagedElement managedElement;

	public CommonSnmpSync(final SNMPManagedElement managedElement) {
		this.managedElement = managedElement;

		apiSynch = new SnmpAPI();

		if ( apiSynch.isAlive() == false ) 
		{
			apiSynch.start();
		}
	}

	public SynchronizationEventNotification[] synchronize(SNMPManagedElement me)
			throws ActionException {
		try {
			String version = me.getProtocolVersion();
			LOGGER.debug("version for mmanaged element:" + me.getFdn() + "version:" + version);

			return translateSyncAlarm(me);
		} catch (Exception ex) {
			throw new ActionException("Synchronise not possible");
		}
	}

	public SynchronizationEventNotification[] translateSyncAlarm(SNMPManagedElement me) throws Exception
	{	
		ArrayList<SnmpPDU> pduList;
		SynchronizationEventNotification[] notif1 = null;

		String mibType = "";

		Map<String, ArrayList<SnmpOID>> snmpWalkAttributes = new HashMap<String, ArrayList<SnmpOID>>();

		try
		{
			snmpWalkAttributes = aauServices.getSupportedAttributeOids(me.getSourceType());

			LOGGER.debug("snmpWalkAttributes  :: --->" +snmpWalkAttributes);
			ArrayList<SnmpOID> synchOidList = new ArrayList<SnmpOID>();
			ArrayList<SynchronizationEventNotification> syncNotifs = new ArrayList<SynchronizationEventNotification>();

			SnmpOID[] oidArray=null;
			doSnmpWalkToNode=new DoSnmpWalkToNode();


			if(snmpWalkAttributes != null && snmpWalkAttributes.size()>0)
			{
				Iterator<Entry<String, ArrayList<SnmpOID>>> itSnmpWalk = snmpWalkAttributes.entrySet().iterator();
				while ((itSnmpWalk).hasNext())
				{
					Map.Entry<String,ArrayList<SnmpOID>> pairs = (itSnmpWalk).next();

					mibType = pairs.getKey();
					synchOidList = pairs.getValue();

					if(isStringNotNull(mibType) && synchOidList.size()>0)
					{
						oidArray= new SnmpOID[synchOidList.size()];
						oidArray=synchOidList.toArray(oidArray);
						doSnmpWalkToNode.snmpWalk(me,oidArray,apiSynch);
					}
				}

				pduList=doSnmpWalkToNode.getPduList();
				LOGGER.debug("The size of pdu list is===>:"+pduList.size());
				ListIterator<SnmpPDU> listIterator = pduList.listIterator();
				while(listIterator.hasNext()){
					SynchronizationEventNotification syncAlarm = extractSynchAlarmInfo(listIterator.next(),new SynchronizationEventNotification(),me);
					if(syncAlarm!=null){
						syncNotifs.add(syncAlarm);
						LOGGER.debug("CommonSnmpSync :: SynchronizationEventNotification:::"+syncAlarm.toString());
					}
				}
				
				notif1=syncNotifs.toArray(new SynchronizationEventNotification[syncNotifs.size()]);
				LOGGER.debug("The size of syncNotifs array===>:"+syncNotifs.size());
				pduList.clear();
				syncNotifs.clear();
			}

			if(notif1.length==0){
				SynchronizationEventNotification[] notif=null;
				notif = new SynchronizationEventNotification[1];
				
				notif[0]=createNoActiveAlarmNotif(me.getIpAddress(),me.getProtocolVersion());

				return notif;
			}

			LOGGER.debug("CommonSnmpSync :: Synchronize() -> Sync Request finish");

			return notif1;
		}
		catch(Exception ex)
		{
			LOGGER.error("CommonSnmpSync :: Synchronize() -> exception:"+ex.toString());
			throw new ActionException("Synchronise not possible");
		}
	}


	public SynchronizationEventNotification extractSynchAlarmInfo(SnmpPDU snmppdu, SynchronizationEventNotification notif,SNMPManagedElement me)
	{

		notif.setFmEventType(Constants.NOTIF_TYPE_SYNCALARM);
		notif.setTime(CreateDateAndTime.getCurrentTime());
		notif.addAdditionalAttribute(TranslationConstant.SNMP_IP_ADDRESS,me.getIpAddress());
		notif.addAdditionalAttribute(TranslationConstant.SNMP_VERSION,me.getProtocolVersion());
		notif.setTimeZone("UTC");
		notif.addAdditionalAttribute(TranslationConstant.SNMP_TRAP_OID,"null");
		notif.addAdditionalAttribute(TranslationConstant.SNMP_SPECIFIC,"null");
		notif.addAdditionalAttribute(TranslationConstant.SNMP_ENTERPRISE,"True");
		notif.addAdditionalAttribute(TranslationConstant.SNMP_GENERIC,"False");

		AlarmTableEntry varbindTable = new AlarmTableEntry();

		Map<String,String> varbindAdditionalText = new HashMap<String,String>();
		Map<String,String> supportedAttributeOIDValues = new HashMap<String,String>();

		String systemType="",attributeName = "",varbindOID = "",varbindValue = "",varbindName = "",varbindType = "",severityToken = "",severityNumber = "",severityValue = "",attributeNumber = "",attributeValue = "",attributeToken = "",varOutput="";

		StringTokenizer severityTokenizer = null,severityMapping = null,additionalAttributeTokenizer = null,attributeMapping = null;

		systemType=me.getSourceType();

		
		for (final Enumeration<SnmpVarBind> e = snmppdu.getVariableBindings().elements(); e
				.hasMoreElements();) {
			final SnmpVarBind binding = (SnmpVarBind) e.nextElement();
			
			varbindOID = "";varbindValue = "";varOutput = "";
			

			/** SNMP Variable Binding. It contains an object identifier and a SnmpVar. */

			varbindOID 	 = binding.getObjectID().toString();
			varOutput = 	binding.toTagString();


			StringTokenizer varTokenizer = new StringTokenizer(varOutput,"\n");
			varTokenizer.nextToken();
			varbindValue = varTokenizer.nextToken();
			varbindValue=varbindValue.substring(varbindValue.indexOf(":")+1).trim();


			LOGGER.debug("CommonSnmpSync :: varbindOID => "+varbindOID+" varbindValue => "+varbindValue);

			attributeName = aauServices.getAttributeName(varbindOID,systemType);

			while(isStringNull(attributeName) && isStringNotNull(varbindOID))
			{
				if ( (varbindOID.startsWith(HandleOtherAlarmAttribute.AXDSEV))){
					axd301FaultIdSev = varbindOID;
				}

				varbindOID = varbindOID.substring(0,varbindOID.lastIndexOf("."));
				attributeName = aauServices.getAttributeName(varbindOID,systemType);
			}

			LOGGER.debug("CommonSnmpSync :: attributeName => "+attributeName+" varbindOID => "+varbindOID);

			/** Put the snmp varbinds to List
				supportedAttributeOIDValues will have attributeName => varbindValue pairs */

			if(isStringNotNull(varbindOID) && isStringNotNull(attributeName))
			{
				varbindTable = aauServices.getAlarmTableEntry(varbindOID,systemType);

				if(varbindTable != null)
				{
					varbindAdditionalText = varbindTable.additionalValues;

					if(varbindAdditionalText != null && varbindAdditionalText.size()>0)
					{
						Iterator itAdditionalText = varbindAdditionalText.entrySet().iterator();

						while (itAdditionalText.hasNext())
						{
							Map.Entry<String,String> pairs = (Map.Entry<String,String>)itAdditionalText.next();

							varbindName = pairs.getKey();
							varbindType = pairs.getValue();

							LOGGER.debug("CommonSnmpSync :: varbindName ==> "+varbindName+" varbindType ==> "+varbindType);

							if(isStringNotNull(varbindName) && isStringNotNull(varbindType) && varbindType.equals("ByteArray"))
							{
								byte[] theByteArray = binding.getVariable().toBytes();
								varbindValue = convertOctalToString(theByteArray);
							}
							else if(isStringNotNull(varbindName) && isStringNotNull(varbindType) && varbindName.equals("Severity"))
							{
								severityTokenizer = new StringTokenizer(varbindType,",");

								while(severityTokenizer.hasMoreTokens())
								{
									severityNumber = "";severityValue = "";severityToken = "";

									try
									{
										severityToken = severityTokenizer.nextToken();
										severityMapping = new StringTokenizer(severityToken,":");

										severityNumber = severityMapping.nextToken();
										severityValue  = severityMapping.nextToken();

									}
									catch(Exception exx)
									{ }

									if(isStringNotNull(severityNumber) && isStringNotNull(varbindValue) && severityNumber.equals(varbindValue))
									{
										varbindValue = severityValue;

										break;
									}
								}
							}
							else if(isStringNotNull(varbindName) && isStringNotNull(varbindType) && varbindName.equals("AttributeMapping"))
							{
								additionalAttributeTokenizer = new StringTokenizer(varbindType,",");

								while(additionalAttributeTokenizer.hasMoreTokens())
								{
									attributeNumber = "";attributeValue = "";attributeToken = "";

									try
									{
										attributeToken = additionalAttributeTokenizer.nextToken();
										attributeMapping = new StringTokenizer(attributeToken,":");

										attributeNumber = attributeMapping.nextToken();
										attributeValue  = attributeMapping.nextToken();
									}
									catch(Exception exy)
									{ }

									if(isStringNotNull(attributeNumber) && isStringNotNull(varbindValue) && attributeNumber.equals(varbindValue))
									{
										varbindValue = attributeValue;

										break;
									}
								}
							}
						}
					}
				}



				LOGGER.debug("CommonSnmpSync :: attributeName ==> "+attributeName+" added to map with varbindValue ==> "+varbindValue);

				supportedAttributeOIDValues.put(attributeName,varbindValue);

			}
		}


		try{
			SynchronizationEventNotification syncNotif= setAttributesForSynchAlarm(notif,supportedAttributeOIDValues);
			return syncNotif;
		}catch(Exception e)
		{
			LOGGER.error("CommonSnmpSync :: Exception in extractSynchAlarmInfo ==> "+e.toString());
		}


		return notif;
	}

	private SynchronizationEventNotification setAttributesForSynchAlarm(SynchronizationEventNotification notif,Map<String, String> supportedAttributeOIDValues)
	{
		String attributeName = "",attributeValue = "",moiresult="",trapbsid="";

		StringBuffer additionaltextBuffer=new StringBuffer();



		if(supportedAttributeOIDValues != null && supportedAttributeOIDValues.size()>0)
		{
			Iterator iterator = supportedAttributeOIDValues.entrySet().iterator();
			try{

				while (iterator.hasNext())
				{
					Map.Entry<String,String> pairs = (Map.Entry<String,String>)iterator.next();

					attributeName = pairs.getKey();
					attributeValue = pairs.getValue();

					/** Setting the attribute values dynamically */

					if (nodeMiscAttrs.translateTmosAttrs(attributeName,attributeValue, notif, ituAlarmEventTypeoid)) {
						continue;
					}
					else if(EventTimeAttrs.translateEventTime(attributeName,attributeValue,notif))
					{
						continue;
					}
					else if ((moiresult = MoInstanceAttributes
							.handleMOInstanceIfApplicable(attributeName,
									attributeValue, notif, additionaltextBuffer,
									trapbsid)) != null) {
						if (!moiresult.equals(MoInstanceAttributes.SNMP_MOI_OK)) {
							trapbsid = moiresult;
						}
						continue;
					}	
					else if(EventTypeAttrs.translateEventTypeAttr(attributeName,attributeValue, notif, nodeMiscAttrs.isPerfdegraded()))
					{
						continue;
					}
					else if(ProbableCauseAttrs.translateProbableCause(attributeName,attributeValue,notif))
					{
						continue;
					}
					else if(PerceivedSeverityAttrs.translatePerceivedSeverityAttr(attributeName, attributeValue, notif, axd301FaultIdSev))
					{
						continue;
					}
					else if(SpecificProblemAttrs.translateSpecificProblem(attributeName, attributeValue, notif,additionaltextBuffer, netraAlarmSeverity))
					{
						continue;
					}
					else if(EventIdAttrs.translateEventId(attributeName,attributeValue, notif))
					{
						continue;
					}
					else if(AlarmAttributeConstants.setadditionalTextAttributes(attributeName,attributeValue, notif, additionaltextBuffer))
					{
						continue;
					}
				}
			}
			catch(Exception e){
				LOGGER.error("CommonSnmpSync :: Exception in WHILE BLOCK**** ==> "+e.toString());
			}
			if(additionaltextBuffer.length()!=0)
				notif.addAdditionalAttribute("additionalText",additionaltextBuffer.toString());


			try{
				SynchronizationEventNotification syncNotif= setAttributesForPlatformMibs(notif);
				return syncNotif;
			}catch(Exception e)
			{
				LOGGER.error("CommonSnmpSync :: Exception in setAttributesForSynchAlarm ==> "+e.toString());
			}
		}
		return notif;
	}

	private SynchronizationEventNotification setAttributesForPlatformMibs(SynchronizationEventNotification notif)
	{
		Map<String, String> additionalAttributes = new HashMap<String, String>();
		additionalAttributes=notif.getAdditionalAttributes();

		if(additionalAttributes.containsKey("ifAdminStatus")){
			LOGGER.debug("setAttributesForPlatformMibs:IF MIB");
			
			String ifAdminStatus=notif.getAdditionalAttribute("ifAdminStatus"); 
			String ifOperStatus=notif.getAdditionalAttribute("ifOperStatus");  

			LOGGER.debug("ifAdminStatus====>"+ifAdminStatus+"ifOperStatus===>"+ifOperStatus);

			if (!((ifAdminStatus.contains("up")  || ifAdminStatus.contains("testing")) && (ifOperStatus.contains("down")))){
				LOGGER.debug("DISCARD SYNCH ALARM:IF MIB");
				notif=null;
				return notif;
			}

			notif.setEventType("2"); 
			notif.setProbableCause("305");
			notif.setSpecificProblem("Link Down");
			notif.setSeverity(Constants.SEV_MAJOR);
			notif.addAdditionalAttribute("additionalText","A linkDown trap signifies that the SNMPv2 entity, acting in an agent role,has detected that the ifOperStatus object for one of its communication links is about to enter the down state from some other state");
		}else if(additionalAttributes.containsKey("mplsLspState")){
			LOGGER.debug("setAttributesForPlatformMibs(:MPLS MIB");
			
			int mplsLspState=Integer.valueOf(notif.getAdditionalAttribute("mplsLspState")).intValue();
			LOGGER.debug("mplsLspState====>"+mplsLspState);

			if(mplsLspState!=3){
				LOGGER.debug("DISCARD SYNCH ALARM:MPLS MIB");
				notif=null;
				return notif;
			}

			notif.setProbableCause("517");
			notif.setEventType("1");
			notif.setSpecificProblem("MPLS LSP Down");
			notif.setSeverity(Constants.SEV_INDETERMINATE);
			notif.addAdditionalAttribute("additionalText","An mplsLspDown trap signifies that the specified LSP is down, because the current active path mplsPathName went down.");
		}else if(additionalAttributes.containsKey("jnxVpnIfStatus")){
			LOGGER.debug("setAttributesForPlatformMibs(:JUNIPER-VPN-MIB(IF) MIB");
			
			int jnxVpnIfStatus=Integer.valueOf(notif.getAdditionalAttribute("jnxVpnIfStatus")).intValue();
			LOGGER.debug("jnxVpnIfStatus====>"+jnxVpnIfStatus);

			if(jnxVpnIfStatus!=4){
				LOGGER.debug("DISCARD SYNCH ALARM:JUNIPER-VPN-MIB(IF)");
				notif=null;
				return notif;
			}

			notif.setProbableCause("0");
			notif.setEventType("2");
			notif.setSpecificProblem("VPN Interface down");
			notif.setSeverity(Constants.SEV_INDETERMINATE);
			notif.addAdditionalAttribute("additionalText","A jnxVpnIfDown notification is generated when the interface with index jnxVpnIfIndex belonging to the VPN named jnxVpnIfVpnName of type jnxVpnIfVpnType transitions to the 'down' state.");
		}else if(additionalAttributes.containsKey("jnxVpnPwStatus")){
			LOGGER.debug("setAttributesForPlatformMibs(:JUNIPER-VPN-MIB(PW) MIB");
			
			int jnxVpnPwStatus=Integer.valueOf(notif.getAdditionalAttribute("jnxVpnPwStatus")).intValue();
			LOGGER.debug("jnxVpnPwStatus====>"+jnxVpnPwStatus);

			if(jnxVpnPwStatus!=1){
				LOGGER.debug("DISCARD SYNCH ALARM:JUNIPER-VPN-MIB(PW)");
				notif=null;
				return notif;
			}

			notif.setProbableCause("0");
			notif.setEventType("2");
			notif.setSpecificProblem("Pseudo-Wire Down");
			notif.setSeverity(Constants.SEV_INDETERMINATE);
			notif.addAdditionalAttribute("additionalText","A jnxVpnPwDown notification is generated when the Pseudo-Wire with index jnxVpnPwIndex belonging to the VPN named jnxVpnPwVpnName of type jnxVpnPwVpnType transitions to the 'down' state.");
		}else if(additionalAttributes.containsKey("jnxRmonAlarmState")){
			LOGGER.debug("setAttributesForPlatformMibs:RMON MIB");
			
			int jnxRmonAlarmState=Integer.valueOf(notif.getAdditionalAttribute("jnxRmonAlarmState")).intValue();
			LOGGER.debug("jnxRmonAlarmState====>"+jnxRmonAlarmState);
			if(jnxRmonAlarmState!=7){
				LOGGER.debug("DISCARD SYNCH ALARM:RMON MIB");
				notif=null;
				return notif;
			}

			notif.setProbableCause("540");
			notif.setEventType("2");
			notif.setSpecificProblem("The object Identifier specified within this RMON alarm does not exist");
			notif.setSeverity(Constants.SEV_INDETERMINATE);
			notif.addAdditionalAttribute("additionalText","The SNMP trap that is generated when the get request for an alarm variable returns an error. The specific error is identified by jnxRmonAlarmGetFailReason.");
		}
		return notif;
	}



	private boolean isStringNotNull(String stringToCheck) {
		boolean isStringNotNull = false;

		if (stringToCheck != null && stringToCheck.length() > 0) {
			isStringNotNull = true;
		}
		return isStringNotNull;

	}

	private boolean isStringNull(String stringToCheck)
	{
		boolean isStringNull = false;

		if(stringToCheck == null || stringToCheck.length()<=0 || stringToCheck.equals("null"))
		{
			isStringNull = true;
		}

		return isStringNull;
	}

	private String convertOctalToString(byte[] theByteArray) {
		for (int i = 0; i < theByteArray.length; i++) {
			if (((byte) theByteArray[i] >= (byte) 0x00)
					&& ((byte) theByteArray[i] < (byte) 0x20)) {
				theByteArray[i] = '0';
			}
		}
		return null;
	}

	public EventNotification[] stopSynch() {
		final EventNotification[] notifications = new EventNotification[1];
		//final String fdn=SNMPFdnConverter.converFdn(managedElement.getFdn());
		final String fdn=managedElement.getFdn();
		notifications[0] = EventNotification
				.createSynchronizationEndNotification(fdn,
						managedElement.getSourceType());
		return notifications;
	}

	public EventNotification[] abortSynch() {
		final EventNotification[] notifications = new EventNotification[1];
		notifications[0] = EventNotification
				.createSynchronizationAbortedNotification(
						managedElement.getFdn(), managedElement.getSourceType());
		return notifications;
	}
	
	private SynchronizationEventNotification createNoActiveAlarmNotif(String ipAddress,String version){
		final SynchronizationEventNotification eventNotification = new SynchronizationEventNotification();
		
		eventNotification.setFmEventType(Constants.NOTIF_TYPE_SYNCALARM);
		eventNotification.addAdditionalAttribute("IPAddress",ipAddress);
		eventNotification.addAdditionalAttribute("Version",version);
		eventNotification.setTime(CreateDateAndTime.getCurrentTime());
		eventNotification.setTimeZone("UTC");
		eventNotification.addAdditionalAttribute("SNMPTrapOID","null");
		eventNotification.addAdditionalAttribute("Specific","null");
		eventNotification.addAdditionalAttribute("Enterprise","True");
		eventNotification.addAdditionalAttribute("Generic","False");
		eventNotification.addAdditionalAttribute("additionalText", "No Active Alarms in Alarm Table.");
		eventNotification.addAdditionalAttribute("AdditionalText", "No Active Alarms in Alarm Table.");
		eventNotification.setEventType("Communication Alarm");
		eventNotification.setProbableCause("Indeterminate");  
		eventNotification.setSpecificProblem("No Active Alarms in Alarm Table.");
		eventNotification.setSeverity(Constants.SEV_INDETERMINATE);
		
		return eventNotification;
	}

}