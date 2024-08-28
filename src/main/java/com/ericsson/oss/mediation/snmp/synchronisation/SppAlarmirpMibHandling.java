package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;
import com.ericsson.oss.mediation.translator.model.HandleTimeTranslation;

public class SppAlarmirpMibHandling {
	

	// returns the array of IF MIB alarms notifications

	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getericsogMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int sppalarmirpcount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[sppalarmirpcount];
		

		for(int sppcnt = 0; count < sppalarmirpcount ;sppcnt++)
		{
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM" );
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");
			notif[count].addAdditionalAttribute("Generic","False");
			String a_nr="";
			try
			{
				a_nr = snmpWalkMap.get("ALARM_ID").nextToken();
				notif[count].setExternalEventId(a_nr);
			}catch(NoSuchElementException e)
			{
				a_nr = "No External Event ID in Alarm.";
				notif[count].setExternalEventId(a_nr);
			}
			String mo_text="";
			try
			{
				mo_text =  snmpWalkMap.get("ALARM_MO_CLASS").nextToken();
				notif[count].addAdditionalAttribute("MO Class",mo_text);
			}catch(NoSuchElementException e)
			{
				mo_text = "No Managed Object Instance.";
				notif[count].addAdditionalAttribute("MO Class",mo_text);
			}
			String a_text="";
			try
			{
				a_text = snmpWalkMap.get("ALARM_ATEXT").nextToken();
				notif[count].addAdditionalAttribute("Additional Text",a_text);

			}catch(NoSuchElementException e)
			{
				a_text = "No Additional Text in Alarm.";
				notif[count].addAdditionalAttribute("Additional Text",a_text);

			}
			String Time="";
			try
			{
				/*
                        NOTE:
                        Alarms contain time in hex format:  07 D3 01 12 0D 1E 1E 00 2D 04 00
                        Events contain time in this format: 2003-01-18;13:30:30.00:-4:00
				 */
				Time = snmpWalkMap.get("ALARM_TIME").nextToken();
				System.out.println("IrpActionHandler::Time in table is " +Time.toString());

				if(Time.indexOf("-") != -1)//event
				{
					String Timezone = null;
					try
					{
						Timezone = HandleTimeTranslation.getEventTimeZone(Time);
						System.out.println("Spp Timezone is: " +Timezone);
					}
					catch(Exception e)
					{
						System.out.println("Spp getEventTimeZone!");
						notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
						System.out.println("Spp setTime(): "+HandleTimeTranslation.getCurrentTime());
						//notif[count].setTimeZone("UTC");

					}
					System.out.println("Time is " +HandleTimeTranslation. _timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time)));	
					notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time))).toString());
					notif[count].setTimeZone(Timezone);
				}
				else //alarm
				{
					String Timezone = null;
					try
					{
						Timezone = HandleTimeTranslation.getTimeZone(Time);
						System.out.println("Spp Timezone is: " +Timezone);
					}
					catch(Exception e)
					{
						System.out.println("Sp Date Format Exception : getEventTimeZone!");
						//	Timezone = new String("UTC");
						notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
						System.out.println("Spp setTime(): "+HandleTimeTranslation.getCurrentTime());
						//notif[count].setTimeZone("UTC");

						System.out.println("Timezone being set to UTC as Exception occurred");
					}

					System.out.println("Time is " +HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time)));
					notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time))).toString());
					notif[count].setTimeZone(Timezone);
				}
			}
			catch(Exception ex)
			{
				System.out.println("Date Format Exception: " + Time.toString());
				notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
				System.out.println("SppActionHandler:synchronize():setTime(): "+HandleTimeTranslation.getCurrentTime());
				//notif[count].setTimeZone("UTC");
				System.out.println("Error setting timezone...Time = "+Time+" Exception: " +ex.getMessage());
			}
			try
			{
				switch(Integer.parseInt(snmpWalkMap.get("ALARM_TYPE").nextToken()))
				{
				case 0:	//unknown
					notif[count].setEventType("0");
					break;

				case 2: //Communications Alarm
					notif[count].setEventType("2");
					break;

				case 3: //Environmental Alarm
					notif[count].setEventType("3");
					break;

				case 4: //Equipment Alarm
					notif[count].setEventType("4");
					break;

				case 10://Processing Error Alarm
					notif[count].setEventType("10");
					break;

				case 11://Quality of Service Alarm
					notif[count].setEventType("11");
					break;

				case 15://Integrity Violation
					notif[count].setEventType("15");
					break;

				case 16://Operational Violation
					notif[count].setEventType("16");
					break;

				case 17://Physical Violation
					notif[count].setEventType("17");
					break;

				case 18://Security Service Violation
					notif[count].setEventType("18");
					break;

				case 19://Time Domain Violation
					notif[count].setEventType("19");
					break;

				default:
					notif[count].setEventType("0");
					break;



				}
			}catch(NoSuchElementException e)
			{
				notif[count].setEventType("0");
				System.out.println("Event Type sent: " +(Integer.parseInt(snmpWalkMap.get("ALARM_TYPE").nextToken())));
			}

			try
			{
				int probableCause = 0;
				probableCause =  Integer.parseInt(snmpWalkMap.get("ALARM_PC").nextToken());
				notif[count].setProbableCause(new Integer(probableCause).toString());
			}catch(NoSuchElementException e)
			{
				notif[count].setProbableCause("0");

			}
			try
			{
				notif[count].setSeverity(HandleAlarmSeverity.getPerceivedSeverity(snmpWalkMap.get("ALARM_PS").nextToken()));

			}
			catch(NoSuchElementException e)
			{
				notif[count].setSeverity("INDETERMINATE");

			}
			try
			{
				notif[count].setSpecificProblem( snmpWalkMap.get("ALARM_SP").nextToken() );
			}
			catch(NoSuchElementException e)
			{
				notif[count].setSpecificProblem("No Specific Problem in Alarm.");
			}
			System.out.println("SppActionHandler::syncAlarmIrp() >> Generated SyncAlarm for alarmId: " + a_nr + " - " + a_text + " - " + mo_text);
		}


	return notif;
	}

}
