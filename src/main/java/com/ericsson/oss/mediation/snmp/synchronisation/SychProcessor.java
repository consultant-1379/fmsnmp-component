package com.ericsson.oss.mediation.snmp.synchronisation;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;

public class SychProcessor implements Processor{

	public SychProcessor(SNMPManagedElement me) {
		// TODO Auto-generated constructor stub
		System.out.println("synch request received for the me"+me.getIpAddress());
	}

	@Override
	public void process(Exchange exchange) throws Exception {
		// TODO Auto-generated method stub
		System.out.println("synch body is"+exchange.getIn().getBody());
	}

}
