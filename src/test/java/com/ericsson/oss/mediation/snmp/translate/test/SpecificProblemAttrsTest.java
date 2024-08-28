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

import com.ericsson.oss.mediation.translator.model.SpecificProblemAttrs;

import com.ericsson.oss.mediation.translator.model.EventNotification;

public class SpecificProblemAttrsTest {

	
	@Test
	public void test1() {
		final String attributeName = "SpecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test2() {
		final String attributeName = "segwspecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test3() {
		final String attributeName = "omperiAlarmAlertSpecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test4() {
		final String attributeName = "ossrcalarmSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test5() {
		final String attributeName = "sppAlarmspecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test6() {
		final String attributeName = "segwspecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test7() {
		final String attributeName = "ericSpecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test8() {
		final String attributeName = "ipmsalarmSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test9() {
		final String attributeName = "mmsFmSystemAlarmProbableCause";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test10() {
		final String attributeName = "netraalarmSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test11() {
		final String attributeName = "sbgtrapName";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test12() {
		final String attributeName = "siteSpecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test13() {
		final String attributeName = "eriAlarmActiveSpecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test14() {
		final String attributeName = "sppeventSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test15() {
		final String attributeName = "sppspecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test16() {
		final String attributeName = "wppalarmSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test17() {
		final String attributeName = "ericSpecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test18() {
		final String attributeName = "granALARM_SP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test19() {
		final String attributeName = "ggsnAlarmSourceId";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test20() {
		final String attributeName = "rbnCardAlarmId";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test21() {
		final String attributeName = "rbnSfpAlarmId";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test22() {
		final String attributeName = "mgcEvent_Slogan";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test23() {
		final String attributeName = "irpalarmSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test24() {
		final String attributeName = "isbladeTrapName";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test25() {
		final String attributeName = "ipmsalarmSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test26() {
		final String attributeName = "eventSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test27() {
		final String attributeName = "ipmuxalarmSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test28() {
		final String attributeName = "ipmuxeventSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test29() {
		final String attributeName = "ivri_trapSpecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test30() {
		final String attributeName = "d_trapSpecificProblem";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test31() {
		final String attributeName = "mgcEvent_Slogan";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test32() {
		final String attributeName = "mgcAlarm_Slogan";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test33() {
		final String attributeName = "netraalarmSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test34() {
		final String attributeName = "eventSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test35() {
		final String attributeName = "alarmSP";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

	@Test
	public void test36() {
		final String attributeName = "newAlarmName";
		final EventNotification notif = new EventNotification();
		final StringBuffer buffer = new StringBuffer();
		final String attributeValue = "TestSp";
		Assert.assertEquals(true, SpecificProblemAttrs
				.translateSpecificProblem(attributeName, attributeValue, notif,
						buffer, 0));
		Assert.assertEquals(notif.getSpecificProblem(), "TestSp");
	}

}
