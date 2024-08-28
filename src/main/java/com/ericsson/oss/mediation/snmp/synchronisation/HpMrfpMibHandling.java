package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;
import com.ericsson.oss.mediation.translator.model.HandleTimeTranslation;

public class HpMrfpMibHandling {
	
	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getHpmrfpAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int mrfpAlarms){
		int count=0;
		SynchronizationEventNotification notif[] = new SynchronizationEventNotification[mrfpAlarms];
		System.out.println(" Fetching contents of MRFP Alarm Table");

		for(int mrfpcount = 0; mrfpcount < mrfpAlarms ;mrfpcount++)
		{
			System.out.println("************************* Count =  " +count);
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");


			// 1.setting  Specific Problem based on the TrapName (or) Trap OID
			String trapname,tempTrapOID = "";
			StringBuffer str = new StringBuffer();
			try
			{
				trapname = snmpWalkMap.get("trapName").nextToken();
				tempTrapOID = snmpWalkMap.get("trapOid").nextToken();
				System.out.println("Trap name for MRFP MIB is " + trapname);
				System.out.println("Trap OID for MRFP is " + tempTrapOID);

				if (trapname.equals("mrfpExecutionStateChanged") || tempTrapOID.startsWith(".1.3.6.1.4.1.11.2.29.2.7.1.1.2"))
				{
					notif[count].setSpecificProblem("92"); //MRFP execution state has changed
				}
				else if (trapname.equals("mrfpConfigurationServerStateChanged") || tempTrapOID.startsWith(".1.3.6.1.4.1.11.2.29.2.7.1.1.3"))
				{
					notif[count].setSpecificProblem("93"); //MRFP configuration server state has changed
				}
				else if (trapname.equals("mrfpConfigurationFileStateChanged") || tempTrapOID.startsWith(".1.3.6.1.4.1.11.2.29.2.7.1.1.4"))
				{
					notif[count].setSpecificProblem("94"); //MRFP configuration file has changed
				}
				else if (trapname.equals("mrfpQualityOfService") || tempTrapOID.startsWith(".1.3.6.1.4.1.11.2.29.2.7.1.1.5"))
				{
					notif[count].setSpecificProblem("95"); //MRFP status is overload
				}
				else if (trapname.equals("asrTtsConnectionStateChanged") || tempTrapOID.startsWith(".1.3.6.1.4.1.11.2.29.2.7.1.1.6"))
				{
					notif[count].setSpecificProblem("96"); //State of the connection to the current ASR/TTS server changed
				}
				else if (trapname.equals("sdspceConnectionStateChanged") || tempTrapOID.startsWith(".1.3.6.1.4.1.11.2.29.2.7.1.1.7"))
				{
					notif[count].setSpecificProblem("97"); //State of the connection to the current Soft DSP Conference Engine (video) server changed
				}
				else if (trapname.equals("licensingStateChanged") || tempTrapOID.startsWith(".1.3.6.1.4.1.11.2.29.2.7.1.1.8"))
				{
					notif[count].setSpecificProblem("98"); //State of the Mrfp Licensing state changed
				}
				else if (trapname.equals("sdspceQualityofService") || tempTrapOID.startsWith(".1.3.6.1.4.1.11.2.29.2.7.1.1.9"))
				{
					notif[count].setSpecificProblem("99"); //Soft DSP Conference Engine (video) overload status
				}
				else
				{
					notif[count].setSpecificProblem("10118"); //No Specific problem matched
				}
			}
			catch(Exception e)
			{
				notif[count].setSpecificProblem("10118"); //No Specific Problem matched
			}


			//3.Setting Perceived Severity based on trapPerceivedSeverity
			try
			{
				System.out.println("Setting Ps");
				String  TrapPerceivedSeverity=snmpWalkMap.get("TrapPerceivedSeverity").nextToken();
				System.out.println("TrapPerceivedSeverity is"+TrapPerceivedSeverity);
				notif[count].setSeverity(HandleAlarmSeverity.getmrfperceivedSeverity(TrapPerceivedSeverity));

			}
			catch(NoSuchElementException e)
			{
				// Perceived Severity will be set to INDETERMINATE If not found in the Alarm Table
				notif[count].setSeverity("INDETERMINATE");
			}



			//4. Setting trapEventTime to Alarm time
			String time = "";

			try
			{

				time =snmpWalkMap.get("TrapEventTime").nextToken();
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
						System.out.println(" HPMRFP ActionHandler :: Event TimeZone = " + timeZone);
					}
					catch (Exception ex)
					{
						System.out.println(" HPMRFP Action Handler :: Exception while formatting date :: getEventTimeZone()");
						ex.printStackTrace();
					}

					System.out.println(" Event Time  = " + HandleTimeTranslation._timeFormatter.format((HandleTimeTranslation.createEventDateAndTime(time))));
					notif[count].setTime(new Date( HandleTimeTranslation._timeFormatter.format((HandleTimeTranslation.createEventDateAndTime(time)))).toString());
					notif[count].setTimeZone(timeZone);

				}
				else // Alarm
				{
					String timeZone = null;
					try
					{
						timeZone = HandleTimeTranslation.getTimeZone(time);
						System.out.println(" Alarm TimeZone = " + timeZone);
					}
					catch (Exception ex)
					{
						System.out.println(" HPMRFP Action Handler :: Exception while formatting date :: getTimeZone()");
					}

					System.out.println(" Alarm Time = " + HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(time)));
					notif[count].setTime( new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(time))).toString());
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



			//5: Setting trapTrendIndication to Add Info
			String tempTrendIndication = "";
			try
			{
				tempTrendIndication = snmpWalkMap.get("TrapTrendIndication").nextToken();
				System.out.println("TrendIndication for MRFP is " + tempTrendIndication);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nAdd Info").append("\nTrendIndication:").append(tempTrendIndication).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing TrapTrendIndication");
			}

			//6: Setting trapNotificationIdentifier to	FDN/ObjectOfRefrence
			String tempTrapNotIden = "";
			try
			{
				tempTrapNotIden = snmpWalkMap.get("TrapNotificationIdentifier").nextToken();
				System.out.println("Trap Notification Identifier for MRFP is " + tempTrapNotIden);
				notif[count].setManagedObjectInstance(tempTrapNotIden);
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing Trap Notification Identifier");
			}


			//7: Setting trapEventType to	Event Type
			try
			{
				int trapET = Integer.parseInt(snmpWalkMap.get("TrapEventType").nextToken().trim());
				System.out.println("Event Type for MRFP is " + trapET);
				notif[count].setEventType(new Integer(trapET).toString());

			}
			catch(Exception e)
			{
				System.out.println("Exception while processing Trap Event Type");
			}


			//8: Setting trapOtherEventType to Add Info
			String tempTrapOtherET = "";
			try
			{
				tempTrapOtherET = snmpWalkMap.get("TrapOtherEventType").nextToken();
				System.out.println("TrapOtherEventType for MRFP is " + tempTrapOtherET);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nOther Event Type:").append(tempTrapOtherET).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing TrapOtherEventType");
			}


			// 9. Setting  trapAdditionalInformation to	Add Info
			String tempTrapAddInfo = "";
			try
			{
				tempTrapAddInfo = snmpWalkMap.get("TrapAdditionalInformation").nextToken();
				System.out.println("TrapAddInfo for MRFP is " + tempTrapAddInfo);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nTrap Additional Info:").append(tempTrapAddInfo).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing TrapAddInfo");
			}


			// 10. Setting trapProbableCause to	ProbableCause
			try
			{
				int tempProbCause = Integer.parseInt(snmpWalkMap.get("TrapProbableCause").nextToken().trim());
				notif[count].setProbableCause(new Integer(tempProbCause).toString());
			}
			catch(Exception ex)
			{
				ex.printStackTrace();
				System.out.println(" Exception in decoding the Probable Cause  setting");
				notif[count].setProbableCause("0"); //Unknown
			}


			//11: Setting trapProposedRepairActions to AddInfo
			String tempPRA = "";
			try
			{
				tempPRA = snmpWalkMap.get("TrapProposedRepairActions").nextToken();
				System.out.println("PRA for MRFP is " + tempPRA);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nProposed Repair Action:").append(tempPRA).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing PRA");
			}


			// 12. Setting  trapCorrelatedNotifications to Add Info
			String tempTrapCorrNot = "";
			try
			{
				tempTrapCorrNot = snmpWalkMap.get("TrapCorrelatedNotifications").nextToken();
				System.out.println("TrapCorrNotification for MRFP is " + tempTrapCorrNot);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nTrap Correlated Notification:").append(tempTrapCorrNot).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing TrapCorrNotification");
			}


			//13. Setting trapAdditionalText to	Add Info
			String tempTrapAddText = "";
			try
			{
				tempTrapAddText = snmpWalkMap.get("TrapAdditionalText").nextToken();
				System.out.println("Trap additional text for MRFP is " + tempTrapAddText);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nTrap Additional Text:").append(tempTrapAddText).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing Trap additional text");
			}

			//14: Setting trapManagedObject to	Add Info
			String tempTrapMO = "";
			try
			{
				tempTrapMO = snmpWalkMap.get("TrapManagedObject").nextToken();
				System.out.println("Trap Managed Object text for MRFP is " + tempTrapMO);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nTrap Managed Object:").append(tempTrapMO).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing Trap Managed Object ");
			}


			//15: Setting trapI18NAlarmText to	Problem text
			String tempTrapNAT = "";
			try
			{
				tempTrapNAT = snmpWalkMap.get("TrapI18NAlarmText").nextToken();
				System.out.println("TrapI18NAlarmText for MRFP is " + tempTrapNAT);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nTrap I18NAlarm Text").append(tempTrapNAT).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing TrapI18NAlarmText ");
			}


			//16: Setting trapOldState	to Add Info
			String tempTrapOldstate = "";
			try
			{
				tempTrapOldstate = snmpWalkMap.get("TrapOldState").nextToken();
				System.out.println("TrapOldState for MRFP is " + tempTrapOldstate);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nTrap Old State").append(tempTrapOldstate).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing TrapOldState ");
			}

			//17: Setting trapNewState to	Add Info
			String tempTrapNewState = "";
			try
			{
				tempTrapNewState = snmpWalkMap.get("TrapNewState").nextToken();
				System.out.println("TrapNewState for MRFP is " + tempTrapNewState);
				notif[count].addAdditionalAttribute("additionalText",str.append("\nTrap New State").append(tempTrapNewState).toString());
			}
			catch(Exception e)
			{
				System.out.println("Exception while processing TrapNewState ");
			}


			//Increment the count
			count++;
		}

	
		
		return notif;
	}

}
