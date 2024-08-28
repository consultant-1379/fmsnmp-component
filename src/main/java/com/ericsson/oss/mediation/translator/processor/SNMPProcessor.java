package com.ericsson.oss.mediation.translator.processor;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

public class SNMPProcessor implements Processor{

	@Override
	public void process(Exchange exchange) throws Exception {
		System.out.println("SNMP processor in route is Ready");
		
	}

}
