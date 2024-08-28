package com.ericsson.oss.mediation.cacheapibean;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;

import com.adventnet.snmp.snmp2.SnmpOID;
import com.ericsson.oss.mediation.cache.model.AlarmTableEntry;
import com.ericsson.oss.mediation.cache.model.RiaModel;
import com.ericsson.oss.mediation.cacheapi.MibCachingInterface;
import com.ericsson.oss.mediation.translator.model.Constants;


public class MibCachingInterfaceBean implements MibCachingInterface {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(MibCachingInterfaceBean.class);
	static Document doc = null;

	private static Map<String, Map<String, Map<String, String>>> sourceTypeAsrapOidAttributeMap = new HashMap<String, Map<String, Map<String, String>>>();

	private static Map<String, Map<String, String>> alarmTrapOidASAttributeMap = new HashMap<String, Map<String, String>>();

	private static Map<String, String> attributeNameOIDMap = new HashMap<String, String>();	


	private static Map<String, Map<String, ArrayList<SnmpOID>>> syncMap = new HashMap<String, Map<String, ArrayList<SnmpOID>>>();

	private static Map<String, AlarmTableEntry> alarmTableEntryMap = new HashMap<String, AlarmTableEntry>();

	private static Map<String, Map<String, AlarmTableEntry>> fMAlarmTableAttributeMap = new HashMap<String, Map<String, AlarmTableEntry>>();

	private static Map<String, RiaModel> riaInfo = new HashMap<String, RiaModel>();

	public static File snmpXmlFolder = new File("/opt/ericsson/snmpXml");

	private static File file[] = new File[snmpXmlFolder.listFiles().length];


	public void readFileFromModelService() {

		LOGGER.info("READ ALL XML FOR CASHING.."+file.length);

		int i = 0;
		DataInputStream in = null;
		String xmlstring=null;
		for (final File fileEntry : snmpXmlFolder.listFiles()) {
			if (fileEntry.isFile()) {
				String name = fileEntry.getName();
				if ((name.substring(name.lastIndexOf('.') + 1, name.length())
						.toLowerCase()).equals("xml")) {
					LOGGER.info("Folder.getAbsolutePath() is"+snmpXmlFolder.getAbsolutePath());
					String snmpFile = snmpXmlFolder.getAbsolutePath() + "/"
							+ fileEntry.getName();
					LOGGER.info("snmpFile is"+snmpFile);
					file[i++] = new File(snmpFile);
				}
			}

		}


		for (int j = 0; j < file.length; j++) {
			if (file[j] == null)
				continue;
			else {
				try {
					if (file[j].getName().endsWith("Riainfo.xml")) {
						LOGGER.info(file[j]+":"+file[j].getName()+"abs:"+file[j].getAbsolutePath());
						byte[] buffer = new byte[(int) file[j].length()];
						in = new DataInputStream(new FileInputStream(file[j]));
						in.readFully(buffer);
						xmlstring = new String(buffer);
						buildRiaCache(xmlstring);
					} else {
						byte[] buffer = new byte[(int) file[j].length()];
						in = new DataInputStream(new FileInputStream(file[j]));
						in.readFully(buffer);
						xmlstring = new String(buffer);
						buildMibCache(xmlstring);
					} 
				}catch (FileNotFoundException e) {
					LOGGER.error("FileNotFoundException in readFileFromModelService"+e.toString());
				}catch (Exception e) {
					LOGGER.error("Exception in readFileFromModelService"+e.toString());
				}

			}
		}
	}


	private void buildRiaCache(String xmlstring) {
		try {
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();
			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlstring));
			Document doc = builder.parse(inputSource);
			doc.getDocumentElement().normalize();

