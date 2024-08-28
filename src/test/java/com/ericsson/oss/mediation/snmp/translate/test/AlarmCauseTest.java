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

import com.ericsson.oss.mediation.translator.model.alarm.handlers.AlarmCause;



public class AlarmCauseTest {

	@Test
	public void getaccAlarmCauseTest() {
		
		System.out.println("Testing the AlarmCauseTest ::::--- ");
	
		Assert.assertEquals("0",AlarmCause.getaccAlarmCause("1"));
		Assert.assertEquals("570",AlarmCause.getaccAlarmCause("2"));
		Assert.assertEquals("5",AlarmCause.getaccAlarmCause("3"));
		Assert.assertEquals("6",AlarmCause.getaccAlarmCause("4"));
		Assert.assertEquals("7",AlarmCause.getaccAlarmCause("5"));
		Assert.assertEquals("8",AlarmCause.getaccAlarmCause("6"));
		Assert.assertEquals("9",AlarmCause.getaccAlarmCause("7"));
		Assert.assertEquals("11",AlarmCause.getaccAlarmCause("8"));
		Assert.assertEquals("325",AlarmCause.getaccAlarmCause("9"));
		Assert.assertEquals("313",AlarmCause.getaccAlarmCause("10"));
		Assert.assertEquals("0",AlarmCause.getaccAlarmCause("11"));
		Assert.assertEquals("517",AlarmCause.getaccAlarmCause("12"));
		Assert.assertEquals("59",AlarmCause.getaccAlarmCause("13"));
		Assert.assertEquals("58",AlarmCause.getaccAlarmCause("14"));
		Assert.assertEquals("55",AlarmCause.getaccAlarmCause("15"));		
		Assert.assertEquals("315",AlarmCause.getaccAlarmCause("16"));
		Assert.assertEquals("151",AlarmCause.getaccAlarmCause("17"));
		Assert.assertEquals("152",AlarmCause.getaccAlarmCause("18"));
		Assert.assertEquals("153",AlarmCause.getaccAlarmCause("19"));
		Assert.assertEquals("332",AlarmCause.getaccAlarmCause("20"));
		Assert.assertEquals("357",AlarmCause.getaccAlarmCause("21"));
		Assert.assertEquals("346",AlarmCause.getaccAlarmCause("22"));
		Assert.assertEquals("348",AlarmCause.getaccAlarmCause("23"));
		Assert.assertEquals("347",AlarmCause.getaccAlarmCause("24"));
		Assert.assertEquals("308",AlarmCause.getaccAlarmCause("25"));
		Assert.assertEquals("344",AlarmCause.getaccAlarmCause("26"));
		
	}

}
