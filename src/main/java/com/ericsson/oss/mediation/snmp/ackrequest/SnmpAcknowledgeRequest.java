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
 *----------------------------------------------------------------------------
package com.ericsson.oss.mediation.snmp.ackrequest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.snmp.heratbeat.CommunicationFailureException;
import com.ericsson.oss.services.fm.service.model.FmMediationAckRequest;


public class SnmpAcknowledgeRequest {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SnmpAcknowledgeRequest.class);
	final private SNMPManagedElement me;

	public SnmpAcknowledgeRequest(final SNMPManagedElement me) {
		this.me = me;
	}

	public void acknowledge(final FmMediationAckRequest ackRequest)
			throws CommunicationFailureException {
		final List<String> alrmList = ackRequest.getAlarmId();
		final String[] alarmInformationIdList = alrmList.toArray(new String[alrmList.size()]);
		//final String ackUserId = ackRequest.getOperatorName();
		//final String ackSystemId = "";
		
		try {
			LOGGER.debug("yet to be implemented.......");


		} catch (final Exception e) {
			LOGGER.error(
					"Exception caught while performing ack/unack on Node. FDN="+
							me.getFdn() + "Request=" + ackRequest.toString(), (Throwable) e);
			throw new CommunicationFailureException(e.toString());
		}
	}

}
*/