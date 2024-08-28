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

import com.ericsson.oss.mediation.translator.model.HandleOtherAlarmAttribute;
import com.ericsson.oss.mediation.translator.model.alarm.handlers.AlarmState;

import com.ericsson.oss.mediation.translator.constant.TranslationConstant;

public class AlarmStateTest {

	@Test
	public void getIfAdminState() {
		
		
		String IfAdminState1 = HandleOtherAlarmAttribute.IfAdminState.concat("up(1).");
		String IfAdminState2 = HandleOtherAlarmAttribute.IfAdminState.concat("down(2).");
		String IfAdminState3 = HandleOtherAlarmAttribute.IfAdminState.concat("testing(3).");		
		
		Assert.assertEquals(IfAdminState1, AlarmState.getIfAdminState("1"));
		Assert.assertEquals(IfAdminState2, AlarmState.getIfAdminState("2"));
		Assert.assertEquals(IfAdminState3, AlarmState.getIfAdminState("3"));
		
		
	}
	
	@Test
	public void getIfOperState(){
		
		String IfOperState1 =HandleOtherAlarmAttribute.IfOperState.concat("up(1).");
		String IfOperState2 =HandleOtherAlarmAttribute.IfOperState.concat("down(2).");
		String IfOperState3 =HandleOtherAlarmAttribute.IfOperState.concat("testing(3).");
		String IfOperState4 =HandleOtherAlarmAttribute.IfOperState.concat("unknown(4).");
		String IfOperState5 =HandleOtherAlarmAttribute.IfOperState.concat("dormant(5).");
		String IfOperState6 =HandleOtherAlarmAttribute.IfOperState.concat("notPresent(6).");
		String IfOperState7 =HandleOtherAlarmAttribute.IfOperState.concat("lowerLayerDown(7).");
		
		
		Assert.assertEquals(IfOperState1, AlarmState.getIfOperState("1"));
		Assert.assertEquals(IfOperState2, AlarmState.getIfOperState("2"));
		Assert.assertEquals(IfOperState3, AlarmState.getIfOperState("3"));
		Assert.assertEquals(IfOperState4, AlarmState.getIfOperState("4"));
		//Assert.assertEquals(IfOperState5, AlarmState.getIfOperState("5"));
		Assert.assertEquals(IfOperState6, AlarmState.getIfOperState("6"));
		Assert.assertEquals(IfOperState7, AlarmState.getIfOperState("7"));
		
				
	}

	@Test
	public void getOidVarAPEnvMonTrapPreviousState(){
	
		String txtTrapPreviousState1 =  TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("initial(1).");
		String txtTrapPreviousState2 = TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("normal(2).");
		String txtTrapPreviousState3 = TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("minor(3).");
		String txtTrapPreviousState4 = TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("major(4).");
		String txtTrapPreviousState5 = TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("critical(5).");
		String txtTrapPreviousState6 = TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("shutdown(6).");
		String txtTrapPreviousState7 = TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("notPresent(7).");
		String txtTrapPreviousState8 = TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("notFunctioning(8).");
		String txtTrapPreviousState9 = TranslationConstant.txtVarApEnvMonTrapPreviousState.concat("unknown(9).");
		
		Assert.assertEquals(txtTrapPreviousState1, AlarmState.getOidVarAPEnvMonTrapPreviousState("1"));
		Assert.assertEquals(txtTrapPreviousState2, AlarmState.getOidVarAPEnvMonTrapPreviousState("2"));
		Assert.assertEquals(txtTrapPreviousState3, AlarmState.getOidVarAPEnvMonTrapPreviousState("3"));
		Assert.assertEquals(txtTrapPreviousState4, AlarmState.getOidVarAPEnvMonTrapPreviousState("4"));
		Assert.assertEquals(txtTrapPreviousState5, AlarmState.getOidVarAPEnvMonTrapPreviousState("5"));
		Assert.assertEquals(txtTrapPreviousState6, AlarmState.getOidVarAPEnvMonTrapPreviousState("6"));
		Assert.assertEquals(txtTrapPreviousState7, AlarmState.getOidVarAPEnvMonTrapPreviousState("7"));
		Assert.assertEquals(txtTrapPreviousState8, AlarmState.getOidVarAPEnvMonTrapPreviousState("8"));
		Assert.assertEquals(txtTrapPreviousState9, AlarmState.getOidVarAPEnvMonTrapPreviousState("9"));
		
		
	}
	
