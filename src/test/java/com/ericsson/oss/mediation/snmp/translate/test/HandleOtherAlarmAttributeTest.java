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
import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;

public class HandleOtherAlarmAttributeTest {
	
	@Test
	public void handleSNFTrapPDUTest()
	{
	EventNotification notif=new EventNotification();
	final String name="alarmActiveResourceId";
	final String value=".1.3.6.1.2.1.121.1.1.1.1.3";
	final String ituAlarmEventTypeoid=".1.3.6.1.4.1.193.110.2.667.1.1.1.1.2";
	final String fdn="127.16.65.218";
	notif=HandleOtherAlarmAttribute.handleSNFTrapPDU(notif, name, value, ituAlarmEventTypeoid, fdn);
	Assert.assertEquals("INDETERMINATE",notif.getSeverity());
	

}
}
