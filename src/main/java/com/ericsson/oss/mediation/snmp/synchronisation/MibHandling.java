/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.cache.model.AlarmTableEntry;
import com.ericsson.oss.mediation.cacheapibean.MibCachingInterfaceBean;
import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.*;
import com.ericsson.snmp.cache.lookup.CacheBeanLookUp;

import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.SnmpVarBind;
import com.ericsson.oss.mediation.translator.model.EventNotification;

public class MibHandling {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(MibHandling.class);
	String additionalAttributeValue = "", additionalAttributeName = "", trapOID = "", attributeName = "", varbindOID = "", varbindValue = "", varbindName = "", varbindType = "", severityToken = "", severityNumber = "", severityValue = "", attributeNumber = "", attributeValue = "", attributeToken = "", varOutput="";
	TranslateAlarm translateAlarm= new TranslateAlarm();
	SynchronizationEventNotification [] syncEventNotifications = null;
	 EventNotification notif = new EventNotification();
	int count=0;
	String broadsoftEventTime = "";
	SnmpVarBind timevarbind;
	private int netraAlarmSeverity;
	String ituAlarmEventTypeoid = "";
	private String axd301FaultIdSev = "";
	private final AxdAlarmAttrs axdAlarmAttrs = new AxdAlarmAttrs();
	private static MibCachingInterfaceBean aauServices = CacheBeanLookUp.getMIBCacheBean_Reference();
	StringTokenizer severityTokenizer = null, severityMapping = null, additionalAttributeTokenizer = null, attributeMapping = null;
	AlarmTableEntry varbindTable = new AlarmTableEntry();
	
	Map<String, String> additionalText = new HashMap<String, String>();
	Map<String, String> varbindAdditionalText = new HashMap<String, String>();
	
	private final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
	final Map<String, String> supportedAttributeOIDValues = new HashMap<String, String>();
	
	StringBuffer additionaltextBuffer=new StringBuffer();
	
