package com.ericsson.oss.mediation.snmp.synchronisation;

import java.io.*;
import java.util.*;

import javax.xml.parsers.*;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.*;

public class EsaMIBHandling {
	
	// returns the array of IF MIB alarms notifications
	//For Counting XML elements
	@SuppressWarnings("rawtypes")
	private static ArrayList xmlEleList = new ArrayList();
    String moifinal="",moi1="",moi2="",moi3="",Ecode = "";
	String stralrmoddesc = " ";
	String stralrmactvid = " ";
	String strituet = " ";
	String strituPS = " ";
	StringTokenizer ituet=null;
	StringTokenizer ituPS=null;
	StringTokenizer alname=null;
	StringTokenizer almodindx=null;;
	StringTokenizer almodestat=null;
	StringTokenizer alrmoddesc=null;
	StringTokenizer alrmactvid=null;
	StringTokenizer aldate=null;
	StringTokenizer aldateo11=null;
	StringTokenizer alactvindx=null;
	 int countDate = 0;
	 String octet11OID;
		// <!-- ESA ALARM MIB-->
		StringBuffer alarmNameBuf = new StringBuffer();
		StringBuffer alarmModIndexBuf = new StringBuffer();
		StringBuffer alarmModStateBuf = new StringBuffer();
		StringBuffer aldateBuf = new StringBuffer();
		StringBuffer aldateo11Buf = new StringBuffer();
		StringBuffer alactIndexBuf = new StringBuffer();
		
