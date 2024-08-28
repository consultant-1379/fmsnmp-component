package com.ericsson.oss.mediation.snmp.translate.test;


import org.junit.*;

import com.ericsson.oss.mediation.translator.model.alarm.handlers.BGPOSFPTrapTypes;

public class BGPOSFPTrapTypesTest {
	
	
	@Ignore
	@Test
	public void getbgposfpTrapTypetest() {
		
		String netscreenTrapType1="traffic-sec";
		String netscreenTrapType2="traffic-min";
		String netscreenTrapType3="winnuke";
		String netscreenTrapType4="syn-attack";				
		String netscreenTrapType5="tear-drop";
		String netscreenTrapType6="ping-death";
		String netscreenTrapType7="ip-spoofing";
		String netscreenTrapType8="ip-src-route";
		String netscreenTrapType9="land";
		String netscreenTrapType10="icmp-flood";
		String netscreenTrapType11="udp-flood";
		String netscreenTrapType12="illegal-cms-svr";
		String netscreenTrapType13="url-block-srv";
		String netscreenTrapType14="port-scan";
		String netscreenTrapType15="addr-sweep";
		String netscreenTrapType16="low-memory";
		String netscreenTrapType17="dns-srv-down";
		String netscreenTrapType18="generic-HW-fail";
		String netscreenTrapType19="lb-srv-down";
		String netscreenTrapType20="log-full";
		String netscreenTrapType21="x509";
		String netscreenTrapType22="vpn-ike";
		String netscreenTrapType23="admin";
		String netscreenTrapType24="sme";
		String netscreenTrapType25="dhcp";
		String netscreenTrapType26="cpu-usage-high";
		String netscreenTrapType27="ip-conflict";
		String netscreenTrapType28="attact-malicious-url";
		String netscreenTrapType29="session-threshold";
		String netscreenTrapType30="ssh-alarm";
		String netscreenTrapType31="vpn-tunnel-up";
		String netscreenTrapType32="vpn-tunnel-down";
		String netscreenTrapType33="vpn-replay-attack";
		String netscreenTrapType34="vpn-l2tp-tunnel-remove";
		String netscreenTrapType35="vpn-l2tp-tunnel-remove-err";
		String netscreenTrapType36="vpn-l2tp-call-remove";
		String netscreenTrapType37="vpn-l2tp-call-remove-err";
		String netscreenTrapType38="vpn-ias-too-many";
		String netscreenTrapType39="vpn-ias-over-threshold";
		String netscreenTrapType40="vpn-ias-under-threshold";
		String netscreenTrapType41="vpn-ias-ike-error";
		String netscreenTrapType42="allocated-session-threshold";
		String netscreenTrapType43="av-scan-mgr";
		String netscreenTrapType44="nsrp-rto-up";
		String netscreenTrapType45="nsrp-rto-down";
		String netscreenTrapType46= "nsrp-trackip-success";
		String netscreenTrapType47= "nsrp-trackip-failed";
		String netscreenTrapType48= "nsrp-trackip-failover";
		String netscreenTrapType49= "nsrp-inconsistent-configuration";
		String netscreenTrapType50= "nsrp-vsd-init";
		String netscreenTrapType51= "nsrp-vsd-master";
		String netscreenTrapType52 = "nsrp-vsd-pbackup";
		String netscreenTrapType53= "nsrp-vsd-backup";
		String netscreenTrapType54= "nsrp-vsd-ineligible";
		String netscreenTrapType55= "nsrp-vsd-inoperable";
		String netscreenTrapType56= "nsrp-vsd-req-hearbeat-2nd";
		String netscreenTrapType57= "nsrp-vsd-reply-2nd";
		String netscreenTrapType58= "nsrp-rto-duplicated";
		String netscreenTrapType59= "dc-fail-reconnect-mc";
		String netscreenTrapType60= "mc-fail-reconnect-db";
		String netscreenTrapType61= "dc-fail-init";
		String netscreenTrapType62= "mc-fail-init";
		String netscreenTrapType63 = "unknown-connect-attempt-dc";
		String netscreenTrapType64= "dc-reinit";
		String netscreenTrapType65= "mc-reinit";
		String netscreenTrapType66= "dc-fail-auth";
		String netscreenTrapType67= "dc-mc-version-unmatch";
		String netscreenTrapType68= "dc-log-full";
		String netscreenTrapType69= "device-connect-dc";
		String netscreenTrapType70= "device-disconnect-dc";
		String netscreenTrapType71= "usb-device-operation";
		String netscreenTrapType72= "ppp-no-ip-cfg";
		String netscreenTrapType73= "ppp-no-ip-in-pool";
		String netscreenTrapType74= "ipv6-conflict";
		String netscreenTrapType75= "dip-util-raise";
		String netscreenTrapType76= "dip-util-clear";
		String netscreenTrapType77= "route-alarm";
		String netscreenTrapType78= "ospf-flood";
		String netscreenTrapType79= "rip-flood";
		String netscreenTrapType80= "bgp-established";
		String netscreenTrapType81= "bgp-backwardtransition";
		String netscreenTrapType82= "ospf-virtifstatechange";
		String netscreenTrapType83= "ospf-nbrstatechange";
		String netscreenTrapType84= "ospf-virtnbrstatechange";
		String netscreenTrapType85= "ospf-ifconfigerror";
		String netscreenTrapType86 = "ospf-virtifconfigerror";
		String netscreenTrapType87= "ospf-ifauthfailure";
		String netscreenTrapType88= "ospf-virtifauthfailure";
		String netscreenTrapType89= "ospf-ifrxbadpacket";
		String netscreenTrapType90= "ospf-virtifrxbadpacket";
		String netscreenTrapType91= "ospf-txretransmit";
		String netscreenTrapType92= "ospf-virtiftxretransmit";
		String netscreenTrapType93= "ospf-originatelsa";
		String netscreenTrapType94= "ospf-maxagelsa";
		String netscreenTrapType95= "ospf-lsdboverflow";
		String netscreenTrapType96= "ospf-lsdbapproachingoverflow";
		String netscreenTrapType97= "ospf-ifstatechange";
		String netscreenTrapType98= "ids-component";
		String netscreenTrapType99= "ids-icmp-flood";
		String netscreenTrapType100= "ids-udp-flood";
		String netscreenTrapType101= "ids-winnuke";
		String netscreenTrapType102= "ids-port-scan";
		String netscreenTrapType103= "ids-addr-sweep";
		String netscreenTrapType104= "ids-tear-drop";
		String netscreenTrapType105= "ids-syn";
		String netscreenTrapType106= "ids-ip-spoofing";
		String netscreenTrapType107= "ids-ping-death";
		String netscreenTrapType108 = "ids-ip-source-route";
		String netscreenTrapType109= "ids-land";
		String netscreenTrapType110= "syn-frag-attack";
		String netscreenTrapType111= "tcp-without-flag";
		String netscreenTrapType112= "unknow-ip-packet";
		String netscreenTrapType113= "bad-ip-option";
		String netscreenTrapType114= "dst-ip-session-limit";
		String netscreenTrapType115= "ids-block-zip";
		String netscreenTrapType116= "ids-block-jar";
		String netscreenTrapType117= "ids-block-exe";
		String netscreenTrapType118 = "ids-block-activex";
		String netscreenTrapType119= "icmp-fragment";
		String netscreenTrapType120= "too-large-icmp";
		String netscreenTrapType121= "tcp-syn-fin";
		String netscreenTrapType122= "tcp-fin-no-ack";
		String netscreenTrapType123= "ids-tcp-syn-ack-ack";
		String netscreenTrapType124= "ids-ip-block-frag";
		String netscreenTrapType125= "ids-icmp-ping-id-zero";
		String netscreenTrapType126= "cpu-limit-s2f-forced";
		String netscreenTrapType127= "cpu-limit-s2f-auto";
		String netscreenTrapType128= "cpu-limit-f2s-forced";
		String netscreenTrapType129= "cpu-limit-f2s-timeout";
		String netscreenTrapType130= "cpu-limit-f2s-auto";
		
		
		
		
		Assert.assertEquals(netscreenTrapType1,BGPOSFPTrapTypes.getbgposfpTrapType("1"));
		Assert.assertEquals(netscreenTrapType2,BGPOSFPTrapTypes.getbgposfpTrapType("2"));
		Assert.assertEquals(netscreenTrapType3,BGPOSFPTrapTypes.getbgposfpTrapType("4"));
		Assert.assertEquals(netscreenTrapType4,BGPOSFPTrapTypes.getbgposfpTrapType("5"));
		Assert.assertEquals(netscreenTrapType5,BGPOSFPTrapTypes.getbgposfpTrapType("6"));
		Assert.assertEquals(netscreenTrapType6,BGPOSFPTrapTypes.getbgposfpTrapType("7"));
		Assert.assertEquals(netscreenTrapType7,BGPOSFPTrapTypes.getbgposfpTrapType("8"));
		Assert.assertEquals(netscreenTrapType8,BGPOSFPTrapTypes.getbgposfpTrapType("9"));
		Assert.assertEquals(netscreenTrapType9,BGPOSFPTrapTypes.getbgposfpTrapType("10"));
		Assert.assertEquals(netscreenTrapType10,BGPOSFPTrapTypes.getbgposfpTrapType("11"));
		Assert.assertEquals(netscreenTrapType11,BGPOSFPTrapTypes.getbgposfpTrapType("12"));
		Assert.assertEquals(netscreenTrapType12,BGPOSFPTrapTypes.getbgposfpTrapType("13"));
		Assert.assertEquals(netscreenTrapType13,BGPOSFPTrapTypes.getbgposfpTrapType("14"));
		Assert.assertEquals(netscreenTrapType14,BGPOSFPTrapTypes.getbgposfpTrapType("16"));
		Assert.assertEquals(netscreenTrapType15,BGPOSFPTrapTypes.getbgposfpTrapType("17"));
		Assert.assertEquals(netscreenTrapType16,BGPOSFPTrapTypes.getbgposfpTrapType("20"));
		Assert.assertEquals(netscreenTrapType17,BGPOSFPTrapTypes.getbgposfpTrapType("21"));
		Assert.assertEquals(netscreenTrapType18,BGPOSFPTrapTypes.getbgposfpTrapType("22"));
		Assert.assertEquals(netscreenTrapType19,BGPOSFPTrapTypes.getbgposfpTrapType("23"));
		Assert.assertEquals(netscreenTrapType20,BGPOSFPTrapTypes.getbgposfpTrapType("24"));
		Assert.assertEquals(netscreenTrapType21,BGPOSFPTrapTypes.getbgposfpTrapType("25"));
		Assert.assertEquals(netscreenTrapType22,BGPOSFPTrapTypes.getbgposfpTrapType("26"));
		Assert.assertEquals(netscreenTrapType23,BGPOSFPTrapTypes.getbgposfpTrapType("27"));
		Assert.assertEquals(netscreenTrapType24,BGPOSFPTrapTypes.getbgposfpTrapType("28"));
		Assert.assertEquals(netscreenTrapType25,BGPOSFPTrapTypes.getbgposfpTrapType("29"));
		Assert.assertEquals(netscreenTrapType26,BGPOSFPTrapTypes.getbgposfpTrapType("30"));
		Assert.assertEquals(netscreenTrapType27,BGPOSFPTrapTypes.getbgposfpTrapType("31"));
		Assert.assertEquals(netscreenTrapType28,BGPOSFPTrapTypes.getbgposfpTrapType("32"));
		Assert.assertEquals(netscreenTrapType29,BGPOSFPTrapTypes.getbgposfpTrapType("33"));
		Assert.assertEquals(netscreenTrapType30,BGPOSFPTrapTypes.getbgposfpTrapType("34"));
		Assert.assertEquals(netscreenTrapType31,BGPOSFPTrapTypes.getbgposfpTrapType("40"));
		Assert.assertEquals(netscreenTrapType32,BGPOSFPTrapTypes.getbgposfpTrapType("41"));
		Assert.assertEquals(netscreenTrapType33,BGPOSFPTrapTypes.getbgposfpTrapType("42"));
		Assert.assertEquals(netscreenTrapType34,BGPOSFPTrapTypes.getbgposfpTrapType("43"));
		Assert.assertEquals(netscreenTrapType35,BGPOSFPTrapTypes.getbgposfpTrapType("44"));
		Assert.assertEquals(netscreenTrapType36,BGPOSFPTrapTypes.getbgposfpTrapType("45"));
		Assert.assertEquals(netscreenTrapType37,BGPOSFPTrapTypes.getbgposfpTrapType("46"));
		Assert.assertEquals(netscreenTrapType38,BGPOSFPTrapTypes.getbgposfpTrapType("47"));
		Assert.assertEquals(netscreenTrapType39,BGPOSFPTrapTypes.getbgposfpTrapType("48"));
		Assert.assertEquals(netscreenTrapType40,BGPOSFPTrapTypes.getbgposfpTrapType("49"));
		Assert.assertEquals(netscreenTrapType41,BGPOSFPTrapTypes.getbgposfpTrapType("50"));
		Assert.assertEquals(netscreenTrapType42,BGPOSFPTrapTypes.getbgposfpTrapType("51"));
		Assert.assertEquals(netscreenTrapType43,BGPOSFPTrapTypes.getbgposfpTrapType("554"));
		Assert.assertEquals(netscreenTrapType44,BGPOSFPTrapTypes.getbgposfpTrapType("60"));
		Assert.assertEquals(netscreenTrapType45,BGPOSFPTrapTypes.getbgposfpTrapType("61"));
		Assert.assertEquals(netscreenTrapType46,BGPOSFPTrapTypes.getbgposfpTrapType("62"));
		Assert.assertEquals(netscreenTrapType47,BGPOSFPTrapTypes.getbgposfpTrapType("63"));
		Assert.assertEquals(netscreenTrapType48,BGPOSFPTrapTypes.getbgposfpTrapType("64"));
		Assert.assertEquals(netscreenTrapType49,BGPOSFPTrapTypes.getbgposfpTrapType("65"));
		Assert.assertEquals(netscreenTrapType50,BGPOSFPTrapTypes.getbgposfpTrapType("70"));
		Assert.assertEquals(netscreenTrapType51,BGPOSFPTrapTypes.getbgposfpTrapType("71"));
		Assert.assertEquals(netscreenTrapType52,BGPOSFPTrapTypes.getbgposfpTrapType("72"));
		Assert.assertEquals(netscreenTrapType53,BGPOSFPTrapTypes.getbgposfpTrapType("73"));
		Assert.assertEquals(netscreenTrapType54,BGPOSFPTrapTypes.getbgposfpTrapType("74"));
		Assert.assertEquals(netscreenTrapType55,BGPOSFPTrapTypes.getbgposfpTrapType("75"));
		Assert.assertEquals(netscreenTrapType56,BGPOSFPTrapTypes.getbgposfpTrapType("76"));
		Assert.assertEquals(netscreenTrapType57,BGPOSFPTrapTypes.getbgposfpTrapType("77"));
		Assert.assertEquals(netscreenTrapType58,BGPOSFPTrapTypes.getbgposfpTrapType("78"));
		Assert.assertEquals(netscreenTrapType59,BGPOSFPTrapTypes.getbgposfpTrapType("79"));
		Assert.assertEquals(netscreenTrapType60,BGPOSFPTrapTypes.getbgposfpTrapType("80"));
		Assert.assertEquals(netscreenTrapType61,BGPOSFPTrapTypes.getbgposfpTrapType("81"));
		Assert.assertEquals(netscreenTrapType62,BGPOSFPTrapTypes.getbgposfpTrapType("82"));
		Assert.assertEquals(netscreenTrapType63,BGPOSFPTrapTypes.getbgposfpTrapType("83"));
		Assert.assertEquals(netscreenTrapType64,BGPOSFPTrapTypes.getbgposfpTrapType("84"));
		Assert.assertEquals(netscreenTrapType65,BGPOSFPTrapTypes.getbgposfpTrapType("85"));
		Assert.assertEquals(netscreenTrapType66,BGPOSFPTrapTypes.getbgposfpTrapType("86"));
		Assert.assertEquals(netscreenTrapType67,BGPOSFPTrapTypes.getbgposfpTrapType("87"));
		Assert.assertEquals(netscreenTrapType68,BGPOSFPTrapTypes.getbgposfpTrapType("88"));
		Assert.assertEquals(netscreenTrapType69,BGPOSFPTrapTypes.getbgposfpTrapType("89"));
		Assert.assertEquals(netscreenTrapType70,BGPOSFPTrapTypes.getbgposfpTrapType("90"));
		Assert.assertEquals(netscreenTrapType71,BGPOSFPTrapTypes.getbgposfpTrapType("93"));
		Assert.assertEquals(netscreenTrapType72,BGPOSFPTrapTypes.getbgposfpTrapType("95"));
		Assert.assertEquals(netscreenTrapType73,BGPOSFPTrapTypes.getbgposfpTrapType("96"));
		Assert.assertEquals(netscreenTrapType74,BGPOSFPTrapTypes.getbgposfpTrapType("101"));
		Assert.assertEquals(netscreenTrapType75,BGPOSFPTrapTypes.getbgposfpTrapType("102"));
		Assert.assertEquals(netscreenTrapType76,BGPOSFPTrapTypes.getbgposfpTrapType("103"));
		Assert.assertEquals(netscreenTrapType77,BGPOSFPTrapTypes.getbgposfpTrapType("205"));
		Assert.assertEquals(netscreenTrapType78,BGPOSFPTrapTypes.getbgposfpTrapType("206"));
		Assert.assertEquals(netscreenTrapType79,BGPOSFPTrapTypes.getbgposfpTrapType("207"));
		Assert.assertEquals(netscreenTrapType80,BGPOSFPTrapTypes.getbgposfpTrapType("208"));
		Assert.assertEquals(netscreenTrapType81,BGPOSFPTrapTypes.getbgposfpTrapType("209"));
		Assert.assertEquals(netscreenTrapType82,BGPOSFPTrapTypes.getbgposfpTrapType("210"));
		Assert.assertEquals(netscreenTrapType83,BGPOSFPTrapTypes.getbgposfpTrapType("211"));
		Assert.assertEquals(netscreenTrapType84,BGPOSFPTrapTypes.getbgposfpTrapType("212"));
		Assert.assertEquals(netscreenTrapType85,BGPOSFPTrapTypes.getbgposfpTrapType("213"));
		Assert.assertEquals(netscreenTrapType86,BGPOSFPTrapTypes.getbgposfpTrapType("214"));
		Assert.assertEquals(netscreenTrapType87,BGPOSFPTrapTypes.getbgposfpTrapType("215"));
		Assert.assertEquals(netscreenTrapType88,BGPOSFPTrapTypes.getbgposfpTrapType("216"));
		Assert.assertEquals(netscreenTrapType89,BGPOSFPTrapTypes.getbgposfpTrapType("217"));
		Assert.assertEquals(netscreenTrapType90,BGPOSFPTrapTypes.getbgposfpTrapType("218"));
		Assert.assertEquals(netscreenTrapType91,BGPOSFPTrapTypes.getbgposfpTrapType("219"));
		Assert.assertEquals(netscreenTrapType92,BGPOSFPTrapTypes.getbgposfpTrapType("220"));
		Assert.assertEquals(netscreenTrapType93,BGPOSFPTrapTypes.getbgposfpTrapType("221"));
		Assert.assertEquals(netscreenTrapType94,BGPOSFPTrapTypes.getbgposfpTrapType("222"));
		Assert.assertEquals(netscreenTrapType95,BGPOSFPTrapTypes.getbgposfpTrapType("223"));
		Assert.assertEquals(netscreenTrapType96,BGPOSFPTrapTypes.getbgposfpTrapType("224"));
		Assert.assertEquals(netscreenTrapType97,BGPOSFPTrapTypes.getbgposfpTrapType("225"));
		Assert.assertEquals(netscreenTrapType98,BGPOSFPTrapTypes.getbgposfpTrapType("400"));
		Assert.assertEquals(netscreenTrapType99,BGPOSFPTrapTypes.getbgposfpTrapType("401"));
		Assert.assertEquals(netscreenTrapType100,BGPOSFPTrapTypes.getbgposfpTrapType("402"));
		Assert.assertEquals(netscreenTrapType101,BGPOSFPTrapTypes.getbgposfpTrapType("403"));
		Assert.assertEquals(netscreenTrapType102,BGPOSFPTrapTypes.getbgposfpTrapType("404"));
		Assert.assertEquals(netscreenTrapType103,BGPOSFPTrapTypes.getbgposfpTrapType("405"));
		Assert.assertEquals(netscreenTrapType104,BGPOSFPTrapTypes.getbgposfpTrapType("406"));
		Assert.assertEquals(netscreenTrapType105,BGPOSFPTrapTypes.getbgposfpTrapType("407"));
		Assert.assertEquals(netscreenTrapType106,BGPOSFPTrapTypes.getbgposfpTrapType("408"));
		Assert.assertEquals(netscreenTrapType107,BGPOSFPTrapTypes.getbgposfpTrapType("409"));
		Assert.assertEquals(netscreenTrapType108,BGPOSFPTrapTypes.getbgposfpTrapType("410"));
		Assert.assertEquals(netscreenTrapType109,BGPOSFPTrapTypes.getbgposfpTrapType("411"));
		Assert.assertEquals(netscreenTrapType110,BGPOSFPTrapTypes.getbgposfpTrapType("412"));
		Assert.assertEquals(netscreenTrapType111,BGPOSFPTrapTypes.getbgposfpTrapType("413"));
		Assert.assertEquals(netscreenTrapType112,BGPOSFPTrapTypes.getbgposfpTrapType("414"));
		Assert.assertEquals(netscreenTrapType113,BGPOSFPTrapTypes.getbgposfpTrapType("415"));
		Assert.assertEquals(netscreenTrapType114,BGPOSFPTrapTypes.getbgposfpTrapType("430"));
		Assert.assertEquals(netscreenTrapType115,BGPOSFPTrapTypes.getbgposfpTrapType("431"));
		Assert.assertEquals(netscreenTrapType116,BGPOSFPTrapTypes.getbgposfpTrapType("432"));
		Assert.assertEquals(netscreenTrapType117,BGPOSFPTrapTypes.getbgposfpTrapType("433"));
		Assert.assertEquals(netscreenTrapType118,BGPOSFPTrapTypes.getbgposfpTrapType("434"));
		Assert.assertEquals(netscreenTrapType119,BGPOSFPTrapTypes.getbgposfpTrapType("435"));
		Assert.assertEquals(netscreenTrapType120,BGPOSFPTrapTypes.getbgposfpTrapType("436"));
		Assert.assertEquals(netscreenTrapType121,BGPOSFPTrapTypes.getbgposfpTrapType("437"));
		Assert.assertEquals(netscreenTrapType122,BGPOSFPTrapTypes.getbgposfpTrapType("438"));
		Assert.assertEquals(netscreenTrapType123,BGPOSFPTrapTypes.getbgposfpTrapType("439"));
		Assert.assertEquals(netscreenTrapType124,BGPOSFPTrapTypes.getbgposfpTrapType("440"));
		Assert.assertEquals(netscreenTrapType125,BGPOSFPTrapTypes.getbgposfpTrapType("441"));
		Assert.assertEquals(netscreenTrapType126,BGPOSFPTrapTypes.getbgposfpTrapType("800"));
		Assert.assertEquals(netscreenTrapType127,BGPOSFPTrapTypes.getbgposfpTrapType("801"));
		Assert.assertEquals(netscreenTrapType128,BGPOSFPTrapTypes.getbgposfpTrapType("802"));
		Assert.assertEquals(netscreenTrapType129,BGPOSFPTrapTypes.getbgposfpTrapType("803"));
		Assert.assertEquals(netscreenTrapType130,BGPOSFPTrapTypes.getbgposfpTrapType("804"));
		
		


		
		

		
		
		
		
	}

}
