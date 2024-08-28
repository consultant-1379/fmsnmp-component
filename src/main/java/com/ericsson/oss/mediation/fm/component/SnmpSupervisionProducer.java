/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ericsson.oss.mediation.fm.component;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.StringTokenizer;

import org.apache.camel.Endpoint;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.snmp.synchronisation.CommonSnmpSync;
import com.ericsson.oss.mediation.supervision.snmp.SnmpSupervision;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.processor.EventModelTranslator;
import com.ericsson.oss.mediation.translator.processor.SnmpExchangeHeaders;
import com.ericsson.oss.mediation.translator.processor.SnmpSupervisionPduTranslationProcessor;
import com.ericsson.oss.services.fm.service.util.DPSConstants;
import com.ericsson.oss.services.fm.service.util.FDNConverter;
import com.ericsson.oss.mediation.translator.constant.SNMPMessageHeaders;

/*
 * The Snmp producer. This class is not needed for SNMP Supervision
 * Exchange Header 
 * Body will be MediationTaskRequest
 */

public class SnmpSupervisionProducer extends DefaultProducer {	

	private static final Logger LOGGER = LoggerFactory.getLogger(SnmpSupervisionProducer.class);
	private final int noSynchMaxRetrival = 3;

	public final  SnmpSupervisionEndpoint endpoint;
	SNMPManagedElement me = new SNMPManagedElement();


	public SnmpSupervisionProducer(final SnmpSupervisionEndpoint endpoint) 
	{
		super(endpoint);
		this.endpoint = endpoint;
	}

	private void initializeManagedElement(final SNMPManagedElement me,
			final Map<String, Object> headers) {
		try{
			LOGGER.debug("intialize starts");
			if(headers.get(SNMPMessageHeaders.SNMPSU_IPADDRESS)==null){
				me.setIpAddress("127.0.0.1");
			}else{
				me.setIpAddress(headers.get(SNMPMessageHeaders.SNMPSU_IPADDRESS).toString());
			}
			if(headers.get(SNMPMessageHeaders.SNMPSU_COMMUNITY)==null){
				me.setCommunityString("public");
			}else{
				me.setCommunityString(headers.get(SNMPMessageHeaders.SNMPSU_COMMUNITY).toString());
			}
			if(headers.get(SNMPMessageHeaders.SNMPSU_PORT)==null){
				me.setPort(161);
			}else{
				me.setPort(Integer.parseInt(headers.get(SNMPMessageHeaders.SNMPSU_PORT).toString()));
			}

			if(headers.get(SNMPMessageHeaders.SNMPSU_VERSION)==null){
				me.setProtocolVersion("v2c");
			}else{
				me.setProtocolVersion(headers.get(SNMPMessageHeaders.SNMPSU_VERSION).toString());
			}
			if(headers.get(DPSConstants.ACKNOWLEDGE_SUPPORTED)==null){
				me.setAcknowledgeSupported(false);
			}else{
				me.setAcknowledgeSupported(Boolean.valueOf(headers.get(DPSConstants.ACKNOWLEDGE_SUPPORTED).toString()));
			}
			if(headers.get(DPSConstants.CLEARALL_BEHAVIOUR)==null){
				me.setClearAllBehavior(false);
			}else{
				me.setClearAllBehavior(Boolean.valueOf(headers.get(DPSConstants.CLEARALL_BEHAVIOUR).toString()));
			}
			if(headers.get(DPSConstants.CLEARALL_BEHAVIOUR)==null){
				me.setCommunicationTimeout(60);
			}else{
				me.setCommunicationTimeout((Integer.parseInt(headers.get(DPSConstants.COMMUNICATION_TIMEOUT).toString())));
			}
			if( headers.get(DPSConstants.DELTA_SYNCH_SUPPORTED)==null){
				me.setDeltaSynchSupported(false);
			}else{
				me.setDeltaSynchSupported((Boolean.valueOf( headers.get(DPSConstants.DELTA_SYNCH_SUPPORTED).toString())));
			}
			if(headers.get(DPSConstants.SOURCE_TYPE)==null){
				LOGGER.error("initializeManagedElement:Setting System type none");
				me.setSourceType("none");	
			}else{
				me.setSourceType((String) headers.get(DPSConstants.SOURCE_TYPE));
			}
			if(headers.get(DPSConstants.FILTER_INFO)==null){
				me.setFilterInfo("none");
			}else{
				me.setFilterInfo((String) headers.get(DPSConstants.FILTER_INFO));
			}
			if(headers.get(DPSConstants.HEARTBEAT_TIMEOUT)==null){
				me.setHeartbeatTimeout(120);
			}else{
				me.setHeartbeatTimeout((Integer.parseInt(headers
						.get(DPSConstants.HEARTBEAT_TIMEOUT).toString())));
			}

			if( headers.get(DPSConstants.SOURCE_SYNCH_SUPPORTED)==null){
				me.setSourceSynchSupported(false);
			}else{
				me.setSourceSynchSupported(Boolean.valueOf( headers
						.get(DPSConstants.SOURCE_SYNCH_SUPPORTED).toString()));
			}

			if( headers.get(DPSConstants.SUBORDINATE_OBJECT_SYNCSUPPORTED)==null){
				me.setSubordinateObjectSyncSupported(false);
			}else{
				me.setSubordinateObjectSyncSupported(Boolean.valueOf( headers
						.get(DPSConstants.SUBORDINATE_OBJECT_SYNCSUPPORTED).toString()));

			}
			if( headers.get(DPSConstants.SYNCH_ON_COMMIT_FAILURE_CLEAR)==null){
				me.setSyncOnCommitFailureClear(false);
			}else{
				me.setSyncOnCommitFailureClear(Boolean.valueOf( headers
						.get(DPSConstants.SYNCH_ON_COMMIT_FAILURE_CLEAR).toString()));
			}
			LOGGER.debug("intialize over");

		}
		catch(Exception e){
			LOGGER.error("Exception in initializeManagedElement("+e.getMessage());
		}
	}