	@Test
	public void getOidVarAPEnvMonTrapCurrentState(){
		
		String txtTrapCurrentState1 =  TranslationConstant.txtVarApEnvMonTrapCurrentState.concat("initial(1).");
		String txtTrapCurrentState2 =  TranslationConstant.txtVarApEnvMonTrapCurrentState.concat("normal(2).");;
		String txtTrapCurrentState3 = TranslationConstant.txtVarApEnvMonTrapCurrentState.concat("minor(3).");
		String txtTrapCurrentState4 =  TranslationConstant.txtVarApEnvMonTrapCurrentState.concat("major(4).");
		String txtTrapCurrentState5 =  TranslationConstant.txtVarApEnvMonTrapCurrentState.concat("critical(5).");
		String txtTrapCurrentState6 =   TranslationConstant.txtVarApEnvMonTrapCurrentState.concat("shutdown(6).");
		String txtTrapCurrentState7 =  TranslationConstant.txtVarApEnvMonTrapCurrentState.concat("notPresent(7).");
		String txtTrapCurrentState8 =  TranslationConstant.txtVarApEnvMonTrapCurrentState.concat("notFunctioning(8).");
		String txtTrapCurrentState9 =  TranslationConstant.txtVarApEnvMonTrapCurrentState.concat("unknown(9).");
		
		Assert.assertEquals(txtTrapCurrentState1, AlarmState.getOidVarAPEnvMonTrapCurrentState("1"));
		Assert.assertEquals(txtTrapCurrentState2, AlarmState.getOidVarAPEnvMonTrapCurrentState("2"));
		Assert.assertEquals(txtTrapCurrentState3, AlarmState.getOidVarAPEnvMonTrapCurrentState("3"));
		Assert.assertEquals(txtTrapCurrentState4, AlarmState.getOidVarAPEnvMonTrapCurrentState("4"));
		Assert.assertEquals(txtTrapCurrentState5, AlarmState.getOidVarAPEnvMonTrapCurrentState("5"));
		Assert.assertEquals(txtTrapCurrentState6, AlarmState.getOidVarAPEnvMonTrapCurrentState("6"));
		Assert.assertEquals(txtTrapCurrentState7, AlarmState.getOidVarAPEnvMonTrapCurrentState("7"));
		Assert.assertEquals(txtTrapCurrentState8, AlarmState.getOidVarAPEnvMonTrapCurrentState("8"));
		Assert.assertEquals(txtTrapCurrentState9, AlarmState.getOidVarAPEnvMonTrapCurrentState("9"));
			
	}
	
	@Test
	public void getOidVarAPSysLogHistLevel(){
		String txtVarApSyslogHistLevel = TranslationConstant.txtVarApSyslogHistLevel.concat("emergency(1).");
		String txtVarApSyslogHistLeve2 = TranslationConstant.txtVarApSyslogHistLevel.concat("critical(2).");
		String txtVarApSyslogHistLeve3=TranslationConstant.txtVarApSyslogHistLevel.concat("major(3).");
		String txtVarApSyslogHistLeve4=TranslationConstant.txtVarApSyslogHistLevel.concat("minor(4) .");
		String txtVarApSyslogHistLeve5=TranslationConstant.txtVarApSyslogHistLevel.concat("warning(5).");
		String txtVarApSyslogHistLeve6= TranslationConstant.txtVarApSyslogHistLevel.concat("notice(6).");
		String txtVarApSyslogHistLeve7=TranslationConstant.txtVarApSyslogHistLevel.concat("info(7).");
		String txtVarApSyslogHistLeve8=TranslationConstant.txtVarApSyslogHistLevel.concat("trace(8).");
		String txtVarApSyslogHistLeve9=TranslationConstant.txtVarApSyslogHistLevel.concat("debug(9).");
		
		Assert.assertEquals(txtVarApSyslogHistLevel, AlarmState.getOidVarAPSysLogHistLevel("1"));
		Assert.assertEquals(txtVarApSyslogHistLeve2, AlarmState.getOidVarAPSysLogHistLevel("2"));
		Assert.assertEquals(txtVarApSyslogHistLeve3, AlarmState.getOidVarAPSysLogHistLevel("3"));
		Assert.assertEquals(txtVarApSyslogHistLeve4, AlarmState.getOidVarAPSysLogHistLevel("4"));
		Assert.assertEquals(txtVarApSyslogHistLeve5, AlarmState.getOidVarAPSysLogHistLevel("5"));
		Assert.assertEquals(txtVarApSyslogHistLeve6, AlarmState.getOidVarAPSysLogHistLevel("6"));
		Assert.assertEquals(txtVarApSyslogHistLeve7, AlarmState.getOidVarAPSysLogHistLevel("7"));
		Assert.assertEquals(txtVarApSyslogHistLeve8, AlarmState.getOidVarAPSysLogHistLevel("8"));
		Assert.assertEquals(txtVarApSyslogHistLeve9, AlarmState.getOidVarAPSysLogHistLevel("9"));
		
		
		
		
	}
	
	

}
