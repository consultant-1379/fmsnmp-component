package com.ericsson.oss.mediation.translator.model;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class CreateDateAndTime {

	private CreateDateAndTime() {
		
	}
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TranslateAlarm.class);
	public static final SimpleDateFormat _timeFormatter = new SimpleDateFormat("yyyyMMddHHmmss");

	@SuppressWarnings("deprecation")
	public static Date getAlarmDateAndTime(final String s) {
		final Date date = new Date();

		final StringTokenizer st = new StringTokenizer(s);

		date.setYear((Integer.valueOf(st.nextToken() + st.nextToken(), 16)
				.intValue()) - 1900);
		date.setMonth((Integer.valueOf(st.nextToken(), 16).intValue()) - 1);
		date.setDate(Integer.valueOf(st.nextToken(), 16).intValue());
		date.setHours(Integer.valueOf(st.nextToken(), 16).intValue());
		date.setMinutes(Integer.valueOf(st.nextToken(), 16).intValue());
		date.setSeconds(Integer.valueOf(st.nextToken(), 16).intValue());

		LOGGER.debug("createAlarmDateAndTime : " + date);

		return date;
	}

	public static String getEventTimeZone(final String s) {
		final StringBuffer buf = new StringBuffer("UTC");

		if (s != null) {
			final StringTokenizer st = new StringTokenizer(s.trim(), ",");
			st.nextToken();
			st.nextToken();
			buf.append(st.nextToken().trim());
		}

		LOGGER.debug("getEventTimeZone : " + buf);

		return buf.toString();
	}

	public static String getCurrentTime() {
		final Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		final Calendar localCal = new GregorianCalendar(TimeZone.getDefault());
		final int hour = cal.get(Calendar.HOUR_OF_DAY);// 1-24
		final int minutes = cal.get(Calendar.MINUTE);// 1-59
		final int second = cal.get(Calendar.SECOND);// 1-59
		final int year = cal.get(Calendar.YEAR);
		final int month = cal.get(Calendar.MONTH);
		final int day = cal.get(Calendar.DATE);
		final SimpleDateFormat TIMEFORMATTER = new SimpleDateFormat("yyyyMMddHHmmss");
		LOGGER.debug("Time in Cal is >>>>>>" + year + "-" + month + "-" + day
				+ "   " + hour + ":" + minutes + ":" + second);

		// boolean am = cal.get(Calendar.AM_PM) == Calendar.AM;

		localCal.clear();
		localCal.set(year, month, day, hour, minutes, second);

		LOGGER.debug("Time in Local is >>>>>>>"
				+ TIMEFORMATTER.format(localCal.getTime()));

		return TIMEFORMATTER.format(localCal.getTime());
	}

	public static String getTimeZone(final String s) {
		
		final StringTokenizer st = new StringTokenizer(s);
		
		for(int i=0;i<7;i++){
			st.nextToken();
		}
		
		StringBuffer buf = new StringBuffer("UTC");

			if (st.hasMoreTokens()) {
				try {
					final int sign = (Integer.valueOf(st.nextToken(), 16)
							.intValue());

					if (sign == 45)// hex. number = 2d
					{
						buf.append('-');
					} else {
						buf.append('+');
					}

					// hours from UTC
					final int hoursFromUTC = (Integer.valueOf(st.nextToken(),
							16).intValue());
					buf.append(hoursFromUTC);

					// minutes from UTC

					final int minutesFromUTC = (Integer.valueOf(st.nextToken(),
							16).intValue());

					buf.append(':');
					buf.append(minutesFromUTC);
				} catch (Exception ex) {
					LOGGER.error("Exception occured in processing the UTC info for getting the time zone hence setting the time zone to UTC");
					buf = new StringBuffer("UTC");
				}
			}
			else {
			LOGGER.debug("getTimeZone(): TIME = null");
		}

		String timeZone = "";
		timeZone = buf.toString();

		return timeZone;
	}


	@SuppressWarnings("deprecation")
	public static Date getEventDateAndTime(final String s) {
		final Date date = new Date();

		final StringTokenizer st = new StringTokenizer(s.trim(), "-");
		date.setYear(Integer.parseInt(st.nextToken().trim()) - 1900);
		date.setMonth(Integer.parseInt(st.nextToken().trim()) - 1);

		final StringTokenizer st2 = new StringTokenizer(st.nextToken().trim(),
				",");
		date.setDate(Integer.parseInt(st2.nextToken()));

		final StringTokenizer st3 = new StringTokenizer(st2.nextToken().trim(),
				":");
		date.setHours(Integer.parseInt(st3.nextToken().trim()));
		date.setMinutes(Integer.parseInt(st3.nextToken().trim()));

		final StringTokenizer st4 = new StringTokenizer(st3.nextToken().trim(),
				".");
		LOGGER.debug("st4 = " + st4.countTokens());
		final int seconds = Integer.parseInt(st4.nextToken().trim());

		// deci_seconds

		double deci_seconds = Double.parseDouble(st4.nextToken().trim()) * 0.1;

		if (deci_seconds >= 0.5) {
			deci_seconds = 1;
		} else {
			deci_seconds = 0;
		}

		final int total_seconds = seconds + (int) deci_seconds;

		date.setSeconds(total_seconds);
		LOGGER.debug("CommonAAUTranslator:: createEventDateAndTime ==> " + date);

		return date;
	}

}
