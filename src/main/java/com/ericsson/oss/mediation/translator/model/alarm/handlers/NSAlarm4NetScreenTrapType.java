package com.ericsson.oss.mediation.translator.model.alarm.handlers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.translator.model.*;

public class NSAlarm4NetScreenTrapType {

	private NSAlarm4NetScreenTrapType() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleOtherAlarmAttribute.class);

	// NETSCREEN TrapType
	public static EventNotification buildNsAlarm4netscreenTrapType(
			final String Name, final String Value,
			final EventNotification notification) {
		EventNotification notif = notification;
		try {

			switch (Integer.parseInt(Value.toString())) {

			case 15:// High Availibility(15)
				notif.addAdditionalAttribute("Specific", "High Availibility");
				notif.setEventType("1"); // Communication
				notif.setFmEventType("ERROR");
				notif.setProbableCause("329");// Loss of Signal
				notif.setSpecificProblem("Loss of Signal.");
				notif.setSeverity(Constants.SEV_WARNING);
				break;
			case 19: // device-dead(19)
				notif.setEventType("5");// equipment alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("315");// Equipment malfunction
				notif.setSpecificProblem("Hardware Fault");
				notif.setSeverity(Constants.SEV_CRITICAL);
				notif.addAdditionalAttribute("Specific", "device-dead");
				break;
			case 20:// low-memory(20)
				notif.addAdditionalAttribute("Specific",
						"Not enough memory available");
				notif.setEventType("2");// Processing Error Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("332"); // Out of memory
				notif.setSpecificProblem("Not enough memory available");
				notif.setSeverity(Constants.SEV_MAJOR);
				break;
			case 21:// dns-srv-down(21)
				notif.addAdditionalAttribute("Specific", "dns-srv-down");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("329");// Loss of signal
				notif.setSpecificProblem("Unable to communicate with Domain Name Service (DNS) server");
				notif.setSeverity(Constants.SEV_MAJOR);
				break;
			case 22:// generic-HW-fail(22)
				notif.addAdditionalAttribute("Specific", "generic-HW-fail");
				notif.setEventType("5");// Equipment Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("335");// Power malfunction
				notif.setSpecificProblem("Hardware Fault");
				notif.setSeverity(Constants.SEV_CRITICAL);
				break;
			case 23:// lb-srv-down(23)
				notif.addAdditionalAttribute("Specific", "lb-srv-down");
				notif.setEventType("1");// Communiations Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("329"); // Loss of signal
				notif.setSpecificProblem("Unable to communicate with Load Balancing (LB) server");
				notif.setSeverity(Constants.SEV_MAJOR);
				break;
			case 24:// log-full(24)
				notif.addAdditionalAttribute("Specific", "log-full");
				notif.setEventType("2");// Processing Error Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("349"); // Storage cpacity problem
				notif.setSpecificProblem("Log file is full");
				notif.setSeverity(Constants.SEV_MAJOR);
				break;
			case 30:// cpu-usage-high(30)
				notif.addAdditionalAttribute("Specific", "cpu-usage-high");
				notif.setEventType("2");// Processing Error Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("351"); // Threshold crossed
				notif.setSpecificProblem("High load on Central Processing Unit (cpu)");
				notif.setSeverity(Constants.SEV_MAJOR);
				break;
			case 40:// VPN-tunnel-up(40)
					// "Virtual Private Network (VPN) tunnel is restored"
				notif.addAdditionalAttribute("Specific", "VPN-tunnel-up");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329"); // Loss of signal
				notif.setSpecificProblem("Virtual Private Network (VPN) tunnel is not available for use");
				notif.setSeverity(Constants.SEV_CLEARED);
				break;
			case 41:// VPN-tunnel-down(41)
				notif.addAdditionalAttribute("Specific", "VPN-tunnel-down");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");// Loss of signal
				notif.setSpecificProblem("Virtual Private Network (VPN) tunnel is not available for use");
				notif.setSeverity(Constants.SEV_MINOR);
				break;
			case 60:// nsrp-rto-down(60)
				notif.addAdditionalAttribute("Specific", "nsrp-rto-down");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("517");// Link Failure
				notif.setSpecificProblem("NSRP rto self unit status change");
				notif.setSeverity(Constants.SEV_CLEARED);
				break;
			case 61:// nsrp-rto-up(61)
				notif.addAdditionalAttribute("Specific", "nsrp-rto-up");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("517");// Link Failure
				notif.setSpecificProblem("NSRP rto self unit status change");
				notif.setSeverity(Constants.SEV_MAJOR);
				break;
			case 62:// nsrp-trackip-success(62)
				notif.addAdditionalAttribute("Specific", "nsrp-trackip-success");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("517");// Link Failure
				notif.setSpecificProblem("NSRP track ip failed");
				notif.setSeverity(Constants.SEV_CLEARED);
				break;
			case 63:// nsrp-trackip-failed(63)
				notif.addAdditionalAttribute("Specific", "nsrp-trackip-failed");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("517");// Link Failure
				notif.setSpecificProblem("NSRP track ip failed");
				notif.setSeverity(Constants.SEV_MAJOR);
				break;
			case 71:// nsrp-vsd-master(71)
				notif.addAdditionalAttribute("Specific", "nsrp-vsd-master");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("329");// Loss of signal
				notif.setSpecificProblem("NSRP vsd group status change to master");
				notif.setSeverity(Constants.SEV_MAJOR);
				break;
			case 76:// nsrp-vsd-2ndpath-request(76)
				notif.addAdditionalAttribute("Specific",
						"nsrp-vsd-2ndpath-request");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");// Loss of signal
				notif.setSpecificProblem("NSRP vsd ha link 2nd path request received");
				notif.setSeverity(Constants.SEV_MAJOR);
				break;
			case 77:// nsrp-vsd-2ndpath-reply(77)
				notif.addAdditionalAttribute("Specific",
						" nsrp-vsd-2ndpath-reply");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");// Loss of signal
				notif.setSpecificProblem("NSRP vsd ha link 2nd path request received");
				notif.setSeverity(Constants.SEV_CLEARED);
				break;
			case 209:// BGP is in downward transition(209)
				notif.addAdditionalAttribute("Specific",
						"BGP is in downward transition");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");// Loss of signal
				notif.setSpecificProblem("BGP is in downward transition");
				notif.setSeverity(Constants.SEV_CRITICAL);
				break;
			// case:this is clear alarm implementation for above alarm
			case 208:// BGP is in downward transition(209)
				notif.addAdditionalAttribute("Specific",
						"BGP is changed to Establish state");
				notif.setEventType("1");// Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");// Loss of signal
				notif.setSpecificProblem("BGP is in downward transition");
				notif.setSeverity(Constants.SEV_CLEARED);
				break;

			default:
				LOGGER.info("default specific type for the trap: "
						+ Integer.parseInt(Value.toString()));
				notif.addAdditionalAttribute("Specific", "no additional text");
				notif.setEventType("0");// Communications Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("0");// Loss of signal
				notif.setSpecificProblem("Unknown Alarm");
				notif.setSeverity(Constants.SEV_INDETERMINATE);
				notif.setSpecificProblem("None");
				LOGGER.info("trap to be dropped");
				notif = null;
				break;
			// end R3 alarms
			}

		} catch (Exception e) {
			LOGGER.info("exception ocuured" + e.getMessage());
			notif.addAdditionalAttribute("Specific", " null");
			notif.setEventType("0");// Unkown Event Type
			notif.setFmEventType("ERROR");
			notif.setProbableCause("0");// Indeterminate
			notif.setSpecificProblem("Unknown Alarm");
			notif.setSeverity(Constants.SEV_INDETERMINATE);
			notif = null;

		}
		return notif;
	}

}
