package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;
import com.ericsson.oss.mediation.translator.model.HandleTimeTranslation;

public class EricssonAlarmIrpMibHandling {
	
	// returns the array of IF MIB alarms notifications
	private static final Logger LOGGER = LoggerFactory.getLogger(EricssonAlarmIrpMibHandling.class);

			@SuppressWarnings("deprecation")
			public SynchronizationEventNotification[] getEnterpriseNsMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int alarmIRPcount){
				int count=0;
				SynchronizationEventNotification notif[] = new SynchronizationEventNotification[alarmIRPcount];
				for(int cnt = 0; cnt < alarmIRPcount; cnt++)
				{

					System.out.println("NetraActionHandler::syncAlarmIrp() >>" + alarmIRPcount);
					System.out.println("COUNT [" +cnt +"]" );
					notif[count] = new SynchronizationEventNotification();
					notif[count].setFmEventType("ALARM");
					notif[count].addAdditionalAttribute("SNMPTrapOID","null");
					notif[count].addAdditionalAttribute("Specific","null");
					notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
					notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
					notif[count].addAdditionalAttribute("Enterprise","True");
					notif[count].addAdditionalAttribute("Generic","False");

					//Processing Alarm Event Type
					String type = "";
					try
					{
						type = snmpWalkMap.get("ALARM_TYPE").nextToken();
						System.out.println("alarmType = "+type);
						//Updated by XPRAKUD for TR HM55846
						switch(Integer.parseInt(type))
						{
						//case 1: 
						case 2:
							notif[count].setEventType("2"); //Communication Alarm
							break;
						case 3:
							notif[count].setEventType("3"); //Enviromental Alarm
							break;
							//case 5:
						case 4:
							notif[count].setEventType("4"); //Equipment Alarm
							break;
						case 6:
							notif[count].setEventType("6");//Administrative alarm
							break;
						case 7:
							notif[count].setEventType("7");//Switching or crossconnecting alarm
							break;
						case 9:
							notif[count].setEventType("9");//Performance event
							break;
						case 10:
							notif[count].setEventType("10"); //Processing Error Alarm
							break;
						case 11:
							notif[count].setEventType("11"); //Quality of service Alarm
							break;
						case 15:
							notif[count].setEventType("15"); //Integrity Violation
							break;
						case 16:
							notif[count].setEventType("16"); //operational Violation
							break;
						case 17:
							notif[count].setEventType("17"); //physical violation
							break;
						case 18:
							notif[count].setEventType("18"); //Security Service Violation
							break;
						case 19:
							notif[count].setEventType("19"); //Time Domain Violation
							break;
						default:                        
							notif[count].setEventType("0");//Unknown event type       
							break;
						}
					}

					catch(Exception e)
					{
						notif[count].setEventType("0");
						System.out.println("Exception in Processing alarmType = "+type);
					}

					//Processing AlarmId
					String a_nr="";
					try
					{
						a_nr = snmpWalkMap.get("ALARM_ID").nextToken();
						notif[count].setExternalEventId(a_nr);
						System.out.println("alarmId = "+a_nr);
					}
					catch(NoSuchElementException e)
					{
						a_nr = "0";
						notif[count].setExternalEventId(a_nr);
						System.out.println("alarmId = "+a_nr);
					}


					//Processing Alarm MoText
					String mo_text="";
					try
					{
						mo_text =  snmpWalkMap.get("ALARM_MO_CLASS ").nextToken();
						System.out.println("alarmMOClass = "+mo_text);
						notif[count].addAdditionalAttribute("managedObjectClass",mo_text);
					}
					catch(Exception e)
					{
						mo_text = "No Managed Object Class.";
						notif[count].addAdditionalAttribute("managedObjectClass",mo_text);
						System.out.println("alarmMOClass = "+mo_text);
					}


					//Processing MoInst
					String mo_Inst = "";
					try
					{
						mo_Inst = snmpWalkMap.get("ALARM_MO_INST").nextToken();
						System.out.println("alrmMOInst = "+mo_Inst);
						notif[count].setManagedObjectInstance(mo_Inst);
					}
					catch(Exception e)
					{
						System.out.println("alarmMOInst = "+mo_Inst);
					}



					//Processing Alarm Time

					/*
				NOTE:
				Alarms contain time in hex format:  07 D3 01 12 0D 1E 1E 00 2D 04 00
				Events contain time in this format: 2003-01-18;13:30:30.00:-4:00
					 */
					String Time="";
					try
					{ 
						Time = snmpWalkMap.get("ALARM_TIME").nextToken();
						System.out.println("Time in table is : " +Time.toString());
						if(Time.indexOf("-") != -1)//event
						{
							String Timezone = null;

							try {
								Timezone = HandleTimeTranslation.getEventTimeZone(Time);
								System.out.println("buildIrpAlarm::TimeZone is: " +Timezone);
							}catch (Exception ex)
							{
								System.out.println("Date Format Exception: getEventTimeZone!");
							}

							System.out.println("Time is" +HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time)));
							notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time))).toString());

							notif[count].setTimeZone(Timezone);
						}

						else //alarm
						{
							String Timezone = null;
							
								if(HandleTimeTranslation.getTimeZone(Time) !=null)
								{
								Timezone = HandleTimeTranslation.getTimeZone(Time);
								System.out.println("TimeZone is: " +Timezone);
								}
								else
									Timezone = "UTC";							

							System.out.println("Time is" +HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time)));
							notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time))).toString());
							notif[count].setTimeZone(Timezone);

						}  

					}
					catch(Exception ex)
					{
						System.out.println("Date Format Exception: " + Time.toString());
						notif[count].setTimeZone("UTC");
						notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
						System.out.println("NetraActionHandler:synchronize():setTime(): "+HandleTimeTranslation.getCurrentTime());

					}


					//Processing AlarmText
					String a_text="";
					try
					{
						a_text = snmpWalkMap.get("ALARM_ATEXT").nextToken();
						notif[count].addAdditionalAttribute( "additionalText", a_text );
						notif[count].addAdditionalAttribute( "AdditionalText", a_text );
						System.out.println("alarmText = "+a_text);
					}
					catch(Exception e)
					{
						a_text = "No Additional Text in Alarm.";
						notif[count].addAdditionalAttribute( "additionalText", a_text );
						notif[count].addAdditionalAttribute( "AdditionalText", a_text );
						System.out.println("alarmText = "+a_text);
					}


					//Processing Alarm Probable Cause
					try
					{
						if(snmpWalkMap.get("ALARM_PC").hasMoreTokens())
						{
							int probableCause = Integer.parseInt(snmpWalkMap.get("ALARM_PC").nextToken());
							System.out.println("Ericsson Alarm IRP Probable Cause = " + probableCause );
							notif[count].setProbableCause(new Integer(probableCause).toString());
						}
						else
						{
							notif[count].setProbableCause("0");
						}
					}

					catch(Exception e)
					{
						notif[count].setProbableCause("0"); //Indeterminate
						System.out.println("Exception in processing alarmPC = Indeterminate ");
					}

					//Processing Alarm PerceivedSeverity

					String tempirpPS = "";
					try
					{
						tempirpPS = snmpWalkMap.get("ALARM_PS").nextToken();
						System.out.println("alarmPS = "+tempirpPS);
						notif[count].setSeverity(HandleAlarmSeverity.getPerceivedSeverity(snmpWalkMap.get("ALARM_PS").nextToken()));

					}
					catch(Exception e)
					{
						notif[count].setSeverity("INDETERMINATE");
						System.out.println("Exception while processing alarmPS = "+tempirpPS);
					}




					//Processing Alarm Specific Problem
					try
					{
						if(snmpWalkMap.get("ALARM_SP").hasMoreTokens())
						{
							String tempirpSP = snmpWalkMap.get("ALARM_SP").nextToken();
							System.out.println("Ericsson Alarm IRP Specific Problem = " + tempirpSP );
							notif[count].setSpecificProblem(tempirpSP);
						}
						else
						{
							notif[count].setSpecificProblem("0");
						}
					}
					catch(Exception e)
					{
						notif[count].setSpecificProblem("0");
						System.out.println("Exception in processing alarmSP");
					}

					count ++;
				}
				
				return notif;
				
			}

}
