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

public class MoInstanceAttributes {

	private static final Integer SNMP_MO_ATTR = Integer.valueOf(0);
	public static final String SNMP_MOI_OK = "SNMP_MOI_OK";
	private static final Map<String, Integer> moInstanceAttrs = new HashMap<String, Integer>();
	static {
		moInstanceAttrs.put("ManagedObjectInstance", SNMP_MO_ATTR);
		moInstanceAttrs.put("ObjectOfRefrence", SNMP_MO_ATTR);
		moInstanceAttrs.put("mplsLspName", SNMP_MO_ATTR);
		moInstanceAttrs.put("jnxVpnIfVpnName", SNMP_MO_ATTR);
		moInstanceAttrs.put("newAlarmId", SNMP_MO_ATTR);
		moInstanceAttrs.put("jnxVpnPwVpnName", SNMP_MO_ATTR);
		moInstanceAttrs.put("alarmMOClass", SNMP_MO_ATTR);
		moInstanceAttrs.put("sbgtrapId", SNMP_MO_ATTR);
		moInstanceAttrs.put("jnxFruContentsIndex", SNMP_MO_ATTR);
		moInstanceAttrs.put("mgcNotificationAddText", SNMP_MO_ATTR);
		moInstanceAttrs.put("mgcEvent_MoInstance", SNMP_MO_ATTR);
		moInstanceAttrs.put("irpalarmMOinst", SNMP_MO_ATTR);
		moInstanceAttrs.put("isbladeTrapAlarmFaultId", SNMP_MO_ATTR);
		moInstanceAttrs.put("isbladetrapbsId", SNMP_MO_ATTR);
		moInstanceAttrs.put("ipmsalarmMOinst", SNMP_MO_ATTR);
		moInstanceAttrs.put("isitetrapbsId", SNMP_MO_ATTR);
		moInstanceAttrs.put("mgcAlarm_MoInstance", SNMP_MO_ATTR);
		moInstanceAttrs.put("netraalarmMOinst", SNMP_MO_ATTR);
		moInstanceAttrs.put("ocmpmanagedObject", SNMP_MO_ATTR);
		moInstanceAttrs.put("omperiAlarmAlertManagedObject", SNMP_MO_ATTR);
		moInstanceAttrs.put("ossrcalarmMOClass", SNMP_MO_ATTR);
		moInstanceAttrs.put("sbgtrapAlarmFaultId", SNMP_MO_ATTR);
		moInstanceAttrs.put("segwManegedObjectInstance", SNMP_MO_ATTR);
		moInstanceAttrs.put("siteManagedObjectInstance", SNMP_MO_ATTR);
		moInstanceAttrs.put("rbnSfpAlarmCardSlot", SNMP_MO_ATTR);
		moInstanceAttrs.put("wppalarmMOClass", SNMP_MO_ATTR);
		moInstanceAttrs.put("granALARM_MANAGEDOBJECTINSTANCE", SNMP_MO_ATTR);
		moInstanceAttrs.put("jnxFruName", SNMP_MO_ATTR);
		moInstanceAttrs.put("zels_IFNAME", SNMP_MO_ATTR);
		moInstanceAttrs.put("_IFNAME", SNMP_MO_ATTR);
		moInstanceAttrs.put("ocmpmanagedObject", SNMP_MO_ATTR);
		moInstanceAttrs.put("eriAlarmActiveManagedObject", SNMP_MO_ATTR);
	}

	public static String  handleMOInstanceIfApplicable(
			final String attributeName, final String attributeValue,
			final EventNotification notif,
			final StringBuffer additionaltextBuffer,
			final String trapbsid) {
		String result = SNMP_MOI_OK;
		if (moInstanceAttrs.containsKey(attributeName)) {
			if (attributeName.equalsIgnoreCase("_IFNAME")) {
				notif.setManagedObjectInstance("IF="
						.concat((attributeValue.toString())));
				StringBuffer str = new StringBuffer("IfName:");
				notif.addAdditionalAttribute("additionalText", str
						.append(attributeValue).toString());
			} else if (attributeName
					.equalsIgnoreCase("isbladetrapbsId")
					|| (attributeName
							.equalsIgnoreCase("isbladetrapbsId"))) {
				result = attributeValue.toString();

			} else if (attributeName
					.equalsIgnoreCase("isbladeTrapAlarmFaultId")
					|| attributeName
							.equalsIgnoreCase("sbgtrapAlarmFaultId")) {
				notif.setManagedObjectInstance(new StringBuffer(
						trapbsid + ",").append(
						attributeValue.toString()).toString());

			} else if (attributeName
					.equalsIgnoreCase("segwManegedObjectInstance")) {

				notif.addAdditionalAttribute(attributeName,
						attributeValue);
			} else {
				StringBuffer mobjBuffer = new StringBuffer();
				String mObjectValue = mobjBuffer.append(attributeValue)
						.toString();
				notif.setManagedObjectInstance(mObjectValue);
				notif.addAdditionalAttribute(attributeName,
						attributeValue);
				// notif.addAdditionalAttribute("AlarmObjectClass",attributeValue);
			}	
		} else {
			result = null;
		}
		return result;
	}
}