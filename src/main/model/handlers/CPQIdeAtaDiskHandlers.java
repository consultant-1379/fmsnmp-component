package com.ericsson.oss.mediation.translator.model.handlers;

public class CPQIdeAtaDiskHandlers {

	/**
	 * private constructor to avoid PMD warnings
	 */
	private CPQIdeAtaDiskHandlers() {

	}

	// hpmrfp cpqIdeAtaDiskStatus
	public static String getcpqIdeAtaDiskStatus(final String attributevalue) {
		String str;
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "ok";
			break;
		case 3:
			str = "smartError";
			break;
		case 4:
			str = "failed";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}

	// hpmrfp cpqIdeAtaDiskChannel
	public static String getcpqIdeAtaDiskChannel(final String attributevalue) {
		String str = "";
		final StringBuffer cpqstr = new StringBuffer();
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "channel0";
			break;
		case 3:
			str = "channel1";
			break;
		default:
			break;
		}
		return cpqstr.append("\n\tATA Disk Channel : ").append(str).toString();
	}

	// hpmrfp cpqIdeAtaDiskNumber
	public static String getcpqIdeAtaDiskNumber(final String attributevalue) {
		String str = "";
		final StringBuffer cpqstr = new StringBuffer();
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "channel0";
			break;
		case 3:
			str = "channel1";
			break;
		default:
			break;
		}
		return cpqstr.append("\n\tATA Disk Channel : ").append(str).toString();
	}

	// hpmrfp cpqIdeLogicalDriveStatus
	public static String getcpqIdeLogicalDriveStatus(final String attributevalue) {
		String str;
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "ok";
			break;
		case 3:
			str = "degraded";
			break;
		case 4:
			str = "rebuilding";
			break;
		case 5:
			str = "failed";
			break;
		default:
			str = "";
			break;
		}
		return str;
	}

}
