package com.ericsson.oss.mediation.translator.constant;

public class TranslationConstant {
	
    //AdditionalAttributes connstants 
	public static final String axdSev = ".1.3.6.1.4.1.193.14.1.1.3.2.2.3.1.6";
	public static final String SNMP_TRAP_OID = "SNMPTrapOID";
	public static final String SNMP_SPECIFIC = "Specific";
	public static final String SNMP_IP_ADDRESS = "IPAddress";
	public static final String SNMP_VERSION = "Version";
	public static final String SNMP_ENTERPRISE = "Enterprise";
	public static final String SNMP_GENERIC = "Generic";
	public static final String ADDITIONAL_ATTRIBUTES = "AdditionalAttributes";
	
	public static final int _TRAP_TYPE_ECS_APPLICATION_DOWN = 102;
	public static final int _TRAP_TYPE_ECS_CPU_LOAD_HIGH = 112;
	public static final int _TRAP_TYPE_ECS_DATA_PATH_DOWN = 120;
	public static final int _TRAP_TYPE_ECS_DISK_FULL = 111;
	public static final int _TRAP_TYPE_ECS_MATED_PAIR_DOWN = 122;
	public static final int _TRAP_TYPE_ECS_MEMORY_FULL = 110;
	public static final int _TRAP_TYPE_ECS_MODULE_DOWN = 103;
	public static final int _TRAP_TYPE_ECS_SCF_PATH_DOWN = 121;
	public static final int _TRAP_TYPE_ECS_SERVICE_DOWN = 101;
	public static final int _TRAP_TYPE_ECS_SESSION_MAXIMUM = 114;

	
	public static final int _TRAP_TYPE_ECS_VS_MAXIMUM = 115;
	public static final int _TRAP_TYPE_ECS_FLOW_MAXIMUM = 116;
	public static final int _TRAP_TYPE_ECS_CHILD_FLOW_MAXIMUM = 117;
	public static final int _TRAP_TYPE_ECS_NO_TRAFFIC_PROCESSED = 123;
	public static final int _TRAP_TYPE_ECS_SASN_FAILS_IN_HANDLING_UDR = 125;

	// NS-MPT MIB.
	public static final int _TRAP_TYPE_MPT_CONTROLLER_FAULT = 1000;

	// ------------------------------------------------------------------------
	// MIB text (Description) for traps, this is mapped as FM Specific Problem
	// ------------------------------------------------------------------------

	public static String txtTrapEcsApplicationDown = "Application Down";
	public static String txtTrapEcsCpuLoadHigh = "CPU Load Above Threshold";
	public static String txtTrapEcsDataPathDown = "Data path unreachable";
	public static String txtTrapEcsDiskFull = "Disk Usage Above Threshold";
	public static String txtTrapEcsMatedPairDown = "SASN-Peer Unreachable";
	public static String txtTrapEcsMemoryFull = "Memory Usage Above Threshold";
	public static String txtTrapEcsModuleDown = "Module Down";
	public static String txtTrapEcsScfPathDown = "SCF Path Unreachable";
	public static String txtTrapEcsServiceDown = "Service Down";
	public static String txtTrapEcsSessionMaximum = "Number of Bearer Sessions Above Threshold";

	// Updated by Pooja for WP00645 for OSSRC6.2 CU3(R3G)
	public static String txtTrapEcsVsMaximum = "Number of Virtual Sessions Above Threshold";
	public static String txtTrapEcsFlowMaximum = "Number of Flows Above Threshold";
	public static String txtTrapEcsChildFlowMaximum = "Number of Child Flows Above Threshold";
	public static String txtTrapEcsNoTrafficProcessed = "No Traffic Processed Lately";
	public static String txtTrapSasnFailsInHandlingUdr = "The SASN fails to open, write, or close a specified Usage Detailed Record (UDR)";

	// Updated by XWOALRA to implement Work Item 1.6 in order to include NS-MPT
	// MIB.
	public static String txtTrapMptControllerFault = "MPT RAID Controller Fault";

