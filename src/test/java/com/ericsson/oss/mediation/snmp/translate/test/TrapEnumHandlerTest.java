package com.ericsson.oss.mediation.snmp.translate.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;
import com.ericsson.oss.mediation.translator.model.alarm.handlers.TrapEnumHandler;

public class TrapEnumHandlerTest {

	@Test
	public void getTrapEnumResulttest() {
		
	Assert.assertEquals("ok(1).", TrapEnumHandler.getTrapEnumResult("1"));
	Assert.assertEquals("notOk(2).", TrapEnumHandler.getTrapEnumResult("2"));
	Assert.assertEquals("unknown(49).", TrapEnumHandler.getTrapEnumResult("49"));
	Assert.assertEquals("unused(50).", TrapEnumHandler.getTrapEnumResult("50"));
		
	}
	
	@Test
	public void getTrapEnumStatusTest(){
		
		Assert.assertEquals("working(1).", TrapEnumHandler.getTrapEnumStatus("1"));
		Assert.assertEquals("notWorking(2).", TrapEnumHandler.getTrapEnumStatus("2"));
		Assert.assertEquals("testing(3).", TrapEnumHandler.getTrapEnumStatus("3"));
		Assert.assertEquals("unknown(49).", TrapEnumHandler.getTrapEnumStatus("49"));
		Assert.assertEquals("unused(50).", TrapEnumHandler.getTrapEnumStatus("50"));
	}

	@Test
	public void getTrapEnumCauseTest(){
		
		Assert.assertEquals("processorInformation(1).", TrapEnumHandler.getTrapEnumCause("1"));
		Assert.assertEquals("systemInformation(2).", TrapEnumHandler.getTrapEnumCause("2"));
		Assert.assertEquals("softwareInformation(3).", TrapEnumHandler.getTrapEnumCause("3"));
		Assert.assertEquals("ispReport(4).", TrapEnumHandler.getTrapEnumCause("4"));
		Assert.assertEquals("securityInformation(10).", TrapEnumHandler.getTrapEnumCause("10"));
		Assert.assertEquals("performanceMeasurement(11).", TrapEnumHandler.getTrapEnumCause("11"));
		Assert.assertEquals("logInformation(12).", TrapEnumHandler.getTrapEnumCause("12"));
		Assert.assertEquals("statusChangeInformation(13).", TrapEnumHandler.getTrapEnumCause("13"));
		Assert.assertEquals("linkInformation(14).", TrapEnumHandler.getTrapEnumCause("14"));
		Assert.assertEquals("snmpInformation(15).", TrapEnumHandler.getTrapEnumCause("15"));
		Assert.assertEquals("networkSyncInformation(20).", TrapEnumHandler.getTrapEnumCause("20"));
		Assert.assertEquals("testResult(21).", TrapEnumHandler.getTrapEnumCause("21"));
		Assert.assertEquals("interfaceInformation(22).", TrapEnumHandler.getTrapEnumCause("22"));
		Assert.assertEquals("connectionInformation(23).", TrapEnumHandler.getTrapEnumCause("23"));
		Assert.assertEquals("equipmentInformation(24).", TrapEnumHandler.getTrapEnumCause("24"));
		Assert.assertEquals("labelSwitchInformation(25).", TrapEnumHandler.getTrapEnumCause("25"));
		Assert.assertEquals("unknown(49).", TrapEnumHandler.getTrapEnumCause("49"));
		Assert.assertEquals("unused(50).", TrapEnumHandler.getTrapEnumCause("50"));
		
	}
	
	@Test
	public void getfunkSbrTrapVarCompTest(){
		
		String funkSbrTrapVarComp1 = HandleOtherAlarmAttribute.TrapVarComp + (" core(1).");
		String funkSbrTrapVarComp2 = HandleOtherAlarmAttribute.TrapVarComp + (" accounting(2).");
		String funkSbrTrapVarComp3 = HandleOtherAlarmAttribute.TrapVarComp + (" authentication(3).");
		
		Assert.assertEquals(funkSbrTrapVarComp1, TrapEnumHandler.getfunkSbrTrapVarComp("1"));
		Assert.assertEquals(funkSbrTrapVarComp2, TrapEnumHandler.getfunkSbrTrapVarComp("2"));
		Assert.assertEquals(funkSbrTrapVarComp3, TrapEnumHandler.getfunkSbrTrapVarComp("3"));
	}
	
	@Test
	public void getInsVrBgpPeerStateTest(){
		
		Assert.assertEquals("idle", TrapEnumHandler.getInsVrBgpPeerState("1"));
		Assert.assertEquals("connect", TrapEnumHandler.getInsVrBgpPeerState("2"));
		Assert.assertEquals("active", TrapEnumHandler.getInsVrBgpPeerState("3"));
		Assert.assertEquals("opensent", TrapEnumHandler.getInsVrBgpPeerState("4"));
		Assert.assertEquals("openconfirm", TrapEnumHandler.getInsVrBgpPeerState("5"));
		Assert.assertEquals("established", TrapEnumHandler.getInsVrBgpPeerState("6"));
		
		
	}
}
