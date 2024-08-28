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

import com.ericsson.oss.mediation.translator.model.EventTimeAttrs;

public class EventTimeAttrsTest {

	
	 @Test
	  public void testGetEventTimeFormat() {
		String attributeName="AlarmTime";		
		Assert.assertEquals(EventTimeAttrs.SNMP_COMMON_TIME, EventTimeAttrs.getEventTimeFormat(attributeName));
		
	}

	
	@Test
	public void testTranslateEventTime() {

		Assert.assertEquals("not implemented", "not implemented");
	
	}

}
