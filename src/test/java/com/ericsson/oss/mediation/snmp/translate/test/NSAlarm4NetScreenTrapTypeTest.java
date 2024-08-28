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

import junit.framework.Assert;

import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.mediation.translator.model.alarm.handlers.NSAlarm4NetScreenTrapType;

public class NSAlarm4NetScreenTrapTypeTest {
	
	@Test
   public void buildNsAlarm4netscreenTrapTypeTest1()
	{
		 String Name="11";
		 String Value="15";
		 EventNotification notification=new EventNotification();
		 notification = NSAlarm4NetScreenTrapType.buildNsAlarm4netscreenTrapType(Name, Value, notification);
		 Assert.assertEquals("WARNING",notification.getSeverity());
		 Assert.assertEquals("329",notification.getProbableCause());
		 Assert.assertEquals("Loss of Signal.",notification.getSpecificProblem());
		
		
	}
	
	@Test
	   public void buildNsAlarm4netscreenTrapTypeTest2()
		{
			 String Name="12";
			 String Value="19";
			 EventNotification notification=new EventNotification();
			 notification = NSAlarm4NetScreenTrapType.buildNsAlarm4netscreenTrapType(Name, Value, notification);
			 Assert.assertEquals("CRITICAL",notification.getSeverity());
			 Assert.assertEquals("315",notification.getProbableCause());
			 Assert.assertEquals("Hardware Fault",notification.getSpecificProblem());
			
			
		}

	
	@Test
	   public void buildNsAlarm4netscreenTrapTypeTest3()
		{
			 String Name="13";
			 String Value="41";
			 EventNotification notification=new EventNotification();
			 notification = NSAlarm4NetScreenTrapType.buildNsAlarm4netscreenTrapType(Name, Value, notification);
			 Assert.assertEquals("MINOR",notification.getSeverity());
			 Assert.assertEquals("329",notification.getProbableCause());
			 Assert.assertEquals("Virtual Private Network (VPN) tunnel is not available for use",notification.getSpecificProblem());
			
			
		}
	@Test
	   public void buildNsAlarm4netscreenTrapTypeTest4()
		{
			 String Name="14";
			 String Value="71";
			 EventNotification notification=new EventNotification();
			 notification = NSAlarm4NetScreenTrapType.buildNsAlarm4netscreenTrapType(Name, Value, notification);
			 Assert.assertEquals("MAJOR",notification.getSeverity());
			 Assert.assertEquals("329",notification.getProbableCause());
			 Assert.assertEquals("NSRP vsd group status change to master",notification.getSpecificProblem());
			
			
		}
	
}