			NodeList SourceTypeASnodes = doc
					.getElementsByTagName("SourceTypeAS");
			for (int j = 0; j < SourceTypeASnodes.getLength(); j++) {

				RiaModel riamodel = new RiaModel();
				Element SourceTypeASelement = (Element) SourceTypeASnodes
						.item(j);
				String SourceType = SourceTypeASelement
						.getAttribute("SourceType");

				NodeList comSupervisionOidASnodes = SourceTypeASelement
						.getElementsByTagName("ComSupervisionOidAS");

				for (int k = 0; k < comSupervisionOidASnodes.getLength(); k++) {

					Element SupervisionOIDASelement = (Element) comSupervisionOidASnodes
							.item(k);

					String ComSupervisionOID = SupervisionOIDASelement
							.getAttribute("ComSupervisionOID");
					riamodel.setComSupervisionOID(ComSupervisionOID);

					String type = SupervisionOIDASelement
							.getAttribute("ComSupervisionType");
					if (type == null) {
					} else {
						riamodel.setComSupervisionType(type);
					}

				}
				riaInfo.put(SourceType, riamodel);
			}
		} catch (Exception e) {
			LOGGER.error("Exception in buildRiaCache is: "+ e.getMessage());
		}

	}
	private void buildMibCache(String xmlstring) 
	{

		if(xmlstring == null){
			return;
		}

		Document doc = parseXmlFile(xmlstring);

		NodeList AlarmSupervisionnodes = doc.getElementsByTagName("AlarmSupervision");
		for (int i = 0; i < AlarmSupervisionnodes.getLength(); i++)
		{
			Element AlarmSupervisionelement = (Element) AlarmSupervisionnodes
					.item(i);
			NodeList SourceTypeASnodes = AlarmSupervisionelement
					.getElementsByTagName("SourceTypeAS");

			for (int j = 0; j < SourceTypeASnodes.getLength(); j++) 
			{
				Element SourceTypeASelement = (Element) SourceTypeASnodes.item(j);

				String SourceType = SourceTypeASelement.getAttribute("SourceType");
				NodeList AlarmTrapOidASnodes = SourceTypeASelement.getElementsByTagName("AlarmTrapOidAS");
				for (int k = 0; k < AlarmTrapOidASnodes.getLength(); k++)
				{
					Element element = (Element) AlarmTrapOidASnodes.item(k);
					String AlarmTrapOid = element.getAttribute("OID");
					NodeList AttributeOid = element.getElementsByTagName("AttributeOid");

					for (int l = 0; l < AttributeOid.getLength(); l++) 
					{
						Element line = (Element) AttributeOid.item(l);

						if (line.getAttribute("OID") != null
								&& line.getAttribute("AttributeName") != null) {
							attributeNameOIDMap.put(line.getAttribute("OID"),
									line.getAttribute("AttributeName"));
						}
					}

					alarmTrapOidASAttributeMap.put(AlarmTrapOid,attributeNameOIDMap);
				}
				sourceTypeAsrapOidAttributeMap.put(SourceType,alarmTrapOidASAttributeMap);

			}
		}


		NodeList SynchronizationNodeList = doc.getElementsByTagName("Synchronization");
		String SourceTypeSync = null;
		String mibType = null;
		for (int i = 0; i < SynchronizationNodeList.getLength(); i++) {
			Element AlarmSynchronizationelement = (Element) SynchronizationNodeList.item(i);
			NodeList SourceTypeSyncnodes = AlarmSynchronizationelement
					.getElementsByTagName("SourceTypeSync");
			Map<String, ArrayList<SnmpOID>> sourceMap = new HashMap<String, ArrayList<SnmpOID>>();
			ArrayList<SnmpOID> oidList=null;
			for (int j = 0; j < SourceTypeSyncnodes.getLength(); j++) {
				Element SourceTypeASelement = (Element) SourceTypeSyncnodes
						.item(j);
				SourceTypeSync = SourceTypeASelement.getAttribute("SourceType");

				NodeList AlarmTrapOidASSyncNodes = SourceTypeASelement
						.getElementsByTagName("MibTypeSync");



				for (int k = 0; k < AlarmTrapOidASSyncNodes.getLength(); k++)
				{
					Element alarmTrapOid = (Element) AlarmTrapOidASSyncNodes
							.item(k);
					mibType = alarmTrapOid.getAttribute("MibType");
					NodeList AttributeOid = alarmTrapOid
							.getElementsByTagName("AttributeOid");

					oidList = new ArrayList<SnmpOID>();

					for (int l = 0; l < AttributeOid.getLength(); l++) {

						Element line = (Element) AttributeOid.item(l);
						if (line.getAttribute("OID") != null
								&& line.getAttribute("AttributeName") != null) {

							oidList.add(new SnmpOID(line.getAttribute("OID")));
						}

					}
					sourceMap.put(mibType, oidList);
				}
				syncMap.put(SourceTypeSync, sourceMap);
			}
		}


		NodeList fMAlarmTableNodeList = doc
				.getElementsByTagName("FMAlarmTable");
		for (int i = 0; i < fMAlarmTableNodeList.getLength(); i++) {

			Element fMAlarmTableelement = (Element) fMAlarmTableNodeList
					.item(i);
			NodeList sourceTypeATnodes = fMAlarmTableelement
					.getElementsByTagName("SourceTypeAT");
			for (int k = 0; k < sourceTypeATnodes.getLength(); k++) {
				Element SourceTypeATelement = (Element) sourceTypeATnodes
						.item(k);
				String sourceTypeAT = SourceTypeATelement
						.getAttribute("SourceType");
				NodeList alarmTrapOidAT = SourceTypeATelement
						.getElementsByTagName("AlarmTrapOidAT");
				for (int j = 0; j < alarmTrapOidAT.getLength(); j++) {
					AlarmTableEntry alarmTableEntry = new AlarmTableEntry();
					alarmTableEntry.additionalValues = new HashMap<String, String>();

					Element element = (Element) alarmTrapOidAT.item(j);
					String AlarmTrapOid = element.getAttribute("OID");
					try {
						NodeList specificProblem = element
								.getElementsByTagName("SpecificProblem");

						if (specificProblem != null) {
							Element line = (Element) specificProblem.item(0);

							if (line != null) {

								String specificProblemName = line
										.getAttribute("AttributeName");
								String specificProblemValue = line
										.getAttribute("Value");

								if (specificProblemName != null
										&& specificProblemValue != null) {
									alarmTableEntry.setSpecificProblem(specificProblemValue);
								}
							}
						}
					} catch (Exception e) {
						LOGGER.error("Exception while retrive node specificProblemValue attribue List: "
								+ e.getMessage());
					}
					try {
						NodeList probableCause = element
								.getElementsByTagName("ProbableCause");
						if (probableCause != null) {
							Element line = (Element) probableCause.item(0);
							if (line != null) {
								String probableCauseName = line
										.getAttribute("AttributeName");

								String probableCauseValue = line
										.getAttribute("Value");

								if (probableCauseName != null
										&& probableCauseValue != null) {
									alarmTableEntry.setProbableCause(probableCauseValue);
								}
							}
						}
					} catch (Exception e) {
						LOGGER.error("Exception while retrive node attribue List: "+ e.getMessage());

					}
					try {
						NodeList eventType = element
								.getElementsByTagName("EventType");
						if (eventType != null) {
							Element line = (Element) eventType.item(0);
							if (line != null) {
								String eventTypeAttributeName = line
										.getAttribute("AttributeName");
								String eventTypeAttributeValue = line
										.getAttribute("Value");

								if (eventTypeAttributeName != null
										&& eventTypeAttributeValue != null) {
									alarmTableEntry.setEventType(eventTypeAttributeValue);
								}
							}
						}
					} catch (Exception e) {
						LOGGER.error("Exception in mib caching ET::"+e.getMessage());
					}

					try {
						NodeList perceivedSeverity = element
								.getElementsByTagName("PerceivedSeverity");
						if (perceivedSeverity != null) {
							Element line = (Element) perceivedSeverity.item(0);
							if (line != null) {
								String perceivedSeverityName = line
										.getAttribute("AttributeName");
								String perceivedSeverityValue = line
										.getAttribute("Value");

								if (perceivedSeverityName != null
										&& perceivedSeverityValue != null) {
									switch(perceivedSeverityValue){
									case Constants.SEV_CRITICAL:
										alarmTableEntry.setPerceivedSeverity(Constants.SEV_CRITICAL);
										break;
									case Constants.SEV_MAJOR:
										alarmTableEntry.setPerceivedSeverity(Constants.SEV_MAJOR);
										break;
									case Constants.SEV_MINOR:
										alarmTableEntry.setPerceivedSeverity(Constants.SEV_MINOR);
										break;
									case Constants.SEV_WARNING:
										alarmTableEntry.setPerceivedSeverity(Constants.SEV_WARNING);
										break;
									case Constants.SEV_CLEARED:
										alarmTableEntry.setPerceivedSeverity(Constants.SEV_CLEARED);
										break;
									case Constants.SEV_INDETERMINATE:
										alarmTableEntry.setPerceivedSeverity(Constants.SEV_INDETERMINATE);
										break;
									default:
										alarmTableEntry.setPerceivedSeverity(Constants.SEV_INDETERMINATE);
										break;
									}
								}

							}
						}

					}catch (Exception e) {
						LOGGER.error("General Exception in mib caching severity::"+e.getMessage());
					}

					try {
						NodeList notificationType = element
								.getElementsByTagName("NotificationType");
						if (notificationType != null) {
							Element line = (Element) notificationType.item(0);

							if (line != null) {

								String notificationTypeName = line
										.getAttribute("NotificationType");
								String notificationTypeValue = line
										.getAttribute("Value");

								if (notificationTypeName != null
										&& notificationTypeValue != null) {
									if (notificationTypeValue
											.equalsIgnoreCase("ALARM")) {
										alarmTableEntry.setNotificationType("ALARM");
									} else if (notificationTypeValue
											.equalsIgnoreCase("ERROR")) {
										alarmTableEntry.setNotificationType("ERROR");
									} else if (notificationTypeValue
											.equalsIgnoreCase("NODE_SUSPENDED")) {
										alarmTableEntry.setNotificationType("NODE_SUSPENDED");
									} else {
										LOGGER.info("UNKNOWN NOTIFICATION TYPE");
									}
								}
							}
						}
					}  catch (Exception e) {
						LOGGER.error("Exception while Nodelist NotificationType:"+e.getMessage());
					}

					try {
						NodeList additionalAttribute = element
								.getElementsByTagName("AdditionalAttribute");
						if (additionalAttribute != null) {
							for (int l = 0; l < additionalAttribute.getLength(); l++) {
								Element line = (Element) additionalAttribute
										.item(l);

								if (line != null) {

									String additionalAttributeName = line
											.getAttribute("AttributeName");
									String additionalAttributeValue = line
											.getAttribute("Value");

									if (additionalAttributeName != null
											&& additionalAttributeValue != null) {
										alarmTableEntry.additionalValues.put(
												additionalAttributeName,
												additionalAttributeValue);
									}
								}
							}
						}
					} catch (Exception e) {
						LOGGER.error("EXCEPTION while additional attribues setting:" + e.toString());

					}
					alarmTableEntryMap.put(AlarmTrapOid, alarmTableEntry);
				}
				fMAlarmTableAttributeMap.put(sourceTypeAT, alarmTableEntryMap);

			}
		}

	}

	/**
	 * @param xmlstring
	 * @return
	 * @throws ParserConfigurationException
	 * @throws SAXException
	 * @throws IOException
	 */
	public static Document parseXmlFile(String xmlstring) {
		try {
			DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory
					.newInstance();
			documentBuilderFactory.setValidating(true);
			documentBuilderFactory.setNamespaceAware(true);
			DocumentBuilder documentBuilder= documentBuilderFactory.newDocumentBuilder();
			documentBuilder.setErrorHandler(new org.xml.sax.ErrorHandler() {
				//Ignore the fatal errors
				public void fatalError(SAXParseException exception)throws SAXException
				{
				}
				//Validation errors 
				public void error(SAXParseException e)throws SAXParseException {
					LOGGER.error("parseXmlFile() :error at line number: " + e.getLineNumber());
					LOGGER.error("parseXmlFile() throws:error message is: " + e.getMessage());


				}
				//Show warnings
				public void warning(SAXParseException err)throws SAXParseException{
					LOGGER.error("parseXmlFile() warning:error message is: " + err.getMessage());
				}
			});

			InputSource inputSource = new InputSource();
			inputSource.setCharacterStream(new StringReader(xmlstring));
			doc = documentBuilder.parse(inputSource);


			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(System.out);
			TransformerFactory tf = TransformerFactory.newInstance();
			Transformer transformer;
			try {
				transformer = tf.newTransformer();
				transformer.setOutputProperty(
						OutputKeys.DOCTYPE_SYSTEM, "CommonAAU.dtd");
				transformer.transform(source, result);
			} catch (TransformerConfigurationException e1) {
				LOGGER.error("buildCache():error message is E1: " + e1.toString());
				e1.printStackTrace();
			} catch (TransformerException e) {
				LOGGER.error("buildCache():error message is E: " + e.toString());
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			LOGGER.error("Parser Configuration Exception while parseXmlFile: "+e.getMessage());
			return doc;

		} catch (SAXException e) {
			LOGGER.error("SAXException while parseXmlFile:"+e.getMessage());
			return doc;

		} catch (IOException e) {
			LOGGER.error("IOException while parseXmlFile::"+e.getMessage());
			return doc;

		}catch (Exception e) {
			LOGGER.error("Exception while parseXmlFile::"+e.getMessage());
			return doc;
		}
		return doc;
	}



	@Override
	public boolean isSupportedOid(String OID, String sourcetype) {
		boolean isSupportedOid = false;
		if (sourceTypeAsrapOidAttributeMap.containsKey(sourcetype)) {
			if (alarmTrapOidASAttributeMap.containsKey(OID)) {
				isSupportedOid = true;
			} 
		}
		return isSupportedOid;
	}

	@Override
	public Map<String, String> getOIDMappingData(Collection<String> OIDs,
			String sourcetype) {
		Map<String, String> hashMap = new HashMap<>();
		Iterator<String> iterator = OIDs.iterator();
		while (iterator.hasNext()) {
			String oid = iterator.next();
			if (sourceTypeAsrapOidAttributeMap.containsKey(sourcetype)) {
				if (attributeNameOIDMap.containsKey(oid)) {
					String name = oid;
					String value = attributeNameOIDMap.get(name);
					hashMap.put(name, value);
				}
			}
		}
		return hashMap;
	}


	@Override
	public Map<String, ArrayList<SnmpOID>> getSupportedAttributeOids(String sourceType)
	{
		LOGGER.debug("sourcetype in getSupportedAttributeOids:::"+sourceType);
		Map<String, ArrayList<SnmpOID>> mibTypes =null;
		if(sourceType!=null){
			mibTypes = new HashMap<String, ArrayList<SnmpOID>>();
			mibTypes=syncMap.get(sourceType);
		}
		return mibTypes;
	}

	@Override
	public AlarmTableEntry getAlarmTableEntry(String OID, String SourceType) {

		if (fMAlarmTableAttributeMap.containsKey(SourceType)) {
			if (fMAlarmTableAttributeMap.get(SourceType).containsKey(OID)) {
				return fMAlarmTableAttributeMap.get(SourceType).get(OID);
			}
		}
		return null;
	}


	@Override
	public String getAttributeName(String OID, String sourceType) 
	{
		String attributeName = null;

		Set eSet=sourceTypeAsrapOidAttributeMap.entrySet();
		Iterator iterator=eSet.iterator();
		while(iterator.hasNext()){
			Map.Entry<String, HashMap<String, HashMap<String, String>>>entry=(Map.Entry<String, HashMap<String, HashMap<String, String>>>)iterator.next();
			String sourceTString=entry.getKey();
			if(sourceTString.equals(sourceType)){
				HashMap<String, HashMap<String, String>>hashMap=entry.getValue();
				Set set=hashMap.entrySet();
				Iterator iterator2=set.iterator();
				while(iterator2.hasNext()){
					Map.Entry<String, HashMap<String,String>>entry2=(Map.Entry<String, HashMap<String,String>>)iterator2.next();
					HashMap<String, String>hashMap2=entry2.getValue();
					if(hashMap2.containsKey(OID)){
						attributeName=hashMap2.get(OID);
						return attributeName;
					}
				}
			}
		}
		return attributeName;
	}

	@Override
	public String getComSupervisionType(String sourcetype) {
		String supervisionType = "pull";
		if (sourcetype != null) {
			if (riaInfo.containsKey(sourcetype)) {
				RiaModel value = riaInfo.get(sourcetype);
				supervisionType = value.getComSupervisionType();
			}
		}
		return supervisionType;
	}

	@Override
	public String getComSupervisionOID(String sourcetype) {
		String ComSupervisionOid = null;
		if (sourcetype != null) {
			if (riaInfo.containsKey(sourcetype)) {
				ComSupervisionOid = riaInfo.get(sourcetype).getComSupervisionOID();
			}
		}
		return ComSupervisionOid;
	}


}
