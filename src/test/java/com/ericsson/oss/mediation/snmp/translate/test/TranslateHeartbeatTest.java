/*------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------*/
package com.ericsson.oss.mediation.snmp.translate.test;

import javax.inject.Inject;

import org.junit.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.adventnet.snmp.snmp2.*;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.model.TranslateAlarm;

public class TranslateHeartbeatTest {

	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(TranslateAlarm.class);
	@Inject
	TranslateAlarm translateAlarm;
	//CreateDateAndTime createAlarmDateAndTime;
		 public SnmpPDU createPdu() throws SnmpException{
			 SnmpPDU pdu = new SnmpPDU(); 
			 SnmpAPI api=new SnmpAPI();
			 pdu.setCommand( api.TRP2_REQ_MSG );
			 pdu.setVersion(3);
			 pdu.setCommunity("local");
			 UDPProtocolOptions option = new UDPProtocolOptions();
			 pdu.setProtocolOptions(option);
			 pdu.setAgentAddr("172.16.65.230");
			 pdu.setRemoteHost("krait");
			 option.setLocalPort(Integer.parseInt("162")); 
			//Add the Up time variable binding 

			  SnmpOID oid1=new SnmpOID(".1.3.6.1.2.1.1.3.0"); 
			  SnmpOID oid2=new SnmpOID(".1.3.6.1.6.3.1.1.4.1.0");
			  
			  //SnmpOID oid3=new SnmpOID(".1.3.6.1.4.1.193.82.2.0.2");
			  
			  SnmpOID sp = new SnmpOID(".1.3.6.1.4.1.193.82.1.8.1.8");
			  SnmpOID pc = new SnmpOID(".1.3.6.1.4.1.193.82.1.8.1.6");
			  SnmpOID equipment = new SnmpOID(".1.3.6.1.4.1.193.82.1.8.1.2");
			  
			  String trapoidvalue=".1.3.6.1.4.1.193.83.2.0.2";		   
			  
			  SnmpVar var1 = SnmpVar.createVariable("4294967295", SnmpAPI.TIMETICKS);
			  SnmpVar var2=SnmpVar.createVariable(trapoidvalue,SnmpAPI.OBJID);
			 
			  SnmpVar var4 = SnmpVar.createVariable("abc", SnmpAPI.STRING);
			  SnmpVar var5 = SnmpVar.createVariable("ad",SnmpAPI.STRING);
			  SnmpVar var6 = SnmpVar.createVariable("snmptest",SnmpAPI.STRING);
		
			  SnmpVarBind varbind1=new SnmpVarBind(oid1,var1);
			  SnmpVarBind varbind2=new SnmpVarBind(oid2,var2);
			  
			  SnmpVarBind varbind4=new SnmpVarBind(sp,var4);
			  SnmpVarBind varbind5=new SnmpVarBind(pc,var5);
			  SnmpVarBind varbind6=new SnmpVarBind(equipment,var6);
			  
			  
			  pdu.addVariableBinding(varbind1);
			  pdu.addVariableBinding(varbind2);
			 
			  pdu.addVariableBinding(varbind4);
			  pdu.addVariableBinding(varbind5);
			  return pdu;
			 
		 }
	
	@Ignore
	@Test
	public void translateHeartbeatTest() {
		 SnmpPDU pdu;
		try {
			 pdu = createPdu();
			 String sourceType = "WPP";
			 TranslateAlarm translateAlarm = new TranslateAlarm();
			 EventNotification notif = new EventNotification(); 
			 notif = translateAlarm.translateHeartbeat(pdu, sourceType);
			 
			 Assert.assertEquals("abc",notif.getSpecificProblem());
			 Assert.assertEquals("ad",notif.getProbableCause());
			 Assert.assertEquals("ERROR",notif.getEventType());
		
		} catch (SnmpException e) {
			LOGGER.error("Exception while createPdu:"+e.getMessage());
		}
		 
	
	}

}
