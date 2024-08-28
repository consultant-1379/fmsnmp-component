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
package com.ericsson.oss.mediation.snmp.translate.test;

import junit.framework.Assert;

import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.alarm.handlers.AXDPAStatus;

public class AXDPAStatusTest {

	@Test
	public void getaxdoptDmOpResult() {
		
		
		Assert.assertEquals("The optDmOpResult is idle",AXDPAStatus.getaxdoptDmOpResult("0") );
		Assert.assertEquals("The optDmOpResult is inProgress", AXDPAStatus.getaxdoptDmOpResult("1"));
		Assert.assertEquals("The optDmOpResult is ready", AXDPAStatus.getaxdoptDmOpResult("2"));
		Assert.assertEquals("The optDmOpResult is prohibited",AXDPAStatus.getaxdoptDmOpResult("3") );
		
	}
	@Test
	public void getaxdpaStatus(){
		
		Assert.assertEquals("The status on the patch functionality is idle", AXDPAStatus.getaxdpaStatus("0"));
		Assert.assertEquals("The status on the patch functionality is installing", AXDPAStatus.getaxdpaStatus("1"));
		
		
	}
	
	@Test
	public void getaxdugStatus(){
		
		Assert.assertEquals("The status on the upgrade functionality is  Idle",AXDPAStatus.getaxdugStatus("1") );
		Assert.assertEquals("The status on the upgrade functionality is Installing",AXDPAStatus.getaxdugStatus("2") );
		Assert.assertEquals("The status on the upgrade functionality is Installed",AXDPAStatus.getaxdugStatus("3") );
		Assert.assertEquals("The status on the upgrade functionality is Activating", AXDPAStatus.getaxdugStatus("4"));
		Assert.assertEquals("The status on the upgrade functionality is activated",AXDPAStatus.getaxdugStatus("5") );
		Assert.assertEquals("The status on the upgrade functionality is committing",AXDPAStatus.getaxdugStatus("6") );
		Assert.assertEquals("The status on the upgrade functionality is committed",AXDPAStatus.getaxdugStatus("7") );
		Assert.assertEquals("The status on the upgrade functionality is RollingBack",AXDPAStatus.getaxdugStatus("8") );
		Assert.assertEquals("The status on the upgrade functionality is Rolledback", AXDPAStatus.getaxdugStatus("9"));
		
	}
	
	@Test
	public void getaxdbgpPeerState(){
		
		Assert.assertEquals("The BGP peer connection state is Idle", AXDPAStatus.getaxdbgpPeerState("1"));
		Assert.assertEquals("The BGP peer connection state is Connect",AXDPAStatus.getaxdbgpPeerState("2") );
		Assert.assertEquals("The BGP peer connection state is Active", AXDPAStatus.getaxdbgpPeerState("3"));
		Assert.assertEquals("The BGP peer connection state is OpenSent",AXDPAStatus.getaxdbgpPeerState("4") );
		Assert.assertEquals("The BGP peer connection state is OpenConfirm", AXDPAStatus.getaxdbgpPeerState("5"));
		Assert.assertEquals("The BGP peer connection state is Established", AXDPAStatus.getaxdbgpPeerState("6"));
		
	}
	
	@Test
	public void getaxdospfNbrState(){
		
		Assert.assertEquals("down",AXDPAStatus.getaxdospfNbrState("1"));
		Assert.assertEquals("attempt",AXDPAStatus.getaxdospfNbrState("2"));
		Assert.assertEquals("init",AXDPAStatus.getaxdospfNbrState("3"));
		Assert.assertEquals("twoway",AXDPAStatus.getaxdospfNbrState("4"));
		Assert.assertEquals("ExchangeStart",AXDPAStatus.getaxdospfNbrState("5"));
		Assert.assertEquals("exchange",AXDPAStatus.getaxdospfNbrState("6"));
		Assert.assertEquals("Loading",AXDPAStatus.getaxdospfNbrState("7"));
		Assert.assertEquals("Full",AXDPAStatus.getaxdospfNbrState("8"));
		
		
		
		
	}
	
	@Test
	public void getaxdisisISAdjState(){
		
		Assert.assertEquals("The state of the adjacency is down",AXDPAStatus.getaxdisisISAdjState("1"));
		Assert.assertEquals("The state of the adjacency is initializing",AXDPAStatus.getaxdisisISAdjState("2"));
		Assert.assertEquals("The state of the adjacency is up",AXDPAStatus.getaxdisisISAdjState("3"));
		Assert.assertEquals("The state of the adjacency is failed",AXDPAStatus.getaxdisisISAdjState("4"));
				
	}
	
