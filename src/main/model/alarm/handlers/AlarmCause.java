package com.ericsson.oss.mediation.translator.model.alarm.handlers;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;

public class AlarmCause {

	/**
	 * to avoid PMD Warnings
	 */
	private AlarmCause() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleOtherAlarmAttribute.class);

	// //TIGRIS accAlarmCause
	public static String getaccAlarmCause(final String Value) {
		int probableCause = 0;
		final String flag = "0";
		try {
			probableCause = Integer.parseInt(Value.toString());
			switch (probableCause) {
			case 1:
				probableCause = 0;
				break;
			case 2:
				probableCause = 570;
				break;
			case 3:
				probableCause = 5;
				break;
			case 4:
				probableCause = 6;
				break;
			case 5:
				probableCause = 7;
				break;
			case 6:
				probableCause = 8;
				break;
			case 7:
				probableCause = 9;
				break;
			case 8:
				probableCause = 11;
				break;
			case 9:
				probableCause = 325;
				break;
			case 10:
				probableCause = 313;
				break;
			case 11:
				probableCause = 0;
				break;
			case 12:
				probableCause = 517;
				break;
			case 13:
				probableCause = 59;
				break;
			case 14:
				probableCause = 58;
				break;
			case 15:
				probableCause = 55;
				break;
			case 16:
				probableCause = 315;
			case 17:
				probableCause = 151;
				break;
			case 18:
				probableCause = 152;
				break;
			case 19:
				probableCause = 153;
				break;
			case 20:
				probableCause = 332;
				break;
			case 21:
				probableCause = 357;
				break;
			case 22:
				probableCause = 346;
				break;
			case 23:
				probableCause = 348;
				break;
			case 24:
				probableCause = 347;
				break;
			case 25:
				probableCause = 308;
				break;
			case 26:
				probableCause = 344;
				break;
			}
		} catch (Exception ex) {
			LOGGER.info("setProbableCause Exception." + ex.getMessage());
			return flag;// Unknown
		}
		final String probCause = String.valueOf(probableCause);
		return probCause;
	}

	// SRD:srdNotificationAddText
	public static String getsrdNotificationAddText(final String Value) {
		String psstr = "";
		try {
			final String str1 = Value.toString();
			LOGGER.info("Additional Text for SUNPLATFORM MIB is " + str1);
			final StringTokenizer sunstr = new StringTokenizer(str1, "/");
			while (sunstr.hasMoreTokens()) {
				final String ps = sunstr.nextToken();

				if (ps.startsWith("PS")) {
					psstr = "PS=" + ps;

				}
			}
			LOGGER.info("Power Supply Info for SUNPLATFORM MIB is " + psstr);

		}

		catch (Exception e) {
			LOGGER.info("No power supply additional text/Problem in processing powersupply info");
			return psstr;
		}
		return psstr;
	}

}
