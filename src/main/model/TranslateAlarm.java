package com.ericsson.oss.mediation.translator.model;

import java.text.SimpleDateFormat;
import java.util.*;

import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adventnet.snmp.snmp2.*;
import com.ericsson.oss.mediation.cache.model.AlarmTableEntry;
import com.ericsson.oss.mediation.cacheapi.MibCachingInterface;
import com.ericsson.oss.mediation.translator.constant.TranslateResult;
import com.ericsson.oss.mediation.translator.model.alarm.handlers.*;
import com.ericsson.oss.mediation.translator.model.handlers.*;
import com.ericsson.snmp.cache.lookup.CacheBeanLookUp;

public class TranslateAlarm {

	String broadsoftEventTime = "";
	SnmpVarBind timevarbind;
	private String mteHotTrigger = "";
	private int netraAlarmSeverity;
	String ituAlarmEventTypeoid = "";
	private boolean perfdegraded = false;
	private String axd301FaultIdSev = "";
	private String axdsecOperation = "";
	private String axdlogRecAcStatus = "";
	private String axdBgpPeerLastErrorReason = "";
	private String axdisisISAdjState = "";
	private String axdmigLoopbackStatus = "";
	private String axdgcpLinkReasonCode = "";
	private String axdpingNotificationType = "";
	private String axdmrErrorCode = "";
	private String axdperfFileFcode = "";
	private String axdperfInfo = "";
	private String axdmplsXCAdminStatus = "";
	private String axdrsvpIfOperStatus = "";
	private String axd301ifName = "";
	private String axd301dnaInterfaceEnteringMethod = "";
	private String axd301dnaInterfaceMyNeighbourIfName = "";
	private String axd301dnaInterfaceMyNeighbourAddress = "";
	private String spvcOperationIndicator = "";
	private String axdmplsIfOperStatus = "", axdmplsLdpSesState, axdifoper,
			axdatmfVccOperStatus;

	private static final String[] gsnReportEvent = { "nocSmallLocalRestart",
			"nocSmallRestart", "nocLargeRestart", "nocReload", "nocCmRestart" };
	// ArrayList<String> gsnReportableEvent = new ArrayList<String>();
	private static final String[] addtionalTextArray = {
			"ggsnAlarmDescription", "AlarmMajorType",
			"AlarmActiveLastSequenceNo", "eriAlarmNObjAdditionalText",
			"eriAlarmNObjMoreAdditionalText", "eriAlarmNObjResourceId",
			"eriAlarmAlertMajorType", "eriAlarmAlertLastSequenceNo",
			"ecsAlarmAdditionalText", "mptadditionalText", "jnxEventTrapDescr",
			"rbnMGEventSender", "rbnMGEventInformation", "rbnFanStatus",
			"rbnPowerStatus", "rbnRadiusReason" };
	private final Collection addtionalTextCollection = Arrays
			.asList(addtionalTextArray);

	public static final SimpleDateFormat TIMEFORMATTER = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	public static final Calendar LOCAL = new GregorianCalendar(
			TimeZone.getDefault());
	private static MibCachingInterface aauServices = CacheBeanLookUp
			.getMIBCacheBean_Reference();

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
	 * Implementation of the inherited abstract method from SUATranslator. This
	 * method translates an incoming object (e.g. Trap) to a NotificationT that
	 * will be pushed over SUPI to FM kernel.
	 * 
	 * @param snmppdu
	 *            Incoming SnmpPDU Object.
	 * @return {@link EventNotification} object.
	 * 
	 * 
	 */
	/**
	 * Looking for the Cashing
	 * 
	 * */