	@Test
	public void getaxdmigLoopbackStatus(){
		Assert.assertEquals("The state of the permanently looped timeslots for LoopBack is failure", AXDPAStatus.getaxdmigLoopbackStatus("1"));
		Assert.assertEquals("The state of the permanently looped timeslots for LoopBack is success",AXDPAStatus.getaxdmigLoopbackStatus("2") );
		
		
	}
	
	@Test
	public void getaxdpingNotificationType(){
		
		Assert.assertEquals("ProbeFailure",AXDPAStatus.getaxdpingNotificationType("1"));
		Assert.assertEquals("TestFailure",AXDPAStatus.getaxdpingNotificationType("2"));
		Assert.assertEquals("TestCompletion",AXDPAStatus.getaxdpingNotificationType("3"));
		
		
		
	}
	
	@Test
	public void getaxdpingEventCtlTestName()
	{
		Assert.assertEquals("ProbeFailure",AXDPAStatus.getaxdpingEventCtlTestName("1"));
		Assert.assertEquals("TestFailure",AXDPAStatus.getaxdpingEventCtlTestName("2"));
		Assert.assertEquals("TestCompletion",AXDPAStatus.getaxdpingEventCtlTestName("3"));
		
	}
	
	@Test
	public void getaxdpingResultsOperStatus(){
		Assert.assertEquals("enabled",AXDPAStatus.getaxdpingResultsOperStatus("1"));
		Assert.assertEquals("disabled",AXDPAStatus.getaxdpingResultsOperStatus("2"));
		
	}
	
	@Test
	public void getaxdtraceRouteNotificationType()
	{
		Assert.assertEquals("pathChange",AXDPAStatus.getaxdtraceRouteNotificationType("1"));
		Assert.assertEquals("testFailure",AXDPAStatus.getaxdtraceRouteNotificationType("2"));
		Assert.assertEquals("testCompletion",AXDPAStatus.getaxdtraceRouteNotificationType("3"));
		
		
	}
	
	@Test
	public void getaxdifMauJabberState()
	{
		Assert.assertEquals("other",AXDPAStatus.getaxdifMauJabberState("1"));
		Assert.assertEquals("unknown",AXDPAStatus.getaxdifMauJabberState("2"));
		Assert.assertEquals("noJabber",AXDPAStatus.getaxdifMauJabberState("3"));
		Assert.assertEquals("jabbering",AXDPAStatus.getaxdifMauJabberState("4"));
		
	}
	@Test
	public void getaxdgcpLinkReasonCode(){
		
		Assert.assertEquals("Link communication is down.",AXDPAStatus.getaxdgcpLinkReasonCode("1"));
		Assert.assertEquals("Link communication is up.",AXDPAStatus.getaxdgcpLinkReasonCode("2"));
		
		
		
	}
	@Test
	public void getaxdlogRecAcStatus(){
		
		Assert.assertEquals("LogRecAcStatus=idle",AXDPAStatus.getaxdlogRecAcStatus("1"));
		Assert.assertEquals("LogRecAcStatus=executing",AXDPAStatus.getaxdlogRecAcStatus("2"));
		Assert.assertEquals("LogRecAcStatus=abort",AXDPAStatus.getaxdlogRecAcStatus("3"));
		Assert.assertEquals("LogRecAcStatus=abortOk",AXDPAStatus.getaxdlogRecAcStatus("4"));
		Assert.assertEquals("LogRecAcStatus=doneOk",AXDPAStatus.getaxdlogRecAcStatus("5"));
		Assert.assertEquals("LogRecAcStatus=doneFailure",AXDPAStatus.getaxdlogRecAcStatus("6"));
	}
	
