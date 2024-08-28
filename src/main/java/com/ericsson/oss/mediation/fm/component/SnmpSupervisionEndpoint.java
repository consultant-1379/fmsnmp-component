/**
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

import java.util.*;

import org.apache.camel.*;
import org.apache.camel.impl.DefaultEndpoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.snmp.heratbeat.HBDispatcher;


/**
 * This class represents an event driven snmp endpoint
 * 
 */
public class SnmpSupervisionEndpoint extends DefaultEndpoint {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SnmpSupervisionEndpoint.class);

	SnmpSupervisionConsumer consumer;
	SnmpSupervisionProducer producer;
	private final Map<String, SNMPManagedElement> supervisedMEs = Collections
			.synchronizedMap(new HashMap<String, SNMPManagedElement>());
	private final Map<String, SNMPManagedElement> supervisedMEsIp = Collections
			.synchronizedMap(new HashMap<String, SNMPManagedElement>());
	private HBDispatcher hbDispatcher;
	private SNMPManagedElement managedElement;
	@SuppressWarnings("unused")
	private Exchange exchange;
	

	public SnmpSupervisionEndpoint() {
	}

	public SnmpSupervisionEndpoint(final String uri,
			final SnmpSupervisionComponent component) {
		super(uri, component);
		this.exchange=this.createExchange();
		LOGGER.info("SnmpSupervisionEndpoint constructor called");
	}

	public SnmpSupervisionEndpoint(final String endpointUri) {
		super(endpointUri);
	}

	public Producer createProducer() {
		if (producer == null) {
			producer = new SnmpSupervisionProducer(this);
		}
		return producer;
	}

	public Consumer createConsumer(final Processor processor) {
		LOGGER.info("Create the Cosumer Instance :::");
		if (consumer == null) {
			consumer = new SnmpSupervisionConsumer(this, processor);
		}
		return consumer;
	}

	public boolean isSingleton() {
		return false;
	}



	/**
	 * @param fdn
	 * @param me
	 */
	public void addMe(final String fdn, final SNMPManagedElement me) {
		supervisedMEs.put(fdn, me);

	}

	/**
	 * @param fdn
	 */
	public void removeMe(final String fdn) {
		supervisedMEs.remove(fdn);

	}
	
	/**
	 * @param fdn
	 * @param me
	 */
	public void addMeIp(final String Ip, final SNMPManagedElement me) {
		supervisedMEsIp.put(Ip, me);

	}

	/**
	 * @param fdn
	 */
	public void removeMeIp(final String Ip) {
		supervisedMEsIp.remove(Ip);

	}

	/**
	 * @param nodeAddress
	 * @return
	 */
	public SNMPManagedElement getMe(final String fdn) {
		this.managedElement=supervisedMEs.get(fdn);
		return this.managedElement;
	}
	
	/**
	 * @param nodeAddress
	 * @return
	 */
	public SNMPManagedElement getMeWithIp(final String Ip) {
		this.managedElement=supervisedMEsIp.get(Ip);
		return this.managedElement;
	}
	public SnmpSupervisionConsumer getConsumer() {
		return consumer;
	}
	
	/**
	 * @return the hbDispatcher
	 */
	public HBDispatcher getHbDispatcher() {
		return hbDispatcher;
	}

	/**
	 * @param hbDispatcher the hbDispatcher to set
	 */
	public void setHbDispatcher(HBDispatcher hbDispatcher) {
		this.hbDispatcher = hbDispatcher;
	}
}