	// Descriptive texts for variables contained within traps
	public static String txtVarApEnvMonTrapInstance = "The object ID of the item which value is exceeds its monitoring threshold. If the item is within a table, this OID is the instance of this item's index within its table : ";
	public static String txtVarApEnvMonTrapPreviousState = "The previous state of the object : ";
	public static String txtVarApEnvMonTrapCurrentState = "The current state of the object which causes the trap to occur : ";
	public static String txtVarApSyslogHistFrom = "The process name and host of the sending client : ";
	public static String txtVarApSyslogHistLevel = "The Log-Level of the message : ";
	public static String txtVarApSyslogHistType = "A textual identification for the Log-Type, which categorizes the log message : ";
	public static String txtVarApSyslogHistContent = "The text of the message.  If the text of the message exceeds 255 bytes, the message will be truncated to 255 bytes : ";
	public static String tampxtVarApSyslogHistTimestamp = "The value of sysUpTime when this message was generated : ";
	public static String txtVarApSysMgmtTrapType = "The object ID of the item which value is exceeds its monitoring threshold : ";
	public static String txtVarApSysMgmtTrapValue = "The value of the object which causes the trap to occur : ";
	public static String txtVarApSysMgmtPowerLocation = "Power Location : ";
	public static String txtVarApSysMgmtPowerPresence = "Power Presence : ";
	public static String txtVarApSysMgmtTempValue = "The value is the temperature of the system in degrees celsius : ";
	public static String txtVarApSysMgmtFanLocation = "Fan Location : ";
	public static String txtVarApSysMgmtFanSpeed = "The value is the percentage of fan speed of given fan location : ";
	public static String txtVarApSysMgmtTaskSuspend = "The text public static String of the critical task that has entered the suspended state.  If the text of the message exceeds 255 bytes, the message will be truncated to 255 bytes : ";
	public static String txtVarApSysMgmtRedRole = "System Management Red Role (unit in a redundant pair) : ";
	public static String txtVarApSysMgmtRedTransState = "The state that the system, give by location, is transitioning too : ";
	public static String txtVarApSysMgmtMediaPorts = "The value is the failure rate percentage at which the system cannot allocate media ports : ";
	public static String txtVarApSysMgmtMediaBandwidth = "The value is the failure rate percentage at which the system cannot allocate media bandwidth : ";
	public static String txtVarApSysMgmtGatewayUnreachable = "The value is the gateway that is unreaachable from the system The public static String will formated as such <xx.xx.xx.xx>:<y>:<zz> where xx.xx.xx.xx is the IPv4 address, y is the slot and zz is the subport ID. If the text of the message exceeds 255 bytes, the message will be truncated to 255 bytes : ";
	public static String txtVarApSysMgmtRadiusDown = "The value identifies if all the radius connections are down or if just some of the radius connects have become unreachable : ";
	public static String txtVarApSysMgmtH323InitFail = "The text public static String is the H.323 stack-name that has failed to initilize If the text of the message exceeds 255 bytes, the message will be truncated to 255 bytes : ";
	public static String txtVarApSysMgmtCfgSaveFail = "The text public static String is the reason phrase why the save config has failed to execute properly on the system. If the text of the message exceeds 255 bytes, the message will be truncated to 255 bytes : ";
	public static String txtVarApSysMgmtHardwareError = "The text public static String is the type of hardware error that has occurred. If the text of the message exceeds 255 bytes, the message will be truncated to 255 bytes : ";
	public static String txtVarApSysMgmtSAHostname = "The hostname of the Session Agent that is changing status : ";
	public static String txtVarApSysMgmtSAIP = "The ip address of the Session Agent that is changing status : ";
	public static String txtVarApSysMgmtSAStatus = "The status that the Session Agent is changing into : ";
	public static String txtVarApSysMgmtSAStatusReason = "The reason for the status change of the Session Agent : ";
	public static String txtVarApSysMgmtAuthFailLevel = "The state a user was trying to switch to or from when failing to authenticate : ";
	public static String txtVarApSysMgmtAuthFailProto = "The protocol a user was using when failing to authenticate : ";
	public static String txtVarApSysMgmtAuthFailOrigin = "The origin of a user who failed to authenticate. The public static String will formated as such <xx.xx.xx.xx>:<yyyy> where xx.xx.xx.xx is the IPv4 address, and yyyy is the port : ";
	public static String txtVarApSysMgmtSystemState = "The current value of the system-state : ";
	public static String txtVarApSysMgmtDOSIpAddress = "The blocked IP address : ";
	public static String txtVarApSysMgmtTaskDelete = "The text public static String of the task that has been deleted from the system : ";

	

	public static String txtVarApSysMgmtAlgdCPULoad = "The value is the CPU utilization percentage of application tasks. : ";
	public static String txtVarApSysMgmtSipInterfaceRealmName = "The realm of the Sip Interface that is changing status. : ";
	public static String txtVarApapSysMgmtSipInterfaceIP = "The ip address of the Sip Interface that is changing status. : ";
	public static String txtVarApapSysMgmtSipInterfaceStatus = "The status that the Sip Interface is changing into. : ";
	public static String txtVarApapSysMgmtSipInterfaceStatusReason = "The reason for the status change of the Sip Interface. : ";
	public static String txtVarApENUMConfigName = "The name of the enum-config element that contains this ENUM server. : ";
	public static String txtApENUMServerIpAddress = "The IP address of this ENUM server. : ";
	public static String txtApENUMServerStatus = "The status of this ENUM server. : ";
	public static String txtApSysMgmtCollectPushServerUnreachable = "The value is the push server that is or was unreachable from the system collector. : ";
	public static String txtApSysMgmtNTPServer = "The value is the server that is or was unreachable from the NTP. : ";
	public static String txtApSysMgmtSurrogateRegHost = "The registrar host name for a surrogate registration : ";
	public static String txtApSysMgmtSurrogateRegAor = "The address of record used in a surrogate registration : ";
	public static String txtApSysMgmtDOSFromUri = "The FROM header of the message that caused the block (if available). : ";
	public static String txtApSysMgmtDOSRealmID = "The blocked Realm ID. : ";
	public static String txtVarApSwCfgTrapPreviousVersion = "The previous version before this trap happened : ";
	public static String txtVarApSwCfgTrapCurrentVersion = "The current version after this trap happened : ";

