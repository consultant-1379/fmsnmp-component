package com.ericsson.oss.mediation.translator.model.alarm.handlers;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;

public class OtherAlarmHandlers {

	private OtherAlarmHandlers() {

	}

	public static String IfAdminState = "The Adminstrative State of the Interface is: ";
	public static String IfOperState = "The operational State of the Interface is: ";
	public static List xmlEleList = new ArrayList();
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HandleOtherAlarmAttribute.class);

	// ESA-SNF

	public static String getResourceOIDName(final String OID, final String fdn) {

		final DocumentBuilderFactory docBuilderFactory = DocumentBuilderFactory
				.newInstance();
		DocumentBuilder docBuilder = null;
		Document doc = null;
		String strNodeName = null;
		String strNeName = null;
		File xmlFile;

		try {
			docBuilder = docBuilderFactory.newDocumentBuilder();
		} catch (ParserConfigurationException e) {
			LOGGER.info("Wrong parser configuration: " + e.getMessage());
		}

		try {
			LOGGER.info("--- The agent FDN from Supervision class");
			final String agentFDN = fdn.toString();
			strNeName = agentFDN.substring(agentFDN.lastIndexOf("=") + 1,
					agentFDN.length());

			xmlFile = new File("/etc/opt/ericsson/nms_umts_cnoss_efmesa/xml/"
					+ strNeName.concat(".xml"));

			doc = docBuilder.parse(xmlFile);
			final Node root = doc.getDocumentElement();
			xmlEleList.add(doc.getDocumentElement().getNodeName());
			writeDocumentToOutput(root);
			NamedNodeMap nnm = null;
			for (int s = 0; s < xmlEleList.size(); s++) {
				if ((String) xmlEleList.get(s) != null) {
					final NodeList nL1 = doc
							.getElementsByTagName((String) xmlEleList.get(s));

					for (int i = 0; i < nL1.getLength(); i++) {
						final Node node = nL1.item(i);

						nnm = node.getAttributes();

						if (getNodeName(nnm, OID) != null) {
							strNodeName = nnm.item(0).getNodeValue();
						}
					}
				}
			}
		} catch (SAXException e) {
			LOGGER.info("Wrong XML file structure: " + e.getMessage());
		} catch (FileNotFoundException f) {

		} catch (IOException e) {
			LOGGER.info("Could not read source file: " + e.getMessage());
		} catch (Exception e) {
		}
		return strNodeName;
	}

	// ESA-SNF

	public static void writeDocumentToOutput(final Node node) {
		Node child;
		final NodeList children = node.getChildNodes();
		for (int i = 0; i < children.getLength(); i++) {
			child = children.item(i);
			if (child.getNodeType() == Node.ELEMENT_NODE) {
				xmlEleList.add(child.getNodeName());
				writeDocumentToOutput(child);
			}
		}

	}

	// ESA-SNF

	public static String getNodeName(final NamedNodeMap nnm, final String OID) {
		String strNodeName = null;
		for (int j = 0; j < nnm.getLength(); j++) {
			if (nnm.item(j).getNodeValue().equals(OID)) {
				strNodeName = nnm.item(0).getNodeName();
			}
		}
		return strNodeName;
	}

	// IF MIB HANDLING OperStatus

	public static String getOperStatus(final int var) {
		String str = IfOperState;
		switch (var) {
		case 1:
			str = str.concat("up(1)");
			break;
		case 2:
			str = str.concat("down(2)");
			break;
		case 3:
			str = str.concat("testing(3)");
			break;
		default:
			str = " --------------";
		}
		return str;
	}

	// IF MIB HANDLING IfAdminState
	public static String getAdminStatus(final int var) {
		String str = IfAdminState;
		switch (var) {
		case 1:
			str = str.concat("up(1)");
			break;
		case 2:
			str = str.concat("down(2)");
			break;
		case 3:
			str = str.concat("testing(3)");
			break;
		default:
			str = " --------------";
		}
		return str;
	}

	// AXD axd301pchIndicator
	public static String getaxd301pchIndicator(final String Value) {
		String axd301pchIndicator = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			axd301pchIndicator = "The PCH Operation create";
			break;
		case 2:
			axd301pchIndicator = "The PCH Operation delete";
			break;
		case 3:
			axd301pchIndicator = "The PCH Operation modifyBw";
			break;
		case 4:
			axd301pchIndicator = "The PCH Operation block";
			break;
		case 5:
			axd301pchIndicator = "The PCH Operation unblock";
			break;
		default:
			break;
		}
		return axd301pchIndicator;
	}

	// AXD axdloopbackMode
	public static String getaxdloopbackMode(final String Value) {
		String axdloopbackMode = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			axdloopbackMode = "The loopback OAM cell was looped back at segment end point";
			break;
		case 2:
			axdloopbackMode = "The loopback OAM cell was looped back at the connection endToEnd point";
			break;
		case 3:
			axdloopbackMode = "The loopback OAM cell was looped back at specified location";
			break;
		default:
			break;
		}
		return axdloopbackMode;
	}

	// AXD axdloopbackResult
	public static String getaxdloopbackResult(final String Value) {
		String axdloopbackResult = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			axdloopbackResult = "The loopback was successful";
			break;
		case 2:
			axdloopbackResult = "The loopback was un successful";
			// loopbackResult=axd301GroupStr.append("the
			// loopback was un successful
			// ").append("fail").toString();
			break;
		default:
			break;
		}
		return axdloopbackResult;
	}

	// AXD axdoamOperation
	public static String getaxdoamOperation(final String Value) {
		String axdoamOperation = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			axdoamOperation = "OAM Operation is" + "setSegmentEndPoint";
			break;
		case 2:
			axdoamOperation = "OAM Operation is" + "clearSegmentEndPoint";
			break;
		case 3:
			axdoamOperation = "OAM Operation is" + "setCcSinkLinkSide";
			break;
		case 4:
			axdoamOperation = "OAM Operation is" + "clearCcSinkLinkSide";
			break;
		case 5:
			axdoamOperation = "OAM Operation is" + "setCcSourceLinkSide";
			break;
		case 6:
			axdoamOperation = "OAM Operation is" + "clearCcSourceLinkSide";
			break;
		case 7:
			axdoamOperation = "OAM Operation is" + "setCcSinkCoreSide";
			break;
		case 8:
			axdoamOperation = "OAM Operation is" + "clearCcSinkCoreSide";
			break;
		case 9:
			axdoamOperation = "OAM Operation is" + "setCcSourceCoreSide";
			break;
		case 10:
			axdoamOperation = "OAM Operation is" + "clearCcSourceCoreSide";
			break;
		case 11:
			axdoamOperation = "OAM Operation is" + "inbandCcStatusChange";
			break;
		case 12:
			axdoamOperation = "OAM Operation is" + "setPmSinkLinkSide";
			break;
		case 13:
			axdoamOperation = "OAM Operation is" + "clearPmSinkLinkSide";
			break;
		case 14:
			axdoamOperation = "OAM Operation is" + "setPmSourceLinkSide";
			break;
		case 15:
			axdoamOperation = "OAM Operation is" + "clearPmSourceLinkSide";
			break;
		case 16:
			axdoamOperation = "OAM Operation is" + "setPmSinkCoreSide";
			break;
		case 17:
			axdoamOperation = "OAM Operation is" + "clearPmSinkCoreSide";
			break;
		case 18:
			axdoamOperation = "OAM Operation is" + "setPmSourceCoreSide";
			break;
		case 19:
			axdoamOperation = "OAM Operation is" + "clearPmSourceCoreSide";
			break;
		case 20:
			axdoamOperation = "OAM Operation is" + "inbandPmStatusChange";
			break;
		case 21:
			axdoamOperation = "OAM Operation is" + "setCcInbandSinkLinkSide";
			break;
		case 22:
			axdoamOperation = "OAM Operation is" + "clearCcInbandSinkLinkSide";
			break;
		case 23:
			axdoamOperation = "OAM Operation is" + "setCcInbandSourceLinkSide";
			break;
		case 24:
			axdoamOperation = "OAM Operation is"
					+ "clearCcInbandSourceLinkSide";
			break;
		case 25:
			axdoamOperation = "OAM Operation is" + "setCcInbandSinkCoreSide";
			break;
		case 26:
			axdoamOperation = "OAM Operation is" + "clearCcInbandSinkCoreSide";
			break;
		case 27:
			axdoamOperation = "OAM Operation is" + "setCcInbandSourceCoreSide";
			break;
		case 28:
			axdoamOperation = "OAM Operation is"
					+ "clearCcInbandSourceCoreSide";
			break;
		case 29:
			axdoamOperation = "OAM Operation is" + "setPmInbandSinkLinkSide";
			break;
		case 30:
			axdoamOperation = "OAM Operation is" + "clearPmInbandSinkLinkSide";
			break;
		case 31:
			axdoamOperation = "OAM Operation is" + "setPmInbandSourceLinkSide";
			break;
		case 32:
			axdoamOperation = "OAM Operation is"
					+ "clearPmInbandSourceLinkSide";
			break;
		case 33:
			axdoamOperation = "OAM Operation is" + "setPmInbandSinkCoreSide";
			break;
		case 34:
			axdoamOperation = "OAM Operation is" + "clearPmInbandSinkCoreSide";
			break;
		case 35:
			axdoamOperation = "OAM Operation is" + "setPmInbandSourceCoreSide";
			break;
		case 36:
			axdoamOperation = "OAM Operation is"
					+ "clearPmInbandSourceCoreSide";
			break;
		case 37:
			axdoamOperation = "OAM Operation is" + "setCcPreventInband";
			break;
		case 38:
			axdoamOperation = "OAM Operation is" + "clearCcPreventInband";
			break;
		case 39:
			axdoamOperation = "OAM Operation is" + "setPmPreventInband";
			break;
		case 40:
			axdoamOperation = "OAM Operation is" + "clearPmPreventInband";
			break;
		case 41:
			axdoamOperation = "OAM Operation is" + "setAisRdiMonitoring";
			break;
		case 42:
			axdoamOperation = "OAM Operation is" + "clearAisRdiMonitoring";
			break;
		default:
			break;
		}
		return axdoamOperation;
	}

	// AXD axdoamResult
	public static String getaxdoamResult(final String Value) {
		String axdoamResult = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			axdoamResult = " and " + "OAM operation was successful";
			break;
		case 2:
			axdoamResult = " and " + "OAM operation was un successful";
			break;
		default:
			break;
		}
		return axdoamResult;
	}

	// AXD axdpchVpVcFaultType
	public static String getaxdpchVpVcFaultType(final String Value) {
		String axdpchVpVcFaultType = "";
		switch (Integer.parseInt(Value.toString())) {

		case 1:
			axdpchVpVcFaultType = "Fault is " + "end2endAisLinkSide";
			break;
		case 2:
			axdpchVpVcFaultType = "Fault is " + "end2endRdiLinkSide";
			break;
		case 3:
			axdpchVpVcFaultType = "Fault is " + "end2endAisCoreSide";
			break;
		case 4:
			axdpchVpVcFaultType = "Fault is " + "end2endRdiCoreSide";
			break;
		case 5:
			axdpchVpVcFaultType = "Fault is segmentAisLinkSide";
			break;
		case 6:
			axdpchVpVcFaultType = "Fault is segmentRdiLinkSide";
			break;
		case 7:
			axdpchVpVcFaultType = "Fault is segmentAisCoreSide";
			break;
		case 8:
			axdpchVpVcFaultType = "Fault is segmentRdiCoreSide";
			break;
		default:
			break;
		}
		return axdpchVpVcFaultType;
	}

	// AXD axdpchVpVcFaultLocation
	public static String getaxdpchVpVcFaultLocation(final String Value) {
		String axdpchVpVcFaultType = "";
		switch (Integer.parseInt(Value.toString())) {

		case 1:
			axdpchVpVcFaultType = "Fault is " + "end2endAisLinkSide";
			break;
		case 2:
			axdpchVpVcFaultType = "Fault is " + "end2endRdiLinkSide";
			break;
		case 3:
			axdpchVpVcFaultType = "Fault is " + "end2endAisCoreSide";
			break;
		case 4:
			axdpchVpVcFaultType = "Fault is " + "end2endRdiCoreSide";
			break;
		case 5:
			axdpchVpVcFaultType = "Fault is segmentAisLinkSide";
			break;
		case 6:
			axdpchVpVcFaultType = "Fault is segmentRdiLinkSide";
			break;
		case 7:
			axdpchVpVcFaultType = "Fault is segmentAisCoreSide";
			break;
		case 8:
			axdpchVpVcFaultType = "Fault is segmentRdiCoreSide";
			break;
		default:
			break;
		}
		return axdpchVpVcFaultType;
	}

	// AXD axdbuStatus
	public static String getaxdbuStatus(final String Value) {
		String buStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 0:
			buStatus = "The status of the backup functionality is idle";
			break;
		case 1:
			buStatus = "The status of the backup functionality is inProgress";
			break;
		case 2:
			buStatus = "The status of the backup functionality is ready";
			break;
		case 3:
			buStatus = "The status of the backup functionality is prohibited";
			break;
		default:
			break;
		}
		return buStatus;
	}

	// AXD axdbuResult
	public static String getaxdbuResult(final String Value) {
		String buResult = "";
		switch (Integer.parseInt(Value.toString())) {
		case 0:
			buResult = "BUResult= OK";
			break;
		case 1:
			buResult = "BUResult=Input Error";
			break;
		case 2:
			buResult = "BUResult=Execution Error";
			break;
		case 3:
			buResult = "BUResult=undeterminable";
			break;
		default:
			break;

		}
		return buResult;
	}

}
