package com.ericsson.oss.mediation.translator.model;

import java.text.SimpleDateFormat;
import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adventnet.snmp.snmp2.SnmpVarBind;

public class HandleTimeTranslation {

	private HandleTimeTranslation(){
		
	}
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleTimeTranslation.class);

	// ESA TIME HANDLING SYNCH

	// Setting TimeZone for Traps
	// This method is implemented to fetch the TimeZone for Alarms
	// 43 ` "+" (ASCII value) or 45 ` "-"(ASCII value) (expecting only these two
	// values in 9th position)

	// 2 ` UTC offset Hour (Only 0 to 13 is expecting)

	// 0 ` UTC offset Min (Only 0 to 59 is expecting)

	public static String getAlarmTimeZone(final String s) {

		final StringBuffer buf = new StringBuffer("UTC");
		if (s != null) {
			final StringTokenizer st = new StringTokenizer(s, ".");
			st.nextToken();
			st.nextToken();
			//final int month = (Integer.valueOf(st.nextToken()).intValue());// -1;
			//final int day = (Integer.valueOf(st.nextToken()).intValue());
			//final int hours = (Integer.valueOf(st.nextToken()).intValue());
			//final int minutes = (Integer.valueOf(st.nextToken()).intValue());
			//final int seconds = (Integer.valueOf(st.nextToken()).intValue());
			// next token is deci-seconds -DISREGARD
			//String g = st.nextToken();
			// direction from UTC: 002b = '+' = 43 , 002d = '-' = 45
			final int sign = Integer.valueOf(st.nextToken()).intValue();
			if (sign == 45)// hex. number = 2d
			{
				buf.append('-');
			} else { // either hex. number = 2b or default to positive anyway
				buf.append('+');
			}
			// hours from UTC
			final int hoursFromUTC = (Integer.valueOf(st.nextToken()).intValue());
			buf.append(hoursFromUTC);
			final int minutesFromUTC = (Integer.valueOf(st.nextToken()).intValue());
			buf.append(':');
			buf.append(minutesFromUTC);
			LOGGER.info("Buffer is: " ,buf);
		} else {

		}

		String timeZone = "";
		timeZone = buf.toString();
		return timeZone;
	}

	// ESA-SNF This method is implemented for setting 28 Octets Date and Time
	// format
	public static Date createAlarmDateAndTime(final String aldateBuff, final int octetsCount) {

		// Now timeval will comtain in the form of Mon Jan 09 13:57:09 MET 2006
		final StringTokenizer st4 = new StringTokenizer(aldateBuff);

		// remove the day token
		st4.nextToken();

		final StringBuffer datebuffer = new StringBuffer();
		// Get the month
		datebuffer.append(st4.nextToken());

		// Get the date
		datebuffer.append(st4.nextToken());

		// Get the time
		datebuffer.append(st4.nextToken());

		// Get the time zone
		final String timezone = st4.nextToken();

		// Get the year
		datebuffer.append(st4.nextToken());

		// append the time zone
		datebuffer.append(timezone);
		// LOGGER.info(datebuffer);

		final SimpleDateFormat sdf = new SimpleDateFormat("MMMMMddhh:mm:ssyyyyzzz");

		final SimpleDateFormat sdfn = new SimpleDateFormat("yyyyMMddHHmmss");
		sdfn.setTimeZone(TimeZone.getTimeZone("UTC"));

		try {

			final Date alarmDate = sdf.parse(datebuffer.toString());

			LOGGER.info("--- Date : " + alarmDate);

			LOGGER.info("--- Time zone : " + timezone);

			LOGGER.info("--- Format Date : " + sdfn.format(alarmDate));
			return alarmDate;
		} catch (Exception ex) {
			LOGGER.info("Exception while processing Alarm Active Date and Time",ex);
			// Exception thrown while processing this VARBIND
			// Setting Date to local current Time
			// notif.setTime(getCurrentTime());
			LOGGER.info("Exception while processing Alarm Active Index and hence not setting the value in ALV");
			return null;
		}
	}

	// MGC mgcEvent_Time
	public static StringTokenizer mgcEventTime(final SnmpVarBind binding) {
		String Time = null;
		String Timezone = null;
		StringTokenizer timeandzone;

		// Get the time
		Time = binding.getVariable().toString();
		LOGGER.info("lenth is : " + Time.length());
		try {

			if (Time.indexOf("-") != -1)// event
			{
				if (Time.length() != 25) {
					LOGGER.info("If the time is not in 11 octet then setting the time to local current time");
					Timezone = "UTC";
					Time = getCurrentTime().toString();
					final String timendzone = Time + "&&" + Timezone;
					timeandzone = new StringTokenizer(timendzone, "&&");
				} else {
					LOGGER.info(" Using Event Time ");
					Timezone = getEventTimeZone(Time);
					Time = TIMEFORMATTER.format(createEventDateAndTime(Time));
					final String timendzone = Time + "&&" + Timezone;
					timeandzone = new StringTokenizer(timendzone, "&&");

				}
			}

			else // alarm
			{
				if (Time.length() != 32) {
					LOGGER.info("If the time is not in 11 octet then setting the time to local current time");
					Timezone = "UTC";
					Time = getCurrentTime().toString();
					final String timendzone = Time + "&&" + Timezone;
					timeandzone = new StringTokenizer(timendzone, "&&");
				} else {
					LOGGER.info(" Using Alarm Time ");
					Timezone = getEventTimeZone(Time);
					Time = TIMEFORMATTER.format(createEventDateAndTime(Time));
					final String timendzone = Time + "&&" + Timezone;
					timeandzone = new StringTokenizer(timendzone, "&&");
				}
			}

		} catch (Exception ex) {
			
			LOGGER.info(" Exception in getting the time zoneand time attribute Hence setting the time zone to UTC and time to current time",ex);
			Timezone = "UTC";
			Time = getCurrentTime().toString();
			final String timendzone = Time + "&&" + Timezone;
			timeandzone = new StringTokenizer(timendzone, "&&");
		}

		return timeandzone;

	}

	// IRP omsTrapContentsTimeStamp , for MGC
	// (mgcEventTime:.1.3.6.1.4.1.42.2.70.101.2.1.2)
	public static StringTokenizer irpomsTrapContents(final SnmpVarBind binding) {
		String Time = null;
		String Timezone = null;
		StringTokenizer timeandzone;

		try {
			LOGGER.info("timestamp");
			final String totalString = binding.toTagString();
			final StringTokenizer st = new StringTokenizer(totalString, ":");
			st.nextToken();
			st.nextToken();

			while (st.hasMoreTokens()) {
				Time = Time + st.nextToken() + ":";
			}
			if (Time.endsWith(":")) {
				// get rid of last :
				Time = Time.substring(0, (Time.length() - 1));
			}// end fix
			/*
			 * NOTE: Alarms contain time in hex format: 07 D3 01 12 0D 1E 1E 00
			 * 2D 04 00 Events contain time in this format:
			 * 2003-01-18;13:30:30.00:-4:00 Time = alarmTime.nextToken();
			 * LOGGER.info("IrpActionHandler::Time in table is : "
			 * +Time.toString()); int
			 * year=0,month=0,day=0,hour=0,minute=0,second =0;
			 */
			if (Time.indexOf("-") != -1)// event
			{

				LOGGER.info("Time Length:::" + Time.length());

				if (Time.length() != 26) {
					LOGGER.info("If the time is not in 11 octet then setting the time to local current time");
					Timezone = "UTC";
					Time = getCurrentTime().toString();
					final String timendzone = Time + "&&" + Timezone;
					timeandzone = new StringTokenizer(timendzone, "&&");
				} else {
					try {
						Timezone = getEventTimeZone(Time);
						Time = TIMEFORMATTER
								.format(createEventDateAndTime(Time));
						LOGGER.info("Time is" + Time);
						final String timendzone = Time + "&&" + Timezone;
						timeandzone = new StringTokenizer(timendzone, "&&");

					} catch (Exception ex) {
						LOGGER.info("exception is",ex);
						Time = TIMEFORMATTER.format(getCurrentTime());
						Timezone = getTimeZone(Time);
						final String timendzone = Time + "&&" + Timezone;
						 timeandzone = new StringTokenizer(timendzone, "&&");
						return timeandzone;
					}

				}
				return timeandzone;
			} else // alarm
			{

				LOGGER.info("Time Length:::" + Time.length());
				if (Time.length() != 33) {
					LOGGER.info("If the time is not in 11 octet then setting the time to local current time");
					Timezone = "UTC";
					Time = getCurrentTime().toString();
					final String timendzone = Time + "&&" + Timezone;
					timeandzone = new StringTokenizer(timendzone, "&&");
				} else {
					try {
						Timezone = getTimeZone(Time);
						Time = TIMEFORMATTER
								.format(createAlarmDateAndTime(Time));
						LOGGER.info("Time is"
								+ TIMEFORMATTER
										.format(createAlarmDateAndTime(Time)));
						LOGGER.info("IrpTranslator::buildIrpAlarm::TimeZone is: "
								+ Timezone);
						final String timendzone = Time + "&&" + Timezone;
						timeandzone = new StringTokenizer(timendzone, "&&");
					} catch (Exception ex) {
						LOGGER.info("exception is",ex);
						Time = TIMEFORMATTER.format(getCurrentTime());
						Timezone = getTimeZone(Time);
						final String timendzone = Time + "&&" + Timezone;
						timeandzone = new StringTokenizer(timendzone, "&&");
						return timeandzone;
					}

				}
				/*
				 * Object ID: .1.3.6.1.4.1.193.82.1.8.1.4.11 STRING:
				 * 2003-1-2,16:23:47.0,+1:0 This is in HEX e.g. 07 D3 01 12 0D
				 * 1E 1E 00 2D 04 00 = 2003-01-18;13:30:30.00:-4:00
				 */
			}

		} catch (Exception ex) {
			LOGGER.info("exception is"+ex);
			Time = TIMEFORMATTER.format(getCurrentTime());
			Timezone = getTimeZone(Time);
			final String timendzone = Time + "&&" + Timezone;
			timeandzone = new StringTokenizer(timendzone, "&&");
			return timeandzone;

		}

		return timeandzone;
	}

	// BroadsoftEventTime
	public static StringTokenizer broadsoftEventTime(final SnmpVarBind binding1) {
		String time = "";
		String timezone = "";
		StringTokenizer timeandzone;

		try {
			final String totalString = binding1.toTagString();
			final StringTokenizer st = new StringTokenizer(totalString, ":");
			st.nextToken();
			st.nextToken();
			while (st.hasMoreTokens()) {
				time = time + st.nextToken() + ":";
			}
			if (time.endsWith(":")) {

				time = time.substring(0, time.length() - 1);
			}

			if (time.indexOf("-") != -1)// event
			{
				time = TIMEFORMATTER.format(createEventDateAndTime(time));
				timezone = getEventTimeZone(time);
				final String timendzone = time + "&&" + timezone;
				timeandzone = new StringTokenizer(timendzone, "&&");
			} else // alarm
			{
				time = TIMEFORMATTER.format(TIMEFORMATTER
						.format(createAlarmDateAndTime(time)));
				timezone = getTimeZone(time);
				final String timendzone = time + "&&" + timezone;
				timeandzone = new StringTokenizer(timendzone, "&&");

			}

		} catch (Exception ex) {
			LOGGER.info("exception is",ex);
			time = TIMEFORMATTER.format(getCurrentTime());
			timezone = getTimeZone(time);
			final String timendzone = time + "&&" + timezone;
			timeandzone = new StringTokenizer(timendzone, "&&");
			return timeandzone;
		}
		return timeandzone;
	}

	// This method is implemented to set the Default Current Time for Traps
	public static Date getCurrentTime() {
		final Calendar cal = new GregorianCalendar(TimeZone.getTimeZone("UTC"));
		final int hour = cal.get(Calendar.HOUR_OF_DAY);// 1-24
		final int minutes = cal.get(Calendar.MINUTE);// 1-59
		final int second = cal.get(Calendar.SECOND);// 1-59
		final int year = cal.get(Calendar.YEAR);
		final int month = cal.get(Calendar.MONTH);
		final int day = cal.get(Calendar.DATE);
		LOGGER.info("Time in cal is>>>>>>" + year + "-" + month + "-" + day
				+ "   " + hour + ":" + minutes + ":" + second);
		//final boolean am = cal.get(Calendar.AM_PM) == Calendar.AM;
		LOCAL.clear();
		LOCAL.set(year, month, day, hour, minutes, second);
		LOGGER.info("Time in local is >>>>>>>"
				+ TIMEFORMATTER.format(LOCAL.getTime()));
		return LOCAL.getTime();
	}

	// This method is implemented for Creating Date and Time for Alarms
	@SuppressWarnings("deprecation")
	public static Date createAlarmDateAndTime(final String s) {
		final Date date = new Date();
		final Calendar calendar = Calendar.getInstance();
		final StringTokenizer st = new StringTokenizer(s);
		calendar.set(Calendar.YEAR, (Integer.valueOf(
				st.nextToken() + st.nextToken(), 16).intValue()) + 1900);
		calendar.set(Calendar.MONTH,
				(Integer.valueOf(st.nextToken(), 16).intValue()) - 1);
		calendar.set(Calendar.DATE, Integer.valueOf(st.nextToken(), 16)
				.intValue());
		calendar.set(Calendar.HOUR_OF_DAY, Integer.valueOf(st.nextToken(), 16)
				.intValue());
		calendar.set(Calendar.MINUTE, Integer.valueOf(st.nextToken(), 16)
				.intValue());
		calendar.set(Calendar.SECOND, Integer.valueOf(st.nextToken(), 16)
				.intValue());
		LOGGER.info("Calendar is:" + calendar);

		date.setYear((Integer.valueOf(st.nextToken() + st.nextToken(), 16)
				.intValue()) - 1900);
		date.setMonth((Integer.valueOf(st.nextToken(), 16).intValue()) - 1);
		date.setDate(Integer.valueOf(st.nextToken(), 16).intValue());
		date.setHours(Integer.valueOf(st.nextToken(), 16).intValue());
		date.setMinutes(Integer.valueOf(st.nextToken(), 16).intValue());
		date.setSeconds(Integer.valueOf(st.nextToken(), 16).intValue());
		LOGGER.info("BroadsoftTranslator::createAlarmDateAndTime: " + date);
		return date;
	}

	// This method is implemented for Creating Date and Time for Events
	@SuppressWarnings("deprecation")
	public static Date createEventDateAndTime(final String s) {
		final Date date = new Date();
		// 2003-4-16,17:59:1.0,+9:0
		// 2003-6-2, 14:46:6.0,+2.0
		final StringTokenizer st = new StringTokenizer(s.trim(), "-");
		date.setYear(Integer.parseInt(st.nextToken().trim()) - 1900);
		date.setMonth(Integer.parseInt(st.nextToken().trim()) - 1);

		final StringTokenizer st2 = new StringTokenizer(st.nextToken().trim(), ",");
		date.setDate(Integer.parseInt(st2.nextToken()));

		final StringTokenizer st3 = new StringTokenizer(st2.nextToken().trim(), ":");
		date.setHours(Integer.parseInt(st3.nextToken().trim()));
		date.setMinutes(Integer.parseInt(st3.nextToken().trim()));

		final StringTokenizer st4 = new StringTokenizer(st3.nextToken().trim(), ".");
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
		LOGGER.info("BroadsoftTranslator::createEventDateAndTime: " + date);
		return date;
	}

	// This method is implemented to fetch the TimeZone for Events
	public static String getEventTimeZone(final String s) {
		final StringBuffer buf = new StringBuffer("UTC");
		if (s != null) {
			final StringTokenizer st = new StringTokenizer(s.trim(), ",");
			st.nextToken();
			st.nextToken();
			buf.append(st.nextToken().trim());
		}
		LOGGER.info("BroadsoftTranslator::getEventTimeZone: " + buf);
		return buf.toString();
	}

	// Setting TimeZone for Traps
	// This method is implemented to fetch the TimeZone for Alarms
	public static String getTimeZone(final String s) {
		LOGGER.info("--->getTimeZone --->");
		final StringBuffer buf = new StringBuffer("UTC");
		if (s != null) {
			final StringTokenizer st = new StringTokenizer(s);
			//final int year = (Integer.valueOf(st.nextToken() + st.nextToken(), 16).intValue());// -1900;
			//int month = (Integer.valueOf(st.nextToken(), 16).intValue());// -1;
			//int day = (Integer.valueOf(st.nextToken(), 16).intValue());
			//int hours = (Integer.valueOf(st.nextToken(), 16).intValue());
			//int minutes = (Integer.valueOf(st.nextToken(), 16).intValue());
			//int seconds = (Integer.valueOf(st.nextToken(), 16).intValue());
			// next token is deci-seconds -DISREGARD
			//String g = st.nextToken();
			// direction from UTC: 002b = '+' = 43 , 002d = '-' = 45
			final int sign = (Integer.valueOf(st.nextToken(), 16).intValue());
			if (sign == 45)// hex. number = 2d
			{
				buf.append('-');
			} else { // either hex. number = 2b or default to positive anyway
				buf.append('+');
			}
			// hours from UTC
			final int hoursFromUTC = (Integer.valueOf(st.nextToken(), 16).intValue());
			buf.append(hoursFromUTC);
			final int minutesFromUTC = (Integer.valueOf(st.nextToken(), 16)
					.intValue());
			buf.append(':');
			buf.append(minutesFromUTC);
			LOGGER.info("getTimeZone: " + buf);
		} else {
			LOGGER.info("getTimeZone(): TIME = null");
		}

		String timeZone = "";
		timeZone = buf.toString();
		return timeZone;
	}

	public static final Calendar LOCAL = new GregorianCalendar(
			TimeZone.getDefault());
	public static final SimpleDateFormat TIMEFORMATTER = new SimpleDateFormat(
			"yyyyMMddHHmmss");
	
	//public static final Calendar _calendar = new GregorianCalendar(TimeZone.getDefault());
	public static final SimpleDateFormat _timeFormatter = new SimpleDateFormat("yyyyMMddHHmmss");

	public static final Calendar local = new GregorianCalendar(TimeZone.getDefault());

}
