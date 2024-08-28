package com.ericsson.oss.mediation.snmp.translate.test;
/*package com.ericsson.oss.mediation.snmp.translate.test;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.inject.Inject;
import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;

import com.adventnet.snmp.snmp2.SnmpAPI;
import com.adventnet.snmp.snmp2.SnmpException;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.adventnet.snmp.snmp2.SnmpPDU;
import com.adventnet.snmp.snmp2.SnmpVar;
import com.adventnet.snmp.snmp2.SnmpVarBind;
import com.adventnet.snmp.snmp2.UDPProtocolOptions;
import com.ericsson.oss.mediation.translator.constant.TranslateResult;
import com.ericsson.oss.mediation.translator.model.*;


------------------------------------------------------------------------------
 *******************************************************************************
 * COPYRIGHT Ericsson 2012
 *
 * The copyright to the computer program(s) herein is the property of
 * Ericsson Inc. The programs may be used and/or copied only with written
 * permission from Ericsson Inc. or in accordance with the terms and
 * conditions stipulated in the agreement/contract under which the
 * program(s) have been supplied.
 *******************************************************************************
 *----------------------------------------------------------------------------

public class TranslateAlarmTest {
	
	@Inject
	TranslateAlarm translateAlarm;
	CreateAlarmDateAndTime createAlarmDateAndTime;
	
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
	 
	 public SnmpPDU createV1Trap() throws SnmpException{
		 
		 SnmpPDU pdu = new SnmpPDU();
		 SnmpAPI api=new SnmpAPI();
		 api.start();
		 pdu.setCommand( api.TRP_REQ_MSG );
		 SnmpOID oid1=new SnmpOID(".1.3.6.1.4.1.193.82.2");
		 
		 SnmpOID et=new SnmpOID(".1.3.6.1.4.1.193.82.1.8.1.5");
		 SnmpOID pc=new SnmpOID(".1.3.6.1.4.1.193.82.1.8.1.6");
		 SnmpOID ps=new SnmpOID(".1.3.6.1.4.1.193.82.1.8.1.7");
		 SnmpOID addtext=new SnmpOID(".1.3.6.1.4.1.193.82.1.8.1.13");
		 String et =".1.3.6.1.4.1.193.82.1.8.1.5"; //et vale 2
		 String pc =".1.3.6.1.4.1.193.82.1.8.1.6"; //pc 2
		 String ps =".1.3.6.1.4.1.193.82.1.8.1.7"; //ps 4
		 String addtext =".1.3.6.1.4.1.193.82.1.8.1.13"; //add 1234
		 
	     pdu.setEnterprise(oid1); 
	     InetAddress hostName;
		try {
			hostName = InetAddress.getByName("localhost");
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	     pdu.setAgentAddr("172.16.65.230");
	     
	     pdu.setTrapType(6);
	     pdu.setSpecificType(1);
	     pdu.setUpTime(100); 
	     
	     SnmpVar var1 = SnmpVar.createVariable("abc", SnmpAPI.STRING);
	     SnmpVar var2 = SnmpVar.createVariable("2",SnmpAPI.STRING);
	     SnmpVar var3 = SnmpVar.createVariable("2",SnmpAPI.STRING);
	     SnmpVar var4 = SnmpVar.createVariable("4",SnmpAPI.STRING);
	     SnmpVar var5 = SnmpVar.createVariable("1234",SnmpAPI.STRING);
	     
	     
	     
	     SnmpVarBind varbind1=new SnmpVarBind(et,var2);
	     SnmpVarBind varbind2=new SnmpVarBind(pc,var3);
	     SnmpVarBind varbind3=new SnmpVarBind(ps,var4);
	     SnmpVarBind varbind4=new SnmpVarBind(addtext,var5);
	     
	     pdu.addVariableBinding(varbind1);
	     pdu.addVariableBinding(varbind2);
	     pdu.addVariableBinding(varbind3);
	     pdu.addVariableBinding(varbind4);
	    
	     
		 
		 
		 
		 return pdu;
	 }
	 
	  @Test
	 public void translateAlarmTest1() throws NamingException, SnmpException{
		 
		 SnmpPDU pdu = createPdu();
		 String sourceType = "WPP";
		 TranslateAlarm translateAlarm = new TranslateAlarm();
		 EventNotification notif = new EventNotification(); 
		 notif = translateAlarm.translateAlarm(pdu, sourceType);
		 Assert.assertEquals("CLEARED",notif.getSeverity());
		
		 translateAlarm.extractSnmpInfo(pdu, sourceType, notif);
		 
		Assert.assertEquals(TranslateResult.DROP_ALARM,notif.getTranslateResult());
		
		 
	 }	 
	  //@Test
		 public void translateAlarmTest2() throws NamingException, SnmpException{
			 
			 SnmpPDU pdu = createPdu();
			 String sourceType = "WPP";
			 TranslateAlarm translateAlarm = new TranslateAlarm();
			 EventNotification notif = new EventNotification(); 
			 notif = translateAlarm.translateAlarm(pdu, sourceType);
			 Assert.assertEquals("CLEARED",notif.getSeverity());
			
			 
			 Assert.assertEquals("abc",notif.getSpecificProblem());
			 Assert.assertEquals("ad",notif.getProbableCause());
			 Assert.assertEquals("ERROR",notif.getEventType());
			 
			 
		 }	 
	 // @Test
		 public void translateAlarmTest3() throws NamingException, SnmpException{
			 
			 SnmpPDU pdu = createPdu();
			 String sourceType = "WPP";
			 TranslateAlarm translateAlarm = new TranslateAlarm();
			 EventNotification notif = new EventNotification(); 
			 notif = translateAlarm.translateAlarm(pdu, sourceType);
			 Assert.assertEquals("CLEARED",notif.getSeverity());
			
			 
			 Assert.assertEquals("abc",notif.getSpecificProblem());
			 Assert.assertEquals("ad",notif.getProbableCause());
			 Assert.assertEquals("ERROR",notif.getEventType());
			 
			 
		 }	 
	 
	 @Test
	 public void translateEventTypeGgsnTest(){
		 String eventName = "Environmental";
		 Assert.assertEquals(3, createAlarmDateAndTime.translateEventTypeGgsn(eventName));
	 }
	 
}
*/