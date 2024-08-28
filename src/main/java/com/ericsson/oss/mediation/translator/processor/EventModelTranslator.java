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
package com.ericsson.oss.mediation.translator.processor;

import java.util.ArrayList;
import java.util.List;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.itpf.sdk.eventbus.model.EventSender;
import com.ericsson.oss.itpf.sdk.eventbus.model.classic.EventSenderBean;
import com.ericsson.oss.mediation.translator.model.EventNotification;
import com.ericsson.oss.services.fm.service.alarm.FmMediationNotification;
import com.ericsson.oss.services.fm.service.util.ConvertEventNotificationToAlarm;

public class EventModelTranslator implements Processor {

	@SuppressWarnings("rawtypes")
	private static EventSender modeledEventSender;
	
	
	private static final Logger LOGGER = LoggerFactory
			.getLogger(EventModelTranslator.class);



	public static void setModeledEventSender(final EventSender sender) {
		modeledEventSender = sender;
	}

	private void updateEventSender() throws ClassNotFoundException {

		if (modeledEventSender == null) {
			Class<?> clazz = null;

			final FmMediationNotification x = new FmMediationNotification();
			clazz = Class.forName(x.getClass().getCanonicalName());

			modeledEventSender = new EventSenderBean(clazz);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.apache.camel.Processor#process(org.apache.camel.Exchange)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public void process(final Exchange exchange) {
		try {
             System.out.println("model translator processor");
			final String type = (String) exchange.getIn().getHeader(
					SnmpExchangeHeaders.HEADER_TYPE);
			if (SnmpExchangeHeaders.TYPE_ALARM.equals(type)) {
				updateEventSender();
				System.out.println("update");
			     final List<EventNotification> eventList=(List<EventNotification>)exchange
						.getIn().getBody();
			     System.out.println("so eventList is:"+eventList.toString());
			     //EventNotification notif[]=(EventNotification[])eventList.toArray();
			
			    EventNotification notif[]=new EventNotification[eventList.size()];
			     for(int i=0;i<eventList.size();i++){
			    	 notif[i]=eventList.get(i);
			    	 System.out.println("notif[i] is"+notif[i]);
			     }
				
				
				final List<FmMediationNotification> alarmNotifcation = new ArrayList<FmMediationNotification>();
				for (int i = 0; i < notif.length; i++) {
					System.out.println("Alarm Received from translator: {}"+
							notif[i].toString());
				     
				
					alarmNotifcation.add(ConvertEventNotificationToAlarm
							.converEventNotificationToAlarmNotification(notif[i]));
					System.out.println("Alarm forward to FMEventChannel:::"+notif[i]);
					System.out.println("notif is null"+notif[i]==null);
					System.out.println("alarmNotifcation is "+alarmNotifcation.toString());
				}
				modeledEventSender.send(alarmNotifcation);
				System.out.println("send to fm service channel"+modeledEventSender);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
			LOGGER.error("Exception in EventModelTranslator. Exception:",
					(Throwable) e);
		}
	}
}
