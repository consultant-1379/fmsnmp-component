package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.fm.component.SnmpSupervisionProducer;
import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.*;

public class AlarmIrpMibHandling {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AlarmIrpMibHandling.class);
	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getAlarmIrpAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int alarmIRPcount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[alarmIRPcount];

		for(int i = 0; i < alarmIRPcount ;i++)
		{
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM" );
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");
			notif[count].addAdditionalAttribute("Generic","False");
			//notif[count].setTimeZone(TimeZone.getDefault().getDisplayName(false,TimeZone.SHORT));
			try
			{

				notif[count].setExternalEventId(snmpWalkMap.get("alarmId").nextToken());
			}catch(NoSuchElementException e)
			{
				String  a_nr = "No External Event ID in Alarm.";
				notif[count].setExternalEventId(a_nr);
			}
			try
			{
				String  mo_text =  snmpWalkMap.get("alarmMOClass").nextToken();
				notif[count].addAdditionalAttribute("Equipment",mo_text);
			}catch(NoSuchElementException e)
			{
				String mo_text = "No Managed Object Instance.";
				notif[count].addAdditionalAttribute("Equipment",mo_text);
			}
			try
			{
				String a_text = snmpWalkMap.get("alarmText").nextToken();
				notif[count].addAdditionalAttribute( "additionalText", a_text );
				notif[count].addAdditionalAttribute( "AdditionalText", a_text );
			}catch(NoSuchElementException e)
			{
				String  a_text = "No Additional Text in Alarm.";
				notif[count].addAdditionalAttribute( "additionalText", a_text );
				notif[count].addAdditionalAttribute( "AdditionalText", a_text );
			}
			try
			{
				String  Time = snmpWalkMap.get("alarmTime").nextToken();
				notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time)).toString()).toString());
			}catch(Exception ex)
			{
				LOGGER.error("Date Format Exception: " );
				notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
			}

			try
			{
				String alarmTimeeventtype=snmpWalkMap.get("alarmTime").nextToken();
				notif[count].setEventType(HandleEventType.getWPPEventType(alarmTimeeventtype));

			}catch(NoSuchElementException e)
			{
				notif[count].setEventType("0");
			}

			try
			{
				int probableCause = 0;
				probableCause =  Integer.parseInt(snmpWalkMap.get("alarmPC").nextToken());
				if(probableCause > 0 && probableCause <= 57)
					probableCause+=300;
				if(probableCause >= 10000 && probableCause <=10156)
					probableCause-=10000;
				if(probableCause >= 16001 && probableCause <=16074)
					probableCause-=15500;
				notif[count].setProbableCause(new Integer(probableCause).toString());
			}catch(NoSuchElementException e)
			{
				notif[count].setProbableCause("0");
			}
			try
			{
				String alarmPS=snmpWalkMap.get("alarmPS").nextToken();
				notif[count].setSeverity(HandleAlarmSeverity.getPerceivedSeverity(alarmPS));

			}catch(NoSuchElementException e)
			{
				notif[count].setSeverity("INDETERMINATE");
			}
			try
			{
				notif[count].setSpecificProblem(snmpWalkMap.get("alarmSP").nextToken());
			}catch(NoSuchElementException e)
			{
				notif[count].setSpecificProblem("No Specific Problem in Alarm.");
			}
			LOGGER.info("syncAlarmIrp() >> Generated SyncAlarm for alarmId: " );
		}
	
		return notif;
	}
}
