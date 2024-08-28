package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

public class JnxVpnPwMibHandling {
	int jnxVpnPwStatusIntValue = 0;
     int vpnPwAlarmsCount=0;
	String jnxVpnPwIndex = "",jnxVpnPwVpnName = "",jnxVpnPwVpnType = "",jnxVpnPwStatus = "";
	/** JUNIPER-VPN-MIB(PW) */
	List<String> listJnxVpnPwIndexValues 	= new ArrayList<String>();
	List<String> listJnxVpnPwVpnNameValues 	= new ArrayList<String>();
	List<String> listJnxVpnPwVpnTypeValues 	= new ArrayList<String>();
	List<String> listJnxVpnPwStatusValues 	= new ArrayList<String>();
	public int jnxVpnIfIndexcount(Map<String,StringTokenizer>snmpWalkMap,int jnxVpnPwIndexCount,int jnxVpnPwStatusCount){
		
		for(int i = 0;i < jnxVpnPwIndexCount;i++)
		{
			if(jnxVpnPwStatusCount > 0)
			{
				jnxVpnPwStatusIntValue = 0;

				jnxVpnPwIndex = "";jnxVpnPwVpnName = "";jnxVpnPwVpnType = "";jnxVpnPwStatus = "";

				try
				{
					jnxVpnPwIndex = snmpWalkMap.get("jnxVpnPwIndex").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					jnxVpnPwVpnName = snmpWalkMap.get("jnxVpnPwVpnName").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					jnxVpnPwVpnType = snmpWalkMap.get("jnxVpnPwVpnType").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					jnxVpnPwStatus = snmpWalkMap.get("jnxVpnPwStatus").nextToken();
					jnxVpnPwStatusIntValue = Integer.valueOf(jnxVpnPwStatus).intValue();
				}
				catch(Exception e)
				{ }

				/** count the number of Link Down alarms based on the value of jnxVpnPwStatus */

				if (jnxVpnPwStatusIntValue == 1)
				{
					listJnxVpnPwIndexValues.add(jnxVpnPwIndex);
					listJnxVpnPwVpnNameValues.add(jnxVpnPwVpnName);
					listJnxVpnPwVpnTypeValues.add(jnxVpnPwVpnType);
					listJnxVpnPwStatusValues.add(jnxVpnPwStatus);

					vpnPwAlarmsCount++;
				}
			}
		}
		return vpnPwAlarmsCount;

	}
	public int getVpnPwAlarmsCount() {
		return vpnPwAlarmsCount;
	}

	public List<String> getListJnxVpnPwIndexValues() {
		return listJnxVpnPwIndexValues;
	}
	public void setListJnxVpnPwIndexValues(List<String> listJnxVpnPwIndexValues) {
		this.listJnxVpnPwIndexValues = listJnxVpnPwIndexValues;
	}
	public List<String> getListJnxVpnPwVpnNameValues() {
		return listJnxVpnPwVpnNameValues;
	}
	public void setListJnxVpnPwVpnNameValues(List<String> listJnxVpnPwVpnNameValues) {
		this.listJnxVpnPwVpnNameValues = listJnxVpnPwVpnNameValues;
	}
	public List<String> getListJnxVpnPwVpnTypeValues() {
		return listJnxVpnPwVpnTypeValues;
	}
	public void setListJnxVpnPwVpnTypeValues(List<String> listJnxVpnPwVpnTypeValues) {
		this.listJnxVpnPwVpnTypeValues = listJnxVpnPwVpnTypeValues;
	}
	public List<String> getListJnxVpnPwStatusValues() {
		return listJnxVpnPwStatusValues;
	}
	public void setListJnxVpnPwStatusValues(List<String> listJnxVpnPwStatusValues) {
		this.listJnxVpnPwStatusValues = listJnxVpnPwStatusValues;
	}
}


