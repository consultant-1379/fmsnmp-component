/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.mediation.translator.model;

import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.model.alarm.handlers.*;


public class AxdAlarmAttrs {

	private String axdatmfVccOperStatus;
	private String axd301dnaInterfaceMyNeighbourIfName;
	private String axd301dnaInterfaceMyNeighbourAddress;
	private String axd301dnaInterfaceEnteringMethod;
	private String axdifoper;
	private String axd301ifName;
	private String spvcOperationIndicator;
	private String axdmplsLdpSesState;
	private String axdmplsIfOperStatus;
	private String axdrsvpIfOperStatus;
	private String axdmplsXCAdminStatus;
	private String axdperfFileFcode;
	private String axdmrErrorCode;
	private String axdsecOperation;
	private String axdlogRecAcStatus;
	private String axdgcpLinkReasonCode;
	private String axdpingNotificationType;
	private String axdmigLoopbackStatus;
	private String axdisisISAdjState;
	private String axdBgpPeerLastErrorReason;

	private static final String ATMVCCOPERSTATUS = "axdatmfVccOperStatus";
	private static final String DNAINTERFACENAME = "axd301dnaInterfaceMyNeighbourIfName";
	private static final String DNAINTERFACEENTMETHOD = "axd301dnaInterfaceEnteringMethod";
	private static final String DNAINTERFACEMYNEIGHBOUR = "axd301dnaInterfaceMyNeighbourAddress";

	private static final String DNACOMMUNITY = "axddnaCommunityString";
	private static final String EVENTREASON = "axdeventReason";
	private static final String IFOPER = "axdifoper";
	private static final String IFADMIN = "axdifadmin";
	private static final String IFINDEX = "axd301ifIndex";
	private static final String SPVCOPINDICATOR = "axdspvcOperationIndicator";
	private static final String SAAUNITIFINDEX = "axd301saalUniIfIndex";
	private static final String DMPLSLDPSESSTATE = "axdmplsLdpSesState";
	private static final String PNNILINKIFINDEX = "axd301pnniLinkIfIndex";
	private static final String MPLSTUNNELFAILUREREASON = "mplsTunnelFailureReason";
	private static final String MPLSTUNNELNAME = "mplsTunnelName";
	private static final String MPLSIFOPERSTATUS = "axdmplsIfOperStatus";
	private static final String RSVPIFOPERSTATUS = "axdrsvpIfOperStatus";
	private static final String MPLSXCADMINSTATUS = "axdmplsXCAdminStatus";
	private static final String PERFINFO = "axdperfInfo";
	private static final String PERFFILEFCODE = "axdperfFileFcode";
	private static final String MRERROROID = "axdmrErrorOid";
	private static final String MRERRORCODE = "axdmrErrorCode";
	private static final String SECOPERATION = "axdsecOperation";
	private static final String LOGRECACINFO = "logRecAcInfo";
	private static final String LOGRECACSTATUS = "axdlogRecAcStatus";
	private static final String TESTRESULE = "axdtestResult";
	private static final String FAILUREREASON = "axdfailureReason";
	private static final String CREATEDEMTYPE = "axdcreatedEmType";
	private static final String POSITION = "axdposition";
	private static final String GCPLINKREASONCODE = "axdgcpLinkReasonCode";
	private static final String IFMAUJABBERSTATE = "axdifMauJabberState";
	private static final String TRACEROUTEEVENTCTLTESTNAME = "axdtraceRouteEventCtlTestName";
	private static final String TRACEROUTENOTIFICATIONTYPE = "axdtraceRouteNotificationType";
	private static final String ERRORTYPETEXT = "axdErrorTypeText";
	private static final String PINGRESULTSOPERSTATUS = "axdpingResultsOperStatus";
	private static final String PINGEVENTCTLTESTNAME = "axdpingEventCtlTestName";
	private static final String PINGNOTIFICATIONTYPE = "axdpingNotificationType";
	private static final String MIGLOOPBACKSTATUS = "axdmigLoopbackStatus";
	private static final String ISISISADJSTATE = "axdisisISAdjState";
	private static final String OSPFNBRSTATE = "axdospfNbrState";
	private static final String BGPPEERLASTERRORREASON = "axdBgpPeerLastErrorReason";
	private static final String BGPPEERSTATE = "axdbgpPeerState";
	private static final String UGINFO = "axdugInfo";
	private static final String UGSTATUS = "axdugStatus";
	private static final String PAINFO = "axdpaInfo";
	private static final String PASTATUS = "axdpaStatus";
	private static final String OPTDMSHORTNAME = "axdoptDmShortName";
	private static final String OPTDMOPINFO = "axdoptDmOpInfo";
	private static final String OPTDMOPRESULT = "axdoptDmOpResult";
	private static final String BUSINFO = "axdbusInfo";
	private static final String BURESULT = "axdbuResult";
	private static final String BUSTATUS = "axdbuStatus";
	private static final String SYSFIREWALLINFO = "axdsysFirewallInfo";
	private static final String SYSFIREWALLSTATUS = "axdsysFirewallStatus";
	private static final String CROEVENTFILENAME = "axdcroEventFileName";
	private static final String PCHVPVCFAULTLOCATION = "axdpchVpVcFaultLocation";
	private static final String PCHVPVCFAULTTYPE = "axdpchVpVcFaultType";
	private static final String OAMRESULT = "axdoamResult";
	private static final String OAMOPERATION = "axdoamOperation";
	private static final String LOOPBACKRESULT = "axdloopbackResult";
	private static final String LOOPBACKMODE = "axdloopbackMode";
	private static final String PCHINDICATOR = "axdpchIndicator";
	private static final String FAULTID = "axdfaultId";