	private void processSupervisionOn(final String NodeAddress,
			final Map<String, Object> headers) {
		LOGGER.debug("SNMP processSupervisionOn called");
		if (endpoint.getMe(NodeAddress) == null) {

			final SNMPManagedElement me = new SNMPManagedElement();
			me.setFdn(NodeAddress);
			initializeManagedElement(me, headers);
			LOGGER.debug("Node Details for supervision request " + me.toString());
			final SnmpSupervision snmpSupervision = new SnmpSupervision(me);

			snmpSupervision.setProcessor(endpoint.getConsumer().getProcessor());
			snmpSupervision.setExchange(endpoint.createExchange());
			me.setSupervision(snmpSupervision);

			endpoint.addMe(me.getFdn(), me);	
			endpoint.addMeIp(me.getIpAddress(), me);

			final EventNotification[] notifications = snmpSupervision
					.startSupervision();
			if(me.isHbrasied()){
				LOGGER.debug("if(me.isHbrasied()){ " );
				forwardEvents(notifications, me,endpoint);
			}

			endpoint.addMe(me.getFdn(), me);

			endpoint.getHbDispatcher().startSupervision(me);
		}else
		{
			LOGGER.info("Supervision is already started for the fdn  {}", me.getFdn());
		}
	}



	private void processSupervisionOff(final String NodeAddress)
	{
		LOGGER.debug("SNMP processSupervisionOff called");
		final SNMPManagedElement me = endpoint.getMe(NodeAddress);
		if (me != null) {
			LOGGER.debug("Stopping Supervision for node " + me.toString());
			try {
				endpoint.getCamelContext().stopRoute(me.getFdn());
				endpoint.getHbDispatcher().stopSupervision(me);
				me.getSupervision().stopSupervision();
				endpoint.getCamelContext().removeRoute(me.getFdn());

			} catch (Exception e) {
				LOGGER.error("Exception in  processSupervisionOff" + e.toString());
			}
			finally {
				endpoint.removeMeIp(me.getIpAddress());
				endpoint.removeMe(NodeAddress);
				LOGGER.debug("Me Removed " + NodeAddress);
			}
		}
	}	
	public static void forwardEvents(final EventNotification[] notifications,
			final SNMPManagedElement me,Endpoint endpoint) 
	{
		LOGGER.debug("Inside forwardEvents method ");
		if ((notifications != null) && (notifications.length > 0)) {
			List<EventNotification> eventList = new ArrayList<EventNotification>();
			final Processor modelTranslator = new EventModelTranslator();
			final Exchange exchange = endpoint.createExchange();
			exchange.getIn().setHeader(SnmpExchangeHeaders.HEADER_TYPE,
					SnmpExchangeHeaders.TYPE_ALARM);
			exchange.getIn().setHeader(SnmpExchangeHeaders.HEADER_ME, me);
			eventList=Arrays.asList(notifications);
			exchange.getIn().setBody(eventList);
			try {

				modelTranslator.process(exchange);
				LOGGER.debug("after putting into process method of EventModelTranslator::");
			} catch (Exception e) {
				LOGGER.error(
						"Failed to forward event generated during supervision, FDN="
								+ me.getFdn()+ (Throwable) e);
			}
		}
	}


