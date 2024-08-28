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

public class PerceivedSeverityAttrs {
	public static final int AXD_SEVERITY = 0;
	public static final int ESAFN_SEVERITY = 1;
	public static final int SEGW_SEVERITY = 2;
	public static final int FUNK_SBR_SEVERITY = 3;
	public static final int OCMPO_SEVERITY = 4;
	public static final int EFWS_SEVERITY = 5;
	public static final int ORA_SEVERITY = 6;
	public static final int MGC_SEVERITY = 7;
	public static final int CPQ_SEVERITY = 8;
	public static final int MRF_SEVERITY = 9;
	public static final int BSS_SEVERITY = 10;
	public static final int BSA_SEVERITY = 11;
	public static final int TIGRIS_SEVERITY = 12;
	public static final int VIVR_SEVERITY = 13;
	public static final int COMMON_SEVERITY = 14;
	public static final int NETRA_SEVERITY = 15;
	
	public static final int AXD_SEVERITY_TYPE = Integer.valueOf(AXD_SEVERITY);
	public static final int ESAFN_SEVERITY_TYPE = Integer
			.valueOf(ESAFN_SEVERITY);
	public static final int SEGW_SEVERITY_TYPE = Integer.valueOf(SEGW_SEVERITY);
	public static final int FUNK_SBR_SEVERITY_TYPE = Integer
			.valueOf(FUNK_SBR_SEVERITY);
	public static final int OCMPO_SEVERITY_TYPE = Integer
			.valueOf(OCMPO_SEVERITY);
	public static final int EFWS_SEVERITY_TYPE = Integer.valueOf(EFWS_SEVERITY);
	public static final int ORA_SEVERITY_TYPE = Integer.valueOf(ORA_SEVERITY);
	public static final int MGC_SEVERITY_TYPE = Integer.valueOf(MGC_SEVERITY);
	public static final int CPQ_SEVERITY_TYPE = Integer.valueOf(CPQ_SEVERITY);
	public static final int MRF_SEVERITY_TYPE = Integer.valueOf(MRF_SEVERITY);
	public static final int BSS_SEVERITY_TYPE = Integer.valueOf(BSS_SEVERITY);
	public static final int BSA_SEVERITY_TYPE = Integer.valueOf(BSA_SEVERITY);
	public static final int TIGRIS_SEVERITY_TYPE = Integer
			.valueOf(TIGRIS_SEVERITY);
	public static final int VIVR_SEVERITY_TYPE = Integer.valueOf(VIVR_SEVERITY);
	public static final int NETRA_SEVERITY_TYPE = Integer.valueOf(NETRA_SEVERITY);
	public static final int COMMON_SEVERITY_TYPE = Integer
			.valueOf(COMMON_SEVERITY);
	
	
	private static final Map<String, Integer> severityAttrs = new HashMap<String, Integer>();
	static {
		severityAttrs.put("ericPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("PerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnDsx1AlarmSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnSseAlarmSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnSfpAlarmSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnSseDiskAlarmSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ecsAlarmPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("mptAlarmPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnDsx3AlarmSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnMGEventSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnIpSecEventSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ossrcalarmPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("mgcEvent_Severity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("omsTrapContentsSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("irpalarmPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("newAlarmPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("axdperceivedSeverity", AXD_SEVERITY_TYPE);
		severityAttrs.put("isbladeTrapAlarmSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ipmsalarmPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("hpocmpperceivedSeverity", MRF_SEVERITY_TYPE);
		severityAttrs.put("mrfperceivedSeverity", MRF_SEVERITY_TYPE);
		severityAttrs.put("mrfperceivedSeverity2", MRF_SEVERITY_TYPE);
		severityAttrs.put("cpqMeAlarmSeverity", CPQ_SEVERITY_TYPE);
		severityAttrs.put("eventPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("wppalarmPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("esasnfPerceivedSeverity", ESAFN_SEVERITY_TYPE);
		severityAttrs.put("efwsalarmPerceivedSeverity2", EFWS_SEVERITY_TYPE);
		severityAttrs.put("efwsalarmPerceivedSeverity", EFWS_SEVERITY_TYPE);
		severityAttrs.put("Broadsoft Alarm Perceived Severity",
				BSA_SEVERITY_TYPE);
		severityAttrs.put("Broadsoft PerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ipmsalarmPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ipmuxeventPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ipmuxalarmPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ipfmAlarmseverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("dlgIsdnSigPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("dlgDsx1SwEvtMskPerceivedSeverity",
				COMMON_SEVERITY_TYPE);
		severityAttrs.put("dlgDsx1PerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("dlgIsdnPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("dlgPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("sviTrapAlarmSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("sviTrap2AlarmSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ivri_trapSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("d_trapSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("mgcAlarm_Severity", MGC_SEVERITY_TYPE);
		severityAttrs.put("mgcEvent_Severity", MGC_SEVERITY_TYPE);
		severityAttrs.put("oraAgentEventSeverity", COMMON_SEVERITY_TYPE);
		// Check with Kunal
		severityAttrs.put("netraalarmPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("netraPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("_OID_VAR_MPT_PERCEIVED_SEVERITY",
				COMMON_SEVERITY_TYPE);
		severityAttrs.put("netspiraPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ocmpo_perceivedSeverity", OCMPO_SEVERITY_TYPE);
		severityAttrs.put("ocmpPerceivedSeverity", OCMPO_SEVERITY_TYPE);
		severityAttrs.put("accAlarmPriority", TIGRIS_SEVERITY_TYPE);
		severityAttrs.put("funkSbrTrapVarSev", FUNK_SBR_SEVERITY);
		severityAttrs.put("sbgPereivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("segwPerceivedSeverity", SEGW_SEVERITY_TYPE);
		severityAttrs.put("sppEventPerceivedseverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("sppeventPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("sppPerceivedseverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("mgcPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("granALARM_PS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("zelsPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ggsnAlarmSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("JnxSyslogSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("redbckPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("sppAlarmPerceivedseverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("ocmpPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("alarmPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("sitePerceivdSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnCardPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("rbnSfpPerceivedSeverity", COMMON_SEVERITY_TYPE);
		severityAttrs.put("srdPerceivedSeverity", MGC_SEVERITY_TYPE);
		severityAttrs.put("eventPS", COMMON_SEVERITY_TYPE);
		severityAttrs.put("vivrperceivedSeverity", VIVR_SEVERITY_TYPE);
		severityAttrs.put("oraAgentEventSeverity", ORA_SEVERITY_TYPE);
		severityAttrs.put("_OID_VAR_ECS_PERCEIVED_SEVERITY",
				COMMON_SEVERITY_TYPE);
	}

	public static boolean translatePerceivedSeverityAttr(final String attributeName,
			final String attributeValue, final EventNotification notif,
			final String axd301FaultIdSev) {
		boolean status = false;
		final Integer severity = severityAttrs.get(attributeName);
		if (severity != null) {
			status = true;
			switch (severity.intValue()) {
			case AXD_SEVERITY:
				if(notif.getFmEventType()=="SYNCALARM"){
					System.out.println("AXD SYNCH ALARM");
			final String alarmNumber = HandleAlarmSeverity.getaxdAlarmNumber(axd301FaultIdSev,
								attributeName, attributeValue);
				notif.setExternalEventId(alarmNumber);
				}
				notif.setSeverity(HandleAlarmSeverity
						.getPerceivedSeverity(attributeValue));
				break;

			case ESAFN_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getesasnfPerceivedSeverity(attributeValue));
				break;
			case SEGW_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getsegwPerceivedSeverity(attributeValue));
				break;
			case FUNK_SBR_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getfunkSbrTrapVarSev(attributeValue));
			case OCMPO_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getocmpoSeverity(attributeValue));
				break;
			case EFWS_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getEFWSAlarmSeverity2(attributeValue));
				break;
			case ORA_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getoraAgentEventSeverity(attributeValue));
			case MGC_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getmgcEventSeverity(attributeValue));
				break;
			case CPQ_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getcpqMeAlarmSeverity(attributeValue));
			case MRF_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getmrfperceivedSeverity2(attributeValue));
			case BSS_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getBSSeverity(attributeValue));
				break;
			case BSA_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getBSAlarmSeverity(attributeValue));
				break;
			case TIGRIS_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getTigrisSeverity(attributeValue));
			case VIVR_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getvivrPerceivedSeverity(attributeValue));
				break;
			case NETRA_SEVERITY:
				break;
			case COMMON_SEVERITY:
				notif.setSeverity(HandleAlarmSeverity
						.getPerceivedSeverity(attributeValue));
				break;
			}
		}
		return status;
	}
	private PerceivedSeverityAttrs()
	{
		
	}
}