	public SynchronizationEventNotification[] getMIBAlarm(SNMPManagedElement me,List<SnmpPDU> responsePDU, Map<String,StringTokenizer> snmpWalkMap,int alarmListCount)
	{
		
	for(SnmpPDU snmppdu:responsePDU)
	{
		count++;
		LOGGER.info("counter:::"+count);
		for (final Enumeration e = snmppdu.getVariableBindings().elements(); e.hasMoreElements();) 
		{
		LOGGER.info("Varbinds::"+snmppdu.getVariableBindings().elements());

		boolean isSNMPV1Trap = false;
		final SnmpVarBind binding = (SnmpVarBind) e.nextElement();
		LOGGER.info("binding::"+binding);
		varbindOID = "";
		varbindValue = "";
		varOutput = "";		
		varbindOID = binding.getObjectID().toString();
		varOutput = binding.toTagString();
		
		final StringTokenizer varTokenizer = new StringTokenizer(varOutput,"\n");
		varTokenizer.nextToken();
		
		varbindValue = varTokenizer.nextToken();
		if(varbindValue != null){
			varbindValue=varbindValue.substring(varbindValue.indexOf(":")+1).trim();
		
		}		

		if ((isStringNotNull(varbindOID) && varbindOID
				.equals(".1.3.6.1.6.3.1.1.4.1.0")) && !isSNMPV1Trap) {
			/** Get the trap OID using SNMP OID */

			trapOID = varbindValue;
			syncEventNotifications[count].addAdditionalAttribute("SNMPTrapOID", trapOID);
			//setAttributesForAlarm(syncEventNotifications[count],snmppdu,supportedAttributeOIDValues,additionaltextBuffer);
			LOGGER.info(" SYNC NOTIFICATUION :::: "+syncEventNotifications[count]);
			
		}
		
		if ((isStringNotNull(varbindOID)
				&& varbindOID.equals(".1.3.6.1.6.3.1.1.4.1.0") && varbindValue
					.startsWith(".1.3.6.1.4.1.9070")) && isSNMPV1Trap) {
			notif = TruetimeAlarmHandler.buildSymmAlarm(varbindOID,varbindValue, notif);
			
		}
		else if (isStringNotNull(varbindOID) && (varbindOID.startsWith(".1.3.6.1.2.1.88.2.0.1"))) 
		{ 
			
			if (nodeMiscAttrs.getMteHotTrigger().equalsIgnoreCase("High CPU load")) {
				notif.setProbableCause("310"); // Cpu Cycles Limit
				
				notif.setSpecificProblem("CPU Utilization Threshold Reached.");

			} else if (nodeMiscAttrs.getMteHotTrigger()
					.equalsIgnoreCase("High swap usage")) {
				notif.setProbableCause("332"); // Out of Memory
				notif.setSpecificProblem("Memory Utilization Threshold Reached.");
			}
			notif.addAdditionalAttribute("additionalText",
					nodeMiscAttrs.getMteHotTrigger());
			
		} else {
			
			if ((isStringNotNull(varbindOID) && (varbindOID
					.startsWith(".1.3.6.1.4.1.42.2.70.101.2.1.2") || (varbindOID
					.startsWith(".1.3.6.1.4.1.193.167.2.1.2.2.1")
					|| varbindOID.startsWith(".1.3.6.1.4.1.5549.1.1")
					|| varbindOID
							.startsWith(".1.3.6.1.4.1.19631.1.1.1.2") || varbindOID
						.startsWith(".1.3.6.1.4.1.6431.1.1.1.1.2"))))) {
				timevarbind = binding;
			}

			if (isStringNotNull(varbindOID)
					&& (varbindOID.startsWith(".1.3.6.1.2.1.88.2.0.1"))) {
				ituAlarmEventTypeoid = varbindOID;

			}
			if (isStringNotNull(varbindOID)
					&& (varbindOID
							.startsWith(HandleOtherAlarmAttribute.AXDSEV))) {
				axd301FaultIdSev = varbindOID;

			}
		
			attributeName = aauServices.getAttributeName(varbindOID,me.getSourceType());

			while (translateAlarm.isStringNull(attributeName)&& isStringNotNull(varbindOID))
			{
				varbindOID = varbindOID.substring(0,varbindOID.lastIndexOf("."));
				attributeName = aauServices.getAttributeName(varbindOID, me.getSourceType());
			}


			if (isStringNotNull(varbindOID)&& isStringNotNull(attributeName))
			{
				varbindTable = aauServices.getAlarmTableEntry(varbindOID, me.getSourceType());

				if (varbindTable != null) 
				{
					varbindAdditionalText = varbindTable.additionalValues;

					if (varbindAdditionalText != null&& varbindAdditionalText.size() > 0) {
						final Iterator itAdditionalText = varbindAdditionalText.entrySet().iterator();

						while (itAdditionalText.hasNext()) 
						{
							final Map.Entry<String, String> pairs = (Map.Entry<String, String>) itAdditionalText
									.next();

							varbindName = pairs.getKey();
							varbindType = pairs.getValue();							

							if (isStringNotNull(varbindName)&& isStringNotNull(varbindType)&& varbindType.equals("ByteArray"))
							{
								final byte[] theByteArray = binding.getVariable().toString().getBytes();
								varbindValue = translateAlarm.convertOctalToString(theByteArray);
							} else if (isStringNotNull(varbindName)
									&& isStringNotNull(varbindType)
									&& varbindName.equals("Severity")) {
								severityTokenizer = new StringTokenizer(
										varbindType, ",");

								while (severityTokenizer
										.hasMoreTokens()) {
									severityNumber = "";
									severityValue = "";
									severityToken = "";

									try {
										severityToken = severityTokenizer
												.nextToken();
										severityMapping = new StringTokenizer(
												severityToken, ":");

										severityNumber = severityMapping
												.nextToken();
										severityValue = severityMapping
												.nextToken();

									} catch (Exception exx) {
									}

									if (isStringNotNull(severityNumber)
											&& isStringNotNull(varbindValue)
											&& severityNumber
													.equals(varbindValue)) {
										varbindValue = severityValue;

										break;
									}
								}
							} else if (isStringNotNull(varbindName)
									&& isStringNotNull(varbindType)
									&& varbindName
											.equals("AttributeMapping")) {
								additionalAttributeTokenizer = new StringTokenizer(
										varbindType, ",");

								while (additionalAttributeTokenizer
										.hasMoreTokens()) {
									attributeNumber = "";
									attributeValue = "";
									attributeToken = "";

									try {
										attributeToken = additionalAttributeTokenizer
												.nextToken();
										attributeMapping = new StringTokenizer(
												attributeToken, ":");

										attributeNumber = attributeMapping
												.nextToken();
										attributeValue = attributeMapping
												.nextToken();

									} catch (Exception exy) {
									}

									if (isStringNotNull(attributeNumber)
											&& isStringNotNull(varbindValue)
											&& attributeNumber
													.equals(varbindValue)) {
										varbindValue = attributeValue;

										break;
									}
								}
							}
						}
					}
				}

					LOGGER.info(" attributeName : " + attributeName
							+ " added to map with varbindValue : "
							+ varbindValue);

					supportedAttributeOIDValues.put(attributeName,
							varbindValue);
			
			}

		}
	}
	}//for end
	return syncEventNotifications;
	}
	
