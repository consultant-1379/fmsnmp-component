package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

public class JnxVpnMibHandling {
	
	int jnxVpnIfStatusIntValue = 0;
     int vpnIfAlarmsCount = 0;
	String jnxVpnIfIndex = "",jnxVpnIfVpnName = "",jnxVpnIfVpnType = "",jnxVpnIfStatus = "";
	

	/** JUNIPER-VPN-MIB(IF) */
	List<String> listJnxVpnIfIndexValues 	= new ArrayList<String>();
	List<String> listJnxVpnIfVpnNameValues 	= new ArrayList<String>();
	List<String> listJnxVpnIfVpnTypeValues 	= new ArrayList<String>();
	List<String> listJnxVpnIfStatusValues 	= new ArrayList<String>();

	
	public int jnxVpnIfIndexcount(Map<String,StringTokenizer>snmpWalkMap,int jnxVpnIfIndexCount,int jnxVpnIfStatusCount){
		
		for(int i = 0;i < jnxVpnIfIndexCount;i++)
		{
			if (jnxVpnIfStatusCount > 0)
			{
				jnxVpnIfStatusIntValue = 0;

				jnxVpnIfIndex = "";jnxVpnIfVpnName = "";jnxVpnIfVpnType = "";jnxVpnIfStatus = "";

				try
				{
					jnxVpnIfIndex = snmpWalkMap.get("jnxVpnIfIndex").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					jnxVpnIfVpnName = snmpWalkMap.get("jnxVpnIfVpnName").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					jnxVpnIfVpnType = snmpWalkMap.get("jnxVpnIfVpnType").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					jnxVpnIfStatus = snmpWalkMap.get("jnxVpnIfStatus").nextToken();
					jnxVpnIfStatusIntValue = Integer.valueOf(jnxVpnIfStatus).intValue();
				}
				catch(Exception e)
				{ }

				/** count the number of Link Down alarms based on the value of jnxVpnIfStatus */

				if (jnxVpnIfStatusIntValue == 4)
				{
					listJnxVpnIfIndexValues.add(jnxVpnIfIndex);
					listJnxVpnIfVpnNameValues.add(jnxVpnIfVpnName);
					listJnxVpnIfVpnTypeValues.add(jnxVpnIfVpnType);
					listJnxVpnIfStatusValues.add(jnxVpnIfStatus);

					vpnIfAlarmsCount++;
				}
			}
		}
		
		return vpnIfAlarmsCount;
	}


	public int getVpnIfAlarmsCount() {
		return vpnIfAlarmsCount;
	}

    public List<String> getListJnxVpnIfIndexValues() {
		return listJnxVpnIfIndexValues;
	}


	public void setListJnxVpnIfIndexValues(List<String> listJnxVpnIfIndexValues) {
		this.listJnxVpnIfIndexValues = listJnxVpnIfIndexValues;
	}


	public List<String> getListJnxVpnIfVpnNameValues() {
		return listJnxVpnIfVpnNameValues;
	}


	public void setListJnxVpnIfVpnNameValues(List<String> listJnxVpnIfVpnNameValues) {
		this.listJnxVpnIfVpnNameValues = listJnxVpnIfVpnNameValues;
	}


	public List<String> getListJnxVpnIfVpnTypeValues() {
		return listJnxVpnIfVpnTypeValues;
	}


	public void setListJnxVpnIfVpnTypeValues(List<String> listJnxVpnIfVpnTypeValues) {
		this.listJnxVpnIfVpnTypeValues = listJnxVpnIfVpnTypeValues;
	}


	public List<String> getListJnxVpnIfStatusValues() {
		return listJnxVpnIfStatusValues;
	}


	public void setListJnxVpnIfStatusValues(List<String> listJnxVpnIfStatusValues) {
		this.listJnxVpnIfStatusValues = listJnxVpnIfStatusValues;
	}
	

}


