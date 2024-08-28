package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.Map;
import java.util.StringTokenizer;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.*;

public class EricssonsogMibHandling {

	// returns the array of IF MIB alarms notifications
	
	public SynchronizationEventNotification[] getericsogMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int sogalarmCount){
		 int count=0;
         SynchronizationEventNotification notif[] = new SynchronizationEventNotification[sogalarmCount];

		for(int cnt = 0; cnt < sogalarmCount ; cnt++)
		{
			//additional text buffer
			StringBuffer str = new StringBuffer();
			System.out.println("ESAActionHandler::syncSOGAlarm() >>" + sogalarmCount);
			System.out.println("COUNT [" +cnt +"]" );
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");
			notif[count].addAdditionalAttribute("Generic","False");
			notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString()); 
			//Processing AlarmId	      

			String alid="";
			try
			{
				if(snmpWalkMap.get("ALARMID").hasMoreTokens())
				{
					alid = snmpWalkMap.get("ALARMID").nextToken();
					System.out.println("alarmId = "+alid);
					notif[count].setManagedObjectInstance(alid);
				}
				else
				{
					System.out.println("No vlaue for alarmId = "+alid);

				}

			}
			catch(Exception e)
			{
				System.out.println("Exception in Processing alarmId = "+alid);
			}

			//Processing AlarmName 
			String sp="";
			try
			{

				if(snmpWalkMap.get("ALARMNAME").hasMoreTokens())
				{
					sp = snmpWalkMap.get("ALARMNAME").nextToken();
					System.out.println("alarmName= "+sp);
					notif[count].setSpecificProblem(sp);

				}
				else
				{
					System.out.println("No vlaue for alarmName = "+sp);
					notif[count].setSpecificProblem("0");


				}

			}
			catch(Exception e)
			{
				notif[count].setSpecificProblem("0");
				System.out.println("Exception in Processing alarmName = "+e.getMessage());
			}


			//Processing  alarmComment 
			String ptext=""; 

			try
			{
				if( snmpWalkMap.get("ALARMCOMMENT").hasMoreTokens())
				{
					ptext =  snmpWalkMap.get("ALARMCOMMENT").nextToken();
					System.out.println(" alarmComment= "+ptext);
					str=str.append("ALARMCOMMENT"+":"+ptext);
					//notif[count].addAdditionalAttribute("additonalText",str.append(ptext).toString());

				}
				else
				{
					System.out.println("No vlaue for  alarmComment= "+ptext);
					str=str.append("ALARMCOMMENT"+":"+"No Alarmcomment in the trap");
					//notif[count].addAdditionalAttribute("additonalText","No Alarmcomment in the trap");


				}

			}
			catch(Exception e)
			{
				notif[count].addAdditionalAttribute("additonalText","No Alarmcomment in the trap"); 	 
				System.out.println("Exception in Processing  alarmComment= "+ptext);
			}




			//Processing  alarmType
			String type=""; 	

			try
			{		
				if( snmpWalkMap.get("ALARMTYPE").hasMoreTokens())
				{
					type =  snmpWalkMap.get("ALARMTYPE").nextToken();
					System.out.println(" alarmType= "+type);
					str.append("ALARMTYPE"+":"+type);
					//notif[count].addAdditionalAttribute("additonalText",str.append(type).toString());

				}
				else
				{
					System.out.println("No vlaue for  alarmType= "+type);
					str.append("ALARMTYPE"+":"+"No AlarmType in the trap");
					//notif[count].addAdditionalAttribute("additonalText","No AlarmType in the trap");      


				}

			}
			catch(Exception e)
			{
				str.append("ALARMTYPE"+":"+"No AlarmType in the trap");
				//notif[count].addAdditionalAttribute("additonalText","No alarmType in the trap");     
				System.out.println("Exception in Processing  alarmType ="+type);
			}


			//Processing  alarmPC
			String pc="";

			try
			{
				if( snmpWalkMap.get("ALARMPC").hasMoreTokens())
				{
					pc =  snmpWalkMap.get("ALARMPC").nextToken();
					System.out.println(" alarmPC = "+pc);
					str.append("ALARMPC"+":"+pc);
					//notif[count].addAdditionalAttribute("additonalText",str.append(pc).toString());

				}
				else
				{
					System.out.println("No vlaue for alarmPC = "+pc);
					str.append("ALARMPC"+":"+"No alarmPC in the trap");
					//notif[count].addAdditionalAttribute("additonalText","No alarmPC in the trap");


				}

			}
			catch(Exception e)
			{
				str.append("ALARMPC"+":"+"No alarmPC in the trap");
				//notif[count].addAdditionalAttribute("additonalText","No alarmPC in the trap");
				System.out.println("Exception in Processing  alarmPC ="+pc);
			}

			//Processing  alarmPPA

			String ppa="";

			try
			{
				if( snmpWalkMap.get("ALARMPRA").hasMoreTokens())
				{
					ppa =  snmpWalkMap.get("ALARMPRA").nextToken();
					System.out.println(" alarmPPA = "+ppa);
					str.append("ALARMPRA"+":"+ppa);
					//notif[count].addAdditionalAttribute("additonalText",str.append(ppa).toString());

				}
				else
				{
					System.out.println("No vlaue for alarmPPA = "+ppa);
					str.append("ALARMPRA"+":"+"No alarmPPA in the trap");
					//notif[count].addAdditionalAttribute("additonalText","No alarmPPA in the trap");


				}

			}
			catch(Exception e)
			{
				notif[count].addAdditionalAttribute("additonalText","No alarmPPA in the trap");
				str.append("ALARMPRA"+":"+"No alarmPPA in the trap");
				System.out.println("Exception in Processing  alarmPPA ="+ppa);
			}


			//Processing Alarm Perceived Severity

			String PS = "";
			try
			{
				PS = snmpWalkMap.get("ALARMPS").nextToken();
				System.out.println("alarmPS = "+PS);
				String severity=HandleAlarmSeverity.getPerceivedSeverity(PS);
				notif[count].setSeverity(severity);

			}
			catch(Exception e)
			{
				notif[count].setSeverity("INDETERMINATE");
				System.out.println("Exception while processing alarmPS = "+PS);
			}


			//Probable cause  and Event Type mapping according to Alarmname

			//Probable Cause Mapping
			String AlarmnameProbableCause=HandleProbableCause.getnewAlarmNameProbableCause(sp);
			notif[count].setProbableCause(AlarmnameProbableCause);

			//Event Type mapping

			String AlarmnameEventType=HandleEventType.getnewAlarmNameEventType(sp);
			notif[count].setEventType(AlarmnameEventType);
			notif[count].addAdditionalAttribute("additonalText",str.toString());
			count++;
		}
	 return notif;
	}
	
}
