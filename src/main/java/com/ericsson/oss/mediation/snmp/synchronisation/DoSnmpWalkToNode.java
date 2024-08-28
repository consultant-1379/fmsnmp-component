package com.ericsson.oss.mediation.snmp.synchronisation;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.translator.constant.SnmpBindAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;

import com.adventnet.snmp.snmp2.SnmpAPI;
import com.adventnet.snmp.snmp2.SnmpException;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.SnmpSession;
import com.adventnet.snmp.snmp2.UDPProtocolOptions;
import com.adventnet.snmp.snmp2.usm.USMUserEntry;
import com.adventnet.snmp.snmp2.usm.USMUtils;

public class DoSnmpWalkToNode {
	private static final Logger LOGGER = LoggerFactory.getLogger(CommonSnmpSync.class);
	ArrayList<SnmpPDU> pduList= new ArrayList<SnmpPDU>();


	//snmpWalk
	/**
	 * Sends a snmp getnext request which returns a list of variables associated with the OID.
	 * @param remoteHost The IP address of the agent.
	 * @param community The community String.
	 * @param portNumber The port number of the agent.
	 * @param The Object Identifier of the agent.
	 * @return The result of the snmp Getnext Request.
	 * @throws SnmpException 
	 */
	public  void snmpWalk(SNMPManagedElement me, SnmpOID[] snmpOID,SnmpAPI apiSynch) throws ActionException {
		String version=me.getProtocolVersion();
		SnmpSession session=null;

		int alarmCount=0;
		try{
			LOGGER.info("Before calling getNumberOfAlarms");
			alarmCount=getNumberOfAlarms(me,snmpOID[0],apiSynch);
		}
		catch(SnmpException e)
		{
			LOGGER.error("Exception in getNumberOfAlarms is:"+e.toString());
		}
		if(alarmCount>0){
			try{
				session=setSnmpSessionParams(me,apiSynch);


				SnmpPDU pdu = new SnmpPDU();

				session.open();

				if(version.equalsIgnoreCase("V3"))
				{		
					pdu.setUserName(me.getUser().getBytes());
					pdu.setContextName(me.getContext().getBytes());						
				}  

				pdu.setCommand(SnmpAPI.GETNEXT_REQ_MSG );

				for(int arindex=0;arindex<snmpOID.length;arindex++){
					pdu.addNull(snmpOID[arindex]);  

				}
				for(int index=0;index<alarmCount;index++){

					try   
					{  			
						pdu = session.syncSend(pdu);  


						if (pdu != null)   
						{  
							LOGGER.debug("snmpWalk:Printing the VarBinds:"+pdu.printVarBinds()); 
							if (pdu.getErrstat() != 0)   
							{  
								LOGGER.info("Error Indication in response: " +  
										SnmpException.exceptionString((byte)pdu.getErrstat()) +   
										"\nErrindex: " + pdu.getErrindex()); 
							} 
							else
							{
								LOGGER.debug("INSERTING PDU INTO MAP ");
								pduList.add(pdu);
							} 
							pdu.setCommand( SnmpAPI.GETNEXT_REQ_MSG );  
							pdu.setReqid(0);  
						} 
					}
					catch (SnmpException e)   
					{  
						LOGGER.error("ERROR in Sending PDU : " + e.getMessage());  
						break;  
					}  
				}

			}
			catch(Exception e)
			{
				LOGGER.info("exception"+e.toString());
			}

			finally{
				session.close();
				apiSynch.close();
				session=null;
				apiSynch=null;
			}

		}    

	}

