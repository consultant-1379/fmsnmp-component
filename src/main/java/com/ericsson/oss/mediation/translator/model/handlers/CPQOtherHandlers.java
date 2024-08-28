package com.ericsson.oss.mediation.translator.model.handlers;

public class CPQOtherHandlers {

	/**
	 * private constructor to avoid PMD warnings
	 */
	private CPQOtherHandlers() {

	}

	// hpmrfp cpqSm2CntlrSelfTestErrors
	public static String getcpqSm2CntlrSelfTestErrors(
			final String attributevalue) {

		final StringBuffer cpqSm2CntlrSelfTestErrorsResult = new StringBuffer();
		final int cpqSm2CntlrSelfTestErrorValue = Integer
				.parseInt(attributevalue.toString());
		int tempcpqSm2CntlrSelfTestErrorValue = cpqSm2CntlrSelfTestErrorValue;
		final String cpqSm2CntlrSelfTestErrorStrings[] = {
				"Busmaster I/O read error.", "Memory test error.",
				"Modem firmware error.", "Modem UART error.",
				"Serial port UART error.", "Keyboard interface error.",
				"Battery interface error.", "NVRAM interface error.",
				"NVRAM write / read / verify error.", "Video Error",
				"PCMCIA Error", "NIC Error", "Mouse interface error.",
				"CPLD error.", "SRAM error.", "EEPROM error.", "I2C error." };
		// StringBuffer errorResultString = new StringBuffer();
		for (int i = 0; i < 17; i++) {
			if ((tempcpqSm2CntlrSelfTestErrorValue & 1) == 1) {
				cpqSm2CntlrSelfTestErrorsResult
						.append(cpqSm2CntlrSelfTestErrorStrings[i] + "\n");
			}
			tempcpqSm2CntlrSelfTestErrorValue = tempcpqSm2CntlrSelfTestErrorValue >> 1;
		}
		return cpqSm2CntlrSelfTestErrorsResult.toString();
	}

	// hpmrfp cpqNicIfPhysAdapterStatus
	public static String getcpqNicIfPhysAdapterStatus(
			final String attributevalue) {
		String str = "";
		final StringBuffer cpqstr = new StringBuffer();
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			str = "unknown";
			break;
		case 2:
			str = "ok";
			break;
		case 3:
			str = "generalFailure";
			break;
		case 4:
			str = "linkFailure";
			break;
		default:
			break;
		}
		return cpqstr.append("\n\tThe physical adapter status : ").append(str)
				.toString();
	}

	// hpmrfp trendIndication
	public static String gettrendIndication(final String attributevalue) {
		String str = "";
		final StringBuffer cpqstr = new StringBuffer();
		switch (Integer.parseInt(attributevalue.toString())) {
		case 0:
			str = "unknown";
			break;
		case 1:
			str = "notApplicable";
			break;
		case 2:
			str = "trendingUp";
			break;
		case 3:
			str = "trendingDown";
			break;
		case 4:
			str = "noChange";
			break;
		default:
			break;
		}
		return cpqstr.append("\n\tTrendIndication :  ").append(str).toString();
	}
}
