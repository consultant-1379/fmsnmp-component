package com.ericsson.oss.mediation.snmp.translate.test;


import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.handlers.CPQScsIHandlers;

public class CPQScsIHandlersTest {

	@Test
	public void getcpqScsiLogDrvStatustest() {
		
     String str1 = "\n\tSCSI Logical Drive Status : "+"other";
     String str2 = "\n\tSCSI Logical Drive Status : "+"ok";
     String str3 = "\n\tSCSI Logical Drive Status : "+"failed";
     String str4 = "\n\tSCSI Logical Drive Status : "+"unconfigured";
     String str5 = "\n\tSCSI Logical Drive Status : "+"recovering";
     String str6 = "\n\tSCSI Logical Drive Status : "+"readyForRebuild";
     String str7 = "\n\tSCSI Logical Drive Status : "+"rebuilding";
     String str8 = "\n\tSCSI Logical Drive Status : "+"wrongDrive";
     String str9 = "\n\tSCSI Logical Drive Status : "+"badConnect";
     String str10 = "\n\tSCSI Logical Drive Status : "+"degraded";
     String str11 = "\n\tSCSI Logical Drive Status : "+"disabled";
     
     Assert.assertEquals(str1,CPQScsIHandlers.getcpqScsiLogDrvStatus("1"));
     Assert.assertEquals(str2,CPQScsIHandlers.getcpqScsiLogDrvStatus("2"));
     Assert.assertEquals(str3,CPQScsIHandlers.getcpqScsiLogDrvStatus("3"));
     Assert.assertEquals(str4,CPQScsIHandlers.getcpqScsiLogDrvStatus("4"));
     Assert.assertEquals(str5,CPQScsIHandlers.getcpqScsiLogDrvStatus("5"));
     Assert.assertEquals(str6,CPQScsIHandlers.getcpqScsiLogDrvStatus("6"));
     Assert.assertEquals(str7,CPQScsIHandlers.getcpqScsiLogDrvStatus("7"));
     Assert.assertEquals(str8,CPQScsIHandlers.getcpqScsiLogDrvStatus("8"));
     Assert.assertEquals(str9,CPQScsIHandlers.getcpqScsiLogDrvStatus("9"));
     Assert.assertEquals(str10,CPQScsIHandlers.getcpqScsiLogDrvStatus("10"));
     Assert.assertEquals(str11,CPQScsIHandlers.getcpqScsiLogDrvStatus("11"));
     
	}
	
	@Test
	public void getcpqScsiPhyDrvStatusTest(){
		
		String str1 = "\n\tSCSI Logical Drive Status : "+"other";
		String str2 = "\n\tSCSI Logical Drive Status : "+"ok";
		String str3 = "\n\tSCSI Logical Drive Status : "+"failed";
		String str4 = "\n\tSCSI Logical Drive Status : "+"notConfigured";
		String str5 = "\n\tSCSI Logical Drive Status : "+"badCable";
		String str6 = "\n\tSCSI Logical Drive Status : "+"missingWasOk";
		String str7 = "\n\tSCSI Logical Drive Status : "+"missingWasFailed";
		String str8 = "\n\tSCSI Logical Drive Status : "+"predictiveFailure";
		String str9 = "\n\tSCSI Logical Drive Status : "+"missingWasPredictiveFailure";
		String str10 = "\n\tSCSI Logical Drive Status : "+"offline";
		String str11 = "\n\tSCSI Logical Drive Status : "+"missingWasOffline";
		String str12 = "\n\tSCSI Logical Drive Status : "+"hardError";
		
		Assert.assertEquals(str1, CPQScsIHandlers.getcpqScsiPhyDrvStatus("1"));
		Assert.assertEquals(str2, CPQScsIHandlers.getcpqScsiPhyDrvStatus("2"));
		Assert.assertEquals(str3, CPQScsIHandlers.getcpqScsiPhyDrvStatus("3"));
		Assert.assertEquals(str4, CPQScsIHandlers.getcpqScsiPhyDrvStatus("4"));
		Assert.assertEquals(str5, CPQScsIHandlers.getcpqScsiPhyDrvStatus("5"));
		Assert.assertEquals(str6, CPQScsIHandlers.getcpqScsiPhyDrvStatus("6"));
		Assert.assertEquals(str7, CPQScsIHandlers.getcpqScsiPhyDrvStatus("7"));
		Assert.assertEquals(str8, CPQScsIHandlers.getcpqScsiPhyDrvStatus("8"));
		Assert.assertEquals(str9, CPQScsIHandlers.getcpqScsiPhyDrvStatus("9"));
		Assert.assertEquals(str10, CPQScsIHandlers.getcpqScsiPhyDrvStatus("10"));
		Assert.assertEquals(str11, CPQScsIHandlers.getcpqScsiPhyDrvStatus("11"));
		Assert.assertEquals(str12, CPQScsIHandlers.getcpqScsiPhyDrvStatus("12"));
	}

}
