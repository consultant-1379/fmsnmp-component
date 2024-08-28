package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.fm.component.SnmpSupervisionProducer;
import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.constant.TranslationConstant;
import com.ericsson.oss.mediation.translator.model.*;

public class AxdFaultMibHandling {
	
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(AxdFaultMibHandling.class);

	
	@SuppressWarnings({ "unused", "deprecation" })
	public SynchronizationEventNotification[] getAxfFaultIDMibAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int alarmFaultIdCount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[alarmFaultIdCount];
		notif[count] = new SynchronizationEventNotification();
		String problemText[]=new String[alarmFaultIdCount];
		String stinfor[]=new String[alarmFaultIdCount];
		String stCause[]=new String[alarmFaultIdCount];
		String stdata[]=new String[alarmFaultIdCount];
		String eventSender1[]=new String[alarmFaultIdCount];
		String faultId[]=new String[alarmFaultIdCount];
		String ObjectId[]=new String[alarmFaultIdCount];        	
		String axdAlarmname[]=new String[alarmFaultIdCount];
		if ( alarmFaultIdCount > 0 )
		{

			String stralarmTime  = snmpWalkMap.get("axdAlarmTime").toString();
			String streventSender = snmpWalkMap.get("eventSender").toString();
			String stralarmClassObj =snmpWalkMap.get("axdAlarmClass").toString();
			String stralarmAddDataObj = snmpWalkMap.get("axdAlarmAddData").toString();
			String stralarmPS = snmpWalkMap.get("axdAlarmPS").toString();
			String stralarmInfo  =  snmpWalkMap.get("axdAlarmInformation").toString();
			String stralarmPC  = snmpWalkMap.get("axdAlarmX733Cause").toString();
			String stralarmCause  = snmpWalkMap.get("axdAlarmCause").toString();
			String stralarmId = snmpWalkMap.get("axdAlarmFaultId").toString();



			StringTokenizer alarmTime = snmpWalkMap.get("axdAlarmTime");
			StringTokenizer sender  = snmpWalkMap.get("eventSender");
			StringTokenizer alarmClassObj = snmpWalkMap.get("axdAlarmClass"); 
			StringTokenizer alarmAddDataObj = snmpWalkMap.get("axdAlarmAddData");
			StringTokenizer alarmPS = snmpWalkMap.get("axdAlarmPS");
			StringTokenizer alarmInfo = snmpWalkMap.get("axdAlarmInformation");
			StringTokenizer alarmPC = snmpWalkMap.get("axdAlarmX733Cause");
			StringTokenizer alarmCause = snmpWalkMap.get("axdAlarmCause");
			StringTokenizer alarmId = snmpWalkMap.get("axdAlarmFaultId");

			LOGGER.info("Fetching  " +" alarmFaultName.countTokens()" + alarmFaultIdCount);
			LOGGER.info("Fetching  " + alarmTime.countTokens() + "alarmTime Tokens.");
			LOGGER.info("Fetching  " + alarmClassObj.countTokens() + "alarmClassObj Tokens.");
			LOGGER.info("Fetching  " + alarmAddDataObj.countTokens() + "alarmAddDataObj Tokens.");
			LOGGER.info("Fetching  " + alarmPS.countTokens() + "alarmPS Tokens.");
			LOGGER.info("Fetching  " + alarmInfo.countTokens() + "alarmInfo Tokens.");
			LOGGER.info("Fetching  " + alarmPC.countTokens() + "alarmPC Tokens.");
			LOGGER.info("Fetching  " + alarmCause.countTokens() + "alarmCause Tokens.");
			LOGGER.info("Fetching  " + alarmId.countTokens() + "axdAlarmFaultId Tokens.");
			LOGGER.info("Fetching  " + sender.countTokens() + "axdsender Tokens.");

			StringBuffer sbAlarmPS=new StringBuffer();
			StringTokenizer alIn;
			String temp = "",token = "",axdSevToken = "";
			StringBuffer faultIDBuf = new StringBuffer();
			StringTokenizer stAlarmPS=null;

			try
			{
				alIn = new StringTokenizer(sbAlarmPS.toString(),"\274");
				while (alIn.hasMoreTokens())
				{
					temp = "";token = "";axdSevToken = "";

					token = alIn.nextToken();
					LOGGER.info("token >>"+token);

					axdSevToken = token.substring(TranslationConstant.severityOID.length());
					LOGGER.info("axdSevToken >>"+axdSevToken);

					temp = axdSevToken.substring(axdSevToken.lastIndexOf(".")+1,axdSevToken.length());
					LOGGER.info("temp >>"+temp);

					faultIDBuf.append(temp+"\274");
				}

				LOGGER.info("faultIDBuf >>"+faultIDBuf.toString());
				stAlarmPS = new StringTokenizer(faultIDBuf.toString(),"\274");
			}
			catch(Exception e)
			{
				LOGGER.info("Exception in building tokens >>"+e.toString());
			}


			for(int cnt = 0; cnt < alarmFaultIdCount ;count++)
			{


				LOGGER.info("************************* Count =  " +count);
				String alarmname="";
				try 
				{
					axdAlarmname[count]=snmpWalkMap.get("axdAlarmName").nextToken();
					LOGGER.info("AlarmName="+axdAlarmname[count]);
					alarmname=axdAlarmname[count];
					//alarmname = alarmFaultName.nextToken();

				}
				catch (Exception e)
				{
					alarmname = "No Alarm Name received " +count;
				}

				notif[count] = new SynchronizationEventNotification();
				notif[count].setFmEventType("ALARM");
				notif[count].addAdditionalAttribute("SNMPTrapOID","null");
				notif[count].addAdditionalAttribute("Specific","null");
				notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
				notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
				notif[count].addAdditionalAttribute("Enterprise","True");


				try
				{
					if(stAlarmPS.hasMoreTokens())
					{
						String eventId = stAlarmPS.nextToken();
						notif[count].setExternalEventId(eventId);
					}
				}
				catch(Exception e)
				{
					LOGGER.info("Error in setExternalEventId >>"+e.toString());
				}

				try
				{
					// External Event Id is set to AlarmId instead of AlarmName 
					if (alarmId.hasMoreTokens()) 
					{ 

						faultId[count]=alarmId.nextToken();
						System.out.println("FaultId=" +faultId[count]);
						ObjectId[count]="OriginalAlarmID="+faultId[count];
						System.out.println("OriginalAlarmID=" +ObjectId[count]);
						//notif[count].setExternalEventId(faultId[count]);

						//notif[count].setExternalEventId(alarmId.nextToken());
						System.out.println("************************* AlarmName =  " +alarmname);
					}
					else
					{
						Integer j=new Integer(count+1); 
						ObjectId[count]="OriginalAlarmID="+j;
						//notif[count].setExternalEventId(j.toString());
						System.out.println("External Event ID set to lastIndex");
					} 

				}
				catch(NoSuchElementException e)
				{
					//notif[count].setExternalEventId("0");
				}

				try
				{
					System.out.println(" Count for " +count+"second");


					System.out.println("add1");
					stinfor[count]="Information="+alarmInfo.nextToken() +"\n";
					System.out.println("add2");
					System.out.println("strInfo"+stinfor[count]);
				}
				catch(NoSuchElementException e)
				{
					System.out.println("Infor catch");
					//String mo_text = "No Information Obtained";
					//stinfor[count]="No Information Received="+mo_text +"\n";
				}	
				try
				{ 
					System.out.println("add3");
					stCause[count]="Cause="+ alarmCause.nextToken()+"\n";
					System.out.println("add4");
					System.out.println("Causest="+stCause[count]);
				}
				catch(NoSuchElementException e)      
				{
					System.out.println("Cause catch");
					String mo_text = "No Casue Obtained";
					stCause[count]="Cause="+mo_text +"\n";

				}

				try
				{
					System.out.println("add5");
					stdata[count]="AdditionalData="+alarmAddDataObj.nextToken()+"\n";
					System.out.println("add6");
					System.out.println("stdata="+stdata[count]);
				}
				catch(NoSuchElementException e)      
				{
					System.out.println("Data catch");
					String mo_text = "No Additional Data Obtained";
					stCause[count]="Additional Data="+mo_text +"\n";

				}  

				//// Composing the Problem Text=Information+Cause+FaultId
				try
				{

					System.out.println("add7");
					problemText[count]="AlarmName="+axdAlarmname[count]+"\n"+stinfor[count]+stCause[count]+ObjectId[count];
					notif[count].addAdditionalAttribute("additionalText",problemText[count]);
					System.out.println("add8");
				}
				catch(NoSuchElementException e)
				{
					System.out.println(e.getMessage());
					System.out.println("add9");
					System.out.println("addt");
					problemText[count]="Unable to Obtain the Problem Text Information " +"\n";

				}

				try
				{
					if(alarmname != null && alarmname.length()>0)
					{
						notif[count].setSpecificProblem(alarmname);
					}
				}
				catch(Exception e)
				{
					System.out.println("Exception in setting SpecificProblem >>"+e.toString());
				}



				try
				{
					String severity=alarmPS.nextToken();
					notif[count].setSeverity(HandleAlarmSeverity.getPerceivedSeverity(severity));

				}
				catch(NoSuchElementException e)
				{
					notif[count].setSeverity("INDETERMINATE");
				}
				catch(Exception e)
				{
					notif[count].setSeverity("INDETERMINATE");
				}
				/**
				 * AXD translate map updated properly for CR819.
				 * No need to manipulate the probable cause value.
				 */
				try
				{
					int probableCause = 0;
					probableCause =Integer.parseInt(alarmPC.nextToken());
					notif[count].setProbableCause(new Integer(probableCause).toString());

				}
				catch(NoSuchElementException e)
				{
					notif[count].setProbableCause("0");
				}
				catch(Exception e)
				{
					notif[count].setProbableCause("0");
				}

				String Time="";
				try
				{
					/*
			              NOTE:
			              Alarms contain time in hex format:  07 D3 01 12 0D 1E 1E 00 2D 04 00
			              Events contain time in this format: 2003-01-18;13:30:30.00:-4:00
					 */
					Time = alarmTime.nextToken();
					if(Time.indexOf("-") != -1)//event
					{
						notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time))).toString());
						notif[count].setTimeZone(HandleTimeTranslation.getEventTimeZone(Time));
					}
					else //alarm
					{

						notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time))).toString());

						notif[count].setTimeZone(HandleTimeTranslation.getTimeZone(Time));
					}
				}
				catch(Exception ex)
				{
					System.out.println("Date Format Exception: " + Time);
					notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
					System.out.println("AXDActionHandler:synchronize():setTime(): "+HandleTimeTranslation.getCurrentTime());

				}

				try
				{
					String eventtype=alarmClassObj.nextToken();
					notif[count].setEventType(HandleEventType.getWPPEventType(eventtype));

				}
				catch(NoSuchElementException e)
				{
					notif[count].setEventType("0");
				}
				catch(Exception e)
				{
					notif[count].setEventType("0");
				}
				System.out.println("Going to Next loop"+count);	
			}

		}

	
		return notif;
	}

}
