package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

public class MplsLspMibHandling {
	int mplsLspStateIntValue = 0;
	int mplsAlarmsCount=0;
	public int getMplsAlarmsCount() {
		return mplsAlarmsCount;
	}


	public List<String> getListMplsLspNameValues() {
		return listMplsLspNameValues;
	}

	public void setListMplsLspNameValues(List<String> listMplsLspNameValues) {
		this.listMplsLspNameValues = listMplsLspNameValues;
	}

	public List<String> getListMplsPathNameValues() {
		return listMplsPathNameValues;
	}

	public void setListMplsPathNameValues(List<String> listMplsPathNameValues) {
		this.listMplsPathNameValues = listMplsPathNameValues;
	}

	public List<String> getListMplsLspStateValues() {
		return listMplsLspStateValues;
	}

	public void setListMplsLspStateValues(List<String> listMplsLspStateValues) {
		this.listMplsLspStateValues = listMplsLspStateValues;
	}

	/** MPLS-MIB */
	List<String> listMplsLspNameValues 		= new ArrayList<String>();
	List<String> listMplsPathNameValues 	= new ArrayList<String>();
	List<String> listMplsLspStateValues 	= new ArrayList<String>();
	String mplsLspName = "",mplsPathName = "",mplsLspState = "",jnxRmonAlarmState = "";

	public int countmplsAlarmsCount(Map<String,StringTokenizer>snmpWalkMap,int mplsLspNameCount,int mplsLspStateCount){
		
		for(int i = 0;i < mplsLspNameCount;i++)
		{
			if(mplsLspStateCount > 0)
			{
				mplsLspStateIntValue = 0;
				mplsLspName = "";mplsPathName = "";mplsLspState = "";jnxRmonAlarmState = "";

				try
				{
					mplsLspName = snmpWalkMap.get("mplsLspName").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					mplsPathName = snmpWalkMap.get("mplsPathName").nextToken();
				}
				catch(Exception e)
				{ }

				try
				{
					mplsLspState = snmpWalkMap.get("mplsLspState").nextToken();
					mplsLspStateIntValue = Integer.valueOf(mplsLspState).intValue();
				}
				catch(Exception e)
				{ }

				/** count the number of Link Down alarms based on the value of mplsLspState */

				if (mplsLspStateIntValue == 3)
				{
					listMplsLspNameValues.add(mplsLspName);
					listMplsPathNameValues.add(mplsPathName);
					listMplsLspStateValues.add(mplsLspState);

					mplsAlarmsCount++;
				}
			}
		}
		return mplsLspNameCount;
		
	}
}
