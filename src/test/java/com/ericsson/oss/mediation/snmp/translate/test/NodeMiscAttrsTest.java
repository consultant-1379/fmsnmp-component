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

import junit.framework.Assert;

import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.NodeMiscAttrs;

import com.ericsson.oss.mediation.translator.model.EventNotification;

public class NodeMiscAttrsTest {
	/*@Test
	public void test1() {
		final String attributeName = "IFADMINSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("ifAdminStatus"),
				"The Adminstrative State of the Interface is: up(1).");
	}

	@Test
	public void test2() {
		final String attributeName = "IFOPERSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("ifOperStatus"),
				"The operational State of the Interface is: up(1).");
	}*/

	@Test
	public void test3() {
		final String attributeName = "ALARMMODELDESCRIPTION";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "alarmModelDescription"),
				"1");
	}

	@Test
	public void test4() {
		final String attributeName = "ALARMACTIVERESOURCEID";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "alarmModelDescription"),
				"1");
	}

	@Test
	public void test5() {
		final String attributeName = "ALARMACTIVEDESCRIPTION";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "alarmModelDescription"),
				"1");
	}

	@Test
	public void test6() {
		final String attributeName = "ITUALARMEVENTTYPE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "alarmModelDescription"),
				"1");
	}

	@Test
	public void test7() {
		final String attributeName = "ITUALARMEVENTTYPEB";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "alarmModelDescription"),
				"1");
	}

	@Test
	public void test8() {
		final String attributeName = "ITUALARMEVENTTYPEB";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "alarmModelDescription"),
				"1");
	}

	@Test
	public void test9() {
		final String attributeName = "ITUPROBABLECAUSE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "alarmModelDescription"),
				"1");
	}

	@Test
	public void test10() {
		final String attributeName = "ITUPROBABLECAUSEB";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "alarmModelDescription"),
				"1");
	}

	//@Test
	public void test11() {
		final String attributeName = "SRDNOTIFICATIONADDTEXT";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("srdNotificationAddText"),
				"1");
	}

	@Test
	public void test12() {
		final String attributeName = "ISBLADETRAPENUMRESULT";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("isTrapEnumResult"),
				"ok(1).");
	}

	@Test
	public void test13() {
		final String attributeName = "SBGENUMRESULT";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("isTrapEnumResult"),
				"ok(1).");
	}

	@Test
	public void test14() {
		final String attributeName = "ISBLADETRAPENUMSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("isTrapEnumStatus"),
				"working(1).");
	}

	@Test
	public void test15() {
		final String attributeName = "SBGENUMSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("isTrapEnumStatus"),
				"working(1).");
	}

	@Test
	public void test16() {
		final String attributeName = "ISBLADETRAPENUMCAUSE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("isTrapEnumCause"),
				"processorInformation(1).");
	}

	@Test
	public void test17() {
		final String attributeName = "SBGENUMCASE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("isTrapEnumCause"),
				"processorInformation(1).");
	}

	@Test
	public void test18() {
		final String attributeName = "FUNKSBRTRAPVARCOMP";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("funkSbrTrapVarComp"),
				"The Component that issued the Trap is  core(1).");
	}

	@Test
	public void test19() {
		final String attributeName = "FUNKSBRTRAPVARCOMP";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("funkSbrTrapVarComp"),
				"The Component that issued the Trap is  core(1).");
	}

	@Test
	public void test20() {
		final String attributeName = "NSVRBGPPEERSTATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "nsVrBgpPeerState"),
				"idle");
	}

	@Test
	public void test21() {
		final String attributeName = "BGPOSFPTRAPTYPE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("bgposfpTrapType"),
				"traffic-sec");
	}

	//@Test
	public void test22() {
		final String attributeName = "NETSCREENTRAPTYPE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("alarmModelDescription"),
				"1");
	}

	//@Test
	public void test23() {
		final String attributeName = "MTEHOTTRIGGER";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("alarmModelDescription"),
				"ok(1).");
	}

	@Test
	public void test24() {
		final String attributeName = "MMSCMSYSTEMADMINISTRATIVESTATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("mmsCmSystemAdministrativeState"),
				"Adminstrative state of the system is : unlocked(1).");
	}

	@Test
	public void test25() {
		final String attributeName = "MMSCMSYSTEMOPERATIONALSTATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("mmsCmSystemOperationalState"),
				"The Operational state of the system is : up(1).");
	}

	@Test
	public void test26() {
		final String attributeName = "MERADMINSTRATIVESTATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("merAdminstrativeState"),
				"Adminstrative state of MER component is : unlocked(1).");
	}

	@Test
	public void test27() {
		final String attributeName = "MEROPERATIONALSTATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("merOperationalState"),
				"Operational state of MER component is : enabled(1).");
	}

	@Test
	public void test28() {
		final String attributeName = "MMSFMSYSTEMALARMID";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("mmsFmSystemAlarmID"),
				"1");
	}

	@Test
	public void test29() {
		final String attributeName = "_IFADMINSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_IFADMINSTATUS"),
				"The Adminstrative State of the Interface is: up(1).");
	}

	@Test
	public void test30() {
		final String attributeName = " _IFOPERSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get(" _IFOPERSTATUS"),
				"The operational State of the Interface is: up(1).");
	}

	@Test
	public void test31() {
		final String attributeName = "_OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE"),
				"The previous state of the object : initial(1).");
	}

	@Test
	public void test32() {
		final String attributeName = "_OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE"),
				"The current state of the object which causes the trap to occur : initial(1).");
	}

	@Test
	public void test33() {
		final String attributeName = "_OID_VAR_AP_SYS_LOG_HIST_LEVEL";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_LOG_HIST_LEVEL"),
				"The Log-Level of the message : emergency(1).");
	}

	@Test
	public void test34() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE"),
				"Power Presence : up(1).");
	}

	@Test
	public void test35() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_FAN_LOCATION";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_FAN_LOCATION"),
				"Fan Location : middle(1).");
	}

	@Test
	public void test36() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_RED_ROLE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"System Management Red Role (unit in a redundant pair) : secondary(1).");
	}

	@Test
	public void test37() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"The state that the system, give by location, is transitioning too : active(1).");
	}

	@Test
	public void test38() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get( "_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"The value identifies if all the radius connections are down or if just some of the radius connects have become unreachable : some-servers-down(1).");
	}

	@Test
	public void test39() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"The reason for the status change of the Session Agent : oosbyproxyerror(1).");
	}

	@Test
	public void test40() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"The state a user was trying to switch to or from when failing to authenticate : user(1).");
	}

	@Test
	public void test41() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"The protocol a user was using when failing to authenticate : telnet(1).");
	}

	@Test
	public void test42() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"The protocol a user was using when failing to authenticate : telnet(1).");
	}

	@Test
	public void test43() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"The reason for the status change of the Sip Interface. : oosbyproxyerror(1) .");
	}

	@Test
	public void test44() {
		final String attributeName = "_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"The status that the Sip Interface is changing into. : outofservice(1).");
	}

	@Test
	public void test45() {
		final String attributeName = "_OID_VAR_AP_ENUM_SERVER_STATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("_OID_VAR_AP_SYS_MGMT_RED_ROLE"),
				"The status of this ENUM server. : lowerpriority(1).");
	}

	@Test
	public void test46() {
		final String attributeName = "EXTREMETRAPAUTHORERRORTYPE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("vrrpTrapAuthErrorType"),
				"invalidAuthType");
	}

	@Test
	public void test47() {
		final String attributeName = "EXTREMESYSTEMPOWERSTATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("vrrpTrapAuthErrorType"),
				"computing");
	}

	@Test
	public void test48() {
		final String attributeName = "EXTREMEPORTMAUSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("extremePortMauStatus"),
				"inserted");
	}

	@Test
	public void test49() {
		final String attributeName = "EXTREMEESRPDMNSTATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("extremeEsrpDmnState"),
				"master");
	}

	@Test
	public void test50() {
		final String attributeName = "BROADSOFT COMPONENT";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("Broadsoft Component"),
				"\nComponent=Mediaserver");
	}

	@Test
	public void test51() {
		final String attributeName = "BROADSOFT SUBCOMPONENT";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get(attributeName),
				"\nSubComponent=Processmonitor");
	}

	@Test
	public void test52() {
		final String attributeName = "TRENDINDICATION";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("trendIndication"),
				"\n\tTrendIndication :  notApplicable");
	}

	@Test
	public void test53() {
		final String attributeName = "CPQNICIFPHYSADAPTERSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqNicIfPhysAdapterStatus"),
				"\n\tThe physical adapter status : unknown");
	}

	@Test
	public void test54() {
		final String attributeName = "CPQSM2CNTLRSELFTESTERRORS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqSm2CntlrSelfTestErrors"),
				"Busmaster I/O read error.\n");
	}

	@Test
	public void test55() {
		final String attributeName = "CPQHEFLTTOLPOWERSUPPLYSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqHeFltTolPowerSupplyStatus"),
				"other");
	}

	@Test
	public void test56() {
		final String attributeName = "CPQHETEMPERATURELOCALE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqHeTemperatureLocale"),
				"other");
	}

	@Test
	public void test57() {
		final String attributeName = "CPQHETHERMALDEGRADEDACTION";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqHeThermalDegradedAction"),
				"other");
	}

	@Test
	public void test58() {
		final String attributeName = "CPQHECORRMEMLOGSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqHeCorrMemLogStatus"),
				"\n\tIDE Logical Drive Status : other");
	}

	@Test
	public void test59() {
		final String attributeName = "CPQIDELOGICALDRIVESTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqIdeLogicalDriveStatus"),
				"other");
	}

	@Test
	public void test60() {
		final String attributeName = "CPQIDEATADISKNUMBER";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqIdeAtaDiskNumber"),
				"\n\tATA Disk Channel : other");
	}

	@Test
	public void test61() {
		final String attributeName = "CPQIDEATADISKCHANNEL";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqIdeAtaDiskChannel"),
				"\n\tATA Disk Channel : other");
	}

	@Test
	public void test62() {
		final String attributeName = "CPQIDEATADISKSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqIdeAtaDiskStatus"),
				"other");
	}

	@Test
	public void test63() {
		final String attributeName = "CPQSCSIPHYDRVSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqScsiPhyDrvStatus"),
				"\n\tSCSI Logical Drive Status : other");
	}

	@Test
	public void test64() {
		final String attributeName = "CPQDALOGDRVSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqDaLogDrvStatus"),
				"other");
	}

	@Test
	public void test65() {
		final String attributeName = "CPQSCSILOGDRVSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqScsiLogDrvStatus"),
				"\n\tSCSI Logical Drive Status : other");
	}

	@Test
	public void test66() {
		final String attributeName = "CPQDACNTLRBOARDSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqDaCntlrBoardStatus"),
				"other");
	}

	@Test
	public void test67() {
		final String attributeName = "CPQDATAPEDRVSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqDaTapeDrvStatus"),
				"other");
	}

	@Test
	public void test68() {
		final String attributeName = "CPQDASPARESTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqDaSpareStatus"),
				"other");
	}

	@Test
	public void test69() {
		final String attributeName = "CPQDAPHYDRVSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqDaPhyDrvStatus"),
				"other");
	}

	@Test
	public void test70() {
		final String attributeName = "CPQDAACCELERRCODE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqDaAccelErrCode"),
				"other");
	}

	@Test
	public void test71() {
		final String attributeName = "CPQDAACCELSTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqDaAccelStatus"),
				"other");
	}

	@Test
	public void test72() {
		final String attributeName = "CPQDACNTLRMODEL";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqDaCntlrModel"),
				"\n\tArray Controller Model : other");
	}

	@Test
	public void test73() {
		final String attributeName = "CPQHOTRAPFLAGS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqHoTrapFlags"),
				"\n\tAgent Type = Client\n\tClient IP address type = static entry\n\tTrap Condition = Not used");
	}

	@Test
	public void test74() {
		final String attributeName = "CPQMEALARMSAMPLETYPE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("cpqMeAlarmSampleType"),
				"\n\tAlarmSampleType : absoluteValue");
	}

	@Test
	public void test75() {
		final String attributeName = "DSX1LINESTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get(attributeName),
				"1");
	}

	@Test
	public void test76() {
		final String attributeName = "DSX3LINESTATUS";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get(attributeName),
				"1");
	}

	@Test
	public void test77() {
		final String attributeName = "RDBMSRELSTATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("rdbmsRelState"),
				"The state of this server's access to this database is: other(1).");
	}

	@Test
	public void test78() {
		final String attributeName = "ORALISTENERSTATE";
		final EventNotification notif = new EventNotification();
		final NodeMiscAttrs nodeMiscAttrs = new NodeMiscAttrs();
		
		final String attributeValue = "1";
		Assert.assertEquals(true, nodeMiscAttrs.translateTmosAttrs(
				attributeName, attributeValue, notif, ""));
		Assert.assertEquals(notif.getAdditionalAttributes().get("oraListenerState"),
				"The current state of the Generic Listener is : up(1).");
	}

}