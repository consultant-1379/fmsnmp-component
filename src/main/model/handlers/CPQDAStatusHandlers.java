package com.ericsson.oss.mediation.translator.model.handlers;

public class CPQDAStatusHandlers {

	/**
	 * private constructor to avoid PMD warings
	 */
	private CPQDAStatusHandlers() {

	}

	// hpmrfp cpqDaSpareStatus
	public static String getcpqDaSpareStatus(final String attributevalue) {
		String str;

		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "ok";
			break;
		case 3:
			str = "failed";
			break;
		case 4:
			str = "predictiveFailure";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}

	// hpmrfp cpqDaTapeDrvStatus
	public static String getcpqDaTapeDrvStatus(final String attributevalue) {
		String str;

		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "invalid";
			break;
		case 3:
			str = "degraded";
			break;
		case 4:
			str = "failed";
			break;
		case 5:
			str = "offline";
			break;
		case 6:
			str = "missingWasOk";
			break;
		case 7:
			str = "missingWasOffline";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}

	// hpmrfp cpqDaCntlrBoardStatus
	public static String getcpqDaCntlrBoardStatus(final String attributevalue) {
		String str;

		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "ok";
			break;
		case 3:
			str = "generalFailure";
			break;
		case 4:
			str = "cableProblem";
			break;
		case 5:
			str = "poweredOff";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}

	// hpmrfp cpqDaLogDrvStatus
	public static String getcpqDaLogDrvStatus(final String attributevalue) {
		String str;

		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "ok";
			break;
		case 3:
			str = "failed";
			break;
		case 4:
			str = "unconfigured";
			break;
		case 5:
			str = "recovering";
			break;
		case 6:
			str = "readyForRebuild";
			break;
		case 7:
			str = "rebuilding";
			break;
		case 8:
			str = "wrongDrive";
			break;
		case 9:
			str = "badConnect";
			break;
		case 10:
			str = "overheating";
			break;
		case 11:
			str = "shutdown";
			break;
		case 12:
			str = "expanding";
			break;
		case 13:
			str = "notAvailable";
			break;
		case 14:
			str = "queuedForExpansion";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}
}
