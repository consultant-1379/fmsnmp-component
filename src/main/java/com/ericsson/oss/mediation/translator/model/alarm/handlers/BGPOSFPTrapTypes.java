package com.ericsson.oss.mediation.translator.model.alarm.handlers;

public class BGPOSFPTrapTypes {

	private BGPOSFPTrapTypes() {

	}

	// NETSCREEN NETSCREEN _BGP_OSPF_MIBS: bgposfpTrapType
	public static String getbgposfpTrapType(final String Value) {
		String netscreenTrapType = null;

		switch (Integer.valueOf(Value).intValue()) {
		case 1:
			netscreenTrapType = "traffic-sec";
			break;
		case 2:
			netscreenTrapType = "traffic-min";
			break;
		case 4:
			netscreenTrapType = "winnuke";
			break;
		case 5:
			netscreenTrapType = "syn-attack";
			break;
		case 6:
			netscreenTrapType = "tear-drop";
			break;
		case 7:
			netscreenTrapType = "ping-death";
			break;
		case 8:
			netscreenTrapType = "ip-spoofing";
			break;
		case 9:
			netscreenTrapType = "ip-src-route";
			break;
		case 10:
			netscreenTrapType = "land";
			break;
		case 11:
			netscreenTrapType = "icmp-flood";
			break;
		case 12:
			netscreenTrapType = "udp-flood";
			break;
		case 13:
			netscreenTrapType = "illegal-cms-svr";
			break;
		case 14:
			netscreenTrapType = "url-block-srv";
			break;
		case 16:
			netscreenTrapType = "port-scan";
			break;
		case 17:
			netscreenTrapType = "addr-sweep";
			break;
		case 20:
			netscreenTrapType = "low-memory";
			break;
		case 21:
			netscreenTrapType = "dns-srv-down";
			break;
		case 22:
			netscreenTrapType = "generic-HW-fail";
			break;
		case 23:
			netscreenTrapType = "lb-srv-down";
			break;
		case 24:
			netscreenTrapType = "log-full";
			break;
		case 25:
			netscreenTrapType = "x509";
			break;
		case 26:
			netscreenTrapType = "vpn-ike";
			break;
		case 27:
			netscreenTrapType = "admin";
			break;
		case 28:
			netscreenTrapType = "sme";
			break;
		case 29:
			netscreenTrapType = "dhcp";
			break;
		case 30:
			netscreenTrapType = "cpu-usage-high";
			break;
		case 31:
			netscreenTrapType = "ip-conflict";
			break;
		case 32:
			netscreenTrapType = "attact-malicious-url";
			break;
		case 33:
			netscreenTrapType = "session-threshold";
			break;
		case 34:
			netscreenTrapType = "ssh-alarm";
			break;
		case 40:
			netscreenTrapType = "vpn-tunnel-up";
			break;
		case 41:
			netscreenTrapType = "vpn-tunnel-down";
			break;
		case 42:
			netscreenTrapType = "vpn-replay-attack";
			break;
		case 43:
			netscreenTrapType = "vpn-l2tp-tunnel-remove";
			break;
		case 44:
			netscreenTrapType = "vpn-l2tp-tunnel-remove-err";
			break;
		case 45:
			netscreenTrapType = "vpn-l2tp-call-remove";
			break;
		case 46:
			netscreenTrapType = "vpn-l2tp-call-remove-err";
			break;
		case 47:
			netscreenTrapType = "vpn-ias-too-many";
			break;
		case 48:
			netscreenTrapType = "vpn-ias-over-threshold";
			break;
		case 49:
			netscreenTrapType = "vpn-ias-under-threshold";
			break;
		case 50:
			netscreenTrapType = "vpn-ias-ike-error";
			break;
		case 51:
			netscreenTrapType = "allocated-session-threshold";
			break;
		case 554:
			netscreenTrapType = "av-scan-mgr";
			break;
		case 60:
			netscreenTrapType = "nsrp-rto-up";
			break;
		case 61:
			netscreenTrapType = "nsrp-rto-down";
			break;
		case 62:
			netscreenTrapType = "nsrp-trackip-success";
			break;
		case 63:
			netscreenTrapType = "nsrp-trackip-failed";
			break;
		case 64:
			netscreenTrapType = "nsrp-trackip-failover";
			break;
		case 65:
			netscreenTrapType = "nsrp-inconsistent-configuration";
			break;
		case 70:
			netscreenTrapType = "nsrp-vsd-init";
			break;
		case 71:
			netscreenTrapType = "nsrp-vsd-master";
			break;
		case 72:
			netscreenTrapType = "nsrp-vsd-pbackup";
			break;
		case 73:
			netscreenTrapType = "nsrp-vsd-backup";
			break;
		case 74:
			netscreenTrapType = "nsrp-vsd-ineligible";
			break;
		case 75:
			netscreenTrapType = "nsrp-vsd-inoperable";
			break;
		case 76:
			netscreenTrapType = "nsrp-vsd-req-hearbeat-2nd";
			break;
		case 77:
			netscreenTrapType = "nsrp-vsd-reply-2nd";
			break;
		case 78:
			netscreenTrapType = "nsrp-rto-duplicated";
			break;
		case 79:
			netscreenTrapType = "dc-fail-reconnect-mc";
			break;
		case 80:
			netscreenTrapType = "mc-fail-reconnect-db";
			break;
		case 81:
			netscreenTrapType = "dc-fail-init";
			break;
		case 82:
			netscreenTrapType = "mc-fail-init";
			break;
		case 83:
			netscreenTrapType = "unknown-connect-attempt-dc";
			break;
		case 84:
			netscreenTrapType = "dc-reinit";
			break;
		case 85:
			netscreenTrapType = "mc-reinit";
			break;
		case 86:
			netscreenTrapType = "dc-fail-auth";
			break;
		case 87:
			netscreenTrapType = "dc-mc-version-unmatch";
			break;
		case 88:
			netscreenTrapType = "dc-log-full";
			break;
		case 89:
			netscreenTrapType = "device-connect-dc";
			break;
		case 90:
			netscreenTrapType = "device-disconnect-dc";
			break;
		case 93:
			netscreenTrapType = "usb-device-operation";
			break;
		case 95:
			netscreenTrapType = "ppp-no-ip-cfg";
			break;
		case 96:
			netscreenTrapType = "ppp-no-ip-in-pool";
			break;
		case 101:
			netscreenTrapType = "ipv6-conflict";
			break;
		case 102:
			netscreenTrapType = "dip-util-raise";
			break;
		case 103:
			netscreenTrapType = "dip-util-clear";
			break;
		case 205:
			netscreenTrapType = "route-alarm";
			break;
		case 206:
			netscreenTrapType = "ospf-flood";
			break;
		case 207:
			netscreenTrapType = "rip-flood";
			break;
		case 208:
			netscreenTrapType = "bgp-established";
			break;
		case 209:
			netscreenTrapType = "bgp-backwardtransition";
			break;
		case 210:
			netscreenTrapType = "ospf-virtifstatechange";
			break;
		case 211:
			netscreenTrapType = "ospf-nbrstatechange";
			break;
		case 212:
			netscreenTrapType = "ospf-virtnbrstatechange";
			break;
		case 213:
			netscreenTrapType = "ospf-ifconfigerror";
			break;
		case 214:
			netscreenTrapType = "ospf-virtifconfigerror";
			break;
		case 215:
			netscreenTrapType = "ospf-ifauthfailure";
			break;
		case 216:
			netscreenTrapType = "ospf-virtifauthfailure";
			break;
		case 217:
			netscreenTrapType = "ospf-ifrxbadpacket";
			break;
		case 218:
			netscreenTrapType = "ospf-virtifrxbadpacket";
			break;
		case 219:
			netscreenTrapType = "ospf-txretransmit";
			break;
		case 220:
			netscreenTrapType = "ospf-virtiftxretransmit";
			break;
		case 221:
			netscreenTrapType = "ospf-originatelsa";
			break;
		case 222:
			netscreenTrapType = "ospf-maxagelsa";
			break;
		case 223:
			netscreenTrapType = "ospf-lsdboverflow";
			break;
		case 224:
			netscreenTrapType = "ospf-lsdbapproachingoverflow";
			break;
		case 225:
			netscreenTrapType = "ospf-ifstatechange";
			break;
		case 400:
			netscreenTrapType = "ids-component";
			break;
		case 401:
			netscreenTrapType = "ids-icmp-flood";
			break;
		case 402:
			netscreenTrapType = "ids-udp-flood";
			break;
		case 403:
			netscreenTrapType = "ids-winnuke";
			break;
		case 404:
			netscreenTrapType = "ids-port-scan";
			break;
		case 405:
			netscreenTrapType = "ids-addr-sweep";
			break;
		case 406:
			netscreenTrapType = "ids-tear-drop";
			break;
		case 407:
			netscreenTrapType = "ids-syn";
			break;
		case 408:
			netscreenTrapType = "ids-ip-spoofing";
			break;
		case 409:
			netscreenTrapType = "ids-ping-death";
			break;
		case 410:
			netscreenTrapType = "ids-ip-source-route";
			break;
		case 411:
			netscreenTrapType = "ids-land";
			break;
		case 412:
			netscreenTrapType = "syn-frag-attack";
			break;
		case 413:
			netscreenTrapType = "tcp-without-flag";
			break;
		case 414:
			netscreenTrapType = "unknow-ip-packet";
			break;
		case 415:
			netscreenTrapType = "bad-ip-option";
			break;
		case 430:
			netscreenTrapType = "dst-ip-session-limit";
			break;
		case 431:
			netscreenTrapType = "ids-block-zip";
			break;
		case 432:
			netscreenTrapType = "ids-block-jar";
			break;
		case 433:
			netscreenTrapType = "ids-block-exe";
			break;
		case 434:
			netscreenTrapType = "ids-block-activex";
			break;
		case 435:
			netscreenTrapType = "icmp-fragment";
			break;
		case 436:
			netscreenTrapType = "too-large-icmp";
			break;
		case 437:
			netscreenTrapType = "tcp-syn-fin";
			break;
		case 438:
			netscreenTrapType = "tcp-fin-no-ack";
			break;
		case 439:
			netscreenTrapType = "ids-tcp-syn-ack-ack";
			break;
		case 440:
			netscreenTrapType = "ids-ip-block-frag";
			break;
		case 441:
			netscreenTrapType = "ids-icmp-ping-id-zero";
			break;
		case 800:
			netscreenTrapType = "cpu-limit-s2f-forced";
			break;
		case 801:
			netscreenTrapType = "cpu-limit-s2f-auto";
			break;
		case 802:
			netscreenTrapType = "cpu-limit-f2s-forced";
			break;
		case 803:
			netscreenTrapType = "cpu-limit-f2s-timeout";
			break;
		case 804:
			netscreenTrapType = "cpu-limit-f2s-auto";
			break;
		default:
			break;
		}
		return netscreenTrapType;
	}
}