	public void process(final Exchange exchange) {
		LOGGER.debug("SNMP componenet supervision producer process called");
		final String torFdnAttr=",FmAccess=1";
		final Map<String, Object> headers = exchange.getIn().getHeaders();
		Set<String> keys = headers.keySet();

		for (Iterator<String> i = keys.iterator(); i.hasNext(); )
		{
			String key = (String) i.next();
			Object value =  headers.get(key);
			LOGGER.debug("header:"+key + " = " + value);
		}

		String requestType=headers.get("REQUEST").getClass().toString();
		requestType=requestType.substring(requestType.lastIndexOf(".")+1);

		LOGGER.debug("Task type received in SNMP component"+requestType);


		if (requestType.equals("FmSnmpMediationSupervisionRequest"))
		{
			try{
				final String nodeAddress=(String)headers.get(SNMPMessageHeaders.SNMP_NODE);
				final String isSupervisionOn=(String)headers.get(SNMPMessageHeaders.ALARM_SUPERVISION);
				String ossFdn=FDNConverter.convertTorFdnToOssFdn(nodeAddress);
				ossFdn=ossFdn.replace(torFdnAttr, "");
				LOGGER.debug("ossFdn:"+ossFdn);
				if (isSupervisionOn.equalsIgnoreCase("true")) {
					processSupervisionOn(ossFdn, headers);
				} else {
					processSupervisionOff(ossFdn);
				}
			}catch(Exception e){
				LOGGER.error("Error while processing supervision request:"+e.getMessage());
			}
		}
		else if(requestType.equals("FmSnmpMediationAlarmSyncRequest"))
		{
			final String nodeAddress=(String)headers.get(SNMPMessageHeaders.SNMP_NODE);
			String ossFdn=FDNConverter.convertTorFdnToOssFdn(nodeAddress);
			ossFdn=ossFdn.replace(torFdnAttr, "");
			LOGGER.debug("FDN after convertTorFdnToOssFdn"+ossFdn+":NodeAddress"+nodeAddress);
			processSync(ossFdn, headers);
			LOGGER.debug("ossFdn in synch:"+ossFdn);					

		}			
		else 
		{	
			LOGGER.debug("Acknowledge is not suppotted for this node");

		}
		try{

			endpoint.getConsumer().getProcessor().process(exchange);
		}
		catch (Exception e) {
			LOGGER.error("Exception for getProcessor():"+e.getMessage());
		}

	}


	private void processSync(String address,final Map<String, Object> headers) {
		LOGGER.debug("processSync called");
		SnmpSupervisionPduTranslationProcessor snmpSupervisionPduTranslationProcessor=null;
		final SNMPManagedElement me = endpoint.getMe(address);

		if (me != null) {
			final CommonSnmpSync snmpSynch = new CommonSnmpSync(me);

			try {
				EventNotification[] notifications = snmpSynch.synchronize(me);
				LOGGER.debug("notifications are:"+notifications);
				snmpSupervisionPduTranslationProcessor=new SnmpSupervisionPduTranslationProcessor();
				for(int i=0;i<notifications.length;i++){
					notifications[i]=snmpSupervisionPduTranslationProcessor.applyManagedElementData(notifications[i], me);
					LOGGER.debug("after apply managed element"+notifications[i]);
				}
				final EventNotification[] syncEnd = snmpSynch.stopSynch();
				LOGGER.debug("syncEnd notifications are:"+syncEnd);
				syncEnd[0].setSyncNotifications(notifications);	
				forwardEvents(syncEnd,me,endpoint);
			} catch (Exception e) {
				LOGGER.error(
						"Exception in SNMP synch processor, fdn="
								+ me.getFdn()+ (Throwable) e);
				final EventNotification[] syncAbort = snmpSynch.abortSynch();
				forwardEvents(syncAbort, me,endpoint);
			}
		} else {
			LOGGER.debug("Process Synch Called before Start Supervision for FDN: "
					+ address);
			retrySynch(address, headers);
		}
	}


	/*	private void processAcknowledge(final FmMediationAckRequest ackRequest) {
		final SNMPManagedElement  me = endpoint.getMe(ackRequest
				.getNodeAddress());
		if (me != null) {

			LOGGER.debug("ack is not supported to node "+ ackRequest.getNodeAddress());
			final SnmpAcknowledgeRequest snmpAckRequest = new SnmpAcknowledgeRequest(me);
			try{
				snmpAckRequest.acknowledge(ackRequest);

			}catch(Exception e){
				LOGGER.debug("Exception while ack request:"+e.getMessage());
			}
		} else {
			LOGGER.debug("Supervision is not started on FDN "+ ackRequest.getNodeAddress());
		}
	}*/


