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

import org.apache.camel.CamelContext;
import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.apache.camel.RoutesBuilder;
import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.impl.DefaultConsumer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.ExceptionListener;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.ObjectMessage;
import javax.jms.Session;
import javax.jms.Topic;
import javax.naming.InitialContext;

import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.UDPProtocolOptions;
import com.ericsson.oss.mediation.cacheapibean.MibCachingInterfaceBean;
import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.snmp.heratbeat.component.SNMPHBComponent;
import com.ericsson.oss.mediation.snmp.heratbeat.component.SNMPHBConsumer;
import com.ericsson.oss.mediation.translator.processor.EventModelTranslator;
import com.ericsson.oss.mediation.translator.processor.SnmpExchangeHeaders;
import com.ericsson.oss.mediation.translator.processor.SnmpSupervisionPduTranslationProcessor;
import com.ericsson.snmp.cache.lookup.CacheBeanLookUp;

/**
 * The SnmpSupervisionComponentConsumer class which will receive the SNMP Trap
 * and process it and will forward it for Translation.
 */
public class SnmpSupervisionConsumer extends DefaultConsumer implements MessageListener, ExceptionListener
{
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SnmpSupervisionConsumer.class);

	private final SnmpSupervisionEndpoint endpoint;
	@SuppressWarnings("unused")
	private final Processor snmpProcessor;
	Exchange exchange=null;




	public SnmpSupervisionConsumer(
			final SnmpSupervisionEndpoint endpoint, final Processor processor) {
		super(endpoint, processor);
		this.endpoint = endpoint;
		this.snmpProcessor = processor;

	}

	@Override
	protected void doStart()  {
		try{
			startHBSupervisionRoute();

			LOGGER.info("SnmpSupervisionConsumer:Before calling the getMIBCacheBean_Reference()");
			final MibCachingInterfaceBean mibCachingInterface = CacheBeanLookUp.getMIBCacheBean_Reference();
			mibCachingInterface.readFileFromModelService();
			LOGGER.debug("SNMP Consumer Started");
			InitialContext ctx = new InitialContext();
			ConnectionFactory connFactory = (ConnectionFactory) ctx.
					lookup("java:/ConnectionFactory");
			LOGGER.info("connFactory:"+connFactory);
			Connection topicConn = connFactory.createConnection(); 
			LOGGER.debug("topicConn:"+topicConn);
			topicConn.start();


			Destination topic = (Destination) ctx.lookup("topic/SnmpTrapListenerTopic");
			LOGGER.debug("topic:"+topic);

			if(topic instanceof Topic)
			{
				Session topicSession = topicConn.createSession(false,
						Session.AUTO_ACKNOWLEDGE);
				LOGGER.debug("topicSession:"+topicSession);


				MessageConsumer topicSubscriber = topicSession.createConsumer(topic);
				LOGGER.debug("topicSubscriber:"+topicSubscriber);

				topicSubscriber.setMessageListener(this);
				LOGGER.debug("asyncSubscriber:added");
				topicConn.setExceptionListener(this);

			}
		}catch(Exception e){
			LOGGER.error("Exception in the doStart method:"+e.toString());
			e.printStackTrace();
		}
	}

	@Override
	public void onException(JMSException exception) {
		exception.printStackTrace();
		LOGGER.debug("SNMP supervision consumer:onException:Exception is :" + exception);
	}

	private void startHBSupervisionRoute() {
		LOGGER.info("startHBSupervisionRoute Called");
		final CamelContext context = endpoint.getCamelContext();
		final RoutesBuilder rb = new RouteBuilder() {
			public void configure() {
				from("SNMPHBSupervision:start").routeId(SNMPHBComponent.routeId)
				.to("log:HB route end");
			}
		};

		try {
			context.addRoutes(rb);
			context.startRoute(SNMPHBComponent.routeId);
			SNMPHBConsumer consumer = (SNMPHBConsumer)context.getRoute(SNMPHBComponent.routeId).getConsumer();
			endpoint.setHbDispatcher(consumer.getDispatcher()) ;
		} catch (Exception e) {
			LOGGER.error(
					"Could not start Heartbeat supervision route ", (Throwable) e);
		}
	}

	@Override
	public void onMessage(Message message) {
		LOGGER.info("Trap received in SNMP component:onMessage called..");
		try {
			if (message instanceof ObjectMessage) { 
				LOGGER.debug(((ObjectMessage)message).getJMSMessageID()); 
				Object objectData = ((ObjectMessage)message).getObject(); 
				if (objectData instanceof SnmpPDU) { 
					SnmpPDU pdu = (SnmpPDU) objectData;
					processPdu(pdu);
				}

			}
		}catch (Throwable ex) {
			LOGGER.error("Exception in SnmpSupervisionConsumer:onMessage"+ex.toString());
		}

	}

	public void processPdu(final SnmpPDU pdu) {
		LOGGER.debug("SnmpSupervisionConsumer:Printing the varbinds in PDU:"+pdu.printVarBinds());
		SNMPManagedElement snmpManagedElement=null;
		String address=null;
		try{
			this.exchange=this.endpoint.createExchange();
			exchange.getIn().setHeader(SnmpExchangeHeaders.HEADER_TYPE, SnmpExchangeHeaders.TYPE_ALARM);

			UDPProtocolOptions options= (UDPProtocolOptions) pdu.getProtocolOptions();
			LOGGER.debug("getRemoteAddressAsString"+options.getRemoteAddressAsString());

			address=options.getRemoteAddressAsString();			

			snmpManagedElement=endpoint.getMeWithIp(address);

			if(snmpManagedElement!=null){
				LOGGER.debug("Managed element ip address is"+snmpManagedElement.getIpAddress());
				exchange.getIn().setHeader(SnmpExchangeHeaders.HEADER_ME, snmpManagedElement);
				exchange.getIn().setBody(pdu);
				SnmpSupervisionPduTranslationProcessor snmpSupervisionPduTranslationProcessor=new SnmpSupervisionPduTranslationProcessor();
				snmpSupervisionPduTranslationProcessor.process(exchange);
				EventModelTranslator eventModelTranslator=new EventModelTranslator();
				eventModelTranslator.process(exchange);
			}else{
				LOGGER.info("Supervision is not started for the node with ip:{}, so trap will be dropped",address);

			}
		}
		catch (Exception e) {
			LOGGER.error("EXception in SnmpSupervisionConsumer:processPdu after translation"+e.toString());

		}



	}




}
