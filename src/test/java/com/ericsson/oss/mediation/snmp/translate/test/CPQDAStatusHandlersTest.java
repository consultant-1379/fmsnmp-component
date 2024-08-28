package com.ericsson.oss.mediation.snmp.translate.test;


import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.handlers.CPQDAStatusHandlers;

public class CPQDAStatusHandlersTest {

	@Test
	public void getcpqDaSpareStatustest() {
		
		String str1 = "other";
		String str2 = "ok";
		String str3 = "failed";
		String str4 = "predictiveFailure";
		
		Assert.assertEquals(str1,CPQDAStatusHandlers.getcpqDaSpareStatus("1"));
		Assert.assertEquals(str2,CPQDAStatusHandlers.getcpqDaSpareStatus("2"));
		Assert.assertEquals(str3,CPQDAStatusHandlers.getcpqDaSpareStatus("3"));
		Assert.assertEquals(str4,CPQDAStatusHandlers.getcpqDaSpareStatus("4"));
		
	}
	@Test
	public void getcpqDaTapeDrvStatusTest(){
		
		String str1 = "other";
		String str2 = "invalid";
		String str3 = "degraded";
		String str4 = "failed";
		String str5 = "offline";
		String str6 = "missingWasOk";
		String str7 = "missingWasOffline";
		
		Assert.assertEquals(str1,CPQDAStatusHandlers.getcpqDaTapeDrvStatus("1"));
		Assert.assertEquals(str2,CPQDAStatusHandlers.getcpqDaTapeDrvStatus("2"));
		Assert.assertEquals(str3,CPQDAStatusHandlers.getcpqDaTapeDrvStatus("3"));
		Assert.assertEquals(str4,CPQDAStatusHandlers.getcpqDaTapeDrvStatus("4"));
		Assert.assertEquals(str5,CPQDAStatusHandlers.getcpqDaTapeDrvStatus("5"));
		Assert.assertEquals(str6,CPQDAStatusHandlers.getcpqDaTapeDrvStatus("6"));
		Assert.assertEquals(str7,CPQDAStatusHandlers.getcpqDaTapeDrvStatus("7"));
		
	}
	@Test
	public void getcpqDaCntlrBoardStatusTest(){
		
		String str1 = "other";
		String str2 = "ok";
		String str3 = "generalFailure";
		String str4 = "cableProblem";
		String str5 = "poweredOff";
		
		Assert.assertEquals(str1,CPQDAStatusHandlers.getcpqDaCntlrBoardStatus("1") );
		Assert.assertEquals(str2,CPQDAStatusHandlers.getcpqDaCntlrBoardStatus("2") );
		Assert.assertEquals(str3,CPQDAStatusHandlers.getcpqDaCntlrBoardStatus("3") );
		Assert.assertEquals(str4,CPQDAStatusHandlers.getcpqDaCntlrBoardStatus("4") );
		Assert.assertEquals(str5,CPQDAStatusHandlers.getcpqDaCntlrBoardStatus("5") );
		
	}
	
	@Test
	public void getcpqDaLogDrvStatussTest(){
		
		String str1 = "other";
		String str2 = "ok";
		String str3 = "failed";
		String str4 = "unconfigured";
		String str5 = "recovering";
		String str6 = "readyForRebuild";
		String str7 = "rebuilding";
		String str8 = "wrongDrive";
		String str9 = "badConnect";
		String str10 = "overheating";
		String str11 = "shutdown";
		String str12 = "expanding";
		String str13 = "notAvailable";
		String str14 = "queuedForExpansion";
		
		Assert.assertEquals(str1,CPQDAStatusHandlers.getcpqDaLogDrvStatus("1"));
		Assert.assertEquals(str2,CPQDAStatusHandlers.getcpqDaLogDrvStatus("2"));
		Assert.assertEquals(str3,CPQDAStatusHandlers.getcpqDaLogDrvStatus("3"));
		Assert.assertEquals(str4,CPQDAStatusHandlers.getcpqDaLogDrvStatus("4"));
		Assert.assertEquals(str5,CPQDAStatusHandlers.getcpqDaLogDrvStatus("5"));
		Assert.assertEquals(str6,CPQDAStatusHandlers.getcpqDaLogDrvStatus("6"));
		Assert.assertEquals(str7,CPQDAStatusHandlers.getcpqDaLogDrvStatus("7"));
		Assert.assertEquals(str8,CPQDAStatusHandlers.getcpqDaLogDrvStatus("8"));
		Assert.assertEquals(str9,CPQDAStatusHandlers.getcpqDaLogDrvStatus("9"));
		Assert.assertEquals(str10,CPQDAStatusHandlers.getcpqDaLogDrvStatus("10"));
		Assert.assertEquals(str11,CPQDAStatusHandlers.getcpqDaLogDrvStatus("11"));
		Assert.assertEquals(str12,CPQDAStatusHandlers.getcpqDaLogDrvStatus("12"));
		Assert.assertEquals(str13,CPQDAStatusHandlers.getcpqDaLogDrvStatus("13"));
		Assert.assertEquals(str14,CPQDAStatusHandlers.getcpqDaLogDrvStatus("14"));
		
		
		
	}

}
