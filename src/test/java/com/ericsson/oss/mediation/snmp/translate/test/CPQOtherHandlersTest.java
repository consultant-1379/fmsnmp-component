package com.ericsson.oss.mediation.snmp.translate.test;


import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.handlers.CPQOtherHandlers;

public class CPQOtherHandlersTest {

	@Test
	public void getcpqNicIfPhysAdapterStatustest() {
		
		String str1 = "\n\tThe physical adapter status : "+"unknown";
		String str2 = "\n\tThe physical adapter status : "+"ok";
		String str3 = "\n\tThe physical adapter status : "+"generalFailure";
		String str4 = "\n\tThe physical adapter status : "+"linkFailure";
		
		Assert.assertEquals(str1,CPQOtherHandlers.getcpqNicIfPhysAdapterStatus("1"));
		Assert.assertEquals(str2,CPQOtherHandlers.getcpqNicIfPhysAdapterStatus("2"));
		Assert.assertEquals(str3,CPQOtherHandlers.getcpqNicIfPhysAdapterStatus("3"));
		Assert.assertEquals(str4,CPQOtherHandlers.getcpqNicIfPhysAdapterStatus("4"));
		
	}
	
	@Test
	public void gettrendIndicationTest(){
		
		String str1 = "\n\tTrendIndication :  "+"unknown";
		String str2 = "\n\tTrendIndication :  "+"notApplicable";
		String str3 = "\n\tTrendIndication :  "+"trendingUp";
		String str4 = "\n\tTrendIndication :  "+"trendingDown";
		String str5 = "\n\tTrendIndication :  "+"noChange";
		
		Assert.assertEquals(str1,CPQOtherHandlers.gettrendIndication("0"));
		Assert.assertEquals(str2,CPQOtherHandlers.gettrendIndication("1"));
		Assert.assertEquals(str3,CPQOtherHandlers.gettrendIndication("2"));
		Assert.assertEquals(str4,CPQOtherHandlers.gettrendIndication("3"));
		Assert.assertEquals(str5,CPQOtherHandlers.gettrendIndication("4"));
		
		
	}

}
