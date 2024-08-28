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

import com.ericsson.oss.mediation.translator.model.alarm.handlers.OtherAlarmHandlers;

public class OtherAlarmHandlersTest {
	
	@Test
	public void getResourceOIDNameTest()
	{
		String oid="0.1.2.4.6";
		 String fdn="127.16.65.218";
		 Assert.assertEquals(null, OtherAlarmHandlers.getResourceOIDName(oid,fdn));
		
	}

}
