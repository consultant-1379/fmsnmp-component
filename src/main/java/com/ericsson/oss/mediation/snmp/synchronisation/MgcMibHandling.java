package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.*;

public class MgcMibHandling {
	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getMgcMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int mgcCount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[mgcCount];
		
		for (int mgcIndex = 0;mgcIndex < mgcCount;mgcIndex++){
			// set the attributes to the notification object array

			notif[count] = new SynchronizationEventNotification();

			// Set the type of the alarm
			notif[count].setFmEventType("ALARM");


			//insert mgcAlarm_Index
			if(snmpWalkMap.get("mgcAlarm_Index").hasMoreTokens()){
				String myAlIndex = snmpWalkMap.get("mgcAlarm_Index").nextToken();
				if ( myAlIndex != null){
					notif[count].setExternalEventId(myAlIndex);
				}else{
					System.out.println(" MGC alarm Index is null ");
				}

			}

			//insert mgcAlarm_Time 
			if(snmpWalkMap.get("mgcAlarm_Time").hasMoreTokens()){
				String time =snmpWalkMap.get("mgcAlarm_Time").nextToken();
				System.out.println("MGCActionHandler::Time in table is : " +time.toString()); 	

				try{
					if (time.length() != 32)
					{
						System.out.println("If the time is not in 11 octet then setting the time to local current time");
						notif[count].setTimeZone("UTC");
						notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
					}
					else
					{ 
						if(time.indexOf("-") != -1)//event
						{
							System.out.println(" Using Event Time ");
							notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(time))).toString());
							notif[count].setTimeZone(HandleTimeTranslation.getEventTimeZone(time));
						}
						else //alarm
						{
							System.out.println(" Using Alarm Time ");
							notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(time))).toString());
							notif[count].setTimeZone(HandleTimeTranslation.getTimeZone(time));
						}
					}	
				}catch(Exception ex){
					System.out.println(" Exception in getting the time zone and time attribute Hence setting the time zone to UTC and time to current time in synchronization");
					notif[count].setTimeZone("UTC");
					notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
				}			

			}else{
				notif[count].setTimeZone("UTC");
				notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());

			}

			//insert mgcAlarm_Name
			if(snmpWalkMap.get("mgcAlarm_Name").hasMoreTokens()){
				String myAlName = snmpWalkMap.get("mgcAlarm_Name").nextToken();
				if (myAlName != null){
					notif[count].addAdditionalAttribute("AlarmName", myAlName);
				}else{
					System.out.println(" MGC Alarm Name is null ");
				}

			}

			//insert  mgcAlarm_ObjType
			if(snmpWalkMap.get("mgcAlarm_ObjType").hasMoreTokens()){		

				String myMOC = snmpWalkMap.get("mgcAlarm_ObjType").nextToken();
				if(myMOC != null ){			  
					System.out.println(" ManagedObjectClass setting ");
					notif[count].addAdditionalAttribute("ManagedObjectClass",
							myMOC);
				}else{
					System.out.println(" MGC MOC is null ");
				}

			}

			//insert  mgcAlarm_MoInst
			if( snmpWalkMap.get("mgcAlarm_MoInst").hasMoreTokens()){
				String myMOI =  snmpWalkMap.get("mgcAlarm_MoInst").nextToken();
				if (myMOI != null){
					System.out.println(" MGC MOI = "+myMOI);
					notif[count].setManagedObjectInstance(myMOI);
				}else{
					System.out.println(" MGC MOI not defined ");
				}			
			}

			//insert  mgcAlarm_Severity
			if(snmpWalkMap.get("mgcAlarm_Severity").hasMoreTokens()){
				try{
					System.out.println("mgcAlarm_Severity is"+snmpWalkMap.get("mgcAlarm_Severity").nextToken());
					notif[count].setSeverity(HandleAlarmSeverity.getEFWSAlarmSeverity2(snmpWalkMap.get("mgcAlarm_Severity").nextToken()));

				}catch(Exception ex){
					ex.printStackTrace();
					System.out.println(" MGC Severity set to indeterminate ");
					notif[count].setSeverity("INDETERMINATE");

				}

			}else{
				System.out.println(" No MGC Severity tokens ");
				notif[count].setSeverity("INDETERMINATE");

			}

			//insert  mgcAlarm_PC
			if( snmpWalkMap.get("mgcAlarm_PC").hasMoreTokens()){
				String myPC =   snmpWalkMap.get("mgcAlarm_PC").nextToken();
				if ( myPC != null ){
					System.out.println(" MGC PC = "+myPC);
					notif[count].setProbableCause( myPC);
				}else{
					System.out.println(" MCG PC is null ");
					notif[count].setProbableCause("0");  
				}

			}else{
				System.out.println(" No MGC PC tokens ");
				notif[count].setProbableCause("0");

			}

			//insert  mgcAlarm_AddText
			if( snmpWalkMap.get("mgcAlarm_AddText").hasMoreTokens()){
				String addAttr =  snmpWalkMap.get("mgcAlarm_AddText").nextToken();
				if (addAttr != null){			    
					notif[count].addAdditionalAttribute( "additionalText",addAttr); 
					notif[count].addAdditionalAttribute("AdditionalText",addAttr);
				}else{
					System.out.println(" Additional attribute is null ");
				}
			}

			//insert  mgcAlarm_SP
			if(snmpWalkMap.get("mgcAlarm_SP").hasMoreTokens()){
				String mySP = snmpWalkMap.get("mgcAlarm_SP").nextToken();
				if ( mySP != null){
					notif[count].setSpecificProblem(mySP);			
				}else{
					System.out.println(" MGC SpecificProblem is NULL ");
					notif[count].setSpecificProblem("None");
				}
			}else{
				System.out.println(" No MGC Specific Problem available ");
				notif[count].setSpecificProblem("None");
			}



			//insert  mgcAlarm_Type				    
			if(snmpWalkMap.get("mgcAlarm_Type").hasMoreTokens()){
				try{
					System.out.println("mgcAlarm_Type:"+snmpWalkMap.get("mgcAlarm_Type").nextToken().trim());
					notif[count].setEventType(HandleEventType.getmgcEventType(snmpWalkMap.get("mgcAlarm_Type").nextToken().trim()));

				}
				catch(Exception ex){
					ex.printStackTrace();
					notif[count].setEventType("0");//Unknown Event type
				}
			}else{
				System.out.println(" No Event Type Tokens present ");
				notif[count].setEventType("0");//Unknown Event type
			}	


			// Increment the count
			count++;
		}

	
		
		return notif;

}
}