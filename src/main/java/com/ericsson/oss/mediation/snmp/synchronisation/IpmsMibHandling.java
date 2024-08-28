package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.*;

public class IpmsMibHandling {
	private static final Logger LOGGER = LoggerFactory.getLogger(IpmsMibHandling.class);
	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getIpmsMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int ipmsalarmcount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[ipmsalarmcount];
		for(int x = 0; x < ipmsalarmcount; x++)
		{
			System.out.println("IPMS" + ipmsalarmcount);
			System.out.println("COUNT [" +count +"]" );

			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			//notif[count].addAdditionalAttribute("IPAddress",ipaddress);
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");
			notif[count].addAdditionalAttribute("Generic","False");

			String mo_text="";
			String mo_inst="";
			String Time="";
			String a_text="";
			try
			{
				mo_text =  snmpWalkMap.get("ipmsalarmMOClass").nextToken();
				System.out.println("alarmMOClass = "+mo_text);
				notif[count].addAdditionalAttribute("managedObjectClass",mo_text);
			}
			catch(Exception e)
			{
				mo_text = "No Managed Object Class.";
				notif[count].addAdditionalAttribute("managedObjectClass",mo_text);
				System.out.println("IPMSActionHandler: Exception in alarmMOClass = "+mo_text);
			}

			try
			{
				mo_inst = snmpWalkMap.get("ipmsalarmMOInst").nextToken();

				notif[count].addAdditionalAttribute("Equipment",mo_text.concat(" ,"+mo_inst));
				notif[count].setManagedObjectInstance(mo_inst);
				System.out.println("alarmMOInst = "+mo_inst);
			}
			catch(Exception e)
			{
				mo_inst = "No Managed Object Inst.";

				notif[count].addAdditionalAttribute("Equipment",mo_text.concat(" ,"+mo_inst));
				notif[count].setManagedObjectInstance(mo_inst);
				System.out.println("IPMSActionHandler: Exception in alarmMOInst = "+mo_inst);
			}

			try
			{
				Time = snmpWalkMap.get("ipmsalarmTime").nextToken();

				System.out.println("IPMSActionHandler::Time in table is : " +Time.toString());

				
				if(Time.indexOf("-") != -1)//event
				{
					String Timezone = null;
					System.out.println("Legth time :" + Time.length());

					if (Time.length() != 25) 		
					{ 
						System.out.println("If the time is not in 11 octet then setting the time to local current time");
						notif[count].setTimeZone("UTC");
						notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
					} 
					else
					{
						try
						{
							Timezone = HandleTimeTranslation.getEventTimeZone(Time);
							System.out.println("TimeZone is: " +Timezone);
						}
						catch (Exception ex)
						{
							System.out.println("Date Format Exception: getEventTimeZone!");
						}

						System.out.println("Time is" +HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time)));
						notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time))).toString());

						notif[count].setTimeZone(Timezone);
					}
				}
				else //alarm
				{
					String Timezone = null;
					System.out.println("Legth time :" + Time.length());	

					if (Time.length() != 32) 	
					{ 
						System.out.println("If the time is not in 11 octet then setting the time to local current time"); 
						notif[count].setTimeZone("UTC");

						notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
					} 
					else
					{						
						if(HandleTimeTranslation.getTimeZone(Time) !=null)
						{
						Timezone = HandleTimeTranslation.getTimeZone(Time);
						System.out.println("buildIPMSAlarm::TimeZone is: " +Timezone);
						}
						else
							Timezone ="UTC" ;
						

						System.out.println("Time is" +HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time)));
						notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time))).toString());
						notif[count].setTimeZone(Timezone);
					}
				}
			}
			catch(Exception ex)
			{
				System.out.println("Date Format Exception: " + Time.toString());
				notif[count].setTimeZone("UTC");
				notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
				System.out.println("IPMSActionHandler:synchronize():setTime(): "+HandleTimeTranslation.getCurrentTime());
				System.out.println("Date Format Exception: " +ex);
				ex.printStackTrace();
			}

			String type = "";

			try
			{
				type = snmpWalkMap.get("ipmsalarmType").nextToken();

				System.out.println("alarmType = "+type);
				notif[count].setEventType(HandleEventType.getipmsalarmEventType(type));

			}
			catch(Exception e)
			{
				notif[count].setEventType("Indeterminate");
				System.out.println("IPMSActionHandler: Exception in setEventType = "+"Indeterminate");
			}

			try
			{
				if(snmpWalkMap.get("ipmsalarmPC").hasMoreTokens())
				{
					int pCause = Integer.parseInt(snmpWalkMap.get("ipmsalarmPC").nextToken());

					System.out.println("alarmPC = "+pCause);
					notif[count].setProbableCause(new Integer(pCause).toString());
				}
				else
				{
					notif[count].setProbableCause("0"); //Indeterminate
					System.out.println("No alarmPC = "+"Indeterminate");
				}
			}
			catch(Exception e)
			{
				notif[count].setProbableCause("0"); //Indeterminate
				System.out.println("IPMSActionHandler: Exception in processing alarmPC = "+"Indeterminate");
			}

			try
			{
				String xCausingInstances = snmpWalkMap.get("ipmsalarmXCausing").nextToken();

				notif[count].addAdditionalAttribute("XCausingInstances ",xCausingInstances);
				System.out.println("XCausingInstances = "+xCausingInstances);
			}
			catch(Exception e)
			{
				System.out.println("IPMSActionHandler: Exception in xCausingInstances");
			}

			String tempPS = "";

			try
			{
				tempPS = snmpWalkMap.get("ipmsalarmPS").nextToken();

				System.out.println("alarmPS = "+tempPS);
				notif[count].setSeverity(HandleAlarmSeverity.getPerceivedSeverity(tempPS));

			}
			catch(Exception e)
			{
				notif[count].setSeverity("INDETERMINATE");
				System.out.println("IPMSActionHandler: Exception in setPerceivedSeverity = "+"INDETERMINATE");
			}

			try
			{
				a_text = snmpWalkMap.get("ipmsalarmText").nextToken();
				notif[count].addAdditionalAttribute("additionalText",a_text);
				notif[count].addAdditionalAttribute("AdditionalText",a_text);
				System.out.println("alarmText = "+a_text);
			}
			catch(Exception e)
			{
				a_text = "No Additional Text in Alarm.";

				notif[count].addAdditionalAttribute("additionalText",a_text);
				notif[count].addAdditionalAttribute("AdditionalText",a_text);
				System.out.println("IPMSActionHandler: Exception in alarmText = "+a_text);
			}



			try
			{
				String xCauseText = snmpWalkMap.get("ipmsalarmCauseXTxt").nextToken();

				notif[count].addAdditionalAttribute("XCauseText ",xCauseText);
				System.out.println("XCauseText = "+xCauseText);
			}
			catch(Exception e)
			{
				System.out.println("IPMSActionHandler: Exception in XCauseText");
			}

			String tempSP = "";

			try
			{
				tempSP = snmpWalkMap.get("ipmsalarmSP").nextToken();
				notif[count].setSpecificProblem(tempSP);
				System.out.println("alarmSP = "+tempSP);
			}
			catch(Exception e)
			{
				notif[count].setSpecificProblem("No Specific Problem in Alarm.");
				System.out.println("IPMSActionHandler: Exception in setSpecificProblem = "+"No Specific Problem in Alarm.");
			}

			count ++;
		}
	
		return notif;
	}

}
