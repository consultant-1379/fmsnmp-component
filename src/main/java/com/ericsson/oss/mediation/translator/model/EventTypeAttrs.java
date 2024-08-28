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

import java.util.HashMap;
import java.util.Map;

import com.ericsson.oss.mediation.translator.model.EventNotification;

public class EventTypeAttrs {
         
	//to avoid pmd
	private EventTypeAttrs(){
		
	}
	public static final int SPP_EVENT = 0;
	public static final int SITE_EVENT = 1;
	public static final int NETRA_EVENT = 2;
	public static final int MMC_EVENT = 3;
	public static final int MGC_EVENT = 4;
	public static final int JAMBALA_EVENT = 5;
	public static final int ISBLADE_EVENT = 6;
	public static final int IPMS_EVENT = 7;
	public static final int WPP_EVENT = 8;
	public static final int EFWS_EVENT = 9;
	public static final int GGSN_EVENT = 10;
	public static final int COMMON_EVENT = 11;

	public static final Integer SPP_EVENT_TYPE = Integer.valueOf(SPP_EVENT);
	public static final Integer SITE_EVENT_TYPE = Integer.valueOf(SITE_EVENT);
	public static final Integer NETRA_EVENT_TYPE = Integer.valueOf(NETRA_EVENT);
	public static final Integer MMC_EVENT_TYPE = Integer.valueOf(MMC_EVENT);
	public static final Integer MGC_EVENT_TYPE = Integer.valueOf(MGC_EVENT);
	public static final Integer JAMBALA_EVENT_TYPE = Integer
			.valueOf(JAMBALA_EVENT);
	public static final Integer ISBLADE_EVENT_TYPE = Integer
			.valueOf(ISBLADE_EVENT);
	public static final Integer IPMS_EVENT_TYPE = Integer.valueOf(IPMS_EVENT);
	public static final Integer WPP_EVENT_TYPE = Integer.valueOf(WPP_EVENT);
	public static final Integer EFWS_EVENT_TYPE = Integer.valueOf(EFWS_EVENT);
	public static final Integer GGSN_EVENT_TYPE = Integer.valueOf(GGSN_EVENT);
	public static final Integer COMMON_EVENT_TYPE = Integer
			.valueOf(COMMON_EVENT);
	private static final Map<String, Integer> eventTyesAttrs = new HashMap<String, Integer>();
	static {
		eventTyesAttrs.put("EventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("ericEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("netspiraEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("rbnEventType", COMMON_EVENT_TYPE);
		eventTyesAttrs.put("sppAlarmType", SPP_EVENT_TYPE);
		eventTyesAttrs.put("netraalarmEventType", NETRA_EVENT_TYPE);
		//eventTyesAttrs.put("alarmEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("axdeventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("rbnCardAlarmType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("rbnSseEventType", COMMON_EVENT_TYPE);
		eventTyesAttrs.put("wppalarmEventType", WPP_EVENT_TYPE);
		//eventTyesAttrs.put("rbnSfpAlarmType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("rbnSseDiskEventType", COMMON_EVENT_TYPE);
		eventTyesAttrs.put("ggsnAlarmName", GGSN_EVENT_TYPE);
		//eventTyesAttrs.put("ecsAlarmEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("ecsAlarmEventType", COMMON_EVENT_TYPE);
		eventTyesAttrs.put("mgcEvent_Type", MGC_EVENT_TYPE);
		eventTyesAttrs.put("irpalarmEventType", JAMBALA_EVENT_TYPE);
		eventTyesAttrs.put("isbladeAlarmClass", ISBLADE_EVENT_TYPE);
		eventTyesAttrs.put("ipmsalarmEventType", IPMS_EVENT_TYPE);
		//eventTyesAttrs.put("eventEventType", COMMON_EVENT_TYPE);
		eventTyesAttrs.put("efwsalarmEventType", EFWS_EVENT_TYPE);
		//eventTyesAttrs.put("ipmsalarmEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("ipmuxalarmEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("ipmuxeventEventType", COMMON_EVENT_TYPE);
		eventTyesAttrs.put("mmsFmSystemAlarmType", MMC_EVENT_TYPE);
		//eventTyesAttrs.put("_OID_VAR_MPT_ALARM_EVENT_TYPE", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("ocmpEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("eriAlarmActiveEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("omperiAlarmAlertEventType", COMMON_EVENT_TYPE);
		eventTyesAttrs.put("sppEventType", SPP_EVENT_TYPE);
		//eventTyesAttrs.put("ossrcalarmEventType", COMMON_EVENT_TYPE);
		eventTyesAttrs.put("sbgEventType", ISBLADE_EVENT_TYPE);
		eventTyesAttrs.put("siteEventType", SITE_EVENT_TYPE);
		//eventTyesAttrs.put("mptAlarmEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("granALARM_TYPE", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("rbnMGEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("rbnIpSecEventType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("rbnChassisAlarmType", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("mgcAlarm_Type", COMMON_EVENT_TYPE);
		//eventTyesAttrs.put("_OID_VAR_ECS_ALARM_EVENT_TYPE", COMMON_EVENT_TYPE);
	}

	public static boolean translateEventTypeAttr(final String attributeName,
			final String attributeValue, final EventNotification notif,
			final boolean perfdegraded) {
		boolean status = false;
		final Integer eventType = eventTyesAttrs.get(attributeName);
		if (eventType != null) {
			status = true;
			switch (eventType.intValue()) {
			case SPP_EVENT:
				notif.setEventType(HandleEventType
						.getsppAlarmType(attributeValue));
				break;

			case SITE_EVENT:
				notif.setEventType(HandleEventType
						.getsiteEventType(attributeValue));
				break;
			case NETRA_EVENT:
				notif.setEventType(HandleEventType
						.getnetraalarmEventType(attributeValue));
				break;
			case MMC_EVENT:
				notif.setEventType(HandleEventType
						.getmmsFmSystemAlarmType(attributeValue));
				// To check if it should be event type or probable cause
				notif.setEventType(HandleEventType.getmmsFmSystemProbableCause(
						attributeValue, perfdegraded));
				break;
			case MGC_EVENT:
				notif.setEventType(HandleEventType
						.getmgcEventType(attributeValue));
				break;
			case JAMBALA_EVENT:
				notif.setEventType(HandleEventType
						.getirpalarmEventType(attributeValue));
				break;
			case ISBLADE_EVENT:
				notif.setEventType(HandleEventType
						.getisbladeAlarmClass(attributeValue));
			case IPMS_EVENT:
				notif.setEventType(HandleEventType
						.getipmsalarmEventType(attributeValue));
				break;
			case WPP_EVENT:
				notif.setEventType(HandleEventType
						.getWPPEventType(attributeValue));
				break;
			case EFWS_EVENT:
				notif.setEventType(HandleEventType
						.getEFWSEventType(attributeValue));
			case GGSN_EVENT:
				final int eventNum = HandleEventType
						.translateEventTypeGgsn(attributeValue.toString());
				final String eventString = Integer.valueOf(eventNum).toString();
				notif.setEventType(eventString);
				break;
			case COMMON_EVENT:
				notif.setEventType(attributeValue.toString());
				break;
			default:
				break;
			}
		}
		return status;
	}
}
