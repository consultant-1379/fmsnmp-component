package com.ericsson.oss.mediation.translator.constant;

public class MMCSpecificProblem {
	/**
	 * to avoid PMD warnings 
	 */
	private MMCSpecificProblem()
	{
		
	}
	private static final String SP100 = "Unable to complete RMI registration.";
	private static final String SP102 = "Unable to log.";
	private static final String SP104 = "Unable to send charging information.";
	private static final String SP106 = "Unable to communicate with server.";
	private static final String SP108 = "Unable to communicate with relay.";
	private static final String SP10A = "Unable to communicate with SOS.";
	private static final String SP10C = "Unable to communicate with topology registry.";
	private static final String SP10E = "Unable to communicate with database.";
	private static final String SP110 = "Unable to communicate with external system.";
	private static final String SP112 = "Unable to route.";
	private static final String SP114 = "Unable to communicate with SSMG.";
	private static final String SP116 = "Unable to communicate with PIPS.";
	private static final String SP118 = "Unable to communicate with MNR.";
	private static final String SP11A = "Unable to communicate with RATE.";
	private static final String SP400 = "Unable to load configuration parameters.";
	private static final String SP402 = "General failure.";
	private static final String SP500 = "System Down.";
	private static final String SP502 = "System Locked.";
	private static final String SP504 = "Subscriber license limit reached.";
	private static final String SP506 = "Message throughput limit reached.";
	private static final String SP508 = "Component license limit reached.";
	private static final String SP50A = "Resource Utilization.";
	

	public static String getSpecificProblem(final String alId) {
		String SP = "";
		if ((alId.equals("x100")) || (alId.equals("x101"))) {
			SP = SP100;
		}
		if ((alId.equals("x102")) || (alId.equals("x103"))) {
			SP = SP102;
		}
		if ((alId.equals("x104")) || (alId.equals("x105"))) {
			SP = SP104;
		}
		if ((alId.equals("x106")) || (alId.equals("x107"))) {
			SP = SP106;
		}
		if ((alId.equals("x108")) || (alId.equals("x109"))) {
			SP = SP108;
		}
		if ((alId.equalsIgnoreCase("x10a")) || (alId.equalsIgnoreCase("x10B"))) {
			SP = SP10A;
		}
		if ((alId.equalsIgnoreCase("x10C")) || (alId.equalsIgnoreCase("x10D"))) {
			SP = SP10C;
		}
		if ((alId.equalsIgnoreCase("x10E")) || (alId.equalsIgnoreCase("x10F"))) {
			SP = SP10E;
		}
		if ((alId.equals("x110")) || (alId.equals("x111"))) {
			SP = SP110;
		}
		if ((alId.equals("x112")) || (alId.equals("x113"))) {
			SP = SP112;
		}
		if ((alId.equals("x114")) || (alId.equals("x115"))) {
			SP = SP114;
		}
		if ((alId.equals("x116")) || (alId.equals("x117"))) {
			SP = SP116;
		}
		if ((alId.equals("x118")) || (alId.equals("x119"))) {
			SP = SP118;
		}
		if ((alId.equalsIgnoreCase("x11a")) || (alId.equalsIgnoreCase("x11B"))) {
			SP = SP11A;
		}
		if ((alId.equals("x400")) || (alId.equals("x401"))) {
			SP = SP400;
		}
		if ((alId.equals("x402")) || (alId.equals("x403"))) {
			SP = SP402;
		}
		if ((alId.equals("x500")) || (alId.equals("x501"))) {
			SP = SP500;
		}
		if ((alId.equals("x502")) || (alId.equals("x503"))) {
			SP = SP502;
		}
		if ((alId.equals("x504")) || (alId.equals("x505"))) {
			SP = SP504;
		}
		if ((alId.equals("x506")) || (alId.equals("x507"))) {
			SP = SP506;
		}
		if ((alId.equals("x508")) || (alId.equals("x509"))) {
			SP = SP508;
		}
		if ((alId.equalsIgnoreCase("x50a")) || (alId.equalsIgnoreCase("x50b"))) {
			SP = SP50A;
		}
		if (alId.equalsIgnoreCase("x999")) {
			SP = "Unknown";
		}
		return SP;
	}

}
