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

public class SnmpPcMaps {
	private static final Map<String, String> pcMap = new HashMap<String, String>();
	
	private static final Map<String, String> alarmNamePcMap = new HashMap<String, String>();
	static {

		pcMap.put("The traffic in the zone evaluation location server is close to or has reached the maximum capacity.", "583");
		pcMap.put("The file path or permission may be wrong.", "584");
		pcMap.put("The ZoneEngine component goes down while it is writing to file", "585");
		pcMap.put("The file name or path may be wrong.", "586");
		pcMap.put("The PluginManager has reported that the plug-in stated in the message had died for some reason.", "587");
		pcMap.put("One or more servers are down.", "588");
		pcMap.put("Certificate is about to expire.", "589");
		pcMap.put("A license key has expired.", "590");
		pcMap.put("A license key is about to expire.", "591");
		pcMap.put("All licensed transactions have been used up.", "592");
		pcMap.put("The licensed transactions are almost used up.", "593");
		pcMap.put("The license manager is not started.", "594");
		pcMap.put("The database is down or DB interface is down.", "595");
		pcMap.put("The requested component may not be started or is restarting. The probable cause can also be an error in the address configuration for the requesting component.", "596");
		pcMap.put("The monitored host is down or problem exists with the network.", "597");
		pcMap.put("Probably some of the plug-ins are down.", "598");
		pcMap.put("One or more plug-ins are down.", "599");
		pcMap.put("All SS7 links are disconnected or deactivated.", "600");
		pcMap.put("SS7 stack not running.", "601");
		pcMap.put("At least one Front End is down.", "602");
		pcMap.put("The SS7 stack is down or the SS7Manager component is down.", "603");
		pcMap.put("The directory is write protected or does not exist.", "604");
		pcMap.put("The file is corrupt or does not exist.", "605");
		pcMap.put("The SS7 link may be disconnected or deactivated.", "606");
		pcMap.put("The SS7 association may be disconnected or deactivated.", "601");
		pcMap.put("One or more plug-ins are down.", "606");
		
		//Put Properties for NewAlarmpcMap properties
		alarmNamePcMap.put("ABC","DEF");
		alarmNamePcMap.put("DBInterface:OracleUserorPasswordError","600");
		alarmNamePcMap.put("FDSAuthority:InvalidSessionId","600");
		alarmNamePcMap.put("ProcEngine:LicenceKeyError","600");
		alarmNamePcMap.put("GUIDriver:LicenceKeyError","600");
		alarmNamePcMap.put("GUIDriver:LicenceKeyOK","600");//authenticationFailure
		
		alarmNamePcMap.put("DBInterface:OracleConnectionStringError","506");// configurationOrCustomizationError
		
		alarmNamePcMap.put("DBInterface:OracleServerUnavailableError","544");
		alarmNamePcMap.put("DBInterface:OracleServerRecovered","544");
		alarmNamePcMap.put("Lookup:UnexpectedState","544");
		alarmNamePcMap.put("Lookup:NotificationGaveError","544");
		alarmNamePcMap.put("MOHandler:RoutingFailed","544");
		alarmNamePcMap.put("MOHandler:RegistrationWarning","544");
		alarmNamePcMap.put("MOHandler:InvalidRegistration","544");
		alarmNamePcMap.put("MOHandler:InvalidUnRegistration","544");
		alarmNamePcMap.put("FDSConfigurationManager:UnknownOperation","544");
		alarmNamePcMap.put("ProcEngine:ConfigurationWriteFailure","544");
		alarmNamePcMap.put("ProcEngine:ConfigurationWriteRecovered","544");
		alarmNamePcMap.put("ProcEngine:BusinessLogicCompilationError","544");
		alarmNamePcMap.put("Mmllp:BusinessLogicCompilationError","544");
		alarmNamePcMap.put("ProcQueue:clusterConnectionFailed","544");
		alarmNamePcMap.put("ProcQueue:clusterConnectionRestored","544");
		alarmNamePcMap.put("GUIDriver:CorbaConnectionFailure","544");
		alarmNamePcMap.put("GUIDriver:CorbaConnectionRecovered","544");
		alarmNamePcMap.put("IfDriver:ConfigurationWriteFailure","544");
		alarmNamePcMap.put("IfDriver:ConfigurationWriteRecovered","544");
		alarmNamePcMap.put("TaskManager:GetTimerFromDbError","544");// softwareError
		
		
		alarmNamePcMap.put("Event receiver connection","0");
		alarmNamePcMap.put("Lookup:RemovedSubscriber","0");
		alarmNamePcMap.put("Lookup:UnrecoveredSubscribers","0");
		alarmNamePcMap.put("Lookup:ManySubscriberRefs","0");
		alarmNamePcMap.put("MOHandler:CorrectUnRegistration","0");
		alarmNamePcMap.put("FDSAuthority:UserLoggedIn","0");
		alarmNamePcMap.put("FDSAuthority:UserLoggedOut","0");
		alarmNamePcMap.put("Lookup:UnsubscribeNonexistent","0");
		alarmNamePcMap.put("MOHandler:CorrectRegistration","0");
		alarmNamePcMap.put("FDSServer:StartUpMessage","0");
		alarmNamePcMap.put("FDSServer:ShutdownUpMessage","0");
		alarmNamePcMap.put("ProcQueue:QueueStopped","0");
		alarmNamePcMap.put("ProcQueue:QueueEmpty","0");
		alarmNamePcMap.put("ProcQueue:QueueSending","0");
		alarmNamePcMap.put("IfDriver:ServerReadyToAccept","0");
		alarmNamePcMap.put("IfDriver:TracingMessageFromClient","0");
		alarmNamePcMap.put("IfDriver:TracingMessageFromComponent","0");
		alarmNamePcMap.put("Subagent is down","0");
		alarmNamePcMap.put("Subagent is up","0");
		alarmNamePcMap.put("SOAPAdapter111 Stop","0");
		alarmNamePcMap.put("SOAPAdapter222 Start","0");
		alarmNamePcMap.put("SOAPAdapter222 Stop","0");
		alarmNamePcMap.put("NeCluster:NetworkElementOffStore","0");//Indeterminate
		
		alarmNamePcMap.put("Lookup:ConfigFailed","516");
		alarmNamePcMap.put("ProcLog:FileSystemError","516");
		alarmNamePcMap.put("CAI3GNtf:MissingConfigCAI3GFailedNtfLogFile","516");
		alarmNamePcMap.put("CAI3GNtf:FailedToOpenCAI3GFailedNtfLogFile","516");
		alarmNamePcMap.put("ProcQueue:fileNotAccessible","516");
		alarmNamePcMap.put("ProcQueue:errorAccessingFile","516");
		alarmNamePcMap.put("ProcQueue:fileAccessible","516");
		alarmNamePcMap.put("TaskManager:FileSystemError","516");//fileError
		
		alarmNamePcMap.put("Lookup:NotificationFailed","306");
		alarmNamePcMap.put("Lookup:NotifierProblems","306");
		alarmNamePcMap.put("FDSConfigurationManager:NotificationWarning","306");
		alarmNamePcMap.put("ProcEngine:ConnectionFailure","306");
		alarmNamePcMap.put("ProcEngine:ConnectionRecovered","306");
		alarmNamePcMap.put("Mmllp:LinkFailure","306");
		alarmNamePcMap.put("Mmllp:LinkRecovered","306");
		alarmNamePcMap.put("Mmllp:LinkManangerConnectionFailure","306");
		alarmNamePcMap.put("Mmllp:LinkManangerConnectionRecovered","306");
		alarmNamePcMap.put("IfDriver:ConnectionFailure","306");
		alarmNamePcMap.put("IfDriver:ConnectionRecovered","306");
		alarmNamePcMap.put("Cpm:CDLinkFailure","306");
		alarmNamePcMap.put("Cpm:SCRLinkFailure","306");
		alarmNamePcMap.put("Cpm:AAALinkFailure","306");
		alarmNamePcMap.put("Cpm:CDLinkRecover","306");
		alarmNamePcMap.put("Cpm:SCRLinkRecover","306");
		alarmNamePcMap.put("Cpm:AAALinkRecover","306");// communicationsSubsystemFailure
		
		alarmNamePcMap.put("FDSConfigurationManager:WriteWarning","560");// diskProblem
		
		alarmNamePcMap.put("FDSAuthority:UserNotAllowed","614");
		alarmNamePcMap.put("ProcEngine:UserNotAllowed","614");
		alarmNamePcMap.put("IfDriver:MaxRetrysToLogInExceeded","614");
		alarmNamePcMap.put("TaskManager:LoginFailure","614");// unauthorizedAccessAttempt
		
		alarmNamePcMap.put("FDSComponentManager:PluginDied","158");
		alarmNamePcMap.put("FDSComponentManager:FailedToSaveConfig","158");
		alarmNamePcMap.put("FDSComponentManager:MORequestFailed","158");
		alarmNamePcMap.put("FDSComponentManager:MORequestInfo","158");
		alarmNamePcMap.put("FDSComponentManager:PluginRecovered","158");
		alarmNamePcMap.put("FDSComponentManager:PluginRecoveryFailed","158");
		alarmNamePcMap.put("FDSComponentManager:Internalerror","158");
		alarmNamePcMap.put("FDSComponentManager:Information","158");
		alarmNamePcMap.put("FDSComponentManager:RebuildingStructuresInfo","158");
		alarmNamePcMap.put("ProcEngine:LoadSubPluginError","158");
		alarmNamePcMap.put("GUIDriver:LoadSubPluginError","158");
		alarmNamePcMap.put("OrderScheduler::ReceiverStopped","158");
		alarmNamePcMap.put("OrderScheduler::ReceiverStarted","158");
		alarmNamePcMap.put("OrderScheduler::ConsumerStopped","158");
		alarmNamePcMap.put("OrderScheduler::ConsumerStarted","158");//applicationSubsystemFailure
		
	
		alarmNamePcMap.put("ProcQueue:QueueFull","339");
		alarmNamePcMap.put("ProcQueue:QueueNotFull","339");
		alarmNamePcMap.put("OrderScheduler::QueueFull","339");
		alarmNamePcMap.put("OrderScheduler::QueueAvailable","339");// queueSizeExceeded
		
		
		alarmNamePcMap.put("ProcQueue:QueueMaxNumberRetriesExceeded","549");
		alarmNamePcMap.put("ProcQueue:QueueIDWarningNumberExceed","549");
		alarmNamePcMap.put("IfDriver:ServerMaxConnectionReached","549");
		alarmNamePcMap.put("Over Limitation Of MaxNumOfCAI3GSession","549");
		alarmNamePcMap.put("Over Limitation Of MaxNumOfSoapConnection","549");// thresholdCrossed
		
		alarmNamePcMap.put("IfDiver:BadLinkDevice","54");// externalIFDeviceProblem
		
		alarmNamePcMap.put("ProcQueue:QueueNotFull","549");
		alarmNamePcMap.put("ProcQueue:QueueNotFull","549");
		

	}

	public static String getProbableCause(final String pcString) {
		return pcMap.get(pcString);
	}
	public static String getnewNameProbableCause(final String newPCString)
	{
		
		return alarmNamePcMap.get(newPCString);
	}
	private SnmpPcMaps()
	{
		
	}

}
