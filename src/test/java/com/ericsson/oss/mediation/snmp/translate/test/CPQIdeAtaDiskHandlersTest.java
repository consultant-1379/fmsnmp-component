package com.ericsson.oss.mediation.snmp.translate.test;


import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.handlers.CPQIdeAtaDiskHandlers;

public class CPQIdeAtaDiskHandlersTest {

	@Test
	public void getcpqIdeAtaDiskStatustest() {
		
		String str1 = "other";
		String str2 = "ok";
		String str3 = "smartError";
		String str4 = "failed";
		
		Assert.assertEquals(str1,CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskStatus("1") );
		Assert.assertEquals(str2,CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskStatus("2") );
		Assert.assertEquals(str3,CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskStatus("3") );
		Assert.assertEquals(str4,CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskStatus("4") );
		
	}
	
	@Test
	public void getcpqIdeAtaDiskChannelTest(){
		
		String str1 = "\n\tATA Disk Channel : "+"other";
		String str2= "\n\tATA Disk Channel : "+"channel0";
		String str3= "\n\tATA Disk Channel : "+"channel1";
		
		Assert.assertEquals(str1, CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskChannel("1"));
		Assert.assertEquals(str2, CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskChannel("2"));
		Assert.assertEquals(str3, CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskChannel("3"));
		
	}
   
	@Test
	public void getcpqIdeAtaDiskNumberTest(){
		
		String str1 = "\n\tATA Disk Channel : "+"other";
		String str2= "\n\tATA Disk Channel : "+"channel0";
		String str3= "\n\tATA Disk Channel : "+"channel1";
		
		Assert.assertEquals(str1, CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskNumber("1"));
		Assert.assertEquals(str2, CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskNumber("2"));
		Assert.assertEquals(str3, CPQIdeAtaDiskHandlers.getcpqIdeAtaDiskNumber("3"));
	}
	
	@Test
	public void getcpqIdeLogicalDriveStatusTest(){
		
		String  str1 = "other";
		String 	str2 = "ok";
		String  str3 = "degraded";
		String  str4 = "rebuilding";
		String  str5 = "failed";
		
		Assert.assertEquals(str1, CPQIdeAtaDiskHandlers.getcpqIdeLogicalDriveStatus("1"));
		Assert.assertEquals(str2, CPQIdeAtaDiskHandlers.getcpqIdeLogicalDriveStatus("2"));
		Assert.assertEquals(str3, CPQIdeAtaDiskHandlers.getcpqIdeLogicalDriveStatus("3"));
		Assert.assertEquals(str4, CPQIdeAtaDiskHandlers.getcpqIdeLogicalDriveStatus("4"));
		Assert.assertEquals(str5, CPQIdeAtaDiskHandlers.getcpqIdeLogicalDriveStatus("5"));
		
	}
}
