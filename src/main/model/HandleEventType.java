package com.ericsson.oss.mediation.translator.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.adventnet.snmp.mibs.mibparser.MCModule;

public class HandleEventType {

	private HandleEventType() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleEventType.class);

	// Processing zelsAlarmNumber
	public static String getzelsAlarmNumberEventType(final String value) {
		String getzelsAlarmNumberEventType = "";
		final int alarm_id = Integer.parseInt(value);

		if ((alarm_id == 22) || (alarm_id == 23) || (alarm_id == 24)
				|| (alarm_id == 25) || (alarm_id == 51) || (alarm_id == 52)
				|| (alarm_id == 54) || (alarm_id == 55)) {
			getzelsAlarmNumberEventType = "21";
		}

		else if ((alarm_id == 42) || (alarm_id == 43) || (alarm_id == 44)
				|| (alarm_id == 45) || (alarm_id == 46) || (alarm_id == 47)
				|| (alarm_id == 48) || (alarm_id == 57) || (alarm_id == 58)) {
			getzelsAlarmNumberEventType = "20";
		} else if ((alarm_id == 33) || (alarm_id == 34) || (alarm_id == 35)
				|| (alarm_id == 36) || (alarm_id == 37) || (alarm_id == 39)
				|| (alarm_id == 40) || (alarm_id == 41) || (alarm_id == 49))

		{
			getzelsAlarmNumberEventType = "1";
		}

		else if ((alarm_id == 26) || (alarm_id == 27) || (alarm_id == 28)
				|| (alarm_id == 29) || (alarm_id == 30) || (alarm_id == 31)
				|| (alarm_id == 32))

		{
			getzelsAlarmNumberEventType = "24";
		}

		else if (alarm_id == 38)

		{
			getzelsAlarmNumberEventType = "23";
		}

		else if (alarm_id == 53)

		{
			getzelsAlarmNumberEventType = "22";
		}

		else if (alarm_id == 50)

		{
			getzelsAlarmNumberEventType = "4";
		}

		else {
			getzelsAlarmNumberEventType = "0";
		}
		return getzelsAlarmNumberEventType;

	}

	// RICSSON-SOG-ALARM-MIB newAlarmName
	public static String getnewAlarmNameEventType(final String attributeValue) {
		String newAlarmNameEventType = "0";
		newAlarmNameEventType = SnmpEtMaps.getEventType(attributeValue);
		if (newAlarmNameEventType == null) {
			return newAlarmNameEventType;
		}

		return newAlarmNameEventType;
	}

	// ESA-SNF ituAlarmEventType,ituAlarmEventTypeB
	public static String getituAlarmEventType(final String value) {
		String ituAlarmEventType = "0";
		try {
			int alarmEventType = Integer.parseInt(value.toString());
			if (alarmEventType <= 11) {
				ituAlarmEventType = value.trim();
			}
		} catch (Exception e) {
			LOGGER.error("Exception while geting getituAlarmEventType:"
					+ e.getStackTrace());
			ituAlarmEventType = "0";// Unknown Event type

		}
		return ituAlarmEventType;
	}

	// SPP sppAlarmType
	public static String getsppAlarmType(final String value) {
		String sppAlarmType = "";

		try {
			switch (Integer.parseInt(value.toString())) {
			case 0: // unknown
			case 2: // Communications Alarm
			case 3: // Environmental Alarm
			case 4: // Equipment Alarm
			case 10:// Processing Error Alarm
			case 11:// Quality of Service Alarm
			case 15:// Integrity Violation
			case 16:// Operational Violation
			case 17:// Physical Violation
			case 18:// Security Service Violation
			case 19:// Time Domain Violation
				sppAlarmType=value.trim();
				break;
			default:
				sppAlarmType = "0";
				break;

			}
		} catch (Exception e) {
			LOGGER.error("Exception is:" + e.getStackTrace());
			sppAlarmType = "0";// Unknown Event type
		}
		return sppAlarmType;
	}

	// SITE siteEventType
	public static String getsiteEventType(final String value) {
		String siteEventType = "";

		try {
			switch (Integer.parseInt(value.toString())) {
			case 1:
			case 2:
				siteEventType = "1"; // Communication Alarm
				break;
			case 3:
				siteEventType = "3"; // Enviromental Alarm
				break;
			case 4:
			case 5:
				siteEventType = "5"; // Equipment Alarm
				break;
			case 10:
				siteEventType = "2"; // Processing Error Alarm
				break;
			case 11:
				siteEventType = "4"; // Quality of service Alarm
				break;
			case 15:
				siteEventType = "15"; // Integrity Violation
				break;
			case 16:
				siteEventType = "16"; // operational Violation
				break;
			case 17:
				siteEventType = "17"; // physical violation
				break;
			case 18:
				siteEventType = "18"; // Security Service Violation
				break;
			case 19:
				siteEventType = "19"; // Time Domain Violation
				break;

			}
		} catch (Exception e) {
			LOGGER.error("Exception is:" + e.getStackTrace());
			siteEventType = "0";// Unknown Event type
		}
		return siteEventType;
	}

	// MMMC mmsFmSystemAlarmType
	public static String getmmsFmSystemAlarmType(final String value) {
		String mmsFmSystemAlarmTypeEventType = "";
		try {
			switch (Integer.valueOf(value).intValue()) {
			// Event Types defined in X733
			case 1:
				mmsFmSystemAlarmTypeEventType = "1"; // Communication Alarm
				break;
				
			case 2:
				mmsFmSystemAlarmTypeEventType = "4"; // Quality of Service Alarm

				break;
			case 3:
				mmsFmSystemAlarmTypeEventType = "2"; // Processing Error Alarm

				break;
			case 4:
				mmsFmSystemAlarmTypeEventType = "5"; // Equipment Alarm

				break;
			case 5:
				mmsFmSystemAlarmTypeEventType = "3"; // Environmental alarm
				break;

			default:
				mmsFmSystemAlarmTypeEventType = "0";// Unknown
				break;
			}
		} catch (Exception ex) {
			LOGGER.error("setEventType Exception." + ex.getMessage());
			mmsFmSystemAlarmTypeEventType = "0";// Unknown
		}
		return mmsFmSystemAlarmTypeEventType;
	}

	public static String getmmsFmSystemProbableCause(final String value,
			final boolean perfdegraded) {
		String mmsFmSystemAlarmProbableCause = "";
		try {
			switch (Integer.valueOf(value).intValue()) {
			// Event Types defined in X733
			case 1:
				mmsFmSystemAlarmProbableCause = "305";// Communications Protocol
														// Error
			case 2:
				if (perfdegraded) {
					mmsFmSystemAlarmProbableCause = "334";// Performance
															// Degraded
				} else {
					mmsFmSystemAlarmProbableCause = "351";// Threshold Crossed
				}
				break;
			case 3:
				mmsFmSystemAlarmProbableCause = "307";// Configuration or
														// Customization Error
				break;
			case 4:
				mmsFmSystemAlarmProbableCause = "0";// Indeterminate
				break;
			case 5:
				mmsFmSystemAlarmProbableCause = "0";// Indeterminate
				break;

			default:
				break;
			}
		} catch (Exception ex) {
			LOGGER.error("setEventType Exception." + ex.getMessage());
			mmsFmSystemAlarmProbableCause = "0";// Indeterminate
		}
		return mmsFmSystemAlarmProbableCause;
	}

	// MGC EVENT TYPE
	public static String getmgcEventType(final String value) {
		String mgcEvent_Type = "";

		try {
			switch (Integer.parseInt(value.toString())) {
			case 1:
				mgcEvent_Type = "0";// Unknown Event type
				break;
			case 2:
				mgcEvent_Type = "2"; // Communication Alarm
				break;
			case 3:
				mgcEvent_Type = "11"; // Qualityofservice alarm
				break;
			case 4:
				mgcEvent_Type = "10"; // Processing Error Alarm
				break;
			case 5:
				mgcEvent_Type = "4"; // Equipment Alarm
				break;
			case 6:
				mgcEvent_Type = "3"; // Environmental Alarm
				break;

			}
		} catch (Exception e) {
			LOGGER.error("Exception is:" + e.getStackTrace());
			LOGGER.error(" Exception in decoding the event type Setting to unknown");
			mgcEvent_Type = "0";// Unknown Event type
			return mgcEvent_Type;

		}
		return mgcEvent_Type;
	}

	// JAMBALA
	public static String getirpalarmEventType(final String value) {
		String irpalarmEventType = "";
		try {
			switch (Integer.parseInt(value.toString())) {
			case 0:
				irpalarmEventType = "0"; // Unknown Event Type
				break;
			case 2:
				irpalarmEventType = "2"; // Communication Alarm
				break;
			case 3:
				irpalarmEventType = "3"; // Enviromental Alarm
				break;
			case 4:
				irpalarmEventType = "4"; // Equipment Alarm
				break;
			case 6:
				irpalarmEventType = "6"; // Administrative alarm
				break;
			case 7:
				irpalarmEventType = "7"; // Switching or crossconnecting alarm
				break;
			case 9:
				irpalarmEventType = "9"; // Performance event
				break;
			case 10:
				irpalarmEventType = "10"; // Processing Error Alarm
				break;
			case 11:
				irpalarmEventType = "11"; // Quality of service Alarm
				break;
			case 15:
				irpalarmEventType = "15"; // Integrity Violation
				break;
			case 16:
				irpalarmEventType = "16"; // operational Violation
				break;
			case 17:
				irpalarmEventType = "17"; // physical violation
				break;
			case 18:
				irpalarmEventType = "18"; // Security Service Violation
				break;
			case 19:
				irpalarmEventType = "19"; // Time Domain Violation
				break;
			default:
				irpalarmEventType = "0"; // Unknown Event Type
				break;
			}
		} catch (Exception ex) {
			LOGGER.error("Exception is:" + ex.getStackTrace());
			irpalarmEventType = "0";
			return irpalarmEventType;
		}
		return irpalarmEventType;
	}

	// ISBLADE EVENT TYPE
	public static String getisbladeAlarmClass(final String value) {
		String isbladeEventType = "";
		try {

			switch (Integer.parseInt(value.toString())) {
			case 2:
				isbladeEventType = "1"; // Communication Alarm
				break;
			case 3:
				isbladeEventType = "3"; // Environmental Alarm
				break;
			case 4:
				isbladeEventType = "5"; // Equipment Alarm
				break;
			case 10:
				isbladeEventType = "2"; // Processing Error Alarm
				break;
			case 11:
				isbladeEventType = "4"; // Quality of Service Alarm
				break;
			case 50:
				isbladeEventType = "20"; // Unused
				break;
			default:
				isbladeEventType = "0"; // Unknown Event Type
				break;

			}
		} catch (Exception ex) {
			LOGGER.error("Exception is:" + ex.getStackTrace());
			isbladeEventType = "0";
			return isbladeEventType;
		}
		return isbladeEventType;
	}

	// WPP EVENT TYPE
	public static String getWPPEventType(final String value) {
		String eventType = "";
		try {

			switch (Integer.parseInt(value.toString())) {
			// Event Types defined in X733
			case 1:
			case 2:
				eventType = "1"; // Communication Alarm
				break;
			case 3:
				eventType = "3"; // Enviromental Alarm
				break;
			case 4:
			case 5:
				eventType = "5"; // Equipment Alarm
				break;
			case 10:
				eventType = "2"; // Processing Error Alarm
				break;
			case 11:
				eventType = "4"; // Quality of service Alarm
				break;
			case 15:
				eventType = "15"; // Integrity Violation
				break;
			case 16:
				eventType = "16"; // operational Violation
				break;
			case 17:
				eventType = "17"; // physical violation
				break;
			case 18:
				eventType = "18"; // Security Service Violation
				break;
			case 19:
				eventType = "19"; // Time Domain Violation
				break;
			default:
				eventType = "0";// Unknown
				break;
			}
		} catch (Exception ex) {
			LOGGER.error("exception is" + ex.getStackTrace());
			eventType = "0";
			return eventType;
		}
		return eventType;
	}

	public static String getEFWSEventType(final String value) {
		String eventType = "";
		switch (Integer.parseInt(value.toString())) {
		// The ITU event Type values as per M.3100
		case 1:
			eventType = "20"; // other
			break;
		case 2:
			eventType = "2"; // communicationsAlarm
			break;
		case 3:
			eventType = "11"; // qualityOfServiceAlarm
			break;
		case 4:
			eventType = "10"; // processingErrorAlarm
			break;
		case 5:
			eventType = "4"; // equipmentAlarm
			break;
		case 6:
			eventType = "3"; // environmentalAlarm
			break;
		case 7:
			eventType = "15"; // integrityViolation
			break;
		case 8:
			eventType = "16"; // OperationalViolation
			break;
		case 9:
			eventType = "17"; // physicalViolation
			break;
		case 10:
			eventType = "18"; // securityServiceViolation
			break;
		case 11:
			eventType = "19"; // timeDomainViolation
			break;
		default:
			eventType = "0";// Unknown
			break;
		}
		return eventType;
	}

	// IPMS
	public static String getipmsalarmEventType(final String value) {
		String ipmsalarmEventType = "";
		switch (Integer.parseInt(value.toString())) {
		case 0:
			ipmsalarmEventType = "0"; // Unknown Event Type
			break;
		case 2:
			ipmsalarmEventType = "2"; // Communication Alarm
			break;
		case 3:
			ipmsalarmEventType = "3"; // Enviromental Alarm
			break;
		case 4:
			ipmsalarmEventType = "4"; // Equipment Alarm
			break;
		case 10:
			ipmsalarmEventType = "10"; // Processing Error Alarm
			break;
		case 11:
			ipmsalarmEventType = "11"; // Quality of service Alarm
			break;
		case 15:
			ipmsalarmEventType = "15"; // Integrity Violation
			break;
		case 16:
			ipmsalarmEventType = "16"; // operational Violation
			break;
		case 17:
			ipmsalarmEventType = "17"; // physical violation
			break;
		case 18:
			ipmsalarmEventType = "18"; // Security Service Violation
			break;
		case 19:
			ipmsalarmEventType = "19"; // Time Domain Violation
			break;
		default:
			ipmsalarmEventType = "0"; // Unknown Event Type
			break;
		}
		return ipmsalarmEventType;
	}

	// NETRA
	public static String getnetraalarmEventType(final String value) {
		String netraalarmEventType = "";
		switch (Integer.parseInt(value.toString())) {
		case 0:
			netraalarmEventType = "0";// Unknown event type
			break;
		case 2:
			netraalarmEventType = "2";// Communications alarm
			break;
		case 3:
			netraalarmEventType = "3";// Environmental alarm
			break;
		case 4:
			netraalarmEventType = "4";// Equipment alarm
			break;
		case 10:
			netraalarmEventType = "10";// Processing error alarm
			break;
		case 11:
			netraalarmEventType = "11";// Quality of service alarm
			break;
		case 15:
			netraalarmEventType = "15";// Integrity violation
			break;
		case 16:
			netraalarmEventType = "16";// Operational violation
			break;
		case 17:
			netraalarmEventType = "17";// Physical violation
			break;
		case 18:
			netraalarmEventType = "18";// Security service violation
			break;
		case 19:
			netraalarmEventType = "19";// Time domain violation
			break;
		default:
			netraalarmEventType = "0";// Unknown event type
			break;
		}
		return netraalarmEventType;
	}

}
