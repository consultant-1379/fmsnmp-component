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
 *----------------------------------------------------------------------------
package com.ericsson.oss.mediation.snmp.translate.test;

import javax.naming.NamingException;

import org.junit.Assert;
import org.junit.Test;

import com.adventnet.snmp.snmp2.*;
import com.ericsson.oss.mediation.translator.model.*;

public class TranslateAlarmTest {
	TranslateAlarm translateAlarm = new TranslateAlarm();
	SnmpPDU pdu = new SnmpPDU(); 
	
	
	
	public SnmpPDU createPdu() throws SnmpException{
		
		 pdu.setCommand(SnmpAPI.GET_REQ_MSG); 
		 pdu.addNull(new SnmpOID(".1.3.6.1.2.1.1.1.0")); 

		 return pdu;
	 }
	
	
	 @Test
	public void translateAlarmTest() throws SnmpException, NamingException{
		
		createPdu();
		 System.out.println("pdu====>"+pdu);
		 String sourcetype = "WPP";
		 EventNotification notif = new EventNotification(); 
		 notif=translateAlarm.translateAlarm(pdu, sourcetype);
		 String val=notif.getTranslateResult();
		boolean returnValue= translateAlarm.extractSnmpInfo(pdu, sourcetype, notif);
		if(returnValue){
		 Assert.assertEquals(TranslateResult.DROP_ALARM,notif.getTranslateResult());
		}
		else{ 
			if (val=="DROP_AND_SYNCH")
			{
				Assert.assertEquals(TranslateResult.DROP_AND_SYNCH,notif.getTranslateResult());
			}
			else
			{
				Assert.assertEquals(TranslateResult.FORWARD_ALARM,notif.getTranslateResult());
			}
		}
			
		 
		 }
	
	
	 

		 		 
}

	*/