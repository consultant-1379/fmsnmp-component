package com.ericsson.oss.mediation.snmp.translate.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.EventIdAttrs;

import com.ericsson.oss.mediation.translator.model.EventNotification;

public class EventIdAttrsTest {

	@Test
	public void test() {
		final String attributeName = "ExternalEventID";
		final String attributeValue = "TestEventId";
		final EventNotification notif = new EventNotification();
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test1() {
		final String attributeName = "alarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test2() {
		final String attributeName = "ExternalEventID";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test3() {
		final String attributeName = "jnxVpnIfIndex";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test4() {
		final String attributeName = "_IFNUMBER";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test5() {
		final String attributeName = "_OID_VAR_ECS_NOTIFICATION_ID";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test6() {
		final String attributeName = "jnxVpnPwIndex";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test7() {
		final String attributeName = "netraalarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test8() {
		final String attributeName = "ecsAlarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test9() {
		final String attributeName = "mptAlarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test10() {
		final String attributeName = "rbnIpSecRemoteId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test11() {
		final String attributeName = "alarmIndex";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test12() {
		final String attributeName = "ggsnAlarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test13() {
		final String attributeName = "ifIndex";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test14() {
		final String attributeName = "rbnChassisAlarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test15() {
		final String attributeName = "JNXContentsContainerIndex";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test16() {
		final String attributeName = "wppalarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test17() {
		final String attributeName = "eventId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test18() {
		final String attributeName = "ipmuxalarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test19() {
		final String attributeName = "ipmuxeventId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test20() {
		final String attributeName = "mgcAlarm_Index";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test21() {
		final String attributeName = "netspiraNotificationId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test22() {
		final String attributeName = "ossrcalarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test23() {
		final String attributeName = "eriAlarmActiveMinorType";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test24() {
		final String attributeName = "ossrcalarmApp";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test25() {
		final String attributeName = "siteAlarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test26() {
		final String attributeName = "sppeventId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test27() {
		final String attributeName = "sppalarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test28() {
		final String attributeName = "jnxRedundancyContentsIndex";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test29() {
		final String attributeName = "granALARM_ALARMID";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test30() {
		final String attributeName = "jnxFruSlot";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test31() {
		final String attributeName = "ospfRouterId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test32() {
		final String attributeName = "rbnNECircuitId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test33() {
		final String attributeName = "zels_IFNUMBER";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

	@Test
	public void test34() {
		final String attributeName = "_OID_VAR_MPT_NOTIFICATION_ID";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestEventId";
		Assert.assertEquals(true, EventIdAttrs.translateEventId(attributeName,
				attributeValue, notif));
		Assert.assertEquals(notif.getExternalEventId(), "TestEventId");
	}

}
