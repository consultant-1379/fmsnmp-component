package com.ericsson.oss.mediation.translator.model.alarm.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.translator.constant.TranslationConstant;
import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;

public class OidVarApSysMgmtHandlers {

	private OidVarApSysMgmtHandlers() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleOtherAlarmAttribute.class);

	// ACME _OID_VAR_AP_SYS_MGMT_POWER_PRESENCE

	public static String get_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE(
			final String Value) {
		String txtVarApSysMgmtPowerPresence = "";
		switch (Integer.valueOf(Value).intValue()) {
		case 0:
			txtVarApSysMgmtPowerPresence = TranslationConstant.txtVarApSysMgmtPowerPresence
					.concat("down(0).");
			break;
		case 1:
			txtVarApSysMgmtPowerPresence = TranslationConstant.txtVarApSysMgmtPowerPresence
					.concat("up(1).");
			break;
		default:
			txtVarApSysMgmtPowerPresence = TranslationConstant.txtVarApSysMgmtPowerPresence
					.concat("-----");
		}
		return txtVarApSysMgmtPowerPresence;
	}

	// ACME _OID_VAR_AP_SYS_MGMT_FAN_LOCATION

	public static String get_OID_VAR_AP_SYS_MGMT_FAN_LOCATION(final String Value) {
		String txtVarApSysMgmtFanLocation = "";
		switch (Integer.valueOf(Value).intValue()) {
		case 0:
			txtVarApSysMgmtFanLocation = TranslationConstant.txtVarApSysMgmtFanLocation
					.concat("left(0).");
			break;
		case 1:
			txtVarApSysMgmtFanLocation = TranslationConstant.txtVarApSysMgmtFanLocation
					.concat("middle(1).");
			break;
		case 2:
			txtVarApSysMgmtFanLocation = TranslationConstant.txtVarApSysMgmtFanLocation
					.concat("right(2).");
			break;
		default:
			txtVarApSysMgmtFanLocation = TranslationConstant.txtVarApSysMgmtFanLocation
					.concat("-----");
		}
		return txtVarApSysMgmtFanLocation;
	}

	// ACME _OID_VAR_AP_SYS_MGMT_RED_ROLE

	public static String get_OID_VAR_AP_SYS_MGMT_RED_ROLE(final String Value) {

		String txtVarApSysMgmtRedRole = "";
		switch (Integer.valueOf(Value).intValue()) {
		case 0:
			txtVarApSysMgmtRedRole = TranslationConstant.txtVarApSysMgmtRedRole
					.concat("primary(0).");
			break;
		case 1:
			txtVarApSysMgmtRedRole = TranslationConstant.txtVarApSysMgmtRedRole
					.concat("secondary(1).");
			break;
		default:
			txtVarApSysMgmtRedRole = TranslationConstant.txtVarApSysMgmtRedRole
					.concat("-----");
		}
		return txtVarApSysMgmtRedRole;

	}

	// ACME _OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE

	public static String get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE(
			final String Value) {
		String txtVarApSysMgmtRedTransState = "";
		switch (Integer.valueOf(Value).intValue()) {
		case 0:
			txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState
					.concat("out-of-service(0).");
			break;
		case 1:
			txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState
					.concat("active(1).");
			break;
		case 2:
			txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState
					.concat("standby(2).");
			break;
		case 3:
			txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState
					.concat("no-peer(3).");
			break;
		default:
			txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState
					.concat("-----");
		}

		return txtVarApSysMgmtRedTransState;
	}

	// ACME _OID_VAR_AP_SYS_MGMT_RADIUS_DOWN

	public static String get_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN(final String Value) {
		String txtVarApSysMgmtRadiusDown = "";

		switch (Integer.valueOf(Value).intValue()) {
		case 0:
			txtVarApSysMgmtRadiusDown = TranslationConstant.txtVarApSysMgmtRadiusDown
					.concat("all-servers-down(0).");
			break;
		case 1:
			txtVarApSysMgmtRadiusDown = TranslationConstant.txtVarApSysMgmtRadiusDown
					.concat("some-servers-down(1).");
			break;
		default:
			txtVarApSysMgmtRadiusDown = TranslationConstant.txtVarApSysMgmtRadiusDown
					.concat(" -----");
		}
		return txtVarApSysMgmtRadiusDown;
	}

	// ACME _OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON

	public static String get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON(
			final String Value) {
		String txtVarApSysMgmtSAStatusReason = "";
		switch (Integer.valueOf(Value).intValue()) {
		case 0:
			txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason
					.concat("administrative(0).");
			break;
		case 1:
			txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason
					.concat("oosbyproxyerror(1).");
			break;
		case 2:
			txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason
					.concat("standby(2).");
			break;
		case 3:
			txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason
					.concat("inservice(3).");
			break;
		case 4:
			txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason
					.concat("constraintsexceeded(4).");
			break;
		case 5:
			txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason
					.concat("unresponsive(5).");
			break;
		default:
			txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason
					.concat("-----");
		}
		return txtVarApSysMgmtSAStatusReason;
	}

	// ACME _OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL

	public static String get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL(
			final String Value) {
		String txtVarApSysMgmtAuthFailLevel = "";

		LOGGER.info("Var OID Matched : apSysMgmtAuthFailLevel");
		switch (Integer.valueOf(Value.toString()).intValue()) {
		case 0:
			txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel
					.concat("login(0).");
			break;
		case 1:
			txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel
					.concat("user(1).");
			break;
		case 2:
			txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel
					.concat("priv(2).");
			break;
		case 3:
			txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel
					.concat("shell(3).");
			break;
		default:
			txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel
					.concat("-----");
		}
		return txtVarApSysMgmtAuthFailLevel;
	}

	// ACME get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE (need to correct the body)

	public static String get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE(final String Value) {
		String txtVarApSysMgmtAuthFailProto = "";
		switch (Integer.valueOf(Value.toString()).intValue()) {
		case 0:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("console(0).");
			break;
		case 1:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("telnet(1).");
			break;
		case 2:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("ftp(2).");
			break;
		case 3:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("ssh(3).");
			break;
		case 4:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("sftp(4).");
			break;
		default:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("-----");
		}
		return txtVarApSysMgmtAuthFailProto;
	}

	// ACME _OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO

	public static String get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO(
			final String Value) {
		String txtVarApSysMgmtAuthFailProto = "";
		switch (Integer.valueOf(Value.toString()).intValue()) {
		case 0:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("console(0).");
			break;
		case 1:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("telnet(1).");
			break;
		case 2:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("ftp(2).");
			break;
		case 3:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("ssh(3).");
			break;
		case 4:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("sftp(4).");
			break;
		default:
			txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto
					.concat("-----");
		}
		return txtVarApSysMgmtAuthFailProto;
	}

	// ACME _OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON

	public static String get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON(
			final String attributeValue) {
		String txtVarApapSysMgmtSipInterfaceStatusReason = "";
		switch (Integer.valueOf(attributeValue.toString()).intValue()) {
		case 0:
			txtVarApapSysMgmtSipInterfaceStatusReason = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason
					.concat("administrative(0).");
			break;
		case 1:
			txtVarApapSysMgmtSipInterfaceStatusReason = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason
					.concat("oosbyproxyerror(1) .");
			break;
		case 2:
			txtVarApapSysMgmtSipInterfaceStatusReason = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason
					.concat("standby(2).");
			break;
		case 3:
			txtVarApapSysMgmtSipInterfaceStatusReason = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason
					.concat("inservice(3).");
			break;
		case 4:
			txtVarApapSysMgmtSipInterfaceStatusReason = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason
					.concat("constraintsexceeded(4).");
			break;
		case 5:
			txtVarApapSysMgmtSipInterfaceStatusReason = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason
					.concat("unresponsive(5).");
			break;
		default:
			txtVarApapSysMgmtSipInterfaceStatusReason = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason
					.concat("-----");
		}
		return txtVarApapSysMgmtSipInterfaceStatusReason;
	}

	// ACME _OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUSN

	public static String get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS(
			final String attributeValue) {
		String txtVarApapSysMgmtSipInterfaceStatus = "";

		switch (Integer.valueOf(attributeValue.toString()).intValue()) {
		case 0:
			txtVarApapSysMgmtSipInterfaceStatus = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatus
					.concat("inservice(0).");
			break;
		case 1:
			txtVarApapSysMgmtSipInterfaceStatus = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatus
					.concat("outofservice(1).");
			break;
		default:
			txtVarApapSysMgmtSipInterfaceStatus = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatus
					.concat("-----");
		}
		return txtVarApapSysMgmtSipInterfaceStatus;
	}

	// ACME _OID_VAR_AP_ENUM_SERVER_STATUS

	public static String get_OID_VAR_AP_ENUM_SERVER_STATUS(
			final String attributeValue) {
		String txtApENUMServerStatus = "";

		switch (Integer.valueOf(attributeValue.toString()).intValue()) {
		case 0:
			txtApENUMServerStatus = TranslationConstant.txtApENUMServerStatus
					.concat("inservice(0).");
			break;
		case 1:
			txtApENUMServerStatus = TranslationConstant.txtApENUMServerStatus
					.concat("lowerpriority(1).");
			break;
		case 2:
			txtApENUMServerStatus = TranslationConstant.txtApENUMServerStatus
					.concat("oosunreachable(2).");
			break;
		default:
			txtApENUMServerStatus = TranslationConstant.txtApENUMServerStatus
					.concat("-----");
		}
		return txtApENUMServerStatus;
	}

}
