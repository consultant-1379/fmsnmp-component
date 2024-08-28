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

import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.model.EventTypeAttrs;

public class EventTypeAttrsTest {

	@Test
	public void test1() {
		final String attributeName = "EventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "0";
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test2() {
		final String attributeName = "ericEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "0";
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test3() {
		final String attributeName = "netspiraEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test4() {
		final String attributeName = "rbnEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test5() {
		final String attributeName = "sppAlarmType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test6() {
		final String attributeName = "netraalarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "0";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test7() {
		final String attributeName = "alarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test8() {
		final String attributeName = "axdeventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test9() {
		final String attributeName = "rbnCardAlarmType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test10() {
		final String attributeName = "rbnSseEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test11() {
		final String attributeName = "wppalarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test12() {
		final String attributeName = "rbnSfpAlarmType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test13() {
		final String attributeName = "rbnSseDiskEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test14() {
		final String attributeName = "ggsnAlarmName";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test15() {
		final String attributeName = "ecsAlarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test16() {
		final String attributeName = "ecsAlarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test17() {
		final String attributeName = "mgcEvent_Type";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
	//	Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test18() {
		final String attributeName = "irpalarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
	//	Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test19() {
		final String attributeName = "isbladeAlarmClass";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "1";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
	//	Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test20() {
		final String attributeName = "ipmsalarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "0";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test21() {
		final String attributeName = "eventEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test22() {
		final String attributeName = "efwsalarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "1";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test23() {
		final String attributeName = "ipmsalarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "0";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test24() {
		final String attributeName = "ipmuxalarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "0";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test25() {
		final String attributeName = "ipmuxeventEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "0";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test26() {
		final String attributeName = "mmsFmSystemAlarmType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test27() {
		final String attributeName = "_OID_VAR_MPT_ALARM_EVENT_TYPE";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test28() {
		final String attributeName = "ocmpEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test29() {
		final String attributeName = "eriAlarmActiveEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test30() {
		final String attributeName = "omperiAlarmAlertEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test31() {
		final String attributeName = "sppEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test32() {
		final String attributeName = "ossrcalarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test33() {
		final String attributeName = "sbgEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "7";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("0", notif.getEventType());
	}

	@Test
	public void test34() {
		final String attributeName = "siteEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "10";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(true, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("2", notif.getEventType());
	}

	@Test
	public void test35() {
		final String attributeName = "mptAlarmEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test36() {
		final String attributeName = "granALARM_TYPE";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test37() {
		final String attributeName = "rbnMGEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test38() {
		final String attributeName = "rbnIpSecEventType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test39() {
		final String attributeName = "rbnChassisAlarmType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test40() {
		final String attributeName = "mgcAlarm_Type";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

	@Test
	public void test41() {
		final String attributeName = "_OID_VAR_ECS_ALARM_EVENT_TYPE";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEt";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(false, EventTypeAttrs.translateEventTypeAttr(
				attributeName, attributeValue, notif, false));
		//Assert.assertEquals("TestEt", notif.getEventType());
	}

}