		int alarmcount = 0; //Alarm Model Description
		int an = 0;			   //Alarm Name
		int ami = 0;		   //Alarm Model Index
		int almodst = 0;      //Alarm Model State
		int actid = 0;        //Alarm Active Id
		int ald = 0; 			   //Alarm date
		int aldo11 = 0;    //Alarm Dtae 11 octets
		int aid = 0; 		   //Alarm active index
		int et = 0;                //Event Type
		int ps = 0;                //Perceived Severity

	
	@SuppressWarnings("deprecation")
	public SynchronizationEventNotification[] getesaalarm(SNMPManagedElement me,Map<String,StringTokenizer> snmpWalkMap,int alarmcount){
         int count=0;
         SynchronizationEventNotification notif[] = new SynchronizationEventNotification[alarmcount];
         StringTokenizer alrmoddesc=snmpWalkMap.get("alarmModelDescription");
         StringTokenizer alrmactvid=alrmactvid = snmpWalkMap.get("alarmActiveResourceId");

		for(int esacount = 0; esacount < alarmcount ;++esacount)
		{
			System.out.println("************************* Count =  " +count);
			notif[count] = new SynchronizationEventNotification();
			notif[count].setFmEventType("ALARM");
			notif[count].addAdditionalAttribute("SNMPTrapOID","null");
			notif[count].addAdditionalAttribute("Specific","null");
			notif[count].addAdditionalAttribute("IPAddress",me.getIpAddress());
			notif[count].addAdditionalAttribute("Version",me.getProtocolVersion());
			notif[count].addAdditionalAttribute("Enterprise","True");
			notif[count].setEventType("2");//Communication Alarm

			StringBuffer str = new StringBuffer();
			String  tempSP="";


			//I. Alarm Model Description  -> Setting it to Specific Problem
			//   Alarm Active Description -> Setting it to Problem Text
			try
			{
				// Mapping -> Specific Problem
				System.out.println("1.Fetching Alarm Model Description values from ESA Alarm Table");
				String amdstring = alrmoddesc.nextToken();
				System.out.println("1.Alarm Model Description received from the Node: " + amdstring);

				//Included HL 94128 fix
				//notif[count].setSpecificProblem(amdstring);
				tempSP = amdstring;

				// Mapping -> Problem Text
				alrmoddesc.nextToken();
				System.out.println("2.Fetching Alarm Active Description values from ESA Alarm Table");
				String actdesstring = alrmoddesc.nextToken();
				System.out.println("2.Alarm Active Description received from the Node: " + actdesstring);
				str.append("\nAlarm Active Description = "+actdesstring);
				alrmoddesc.nextToken();
				alrmoddesc.nextToken();


			}
			catch(Exception a1)
			{
				System.out.println("**I.Exception while processing while setting Alarm Model Description,Alarm Active Description");
			}




			//II.Alarm Active Resource ID -> Setting it to MOI and External Event Id
			try
			{

				System.out.println("3.Fetching Alarm Active Resource ID values from ESA Alarm Table");
				//alrmactvid.nextToken();
				String acidstring = alrmactvid.nextToken();

				
				System.out.println("alarmActiveResourceID : "+acidstring); 
				str.append("\nAlarm Active ID = " +acidstring);

				String parentStr = null;
				String childStr = null;
				moi1=acidstring; //Setting into ExternalEventId

				System.out.println("3.Alarm Active Resource ID received from the Node: " + acidstring);
				if(getResourceOIDName(acidstring.substring(1,acidstring.length()-2)) != null && getResourceOIDName(acidstring.substring(1,acidstring.length())) != null)
					notif[count].setManagedObjectInstance("Component="+getResourceOIDName(acidstring.substring(1,acidstring.length()-2))+",Component="+getResourceOIDName(acidstring.substring(1,acidstring.length())));

			}
			catch(Exception a2)
			{
				System.out.println("** II.Exception while processing Alarm Active Resource ID");
			}



			//III.Itu Alarm Event Type -> Setting it to Event Type
			try
			{
				//Mapping -> Event Type
				System.out.println("4.Fetching ITU Event Type values from ESA Alarm Table");
				ituet.nextToken();
				ituet.nextToken();
				ituet.nextToken();
				String etstring = ituet.nextToken();
				System.out.println("4.ITU Event Type received from the Node: " + etstring);
				String ituEventType=HandleEventType.getituAlarmEventType(etstring);
				notif[count].setEventType(ituEventType);

			}
			catch(Exception a4)
			{
				System.out.println("*** III. Exception while processing Alarm Event Type");
				//Default attributes are set here !!!
				notif[count].setEventType("0");//Unknown
			}

			//IV.ITU Perceived Severity -> Setting it to Perceived Severity
			try
			{
				System.out.println("5.Fetching ITU Perceived Severity values from ESA Alarm Table");
				ituPS.nextToken();
				ituPS.nextToken();
				ituPS.nextToken();
				String psstring = ituPS.nextToken();
				System.out.println("5.Perceived Severity received from the Node: " + psstring);
				String psevrty = psstring.substring(psstring.lastIndexOf(".")+1);
				int lastIndex = psstring.lastIndexOf(".");
				int lastButOneIndex = psstring.lastIndexOf(".", lastIndex-1);
				System.out.println("lastIndex::" + lastIndex + "lastButOneIndex::" + lastButOneIndex);
				Ecode = psstring.substring(lastButOneIndex+1, lastIndex);
				System.out.println("5.Perceived Severity is found to be: " + psevrty);
				System.out.println("Error code is found to be: " + Ecode);
				ituPS.nextToken();

				String ituseverity=HandleAlarmSeverity.getEFWSAlarmSeverity(psevrty);
				notif[count].setSeverity(ituseverity);
			}
			catch(Exception a6)
			{
				System.out.println("***IV.Exception while processing Perceived Severity");
				notif[count].setSeverity("INDETERMINATE");
			}


			//6.Alarm List Name -> Setting it to Problem Text and MOI
			try
			{
				System.out.println("6.Fetching Alarm List Name values from ESA Alarm Table");
				String alnstring = alname.nextToken();
				System.out.println("6.Alarm List Name received from the Node: " + alnstring);
				alname.nextToken();
				alname.nextToken();
				alname.nextToken();
				alname.nextToken();
				str.append("\nAlarm List Name = "+alnstring); //Problem Text
				moi2=alnstring; //MOI

			}
			catch(Exception a7)
			{
				System.out.println("**** Exception while processing 7.Alarm List Name");
			}


			//7.Alarm Model Index -> Setting it to MOI
			try
			{
				System.out.println("7.Fetching Alarm Model Index values from ESA Alarm Table");
				String amistring = almodindx.nextToken();
				System.out.println("7.Alarm Model String received from the Node: " + amistring);
				moi3=amistring;

			
				str.append("\nAlarm Error Code=" + Ecode);
			
			}
			catch(Exception a8)
			{
				System.out.println("*****Exception while processing 8.Alarm Model Index");
			}



			//8.Alarm Active Date and Time -> Setting it to Problem Text
			try
			{

				System.out.println("8.Fetching Alarm Active Date and Time values from ESA Alarm Table");
				String adtstring = aldate.nextToken();
				String adtstringo11 = aldateo11.nextToken();
				System.out.println("8.Alarm Active Date and Time received from the Node: " + adtstring);
				System.out.println("8.Alarm Active Date and Time received from the Node(11 chars): " + adtstringo11);

				// Now timeval will comtain in the form of Mon Jan 09 13:57:09 MET 2006


				try
				{

					if(countDate == 28)
					{
						//System.out.println("--- Setting 28 Octets Date & Time Format ");
						Date octet28Date =HandleTimeTranslation.createAlarmDateAndTime(adtstring,countDate);
						notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(octet28Date)).toString());
						notif[count].setTimeZone("UTC");
					}

					else if(countDate == 11)
					{
						//System.out.println("--- Setting 11  Octets Date & Time Format ");
						Date octet11Date = HandleTimeTranslation.createAlarmDateAndTime(adtstringo11);
						notif[count].setTime(new Date(HandleTimeTranslation._timeFormatter.format(octet11Date)).toString());
						notif[count].setTimeZone(HandleTimeTranslation.getTimeZone(adtstringo11));

					}

					else
					{
						System.out.println("--- Setting Current Date & Time Format ");
						notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
					}
				}catch (Exception ae)
				{
					System.out.println("ESAActionHandler>>Translator::Date Format Exception: " + aldateBuf.toString());
					System.out.println("ESAActionHandler::setTime(): "+HandleTimeTranslation.getCurrentTime());
					//Exception thrown while processing Alarm Active Date and Time
					//Hence setting the Local Current Time
					notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
				}


			}
			catch(Exception a9)
			{
				System.out.println("*****Exception while processing Alarm Active Date and Time");
				notif[count].setTime(HandleTimeTranslation.getCurrentTime().toString());
			}


