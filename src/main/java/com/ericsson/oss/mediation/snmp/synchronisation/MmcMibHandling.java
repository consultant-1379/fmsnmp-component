package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.*;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.constant.MMCSpecificProblem;
import com.ericsson.oss.mediation.translator.constant.TranslationConstant;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;
import com.ericsson.oss.mediation.translator.model.HandleTimeTranslation;

public class MmcMibHandling {
	
	// returns the array of IF MIB alarms notifications

				@SuppressWarnings("deprecation")
				public SynchronizationEventNotification[] getMmcMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int mmccount,int ifAlarmsCount){
					int count=0;
					SynchronizationEventNotification notif[] = new SynchronizationEventNotification[mmccount];


					String a_nr = snmpWalkMap.get("mmsAlarmID").nextToken();
					StringTokenizer alarmOpState =snmpWalkMap.get("mmsSystemOpState");
					StringTokenizer alarmAdminState =snmpWalkMap.get("mmsSystemAdminState");
					StringTokenizer alarmOBjClass =snmpWalkMap.get("mmsAlarmManagedObjectClass");
					StringTokenizer alarmOBjInstance =snmpWalkMap.get("mmsAlarmManagedObjectInst");
					StringTokenizer alarmTime =snmpWalkMap.get("mmsAlarmTimeOfOccurance"); 
					StringTokenizer alarmType =snmpWalkMap.get("mmsAlarmType");
					StringTokenizer alarmProbableCause =snmpWalkMap.get("mmsAlarmProbableCause");
					StringTokenizer alarmPS =snmpWalkMap.get("mmsAlarmPS");
					StringTokenizer alarmSP =snmpWalkMap.get("mmsAlarmSP"); 

					System.out.println(this.getClass()+" Fetching contents of MMC alarm Table (1.3.6.1.4.1.193.91.1.3.4).");
					System.out.println(this.getClass()+"Fetched: alarmIdentity.countTokens() alarm entries."+mmccount);
					System.out.println(this.getClass()+"Fetched: " + alarmOpState.countTokens() + " alarm operational state entries.");
					System.out.println(this.getClass()+" Fetched: " + alarmAdminState.countTokens() + " alarm Adminstrative state entries.");
					System.out.println(this.getClass()+"Fetched: " + alarmOBjClass.countTokens() + " alarm OBjClass entries.");
					System.out.println(this.getClass()+"Fetched: " + alarmOBjInstance.countTokens() + " alarm OBjInstance entries.");
					System.out.println(this.getClass()+"Fetched: " + alarmPS.countTokens() + " alarmPS entries.");
					System.out.println(this.getClass()+"Fetched: " + alarmSP.countTokens() + " alarmText entries.");
					System.out.println(this.getClass()+" Fetched: " + alarmType.countTokens() + " alarmType entries.");
					System.out.println("PRINT *****************");

					if(( a_nr == null || a_nr.equalsIgnoreCase("null") || a_nr.equalsIgnoreCase("") || a_nr.equalsIgnoreCase(" ") && (ifAlarmsCount == 0)))
					{
						//if there are null alarms in the alarm table
						notif = new SynchronizationEventNotification[1];
						System.out.println(getClass() + ":: NULL alarms in Table");
						notif[0] = new SynchronizationEventNotification();
						notif[0].setFmEventType( "ERROR" );
						notif[0].addAdditionalAttribute("SNMPTrapOID","null");
						notif[0].addAdditionalAttribute("Specific","null");
						notif[0].addAdditionalAttribute("IPAddress",me.getIpAddress());
						notif[0].addAdditionalAttribute("Version",me.getProtocolVersion());
						notif[0].addAdditionalAttribute("Enterprise","True");
						notif[0].addAdditionalAttribute("Generic","False");
						notif[0].addAdditionalAttribute( "additionalText", "No Active Alarms in Alarm Table." );
						notif[0].addAdditionalAttribute( "AdditionalText", "No Active Alarms in Alarm Table." );
						notif[0].setEventType("1"); //Communication Alarm
						notif[0].setProbableCause("0");  //Indeterminate
						notif[0].setSpecificProblem( "No Active Alarms in Alarm Table." );
						notif[0].setTime(HandleTimeTranslation.getCurrentTime().toString());
						System.out.println(getClass() + "::syncronize() >> Fetched NOTHING in alarm Table!");
						notif[0].setSeverity("INDETERMINATE");  //Alarm issue with severity as Indeterminat
						System.out.println(" Sync Request finished. Null alarm in table, alarm= "+mmccount);
						//return notif;
					}

					String  a_text, mo_class, Time = "",alarmNo="",mmsCmSystemOpState="",mmsCmSystemAdState="";
					notif = new SynchronizationEventNotification[mmccount];
					for(int mmccnt = 0; mmccnt < mmccount ;mmccnt++)
					{
						boolean perfdegraded = false;
						notif[count] = new SynchronizationEventNotification();
						notif[count].setFmEventType("ALARM");
						notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
						notif[count].addAdditionalAttribute("SNMPTrapOID","null");
						notif[count].addAdditionalAttribute("Specific","null");
						notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
						notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
						notif[count].addAdditionalAttribute("Enterprise","True");
						notif[count].addAdditionalAttribute("Generic","False");
						notif[count].setSpecificProblem("No Specific Problem in Alarm.");
						notif[count].setProbableCause("0");
						try
						{
							System.out.println("Alarm Identity = " +a_nr);
							String alarmIdstring = a_nr;
							if (alarmIdstring.indexOf('x') != -1)
							{
								alarmNo = alarmIdstring.substring(alarmIdstring.indexOf('x'));
							}
							else
							{
								alarmNo = "x999";
							}
							System.out.println("Alarm Id String = " +alarmNo);
							if ((alarmNo.equals("x500") || alarmNo.equals("x501") || alarmNo.equals("x502") || alarmNo.equals("x503")))
							{
								perfdegraded = true;
							}
							notif[count].setExternalEventId(a_nr);
							notif[count].setSpecificProblem(MMCSpecificProblem.getSpecificProblem(alarmNo));
						}
						catch(NoSuchElementException e)
						{
							a_nr = "No External Event ID in Alarm.";
							notif[count].setExternalEventId(a_nr);
						}
						try
						{
							switch(Integer.valueOf(alarmOpState.nextToken()).intValue())
							{
							case 1:
								//mmsCmSystemOpState =HandleAllOtherAlarmAttribute.getMmsCmSystemOpState().concat("up(1).");
							break;
							case 3:mmsCmSystemOpState = mmsCmSystemOpState.concat("degraded(3).");
							break;
							case 4:mmsCmSystemOpState = mmsCmSystemOpState.concat("down(4).");
							break;
							case 5:mmsCmSystemOpState = mmsCmSystemOpState.concat("Unknown(5).");
							break;
							default:
								mmsCmSystemOpState = mmsCmSystemOpState.concat("--------------");
								break;
							}
							notif[count].addAdditionalAttribute("MMSCmSystemOperationalState",mmsCmSystemOpState);
						}
						catch(NoSuchElementException e)
						{
							notif[count].addAdditionalAttribute("MMSCmSystemOperationalState","No Operational State in Alarm");
						}
						try
						{
							switch((Integer.valueOf(alarmAdminState.nextToken()).intValue()))
							{
							case 1:
								//mmsCmSystemAdState =MMCSpecificProblem.getMmsCmSystemAdState().concat("unlocked(1).");
							break;
							case 2:
								//mmsCmSystemAdState =MMCSpecificProblem.getMmsCmSystemAdState().concat("locked(2).");
							break;
							case 3:
								//mmsCmSystemAdState = MMCSpecificProblem.getMmsCmSystemAdState().concat("shutdown(3).");
							break;
							default:
								//mmsCmSystemAdState = MMCSpecificProblem.getMmsCmSystemAdState().concat("--------------");
								break;
							}
							notif[count].addAdditionalAttribute("MerAdminstrativeState", mmsCmSystemAdState);
						}
						catch(NoSuchElementException e)
						{
							notif[count].addAdditionalAttribute("MerAdminstrativeState","No Administrative State in Alarm");
						}

						try
						{
							mo_class = alarmOBjClass.nextToken();
							notif[count].addAdditionalAttribute("AlarmObjectClass",new StringBuffer(TranslationConstant.AlarmOBjClass).append(mo_class).toString());
						}
						catch(NoSuchElementException e)
						{
							//notif[count].setManagedObjectInstance("null");
							notif[count].addAdditionalAttribute("AlarmObjectClass"," No Alarm Object Class in Alarm.");
						}

						try
						{
							notif[count].addAdditionalAttribute("AlarmObjectInstance",new StringBuffer(TranslationConstant.AlarmOBjInstance).append(alarmOBjInstance.nextToken()).toString());
						}
						catch(NoSuchElementException e)
						{
							notif[count].addAdditionalAttribute("AlarmObjectInstance"," No Alarm Object Instance in Alarm.");
						}

						try
						{
							// Perceived severity type is octet string and not integer for this MIB as per TR HF24646
							a_text = alarmPS.nextToken();
							System.out.println("Perceived Severity = "  + a_text );
							notif[count].setSeverity(HandleAlarmSeverity.getPerceivedSeverity("INDETERMINATE"));

						}
						catch(NoSuchElementException e)
						{
							notif[count].setSeverity("INDETERMINATE");
						}
						catch(NumberFormatException excep)
						{
							notif[count].setSeverity("INDETERMINATE");
						}
						catch(Exception ex)
						{
							notif[count].setSeverity("INDETERMINATE");
						}

						try
						{
							a_text = alarmSP.nextToken();
							notif[count].addAdditionalAttribute("additionalText",a_text);
							notif[count].addAdditionalAttribute("AdditionalText",a_text);
						}
						catch(NoSuchElementException e)
						{
							notif[count].addAdditionalAttribute("additionalText", "No Additional Text in Alarm.");
							notif[count].addAdditionalAttribute("AdditionalText", "No Additional Text in Alarm.");
						}

						try
						{
							switch(Integer.valueOf(alarmType.nextToken()).intValue())
							{
							//Event Types defined in X733
							case 1:
								notif[count].setEventType("1"); //Communication Alarm
								notif[count].setProbableCause("305");//Communications Protocol Error
							case 2:
								notif[count].setEventType("4"); //Quality of Service Alarm
								if (perfdegraded)
								{
									notif[count].setProbableCause("334");//Performance Degraded
								}
								else
								{
									notif[count].setProbableCause("351");//Threshold Crossed
								}
								break;
							case 3:
								notif[count].setEventType("2"); //Processing Error Alarm
								notif[count].setProbableCause("307");//Configuration or Customization Error
								break;
							case 4:
								notif[count].setEventType("5"); //Equipment Alarm
								notif[count].setProbableCause("0");//Indeterminate
								break;
							case 5:
								notif[count].setEventType("3"); //Environmental alarm
								notif[count].setProbableCause("0");//Indeterminate
								break;

							default:
								notif[count].setEventType("0");//Unknown
								notif[count].setProbableCause("0");//Indeterminate
								break;
							}
						}
						catch(Exception ex)
						{
							notif[count].setEventType("0");//Unknown
							notif[count].setProbableCause("0");//Indeterminate
						}

						try
						{
							Time = alarmTime.nextToken();
							if(Time.indexOf("-") != -1)//event
							{
								notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createEventDateAndTime(Time))).toString());
								notif[count].setTimeZone(TimeZone.getTimeZone(
										HandleTimeTranslation.getEventTimeZone(Time)).getDisplayName(
												false,TimeZone.SHORT));
							}
							else //alarm
							{
								notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(HandleTimeTranslation.createAlarmDateAndTime(Time))).toString());
								notif[count].setTimeZone(TimeZone.getTimeZone(
										HandleTimeTranslation.getTimeZone(Time)).getDisplayName(
												false,TimeZone.SHORT));
							}
						}
						catch(Exception ex)
						{
							System.out.println("Date Format Exception: " + Time);
							notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
							System.out.println("MmcActionHandler:synchronize():setTime(): "+HandleTimeTranslation.getCurrentTime());
						}
						count++;
					}

				
				return notif;	
				}

}
