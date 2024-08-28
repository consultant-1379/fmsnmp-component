package com.ericsson.oss.mediation.translator.constant;

public class NetsipraConstants {

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

	// Updated by XWOPOTY to implement WP00645 for OSSRC6.2 Ship R3G
	public static final int _TRAP_TYPE_ECS_VS_MAXIMUM = 115;
	public static final int _TRAP_TYPE_ECS_FLOW_MAXIMUM = 116;
	public static final int _TRAP_TYPE_ECS_CHILD_FLOW_MAXIMUM = 117;
	public static final int _TRAP_TYPE_ECS_NO_TRAFFIC_PROCESSED = 123;
	public static final int _TRAP_TYPE_ECS_SASN_FAILS_IN_HANDLING_UDR = 125;

	// Updated by XWOALRA to implement Work Item 1.6 in order to include NS-MPT
	// MIB.
	public static final int _TRAP_TYPE_MPT_CONTROLLER_FAULT = 1000;

	// ------------------------------------------------------------------------
	// MIB text (Description) for traps, this is mapped as FM Specific Problem
	// ------------------------------------------------------------------------
	// Updated by XBLEKUR for CR1155 delivered in O10.2.8
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

}
