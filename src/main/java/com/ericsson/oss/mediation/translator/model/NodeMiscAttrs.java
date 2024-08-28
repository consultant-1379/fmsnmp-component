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
package com.ericsson.oss.mediation.translator.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


import com.ericsson.oss.mediation.fm.component.SnmpSupervisionProducer;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.model.alarm.handlers.*;
import com.ericsson.oss.mediation.translator.model.handlers.*;

public class NodeMiscAttrs {
	private static final String IFADMINSTATUS = "IFADMINSTATUS";
	private static final String IFOPERSTATUS = "IFOPERSTATUS";
	private static final String ALARMMODELDESCRIPTION = "ALARMMODELDESCRIPTION";
	private static final String ALARMACTIVERESOURCEID = "ALARMACTIVERESOURCEID";
	private static final String ALARMACTIVEDESCRIPTION = "ALARMACTIVEDESCRIPTION";
	private static final String ITUALARMEVENTTYPE = "ITUALARMEVENTTYPE";
	private static final String ITUALARMEVENTTYPEB = "ITUALARMEVENTTYPEB";
	private static final String ITUPROBABLECAUSE = "ITUPROBABLECAUSE";
	private static final String ITUPROBABLECAUSEB = "ITUPROBABLECAUSEB";
	private static final String SRDNOTIFICATIONADDTEXT = "SRDNOTIFICATIONADDTEXT";
	private static final String ISBLADETRAPENUMRESULT = "ISBLADETRAPENUMRESULT";
	private static final String SBGENUMRESULT = "SBGENUMRESULT";
	private static final String ISBLADETRAPENUMSTATUS = "ISBLADETRAPENUMSTATUS";
	private static final String SBGENUMSTATUS = "SBGENUMSTATUS";
	private static final String ISBLADETRAPENUMCAUSE = "ISBLADETRAPENUMCAUSE";
	private static final String SBGENUMCASE = "SBGENUMCASE";
	private static final String FUNKSBRTRAPVARCOMP = "FUNKSBRTRAPVARCOMP";
	private static final String NSVRBGPPEERSTATE = "NSVRBGPPEERSTATE";
	private static final String BGPOSFPTRAPTYPE = "BGPOSFPTRAPTYPE";
	private static final String NETSCREENTRAPTYPE = "NETSCREENTRAPTYPE";
	private static final String MTEHOTTRIGGER = "MTEHOTTRIGGER";
	private static final String MMSCMSYSTEMADMINISTRATIVESTATE = "MMSCMSYSTEMADMINISTRATIVESTATE";
	private static final String MMSCMSYSTEMOPERATIONALSTATE = "MMSCMSYSTEMOPERATIONALSTATE";
	private static final String MERADMINSTRATIVESTATE = "MERADMINSTRATIVESTATE";
	private static final String MEROPERATIONALSTATE = "MEROPERATIONALSTATE";
	private static final String MMSFMSYSTEMALARMID = "MMSFMSYSTEMALARMID";
	private static final String _IFADMINSTATUS = "_IFADMINSTATUS";
	private static final String _IFOPERSTATUS = " _IFOPERSTATUS";
	private static final String _OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE = "_OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE";
	private static final String _OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE = "_OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE";
	private static final String _OID_VAR_AP_SYS_LOG_HIST_LEVEL = "_OID_VAR_AP_SYS_LOG_HIST_LEVEL";
	private static final String _OID_VAR_AP_SYS_MGMT_POWER_PRESENCE = "_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE";
	private static final String _OID_VAR_AP_SYS_MGMT_FAN_LOCATION = "_OID_VAR_AP_SYS_MGMT_FAN_LOCATION";
	private static final String _OID_VAR_AP_SYS_MGMT_RED_ROLE = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
	private static final String _OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE = "_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE";
	private static final String _OID_VAR_AP_SYS_MGMT_RADIUS_DOWN = "_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN";
	private static final String _OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON = "_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON";
	private static final String _OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL = "_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL";
	private static final String _OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO = "_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO";
	private static final String _OID_VAR_AP_SYS_MGMT_SYSTEM_STATE = "_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE";
	private static final String _OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON = "_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON";
	private static final String _OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS = "_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS";
	private static final String _OID_VAR_AP_ENUM_SERVER_STATUS = "_OID_VAR_AP_ENUM_SERVER_STATUS";
	private static final String EXTREMETRAPAUTHORERRORTYPE = "EXTREMETRAPAUTHORERRORTYPE";
	private static final String EXTREMESYSTEMPOWERSTATE = "EXTREMESYSTEMPOWERSTATE";
	private static final String EXTREMEPORTMAUSTATUS = "EXTREMEPORTMAUSTATUS";
	private static final String EXTREMEESRPDMNSTATE = "EXTREMEESRPDMNSTATE";
	private static final String BROADSOFT_COMPONENT = "BROADSOFT COMPONENT";
	private static final String BROADSOFT_SUBCOMPONENT = "BROADSOFT SUBCOMPONENT";
	private static final String TRENDINDICATION = "TRENDINDICATION";
	private static final String CPQNICIFPHYSADAPTERSTATUS = "CPQNICIFPHYSADAPTERSTATUS";
	private static final String CPQSM2CNTLRSELFTESTERRORS = "CPQSM2CNTLRSELFTESTERRORS";
	private static final String CPQHEFLTTOLPOWERSUPPLYSTATUS = "CPQHEFLTTOLPOWERSUPPLYSTATUS";
	private static final String CPQHETEMPERATURELOCALE = "CPQHETEMPERATURELOCALE";
	private static final String CPQHETHERMALDEGRADEDACTION = "CPQHETHERMALDEGRADEDACTION";
	private static final String CPQHECORRMEMLOGSTATUS = "CPQHECORRMEMLOGSTATUS";
	private static final String CPQIDELOGICALDRIVESTATUS = "CPQIDELOGICALDRIVESTATUS";
	private static final String CPQIDEATADISKNUMBER = "CPQIDEATADISKNUMBER";
	private static final String CPQIDEATADISKCHANNEL = "CPQIDEATADISKCHANNEL";
	private static final String CPQIDEATADISKSTATUS = "CPQIDEATADISKSTATUS";
	private static final String CPQSCSIPHYDRVSTATUS = "CPQSCSIPHYDRVSTATUS";
	private static final String CPQDALOGDRVSTATUS = "CPQDALOGDRVSTATUS";
	private static final String CPQSCSILOGDRVSTATUS = "CPQSCSILOGDRVSTATUS";
	private static final String CPQDACNTLRBOARDSTATUS = "CPQDACNTLRBOARDSTATUS";
	private static final String CPQDATAPEDRVSTATUS = "CPQDATAPEDRVSTATUS";
	private static final String CPQDASPARESTATUS = "CPQDASPARESTATUS";
	private static final String CPQDAPHYDRVSTATUS = "CPQDAPHYDRVSTATUS";
	private static final String CPQDAACCELERRCODE = "CPQDAACCELERRCODE";
	private static final String CPQDAACCELSTATUS = "CPQDAACCELSTATUS";
	private static final String CPQDACNTLRMODEL = "CPQDACNTLRMODEL";
	private static final String CPQHOTRAPFLAGS = "CPQHOTRAPFLAGS";
	private static final String CPQMEALARMSAMPLETYPE = "CPQMEALARMSAMPLETYPE";
	private static final String DSX1LINESTATUS = "DSX1LINESTATUS";
	private static final String DSX3LINESTATUS = "DSX3LINESTATUS";
	private static final String RDBMSRELSTATE = "RDBMSRELSTATE";
	private static final String ORALISTENERSTATE = "ORALISTENERSTATE";
	private boolean perfdegraded = false;
	private String mteHotTrigger = "";
	
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(NodeMiscAttrs.class);
	/**
	 * @return the perfdegraded
	 */
	public boolean isPerfdegraded() {
		LOGGER.info("NodeMiscAttrs**** isPerfdegraded()***** ==> ");
		return perfdegraded;
	}

