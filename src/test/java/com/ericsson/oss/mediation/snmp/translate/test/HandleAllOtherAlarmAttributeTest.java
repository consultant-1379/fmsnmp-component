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

package com.ericsson.oss.mediation.snmp.translate.test;

 import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.alarm.handlers.*;
public class HandleAllOtherAlarmAttributeTest {
	
	@Test
	public void getaccAlarmCauseTest(){
		String attributeValue = "10";
		Assert.assertEquals("313",AlarmCause.getaccAlarmCause(attributeValue));
	}
	
	@Test
	public void getsrdNotificationAddTextTest(){
		String attributeValue = "122";
		Assert.assertEquals("",AlarmCause.getsrdNotificationAddText(attributeValue));
	}
	
	@Test
	public void getTrapEnumResultTest(){
		String attributeValue = "49";
		Assert.assertEquals("unknown(49).",TrapEnumHandler.getTrapEnumResult(attributeValue));
	}
	
	@Test
	public void getTrapEnumStatusTest(){
		String attributeValue = "2";
		Assert.assertEquals("notWorking(2).",TrapEnumHandler.getTrapEnumStatus(attributeValue));
	}
	
	@Test
	public void getTrapEnumCauseTest(){
		String attributeValue = "13";
		Assert.assertEquals("statusChangeInformation(13).",TrapEnumHandler.getTrapEnumCause(attributeValue));
	}
	
	@Test
	public void getfunkSbrTrapVarCompTest(){
		String attributeValue = "3";
		Assert.assertEquals("The Component that issued the Trap is  authentication(3).",TrapEnumHandler.getfunkSbrTrapVarComp(attributeValue));
	}
	
	@Test
	public void getInsVrBgpPeerStateTest(){
		String attributeValue = "4";
		Assert.assertEquals("opensent",TrapEnumHandler.getInsVrBgpPeerState(attributeValue));
	}
	
	@Test
	public void getbgposfpTrapTypeTest(){
		String attributeValue1 = "554";
		String attributeValue2= "1";
		String attributeValue3 = "4";
		String attributeValue4= "7";
		String attributeValue5 = "9";
		
		
		Assert.assertEquals("av-scan-mgr",BGPOSFPTrapTypes.getbgposfpTrapType(attributeValue1));
		Assert.assertEquals("traffic-sec",BGPOSFPTrapTypes.getbgposfpTrapType(attributeValue2));
		Assert.assertEquals("winnuke",BGPOSFPTrapTypes.getbgposfpTrapType(attributeValue3));
		Assert.assertEquals("ping-death",BGPOSFPTrapTypes.getbgposfpTrapType(attributeValue4));
		Assert.assertEquals("ip-src-route",BGPOSFPTrapTypes.getbgposfpTrapType(attributeValue5));
		
	}
	
	@Test
	public void getnetraalarmpswith5Test(){
		String attributeValue1 = "ASDNS Node UP";
		String attributeValue2 = "DNS Server Started successfully";
		String attributeValue3 = "Storage Server stopped on operator request or caused by serious error. Check logs for the cause of the problem.";
		String attributeValue4 = "Disk Array returns to normal state successfully";
		int value =5;
		Assert.assertEquals("ASDNS Node is down",NetTraAlarmPSwith5.getnetraalarmpswith5(attributeValue1,value)[0]);
		Assert.assertEquals("DNS Server is down. Please check the Problem Text for more information",NetTraAlarmPSwith5.getnetraalarmpswith5(attributeValue2,value)[0]);
		Assert.assertEquals("EmS Server is down. Please check the Problem Text for more information",NetTraAlarmPSwith5.getnetraalarmpswith5(attributeValue3,value)[0]);
		Assert.assertEquals("Disk Array problem. Please check the Problem Text for more information",NetTraAlarmPSwith5.getnetraalarmpswith5(attributeValue4,value)[0]);
	}
	
	@Test
	public void getIfAdminStateTest(){
		String attributeValue = "2";
		Assert.assertEquals("The Adminstrative State of the Interface is: down(2).",AlarmState.getIfAdminState(attributeValue));
	}
	
