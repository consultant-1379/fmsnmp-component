package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;
import com.ericsson.oss.mediation.translator.model.HandleTimeTranslation;

public class GranMibHandling {
	
	// returns the array of IF MIB alarms notifications
	
	public SynchronizationEventNotification[] getGranMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int alarmListCount){
         int count=0;
         String s9 =null;
		SynchronizationEventNotification [] synchEventNotifications = null;
		int alarmTimeCount = snmpWalkMap.get("granALARM_TIME").countTokens();
		synchEventNotifications = new SynchronizationEventNotification[alarmTimeCount];
		for(int i = 0; i < alarmListCount; ++i)
		{
			
			synchEventNotifications[count] = new SynchronizationEventNotification(); //NO_ANALYSIS NOPMD
			synchEventNotifications[count].setFmEventType("ALARM");
			synchEventNotifications[count].addAdditionalAttribute("SNMPTrapOID", "");
			synchEventNotifications[count].addAdditionalAttribute("IPAddress", me.getIpAddress());
			synchEventNotifications[count].addAdditionalAttribute("Version", "V2C");
			synchEventNotifications[count].addAdditionalAttribute("Enterprise", "True");
			try{				 
				
				synchEventNotifications[count].setManagedObjectInstance(snmpWalkMap.get("granALARM_MANAGEDOBJECTINSTANCE").nextToken());
				synchEventNotifications[count].addAdditionalAttribute(
						"additionalText",snmpWalkMap.get("granALARM_ATEXT").nextToken());
				synchEventNotifications[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(s9))).toString());
				synchEventNotifications[count].setTimeZone(HandleTimeTranslation.getEventTimeZone(s9));
				synchEventNotifications[count].setEventType(snmpWalkMap.get("granALARM_TYPE").nextToken());
				synchEventNotifications[count].setProbableCause(snmpWalkMap.get("granALARM_PC").nextToken());
				String severity=snmpWalkMap.get("granALARM_PS").nextToken();
				String granSeverity=HandleAlarmSeverity.getPerceivedSeverity(severity);
				synchEventNotifications[count].setSeverity(granSeverity);
				synchEventNotifications[count].setSpecificProblem(snmpWalkMap.get("granALARM_SP").nextToken());
				
			}catch(final NoSuchElementException nosuchelementexceptione){
				synchEventNotifications[count].setManagedObjectInstance(snmpWalkMap.get("granALARM_MANAGEDOBJECTINSTANCE").nextToken());
				System.out.println("nosuchelementexceptione:"+nosuchelementexceptione.getMessage());
			
				System.out.println("nosuchelementexceptione:"+nosuchelementexceptione.getMessage());
				synchEventNotifications[count].addAdditionalAttribute("additionalText",
						"none");
				synchEventNotifications[count].setEventType("0");
				synchEventNotifications[count].setProbableCause("0");
				synchEventNotifications[count].setSeverity("INDETERMINATE");
				synchEventNotifications[count].setSpecificProblem("none");
			}			
			
			catch(final Exception e){
				  s9 = snmpWalkMap.get("granALARM_TIME").nextToken();
				synchEventNotifications[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
				synchEventNotifications[count].setTimeZone(HandleTimeTranslation.getTimeZone(s9));
			}
			
		}
		
	    return synchEventNotifications;
	}

}
