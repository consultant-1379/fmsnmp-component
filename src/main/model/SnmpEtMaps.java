/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.mediation.translator.model;

import java.util.HashMap;
import java.util.Map;

public class SnmpEtMaps {
	
	private static final Map<String, String> etMap = new HashMap<String, String>();

	static
	{
		etMap.put("DBInterface:OracleUserorPasswordError", "10");
		etMap.put("FDSAuthority:InvalidSessionId", "10");
		etMap.put("FDSAuthority:UserNotAllowed", "10");
		etMap.put("FDSAuthority:UserLoggedIn", "10");
		etMap.put("FDSAuthority:UserLoggedOut", "10");
		etMap.put("ProcEngine:LicenceKeyError", "10");
		etMap.put("ProcEngine:UserNotAllowed", "10");
		etMap.put("IfDriver:MaxRetrysToLogInExceeded", "10");
		etMap.put("TaskManager:LoginFailure", "10");// securityServiceOrMechanismViolation
		
		
		etMap.put("DBInterface:OracleConnectionStringError", "4");
		etMap.put("DBInterface:OracleServerUnavailableError", "4");
		etMap.put("DBInterface:OracleServerRecovered", "4");
		etMap.put("Lookup:ConfigFailed", "4");
		etMap.put("Lookup:NotificationFailed", "4");
		etMap.put("Lookup:NotifierProblems", "4");
		etMap.put("Lookup:UnexpectedState", "4");
		etMap.put("Lookup:RemovedSubscriber", "4");
		etMap.put("Lookup:UnrecoveredSubscribers", "4");
		etMap.put("Lookup:ReregisteredSubscriber", "4");
		etMap.put("Lookup:ManySubscriberRefs", "4");
		etMap.put("Lookup:UnsubscribeNonexistent", "4");
		etMap.put("Lookup:NotificationGaveError", "4");
		etMap.put("Lookup:BufferOverflow", "4");
		etMap.put("MOHandler:RoutingFailed", "4");
		etMap.put("MOHandler:RegistrationWarning", "4");
		etMap.put("MOHandler:InvalidRegistration", "4");
		etMap.put("MOHandler:CorrectRegistration", "4");
		etMap.put("MOHandler:CorrectUnRegistration", "4");
		etMap.put("MOHandler:InvalidUnRegistration", "4");
		etMap.put("FDSConfigurationManager:WriteWarning", "4");
		etMap.put("FDSConfigurationManager:NotificationWarning", "4");
		etMap.put("FDSConfigurationManager:UnknownOperation", "4");
		etMap.put("FDSComponentManager:PluginDied", "4");
		etMap.put("FDSComponentManager:FailedToSaveConfig", "4");
		etMap.put("FDSComponentManager:MORequestFailed", "4");
		etMap.put("FDSComponentManager:MORequestInfo", "4");
		etMap.put("FDSComponentManager:PluginRecovered", "4");
		etMap.put("FDSComponentManager:PluginRecoveryFailed", "4");
		etMap.put("FDSComponentManager:Internalerror", "4");
		etMap.put("FDSComponentManager:Information", "4");
		etMap.put("FDSComponentManager:RebuildingStructuresInfo", "4");
		etMap.put("FDSServer:StartUpMessage", "4");
		etMap.put("FDSServer:ShutdownUpMessage", "4");
		etMap.put("ProcLog:FileSystemError", "4");
		etMap.put("ProcEngine:ConnectionFailure", "4");
		etMap.put("ProcEngine:ConnectionRecovered", "4");
		etMap.put("ProcEngine:ConfigurationWriteFailure", "4");
		etMap.put("ProcEngine:ConfigurationWriteRecovered", "4");
		etMap.put("ProcEngine:BusinessLogicCompilationError", "4");
		etMap.put("CAI3GNtf:MissingConfigCAI3GFailedNtfLogFile", "4");
		etMap.put("CAI3GNtf:FailedToOpenCAI3GFailedNtfLogFile", "4");
		etMap.put("ProcEngine:LoadSubPluginError", "4");
		etMap.put("Mmllp:BusinessLogicCompilationError", "4");
		etMap.put("ProcQueue:clusterConnectionFailed", "4");
		etMap.put("ProcQueue:fileNotAccessible", "4");
		etMap.put("ProcQueue:errorAccessingFile", "4");
		etMap.put("ProcQueue:QueueStopped", "4");
		etMap.put("ProcQueue:QueueFull", "4");
		etMap.put("ProcQueue:QueueEmpty", "4");
		etMap.put("ProcQueue:QueueMaxNumberRetriesExceeded", "4");
		etMap.put("ProcQueue:clusterConnectionRestored", "4");
		
		etMap.put("ProcQueue:fileAccessible", "4");
		etMap.put("ProcQueue:QueueSending", "4");
		etMap.put("ProcQueue:QueueNotFull", "4");
		etMap.put("ProcQueue:QueueIDWarningNumberExceed", "4");
		etMap.put("GUIDriver:CorbaConnectionFailure", "4");
		etMap.put("GUIDriver:LicenceKeyError", "4");
		etMap.put("GUIDriver:LicenceKeyOK", "4");
		etMap.put("GUIDriver:CorbaConnectionRecovered", "4");
		etMap.put("GUIDriver:LoadSubPluginError", "4");
		etMap.put("IfDriver:ConnectionFailure", "4");
		etMap.put("IfDriver:ConnectionRecovered", "4");
		etMap.put("IfDriver:ConfigurationWriteFailure", "4");
		etMap.put("IfDriver:ConfigurationWriteRecovered", "4");
		etMap.put("IfDriver:ServerMaxConnectionReached", "4");
		etMap.put("IfDriver:ServerReadyToAccept", "4");
		etMap.put("IfDriver:TracingMessageFromClient", "4");
		etMap.put("IfDriver:TracingMessageFromComponent", "4");
		etMap.put("TaskManager:FileSystemError", "4");
		etMap.put("TaskManager:GetTimerFromDbError", "4");
		etMap.put("Cpm:CDLinkFailure", "4");
		etMap.put("Cpm:SCRLinkFailure", "4");
		etMap.put("Cpm:AAALinkFailure", "4");
		etMap.put("Cpm:CDLinkRecover", "4");
		etMap.put("Cpm:SCRLinkRecover", "4");
		etMap.put("Cpm:AAALinkRecover", "4");
		etMap.put("OrderScheduler::QueueFull", "4");
		etMap.put("OrderScheduler::QueueAvailable", "4");
		etMap.put("OrderScheduler::ReceiverStopped", "4");
		etMap.put("OrderScheduler::ReceiverStarted", "4");
		etMap.put("OrderScheduler::ConsumerStopped", "4");
		etMap.put("OrderScheduler::ConsumerStarted", "4");
		etMap.put("NeCluster:NetworkElementOffStore", "4");
		etMap.put("Over Limitation Of MaxNumOfCAI3GSession", "4");
		etMap.put("Over Limitation Of MaxNumOfSoapConnection", "4");// Processing error alarm.
		
		
		
		etMap.put("Event receiver connection", "2");
		etMap.put("Mmllp:LinkFailure", "2");
		etMap.put("Mmllp:LinkRecovered", "2");
		etMap.put("Mmllp:LinkManangerConnectionFailure", "2");
		etMap.put("Mmllp:LinkManangerConnectionRecovered", "2");
		etMap.put("Subagent is down", "2");
		etMap.put("Subagent is up", "2");
		etMap.put("SOAPAdapter111 Start", "2");
		etMap.put("SOAPAdapter111 Stop", "2");
		etMap.put("SOAPAdapter222 Start", "2");
		etMap.put("SOAPAdapter222 Stop", "2");// Communications alarm
		
		
		
		etMap.put("IfDiver:BadLinkDevice", "5");// equipment alarm
		etMap.put("Cpm:SCRLinkFailure", "4");
		etMap.put("Cpm:SCRLinkFailure", "4");
		etMap.put("Cpm:SCRLinkFailure", "4");
		etMap.put("Cpm:SCRLinkFailure", "4");
		etMap.put("Cpm:SCRLinkFailure", "4");
		etMap.put("Cpm:SCRLinkFailure", "4");
		
	}
	
	public static String getEventType(String pcString) {
		
		return etMap.get(pcString);
	}

}
