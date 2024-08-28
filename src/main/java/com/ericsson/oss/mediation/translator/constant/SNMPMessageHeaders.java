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
package com.ericsson.oss.mediation.translator.constant;

/**
 * Utility class for declaring header names that will go into DataPath
 * 
 * @author edejket
 * 
 */
public class SNMPMessageHeaders {

	public static final String SNMPSU_IPADDRESS = "ipaddress";
	public static final String SNMPSU_PORT = "port";
	public static final String SNMPSU_VERSION = "protocolVersion";
	public static final String SNMPSU_COMMUNITY = "communitystring";
	public static final String SNMPSU_AUTHENTICPROTOCOL = "authenticationprotocol";
	public static final String SNMPSU_AUTHENTICATIONPASSWORD = "authpassword";
	public static final String SNMPSU_PRIVACYPROTOCOL = "privacyprotocol";
	public static final String SNMPSU_PRIVACYPASSWORD = "privacypassword";
	public static final String SNMPSU_USER = "user";
	public static final String SNMPSU_CONTEXT = "context";
	public static final String SNMPSU_SOURCETYPE = "sourcetype";
	public static final String SNMPSU_FDN = "fdn";
	public static final String SNMPSU_HEARTBEATTIMEOUT = "heartbeattimeout";

	public static final String SOURCETYPE = "SourceType";
	public static final String HEARTBEATNOTIFICATION = "HeartBeatNotification";
	public static final String SPECIFICPROBLEM = "specificProblem";
	public static final String COMMUNICATIONSTATUS = "CommunicationStatus";
	public static final String PROBLEMTEXT = "problemText";
	public static final String HEARTBEATTRAP = "heartbeattrap";
	public static final String ALARM = "alarm";
	
	public static final String SNMP_NODE="nodeAddress";
	public static final String ALARM_SUPERVISION="alarmsupervision";

}
