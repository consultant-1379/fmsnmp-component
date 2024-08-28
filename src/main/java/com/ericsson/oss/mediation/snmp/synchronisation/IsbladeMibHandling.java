package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.Date;
import java.util.Map;
import java.util.StringTokenizer;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;
import com.ericsson.oss.mediation.translator.model.HandleEventType;
import com.ericsson.oss.mediation.translator.model.HandleTimeTranslation;

public class IsbladeMibHandling {
	
	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getIsbladeMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int isbladealarmCount){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[isbladealarmCount];
		


		StringTokenizer isFmCurrentAlarmBsId1 =snmpWalkMap.get("_IS_FM_CURRENT_ALARM_BSID");
		StringTokenizer isFmCurrentAlarmFaultId1 =snmpWalkMap.get("_IS_FM_CURRENT_ALARM_FAULTID");
		StringTokenizer isFmCurrentAlarmName1 =snmpWalkMap.get("_IS_FM_CURRENT_ALARM_NAME");
		StringTokenizer isFmCurrentAlarmNbName1 =snmpWalkMap.get("_IS_FM_CURRENT_ALARM_NBNAME"); 
		StringTokenizer isFmCurrentAlarmTime1 = snmpWalkMap.get("_IS_FM_CURRENT_ALARM_TIME");
		StringTokenizer isFmCurrentAlarmSender1 =snmpWalkMap.get("_IS_FM_CURRENT_ALARM_SENDER"); 
		StringTokenizer isFmCurrentAlarmClass1 = snmpWalkMap.get("_IS_FM_CURRENT_ALARM_CLASS");
		StringTokenizer isFmCurrentAlarmSeverity1 = snmpWalkMap.get("_IS_FM_CURRENT_ALARM_SEVERITY");
		StringTokenizer isFmCurrentAlarmCause1 =snmpWalkMap.get("_IS_FM_CURRENT_ALARM_CAUSE"); 
		StringTokenizer isFmCurrentAlarmX733Cause1 =snmpWalkMap.get("_IS_FM_CURRENT_ALARM_X733CAUSE"); 
		StringTokenizer isFmCurrentAlarmInformation1 =snmpWalkMap.get("_IS_FM_CURRENT_ALARM_INFORMATION"); 

		String txtVarIsFmCurrentAlarmName = "The name of the alarm. Maximum lengt 32 char. : ";
		String txtVarIsFmCurrentAlarmNbName = "The name of the notification used to report the fault. Maximum lengt 32 char. : ";
		String txtVarIsFmCurrentAlarmBsId = "The identity of the blade system that reported the fault. Maximum lengt 32 char. : ";
		String txtVarIsFmCurrentAlarmFaultId = "An identity that uniquely identifies a fault. The fault identity is also used as the index of the table. : ";
		String txtVarIsFmCurrentAlarmTime = "The time the fault was reported. : ";
		String txtVarIsFmCurrentAlarmSender = "The name of the alarm or event sending object. : ";
		String txtVarIsFmCurrentAlarmClass = "The class of the alarm. Communications, equipment etc. : ";
		String txtVarIsFmCurrentAlarmSeverity = "The perceived severity of the fault causing the alarm. Major, minor etc : ";
		String txtVarIsFmCurrentAlarmCause = "A textual description of the probable cause of the alarm. : ";
		String txtVarIsFmCurrentAlarmX733Cause = "The probable cause of the alarm expressed as an enum according to X.733, M.3100 or I.610 : ";
		String txtVarIsFmCurrentAlarmInformation = "Additional information pin-pointing the problem. : ";



		for(int cnt = 0; cnt < isbladealarmCount; cnt++) // walk through all the entries
		{

			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");


			//Updated by XWORAMJ - To fix the TR HG82877

			/* Code is updated to process the Synchronization of ISBLADE, if the Alarm attributes
				return NULL value/Empty string. Default value is set to the attributes which returns NULL
				and Synchronization continues...*/
			// ---------------------------------------------------------------
			//Processing Alarm BS ID - To be set into Managed Object Instance
			// ---------------------------------------------------------------
			if(isFmCurrentAlarmBsId1.hasMoreTokens() && isFmCurrentAlarmFaultId1.hasMoreTokens())
			{
				String valIsFmCurrentAlarmBsId = isFmCurrentAlarmBsId1.nextToken();
				String  valIsFmCurrentAlarmFaultId = isFmCurrentAlarmFaultId1.nextToken();
				if (valIsFmCurrentAlarmBsId.equals("No value") && valIsFmCurrentAlarmFaultId.equals("No value"))
				{
					System.out.println("No Managed Object Instance value found");
				}
				else
				{
					notif[count].setManagedObjectInstance(new StringBuffer(valIsFmCurrentAlarmBsId + ",").append(valIsFmCurrentAlarmFaultId).toString());
				}
			}
			else
			{
				System.out.println("No Managed Object Instance Token found");
			}


			// --------------------------------------------------------
			// Processing Alarm Name - To be set into Specific Problem
			// --------------------------------------------------------
			if(isFmCurrentAlarmName1.hasMoreTokens())
			{
				String valIsFmCurrentAlarmName = isFmCurrentAlarmName1.nextToken();
				if (valIsFmCurrentAlarmName.equals("No value"))
				{
					System.out.println("No Specific Problem value found");
					notif[count].setSpecificProblem("No Specific Problem value found");
				}
				else
				{
					notif[count].setSpecificProblem(valIsFmCurrentAlarmName);
				}
			}
			else
			{
				String valIsFmCurrentAlarmName = "No Specific Problem value found";

			}

			// -------------------------------------------------------------
			//Processing Alarm NB Name - To be set into Additional Attribute
			// --------------------------------------------------------------
			if(isFmCurrentAlarmNbName1.hasMoreTokens())
			{
				String valIsFmCurrentAlarmNbName = isFmCurrentAlarmNbName1.nextToken();
				if (valIsFmCurrentAlarmNbName.equals("No value"))
				{
					System.out.println("No AlarmNBName value found");
					notif[count].addAdditionalAttribute("isFmCurrentAlarmNbName","No AlarmNBName value found");
				}
				else
				{
					notif[count].addAdditionalAttribute("isFmCurrentAlarmNbName",new StringBuffer(txtVarIsFmCurrentAlarmNbName).append(valIsFmCurrentAlarmNbName).toString());
				}
			}
			else
			{
				String valIsFmCurrentAlarmNbName = "No AlarmNBName token found";
			}

			// --------------------------------------------------------
			// Processing Alarm Time - To be set into Alarm Time
			// --------------------------------------------------------
			if(isFmCurrentAlarmTime1.hasMoreTokens())
			{
				String valIsFmCurrentAlarmTime = isFmCurrentAlarmTime1.nextToken();

				// Set isFmCurrentAlarmTime
				// Set this attribute to Event Time in FM
				String time = "";
				try
				{
					time = valIsFmCurrentAlarmTime;
					System.out.println(" ISBLADE Action Handler => Event time mapping started...");

					// ---------------------------------------------------------------------
					// !!!NOTE!!!
					// Alarms contain time in hex format:  0x07D301120D1E1E002D0400
					// SNMP SMT interpretes it as 07 D3 01 12 0D 1E 1E 00 2D 04 00
					// Events contain time in this format: 2003-01-18;13:30:30.00:-4:00
					// ---------------------------------------------------------------------

					System.out.println(" Resultant Time String = " + time);

					if(time.indexOf("-") != -1) //Event
					{
						String timeZone = null;

						try
						{
							timeZone =HandleTimeTranslation.getEventTimeZone(time);
							System.out.println("  Event TimeZone = " + timeZone);
						}
						catch (Exception ex)
						{
							System.out.println(" ISBLADE Action Handler :: Exception while formatting date :: getEventTimeZone()");
							ex.printStackTrace();
						}

						System.out.println(" Event Time  = " + HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(time)));
						notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(time))).toString());
						notif[count].setTimeZone(timeZone);

					}
					else // Alarm
					{
						String timeZone = null;
						try
						{
							timeZone = HandleTimeTranslation.getTimeZone(time);
							System.out.println(" ISBLADE Action Handler :: buildISBLADEAlarm :: Alarm TimeZone = " + timeZone);
						}
						catch (Exception ex)
						{
							System.out.println(" ISBLADE Action Handler :: Exception while formatting date :: getTimeZone()");
						}

						System.out.println(" Alarm Time = " + HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(time)));
						notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(time))).toString());
						notif[count].setTimeZone(timeZone);

						/*
								Object ID: .1.3.6.1.4.1.193.82.1.8.1.4.11
								STRING: 2003-1-2,16:23:47.0,+1:0
								This is in HEX e.g.
								07 D3 01 12 0D 1E 1E 00 2D 04 00 = 2003-01-18;13:30:30.00:-4:00
						 */
					}
				}
				catch(Exception ex)
				{
					System.out.println(" ISBLADE Action Handler :: buildISBLADEAlarm :: Date Format Exception: " + time);
					notif[count].setTimeZone("UTC");
					notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
					System.out.println(" ISBLADE Action Handler :: buildISBLADEAlarm() :: setTime() :: " + HandleTimeTranslation.getCurrentTime());
				}
			}
			else
			{
				System.out.println("No Time token value found");
				notif[count].setTimeZone("UTC");
				notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());

			}

			// -------------------------------------------------------------
			//Processing Alarm Sender - To be set into Additional Attribute
			// -------------------------------------------------------------
			if(isFmCurrentAlarmSender1.hasMoreTokens())
			{
				String valIsFmCurrentAlarmSender = isFmCurrentAlarmSender1.nextToken();
				if (valIsFmCurrentAlarmSender.equals("No value"))
				{
					System.out.println("No ManagedObjectClass value found");
					notif[count].addAdditionalAttribute("ManagedObjectClass","No ManagedObjectClass value found");
				}
				else
				{
					notif[count].addAdditionalAttribute("ManagedObjectClass",valIsFmCurrentAlarmSender);
				}

			}
			else
			{
				String valIsFmCurrentAlarmSender = "No ManagedObjectClass token found";
			}

			// --------------------------------------------------------
			// Processing Alarm Class - To be set into Event Type
			// --------------------------------------------------------
			if(isFmCurrentAlarmClass1.hasMoreTokens())
			{
				System.out.println("Integer.parseInt(isFmCurrentAlarmClass1.nextToken().trim():"+Integer.parseInt(isFmCurrentAlarmClass1.nextToken().trim()));

				try
				{
					notif[count].setEventType(HandleEventType.getvalIsFmCurrentAlarmClassEventType(Integer.parseInt(isFmCurrentAlarmClass1.nextToken().trim())));
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					System.out.println(" Exception in decoding the event type Setting to unknown");
					notif[count].setEventType("0");//Unknown Event type
				}

			}
			else
			{
				System.out.println("No Alarm Class token found");
			}

			// -------------------------------------------------------------
			// Processing Alarm Severity - To be set into Severity
			// -------------------------------------------------------------
			if(isFmCurrentAlarmSeverity1.hasMoreTokens())
			{
				try
				{
					int valIsFmCurrentAlarmSeverity = Integer.parseInt(isFmCurrentAlarmSeverity1.nextToken().trim());
					notif[count].setSeverity(HandleAlarmSeverity.getIntegerPerceivedSeverity(valIsFmCurrentAlarmSeverity));
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					System.out.println(" Exception in decoding the Severity setting");
					notif[count].setSeverity("INDETERMINATE");
				}

			}
			else
			{
				System.out.println("No Severity token found");
				notif[count].setSeverity("INDETERMINATE");
			}


			// -------------------------------------------------------------
			// Processing Alarm Cause - To be set into Additional Attribute
			// -------------------------------------------------------------
			if(isFmCurrentAlarmCause1.hasMoreTokens())
			{
				String valIsFmCurrentAlarmCause = isFmCurrentAlarmCause1.nextToken();
				if (valIsFmCurrentAlarmCause.equals("No value"))
				{
					System.out.println("No AlarmCause value found");
					notif[count].addAdditionalAttribute("isFmCurrentAlarmCause","No AlarmCause value found");
				}
				else
				{
					notif[count].addAdditionalAttribute("isFmCurrentAlarmCause", new StringBuffer(txtVarIsFmCurrentAlarmCause).append(valIsFmCurrentAlarmCause).toString());
				}
			}
			else
			{
				System.out.println("No AlarmCause Token found");
			}

			// -------------------------------------------------------------
			//Processing Alarm x733Cause - To be set into Probable Cause
			// -------------------------------------------------------------
			if(isFmCurrentAlarmX733Cause1.hasMoreTokens())
			{
				try
				{
					int valIsFmCurrentAlarmX733Cause = Integer.parseInt(isFmCurrentAlarmX733Cause1.nextToken().trim());
					notif[count].setProbableCause(new Integer(valIsFmCurrentAlarmX733Cause).toString());
				}
				catch(Exception ex)
				{
					ex.printStackTrace();
					System.out.println(" Exception in decoding the Probable Cause  setting");
					notif[count].setProbableCause("0"); //Indeterminate
				}
			}
			else
			{
				System.out.println("No AlarmX733Cause Token found");
			}

			// --------------------------------------------------------------------
			// Processing Alarm Information - To be set into Additional Attribute
			// --------------------------------------------------------------------
			if(isFmCurrentAlarmInformation1.hasMoreTokens())
			{
				String valIsFmCurrentAlarmInformation = isFmCurrentAlarmInformation1.nextToken();
				if (valIsFmCurrentAlarmInformation.equals("No value"))
				{
					System.out.println("No Alarm Information value found");
					notif[count].addAdditionalAttribute("isFmCurrentAlarmInformation","No Alarm Information value found");
				}
				else
				{
					notif[count].addAdditionalAttribute("isFmCurrentAlarmInformation", new StringBuffer(txtVarIsFmCurrentAlarmInformation).append(valIsFmCurrentAlarmInformation).toString());
				}
			}
			else
			{
				System.out.println("No Alarm Information Token found");
			}

			count++;

		} // for
	
		
		return notif;
		
	}

}
