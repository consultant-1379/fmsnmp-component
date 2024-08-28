
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

	public void getBSSubComponentTest(){
		
		String str1="\n SubComponent=Unspecified";
		String str2="\n SubComponent=Processmonitor";
		String str3="\n SubComponent=Webserver";
		String str4="\n SubComponent=Database";
		String str5="\n SubComponent=Unspecified";
		String str6="\n SubComponent=Sip";
		String str7="\n SubComponent=Ccp";
		String str8="\n SubComponent=Mgcp";
		String str9="\n SubComponent=Mcp";
		String str10="\n SubComponent=Smtp";
		String str11="\n SubComponent=Pop3";
		String str12="\n SubComponent=Rtcp";
		String str13="\n SubComponent=Conferencing";
		String str14="\n SubComponent=Rtp";
		String str15="\n SubComponent=Ivr";
		String str16="\n SubComponent=Filesystem";
		String str17="\n SubComponent=Callp";
		String str18="\n SubComponent=Nssynch";
		String str19="\n SubComponent=Mss";
		String str20="\n SubComponent=Transevent";
		String str21="\n SubComponent=Emergency";
		String str22="\n SubComponent=Smap";
		String str23="\n SubComponent=Loggingserver";
		String str24="\n SubComponent=Nslocation";
		String str25="\n SubComponent=Ims";
		String str26="\n SubComponent=Nrs";
		String str27="\n SubComponent=Oss";
		String str28="\n SubComponent=Accounting";
		String str29="\n SubComponent=Licensing";
		String str30="\n SubComponent=Ldap";
		String str31="\n SubComponent=PmReporting";
		String str32="\n SubComponent=Smdi";
		String str33="\n SubComponent=CpeDeviceManagement";
		String str34="\n SubComponent=NetworkDeviceManagement";
		String str35="\n SubComponent=ExternalAuthentication";
		String str36="\n SubComponent=LiveAudio";
		
		
		
		
		
		
	}
	
}
