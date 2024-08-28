package com.ericsson.oss.mediation.snmp.translate.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.handlers.BSComponent;


public class BSComponentTest {

	@Test
	public void getBSComponenttest() {
		
		
		String str1= "\nComponent=Applicationserver" ;
		String str2= "\nComponent=Mediaserver" ;
		String str3= "\nComponent=Networkserver" ;
		String str4= "\nComponent=Relayserver" ;
		String str5= "\nComponent=Servicecontrolproxy" ;
		String str6= "\nComponent=Elementmanagementsystem" ;
		String str7= "\nComponent=Webserver" ;
		String str8= "\nComponent=Calldetailserver" ;
		String str9= "\nComponent=clientmanagementprofileserver" ;
		
		Assert.assertEquals(str1,BSComponent.getBSComponent("0"));
		Assert.assertEquals(str2,BSComponent.getBSComponent("1"));
		Assert.assertEquals(str3,BSComponent.getBSComponent("2"));
		Assert.assertEquals(str4,BSComponent.getBSComponent("3"));
		Assert.assertEquals(str5,BSComponent.getBSComponent("4"));
		Assert.assertEquals(str6,BSComponent.getBSComponent("5"));
		Assert.assertEquals(str7,BSComponent.getBSComponent("6"));
		Assert.assertEquals(str8,BSComponent.getBSComponent("7"));
		Assert.assertEquals(str9,BSComponent.getBSComponent("8"));
		
		}
 @Test
	public void getBSSubComponentTest(){
		
		String str1="\nSubComponent=Unspecified";
		String str2="\nSubComponent=Processmonitor";
		String str3="\nSubComponent=Webserver";
		String str4="\nSubComponent=Database";
		String str5="\nSubComponent=Sip";
		String str6="\nSubComponent=Ccp";
		String str7="\nSubComponent=Mgcp";
		String str8="\nSubComponent=Mcp";
		String str9="\nSubComponent=Smtp";
		String str10="\nSubComponent=Pop3";
		String str11="\nSubComponent=Rtcp";
		String str12="\nSubComponent=Conferencing";
		String str13="\nSubComponent=Rtp";
		String str14="\nSubComponent=Ivr";
		String str15="\nSubComponent=Filesystem";
		String str16="\nSubComponent=Callp";
		String str17="\nSubComponent=Nssynch";
		String str18="\nSubComponent=Mss";
		String str19="\nSubComponent=Transevent";
		String str20="\nSubComponent=Emergency";
		String str21="\nSubComponent=Smap";
		String str22="\nSubComponent=Loggingserver";
		String str23="\nSubComponent=Nslocation";
		String str24="\nSubComponent=Ims";
		String str25="\nSubComponent=Nrs";
		String str26="\nSubComponent=Oss";
		String str27="\nSubComponent=Accounting";
		String str28="\nSubComponent=Licensing";
		String str29="\nSubComponent=Ldap";
		String str30="\nSubComponent=PmReporting";
		String str31="\nSubComponent=Smdi";
		String str32="\nSubComponent=CpeDeviceManagement";
		String str33="\nSubComponent=NetworkDeviceManagement";
		String str34="\nSubComponent=ExternalAuthentication";
		String str35="\nSubComponent=LiveAudio";
		String str36="\nSubComponent=ServicePackMigration";
		String str37="\nSubComponent=Cap";
		String str38="\nSubComponent=OpenClientServer";
		String str39="\nSubComponent=VoicePortal";
		String str40="\nSubComponent=CallLogs";
		String str41="\nSubComponent=OciReporting";
		String str42="\nSubComponent=bcct";
		String str43="\nSubComponent=diameterServer";
		String str44="\nSubComponent=cms";
		String str45="\nSubComponent=taskMonitor";
		String str46="\nSubComponent=tcp";
		String str47="\nSubComponent=logging";
		
		Assert.assertEquals(str1,BSComponent.getBSSubComponent("0") );
		Assert.assertEquals(str2,BSComponent.getBSSubComponent("1") );
		Assert.assertEquals(str3,BSComponent.getBSSubComponent("2") );
		Assert.assertEquals(str4,BSComponent.getBSSubComponent("3") );
		Assert.assertEquals(str5,BSComponent.getBSSubComponent("4") );
		Assert.assertEquals(str6,BSComponent.getBSSubComponent("5") );
		Assert.assertEquals(str7,BSComponent.getBSSubComponent("6") );
		Assert.assertEquals(str8,BSComponent.getBSSubComponent("7") );
		Assert.assertEquals(str9,BSComponent.getBSSubComponent("8") );
		Assert.assertEquals(str10,BSComponent.getBSSubComponent("9") );
		Assert.assertEquals(str11,BSComponent.getBSSubComponent("10") );
		Assert.assertEquals(str12,BSComponent.getBSSubComponent("11") );
		Assert.assertEquals(str13,BSComponent.getBSSubComponent("12") );
		Assert.assertEquals(str14,BSComponent.getBSSubComponent("13") );
		Assert.assertEquals(str15,BSComponent.getBSSubComponent("14") );
		Assert.assertEquals(str16,BSComponent.getBSSubComponent("15") );
		Assert.assertEquals(str17,BSComponent.getBSSubComponent("16") );
		Assert.assertEquals(str18,BSComponent.getBSSubComponent("17") );
		Assert.assertEquals(str19,BSComponent.getBSSubComponent("18") );
		Assert.assertEquals(str20,BSComponent.getBSSubComponent("19") );
		Assert.assertEquals(str21,BSComponent.getBSSubComponent("20") );
		Assert.assertEquals(str22,BSComponent.getBSSubComponent("21") );
		Assert.assertEquals(str23,BSComponent.getBSSubComponent("22") );
		Assert.assertEquals(str24,BSComponent.getBSSubComponent("23") );
		Assert.assertEquals(str25,BSComponent.getBSSubComponent("24") );
		Assert.assertEquals(str26,BSComponent.getBSSubComponent("25") );
		Assert.assertEquals(str27,BSComponent.getBSSubComponent("26") );
		Assert.assertEquals(str28,BSComponent.getBSSubComponent("27") );
		Assert.assertEquals(str29,BSComponent.getBSSubComponent("28") );
		Assert.assertEquals(str30,BSComponent.getBSSubComponent("29") );
		Assert.assertEquals(str31,BSComponent.getBSSubComponent("30") );
		Assert.assertEquals(str32,BSComponent.getBSSubComponent("31") );
		Assert.assertEquals(str33,BSComponent.getBSSubComponent("32") );
		Assert.assertEquals(str34,BSComponent.getBSSubComponent("33") );
		Assert.assertEquals(str35,BSComponent.getBSSubComponent("34") );
		Assert.assertEquals(str36,BSComponent.getBSSubComponent("35") );
		Assert.assertEquals(str37,BSComponent.getBSSubComponent("36") );
		Assert.assertEquals(str38,BSComponent.getBSSubComponent("37") );
		Assert.assertEquals(str39,BSComponent.getBSSubComponent("38") );
		Assert.assertEquals(str40,BSComponent.getBSSubComponent("39") );
		Assert.assertEquals(str41,BSComponent.getBSSubComponent("40") );
		Assert.assertEquals(str42,BSComponent.getBSSubComponent("41") );
		Assert.assertEquals(str43,BSComponent.getBSSubComponent("42") );
		Assert.assertEquals(str44,BSComponent.getBSSubComponent("43") );
		Assert.assertEquals(str45,BSComponent.getBSSubComponent("44") );
		Assert.assertEquals(str46,BSComponent.getBSSubComponent("45") );
		Assert.assertEquals(str47,BSComponent.getBSSubComponent("46") );
		
		
		
		
		
		
		
		
		
		
		
		
	}
	
}
