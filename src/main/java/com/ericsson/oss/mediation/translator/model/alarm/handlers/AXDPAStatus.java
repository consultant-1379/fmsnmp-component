package com.ericsson.oss.mediation.translator.model.alarm.handlers;

public class AXDPAStatus {

	private AXDPAStatus() {

	}

	// AXD axdoptDmOpResult
	public static String getaxdoptDmOpResult(final String Value) {
		String optDmOpResult = "";
		switch (Integer.parseInt(Value.toString())) {
		case 0:
			optDmOpResult = "The optDmOpResult is idle";
			break;
		case 1:
			optDmOpResult = "The optDmOpResult is inProgress";
			break;
		case 2:
			optDmOpResult = "The optDmOpResult is ready";
			break;
		case 3:
			optDmOpResult = "The optDmOpResult is prohibited";
		default:
			break;
		}
		return optDmOpResult;
	}

	// AXD axdoptDmOpResult
	public static String getaxdpaStatus(final String Value) {
		String paStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 0:
			paStatus = "The status on the patch functionality is idle";
			break;

		case 1:
			paStatus = "The status on the patch functionality is installing";
			break;
		default:
			break;

		}
		return paStatus;
	}

	// AXD axdugStatus
	public static String getaxdugStatus(final String Value) {
		String ugStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			ugStatus = "The status on the upgrade functionality is  Idle";
			break;
		case 2:
			ugStatus = "The status on the upgrade functionality is Installing";
			break;
		case 3:
			ugStatus = "The status on the upgrade functionality is Installed";
			break;
		case 4:
			ugStatus = "The status on the upgrade functionality is Activating";
			break;
		case 5:
			ugStatus = "The status on the upgrade functionality is activated";
			break;
		case 6:
			ugStatus = "The status on the upgrade functionality is committing";
			break;
		case 7:
			ugStatus = "The status on the upgrade functionality is committed";
			break;
		case 8:
			ugStatus = "The status on the upgrade functionality is RollingBack";
			break;
		case 9:
			ugStatus = "The status on the upgrade functionality is Rolledback";
			break;

		default:
			break;

		}
		return ugStatus;
	}

	// AXD axdbgpPeerState
	public static String getaxdbgpPeerState(final String Value) {
		String bgpPeerState = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			bgpPeerState = "The BGP peer connection state is Idle";
			break;

		case 2:
			bgpPeerState = "The BGP peer connection state is Connect";
			break;

		case 3:
			bgpPeerState = "The BGP peer connection state is Active";
			break;

		case 4:
			bgpPeerState = "The BGP peer connection state is OpenSent";
			break;

		case 5:
			bgpPeerState = "The BGP peer connection state is OpenConfirm";
			break;

		case 6:
			bgpPeerState = "The BGP peer connection state is Established";
			break;

		default:
			break;
		}
		return bgpPeerState;
	}

	// AXD axdospfNbrState
	public static String getaxdospfNbrState(final String Value) {
		String ospfNbrState = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("down").append("\n").toString();
			ospfNbrState = "down";
			break;
		case 2:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("attempt").append("\n").toString();
			ospfNbrState = "attempt";
			break;
		case 3:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("init").append("\n").toString();
			ospfNbrState = "init";
			break;
		case 4:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("twoway").append("\n").toString();
			ospfNbrState = "twoway";
			break;
		case 5:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("ExchangeStart").append("\n").toString();
			ospfNbrState = "ExchangeStart";
			break;
		case 6:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("exchange").append("\n").toString();
			ospfNbrState = "exchange";
			break;
		case 7:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("Loading").append("\n").toString();
			ospfNbrState = "Loading";
			break;
		case 8:
			// ospfNbrState=axd301GroupStr.append("OspfNbrState=").append("Full").append("\n").toString();
			ospfNbrState = "Full";
			break;
		default:
			break;
		}
		return ospfNbrState;
	}

	// AXD axdospfNbrState
	public static String getaxdisisISAdjState(final String Value) {
		String isisISAdjState = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			isisISAdjState = "The state of the adjacency is down";
			break;
		case 2:
			isisISAdjState = "The state of the adjacency is initializing";
			break;
		case 3:
			isisISAdjState = "The state of the adjacency is up";
			break;
		case 4:
			isisISAdjState = "The state of the adjacency is failed";
			break;
		default:
			break;
		}
		return isisISAdjState;
	}

	// AXD axdospfNbrState
	public static String getaxdmigLoopbackStatus(final String Value) {
		String migLoopbackStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			migLoopbackStatus = "The state of the permanently looped timeslots for LoopBack is failure";
			break;
		case 2:
			migLoopbackStatus = "The state of the permanently looped timeslots for LoopBack is success";
			break;
		default:
			break;
		}
		return migLoopbackStatus;
	}

	// AXD axdpingNotificationType
	public static String getaxdpingNotificationType(final String Value) {
		String pingNotificationType = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			pingNotificationType = "ProbeFailure";

			break;
		case 2:
			pingNotificationType = "TestFailure";

			break;
		case 3:
			pingNotificationType = "TestCompletion";

			break;
		default:
			break;
		}
		return pingNotificationType;
	}

	// AXD axdpingNotificationType
	public static String getaxdpingEventCtlTestName(final String Value) {
		String pingEventCtlTestName = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			pingEventCtlTestName = "ProbeFailure";

			break;
		case 2:
			pingEventCtlTestName = "TestFailure";

			break;
		case 3:
			pingEventCtlTestName = "TestCompletion";

			break;
		default:
			break;
		}
		return pingEventCtlTestName;
	}

	// AXD getaxdpingResultsOperStatus
	public static String getaxdpingResultsOperStatus(final String Value) {
		String pingResultsOperStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:

			pingResultsOperStatus = "enabled";
			break;
		case 2:

			pingResultsOperStatus = "disabled";
			break;
		default:
			break;
		}
		return pingResultsOperStatus;
	}

	// AXD getaxdpingResultsOperStatus
	public static String getaxdtraceRouteNotificationType(final String Value) {
		String traceRouteNotificationType = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:

			traceRouteNotificationType = "pathChange";
			break;
		case 2:

			traceRouteNotificationType = "testFailure";
			break;
		case 3:

			traceRouteNotificationType = "testCompletion";
			break;
		default:
			break;

		}
		return traceRouteNotificationType;
	}

	// AXD getaxdpingResultsOperStatus
	public static String getaxdifMauJabberState(final String Value) {
		String ifMauJabberState = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:

			ifMauJabberState = "other";
			break;
		case 2:

			ifMauJabberState = "unknown";
			break;
		case 3:
			ifMauJabberState = "noJabber";

			break;
		case 4:

			ifMauJabberState = "jabbering";
			break;
		default:
			break;
		}
		return ifMauJabberState;
	}

	// AXD gcpLinkReasonCode
	public static String getaxdgcpLinkReasonCode(final String Value) {
		String gcpLinkReasonCode = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			gcpLinkReasonCode = "Link communication is down.";

			break;
		case 2:
			gcpLinkReasonCode = "Link communication is up.";

			break;
		default:
			break;
		}
		return gcpLinkReasonCode;
	}

	// AXD gcpLinkReasonCode
	public static String getaxdlogRecAcStatus(final String Value) {
		String logRecAcStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			logRecAcStatus = "LogRecAcStatus=idle";

			break;
		case 2:
			logRecAcStatus = "LogRecAcStatus=executing";

			break;
		case 3:
			logRecAcStatus = "LogRecAcStatus=abort";

			break;
		case 4:
			logRecAcStatus = "LogRecAcStatus=abortOk";

			break;
		case 5:
			logRecAcStatus = "LogRecAcStatus=doneOk";

			break;
		case 6:
			logRecAcStatus = "LogRecAcStatus=doneFailure";

			break;
		default:
			break;
		}
		return logRecAcStatus;
	}

	// AXD secOperation
	public static String getaxdsecOperation(final String Value) {
		String secOperation = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			secOperation = "Operation type when security data is changed=new";
			break;
		case 2:
			secOperation = "Operation type when security data is changed=changed";
			break;
		case 3:
			secOperation = "Operation type when security data is changed=deleted";
			break;
		default:
			break;
		}
		return secOperation;
	}

	// AXD secOperation
	public static String getaxdmrErrorCode(final String Value) {
		String mrErrorCode = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			mrErrorCode = "The mesurement Error Code=unSpecified";
			break;
		case 2:
			mrErrorCode = "The mesurement Error Code=noSuchObject";
			break;
		case 3:
			mrErrorCode = "The mesurement Error Code=noSuchInstance";
			break;
		case 4:
			mrErrorCode = "The mesurement Error Code=hardwareFault";
			break;
		case 5:
			mrErrorCode = "The mesurement Error Code=noDataAvailable";
			break;
		case 6:
			mrErrorCode = "The mesurement Error Code=other";
			break;
		case 7:
			mrErrorCode = "The mesurement Error Code=noSuchName";
			break;
		case 8:
			mrErrorCode = "The mesurement Error Code=badOid";
			break;
		default:
			break;
		}
		return mrErrorCode;
	}

	// AXD secOperation
	public static String getaxdperfFileFcode(final String Value) {
		String PerfFileFcode = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("echunck").append("\n").toString();
			PerfFileFcode = "echuck";
			break;
		case 2:
			PerfFileFcode = "eclosed";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("enclosed").append("\n").toString();
			break;
		case 3:
			PerfFileFcode = "econn";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("econn").append("\n").toString();
			break;
		case 4:
			PerfFileFcode = "ehost";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("ehost").append("\n").toString();
			break;
		case 5:
			PerfFileFcode = "elogin";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("elogin").append("\n").toString();
			break;
		case 6:
			PerfFileFcode = "enotbinary";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("enotbinary").append("\n").toString();
			break;
		case 7:
			PerfFileFcode = "epath";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("epath").append("\n").toString();
			break;
		case 8:
			PerfFileFcode = "etype";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("etype").append("\n").toString();
			break;
		case 9:
			PerfFileFcode = "euser";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("euser").append("\n").toString();
			break;
		case 10:
			PerfFileFcode = "eunknown";
			// PerfFileFcode=axd301GroupStr.append("PerfFilecode=").append("eunknown").append("\n").toString();
			break;
		default:
			break;
		}
		return PerfFileFcode;
	}

	// AXD secOperation
	public static String getaxdmplsXCAdminStatus(final String Value) {
		String mplsXCAdminStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			mplsXCAdminStatus = "If AdminStatus is=UP";

			break;
		case 2:
			mplsXCAdminStatus = "If AdminStatus is=Down";

			break;
		case 3:
			mplsXCAdminStatus = "If AdminStatus is=TESTING";

			break;
		default:
			break;
		}
		return mplsXCAdminStatus;
	}

	// AXD secOperation
	public static String getaxdrsvpIfOperStatus(final String Value) {
		String rsvpIfOperStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			rsvpIfOperStatus = "An event reporting that operational state of the RSVP rsvpIfOperStatus=up";
			break;
		case 2:
			rsvpIfOperStatus = "An event reporting that operational state of the RSVP rsvpIfOperStatus=down";
			break;
		case 3:
			rsvpIfOperStatus = "An event reporting that operational state of the RSVP rsvpIfOperStatus=grrestart";
			break;
		default:
			break;
		}
		return rsvpIfOperStatus;
	}

	// AXD axdmplsIfOperStatus
	public static String getaxdmplsIfOperStatus(final String Value) {
		String mplsIfOperStatus = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			mplsIfOperStatus = "An event reporting that an interface state  has changed to up";
			break;
		case 2:
			mplsIfOperStatus = "An event reporting that an interface state  has changed to down";
			break;
		default:
			break;
		}
		return mplsIfOperStatus;
	}

	// AXD axdmplsIfOperStatus
	public static String getaxdmplsLdpSesState(final String Value) {
		String mplsLdpSesState = "";
		switch (Integer.parseInt(Value.toString())) {
		case 1:
			mplsLdpSesState = "An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState="
					+ "nonexistent";
			break;
		case 2:
			mplsLdpSesState = "An event reporting that the LDP session state,is changed to or from operational and  mplsLdpSesState="
					+ "initialized";
			break;
		case 3:
			mplsLdpSesState = "An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState="
					+ "openrec";
			break;
		case 4:
			mplsLdpSesState = "An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState="
					+ "opensent";
			break;
		case 5:
			mplsLdpSesState = "An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState="
					+ "operational";
			break;
		default:
			break;
		}
		return mplsLdpSesState;
	}

}