	/**
	 * @return the mteHotTrigger
	 */
	public String getMteHotTrigger() {
		return mteHotTrigger;
	}

	public boolean translateTmosAttrs(String attributeName,
			String attributeValue, EventNotification notif,
			final String ituAlarmEventTypeoid) {
		LOGGER.debug("NodeMiscAttrs: translateTmosAttrs() ==>"+attributeValue);
		boolean status = true;
		final String ucAttibuteName = attributeName.toUpperCase();

		switch (ucAttibuteName) {
		case IFADMINSTATUS:
			attributeName = "ifAdminStatus";
			break;
		case IFOPERSTATUS:
			attributeName = "ifOperStatus";
			break;
		case ALARMMODELDESCRIPTION:
		case ALARMACTIVERESOURCEID:
		case ALARMACTIVEDESCRIPTION:
		case ITUALARMEVENTTYPE:
		case ITUALARMEVENTTYPEB:
		case ITUPROBABLECAUSE:
		case ITUPROBABLECAUSEB:
			final String fdn = "esa-snf-fdn";
			attributeName = "alarmModelDescription";
			notif = HandleOtherAlarmAttribute.handleSNFTrapPDU(notif,
					attributeName, attributeName, ituAlarmEventTypeoid, fdn);
			break;

		case SRDNOTIFICATIONADDTEXT:
			attributeName = "srdNotificationAddText";
			attributeValue = AlarmCause
					.getsrdNotificationAddText(attributeName);
			if (!attributeValue.equals("")) {
				notif.setManagedObjectInstance(attributeValue);
			}
			break;
		case ISBLADETRAPENUMRESULT:
		case SBGENUMRESULT:
			attributeName = "isTrapEnumResult";
			attributeValue = TrapEnumHandler.getTrapEnumResult(attributeValue);

			break;
		case ISBLADETRAPENUMSTATUS:
		case SBGENUMSTATUS:
			attributeName = "isTrapEnumStatus";
			attributeValue = TrapEnumHandler.getTrapEnumStatus(attributeValue);
			break;

		case ISBLADETRAPENUMCAUSE:
		case SBGENUMCASE:
			attributeName = "isTrapEnumCause";
			attributeValue = TrapEnumHandler.getTrapEnumCause(attributeValue);
			break;

		case FUNKSBRTRAPVARCOMP:
			attributeName = "funkSbrTrapVarComp";
			attributeValue = TrapEnumHandler
					.getfunkSbrTrapVarComp(attributeValue);
			break;

		// RAD funkSbrTrapVarComp
		case NSVRBGPPEERSTATE:

			// NETSCREEN nsVrBgpPeerState

			attributeName = "nsVrBgpPeerState";
			attributeValue = TrapEnumHandler
					.getInsVrBgpPeerState(attributeValue);

			break;

		// NETSCREEN _BGP_OSPF_MIBS: bgposfpTrapType
		case BGPOSFPTRAPTYPE:
			attributeName = "bgposfpTrapType";
			attributeValue = BGPOSFPTrapTypes
					.getbgposfpTrapType(attributeValue);

			break;

		// NETSCREEN netscreenTrapType
		case NETSCREENTRAPTYPE:
			// int TrapType = Integer.parseInt(attributeValue);
			notif = NSAlarm4NetScreenTrapType.buildNsAlarm4netscreenTrapType(
					attributeName, attributeValue, notif);

			// NETRA mteHotTrigger
		case MTEHOTTRIGGER:
			mteHotTrigger = attributeValue;
			break;
		// MMMC mmsCmSystemAdministrativeState
		case MMSCMSYSTEMADMINISTRATIVESTATE:

			attributeName = "mmsCmSystemAdministrativeState";
			attributeValue = NMSSystemHandlers
					.getmmsCmSystemAdministrativeState(attributeValue);

			break;
		// MMMC mmsCmSystemOperationalState
		case MMSCMSYSTEMOPERATIONALSTATE:

			attributeName = "mmsCmSystemOperationalState";
			attributeValue = NMSSystemHandlers
					.getmmsCmSystemOperationalState(attributeValue);

			break;
		// MMMC merAdminstrativeState
		case MERADMINSTRATIVESTATE:

			attributeName = "merAdminstrativeState";
			attributeValue = NMSSystemHandlers
					.getmerAdminstrativeState(attributeValue);

			break;
		// MMMC merOperationalState
		case MEROPERATIONALSTATE:

			attributeName = "merOperationalState";
			attributeValue = NMSSystemHandlers
					.getmerOperationalState(attributeValue);

			break;
		// MMMC mmsFmSystemAlarmID
		case MMSFMSYSTEMALARMID:

			attributeName = "mmsFmSystemAlarmID";
			attributeValue = NMSSystemHandlers
					.getmmsFmSystemAlarmID(attributeValue);
			perfdegraded = AXDStates.isPerfDegraded(attributeValue);
			notif.setEventAgentId(attributeValue);
			break;
		// MIBII _IFADMINSTATUS
		case _IFADMINSTATUS:

			attributeName = "_IFADMINSTATUS";
			attributeValue = AlarmState.getIfAdminState(attributeValue);
			break;

		// MIBII _IFOPERSTATUS
		case _IFOPERSTATUS:

			attributeName = " _IFOPERSTATUS";
			attributeValue = AlarmState.getIfOperState(attributeValue);
			break;
		// ACME TRANSLATION
		case _OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE:
			attributeName = "_OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE";
			attributeValue = AlarmState
					.getOidVarAPEnvMonTrapPreviousState(attributeValue);
			break;
		case _OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE:
			attributeName = "_OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE";
			attributeValue = AlarmState
					.getOidVarAPEnvMonTrapCurrentState(attributeValue);
			break;

		case _OID_VAR_AP_SYS_LOG_HIST_LEVEL:
			attributeName = "_OID_VAR_AP_SYS_LOG_HIST_LEVEL";
			attributeValue = AlarmState
					.getOidVarAPSysLogHistLevel(attributeValue);
			break;

		case _OID_VAR_AP_SYS_MGMT_POWER_PRESENCE:
			attributeName = "_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE(attributeValue);
			break;
		case _OID_VAR_AP_SYS_MGMT_FAN_LOCATION:
			attributeName = "_OID_VAR_AP_SYS_MGMT_FAN_LOCATION";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_FAN_LOCATION(attributeValue);
			break;
		case _OID_VAR_AP_SYS_MGMT_RED_ROLE:
			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_RED_ROLE(attributeValue);
			break;
		case _OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE:
			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE(attributeValue);

			break;
		case _OID_VAR_AP_SYS_MGMT_RADIUS_DOWN:
			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN(attributeValue);

			break;
		case _OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON:
			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON(attributeValue);

			break;
		case _OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL:
			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL(attributeValue);

			break;
		case _OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO:
			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO(attributeValue);

			break;
		case _OID_VAR_AP_SYS_MGMT_SYSTEM_STATE:
			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE(attributeValue);

			break;
		case _OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON:
			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON(attributeValue);

			break;
		case _OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS:

			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS(attributeValue);

			break;
		case _OID_VAR_AP_ENUM_SERVER_STATUS:
			attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
			attributeValue = OidVarApSysMgmtHandlers
					.get_OID_VAR_AP_ENUM_SERVER_STATUS(attributeValue);

			break;

		// EXTREME
		case EXTREMETRAPAUTHORERRORTYPE:

			attributeName = "vrrpTrapAuthErrorType";
			attributeValue = ExtreamTrapSystem
					.getextremeTrapAuthorerrorType(attributeValue);
			break;
		case EXTREMESYSTEMPOWERSTATE:
			attributeName = "vrrpTrapAuthErrorType";
			attributeValue = ExtreamTrapSystem
					.getextremeSystemPowerState(attributeValue);

			break;
		case EXTREMEPORTMAUSTATUS:

			attributeName = "extremePortMauStatus";
			attributeValue = ExtreamTrapSystem
					.getextremePortMauStatus(attributeValue);
			break;
		case EXTREMEESRPDMNSTATE:

			attributeName = "extremeEsrpDmnState";
			attributeValue = ExtreamTrapSystem
					.getextremeEsrpDmnState(attributeValue);
			break;

		// BROADSOFT
		case BROADSOFT_COMPONENT:

			attributeName = "Broadsoft Component";
			attributeValue = BSComponent.getBSComponent(attributeValue);
			break;
		case BROADSOFT_SUBCOMPONENT:
			notif.setManagedObjectInstance("EQ="
					+ BSComponent.getBSSubComponent(attributeValue));
			attributeValue = BSComponent.getBSSubComponent(attributeValue);
			break;
		case TRENDINDICATION:

			attributeName = "trendIndication";
			attributeValue = CPQOtherHandlers
					.gettrendIndication(attributeValue);
			break;
		case CPQNICIFPHYSADAPTERSTATUS:

			attributeName = "cpqNicIfPhysAdapterStatus";
			attributeValue = CPQOtherHandlers
					.getcpqNicIfPhysAdapterStatus(attributeValue);
			break;
		case CPQSM2CNTLRSELFTESTERRORS:

			attributeName = "cpqSm2CntlrSelfTestErrors";
			attributeValue = CPQOtherHandlers
					.getcpqSm2CntlrSelfTestErrors(attributeValue);
			break;
		case CPQHEFLTTOLPOWERSUPPLYSTATUS:

			attributeName = "cpqHeFltTolPowerSupplyStatus";
			attributeValue = CPQHeHandlers
					.getcpqHeFltTolPowerSupplyStatus(attributeValue);
			break;
		case CPQHETEMPERATURELOCALE:
			attributeName = "cpqHeTemperatureLocale";
			attributeValue = CPQHeHandlers
					.getcpqHeTemperatureLocale(attributeValue);
			break;
		case CPQHETHERMALDEGRADEDACTION:

			attributeName = "cpqHeThermalDegradedAction";
			attributeValue = CPQHeHandlers
					.getcpqHeThermalDegradedAction(attributeValue);
			break;
		case CPQHECORRMEMLOGSTATUS:
			attributeName = "cpqHeCorrMemLogStatus";
			attributeValue = CPQHeHandlers
					.getcpqHeCorrMemLogStatus(attributeValue);
			break;
		case CPQIDELOGICALDRIVESTATUS:
			attributeName = "cpqIdeLogicalDriveStatus";
			attributeValue = CPQIdeAtaDiskHandlers
					.getcpqIdeLogicalDriveStatus(attributeValue);
			break;
		case CPQIDEATADISKNUMBER:
			attributeName = "cpqIdeAtaDiskNumber";
			attributeValue = CPQIdeAtaDiskHandlers
					.getcpqIdeAtaDiskNumber(attributeValue);
			break;
		case CPQIDEATADISKCHANNEL:

			attributeName = "cpqIdeAtaDiskChannel";
			attributeValue = CPQIdeAtaDiskHandlers
					.getcpqIdeAtaDiskChannel(attributeValue);
			break;
		case CPQIDEATADISKSTATUS:

			attributeName = "cpqIdeAtaDiskStatus";
			attributeValue = CPQIdeAtaDiskHandlers
					.getcpqIdeAtaDiskStatus(attributeValue);
			break;
		case CPQSCSIPHYDRVSTATUS:

			attributeName = "cpqScsiPhyDrvStatus";
			attributeValue = CPQScsIHandlers
					.getcpqScsiPhyDrvStatus(attributeValue);
			break;
		case CPQDALOGDRVSTATUS:

			attributeName = "cpqDaLogDrvStatus";
			attributeValue = CPQDAStatusHandlers
					.getcpqDaLogDrvStatus(attributeValue);
			break;
		case CPQSCSILOGDRVSTATUS:
			attributeName = "cpqScsiLogDrvStatus";
			attributeValue = CPQScsIHandlers
					.getcpqScsiLogDrvStatus(attributeValue);
			break;
		case CPQDACNTLRBOARDSTATUS:
			attributeName = "cpqDaCntlrBoardStatus";
			attributeValue = CPQDAStatusHandlers
					.getcpqDaCntlrBoardStatus(attributeValue);
			break;
		case CPQDATAPEDRVSTATUS:

			attributeName = "cpqDaTapeDrvStatus";
			attributeValue = CPQDAStatusHandlers
					.getcpqDaTapeDrvStatus(attributeValue);
			break;
		// HPMRF cpqDaSpareStatus
		case CPQDASPARESTATUS:

			attributeName = "cpqDaSpareStatus";
			attributeValue = CPQDAStatusHandlers
					.getcpqDaSpareStatus(attributeValue);
			break;
		// HPMRF cpqDaPhyDrvStatus
		case CPQDAPHYDRVSTATUS:

			attributeName = "cpqDaPhyDrvStatus";
			attributeValue = CPQDaAccelHandler
					.getcpqDaPhyDrvStatus(attributeValue);
			break;
		case CPQDAACCELERRCODE:

			attributeName = "cpqDaAccelErrCode";
			attributeValue = CPQDaAccelHandler
					.getcpqDaAccelErrCode(attributeValue);
			break;
		case CPQDAACCELSTATUS:

			attributeName = "cpqDaAccelStatus";
			attributeValue = CPQDaAccelHandler
					.getcpqDaAccelStatus(attributeValue);
			break;

		case CPQDACNTLRMODEL:

			attributeName = "cpqDaCntlrModel";
			attributeValue = CPQDaAccelHandler
					.getcpqDaCntlrModel(attributeValue);
			break;
		case CPQHOTRAPFLAGS:
			attributeName = "cpqHoTrapFlags";
			attributeValue = CPQHoTrapFlags.getcpqHoTrapFlags(attributeValue);
			break;
		case CPQMEALARMSAMPLETYPE:
			attributeName = "cpqMeAlarmSampleType";
			attributeValue = CPQHoTrapFlags
					.getcpqMeAlarmSampleType(attributeValue);
			break;
		case DSX1LINESTATUS:
			// attributeValue =
			// translatedsx1LineStatus(Integer.valueOf(attributeValue));
			break;
		case DSX3LINESTATUS:
			// attributeValue =
			// translatedsx3LineStatus(Integer.valueOf(attributeValue));
			break;
		case RDBMSRELSTATE:

			attributeName = "rdbmsRelState";
			attributeValue = ExtreamTrapSystem.getrdbmsRelState(attributeValue);
			break;
		case ORALISTENERSTATE:
			attributeName = "oraListenerState";
			attributeValue = ExtreamTrapSystem
					.getoraListenerState(attributeValue);
			break;
		default:
			status = false;
			break;
		}
		if (status) {
			notif.addAdditionalAttribute(attributeName, attributeValue);
		}
		return status;
	}

}