	public EventNotification translateAlarm(final SnmpPDU snmppdu,
			final String sourcetype) {
		LOGGER.debug("TRANSLATE THE SNMP PDU");
		final EventNotification notif = new EventNotification();

		notif.setSeverity(Constants.SEV_CLEARED);

		LOGGER.debug(getClass() + ":: CommonAAUTranslator received trap");

		try {
			if (extractSnmpInfo(snmppdu, sourcetype, notif)) {
				LOGGER.debug(getClass()
						+ ":: CommonAAUTranslator Trap Discarded");
				notif.setTranslateResult(TranslateResult.DROP_ALARM);
				return notif;
			} else {
				if (notif.getTranslateResult() == TranslateResult.DROP_AND_SYNCH) {
					LOGGER.debug("Calling Drop&Sync ==>");
					notif.setTranslateResult(TranslateResult.DROP_AND_SYNCH);
					return notif;
				} else {
					LOGGER.debug("Forwarding the Alarm in CommonAAUTranslator");
					notif.setTranslateResult(TranslateResult.FORWARD_ALARM);
					return notif;
				}
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception :" + e.getStackTrace());
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
		try {
			new InitialContext();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			LOGGER.error("Exception :" + e.getStackTrace());
		}

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
	 * @throws NamingException
	 */

	public boolean extractSnmpInfo(final SnmpPDU snmppdu,
			final String sourcetype, EventNotification notif)
			throws NamingException {

		final StringBuffer additionaltextBuffer = new StringBuffer();

		AlarmTableEntry alarmTable = new AlarmTableEntry();
		AlarmTableEntry varbindTable = new AlarmTableEntry();

		Map<String, String> additionalText = new HashMap<String, String>();
		Map<String, String> varbindAdditionalText = new HashMap<String, String>();
		final Map<String, String> supportedAttributeOIDValues = new HashMap<String, String>();
		// List<String> attributeOIDList = new ArrayList<String>();

		String additionalAttributeValue = "", additionalAttributeName = "", trapOID = "", attributeName = "", varbindOID = "", varbindValue = "", varbindName = "", varbindType = "", severityToken = "", severityNumber = "", severityValue = "", attributeNumber = "", attributeValue = "", attributeToken = "";

		StringTokenizer severityTokenizer = null, severityMapping = null, additionalAttributeTokenizer = null, attributeMapping = null;

		boolean isSNMPV1Trap = false;

		/**
		 * The SnmpPDU class represents the SNMP PDU used in protocol
		 * operations. Note: Trap Translation method for V1 and V2C/V3 is
		 * different since the PDU format is different
		 **/
		if ((snmppdu.getCommand() == SnmpAPI.TRP2_REQ_MSG)
				|| snmppdu.getCommand() == SnmpAPI.TRP_REQ_MSG) {
			if ((snmppdu.getCommand() == SnmpAPI.TRP2_REQ_MSG)
					|| (snmppdu.getVersion() == SnmpAPI.SNMP_VERSION_3))// SNMPV2C/V3

			{
				LOGGER.debug(getClass() + " >> SNMPV2C/V3 Notification");

				final String agentIP = ((UDPProtocolOptions) (snmppdu
						.getProtocolOptions())).getRemoteAddressAsString();

				LOGGER.debug("agentIp is:" + agentIP);

				if (snmppdu.getVersion() == SnmpAPI.SNMP_VERSION_3) // SNMPV3
				// Trap
				{
					notif.addAdditionalAttribute(
							AdditionalAttrConstants.SNMP_SPECIFIC, "null");
					notif.addAdditionalAttribute(
							AdditionalAttrConstants.SNMP_IP_ADDRESS, agentIP);
					notif.addAdditionalAttribute(
							AdditionalAttrConstants.SNMP_VERSION, "SNMPV3");
				} else // SNMPV2 Trap
				{
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
			} else if (snmppdu.getCommand() == SnmpAPI.TRP_REQ_MSG)// SNMPV1
			// Trap
			{
				LOGGER.debug(" :: CommonAAUTranslator >> SNMPV1 Trap");
				LOGGER.debug("Trap received from: " + snmppdu.getAgentAddress()
						+ ", community: " + snmppdu.getCommunity());
				LOGGER.debug("Enterprise: " + snmppdu.getEnterprise());
				LOGGER.debug("Agent: " + snmppdu.getAgentAddress());
				LOGGER.debug("Trap Type: " + snmppdu.getTrapType());
				LOGGER.debug("Specific Type: " + snmppdu.getSpecificType());
				LOGGER.debug("Time: " + snmppdu.getUpTime() + "\nVARBINDS:");
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

				LOGGER.debug(" Enterprise Specific : " + EntOID);
				LOGGER.debug(" Specific Type : " + specificType);
				LOGGER.debug(" Generic Trap Type : " + trapType);
				LOGGER.debug(" SNMP V1 Trap OID : " + trapOID);

				// handling the tigris alarm
				if (trapType == 6 && EntOID.startsWith(".1.3.6.1.4.1.5.1.1")) {
					// buildTigrisAlarm(snmppdu, notif);
				}

				/**
				 * Setting Specific Type, IPAddress and SNMP Version for
				 * notification
				 */
				final String specType = String.valueOf(specificType);

				notif.addAdditionalAttribute(
						AdditionalAttrConstants.SNMP_TRAP_OID, EntOID);
				notif.addAdditionalAttribute(
						AdditionalAttrConstants.SNMP_SPECIFIC, specType);
				notif.addAdditionalAttribute(
						AdditionalAttrConstants.SNMP_IP_ADDRESS, agentIP);
				// TODO: Check if it should always be SNMPV1.
				notif.addAdditionalAttribute(
						AdditionalAttrConstants.SNMP_VERSION, "SNMPV1");
				notif.addAdditionalAttribute(
						AdditionalAttrConstants.SNMP_ENTERPRISE, "True");
				notif.addAdditionalAttribute(
						AdditionalAttrConstants.SNMP_GENERIC, "False");

			}

			for (final Enumeration e = snmppdu.getVariableBindings().elements(); e
					.hasMoreElements();) {

				final SnmpVarBind binding = (SnmpVarBind) e.nextElement();
				varbindOID = "";
				varbindValue = "";

				/**
				 * SNMP Variable Binding. It contains an object identifier and a
				 * SnmpVar.
				 */

				varbindOID = binding.getObjectID().toString();
				varbindValue = binding.getVariable().toString();

				LOGGER.debug(" varbindOID => " + varbindOID
						+ " varbindValue => " + varbindValue);

				LOGGER.debug("isStringNotNull(varbindOID):"
						+ isStringNotNull(varbindOID));
				LOGGER.debug("varbindOID.equals(.1.3.6.1.6.3.1.1.4.1.0):"
						+ varbindOID.equals(".1.3.6.1.6.3.1.1.4.1.0"));
				LOGGER.debug("!isSNMPV1Trap:)" + !isSNMPV1Trap);

				if ((isStringNotNull(varbindOID) && varbindOID
						.equals(".1.3.6.1.6.3.1.1.4.1.0")) && !isSNMPV1Trap) {
					/** Get the trap OID using SNMP OID */

					trapOID = varbindValue;
					notif.addAdditionalAttribute("SNMPTrapOID", trapOID);

					LOGGER.debug(" SNMP V2/V3 Trap OID : " + trapOID);

				}
				// check for the true time
				if ((isStringNotNull(varbindOID)
						&& varbindOID.equals(".1.3.6.1.6.3.1.1.4.1.0") && varbindValue
							.startsWith(".1.3.6.1.4.1.9070")) && isSNMPV1Trap) {
					// handle the buildSymmAlarm for the TRUETIME
					notif = TruetimeAlarmHandler.buildSymmAlarm(varbindOID,
							varbindValue, notif);
					// need to break here
					break;
				}

				else if (isStringNotNull(varbindOID)
						&& (varbindOID.startsWith(".1.3.6.1.2.1.88.2.0.1"))) { // PROCESSING
					// DISMAN-EVENT-MIB
					// NETRA

					if (mteHotTrigger.equalsIgnoreCase("High CPU load")) {
						notif.setProbableCause("310"); // Cpu Cycles Limit
						// Exceeded
						notif.setSpecificProblem("CPU Utilization Threshold Reached.");

					} else if (mteHotTrigger
							.equalsIgnoreCase("High swap usage")) {
						notif.setProbableCause("332"); // Out of Memory
						notif.setSpecificProblem("Memory Utilization Threshold Reached.");
					}
					notif.addAdditionalAttribute("additionalText",
							mteHotTrigger);
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

					if (isStringNotNull(varbindOID)
							&& (varbindOID
									.startsWith(HandleOtherAlarmAttribute.AXDSEV))) {
						axd301FaultIdSev = varbindOID;

					}
					// !!!

					attributeName = aauServices.getAttributeName(varbindOID,
							sourcetype);

					while (isStringNull(attributeName)
							&& isStringNotNull(varbindOID)) {
						varbindOID = varbindOID.substring(0,
								varbindOID.lastIndexOf("."));
						LOGGER.debug("varbindOID:" + varbindOID + "last index:"
								+ varbindOID.lastIndexOf(".")
								+ "attributeName:" + attributeName);
						attributeName = aauServices.getAttributeName(
								varbindOID, sourcetype);
						LOGGER.debug("varbindOID:" + varbindOID
								+ "attributeName:" + attributeName);
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

									LOGGER.debug(" varbindName : "
											+ varbindName + " varbindType : "
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

						if (attributeName.equals("AlarmTime")) {
							/**
							 * Passing a different param for calculating Alarm
							 * Time supportedAttributeOIDValues will have
							 * attributeName => attributeValue/varbindValue
							 * pairs
							 */

							supportedAttributeOIDValues.put(attributeName,
									binding.toString());
						} else {
							LOGGER.debug(" attributeName : " + attributeName
									+ " added to map with varbindValue : "
									+ varbindValue);

							supportedAttributeOIDValues.put(attributeName,
									varbindValue);
						}
					}

				}
			}

			/** Check if the trap OID is supported by AAU */

			alarmTable = aauServices.getAlarmTableEntry(trapOID, sourcetype);

			if (alarmTable != null) {
				/** get the FmAlarmTable(Alarm hardcoded values) using FM API */

				LOGGER.debug(" setting hardcoded values for Trap : " + trapOID);

				if (isStringNotNull(alarmTable.eventType)) {
					notif.setEventType(alarmTable.eventType);
				}

				if (isStringNotNull(alarmTable.probableCause)) {
					notif.setProbableCause(alarmTable.probableCause);
				}

				if (alarmTable.perceivedSeverity != null) {
					notif.setSeverity(alarmTable.perceivedSeverity);
				}

				if (alarmTable.notificationType != null) {
					notif.setFmEventType(alarmTable.notificationType);
				}

				if (isStringNotNull(alarmTable.specificProblem)) {
					notif.setSpecificProblem(alarmTable.specificProblem);
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
			LOGGER.debug(getClass()
					+ "  :: Not a SNMPV1/SNMPV2c/SNMPV3 Notification : "
					+ trapOID);

			return true;
		}

	}

	public void setAttributesForAlarm(EventNotification notif,
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

				/** Setting the attribute values dynamically */

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
									.equals("_OID_VAR_ECS_ALARM_EVENT_TYPE"))) {
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

				else if (isStringNotNull(attributeName)
						&& (attributeName.equals("ProbableCause")
								|| attributeName.equals("ericProbableCause")
								|| attributeName.equals("netspiraProbablcause")
								|| attributeName.equals("wppalarmPC")
								|| attributeName.equals("rbnProbableCause")
								|| attributeName
										.equals("rbnCardAlarmProbableCause")
								|| attributeName
										.equals("rbnSseAlarmProbableCause")
								|| attributeName.equals("alarmPC")
								|| attributeName
										.equals("sppAlarmProbableCause")
								|| attributeName.equals("srdprobableCause")
								|| attributeName
										.equals("rbnSfpAlarmProbableCause")
								|| attributeName
										.equals("rbnSseDiskAlarmProbableCause")
								|| attributeName
										.equals("ecsAlarmProbableCause")
								|| attributeName.equals("accAlarmCause")
								|| attributeName.equals("irpalarmPC")
								|| attributeName
										.equals("omsTrapContentsProbableCause")
								|| attributeName
										.equals("isbladeTrapAlarmX733Cause")
								|| attributeName.equals("ipmsalarmPC")
								|| attributeName
										.equals("efwsalarmProbableCause2")
								|| attributeName
										.equals("efwsalarmProbableCause")
								|| attributeName.equals("ipmsalarmPC")
								|| attributeName
										.equals("Broadsoft Probable Cause")
								|| attributeName.equals("ipmuxalarmPC")
								|| attributeName
										.equals("ipfmAlarmprobablecause")
								|| attributeName.equals("d_trapCause")
								|| attributeName.equals("ivri_trapCause")
								|| attributeName
										.equals("mgcAlarm_Probablecause")
								|| attributeName.equals("netraalarmPC")
								|| attributeName
										.equals("_OID_VAR_MPT_PROBABLE_CAUSE")
								|| attributeName
										.equals("eriAlarmActiveProbableCause")
								|| attributeName.equals("ocmpProbablecause")
								|| attributeName.equals("ocmpo_probableCause")
								|| attributeName
										.equals("omperiAlarmAlertProbableCause")
								|| attributeName.equals("ossrcalarmPC")
								|| attributeName.equals("sppProbableCause")
								|| attributeName.equals("sbgProbableCause")
								|| attributeName.equals("siteProbableCause")
								|| attributeName
										.equals("mptAlarmProbableCause")
								|| attributeName.equals("mgcprobableCause")
								|| attributeName.equals("granALARM_PC")
								|| attributeName
										.equals("rbnMGEventProbableCause")
								|| attributeName
										.equals("rbnChassisAlarmProbableCause")
								|| attributeName.equals("vivrprobableCause")
								|| attributeName
										.equals("_OID_VAR_ECS_PROBABLE_CAUSE")
								|| attributeName.equals("sbgTrapcause") || attributeName
									.equals("axdprobableCause"))) {
					LOGGER.debug("attributeName:" + attributeName
							+ "attributeValue" + attributeValue);

					if (attributeName.equals("accAlarmCause")) {
						attributeValue = AlarmCause
								.getaccAlarmCause(attributeValue);
					}
					notif.setProbableCause(attributeValue);

				}

				else if (isStringNotNull(attributeName)
						&& (attributeName.equals("ericPerceivedSeverity")
								|| attributeName.equals("PerceivedSeverity")
								|| attributeName.equals("rbnPerceivedSeverity")
								|| attributeName.equals("rbnDsx1AlarmSeverity")
								|| attributeName.equals("rbnSseAlarmSeverity")
								|| attributeName.equals("rbnSfpAlarmSeverity")
								|| attributeName
										.equals("rbnSseDiskAlarmSeverity")
								|| attributeName
										.equals("ecsAlarmPerceivedSeverity")
								|| attributeName
										.equals("mptAlarmPerceivedSeverity")
								|| attributeName.equals("rbnDsx3AlarmSeverity")
								|| attributeName.equals("rbnMGEventSeverity")
								|| attributeName
										.equals("rbnIpSecEventSeverity")
								|| attributeName
										.equalsIgnoreCase("ossrcalarmPS")
								|| attributeName
										.equalsIgnoreCase("mgcEvent_Severity")
								|| attributeName
										.equalsIgnoreCase("omsTrapContentsSeverity")
								|| attributeName.equalsIgnoreCase("irpalarmPS")
								|| attributeName
										.equalsIgnoreCase("newAlarmPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("axdperceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("isbladeTrapAlarmSeverity")
								|| attributeName
										.equalsIgnoreCase("ipmsalarmPS")
								|| attributeName
										.equalsIgnoreCase("hpocmpperceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("mrfperceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("mrfperceivedSeverity2")
								|| attributeName
										.equalsIgnoreCase("cpqMeAlarmSeverity")
								|| attributeName.equalsIgnoreCase("eventPS")
								|| attributeName.equalsIgnoreCase("wppalarmPS")
								|| attributeName
										.equalsIgnoreCase("esasnfPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("efwsalarmPerceivedSeverity2")
								|| attributeName
										.equalsIgnoreCase("efwsalarmPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("Broadsoft Alarm Perceived Severity")
								|| attributeName
										.equalsIgnoreCase("Broadsoft PerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("ipmsalarmPS")
								|| attributeName
										.equalsIgnoreCase("ipmuxeventPS")
								|| attributeName
										.equalsIgnoreCase("ipmuxalarmPS")
								|| attributeName
										.equalsIgnoreCase("ipfmAlarmseverity")
								|| attributeName
										.equalsIgnoreCase("dlgIsdnSigPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("dlgDsx1SwEvtMskPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("dlgDsx1PerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("dlgIsdnPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("dlgPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("sviTrapAlarmSeverity")
								|| attributeName
										.equalsIgnoreCase("sviTrap2AlarmSeverity")
								|| attributeName
										.equalsIgnoreCase("ivri_trapSeverity")
								|| attributeName
										.equalsIgnoreCase("d_trapSeverity")
								|| attributeName
										.equalsIgnoreCase("mgcAlarm_Severity")
								|| attributeName
										.equalsIgnoreCase("mgcEvent_Severity")
								|| attributeName
										.equalsIgnoreCase("oraAgentEventSeverity")
								|| attributeName
										.equalsIgnoreCase("netraalarmPS")
								|| attributeName
										.equalsIgnoreCase("netraPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("_OID_VAR_MPT_PERCEIVED_SEVERITY")
								|| attributeName
										.equalsIgnoreCase("netspiraPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("ocmpo_perceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("ocmpPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("accAlarmPriority")
								|| attributeName
										.equalsIgnoreCase("funkSbrTrapVarSev")
								|| attributeName
										.equalsIgnoreCase("sbgPereivedSeverity")
								|| attributeName
										.equalsIgnoreCase("segwPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("sppEventPerceivedseverity")
								|| attributeName.equalsIgnoreCase("sppeventPS")
								|| attributeName
										.equalsIgnoreCase("sppPerceivedseverity")
								|| attributeName
										.equalsIgnoreCase("mgcPerceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("granALARM_PS")
								|| attributeName.equals("zelsPS")
								|| attributeName.equals("ggsnAlarmSeverity")
								|| attributeName.equals("JnxSyslogSeverity")
								|| attributeName
										.equals("redbckPerceivedSeverity")
								|| attributeName
										.equals("sppAlarmPerceivedseverity")
								|| attributeName
										.equals("ocmpPerceivedSeverity")
								|| attributeName.equals("alarmPS")
								|| attributeName.equals("sitePerceivdSeverity")
								|| attributeName
										.equals("rbnCardPerceivedSeverity")
								|| attributeName
										.equals("rbnSfpPerceivedSeverity")
								|| attributeName.equals("srdPerceivedSeverity")
								|| attributeName.equalsIgnoreCase("eventPS")
								|| attributeName
										.equalsIgnoreCase("vivrperceivedSeverity")
								|| attributeName
										.equalsIgnoreCase("oraAgentEventSeverity") || attributeName
									.equalsIgnoreCase("_OID_VAR_ECS_PERCEIVED_SEVERITY"))) {
					// AXD axdperceivedSeverity
					if (attributeName.equalsIgnoreCase("axdperceivedSeverity")) {
						String axdperceivedSeverity = HandleAlarmSeverity
								.getaxdperceivedSeverity(axd301FaultIdSev,
										attributeName, attributeValue);
						notif.setExternalEventId(axdperceivedSeverity);
						notif.setSeverity(HandleAlarmSeverity
								.getPerceivedSeverity(attributeValue));
					}
					// ESA-SNFesasnfPerceivedSeverity
					else if (attributeName
							.equalsIgnoreCase("esasnfPerceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getesasnfPerceivedSeverity(attributeValue));
					}
					// SEGW segwPerceivedSeverity
					else if (attributeName
							.equalsIgnoreCase("segwPerceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getsegwPerceivedSeverity(attributeValue));
					}
					// RAD funkSbrTrapVarSev
					else if (attributeName
							.equalsIgnoreCase("funkSbrTrapVarSev")) {
						notif.setSeverity(HandleAlarmSeverity
								.getfunkSbrTrapVarSev(attributeValue));
					}
					// OCMP ocmpo_perceivedSeverity
					else if (attributeName
							.equalsIgnoreCase("ocmpo_perceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getocmpoperceivedSeverity(attributeValue));
					}
					// NETRA Netra_Alarm_Severity
					else if (attributeName
							.equalsIgnoreCase("ocmpPerceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getocmpPerceivedSeverity(attributeValue));
					}

					// mmc oraAgentEventSeverity
					else if (attributeName
							.equalsIgnoreCase("oraAgentEventSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getoraAgentEventSeverity(attributeValue));
					}
					// mgc,netra SUN MIB mgcEvent_Severity,mgcPerceivedSeverity
					else if (attributeName
							.equalsIgnoreCase("cpqMeAlarmSeverity")
							|| attributeName
									.equalsIgnoreCase("mgcAlarm_Severity")
							|| attributeName
									.equalsIgnoreCase("mgcPerceivedSeverity")
							|| attributeName
									.equalsIgnoreCase("srdPerceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getmgcEventSeverity(attributeValue));
					}
					// hpmrf
					else if (attributeName
							.equalsIgnoreCase("cpqMeAlarmSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getcpqMeAlarmSeverity(attributeValue));
					}

					else if (attributeName
							.equalsIgnoreCase("mrfperceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getmrfperceivedSeverity(attributeValue));
					}

					else if (attributeName
							.equalsIgnoreCase("mrfperceivedSeverity2")
							|| attributeName
									.equalsIgnoreCase("hpocmpperceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getmrfperceivedSeverity2(attributeValue));
					} else if (attributeName
							.equalsIgnoreCase("Broadsoft PerceivedSeverity")
							|| attributeName
									.equalsIgnoreCase("mgcAlarm_Severity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getBSSeverity(attributeValue));
					}
					// EFWS SEVERITY
					else if (attributeName
							.equalsIgnoreCase("efwsalarmPerceivedSeverity2")) {
						notif.setSeverity(HandleAlarmSeverity
								.getEFWSAlarmSeverity2(attributeValue));
					}

					// EFWS SEVERITY2
					else if (attributeName
							.equalsIgnoreCase("efwsalarmPerceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getEFWSAlarmSeverity(attributeValue));
					}

					else if (attributeName
							.equalsIgnoreCase("Broadsoft Alarm Perceived Severity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getBSAlarmSeverity(attributeValue));
					}

					else if (attributeName
							.equalsIgnoreCase("ocmpo_perceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getocmpoSeverity(attributeValue));
						;
					}

					else if (attributeName
							.equalsIgnoreCase("ocmpPerceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getocmpoSeverity(attributeValue));
					} else if (attributeName
							.equalsIgnoreCase("funkSbrTrapVarSev")) {
						// setradSeverity(notif,attributeValue);
					} else if (attributeName
							.equalsIgnoreCase("segwPerceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getsegwSeverity(attributeValue));
					} else if (attributeName
							.equalsIgnoreCase("mgcPerceivedSeverity")) {
						// setmgcSeverity(notif,attributeValue);
					}

					else if (attributeName.equalsIgnoreCase("accAlarmPriority")) {
						notif.setSeverity(HandleAlarmSeverity
								.getTigrisSeverity(attributeValue));
					}

					else if (attributeName
							.equalsIgnoreCase("vivrperceivedSeverity")) {
						notif.setSeverity(HandleAlarmSeverity
								.getvivrPerceivedSeverity(attributeValue));

					}// default
						// 1:CRITICAL,2:MAJOR,3:MINOR,4:WARNING,5:CLEARED,REST:INDETERMINATE
					else {
						try {
							LOGGER.debug("attributeName:" + attributeName
									+ "attributeValue");
							notif.setSeverity(HandleAlarmSeverity
									.getPerceivedSeverity(attributeValue));
						} catch (Exception e) {
							LOGGER.debug(" Exception in setPerceivedSeverity ()--> "
									+ e.toString());
							notif.setSeverity(Constants.SEV_INDETERMINATE);
						}
					}
				}
			}

		}

		else if (isStringNotNull(attributeName)
				&& (attributeName.equals("SpecificProblem") || (attributeName
						.equals("segwspecificProblem") || (attributeName
						.equals("omperiAlarmAlertSpecificProblem") || (attributeName
						.equals("ossrcalarmSP") || (attributeName
						.equals("sppAlarmspecificProblem") || (attributeName
						.equals("segwspecificProblem")
						|| attributeName.equals("ericSpecificProblem")
						|| attributeName.equals("ipmsalarmSP")
						|| attributeName
								.equals("mmsFmSystemAlarmProbableCause")
						|| attributeName.equals("netraalarmSP")
						|| attributeName.equals("sbgtrapName")
						|| attributeName.equals("siteSpecificProblem")
						|| attributeName
								.equals("eriAlarmActiveSpecificProblem")
						|| attributeName.equals("sppeventSP")
						|| attributeName.equals("sppspecificProblem")
						|| attributeName.equals("wppalarmSP")
						|| attributeName.equals("ericSpecificProblem")
						|| attributeName.equals("granALARM_SP")
						|| attributeName.equals("ggsnAlarmSourceId")
						|| attributeName.equals("rbnCardAlarmId")
						|| attributeName.equals("rbnSfpAlarmId")
						|| attributeName.equals("mgcEvent_Slogan")
						|| attributeName.equals("irpalarmSP")
						|| attributeName.equals("isbladeTrapName")
						|| attributeName.equals("ipmsalarmSP")
						|| attributeName.equals("eventSP")
						|| attributeName.equals("ipmuxalarmSP")
						|| attributeName.equals("ipmuxeventSP")
						|| attributeName.equals("ivri_trapSpecificProblem")
						|| attributeName.equals("d_trapSpecificProblem")
						|| attributeName.equals("mgcEvent_Slogan")
						|| attributeName.equals("mgcAlarm_Slogan")
						|| attributeName.equals("netraalarmSP")
						|| attributeName.equals("eventSP")
						|| attributeName.equals("alarmSP") || attributeName
							.equals("newAlarmName")))))))) {
			LOGGER.debug("attributeName:" + attributeName + "attributeValue:"
					+ attributeValue);
			// ERICSSON-SOG-ALARM-MIB newAlarmName
			if (attributeName.equals("newAlarmName")) {
				String newAlarmNameProbablecause = HandleProbableCause
						.getnewAlarmNameProbableCause(attributeValue);
				String newAlarmNameEventType = HandleEventType
						.getnewAlarmNameEventType(attributeValue);
				notif.setProbableCause(newAlarmNameProbablecause);
				notif.setEventType(newAlarmNameEventType);

			}
			if (attributeName.equals("netraalarmSP")) {
				// String tempSpec_problem = attributeValue.toString();
				String spec_problem[] = NetTraAlarmPSwith5
						.getnetraalarmpswith5(attributeValue,
								netraAlarmSeverity);
				notif.setSpecificProblem(spec_problem[0]);
				additionaltextBuffer.append("problemTextString" + ":"
						+ spec_problem[1] + "\n");

			}

			// for gsn event filtering // this can be ON OFF may be through
			// properties file, special case this is
			if (attributeName.equalsIgnoreCase("eventsp")) {
				for (int j = 0; j < gsnReportEvent.length; j++) {
					if (gsnReportEvent[j].equalsIgnoreCase(attributeValue)) {
						notif.setSpecificProblem(attributeValue);
						notif.setTranslateResult(TranslateResult.FORWARD_ALARM);
					} else {
						notif.setSpecificProblem(attributeValue);
						notif.setTranslateResult(TranslateResult.DROP_ALARM);
					}
				}
			}

			else {

				notif.setSpecificProblem(attributeValue);
			}
		}

		else if (isStringNotNull(attributeName)
				&& (attributeName.equalsIgnoreCase("AlarmId")
						|| attributeName.equals("ExternalEventID")
						|| attributeName.equals("jnxVpnIfIndex")
						|| attributeName.equals("_IFNUMBER")
						|| attributeName.equals("_OID_VAR_ECS_NOTIFICATION_ID")
						|| attributeName.equals("jnxVpnPwIndex")
						|| attributeName.equals("netraalarmId")
						|| attributeName.equals("ecsAlarmId")
						|| attributeName.equals("mptAlarmId")
						|| attributeName.equals("rbnIpSecRemoteId")
						|| attributeName.equals("alarmIndex")
						|| attributeName.equals("ggsnAlarmId")
						|| attributeName.equals("ifIndex")
						|| attributeName.equals("rbnChassisAlarmId")
						|| attributeName.equals("JNXContentsContainerIndex")
						|| attributeName.equals("wppalarmId")
						|| attributeName.equals("eventId")
						|| attributeName.equals("ipmuxalarmId")
						|| attributeName.equals("ipmuxeventId")
						|| attributeName.equals("mgcAlarm_Index")
						|| attributeName.equals("netspiraNotificationId")
						|| attributeName.equals("ossrcalarmId")
						|| attributeName.equals("eriAlarmActiveMinorType")
						|| attributeName.equals("ossrcalarmApp")
						|| attributeName.equals("siteAlarmId")
						|| attributeName.equals("sppeventId")
						|| attributeName.equals("sppalarmId")
						|| attributeName.equals("jnxRedundancyContentsIndex")
						|| attributeName.equals("granALARM_ALARMID")
						|| attributeName.equals("jnxFruSlot")
						|| attributeName.equals("ospfRouterId")
						|| attributeName.equals("rbnNECircuitId")
						|| attributeName.equals("zels_IFNUMBER") || attributeName
							.equals("_OID_VAR_MPT_NOTIFICATION_ID"))) {
			LOGGER.debug("ExternalEventID:" + attributeName + ":"
					+ attributeValue);
			// WPP EVENTID
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

			notif.setExternalEventId(attributeValue);
		}

		// PUT ALL OTHER ATTRIBUTE IN ADDITIONALTEXT AND ADDITIONAL ATTRIBUTE
		// @CLASS HANDLEALLOTHERALARMATTRIBUTE axdfaultId
		else {

			// AXD axd301dnaInterfaceEnteringMethod
			if (attributeName.equalsIgnoreCase("axdatmfVccOperStatus")) {

				axdatmfVccOperStatus = AXDStates
						.getaxdatmfVccOperStatus(attributeValue);
				attributeValue = axdatmfVccOperStatus;

			}
			// AXD axd301dnaInterfaceMyNeighbourIfName
			if (attributeName
					.equalsIgnoreCase("axd301dnaInterfaceMyNeighbourIfName")) {

				axd301dnaInterfaceMyNeighbourIfName = attributeValue;
				attributeValue = axd301dnaInterfaceMyNeighbourIfName;

			}

			// AXD axd301dnaInterfaceEnteringMethod
			if (attributeName
					.equalsIgnoreCase("axd301dnaInterfaceEnteringMethod")) {

				axd301dnaInterfaceMyNeighbourAddress = attributeValue;
				attributeValue = axd301dnaInterfaceMyNeighbourAddress;

			}
			// AXD axd301dnaInterfaceEnteringMethod
			if (attributeName
					.equalsIgnoreCase("axd301dnaInterfaceEnteringMethod")) {

				axd301dnaInterfaceEnteringMethod = AXDStates
						.getaxd301dnaInterfaceEnteringMethod(attributeValue);
				attributeValue = axd301dnaInterfaceEnteringMethod;

			}

			// AXD axddnaCommunityString
			if (attributeName.equalsIgnoreCase("axddnaCommunityString")) {

			}
			// AXD axdeventReason
			if (attributeName.equalsIgnoreCase("axdeventReason")) {

			}

			// AXD axdifoper
			if (attributeName.equalsIgnoreCase("axdifoper")) {

				axdifoper = AXDStates.getaxdifoper(attributeValue);
				attributeValue = axdifoper;
			}

			// AXD axdifadmin
			if (attributeName.equalsIgnoreCase("axdifadmin")) {

				AXDStates.getaxdifadmin(attributeValue);
				attributeValue = axd301ifName;
			}

			// AXD axd301ifIndex
			if (attributeName.equalsIgnoreCase("axd301ifIndex")) {

				attributeValue = axd301ifName;
			}
			// AXD axd301ifName
			if (attributeName.equalsIgnoreCase("axdspvcOperationIndicator")) {

				axd301ifName = attributeValue;
				attributeValue = axd301ifName;
			}

			// AXD axdmplsLdpSesState
			if (attributeName.equalsIgnoreCase("axdspvcOperationIndicator")) {

				spvcOperationIndicator = AXDStates
						.getspvcOperationIndicator(attributeValue);
				attributeValue = spvcOperationIndicator;
			}

			// AXD axdmplsLdpSesState
			if (attributeName.equalsIgnoreCase("axd301saalUniIfIndex")) {

			}

			// AXD axdmplsLdpSesState
			if (attributeName.equalsIgnoreCase("axdmplsLdpSesState")) {

				axdmplsLdpSesState = AXDPAStatus
						.getaxdmplsLdpSesState(attributeValue);
				attributeValue = axdmplsLdpSesState;
			}

			// AXD mplsTunnelFailureReason
			if (attributeName.equalsIgnoreCase("axd301pnniLinkIfIndex")) {

			}

			// AXD mplsTunnelFailureReason
			if (attributeName.equalsIgnoreCase("mplsTunnelFailureReason")) {

			}

			// AXD mplsTunnelName
			if (attributeName.equalsIgnoreCase("mplsTunnelName")) {

			}
			// AXD axdmplsIfOperStatus
			if (attributeName.equalsIgnoreCase("axdmplsIfOperStatus")) {

				axdmplsIfOperStatus = AXDPAStatus
						.getaxdrsvpIfOperStatus(attributeValue);
				attributeValue = axdmplsIfOperStatus;

			}

			// AXD axdmplsXCAdminStatus
			if (attributeName.equalsIgnoreCase("axdrsvpIfOperStatus")) {

				axdrsvpIfOperStatus = AXDPAStatus
						.getaxdrsvpIfOperStatus(attributeValue);
				attributeValue = axdrsvpIfOperStatus;

			}

			// AXD axdmplsXCAdminStatus
			if (attributeName.equalsIgnoreCase("axdmplsXCAdminStatus")) {

				axdmplsXCAdminStatus = AXDPAStatus
						.getaxdmplsXCAdminStatus(attributeValue);
				attributeValue = axdmplsXCAdminStatus;

			}

			// AXD axdperfFileFcode
			if (attributeName.equalsIgnoreCase("axdperfInfo")) {

				axdperfInfo = attributeValue;
				attributeValue = axdperfInfo;

			}

			// AXD axdperfFileFcode
			if (attributeName.equalsIgnoreCase("axdperfFileFcode")) {

				axdperfFileFcode = AXDPAStatus
						.getaxdperfFileFcode(attributeValue);
				attributeValue = axdperfFileFcode;

			}

			// AXD axdmrErrorOid
			if (attributeName.equalsIgnoreCase("axdmrErrorOid")) {

			}

			// AXD mrErrorCode
			if (attributeName.equalsIgnoreCase("axdmrErrorCode")) {

				axdmrErrorCode = AXDPAStatus.getaxdmrErrorCode(attributeValue);
				attributeValue = axdmrErrorCode;

			}

			// AXD axdsecOperation
			if (attributeName.equalsIgnoreCase("axdsecOperation")) {

				axdsecOperation = AXDPAStatus
						.getaxdsecOperation(attributeValue);
				attributeValue = axdsecOperation;

			}

			// AXD axdlogRecAcStatus
			if (attributeName.equalsIgnoreCase("logRecAcInfo")) {

			}

			// AXD axdlogRecAcStatus
			if (attributeName.equalsIgnoreCase("axdlogRecAcStatus")) {

				axdlogRecAcStatus = AXDPAStatus
						.getaxdlogRecAcStatus(attributeValue);
				attributeValue = axdlogRecAcStatus;

			}

			// AXD axdtestResult
			if (attributeName.equalsIgnoreCase("axdtestResult")) {

			}
			// AXD axdfailureReason
			if (attributeName.equalsIgnoreCase("axdfailureReason")) {

			}

			// AXD axdcreatedEmType
			if (attributeName.equalsIgnoreCase("axdcreatedEmType")) {

				Integer.parseInt(attributeValue);

			}

			// AXD axdposition
			if (attributeName.equalsIgnoreCase("axdposition")) {

			}

			// AXD axdgcpLinkReasonCode
			if (attributeName.equalsIgnoreCase("axdgcpLinkReasonCode")) {

				axdgcpLinkReasonCode = AXDPAStatus
						.getaxdgcpLinkReasonCode(attributeValue);
				attributeValue = axdgcpLinkReasonCode;

			}

			// AXD axdifMauJabberState
			if (attributeName.equalsIgnoreCase("axdifMauJabberState")) {
				attributeValue = AXDPAStatus
						.getaxdifMauJabberState(attributeValue);

			}

			// AXD traceRouteEventCtlTestName
			if (attributeName.equalsIgnoreCase("axdtraceRouteEventCtlTestName")) {

			}
			// AXD axdErrorTypeText
			if (attributeName.equalsIgnoreCase("axdtraceRouteNotificationType")) {

				attributeValue = AXDPAStatus
						.getaxdtraceRouteNotificationType(attributeValue);

			}

			// AXD axdErrorTypeText
			if (attributeName.equalsIgnoreCase("axdErrorTypeText")) {
				attributeName = "axdErrorTypeText";

			}

			// AXD axdpingResultsOperStatus
			if (attributeName.equalsIgnoreCase("axdpingResultsOperStatus")) {
				attributeName = "axdpingResultsOperStatus";
				attributeValue = AXDPAStatus
						.getaxdpingResultsOperStatus(attributeValue);

			}

			// AXD axdpingNotificationType
			if (attributeName.equalsIgnoreCase("axdpingEventCtlTestName")) {

			}

			// AXD axdpingNotificationType
			if (attributeName.equalsIgnoreCase("axdpingNotificationType")) {

				axdpingNotificationType = AXDPAStatus
						.getaxdpingNotificationType(attributeValue);
				attributeValue = axdpingNotificationType;
			}

			// AXD axdmigLoopbackStatus
			if (attributeName.equalsIgnoreCase("axdmigLoopbackStatus")) {

				axdmigLoopbackStatus = AXDPAStatus
						.getaxdmigLoopbackStatus(attributeValue);
				attributeValue = axdmigLoopbackStatus;
			}

			// AXD axdisisISAdjState
			if (attributeName.equalsIgnoreCase("axdisisISAdjState")) {

				axdisisISAdjState = AXDPAStatus
						.getaxdisisISAdjState(attributeValue);
				attributeValue = axdisisISAdjState;
			}

			// AXD ospfNbrState
			if (attributeName.equalsIgnoreCase("axdospfNbrState")) {
				attributeName = "axdospfNbrState";
				attributeValue = AXDPAStatus.getaxdospfNbrState(attributeValue);

			}

			// AXD ugInfo
			if (attributeName.equalsIgnoreCase("axdBgpPeerLastErrorReason")) {
				axdBgpPeerLastErrorReason = "BGPLastErrorReason="
						+ attributeValue;
				attributeValue = axdBgpPeerLastErrorReason;
			}

			// AXD axdbgpPeerState
			if (attributeName.equalsIgnoreCase("axdbgpPeerState")) {
				attributeValue = AXDPAStatus.getaxdbgpPeerState(attributeValue);

			}

			// AXD ugInfo
			if (attributeName.equalsIgnoreCase("axdugInfo")) {

			}

			// AXD paInfo
			if (attributeName.equalsIgnoreCase("axdugStatus")) {
				attributeValue = AXDPAStatus.getaxdugStatus(attributeValue);

			}

			// AXD paInfo
			if (attributeName.equalsIgnoreCase("axdpaInfo")) {

			}

			// AXD paStatus
			if (attributeName.equalsIgnoreCase("axdpaStatus")) {
				attributeValue = AXDPAStatus.getaxdpaStatus(attributeValue);

			}

			// AXD axdoptDmShortName
			if (attributeName.equalsIgnoreCase("axdoptDmShortName")) {
				attributeValue = "optDmShortName is" + attributeValue;

			}
			// AXD axdoptDmOpInfo
			if (attributeName.equalsIgnoreCase("axdoptDmOpInfo")) {

			}
			// AXD axdoptDmOpResult
			if (attributeName.equalsIgnoreCase("axdoptDmOpResult")) {

			}
			// AXD axdbusInfo
			if (attributeName.equalsIgnoreCase("axdbusInfo")) {

			}
			// AXD axdbuResult
			if (attributeName.equalsIgnoreCase("axdbuResult")) {
				attributeValue = OtherAlarmHandlers
						.getaxdbuResult(attributeValue);

			}
			// AXD "axdbuStatus"
			if (attributeName.equalsIgnoreCase("axdbuStatus")) {
				attributeValue = OtherAlarmHandlers
						.getaxdbuStatus(attributeValue);

			}
			// AXD "axdrestartingCP"
			if (attributeName.equalsIgnoreCase("axdsysFirewallInfo")) {
				attributeValue = "CP that was restarted is:" + attributeValue;

			}
			// AXD axdsysFirewallInfo
			if (attributeName.equalsIgnoreCase("axdsysFirewallInfo")) {

			}
			// AXD axdsysFirewallInfo
			if (attributeName.equalsIgnoreCase("axdsysFirewallInfo")) {

			}
			// AXD axdsysFirewallStatus
			if (attributeName.equalsIgnoreCase("axdsysFirewallStatus")) {
				attributeValue = "Name of successfully transferred file, which is to be logged in CroLog is "
						+ attributeValue;

			}
			// AXD axdcroEventFileName
			if (attributeName.equalsIgnoreCase("axdcroEventFileName")) {
				attributeValue = "Name of successfully transferred file, which is to be logged in CroLog is "
						+ attributeValue;

			}
			// AXD axdpchVpVcFaultLocation
			if (attributeName.equalsIgnoreCase("axdpchVpVcFaultLocation")) {

			}
			// AXD axdpchVpVcFaultType
			if (attributeName.equalsIgnoreCase("axdpchVpVcFaultType")) {
				attributeValue = OtherAlarmHandlers
						.getaxdpchVpVcFaultType(attributeValue);

			}
			// AXD axdoamResult
			if (attributeName.equalsIgnoreCase("axdoamResult")) {
				attributeValue = OtherAlarmHandlers
						.getaxdoamResult(attributeValue);

			}
			// AXD axdoamOperation
			if (attributeName.equalsIgnoreCase("axdoamOperation")) {
				attributeValue = OtherAlarmHandlers
						.getaxdoamOperation(attributeValue);

			}
			// AXD axdloopbackResult
			if (attributeName.equalsIgnoreCase("axdloopbackResult")) {
				attributeValue = OtherAlarmHandlers
						.getaxdloopbackResult(attributeValue);

			}

			// AXD axdloopbackMode
			if (attributeName.equalsIgnoreCase("axdloopbackMode")) {
				attributeValue = OtherAlarmHandlers
						.getaxdloopbackMode(attributeValue);

			}
			// AXD axdpchIndicator
			if (attributeName.equalsIgnoreCase("axdpchIndicator")) {
				attributeValue = OtherAlarmHandlers
						.getaxd301pchIndicator(attributeValue);

			}
			// AXD axdfaultId
			if (attributeName.equalsIgnoreCase("axdfaultId")) {
				attributeValue = attributeValue;

			}

			// IF MIb ifadminstatus
			if (attributeName.equalsIgnoreCase("ifAdminStatus")) {
				attributeName = "ifAdminStatus";
				attributeValue = AlarmState.getIfAdminState(attributeValue);

			}
			// IF MIb ifoperstatus
			if (attributeName.equalsIgnoreCase("ifOperStatus")) {
				attributeName = "ifOperStatus";
				attributeValue = AlarmState.getIfOperState(attributeValue);

			}
			// ESA-SNF :SNFTrapPDU VARBIND
			if (attributeName.equalsIgnoreCase("alarmModelDescription")
					|| attributeName.equalsIgnoreCase("alarmActiveResourceId")
					|| attributeName.equalsIgnoreCase("alarmActiveDescription")
					|| attributeName.equalsIgnoreCase("ituAlarmEventType")
					|| attributeName.equalsIgnoreCase("ituAlarmEventTypeB")
					|| attributeName.equalsIgnoreCase("ituAlarmEventTypeB")
					|| attributeName.equalsIgnoreCase("ituProbableCause")
					|| attributeName.equalsIgnoreCase("ituProbableCauseB")) {
				String fdn = "esa-snf-fdn";
				attributeName = "alarmModelDescription";
				notif = HandleOtherAlarmAttribute
						.handleSNFTrapPDU(notif, attributeName, attributeName,
								ituAlarmEventTypeoid, fdn);
			}
			// SRD:srdNotificationAddText
			if (attributeName.equalsIgnoreCase("srdNotificationAddText")) {
				attributeName = "srdNotificationAddText";
				attributeValue = AlarmCause
						.getsrdNotificationAddText(attributeName);
				if (!attributeValue.equals("")) {
					notif.setManagedObjectInstance(attributeValue);
				}

			}
			// SBG:isTrapEnumResult, ISBLADE:isTrapEnumResult
			if (attributeName.equalsIgnoreCase("isbladeTrapEnumResult")
					|| attributeName.equalsIgnoreCase("sbgEnumResult")) {
				attributeName = "isTrapEnumResult";
				attributeValue = TrapEnumHandler
						.getTrapEnumResult(attributeValue);

			}
			// _OID_VAR_IS_TRAP_ENUM_STATUS,
			// ISBLADE:_OID_VAR_IS_TRAP_ENUM_STATUS
			if (attributeName.equalsIgnoreCase("isbladeTrapEnumStatus")
					|| attributeName.equalsIgnoreCase("sbgEnumStatus")) {
				attributeName = "isTrapEnumStatus";
				attributeValue = TrapEnumHandler
						.getTrapEnumStatus(attributeValue);

			}

			// SBG:sbgEnumCase, ISBLADE:isbladeTrapEnumCause
			if (attributeName.equalsIgnoreCase("isbladeTrapEnumCause")
					|| attributeName.equalsIgnoreCase("sbgEnumCase")) {
				attributeName = "isTrapEnumCause";
				attributeValue = TrapEnumHandler
						.getTrapEnumCause(attributeValue);

			}
			// RAD funkSbrTrapVarComp
			if (attributeName.equalsIgnoreCase("funkSbrTrapVarComp")) {
				attributeName = "funkSbrTrapVarComp";
				attributeValue = TrapEnumHandler
						.getfunkSbrTrapVarComp(attributeValue);

			}
			// RAD funkSbrTrapVarComp
			if (attributeName.equalsIgnoreCase("funkSbrTrapVarComp")) {
				attributeName = "funkSbrTrapVarComp";
				attributeValue = TrapEnumHandler
						.getfunkSbrTrapVarComp(attributeValue);

			}

			// NETSCREEN nsVrBgpPeerState
			if (attributeName.equalsIgnoreCase("nsVrBgpPeerState")) {
				attributeName = "nsVrBgpPeerState";
				attributeValue = TrapEnumHandler
						.getInsVrBgpPeerState(attributeValue);

			}

			// NETSCREEN _BGP_OSPF_MIBS: bgposfpTrapType
			if (attributeName.equalsIgnoreCase("bgposfpTrapType")) {
				attributeName = "bgposfpTrapType";
				attributeValue = BGPOSFPTrapTypes
						.getbgposfpTrapType(attributeValue);

			}
			// NETSCREEN netscreenTrapType
			if (attributeName.equalsIgnoreCase("netscreenTrapType")) {
				// int TrapType = Integer.parseInt(attributeValue);
				notif = NSAlarm4NetScreenTrapType
						.buildNsAlarm4netscreenTrapType(attributeName,
								attributeValue, notif);

			}
			// NETRA mteHotTrigger
			if (attributeName.equalsIgnoreCase("mteHotTrigger")) {
				mteHotTrigger = attributeValue;
			}
			// MMMC mmsCmSystemAdministrativeState
			if (attributeName
					.equalsIgnoreCase("mmsCmSystemAdministrativeState")) {

				attributeName = "mmsCmSystemAdministrativeState";
				attributeValue = NMSSystemHandlers
						.getmmsCmSystemAdministrativeState(attributeValue);

			}
			// MMMC mmsCmSystemOperationalState
			if (attributeName.equalsIgnoreCase("mmsCmSystemOperationalState")) {

				attributeName = "mmsCmSystemOperationalState";
				attributeValue = NMSSystemHandlers
						.getmmsCmSystemOperationalState(attributeValue);

			}
			// MMMC merAdminstrativeState
			if (attributeName.equalsIgnoreCase("merAdminstrativeState")) {

				attributeName = "merAdminstrativeState";
				attributeValue = NMSSystemHandlers
						.getmerAdminstrativeState(attributeValue);

			}
			// MMMC merOperationalState
			if (attributeName.equalsIgnoreCase("merOperationalState")) {

				attributeName = "merOperationalState";
				attributeValue = NMSSystemHandlers
						.getmerOperationalState(attributeValue);

			}
			// MMMC mmsFmSystemAlarmID
			if (attributeName.equalsIgnoreCase("mmsFmSystemAlarmID")) {

				attributeName = "mmsFmSystemAlarmID";
				attributeValue = NMSSystemHandlers
						.getmmsFmSystemAlarmID(attributeValue);
				perfdegraded = AXDStates.isPerfDegraded(attributeValue);
				notif.setEventAgentId(attributeValue);
			}
			// MIBII _IFADMINSTATUS
			if (attributeName.equalsIgnoreCase("_IFADMINSTATUS")) {

				attributeName = "_IFADMINSTATUS";
				attributeValue = AlarmState.getIfAdminState(attributeValue);
			}

			// MIBII _IFOPERSTATUS
			if (attributeName.equalsIgnoreCase(" _IFOPERSTATUS")) {

				attributeName = " _IFOPERSTATUS";
				attributeValue = AlarmState.getIfOperState(attributeValue);
			}
			// ACME TRANSLATION
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE")) {
				attributeName = "_OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE";
				attributeValue = AlarmState
						.getOidVarAPEnvMonTrapPreviousState(attributeValue);
			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE")) {
				attributeName = "_OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE";
				attributeValue = AlarmState
						.getOidVarAPEnvMonTrapCurrentState(attributeValue);
			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_LOG_HIST_LEVEL")) {
				attributeName = "_OID_VAR_AP_SYS_LOG_HIST_LEVEL";
				attributeValue = AlarmState
						.getOidVarAPSysLogHistLevel(attributeValue);
			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE(attributeValue);
			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_FAN_LOCATION")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_FAN_LOCATION";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_FAN_LOCATION(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_RED_ROLE")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_RED_ROLE(attributeValue);
			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE(attributeValue);

			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN(attributeValue);

			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON(attributeValue);

			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL(attributeValue);

			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO(attributeValue);

			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE(attributeValue);

			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON(attributeValue);

			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS(attributeValue);

			}
			if (attributeName
					.equalsIgnoreCase("_OID_VAR_AP_ENUM_SERVER_STATUS")) {
				attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
				attributeValue = OidVarApSysMgmtHandlers
						.get_OID_VAR_AP_ENUM_SERVER_STATUS(attributeValue);

			}

			// EXTREME
			if (attributeName.equalsIgnoreCase("extremeTrapAuthorerrorType")) {

				attributeName = "vrrpTrapAuthErrorType";
				attributeValue = ExtreamTrapSystem
						.getextremeTrapAuthorerrorType(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("extremeSystemPowerState")) {
				attributeName = "vrrpTrapAuthErrorType";
				attributeValue = ExtreamTrapSystem
						.getextremeSystemPowerState(attributeValue);

			}
			if (attributeName.equalsIgnoreCase("extremePortMauStatus")) {

				attributeName = "extremePortMauStatus";
				attributeValue = ExtreamTrapSystem
						.getextremePortMauStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("extremeEsrpDmnState")) {

				attributeName = "extremeEsrpDmnState";
				attributeValue = ExtreamTrapSystem
						.getextremeEsrpDmnState(attributeValue);
			}

			// BROADSOFT
			if (attributeName.equalsIgnoreCase("Broadsoft Component")) {

				attributeName = "Broadsoft Component";
				attributeValue = BSComponent.getBSComponent(attributeValue);
			}
			if (attributeName.equals("Broadsoft SUBComponent")) {
				notif.setManagedObjectInstance("EQ="
						+ BSComponent.getBSSubComponent(attributeValue));
				attributeValue = BSComponent.getBSSubComponent(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("trendIndication")) // trendIndication
			{

				attributeName = "trendIndication";
				attributeValue = CPQOtherHandlers
						.gettrendIndication(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqNicIfPhysAdapterStatus")) // cpqNicIfPhysAdapterStatus
			{

				attributeName = "cpqNicIfPhysAdapterStatus";
				attributeValue = CPQOtherHandlers
						.getcpqNicIfPhysAdapterStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqSm2CntlrSelfTestErrors")) // cpqSm2CntlrSelfTestErrors
			{

				attributeName = "cpqSm2CntlrSelfTestErrors";
				attributeValue = CPQOtherHandlers
						.getcpqSm2CntlrSelfTestErrors(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqHeFltTolPowerSupplyStatus")) // cpqHeFltTolPowerSupplyStatus
			{

				attributeName = "cpqHeFltTolPowerSupplyStatus";
				attributeValue = CPQHeHandlers
						.getcpqHeFltTolPowerSupplyStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqHeTemperatureLocale")) // cpqHeTemperatureLocale
			{

				attributeName = "cpqHeTemperatureLocale";
				attributeValue = CPQHeHandlers
						.getcpqHeTemperatureLocale(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqHeThermalDegradedAction")) // cpqHeThermalDegradedAction
			{

				attributeName = "cpqHeThermalDegradedAction";
				attributeValue = CPQHeHandlers
						.getcpqHeThermalDegradedAction(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqHeCorrMemLogStatus")) // cpqHeCorrMemLogStatus
			{

				attributeName = "cpqHeCorrMemLogStatus";
				attributeValue = CPQHeHandlers
						.getcpqHeCorrMemLogStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqIdeLogicalDriveStatus")) // cpqIdeLogicalDriveStatus
			{

				attributeName = "cpqIdeLogicalDriveStatus";
				attributeValue = CPQIdeAtaDiskHandlers
						.getcpqIdeLogicalDriveStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqIdeAtaDiskNumber")) // cpqIdeAtaDiskNumber
			{

				attributeName = "cpqIdeAtaDiskNumber";
				attributeValue = CPQIdeAtaDiskHandlers
						.getcpqIdeAtaDiskNumber(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqIdeAtaDiskChannel")) // cpqIdeAtaDiskChannel
			{

				attributeName = "cpqIdeAtaDiskChannel";
				attributeValue = CPQIdeAtaDiskHandlers
						.getcpqIdeAtaDiskChannel(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqIdeAtaDiskStatus")) // cpqIdeAtaDiskStatus
			{

				attributeName = "cpqIdeAtaDiskStatus";
				attributeValue = CPQIdeAtaDiskHandlers
						.getcpqIdeAtaDiskStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqScsiPhyDrvStatus")) // cpqScsiPhyDrvStatus
			{

				attributeName = "cpqScsiPhyDrvStatus";
				attributeValue = CPQScsIHandlers
						.getcpqScsiPhyDrvStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqDaLogDrvStatus")) // cpqDaLogDrvStatus
			{

				attributeName = "cpqDaLogDrvStatus";
				attributeValue = CPQDAStatusHandlers
						.getcpqDaLogDrvStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqScsiLogDrvStatus")) // cpqScsiLogDrvStatus
			{

				attributeName = "cpqScsiLogDrvStatus";
				attributeValue = CPQScsIHandlers
						.getcpqScsiLogDrvStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqDaCntlrBoardStatus")) // cpqDaCntlrBoardStatus
			{

				attributeName = "cpqDaCntlrBoardStatus";
				attributeValue = CPQDAStatusHandlers
						.getcpqDaCntlrBoardStatus(attributeValue);
			}
			if (attributeName.equalsIgnoreCase("cpqDaTapeDrvStatus")) // cpqDaTapeDrvStatus
			{

				attributeName = "cpqDaTapeDrvStatus";
				attributeValue = CPQDAStatusHandlers
						.getcpqDaTapeDrvStatus(attributeValue);
			}// HPMRF cpqDaSpareStatus
			if (attributeName.equalsIgnoreCase("cpqDaSpareStatus")) {

				attributeName = "cpqDaSpareStatus";
				attributeValue = CPQDAStatusHandlers
						.getcpqDaSpareStatus(attributeValue);
			}// HPMRF cpqDaPhyDrvStatus
			else if (attributeName.equalsIgnoreCase("cpqDaPhyDrvStatus")) {

				attributeName = "cpqDaPhyDrvStatus";
				attributeValue = CPQDaAccelHandler
						.getcpqDaPhyDrvStatus(attributeValue);
			}// HPMRF cpqDaAccelErrCode
			else if (attributeName.equalsIgnoreCase("cpqDaAccelErrCode")) {

				attributeName = "cpqDaAccelErrCode";
				attributeValue = CPQDaAccelHandler
						.getcpqDaAccelErrCode(attributeValue);
			}// HPMRF cpqDaAccelStatus
			else if (attributeName.equalsIgnoreCase("cpqDaAccelStatus")) {

				attributeName = "cpqDaAccelStatus";
				attributeValue = CPQDaAccelHandler
						.getcpqDaAccelStatus(attributeValue);
			}

			// HPMRF cpqDaCntlrModel
			else if (attributeName.equalsIgnoreCase("cpqDaCntlrModel")) {

				attributeName = "cpqDaCntlrModel";
				attributeValue = CPQDaAccelHandler
						.getcpqDaCntlrModel(attributeValue);
			} else if (attributeName.equalsIgnoreCase("cpqHoTrapFlags")) {

				attributeName = "cpqHoTrapFlags";
				attributeValue = CPQHoTrapFlags
						.getcpqHoTrapFlags(attributeValue);
			} else if (attributeName.equalsIgnoreCase("cpqMeAlarmSampleType")) {

				attributeName = "cpqMeAlarmSampleType";
				attributeValue = CPQHoTrapFlags
						.getcpqMeAlarmSampleType(attributeValue);
			} else if (attributeName.equals("dsx1LineStatus")) {
				// attributeValue =
				// translatedsx1LineStatus(Integer.valueOf(attributeValue));
			} else if (attributeName.equals("dsx3LineStatus")) {
				// attributeValue =
				// translatedsx3LineStatus(Integer.valueOf(attributeValue));
			} else if (attributeName.equalsIgnoreCase("rdbmsRelState")) // MMC
			// rdbmsRelState
			{

				attributeName = "rdbmsRelState";
				attributeValue = ExtreamTrapSystem
						.getrdbmsRelState(attributeValue);
			} else if (attributeName.equalsIgnoreCase("oraListenerState")) // MMC
			// oraListenerState
			{

				attributeName = "oraListenerState";
				attributeValue = ExtreamTrapSystem
						.getoraListenerState(attributeValue);
			}

			additionaltextBuffer.append(attributeName + ":" + attributeValue
					+ "\n");

			notif.addAdditionalAttribute(attributeName, attributeValue);
		}

		notif.addAdditionalAttribute("additionalText",
				additionaltextBuffer.toString());
	}

	public static Date getEventDateAndTime(final String s) {
		Date date = new Date();

		StringTokenizer st = new StringTokenizer(s.trim(), "-");
		date.setYear(Integer.parseInt(st.nextToken().trim()) - 1900);
		date.setMonth(Integer.parseInt(st.nextToken().trim()) - 1);

		StringTokenizer st2 = new StringTokenizer(st.nextToken().trim(), ",");
		date.setDate(Integer.parseInt(st2.nextToken()));

		StringTokenizer st3 = new StringTokenizer(st2.nextToken().trim(), ":");
		date.setHours(Integer.parseInt(st3.nextToken().trim()));
		date.setMinutes(Integer.parseInt(st3.nextToken().trim()));

		StringTokenizer st4 = new StringTokenizer(st3.nextToken().trim(), ".");
		LOGGER.debug("st4 = " + st4.countTokens());
		int seconds = Integer.parseInt(st4.nextToken().trim());

		// deci_seconds

		double deci_seconds = Double.parseDouble(st4.nextToken().trim()) * 0.1;

		if (deci_seconds >= 0.5) {
			deci_seconds = 1;
		} else {
			deci_seconds = 0;
		}

		int total_seconds = seconds + (int) deci_seconds;

		date.setSeconds(total_seconds);
		LOGGER.debug("CommonAAUTranslator:: createEventDateAndTime ==> " + date);

		return date;
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

	private String convertOctalToString(byte[] theByteArray) {
		for (int i = 0; i < theByteArray.length; i++) {
			if (((byte) theByteArray[i] >= (byte) 0x00)
					&& ((byte) theByteArray[i] < (byte) 0x20)) {
				theByteArray[i] = '0';
			}
		}

		String stringValue = new String(theByteArray);

		return stringValue;
	}

	public void setAlarmTime(final EventNotification notif,
			final String attributeValue) {
		String time = "";

		try {
			StringTokenizer st = new StringTokenizer(attributeValue, ":");

			st.nextToken();
			st.nextToken();

			while (st.hasMoreTokens()) {
				time = time + st.nextToken() + ":";
			}

			if (time.endsWith(":")) {
				time = time.substring(0, (time.length() - 1));
			}

			if (time.indexOf("-") != -1)// event
			{
				String Timezone = null;
				LOGGER.debug("Time Length : " + time.length());

				if (time.length() != 26) {
					LOGGER.debug("If the time is not in 11 octet then setting the time to local current time");
					notif.setTimeZone("UTC");
					notif.setTime((new Date()));
				} else {
					try {
						Timezone = CreateAlarmDateAndTime
								.getEventTimeZone(time);
						LOGGER.debug("TimeZone is : " + Timezone);
					} catch (Exception ex) {
						LOGGER.error("Date Format Exception");
					}

					LOGGER.debug("Time is"
							+ TIMEFORMATTER.format(CreateAlarmDateAndTime
									.getEventDateAndTime(time)));

					notif.setTimeZone(Timezone);
				}
			} else // alarm
			{
				String Timezone = null;

				LOGGER.debug("Time Length : " + time.length());

				if (time.length() != 33) {
					LOGGER.debug("If the time is not in 11 octet then setting the time to local current time");
					notif.setTimeZone("UTC");
					notif.setTime(new Date());
				} else {
					try {
						Timezone = CreateAlarmDateAndTime.getTimeZone(time);
						LOGGER.debug("TimeZone is : " + Timezone);
					} catch (Exception ex) {
						LOGGER.error("Date Format Exception");
					}

					LOGGER.debug("Time is"
							+ TIMEFORMATTER.format(CreateAlarmDateAndTime
									.getAlarmDateAndTime(time)));
					notif.setTime(CreateAlarmDateAndTime
							.getAlarmDateAndTime(time));
					notif.setTimeZone(Timezone);
				}
			}
		} catch (Exception ex) {
			LOGGER.error("Date Format Exception : " + time);
			notif.setTimeZone("UTC");
			notif.setTime(new Date());
			LOGGER.info("setTime() : "
					+ CreateAlarmDateAndTime.getCurrentTime());
		}
	}

}