			//9.Alarm Model State -> Setting it to Problem Text
			try
			{
				System.out.println("9.Fetching Alarm Model State values from ESA Alarm Table");
				String amsstring = almodestat.nextToken();
				System.out.println("9.Alarm Model State received from the Node: " + amsstring);
				str.append("\nAlarm Model State = "+amsstring);
			}
			catch(Exception a10)
			{
				System.out.println("*****Exception while processing 10.Alarm Model State");
			}


			//10.Alarm Active Index -> Setting it to Problem Text
			try
			{
				System.out.println("10.Fetching Alarm Active Index values from ESA Alarm Table");
				String avidstring = alactvindx.nextToken();
				System.out.println("10.Alarm Active Index received from the Node: " + avidstring);
				str.append("\nAlarm Active Index = "+avidstring);
			}
			catch(Exception a11)
			{
				System.out.println("Exception while processing 11.Alarm Active Index");
			}


			System.out.println(" Additional text " + str.toString());

			// Set the additional text attribute
			notif[count].addAdditionalAttribute("additionalText",str.toString());

			//Setting MOI Based on Active Resource ID, alarm List Name and Alarm Model Index
			moifinal = moi1 + "," + moi2 + "," + moi3;
			System.out.println("Setting External Event ID into :" + moifinal);

			try
			{
				//Mapping -> Probable Cause
				System.out.println("11.Fetching Probable Cause from ESA Alarm Table");
				String pcstring = ituet.nextToken();
				System.out.println("11.ITU Probable Cause received from the Node: " + pcstring);
				notif[count].setProbableCause(pcstring + moifinal);
			}
			catch(Exception a4)
			{
				System.out.println("*** III. Exception while processing Probable Cause");
				a4.printStackTrace();
				notif[count].setProbableCause("0"); //Indeterminate
			}

			notif[count].setSpecificProblem(tempSP);


