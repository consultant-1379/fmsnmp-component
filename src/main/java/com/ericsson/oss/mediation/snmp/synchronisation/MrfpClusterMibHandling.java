package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;
import com.ericsson.oss.mediation.translator.model.HandleTimeTranslation;

public class MrfpClusterMibHandling {
	
	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getmrfpClusterAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int mrfpClusterAlarms){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[mrfpClusterAlarms];

		// Prints Entries in MRFPCLUSTER-MIB Table

		for(int mrfpcount = 0; mrfpcount < mrfpClusterAlarms ;mrfpcount++)
		{
			System.out.println("************************* Count =  " +count);
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");

			// 1.setting  Specific Problem
			String specificProblem = "";
			StringBuffer str = new StringBuffer();

			try
			{
				specificProblem = snmpWalkMap.get("sI18NAlarmText").nextToken();

				System.out.println("Specific problem for MRFPCLUSTER-MIB is " + specificProblem);

				notif[count].setSpecificProblem(specificProblem);
			}
			catch(Exception e)
			{
				notif[count].setSpecificProblem("10118"); //No Specific problem matched
			}

			//3.Setting Perceived Severity based on stPerceivedSeverity
			try
			{
				String mrfseverity=snmpWalkMap.get("sPerceivedSeverity").nextToken();
				System.out.println("mrfseverity:"+mrfseverity);
				notif[count].setSeverity(HandleAlarmSeverity.getmrfperceivedSeverity(mrfseverity));

			}
			catch(Exception e)
			{
				notif[count].setSeverity("INDETERMINATE");
			}

			//4. Setting stEventTime to Alarm time
			String time = "";

			try
			{

				time = snmpWalkMap.get("sEventTime").nextToken();
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
						timeZone = HandleTimeTranslation.getEventTimeZone(time);
						System.out.println(" Event TimeZone = " + timeZone);
					}
					catch (Exception ex)
					{
						System.out.println("Exception while formatting date :: getEventTimeZone()");
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
						System.out.println("  Alarm TimeZone = " + timeZone);
					}
					catch (Exception ex)
					{
						System.out.println(" Exception while formatting date :: getTimeZone()");
					}

					System.out.println(" Alarm Time = " +HandleTimeTranslation. _timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(time)));
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
				System.out.println(" HPMRFP Action Handler Date Format Exception: " + time);
				notif[count].setTimeZone("UTC");
				notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
				System.out.println(" HPMRFP Action Handler setTime() :: " + HandleTimeTranslation.getCurrentTime());
			}

			//5: Setting stTrendIndication to Add Info
			String tempTrendIndication = "";

			try
			{
				tempTrendIndication =snmpWalkMap.get("sTrendIndication").nextToken();
				System.out.println("TrendIndication for MRFPCLUSTER-MIB is " + tempTrendIndication);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nAdd Info").append("\nTrendIndication:").append(tempTrendIndication).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing trendIndication");
			}

			//6: Setting stNotificationIdentifier to FDN/ObjectOfRefrence
			String tempTrapNotIden = "";
			try
			{
				tempTrapNotIden = snmpWalkMap.get("sNotificationIdentifier").nextToken();
				System.out.println("Notification Identifier for MRFPCLUSTER-MIB is " + tempTrapNotIden);
				notif[count].setManagedObjectInstance(tempTrapNotIden);
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing notificationIdentifier");
			}


			//7: Setting stEventType to Event Type
			try
			{
				int trapET = Integer.parseInt(snmpWalkMap.get("sEventType").nextToken().trim());
				System.out.println("Event Type for MRFPCLUSTER-MIB is " + trapET);
				notif[count].setEventType(new Integer(trapET).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing eventType");
				notif[count].setEventType("0");
			}


			//8: Setting stOtherEventType to Add Info
			String tempTrapOtherET = "";
			try
			{
				tempTrapOtherET = snmpWalkMap.get("sOtherEventType").nextToken();
				System.out.println("OtherEventType for MRFPCLUSTER-MIB is " + tempTrapOtherET);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nOther Event Type:").append(tempTrapOtherET).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing otherEventType");
			}

			// 9. Setting  trapAdditionalInformation to Add Info
			String tempTrapAddInfo = "";
			try
			{
				tempTrapAddInfo = snmpWalkMap.get("sAdditionalInformation").nextToken();
				System.out.println("AddInfo for MRFPCLUSTER-MIB is " + tempTrapAddInfo);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nAdditional Info:").append(tempTrapAddInfo).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing additionalInformation");
			}


			// 10. Setting stProbableCause to ProbableCause
			try
			{
				int tempProbCause = Integer.parseInt(snmpWalkMap.get("sProbableCause").nextToken().trim());
				notif[count].setProbableCause(new Integer(tempProbCause).toString());
			}
			catch(Exception ex)
			{
				System.out.println("Exception in setting probableCause");
				notif[count].setProbableCause("0"); //Unknown
			}


			//11: Setting stProposedRepairActions to AddInfo
			String tempPRA = "";
			try
			{
				tempPRA = snmpWalkMap.get("sProposedRepairActions").nextToken();
				System.out.println("PRA for MRFPCLUSTER-MIB is " + tempPRA);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nProposed Repair Action:").append(tempPRA).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing proposedRepairActions");
			}


			// 12. Setting  stCorrelatedNotifications to Add Info
			String tempTrapCorrNot = "";
			try
			{
				tempTrapCorrNot = snmpWalkMap.get("sCorrelatedNotifications").nextToken();
				System.out.println("CorrNotification for MRFPCLUSTER-MIB is " + tempTrapCorrNot);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nCorrelated Notification:").append(tempTrapCorrNot).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing correlatedNotifications");
			}


			//13. Setting stAdditionalText to	Add Info
			String tempTrapAddText = "";
			try
			{
				tempTrapAddText = snmpWalkMap.get("sAdditionalText").nextToken();
				System.out.println("additional text for MRFPCLUSTER-MIB is " + tempTrapAddText);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nAdditional Text:").append(tempTrapAddText).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing additionalText");
			}

			//14: Setting stManagedObject to Add Info
			String tempTrapMO = "";
			try
			{
				tempTrapMO = snmpWalkMap.get("sManagedObject").nextToken();
				System.out.println("Managed Object text for MRFPCLUSTER-MIB is " + tempTrapMO);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nManaged Object:").append(tempTrapMO).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing  Managed Object ");
			}

			//15: Setting stMrfpClusterNodeName to Problem text
			String clusterNodeName = "";
			try
			{
				clusterNodeName = snmpWalkMap.get("sMrfpClusterNodeName").nextToken();
				System.out.println("MrfpClusterNodeName for MRFPCLUSTER-MIB is " + clusterNodeName);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nMrfpClusterNodeName:").append(clusterNodeName).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing ClusterNodeName ");
			}


			//16: Setting stMrfpClusterNodeApplication to Add Info
			String clusterNodeApplication = "";
			try
			{
				clusterNodeApplication = snmpWalkMap.get("sMrfpClusterNodeApplication").nextToken();
				System.out.println("MrfpClusterNodeApplication for MRFP is " + clusterNodeApplication);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nMrfpClusterNodeApplication:").append(clusterNodeApplication).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing clusterNodeApplication ");
			}

			//17: Setting stMrfpClusterNodeOperOldState to Add Info
			String clusterNodeOperOldState = "";
			try
			{
				clusterNodeOperOldState = snmpWalkMap.get("sMrfpClusterNodeOperOldState").nextToken();
				System.out.println("MrfpClusterNodeOperOldState for MRFP is " + clusterNodeOperOldState);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nMrfpClusterNodeOperOldState:").append(clusterNodeOperOldState).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing clusterNodeOperOldState");
			}

			//18: Setting stMrfpClusterNodeOperNewState to Add Info
			String clusterNodeOperNewState = "";
			try
			{
				clusterNodeOperNewState =snmpWalkMap.get("sMrfpClusterNodeOperNewState") .nextToken();
				System.out.println("MrfpClusterNodeOperNewState for MRFP is " + clusterNodeOperNewState);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nMrfpClusterNodeOperNewState:").append(clusterNodeOperNewState).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing clusterNodeOperNewState");
			}

			//19: Setting stMrfpClusterNodeAdminState to Add Info
			String clusterNodeAdminState = "";
			try
			{
				clusterNodeAdminState = snmpWalkMap.get("stMrfpClusterNodeAdminState").nextToken();
				System.out.println("MrfpClusterNodeAdminState for MRFP is " + clusterNodeAdminState);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nMrfpClusterNodeAdminState:").append(clusterNodeAdminState).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing clusterNodeAdminState");
			}

			//Increment the count
			count++;
		}

	
		return notif;
		
	}

}
