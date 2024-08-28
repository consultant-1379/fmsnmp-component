package com.ericsson.oss.mediation.translator.model;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.ericsson.oss.mediation.translator.constant.TranslationConstant;

import com.ericsson.oss.mediation.translator.model.EventNotification;


public class HandleAllOtherAlarmAttribute {
	
	HandleAllOtherAlarmAttribute(){
		
	}

	private static final Logger LOGGER = LoggerFactory.getLogger(HandleAllOtherAlarmAttribute.class);
	public static Boolean perfdegraded = false;
	public static StringBuilder problemTextString=new StringBuilder();
	public static boolean TrapTypeflag=false;
	private static final Map<String, String> accCauseMap = new HashMap<String, String>();
	private static final Map<String, String> enumResultMap = new HashMap<String, String>();
	private static final Map<String, String> enumStatusMap = new HashMap<String, String>();
	private static final Map<String, String> enumCauseMap = new HashMap<String, String>();
	private static final Map<String, String> InsVrBgpPeerState = new HashMap<String, String>();
	private static final Map<String, String> bgposfpTrapType = new HashMap<String, String>();
	private static final Map<String, String> ifAdminState = new HashMap<String, String>();
	private static final Map<String, String> ifOperStatus = new HashMap<String, String>();
	private static final Map<String, String> funkSbrTrapVarCompmap = new HashMap<String, String>();
	private static final Map<String, String> txtVarApEnvMonTrapPreviousStateMap = new HashMap<String, String>();
	private static final Map<String, String> txtVarApEnvMonTrapCurrentStateMap = new HashMap<String, String>();
	static{
		txtVarApEnvMonTrapPreviousStateMap.put("1",TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("initial(1)."));
		txtVarApEnvMonTrapPreviousStateMap.put("2", TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("normal(2)."));
		txtVarApEnvMonTrapPreviousStateMap.put("3",TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("minor(3)."));
		txtVarApEnvMonTrapPreviousStateMap.put("4", TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("major(4)."));
		txtVarApEnvMonTrapPreviousStateMap.put("5",TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("critical(5)."));
		txtVarApEnvMonTrapPreviousStateMap.put("6",  TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("shutdown(6)."));
		txtVarApEnvMonTrapPreviousStateMap.put("7",TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("notPresent(7)."));
		txtVarApEnvMonTrapPreviousStateMap.put("8", TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("notFunctioning(8)."));
		txtVarApEnvMonTrapPreviousStateMap.put("9", TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("unknown(9)."));
		
		txtVarApEnvMonTrapCurrentStateMap.put("1",TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("initial(1)."));
		txtVarApEnvMonTrapCurrentStateMap.put("2", TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("normal(2)."));
		txtVarApEnvMonTrapCurrentStateMap.put("3",TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("minor(3)."));
		txtVarApEnvMonTrapCurrentStateMap.put("4", TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("major(4)."));
		txtVarApEnvMonTrapCurrentStateMap.put("5",TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("critical(5)."));
		txtVarApEnvMonTrapCurrentStateMap.put("6",  TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("shutdown(6)."));
		txtVarApEnvMonTrapCurrentStateMap.put("7",TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("notPresent(7)."));
		txtVarApEnvMonTrapCurrentStateMap.put("8", TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("notFunctioning(8)."));
		txtVarApEnvMonTrapCurrentStateMap.put("9", TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("unknown(9)."));

		funkSbrTrapVarCompmap.put("1", HandleAllOtherAlarmAttribute.TrapVarComp.concat(" core(1)."));
		funkSbrTrapVarCompmap.put("2", HandleAllOtherAlarmAttribute.TrapVarComp.concat(" accounting(2)."));
		funkSbrTrapVarCompmap.put("3", HandleAllOtherAlarmAttribute.TrapVarComp.concat(" authentication(3)."));
		accCauseMap.put("1","0");
		accCauseMap.put("2","570");
		accCauseMap.put("3","5");
		accCauseMap.put("4","6");
		accCauseMap.put("5","7");
		accCauseMap.put("6","8");
		accCauseMap.put("7","9");
		accCauseMap.put("8","11");
		accCauseMap.put("9","325");
		accCauseMap.put("10","313");
		accCauseMap.put("11","0");
		accCauseMap.put("12","517");
		accCauseMap.put("13","59");
		accCauseMap.put("14","58");
		accCauseMap.put("15","55");
		accCauseMap.put("16","315");
		accCauseMap.put("17","151");
		accCauseMap.put("18","152");
		accCauseMap.put("19","153");
		accCauseMap.put("20","332");
		accCauseMap.put("21","357");
		accCauseMap.put("22","346");
		accCauseMap.put("23","348");
		accCauseMap.put("24","347");
		accCauseMap.put("25","308");
		accCauseMap.put("26","344");
		enumResultMap.put("1", "ok(1).");
		enumResultMap.put("2", "notOk(2).");
		enumResultMap.put("49", "unknown(49).");
		enumResultMap.put("50", "unknown(50).");
		enumStatusMap.put("1",  "working(1).");
		enumStatusMap.put("2",  "notWorking(2).");
		enumStatusMap.put("3",  "testing(3).");
		enumStatusMap.put("49",  "unknown(49).");
		enumStatusMap.put("50",  "unused(50).");
		enumCauseMap.put("1", "processorInformation(1).");
		enumCauseMap.put("2", "systemInformation(2).");
		enumCauseMap.put("3", "softwareInformation(3).");
		enumCauseMap.put("4", "ispReport(4).");
		enumCauseMap.put("10", "securityInformation(10).");
		enumCauseMap.put("11", "performanceMeasurement(11).");
		enumCauseMap.put("12", "logInformation(12).");
		enumCauseMap.put("13", "statusChangeInformation(13).");
		enumCauseMap.put("14", "linkInformation(14).");
		enumCauseMap.put("15", "snmpInformation(15).");
		enumCauseMap.put("20", "networkSyncInformation(20).");
		enumCauseMap.put("21", "testResult(21).");
		enumCauseMap.put("22", "interfaceInformation(22).");
		enumCauseMap.put("23", "connectionInformation(23).");
		enumCauseMap.put("24", "equipmentInformation(24).");
		enumCauseMap.put("25", "labelSwitchInformation(25).");
		enumCauseMap.put("49", "unknown(49).");
		enumCauseMap.put("49", "unused(50).");

		InsVrBgpPeerState.put("1", "idle");
		InsVrBgpPeerState.put("2", "connect");
		InsVrBgpPeerState.put("3", "active");
		InsVrBgpPeerState.put("4", "opensent");
		InsVrBgpPeerState.put("5", "openconfirm");
		InsVrBgpPeerState.put("6", "established");



		bgposfpTrapType.put("1","traffic-sec");
		bgposfpTrapType.put("2","traffic-min");   
		bgposfpTrapType.put("4","winnuke");
		bgposfpTrapType.put("5","syn-attack");
		bgposfpTrapType.put("6","tear-drop");
		bgposfpTrapType.put("7","ping-death");
		bgposfpTrapType.put("8","ip-spoofing");
		bgposfpTrapType.put("9","ip-src-route");
		bgposfpTrapType.put("10","land");
		bgposfpTrapType.put("11","icmp-flood");
		bgposfpTrapType.put("12","udp-flood");
		bgposfpTrapType.put("13","illegal-cms-svr");
		bgposfpTrapType.put("14","url-block-srv");
		bgposfpTrapType.put("16","port-scan");
		bgposfpTrapType.put("17","addr-sweep");
		bgposfpTrapType.put("20","low-memory");
		bgposfpTrapType.put("21","dns-srv-down");
		bgposfpTrapType.put("22","generic-HW-fail"); 
		bgposfpTrapType.put("23","lb-srv-down"); 
		bgposfpTrapType.put("24","log-full"); 
		bgposfpTrapType.put("25","x509"); 
		bgposfpTrapType.put("26","vpn-ike"); 
		bgposfpTrapType.put("27","admin"); 
		bgposfpTrapType.put("28","sme"); 
		bgposfpTrapType.put("29","dhcp"); 
		bgposfpTrapType.put("30","cpu-usage-high"); 
		bgposfpTrapType.put("31","ip-conflict"); 
		bgposfpTrapType.put("32","attact-malicious-url"); 
		bgposfpTrapType.put("33","session-threshold"); 
		bgposfpTrapType.put("34","ssh-alarm"); 
		bgposfpTrapType.put("40","vpn-tunnel-up");
		bgposfpTrapType.put("41","vpn-tunnel-down"); 
		bgposfpTrapType.put("42","vpn-replay-attack"); 
		bgposfpTrapType.put("43","vpn-l2tp-tunnel-remove"); 
		bgposfpTrapType.put("44","vpn-l2tp-tunnel-remove-err"); 
		bgposfpTrapType.put("45","vpn-l2tp-call-remove"); 
		bgposfpTrapType.put("46","vpn-l2tp-call-remove-err"); 
		bgposfpTrapType.put("47","vpn-ias-too-many"); 
		bgposfpTrapType.put("48","vpn-ias-over-threshold"); 
		bgposfpTrapType.put("49","vpn-ias-under-threshold"); 
		bgposfpTrapType.put("50","vpn-ias-ike-error"); 
		bgposfpTrapType.put("51","allocated-session-threshold"); 
		bgposfpTrapType.put("554","av-scan-mgr"); 
		bgposfpTrapType.put("60","nsrp-rto-up"); 
		bgposfpTrapType.put("61","nsrp-rto-down"); 
		bgposfpTrapType.put("62","nsrp-trackip-success"); 
		bgposfpTrapType.put("63","nsrp-trackip-failed"); 
		bgposfpTrapType.put("64","nsrp-trackip-failover"); 
		bgposfpTrapType.put("65","nsrp-inconsistent-configuration"); 
		bgposfpTrapType.put("70","nsrp-vsd-init"); 
		bgposfpTrapType.put("71","nsrp-vsd-master"); 
		bgposfpTrapType.put("72","nsrp-vsd-pbackup"); 
		bgposfpTrapType.put("73","nsrp-vsd-backup"); 
		bgposfpTrapType.put("74","nsrp-vsd-ineligible"); 
		bgposfpTrapType.put("75","nsrp-vsd-inoperable"); 
		bgposfpTrapType.put("76","nsrp-vsd-req-hearbeat-2nd"); 
		bgposfpTrapType.put("77","nsrp-vsd-reply-2nd"); 
		bgposfpTrapType.put("78","nsrp-rto-duplicated"); 
		bgposfpTrapType.put("79","dc-fail-reconnect-mc"); 
		bgposfpTrapType.put("80","mc-fail-reconnect-db"); 
		bgposfpTrapType.put("81","dc-fail-init"); 
		bgposfpTrapType.put("82","mc-fail-init"); 
		bgposfpTrapType.put("83","unknown-connect-attempt-dc"); 
		bgposfpTrapType.put("84","dc-reinit"); 
		bgposfpTrapType.put("85","mc-reinit"); 
		bgposfpTrapType.put("86","dc-fail-auth"); 
		bgposfpTrapType.put("87","dc-mc-version-unmatch"); 
		bgposfpTrapType.put("88","dc-log-full"); 
		bgposfpTrapType.put("89","device-connect-dc"); 
		bgposfpTrapType.put("90","device-disconnect-dc"); 
		bgposfpTrapType.put("93","usb-device-operation"); 
		bgposfpTrapType.put("95","ppp-no-ip-cfg"); 
		bgposfpTrapType.put("96","ppp-no-ip-in-pool"); 
		bgposfpTrapType.put("101","ipv6-conflict"); 
		bgposfpTrapType.put("102","dip-util-raise");
		bgposfpTrapType.put("103","dip-util-clear");
		bgposfpTrapType.put("205","route-alarm");
		bgposfpTrapType.put("206","ospf-flood");
		bgposfpTrapType.put("207","rip-flood");
		bgposfpTrapType.put("208","bgp-established");
		bgposfpTrapType.put("209","bgp-backwardtransition");
		bgposfpTrapType.put("210","ospf-virtifstatechange");

		bgposfpTrapType.put("211","ospf-nbrstatechange");
		bgposfpTrapType.put("212","ospf-virtnbrstatechange");
		bgposfpTrapType.put("213","ospf-ifconfigerror");
		bgposfpTrapType.put("214","bgp-established");
		bgposfpTrapType.put("215","bgp-backwardtransition");
		bgposfpTrapType.put("216","ospf-virtifstatechange");

		bgposfpTrapType.put("217","ospf-ifrxbadpacket");
		bgposfpTrapType.put("218","ospf-virtifrxbadpacket");
		bgposfpTrapType.put("219","ospf-txretransmit");
		bgposfpTrapType.put("220","ospf-virtiftxretransmit");
		bgposfpTrapType.put("221","ospf-originatelsa");
		bgposfpTrapType.put("222","ospf-maxagelsa");

		bgposfpTrapType.put("223","ospf-lsdboverflow");
		bgposfpTrapType.put("224","ospf-lsdbapproachingoverflow");
		bgposfpTrapType.put("225","ospf-ifstatechange");
		bgposfpTrapType.put("400","ids-component");
		bgposfpTrapType.put("401","ids-udp-flood");
		bgposfpTrapType.put("402","ids-udp-flood");

		bgposfpTrapType.put("403","ids-winnuke");
		bgposfpTrapType.put("404","ids-port-scan");
		bgposfpTrapType.put("405","ids-addr-sweep");
		bgposfpTrapType.put("406","ids-tear-drop");
		bgposfpTrapType.put("407","ids-syn");
		bgposfpTrapType.put("408","ids-ip-spoofing");

		bgposfpTrapType.put("409","ids-ping-death");
		bgposfpTrapType.put("410","ids-ip-source-route");
		bgposfpTrapType.put("411","ids-land");
		bgposfpTrapType.put("412","syn-frag-attack");
		bgposfpTrapType.put("413","tcp-without-flag");
		bgposfpTrapType.put("414","unknow-ip-packet");


		bgposfpTrapType.put("415","bad-ip-option");
		bgposfpTrapType.put("430","dst-ip-session-limit");
		bgposfpTrapType.put("431","ids-block-zip");
		bgposfpTrapType.put("432","ids-block-jar");
		bgposfpTrapType.put("433","ids-block-exe");
		bgposfpTrapType.put("434","ids-block-activex");

		bgposfpTrapType.put("435","icmp-fragment");
		bgposfpTrapType.put("436","too-large-icmp");
		bgposfpTrapType.put("437","tcp-syn-fin");
		bgposfpTrapType.put("438","tcp-fin-no-ack");
		bgposfpTrapType.put("439","ids-tcp-syn-ack-ack");
		bgposfpTrapType.put("440","ids-ip-block-frag");

		bgposfpTrapType.put("441","ids-icmp-ping-id-zero");
		bgposfpTrapType.put("800","cpu-limit-s2f-forced");
		bgposfpTrapType.put("801","cpu-limit-s2f-auto");
		bgposfpTrapType.put("802","cpu-limit-f2s-forced");
		bgposfpTrapType.put("803","cpu-limit-f2s-timeout");
		bgposfpTrapType.put("804","cpu-limit-f2s-auto");

		ifAdminState.put("1", HandleAllOtherAlarmAttribute.TrapVarComp.concat(" core(1)."));
		ifAdminState.put("2", HandleAllOtherAlarmAttribute.TrapVarComp.concat(" accounting(2)."));
		ifAdminState.put("3", HandleAllOtherAlarmAttribute.TrapVarComp.concat(" authentication(3)."));

		ifOperStatus.put("1", HandleAllOtherAlarmAttribute.IfOperState.concat("up(1)."));
		ifOperStatus.put("2", HandleAllOtherAlarmAttribute.IfOperState.concat("down(2)."));
		ifOperStatus.put("3", HandleAllOtherAlarmAttribute.IfOperState.concat("testing(3)."));
		ifOperStatus.put("4", HandleAllOtherAlarmAttribute.IfOperState.concat("unknown(4)."));
		ifOperStatus.put("5", HandleAllOtherAlarmAttribute.IfOperState.concat("dormant(5) ."));
		ifOperStatus.put("6", HandleAllOtherAlarmAttribute.IfOperState.concat("notPresent(6)."));
		ifOperStatus.put("7", HandleAllOtherAlarmAttribute.IfOperState.concat("lowerLayerDown(7)."));
	}

	
	// SRD:srdNotificationAddText
	public static String getsrdNotificationAddText(final String Value) {
		String psstr = "";
		try {
			final String str1 = Value.toString();
			LOGGER.info("Additional Text for SUNPLATFORM MIB is " + str1);
			final StringTokenizer sunstr = new StringTokenizer(str1, "/");
			while (sunstr.hasMoreTokens()) {
				final String ps = sunstr.nextToken();

				if (ps.startsWith("PS")) {
					psstr = "PS=" + ps;

				}
			}
			LOGGER.info("Power Supply Info for SUNPLATFORM MIB is ",psstr);

		}

		catch (Exception e) {
			LOGGER.info("No power supply additional text/Problem in processing powersupply info",e);
			return psstr;
		}
		return psstr;
	}
	////ESA-SNF:SNFTrapPDU
	@SuppressWarnings("deprecation")
	public static EventNotification handleSNFTrapPDU(EventNotification notif,String Name,String Value,String fdn){
		//Declaration and Initialization
		//StringBuffer str = new StringBuffer("");
		StringBuilder str = new StringBuilder("");
		String moi1 = "", moi2 = "", moi3 = "", moifinal = "";
		//st = new StringTokenizer(oid2.trim(), ".");
		//Processing 1.AlarmModelDescription
		if (Name.equalsIgnoreCase("alarmModelDescription")) {
			try {
				String amodel = Value.toString();
				LOGGER.debug("1.Alarm Model Description received from the Node : "
						+ amodel);

				notif.setSpecificProblem(amodel);

				//2. Processing alarmListName and setting it into ObjectOfReference & Problem Text

				//Looping is performed at the first index of Alarm List name to find out the value of Alarmlistname

				// Get the aalrm List name
				//String restOfIndex = oid2.substring(alarmModelDescription.length());
				String restOfIndex = alarmModelDescription;

				StringTokenizer inTok = new StringTokenizer(restOfIndex,
						".");

				// Get the count of characters in alarm name
				int countAlName = Integer.parseInt(inTok.nextToken());

				//iterate for the characters of AlarmName
				StringBuffer alarmNameBuf = new StringBuffer();

				for (int alnameIndex = 0; alnameIndex < countAlName; ++alnameIndex) {
					try {
						alarmNameBuf.append((char) (Integer.parseInt(inTok
								.nextToken())));
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}

				LOGGER.debug("2.Alarm List Name is : ", alarmNameBuf);
				moi1 = alarmNameBuf.toString();

				//Alarm List Name is mapped to Problem Text
				str
				.append("\nAlarm List Name = "
						+ alarmNameBuf.toString());

				String alarmModelIndex = "";

				//alarmModelIndex
				if (inTok.hasMoreTokens()) {
					alarmModelIndex = inTok.nextToken();
				}

				moi2 = alarmModelIndex;
				/**
				 * Taken care of  of displaying the Error Code.
				 */
				LOGGER.debug("3.Alarm Model Index is : ",alarmModelIndex);
				str.append("\nAlarm Error Code=" + moi2);

				String alarmModelState = "";
				//alarmModelState
				if (inTok.hasMoreTokens())
					alarmModelState = inTok.nextToken();

				LOGGER.debug("4.Alarm Model State is : ", alarmModelState);
				str.append("\nAlarm Model State = " + alarmModelState);

			} catch (Exception aln) {
				LOGGER.error("Exception while processing Alarm List Name",aln);
				return notif;
			}

		}

		//5.Processing alarmActiveResourceID and Setting into External Event Agent ID
		if (Name.equalsIgnoreCase("alarmActiveResourceId")) {
			String aresource = Value.toString();
			LOGGER.debug("5.alarmActiveResourceID : ",aresource);
			str.append("\nAlarm Active ID = " + aresource);

			//Setting it to External Event Agent ID
			if (!(aresource == "")) {
				// Setting OOR based on ActiveAlarmResourceOID from XML file
				if (getResourceOIDName(aresource.substring(1,aresource.length() - 2),fdn) != null&& getResourceOIDName(aresource.substring(1, aresource.length()),fdn) != null){
					String ManagedObjectInstance="Component="+getResourceOIDName(aresource.substring(1, aresource.length() - 2),fdn)+ ",Component="+ getResourceOIDName(aresource
							.substring(1, aresource.length()),fdn);
					notif.setManagedObjectInstance(ManagedObjectInstance);
				}
			} else {
				LOGGER.debug("Alarm Active Resource ID is not received in the Trap PDU");
				//No External Event ID is set here
			}

			//Alarm Active Resource ID needs to  mapped to MOI
			moi3 = aresource;

			//6.Processing alarmActiveDateandTime
			LOGGER.debug("6.Processing Alarm Date and time : ");
			try {
				String restOfIndex = alarmActiveResourceId;
				//LOGGER.info("--- RestofIndex : "+restOfIndex);	

				// remove the alarm name component
				StringTokenizer inTok = new StringTokenizer(restOfIndex,
						".");

				// Get the count of characters in alarm name
				String stringOfCountAlname=inTok.nextToken();
				int countAlName = Integer.parseInt(stringOfCountAlname);
				int charCount=stringOfCountAlname.length();
				//iterate for the characters of AlarmName
				for (int alnameIndex = 0; alnameIndex < countAlName; ++alnameIndex) {
					String temp=inTok.nextToken();
					charCount=charCount+temp.length();
				}

				// Get the date component
				StringBuffer aldateBuf = new StringBuffer();

				int countDate = Integer.parseInt(inTok.nextToken());

				//Get the 11 Octet OID
				int octetDigitsCount = 2;
				if (countDate > 10)
					octetDigitsCount++;
				String dateStringExtracted=restOfIndex.substring(charCount+countAlName+1);
				LOGGER.debug("Date string extracted is ",dateStringExtracted);
				int sourceIndex = dateStringExtracted.indexOf("."+countDate+".")+1;
				int destinationIndex =dateStringExtracted.lastIndexOf(".");
				String octet11OID = dateStringExtracted.substring(sourceIndex+octetDigitsCount,destinationIndex);

				for (int alDateCount = 0; alDateCount < countDate; ++alDateCount) {
					aldateBuf.append((char) Integer.parseInt(inTok
							.nextToken()));
				}

				String octet28DateTime = aldateBuf.toString();
				LOGGER.debug("octet28DateTime:",octet28DateTime);

				// get the activeindexComponent
				String alactIndex = "";
				if (inTok.hasMoreTokens()) {
					alactIndex = inTok.nextToken();
				}

				String actvid = "";

				try {

					if (countDate == 28) {
						//LOGGER.info("--- Setting 28 Octets Date & Time Format ");
						Date octet28Date = HandleTimeTranslation
								.createAlarmDateAndTime(octet28DateTime,
										countDate);
						notif.setTime(new Date(HandleTimeTranslation._timeFormatter.format(octet28Date)).toString());
						notif.setTimeZone("UTC");
					}

					else if (countDate == 11) {
						LOGGER.debug("Setting 11  Date & Time Format ");
						Date octet11Date = HandleTimeTranslation
								.createAlarmDateAndTime(octet11OID);
						LOGGER.debug("--- Date from 11 octet input :"
								, HandleTimeTranslation
								.createAlarmDateAndTime(octet11OID));

						notif.setTime(new Date(HandleTimeTranslation._timeFormatter.format(octet11Date)).toString());
						notif.setTimeZone(HandleTimeTranslation
								.getTimeZone(octet11OID));
					}

					else {
						LOGGER.debug("Setting Current Date & Time Format ");
						notif.setTime(HandleTimeTranslation.getCurrentTime().toString());
					}
				} catch (Exception ae) {
					LOGGER.debug("ESATranslator::Date Format Exception: "
							, aldateBuf.toString(),ae);
					LOGGER.debug("ESATranslator::setTime(): "
							+ HandleTimeTranslation.getCurrentTime());
					//Exception thrown while processing Alarm Active Date and Time
					//Hence setting the Local Current Time
					notif.setTime(HandleTimeTranslation.getCurrentTime().toString());
					return notif;
				}

				//7.Processing alarmActiveIndex 
				//LOGGER.info("Processing alarmActiveIndex ");
				actvid = alactIndex;
				LOGGER.info("7.Alarm Active Index is : " + actvid);
				str.append("\nAlarm Active Index = " + actvid);

			} catch (Exception ex) {
				LOGGER.error("Exception while processing Alarm Active Date and Time",ex);
				//Exception thrown while processing this VARBIND
				//Setting Date to local current Time
				notif.setTime(HandleTimeTranslation.getCurrentTime().toString());
				LOGGER.error("Exception while processing Alarm Active Index and hence not setting the value in ALV");
				return notif;

			}

		}

		//8.Processing Alarm Active Description and setting it to Problem Text in ALV
		if (Name.equalsIgnoreCase("alarmActiveDescription")) {
			str.append("\nAlarm Active Description = "
					+ Value.toString());
			LOGGER.debug("8.Alarm Active Description from SNF is : "
					+ Value.toString());
		}

		//9.Processing Alarm Event Type - VARBIND
		if (Name.equalsIgnoreCase("ituAlarmEventType"))

			notif.setEventType(HandleEventType.getituAlarmEventType(Value));//Unknown

		//10.Processing ituPerceivedSeverity.
		//This block is used to Tokenize ituAlarmEventType and find the Alarm Perceived Severity
		try {

			// need to check this behaviour while going for implementation
		/*	String severity = ituAlarmEventTypeoid.toString().substring(
					ituAlarmEventTypeoid.toString().lastIndexOf(".") + 1);
*/
			/************************
						 //Processing PerceivedSeverity
						 StringTokenizer st3 = new StringTokenizer(oid2.trim(),".");
						 String severity="";
						 int count=st3.countTokens();
						 LOGGER.info("Count of values from the ituAlarmEventType OID :"+ count);
						 //Looping is performed here to fetch the Severity value, which falls in the End of this Varbind
						 for (int m=0;m<count;m++)
						 {
						 severity =st3.nextToken().trim();
						 }
			 ***************************/

			LOGGER.debug("10.Severity found in the Trap PDU is : ","unknowyet");

			// Variable Severity hold the value of severity to be set for the Alarm
			// The following code is used to map the Severity as per the M3100 and X733 Standards

			notif.setSeverity(HandleAlarmSeverity.getEFWSAlarmSeverity("unknowyet"));

		}//End of try block
		catch (Exception exc) {
			LOGGER.error("10.ERROR IN PROCESSING",exc);
			LOGGER.error("Exception while processing Severity...setting Default Severity for the SNF Trap");
			notif.setSeverity("INDETERMINATE");
			return notif;
		}


		moifinal = moi3 + "," + moi1 + "," + moi2;

		//11.Processing Alarm Probable Cause - VARBIND
		if (Name.equalsIgnoreCase("ituProbableCause")
				||Name.equalsIgnoreCase(ituProbableCauseB)) {
			try {
				int probableCause = 0;
				probableCause = Integer.parseInt(Value
						.toString());
				LOGGER.debug("11.Probable Cause found in the SNF Trap PDU : "
						+ probableCause);
				notif.setProbableCause(new Integer(probableCause)
				.toString() + moifinal);
			} catch (Exception x) {

				LOGGER.error("Exception while processing Probable Cause...setting Default PC for the SNF Trap",x);
				notif.setProbableCause("0"); //Indeterminate
				return notif;
			}
		}
		return notif;
	}

	////TIGRIS accAlarmCause
	public static String getaccAlarmCause(String Name,String Value){
		String probableCause="";
		try
		{
			probableCause = accCauseMap.get(Value);

		}
		catch(Exception ex)
		{
			LOGGER.error("setProbableCause Exception." ,ex);
			return new Integer(0).toString();//Unknown
		}
		return probableCause;
	}

	////SRD:srdNotificationAddText
	public static String getsrdNotificationAddText(String Name,String Value){
		String psstr="";
		try
		{
			String str1=Value.toString();
			LOGGER.debug("Additional Text for SUNPLATFORM MIB is "+str1);
			StringTokenizer sunstr=new StringTokenizer(str1,"/");
			while(sunstr.hasMoreTokens())
			{
				String ps=sunstr.nextToken();

				if(ps.startsWith("PS"))
				{
					psstr="PS="+ps;

				}
			}
			LOGGER.debug("Power Supply Info for SUNPLATFORM MIB is ",psstr);

		}

		catch(Exception e)
		{
			LOGGER.error("No power supply additional text/Problem in processing powersupply info",e);
			return psstr;
		}
		return psstr;
	}

	////SBG:TrapEnumResult, ISBLADE:TrapEnumResult
	public static String getTrapEnumResult(String Name,String Value){
		String str11 = "";
		str11=enumResultMap.get(Value);
		return str11;
	}

	////SBG:sbgEnumCase, ISBLADE:isbladeTrapEnumCause
	public static String getTrapEnumStatus(String Name,String Value){
		String str22 = "";
		str22=enumStatusMap.get(Value);
		return str22;
	}

	////SBG:sbgEnumCase, ISBLADE:isbladeTrapEnumCause
	public static String getTrapEnumCause(String Name,String Value){
		String str = "";
		str=enumCauseMap.get(Value);
		return str;
	}

	//RAD nsVrBgpPeerState
	public static String getfunkSbrTrapVarComp(String Name,String Value){
		String funkSbrTrapVarComp=funkSbrTrapVarCompmap.get(Value);

		return funkSbrTrapVarComp;
	}

	//NETSCREEN nsVrBgpPeerState
	public static String getInsVrBgpPeerState(String Name,String Value){
		String nsVrBgpPeerState=InsVrBgpPeerState.get(Value);

		return nsVrBgpPeerState;
	}


	//NETSCREEN NETSCREEN _BGP_OSPF_MIBS: bgposfpTrapType
	public static String getbgposfpTrapType(String Name,String Value){
		String netscreenTrapType=bgposfpTrapType.get(Value);
		return netscreenTrapType;
	}


	// NETSCREEN TrapType
	public static EventNotification buildNsAlarm4netscreenTrapType(String Name,String Value,EventNotification notification){
		EventNotification notif=notification;
		try{


			switch(Integer.parseInt(Value.toString()))
			{

			case 15://High Availibility(15)
				notif.addAdditionalAttribute("Specific","High Availibility");
				notif.setEventType("1"); //Communication
				notif.setFmEventType("ERROR");
				notif.setProbableCause("329");//  Loss of Signal
				notif.setSpecificProblem("Loss of Signal.");
				notif.setSeverity("WARNING");
				TrapTypeflag= true;
				break;
			case 19: //device-dead(19)
				notif.setEventType("5");//equipment alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("315");//  Equipment malfunction
				notif.setSpecificProblem("Hardware Fault");
				notif.setSeverity("CRITICAL");
				notif.addAdditionalAttribute("Specific","device-dead");
				TrapTypeflag= true;
				break;
			case 20://low-memory(20)
				notif.addAdditionalAttribute("Specific","Not enough memory available");
				notif.setEventType("2");//Processing Error Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("332");  // Out of memory
				notif.setSpecificProblem("Not enough memory available");
				notif.setSeverity("MAJOR");
				TrapTypeflag= true;
				break;
			case 21://dns-srv-down(21)
				notif.addAdditionalAttribute("Specific","dns-srv-down");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("329");//Loss of signal
				notif.setSpecificProblem("Unable to communicate with Domain Name Service (DNS) server");
				notif.setSeverity("MAJOR");
				TrapTypeflag= true;
				break;
			case 22://generic-HW-fail(22)
				notif.addAdditionalAttribute("Specific","generic-HW-fail");
				notif.setEventType("5");//Equipment Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("335");//Power malfunction
				notif.setSpecificProblem("Hardware Fault");
				notif.setSeverity("CRITICAL");
				TrapTypeflag= true;
				break;
			case 23://lb-srv-down(23)
				notif.addAdditionalAttribute("Specific","lb-srv-down");
				notif.setEventType("1");//Communiations Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("329"); // Loss of signal
				notif.setSpecificProblem("Unable to communicate with Load Balancing (LB) server");
				notif.setSeverity("MAJOR");
				TrapTypeflag= true;
				break;
			case 24://log-full(24)
				notif.addAdditionalAttribute("Specific","log-full");
				notif.setEventType("2");//Processing Error Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("349"); //Storage cpacity problem
				notif.setSpecificProblem("Log file is full");
				notif.setSeverity("MAJOR");
				TrapTypeflag= true;
				break;
			case 30://cpu-usage-high(30)
				notif.addAdditionalAttribute("Specific","cpu-usage-high");
				notif.setEventType("2");//Processing Error Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("351"); //Threshold crossed
				notif.setSpecificProblem("High load on Central Processing Unit (cpu)");
				notif.setSeverity("MAJOR");
				TrapTypeflag= true;
				break;
			case 40://VPN-tunnel-up(40) "Virtual Private Network (VPN) tunnel is restored"
				notif.addAdditionalAttribute("Specific","VPN-tunnel-up");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329"); //  Loss of signal
				notif.setSpecificProblem("Virtual Private Network (VPN) tunnel is not available for use");
				notif.setSeverity("CLEARED");
				TrapTypeflag= true;
				break;
			case 41://VPN-tunnel-down(41)
				notif.addAdditionalAttribute("Specific","VPN-tunnel-down");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");//Loss of signal
				notif.setSpecificProblem("Virtual Private Network (VPN) tunnel is not available for use");
				notif.setSeverity("MINOR");
				TrapTypeflag= true;
				break;
			case 60://nsrp-rto-down(60)
				notif.addAdditionalAttribute("Specific","nsrp-rto-down");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("517");//Link Failure
				notif.setSpecificProblem("NSRP rto self unit status change");
				notif.setSeverity("CLEARED");
				TrapTypeflag= true;
				break;
			case 61://nsrp-rto-up(61)
				notif.addAdditionalAttribute("Specific","nsrp-rto-up");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("517");//Link Failure
				notif.setSpecificProblem("NSRP rto self unit status change");
				notif.setSeverity("MAJOR");
				TrapTypeflag= true;
				break;
			case 62://nsrp-trackip-success(62)
				notif.addAdditionalAttribute("Specific","nsrp-trackip-success");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("517");//Link Failure
				notif.setSpecificProblem("NSRP track ip failed");
				notif.setSeverity("CLEARED");
				TrapTypeflag= true;
				break;
			case 63://nsrp-trackip-failed(63)
				notif.addAdditionalAttribute("Specific","nsrp-trackip-failed");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("517");//Link Failure
				notif.setSpecificProblem("NSRP track ip failed");
				notif.setSeverity("MAJOR");
				TrapTypeflag= true;
				break;
			case 71://nsrp-vsd-master(71)
				notif.addAdditionalAttribute("Specific","nsrp-vsd-master");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("329");//Loss of signal
				notif.setSpecificProblem("NSRP vsd group status change to master");
				notif.setSeverity("MAJOR");
				TrapTypeflag= true;
				break;
			case 76://nsrp-vsd-2ndpath-request(76)
				notif.addAdditionalAttribute("Specific","nsrp-vsd-2ndpath-request");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");//Loss of signal
				notif.setSpecificProblem("NSRP vsd ha link 2nd path request received");
				notif.setSeverity("MAJOR");
				TrapTypeflag= true;
				break;
			case 77:// nsrp-vsd-2ndpath-reply(77)
				notif.addAdditionalAttribute("Specific"," nsrp-vsd-2ndpath-reply");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");//Loss of signal
				notif.setSpecificProblem("NSRP vsd ha link 2nd path request received");
				notif.setSeverity("CLEARED");
				TrapTypeflag= true;
				break;
			case 209:// BGP is in downward transition(209)
				notif.addAdditionalAttribute("Specific","BGP is in downward transition");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");//Loss of signal
				notif.setSpecificProblem("BGP is in downward transition");
				notif.setSeverity("CRITICAL");
				TrapTypeflag= true;
				break;
				//case:this is clear alarm implementation for above alarm
			case 208:// BGP is in downward transition(209)
				notif.addAdditionalAttribute("Specific","BGP is changed to Establish state");
				notif.setEventType("1");//Communications Alarm
				notif.setFmEventType("ALARM");
				notif.setProbableCause("329");//Loss of signal
				notif.setSpecificProblem("BGP is in downward transition");
				notif.setSeverity("CLEARED");
				TrapTypeflag= true;
				break;

			default:
				LOGGER.debug("default specific type for the trap: "+Integer.parseInt(Value.toString()));
				notif.addAdditionalAttribute("Specific","no additional text");
				notif.setEventType("0");//Communications Alarm
				notif.setFmEventType("ERROR");
				notif.setProbableCause("0");//Loss of signal
				notif.setSpecificProblem("Unknown Alarm");
				notif.setSeverity("INDETERMINATE");
				notif.setSpecificProblem("None");
				LOGGER.info("trap to be dropped");
				notif=null;
				break;
				//end R3 alarms
			}

		}catch(Exception e){   
			LOGGER.error("exception ocuured",e);
			notif.addAdditionalAttribute("Specific"," null");
			notif.setEventType("0");//Unkown Event Type
			notif.setFmEventType("ERROR");
			notif.setProbableCause("0");//Indeterminate
			notif.setSpecificProblem("Unknown Alarm");
			notif.setSeverity("INDETERMINATE");
			TrapTypeflag= true;
			notif=null;

		}
		return notif;
	}

	//netraalarmps and NETRA_Alarm_Severity==5
	public static String getnetraalarmpswith5(String Name,String Value,int Alarm_Severity){
		String netraspecificproblm="null";



		String spec_problem=Value.toString();

		if(spec_problem.equalsIgnoreCase("ASDNS Node UP") && Alarm_Severity==5)
		{
			netraspecificproblm="ASDNS Node is down";
		}
		else if(spec_problem.equalsIgnoreCase("ENUM Server Cluster up") && Alarm_Severity==5)
		{
			netraspecificproblm="NDB Connection to a MySQL cluster is down. Please check the MySQL Cluster configuration and possible HW resources.";
		}
		else if(spec_problem.equalsIgnoreCase("ENUM Server Cluster Available") && Alarm_Severity==5)
		{
			netraspecificproblm="NDB Connections to the all MySQL clusters are down. Please check the MySQL Cluster configuration and possible HW resources";
		}
		else if(spec_problem.equalsIgnoreCase("Backup process is completed.") && Alarm_Severity==5)
		{
			netraspecificproblm="Operator initiated a Backup Process.";
		}
		else if(spec_problem.equalsIgnoreCase("Restore process is completed") && Alarm_Severity==5)
		{
			netraspecificproblm="Restore process started. Configuration will be restored from specified backup.";
		}
		else if(spec_problem.equalsIgnoreCase("Sun Cluster returns to normal state successfully") && Alarm_Severity==5)
		{
			netraspecificproblm="Not all resources, resource groups, device groups, cluster nodes and transport paths are online";
		}
		else if(spec_problem.equalsIgnoreCase("Disk Array connection returns to normal state successfully") && Alarm_Severity==5)
		{
			netraspecificproblm="The connection to external disk array may be broken";
		}
		else if(spec_problem.equalsIgnoreCase("SNMP Master agent Cold Started") && Alarm_Severity==5)
		{
			netraspecificproblm="SNMP Master agent Down";
		}

		///1.ipworksDnsServShutDown
		else if (spec_problem.equalsIgnoreCase("DNS Server Shutdown on operator request or caused by serious error,Check logs for the cause of the problem."))
		{
			netraspecificproblm="DNS Server is down. Please check the Problem Text for more information";
			problemTextString.append("DNS Server Shutdown on operator request or caused by serious error,Check logs for the cause of the problem.\n");

		}

		else if(spec_problem.equalsIgnoreCase("DNS Server Shutdown on operator request or caused by serious error,Check logs for the cause of the problem"))
		{
			netraspecificproblm="DNS Server is down. Please check the Problem Text for more information";
			problemTextString.append("DNS Server Shutdown on operator request or caused by serious error,Check logs for the cause of the problem\n"); 

		}
		///2.ipworksDnsServFatalError
		else if(spec_problem.equalsIgnoreCase("DNS Server Failed to start due to configuration or customization problem. Please check the configuration attributes"))
		{
			netraspecificproblm="DNS Server is down. Please check the Problem Text for more information";
			problemTextString.append("DNS Server Failed to start due to configuration or customization problem. Please check the configuration attributes\n");	

		}

		///3.ipworksDnsServStart Clear alarm for the Above two alarms
		else if(spec_problem.equalsIgnoreCase("DNS Server Started successfully") && Alarm_Severity==5)
		{
			netraspecificproblm="DNS Server is down. Please check the Problem Text for more information";
			problemTextString.append("DNS Server Started successfully\n");	

		}

		//4.ipworksEmSSShutDown
		else if(spec_problem.equalsIgnoreCase("Storage Server stopped on operator request or caused by serious error. Check logs for the cause of the problem."))
		{
			netraspecificproblm="EmS Server is down. Please check the Problem Text for more information";
			problemTextString.append("Storage Server stopped on operator request or caused by serious error. Check logs for the cause of the problem.\n");	

		}
		else if(spec_problem.equalsIgnoreCase("Storage Server stopped on operator request or caused by serious error. Check logs for the cause of the problem"))
		{
			netraspecificproblm="EmS Server is down. Please check the Problem Text for more information";
			problemTextString.append("Storage Server stopped on operator request or caused by serious error. Check logs for the cause of the problem\n"); 

		}


		//5.ipworksEmSSNotReachable

		else if(spec_problem.equalsIgnoreCase("Storage Server is not reachable by Server Manager."))
		{
			netraspecificproblm="EmS Server is down. Please check the Problem Text for more information";
			problemTextString.append("Storage Server is not reachable by Server Manager.\n"); 

		}
		// Below is the clearing record for ipworksEmSSNotReachable and  ipworksEmSSShutDown alarm
		//6.ipworksEmSSStart and ipworksEmSSReachable
		else if(spec_problem.equalsIgnoreCase("Storage Server Started") && Alarm_Severity==5)
		{
			netraspecificproblm="EmS Server is down. Please check the Problem Text for more information";
			problemTextString.append("Storage Server Started\n");

		}

		//7.ipworksEnumServShutDown

		else if(spec_problem.equalsIgnoreCase("ENUM Server Shutdown on operator request or caused by serious error. Check logs for the cause of the problem."))
		{
			netraspecificproblm="ENUM Server is down. Please check the Problem Text for more information";
			problemTextString.append("ENUM Server Shutdown on operator request or caused by serious error. Check logs for the cause of the problem.\n"); 

		}
		//8.ipworksEnumServFatalError
		else if(spec_problem.equalsIgnoreCase("The ENUM server was unable to start because the initialization process encountered a non-recoverable error."))
		{
			netraspecificproblm="ENUM Server is down. Please check the Problem Text for more information";
			problemTextString.append("The ENUM server was unable to start because the initialization process encountered a non-recoverable error.\n");	

		}
		// Below alarm is clearing trap for the ipworksEnumServFatalError and ipworksEnumServShutDown
		//9.ipworksEnumServStart
		else if(spec_problem.equalsIgnoreCase("ENUM Server Started successfully.") && Alarm_Severity==5)
		{
			netraspecificproblm="ENUM Server is down. Please check the Problem Text for more information";
			problemTextString.append("ENUM Server Started successfully.\n");	

		}

		//10.ipworksDiskArrayNeedMaintenance
		else if(spec_problem.equalsIgnoreCase("Disk Array needs maintenance"))
		{
			netraspecificproblm="Disk Array problem. Please check the Problem Text for more information";
			problemTextString.append("Disk Array needs maintenance\n");		

		}

		//11.ipworksDiskArrayLastErred
		else if(spec_problem.equalsIgnoreCase("Disk Array go into the state of last error"))
		{
			netraspecificproblm="Disk Array problem. Please check the Problem Text for more information";
			problemTextString.append("Disk Array go into the state of last error\n");	

		}

		//12.ipworksDiskArrayUnavailable
		else if(spec_problem.equalsIgnoreCase("A device cannot be accessed, but has not incurred errors."))
		{
			netraspecificproblm="Disk Array problem. Please check the Problem Text for more information";
			problemTextString.append("A device cannot be accessed, but has not incurred errors.\n");	

		}

		// Below alarm is clearing alarm for the ipworksDiskArrayLastErred ipworksDiskArrayUnavailable and ipworksDiskArrayLastErred
		//13.ipworksDiskArrayOk
		else if(spec_problem.equalsIgnoreCase("Disk Array returns to normal state successfully") && Alarm_Severity==5)
		{
			netraspecificproblm="Disk Array problem. Please check the Problem Text for more information";
			problemTextString.append("Disk Array returns to normal state successfully\n");	

		}

		///Link Up Alarm
		else if(spec_problem.equalsIgnoreCase("linkUp,communication link restored") && Alarm_Severity==5)
		{
			netraspecificproblm="linkDown,communication link failure";
		}
		else
		{
			netraspecificproblm="no specific problem"; 		
		}

		return netraspecificproblm;
	}

	//IFADMINSTATUS
	public static String getIfAdminState(String Name,String Value){
		String IfAdminState=ifAdminState.get(Value);

		return IfAdminState;
	}

	//IFOPERSTATUS
	public static String getIfOperState(String Name,String Value){
		String IfOperState=ifOperStatus.get(Value);

		return IfOperState;
	}

	//ACME _OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE

	public static String get_OID_VAR_AP_ENV_MON_TRAP_PREVIOUS_STATE(String Name,String Value){




		String txtVarApEnvMonTrapPreviousState=txtVarApEnvMonTrapPreviousStateMap.get(Value);
		LOGGER.error("Var OID Matched : apEnvMonTrapPreviousState",txtVarApEnvMonTrapPreviousState);


		return txtVarApEnvMonTrapPreviousState;
	}

	//ACME _OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE

	public static String get_OID_VAR_AP_ENV_MON_TRAP_CURRENT_STATE(String Name,String Value){
		
		String txtVarApEnvMonTrapCurrentState=txtVarApEnvMonTrapCurrentStateMap.get(Value);

		return txtVarApEnvMonTrapCurrentState;
	}

	//ACME "_OID_VAR_AP_SYS_LOG_HIST_LEVEL"

	public static String get_OID_VAR_AP_SYS_LOG_HIST_LEVEL(String Name,String Value){
		String txtVarApSyslogHistLevel="";
		switch(Integer.valueOf(Value).intValue())
		{
		case 1: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("emergency(1)."); break;
		case 2: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("critical(2)."); break;
		case 3: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("major(3)."); break;
		case 4: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("minor(4) ."); break;
		case 5: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("warning(5)."); break;
		case 6: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("notice(6)."); break;
		case 7: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("info(7)."); break;
		case 8: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("trace(8)."); break;
		case 9: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("debug(9)."); break;
		default: txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("-----");
		}	
		return txtVarApSyslogHistLevel;
	}

	//ACME _OID_VAR_AP_SYS_MGMT_POWER_PRESENCE

	public static String get_OID_VAR_AP_SYS_MGMT_POWER_PRESENCE(String Name,String Value){
		String txtVarApSysMgmtPowerPresence="";
		switch(Integer.valueOf(Value).intValue())
		{
		case 0: txtVarApSysMgmtPowerPresence = TranslationConstant.txtVarApSysMgmtPowerPresence.concat("down(0).");break;
		case 1:	txtVarApSysMgmtPowerPresence = TranslationConstant.txtVarApSysMgmtPowerPresence.concat("up(1)."); break;
		default: txtVarApSysMgmtPowerPresence = TranslationConstant.txtVarApSysMgmtPowerPresence.concat("-----");
		}
		return txtVarApSysMgmtPowerPresence;
	}

	//ACME _OID_VAR_AP_SYS_MGMT_FAN_LOCATION

	public static String get_OID_VAR_AP_SYS_MGMT_FAN_LOCATION(String Name,String Value){
		String txtVarApSysMgmtFanLocation="";
		switch(Integer.valueOf(Value).intValue())
		{
		case 0: txtVarApSysMgmtFanLocation = TranslationConstant.txtVarApSysMgmtFanLocation.concat("left(0)."); break;
		case 1:	txtVarApSysMgmtFanLocation = TranslationConstant.txtVarApSysMgmtFanLocation.concat("middle(1)."); break;
		case 2:	txtVarApSysMgmtFanLocation = TranslationConstant.txtVarApSysMgmtFanLocation.concat("right(2)."); break;
		default: txtVarApSysMgmtFanLocation = TranslationConstant.txtVarApSysMgmtFanLocation.concat("-----");
		}
		return txtVarApSysMgmtFanLocation;
	}

	//ACME _OID_VAR_AP_SYS_MGMT_RED_ROLE  

	public static String get_OID_VAR_AP_SYS_MGMT_RED_ROLE(String Name,String Value){

		String 	txtVarApSysMgmtRedRole="";
		switch(Integer.valueOf(Value).intValue())
		{
		case 0: txtVarApSysMgmtRedRole = TranslationConstant.txtVarApSysMgmtRedRole.concat("primary(0)."); break;
		case 1:	txtVarApSysMgmtRedRole = TranslationConstant.txtVarApSysMgmtRedRole.concat("secondary(1)."); break;
		default: txtVarApSysMgmtRedRole = TranslationConstant.txtVarApSysMgmtRedRole.concat("-----");
		}
		return txtVarApSysMgmtRedRole;

	}

	//ACME _OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE

	public static String get_OID_VAR_AP_SYS_MGMT_RED_TRANS_STATE(String Name,String Value){
		String 	txtVarApSysMgmtRedTransState="";
		switch(Integer.valueOf(Value).intValue())
		{
		case 0: txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState.concat("out-of-service(0)."); break;
		case 1: txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState.concat("active(1)."); break;
		case 2: txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState.concat("standby(2)."); break;
		case 3:	txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState.concat("no-peer(3)."); break;
		default: txtVarApSysMgmtRedTransState = TranslationConstant.txtVarApSysMgmtRedTransState.concat("-----");
		}

		return txtVarApSysMgmtRedTransState;
	}


	//ACME _OID_VAR_AP_SYS_MGMT_RADIUS_DOWN

	public static String get_OID_VAR_AP_SYS_MGMT_RADIUS_DOWN(String Name,String Value){
		String 	txtVarApSysMgmtRadiusDown="";

		switch(Integer.valueOf(Value).intValue())
		{
		case 0: txtVarApSysMgmtRadiusDown = TranslationConstant.txtVarApSysMgmtRadiusDown.concat("all-servers-down(0)."); break;
		case 1: txtVarApSysMgmtRadiusDown = TranslationConstant.txtVarApSysMgmtRadiusDown.concat("some-servers-down(1)."); break;
		default: txtVarApSysMgmtRadiusDown = TranslationConstant.txtVarApSysMgmtRadiusDown.concat(" -----");
		}
		return txtVarApSysMgmtRadiusDown;
	}

	//ACME _OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON

	public static String get_OID_VAR_AP_SYS_MGMT_SA_STATUS_REASON(String Name,String Value){
		String 	txtVarApSysMgmtSAStatusReason="";
		switch(Integer.valueOf(Value).intValue())
		{
		case 0: txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason.concat("administrative(0)."); break;
		case 1: txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason.concat("oosbyproxyerror(1)."); break;
		case 2: txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason.concat("standby(2)."); break;
		case 3: txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason.concat("inservice(3)."); break;
		case 4: txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason.concat("constraintsexceeded(4)."); break;
		case 5: txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason.concat("unresponsive(5)."); break;
		default: txtVarApSysMgmtSAStatusReason = TranslationConstant.txtVarApSysMgmtSAStatusReason.concat("-----");
		}
		return txtVarApSysMgmtSAStatusReason;
	}

	//ACME _OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL

	public static String get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_LEVEL(String Name,String Value){
		String 	txtVarApSysMgmtAuthFailLevel="";

		LOGGER.info("Var OID Matched : apSysMgmtAuthFailLevel");
		switch(Integer.valueOf(Value.toString()).intValue())
		{
		case 0: txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel.concat("login(0)."); break;
		case 1: txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel.concat("user(1)."); break;
		case 2: txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel.concat("priv(2)."); break;
		case 3: txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel.concat("shell(3)."); break;
		default: txtVarApSysMgmtAuthFailLevel = TranslationConstant.txtVarApSysMgmtAuthFailLevel.concat("-----");
		}
		return txtVarApSysMgmtAuthFailLevel;
	}

	//ACME get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE  (need to correct the body)

	public static String get_OID_VAR_AP_SYS_MGMT_SYSTEM_STATE(String Name,String Value){
		String 	txtVarApSysMgmtAuthFailProto="";
		switch(Integer.valueOf(Value.toString()).intValue())
		{
		case 0: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("console(0)."); break;
		case 1: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("telnet(1)."); break;
		case 2: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("ftp(2)."); break;
		case 3: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("ssh(3)."); break;
		case 4: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("sftp(4)."); break;
		default: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("-----");
		}
		return txtVarApSysMgmtAuthFailProto;
	}

	//ACME _OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO

	public static String get_OID_VAR_AP_SYS_MGMT_AUTH_FAIL_PROTO(String Name,String Value){
		String 	txtVarApSysMgmtAuthFailProto="";
		switch(Integer.valueOf(Value.toString()).intValue())
		{
		case 0: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("console(0)."); break;
		case 1: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("telnet(1)."); break;
		case 2: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("ftp(2)."); break;
		case 3: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("ssh(3)."); break;
		case 4: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("sftp(4)."); break;
		default: txtVarApSysMgmtAuthFailProto = TranslationConstant.txtVarApSysMgmtAuthFailProto.concat("-----");
		}
		return txtVarApSysMgmtAuthFailProto;
	}

	//ACME _OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON

	public static String get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS_REASON(String attributeName,String attributeValue){
		String 	txtVarApapSysMgmtSipInterfaceStatusReason="";
		switch(Integer.valueOf(attributeValue.toString()).intValue())
		{
		case 0: txtVarApapSysMgmtSipInterfaceStatusReason =  TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason.concat("administrative(0)."); break;
		case 1: txtVarApapSysMgmtSipInterfaceStatusReason =  TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason.concat("oosbyproxyerror(1) ."); break;
		case 2: txtVarApapSysMgmtSipInterfaceStatusReason =  TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason.concat("standby(2)."); break;
		case 3: txtVarApapSysMgmtSipInterfaceStatusReason =  TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason.concat("inservice(3)."); break;
		case 4: txtVarApapSysMgmtSipInterfaceStatusReason =  TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason.concat("constraintsexceeded(4)."); break;
		case 5: txtVarApapSysMgmtSipInterfaceStatusReason =  TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason.concat("unresponsive(5)."); break;
		default: txtVarApapSysMgmtSipInterfaceStatusReason =  TranslationConstant.txtVarApapSysMgmtSipInterfaceStatusReason.concat("-----");
		}
		return txtVarApapSysMgmtSipInterfaceStatusReason;
	}

	//ACME _OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUSN

	public static String get_OID_VAR_AP_SYS_MGMT_SIP_INTERFACE_STATUS(String attributeName,String attributeValue){
		String 	txtVarApapSysMgmtSipInterfaceStatus="";

		switch(Integer.valueOf(attributeValue.toString()).intValue())
		{
		case 0: txtVarApapSysMgmtSipInterfaceStatus = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatus.concat("inservice(0)."); break;
		case 1: txtVarApapSysMgmtSipInterfaceStatus = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatus.concat("outofservice(1)."); break;
		default: txtVarApapSysMgmtSipInterfaceStatus = TranslationConstant.txtVarApapSysMgmtSipInterfaceStatus.concat("-----");
		}
		return txtVarApapSysMgmtSipInterfaceStatus;
	}

	//ACME _OID_VAR_AP_ENUM_SERVER_STATUS

	public static String get_OID_VAR_AP_ENUM_SERVER_STATUS(String attributeName,String attributeValue){
		String 	txtApENUMServerStatus="";

		switch(Integer.valueOf(attributeValue.toString()).intValue())
		{
		case 0: txtApENUMServerStatus = TranslationConstant.txtApENUMServerStatus.concat("inservice(0)."); break;
		case 1: txtApENUMServerStatus = TranslationConstant.txtApENUMServerStatus.concat("lowerpriority(1)."); break;
		case 2: txtApENUMServerStatus = TranslationConstant.txtApENUMServerStatus.concat("oosunreachable(2)."); break;
		default: txtApENUMServerStatus = TranslationConstant.txtApENUMServerStatus.concat("-----");
		}
		return txtApENUMServerStatus;
	}

	//EXTREME extremeTrapAuthorerrorType

	public static String getextremeTrapAuthorerrorType(String attributeName,String attributeValue){
		String vrrpTrapAuthErrorType="";
		switch(Integer.parseInt(attributeValue.toString()))
		{

		case 1:
			vrrpTrapAuthErrorType="invalidAuthType";	
			break;
		case 2:
			vrrpTrapAuthErrorType="authTypeMismatch";
			break;
		case 3:
			vrrpTrapAuthErrorType="authFailure";

			break;
		default:
			vrrpTrapAuthErrorType="defaultFailure";

			break;
		}
		return vrrpTrapAuthErrorType;
	}


	//EXTREME extremeSystemPowerState

	public static String getextremeSystemPowerState(String attributeName,String attributeValue){
		String extremeSystemPowerState="";
		switch(Integer.parseInt(attributeValue.toString()))
		{
		case 1:
			extremeSystemPowerState = "computing";
			break;
		case 2:
			extremeSystemPowerState = "sufficientButNotRedundantPower";
			break;
		case 3:
			extremeSystemPowerState = "redundantPowerAvailable";
			break;
		case 4:
			extremeSystemPowerState = "insufficientPower";
			break; 
		default :
			extremeSystemPowerState = "Entered input value should be between 1..4";
			break;

		}

		return extremeSystemPowerState;
	}

	//EXTREME extremePortMauStatus

	public static String getextremePortMauStatus(String attributeName,String attributeValue){
		String extremePortMauStatus="";
		switch(Integer.parseInt(attributeValue.toString()))
		{	
		case 1:
			extremePortMauStatus = "inserted";  
			break;
		case 2:
			extremePortMauStatus = "empty";
			break;
		}

		return extremePortMauStatus;
	}

	//EXTREME extremeEsrpDmnState

	public static String getextremeEsrpDmnState(String attributeName,String attributeValue){

		String extremeEsrpDmnState="";
		switch(Integer.parseInt(attributeValue.toString()))
		{
		case 0:
			extremeEsrpDmnState = "neutral";
			break;
		case 1:
			extremeEsrpDmnState = "master";
			break;
		case 2:
			extremeEsrpDmnState = "slave";
		case 3:
			extremeEsrpDmnState = "premaster";
			break;
		case 4:
			extremeEsrpDmnState = "aware";
			break;
		default : break;	
		}

		return extremeEsrpDmnState;
	}

	//MMC rdbmsRelState

	public static String getrdbmsRelState(String attributeName,String attributeValue){

		String rdbmsState="";

		switch(Integer.valueOf(attributeValue.toString()).intValue())
		{
		case 1:rdbmsState = HandleAllOtherAlarmAttribute.rdbmsState.concat("other(1).");
		break;
		case 2:rdbmsState = HandleAllOtherAlarmAttribute.rdbmsState.concat("active(2).");
		break;
		case 3:rdbmsState = HandleAllOtherAlarmAttribute.rdbmsState.concat("available(3).");
		break;
		case 4:rdbmsState = HandleAllOtherAlarmAttribute.rdbmsState.concat("restricted(4).");
		break;
		case 5:rdbmsState = HandleAllOtherAlarmAttribute.rdbmsState.concat("unavailable(5).");
		break;
		default:
			rdbmsState = HandleAllOtherAlarmAttribute.rdbmsState.concat("--------------");
			break;
		}
		return rdbmsState;
	}

	//MMC rdbmsRelState

	public static String getoraListenerState(String attributeName,String attributeValue){

		String genericListenerState="";

		switch(Integer.valueOf(attributeValue.toString()).intValue())
		{
		case 1:genericListenerState = HandleAllOtherAlarmAttribute.genericListenerState.concat("up(1).");
		break;
		case 2:genericListenerState = HandleAllOtherAlarmAttribute.genericListenerState.concat("down(2).");
		break;
		default:
			genericListenerState = HandleAllOtherAlarmAttribute.genericListenerState.concat("--------------");
			break;
		}
		return genericListenerState;
	}

	//MMC mmsFmSystemAlarmID

	public static String getmmsFmSystemAlarmID(String attributeName,String attributeValue){
		String alarmIdentity = "";
		String alarmID="";

		alarmIdentity = attributeValue.toString();
		LOGGER.debug("Alarm Id String = " +alarmIdentity);

		try
		{
			if (alarmIdentity.indexOf('x') != -1)
			{
				LOGGER.debug("Found x.....................");
				alarmID = alarmIdentity.substring((alarmIdentity.indexOf('x')));
			}
			else
			{
				LOGGER.debug("Did not find X.............");
				alarmID = "x999";
			}
			LOGGER.debug("Alarm Id String = " +alarmID);

		}
		catch (Exception e1)
		{
			LOGGER.error("Error on mmsFmSystemAlarmID" +e1);
		}

		if ((alarmID.equals("x500") || alarmID.equals("x501") || alarmID.equals("x502") || alarmID.equals("x503")))
		{
			perfdegraded = true;
			LOGGER.error("perfdegraded",perfdegraded);
		}
		return alarmIdentity;
	}

	//MMC merOperationalState

	public static String getmerOperationalState(String attributeName,String attributeValue){

		String merOpState="";

		switch(Integer.valueOf(attributeValue.toString()).intValue())
		{
		case 1:merOpState = HandleAllOtherAlarmAttribute.merOpState.concat("enabled(1).");
		break;
		case 2:merOpState = HandleAllOtherAlarmAttribute.merOpState.concat("disabled(2).");
		break;
		default:
			merOpState = HandleAllOtherAlarmAttribute.merOpState.concat("--------------");
			break;
		}
		return merOpState;			
	}

	//MMC merAdminstrativeState

	public static String getmerAdminstrativeState(String attributeName,String attributeValue){

		String merAdminState="";

		switch(Integer.valueOf(attributeValue.toString()).intValue())
		{
		case 1:merAdminState = HandleAllOtherAlarmAttribute.merAdminState.concat("unlocked(1).");
		break;
		case 2:merAdminState = HandleAllOtherAlarmAttribute.merAdminState.concat("locked(2).");
		break;
		case 3:merAdminState = HandleAllOtherAlarmAttribute.merAdminState.concat("shutdown(3).");
		break;
		default:
			merAdminState = HandleAllOtherAlarmAttribute.merAdminState.concat("--------------");
			break;
		}
		return merAdminState;			
	}


	//MMC mmsCmSystemOperationalState

	public static String getmmsCmSystemOperationalState(String attributeName,String attributeValue){

		String mmsCmSystemOpState="";

		switch(Integer.valueOf(attributeValue).intValue())
		{
		case 1:mmsCmSystemOpState = HandleAllOtherAlarmAttribute.mmsCmSystemOpState.concat("up(1).");
		break;
		case 2:mmsCmSystemOpState = HandleAllOtherAlarmAttribute.mmsCmSystemOpState.concat("locked(2).");
		break;
		case 3:mmsCmSystemOpState = HandleAllOtherAlarmAttribute.mmsCmSystemOpState.concat("degraded(3).");
		break;
		case 4:mmsCmSystemOpState = HandleAllOtherAlarmAttribute.mmsCmSystemOpState.concat("down(4).");
		break;
		case 5:mmsCmSystemOpState = HandleAllOtherAlarmAttribute.mmsCmSystemOpState.concat("Unknown(5).");
		break;
		default:
			mmsCmSystemOpState = mmsCmSystemOpState.concat("--------------");
			break;
		}
		return mmsCmSystemOpState;			
	}

	//MMC mmsCmSystemAdministrativeState

	public static String getmmsCmSystemAdministrativeState(String attributeName,String attributeValue){

		String mmsCmSystemAdministrativeState="";

		switch(Integer.valueOf(attributeValue.toString()).intValue())
		{
		case 1:mmsCmSystemAdState = HandleAllOtherAlarmAttribute.mmsCmSystemAdState.concat("unlocked(1).");
		break;
		case 2:mmsCmSystemAdState = HandleAllOtherAlarmAttribute.mmsCmSystemAdState.concat("locked(2).");
		break;
		case 3:mmsCmSystemAdState = HandleAllOtherAlarmAttribute.mmsCmSystemAdState.concat("shutdown(3).");
		break;
		default:
			mmsCmSystemAdState = HandleAllOtherAlarmAttribute.mmsCmSystemAdState.concat("--------------");
			break;
		}
		return mmsCmSystemAdministrativeState;			
	}


	// ESA-SNF

	@SuppressWarnings("unchecked")
	public static String getResourceOIDName(String OID,String fdn) {

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = null;
		Document doc = null;
		String strNodeName = null;
		String strNeName = null;
		File xmlFile;

		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LOGGER.error("Wrong parser configuration: ",e);
		}

		try {
			LOGGER.debug("--- The agent FDN from Supervision class");
			String agentFDN = fdn.toString();
			strNeName = agentFDN.substring(agentFDN.lastIndexOf("=")+1, agentFDN.length());


			xmlFile = new File("/etc/opt/ericsson/nms_umts_cnoss_efmesa/xml/"
					+ strNeName.concat(".xml"));

			doc = docBuilder.parse(xmlFile);
			Node root = doc.getDocumentElement();
			xmlEleList.add(doc.getDocumentElement().getNodeName());
			writeDocumentToOutput(root);
			NamedNodeMap nnm = null;
			for (int s = 0; s < xmlEleList.size(); s++) {
				if ((String) xmlEleList.get(s) != null) {
					NodeList nL1 = doc.getElementsByTagName((String) xmlEleList
							.get(s));

					for (int i = 0; i < nL1.getLength(); i++) {
						Node node = nL1.item(i);

						nnm = node.getAttributes();

						if (getNodeName(nnm, OID) != null)
							strNodeName = nnm.item(0).getNodeValue();
					}
				}
			}
		} catch (SAXException e) {
			LOGGER.error("Wrong XML file structure: " + e.getMessage());
		} catch (FileNotFoundException f) {
			LOGGER.error("--- The XML File Not found in the corresponding PATH!!! ");
		} catch (IOException e) {
			LOGGER.error("Could not read source file: " + e.getMessage());
		} catch (Exception e) {
			LOGGER.error("exception is",e);
		}
		return strNodeName;
	}