	public boolean translateAXDAddlAttrs(String attributeName,
			String attributeValue, final EventNotification notif, final StringBuffer additionaltextBuffer) {
		{
			boolean status = true;
			switch (attributeName) {
			// AXD axd301dnaInterfaceEnteringMethod
			case ATMVCCOPERSTATUS:
				axdatmfVccOperStatus = AXDStates
						.getaxdatmfVccOperStatus(attributeValue);
				attributeValue = axdatmfVccOperStatus;

				break;
			case DNAINTERFACENAME:

				axd301dnaInterfaceMyNeighbourIfName = attributeValue;
				attributeValue = axd301dnaInterfaceMyNeighbourIfName;

				break;

			case DNAINTERFACEMYNEIGHBOUR:

				axd301dnaInterfaceMyNeighbourAddress = attributeValue;
				attributeValue = axd301dnaInterfaceMyNeighbourAddress;
				break;
			case DNAINTERFACEENTMETHOD:

				axd301dnaInterfaceEnteringMethod = AXDStates
						.getaxd301dnaInterfaceEnteringMethod(attributeValue);
				attributeValue = axd301dnaInterfaceEnteringMethod;

			case DNACOMMUNITY:
			case EVENTREASON:
				break;
			case IFOPER:

				axdifoper = AXDStates.getaxdifoper(attributeValue);
				attributeValue = axdifoper;
				break;
			case IFADMIN:

				AXDStates.getaxdifadmin(attributeValue);
				attributeValue = axd301ifName;
				break;
			case IFINDEX:
				// ?
				attributeValue = axd301ifName;
				break;
			case SPVCOPINDICATOR:
				axd301ifName = attributeValue;
				attributeValue = axd301ifName;
				spvcOperationIndicator = AXDStates
						.getspvcOperationIndicator(attributeValue);
				attributeValue = spvcOperationIndicator;
				break;
			case SAAUNITIFINDEX:
				break;
			case DMPLSLDPSESSTATE:

				axdmplsLdpSesState = AXDPAStatus
						.getaxdmplsLdpSesState(attributeValue);
				attributeValue = axdmplsLdpSesState;
				break;

			case PNNILINKIFINDEX:
			case MPLSTUNNELFAILUREREASON:
			case MPLSTUNNELNAME:
				break;

			case MPLSIFOPERSTATUS:
				axdmplsIfOperStatus = AXDPAStatus
						.getaxdrsvpIfOperStatus(attributeValue);
				attributeValue = axdmplsIfOperStatus;
				break;

			case RSVPIFOPERSTATUS:
				axdrsvpIfOperStatus = AXDPAStatus
						.getaxdrsvpIfOperStatus(attributeValue);
				attributeValue = axdrsvpIfOperStatus;
				break;

			case MPLSXCADMINSTATUS:
				axdmplsXCAdminStatus = AXDPAStatus
						.getaxdmplsXCAdminStatus(attributeValue);
				attributeValue = axdmplsXCAdminStatus;
				break;

			case PERFINFO:
				final String axdperfInfo = attributeValue;
				attributeValue = axdperfInfo;
				break;

			case PERFFILEFCODE:
				axdperfFileFcode = AXDPAStatus
						.getaxdperfFileFcode(attributeValue);
				attributeValue = axdperfFileFcode;
				break;

			case MRERROROID:
				break;

			case MRERRORCODE:
				axdmrErrorCode = AXDPAStatus.getaxdmrErrorCode(attributeValue);
				attributeValue = axdmrErrorCode;
				break;
			case SECOPERATION:

				axdsecOperation = AXDPAStatus
						.getaxdsecOperation(attributeValue);
				attributeValue = axdsecOperation;
				break;
			case LOGRECACINFO:
				break;

			// AXD axdlogRecAcStatus
			case LOGRECACSTATUS:

				axdlogRecAcStatus = AXDPAStatus
						.getaxdlogRecAcStatus(attributeValue);
				attributeValue = axdlogRecAcStatus;

				break;

			// AXD axdtestResult
			case TESTRESULE:
				break;
			// AXD axdfailureReason
			case FAILUREREASON:
				break;

			// AXD axdcreatedEmType
			case CREATEDEMTYPE:
				Integer.parseInt(attributeValue);
				break;

			// AXD axdposition
			case POSITION:
				break;

			// AXD axdgcpLinkReasonCode
			case GCPLINKREASONCODE:

				axdgcpLinkReasonCode = AXDPAStatus
						.getaxdgcpLinkReasonCode(attributeValue);
				attributeValue = axdgcpLinkReasonCode;
				break;

			// AXD axdifMauJabberState
			case IFMAUJABBERSTATE:
				attributeValue = AXDPAStatus
						.getaxdifMauJabberState(attributeValue);

				break;

			// AXD traceRouteEventCtlTestName
			case TRACEROUTEEVENTCTLTESTNAME:
				break;

			// AXD axdErrorTypeText
			case TRACEROUTENOTIFICATIONTYPE:

				attributeValue = AXDPAStatus
						.getaxdtraceRouteNotificationType(attributeValue);

				break;

			// AXD axdErrorTypeText
			case ERRORTYPETEXT:
				attributeName = "axdErrorTypeText";

				break;

			// AXD axdpingResultsOperStatus
			case PINGRESULTSOPERSTATUS:
				attributeName = "axdpingResultsOperStatus";
				attributeValue = AXDPAStatus
						.getaxdpingResultsOperStatus(attributeValue);

				break;

			// AXD axdpingNotificationType
			case PINGEVENTCTLTESTNAME:
				break;

			// AXD axdpingNotificationType
			case PINGNOTIFICATIONTYPE:

				axdpingNotificationType = AXDPAStatus
						.getaxdpingNotificationType(attributeValue);
				attributeValue = axdpingNotificationType;
				break;

			// AXD axdmigLoopbackStatus
			case MIGLOOPBACKSTATUS:

				axdmigLoopbackStatus = AXDPAStatus
						.getaxdmigLoopbackStatus(attributeValue);
				attributeValue = axdmigLoopbackStatus;
				break;

			// AXD axdisisISAdjState
			case ISISISADJSTATE:

				axdisisISAdjState = AXDPAStatus
						.getaxdisisISAdjState(attributeValue);
				attributeValue = axdisisISAdjState;
				break;

			// AXD ospfNbrState
			case OSPFNBRSTATE:
				attributeName = "axdospfNbrState";
				attributeValue = AXDPAStatus.getaxdospfNbrState(attributeValue);

				break;

			// AXD ugInfo
			case BGPPEERLASTERRORREASON:
				axdBgpPeerLastErrorReason = "BGPLastErrorReason="
						+ attributeValue;
				attributeValue = axdBgpPeerLastErrorReason;
				break;

			// AXD axdbgpPeerState
			case BGPPEERSTATE:
				attributeValue = AXDPAStatus.getaxdbgpPeerState(attributeValue);

				break;

			// AXD ugInfo
			case UGINFO:
				break;

			// AXD paInfo
			case UGSTATUS:
				attributeValue = AXDPAStatus.getaxdugStatus(attributeValue);

				break;

			// AXD paInfo
			case PAINFO:
				break;

			// AXD paStatus
			case PASTATUS:
				attributeValue = AXDPAStatus.getaxdpaStatus(attributeValue);

				break;

			// AXD axdoptDmShortName
			case OPTDMSHORTNAME:
				attributeValue = "optDmShortName is" + attributeValue;

				break;
			// AXD axdoptDmOpInfo
			case OPTDMOPINFO:
				break;
			// AXD axdoptDmOpResult
			case OPTDMOPRESULT:
				break;
			// AXD axdbusInfo
			case BUSINFO:
				break;

			// AXD axdbuResult
			case BURESULT:

				attributeValue = OtherAlarmHandlers
						.getaxdbuResult(attributeValue);

				break;
			// AXD "axdbuStatus"
			case BUSTATUS:
				attributeValue = OtherAlarmHandlers
						.getaxdbuStatus(attributeValue);

				break;
			// AXD "axdrestartingCP"
			case SYSFIREWALLINFO:
				attributeValue = "CP that was restarted is:" + attributeValue;

				break;

			// AXD axdsysFirewallStatus
			case SYSFIREWALLSTATUS:

				attributeValue = "Name of successfully transferred file, which is to be logged in CroLog is "
						+ attributeValue;

				break;
			// AXD axdcroEventFileName
			case CROEVENTFILENAME:
				attributeValue = "Name of successfully transferred file, which is to be logged in CroLog is "
						+ attributeValue;

				break;
			// AXD axdpchVpVcFaultLocation
			case PCHVPVCFAULTLOCATION:
				break;
			// AXD axdpchVpVcFaultType
			case PCHVPVCFAULTTYPE:
				attributeValue = OtherAlarmHandlers
						.getaxdpchVpVcFaultType(attributeValue);

				break;
			// AXD axdoamResult
			case OAMRESULT:
				attributeValue = OtherAlarmHandlers
						.getaxdoamResult(attributeValue);

				break;
			// AXD axdoamOperation
			case OAMOPERATION:
				attributeValue = OtherAlarmHandlers
						.getaxdoamOperation(attributeValue);

				break;
			// AXD axdloopbackResult
			case LOOPBACKRESULT:
				attributeValue = OtherAlarmHandlers
						.getaxdloopbackResult(attributeValue);

				break;

			// AXD axdloopbackMode
			case LOOPBACKMODE:
				attributeValue = OtherAlarmHandlers
						.getaxdloopbackMode(attributeValue);

				break;
			// AXD axdpchIndicator
			case PCHINDICATOR:
				attributeValue = OtherAlarmHandlers
						.getaxd301pchIndicator(attributeValue);

				break;
			// AXD axdfaultId
			case FAULTID:
				break;

			default:
				status = false;
				break;

			}
			if (status) {
				additionaltextBuffer.append(attributeName + ":" + attributeValue
						+ "\n");
				notif.addAdditionalAttribute(attributeName, attributeValue);
			}
			return status;
		}

	}
}
