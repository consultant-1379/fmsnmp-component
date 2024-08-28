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
import com.ericsson.oss.mediation.translator.model.alarm.handlers.AlarmCause;

public class ProbableCauseAttrs {
	private static final Map<String, Integer> pcAttrs = new HashMap<String, Integer>();
	private static final Integer IS_PC_ATTR = Integer.valueOf(0);
	static {
		pcAttrs.put("ProbableCause", IS_PC_ATTR);
		pcAttrs.put("accAlarmCause", IS_PC_ATTR);
		/*pcAttrs.put("ericProbableCause", IS_PC_ATTR);
		pcAttrs.put("netspiraProbablcause", IS_PC_ATTR);
		pcAttrs.put("wppalarmPC", IS_PC_ATTR);
		pcAttrs.put("rbnProbableCause", IS_PC_ATTR);
		pcAttrs.put("rbnCardAlarmProbableCause", IS_PC_ATTR);
		pcAttrs.put("rbnSseAlarmProbableCause", IS_PC_ATTR);
		pcAttrs.put("alarmPC", IS_PC_ATTR);
		pcAttrs.put("sppAlarmProbableCause", IS_PC_ATTR);
		pcAttrs.put("srdprobableCause", IS_PC_ATTR);
		pcAttrs.put("rbnSfpAlarmProbableCause", IS_PC_ATTR);
		pcAttrs.put("rbnSseDiskAlarmProbableCause", IS_PC_ATTR);
		pcAttrs.put("ecsAlarmProbableCause", IS_PC_ATTR);
		pcAttrs.put("irpalarmPC", IS_PC_ATTR);
		pcAttrs.put("omsTrapContentsProbableCause", IS_PC_ATTR);
		pcAttrs.put("isbladeTrapAlarmX733Cause", IS_PC_ATTR);
		pcAttrs.put("ipmsalarmPC", IS_PC_ATTR);
		pcAttrs.put("efwsalarmProbableCause2", IS_PC_ATTR);
		pcAttrs.put("efwsalarmProbableCause", IS_PC_ATTR);
		pcAttrs.put("ipmsalarmPC", IS_PC_ATTR);
		pcAttrs.put("Broadsoft Probable Cause", IS_PC_ATTR);
		pcAttrs.put("ipmuxalarmPC", IS_PC_ATTR);
		pcAttrs.put("ipfmAlarmprobablecause", IS_PC_ATTR);
		pcAttrs.put("d_trapCause", IS_PC_ATTR);
		pcAttrs.put("ivri_trapCause", IS_PC_ATTR);
		pcAttrs.put("mgcAlarm_Probablecause", IS_PC_ATTR);
		pcAttrs.put("netraalarmPC", IS_PC_ATTR);
		pcAttrs.put("_OID_VAR_MPT_PROBABLE_CAUSE", IS_PC_ATTR);
		pcAttrs.put("eriAlarmActiveProbableCause", IS_PC_ATTR);
		pcAttrs.put("ocmpProbablecause", IS_PC_ATTR);
		pcAttrs.put("ocmpo_probableCause", IS_PC_ATTR);
		pcAttrs.put("omperiAlarmAlertProbableCause", IS_PC_ATTR);
		pcAttrs.put("ossrcalarmPC", IS_PC_ATTR);
		pcAttrs.put("sppProbableCause", IS_PC_ATTR);
		pcAttrs.put("sbgProbableCause", IS_PC_ATTR);
		pcAttrs.put("siteProbableCause", IS_PC_ATTR);
		pcAttrs.put("mptAlarmProbableCause", IS_PC_ATTR);
		pcAttrs.put("mgcprobableCause", IS_PC_ATTR);
		pcAttrs.put("granALARM_PC", IS_PC_ATTR);
		pcAttrs.put("rbnMGEventProbableCause", IS_PC_ATTR);
		pcAttrs.put("rbnChassisAlarmProbableCause", IS_PC_ATTR);
		pcAttrs.put("vivrprobableCause", IS_PC_ATTR);
		pcAttrs.put("_OID_VAR_ECS_PROBABLE_CAUSE", IS_PC_ATTR);
		pcAttrs.put("sbgTrapcause", IS_PC_ATTR);
		pcAttrs.put("axdprobableCause", IS_PC_ATTR);*/
	}

	public static boolean translateProbableCause(final String attributeName,
			final String attributeValue, final EventNotification notif) {
		boolean status = false;
		
		System.out.println("attributeValue::in ::translateProbableCause"+attributeValue);
		System.out.println("pcAttrs.get(attributeName)::translateProbableCause"+pcAttrs.get(attributeName));
		
		if (pcAttrs.get(attributeName) != null) 
		{
			status = true;
			if (attributeName.equals("accAlarmCause")) {
				notif.setProbableCause(AlarmCause
						.getaccAlarmCause(attributeValue));
			} else {
				notif.setProbableCause(attributeValue);
			}
		}
		System.out.println("PC status::"+status);
		return status;
	}
	private ProbableCauseAttrs()
	{
		
	}
}