	/*public SynchronizationEventNotification[] getMIBAlarm(SNMPManagedElement me,List<SnmpPDU> responsePDU, Map<String,StringTokenizer> snmpWalkMap,int alarmListCount){
		int count=0;
        String s9 =null;
		SynchronizationEventNotification [] synchEventNotifications = null;
		int alarmTimeCount = snmpWalkMap.get("AlarmTime").countTokens();
		if(alarmTimeCount>0)
		{
		synchEventNotifications = new SynchronizationEventNotification[alarmTimeCount];
		for(int i = 0; i < alarmListCount; ++i)
		{			
			synchEventNotifications[count] = new SynchronizationEventNotification(); //NO_ANALYSIS NOPMD
			synchEventNotifications[count].setFmEventType("ALARM");
			synchEventNotifications[count].addAdditionalAttribute("SNMPTrapOID", "");
			synchEventNotifications[count].addAdditionalAttribute("IPAddress", me.getIpAddress());
			synchEventNotifications[count].addAdditionalAttribute("Version", "V2C");
			synchEventNotifications[count].addAdditionalAttribute("Enterprise", "True");
			try{				 
				
				//synchEventNotifications[count].setManagedObjectInstance(snmpWalkMap.get("granALARM_MANAGEDOBJECTINSTANCE").nextToken());
				synchEventNotifications[count].addAdditionalAttribute("AddtionalText",snmpWalkMap.get("AddtionalText").nextToken());
				synchEventNotifications[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(s9))).toString());
				synchEventNotifications[count].setTimeZone(HandleTimeTranslation.getEventTimeZone(s9));
				synchEventNotifications[count].setEventType(snmpWalkMap.get("EventType").nextToken());
				synchEventNotifications[count].setProbableCause(snmpWalkMap.get("PerceivedSeverity").nextToken());
				String severity=snmpWalkMap.get("PerceivedSeverity").nextToken();
				String granSeverity=HandleAlarmSeverity.getPerceivedSeverity(severity);
				synchEventNotifications[count].setSeverity(granSeverity);
				synchEventNotifications[count].setSpecificProblem(snmpWalkMap.get("SpecificProblem").nextToken());
				
			}catch(final NoSuchElementException ne){
				//synchEventNotifications[count].setManagedObjectInstance(snmpWalkMap.get("granALARM_MANAGEDOBJECTINSTANCE").nextToken());				
			
				synchEventNotifications[count].addAdditionalAttribute("AddtionalText","none");
				synchEventNotifications[count].setEventType("0");
				synchEventNotifications[count].setProbableCause("0");
				synchEventNotifications[count].setSeverity("INDETERMINATE");
				synchEventNotifications[count].setSpecificProblem("none");
				LOGGER.info("NoSuchElementException :"+ne.getMessage());
			}			
			
			catch(final Exception e){
				  s9 = snmpWalkMap.get("AlarmTime").nextToken();
				synchEventNotifications[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
				synchEventNotifications[count].setTimeZone(HandleTimeTranslation.getTimeZone(s9));
			}
			
		}
		}
		
	    return synchEventNotifications;
	}*/
	
	
	public boolean isStringNotNull(final String stringToCheck) {
		boolean isStringNotNull = false;

		if (stringToCheck != null && stringToCheck.length() > 0) {
			isStringNotNull = true;
		}

		return isStringNotNull;
	}
	//TODO:
	/*public void setAttributesForAlarm(SynchronizationEventNotification notif,
			final SnmpPDU snmppdu, final Map supportedAttributeOIDValues,
			final StringBuffer additionaltextBuffer) {

		String trapbsid = ""; // isblade

		String attributeName = "", attributeValue = "";

		// StringBuffer additionaltextBuffer=new StringBuffer();

		LOGGER.debug("setting the attribute values dynamically");

		if (supportedAttributeOIDValues != null
				&& supportedAttributeOIDValues.size() > 0) {
			final Iterator iterator = supportedAttributeOIDValues.entrySet()
					.iterator();

			while (iterator.hasNext()) {
				final Map.Entry<String, String> pairs = (Map.Entry<String, String>) iterator
						.next();

				attributeName = pairs.getKey();
				attributeValue = pairs.getValue();

			
				String moiresult;
				if (isStringNotNull(attributeName)
						&& (EventTimeAttributes
								.getEventTimeFormat(attributeName) != null)) {
					Integer eventTimeFormat = EventTimeAttributes
							.getEventTimeFormat(attributeName);
					// Broadsoft Event Time
					if (EventTimeAttributes.SNMP_BROADSOFT_TIME
							.equals(eventTimeFormat)) {
						final StringTokenizer BroadsoftTimeandzone = HandleTimeTranslation
								.broadsoftEventTime(timevarbind);
						String time = "";
						while (BroadsoftTimeandzone.hasMoreTokens()) {
							time = BroadsoftTimeandzone.nextToken();
							BroadsoftTimeandzone.nextToken();
						}
						notif.setTime(new Date(time));// NEXT IRP AND MGC
														// mgcEventTime

					} else if (EventTimeAttributes.SNMP_OMS_TIME
							.equals(eventTimeFormat)) {
						final StringTokenizer omsTrapContentsTimeStampTimeandzone = HandleTimeTranslation
								.irpomsTrapContents(timevarbind);
						String time = "";
						String timezone = "";
						while (omsTrapContentsTimeStampTimeandzone
								.hasMoreTokens()) {
							time = omsTrapContentsTimeStampTimeandzone
									.nextToken();
							timezone = omsTrapContentsTimeStampTimeandzone
									.nextToken();
						}
						notif.setTime(new Date(time));
						notif.setTimeZone(timezone);

					} else if (EventTimeAttributes.SNMP_MCG_TIME
							.equals(eventTimeFormat)) {
						StringTokenizer mgcEvent_TimeTimeandzone = HandleTimeTranslation
								.mgcEventTime((timevarbind));
						String time = "";
						String timezone = "";
						while (mgcEvent_TimeTimeandzone.hasMoreTokens()) {
							time = mgcEvent_TimeTimeandzone.nextToken();
							timezone = mgcEvent_TimeTimeandzone.nextToken();
						}
						notif.setTime(new Date(time));
						notif.setTimeZone(timezone);

					} else {
						setAlarmTime(notif, attributeValue);
					}
				} else if ((moiresult = MoInstanceAttributes
						.handleMOInstanceIfApplicable(attributeName,
								attributeValue, notif, additionaltextBuffer,
								trapbsid)) != null) {

					if (moiresult.equals(MoInstanceAttributes.SNMP_MOI_OK)) {
						trapbsid = moiresult;
					}

				} else if (isStringNotNull(attributeName)
						&& (attributeName.equals("EventType")
								|| attributeName.equals("ericEventType")
								|| attributeName.equals("netspiraEventType")
								|| attributeName.equals("rbnEventType")
								|| attributeName.equals("sppAlarmType")
								|| attributeName.equals("netraalarmEventType")
								|| attributeName.equals("alarmEventType")
								|| attributeName.equals("axdeventType")
								|| attributeName.equals("rbnCardAlarmType")
								|| attributeName.equals("rbnSseEventType")
								|| attributeName.equals("wppalarmEventType")
								|| attributeName.equals("rbnSfpAlarmType")
								|| attributeName.equals("rbnSseDiskEventType")
								|| attributeName.equals("ggsnAlarmName")
								|| attributeName.equals("ecsAlarmEventType")
								|| attributeName.equals("ecsAlarmEventType")
								|| attributeName.equals("mgcEvent_Type")
								|| attributeName.equals("irpalarmEventType")
								|| attributeName.equals("isbladeAlarmClass")
								|| attributeName.equals("ipmsalarmEventType")
								|| attributeName.equals("eventEventType")
								|| attributeName.equals("efwsalarmEventType")
								|| attributeName.equals("ipmsalarmEventType")
								|| attributeName.equals("ipmuxalarmEventType")
								|| attributeName.equals("ipmuxeventEventType")
								|| attributeName.equals("mmsFmSystemAlarmType")
								|| attributeName
										.equals("_OID_VAR_MPT_ALARM_EVENT_TYPE")
								|| attributeName.equals("ocmpEventType")
								|| attributeName
										.equals("eriAlarmActiveEventType")
								|| attributeName
										.equals("omperiAlarmAlertEventType")
								|| attributeName.equals("sppEventType")
								|| attributeName.equals("ossrcalarmEventType")
								|| attributeName.equals("sbgEventType")
								|| attributeName.equals("siteEventType")
								|| attributeName.equals("mptAlarmEventType")
								|| attributeName.equals("granALARM_TYPE")
								|| attributeName.equals("rbnMGEventType")
								|| attributeName.equals("rbnIpSecEventType")
								|| attributeName.equals("rbnChassisAlarmType")
								|| attributeName.equals("mgcAlarm_Type") || attributeName
									.equals("_OID_VAR_ECS_ALARM_EVENT_TYPE"))) {/*
					LOGGER.debug("EventType:" + attributeName + ":"
							+ attributeValue);

					// SPP sppAlarmType
					if (attributeName.equals("sppAlarmType")
							|| attributeName.equals("sppEventType")) {

						notif.setEventType(HandleEventType
								.getsppAlarmType(attributeValue));
					}

					// SITE siteEventType
					if (attributeName.equals("siteEventType")) {

						notif.setEventType(HandleEventType
								.getsiteEventType(attributeValue));
					}

					// NETRA NETRA EVENT TYPE
					if (attributeName.equals("netraalarmEventType")) {

						notif.setEventType(HandleEventType
								.getnetraalarmEventType(attributeValue));
					}
					// MMC mmsFmSystemAlarmType
					else if (attributeName.equals("mmsFmSystemAlarmType")) {
						notif.setEventType(HandleEventType
								.getmmsFmSystemAlarmType(attributeValue));
						notif.setEventType(HandleEventType
								.getmmsFmSystemProbableCause(attributeValue,
										perfdegraded));
					}
					// MGC mgcEvent_Type mgcAlarm_Type
					else if (attributeName.equals("mgcEvent_Type")
							|| attributeName.equals("mgcAlarm_Type")) {

						notif.setEventType(HandleEventType
								.getmgcEventType(attributeValue));
					}
					// JAMBALA
					else if (attributeName.equals("irpalarmEventType")) {

						notif.setEventType(HandleEventType
								.getirpalarmEventType(attributeValue));
					}
					// ISBLADE
					else if (attributeName.equals("isbladeAlarmClass")
							|| attributeName.equals("sbgEventType")) {

						notif.setEventType(HandleEventType
								.getisbladeAlarmClass(attributeValue));
					}
					// IPMS
					else if (attributeName.equals("ipmsalarmEventType")) {

						notif.setEventType(HandleEventType
								.getipmsalarmEventType(attributeValue));
					}
					// wpp
					else if (attributeName.equals("wppalarmEventType")
							|| attributeName.equals("eventEventType")) {

						notif.setEventType(HandleEventType
								.getWPPEventType(attributeValue));
					}
					// efws
					else if (attributeName.equals("efwsalarmEventType")) {

						notif.setEventType(HandleEventType
								.getEFWSEventType(attributeValue));
					}

					else if (attributeName.equals("ggsnAlarmName")) {
						int eventNum = CreateAlarmDateAndTime
								.translateEventTypeGgsn(attributeValue
										.toString());
						String eventString = Integer.valueOf(eventNum)
								.toString();
						LOGGER.debug("GGSN EventType:" + eventString);
						notif.setEventType(eventString);
					} else {

						notif.setEventType(attributeValue.toString());
					}
				}

				else if (isStringNotNull(attributeName)	&& (attributeName.equals("ProbableCause")))
					{		
					notif.setProbableCause(attributeValue);

				}

				else if (isStringNotNull(attributeName)
						&& (attributeName.equals("PerceivedSeverity")))
								
								
							notif.setSeverity(HandleAlarmSeverity
									.getPerceivedSeverity(attributeValue));
						
							notif.setSeverity(Constants.SEV_INDETERMINATE);
						}
					}
	

		else if (isStringNotNull(attributeName)
				&& (attributeName.equals("SpecificProblem") )){
			notif.setSpecificProblem(attributeValue);
		}
		
		
		else if (isStringNotNull(attributeName)&& (attributeName.equalsIgnoreCase("alarmId"))) {
			LOGGER.debug("ExternalEventID:" + attributeName + ":"
					+ attributeValue);
			
			if (attributeName.equals("eventId")) {
			} else if (attributeName.equals("ifIndex")
					|| attributeName.equals("alarmIndex")
					|| attributeName.equals("JNXContentsContainerIndex")
					|| attributeName.equals("jnxRedundancyContentsIndex")
					|| attributeName.equals("ospfRouterId")
					|| attributeName.equals("jnxVpnIfIndex")
					|| attributeName.equals("rbnNECircuitId")
					|| attributeName.equals("ossrcalarmApp")) {
				notif.addAdditionalAttribute(attributeName, attributeValue);
			} else if (attributeName.equals("jnxFruSlot")) {
				notif.addAdditionalAttribute(attributeName, attributeValue);
				notif.setEventAgentId(attributeValue);
			}
			notif.addAdditionalAttribute("additionalText",additionaltextBuffer.toString());
			notif.setExternalEventId(attributeValue);
		}
		
}*/
}

