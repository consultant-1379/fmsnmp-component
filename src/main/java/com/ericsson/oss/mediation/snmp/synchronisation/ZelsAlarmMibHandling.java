package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.*;

public class ZelsAlarmMibHandling {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(ZelsAlarmMibHandling.class);

	// returns the array of IF MIB alarms notifications

	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getzelsMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int zelsalarmcount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[zelsalarmcount];


		for(int cnt = 0; cnt < zelsalarmcount; cnt++)
		{

			LOGGER.info("Zels syncAlarm >>" + zelsalarmcount);
			LOGGER.info("COUNT [" +cnt +"]" );
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");
			notif[count].addAdditionalAttribute("Generic","False");


			//Processing zelsAlarmNumber
			String a_nr="";
			try
			{
				a_nr = snmpWalkMap.get("ALARM_ID").nextToken();
				notif[count].setManagedObjectInstance(a_nr);
				LOGGER.info("alarmId = "+a_nr);
				String eventType=HandleEventType.getzelsAlarmNumberEventType(a_nr);
				notif[count].setEventType(eventType);

			}
			catch(NoSuchElementException e)
			{
				a_nr = "No OOR in Alarm.";
				notif[count].setManagedObjectInstance(a_nr);
				LOGGER.info("alarmId = "+a_nr);
			}


			//Processing zelsObjectClassOfReference 
			String mo_text="";
			try
			{
				mo_text =  snmpWalkMap.get("ALARM_OBJECT_CLASS").nextToken();
				LOGGER.info("alarmClassRef = "+mo_text);
				notif[count].addAdditionalAttribute("ZelsObjectClass",mo_text);
			}
			catch(Exception e)
			{
				mo_text = "No Object Class Reference.";
				notif[count].addAdditionalAttribute("ZelsObjectClass",mo_text);
				LOGGER.info("alarmClassRef = "+mo_text);
			}

			//Processing zelsObjectOfReference 
			String mo_Inst = "";
			try
			{
				mo_Inst =snmpWalkMap.get("ALARM_OBJECT_REF") .nextToken();
				LOGGER.info("alarmObjectRef = "+mo_Inst);
				notif[count].addAdditionalAttribute("ZelsObjectReference",mo_Inst);
			}
			catch(Exception e)
			{
				mo_Inst = "No Object Reference.";
				notif[count].addAdditionalAttribute("ZelsObjectReference",mo_Inst);
				LOGGER.info("ZelsObjectReference = "+mo_Inst); 
			}

			//Processing Proposed repair action
			String pra= "";
			try
			{
				pra= snmpWalkMap.get("ALARM_PRA").nextToken();
				notif[count].addAdditionalAttribute("zelsProposedRepairAction",pra);
				LOGGER.info("zelsProposedRepairAction = "+pra);

			}
			catch(Exception e)
			{
				pra = "No proposed Repair"; 
				notif[count].addAdditionalAttribute("zelsProposedRepairAction",pra);                          
				LOGGER.info("zelsProposedRepairAction = "+pra);
			}

			//Processing Alarm Time

			/*
			NOTE:
			Alarms contain time in hex format:  07 D3 01 12 0D 1E 1E 00 2D 04 00
			Events contain time in this format: 2003-01-18;13:30:30.00:-4:00
			 */
			String Time="";
			try
			{

				Time = snmpWalkMap.get("ALARM_TIME").nextToken();
				LOGGER.info("Zels Time in table is : " +Time.toString());
				if(Time.indexOf("-") != -1)//event
				{
					String Timezone = null;

					try {
						Timezone = HandleTimeTranslation.getEventTimeZone(Time);
						LOGGER.info("Zels TimeZone is: " +Timezone);
					}catch (Exception ex)
					{
						LOGGER.info("Zels Date Format Exception: getEventTimeZone!");
					}

					LOGGER.info("Time is" +HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time)));
					notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time))).toString());

					notif[count].setTimeZone(Timezone);
				}

				else //alarm
				{
					String Timezone = null;
					
						if( HandleTimeTranslation.getTimeZone(Time) !=null)
						{
							Timezone = HandleTimeTranslation.getTimeZone(Time);
							LOGGER.info("Zels TimeZone is: " +Timezone);
						}
						else
							Timezone = new String ("UTC");	
											

					LOGGER.info("Time is" +HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time)));
					notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time))).toString());
					notif[count].setTimeZone(Timezone);

				}
			}
			catch(Exception ex)
			{
				LOGGER.info("Date Format Exception: " + Time.toString());
				notif[count].setTimeZone("UTC");
				notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
				LOGGER.info("Zels setTime(): "+HandleTimeTranslation.getCurrentTime());

			}


			//Processing AlarmText
			String a_text="";
			try
			{
				a_text = snmpWalkMap.get("ALARM_ATEXT").nextToken();

				notif[count].addAdditionalAttribute("additionalText", a_text);

				LOGGER.info("alarmText = "+a_text);
			}
			catch(Exception e)
			{
				a_text = "No Additional Text in Alarm.";
				notif[count].addAdditionalAttribute( "additionalText", a_text );
				LOGGER.info("alarmText = "+a_text);
			}  

			//Processing Proposed repair action 

			//Processing Alarm Probable Cause
			String probablecause="";
			try
			{
				if(snmpWalkMap.get("ALARM_PC").hasMoreTokens())
				{
					probablecause =snmpWalkMap.get("ALARM_PC").nextToken();
					LOGGER.info("Ericsson Alarm ZELS Probable Cause = " + probablecause );
					String pc=HandleProbableCause.getAlarmProbabaleCause(probablecause);
					notif[count].setProbableCause(pc);
				}            
			}
			catch(Exception e)
			{
				notif[count].setProbableCause("0"); //Indeterminate
				LOGGER.info("Exception in processing alarmPC = Indeterminate ");
			}

			//Processing Alarm Perceived Severity
			String tempzelsPS = "";
			try
			{
				tempzelsPS = snmpWalkMap.get("ALARM_PS").nextToken();
				LOGGER.info("alarmPS = "+tempzelsPS);
				String severity=HandleAlarmSeverity.getPerceivedSeverity(tempzelsPS);
				notif[count].setSeverity(severity);

			}
			catch(Exception e)
			{
				notif[count].setSeverity("INDETERMINATE");
				LOGGER.info("Exception while processing alarmPS = "+tempzelsPS);
			}

			//Processing Alarm Specific Problem
			try
			{
				if(snmpWalkMap.get("ALARM_SP").hasMoreTokens())
				{
					String tempzelsSP = (snmpWalkMap.get("ALARM_SP").nextToken());
					LOGGER.info("Ericsson Alarm ZELS Specific Problem = " + tempzelsSP );
					notif[count].setSpecificProblem(tempzelsSP);
				}
				else
				{
					notif[count].setSpecificProblem("None");
				}
			}
			catch(Exception e)
			{
				notif[count].setSpecificProblem("None");
				LOGGER.info("Exception in processing alarmSP");
			}

			count ++;
		}
	
     return notif;
	}
}
