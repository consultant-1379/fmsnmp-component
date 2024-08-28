package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.Map;
import java.util.StringTokenizer;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.constant.TranslationConstant;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;

public class NsMptMibHandling {
	

	// returns the array of IF MIB alarms notifications

	public SynchronizationEventNotification[] getNsmptMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int mptnetsipraAlarmCount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[mptnetsipraAlarmCount];

		for( int i = 0 ; i < mptnetsipraAlarmCount ; i++ )
		{

			String valMptAlarmEventType="";
			try
			{
				valMptAlarmEventType =snmpWalkMap.get("_OID_VAR_MPT_ALARM_EVENT_TYPE").nextToken().trim();
			}
			catch(Exception e)
			{
				valMptAlarmEventType="0";
			}
			String valMptProbableCause="";
			try
			{
				valMptProbableCause = snmpWalkMap.get("_OID_VAR_MPT_PROBABLE_CAUSE").nextToken().trim();
			}
			catch(Exception e)
			{
				valMptProbableCause="0";
			}

			int valMptPerceivedSeverity=0;
			try
			{
				valMptPerceivedSeverity = Integer.parseInt(snmpWalkMap.get("_OID_VAR_MPT_PERCEIVED_SEVERITY").nextToken().trim());
			}
			catch(Exception e)
			{
				valMptPerceivedSeverity=0;
			}

			String valMptNotificationID="";
			try
			{
				valMptNotificationID = snmpWalkMap.get("_OID_VAR_MPT_NOTIFICATION_ID").nextToken().trim();
			}
			catch(Exception e)
			{
				valMptNotificationID="0";
			}
			String valMptAdditionalText="";
			try
			{
				valMptAdditionalText = snmpWalkMap.get("_OID_VAR_MPT_ADDITIONAL_TEXT").nextToken().trim();
			}
			catch(Exception e)
			{
				valMptAdditionalText="";
			}
			System.out.println("7");	
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");

			//Set Specific Problem
			notif[count].setSpecificProblem(TranslationConstant.txtTrapMptControllerFault);
			notif[count].addAdditionalAttribute("Trap Name","mptControllerFault");
			// Set Event Type
			notif[count].setEventType(valMptAlarmEventType);

			// Set Probable Cause
			notif[count].setProbableCause(valMptProbableCause);

			// Set Perceived Severity
			notif[count].setSeverity(HandleAlarmSeverity.getIntegerPerceivedSeverity(valMptPerceivedSeverity));

			// Set Notification ID
			notif[count].setExternalEventId(valMptNotificationID);


			// Set Additional Text
			notif[count].addAdditionalAttribute("AdditionalText",valMptAdditionalText);
			
			notif[count].addAdditionalAttribute("additionalText",valMptAdditionalText);

			count++;



		}//for
	
		return notif;

}
}
