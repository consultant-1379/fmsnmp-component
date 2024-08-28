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

public class EventIdAttrs {
	private static final Map<String, Integer> eventIdAttrs = new HashMap<String, Integer>();
	private static final Integer IS_EVENT_ID_ATTR = Integer.valueOf(0);
	private EventIdAttrs() {
		
	}
	static {

		eventIdAttrs.put("alarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("ExternalEventID", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("jnxVpnIfIndex", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("_IFNUMBER", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("_OID_VAR_ECS_NOTIFICATION_ID", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("jnxVpnPwIndex", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("netraalarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("ecsAlarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("mptAlarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("rbnIpSecRemoteId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("alarmIndex", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("ggsnAlarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("ifIndex", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("rbnChassisAlarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("JNXContentsContainerIndex", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("wppalarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("eventId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("ipmuxalarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("ipmuxeventId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("mgcAlarm_Index", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("netspiraNotificationId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("ossrcalarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("eriAlarmActiveMinorType", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("ossrcalarmApp", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("siteAlarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("sppeventId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("sppalarmId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("jnxRedundancyContentsIndex", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("granALARM_ALARMID", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("jnxFruSlot", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("ospfRouterId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("rbnNECircuitId", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("zels_IFNUMBER", IS_EVENT_ID_ATTR);
		eventIdAttrs.put("_OID_VAR_MPT_NOTIFICATION_ID", IS_EVENT_ID_ATTR);
	}
	public static boolean translateEventId(final String attributeName,
			final String attributeValue, final EventNotification notif) {
		boolean status = false;
		if (eventIdAttrs.get(attributeName) != null) {
			status = true;
			if (attributeName.equals("eventId")) {
			} else if (attributeName.equals("ifIndex")
					|| attributeName.equals("alarmIndex")
					|| attributeName.equals("JNXContentsContainerIndex")
					|| attributeName.equals("jnxRedundancyContentsIndex")
					|| attributeName.equals("ospfRouterId")
					|| attributeName.equals("jnxVpnIfIndex")
					|| attributeName.equals("rbnNECircuitId")
					|| attributeName.equals("ossrcalarmApp")) {
				notif.addAdditionalAttribute(attributeName, attributeValue);
			} else if (attributeName.equals("jnxFruSlot")) {
				notif.addAdditionalAttribute(attributeName, attributeValue);
				notif.setEventAgentId(attributeValue);
			}

			notif.setExternalEventId(attributeValue);
		}
		return status;
	}
	
}
