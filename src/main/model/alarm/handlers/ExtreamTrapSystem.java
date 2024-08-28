package com.ericsson.oss.mediation.translator.model.alarm.handlers;

import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;

public class ExtreamTrapSystem {

	private ExtreamTrapSystem() {

	}

	// EXTREME extremeTrapAuthorerrorType

	public static String getextremeTrapAuthorerrorType(
			final String attributeValue) {
		String vrrpTrapAuthErrorType = "";
		switch (Integer.parseInt(attributeValue.toString())) {

		case 1:
			vrrpTrapAuthErrorType = "invalidAuthType";
			break;
		case 2:
			vrrpTrapAuthErrorType = "authTypeMismatch";
			break;
		case 3:
			vrrpTrapAuthErrorType = "authFailure";

			break;
		default:
			vrrpTrapAuthErrorType = "defaultFailure";

			break;
		}
		return vrrpTrapAuthErrorType;
	}

	// EXTREME extremeSystemPowerState

	public static String getextremeSystemPowerState(final String attributeValue) {
		String extremeSystemPowerState = "";
		switch (Integer.parseInt(attributeValue.toString())) {
		case 1:
			extremeSystemPowerState = "computing";
			break;
		case 2:
			extremeSystemPowerState = "sufficientButNotRedundantPower";
			break;
		case 3:
			extremeSystemPowerState = "redundantPowerAvailable";
			break;
		case 4:
			extremeSystemPowerState = "insufficientPower";
			break;
		default:
			extremeSystemPowerState = "Entered input value should be between 1..4";
			break;

		}

		return extremeSystemPowerState;
	}

	// EXTREME extremePortMauStatus

	public static String getextremePortMauStatus(final String attributeValue) {
		String extremePortMauStatus = "";
		switch (Integer.parseInt(attributeValue.toString())) {
		case 1:
			extremePortMauStatus = "inserted";
			break;
		case 2:
			extremePortMauStatus = "empty";
			break;

		}

		return extremePortMauStatus;
	}

	// EXTREME extremeEsrpDmnState

	public static String getextremeEsrpDmnState(final String attributeValue) {

		String extremeEsrpDmnState = "";
		switch (Integer.parseInt(attributeValue.toString())) {
		case 0:
			extremeEsrpDmnState = "neutral";
			break;
		case 1:
			extremeEsrpDmnState = "master";
			break;
		case 2:
			extremeEsrpDmnState = "slave";
		case 3:
			extremeEsrpDmnState = "premaster";
			break;
		case 4:
			extremeEsrpDmnState = "aware";
			break;
		default:
			break;
		}

		return extremeEsrpDmnState;
	}

	// MMC rdbmsRelState

	public static String getrdbmsRelState(final String attributeValue) {

		String rdbmsState = "";

		switch (Integer.valueOf(attributeValue.toString()).intValue()) {
		case 1:
			rdbmsState = HandleOtherAlarmAttribute.rdbmsState
					.concat("other(1).");
			break;
		case 2:
			rdbmsState = HandleOtherAlarmAttribute.rdbmsState
					.concat("active(2).");
			break;
		case 3:
			rdbmsState = HandleOtherAlarmAttribute.rdbmsState
					.concat("available(3).");
			break;
		case 4:
			rdbmsState = HandleOtherAlarmAttribute.rdbmsState
					.concat("restricted(4).");
			break;
		case 5:
			rdbmsState = HandleOtherAlarmAttribute.rdbmsState
					.concat("unavailable(5).");
			break;
		default:
			rdbmsState = HandleOtherAlarmAttribute.rdbmsState
					.concat("--------------");
			break;
		}
		return rdbmsState;
	}

	// MMC rdbmsRelState

	public static String getoraListenerState(final String attributeValue) {

		String genericListenerState = "";

		switch (Integer.valueOf(attributeValue.toString()).intValue()) {
		case 1:
			genericListenerState = HandleOtherAlarmAttribute.genericListenerState
					.concat("up(1).");
			break;
		case 2:
			genericListenerState = HandleOtherAlarmAttribute.genericListenerState
					.concat("down(2).");
			break;
		default:
			genericListenerState = HandleOtherAlarmAttribute.genericListenerState
					.concat("--------------");
			break;
		}
		return genericListenerState;
	}

}
