package com.ericsson.oss.mediation.translator.model.alarm.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.translator.constant.TranslationConstant;
import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;

public class AlarmState {

	private AlarmState() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleOtherAlarmAttribute.class);

	// IFADMINSTATUS
	public static String getIfAdminState(final String Value) {
		String IfAdminState = Value;

		switch (Integer.valueOf(Value.toString()).intValue()) {
		case 1:
			IfAdminState = HandleOtherAlarmAttribute.IfAdminState
					.concat("up(1).");
			break;
		case 2:
			IfAdminState = HandleOtherAlarmAttribute.IfAdminState
					.concat("down(2).");
			break;
		case 3:
			IfAdminState = HandleOtherAlarmAttribute.IfAdminState
					.concat("testing(3).");
			break;
		default:
			IfAdminState = HandleOtherAlarmAttribute.IfAdminState
					.concat(" --------------");
			break;
		}
		return IfAdminState;
	}

	// IFOPERSTATUS
	public static String getIfOperState(final String Value) {
		String IfOperState = Value;

		switch (Integer.valueOf(Value.toString()).intValue()) {
		case 1:
			IfOperState = HandleOtherAlarmAttribute.IfOperState
					.concat("up(1).");
			break;
		case 2:
			IfOperState = HandleOtherAlarmAttribute.IfOperState
					.concat("down(2).");
			break;
		case 3:
			IfOperState = HandleOtherAlarmAttribute.IfOperState
					.concat("testing(3).");
			break;
		case 4:
			IfOperState = HandleOtherAlarmAttribute.IfOperState
					.concat("unknown(4).");
			break;
		case 5:
			IfOperState = HandleOtherAlarmAttribute.IfOperState
					.concat("dormant(5) .");
			break;
		case 6:
			IfOperState = HandleOtherAlarmAttribute.IfOperState
					.concat("notPresent(6).");
			break;
		case 7:
			IfOperState = HandleOtherAlarmAttribute.IfOperState
					.concat("lowerLayerDown(7).");
			break;
		default:
			IfOperState = HandleOtherAlarmAttribute.IfOperState
					.concat(" --------------");
			break;
		}
		return IfOperState;
	}

	// ACME _OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE

	public static String getOidVarAPEnvMonTrapPreviousState(final String Value) {
		// String IfOperState = null;

		String txtVarApEnvMonTrapPreviousState = "";
		LOGGER.info("Var OID Matched : apEnvMonTrapPreviousState");
		switch (Integer.valueOf(Value).intValue()) {
		case 1:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("initial(1).");
			break;
		case 2:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("normal(2).");
			break;
		case 3:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("minor(3).");
			break;
		case 4:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("major(4).");
			break;
		case 5:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("critical(5).");
			break;
		case 6:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("shutdown(6).");
			break;
		case 7:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("notPresent(7).");
			break;
		case 8:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("notFunctioning(8).");
			break;
		case 9:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("unknown(9).");
			break;
		default:
			txtVarApEnvMonTrapPreviousState = TranslationConstant.txtVarApEnvMonTrapPreviousState
					.concat("-----");
		}

		return txtVarApEnvMonTrapPreviousState;
	}

	// ACME _OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE

	public static String getOidVarAPEnvMonTrapCurrentState(final String Value) {
		// String IfOperState = null;

		String txtVarApEnvMonTrapCurrentState = "";
		switch (Integer.valueOf(Value).intValue()) {
		case 1:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("initial(1).");
			break;
		case 2:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("normal(2).");
			break;
		case 3:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("minor(3).");
			break;
		case 4:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("major(4).");
			break;
		case 5:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("critical(5).");
			break;
		case 6:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("shutdown(6).");
			break;
		case 7:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("notPresent(7).");
			break;
		case 8:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("notFunctioning(8).");
			break;
		case 9:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("unknown(9).");
			break;
		default:
			txtVarApEnvMonTrapCurrentState = TranslationConstant.txtVarApEnvMonTrapCurrentState
					.concat("-----");
		}
		return txtVarApEnvMonTrapCurrentState;
	}

	// ACME "_OID_VAR_AP_SYS_LOG_HIST_LEVEL"

	public static String getOidVarAPSysLogHistLevel(final String Value) {
		String txtVarApSyslogHistLevel = "";
		switch (Integer.valueOf(Value).intValue()) {
		case 1:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("emergency(1).");
			break;
		case 2:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("critical(2).");
			break;
		case 3:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("major(3).");
			break;
		case 4:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("minor(4) .");
			break;
		case 5:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("warning(5).");
			break;
		case 6:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("notice(6).");
			break;
		case 7:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("info(7).");
			break;
		case 8:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("trace(8).");
			break;
		case 9:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("debug(9).");
			break;
		default:
			txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel
					.concat("-----");
		}
		return txtVarApSyslogHistLevel;
	}

}
