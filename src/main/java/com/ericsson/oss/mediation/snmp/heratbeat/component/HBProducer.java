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
package com.ericsson.oss.mediation.snmp.heratbeat.component;

import org.apache.camel.Exchange;
import org.apache.camel.impl.DefaultProducer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * The HelloWorld producer.
 */
public class HBProducer extends DefaultProducer {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(HBProducer.class);
   private SNMPHBEndpoint endpoint;

    public HBProducer(SNMPHBEndpoint endpoint) {
        super(endpoint);
        LOGGER.info("HBProducer: Constructor");
        this.endpoint = endpoint;
    }

    public void process(Exchange exchange) throws Exception {
    }

}