	//ESA-SNF

	@SuppressWarnings("unchecked")
	public static void writeDocumentToOutput(Node node) {
		Node child;
		NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				xmlEleList.add(child.getNodeName());
				writeDocumentToOutput(child);
			}
		}

	}

	//ESA-SNF

	public static String getNodeName(NamedNodeMap nnm, String OID) {
		String strNodeName = null;
		for (int j = 0; j < nnm.getLength(); j++) {
			if (nnm.item(j).getNodeValue().equals(OID))
				strNodeName = nnm.item(0).getNodeName();
		}
		return strNodeName;
	}

	// IF MIB HANDLING OperStatus

	public static String getOperStatus(int var)
	{
		String str = IfOperState;
		switch(var)
		{
		case 1: str = "IfOperState up(1)"; break;
		case 2: str =  "IfOperState down(2)"; break;
		case 3: str =  "IfOperState testing(3)"; break;
		default: str = " --------------";
		}
		return str;
	}

	// IF MIB HANDLING IfAdminState
	public static String getAdminStatus(int var)
	{
		String str = IfAdminState;
		switch(var)
		{
		case 1: str = "IfAdminState up(1)"; break;
		case 2: str = "IfAdminState down(2)"; break;
		case 3: str = "IfAdminState testing(3)"; break;
		default: str = " --------------";
		}
		return str;
	}

	// AXD axd301pchIndicator
	public static String getaxd301pchIndicator(String Name,String Value)
	{
		String axd301pchIndicator="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: 
			axd301pchIndicator="The PCH Operation create";
			break;
		case 2: axd301pchIndicator="The PCH Operation delete";
		break;
		case 3: axd301pchIndicator="The PCH Operation modifyBw";
		break;
		case 4: axd301pchIndicator="The PCH Operation block";
		break;
		case 5: axd301pchIndicator="The PCH Operation unblock";
		break;
		default: break;
		}
		return axd301pchIndicator;
	}

	// AXD axdloopbackMode
	public static String getaxdloopbackMode(String Name,String Value)
	{
		String axdloopbackMode="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: axdloopbackMode="The loopback OAM cell was looped back at segment end point";
		break;
		case 2: axdloopbackMode="The loopback OAM cell was looped back at the connection endToEnd point";
		break;
		case 3: axdloopbackMode="The loopback OAM cell was looped back at specified location";
		break;
		default: break;
		}
		return axdloopbackMode;
	}

	// AXD axdloopbackResult
	public static String getaxdloopbackResult(String Name,String Value)
	{
		String axdloopbackResult="";
		switch(Integer.parseInt(Value.toString()))
		{   case 1:    
			axdloopbackResult="The loopback was successful";
			break;
		case 2:
			axdloopbackResult="The loopback was un successful";
			// loopbackResult=axd301GroupStr.append("the
			// loopback was un successful
			// ").append("fail").toString();
			break;
		default: break;}
		return axdloopbackResult;
	}

	// AXD axdoamOperation
	public static String getaxdoamOperation(String Name,String Value)
	{
		String axdoamOperation="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: axdoamOperation="OAM Operation is setSegmentEndPoint";
		break;
		case 2: axdoamOperation="OAM Operation is clearSegmentEndPoint";
		break;
		case 3: axdoamOperation="OAM Operation is setCcSinkLinkSide";
		break;
		case 4: axdoamOperation="OAM Operation is clearCcSinkLinkSide";
		break;
		case 5: axdoamOperation="OAM Operation is setCcSourceLinkSide";
		break;
		case 6: axdoamOperation="OAM Operation is clearCcSourceLinkSide";
		break;
		case 7: axdoamOperation="OAM Operation is setCcSinkCoreSide";
		break;
		case 8: axdoamOperation="OAM Operation is clearCcSinkCoreSide";
		break;
		case 9: axdoamOperation="OAM Operation is setCcSourceCoreSide";
		break;
		case 10: axdoamOperation="OAM Operation is clearCcSourceCoreSide";
		break;
		case 11: axdoamOperation="OAM Operation is inbandCcStatusChange";
		break;
		case 12: axdoamOperation="OAM Operation is setPmSinkLinkSide";
		break;
		case 13: axdoamOperation="OAM Operation is clearPmSinkLinkSide";
		break;
		case 14: axdoamOperation="OAM Operation is setPmSourceLinkSide";
		break;
		case 15: axdoamOperation="OAM Operation is clearPmSourceLinkSide";
		break;
		case 16: axdoamOperation="OAM Operation is setPmSinkCoreSide";
		break;
		case 17: axdoamOperation="OAM Operation is clearPmSinkCoreSide";
		break;
		case 18: axdoamOperation="OAM Operation is setPmSourceCoreSide";
		break;
		case 19: axdoamOperation="OAM Operation is clearPmSourceCoreSide";
		break;
		case 20: axdoamOperation="OAM Operation is inbandPmStatusChange";
		break;
		case 21: axdoamOperation="OAM Operation is setCcInbandSinkLinkSide";
		break;
		case 22: axdoamOperation="OAM Operation is clearCcInbandSinkLinkSide";
		break;
		case 23: axdoamOperation="OAM Operation is setCcInbandSourceLinkSide";
		break;
		case 24: axdoamOperation="OAM Operation is clearCcInbandSourceLinkSide";
		break;
		case 25: axdoamOperation="OAM Operation is setCcInbandSinkCoreSide";
		break;
		case 26: axdoamOperation="OAM Operation is clearCcInbandSinkCoreSide";
		break;
		case 27: axdoamOperation="OAM Operation is setCcInbandSourceCoreSide";
		break;
		case 28: axdoamOperation="OAM Operation is clearCcInbandSourceCoreSide";
		break;
		case 29: axdoamOperation="OAM Operation is setPmInbandSinkLinkSide";
		break;
		case 30: axdoamOperation="OAM Operation is clearPmInbandSinkLinkSide";
		break;
		case 31: axdoamOperation="OAM Operation is setPmInbandSourceLinkSide";
		break;
		case 32: axdoamOperation="OAM Operation is clearPmInbandSourceLinkSide";
		break;
		case 33: axdoamOperation="OAM Operation is setPmInbandSinkCoreSide";
		break;
		case 34: axdoamOperation="OAM Operation is"+"clearPmInbandSinkCoreSide";
		break;
		case 35: axdoamOperation="OAM Operation is setPmInbandSourceCoreSide"; 
		break;
		case 36: axdoamOperation="OAM Operation is clearPmInbandSourceCoreSide"; 
		break;
		case 37: axdoamOperation="OAM Operation is setCcPreventInband"; 
		break;
		case 38: axdoamOperation="OAM Operation is clearCcPreventInband"; 
		break;
		case 39: axdoamOperation="OAM Operation is setPmPreventInband"; 
		break;
		case 40: axdoamOperation="OAM Operation is clearPmPreventInband"; 
		break;
		case 41: axdoamOperation="OAM Operation is setAisRdiMonitoring"; 
		break;
		case 42: axdoamOperation="OAM Operation is clearAisRdiMonitoring"; 
		break;
		default: break;
		}
		return axdoamOperation;
	}

	// AXD axdoamResult
	public static String getaxdoamResult(String Name,String Value)
	{
		String axdoamResult="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: axdoamResult=" and OAM operation was successful";
		break;
		case 2: axdoamResult=" and OAM operation was un successful";
		break;
		default: break;
		}
		return axdoamResult;
	}

	// AXD axdpchVpVcFaultType
	public static String getaxdpchVpVcFaultType(String Name,String Value)
	{
		String axdpchVpVcFaultType="";
		switch(Integer.parseInt(Value.toString()))
		{

		case 1: axdpchVpVcFaultType="Fault is end2endAisLinkSide";
		break;
		case 2: axdpchVpVcFaultType="Fault is end2endRdiLinkSide";
		break; 
		case 3: axdpchVpVcFaultType="Fault is end2endAisCoreSide";
		break; 
		case 4: axdpchVpVcFaultType="Fault is end2endRdiCoreSide";
		break; 
		case 5: axdpchVpVcFaultType="Fault is segmentAisLinkSide";
		break; 
		case 6: axdpchVpVcFaultType="Fault is segmentRdiLinkSide";
		break; 
		case 7: axdpchVpVcFaultType="Fault is segmentAisCoreSide";
		break; 
		case 8: axdpchVpVcFaultType="Fault is segmentRdiCoreSide";
		break;
		default: break;	
		}
		return axdpchVpVcFaultType;
	}

	// AXD axdpchVpVcFaultLocation
	public static String getaxdpchVpVcFaultLocation(String Name,String Value)
	{
		String axdpchVpVcFaultType="";
		switch(Integer.parseInt(Value.toString()))
		{

		case 1: axdpchVpVcFaultType="Fault is "+"end2endAisLinkSide";
		break;
		case 2: axdpchVpVcFaultType="Fault is "+"end2endRdiLinkSide";
		break; 
		case 3: axdpchVpVcFaultType="Fault is "+"end2endAisCoreSide";
		break; 
		case 4: axdpchVpVcFaultType="Fault is "+"end2endRdiCoreSide";
		break; 
		case 5: axdpchVpVcFaultType="Fault is segmentAisLinkSide";
		break; 
		case 6: axdpchVpVcFaultType="Fault is segmentRdiLinkSide";
		break; 
		case 7: axdpchVpVcFaultType="Fault is segmentAisCoreSide";
		break; 
		case 8: axdpchVpVcFaultType="Fault is segmentRdiCoreSide";
		break;
		default: break;	
		}
		return axdpchVpVcFaultType;
	}

	// AXD axdbuStatus
	public static String getaxdbuStatus(String Name,String Value)
	{
		String buStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 0: buStatus="The status of the backup functionality is idle";
		break;
		case 1: buStatus="The status of the backup functionality is inProgress";
		break;
		case 2: buStatus="The status of the backup functionality is ready";
		break;
		case 3: buStatus="The status of the backup functionality is prohibited";
		break;
		default: break;
		}
		return buStatus;
	}

	// AXD axdbuResult
	public static String getaxdbuResult(String Name,String Value)
	{
		String buResult="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 0:
			buResult="BUResult= OK";
			break;
		case 1:
			buResult="BUResult=Input Error";
			break;
		case 2:
			buResult="BUResult=Execution Error";
			break;
		case 3:
			buResult="BUResult=undeterminable";
			break;
		default:
			break;

		}
		return buResult;
	}

	// AXD axdoptDmOpResult
	public static String getaxdoptDmOpResult(String Name,String Value)
	{
		String optDmOpResult="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 0: optDmOpResult="The optDmOpResult is idle";
		break;
		case 1: optDmOpResult="The optDmOpResult is inProgress";
		break;
		case 2: optDmOpResult="The optDmOpResult is ready";
		break;
		case 3: optDmOpResult="The optDmOpResult is prohibited";
		default: break;
		}
		return optDmOpResult;
	}

	// AXD axdoptDmOpResult
	public static String getaxdpaStatus(String Name,String Value)
	{
		String paStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 0:
			paStatus="The status on the patch functionality is idle";
			break;

		case 1:
			paStatus="The status on the patch functionality is installing";
			break;
		default:
			break;

		}
		return paStatus;
	}

	// AXD axdugStatus
	public static String getaxdugStatus(String Name,String Value)
	{
		String ugStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			ugStatus="The status on the upgrade functionality is  Idle";
			break;
		case 2:
			ugStatus="The status on the upgrade functionality is Installing";	
			break;
		case 3:
			ugStatus="The status on the upgrade functionality is Installed";	
			break;
		case 4:
			ugStatus="The status on the upgrade functionality is Activating";	
			break;
		case 5:
			ugStatus="The status on the upgrade functionality is activated";	
			break;
		case 6:
			ugStatus="The status on the upgrade functionality is committing";	
			break;
		case 7:
			ugStatus="The status on the upgrade functionality is committed";	
			break;
		case 8:
			ugStatus="The status on the upgrade functionality is RollingBack";
			break;
		case 9:
			ugStatus="The status on the upgrade functionality is Rolledback";	
			break;

		default:
			break;


		}
		return ugStatus;
	}

	// AXD axdbgpPeerState
	public static String getaxdbgpPeerState(String Name,String Value)
	{
		String bgpPeerState="";
		switch(Integer.parseInt(Value.toString()))
		{	
		case 1:
			bgpPeerState="The BGP peer connection state is Idle";
			break;

		case 2:
			bgpPeerState="The BGP peer connection state is Connect";
			break;

		case 3:
			bgpPeerState="The BGP peer connection state is Active";
			break;

		case 4:
			bgpPeerState="The BGP peer connection state is OpenSent";
			break;

		case 5:
			bgpPeerState="The BGP peer connection state is OpenConfirm";
			break;

		case 6:
			bgpPeerState="The BGP peer connection state is Established";
			break;

		default:
			break;
		}
		return bgpPeerState;
	}

	// AXD axdospfNbrState
	public static String getaxdospfNbrState(String Name,String Value)
	{
		String ospfNbrState="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("down").append("\n").toString();
			ospfNbrState="down";
			break;
		case 2:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("attempt").append("\n").toString();
			ospfNbrState="attempt";
			break;
		case 3:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("init").append("\n").toString();
			ospfNbrState="init";
			break;
		case 4:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("twoway").append("\n").toString();
			ospfNbrState="twoway";
			break;
		case 5:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("ExchangeStart").append("\n").toString();
			ospfNbrState="ExchangeStart";
			break;
		case 6:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("exchange").append("\n").toString();
			ospfNbrState="exchange";
			break;
		case 7:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("Loading").append("\n").toString();
			ospfNbrState="Loading";
			break;
		case 8:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("Full").append("\n").toString();
			ospfNbrState="Full";
			break;
		default:
			break;
		}
		return ospfNbrState;
	}

	// AXD axdospfNbrState
	public static String getaxdisisISAdjState(String Name,String Value)
	{
		String isisISAdjState="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: isisISAdjState="The state of the adjacency is down";
		break;
		case 2: isisISAdjState="The state of the adjacency is initializing";
		break;
		case 3: isisISAdjState="The state of the adjacency is up";
		break;	
		case 4: isisISAdjState="The state of the adjacency is failed";
		break;
		default: 
			break;
		}
		return isisISAdjState;
	}

	// AXD axdospfNbrState
	public static String getaxdmigLoopbackStatus(String Name,String Value)
	{
		String migLoopbackStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: migLoopbackStatus="The state of the permanently looped timeslots for LoopBack is failure";
		break;
		case 2:migLoopbackStatus="The state of the permanently looped timeslots for LoopBack is success";
		break;
		default: break;
		}
		return migLoopbackStatus;
	}
	// AXD axdpingNotificationType
	public static String getaxdpingNotificationType(String Name,String Value)
	{
		String pingNotificationType="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: 
			pingNotificationType="ProbeFailure";

			break;
		case 2: 
			pingNotificationType="TestFailure";

			break;
		case 3: 
			pingNotificationType="TestCompletion";

			break;
		default: break;
		}
		return pingNotificationType;
	}

	// AXD axdpingNotificationType
	public static String getaxdpingEventCtlTestName(String Name,String Value)
	{
		String pingEventCtlTestName="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: 
			pingEventCtlTestName="ProbeFailure";

			break;
		case 2: 
			pingEventCtlTestName="TestFailure";

			break;
		case 3: 
			pingEventCtlTestName="TestCompletion";

			break;
		default: break;
		}
		return pingEventCtlTestName;
	}

	// AXD getaxdpingResultsOperStatus
	public static String getaxdpingResultsOperStatus(String Name,String Value)
	{
		String pingResultsOperStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: 

			pingResultsOperStatus= "enabled";
			break;
		case 2: 

			pingResultsOperStatus= "disabled";
			break;	
		default: break;
		}
		return pingResultsOperStatus;
	}

	// AXD getaxdpingResultsOperStatus
	public static String getaxdtraceRouteNotificationType(String Name,String Value)
	{
		String traceRouteNotificationType="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: 

			traceRouteNotificationType= "pathChange";
			break;
		case 2: 

			traceRouteNotificationType=  "testFailure";
			break;
		case 3: 

			traceRouteNotificationType=  "testCompletion";
			break;
		default: break;

		}
		return traceRouteNotificationType;
	}


	// AXD getaxdpingResultsOperStatus
	public static String getaxdifMauJabberState(String Name,String Value)
	{
		String ifMauJabberState="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: 

			ifMauJabberState= "other";
			break;
		case 2: 

			ifMauJabberState= "unknown";
			break;		
		case 3: 
			ifMauJabberState= "noJabber";

			break;	
		case 4: 


			ifMauJabberState= "jabbering";
			break;	
		default: break;
		}
		return ifMauJabberState;
	}

	// AXD gcpLinkReasonCode
	public static String getaxdgcpLinkReasonCode(String Name,String Value)
	{
		String gcpLinkReasonCode="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: 
			gcpLinkReasonCode="Link communication is down.";

			break;
		case 2: 
			gcpLinkReasonCode="Link communication is up.";

			break;
		default: break;
		}
		return gcpLinkReasonCode;
	}

	// AXD gcpLinkReasonCode
	public static String getaxdlogRecAcStatus(String Name,String Value)
	{
		String logRecAcStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: 
			logRecAcStatus="LogRecAcStatus=idle";

			break;
		case 2: 
			logRecAcStatus="LogRecAcStatus=executing";

			break;
		case 3: 
			logRecAcStatus="LogRecAcStatus=abort";

			break;
		case 4: 
			logRecAcStatus="LogRecAcStatus=abortOk";

			break;
		case 5: 
			logRecAcStatus="LogRecAcStatus=doneOk";

			break;
		case 6: 
			logRecAcStatus="LogRecAcStatus=doneFailure";

			break;
		default: break;
		}
		return logRecAcStatus;
	}

	// AXD secOperation
	public static String getaxdsecOperation(String Name,String Value)
	{
		String secOperation="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			secOperation="Operation type when security data is changed=new";
			break;
		case 2: 
			secOperation="Operation type when security data is changed=changed";
			break;
		case 3: 
			secOperation="Operation type when security data is changed=deleted";
			break;
		default: break;
		}
		return secOperation;
	}

	// AXD secOperation
	public static String getaxdmrErrorCode(String Name,String Value)
	{
		String mrErrorCode="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: mrErrorCode="The mesurement Error Code=unSpecified"; 
		break;
		case 2: mrErrorCode="The mesurement Error Code=noSuchObject"; 
		break; 
		case 3: mrErrorCode="The mesurement Error Code=noSuchInstance"; 
		break; 
		case 4: mrErrorCode="The mesurement Error Code=hardwareFault"; 
		break; 
		case 5: mrErrorCode="The mesurement Error Code=noDataAvailable"; 
		break; 
		case 6: mrErrorCode="The mesurement Error Code=other"; 
		break; 
		case 7: mrErrorCode="The mesurement Error Code=noSuchName"; 
		break; 
		case 8: mrErrorCode="The mesurement Error Code=badOid"; 
		break;
		default: break;
		}
		return mrErrorCode;
	}

	// AXD secOperation
	public static String getaxdperfFileFcode(String Name,String Value)
	{
		String PerfFileFcode="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1: 
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("echunck").append("\n").toString();
			PerfFileFcode="echuck";
			break;
		case 2: 
			PerfFileFcode="eclosed";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("enclosed").append("\n").toString();
			break;
		case 3: 
			PerfFileFcode="econn";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("econn").append("\n").toString();
			break;
		case 4: 
			PerfFileFcode="ehost";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("ehost").append("\n").toString();
			break;
		case 5: 
			PerfFileFcode="elogin";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("elogin").append("\n").toString();
			break;
		case 6: 
			PerfFileFcode="enotbinary";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("enotbinary").append("\n").toString();
			break;
		case 7: 
			PerfFileFcode="epath";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("epath").append("\n").toString();
			break;
		case 8: 
			PerfFileFcode="etype";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("etype").append("\n").toString();
			break;
		case 9: PerfFileFcode="euser";
		// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("euser").append("\n").toString();
		break;
		case 10: PerfFileFcode="eunknown";
		// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("eunknown").append("\n").toString();
		break;
		default: break;
		}
		return PerfFileFcode;
	}

	// AXD secOperation
	public static String getaxdmplsXCAdminStatus(String Name,String Value)
	{
		String mplsXCAdminStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			mplsXCAdminStatus="If AdminStatus is=UP";

			break;
		case 2:
			mplsXCAdminStatus="If AdminStatus is=Down";

			break;
		case 3:
			mplsXCAdminStatus="If AdminStatus is=TESTING";

			break;
		default: break;
		}
		return mplsXCAdminStatus;
	}

	// AXD secOperation
	public static String getaxdrsvpIfOperStatus(String Name,String Value)
	{
		String rsvpIfOperStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			rsvpIfOperStatus="An event reporting that operational state of the RSVP rsvpIfOperStatus=up";
			break;
		case 2:
			rsvpIfOperStatus="An event reporting that operational state of the RSVP rsvpIfOperStatus=down";
			break;
		case 3:
			rsvpIfOperStatus="An event reporting that operational state of the RSVP rsvpIfOperStatus=grrestart";
			break;
		default: break;
		}
		return rsvpIfOperStatus;
	}

	// AXD axdmplsIfOperStatus
	public static String getaxdmplsIfOperStatus(String Name,String Value)
	{
		String mplsIfOperStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			mplsIfOperStatus="An event reporting that an interface state  has changed to up";
			break;
		case 2:
			mplsIfOperStatus="An event reporting that an interface state  has changed to down";
			break;
		default: break;
		}
		return mplsIfOperStatus;
	}

	// AXD axdmplsIfOperStatus
	public static String getaxdmplsLdpSesState(String Name,String Value)
	{
		String mplsLdpSesState="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			mplsLdpSesState="An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState=nonexistent";
			break;
		case 2:
			mplsLdpSesState="An event reporting that the LDP session state,is changed to or from operational and  mplsLdpSesState=initialized";
			break;
		case 3:
			mplsLdpSesState="An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState=openrec";
			break;
		case 4:
			mplsLdpSesState="An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState=opensent";
			break;
		case 5:
			mplsLdpSesState="An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState=operational";
			break;
		default:break;
		}
		return mplsLdpSesState;
	}

	// AXD axdmplsIfOperStatus
	public static String getspvcOperationIndicator(String Name,String Value)
	{
		String spvcOperationIndicator="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			spvcOperationIndicator="create";
			break;
		case 2:
			spvcOperationIndicator="delete";
			break;
		case 3:
			spvcOperationIndicator="restart";
			break;
		case 4:
			spvcOperationIndicator="reroute";
			break;
		default: break;
		}
		return spvcOperationIndicator;
	}

	// AXD axdmplsIfOperStatus
	public static String getaxdifadmin(String Name,String Value)
	{
		String ifadmin="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			ifadmin="up";
			break;
		case 2:
			ifadmin="down";
			break;
		case 3:
			ifadmin="testing";
			break;
		default: break;
		}
		return ifadmin;
	}

	// AXD axdmplsIfOperStatus
	public static String getaxdifoper(String Name,String Value)
	{
		String ifoper="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			ifoper="If OperStatus is=up";
			break;
		case 2:
			ifoper="If OperStatus is=down";
			break;
		case 3:
			ifoper="If OperStatus is=testing";
			break;
		case 4:
			ifoper="If OperStatus is=Unknown";
			break;
		case 5:
			ifoper="If OperStatus is=dormant";
			break;
		case 6:
			ifoper="If OperStatus is=notPresent";
			break;
		case 7:
			ifoper="If OperStatus is=lowerLayerDown";
			break;
		default: break;

		}
		return ifoper;
	}

	// AXD axd301dnaInterfaceEnteringMethod
	public static String getaxd301dnaInterfaceEnteringMethod(String Name,String Value)
	{
		String axd301dnaInterfaceEnteringMethod="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			axd301dnaInterfaceEnteringMethod="manual";
			break;
		case 2:
			axd301dnaInterfaceEnteringMethod="automatic";
			break;
		default: break;
		}
		return axd301dnaInterfaceEnteringMethod;
	}

	// AXD axd301dnaInterfaceEnteringMethod
	public static String getaxdatmfVccOperStatus(String Name,String Value)
	{
		String atmfVccOperStatus="";
		switch(Integer.parseInt(Value.toString()))
		{
		case 1:
			atmfVccOperStatus="Unknown";

			break;
		case 2:
			atmfVccOperStatus="End2EndUp";

			break;
		case 3:
			atmfVccOperStatus="End2EndDown";

			break;
		case 4:
			atmfVccOperStatus="localUpEnd2endUnknown";

			break;
		case 5:
			atmfVccOperStatus="localDown";

			break;
		default:break;
		}
		return atmfVccOperStatus;
	}


	//  axdSev = ".1.3.6.1.4.1.193.14.1.1.3.2.2.3.1.6";
	public static final String  axdSev = ".1.3.6.1.4.1.193.14.1.1.3.2.2.3.1.6";

	//ESA-SNF,For Counting XML elements
	@SuppressWarnings("rawtypes")
	public static ArrayList xmlEleList = new ArrayList();
	//ESA-SNF CONSTANTS

	public static final String alarmModelDescription = ".1.3.6.1.4.1.193.110.2.666.1.1.2.1.6";
	public static final String alarmActiveResourceId = ".1.3.6.1.4.1.193.110.2.666.1.2.2.1.10";

	public static final String alarmActiveDescription = ".1.3.6.1.4.1.193.110.2.666.1.2.2.1.11";

	public static final String ituAlarmEventTypeB = ".1.3.6.1.4.1.193.110.2.667.1.1.1.1.2";

	public static final String ituProbableCauseB = ".1.3.6.1.4.1.193.110.2.667.1.1.1.1.3";

	public static final String ituAlarmEventType = ".1.3.6.1.2.1.121.1.1.1.1.2";

	public static final String ituProbableCause = ".1.3.6.1.2.1.121.1.1.1.1.3";

	// MMC CONSTANTS

	public static String eventTime = "The time the event or alarm was sent: ";
	public static String filterDescript = "The name of the filter is : ";
	public static String rdbmsState = " The state of this server's access to this database is: ";
	public static String numOutofSpaceAccesses = "The number of times the server has been unable to obtain disk spece since startup is : ";
	public static String genericListenerState = "The current state of the Generic Listener is : ";
	public static String eventName = "The name of the event that occurred is ";
	public static String eventId = "The ID of the registration that generated this event occurrence is ";
	public static String eventService = "The name of the service being monitored by this event is ";
	public static String eventUser = "The name of the user who registered for this event is ";
	public static String eventAppId = "The Id of the Enterprise Manager console application through which this event was registered is ";
	public static String eventArgs = "The arguments that were passed to the script that detected the event are : ";
	public static String eventResults = "The results generated by the script that detected the event are : ";

	public static String merOpState = "Operational state of MER component is : ";
	public static String merAdminState = "Adminstrative state of MER component is : ";
	public static String repQSize = "Maximum number of MDRs waiting for transmission to storage is : ";
	public static String repQlevel = "Actual number of MDRs waiting for transmission to storage is : ";
	public static String radiusAccQLevel = "Actual Number of events in the queue is : ";
	public static String radiusAccQSize = "Maximum Number of events in the queue is : ";

	public static String mmsCmSystemOpState = "The Operational state of the system is : ";
	public static String mmsCmSystemAdState = "Adminstrative state of the system is : ";

	public static String alarmOBjClass = "The class of the network resources associated with the event or alarm: ";
	public static String alarmOBjInstance = "The instance (of a class) of the network resource associated with the event or alarm: ";

	public static String coldStart = "A coldStart trap signifies that the sending protocol entity is reinitializing itself such that the agent's configuration or the protocol entity implementation may be  altered.";
	public static String warmStart = "A warmStart trap signifies that the sending protocol entity is reinitializing itself such that neither the agent configuration nor the protocol entity implementation is altered.";
	public static String linkDown = "A linkDown trap signifies that the sending protocol entity recognizes a failure in one of the communication links represented in the agent's configuration.";
	public static String linkUp = "A linkUp trap signifies that the sending protocol entity recognizes that one of the communication links  represented in the agent's configuration has come up.";
	public static String authenticationFailure = "An authenticationFailure trap signifies that the sending protocol entity is the addressee of a protocol message that is not properly authenticated.";
	public static String egpNeighborLoss = "An egpNeighborLoss trap signifies that an EGP neighbor for whom the sending protocol entity was an EGP peer has been marked down and the peer relationship no longer obtains.";

	public static String IfAdminState = "The Adminstrative State of the Interface is: ";
	public static String IfOperState = "The operational State of the Interface is: ";

	//RAD CONSTANTS

	public static String TrapVarComp = "The Component that issued the Trap is ";
	public static String TrapVarSWName = "RADIUS Server Software Identity is ";
	public static String TrapVarThreadsAvail = "The number of threads available in the thread worker pool is ";
	public static String TrapVarBytesAvail = "The number of bytes available in the file system is ";
	public static String TrapVarPrivateDir = "The path to the private directory used by the RADIUS server is ";
	public static String TrapVarNumberOfOccurrences = "The dilution factor for the trap is ";
	public static String TrapVarSQLConnects = "The number of connection attempts to a SQL database is ";
	public static String TrapVarSQLDisconnects ="The number of disconnects from a SQL database is ";
	public static String TrapVarSQLTimeouts = "The number of timeouts encountered on SQL Database transactions is ";
	public static String TrapVarServiceDispatcherErrCode = "Error code returned in an attempt to start the RADIUS service on Windows NT is ";
	public static String TrapVarSetStatusErrCode = "Error Code returned from service control dispatcher is ";
	public static String TrapVarGetDiskFreeSpaceErrCode = "The error code returned in response GetDiskFreeSpaceEx is ";
	public static String TrapVarIniString = "The INI file setting is ";
	public static String TrapVarDbType = "The type of database is ";
	public static String TrapVarFailedSystemName = "The name of the remote system failing connectivity is ";
	public static String TrapVarUserName = "The name of the user is ";
	public static String TrapVarPersistStoreName = "The name of the persistent storage is ";
	public static String TrapVarDiagnosticMessage = "Possible root causes of the trap is ";
	public static String TrapVarIPAddrPoolName = "The name of the IP address pool is ";
	public static String TrapVarIPAddrAvail = "The number of addresses available in the IP address pool is ";


}
