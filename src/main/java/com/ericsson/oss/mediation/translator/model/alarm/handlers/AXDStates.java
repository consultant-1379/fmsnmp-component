package com.ericsson.oss.mediation.translator.model.alarm.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;

public class AXDStates {

	private AXDStates() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleOtherAlarmAttribute.class);

	// AXD axdmplsIfOperStatus
	public static String getspvcOperationIndicator(final String Value) {
		String spvcOperationIndicator = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			spvcOperationIndicator = "create";
			break;
		case 2:
			spvcOperationIndicator = "delete";
			break;
		case 3:
			spvcOperationIndicator = "restart";
			break;
		case 4:
			spvcOperationIndicator = "reroute";
			break;
		default:
			break;
		}
		return spvcOperationIndicator;
	}

	// AXD axdmplsIfOperStatus
	public static String getaxdifadmin(final String Value) {
		String ifadmin = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			ifadmin = "up";
			break;
		case 2:
			ifadmin = "down";
			break;
		case 3:
			ifadmin = "testing";
			break;
		default:
			break;
		}
		return ifadmin;
	}

	// AXD axdmplsIfOperStatus
	public static String getaxdifoper(final String Value) {
		String ifoper = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			ifoper = "If OperStatus is=" + "up";
			break;
		case 2:
			ifoper = "If OperStatus is=" + "down";
			break;
		case 3:
			ifoper = "If OperStatus is=" + "testing";
			break;
		case 4:
			ifoper = "If OperStatus is=" + "Unknown";
			break;
		case 5:
			ifoper = "If OperStatus is=" + "dormant";
			break;
		case 6:
			ifoper = "If OperStatus is=" + "notPresent";
			break;
		case 7:
			ifoper = "If OperStatus is=" + "lowerLayerDown";
			break;
		default:
			break;

		}
		return ifoper;
	}

	// AXD axd301dnaInterfaceEnteringMethod
	public static String getaxd301dnaInterfaceEnteringMethod(final String Value) {
		String axd301dnaInterfaceEnteringMethod = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			axd301dnaInterfaceEnteringMethod = "manual";
			break;
		case 2:
			axd301dnaInterfaceEnteringMethod = "automatic";
			break;
		default:
			break;
		}
		return axd301dnaInterfaceEnteringMethod;
	}

	// AXD axd301dnaInterfaceEnteringMethod
	public static String getaxdatmfVccOperStatus(final String Value) {
		String atmfVccOperStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			atmfVccOperStatus = "Unknown";

			break;
		case 2:
			atmfVccOperStatus = "End2EndUp";

			break;
		case 3:
			atmfVccOperStatus = "End2EndDown";

			break;
		case 4:
			atmfVccOperStatus = "localUpEnd2endUnknown";

			break;
		case 5:
			atmfVccOperStatus = "localDown";

			break;
		default:
			break;
		}
		return atmfVccOperStatus;
	}

	/**
	 * @param attributeValue
	 * @return
	 */
	public static boolean isPerfDegraded(final String attributeValue) {
		String alarmIdentity = "";
		String alarmID = "";
		boolean perfdegraded = false;
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

		if ((alarmID.equals("x500") || alarmID.equals("x501")
				|| alarmID.equals("x502") || alarmID.equals("x503"))) {
			perfdegraded = true;
			LOGGER.info("perfdegraded" + perfdegraded);
		}
		return perfdegraded;
	}

}
