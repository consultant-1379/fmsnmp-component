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
import com.ericsson.oss.mediation.translator.model.alarm.handlers.NetTraAlarmPSwith5;

public class SpecificProblemAttrs {
	private static final Map<String, Integer> spAttrs = new HashMap<String, Integer>();
	private static final Integer IS_SP_ATTR = Integer.valueOf(0);
	private static final String[] gsnReportEvent = { "nocSmallLocalRestart",
			"nocSmallRestart", "nocLargeRestart", "nocReload", "nocCmRestart" };
	static {
		spAttrs.put("SpecificProblem", IS_SP_ATTR);
		spAttrs.put("axdSpecificProblem", IS_SP_ATTR);
		spAttrs.put("segwspecificProblem", IS_SP_ATTR);
		spAttrs.put("omperiAlarmAlertSpecificProblem", IS_SP_ATTR);
		spAttrs.put("ossrcalarmSP", IS_SP_ATTR);
		spAttrs.put("sppAlarmspecificProblem", IS_SP_ATTR);
		spAttrs.put("segwspecificProblem", IS_SP_ATTR);
		spAttrs.put("ericSpecificProblem", IS_SP_ATTR);
		spAttrs.put("ipmsalarmSP", IS_SP_ATTR);
		spAttrs.put("mmsFmSystemAlarmProbableCause", IS_SP_ATTR);
		spAttrs.put("netraalarmSP", IS_SP_ATTR);
		spAttrs.put("sbgtrapName", IS_SP_ATTR);
		spAttrs.put("siteSpecificProblem", IS_SP_ATTR);
		spAttrs.put("eriAlarmActiveSpecificProblem", IS_SP_ATTR);
		spAttrs.put("sppeventSP", IS_SP_ATTR);
		spAttrs.put("sppspecificProblem", IS_SP_ATTR);
		spAttrs.put("wppalarmSP", IS_SP_ATTR);
		spAttrs.put("ericSpecificProblem", IS_SP_ATTR);
		spAttrs.put("granALARM_SP", IS_SP_ATTR);
		spAttrs.put("ggsnAlarmSourceId", IS_SP_ATTR);
		spAttrs.put("rbnCardAlarmId", IS_SP_ATTR);
		spAttrs.put("rbnSfpAlarmId", IS_SP_ATTR);
		spAttrs.put("mgcEvent_Slogan", IS_SP_ATTR);
		spAttrs.put("irpalarmSP", IS_SP_ATTR);
		spAttrs.put("isbladeTrapName", IS_SP_ATTR);
		spAttrs.put("ipmsalarmSP", IS_SP_ATTR);
		spAttrs.put("eventSP", IS_SP_ATTR);
		spAttrs.put("ipmuxalarmSP", IS_SP_ATTR);
		spAttrs.put("ipmuxeventSP", IS_SP_ATTR);
		spAttrs.put("ivri_trapSpecificProblem", IS_SP_ATTR);
		spAttrs.put("d_trapSpecificProblem", IS_SP_ATTR);
		spAttrs.put("mgcEvent_Slogan", IS_SP_ATTR);
		spAttrs.put("mgcAlarm_Slogan", IS_SP_ATTR);
		spAttrs.put("netraalarmSP", IS_SP_ATTR);
		spAttrs.put("eventSP", IS_SP_ATTR);
		spAttrs.put("alarmSP", IS_SP_ATTR);
		spAttrs.put("newAlarmName", IS_SP_ATTR);
	}

	public static boolean translateSpecificProblem(final String attributeName,
			final String attributeValue, final EventNotification notif,
			final StringBuffer additionaltextBuffer, final int netraAlarmSeverity) {
		boolean status = false;
		final Integer spAttr = spAttrs.get(attributeName);
		if (spAttr != null) {
			status = true;
			// ERICSSON-SOG-ALARM-MIB newAlarmName
			if (attributeName.equals("newAlarmName")) {
				final String newAlarmNameProbablecause = HandleProbableCause
						.getnewAlarmNameProbableCause(attributeValue);
				final String newAlarmNameEventType = HandleEventType
						.getnewAlarmNameEventType(attributeValue);
				notif.setProbableCause(newAlarmNameProbablecause);
				notif.setEventType(newAlarmNameEventType);

			}
			if (attributeName.equals("netraalarmSP")) {
				// String tempSpec_problem = attributeValue.toString();
				final String spec_problem[] = NetTraAlarmPSwith5
						.getnetraalarmpswith5(attributeValue,
								netraAlarmSeverity);
				notif.setSpecificProblem(spec_problem[0]);
				additionaltextBuffer.append("problemTextString" + ":"
						+ spec_problem[1] + "\n");

			}

			// for gsn event filtering // this can be ON OFF may be through
			// properties file, special case this is
			if (attributeName.equalsIgnoreCase("eventsp")) {
				for (int j = 0; j < gsnReportEvent.length; j++) {
					if (gsnReportEvent[j].equalsIgnoreCase(attributeValue)) {
						notif.setSpecificProblem(attributeValue);
						notif.setTranslateResult(TranslateResult.FORWARD_ALARM);
					} else {
						notif.setSpecificProblem(attributeValue);
						notif.setTranslateResult(TranslateResult.DROP_ALARM);
					}
				}
			}

			else {

				notif.setSpecificProblem(attributeValue);

			}
		}
		return status;
	}
	private SpecificProblemAttrs()
	{
		
	}
}
