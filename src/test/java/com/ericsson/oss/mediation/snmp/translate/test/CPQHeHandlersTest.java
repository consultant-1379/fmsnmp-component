package com.ericsson.oss.mediation.snmp.translate.test;


import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.handlers.CPQHeHandlers;

public class CPQHeHandlersTest {

	@Test
	public void getcpqHeCorrMemLogStatustest() {
		
		String str1 = "\n\tIDE Logical Drive Status : "+"other";
		String str2 = "\n\tIDE Logical Drive Status : "+"notSupported";
		String str3 = "\n\tIDE Logical Drive Status : "+"disabled";
		String str4 = "\n\tIDE Logical Drive Status : "+"enabled";
		
		Assert.assertEquals(str1,CPQHeHandlers.getcpqHeCorrMemLogStatus("1"));
		Assert.assertEquals(str2,CPQHeHandlers.getcpqHeCorrMemLogStatus("2"));
		Assert.assertEquals(str3,CPQHeHandlers.getcpqHeCorrMemLogStatus("3"));
		Assert.assertEquals(str4,CPQHeHandlers.getcpqHeCorrMemLogStatus("4"));
		
	}
	
	@Test
	public void getcpqHeThermalDegradedActionTest(){
		
		String str1 = "other";
		String str2 = "notSupported";
		String str3 = "disabled";
		String str4 = "enabled";
		
		Assert.assertEquals(str1, CPQHeHandlers.getcpqHeThermalDegradedAction("1"));
		Assert.assertEquals(str2, CPQHeHandlers.getcpqHeThermalDegradedAction("2"));
		Assert.assertEquals(str3, CPQHeHandlers.getcpqHeThermalDegradedAction("3"));
		Assert.assertEquals(str4, CPQHeHandlers.getcpqHeThermalDegradedAction("4"));
		
	}
	
	@Test
	public void getcpqHeTemperatureLocaleTest(){
		
		String str1 = "other";
		String str2 = "unknown";
		String str3 = "system";
		String str4 = "systemBoard";
		String str5 = "ioBoard";
		String str6 = "cpu";
		String str7 = "memory";
		String str8 = "storage";
		String str9 = "removableMedia";
		String str10 = "powerSupply";
		String str11 = "ambient";
		String str12 = "chassis";
		String str13 = "bridgeCard";
		
		Assert.assertEquals(str1,CPQHeHandlers.getcpqHeTemperatureLocale("1"));
		Assert.assertEquals(str2,CPQHeHandlers.getcpqHeTemperatureLocale("2"));
		Assert.assertEquals(str3,CPQHeHandlers.getcpqHeTemperatureLocale("3"));
		Assert.assertEquals(str4,CPQHeHandlers.getcpqHeTemperatureLocale("4"));
		Assert.assertEquals(str5,CPQHeHandlers.getcpqHeTemperatureLocale("5"));
		Assert.assertEquals(str6,CPQHeHandlers.getcpqHeTemperatureLocale("6"));
		Assert.assertEquals(str7,CPQHeHandlers.getcpqHeTemperatureLocale("7"));
		Assert.assertEquals(str8,CPQHeHandlers.getcpqHeTemperatureLocale("8"));
		Assert.assertEquals(str9,CPQHeHandlers.getcpqHeTemperatureLocale("9"));
		Assert.assertEquals(str10,CPQHeHandlers.getcpqHeTemperatureLocale("10"));
		Assert.assertEquals(str11,CPQHeHandlers.getcpqHeTemperatureLocale("11"));
		Assert.assertEquals(str12,CPQHeHandlers.getcpqHeTemperatureLocale("12"));
		Assert.assertEquals(str13,CPQHeHandlers.getcpqHeTemperatureLocale("13"));
		
	}
	
	@Test
	public void getcpqHeFltTolPowerSupplyStatusTest(){
		
		String str1 = "other";
		String str2 = "unknown";
		String str3 = "system";
		String str4 = "systemBoard";
		String str5 = "ioBoard";
		String str6 = "cpu";
		String str7 = "memory";
		String str8 = "storage";
		String str9 = "removableMedia";
		String str10 = "powerSupply";
		String str11 = "ambient";
		String str12 = "chassis";
		String str13 = "bridgeCard";
		
		Assert.assertEquals(str1,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("1"));
		Assert.assertEquals(str2,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("2"));
		Assert.assertEquals(str3,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("3"));
		Assert.assertEquals(str4,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("4"));
		Assert.assertEquals(str5,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("5"));
		Assert.assertEquals(str6,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("6"));
		Assert.assertEquals(str7,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("7"));
		Assert.assertEquals(str8,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("8"));
		Assert.assertEquals(str9,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("9"));
		Assert.assertEquals(str10,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("10"));
		Assert.assertEquals(str11,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("11"));
		Assert.assertEquals(str12,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("12"));
		Assert.assertEquals(str13,CPQHeHandlers.getcpqHeFltTolPowerSupplyStatus("13"));
		
	}

}
