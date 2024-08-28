package com.ericsson.oss.mediation.snmp.synch;

import org.junit.Assert;
import org.junit.Test;

import com.ericsson.oss.mediation.cacheapibean.MibCachingInterfaceBean;
import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.snmp.synchronisation.CommonSnmpSync;
import com.ericsson.oss.mediation.snmp.synchronisation.SynchronizationEventNotification;

public class CommonSnmpSynctest {
	
	MibCachingInterfaceBean mibcaching;
	SynchronizationEventNotification[] syncNotif = new SynchronizationEventNotification[999999];
	SNMPManagedElement me = new SNMPManagedElement();
	CommonSnmpSync css = new CommonSnmpSync(me);
	
	@Test
	public void translateSyncAlarmtest() throws Exception
	{
		me.setSourceType("WPP");
		me.setIpAddress("172.16.69.184");
		me.setProtocolVersion("V2C");
		try {
			syncNotif = css.translateSyncAlarm(me);
			Assert.assertNull(syncNotif);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		
		
		
	}

}
