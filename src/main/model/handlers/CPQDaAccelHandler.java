package com.ericsson.oss.mediation.translator.model.handlers;

public class CPQDaAccelHandler {

	/**
	 * private constructor to avoid PMD warnings
	 */
	private CPQDaAccelHandler() {

	}

	// hpmrfp cpqDaAccelErrCode c
	public static String getcpqDaAccelErrCode(final String attributevalue) {
		String str;

		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "invalid";
			break;
		case 3:
			str = "badConfig";
			break;
		case 4:
			str = "lowBattery";
			break;
		case 5:
			str = "disableCmd";
			break;
		case 6:
			str = "noResources";
			break;
		case 7:
			str = "notConnected";
			break;
		case 8:
			str = "badMirrorData";
			break;
		case 9:
			str = "readErr";
			break;
		case 10:
			str = "writeErr";
			break;
		case 11:
			str = "configCmd";
			break;
		case 12:
			str = "expandInProgress";
			break;
		case 13:
			str = "snapshotInProgress";
			break;
		case 14:
			str = "redundantLowBattery";
			break;
		case 15:
			str = "redundantSizeMismatch";
			break;
		case 16:
			str = "redundantCacheFailure";
			break;
		case 17:
			str = "excessiveEccErrors";
			break;
		case 18:
			str = "adgEnablerMissing";
			break;
		case 19:
			str = "postEccErrors";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}

	// hpmrfp cpqDaAccelStatus
	public static String getcpqDaAccelStatus(final String attributevalue) {
		String str;

		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "invalid";
			break;
		case 3:
			str = "enabled";
			break;
		case 4:
			str = "tmpDisabled";
			break;
		case 5:
			str = "permDisabled";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}

	// hpmrfp cpqDaCntlrModel
	public static String getcpqDaCntlrModel(final String attributevalue) {
		String str;
		final StringBuffer cpqstr2 = new StringBuffer();
		switch (Integer.parseInt(attributevalue.toString())) {
		case 1:
			str = "other";
			break;
		case 2:
			str = "ida";
			break;
		case 3:
			str = "idaExpansion";
			break;
		case 4:
			str = "ida-2";
			break;
		case 5:
			str = "smart";
			break;
		case 6:
			str = "smart-2e";
			break;
		case 7:
			str = "smart-2p";
			break;
		case 8:
			str = "smart-2sl";
			break;
		case 9:
			str = "smart-3100es";
			break;
		case 10:
			str = "smart-3200";
			break;
		case 11:
			str = "smart-2dh";
			break;
		case 12:
			str = "smart-221";
			break;
		case 13:
			str = "sa-4250es";
			break;
		case 14:
			str = "sa-4200";
			break;
		case 15:
			str = "sa-integrated";
			break;
		case 16:
			str = "sa-431";
			break;
		case 17:
			str = "sa-5300";
			break;
		case 18:
			str = "raidLc2";
			break;
		case 19:
			str = "sa-5i";
			break;
		case 20:
			str = "sa-532";
			break;
		case 21:
			str = "sa-531";
			break;
		case 22:
			str = "sa-641";
			break;
		case 23:
			str = "sa-642";
			break;
		case 24:
			str = "sa-6400";
			break;
		case 25:
			str = "sa-6400em";
			break;
		case 26:
			str = "sa-6i";
			break;
		default:
			str = "";
			break;
		}
		return cpqstr2.append("\n\tArray Controller Model : ").append(str)
				.toString();
	}

	// hpmrfp cpqDaPhyDrvStatus
	public static String getcpqDaPhyDrvStatus(final String attributevalue) {
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
}
