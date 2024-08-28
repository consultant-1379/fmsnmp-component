package com.ericsson.oss.mediation.translator.model.handlers;

public class CPQHeHandlers {

	/**
	 * private constructor to avoid PMD warnings
	 */
	private CPQHeHandlers() {

	}

	// hpmrfp cpqHeCorrMemLogStatus
	public static String getcpqHeCorrMemLogStatus(final String attributevalue) {
		String str = "";
		final StringBuffer cpqstr = new StringBuffer();
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "notSupported";
			break;
		case 3:
			str = "disabled";
			break;
		case 4:
			str = "enabled";
			break;
		default:
			break;
		}
		return cpqstr.append("\n\tIDE Logical Drive Status : ").append(str)
				.toString();
	}

	// hpmrfp cpqHeThermalDegradedAction
	public static String getcpqHeThermalDegradedAction(
			final String attributevalue) {
		String str = "";
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "notSupported";
			break;
		case 3:
			str = "disabled";
			break;
		case 4:
			str = "enabled";
			break;
		default:
			break;
		}
		return str;
	}

	// hpmrfp cpqHeTemperatureLocale
	public static String getcpqHeTemperatureLocale(final String attributevalue) {
		String str = "";
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "unknown";
			break;
		case 3:
			str = "system";
			break;
		case 4:
			str = "systemBoard";
			break;
		case 5:
			str = "ioBoard";
			break;
		case 6:
			str = "cpu";
			break;
		case 7:
			str = "memory";
			break;
		case 8:
			str = "storage";
			break;
		case 9:
			str = "removableMedia";
			break;
		case 10:
			str = "powerSupply";
			break;
		case 11:
			str = "ambient";
			break;
		case 12:
			str = "chassis";
			break;
		case 13:
			str = "bridgeCard";
			break;
		default:
			break;
		}
		return str;
	}

	// hpmrfp cpqHeFltTolPowerSupplyStatus
	public static String getcpqHeFltTolPowerSupplyStatus(
			final String attributevalue) {
		String str = "";
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "other";
			break;
		case 2:
			str = "unknown";
			break;
		case 3:
			str = "system";
			break;
		case 4:
			str = "systemBoard";
			break;
		case 5:
			str = "ioBoard";
			break;
		case 6:
			str = "cpu";
			break;
		case 7:
			str = "memory";
			break;
		case 8:
			str = "storage";
			break;
		case 9:
			str = "removableMedia";
			break;
		case 10:
			str = "powerSupply";
			break;
		case 11:
			str = "ambient";
			break;
		case 12:
			str = "chassis";
			break;
		case 13:
			str = "bridgeCard";
			break;
		default:
			break;
		}
		return str;
	}

}
