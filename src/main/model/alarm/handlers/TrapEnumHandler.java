package com.ericsson.oss.mediation.translator.model.alarm.handlers;

import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;

public class TrapEnumHandler {

	private TrapEnumHandler() {

	}

	// //SBG:TrapEnumResult, ISBLADE:TrapEnumResult
	public static String getTrapEnumResult(final String Value) {
		String str11 = "";
		switch (Integer.valueOf(Value.toString().trim()).intValue()) {
		case 1:
			str11 = "ok(1).";
			break;
		case 2:
			str11 = "notOk(2).";
			break;
		case 49:
			str11 = "unknown(49).";
			break;
		case 50:
			str11 = "unused(50).";
			break;
		default:
			str11 = "-----";
		}
		return str11;
	}

	// //SBG:sbgEnumCase, ISBLADE:isbladeTrapEnumCause
	public static String getTrapEnumStatus(final String Value) {
		String str22 = "";
		switch (Integer.valueOf(Value.toString().trim()).intValue()) {
		case 1:
			str22 = "working(1).";
			break;
		case 2:
			str22 = "notWorking(2).";
			break;
		case 3:
			str22 = "testing(3).";
			break;
		case 49:
			str22 = "unknown(49).";
			break;
		case 50:
			str22 = "unused(50).";
			break;
		default:
			str22 = "-----";
		}
		return str22;
	}

	// //SBG:sbgEnumCase, ISBLADE:isbladeTrapEnumCause
	public static String getTrapEnumCause(final String Value) {
		String str = "";
		switch (Integer.valueOf(Value.toString().trim()).intValue()) {
		case 1:
			str = "processorInformation(1).";
			break;
		case 2:
			str = "systemInformation(2).";
			break;
		case 3:
			str = "softwareInformation(3).";
			break;
		case 4:
			str = "ispReport(4).";
			break;
		case 10:
			str = "securityInformation(10).";
			break;
		case 11:
			str = "performanceMeasurement(11).";
			break;
		case 12:
			str = "logInformation(12).";
			break;
		case 13:
			str = "statusChangeInformation(13).";
			break;
		case 14:
			str = "linkInformation(14).";
			break;
		case 15:
			str = "snmpInformation(15).";
			break;
		case 20:
			str = "networkSyncInformation(20).";
			break;
		case 21:
			str = "testResult(21).";
			break;
		case 22:
			str = "interfaceInformation(22).";
			break;
		case 23:
			str = "connectionInformation(23).";
			break;
		case 24:
			str = "equipmentInformation(24).";
			break;
		case 25:
			str = "labelSwitchInformation(25).";
			break;
		case 49:
			str = "unknown(49).";
			break;
		case 50:
			str = "unused(50).";
			break;
		default:
			str = "-----";
		}
		return str;
	}

	// RAD nsVrBgpPeerState
	public static String getfunkSbrTrapVarComp(final String Value) {
		String funkSbrTrapVarComp = null;

		switch (Integer.valueOf(Value.toString()).intValue()) {
		case 1:
			funkSbrTrapVarComp = HandleOtherAlarmAttribute.TrapVarComp
					.concat(" core(1).");
			break;
		case 2:
			funkSbrTrapVarComp = HandleOtherAlarmAttribute.TrapVarComp
					.concat(" accounting(2).");
			break;
		case 3:
			funkSbrTrapVarComp = HandleOtherAlarmAttribute.TrapVarComp
					.concat(" authentication(3).");
			break;
		default:
			funkSbrTrapVarComp = HandleOtherAlarmAttribute.TrapVarComp
					.concat(" --------------");
			break;
		}
		return funkSbrTrapVarComp;
	}

	// NETSCREEN nsVrBgpPeerState
	public static String getInsVrBgpPeerState(final String Value) {
		String nsVrBgpPeerState = null;

		switch (Integer.parseInt(Value.toString())) {
		case 1:
			nsVrBgpPeerState = "idle";
			break;
		case 2:
			nsVrBgpPeerState = "connect";
			break;
		case 3:
			nsVrBgpPeerState = "active";
			break;
		case 4:
			nsVrBgpPeerState = "opensent";
			break;
		case 5:
			nsVrBgpPeerState = "openconfirm";
			break;
		case 6:
			nsVrBgpPeerState = "established";
			break;
		default:
			break;
		}
		return nsVrBgpPeerState;
	}

}
