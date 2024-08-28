package com.ericsson.oss.mediation.translator.model.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author tcsgopa
 * 
 */
public class CPQHoTrapFlags {

	/**
	 * private constructor to avoid PMD warnings
	 */
	private CPQHoTrapFlags() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(CPQHoTrapFlags.class);

	public static String getcpqHoTrapFlags(final String attributevalue) {

		final StringBuffer cpqstr = new StringBuffer();

		final int cpqHoTrapFlagValue = Integer.parseInt(attributevalue);
		final int agentType = cpqHoTrapFlagValue & 1;
		if (agentType == 0) {
			cpqstr.append("\n\tAgent Type = ").append("Server");
		} else {
			cpqstr.append("\n\tAgent Type = ").append("Client");
		}

		final int clientIPAddressType = cpqHoTrapFlagValue & 2;
		if (clientIPAddressType == 2) {
			cpqstr.append("\n\tClient IP address type = ").append("DHCP entry");
		} else {
			cpqstr.append("\n\tClient IP address type = ").append(
					"static entry");
		}

		final int trapCondition = cpqHoTrapFlagValue & 28;
		try {
			switch (trapCondition) {
			case 0:
				cpqstr.append("\n\tTrap Condition = ").append("Not used");
				break;

			case 4:
				cpqstr.append("\n\tTrap Condition = ").append(
						"Condition unknown or N/A");
				break;

			case 8:
				cpqstr.append("\n\tTrap Condition = ").append("Condition ok");
				break;

			case 12:
				cpqstr.append("\n\tTrap Condition = ").append(
						"Condition degraded");
				break;

			case 16:
				cpqstr.append("\n\tTrap Condition = ").append(
						"Condition failed");
				break;

			case 20:
				break;

			case 24:
				break;

			case 28:
				break;

			default:
				break;
			}

		} catch (Exception ex) {
			LOGGER.info("--- HPMRFP Translator >> Exeption During CPQMIB variable translation");
			return cpqstr.toString();
		}
		return cpqstr.toString();
	}

	public static String getcpqMeAlarmSampleType(final String attributevalue) {

		final StringBuffer cpqstr1 = new StringBuffer();
		String str;
		switch (Integer.parseInt(attributevalue.toString())) {
		case 1:
			str = "absoluteValue";
			break;
		case 2:
			str = "deltaValue";
			break;
		case 3:
			str = "absSuppressRisingTrap";
			break;
		case 4:
			str = "absSuppressFallingTrap";
			break;
		default:
			str = "";
			break;
		}
		cpqstr1.append("\n\tAlarmSampleType : ").append(str);
		return cpqstr1.toString();
	}

}
