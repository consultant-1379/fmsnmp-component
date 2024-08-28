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

import com.ericsson.oss.mediation.translator.model.ProbableCauseAttrs;

import com.ericsson.oss.mediation.translator.model.EventNotification;

public class ProbableCauseAttrsTest {

	@Test
	public void test1() {
		final String attributeName = "ProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(true, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test2() {
		final String attributeName = "ericProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test3() {
		final String attributeName = "netspiraProbablcause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test4() {
		final String attributeName = "wppalarmPC";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "ProbableCause";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("ProbableCause", notif.getProbableCause());
	}

	@Test
	public void test5() {
		final String attributeName = "rbnProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "ProbableCause";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("ProbableCause", notif.getProbableCause());
	}

	@Test
	public void test6() {
		final String attributeName = "rbnCardAlarmProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test7() {
		final String attributeName = "rbnSseAlarmProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test8() {
		final String attributeName = "alarmPC";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test9() {
		final String attributeName = "sppAlarmProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test10() {
		final String attributeName = "srdprobableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test11() {
		final String attributeName = "rbnSfpAlarmProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test12() {
		final String attributeName = "rbnSseDiskAlarmProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test13() {
		final String attributeName = "ecsAlarmProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test14() {
		final String attributeName = "accAlarmCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(true, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		Assert.assertEquals("0", notif.getProbableCause());
	}

	@Test
	public void test15() {
		final String attributeName = "irpalarmPC";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("0", notif.getProbableCause());
	}

	@Test
	public void test16() {
		final String attributeName = "omsTrapContentsProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test17() {
		final String attributeName = "isbladeTrapAlarmX733Cause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test18() {
		final String attributeName = "ipmsalarmPC";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test19() {
		final String attributeName = "efwsalarmProbableCause2";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test20() {
		final String attributeName = "efwsalarmProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test21() {
		final String attributeName = "ipmsalarmPC";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test22() {
		final String attributeName = "Broadsoft Probable Cause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test23() {
		final String attributeName = "ipmuxalarmPC";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test24() {
		final String attributeName = "ipfmAlarmprobablecause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test25() {
		final String attributeName = "d_trapCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test26() {
		final String attributeName = "ivri_trapCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test27() {
		final String attributeName = "mgcAlarm_Probablecause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test28() {
		final String attributeName = "netraalarmPC";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test29() {
		final String attributeName = "_OID_VAR_MPT_PROBABLE_CAUSE";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test30() {
		final String attributeName = "eriAlarmActiveProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test31() {
		final String attributeName = "ocmpProbablecause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test32() {
		final String attributeName = "ocmpo_probableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test33() {
		final String attributeName = "omperiAlarmAlertProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test34() {
		final String attributeName = "ossrcalarmPC";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test35() {
		final String attributeName = "sppProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test36() {
		final String attributeName = "sbgProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test37() {
		final String attributeName = "siteProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test38() {
		final String attributeName = "mptAlarmProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test39() {
		final String attributeName = "mgcprobableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test40() {
		final String attributeName = "granALARM_PC";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test41() {
		final String attributeName = "rbnMGEventProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test42() {
		final String attributeName = "rbnChassisAlarmProbableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test43() {
		final String attributeName = "vivrprobableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test44() {
		final String attributeName = "_OID_VAR_ECS_PROBABLE_CAUSE";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test45() {
		final String attributeName = "sbgTrapcause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

	@Test
	public void test46() {
		final String attributeName = "axdprobableCause";
		final EventNotification notif = new EventNotification();
		final String attributeValue = "TestPc";
		Assert.assertEquals(false, ProbableCauseAttrs.translateProbableCause(
				attributeName, attributeValue, notif));
		//Assert.assertEquals("TestPc", notif.getProbableCause());
	}

}
