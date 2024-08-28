package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleTimeTranslation;

public class JambalaMibHandling {
	
	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getjambalaMIBAlarm(final SNMPManagedElement me,final Map<String,StringTokenizer> snmpWalkMap,final int jambalaalarmIdcount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[jambalaalarmIdcount];
	
		String mo_text="";
		String  a_nr="";
		String Time="";
		for(int cnt = 0; cnt < jambalaalarmIdcount;cnt++)
		{
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			//notif[count].setTimeZone(TimeZone.getDefault().getDisplayName(false,TimeZone.SHORT));
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");
			notif[count].addAdditionalAttribute("Generic","False");
			try
			{
				a_nr = snmpWalkMap.get("ALARM_ID").nextToken();
				notif[count].setExternalEventId(a_nr);
			}catch(NoSuchElementException e)
			{
				a_nr = "No External Event ID in Alarm.";
				notif[count].setExternalEventId(a_nr);
			}
			try
			{
				mo_text =  snmpWalkMap.get("ALARM_MO_CLASS").nextToken();
			}catch(NoSuchElementException e)
			{
				mo_text = "No Managed Object Class.";
			}
			try
			{
				String mo_inst = snmpWalkMap.get("ALARM_MO_INST").nextToken();
				notif[count].addAdditionalAttribute("Equipment",mo_text.concat(mo_inst));
			}catch(NoSuchElementException e)
			{
				String mo_inst = "No Managed Object Inst.";
				notif[count].addAdditionalAttribute("Equipment",mo_text.concat(mo_inst));
			}
			try
			{
				Time = snmpWalkMap.get("ALARM_TIME").nextToken();
				TimeZone tz = TimeZone.getTimeZone(HandleTimeTranslation.getTimeZone(Time));
				tz.setID(tz.getDisplayName(false,TimeZone.SHORT));
				HandleTimeTranslation._timeFormatter.setTimeZone(tz);
				notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time))).toString());
			}
			catch(Exception ex)
			{
				System.out.println("Date Format Exception: " + Time);
				notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
			}
			try
			{
				notif[count].setTimeZone(TimeZone.getTimeZone(
						HandleTimeTranslation.getTimeZone(Time)).getDisplayName(
								false,TimeZone.SHORT));
			}
			catch(Exception ex)
			{

				System.out.println("Error setting timezone...Time = "
						+Time+" Exception: " +ex.getMessage());
				notif[count].setTimeZone(TimeZone.getTimeZone(
						"GMT").getDisplayName(
								false,TimeZone.SHORT));
			}
			try
			{
				switch(Integer.parseInt(snmpWalkMap.get("ALARM_TYPE").nextToken()))
				{
				case 1:
				case 2:
					notif[count].setEventType("1"); //Communication Alarm
					break;
				case 3:
					notif[count].setEventType("3"); //Enviromental Alarm
					break;
				case 4:
				case 5:
					notif[count].setEventType("5"); //Equipment Alarm
					break;
				case 10:
					notif[count].setEventType("2"); //Processing Error Alarm
					break;
				case 11:
					notif[count].setEventType("4"); //Quality of service Alarm
					break;
				case 15:
					notif[count].setEventType("15"); //Integrity Violation
					break;
				case 16:
					notif[count].setEventType("16"); //operational Violation
					break;
				case 17:
					notif[count].setEventType("17"); //physical violation
					break;
				case 18:
					notif[count].setEventType("18"); //Security Service Violation
					break;
				case 19:
					notif[count].setEventType("19"); //Time Domain Violation
					break;
				}
			}catch(NoSuchElementException e)
			{
				notif[count].setEventType("Indeterminate");
			}
			try
			{
				notif[count].setProbableCause(snmpWalkMap.get("ALARM_PC").nextToken());
			}catch(NoSuchElementException e)
			{
				notif[count].setProbableCause("0"); //Indeterminate
			}
			try
			{
				System.out.println("ALARM_PS:"+snmpWalkMap.get("ALARM_PS").nextToken());
				notif[count].setSeverity(snmpWalkMap.get("ALARM_PS").nextToken());


			}catch(NoSuchElementException e)
			{
				notif[count].setSeverity("INDETERMINATE");
			}
			try
			{
				String  a_text = snmpWalkMap.get("ALARM_ATEXT").nextToken();
				notif[count].addAdditionalAttribute( "additionalText", a_text );
				notif[count].addAdditionalAttribute( "AdditionalText", a_text );
			}catch(NoSuchElementException e)
			{
				String a_text = "No Additional Text in Alarm.";
				notif[count].addAdditionalAttribute( "additionalText", a_text );
				notif[count].addAdditionalAttribute( "AdditionalText", a_text );
			}
			try
			{
				notif[count].setSpecificProblem(snmpWalkMap.get("ALARM_SP").nextToken());
			}catch(NoSuchElementException e)
			{
				notif[count].setSpecificProblem("No Specific Problem in Alarm.");
			}
			count++;
		}

	
		return notif;
	}

}
