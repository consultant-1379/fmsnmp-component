package com.ericsson.oss.mediation.translator.model.alarm.handlers;

public class NetTraAlarmPSwith5 {

	private NetTraAlarmPSwith5() {
	}

	// netraalarmps and NETRA_Alarm_Severity==5
	public static String[] getnetraalarmpswith5(final String Value,
			final int Alarm_Severity) {
		String netraspecificproblm = "null";
		final StringBuffer problemTextString = new StringBuffer();
		final String spec_problem = Value.toString();

		if (spec_problem.equalsIgnoreCase("ASDNS Node UP")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "ASDNS Node is down";
		} else if (spec_problem.equalsIgnoreCase("ENUM Server Cluster up")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "NDB Connection to a MySQL cluster is down. Please check the MySQL Cluster configuration and possible HW resources.";
		} else if (spec_problem
				.equalsIgnoreCase("ENUM Server Cluster Available")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "NDB Connections to the all MySQL clusters are down. Please check the MySQL Cluster configuration and possible HW resources";
		} else if (spec_problem
				.equalsIgnoreCase("Backup process is completed.")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "Operator initiated a Backup Process.";
		} else if (spec_problem
				.equalsIgnoreCase("Restore process is completed")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "Restore process started. Configuration will be restored from specified backup.";
		} else if (spec_problem
				.equalsIgnoreCase("Sun Cluster returns to normal state successfully")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "Not all resources, resource groups, device groups, cluster nodes and transport paths are online";
		} else if (spec_problem
				.equalsIgnoreCase("Disk Array connection returns to normal state successfully")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "The connection to external disk array may be broken";
		} else if (spec_problem
				.equalsIgnoreCase("SNMP Master agent Cold Started")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "SNMP Master agent Down";
		}

		// /1.ipworksDnsServShutDown
		else if (spec_problem
				.equalsIgnoreCase("DNS Server Shutdown on operator request or caused by serious error,Check logs for the cause of the problem.")) {
			netraspecificproblm = "DNS Server is down. Please check the Problem Text for more information";
			problemTextString
					.append("DNS Server Shutdown on operator request or caused by serious error,Check logs for the cause of the problem.");
			problemTextString.append("\n");
		}

		else if (spec_problem
				.equalsIgnoreCase("DNS Server Shutdown on operator request or caused by serious error,Check logs for the cause of the problem")) {
			netraspecificproblm = "DNS Server is down. Please check the Problem Text for more information";
			problemTextString
					.append("DNS Server Shutdown on operator request or caused by serious error,Check logs for the cause of the problem");
			problemTextString.append("\n");
		}
		// /2.ipworksDnsServFatalError
		else if (spec_problem
				.equalsIgnoreCase("DNS Server Failed to start due to configuration or customization problem. Please check the configuration attributes")) {
			netraspecificproblm = "DNS Server is down. Please check the Problem Text for more information";
			problemTextString
					.append("DNS Server Failed to start due to configuration or customization problem. Please check the configuration attributes\n");
		}

		// /3.ipworksDnsServStart Clear alarm for the Above two alarms
		else if (spec_problem
				.equalsIgnoreCase("DNS Server Started successfully")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "DNS Server is down. Please check the Problem Text for more information";
			problemTextString.append("DNS Server Started successfully\n");
		}

		// 4.ipworksEmSSShutDown
		else if (spec_problem
				.equalsIgnoreCase("Storage Server stopped on operator request or caused by serious error. Check logs for the cause of the problem.")) {
			netraspecificproblm = "EmS Server is down. Please check the Problem Text for more information";
			problemTextString
					.append("Storage Server stopped on operator request or caused by serious error. Check logs for the cause of the problem.");
			problemTextString.append("\n");
		} else if (spec_problem
				.equalsIgnoreCase("Storage Server stopped on operator request or caused by serious error. Check logs for the cause of the problem")) {
			netraspecificproblm = "EmS Server is down. Please check the Problem Text for more information";
			problemTextString
					.append("Storage Server stopped on operator request or caused by serious error. Check logs for the cause of the problem");
			problemTextString.append("\n");
		}

		// 5.ipworksEmSSNotReachable

		else if (spec_problem
				.equalsIgnoreCase("Storage Server is not reachable by Server Manager.")) {
			netraspecificproblm = "EmS Server is down. Please check the Problem Text for more information";
			problemTextString
					.append("Storage Server is not reachable by Server Manager.");
			problemTextString.append("\n");
		}
		// Below is the clearing record for ipworksEmSSNotReachable and
		// ipworksEmSSShutDown alarm
		// 6.ipworksEmSSStart and ipworksEmSSReachable
		else if (spec_problem.equalsIgnoreCase("Storage Server Started")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "EmS Server is down. Please check the Problem Text for more information";
			problemTextString.append("Storage Server Started");
			problemTextString.append("\n");
		}

		// 7.ipworksEnumServShutDown

		else if (spec_problem
				.equalsIgnoreCase("ENUM Server Shutdown on operator request or caused by serious error. Check logs for the cause of the problem.")) {
			netraspecificproblm = "ENUM Server is down. Please check the Problem Text for more information";
			problemTextString
					.append("ENUM Server Shutdown on operator request or caused by serious error. Check logs for the cause of the problem.");
			problemTextString.append("\n");
		}
		// 8.ipworksEnumServFatalError
		else if (spec_problem
				.equalsIgnoreCase("The ENUM server was unable to start because the initialization process encountered a non-recoverable error.")) {
			netraspecificproblm = "ENUM Server is down. Please check the Problem Text for more information";
			problemTextString
					.append("The ENUM server was unable to start because the initialization process encountered a non-recoverable error.");
			problemTextString.append("\n");
		}
		// Below alarm is clearing trap for the ipworksEnumServFatalError and
		// ipworksEnumServShutDown
		// 9.ipworksEnumServStart
		else if (spec_problem
				.equalsIgnoreCase("ENUM Server Started successfully.")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "ENUM Server is down. Please check the Problem Text for more information";
			problemTextString.append("ENUM Server Started successfully.");
			problemTextString.append("\n");
		}

		// 10.ipworksDiskArrayNeedMaintenance
		else if (spec_problem.equalsIgnoreCase("Disk Array needs maintenance")) {
			netraspecificproblm = "Disk Array problem. Please check the Problem Text for more information";
			problemTextString.append("Disk Array needs maintenance");
			problemTextString.append("\n");
		}

		// 11.ipworksDiskArrayLastErred
		else if (spec_problem
				.equalsIgnoreCase("Disk Array go into the state of last error")) {
			netraspecificproblm = "Disk Array problem. Please check the Problem Text for more information";
			problemTextString
					.append("Disk Array go into the state of last error");
			problemTextString.append("\n");
		}

		// 12.ipworksDiskArrayUnavailable
		else if (spec_problem
				.equalsIgnoreCase("A device cannot be accessed, but has not incurred errors.")) {
			netraspecificproblm = "Disk Array problem. Please check the Problem Text for more information";
			problemTextString
					.append("A device cannot be accessed, but has not incurred errors.");
			problemTextString.append("\n");
		}

		// Below alarm is clearing alarm for the ipworksDiskArrayLastErred
		// ipworksDiskArrayUnavailable and ipworksDiskArrayLastErred
		// 13.ipworksDiskArrayOk
		else if (spec_problem
				.equalsIgnoreCase("Disk Array returns to normal state successfully")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "Disk Array problem. Please check the Problem Text for more information";
			problemTextString
					.append("Disk Array returns to normal state successfully");
			problemTextString.append("\n");
		}

		// /Link Up Alarm
		else if (spec_problem
				.equalsIgnoreCase("linkUp,communication link restored")
				&& Alarm_Severity == 5) {
			netraspecificproblm = "linkDown,communication link failure";
		} else {
			netraspecificproblm = "no specific problem";
		}
		String[] returnValue = new String[2];
		returnValue[0] = netraspecificproblm;
		returnValue[1] = problemTextString.toString();
		return returnValue;
	}
}
