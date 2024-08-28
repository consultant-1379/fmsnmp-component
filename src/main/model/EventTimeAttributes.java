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

public class EventTimeAttributes {
	public static final Integer SNMP_BROADSOFT_TIME = Integer.valueOf(0);
	public static final Integer SNMP_COMMON_TIME = Integer.valueOf(1);
	public static final Integer SNMP_OMS_TIME = Integer.valueOf(2);
	public static final Integer SNMP_MCG_TIME = Integer.valueOf(3);
	private static final Map<String, Integer> eventTimeAttrs = new HashMap<String, Integer>();
	static {
		
			eventTimeAttrs.put("AlarmTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("rbnCardAlarmDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("rbnSseAlarmDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("rbnSfpAlarmDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("wppalarmEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("rbnMGEventDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("rbnIpSecEventDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("jnxCollNotifyDate",SNMP_COMMON_TIME);
			eventTimeAttrs.put("jnxDomCurrentAlarmDate",SNMP_COMMON_TIME);
			eventTimeAttrs.put("jnxCmCfgChgEventDate",SNMP_COMMON_TIME);
			eventTimeAttrs.put("jnxCmRescueChgDate",SNMP_COMMON_TIME);
			eventTimeAttrs.put("zelsEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("irpalarmEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("isbladetrapTimeAndDate",SNMP_COMMON_TIME);
			eventTimeAttrs.put("ipmsalarmEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("mrfeventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("hpocmpeventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("mrfeventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("eventEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("efwsdate_time",SNMP_COMMON_TIME);

			eventTimeAttrs.put("ipmsalarmEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("ipmuxalarmEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("ipmuxeventEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("ipfmAlarmtime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("ivri_trapTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("d_trapTime",SNMP_COMMON_TIME);

			eventTimeAttrs.put("mmsFmSystemAlarmTimeOfOccurance",SNMP_COMMON_TIME);
			eventTimeAttrs.put("oraAgentEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("netraalarmEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("ocmpo_timeStamp",SNMP_COMMON_TIME);
			eventTimeAttrs.put("ocmpEventdateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("omperiAlarmAlertEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("sbgtrapTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("ossrcalarmEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("segwEventdateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("siteEventDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("sppEventDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("oraAgentEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("mgcEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("granALARM_TIME",SNMP_COMMON_TIME);
			eventTimeAttrs.put("jnxOtnLastAlarmDate",SNMP_COMMON_TIME);
			eventTimeAttrs.put("jnxAVPatternTimestamp",SNMP_COMMON_TIME);
			eventTimeAttrs.put("jnxJsPacketMirrorDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("jnxAVPatternTimestamp",SNMP_COMMON_TIME);
			eventTimeAttrs.put("jnxJsIdpLastSignatureUpdateTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("eventEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("eriAlarmActiveEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("sppAlarmtDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("alarmEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("vivrtimeStamp",SNMP_COMMON_TIME);
			eventTimeAttrs.put("mgcEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("srdEventTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("esasnfEventDateAndTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("axd301GroupTime",SNMP_COMMON_TIME);
			eventTimeAttrs.put("Broadsoft Event Time",SNMP_BROADSOFT_TIME);
			eventTimeAttrs.put("Broadsoft TimeStamp",SNMP_BROADSOFT_TIME);
			eventTimeAttrs.put("BroadsoftTime2",SNMP_BROADSOFT_TIME);
			eventTimeAttrs.put("Broadsoft TimeStamp",SNMP_BROADSOFT_TIME);			
			eventTimeAttrs.put("omsTrapContentsTimeStamp",SNMP_OMS_TIME);
			eventTimeAttrs.put("mgcEvent_Time",SNMP_MCG_TIME);
			eventTimeAttrs.put("mgcAlarm_Time",SNMP_MCG_TIME);
	}
	public static Integer getEventTimeFormat(String attributeName) {
		return eventTimeAttrs.get(attributeName);
	}
}