	// BROADSOFT CONSTANTS

	// Specific Problem Definition - SUN PLATFORM MIB
	public static String sunPlatObjectCreationSP = "Indicates that an object, indicated by sunPlatNotificationObject,has been created as defined in X.721";
	public static String sunPlatObjectDeletionSP = "Indicates that an object, indicated by sunPlatNotificationObject,has been deleted as defined in X.721";
	public static String sunPlatCommunicationsAlarmSP = "Indicates that a communication alarm has occurred as defined in X.721";
	public static String sunPlatEnvironmentalAlarmSP = "Indicates that an environment alarm has occurred as defined in X.721";
	public static String sunPlatEquipmentAlarmSP = "Indicates that an environment alarm has occurred as defined in X.721";
	public static String sunPlatProcessingErrorAlarmSP = "Indicates that a processing error alarm has occurred as defined in X.721";
	public static String sunPlatStateChangeSP = "Indicates that a state change has occurred on the state attribute  identified by sunPlatNotificationChangedOID. As states are  enumerations of type INTEGER, the old and new states are passed in  sunPlatNotificationOldInteger and sunPlatNotificationNewInteger  respectively";
	public static String sunPlatAttributeChangeIntegerSP = "Indicates that attribute identified by sunPlatNotificationChangedOID has changed from the value in sunPlatNotificationOldInteger to the value in sunPlatNotificationNewInteger. This is as defined in X.721";
	public static String sunPlatAttributeChangeStringSP = "Indicates that attribute identified by sunPlatNotificationChangedOID has changed from the value in sunPlatNotificationOldString to the  value in sunPlatNotificationNewString. This is as defined in X.721";
	public static String sunPlatAttributeChangeOIDSP = "Indicates that attribute identified by sunPlatNotificationChangedOID  has changed from the value in sunPlatNotificationOldOID to the value in sunPlatNotificationNewOID.This is as defined in X.721";
	public static String sunPlatQualityOfServiceAlarmSP = "Indicates that a quality of service alarm has occurred as defined in  X.721";
	public static String sunPlatIndeterminateAlarmSP = "Indicates that an alarm of an indeterminate type has occurred";

	public static String broadworksSP = "A notification indicates detection of an abnormal condition in the system";
	public static String broadworksAddSP = "Specific Problem defined in Additional Text";

	// Specific Traps - ENTITY MIB
	public static String entConfigChange = "An entConfigChange notification is generated when the valueof entLastChangeTime changes";

	// IF MIB CONSTANTS

	// Additional Text
	public static String coldStart = "A coldStart trap signifies that the sending protocol entity is reinitializing itself such that the agent's configuration or the protocol entity implementation may be  altered.";
	public static String warmStart = "A warmStart trap signifies that the sending protocol entity is reinitializing itself such that neither the agent configuration nor the protocol entity implementation is altered.";
	public static String linkDown = "A linkDown trap signifies that the sending protocol entity recognizes a failure in one of the communication links represented in the agent's configuration.";
	public static String linkUp = "A linkUp trap signifies that the sending protocol entity recognizes that one of the communication links  represented in the agent's configuration has come up.";
	public static String authenticationFailure = "An authenticationFailure trap signifies that the sending protocol entity is the addressee of a protocol message that is not properly authenticated.";
	public static String egpNeighborLoss = "An egpNeighborLoss trap signifies that an EGP neighbor for whom the sending protocol entity was an EGP peer has been marked down and the peer relationship no longer obtains.";

	public static String IfAdminState = "The Adminstrative State of the Interface is: ";
	public static String IfOperState = "The operational State of the Interface is: ";
	
	 //mmmc related constants
	 
	 public static String mmsCmSystemOpState = "The Operational state of the system is : ";
	 public static String mmsCmSystemAdState = "Adminstrative state of the system is : ";
	 public static String AlarmOBjClass = "The class of the network resources associated with the event or alarm: ";
	 public static String AlarmOBjInstance = "The instance (of a class) of the network resource associated with the event or alarm: ";
	 public static String eventTime = "The time the event or alarm was sent: ";
	 
	 // axd constant
	 public static String severityOID = ".1.3.6.1.4.1.193.14.1.1.3.2.2.3.1.6";

}
