package com.ericsson.oss.mediation.snmp.translate.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.handlers.CPQDaAccelHandler;

public class CPQDaAccelHandlerTest {

	@Test
	public void getcpqDaAccelErrCodetest() {
		
		String str1="other";
		String str2="invalid";
		String str3 = "badConfig";
		String str4= "lowBattery";
		String str5 = "disableCmd";
		String str6 = "noResources";
		String str7 = "notConnected";
		String str8 = "badMirrorData";
		String str9 = "readErr";
		String str10 = "writeErr";
		String str11 = "configCmd";
		String str12 = "expandInProgress";
		String str13 = "snapshotInProgress";
		String str14 = "redundantLowBattery";
		String str15 = "redundantSizeMismatch";
		String str16 = "redundantCacheFailure";
		String str17 = "excessiveEccErrors";
		String str18 = "adgEnablerMissing";
		String str19 = "postEccErrors";
		
		
		Assert.assertEquals(str1,CPQDaAccelHandler.getcpqDaAccelErrCode("1"));
		Assert.assertEquals(str2,CPQDaAccelHandler.getcpqDaAccelErrCode("2"));
		Assert.assertEquals(str3,CPQDaAccelHandler.getcpqDaAccelErrCode("3"));
		Assert.assertEquals(str4,CPQDaAccelHandler.getcpqDaAccelErrCode("4"));
		Assert.assertEquals(str5,CPQDaAccelHandler.getcpqDaAccelErrCode("5"));
		Assert.assertEquals(str6,CPQDaAccelHandler.getcpqDaAccelErrCode("6"));
		Assert.assertEquals(str7,CPQDaAccelHandler.getcpqDaAccelErrCode("7"));
		Assert.assertEquals(str8,CPQDaAccelHandler.getcpqDaAccelErrCode("8"));
		Assert.assertEquals(str9,CPQDaAccelHandler.getcpqDaAccelErrCode("9"));
		Assert.assertEquals(str10,CPQDaAccelHandler.getcpqDaAccelErrCode("10"));
		Assert.assertEquals(str11,CPQDaAccelHandler.getcpqDaAccelErrCode("11"));
		Assert.assertEquals(str12,CPQDaAccelHandler.getcpqDaAccelErrCode("12"));
		Assert.assertEquals(str13,CPQDaAccelHandler.getcpqDaAccelErrCode("13"));
		Assert.assertEquals(str14,CPQDaAccelHandler.getcpqDaAccelErrCode("14"));
		Assert.assertEquals(str15,CPQDaAccelHandler.getcpqDaAccelErrCode("15"));
		Assert.assertEquals(str16,CPQDaAccelHandler.getcpqDaAccelErrCode("16"));
		Assert.assertEquals(str17,CPQDaAccelHandler.getcpqDaAccelErrCode("17"));
		Assert.assertEquals(str18,CPQDaAccelHandler.getcpqDaAccelErrCode("18"));
		Assert.assertEquals(str19,CPQDaAccelHandler.getcpqDaAccelErrCode("19"));
		
		
		
		
		
		
		
	}
		

}
