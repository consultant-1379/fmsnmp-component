package com.ericsson.oss.mediation.translator.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CopyOfHandleAlarmSeverity {
	/**
	 * to avoid PMD Warnings
	 */
	private CopyOfHandleAlarmSeverity(){
		
	}
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CopyOfHandleAlarmSeverity.class);

	// AXD axdperceivedSeverity
	public static String getaxdperceivedSeverity(final String axdperceivedSeverity,
			final String attributename, final String attributevalue) {
		String axd301FaultIdSev = "";
		try {

			final int sevOIDLen = HandleOtherAlarmAttribute.AXDSEV.length();
			LOGGER.info("SEV OID len >> " + sevOIDLen);

			final String axdSevToken = axdperceivedSeverity.substring(sevOIDLen);
			LOGGER.info("axdSevToken >> " + axdSevToken);
			axd301FaultIdSev = axdSevToken.substring(
					axdSevToken.lastIndexOf(".") + 1, axdSevToken.length());
			LOGGER.info(" axd301FaultIdSev >> " + axd301FaultIdSev);

		} catch (Exception e) {
			LOGGER.info("Exception in setting ExternalEventId >> "
					+ e.toString());
			return axd301FaultIdSev;
		}
		return axd301FaultIdSev;
	}

	// ESA-SNF esasnfPerceivedSeverity
	public static String getesasnfPerceivedSeverity(final String attributevalue) {
		String esasnfPerceivedSeverity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 1:
			esasnfPerceivedSeverity = Constants.SEV_WARNING;
			break;
		case 2:
			esasnfPerceivedSeverity = Constants.SEV_MINOR;
			break;
		case 3:
			esasnfPerceivedSeverity = Constants.SEV_MAJOR;
			break;
		case 4:
			esasnfPerceivedSeverity = Constants.SEV_CRITICAL;
			break;
		case 5:
			esasnfPerceivedSeverity = Constants.SEV_CLEARED;
			break;
		default:
			esasnfPerceivedSeverity = Constants.SEV_INDETERMINATE;
			break;
		}
		return esasnfPerceivedSeverity;
	}

	// SEGW segwPerceivedSeverity
	public static String getsegwPerceivedSeverity(final String attributevalue) {
		String segwPerceivedSeverity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 0:
		case 3:
			segwPerceivedSeverity = Constants.SEV_MINOR;
			break;
		case 1:
			segwPerceivedSeverity =  Constants.SEV_MINOR;
			break;
		case 2:
			segwPerceivedSeverity = Constants.SEV_CRITICAL;
			break;
		case 4:
			segwPerceivedSeverity = Constants.SEV_WARNING;
			break;
		case 5:
		case 6:
		case 7:
			segwPerceivedSeverity = Constants.SEV_INDETERMINATE;
			break;
		default:
			break;
		}
		return segwPerceivedSeverity;
	}

	// RAD funkSbrTrapVarSev
	public static String getfunkSbrTrapVarSev(final String attributevalue) {
		String funkSbrTrapVarSev = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 0:
			funkSbrTrapVarSev = Constants.SEV_INDETERMINATE;
			break;
		case 1:
			funkSbrTrapVarSev = Constants.SEV_INDETERMINATE;
			break;
		case 2:
			funkSbrTrapVarSev = Constants.SEV_WARNING;
			break;
		case 3:
			funkSbrTrapVarSev = Constants.SEV_MAJOR;
			break;
		default:
			funkSbrTrapVarSev = Constants.SEV_INDETERMINATE;
			break;
		}
		return funkSbrTrapVarSev;
	}

	// OCMP ocmpo_perceivedSeverity
	public static String getocmpoperceivedSeverity(final String attributevalue) {
		String ocmpo_perceivedSeverity = Constants.SEV_INDETERMINATE;
		try {
			final String severityString = attributevalue.toString();

			if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_CRITICAL)) {
				ocmpo_perceivedSeverity = Constants.SEV_CRITICAL;
			} else if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_MAJOR)) {
				ocmpo_perceivedSeverity = Constants.SEV_MAJOR;
			} else if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_MINOR)) {
				ocmpo_perceivedSeverity = Constants.SEV_MINOR;
			} else if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_WARNING)) {
				ocmpo_perceivedSeverity = Constants.SEV_WARNING;
			} else if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_CLEARED)) {
				ocmpo_perceivedSeverity = Constants.SEV_CLEARED;
			} else {
				ocmpo_perceivedSeverity = Constants.SEV_INDETERMINATE;
			}
		} catch (Exception ex) {
			ocmpo_perceivedSeverity = Constants.SEV_INDETERMINATE;
			return ocmpo_perceivedSeverity;
		}
		return ocmpo_perceivedSeverity;
	}

	// OCMP SEVERITY
	public static String getocmpPerceivedSeverity(final String attributevalue) {
		String ocmpseverity = Constants.SEV_INDETERMINATE;
		try {
			switch (Integer.valueOf(attributevalue.toString()).intValue()) {
			case 0:
				ocmpseverity = Constants.SEV_INDETERMINATE;
				break;
			case 3:
				ocmpseverity = Constants.SEV_WARNING;
				break;
			case 4:
				ocmpseverity = Constants.SEV_MINOR;
				break;
			case 5:
				ocmpseverity = Constants.SEV_MAJOR;
				break;
			case 6:
				ocmpseverity = Constants.SEV_CRITICAL;
				break;

			case 8:
				ocmpseverity = Constants.SEV_CLEARED;
				break;
			default:
				ocmpseverity = Constants.SEV_INDETERMINATE;
				break;
			}
		} catch (Exception e) {
			ocmpseverity = Constants.SEV_INDETERMINATE;
			return ocmpseverity;
		}

		return ocmpseverity;
	}

	// MMC
	public static String getoraAgentEventSeverity(final String attributevalue) {
		String severity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 1:
			severity = Constants.SEV_WARNING;
			break;
		case 2:
			severity = Constants.SEV_MAJOR;
			break;
		default:
			severity = Constants.SEV_CLEARED;
			break;
		}
		return severity;
	}

	// handles (BROADSOFT,MGC,SRD)
	public static String getmgcEventSeverity(final String attributevalue) {
		String severity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 1:
			severity = Constants.SEV_INDETERMINATE;
			break;
		case 2:
			severity = Constants.SEV_CRITICAL;
			break;
		case 3:
			severity = Constants.SEV_MAJOR;
			break;
		case 4:
			severity = Constants.SEV_MINOR;
			break;
		case 5:
			severity = Constants.SEV_WARNING;
			break;
		case 6:
			severity = Constants.SEV_CLEARED;
			break;
		default:
			severity = Constants.SEV_INDETERMINATE;
			break;
		}
		return severity;
	}

	// handles (BROADSOFT,MGC)
	public static String getBSSeverity(final String attributevalue) {
		String severity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 1:
			severity = Constants.SEV_INDETERMINATE;
			break;
		case 2:
			severity = Constants.SEV_CRITICAL;
			break;
		case 3:
			severity = Constants.SEV_MAJOR;
			break;
		case 4:
			severity = Constants.SEV_MINOR;
			break;
		case 5:
			severity = Constants.SEV_WARNING;
			break;
		case 6:
			severity = Constants.SEV_CLEARED;
			break;
		default:
			severity = Constants.SEV_INDETERMINATE;
			break;
		}
		return severity;
	}

	// Broadsoft Alarm Perceived Severity
	public static String getBSAlarmSeverity(final String attributevalue) {
		String severity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 0:
			severity = Constants.SEV_INDETERMINATE;
			break;
		case 4:
			severity = Constants.SEV_CRITICAL;
			break;
		case 3:
			severity = Constants.SEV_MAJOR;
			break;
		case 2:
			severity = Constants.SEV_MINOR;
			break;
		case 1:
			severity = Constants.SEV_WARNING;
			break;
		default:
			severity = Constants.SEV_INDETERMINATE;
			break;
		}
		return severity;
	}

	// EFWS Alarm Perceived Severity
	public static String getEFWSAlarmSeverity(final String attributevalue) {
		String severity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			severity = Constants.SEV_CLEARED;
			break;
		case 2:
			severity = Constants.SEV_INDETERMINATE;
			break;
		case 3:
			severity = Constants.SEV_CRITICAL;
			break;
		case 4:
			severity = Constants.SEV_MAJOR;
			break;
		case 5:
			severity = Constants.SEV_MINOR;
			break;
		case 6:
			severity = Constants.SEV_WARNING;
			break;
		default:
			severity = Constants.SEV_INDETERMINATE;
			break;
		}
		return severity;
	}

	// EFWS Alarm Perceived Severity2
	public static String getEFWSAlarmSeverity2(final String attributevalue) {
		String severity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {

		case 1:
			severity = Constants.SEV_INDETERMINATE;
			break;
		case 2:
			severity = Constants.SEV_CRITICAL;
			break;
		case 3:
			severity = Constants.SEV_MAJOR;
			break;
		case 4:
			severity = Constants.SEV_MINOR;
			break;
		case 5:
			severity = Constants.SEV_WARNING;
			break;
		case 6:
			severity = Constants.SEV_CLEARED;
			break;
		default:
			severity = Constants.SEV_INDETERMINATE;
			break;
		}
		return severity;
	}

	// HPMRFP Alarm Perceived Severity2
	public static String getcpqMeAlarmSeverity(final String attributevalue) {
		String cpqMeAlarmSeverity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 1:
			cpqMeAlarmSeverity = Constants.SEV_INDETERMINATE;
			break;
		case 2:
			cpqMeAlarmSeverity = Constants.SEV_INDETERMINATE;
			break;
		case 3:
			cpqMeAlarmSeverity = Constants.SEV_MINOR;
			break;
		case 4:
			cpqMeAlarmSeverity = Constants.SEV_WARNING;
			break;
		case 5:
			cpqMeAlarmSeverity = Constants.SEV_MAJOR;
			break;
		case 6:
			cpqMeAlarmSeverity = Constants.SEV_CRITICAL;
			break;
		default:
			cpqMeAlarmSeverity = Constants.SEV_INDETERMINATE;
			break;
		}
		return cpqMeAlarmSeverity;
	}

	// HPMRFP mrfperceivedSeverity
	public static String getmrfperceivedSeverity(final String attributevalue) {
		String mrfperceivedSeverity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 0:
			mrfperceivedSeverity = Constants.SEV_INDETERMINATE;
			break;
		case 1:
			mrfperceivedSeverity = Constants.SEV_INDETERMINATE;
			break;
		case 2:
			mrfperceivedSeverity = Constants.SEV_INDETERMINATE;
			break;
		case 3:
			mrfperceivedSeverity = Constants.SEV_WARNING;
			break;
		case 4:
			mrfperceivedSeverity = Constants.SEV_MINOR;
			break;
		case 5:
			mrfperceivedSeverity = Constants.SEV_MAJOR;
			break;
		case 6:
			mrfperceivedSeverity = Constants.SEV_CRITICAL;
			break;
		case 7:
			mrfperceivedSeverity = Constants.SEV_CRITICAL;
			break;
		case 8:
			mrfperceivedSeverity = Constants.SEV_CLEARED;
			break;
		default:
			mrfperceivedSeverity = Constants.SEV_INDETERMINATE;
			break;
		}
		return mrfperceivedSeverity;
	}

	// HPMRFP mrfperceivedSeverity
	public static String getmrfperceivedSeverity2(final String attributevalue) {
		String mrfperceivedSeverity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 0:
			mrfperceivedSeverity = Constants.SEV_INDETERMINATE;
			break;
		case 1:
			mrfperceivedSeverity = Constants.SEV_INDETERMINATE;
			break;
		case 2:
			mrfperceivedSeverity = Constants.SEV_WARNING;
			break;
		case 3:
			mrfperceivedSeverity = Constants.SEV_WARNING;
			break;
		case 4:
			mrfperceivedSeverity = Constants.SEV_MINOR;
			break;
		case 5:
			mrfperceivedSeverity = Constants.SEV_MAJOR;
			break;
		case 6:
			mrfperceivedSeverity = Constants.SEV_CRITICAL;
			break;
		case 7:
			mrfperceivedSeverity = Constants.SEV_WARNING;
			break;
		case 8:
			mrfperceivedSeverity = Constants.SEV_CLEARED;
			break;
		default:
			mrfperceivedSeverity = Constants.SEV_INDETERMINATE;
			break;
		}
		return mrfperceivedSeverity;
	}

	// default severity handler method
	public static String getPerceivedSeverity(final String severity) {
		String perceivedSeverity = Constants.SEV_INDETERMINATE;

		final int severityIntValue = Integer.parseInt(severity);

		try {
			switch (severityIntValue) {
			case 0:
				perceivedSeverity = Constants.SEV_INDETERMINATE;
				break;
			case 1:
				perceivedSeverity = Constants.SEV_CRITICAL;
				break;
			case 2:
				perceivedSeverity = Constants.SEV_MAJOR;
				break;
			case 3:
				perceivedSeverity = Constants.SEV_MINOR;
				break;
			case 4:
				perceivedSeverity = Constants.SEV_WARNING;
				break;
			case 5:
				perceivedSeverity = Constants.SEV_CLEARED;
				break;
			default:
				perceivedSeverity = Constants.SEV_INDETERMINATE;
				break;
			}
		} catch (Exception e) {
			LOGGER.info(" Exception in getPerceivedSeverity ()--> "
					+ e.toString());
			perceivedSeverity = Constants.SEV_INDETERMINATE;
		}

		return perceivedSeverity;
	}

	// VIVR
	public static String getvivrPerceivedSeverity(final String attributeValue) {
		String vivrseverity = Constants.SEV_INDETERMINATE;
		try {
			if (attributeValue.equalsIgnoreCase("INFORMATIVE")) {
				vivrseverity = Constants.SEV_INDETERMINATE;
			} else if (attributeValue.equalsIgnoreCase(Constants.SEV_CRITICAL)) {
				vivrseverity = Constants.SEV_CRITICAL;
			} else if (attributeValue.equalsIgnoreCase(Constants.SEV_MAJOR)) {
				vivrseverity = Constants.SEV_MAJOR;
			} else if (attributeValue.equalsIgnoreCase(Constants.SEV_MINOR)) {
				vivrseverity = Constants.SEV_MINOR;
			} else if (attributeValue.equalsIgnoreCase(Constants.SEV_WARNING)) {
				vivrseverity = Constants.SEV_WARNING;
			} else if (attributeValue.equalsIgnoreCase(Constants.SEV_CLEARED)) {
				vivrseverity = Constants.SEV_CLEARED;
			} else {
				vivrseverity = Constants.SEV_INDETERMINATE;
			}
		} catch (Exception ex) {
			LOGGER.info("Exception:" + ex);
			vivrseverity = Constants.SEV_INDETERMINATE;
		}

		return vivrseverity;
	}

	// TIGRIS

	public static String getTigrisSeverity(final String attributeValue) {

		String tigrisseverity = Constants.SEV_INDETERMINATE;
		try {
			switch (Integer.parseInt(attributeValue.toString())) {
			case 0:
				tigrisseverity = Constants.SEV_CLEARED;
				break;
			case 1:
				tigrisseverity = Constants.SEV_INDETERMINATE;
				break;
			case 2:
				tigrisseverity = Constants.SEV_MINOR;
				break;
			case 3:
				tigrisseverity = Constants.SEV_MAJOR;
				break;
			case 4:
				tigrisseverity = Constants.SEV_CRITICAL;
				break;
			default:
				tigrisseverity = Constants.SEV_INDETERMINATE;
				break;
			}
		} catch (Exception ex) {

			LOGGER.info("setPerceivedSeverity Exception." + ex.getMessage());
			tigrisseverity = Constants.SEV_INDETERMINATE;
			return tigrisseverity;
		}
		return tigrisseverity;

	}

	// SEGS

	public static String getsegwSeverity(final String attributeValue) {

		String segwseverity = Constants.SEV_INDETERMINATE;
		switch (Integer.parseInt(attributeValue.toString())) {
		case 0:
			segwseverity = Constants.SEV_MAJOR;
			break;
		case 1:
			segwseverity = Constants.SEV_MINOR;
			break;
		case 2:
			segwseverity = Constants.SEV_CRITICAL;
			break;
		case 3:
			segwseverity = Constants.SEV_MINOR;
			break;
		case 4:
			segwseverity = Constants.SEV_WARNING;
			break;
		case 5:
			segwseverity = Constants.SEV_INDETERMINATE;
			break;
		case 6:
			segwseverity = Constants.SEV_INDETERMINATE;
			break;
		case 7:
			segwseverity = Constants.SEV_INDETERMINATE;
			break;
		default:
			break;
		}

		return segwseverity;

	}

	// OCMP SEVERITY

	public static String getocmpoSeverity(final String attributeValue) {
		String ompseverity = Constants.SEV_INDETERMINATE;
		try {
			final String severityString = attributeValue.toString();

			if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_CRITICAL)) {
				ompseverity = Constants.SEV_CRITICAL;
			} else if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_MAJOR)) {
				ompseverity = Constants.SEV_MAJOR;
			} else if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_MINOR)) {
				ompseverity = Constants.SEV_MINOR;
			} else if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_WARNING)) {
				ompseverity = Constants.SEV_WARNING;
			} else if (severityString != null && severityString.length() > 0
					&& severityString.equalsIgnoreCase(Constants.SEV_CLEARED)) {
				ompseverity = Constants.SEV_CLEARED;
			} else {
				ompseverity = Constants.SEV_INDETERMINATE;
			}
		} catch (Exception ex) {
			ompseverity = Constants.SEV_INDETERMINATE;
			return ompseverity;
		}
		return ompseverity;

	}

	// OCMP SEVERITY CASE 2

	/*private void getocmpSeverity(EventNotification notif, String attributeValue) {
		String ocmpseverity2 = Constants.SEV_INDETERMINATE;
		try {
			switch (Integer.valueOf(attributeValue.toString()).intValue()) {
			case 0:
				ocmpseverity2 = Constants.SEV_INDETERMINATE;
				break;

			case 3:
				ocmpseverity2 = Constants.SEV_WARNING;
				break;
			case 4:
				ocmpseverity2 = Constants.SEV_MINOR;
				break;
			case 5:
				ocmpseverity2 = Constants.SEV_MAJOR;
				break;
			case 6:
				ocmpseverity2 = Constants.SEV_CRITICAL;
				break;

			case 8:
				ocmpseverity2 = Constants.SEV_CLEARED;
				break;
			default:
				ocmpseverity2 = Constants.SEV_INDETERMINATE;
				break;
			}
		} catch (Exception exx) {
			ocmpseverity2 = Constants.SEV_INDETERMINATE;
		}

	}*/

}
