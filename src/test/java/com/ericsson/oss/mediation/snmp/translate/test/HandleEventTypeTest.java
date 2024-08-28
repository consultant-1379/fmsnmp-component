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

import org.junit.*;

import com.ericsson.oss.mediation.translator.model.HandleEventType;

public class HandleEventTypeTest {
	
	@Test
	public void getzelsAlarmNumberEventTypeTest(){
		String attributeValue = "55";
		Assert.assertEquals("21",HandleEventType.getzelsAlarmNumberEventType(attributeValue));
	}
	
	@Test
	public void getnewAlarmNameEventTypeTest(){
		String attributeValue1 = "FDSAuthority:InvalidSessionId";
		String attributeValue2 = "Lookup:NotifierProblems";
		String attributeValue3 = "Mmllp:LinkRecovered";
		String attributeValue4 = "IfDiver:BadLinkDevice";
		Assert.assertEquals("10",HandleEventType.getnewAlarmNameEventType(attributeValue1));
		Assert.assertEquals("4",HandleEventType.getnewAlarmNameEventType(attributeValue2));
		Assert.assertEquals("2",HandleEventType.getnewAlarmNameEventType(attributeValue3));
		Assert.assertEquals("5",HandleEventType.getnewAlarmNameEventType(attributeValue4));
	}
	
	@Test
	public void getituAlarmEventTypeTest(){
		String attributeValue = "3";
		Assert.assertEquals("3",HandleEventType.getituAlarmEventType(attributeValue));
	}
	
	@Test
	public void getsppAlarmTypeTest(){
		String attributeValue = "0";
		Assert.assertEquals("0",HandleEventType.getsppAlarmType(attributeValue));
	}
	@Ignore
	@Test
	public void getsiteEventTypeTest(){
		String attributeValue = "11";
		Assert.assertEquals("11",HandleEventType.getsiteEventType(attributeValue));
	}
	
	@Test
	public void getmmsFmSystemAlarmTypeTest(){
		String attributeValue = "2";		
    	Assert.assertEquals("4",HandleEventType.getmmsFmSystemAlarmType(attributeValue));		
	}
	
	@Test
	public void getmgcEvent_TypeTest(){
		String attributeValue = "3";
		Assert.assertEquals("11",HandleEventType.getmgcEventType(attributeValue));
	}
	
	@Test
	public void getirpalarmEventTypeTest(){
		String attributeValue = "17";
		Assert.assertEquals("17",HandleEventType.getirpalarmEventType(attributeValue));
	}
	
	@Test
	public void getisbladeAlarmClassTest(){
		String attributeValue = "10";
		Assert.assertEquals("2",HandleEventType.getisbladeAlarmClass(attributeValue));
	}
	
	@Test
	public void getWPPEventTypeTest(){
		String attributeValue = "11";
		Assert.assertEquals("4",HandleEventType.getWPPEventType(attributeValue));
	}
	@Ignore
	@Test
	public void getEFWSEventTypeTest(){
		String attributeValue = "4";
		Assert.assertEquals("10",HandleEventType.getEFWSEventType(attributeValue));
	}
	
	@Test
	public void getipmsalarmEventTypeTest(){
		String attributeValue = "19";
		Assert.assertEquals("19",HandleEventType.getipmsalarmEventType(attributeValue));
	}
	
	@Test
	public void getnetraalarmEventType(){
		String attributeValue = "122";
		Assert.assertEquals("0",HandleEventType.getnetraalarmEventType(attributeValue));
	}
	
	
	

}
