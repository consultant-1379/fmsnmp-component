package com.ericsson.oss.mediation.snmp.translate.test;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;
import com.ericsson.oss.mediation.translator.model.alarm.handlers.ExtreamTrapSystem;

public class ExtreamTrapSystemTest {

	@Test
	public void getextremeTrapAuthorerrorTypetest() {
		
		String vrrpTrapAuthErrorType1="invalidAuthType";
		String vrrpTrapAuthErrorType2= "authTypeMismatch";
		String vrrpTrapAuthErrorType3= "authFailure";


		
		Assert.assertEquals(vrrpTrapAuthErrorType1,ExtreamTrapSystem.getextremeTrapAuthorerrorType("1"));
		Assert.assertEquals(vrrpTrapAuthErrorType2,ExtreamTrapSystem.getextremeTrapAuthorerrorType("2"));
		Assert.assertEquals(vrrpTrapAuthErrorType3,ExtreamTrapSystem.getextremeTrapAuthorerrorType("3"));
		
	}

	@Test
	public void getextremeSystemPowerStateTest() {
		
		String extremeSystemPowerState1= "computing";
		String extremeSystemPowerState2= "sufficientButNotRedundantPower";
		String extremeSystemPowerState3= "redundantPowerAvailable";
		String extremeSystemPowerState4= "insufficientPower";


		
		Assert.assertEquals(extremeSystemPowerState1, ExtreamTrapSystem.getextremeSystemPowerState("1"));
		Assert.assertEquals(extremeSystemPowerState2,ExtreamTrapSystem.getextremeSystemPowerState("2"));
		Assert.assertEquals(extremeSystemPowerState3, ExtreamTrapSystem.getextremeSystemPowerState("3"));
		Assert.assertEquals(extremeSystemPowerState4, ExtreamTrapSystem.getextremeSystemPowerState("4"));
		
		
	}
	
	@Test
	public void getextremePortMauStatusTest(){
		
		String extremePortMauStatus1= "inserted";
		String extremePortMauStatus2= "empty";
		
		Assert.assertEquals(extremePortMauStatus1, ExtreamTrapSystem.getextremePortMauStatus("1"));
		Assert.assertEquals(extremePortMauStatus2, ExtreamTrapSystem.getextremePortMauStatus("2"));
		
	}
	
	@Test
	public void getextremeEsrpDmnStateTest(){
		
		String extremeEsrpDmnState0= "neutral";
		String extremeEsrpDmnState1= "master";
		String extremeEsrpDmnState2= "slave";
		String extremeEsrpDmnState3= "premaster";
		String extremeEsrpDmnState4= "aware";
		
		Assert.assertEquals(extremeEsrpDmnState0, ExtreamTrapSystem.getextremeEsrpDmnState("0"));
		Assert.assertEquals(extremeEsrpDmnState1, ExtreamTrapSystem.getextremeEsrpDmnState("1"));
		Assert.assertEquals(extremeEsrpDmnState2, ExtreamTrapSystem.getextremeEsrpDmnState("2"));
		Assert.assertEquals(extremeEsrpDmnState3,ExtreamTrapSystem.getextremeEsrpDmnState("3"));
		Assert.assertEquals(extremeEsrpDmnState4,ExtreamTrapSystem.getextremeEsrpDmnState("4"));
		
	}
	
	@Test
	public void getrdbmsRelStateTest(){
		
		String rdbmsState1=HandleOtherAlarmAttribute.rdbmsState + "other(1).";
		String rdbmsState2=HandleOtherAlarmAttribute.rdbmsState + "active(2).";
		String rdbmsState3=HandleOtherAlarmAttribute.rdbmsState + "available(3).";
		String rdbmsState4=HandleOtherAlarmAttribute.rdbmsState + "restricted(4).";
		String rdbmsState5=HandleOtherAlarmAttribute.rdbmsState + "unavailable(5).";
		
		Assert.assertEquals(rdbmsState1,ExtreamTrapSystem.getrdbmsRelState("1"));
		Assert.assertEquals(rdbmsState2, ExtreamTrapSystem.getrdbmsRelState("2"));
		Assert.assertEquals(rdbmsState3,ExtreamTrapSystem.getrdbmsRelState("3"));
		Assert.assertEquals(rdbmsState4, ExtreamTrapSystem.getrdbmsRelState("4"));
		Assert.assertEquals(rdbmsState5, ExtreamTrapSystem.getrdbmsRelState("5"));	
		
	}
	
	@Test
	public void getoraListenerStateTest(){
		
		String genericListenerState1=HandleOtherAlarmAttribute.genericListenerState + "up(1).";
		String genericListenerState2=HandleOtherAlarmAttribute.genericListenerState + "down(2).";
		
		
		Assert.assertEquals(genericListenerState1,ExtreamTrapSystem.getoraListenerState("1"));
		Assert.assertEquals(genericListenerState2, ExtreamTrapSystem.getoraListenerState("2"));
		
		
	}
}
