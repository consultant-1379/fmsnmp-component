package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.Map;
import java.util.StringTokenizer;

public class CountAlarmInMibs {
	
	public  int CountMibAlarms(Map<String, StringTokenizer>snmpWalkMap,String toSearch){
		return snmpWalkMap.get(toSearch).countTokens();
		
	}

}
