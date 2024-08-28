package com.ericsson.oss.mediation.translator.model;

public class HandleProbableCause {

	private HandleProbableCause() {
	}

	// ESA-SNF ALARM_PC
	public static String getAlarmProbabaleCause(final String probablecause) {

		String alarmPC = SnmpPcMaps.getProbableCause(probablecause);
		if (alarmPC == null) {
			alarmPC = "0";
		}

		return alarmPC;
	}

	// ERICSSON-SOG-ALARM-MIB newAlarmNameProbableCause

	public static String getnewAlarmNameProbableCause(final String alarmName) {

		String newAlarmNameProbableCause = SnmpPcMaps
				.getnewNameProbableCause(alarmName);
		if (newAlarmNameProbableCause == null) {
			newAlarmNameProbableCause = "0";

		}

		return newAlarmNameProbableCause;

	}

	// WPP
	public static String getWPPProbabaleCuase(final String value) {
		String probCause = null;
		int probableCause = Integer.parseInt(value);
		if (probableCause > 0 && probableCause <= 57) {
			probableCause += 300;
		}
		if (probableCause >= 10000 && probableCause <= 10156) {
			probableCause -= 10000;
		}
		if (probableCause >= 16001 && probableCause <= 16074) {
			probableCause -= 15500;
		}
		probCause = String.valueOf(probableCause);
		return probCause;
	}

	public static String getEFWSProbableCause(final String value) {

		String probCause = null;
		int probableCause = Integer.parseInt(value.toString());
		// Probable Cause entries are processed for IANA ITU Type
		if (probableCause >= 1 && probableCause <= 26) {
			probableCause += 1000;
		}
		if (probableCause >= 51 && probableCause <= 82) {
			probableCause += 1000;
		}
		if (probableCause >= 101 && probableCause <= 136) {
			probableCause += 1000;
		}
		if (probableCause >= 151 && probableCause <= 166) {
			probableCause += 1000;
		}
		if (probableCause >= 201 && probableCause <= 207) {
			probableCause += 1000;
		}
		if (probableCause >= 500 && probableCause <= 555) {
			probableCause += 1000;
		}
		if (probableCause >= 600 && probableCause <= 615) {
			probableCause += 1000;
		}
		if (probableCause == 1024) {
			probableCause += 1000;
		}
		probCause = String.valueOf(probableCause);

		return probCause;

	}

}
