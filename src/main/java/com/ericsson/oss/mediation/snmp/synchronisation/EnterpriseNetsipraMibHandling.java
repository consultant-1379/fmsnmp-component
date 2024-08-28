package com.ericsson.oss.mediation.snmp.synchronisation;

import java.util.Map;
import java.util.StringTokenizer;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.constant.TranslationConstant;
import com.ericsson.oss.mediation.translator.model.HandleAlarmSeverity;

public class EnterpriseNetsipraMibHandling {
	
	// returns the array of IF MIB alarms notifications

		public SynchronizationEventNotification[] getEnterpriseNsMIBAlarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int ecsnetsipraAlarmCount){
			int count=0;
			SynchronizationEventNotification notif[] = new SynchronizationEventNotification[ecsnetsipraAlarmCount];
			
			for(int cnt = 0; cnt < ecsnetsipraAlarmCount ; cnt++) // walk through all the entries
			{

				int valEcsAlarmSpecificType=0;
				String valEcsAlarmEventType="";
				String valEcsProbableCause="";
				int valEcsPerceivedSeverity=0;
				String valEcsNotificationID="";
				String valEcsAdditionalText="";
				
				try
				{
					valEcsAlarmSpecificType = Integer.parseInt(snmpWalkMap.get("_OID_VAR_ECS_ALARM_SPECIFIC_TYPE").nextToken().trim());
					valEcsAlarmEventType = snmpWalkMap.get("_OID_VAR_ECS_ALARM_EVENT_TYPE").nextToken().trim();
					
					valEcsProbableCause = snmpWalkMap.get("_OID_VAR_ECS_PROBABLE_CAUSE").nextToken().trim();
				
					valEcsPerceivedSeverity = Integer.parseInt(snmpWalkMap.get("_OID_VAR_ECS_PERCEIVED_SEVERITY").nextToken().trim());
							
					valEcsNotificationID = snmpWalkMap.get("_OID_VAR_ECS_NOTIFICATION_ID").nextToken().trim();
								
					valEcsAdditionalText = snmpWalkMap.get("_OID_VAR_ECS_ADDITIONAL_TEXT").nextToken().trim();
				}
				catch(Exception e)
				{
					valEcsAlarmSpecificType=0;
					valEcsAlarmEventType = "0";
					valEcsProbableCause = "0";
					valEcsPerceivedSeverity=0;
					valEcsNotificationID = "0";
				}	
				

				notif[count] = new SynchronizationEventNotification();
				notif[count].setFmEventType("ALARM");
				notif[count].addAdditionalAttribute("SNMPTrapOID","null");
				notif[count].addAdditionalAttribute("Specific","null");
				notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
				notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
				notif[count].addAdditionalAttribute("Enterprise","True");
				// ---------------------------------------------------------------
				// Go ahead with mapping of the required attributes!!!
				// ---------------------------------------------------------------

				// Set specific problem
				switch(valEcsAlarmSpecificType)
				{
				case TranslationConstant._TRAP_TYPE_ECS_APPLICATION_DOWN:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsApplicationDown);
					notif[count].addAdditionalAttribute("Trap Name","ecsApplicationDown");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsApplicationDown");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_CPU_LOAD_HIGH:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsCpuLoadHigh);
					notif[count].addAdditionalAttribute("Trap Name","ecsCpuLoadHigh");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsCpuLoadHigh");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_DATA_PATH_DOWN:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsDataPathDown);
					notif[count].addAdditionalAttribute("Trap Name","ecsDataPathDown");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsDataPathDown");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_DISK_FULL:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsDiskFull);
					notif[count].addAdditionalAttribute("Trap Name","ecsDiskFull");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsDiskFull");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_MATED_PAIR_DOWN:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsMatedPairDown);
					notif[count].addAdditionalAttribute("Trap Name","ecsMatedPairDown");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsMatedPairDown");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_MEMORY_FULL:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsMemoryFull);
					notif[count].addAdditionalAttribute("Trap Name","ecsMemoryFull");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsMemoryFull");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_MODULE_DOWN:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsModuleDown);
					notif[count].addAdditionalAttribute("Trap Name","ecsModuleDown");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsModuleDown");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_SCF_PATH_DOWN:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsScfPathDown);
					notif[count].addAdditionalAttribute("Trap Name","ecsScfPathDown");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsScfPathDown");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_SERVICE_DOWN:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsServiceDown);
					notif[count].addAdditionalAttribute("Trap Name","ecsServiceDown");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsServiceDown");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_VS_MAXIMUM:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsVsMaximum);
					notif[count].addAdditionalAttribute("Trap Name","ecsVSMaximum");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsVSMaximum");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_FLOW_MAXIMUM:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsFlowMaximum);
					notif[count].addAdditionalAttribute("Trap Name","ecsFlowMaximum");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsFlowMaximum");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_NO_TRAFFIC_PROCESSED:   
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsNoTrafficProcessed);
					notif[count].addAdditionalAttribute("Trap Name","ecsNoTrafficProcessed");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsNoTrafficProcessed");
					break;
		
				case TranslationConstant._TRAP_TYPE_ECS_CHILD_FLOW_MAXIMUM:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsChildFlowMaximum);
					notif[count].addAdditionalAttribute("Trap Name","ecsChildFlowMaximum");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsChildFlowMaximum");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_SASN_FAILS_IN_HANDLING_UDR:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapSasnFailsInHandlingUdr);
					notif[count].addAdditionalAttribute("Trap Name","ecsSasnFailsInHandlingUdr");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsSasnFailsInHandlingUdr");
					break;
				case TranslationConstant._TRAP_TYPE_ECS_SESSION_MAXIMUM:
					notif[count].setSpecificProblem(TranslationConstant.txtTrapEcsSessionMaximum);
					notif[count].addAdditionalAttribute("Trap Name","ecsSessionMaximum");
					System.out.println(" NetSpiraActionHandler >> Trap OID Matched : ecsSessionMaximum");
					break;
				default:
					notif[count].setSpecificProblem("UNHANDLED"); 
					break;
				}

			
				notif[count].setEventType(valEcsAlarmEventType);
				
				notif[count].setProbableCause(valEcsProbableCause);
				
				notif[count].setSeverity(HandleAlarmSeverity.getIntegerPerceivedSeverity((valEcsPerceivedSeverity)));
				
				notif[count].setExternalEventId(valEcsNotificationID);
			
				notif[count].addAdditionalAttribute("AdditionalText",valEcsAdditionalText);
				
				notif[count].addAdditionalAttribute("additionalText",valEcsAdditionalText);

				count++;

			} // for
           return notif;
}
}
