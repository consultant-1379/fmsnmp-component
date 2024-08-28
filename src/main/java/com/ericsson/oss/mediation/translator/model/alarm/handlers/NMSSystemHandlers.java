package com.ericsson.oss.mediation.translator.model.alarm.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;

public class NMSSystemHandlers {

	private NMSSystemHandlers() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleOtherAlarmAttribute.class);

	// MMC mmsFmSystemAlarmID
	public static String getmmsFmSystemAlarmID(final String attributeValue) {
		String alarmIdentity = "";

		String alarmID = "";

		alarmIdentity = attributeValue.toString();
		LOGGER.info("Alarm Id String = " + alarmIdentity);
		LOGGER.info("alarmIdentity read....... ");

		try {
			if (alarmIdentity.indexOf('x') != -1) {
				LOGGER.info("Found x.....................");
				alarmID = alarmIdentity.substring((alarmIdentity.indexOf('x')));
			} else {
				LOGGER.info("Did not find X.............");
				alarmID = "x999";
			}
			LOGGER.info("Alarm Id String = " + alarmID);

		} catch (Exception e1) {
			LOGGER.info("Error on mmsFmSystemAlarmID" + e1.getMessage());
		}

		return alarmIdentity;
	}

	// MMC merOperationalState

	public static String getmerOperationalState(final String attributeValue) {

		String merOpState = "";

		switch (Integer.valueOf(attributeValue.toString()).intValue()) {
		case 1:
			merOpState = HandleOtherAlarmAttribute.merOpState
					.concat("enabled(1).");
			break;
		case 2:
			merOpState = HandleOtherAlarmAttribute.merOpState
					.concat("disabled(2).");
			break;
		default:
			merOpState = HandleOtherAlarmAttribute.merOpState
					.concat("--------------");
			break;
		}
		return merOpState;
	}

	// MMC merAdminstrativeState

	public static String getmerAdminstrativeState(final String attributeValue) {

		String merAdminState = "";

		switch (Integer.valueOf(attributeValue.toString()).intValue()) {
		case 1:
			merAdminState = HandleOtherAlarmAttribute.merAdminState
					.concat("unlocked(1).");
			break;
		case 2:
			merAdminState = HandleOtherAlarmAttribute.merAdminState
					.concat("locked(2).");
			break;
		case 3:
			merAdminState = HandleOtherAlarmAttribute.merAdminState
					.concat("shutdown(3).");
			break;
		default:
			merAdminState = HandleOtherAlarmAttribute.merAdminState
					.concat("--------------");
			break;
		}
		return merAdminState;
	}

	// MMC mmsCmSystemOperationalState

	public static String getmmsCmSystemOperationalState(
			final String attributeValue) {

		String mmsCmSystemOpState = "";

		switch (Integer.valueOf(attributeValue).intValue()) {
		case 1:
			mmsCmSystemOpState = HandleOtherAlarmAttribute.mmsCmSystemOpState
					.concat("up(1).");
			break;
		case 2:
			mmsCmSystemOpState = HandleOtherAlarmAttribute.mmsCmSystemOpState
					.concat("locked(2).");
			break;
		case 3:
			mmsCmSystemOpState = HandleOtherAlarmAttribute.mmsCmSystemOpState
					.concat("degraded(3).");
			break;
		case 4:
			mmsCmSystemOpState = HandleOtherAlarmAttribute.mmsCmSystemOpState
					.concat("down(4).");
			break;
		case 5:
			mmsCmSystemOpState = HandleOtherAlarmAttribute.mmsCmSystemOpState
					.concat("Unknown(5).");
			break;
		default:
			mmsCmSystemOpState = mmsCmSystemOpState.concat("--------------");
			break;
		}
		return mmsCmSystemOpState;
	}

	// MMC mmsCmSystemAdministrativeState

	public static String getmmsCmSystemAdministrativeState(
			final String attributeValue) {

		String mmsCmSystemAdministrativeState = "";

		switch (Integer.valueOf(attributeValue.toString()).intValue()) {
		case 1:
			mmsCmSystemAdministrativeState = HandleOtherAlarmAttribute.mmsCmSystemAdState
					.concat("unlocked(1).");
			break;
		case 2:
			mmsCmSystemAdministrativeState = HandleOtherAlarmAttribute.mmsCmSystemAdState
					.concat("locked(2).");
			break;
		case 3:
			mmsCmSystemAdministrativeState = HandleOtherAlarmAttribute.mmsCmSystemAdState
					.concat("shutdown(3).");
			break;
		default:
			mmsCmSystemAdministrativeState = HandleOtherAlarmAttribute.mmsCmSystemAdState
					.concat("--------------");
			break;
		}
		return mmsCmSystemAdministrativeState;
	}

}
