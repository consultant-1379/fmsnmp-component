package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

public class JnxRmonMibHandling {
	public static int juniperRMONAlarmsCount = 0;

	String alarmIndex = "",alarmVariable = "",getFailReason = "",jnxRmonAlarmState = "";
	/** JUNIPER-RMON-MIB */
	List<String> listJnxRmonAlarmStateValues 	= new ArrayList<String>();
	List<String> listAlarmIndexValues 			= new ArrayList<String>();
	List<String> listAlarmVariableValues 		= new ArrayList<String>();
	List<String> listGetFailReasonValues 		= new ArrayList<String>();
	public int getJnxRmonAlarmStateIntValue() {
		return juniperRMONAlarmsCount;
	}

	public String getAlarmIndex() {
		return alarmIndex;
	}
	public void setAlarmIndex(String alarmIndex) {
		this.alarmIndex = alarmIndex;
	}
	public String getAlarmVariable() {
		return alarmVariable;
	}
	public void setAlarmVariable(String alarmVariable) {
		this.alarmVariable = alarmVariable;
	}
	public String getGetFailReason() {
		return getFailReason;
	}
	public void setGetFailReason(String getFailReason) {
		this.getFailReason = getFailReason;
	}
	public String getJnxRmonAlarmState() {
		return jnxRmonAlarmState;
	}
	public void setJnxRmonAlarmState(String jnxRmonAlarmState) {
		this.jnxRmonAlarmState = jnxRmonAlarmState;
	}
	public List<String> getListJnxRmonAlarmStateValues() {
		return listJnxRmonAlarmStateValues;
	}
	public void setListJnxRmonAlarmStateValues(
			List<String> listJnxRmonAlarmStateValues) {
		this.listJnxRmonAlarmStateValues = listJnxRmonAlarmStateValues;
	}
	public List<String> getListAlarmIndexValues() {
		return listAlarmIndexValues;
	}
	public void setListAlarmIndexValues(List<String> listAlarmIndexValues) {
		this.listAlarmIndexValues = listAlarmIndexValues;
	}
	public List<String> getListAlarmVariableValues() {
		return listAlarmVariableValues;
	}
	public void setListAlarmVariableValues(List<String> listAlarmVariableValues) {
		this.listAlarmVariableValues = listAlarmVariableValues;
	}
	public List<String> getListGetFailReasonValues() {
		return listGetFailReasonValues;
	}
	public void setListGetFailReasonValues(List<String> listGetFailReasonValues) {
		this.listGetFailReasonValues = listGetFailReasonValues;
	}
	public int countJnxRmonAlarm(Map<String,StringTokenizer>snmpWalkMap,int alarmIndexCount,int alarmStateCount){
		for(int i = 0;i < alarmIndexCount;i++)
		{
			if(alarmStateCount > 0)
			{
			
				int jnxRmonAlarmStateIntValue = 0;
				try
				{
					alarmIndex = snmpWalkMap.get("alarmIndex").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					alarmVariable = snmpWalkMap.get("alarmVariable").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					getFailReason = snmpWalkMap.get("jnxRmonAlarmGetFailReason").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					jnxRmonAlarmState = snmpWalkMap.get("jnxRmonAlarmState").nextToken();
					jnxRmonAlarmStateIntValue = Integer.valueOf(jnxRmonAlarmState).intValue();
				}
				catch(Exception e)
				{ }

				/** count the number of Link Down alarms based on the value of jnxRmonAlarmState */

				if (jnxRmonAlarmStateIntValue == 7)
				{
					listAlarmIndexValues.add(alarmIndex);
					listAlarmVariableValues.add(alarmVariable);
					listGetFailReasonValues.add(getFailReason);
					listJnxRmonAlarmStateValues.add(jnxRmonAlarmState);

					juniperRMONAlarmsCount++;
				}
			}
		}
		return juniperRMONAlarmsCount;
	}

}
