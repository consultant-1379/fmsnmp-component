
package com.ericsson.oss.mediation.snmp;

import java.net.UnknownHostException;
import java.util.Vector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adventnet.snmp.snmp2.SnmpAPI;
import com.adventnet.snmp.snmp2.SnmpException;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.SnmpSession;
import com.adventnet.snmp.snmp2.SnmpVarBind;
import com.adventnet.snmp.snmp2.UDPProtocolOptions;
import com.adventnet.snmp.snmp2.usm.USMUserEntry;
import com.adventnet.snmp.snmp2.usm.USMUtils;
import com.ericsson.oss.mediation.cacheapibean.MibCachingInterfaceBean;
import com.ericsson.oss.mediation.fm.me.AbstractManagedElement;
import com.ericsson.oss.mediation.supervision.snmp.SnmpSupervision;
import com.ericsson.oss.mediation.translator.constant.SnmpBindAddress;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.snmp.cache.lookup.CacheBeanLookUp;


public class SNMPManagedElement  extends AbstractManagedElement {
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SNMPManagedElement.class);
	
	private String ipAddress;
	private int port;
	private String communityString;
	private String authenticationMethod;
	private String encryptionMethod;
	private String context;
	private String protocolVersion;
	private String additionalAtribute;
	private SnmpSupervision supervision;
	private String user ;
	private String pwd;
	private String sourceType;
	private String authenticationPwd;
	private String encryptionPwd ;


	/**
	 * @return the encryptionPwd
	 */
	public String getEncryptionPwd() {
		return encryptionPwd;
	}

	/**
	 * @param encryptionPwd the encryptionPwd to set
	 */
	public void setEncryptionPwd(String encryptionPwd) {
		this.encryptionPwd = encryptionPwd;
	}

	public static final int STATEESTABLISHED = 1;
	public static final int STATEFAILED = 2;
	private int state = STATEESTABLISHED;

	private boolean isHbrasied=false;

	public boolean isHbrasied() {
		return isHbrasied;
	}

	public void setHbrasied(boolean isHbrasied) {
		this.isHbrasied = isHbrasied;
	}

	/**
	 * @return the additionalAtribute
	 */
	public String getAdditionalAtribute() {
		return additionalAtribute;
	}

	/**
	 * @param additionalAtribute the additionalAtribute to set
	 */
	public void setAdditionalAtribute(final String additionalAtribute) {
		this.additionalAtribute = additionalAtribute;
	}

	/**
	 * @return the protocolVersion
	 */
	public String getProtocolVersion() {
		return protocolVersion;
	}

	/**
	 * @param protocolVersion the protocolVersion to set
	 */
	public void setProtocolVersion(final String protocolVersion) {
		this.protocolVersion = protocolVersion;
	}

	public SNMPManagedElement()
	{
		super();
	}

	/**
	 * @return the ipAddress
	 */
	public String getIpAddress() {
		return ipAddress;
	}

	/**
	 * @param ipAddress the ipAddress to set
	 */
	public void setIpAddress(final String ipAddress) {
		this.ipAddress = ipAddress;
	}

	/**
	 * @return the port
	 */
	public int getPort() {
		return port;
	}

	/**
	 * @param port the port to set
	 */
	public void setPort(final int port) {
		this.port = port;
	}

	/**
	 * @return the communityString
	 */
	public String getCommunityString() {
		return communityString;
	}

	/**
	 * @param communityString the communityString to set
	 */
	public void setCommunityString(final String communityString) {
		this.communityString = communityString;
	}

	/**
	 * @return the authenticationMethod
	 */
	public String getAuthenticationMethod() {
		return authenticationMethod;
	}

	/**
	 * @param authenticationMethod the authenticationMethod to set
	 */
	public void setAuthenticationMethod( final String authenticationMethod) {
		this.authenticationMethod = authenticationMethod;
	}

	/**
	 * @return the encryptionMethod
	 */
	public String getEncryptionMethod() {
		return encryptionMethod;
	}

	/**
	 * @param encryptionMethod the encryptionMethod to set
	 */
	public void setEncryptionMethod(final String encryptionMethod) {
		this.encryptionMethod = encryptionMethod;
	}

	/**
	 * @return the context
	 */
	public String getContext() {
		return context;
	}

	/**
	 * @param context the context to set
	 */
	public void setContext(final String context) {
		this.context = context;
	}
	public EventNotification[] setState(final int presentState, final String s) {
		EventNotification[] eventnotification=null;
		//String fdn=SNMPFdnConverter.converFdn(getFdn());
		String fdn=getFdn();
		final boolean flag = (getState() == presentState);
		if (flag) {
			eventnotification = new EventNotification[1];
			if (getState() == SNMPManagedElement.STATEFAILED
					&& presentState == SNMPManagedElement.STATEFAILED) {
				eventnotification[0] = EventNotification.createHeartbeatAlarm(fdn,getSourceType(), s);
			} else if (getState() == SNMPManagedElement.STATEESTABLISHED
					&& presentState == SNMPManagedElement.STATEESTABLISHED) 
			{
				eventnotification[0] = EventNotification.createHeartbeatAlarmClearing(fdn, getSourceType(), s);
			}
		}
		setState(presentState);
		return eventnotification;
	}
	
	
	public boolean checkNodeConnection() {
		LOGGER.debug("SNMPManagedElement checkNodeConnection");
		SnmpAPI api = new SnmpAPI();

		SnmpSession session=setSnmpSessionParams(this,api);

		boolean connectionStatus = true;
		try {
			session.open();
		} catch (SnmpException e1) {
			LOGGER.error("checkNodeConnection:Exception in opening SNMP Session:"+e1.toString());
		}
		try{

			final SnmpPDU pdu = new SnmpPDU();
			pdu.setCommand(SnmpAPI.GET_REQ_MSG);


			MibCachingInterfaceBean aauServices = CacheBeanLookUp.getMIBCacheBean_Reference();
			String snmpoid=aauServices.getComSupervisionOID(this.getSourceType());
			LOGGER.debug("SNMP OID is"+snmpoid);
			if(snmpoid!=null){
				pdu.addNull(new SnmpOID(snmpoid));
			}else{
				pdu.addNull(new SnmpOID(".1.3.6.1.2.1.1.1.0"));
			}


			LOGGER.debug("Check the NODE CONENCTION STATUS >>>>>>>>>>>");
			SnmpPDU response_pdu=null;
			try
			{
				response_pdu = session.syncSend(pdu);
				if(response_pdu!=null){
					LOGGER.debug("  GETINNG PDU FROM  NODE"+response_pdu.toString());
				}

			}catch (Exception e) {				
				LOGGER.error("ERROR:: "+e.getMessage());
				connectionStatus = false;
			}
			if (response_pdu == null) 
			{
				LOGGER.debug("Node Connetion is false:::");
				connectionStatus = false;
				throw new SnmpException("Get request timed out!");
			}

			final Vector v = response_pdu.getVariableBindings();

			for (int i = 0; i < v.size(); i++) 
			{
				final SnmpVarBind varBind = (SnmpVarBind) v.get(i);
				if (varBind.getErrindex() != 0) 
				{
					connectionStatus = false;
					throw new SnmpException(
							SnmpException.exceptionString((byte) varBind.getErrindex()));
				}
			}

			if (SnmpAPI.REPORT_MSG == response_pdu.getCommand()) {
				connectionStatus = false;
				throw new SnmpException("Report PDU received: "	+ response_pdu.printVarBinds());
			}

			if (response_pdu.getErrstat() != 0) {
				connectionStatus = false;
				throw new SnmpException(response_pdu.getError());
			}

		} catch (Exception e) {
			LOGGER.error("Exception in CheckConnection is" + e.getMessage());
		}

		finally {
			try {
				api.close();
				session.close();
				api = null;
				LOGGER.debug("CLOSED SNMP SESSION and API");

			} catch (Exception e) {
				LOGGER.error("Exception causing while closing snmp api: "
						+ e.getMessage());
			}
		}
		LOGGER.info("Final Node staus for the Nodeconection HeartBaet ::::"+ this.getState()+"connectionStatus===>"+connectionStatus);
		setState(STATEESTABLISHED);
		return connectionStatus;

	}

	private SnmpSession setSnmpSessionParams(final SNMPManagedElement me,SnmpAPI api)
	{
		UDPProtocolOptions options = new UDPProtocolOptions(); 

		String sourceBindAddress="127.0.0.1";

		try {
			sourceBindAddress=SnmpBindAddress.getBindingAddress();
			LOGGER.debug("setSnmpSessionParams:sourceBindAddress"+sourceBindAddress);
		} catch (UnknownHostException e2) {
			LOGGER.error("setSnmpSessionParams:Exception in getting JBOSS bind address"+e2.toString());
		}
		options.setLocalAddresses(new String[]{sourceBindAddress});
		final String version=me.getProtocolVersion();



		SnmpSession session = new SnmpSession(api); 
		try{

			if(version.equalsIgnoreCase("v1"))
			{
				session.setVersion(SnmpAPI.SNMP_VERSION_1);
			}
			else if(version.equalsIgnoreCase("v2c"))
			{
				session.setVersion(SnmpAPI.SNMP_VERSION_2C);	
			}
			else
			{
				session.setVersion(SnmpAPI.SNMP_VERSION_3);
				session.setContextName(me.getContext().getBytes());
			}
			options.setRemoteHost((me.getIpAddress()));


			LOGGER.debug("RemoteHost is===> "+options.getRemoteHost());
			options.setRemotePort(me.getPort());
			session.setProtocolOptions(options); 
			session.setCommunity(me.getCommunityString()); 
			session.setTimeout(5000);


			int protocol = 0;
			if(version.equalsIgnoreCase("V3"))
			{
				if(me.getAuthenticationMethod().equals("SHA"))
				{
					protocol = USMUserEntry.SHA_AUTH;	    
				}	
				else if(me.getAuthenticationMethod().equals("MD5"))
				{
					protocol = USMUserEntry.MD5_AUTH;
				}
				else
				{
					protocol = USMUserEntry.NO_AUTH;
				}
			}

			if(version.equalsIgnoreCase("V3"))
			{		

				USMUtils.init_v3_parameters(me.getUser(),// userName
						protocol,// authProtocol
						me.getAuthenticationPwd(),// authPassword
						me.getEncryptionMethod(),// privPassword
						me.getIpAddress(),// agentHost
						me.getPort(),// agentPort
						session);					
			}  

		}catch(Exception e){
			LOGGER.error("Exception in setSnmpSessionParams===>"+e.toString());
		}
		return session;
	}




	/**
	 * @return the state
	 */
	public int getState() {
		return state;
	}
	public void setState(final int state) {
			this.state=state;
	}


	/**
	 * @return the supervision
	 */
	public SnmpSupervision getSupervision() {
		return supervision;
	}

	/**
	 * @param supervision the supervision to set
	 */
	public void setSupervision(final SnmpSupervision supervision) {
		this.supervision = supervision;
	}

	/**
	 * @return the user
	 */
	public String getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(String user) {
		this.user = user;
	}

	/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the authenticationPwd
	 */
	public String getAuthenticationPwd() {
		return authenticationPwd;
	}

	/**
	 * @param authenticationPwd the authenticationPwd to set
	 */
	public void setAuthenticationPwd(String authenticationPwd) {
		this.authenticationPwd = authenticationPwd;
	}

	@Override
	public String toString() {
		return "SNMPManagedElement [ipAddress=" + ipAddress + ", port=" + port
				+ ", communityString=" + communityString
				+ ", authenticationMethod=" + authenticationMethod
				+ ", encryptionMethod=" + encryptionMethod + ", context="
				+ context + ", protocolVersion=" + protocolVersion
				+ ", additionalAtribute=" + additionalAtribute
				+ ", supervision=" + supervision + ", user=" + user + ", pwd="
				+ pwd + ", sourceType=" + sourceType + ", authenticationPwd="
				+ authenticationPwd + ", encryptionPwd=" + encryptionPwd
				+ ", state=" + state + "]";
	}



}
