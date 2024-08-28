package com.ericsson.oss.mediation.snmp.translate.test;

import junit.framework.Assert;

import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.alarm.handlers.AXDStates;

public class AXDStatesTest {

	@Test
	public void getspvcOperationIndicator() {
	
		Assert.assertEquals("create",AXDStates.getspvcOperationIndicator("1"));
		Assert.assertEquals("delete", AXDStates.getspvcOperationIndicator("2"));
		Assert.assertEquals("restart",AXDStates.getspvcOperationIndicator("3"));
		Assert.assertEquals("reroute",AXDStates.getspvcOperationIndicator("4"));
		
		
	}

	 @Test
	 public void getaxdifadmin(){
		 
		 Assert.assertEquals("up",AXDStates.getaxdifadmin("1"));
		 Assert.assertEquals("down",AXDStates.getaxdifadmin("2"));
		 Assert.assertEquals("testing",AXDStates.getaxdifadmin("3"));
		 
	 }
	 
	 @Test
	 public void getaxdifoper(){
		 
		 String ifoper1="If OperStatus is=" + "up";
		 String ifoper2="If OperStatus is=" + "down";
		 String ifoper3="If OperStatus is=" + "testing";
		 String ifoper4="If OperStatus is=" + "Unknown";
		 String ifoper5="If OperStatus is=" + "dormant";
		 String ifoper6="If OperStatus is=" + "notPresent";
		 String ifoper7="If OperStatus is=" + "lowerLayerDown";
		 
		 
		 Assert.assertEquals(ifoper1,AXDStates.getaxdifoper("1"));
		 Assert.assertEquals(ifoper2,AXDStates.getaxdifoper("2"));
		 Assert.assertEquals(ifoper3,AXDStates.getaxdifoper("3"));
		 Assert.assertEquals(ifoper4,AXDStates.getaxdifoper("4"));
		 Assert.assertEquals(ifoper5,AXDStates.getaxdifoper("5"));
		 Assert.assertEquals(ifoper6,AXDStates.getaxdifoper("6"));
		 Assert.assertEquals(ifoper7,AXDStates.getaxdifoper("7"));
		 
	 }
	 
	 @Test
	 public void getaxd301dnaInterfaceEnteringMethod(){
		 
		 Assert.assertEquals("manual",AXDStates.getaxd301dnaInterfaceEnteringMethod("1"));
		 Assert.assertEquals("automatic",AXDStates.getaxd301dnaInterfaceEnteringMethod("2"));
		 
	 }
	 
	 @Test
	 public void getaxdatmfVccOperStatus(){
		 
		 Assert.assertEquals("Unknown",AXDStates.getaxdatmfVccOperStatus("1"));
		 Assert.assertEquals("End2EndUp",AXDStates.getaxdatmfVccOperStatus("2"));
		 Assert.assertEquals("End2EndDown",AXDStates.getaxdatmfVccOperStatus("3"));
		 Assert.assertEquals("localUpEnd2endUnknown",AXDStates.getaxdatmfVccOperStatus("4"));
		 Assert.assertEquals("localDown",AXDStates.getaxdatmfVccOperStatus("5"));
	 }
	 
	
}
