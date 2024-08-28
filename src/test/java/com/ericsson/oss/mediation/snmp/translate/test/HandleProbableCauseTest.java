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

import com.ericsson.oss.mediation.translator.model.HandleProbableCause;
public class HandleProbableCauseTest {
	
	
	
	@Test
	public void getALARM_PCProbabaleCauseTest(){
		
		String value0 ="The traffic in the zone evaluation location server is close to or has reached the maximum capacity.";


		String value1 = "The file path or permission may be wrong.";
		String value2 = "The ZoneEngine component goes down while it is writing to file";
		String value3 = "The file name or path may be wrong.";
		String value4 = "The PluginManager has reported that the plug-in stated in the message had died for some reason.";
		String value5 = "One or more servers are down.";
		String value6 = "Certificate is about to expire.";
		String value7 = "A license key has expired.";
		String value8 = "A license key is about to expire.";
		String value9 = "All licensed transactions have been used up.";
		Assert.assertEquals("583",HandleProbableCause.getAlarmProbabaleCause(value0));
		Assert.assertEquals("584",HandleProbableCause.getAlarmProbabaleCause(value1));
		Assert.assertEquals("585",HandleProbableCause.getAlarmProbabaleCause(value2));
		Assert.assertEquals("586",HandleProbableCause.getAlarmProbabaleCause(value3));
		Assert.assertEquals("587",HandleProbableCause.getAlarmProbabaleCause(value4));
		Assert.assertEquals("588",HandleProbableCause.getAlarmProbabaleCause(value5));
		Assert.assertEquals("589",HandleProbableCause.getAlarmProbabaleCause(value6));
		Assert.assertEquals("590",HandleProbableCause.getAlarmProbabaleCause(value7));
		Assert.assertEquals("591",HandleProbableCause.getAlarmProbabaleCause(value8));
		Assert.assertEquals("592",HandleProbableCause.getAlarmProbabaleCause(value9));
	}
	
	@Test
	public void getEFWSProbableCauseTest(){
		String attributeValue = "10";
		Assert.assertEquals("1010",HandleProbableCause.getEFWSProbableCause(attributeValue));
	}
	
	@Test
	public void getnewAlarmNameProbableCauseTest(){
		String attributeValue1 = "DBInterface:OracleUserorPasswordError";
		String attributeValue2 = "DBInterface:OracleConnectionStringError";
		String attributeValue3 = "MOHandler:InvalidRegistration";
		String attributeValue4 = "FDSAuthority:UserLoggedIn";
		String attributeValue5 = "ProcQueue:errorAccessingFile";
		String attributeValue6 = "Mmllp:LinkManangerConnectionFailure";
		String attributeValue7 = "FDSConfigurationManager:WriteWarning";
		
		String attributeValue8 = "FDSAuthority:UserNotAllowed";
		String attributeValue9 = "FDSComponentManager:PluginRecoveryFailed";
		String attributeValue10 = "OrderScheduler::QueueFull";
		String attributeValue11 = "Over Limitation Of MaxNumOfSoapConnection";
		
		String attributeValue12 = "IfDiver:BadLinkDevice";
		
		//Assert.assertEquals("600",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue1));
		Assert.assertEquals("506",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue2));
		Assert.assertEquals("544",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue3));
		Assert.assertEquals("0",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue4));
		Assert.assertEquals("516",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue5));
		Assert.assertEquals("306",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue6));
		Assert.assertEquals("560",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue7));
		Assert.assertEquals("614",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue8));
		Assert.assertEquals("158",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue9));
		Assert.assertEquals("339",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue10));
		Assert.assertEquals("549",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue11));
		Assert.assertEquals("54",HandleProbableCause.getnewAlarmNameProbableCause(attributeValue12));
		
	}
	
	@Test
	public void getWPPProbabaleCuaseTest(){
		String value1 = "10";
		String value2 = "10001";
		String value3 = "16074";
		Assert.assertEquals("310",HandleProbableCause.getWPPProbabaleCuase(value1));
		Assert.assertEquals("1",HandleProbableCause.getWPPProbabaleCuase(value2));
		Assert.assertEquals("574",HandleProbableCause.getWPPProbabaleCuase(value3));
	}
}
