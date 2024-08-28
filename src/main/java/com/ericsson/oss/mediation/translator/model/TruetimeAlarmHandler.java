package com.ericsson.oss.mediation.translator.model;

import java.util.StringTokenizer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.translator.model.EventNotification;

public class TruetimeAlarmHandler {

	private TruetimeAlarmHandler() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(TruetimeAlarmHandler.class);

	// build the SymmAlarm for the true time, implementation is for the snmpv1
	// only till the date

	public static EventNotification buildSymmAlarm(final String varbindOID,
			final String varbindValue, final EventNotification notification) {
		final EventNotification notif = notification;
		final String OID = varbindValue;
		
		String alarmString = "";
		String typeSubStr = "";
		String classSubStr = "";
		String levelSubStr = "";
		String actionSubStr = "";
		String flagsSubStr = "";
		String spSubStr = "";
		String classString = "";
		String specificProblem = "";
		String levelString = "";
		String actionString = "";
		String flagsString = "";
		StringTokenizer alarmContentTokenizer = null;
		StringTokenizer alarmContentTokenizer1 = null;
		StringTokenizer alarmContentTokenizer2 = null;
		StringTokenizer alarmContentTokenizer3 = null;
		StringTokenizer alarmContentTokenizer4 = null;
		final StringBuffer additionalText = new StringBuffer();

		notif.setTimeZone("UTC");

		try {
			final String date = OID.substring(0, OID.indexOf("Alarm") - 1);
			
			//TODO:Need to Check
			notif.setTime((HandleTimeTranslation.createAlarmDateAndTime(date)).toString());
		} catch (Exception ae) {
			LOGGER.info("Caught Exception=:::setTime(): "
					+ HandleTimeTranslation.getCurrentTime());
			
			//TODO:Need to Check
			notif.setTime(HandleTimeTranslation.getCurrentTime().toString());
		}
		final String alContent = OID.substring(OID.indexOf("Alarm"));
		alarmContentTokenizer = new StringTokenizer(alContent, ":");
		alarmString = alarmContentTokenizer.nextToken();

		additionalText.append("\n" + alarmString);
		alarmContentTokenizer.nextToken();

		typeSubStr = alarmContentTokenizer.nextToken().trim();
		alarmContentTokenizer1 = new StringTokenizer(typeSubStr, ",");

		classSubStr = alarmContentTokenizer.nextToken().trim();
		alarmContentTokenizer2 = new StringTokenizer(classSubStr, ",");

		levelSubStr = alarmContentTokenizer.nextToken().trim();
		alarmContentTokenizer3 = new StringTokenizer(levelSubStr, ",");

		actionSubStr = alarmContentTokenizer.nextToken().trim();
		alarmContentTokenizer4 = new StringTokenizer(actionSubStr, ",");

		flagsSubStr = alarmContentTokenizer.nextToken().trim();

		spSubStr = alarmContentTokenizer1.nextToken();
		classString = alarmContentTokenizer1.nextToken().trim() + ": "
				+ alarmContentTokenizer2.nextToken();
		specificProblem = spSubStr + ", " + classString;
		notif.setSpecificProblem(specificProblem);

		levelString = alarmContentTokenizer2.nextToken().trim() + ": "
				+ alarmContentTokenizer3.nextToken();
		additionalText.append("\n" + levelString);

		actionString = alarmContentTokenizer3.nextToken().trim() + ": "
				+ alarmContentTokenizer4.nextToken();

		if (levelString.contains(Constants.SEV_MAJOR)) {
			notif.setSeverity(Constants.SEV_MAJOR);
		} else if (levelString.contains(Constants.SEV_MINOR)) {
			notif.setSeverity(Constants.SEV_MINOR);
		} else {
			notif.setSeverity(Constants.SEV_INDETERMINATE);
		}

		if (actionString.contains("Clear")) {
			notif.setSeverity(Constants.SEV_CLEARED);
		}

		flagsString = alarmContentTokenizer4.nextToken().trim() + ": "
				+ flagsSubStr;
		additionalText.append("\n" + flagsString);

		notif.addAdditionalAttribute("additionalText",
				additionalText.toString());

		return notif;

	}

}
