package com.ericsson.oss.mediation.translator.model.handlers;

/**
 * @author tcsgopa
 * 
 */
public class BSComponent {

	/**
	 * private constructor to avoid PMD warings
	 */
	private BSComponent() {

	}

	public static String getBSComponent(final String attributevalue) {
		final StringBuffer str1 = new StringBuffer();
		switch (Integer.parseInt(attributevalue)) {
		case 0:
			str1.append("\nComponent=").append("Applicationserver");
			break;
		case 1:
			str1.append("\nComponent=").append("Mediaserver");
			break;
		case 2:
			str1.append("\nComponent=").append("Networkserver");
			break;
		case 3:
			str1.append("\nComponent=").append("Relayserver");
			break;
		case 4:
			str1.append("\nComponent=").append("Servicecontrolproxy");
			break;
		case 5:
			str1.append("\nComponent=").append("Elementmanagementsystem");
			break;
		case 6:
			str1.append("\nComponent=").append("Webserver");
			break;
		case 7:
			str1.append("\nComponent=").append("Calldetailserver");
			break;
		case 8:
			str1.append("\nComponent=").append("clientmanagementprofileserver");
			break;

		}
		return str1.toString();
	}

	public static String getBSSubComponent(final String attributevalue) {
		final StringBuffer str = new StringBuffer();

		switch (Integer.parseInt(attributevalue)) {
		case 0:
			str.append("\nSubComponent=").append("Unspecified");
			break;
		case 1:
			str.append("\nSubComponent=").append("Processmonitor");
			break;
		case 2:
			str.append("\nSubComponent=").append("Webserver");
			break;
		case 3:
			str.append("\nSubComponent=").append("Database");
			break;
		case 4:
			str.append("\nSubComponent=").append("Sip");
			break;
		case 5:
			str.append("\nSubComponent=").append("Ccp");
			break;
		case 6:
			str.append("\nSubComponent=").append("Mgcp");
			break;
		case 7:
			str.append("\nSubComponent=").append("Mcp");
			break;
		case 8:
			str.append("\nSubComponent=").append("Smtp");
			break;
		case 9:
			str.append("\nSubComponent=").append("Pop3");
			break;
		case 10:
			str.append("\nSubComponent=").append("Rtcp");
			break;
		case 11:
			str.append("\nSubComponent=").append("Conferencing");
			break;
		case 12:
			str.append("\nSubComponent=").append("Rtp");
			break;
		case 13:
			str.append("\nSubComponent=").append("Ivr");
			break;
		case 14:
			str.append("\nSubComponent=").append("Filesystem");
			break;
		case 15:
			str.append("\nSubComponent=").append("Callp");
			break;
		case 16:
			str.append("\nSubComponent=").append("Nssynch");
			break;
		case 17:
			str.append("\nSubComponent=").append("Mss");
			break;
		case 18:
			str.append("\nSubComponent=").append("Transevent");
			break;
		case 19:
			str.append("\nSubComponent=").append("Emergency");
			break;
		case 20:
			str.append("\nSubComponent=").append("Smap");
			break;
		case 21:
			str.append("\nSubComponent=").append("Loggingserver");
			break;
		case 22:
			str.append("\nSubComponent=").append("Nslocation");
			break;
		case 23:
			str.append("\nSubComponent=").append("Ims");
			break;
		case 24:
			str.append("\nSubComponent=").append("Nrs");
			break;
		case 25:
			str.append("\nSubComponent=").append("Oss");
			break;
		case 26:
			str.append("\nSubComponent=").append("Accounting");
			break;
		case 27:
			str.append("\nSubComponent=").append("Licensing");
			break;
		case 28:
			str.append("\nSubComponent=").append("Ldap");
			break;
		case 29:
			str.append("\nSubComponent=").append("PmReporting");
			break;
		case 30:
			str.append("\nSubComponent=").append("Smdi");
			break;
		case 31:
			str.append("\nSubComponent=").append("CpeDeviceManagement");
			break;
		case 32:
			str.append("\nSubComponent=").append("NetworkDeviceManagement");
			break;
		case 33:
			str.append("\nSubComponent=").append("ExternalAuthentication");
			break;
		case 34:
			str.append("\nSubComponent=").append("LiveAudio");

			break;
		case 35:
			str.append("\nSubComponent=").append("ServicePackMigration");

			break;
		case 36:
			str.append("\nSubComponent=").append("Cap");

			break;
		case 37:
			str.append("\nSubComponent=").append("OpenClientServer");

			break;
		case 38:
			str.append("\nSubComponent=").append("VoicePortal");

			break;
		case 39:
			str.append("\nSubComponent=").append("CallLogs");

			break;
		case 40:
			str.append("\nSubComponent=").append("OciReporting");

			break;
		case 41:
			str.append("\nSubComponent=").append("bcct");

			break;
		case 42:
			str.append("\nSubComponent=").append("diameterServer");

			break;
		case 43:
			str.append("\nSubComponent=").append("cms");

			break;
		case 44:
			str.append("\nSubComponent=").append("taskMonitor");

			break;
		case 45:
			str.append("\nSubComponent=").append("tcp");

			break;

		case 46:
			str.append("\nSubComponent=").append("logging");

			break;

		}
		return str.toString();
	}
}
