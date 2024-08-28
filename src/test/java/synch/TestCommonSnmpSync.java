/*package com.ericsson.oss.mediation.snmp.sync;
import static org.junit.Assert.*;
import static org.mockito.Mockito.when;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.lang.reflect.Field;
import java.util.*;

import org.apache.log4j.Logger;
import org.junit.*;

import com.adventnet.snmp.snmp2.SnmpAPI;
import com.adventnet.snmp.snmp2.SnmpOID;
import com.ericsson.oss.mediation.fm.component.SnmpSupervisionEndpoint;
import com.ericsson.oss.mediation.fm.component.SnmpSupervisionProducer;
import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.oss.mediation.snmp.synchronisation.ActionException;
import com.ericsson.oss.mediation.snmp.synchronisation.CommonSnmpSync;
import com.ericsson.oss.mediation.snmp.synchronisation.DoSnmpWalkToNode;
import com.ericsson.oss.mediation.snmp.synchronisation.SynchronizationEventNotification;

public class TestCommonSnmpSync {
	
	@Mock
	private  Logger mockingLogger;
	
	@Mock
	private SNMPManagedElement me;
	
	@Mock
	private DoSnmpWalkToNode doSnmpWalkToNode;
	
	@Mock 
	private SnmpSupervisionEndpoint endpoint;
	
	@Mock
	private SynchronizationEventNotification event;
	
	@Mock
	private Map<String, ArrayList<SnmpOID>> ;
	
	@Mock
	private MibCachingInterface aauServices;
	
	@Mock
	private Map.Entry<String,ArrayList<SnmpOID>> pairs;
	
	@Mock
	private Iterator itSnmpWalk;
	
	@Mock
	private SnmpAPI apiSynch;
	
	@Mock
	private SnmpOID[] oidArray;
	
	private CommonSnmpSync snmpSynch;

		@Ignore @Test
		public void TesttranslateSyncAlarm() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException ,ActionException{
		
			setUp();
			
			snmpSynch = new CommonSnmpSync(me);
			snmpSynch.synchronize(me);
		//	when(snmpWalkAttributes!=null).thenReturn(true);
			when(snmpWalkAttributes.size()>0).thenReturn(true);
			{
				when(itSnmpWalk.hasNext()).thenReturn(true);
				{
					when(pairs.getKey()!=null).thenReturn(true);
					when(pairs.getValue().size()!=0).thenReturn(true);
					{
						when(doSnmpWalkToNode.snmpWalk(me,oidArray,apiSynch)).thenReturn()
						
					}
				}
			}
		}
		
		
	
		public void setUp() throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException{
		
		MockitoAnnotations.initMocks(this);
		snmpSynch=new CommonSnmpSync(me);
		final Field mockLoggerListener = CommonSnmpSync.class
				.getDeclaredField("logger");
		mockLoggerListener.setAccessible(true);
		mockLoggerListener.set(snmpSynch, mockingLogger);
	
		
		when(me.getSourceType()).thenReturn("WPP");
		when(me.getIpAddress()).thenReturn("172.16.65.12");
		when(me.getProtocolVersion()).thenReturn("V2C");
		
		
}

}
*/