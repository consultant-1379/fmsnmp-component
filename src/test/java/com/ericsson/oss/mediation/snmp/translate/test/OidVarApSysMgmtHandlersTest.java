package com.ericsson.oss.mediation.snmp.translate.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.constant.TranslationConstant;
import com.ericsson.oss.mediation.translator.model.alarm.handlers.OidVarApSysMgmtHandlers;

public class OidVarApSysMgmtHandlersTest {

	@Test
	public void get_OID_VAR_AP_SYS_MGMT_POWER_PRESENCEtest() {
		
		String txtVarApSysMgmtPowerPresence1=TranslationConstant.txtVarApSysMgmtPowerPresence + "down(0).";
		String txtVarApSysMgmtPowerPresence2=TranslationConstant.txtVarApSysMgmtPowerPresence + "up(1).";
		
		Assert.assertEquals(txtVarApSysMgmtPowerPresence1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE("0"));
		Assert.assertEquals(txtVarApSysMgmtPowerPresence2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE("1"));
		
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_FAN_LOCATIONTest(){
		
		String txtVarApSysMgmtFanLocation1=TranslationConstant.txtVarApSysMgmtFanLocation + ("left(0).");
		String txtVarApSysMgmtFanLocation2=TranslationConstant.txtVarApSysMgmtFanLocation + ("middle(1).");
		String txtVarApSysMgmtFanLocation3=TranslationConstant.txtVarApSysMgmtFanLocation + ("right(2).");
		
		Assert.assertEquals(txtVarApSysMgmtFanLocation1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_FAN_LOCATION("0"));
		Assert.assertEquals(txtVarApSysMgmtFanLocation2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_FAN_LOCATION("1"));
		Assert.assertEquals(txtVarApSysMgmtFanLocation3,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_FAN_LOCATION("2"));
	}

	@Test
	public void get_OID_VAR_AP_SYS_MGMT_RED_ROLETest(){
		
		String txtVarApSysMgmtRedRole1=TranslationConstant.txtVarApSysMgmtRedRole + ("primary(0).");
		String txtVarApSysMgmtRedRole2=TranslationConstant.txtVarApSysMgmtRedRole + ("secondary(1).");
		
		Assert.assertEquals(txtVarApSysMgmtRedRole1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RED_ROLE("0"));
		Assert.assertEquals(txtVarApSysMgmtRedRole2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RED_ROLE("1"));
		
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATETest(){
		
		String txtVarApSysMgmtRedTransState1=TranslationConstant.txtVarApSysMgmtRedTransState + ("out-of-service(0).");
		String txtVarApSysMgmtRedTransState2=TranslationConstant.txtVarApSysMgmtRedTransState + ("active(1).");
		String txtVarApSysMgmtRedTransState3=TranslationConstant.txtVarApSysMgmtRedTransState +  ("standby(2).");
		String txtVarApSysMgmtRedTransState4=TranslationConstant.txtVarApSysMgmtRedTransState +  ("no-peer(3).");
		
		Assert.assertEquals(txtVarApSysMgmtRedTransState1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE("0"));
		Assert.assertEquals(txtVarApSysMgmtRedTransState2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE("1"));
		Assert.assertEquals(txtVarApSysMgmtRedTransState3,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE("2"));
		Assert.assertEquals(txtVarApSysMgmtRedTransState4,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE("3"));
		
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_RADIUS_DOWNTest(){
		
		 String txtVarApSysMgmtRadiusDown1=TranslationConstant.txtVarApSysMgmtRadiusDown + ("all-servers-down(0).");
		 String txtVarApSysMgmtRadiusDown2=TranslationConstant.txtVarApSysMgmtRadiusDown +  ("some-servers-down(1).");
		 
		 Assert.assertEquals(txtVarApSysMgmtRadiusDown1, OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN("0"));
		 Assert.assertEquals(txtVarApSysMgmtRadiusDown2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN("1"));
		 
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASONTest(){
		
		String txtVarApSysMgmtSAStatusReason1=TranslationConstant.txtVarApSysMgmtSAStatusReason + ("administrative(0).");
		String txtVarApSysMgmtSAStatusReason2=TranslationConstant.txtVarApSysMgmtSAStatusReason + ("oosbyproxyerror(1).");
		String txtVarApSysMgmtSAStatusReason3=TranslationConstant.txtVarApSysMgmtSAStatusReason + ("standby(2).");
		String txtVarApSysMgmtSAStatusReason4=TranslationConstant.txtVarApSysMgmtSAStatusReason + ("inservice(3).");
		String txtVarApSysMgmtSAStatusReason5=TranslationConstant.txtVarApSysMgmtSAStatusReason +  ("constraintsexceeded(4).");
		String txtVarApSysMgmtSAStatusReason6=TranslationConstant.txtVarApSysMgmtSAStatusReason +  ("unresponsive(5).");
		
		Assert.assertEquals(txtVarApSysMgmtSAStatusReason1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON("0"));
		Assert.assertEquals(txtVarApSysMgmtSAStatusReason2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON("1"));
		Assert.assertEquals(txtVarApSysMgmtSAStatusReason3,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON("2"));
		Assert.assertEquals(txtVarApSysMgmtSAStatusReason4,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON("3"));
		Assert.assertEquals(txtVarApSysMgmtSAStatusReason5,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON("4"));
		Assert.assertEquals(txtVarApSysMgmtSAStatusReason6,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON("5"));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVELTest(){
		
		 String txtVarApSysMgmtAuthFailLevel1=TranslationConstant.txtVarApSysMgmtAuthFailLevel + ("login(0).");
		 String txtVarApSysMgmtAuthFailLevel2=TranslationConstant.txtVarApSysMgmtAuthFailLevel + ("user(1).");
		 String txtVarApSysMgmtAuthFailLevel3=TranslationConstant.txtVarApSysMgmtAuthFailLevel + ("priv(2).");
		 String txtVarApSysMgmtAuthFailLevel4=TranslationConstant.txtVarApSysMgmtAuthFailLevel +  ("shell(3).");
		 
		 Assert.assertEquals(txtVarApSysMgmtAuthFailLevel1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL("0"));
		 Assert.assertEquals(txtVarApSysMgmtAuthFailLevel2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL("1"));
		 Assert.assertEquals(txtVarApSysMgmtAuthFailLevel3,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL("2"));
		 Assert.assertEquals(txtVarApSysMgmtAuthFailLevel4,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL("3"));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATETest(){
		
		String txtVarApSysMgmtAuthFailProto1=TranslationConstant.txtVarApSysMgmtAuthFailProto + ("console(0).");
		String txtVarApSysMgmtAuthFailProto2=TranslationConstant.txtVarApSysMgmtAuthFailProto + ("telnet(1).");
		String txtVarApSysMgmtAuthFailProto3=TranslationConstant.txtVarApSysMgmtAuthFailProto + ("ftp(2).");
		String txtVarApSysMgmtAuthFailProto4=TranslationConstant.txtVarApSysMgmtAuthFailProto + ("ssh(3).");
		String txtVarApSysMgmtAuthFailProto5=TranslationConstant.txtVarApSysMgmtAuthFailProto + ("sftp(4).");
		
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE("0"));
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE("1"));
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto3,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE("2"));
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto4,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE("3"));
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto5,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE("4"));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTOTest(){
		
		String txtVarApSysMgmtAuthFailProto1 = TranslationConstant.txtVarApSysMgmtAuthFailProto + ("console(0).");
		String txtVarApSysMgmtAuthFailProto2=  TranslationConstant.txtVarApSysMgmtAuthFailProto + ("telnet(1).");
		String txtVarApSysMgmtAuthFailProto3 = TranslationConstant.txtVarApSysMgmtAuthFailProto + ("ftp(2).");
		String txtVarApSysMgmtAuthFailProto4 = TranslationConstant.txtVarApSysMgmtAuthFailProto +  ("ssh(3).");
		String txtVarApSysMgmtAuthFailProto5 = TranslationConstant.txtVarApSysMgmtAuthFailProto +  ("sftp(4).");
		
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO("0"));
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO("1"));
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto3,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO("2"));
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto4,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO("3"));
		Assert.assertEquals(txtVarApSysMgmtAuthFailProto5,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO("4"));
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASONTest(){
		
		String txtVarApapSysMgmtSipInterfaceStatusReason1=TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason + ("administrative(0).");
		String txtVarApapSysMgmtSipInterfaceStatusReason2=TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason + ("oosbyproxyerror(1) .");
		String txtVarApapSysMgmtSipInterfaceStatusReason3=TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason + ("standby(2).");
		String txtVarApapSysMgmtSipInterfaceStatusReason4=TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason + ("inservice(3).");
		String txtVarApapSysMgmtSipInterfaceStatusReason5=TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason + ("constraintsexceeded(4).");
		String txtVarApapSysMgmtSipInterfaceStatusReason6=TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason + ("unresponsive(5).");
		
		Assert.assertEquals(txtVarApapSysMgmtSipInterfaceStatusReason1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON("0"));
		Assert.assertEquals(txtVarApapSysMgmtSipInterfaceStatusReason2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON("1"));
		Assert.assertEquals(txtVarApapSysMgmtSipInterfaceStatusReason3,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON("2"));
		Assert.assertEquals(txtVarApapSysMgmtSipInterfaceStatusReason4,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON("3"));
		Assert.assertEquals(txtVarApapSysMgmtSipInterfaceStatusReason5,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON("4"));
		Assert.assertEquals(txtVarApapSysMgmtSipInterfaceStatusReason6,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON("5"));
		
	}
	
	@Test
	public void get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUSTest(){
		
		String txtVarApapSysMgmtSipInterfaceStatus1 = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatus + ("inservice(0).");
		String txtVarApapSysMgmtSipInterfaceStatus2 = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatus +	 ("outofservice(1).");
		
		Assert.assertEquals(txtVarApapSysMgmtSipInterfaceStatus1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS("0"));
		Assert.assertEquals(txtVarApapSysMgmtSipInterfaceStatus2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS("1"));
		
	}
	
	@Test
	public void get_OID_VAR_AP_ENUM_SERVER_STATUSTest(){
		
		 String txtApENUMServerStatus1 = TranslationConstant.txtApENUMServerStatus + ("inservice(0).");
	     String txtApENUMServerStatus2 = TranslationConstant.txtApENUMServerStatus + ("lowerpriority(1).");
	     String txtApENUMServerStatus3 = TranslationConstant.txtApENUMServerStatus + ("oosunreachable(2).");
	     
	     Assert.assertEquals(txtApENUMServerStatus1,OidVarApSysMgmtHandlers.get_OID_VAR_AP_ENUM_SERVER_STATUS("0"));
	     Assert.assertEquals(txtApENUMServerStatus2,OidVarApSysMgmtHandlers.get_OID_VAR_AP_ENUM_SERVER_STATUS("1"));
	     Assert.assertEquals(txtApENUMServerStatus3,OidVarApSysMgmtHandlers.get_OID_VAR_AP_ENUM_SERVER_STATUS("2"));
	}
}
