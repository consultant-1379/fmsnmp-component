package com.ericsson.oss.mediation.translator.constant;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class SnmpBindAddress {
	
	
	/**
	 * @return Binding IP Address of the JBOSS server
	 */

	public static String getBindingAddress() throws UnknownHostException {
		String finalListeningIP = System.getProperty("jboss.bind.address");
			
		if (finalListeningIP != null) {

			if (finalListeningIP.equalsIgnoreCase("0.0.0.0")
					|| finalListeningIP.equalsIgnoreCase("127.0.0.1")) {
				finalListeningIP = InetAddress.getLocalHost().getHostAddress()
						.toString();
			}
		} else {
			finalListeningIP = InetAddress.getLocalHost().getHostAddress()
					.toString();
		}
		return finalListeningIP;
	}
	
	

}