			//Increment the count
			count++;
		}
		
	    return notif;
	}
	
	public int countEsaMibAlarm(Map<String, StringTokenizer>snmpWalkMap){


		alrmoddesc =snmpWalkMap.get("alarmModelDescription");
		//Call to the method processAlIndex, to process the index of Alarm Model description
		try
		{
			//processAlIndex(sbAlNameModelIndex.toString(),alarmNameBuf, alarmModIndexBuf,alarmModStateBuf);
		}
		catch(Exception e)
		{
			System.out.println(" Problem in extracting the attribute values"+
					" from the alarmModeldescription OID " );
			e.printStackTrace();

		}

		alname = new StringTokenizer(alarmNameBuf.toString(),"\274");
		almodindx = new StringTokenizer(alarmModIndexBuf.toString(),"\274");
		almodestat = new StringTokenizer(alarmModStateBuf.toString(),"\274");

		alrmactvid = snmpWalkMap.get("alarmActiveResourceId");
		aldateBuf = new StringBuffer();
		aldateo11Buf = new StringBuffer();
		alactIndexBuf = new StringBuffer();

		try
		{
			//processActvIndex (sbAlDateActvIndex.toString(), aldateBuf,alactIndexBuf,aldateo11Buf);
		}
		catch(Exception e)
		{
			System.out.println(" Problem in getting the attribute values from the"+
					" alarmActiveResourceId ");
			e.printStackTrace();
		}

		aldate = new StringTokenizer(aldateBuf.toString(),"\274");
		aldateo11 = new StringTokenizer(aldateo11Buf.toString(),"\274");
		alactvindx = new StringTokenizer(alactIndexBuf.toString(),"\274");

		System.out.println("value for date is::" + aldateBuf.toString());
		System.out.println("value for date is(for 11 chars)::" + aldateo11Buf.toString());


		// 3.****** alarmActiveDescription ***** can be found using alarmActiveVariableOctetStringVal.
		// We are already processing alarmActiveVariableOctetStringVal to fetch alarmModelDescription


		// 4. ***** ituAlarmEventType ********
		// alarmActiveVariableInteger32Val from which you can fetch ituAlarmEventType and ituProbableCause

		strituet = snmpWalkMap.get("ituAlarmEventType").toString();

		ituet = new StringTokenizer(strituet,"\274");

		// 5.********* ituPerceivedSeverity ***********
		// will be fetched from alarmActiveVariableID

		strituPS = snmpWalkMap.get("ituPerceivedSeverity").toString();

		ituPS = new StringTokenizer(strituPS,"\274");

		/************************ ALARM ENTRIES COUNT********************************************
			      // To count no of Entries in ESA Alarm Table
			      // alarmModelDescription tokens are counted, each entry will have 5 entries for 1 alarm.
			      // alarm count is identified based on the tokens divided by 5
		 *****************************************************************************************/
		alarmcount = alrmoddesc.countTokens()/5; //Alarm Model Description
		an = alname.countTokens();			   //Alarm Name
		ami = almodindx.countTokens();		   //Alarm Model Index
		almodst = almodestat.countTokens();      //Alarm Model State
		actid = alrmactvid.countTokens();        //Alarm Active Id
		ald = aldate.countTokens(); 			   //Alarm date
		aldo11 = aldateo11.countTokens();    //Alarm Dtae 11 octets
		aid = alactvindx.countTokens(); 		   //Alarm active index
		et = ituet.countTokens();                //Event Type
		ps = ituPS.countTokens();                //Perceived Severity

		// Prints no of Entries in ESA Alarm Table
		System.out.println("Entries in ESA Alarm Table************************************************");
		System.out.println(":: Size of Alarm Model Description Entry" + alarmcount);
		System.out.println(":: Size of Alarm List Name" + an);
		System.out.println(":: Size of Alarm Model Index" + ami);
		System.out.println(":: Size of Alarm Model State" + almodst);
		System.out.println(":: Size of Alarm Resource ID Entry" + actid);
		System.out.println(":: Size of Alarm Date" + ald);
		System.out.println(":: Size of Alarm Date 11 octets" + aldo11);
		System.out.println(":: Size of Alarm Event Type Entry" + et);
		System.out.println(":: Size of Alarm Active Index" + aid);
		System.out.println(":: Size of Alarm Perceived Severity" + ps);
		System.out.println("Entries in ESA Alarm Table************************************************");

	     return alarmcount;
	}
	
	public static String getResourceOIDName(String OID) 
	{

		DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder docBuilder=null;
		Document doc = null;
		String strNodeName = null;
		String strNeName = null;
		File xmlFile; 

		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		}
		catch (ParserConfigurationException e) {
			System.out.println("Wrong parser configuration: " + e.getMessage());
		}

		try 
		{
			// need to check this here
			//System.out.println("--- The agent FDN from Supervision class : "+ESASupervision.strFDN);
			//String agentFDN =agentFDN.toString();
			String agentFDN ="agentFDN";
			String strFDN = agentFDN.substring(agentFDN.lastIndexOf("=")+1, agentFDN.length());
			strNeName = strFDN; 
			xmlFile = new File("/etc/opt/ericsson/nms_umts_cnoss_efmesa/xml/"+strNeName.concat(".xml"));
			doc = docBuilder.parse(xmlFile);
			Node root = doc.getDocumentElement();
			xmlEleList.add(doc.getDocumentElement().getNodeName());
			writeDocumentToOutput(root);
			NamedNodeMap nnm = null;

			for(int s=0;s<xmlEleList.size();s++){
				if((String)xmlEleList.get(s) != null){
					NodeList nL1 = doc.getElementsByTagName((String)xmlEleList.get(s));

					for(int i=0;i<nL1.getLength();i++){
						Node node = nL1.item(i);

						nnm = node.getAttributes();

						if(getNodeName(nnm,OID) != null)
							strNodeName = nnm.item(0).getNodeValue();
					}			
				}  	 
			}
		}
		catch (SAXException e) {
			System.out.println("Wrong XML file structure: " + e.getMessage());
		}
		catch(FileNotFoundException f){
			System.out.println("--- The XML File Not found in the corresponding PATH!!! ");
		}
		catch (IOException e) {
			System.out.println("Could not read source file: " + e.getMessage());
		}
		catch (Exception e) {
			System.out.println("Exception in getResourceOIDName Method:" + e.toString());
		}
		return strNodeName;
	}
	
	public static void writeDocumentToOutput(Node node) 
	{
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
	
	public static String getNodeName(NamedNodeMap nnm, String OID)
	{ 
		String strNodeName=null;
		for(int j=0;j<nnm.getLength();j++){
			if(nnm.item(j).getNodeValue().equals(OID))
				strNodeName = nnm.item(0).getNodeName();
		} 
		return strNodeName; 
	}

}