	@Test
	public void getIfOperStateTest(){
		String attributeValue = "6";
		Assert.assertEquals("The operational State of the Interface is: notPresent(6).",AlarmState.getIfOperState(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATETest(){
		String attributeValue = "6";
		Assert.assertEquals("The previous state of the object : shutdown(6).",AlarmState.getOidVarAPEnvMonTrapPreviousState(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATETest(){
		String attributeValue = "8";
		Assert.assertEquals("The current state of the object which causes the trap to occur : notFunctioning(8).",AlarmState.getOidVarAPEnvMonTrapCurrentState(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_LOG_HIST_LEVELTest(){
		String attributeValue = "4";
		Assert.assertEquals("The Log-Level of the message : minor(4) .",AlarmState.getOidVarAPSysLogHistLevel(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_POWER_PRESENCETest(){
		String attributeValue = "1";
		Assert.assertEquals("Power Presence : up(1).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_RED_ROLETest(){
		String attributeValue = "0";
		Assert.assertEquals("System Management Red Role (unit in a redundant pair) : primary(0).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RED_ROLE(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_FAN_LOCATIONTest(){
		String attributeValue = "0";
		Assert.assertEquals("Fan Location : left(0).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_FAN_LOCATION(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATETest(){
		String attributeValue = "2";
		Assert.assertEquals("The state that the system, give by location, is transitioning too : standby(2).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_RADIUS_DOWNTest(){
		String attributeValue = "1";
		Assert.assertEquals("The value identifies if all the radius connections are down or if just some of the radius connects have become unreachable : some-servers-down(1).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASONTest(){
		String attributeValue = "3";
		Assert.assertEquals("The reason for the status change of the Session Agent : inservice(3).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVELTest(){
		String attributeValue = "1";
		Assert.assertEquals("The state a user was trying to switch to or from when failing to authenticate : user(1).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATETest(){
		String attributeValue = "3";
		Assert.assertEquals("The protocol a user was using when failing to authenticate : ssh(3).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTOTest(){
		String attributeValue = "2";
		Assert.assertEquals("The protocol a user was using when failing to authenticate : ftp(2).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUSTest(){
		String attributeValue = "1";
		Assert.assertEquals("The status that the Sip Interface is changing into. : outofservice(1).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS(attributeValue));
	}
	
	@Test
	public void get_OID_VAR_AP_ENUM_SERVER_STATUSTest(){
		String attributeValue = "1";
		Assert.assertEquals("The status of this ENUM server. : lowerpriority(1).",OidVarApSysMgmtHandlers.get_OID_VAR_AP_ENUM_SERVER_STATUS(attributeValue));
	}
	
	@Test
	public void getextremeTrapAuthorerrorTypeTest(){
		String attributeValue1 = "3";
		String attributeValue2 = "1";
		String attributeValue3 = "2";
		
		Assert.assertEquals("authFailure",ExtreamTrapSystem.getextremeTrapAuthorerrorType(attributeValue1));
		Assert.assertEquals("invalidAuthType",ExtreamTrapSystem.getextremeTrapAuthorerrorType(attributeValue2));
		Assert.assertEquals("authTypeMismatch",ExtreamTrapSystem.getextremeTrapAuthorerrorType(attributeValue3));
	}
	
	@Test
	public void getextremeSystemPowerStateTest(){
		String attributeValue1 = "4";
		String attributeValue2 = "1";
		String attributeValue3 = "2";
		String attributeValue4 = "3";
		
		
		Assert.assertEquals("insufficientPower",ExtreamTrapSystem.getextremeSystemPowerState(attributeValue1));
		Assert.assertEquals("computing",ExtreamTrapSystem.getextremeSystemPowerState(attributeValue2));
		Assert.assertEquals("sufficientButNotRedundantPower",ExtreamTrapSystem.getextremeSystemPowerState(attributeValue3));
		Assert.assertEquals("redundantPowerAvailable",ExtreamTrapSystem.getextremeSystemPowerState(attributeValue4));
		
	}
	
	@Test
	public void getextremePortMauStatusTest(){
		String attributeValue = "2";
		Assert.assertEquals("empty",ExtreamTrapSystem.getextremePortMauStatus(attributeValue));
	}
	
	@Test
	public void getextremeEsrpDmnStateTest(){
		String attributeValue = "1";
		Assert.assertEquals("master",ExtreamTrapSystem.getextremeEsrpDmnState(attributeValue));
	}
	
	@Test
	public void getrdbmsRelStateTest(){
		String attributeValue1 = "3";
		String attributeValue2 = "1";
		String attributeValue3 = "2";
		String attributeValue4 = "4";
		
		Assert.assertEquals("The state of this server's access to this database is: available(3).",ExtreamTrapSystem.getrdbmsRelState(attributeValue1));
		
	}
	
	@Test
	public void getoraListenerStateTest(){
		String attributeValue = "2";
		Assert.assertEquals("The current state of the Generic Listener is : down(2).",ExtreamTrapSystem.getoraListenerState(attributeValue));
	}
	
	@Test
	public void getmmsFmSystemAlarmIDTest(){
		String attributeValue = "2";
		Assert.assertEquals("2",NMSSystemHandlers.getmmsFmSystemAlarmID(attributeValue));
	}
	
	@Test
	public void getmerOperationalStateTest(){
		String attributeValue = "2";
		Assert.assertEquals("Operational state of MER component is : disabled(2).",NMSSystemHandlers.getmerOperationalState(attributeValue));
	}
	
	@Test
	public void getmerAdminstrativeStateTest(){
		String attributeValue = "2";
		Assert.assertEquals("Adminstrative state of MER component is : locked(2).",NMSSystemHandlers.getmerAdminstrativeState(attributeValue));
	}
	
	@Test
	public void getmmsCmSystemOperationalStateTest(){
		String attributeValue = "2";
		Assert.assertEquals("The Operational state of the system is : locked(2).",NMSSystemHandlers.getmmsCmSystemOperationalState(attributeValue));
	}
	
	@Test
	public void getmmsCmSystemAdministrativeStateTest(){
		String attributeValue = "1";
		Assert.assertEquals("Adminstrative state of the system is : unlocked(1).",NMSSystemHandlers.getmmsCmSystemAdministrativeState(attributeValue));
	}
	
	@Test
	public void getaxd301pchIndicatorTest(){
		String attributeValue = "4";
		Assert.assertEquals("The PCH Operation block",OtherAlarmHandlers.getaxd301pchIndicator(attributeValue));
	}
	
	@Test
	public void getaxdloopbackModeTest(){
		String attributeValue = "1";
		Assert.assertEquals("The loopback OAM cell was looped back at segment end point",OtherAlarmHandlers.getaxdloopbackMode(attributeValue));
	}
	
	@Test
	public void getaxdloopbackResultTest(){
		String attributeValue = "2";
		Assert.assertEquals("The loopback was un successful",OtherAlarmHandlers.getaxdloopbackResult(attributeValue));
	}
	
	@Test
	public void getaxdoamOperationTest(){
		String attributeValue = "6";
		Assert.assertEquals("OAM Operation isclearCcSourceLinkSide",OtherAlarmHandlers.getaxdoamOperation(attributeValue));
	}
	
	@Test
	public void getaxdoamResultTest(){
		String attributeValue = "2";
		Assert.assertEquals(" and OAM operation was un successful",OtherAlarmHandlers.getaxdoamResult(attributeValue));
	}
	
	@Test
	public void getaxdpchVpVcFaultTypeTest(){
		String attributeValue = "7";
		Assert.assertEquals("Fault is segmentAisCoreSide",OtherAlarmHandlers.getaxdpchVpVcFaultType(attributeValue));
	}
	
	@Test
	public void getaxdpchVpVcFaultLocationTest(){
		String attributeValue = "7";
		Assert.assertEquals("Fault is segmentAisCoreSide",OtherAlarmHandlers.getaxdpchVpVcFaultLocation(attributeValue));
	}
	
	@Test
	public void getaxdbuStatusTest(){
		String attributeValue = "3";
		Assert.assertEquals("The status of the backup functionality is prohibited",OtherAlarmHandlers.getaxdbuStatus(attributeValue));
	}
	
	@Test
	public void getaxdbuResultTest(){
		String attributeValue = "1";
		Assert.assertEquals("BUResult=Input Error",OtherAlarmHandlers.getaxdbuResult(attributeValue));
	}
	
	
	
	
	@Test
	public void getaxdoptDmOpResultTest(){
		String attributeValue = "2";
		Assert.assertEquals("The optDmOpResult is ready",AXDPAStatus.getaxdoptDmOpResult(attributeValue));
	}
	
	@Test
	public void getaxdpaStatusTest(){
		String attributeValue = "1";
		Assert.assertEquals("The status on the patch functionality is installing",AXDPAStatus.getaxdpaStatus(attributeValue));
	}
	
	@Test
	public void getaxdugStatusTest(){
		String attributeValue = "5";
		Assert.assertEquals("The status on the upgrade functionality is activated",AXDPAStatus.getaxdugStatus(attributeValue));
	}
	
	@Test
	public void getaxdbgpPeerStateTest(){
		String attributeValue = "4";
		Assert.assertEquals("The BGP peer connection state is OpenSent",AXDPAStatus.getaxdbgpPeerState(attributeValue));
	}
	
	@Test
	public void getaxdospfNbrStateTest(){
		String attributeValue = "6";
		Assert.assertEquals("exchange",AXDPAStatus.getaxdospfNbrState(attributeValue));
	}
	
	@Test
	public void getaxdisisISAdjStateTest(){
		String attributeValue = "3";
		Assert.assertEquals("The state of the adjacency is up",AXDPAStatus.getaxdisisISAdjState(attributeValue));
	}
	
	@Test
	public void getaxdmigLoopbackStatusTest(){
		String attributeValue = "2";
		Assert.assertEquals("The state of the permanently looped timeslots for LoopBack is success",AXDPAStatus.getaxdmigLoopbackStatus(attributeValue));
	}
	
	@Test
	public void getaxdpingNotificationTypeTest(){
		String attributeValue = "2";
		Assert.assertEquals("TestFailure",AXDPAStatus.getaxdpingNotificationType(attributeValue));
	}
	
	@Test
	public void getaxdpingEventCtlTestNameTest(){
		String attributeValue = "3";
		Assert.assertEquals("TestCompletion",AXDPAStatus.getaxdpingEventCtlTestName(attributeValue));
	}
	
	@Test
	public void getaxdpingResultsOperStatusTest(){
		String attributeValue = "1";
		Assert.assertEquals("enabled",AXDPAStatus.getaxdpingResultsOperStatus(attributeValue));
	}
	
	@Test
	public void getaxdtraceRouteNotificationTypeTest(){
		String attributeValue = "2";
		Assert.assertEquals("testFailure",AXDPAStatus.getaxdtraceRouteNotificationType(attributeValue));
	}
	
	@Test
	public void getaxdifMauJabberStateTest(){
		String attributeValue = "4";
		Assert.assertEquals("jabbering",AXDPAStatus.getaxdifMauJabberState(attributeValue));
	}
	
	@Test
	public void getaxdgcpLinkReasonCodeTest(){
		String attributeValue = "2";
		Assert.assertEquals("Link communication is up.",AXDPAStatus.getaxdgcpLinkReasonCode(attributeValue));
	}
	
	@Test
	public void getaxdlogRecAcStatusTest(){
		String attributeValue = "4";
		Assert.assertEquals("LogRecAcStatus=abortOk",AXDPAStatus.getaxdlogRecAcStatus(attributeValue));
	}
	
	@Test
	public void getaxdsecOperationTest(){
		String attributeValue = "2";
		Assert.assertEquals("Operation type when security data is changed=changed",AXDPAStatus.getaxdsecOperation(attributeValue));
	}
	
	@Test
	public void getaxdmrErrorCodeTest(){
		String attributeValue = "1";
		Assert.assertEquals("The mesurement Error Code=unSpecified",AXDPAStatus.getaxdmrErrorCode(attributeValue));
	}
	
	@Test
	public void getaxdperfFileFcodeTest(){
		String attributeValue = "9";
		Assert.assertEquals("euser",AXDPAStatus.getaxdperfFileFcode(attributeValue));
	}
	
	@Test
	public void getaxdmplsXCAdminStatusTest(){
		String attributeValue = "2";
		Assert.assertEquals("If AdminStatus is=Down",AXDPAStatus.getaxdmplsXCAdminStatus(attributeValue));
	}
	
	@Test
	public void getaxdrsvpIfOperStatusTest(){
		String attributeValue = "2";
		Assert.assertEquals("An event reporting that operational state of the RSVP rsvpIfOperStatus=down",AXDPAStatus.getaxdrsvpIfOperStatus(attributeValue));
	}
	
	@Test
	public void getaxdmplsIfOperStatusTest(){
		String attributeValue = "1";
		Assert.assertEquals("An event reporting that an interface state  has changed to up",AXDPAStatus.getaxdmplsIfOperStatus(attributeValue));
	}
	
	@Test
	public void getaxdmplsLdpSesStateTest(){
		String attributeValue = "1";
		Assert.assertEquals("An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState=nonexistent",AXDPAStatus.getaxdmplsLdpSesState(attributeValue));
	}
	
	@Test
	public void getspvcOperationIndicatorTest(){
		String attributeValue = "4";
		Assert.assertEquals("reroute",AXDStates.getspvcOperationIndicator(attributeValue));
	}
	
	@Test
	public void getaxdifadminTest(){
		String attributeValue = "2";
		Assert.assertEquals("down",AXDStates.getaxdifadmin(attributeValue));
	}
	
	@Test
	public void getaxdifoperTest(){
		String attributeValue = "4";
		Assert.assertEquals("If OperStatus is=Unknown",AXDStates.getaxdifoper(attributeValue));
	}
	
	@Test
	public void getaxd301dnaInterfaceEnteringMethodTest(){
		String attributeValue = "2";
		Assert.assertEquals("automatic",AXDStates.getaxd301dnaInterfaceEnteringMethod(attributeValue));
	}
	
	@Test
	public void getaxdatmfVccOperStatusTest(){
		String attributeValue = "4";
		Assert.assertEquals("localUpEnd2endUnknown",AXDStates.getaxdatmfVccOperStatus(attributeValue));
	}
	
	
}
