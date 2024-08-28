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

import com.ericsson.oss.mediation.translator.model.*;

public class HandleAlarmSeverityTest {
	
	@Test
	public void getaxdperceivedSeverityTest(){
		String attributeValue = "";
		String attributeName = " ";
		String axdPerceivedseverity = ".1.3.6.1.4.1.193.14.1.1.3.2.2.3.1.6";
		Assert.assertEquals("",HandleAlarmSeverity.getaxdAlarmNumber(axdPerceivedseverity, attributeName, attributeValue));
	}
	
	
	@Test
	public void getesasnfPerceivedSeverityTest(){
		final String attributeValue = "2";
		Assert.assertEquals(Constants.SEV_MINOR,HandleAlarmSeverity.getesasnfPerceivedSeverity(attributeValue));
	}
	
	@Test
	public void getfunkSbrTrapVarSevTest(){
		final String attributeValue = "3";
		Assert.assertEquals(Constants.SEV_MAJOR,HandleAlarmSeverity.getfunkSbrTrapVarSev(attributeValue));
	}
	
	@Test
	public void getocmpo_perceivedSeverityTest(){
		final String attributeValue = "warning";
		Assert.assertEquals(Constants.SEV_WARNING,HandleAlarmSeverity.getocmpoperceivedSeverity(attributeValue));
	}
	
	@Test
	public void getocmpPerceivedSeverityTest(){
		final String attributeValue = "6";
		Assert.assertEquals(Constants.SEV_CRITICAL,HandleAlarmSeverity.getocmpPerceivedSeverity(attributeValue));
	}
	
	@Test
	public void getoraAgentEventSeverityTest(){
		final String attributeValue = "2";
		Assert.assertEquals(Constants.SEV_MAJOR,HandleAlarmSeverity.getoraAgentEventSeverity(attributeValue));
	}
	
	@Test
	public void getmgcEvent_SeverityTest(){
		final String attributeValue = "1";
		Assert.assertEquals("INDETERMINATE",HandleAlarmSeverity.getmgcEventSeverity(attributeValue));
	}
	
	@Test
	public void getBSSeverityTest(){
		final String attributeValue = "6";
		Assert.assertEquals(Constants.SEV_CLEARED,HandleAlarmSeverity.getBSSeverity(attributeValue));
	}
	
	@Test
	public void getBSAlarmSeverityTest(){
		final String attributeValue = "0";
		Assert.assertEquals(Constants.SEV_INDETERMINATE,HandleAlarmSeverity.getBSAlarmSeverity(attributeValue));
	}
	
	@Test
	public void getEFWSAlarmSeverityTest(){
		final String attributeValue = "5";
		Assert.assertEquals(Constants.SEV_MINOR,HandleAlarmSeverity.getEFWSAlarmSeverity(attributeValue));
	}
	
	@Test
	public void getEFWSAlarmSeverity2Test(){
		final String attributeValue = "5";
		Assert.assertEquals(Constants.SEV_WARNING,HandleAlarmSeverity.getEFWSAlarmSeverity2(attributeValue));
	}
	
	@Test
	public void getcpqMeAlarmSeverityTest(){
		final String attributeValue = "6";
		Assert.assertEquals(Constants.SEV_CRITICAL,HandleAlarmSeverity.getcpqMeAlarmSeverity(attributeValue));
	}
	
	@Test
	public void getmrfperceivedSeverityTest(){
		final String attributeValue = "2";
		Assert.assertEquals(Constants.SEV_INDETERMINATE,HandleAlarmSeverity.getmrfperceivedSeverity(attributeValue));
	}
	
	@Test
	public void getmrfperceivedSeverity2Test(){
		final String attributeValue = "8";
		Assert.assertEquals(Constants.SEV_CLEARED,HandleAlarmSeverity.getmrfperceivedSeverity2(attributeValue));
	}
	
	@Test
	public void getPerceivedSeverityTest(){
		final String attributeValue = "4";
		Assert.assertEquals(Constants.SEV_WARNING,HandleAlarmSeverity.getPerceivedSeverity(attributeValue));
	}
	
	@Test
	public void getvivrPerceivedSeverityTest(){
		final String attributeValue = "INFORMATIVE";
		Assert.assertEquals(Constants.SEV_INDETERMINATE,HandleAlarmSeverity.getvivrPerceivedSeverity(attributeValue));
	}
	
	@Test
	public void getTigrisSeverityTest(){
		final String attributeValue = "3";
		Assert.assertEquals(Constants.SEV_MAJOR,HandleAlarmSeverity.getTigrisSeverity(attributeValue));
	}

	@Test
	public void getsegwSeverityTest(){
		final String attributeValue = "2";
		Assert.assertEquals(Constants.SEV_CRITICAL,HandleAlarmSeverity.getsegwSeverity(attributeValue));
	}
	
	@Test
	public void getocmpo_SeverityTest(){
		final String attributeValue = Constants.SEV_MINOR;
		Assert.assertEquals(Constants.SEV_MINOR,HandleAlarmSeverity.getocmpoSeverity(attributeValue));
	}
	
	@Test
	public void getocmpSeverityTest(){
		final String attributeValue = "6";
		EventNotification notif = new EventNotification();
		Assert.assertEquals("CRITICAL",HandleAlarmSeverity.getocmpPerceivedSeverity(attributeValue));
	}
	@Test
	public void getsegwPerceivedSeverityTest()
	{
		final String attributevalue1="0";
		final String attributevalue2="2";
		final String attributevalue3="4";
		final String attributevalue4="9";
		Assert.assertEquals(Constants.SEV_MINOR,HandleAlarmSeverity.getsegwPerceivedSeverity(attributevalue1));
		Assert.assertEquals(Constants.SEV_CRITICAL,HandleAlarmSeverity.getsegwPerceivedSeverity(attributevalue2));
		Assert.assertEquals(Constants.SEV_WARNING,HandleAlarmSeverity.getsegwPerceivedSeverity(attributevalue3));
		Assert.assertEquals(Constants.SEV_INDETERMINATE,HandleAlarmSeverity.getsegwPerceivedSeverity(attributevalue4));
		
	}
}