	private int getNumberOfAlarms(final SNMPManagedElement me, final SnmpOID objId,final SnmpAPI api) throws SnmpException
	{
		int numberOfAlarms = 0;
		SnmpSession session=null;
		final String version=me.getProtocolVersion();

		try {
			session=setSnmpSessionParams(me,api);
			session.open();
		} catch (SnmpException e1) {
			LOGGER.info("getNumberOfAlarms:Exception:"+e1.toString());
		}


		try
		{

			SnmpPDU pdu = new SnmpPDU();

			if(version.equalsIgnoreCase("V3"))
			{		
				pdu.setUserName(me.getUser().getBytes());
				pdu.setContextName(me.getContext().getBytes());						
			}  

			pdu.setCommand(SnmpAPI.GETNEXT_REQ_MSG );

			int rootoid[] = (int[]) objId.toValue();    
			if (rootoid == null)      
			{  
				LOGGER.info("Invalid OID argument: ");  
			}  
			else  
			{  
				pdu.addNull(objId);  
			}     

			while (true) 
			{  
				try   
				{  			
					pdu = session.syncSend(pdu);  
				}   
				catch (SnmpException e)   
				{  
					LOGGER.info("Sending PDU : " + e.getMessage());  
					break;  
				}  

				if (pdu == null)   
				{  
					LOGGER.info("Request timed out to: ");  
					break;
				}  		
				if (!isInSubTree(rootoid,pdu))   
				{  
					break;  
				}  
				if (pdu.getErrstat() != 0)   
				{  
					LOGGER.info("Error Indication in response: " );
					throw new SnmpException("Error Indication in response: "+pdu.getErrstat() +   "\nErrindex: " + pdu.getErrindex());   // isn't it complex exception anyway !!!
				} 
				else
				{
					LOGGER.info("after response"+pdu.printVarBinds());
					numberOfAlarms++;

				}  

				pdu.setCommand(SnmpAPI.GETNEXT_REQ_MSG );  
				pdu.setReqid(0);  
			}

		}
		catch(Exception e)
		{
			LOGGER.info("Exception==>"+e.getMessage());
		}
		finally{
			LOGGER.info("Closing SNMP session in getNumberOfAlarms()");
			session.close();
			api.close();
			session=null;

		}
		LOGGER.info("NumberOfAlarms===>:"+numberOfAlarms);
		return  numberOfAlarms;

	}



	private SnmpSession setSnmpSessionParams(final SNMPManagedElement me,SnmpAPI api) throws SnmpException{
		UDPProtocolOptions options = new UDPProtocolOptions(); 
		final String version=me.getProtocolVersion();
		String sourceBindAddress=null;

		try {
			sourceBindAddress=SnmpBindAddress.getBindingAddress();
			LOGGER.info("sourceBindAddress is"+sourceBindAddress);
		} catch (UnknownHostException e1) {
			LOGGER.info("SNMPWALK:setSnmpSessionParams:Exception in getting JBOSS bind address"+e1.toString());
		}


		options.setLocalAddresses(new String[]{sourceBindAddress});
		SnmpSession session = new SnmpSession(api); 

		try{
			if(version.equalsIgnoreCase("v1"))
			{
				session.setVersion(SnmpAPI.SNMP_VERSION_1);
			}
			else if(version.equalsIgnoreCase("v2c"))
			{
				LOGGER.info("######## equalsIgnoreCase(V2C******** "+options.getRemoteHost());
				session.setVersion(SnmpAPI.SNMP_VERSION_2C);	
			}
			else
			{
				session.setVersion(SnmpAPI.SNMP_VERSION_3);
				session.setContextName(me.getContext().getBytes());
			}

			options.setRemoteHost((me.getIpAddress()));
			LOGGER.info("options.RemoteHost is::"+options.getRemoteHost());
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
			LOGGER.info("Exception in setSnmpSessionParams===>"+e.toString());
		}
		return session;
	}



	static boolean isInSubTree(int[] rootoid, SnmpPDU pdu)   
	{  
		SnmpOID objID = (SnmpOID) pdu.getObjectID(0);  
		if (objID == null)  
		{  
			return false;  
		}     

		int oid[] = (int[]) objID.toValue();  
		if (oid == null)  
		{  
			return false;  
		}     

		if (oid.length < rootoid.length)   
		{  
			return false;  
		}     
		for (int i=0;i<rootoid.length;i++)  
		{  
			if (oid[i] != rootoid[i])  
			{  
				return false;  
			}     
		}     
		return true;  
	}

	/**
	 * @return the pduList
	 */
	public ArrayList<SnmpPDU> getPduList() {
		return pduList;
	}

	/**
	 * @param pduList the pduList to set
	 */
	public void setPduList(ArrayList<SnmpPDU> pduList) {
		this.pduList = pduList;
	} 

}
