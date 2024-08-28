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

import com.ericsson.oss.mediation.translator.model.handlers.*;


public class BroadSoftHandlerTest {
	
	@Test
	public void getBSSubComponentTest(){
		
		
		final String  attributeValue = "5";
		Assert.assertEquals("\nSubComponent=Ccp",BSComponent.getBSSubComponent(attributeValue));
		
	}
	
	@Test
	public void getBSComponentTest(){
	
		final String attributeValue = "4";
		final String attributeValue1 = "2";
		final String attributeValue2 = "5";
		Assert.assertEquals("\nComponent=Servicecontrolproxy",BSComponent.getBSComponent(attributeValue));
		Assert.assertEquals("\nComponent=Networkserver",BSComponent.getBSComponent(attributeValue1));
		Assert.assertEquals("\nComponent=Elementmanagementsystem",BSComponent.getBSComponent(attributeValue2));
	}
	
	@Test
	public void getcpqHoTrapFlagsTest(){
		
		final String attributeValue = "10";
		Assert.assertEquals("\n\tAgent Type = Server\n\tClient IP address type = DHCP entry\n\tTrap Condition = Condition ok",CPQHoTrapFlags.getcpqHoTrapFlags(attributeValue));
	}
	
	@Test
	public void getcpqMeAlarmSampleTypeTest(){
		
		final String attributeValue = "3";
		Assert.assertEquals("\n\tAlarmSampleType : absSuppressRisingTrap", CPQHoTrapFlags.getcpqMeAlarmSampleType(attributeValue));
	}
	
	@Test
	public void getcpqDaCntlrModelTest(){
		
		final String attributeValue = "22";
		Assert.assertEquals("\n\tArray Controller Model : sa-641", CPQDaAccelHandler.getcpqDaCntlrModel(attributeValue));
	}
	
	@Test
	public void getcpqDaAccelStatusTest(){
		final String attributeValue1 = "3";
		final String attributeValue2 = "1";
		final String attributeValue3 = "2";
		final String attributeValue4 = "4";
		Assert.assertEquals("enabled",CPQDaAccelHandler.getcpqDaAccelStatus(attributeValue1));
		Assert.assertEquals("other",CPQDaAccelHandler.getcpqDaAccelStatus(attributeValue2));
		Assert.assertEquals("invalid",CPQDaAccelHandler.getcpqDaAccelStatus(attributeValue3));
		Assert.assertEquals("tmpDisabled",CPQDaAccelHandler.getcpqDaAccelStatus(attributeValue4));}
	
	@Test
	public void getcpqDaAccelErrCodeTest(){
		final String attributeValue1 = "11";
		final String attributeValue2 = "2";
		final String attributeValue3= "5";
		final String attributeValue4= "15";
		
		Assert.assertEquals("configCmd",CPQDaAccelHandler.getcpqDaAccelErrCode(attributeValue1));
		Assert.assertEquals("invalid",CPQDaAccelHandler.getcpqDaAccelErrCode(attributeValue2));
		Assert.assertEquals("disableCmd",CPQDaAccelHandler.getcpqDaAccelErrCode(attributeValue3));
		Assert.assertEquals("redundantSizeMismatch",CPQDaAccelHandler.getcpqDaAccelErrCode(attributeValue4));
		
	}
	
	@Test
	public void getcpqDaPhyDrvStatusTest(){
		final String attributeValue = "4";
		Assert.assertEquals("predictiveFailure",CPQDaAccelHandler.getcpqDaPhyDrvStatus(attributeValue));
	}
	
	@Test
	public void getcpqDaSpareStatusTest(){
		final String attributeValue = "3";
		Assert.assertEquals("failed",CPQDAStatusHandlers.getcpqDaSpareStatus(attributeValue));
	}
	
	@Test
	public void getcpqDaTapeDrvStatusTest(){
		final String attributeValue = "3";
		Assert.assertEquals("degraded",CPQDAStatusHandlers.getcpqDaTapeDrvStatus(attributeValue));
	}

	@Test
	public void getcpqDaCntlrBoardStatusTest(){
		final String attributeValue = "2";
		Assert.assertEquals("ok",CPQDAStatusHandlers.getcpqDaCntlrBoardStatus(attributeValue));
	}
	
	@Test
	public void getcpqDaLogDrvStatusTest(){
		final String attributeValue = "7";
		Assert.assertEquals("rebuilding",CPQDAStatusHandlers.getcpqDaLogDrvStatus(attributeValue));
	}
	
	@Test
	public void getcpqScsiLogDrvStatusTest(){
		final String attributeValue = "9";
		Assert.assertEquals("\n\tSCSI Logical Drive Status : badConnect",CPQScsIHandlers.getcpqScsiLogDrvStatus(attributeValue));
	}
	
	@Test
	public void getcpqScsiPhyDrvStatusTest(){
		final String attributeValue = "10";
		Assert.assertEquals("\n\tSCSI Logical Drive Status : offline",CPQScsIHandlers.getcpqScsiPhyDrvStatus(attributeValue));
	}
	
	@Test
	public void getcpqIdeAtaDiskStatusTest(){
		final String attributeValue = "3";
		Assert.assertEquals("smartError",CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskStatus(attributeValue));
	}
	
	@Test
	public void getcpqIdeAtaDiskChannelTest(){
		final String attributeValue = "3";
		Assert.assertEquals("\n\tATA Disk Channel : channel1",CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskChannel(attributeValue));
	}
	
	@Test
	public void getcpqIdeAtaDiskNumberTest(){
		final String attributeValue = "2";
		Assert.assertEquals("\n\tATA Disk Channel : channel0",CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskNumber(attributeValue));
	}
	
	@Test
	public void getcpqIdeLogicalDriveStatusTest(){
		final String attributeValue = "3";
		Assert.assertEquals("degraded",CPQIdeAtaDiskHandlers.getcpqIdeLogicalDriveStatus(attributeValue));
	}
	
	@Test
	public void getcpqHeCorrMemLogStatusTest(){
		final String attributeValue = "2";
		Assert.assertEquals("\n\tIDE Logical Drive Status : notSupported",CPQHeHandlers.getcpqHeCorrMemLogStatus(attributeValue));
	}
	
	@Test
	public void getcpqHeThermalDegradedActionTest(){
		final String attributeValue = "4";
		Assert.assertEquals("enabled",CPQHeHandlers.getcpqHeThermalDegradedAction(attributeValue));
	}
	
	@Test
	public void getcpqHeTemperatureLocaleTest(){
		final String attributeValue = "13";
		Assert.assertEquals("bridgeCard",CPQHeHandlers.getcpqHeTemperatureLocale(attributeValue));
	}
	
	@Test
	public void getcpqHeFltTolPowerSupplyStatusTest(){
		final	String attributeValue = "5";
		Assert.assertEquals("ioBoard",CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus(attributeValue));
	}
	
	@Test
	public void getcpqSm2CntlrSelfTestErrorsTest(){
		final String attributeValue = "2";
		Assert.assertEquals("Memory test error.\n",CPQOtherHandlers.getcpqSm2CntlrSelfTestErrors(attributeValue));
	}
	
	@Test
	public void getcpqNicIfPhysAdapterStatusTest(){
		final String attributeValue = "1";
		Assert.assertEquals("\n\tThe physical adapter status : unknown",CPQOtherHandlers.getcpqNicIfPhysAdapterStatus(attributeValue));
	}
	
	@Test
	public void gettrendIndicationTest(){
		final String attributeValue = "2";
		Assert.assertEquals("\n\tTrendIndication :  trendingUp",CPQOtherHandlers.gettrendIndication(attributeValue));
	
}
}