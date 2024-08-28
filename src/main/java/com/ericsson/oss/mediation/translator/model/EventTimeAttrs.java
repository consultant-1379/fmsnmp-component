/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.mediation.translator.model;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.translator.model.EventNotification;


public class EventTimeAttrs {
	
	private EventTimeAttrs()
	{
		
	}
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EventTimeAttrs.class);
	//public static final Integer SNMP_BROADSOFT_TIME = Integer.valueOf(0);
	public static final Integer SNMP_COMMON_TIME = Integer.valueOf(1);
	//public static final Integer SNMP_OMS_TIME = Integer.valueOf(2);
	//public static final Integer SNMP_MCG_TIME = Integer.valueOf(3);
	private static final Map<String, Integer> eventTimeAttrs = new HashMap<String, Integer>();
	static {

		eventTimeAttrs.put("AlarmTime", SNMP_COMMON_TIME);
		
	}

	public static Integer getEventTimeFormat(final String attributeName) {
		return eventTimeAttrs.get(attributeName);
	}

	public static boolean translateEventTime(final String attributeName,
			final String attributeValue, final EventNotification notif) {
		boolean status = false;
		final Integer eventTimeFormat = EventTimeAttrs
				.getEventTimeFormat(attributeName);
		if (eventTimeFormat != null) {
			status = true;
			// Broadsoft Event Time
			/*if (EventTimeAttrs.SNMP_BROADSOFT_TIME.equals(eventTimeFormat)) {
				final StringTokenizer BroadsoftTimeandzone = HandleTimeTranslation
						.broadsoftEventTime(timevarbind);
				String time = "";
				while (BroadsoftTimeandzone.hasMoreTokens()) {
					time = BroadsoftTimeandzone.nextToken();
					BroadsoftTimeandzone.nextToken();
				}
				
				//TODO:Need to Check
				notif.setTime(new Date(time).toString());// NEXT IRP AND MGC
												// mgcEventTime

			} else if (EventTimeAttrs.SNMP_OMS_TIME
					.equals(eventTimeFormat)) {
				final StringTokenizer omsTrapContentsTimeStampTimeandzone = HandleTimeTranslation
						.irpomsTrapContents(timevarbind);
				String time = "";
				String timezone = "";
				while (omsTrapContentsTimeStampTimeandzone.hasMoreTokens()) {
					time = omsTrapContentsTimeStampTimeandzone.nextToken();
					timezone = omsTrapContentsTimeStampTimeandzone.nextToken();
				}
				
				//TODO:Need to Check
				notif.setTime(new Date(time).toString());
				notif.setTimeZone(timezone);

			} else if (EventTimeAttrs.SNMP_MCG_TIME
					.equals(eventTimeFormat)) {
				final StringTokenizer mgcEvent_TimeTimeandzone = HandleTimeTranslation
						.mgcEventTime((timevarbind));
				String time = "";
				String timezone = "";
				while (mgcEvent_TimeTimeandzone.hasMoreTokens()) {
					time = mgcEvent_TimeTimeandzone.nextToken();
					timezone = mgcEvent_TimeTimeandzone.nextToken();
				}
				
				//TODO:Need to Check
				notif.setTime(new Date(time).toString());
				notif.setTimeZone(timezone);

			} else {*/
				setAlarmTime(notif, attributeValue);
		}
		return status;

	}
	

	public static void setAlarmTime(final EventNotification notif,
			final String attributeValue) {

		try {
			
			if(attributeValue.indexOf("-") != -1)//event
			{
				String Timezone = null;
					try
					{
						Timezone = CreateDateAndTime.getEventTimeZone(attributeValue);
					}
					catch (Exception ex)
					{
						LOGGER.error("setAlarmTime ::Date Format Exception for event"+ex.getMessage());
					}
					
					
					notif.setTime(CreateDateAndTime._timeFormatter.format(CreateDateAndTime.getEventDateAndTime(attributeValue)));
					notif.setTimeZone(Timezone);
			}
			else //alarm
			{
				String Timezone = null;
				
					try
					{
						Timezone = CreateDateAndTime.getTimeZone(attributeValue);
						LOGGER.debug("setAlarmTime:Timezone"+Timezone);
					}
					catch (Exception ex)
					{
						LOGGER.error("setAlarmTime ::Date Format Exception for alarm"+ex.getMessage());
					}
					
					notif.setTime(CreateDateAndTime._timeFormatter.format(CreateDateAndTime.getAlarmDateAndTime(attributeValue)));
					notif.setTimeZone(Timezone);
			}

		} catch (Exception ex) {
			LOGGER.error("setAlarmTime ::Date Format Exception:Setting current time"+ex.getMessage());
			notif.setTimeZone("UTC");
			notif.setTime(CreateDateAndTime.getCurrentTime());
		}
	}

}