	@Test
	public void getaxdsecOperation(){
		
		Assert.assertEquals("Operation type when security data is changed=new", AXDPAStatus.getaxdsecOperation("1"));
		Assert.assertEquals("Operation type when security data is changed=changed", AXDPAStatus.getaxdsecOperation("2") );
		Assert.assertEquals("Operation type when security data is changed=deleted", AXDPAStatus.getaxdsecOperation("3") );
		
		
	}
	@Test
	public void getaxdmrErrorCode(){
		
		Assert.assertEquals("The mesurement Error Code=unSpecified", AXDPAStatus.getaxdmrErrorCode("1"));
		Assert.assertEquals("The mesurement Error Code=noSuchObject",AXDPAStatus.getaxdmrErrorCode("2") );
		Assert.assertEquals("The mesurement Error Code=noSuchInstance", AXDPAStatus.getaxdmrErrorCode("3"));
		Assert.assertEquals("The mesurement Error Code=hardwareFault", AXDPAStatus.getaxdmrErrorCode("4"));
		Assert.assertEquals("The mesurement Error Code=noDataAvailable",AXDPAStatus.getaxdmrErrorCode("5") );
		Assert.assertEquals("The mesurement Error Code=other", AXDPAStatus.getaxdmrErrorCode("6"));
		Assert.assertEquals("The mesurement Error Code=noSuchName", AXDPAStatus.getaxdmrErrorCode("7"));
		Assert.assertEquals("The mesurement Error Code=badOid",AXDPAStatus.getaxdmrErrorCode("8") );
		
	}
	@Test
	public void getaxdrsvpIfOperStatus(){
		
		Assert.assertEquals("An event reporting that operational state of the RSVP rsvpIfOperStatus=up", AXDPAStatus.getaxdrsvpIfOperStatus("1"));
		Assert.assertEquals("An event reporting that operational state of the RSVP rsvpIfOperStatus=down", AXDPAStatus.getaxdrsvpIfOperStatus("2"));
		Assert.assertEquals("An event reporting that operational state of the RSVP rsvpIfOperStatus=grrestart",AXDPAStatus.getaxdrsvpIfOperStatus("3") );
		
		
	}
	@Test
	public void getaxdmplsXCAdminStatus(){
		
		Assert.assertEquals("If AdminStatus is=UP",AXDPAStatus.getaxdmplsXCAdminStatus("1"));
		Assert.assertEquals("If AdminStatus is=Down",AXDPAStatus.getaxdmplsXCAdminStatus("2"));
		Assert.assertEquals("If AdminStatus is=TESTING",AXDPAStatus.getaxdmplsXCAdminStatus("3"));
		
	}
	@Test
	public void getaxdmplsIfOperStatus(){
		Assert.assertEquals("An event reporting that an interface state  has changed to up", AXDPAStatus.getaxdmplsIfOperStatus("1"));
		Assert.assertEquals("An event reporting that an interface state  has changed to down",AXDPAStatus.getaxdmplsIfOperStatus("2") );
		
	}
	@Test
	public void getaxdmplsLdpSesState(){
		String secState1="An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState="
				+ "nonexistent";
		String secState2="An event reporting that the LDP session state,is changed to or from operational and  mplsLdpSesState="
				+ "initialized";
		String secState3= "An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState="
				+ "openrec";
		String secState4="An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState="
				+ "opensent";
		String secState5= "An event reporting that the LDP session state,is changed to or from operational and mplsLdpSesState="
				+ "operational";
		
		Assert.assertEquals(secState1, AXDPAStatus.getaxdmplsLdpSesState("1"));
		Assert.assertEquals(secState2, AXDPAStatus.getaxdmplsLdpSesState("2"));
		Assert.assertEquals(secState3,AXDPAStatus.getaxdmplsLdpSesState("3") );
		Assert.assertEquals(secState4, AXDPAStatus.getaxdmplsLdpSesState("4"));
		Assert.assertEquals(secState5,AXDPAStatus.getaxdmplsLdpSesState("5") );
		
		
	}
	@Test
	public void getaxdperfFileFcode(){
		
		Assert.assertEquals("echuck",AXDPAStatus.getaxdperfFileFcode("1") );
		Assert.assertEquals("eclosed",AXDPAStatus.getaxdperfFileFcode("2") );
		Assert.assertEquals("econn",AXDPAStatus.getaxdperfFileFcode("3") );
		Assert.assertEquals("ehost", AXDPAStatus.getaxdperfFileFcode("4"));
		Assert.assertEquals("elogin", AXDPAStatus.getaxdperfFileFcode("5"));
		Assert.assertEquals("enotbinary", AXDPAStatus.getaxdperfFileFcode("6"));
		Assert.assertEquals("epath", AXDPAStatus.getaxdperfFileFcode("7"));
		Assert.assertEquals("etype", AXDPAStatus.getaxdperfFileFcode("8"));
		Assert.assertEquals("euser", AXDPAStatus.getaxdperfFileFcode("9"));
		Assert.assertEquals("eunknown", AXDPAStatus.getaxdperfFileFcode("10"));
					
	}
	
	
	

}
