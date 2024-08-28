package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;
import com.ericsson.oss.mediation.translator.model.HandleProbableCause;

public class SunPlatformMibHandling {
	public SynchronizationEventNotification[] getSunPlatformMibAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int sunplatformmibcount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[sunplatformmibcount];
		for(int suncount = 0; suncount < sunplatformmibcount ;suncount++)
		{
			System.out.println("************************* Count =  " +count);
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");


			// Setting Perceived Severity of the Alarm v=getBSSeverity
			try
			{

				System.out.println("Setting Perceived Severity values");
				String sunPlatLogRecordAlarmPerceivedSeverity=snmpWalkMap.get("sunPlatLogRecordAlarmPerceivedSeverity").nextToken();
				System.out.println("sunPlatLogRecordAlarmPerceivedSeverity is"+sunPlatLogRecordAlarmPerceivedSeverity);
				notif[count].setSeverity(HandleAlarmSeverity.getPerceivedSeverity(sunPlatLogRecordAlarmPerceivedSeverity));

			}
			catch(NoSuchElementException e)
			{
				// Perceived Severity will be set to INDETERMINATE If not found in the Alarm Table
				notif[count].setSeverity("INDETERMINATE");
			}



			// Setting the Probable Cause values
			try
			{

				int probableCause = 0;
				probableCause =Integer.parseInt(snmpWalkMap.get("sunPlatLogRecordAlarmProbableCause").nextToken());
				notif[count].setProbableCause(new Integer(probableCause).toString());
				System.out.println("Probable cause for SUN PLATFORM Alarm is" + probableCause);
				// Setting up the Event Type for Alarms based on fetched Probable Cause values
				notif[count].setEventType(HandleProbableCause.getEventTypefrmProbableCause(probableCause));


			}
			catch(NoSuchElementException e)
			{
				notif[count].setEventType("0");
			}




			// Setting Specific Problem Values
			String tempSP = "";
			StringBuffer str = new StringBuffer();
			try
			{

				tempSP = snmpWalkMap.get("sunPlatLogRecordAlarmSpecificProblem").nextToken();
				notif[count].setSpecificProblem("SUN Platform Alarm");
				/*notif[count].addAdditionalAttribute("Additional Text",tempSP);*/

				System.out.println("Specific Problem for SUNPLATFORM MIB is "+tempSP);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nAddInfo:").append("\nAlarm Name=").append(tempSP).toString());
			}
			catch(Exception e)
			{
				notif[count].setSpecificProblem("No Specific Problem in Alarm");

			}




			// Setting Proposed Repair Action values
			String tempPRA = "";
			try
			{

				tempPRA = snmpWalkMap.get("sunPlatLogRecordAlarmRepairAction").nextToken();
				/*notif[count].addAdditionalAttribute("Proposed Repair Action",tempPRA);*/
				System.out.println("Proposed Repair Action for SUNPLATFORM MIB is "+tempPRA);
				notif[count].addAdditionalAttribute("additionalText", str.append("\n").append(tempPRA).toString());
			}
			catch(Exception e)
			{
				String tempPRAC="Repair Action Unknown";
				notif[count].addAdditionalAttribute("Additional Text",tempPRAC);

			}
			//Increment the count
			count++;

		}

	
		return notif;
	}

}
