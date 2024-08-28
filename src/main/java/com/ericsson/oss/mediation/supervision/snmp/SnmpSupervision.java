/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2013
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.mediation.supervision.snmp;

//import org.apache.camel.Exchange;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.fm.component.SnmpSupervisionProducer;
import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.model.EventNotification;



public class SnmpSupervision {

	private static final Logger LOGGER = LoggerFactory
			.getLogger(SnmpSupervision.class);
	private Processor processor;
	private Exchange exchange;
	private SNMPManagedElement managedElement;

	public SnmpSupervision() {

	}

	public SnmpSupervision(final SNMPManagedElement managedElement) {
		
		this.managedElement = managedElement;
	
	}

	public Processor getProcessor() {
		return processor;
	}

	public Exchange getExchange() {
		return exchange;
	}

	
	public void stopSupervision() {
		managedElement.setState(SNMPManagedElement.STATEESTABLISHED);
		final EventNotification[] notifications = managedElement.setState(
				SNMPManagedElement.STATEESTABLISHED, null);
		
		if(managedElement.isHbrasied() && notifications.length!=0){
		SnmpSupervisionProducer.forwardEvents(notifications, managedElement,exchange.getFromEndpoint());
		managedElement.setHbrasied(false);
		}
		
	}


	public EventNotification[] startSupervision() {
		EventNotification[] notifications = null;

		try {
			LOGGER.info("startSupervision SNMP...");
			
			notifications = managedElement.setState(SNMPManagedElement.STATEESTABLISHED,null);
			LOGGER.debug("returning notifications::::::::"+notifications+":"+managedElement.getFdn());
			
			
		} catch (Exception e) {
			notifications = managedElement.setState(SNMPManagedElement.STATEFAILED, e.getMessage());
			managedElement.setHbrasied(true);
			LOGGER.error("Could not attach consumer to Node. FDN: {}",
					managedElement.getFdn());
			if (notifications.length > 0) {
				LOGGER.debug(
						"Heartbeat alarm will be raised and reattach will be attempt when HB alarm clears. FDN {}, alarm: {}"+
						managedElement.getFdn()+ notifications[0].toString());
			}
		}
		return notifications;
	}

	/**
	 * @param processor
	 */
	public void setProcessor(final Processor processor) {
		this.processor = processor;
	}

	/**
	 * @param createExchange
	 */
	public void setExchange(final Exchange exchange) {
		this.exchange = exchange;
	}


}
