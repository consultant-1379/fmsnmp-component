package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleAllOtherAlarmAttribute;
import com.ericsson.oss.mediation.translator.model.HandleTimeTranslation;

import com.adventnet.snmp.snmp2.SnmpPDU;

public class IFMibHandling {
	/** IF-MIB */
	List<String> listIfIndexValues 			= new ArrayList<String>();
	List<String> listIfAdminStatusValues 	= new ArrayList<String>();	
	List<String> listIfOperStatusValues 	= new ArrayList<String>();
    int ifAlarmsCount=0;
	private static final Logger LOGGER = LoggerFactory
			.getLogger(IFMibHandling.class);
   
	public int CountIfAlarm(Map<String,StringTokenizer>snmpWalkMap,int ifIndexCount){


		for(int i = 0;i < ifIndexCount;i++)
		{
			int operStatus = 0;int adminStatus = 0;

			String ifIndex = "",ifAdminStatus = "",ifOperStatus = "";

			try
			{
				ifIndex = snmpWalkMap.get("ifIndex").nextToken();
			
				ifAdminStatus = snmpWalkMap.get("ifAdminStatus").nextToken();
				adminStatus = Integer.valueOf(ifAdminStatus).intValue();
			
				ifOperStatus = snmpWalkMap.get("ifOperStatus").nextToken();
				operStatus = Integer.valueOf(ifOperStatus).intValue();
			

			/** count the number of Link Down alarms based on the value of operStatus and adminStatus */

			if ((adminStatus == 1 || adminStatus == 3) && (operStatus == 2))
			{
				listIfIndexValues.add(ifIndex);
				listIfAdminStatusValues.add(ifAdminStatus);
				listIfOperStatusValues.add(ifOperStatus);

				ifAlarmsCount ++;
			}
			}
			catch(Exception e){
				LOGGER.error("Exception while CountIfAlarm::"+e.getMessage());
			}
		}
		System.out.println("ifAlarmsCount count is "+ifAlarmsCount);
		return ifAlarmsCount;
	}
	public List<String> getListIfIndexValues() {
		return listIfIndexValues;
	}
	public void setListIfIndexValues(List<String> listIfIndexValues) {
		this.listIfIndexValues = listIfIndexValues;
	}
	public List<String> getListIfAdminStatusValues() {
		return listIfAdminStatusValues;
	}
	public void setListIfAdminStatusValues(List<String> listIfAdminStatusValues) {
		this.listIfAdminStatusValues = listIfAdminStatusValues;
	}
	public List<String> getListIfOperStatusValues() {
		return listIfOperStatusValues;
	}
	public void setListIfOperStatusValues(List<String> listIfOperStatusValues) {
		this.listIfOperStatusValues = listIfOperStatusValues;
	}
	public int getIfAlarmsCount() {
		return ifAlarmsCount;
	}
	public void setIfAlarmsCount(int ifAlarmsCount) {
		this.ifAlarmsCount = ifAlarmsCount;
	}
	
	// returns the array of IF MIB alarms notifications
	
	public SynchronizationEventNotification[] getIFMIBAlarm(SNMPManagedElement me,List<SnmpPDU> responsePdu, int ifAlarmsCount){
		
	     int count=0;
	     String ifIndex=null;
		
		Iterator<String> itrIfIndexValues		= listIfIndexValues.iterator();
		Iterator<String> itrIfAdminStatusValues = listIfAdminStatusValues.iterator();
		Iterator<String> itrIfOperStatusValues 	= listIfOperStatusValues.iterator();
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[ifAlarmsCount];
		for(int i = 0; i < ifAlarmsCount; i++)
		{
			StringBuffer additionaltextBuffer=new StringBuffer();
			System.out.println("CommonAAUSnmpActionHandler :: Setting notification for Link Down alarms ==> "+count);

			notif[count] = new SynchronizationEventNotification();

			notif[count].setFmEventType("ALARM");
			notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].setTimeZone("UTC");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("Enterprise","True");
			notif[count].addAdditionalAttribute("Generic","False");
			notif[count].setEventType("2"); //Communication Alarm
			notif[count].setProbableCause("305"); //Communications Protocol Error
			notif[count].setSpecificProblem("Link Down");
			notif[count].setSeverity("MAJOR");
			notif[count].addAdditionalAttribute("Generic", "Link Down");

			try
			{
				ifIndex = itrIfIndexValues.next();
				notif[count].setExternalEventId(ifIndex);
				notif[count].addAdditionalAttribute("ifIndex",ifIndex);
			
				String ifAdminStatusValue =HandleAllOtherAlarmAttribute.getIfAdminState("ifAdminStatus",itrIfAdminStatusValues.next());

				System.out.println("ifAdminStatus:"+ifAdminStatusValue);
				notif[count].addAdditionalAttribute("IfAdminStatus",ifAdminStatusValue);
			

			
				String ifOperStatusValue =HandleAllOtherAlarmAttribute.getIfAdminState("ifOperStatus",itrIfOperStatusValues.next());
				System.out.println("ifOperStatus:"+ifOperStatusValue);
				notif[count].addAdditionalAttribute("ifOperStatus",ifOperStatusValue);
			

			count ++;
		}catch(Exception e)
		{
			
		}
		}
		
		return notif;
	
	}
}