	public static String convertTorFdnToOssFdn(final String torFdn) {
		LOGGER.debug("starting of convertTorFdnToOssFdn:::"+torFdn);
		int i = 0;
		String result = "";
		final StringTokenizer torToken = new StringTokenizer(torFdn, ",");
		while (torToken.hasMoreElements()) {
			final String[] fdnPart = ((String) torToken.nextElement())
					.split("=");
			String convertedPart = inveseConversion.get(fdnPart[0].trim());
			if (i !=0 ) {result +=",";}
			if (convertedPart != null) {
				result += convertedPart + "=" + fdnPart[1];
			} else {
				result += fdnPart[0].trim()+ "=" + fdnPart[1];
			}

			i++;
		}
		LOGGER.debug("Before result:::"+result);
		return result;
	}
	private static Map<String, String> conversion = new HashMap<>();
	private static Map<String, String> inveseConversion = new HashMap<>();
	static {
		// If any of network model element names are changed, map values need to
		// be updated
		conversion.put("SubNetwork", "NW");
		conversion.put("NonRootSubNetwork", "SN");
		conversion.put("MeContext", "MeC");
	}
	static {
		// If any of network model element names are changed, map values need to
		// be updated
		inveseConversion.put("NW", "SubNetwork");
		inveseConversion.put("SN", "SubNetwork");
		inveseConversion.put("MeC", "MeContext");
	}


/*	public  Map<String,Object> createHeaders() {
		final Map<String, Object>  headers = new HashMap<String, Object>();

		headers.put(DPSConstants.AUTOMATIC_SYNCHRONIZATION,"true");
		headers.put(DPSConstants.IS_NODESUSPENDED,"false");
		headers.put(DPSConstants.ALARM_SUPERVISION,"true");
		headers.put(DPSConstants.HEARTBEAT_SUPERVISON,"true");
		headers.put(DPSConstants.HEARTBEAT_TIMEOUT,
				"30");
		headers.put(DPSConstants.DELTA_SYNCH_SUPPORTED,
				"true");
		headers.put(DPSConstants.SOURCE_SYNCH_SUPPORTED,
				"true");
		headers.put(DPSConstants.SYNCH_ON_COMMIT_FAILURE_CLEAR,
				"true");
		headers.put(DPSConstants.ACKNOWLEDGE_SUPPORTED,
				"true");
		headers.put(DPSConstants.CLOSED_SUPPORTED,
				"true");
		headers.put(DPSConstants.COMMUNICATION_TIMEOUT,
				"20");
		headers.put(DPSConstants.SUBORDINATE_OBJECT_SYNCSUPPORTED,
				"true");
		headers.put(DPSConstants.FILTER_INFO, "");
		headers.put(DPSConstants.CLEARALL_BEHAVIOUR, "true");
		//headers.put(DPSConstants.SOURCE_TYPE, "JAMBALA");
		headers.put(DPSConstants.SOURCE_TYPE, "WPP");
		return headers;
	}	*/

	@SuppressWarnings("static-access")
	public void retrySynch(final String nodeAddress,
			final Map<String, Object> headers) {
		LOGGER.debug("Trying retreive Start Synch for a while and check the status of Me");
		final SNMPManagedElement me = endpoint.getMe(nodeAddress);
		int retrySynchCounter = 0;
		final CommonSnmpSync snmpSynch = new CommonSnmpSync(me);
		while (retrySynchCounter < noSynchMaxRetrival) {
			try {
				Thread.currentThread().sleep(1000);
				if (me != null) {
					retrySynchCounter = 0;
					processSync(nodeAddress, headers);
					break;
				} else {
					retrySynchCounter += 1;
				}
			} catch (InterruptedException e) {
				LOGGER.error("Exception in Getting Synch Sleep {}", e.getMessage());
				retrySynchCounter += 1;
			}
		}
		if (retrySynchCounter >= noSynchMaxRetrival) {
			String sourceType = (String) headers.get(DPSConstants.SOURCE_TYPE);
			LOGGER.debug(" Source Type for given node with FDN: "
					+ nodeAddress + " is: " + sourceType);

			LOGGER.debug(" Raise Synch Abort Notification due to the supervision not yet started with FDN: {}"+ nodeAddress);
			EventNotification[] syncAbort = snmpSynch.abortSynch();
			forwardEvents(syncAbort, me, endpoint);

		}
	}


}

