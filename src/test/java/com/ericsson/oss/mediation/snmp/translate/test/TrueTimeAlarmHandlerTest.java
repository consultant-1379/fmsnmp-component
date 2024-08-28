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

import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.model.TruetimeAlarmHandler;

public class TrueTimeAlarmHandlerTest {
	
	
	@Test
	public void buildSymmAlarmTest(){
		String varbindOID = ".1.3.6.1.4.1.9070.1.2.3.1.5.1.6.6";
		String varbindValue = "Thu Jun  7 18:17:39 2012 Alarm 1317184291: Type: Timing No Source Alarm, Class: Timing, Level: Major, Action: Raise Alarm, Flags: 0x17"; 
		EventNotification notif = new EventNotification();
		notif = TruetimeAlarmHandler.buildSymmAlarm(varbindOID, varbindValue, notif);
		Assert.assertEquals("UTC", notif.getTimeZone());
		Assert.assertEquals("INDETERMINATE",notif.getSeverity());
		Assert.assertEquals("Timing No Source Alarm, Class: Timing",notif.getSpecificProblem());
						
	}
	 

}
