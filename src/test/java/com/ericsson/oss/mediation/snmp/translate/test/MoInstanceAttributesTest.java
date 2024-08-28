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
 *----------------------------------------------------------------------------

package com.ericsson.oss.mediation.snmp.translate.test;

import junit.framework.Assert;

import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.model.MoInstanceAttributes;

public class MoInstanceAttributesTest {

	@Test
	public void test1() {
		final String attributeName = "ManagedObjectInstance";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test2() {
		final String attributeName = "ObjectOfRefrence";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test3() {
		final String attributeName = "mplsLspName";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test4() {
		final String attributeName = "jnxVpnIfVpnName";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test5() {
		final String attributeName = "newAlarmId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test6() {
		final String attributeName = "jnxVpnPwVpnName";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test7() {
		final String attributeName = "alarmMOClass";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test8() {
		final String attributeName = "sbgtrapId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test9() {
		final String attributeName = "jnxFruContentsIndex";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test10() {
		final String attributeName = "mgcNotificationAddText";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test11() {
		final String attributeName = "mgcEvent_MoInstance";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test12() {
		final String attributeName = "irpalarmMOinst";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test13() {
		final String attributeName = "isbladeTrapAlarmFaultId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("test,TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test14() {
		final String attributeName = "isbladetrapbsId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals("TestMo", MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
	}

	@Test
	public void test15() {
		final String attributeName = "ipmsalarmMOinst";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test16() {
		final String attributeName = "isitetrapbsId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test17() {
		final String attributeName = "mgcAlarm_MoInstance";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test18() {
		final String attributeName = "netraalarmMOinst";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test19() {
		final String attributeName = "ocmpmanagedObject";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test20() {
		final String attributeName = "omperiAlarmAlertManagedObject";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test21() {
		final String attributeName = "ossrcalarmMOClass";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test22() {
		final String attributeName = "sbgtrapAlarmFaultId";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("test,TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test23() {
		final String attributeName = "segwManegedObjectInstance";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getAdditionalAttributes().get(attributeName));
	}

	@Test
	public void test24() {
		final String attributeName = "siteManagedObjectInstance";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test25() {
		final String attributeName = "rbnSfpAlarmCardSlot";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test26() {
		final String attributeName = "wppalarmMOClass";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test27() {
		final String attributeName = "granALARM_MANAGEDOBJECTINSTANCE";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test28() {
		final String attributeName = "jnxFruName";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test29() {
		final String attributeName = "zels_IFNAME";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test30() {
		final String attributeName = "_IFNAME";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("IF=TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test31() {
		final String attributeName = "ocmpmanagedObject";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

	@Test
	public void test32() {
		final String attributeName = "eriAlarmActiveManagedObject";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestMo";
		final StringBuffer buffer = new StringBuffer();
		Assert.assertEquals(MoInstanceAttributes.SNMP_MOI_OK, MoInstanceAttributes
				.handleMOInstanceIfApplicable(attributeName, attributeValue,
						notif, buffer, "test"));
		Assert.assertEquals("TestMo", notif.getManagedObjectInstance());
	}

}
*/