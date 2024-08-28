package com.ericsson.oss.mediation.translator.model.handlers;

/**
 * @author tcsgopa
 * 
 */
public class CPQScsIHandlers {

	/**
	 * private constructor to avoid PMD warnings
	 */
	private CPQScsIHandlers() {

	}

	// hpmrfp cpqScsiLogDrvStatus
	public static String getcpqScsiLogDrvStatus(final String attributevalue) {
		String str;
		final StringBuffer cpqstr = new StringBuffer();
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
			str = "degraded";
			break;
		case 11:
			str = "disabled";
			break;
		default:
			str = "";
			break;
		}
		return cpqstr.append("\n\tSCSI Logical Drive Status : ").append(str)
				.toString();
	}

	// hpmrfp cpqScsiPhyDrvStatus
	public static String getcpqScsiPhyDrvStatus(final String attributevalue) {
		String str;
		final StringBuffer cpqstr = new StringBuffer();
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
			str = "notConfigured";
			break;
		case 5:
			str = "badCable";
			break;
		case 6:
			str = "missingWasOk";
			break;
		case 7:
			str = "missingWasFailed";
			break;
		case 8:
			str = "predictiveFailure";
			break;
		case 9:
			str = "missingWasPredictiveFailure";
			break;
		case 10:
			str = "offline";
			break;
		case 11:
			str = "missingWasOffline";
			break;
		case 12:
			str = "hardError";
			break;
		default:
			str = "";
			break;
		}
		return cpqstr.append("\n\tSCSI Logical Drive Status : ").append(str)
				.toString();
	}

}
