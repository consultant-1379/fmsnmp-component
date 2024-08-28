package com.ericsson.oss.mediation.translator.model;

import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

//import com.adventnet.snmp.mibs.mibparser.MCModule;

public class HandleEventType {

	private HandleEventType() {

	}

	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleEventType.class);
	private static final Map<String, String> sppAlarmTypeMap = new HashMap<String, String>();
	private static final Map<String, String> siteEventTypeMap = new HashMap<String, String>();
	private static final Map<String, String> FmSystemAlarmTypeEventTypeMap = new HashMap<String, String>();
	private static final Map<String, String> FmSystemAlarmProbableCauseMap = new HashMap<String, String>();
	private static final Map<String, String> mgcEventtypeMap = new HashMap<String, String>();
	private static final Map<String, String> valIsFmCurrentAlarmClassMap = new HashMap<String, String>();
	private static final Map<String, String> itueventMap = new HashMap<String, String>();
	private static final Map<String, String> gsneventMap = new HashMap<String, String>();
	private static final Map<String, String> isbladeEventTypeMap = new HashMap<String, String>();

	static{
		
		isbladeEventTypeMap.put("1", "1");
		isbladeEventTypeMap.put("50", "20");
		gsneventMap.put("0", "0");
		gsneventMap.put("1", "1");
		gsneventMap.put("2", "1");
		gsneventMap.put("3", "3");
		gsneventMap.put("4", "5");
		gsneventMap.put("5", "5");
		gsneventMap.put("10", "2");
		gsneventMap.put("11", "4");
		gsneventMap.put("15", "15");
		gsneventMap.put("16", "16");
		gsneventMap.put("17", "17");
		gsneventMap.put("18", "18");
		gsneventMap.put("19", "19");


		valIsFmCurrentAlarmClassMap.put("1", "20");
		valIsFmCurrentAlarmClassMap.put("2", "2");
		valIsFmCurrentAlarmClassMap.put("3", "11");
		valIsFmCurrentAlarmClassMap.put("4", "10");
		valIsFmCurrentAlarmClassMap.put("5", "4");
		valIsFmCurrentAlarmClassMap.put("6", "3");
		valIsFmCurrentAlarmClassMap.put("7", "15");
		valIsFmCurrentAlarmClassMap.put("8", "16");
		valIsFmCurrentAlarmClassMap.put("9", "17");
		valIsFmCurrentAlarmClassMap.put("10", "18");
		valIsFmCurrentAlarmClassMap.put("11", "19");


		valIsFmCurrentAlarmClassMap.put("2", "1");
		valIsFmCurrentAlarmClassMap.put("3", "3");
		valIsFmCurrentAlarmClassMap.put("4", "5");
		valIsFmCurrentAlarmClassMap.put("10", "2");
		valIsFmCurrentAlarmClassMap.put("11", "4");
		valIsFmCurrentAlarmClassMap.put("50", "20");

		mgcEventtypeMap.put("1", "0");
		mgcEventtypeMap.put("2", "2");
		mgcEventtypeMap.put("3", "11");
		mgcEventtypeMap.put("4", "10");
		mgcEventtypeMap.put("5", "4");
		mgcEventtypeMap.put("6", "3");

		FmSystemAlarmProbableCauseMap.put("1", "305");
		FmSystemAlarmProbableCauseMap.put("3", "307");

		FmSystemAlarmTypeEventTypeMap.put("1", "1");
		FmSystemAlarmTypeEventTypeMap.put("2", "4");
		FmSystemAlarmTypeEventTypeMap.put("3", "2");
		FmSystemAlarmTypeEventTypeMap.put("4", "5");
		FmSystemAlarmTypeEventTypeMap.put("5", "3");

		siteEventTypeMap.put("1", "1");
		siteEventTypeMap.put("2", "1");
		siteEventTypeMap.put("3", "3");
		siteEventTypeMap.put("4", "5");
		siteEventTypeMap.put("5", "5");
		siteEventTypeMap.put("10", "2");

		siteEventTypeMap.put("11", "4");
		siteEventTypeMap.put("15", "15");
		siteEventTypeMap.put("16", "16");
		siteEventTypeMap.put("17", "17");
		siteEventTypeMap.put("18", "18");
		siteEventTypeMap.put("19", "19");



		sppAlarmTypeMap.put("0","sameandequal");
		sppAlarmTypeMap.put("2","sameandequal");
		sppAlarmTypeMap.put("3","sameandequal");
		sppAlarmTypeMap.put("4","sameandequal");
		sppAlarmTypeMap.put("10","sameandequal");
		sppAlarmTypeMap.put("11","sameandequal");
		sppAlarmTypeMap.put("15","sameandequal");
		sppAlarmTypeMap.put("16","sameandequal");
		sppAlarmTypeMap.put("17","sameandequal");
		sppAlarmTypeMap.put("18","sameandequal");
		sppAlarmTypeMap.put("19","sameandequal");
	}

	// Processing zelsAlarmNumber
	public static String getzelsAlarmNumberEventType(final String value) {
		String getzelsAlarmNumberEventType = "";
		final int alarm_id = Integer.parseInt(value);

		if ((alarm_id == 22) || (alarm_id == 23) || (alarm_id == 24)
				|| (alarm_id == 25) || (alarm_id == 51) || (alarm_id == 52)
				|| (alarm_id == 54) || (alarm_id == 55)) {
			getzelsAlarmNumberEventType = "21";
		}

		else if ((alarm_id == 42) || (alarm_id == 43) || (alarm_id == 44)
				|| (alarm_id == 45) || (alarm_id == 46) || (alarm_id == 47)
				|| (alarm_id == 48) || (alarm_id == 57) || (alarm_id == 58)) {
			getzelsAlarmNumberEventType = "20";
		} else if ((alarm_id == 33) || (alarm_id == 34) || (alarm_id == 35)
				|| (alarm_id == 36) || (alarm_id == 37) || (alarm_id == 39)
				|| (alarm_id == 40) || (alarm_id == 41) || (alarm_id == 49))

		{
			getzelsAlarmNumberEventType = "1";
		}

		else if ((alarm_id == 26) || (alarm_id == 27) || (alarm_id == 28)
				|| (alarm_id == 29) || (alarm_id == 30) || (alarm_id == 31)
				|| (alarm_id == 32))

		{
			getzelsAlarmNumberEventType = "24";
		}

		else if (alarm_id == 38)

		{
			getzelsAlarmNumberEventType = "23";
		}

		else if (alarm_id == 53)

		{
			getzelsAlarmNumberEventType = "22";
		}

		else if (alarm_id == 50)

		{
			getzelsAlarmNumberEventType = "4";
		}

		else {
			getzelsAlarmNumberEventType = "0";
		}
		return getzelsAlarmNumberEventType;

	}

	// ERICSSON-SOG-ALARM-MIB newAlarmName
	public static String getnewAlarmNameEventType(final String attributeValue) {
		String newAlarmNameEventType = "0";
		newAlarmNameEventType = SnmpEtMaps.getEventType(attributeValue);
		if (newAlarmNameEventType == null) {
			return newAlarmNameEventType;
		}

		return newAlarmNameEventType;
	}

	// ESA-SNF ituAlarmEventType,ituAlarmEventTypeB
	public static String getituAlarmEventType(final String value) {
		String ituAlarmEventType = "0";
		try {
			final int alarmEventType = Integer.parseInt(value.toString());
			if (alarmEventType <= 11) {
				ituAlarmEventType = value.trim();
			}
		} catch (Exception e) {
			LOGGER.error("Exception while geting getituAlarmEventType:"
					+ e.getStackTrace());
			ituAlarmEventType = "0";// Unknown Event type

		}
		return ituAlarmEventType;
	}

	// SPP sppAlarmType
	public static String getsppAlarmType(final String value) {
		String sppAlarmType = "0";

		try {
			LOGGER.info("event type before sppMap:::"+value);
			if(sppAlarmType.contains(value))
				
			{
				sppAlarmType=value.trim();
			
			}

		} catch (Exception e) {
			LOGGER.error("Exception is:" + e.getStackTrace());
			sppAlarmType = "0";// Unknown Event type
		}
		return sppAlarmType;
	}

	// SITE siteEventType
	public static String getsiteEventType(final String value) {
		String siteEventType = "0";

		try {
			LOGGER.info("event type before siteMap:::"+value);
			if(siteEventType.contains(value))
			{
				siteEventType=siteEventTypeMap.get(value);
			}

		} catch (Exception e) {
			LOGGER.error("Exception is:" + e.getStackTrace());
			siteEventType = "0";// Unknown Event type
		}
		return siteEventType;
	}

	// MMMC mmsFmSystemAlarmType
	public static String getmmsFmSystemAlarmType(final String value) {
		String mmsFmSystemAlarmTypeEventType = "0";
		try {
			if(FmSystemAlarmTypeEventTypeMap.containsKey(value)){
				mmsFmSystemAlarmTypeEventType=FmSystemAlarmTypeEventTypeMap.get(value);
			}

		} catch (Exception ex) {
			LOGGER.error("setEventType Exception." + ex.getMessage());
			mmsFmSystemAlarmTypeEventType = "0";// Unknown
		}
		return mmsFmSystemAlarmTypeEventType;
	}

	public static String getmmsFmSystemProbableCause(final String value,
			final boolean perfdegraded) {
		String mmsFmSystemAlarmProbableCause = "0";
		try {
			if(FmSystemAlarmProbableCauseMap.containsKey(value)){
				mmsFmSystemAlarmProbableCause=FmSystemAlarmProbableCauseMap.get(value);
			}else if(Integer.valueOf(value).intValue()==2){
				if (perfdegraded) {
					mmsFmSystemAlarmProbableCause = "334";// Performance
					// Degraded
				} else {
					mmsFmSystemAlarmProbableCause = "351";// Threshold Crossed
				}
			}

		} catch (Exception ex) {
			LOGGER.error("setEventType Exception." + ex.getMessage());
			mmsFmSystemAlarmProbableCause = "0";// Indeterminate
		}
		return mmsFmSystemAlarmProbableCause;
	}

	// MGC EVENT TYPE
	public static String getmgcEventType(final String value) {
		String mgcEvent_Type = "0";

		try {
			mgcEvent_Type=mgcEventtypeMap.get(value);
		} catch (Exception e) {
			LOGGER.error("Exception is:" + e.getStackTrace());
			LOGGER.error(" Exception in decoding the event type Setting to unknown");
			mgcEvent_Type = "0";// Unknown Event type
			return mgcEvent_Type;

		}
		return mgcEvent_Type;
	}

	// JAMBALA
	public static String getirpalarmEventType(final String value) {
		String irpalarmEventType = "0";
		try {
			if(Integer.parseInt(value.toString())>=0&&Integer.parseInt(value.toString())<=19){
				irpalarmEventType=value;
			}

		} catch (Exception ex) {
			LOGGER.error("Exception is:" + ex.getStackTrace());
			irpalarmEventType = "0";
			return irpalarmEventType;
		}
		return irpalarmEventType;
	}

	// ISBLADE EVENT TYPE
	public static String getisbladeAlarmClass(final String value) {
		String isbladeEventType = "";
		try {
             if(isbladeEventTypeMap.containsKey(value)){
            	 isbladeEventType=isbladeEventTypeMap.get(value);
             }else if(gsneventMap.containsKey(value)){
            	 isbladeEventType=gsneventMap.get(value);
             }
			
		} catch (Exception ex) {
			LOGGER.error("Exception is:", ex);
			isbladeEventType = "0";
			return isbladeEventType;
		}
		return isbladeEventType;
	}

	// WPP EVENT TYPE
	public static String getWPPEventType(final String value) {
		String eventType = "0";
		try {
			LOGGER.info("event type before wppMap:::"+value);
			if(gsneventMap.containsKey(value))
			{
				eventType=gsneventMap.get(value);
				LOGGER.info("event type after wppMap:::"+value);
			}
			
			
		} catch (Exception ex) {
			LOGGER.error("exception is", ex);
			eventType = "0";
			return eventType;
		}
		return eventType;
	}

	public static String getEFWSEventType(final String value) {
		String eventType="";
		if(itueventMap.containsKey(value))
		{
			eventType = itueventMap.get(value);
		}

		return eventType;
	}

	// IPMS
	public static String getipmsalarmEventType(final String value) {
		String ipmsalarmEventType = "0";
		if(Integer.parseInt(value.toString())>=2&&Integer.parseInt(value.toString())<=4||Integer.parseInt(value.toString())>=10&&Integer.parseInt(value.toString())<=19){
			ipmsalarmEventType=value;
		}
		return ipmsalarmEventType;
	}

	// NETRA
	public static String getnetraalarmEventType(final String value) {
		String netraalarmEventType = "0";
		if(Integer.parseInt(value.toString())>=2&&Integer.parseInt(value.toString())<=4
				||Integer.parseInt(value.toString())>=10&&Integer.parseInt(value.toString())<=11
				||Integer.parseInt(value.toString())>=15&&Integer.parseInt(value.toString())<=19){
			netraalarmEventType=value;
		}

		return netraalarmEventType;
	}


	// valIsFmCurrentAlarmClass
	// --------------------------------------------------------
	// Processing Alarm Class - To be set into Event Type
	// --------------------------------------------------------
	public static String getvalIsFmCurrentAlarmClassEventType(final int value) {
		String valIsFmCurrentAlarmClass ="0";
		if(valIsFmCurrentAlarmClass.contains(Integer.toString(value))){
			valIsFmCurrentAlarmClass=Integer.toString(value);
		}
		return valIsFmCurrentAlarmClass;
	}
	
	public static int translateEventTypeGgsn(final String eventName)
	{
		if((eventName.equalsIgnoreCase("Communication")) || (eventName.equalsIgnoreCase("Communications alarm")))
		{
			return 1;
		}
		else if((eventName.equalsIgnoreCase("Processing Error")) || (eventName.equalsIgnoreCase("Processing error alarm")))
		{
			return 2;
		}
		else if(eventName.equalsIgnoreCase("Environmental"))
		{
			return 3;
		}
		else if(eventName.equalsIgnoreCase("Quality of Service"))
		{
			return 4;
		}
		else if(eventName.equalsIgnoreCase("Hardware"))
		{
			return 5;
		}
		else{
			
		    }
		return 0;
	}



}
