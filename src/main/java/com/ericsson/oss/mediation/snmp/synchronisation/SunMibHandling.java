package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;

public class SunMibHandling {
	


	public SynchronizationEventNotification[] getSunMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int sunCount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[sunCount];


		for( int sunIndex = 0;sunIndex < sunCount;++sunIndex){

			// Set the attributes to the notification object array
			notif[count] = new SynchronizationEventNotification();

			// Set the type of the alarm
			notif[count].setFmEventType("ALARM");

			try
			{	
				System.out.println("sunPlatLogRecordAlarmPerceivedSeverity is"+snmpWalkMap.get("sunPlatLogRecordAlarmPerceivedSeverity ").nextToken());
				notif[count].setSeverity(HandleAlarmSeverity.getEFWSAlarmSeverity2(snmpWalkMap.get("sunPlatLogRecordAlarmPerceivedSeverity ").nextToken()));

			}
			catch(NoSuchElementException e)
			{
				// Perceived Severity will be set to INDETERMINATE If not found in the Alarm Table
				notif[count].setSeverity("INDETERMINATE");
			} 


			//Setting the Probable Cause values
			try
			{
				// The event type is set according to value present in 
				// MGC Translate MAP
				int probableCause = 0;
				probableCause =Integer.parseInt(snmpWalkMap.get("sunPlatLogRecordAlarmProbableCause ").nextToken());
				notif[count].setProbableCause(new Integer(probableCause).toString());
				System.out.println("Probable cause for SUN PLATFORM Alarm is" + probableCause);
				// Setting up the Event Type for Alarms based on fetched Probable Cause values
				if ((probableCause >=0) && (probableCause <=26))
				{
					notif[count].setEventType("2"); //Communication Alarm
				}
				else if((probableCause >26) && (probableCause <=82))
				{
					notif[count].setEventType("4"); //Equipment Alarm
				}
				else if((probableCause >82) && (probableCause <=136))
				{
					notif[count].setEventType("3"); //Environmental Alarm
				}
				else if((probableCause >137) && (probableCause <=167))
				{
					notif[count].setEventType("10"); //Processing Error Alarm
				}
				else if((probableCause >167) && (probableCause <=207))
				{
					notif[count].setEventType("2"); //Communication Alarm
				}
				else if((probableCause >207) && (probableCause <=256))
				{
					notif[count].setEventType("4"); //Equipment Alarm
				}
				else if((probableCause >256) && (probableCause <=259))
				{
					notif[count].setEventType("2"); //Communication Alarm
				}
				else if((probableCause >259) && (probableCause <=261))
				{
					notif[count].setEventType("10"); //Processing Error Alarm
				}
				else if((probableCause >261) && (probableCause <=276))
				{
					notif[count].setEventType("11"); //Quality of Service Alarm
				}
				else
				{
					notif[count].setEventType("0"); //Unknown Event Type
				}
			}
			catch(NoSuchElementException e)
			{
				notif[count].setEventType("0");
			}

			// Setting Specific Problem Values
			String tempSP = "";
			StringBuffer str = new StringBuffer("");
			try
			{
				tempSP = snmpWalkMap.get("sunPlatLogRecordAlarmSpecificProblem ").nextToken();
				notif[count].setSpecificProblem("SUN Platform Alarm");
				System.out.println("Specific Problem for SUNPLATFORM MIB is "+tempSP);
				notif[count].addAdditionalAttribute("additionalText", str.append("\nAddInfo:").append("\nAlarm Name=").append(tempSP).toString());
			}
			catch(Exception e)
			{
				notif[count].setSpecificProblem("No Specific Problem in Alarm");
			}


			// Setting Proposed Repair Action values
			String tempPRA = "";
			try
			{
				tempPRA = snmpWalkMap.get("sunPlatLogRecordAlarmRepairAction ").nextToken();
				System.out.println("Proposed Repair Action for SUNPLATFORM MIB is "+tempPRA);
				notif[count].addAdditionalAttribute("additionalText", str.append("\n").append("Repair Action: ").append(tempPRA).toString());
			}
			catch(Exception e)
			{
				String tempPRAC="Repair Action Unknown";
				notif[count].addAdditionalAttribute("additionalText",tempPRAC);
			}

			//Increment the sun count
			count++;
		}
	
         return notif;
	}
}
