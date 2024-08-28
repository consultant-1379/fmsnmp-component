package com.ericsson.oss.mediation.translator.model;

import java.util.Enumeration;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adventnet.snmp.snmp2.SnmpAPI;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.SnmpVarBind;
import com.adventnet.snmp.snmp2.UDPProtocolOptions;
import com.ericsson.oss.mediation.cache.model.AlarmTableEntry;
import com.ericsson.oss.mediation.cacheapibean.MibCachingInterfaceBean;
import com.ericsson.snmp.cache.lookup.CacheBeanLookUp;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.constant.TranslationConstant;

public class TranslateAlarm {

	String broadsoftEventTime = "";
	SnmpVarBind timevarbind;
	private int netraAlarmSeverity;
	String ituAlarmEventTypeoid = "";
	private String axd301FaultIdSev = "";
	//private final AxdAlarmAttrs axdAlarmAttrs = new AxdAlarmAttrs();
	NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();

	private static MibCachingInterfaceBean aauServices = CacheBeanLookUp.getMIBCacheBean_Reference();
	public HashMap<String, EventNotification> axdHashMap = new HashMap<String, EventNotification>();


	/**
	 * @return
	 */
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TranslateAlarm.class);

	/*
	 * The PSTU global alarm notification sequence number. If this number is out
	 * of sequence a synch will be ordered. It usually means that alarms have
	 * been lost or that the PSTU restarted.
	 */
	public static final String eriAlarmActiveLastSequenceNo = null;

	/**
	 * Counter for AlarmActiveLastSequenceNo.
	 */
	public long AlarmActiveLastSequenceNo = -1;

	/**
	 * Looking for the Cashing
	 * 
	 * */

	public EventNotification translateAlarm(final SnmpPDU snmppdu,
			final String sourcetype) 
	{

		final EventNotification notif = new EventNotification();

		notif.setSeverity(Constants.SEV_CLEARED);

		try {
			if (extractSnmpInfo(snmppdu, sourcetype, notif)) {				
				LOGGER.info("CommonAAUTranslator Trap Discarded");
				notif.setTranslateResult(TranslateResult.DROP_ALARM);
				return notif;
			} else {
				if (notif.getTranslateResult() == TranslateResult.DROP_AND_SYNCH) {
					notif.setTranslateResult(TranslateResult.DROP_AND_SYNCH);
					return notif;
				} else {
					LOGGER.debug("Forwarding the Alarm in CommonAAUTranslator");
					notif.setTranslateResult(TranslateResult.FORWARD_ALARM);
					LOGGER.info("Eventnotification object in TranslateAlarm:translateAlarm:"+notif.toString());
					return notif;
				}
			}
		} 
		catch(Exception e)
		{
			LOGGER.error("Naming Exception while setTranslateResult::"+e.getMessage());
		}
		return notif;

	}

	/**
	 * Called by for each incoming PDU that matches ComSupervisionOID when
	 * ComSupervisionType = "push". Checks the sequence number.If its out of
	 * sequence then a TranslateResult DROP_AND_SYNCH is return. If the PDU is
	 * in sequence then a TranslateResult DROP_ALARM is returned.
	 * 
	 * @param pdu
	 *            The incoming PDU
	 * @param clearedHeartbeat
	 *            If this trap cleared a heartbeat of not
	 * @param result
	 *            The result status of the translation.
	 * @return always null
	 * @throws Exception
	 */
	public EventNotification translateHeartbeat(final SnmpPDU pdu,
			final String sourcetype) {

		LOGGER.debug("translateHeartbeat()-->");

		final EventNotification eventNotification = new EventNotification();
		eventNotification.setTranslateResult(TranslateResult.DROP_ALARM);
		if ((pdu.getCommand() == SnmpAPI.TRP_REQ_MSG)
				|| (pdu.getCommand() == SnmpAPI.TRP2_REQ_MSG)
				|| (pdu.getVersion() == SnmpAPI.SNMP_VERSION_3)) {
			for (final Enumeration e = pdu.getVariableBindings().elements(); e
					.hasMoreElements();) {
				final SnmpVarBind snmpvarbind = (SnmpVarBind) e.nextElement();
				final String oid = snmpvarbind.getObjectID().toString();
				final String ericHbOid = "..1.3.6.1.4.1.193.183.4.1.3.3";
				String oidName = "";
				if (oid.startsWith(ericHbOid)) {
					oidName = aauServices.getAttributeName(ericHbOid,
							sourcetype);
				} else {
					oidName = aauServices.getAttributeName(oid, sourcetype);
				}

				if ((oidName != null)
						&& oidName.equals("AlarmActiveLastSequenceNo")) {
					if (!checkAlarmActiveLastSequenceNo(snmpvarbind)) {
						eventNotification
						.setTranslateResult(TranslateResult.DROP_AND_SYNCH);

					}
					break;
				}
			}
		}

		return eventNotification;
	}

	/**
	 * Used to check the notification Id.
	 * 
	 * The notification sequence can be out of sequence for as long as heartbeat
	 * interval at startup since there is not value to compare with until the
	 * first trap is received.
	 * 
	 * @param snmpvarbind
	 *            The variable binding containing the notification id value.
	 * @param clearedHeartbeat
	 *            If this trap cleared a heartbeat or not
	 * @return true if the notification Id is in sequence, false otherwise.
	 */
	private boolean checkAlarmActiveLastSequenceNo(final SnmpVarBind snmpvarbind) {
		LOGGER.debug("checkAlarmActiveLastSequenceNo-->");

		final String AlarmActiveLastSequenceNoString = snmpvarbind
				.getVariable().toString();
		final long oldAlarmActiveLastSequenceNo = AlarmActiveLastSequenceNo;
		AlarmActiveLastSequenceNo = Long
				.parseLong(AlarmActiveLastSequenceNoString);
		if ((oldAlarmActiveLastSequenceNo == -1)) {

			return true;
		} else if (AlarmActiveLastSequenceNo == 0) {

			LOGGER.debug("checkAlarmActiveLastSequenceNo() AlarmActiveLastSequenceNo = 0 PSTU Agent restarted!");

			return false;
		} else if ((oldAlarmActiveLastSequenceNo + 1) != AlarmActiveLastSequenceNo) {

			LOGGER.debug("checkAlarmActiveLastSequenceNo() AlarmActiveLastSequenceNo out of sequence, oldAlarmActiveLastSequenceNo = "
					+ oldAlarmActiveLastSequenceNo
					+ ", new AlarmActiveLastSequenceNo="
					+ AlarmActiveLastSequenceNo);

			return false;
		}

		LOGGER.debug("checkAlarmActiveLastSequenceNo<--");
		return true;
	}

	/**
	 * Determines if the received Trap is generic or Enterprise specific.
	 * 
	 * @param snmppdu
	 *            Incoming SnmpPDU Object.snm
	 * @param notif
	 *            EventNotification Object
	 * @return true if to discard the Trap, otherwise false.
	 */

	public boolean extractSnmpInfo(final SnmpPDU snmppdu,
			final String sourcetype, EventNotification notif){
		notif.setFmEventType(Constants.NOTIF_TYPE_ALARM);

		final StringBuffer additionaltextBuffer = new StringBuffer();
		AlarmTableEntry alarmTable = new AlarmTableEntry();
		AlarmTableEntry varbindTable = new AlarmTableEntry();

		Map<String, String> additionalText = new HashMap<String, String>();
		Map<String, String> varbindAdditionalText = new HashMap<String, String>();
		final Map<String, String> supportedAttributeOIDValues = new HashMap<String, String>();

		String additionalAttributeValue = "", additionalAttributeName = "", trapOID = "", attributeName = "", varbindOID = "", varbindValue = "", varbindName = "", varbindType = "", severityToken = "", severityNumber = "", severityValue = "", attributeNumber = "", attributeValue = "", attributeToken = "", varOutput="";

		StringTokenizer severityTokenizer = null, severityMapping = null, additionalAttributeTokenizer = null, attributeMapping = null;

		boolean isSNMPV1Trap = false;

		/**
		 * The SnmpPDU class represents the SNMP PDU used in protocol
		 * operations. NoteTrap Translation method for V1 and V2C/V3 is
		 * different since the PDU format is different
		 **/
		if ((snmppdu.getCommand() == SnmpAPI.TRP2_REQ_MSG)
				|| snmppdu.getCommand() == SnmpAPI.TRP_REQ_MSG) {
			if ((snmppdu.getCommand() == SnmpAPI.TRP2_REQ_MSG)
					|| (snmppdu.getVersion() == SnmpAPI.SNMP_VERSION_3))

			{
				LOGGER.debug(getClass() + " >> SNMPV2C/V3 Notification");
				final String agentIP = ((UDPProtocolOptions) (snmppdu.getProtocolOptions())).getRemoteAddressAsString();

				if (snmppdu.getVersion() == SnmpAPI.SNMP_VERSION_3) 
				{
					LOGGER.debug("SNMPV3 trap:::");
					notif.addAdditionalAttribute(
							AdditionalAttrConstants.SNMP_SPECIFIC, "null");
					notif.addAdditionalAttribute(
							AdditionalAttrConstants.SNMP_IP_ADDRESS, agentIP);
					notif.addAdditionalAttribute(
							AdditionalAttrConstants.SNMP_VERSION, "SNMPV3");
				} else
				{
					LOGGER.debug("SNMPV2 trap:::");
					notif.addAdditionalAttribute(
							AdditionalAttrConstants.SNMP_SPECIFIC, "null");
					notif.addAdditionalAttribute(
							AdditionalAttrConstants.SNMP_IP_ADDRESS, agentIP);
					notif.addAdditionalAttribute(
							AdditionalAttrConstants.SNMP_VERSION, "SNMPV2");
				}

				notif.addAdditionalAttribute(
						AdditionalAttrConstants.SNMP_ENTERPRISE, "True");
				notif.addAdditionalAttribute(
						AdditionalAttrConstants.SNMP_GENERIC, "False");
			} else if (snmppdu.getCommand() == SnmpAPI.TRP_REQ_MSG)
			{
				LOGGER.debug(" :CommonAAUTranslator >> SNMPV1 Trap");
				LOGGER.debug("Trap received from" + snmppdu.getAgentAddress()
						+ ", community" + snmppdu.getCommunity());
				/**
				 * Gets the enterprise field of the Trap PDU which is of type
				 * SnmpOID, it is then converted to a String.
				 * 
				 * Get the generic type of the trap. 0 - 5 Standard Traps. 6
				 * Enterprise Specific Traps.
				 * 
				 * Get the specific Trap number.
				 */

				final String EntOID = snmppdu.getEnterprise().toString();
				final int trapType = snmppdu.getTrapType();
				final int specificType = snmppdu.getSpecificType();
				final String agentIP = snmppdu.getAgentAddress()
						.getHostAddress().toString();

				isSNMPV1Trap = true;
				// trapOID = EntOID+"/"+specificType;

				if (trapType == 6) {
					/** Specific SNMP V1 traps **/
					trapOID = EntOID + ".0." + specificType;
				} else {
					/** Generic SNMP V1 traps **/
					trapOID = EntOID + "." + trapType + ".1";
				}

				LOGGER.debug(" Enterprise Specific " + EntOID);
				LOGGER.debug(" Specific Type " + specificType);
				LOGGER.debug(" Generic Trap Type " + trapType);
				LOGGER.debug(" SNMP V1 Trap OID " + trapOID);

				/*// handling the tigris alarm
				if (trapType == 6 && EntOID.startsWith(".1.3.6.1.4.1.5.1.1")) {
					// buildTigrisAlarm(snmppdu, notif);
				}*/

				/**
				 * Setting Specific Type, IPAddress and SNMP Version for
				 * notification
				 */
				final String specType = String.valueOf(specificType);

				notif.addAdditionalAttribute(AdditionalAttrConstants.SNMP_TRAP_OID, EntOID);
				notif.addAdditionalAttribute(AdditionalAttrConstants.SNMP_SPECIFIC, specType);
				notif.addAdditionalAttribute(AdditionalAttrConstants.SNMP_IP_ADDRESS, agentIP);
				// TODOCheck if it should always be SNMPV1.
				notif.addAdditionalAttribute(AdditionalAttrConstants.SNMP_VERSION, "SNMPV1");
				notif.addAdditionalAttribute(AdditionalAttrConstants.SNMP_ENTERPRISE, "True");
				notif.addAdditionalAttribute(AdditionalAttrConstants.SNMP_GENERIC, "False");

			}
			try{

				for (final Enumeration<SnmpVarBind> e = snmppdu.getVariableBindings().elements(); e
						.hasMoreElements();) {

					final SnmpVarBind binding = (SnmpVarBind) e.nextElement();
					varbindOID = "";
					varbindValue = "";
					varOutput = "";

					/**
					 * SNMP Variable Binding. It contains an object identifier and a
					 * SnmpVar.
					 */

					varbindOID = binding.getObjectID().toString();
					varOutput = binding.toTagString();

					final StringTokenizer varTokenizer = new StringTokenizer(varOutput,"\n");
					varTokenizer.nextToken();

					varbindValue = varTokenizer.nextToken();
					
					
					if(varbindValue != null){
						varbindValue=varbindValue.substring(varbindValue.indexOf(":")+1).trim();
						LOGGER.debug("varbindValue::"+varbindValue);
					}
					LOGGER.debug(" varbindOID => " + varbindOID	+ " varbindValue => " + varbindValue);


					if ((isStringNotNull(varbindOID) && varbindOID
							.equals(".1.3.6.1.6.3.1.1.4.1.0")) && !isSNMPV1Trap) {
						/** Get the trap OID using SNMP OID */

						trapOID = varbindValue;
						notif.addAdditionalAttribute(TranslationConstant.SNMP_TRAP_OID, trapOID);

						LOGGER.debug(" SNMP V2/V3 Trap OID " + trapOID);

					}
					if ((isStringNotNull(varbindOID)
							&& varbindOID.equals(".1.3.6.1.6.3.1.1.4.1.0") && varbindValue
							.startsWith(".1.3.6.1.4.1.9070")) && isSNMPV1Trap) {
						notif = TruetimeAlarmHandler.buildSymmAlarm(varbindOID,
								varbindValue, notif);
					}
					else if (isStringNotNull(varbindOID) && (varbindOID.startsWith(".1.3.6.1.2.1.88.2.0.1"))) { // PROCESSING
						// DISMAN-EVENT-MIB
						// NETRA

						if (nodeMiscAttrs.getMteHotTrigger().equalsIgnoreCase("High CPU load")) {
							notif.setProbableCause("310"); // Cpu Cycles Limit
							// Exceeded
							notif.setSpecificProblem("CPU Utilization Threshold Reached.");

						} else if (nodeMiscAttrs.getMteHotTrigger()
								.equalsIgnoreCase("High swap usage")) {
							notif.setProbableCause("332"); // Out of Memory
							notif.setSpecificProblem("Memory Utilization Threshold Reached.");
						}
						notif.addAdditionalAttribute("additionalText",
								nodeMiscAttrs.getMteHotTrigger());
						// ituAlarmEventType
					} else {
						// Broadsoft Event Time,IRP TIME Broadsoft Event Time,IRP
						// TIME
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

						attributeName = aauServices.getAttributeName(varbindOID,
								sourcetype);

						while (isStringNull(attributeName)&& isStringNotNull(varbindOID)) {
							varbindOID = varbindOID.substring(0,varbindOID.lastIndexOf("."));
							
							attributeName = aauServices.getAttributeName(varbindOID, sourcetype);
						}

						LOGGER.debug(" attributeName:" + attributeName
								+ " varbindOID:" + varbindOID);

						/**
						 * Put the snmp varbinds to List supportedAttributeOIDValues
						 * will have attributeName => varbindValue pairs
						 */

						if (isStringNotNull(varbindOID)
								&& isStringNotNull(attributeName)) {
							varbindTable = aauServices.getAlarmTableEntry(
									varbindOID, sourcetype);

							if (varbindTable != null) {
								varbindAdditionalText = varbindTable.additionalValues;

								if (varbindAdditionalText != null
										&& varbindAdditionalText.size() > 0) {
									final Iterator itAdditionalText = varbindAdditionalText
											.entrySet().iterator();

									while (itAdditionalText.hasNext()) {
										final Map.Entry<String, String> pairs = (Map.Entry<String, String>) itAdditionalText
												.next();

										varbindName = pairs.getKey();
										varbindType = pairs.getValue();

										LOGGER.debug(" varbindName "
												+ varbindName + " varbindType "
												+ varbindType);

										if (isStringNotNull(varbindName)
												&& isStringNotNull(varbindType)
												&& varbindType.equals("ByteArray")) {
											final byte[] theByteArray = binding
													.getVariable().toString()
													.getBytes();
											varbindValue = convertOctalToString(theByteArray);
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

							LOGGER.debug(" attributeName " + attributeName
									+ " added to map with varbindValue "
									+ varbindValue);

							supportedAttributeOIDValues.put(attributeName,
									varbindValue);

						}

					}
				}
			}catch(Exception e){
				LOGGER.error("Exception while enumerating the varbinds"+e.getMessage());
			}

			/** Check if the trap OID is supported by AAU */

			alarmTable = aauServices.getAlarmTableEntry(trapOID, sourcetype);

			if (alarmTable != null) {
				/** get the FmAlarmTable(Alarm hardcoded values) using FM API */

				LOGGER.debug(" setting hardcoded values for Trap " + trapOID);

				if (isStringNotNull(alarmTable.getEventType())) {
					notif.setEventType(alarmTable.getEventType());
				}

				if (isStringNotNull(alarmTable.getProbableCause())) {
					notif.setProbableCause(alarmTable.getProbableCause());
				}

				if (alarmTable.getPerceivedSeverity() != null) {
					notif.setSeverity(alarmTable.getPerceivedSeverity());
				}

				if (alarmTable.getNotificationType() != null) {
					notif.setFmEventType(alarmTable.getNotificationType());
				}

				if (isStringNotNull(alarmTable.getSpecificProblem())) {
					notif.setSpecificProblem(alarmTable.getSpecificProblem());
				}

				additionalText = alarmTable.additionalValues;

				if (additionalText != null && additionalText.size() > 0) {
					final Iterator iterator = additionalText.entrySet()
							.iterator();

					while (iterator.hasNext()) {
						final Map.Entry<String, String> pairs = (Map.Entry<String, String>) iterator
								.next();

						additionalAttributeName = pairs.getKey();
						additionalAttributeValue = pairs.getValue();

						if (isStringNotNull(additionalAttributeValue)
								&& additionalAttributeValue
								.equals("DropAndSync")) {
							notif.setTranslateResult(TranslateResult.DROP_AND_SYNCH);
							LOGGER.debug(" TranslateResult set to Drop and Sync");
						} else if (isStringNotNull(attributeName)
								&& attributeName
								.equalsIgnoreCase("additionalText")) {
							additionaltextBuffer.append(attributeName + ":"
									+ attributeValue);
						} else {

							notif.addAdditionalAttribute(
									additionalAttributeName + ":",
									additionalAttributeValue + "\n");
						}
					}
				}

				/**
				 * Get mapping between the given OIDs and the corresponding
				 * Attributes Set the attribute values dynamically
				 */


				setAttributesForAlarm(notif, snmppdu,
						supportedAttributeOIDValues, additionaltextBuffer);

				return false;
			}
			return false;
		} else {
			LOGGER.info(getClass()+ "  :Not a SNMPV1/SNMPV2c/SNMPV3 Notification "+ trapOID);

			return true;
		}

	}

	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void setAttributesForAlarm(final EventNotification notif,
			final SnmpPDU snmppdu, final Map supportedAttributeOIDValues,
			final StringBuffer additionaltextBuffer) {

		String trapOID="";
		try {
			LOGGER.debug("inside setAttributesForAlarm:::");
			String trapbsid = ""; // isblade
			String moiresult;
			String attributeName = "", attributeValue = "";

			if (supportedAttributeOIDValues != null
					&& supportedAttributeOIDValues.size() > 0) {

				final Iterator iterator = supportedAttributeOIDValues.entrySet().iterator();

				while (iterator.hasNext()) {
					final Map.Entry<String, String> pairs = (Map.Entry<String, String>) iterator.next();

					attributeName = pairs.getKey();
					attributeValue = pairs.getValue();

					if (nodeMiscAttrs.translateTmosAttrs(attributeName,
							attributeValue, notif, ituAlarmEventTypeoid)) {
						continue;
					}
					if (EventTimeAttrs.translateEventTime(attributeName,
							attributeValue, notif)) {
						continue;
					}
					if ((moiresult = MoInstanceAttributes
							.handleMOInstanceIfApplicable(attributeName,
									attributeValue, notif, additionaltextBuffer,
									trapbsid)) != null) {
						if (!moiresult.equals(MoInstanceAttributes.SNMP_MOI_OK)) {
							trapbsid = moiresult;
						}
						continue;
					}						

					if (EventTypeAttrs.translateEventTypeAttr(attributeName,
							attributeValue, notif, nodeMiscAttrs.isPerfdegraded())) {
						continue;
					}
					if (ProbableCauseAttrs.translateProbableCause(attributeName,
							attributeValue, notif)) {
						continue;
					}

					if (PerceivedSeverityAttrs.translatePerceivedSeverityAttr(
							attributeName, attributeValue, notif, axd301FaultIdSev)) {
						continue;
					}

					if (SpecificProblemAttrs.translateSpecificProblem(
							attributeName, attributeValue, notif,
							additionaltextBuffer, netraAlarmSeverity)) {
						continue;
					}

					if (EventIdAttrs.translateEventId(attributeName,
							attributeValue, notif)) {
						continue;
					}
					if(AlarmAttributeConstants.setadditionalTextAttributes(attributeName,
							attributeValue, notif,additionaltextBuffer)){
						continue;

					}		

				}
				notif.addAdditionalAttribute("additionalText",
						additionaltextBuffer.toString());


			}

			if(snmppdu.getCommand() == SnmpAPI.TRP_REQ_MSG)
				trapOID = notif.getAdditionalAttribute("Enterprise");
			else
				trapOID = notif.getAdditionalAttribute(TranslationConstant.SNMP_TRAP_OID);



			if((trapOID.startsWith(HandleOtherAlarmAttribute._AXD301EASGCP)||trapOID.startsWith(HandleOtherAlarmAttribute._AXD301EASMLG)
					||trapOID.startsWith(HandleOtherAlarmAttribute._AXD301EASMIG)||trapOID.startsWith(HandleOtherAlarmAttribute._AXD301IMA)
					||trapOID.startsWith(HandleOtherAlarmAttribute._AXD301PNNIMIB)||trapOID.startsWith(HandleOtherAlarmAttribute._AXD301EQMSWSMIB)
					||trapOID.startsWith(HandleOtherAlarmAttribute._AXD301EQMIFSWSMIB))&& notif.getFmEventType()!="ERROR"){
				LOGGER.debug("Putting the AXD alarm notification into HashMap");
				axdHashMap.put(notif.getExternalEventId(), notif);
			}else if(trapOID.startsWith(HandleOtherAlarmAttribute._AXDCLEAROID)){
				LOGGER.debug("Getting the AXD alarm notification data from HashMap");
				EventNotification axdNotif= new EventNotification();
				axdNotif= axdHashMap.get(notif.getExternalEventId());

				final String axdClearSP=axdNotif.getSpecificProblem();
				final String axdClearPC=axdNotif.getProbableCause();
				final String axdClearET=axdNotif.getEventType();
				final String axdClearPT=axdNotif.getAdditionalAttribute("additionalText");

				notif.setSpecificProblem(axdClearSP);
				notif.setProbableCause(axdClearPC);
				notif.setEventType(axdClearET);
				notif.addAdditionalAttribute("additionalText", axdClearPT);
			}
		} catch (Exception e) {
			LOGGER.error("Exception while setAttributesForAlarm:"+e.getMessage());
		}
	}


	public boolean isStringNotNull(final String stringToCheck) {
		boolean isStringNotNull = false;

		if (stringToCheck != null && stringToCheck.length() > 0) {
			isStringNotNull = true;
		}

		return isStringNotNull;
	}

	public boolean isStringNull(final String stringToCheck) {
		boolean isStringNull = false;

		if (stringToCheck == null || stringToCheck.length() <= 0
				|| stringToCheck.equals("null")) {
			isStringNull = true;
		}

		return isStringNull;
	}

	public String convertOctalToString(byte[] theByteArray) {
		for (int i = 0; i < theByteArray.length; i++) {
			if (((byte) theByteArray[i] >= (byte) 0x00)
					&& ((byte) theByteArray[i] < (byte) 0x20)) {
				theByteArray[i] = '0';
			}
		}

		final String stringValue = new String(theByteArray);

		return stringValue;
	}

}
