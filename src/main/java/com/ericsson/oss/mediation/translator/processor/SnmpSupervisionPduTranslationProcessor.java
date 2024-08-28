package com.ericsson.oss.mediation.translator.processor;
import java.util.*;

import org.apache.camel.Exchange;
import org.apache.camel.Processor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.cacheapibean.MibCachingInterfaceBean;
import com.ericsson.oss.mediation.snmp.SNMPManagedElement;
import com.ericsson.snmp.cache.lookup.CacheBeanLookUp;

import com.adventnet.snmp.beans.SnmpTarget;
import com.adventnet.snmp.snmp2.*;
import com.ericsson.oss.mediation.translator.model.*;

public class SnmpSupervisionPduTranslationProcessor implements Processor {

	/**
	 * @param endpoint
	 */
	private static final String INFORM_PROCESSING_FAILED = "Inform Processing failed, An exception occured during Inform handling.";
	private static final String TRAP_TRANSLATION_FAILED = "Trap translator  returned null. Trap has been dropped.";
	private static final Calendar CAL = new GregorianCalendar(
			TimeZone.getTimeZone("UTC"));
	// private static final SimpleDateFormat TIMEFORMAT = new
	// SimpleDateFormat("yyyyMMddHHmmss");
	private static final Logger LOGGER = LoggerFactory
			.getLogger(SnmpSupervisionPduTranslationProcessor.class);
	private static final String SNMPTRAPOID = ".1.3.6.1.6.3.1.1.4.1.0";
	private final TranslateAlarm translateAlarm = new TranslateAlarm();
	private final List<EventNotification> eventList = new ArrayList<EventNotification>();
	private static MibCachingInterfaceBean mibCachingInterface = CacheBeanLookUp.getMIBCacheBean_Reference();

	public void process(final Exchange exchange) {
		SnmpPDU snmpPDU=null;
		try {
			LOGGER.debug("PROCESING SNMP PDU...in SnmpSupervisionPduTranslationProcessor>>>>>>>>>>>.");



			if(exchange != null && !exchange.equals("") )
			{
				LOGGER.debug("starts here");
				EventNotification eventNotification = null;
				boolean isInform = false;
				final SNMPManagedElement snmpMe = (SNMPManagedElement) exchange.getIn().getHeader("fdn");
				LOGGER.debug("snmppdu in translator is:"+((SnmpPDU) exchange.getIn().getBody()).printVarBinds());
				LOGGER.debug("snmpmanaged element is"+snmpMe);
				if (exchange.getIn().getHeader("type").equals("alarm")) {
					Boolean processNext = true;			
					snmpPDU =(SnmpPDU) exchange.getIn().getBody();

					if ((snmpPDU.getCommand() == SnmpAPI.INFORM_REQ_MSG)
							|| (snmpPDU.getCommand() == SnmpAPI.GET_REQ_MSG)) {
						LOGGER.debug("Whethte this SNMP is Req Msg::::1");
						isInform = handleInform(snmpPDU, snmpMe);

						if (!isInform) {
							LOGGER.debug("Failed to handler inform for FDN "
									+ snmpMe.getFdn());
							processNext = false;
						}
					}

					LOGGER.debug("snmpPDU.getCommand() value:::"+snmpPDU.getCommand());
					if ((snmpPDU.getCommand() == SnmpAPI.TRP_REQ_MSG)
							|| (snmpPDU.getCommand() == SnmpAPI.TRP2_REQ_MSG)
							|| snmpPDU.getCommand() == SnmpAPI.INFORM_REQ_MSG) {
						LOGGER.debug("pdu type");
						LOGGER.debug("pdu type is"+snmpPDU.getCommand());

						if (snmpPDU.getCommand() == SnmpAPI.INFORM_REQ_MSG// ||snmpPDU.getCommand()==SnmpAPI.TRP2_REQ_MSG
								&& processNext ) {
							LOGGER.debug("processing inform");

							eventNotification = new EventNotification();

							eventNotification.setManagedObjectInstance(snmpMe.getFdn());
							eventNotification.setSpecificProblem(INFORM_PROCESSING_FAILED);
							eventNotification.setProbableCause("Internal Error");
							eventNotification.setSeverity(Constants.SEV_MAJOR);
							eventNotification.setSourceType(snmpMe.getSourceType());
							eventNotification.setFmEventType(Constants.NOTIF_TYPE_ALARM);
							eventNotification.setEventType("1");

							//TODO:Need to Check
							//eventNotification.setTime((CAL.getTime()));
							eventNotification.setTimeZone("UTC");
							eventNotification.setExternalEventId("");
							eventNotification.addAdditionalAttribute(AdditionalAttrConstants.ADDITIONAL_ATTRIBUTES,INFORM_PROCESSING_FAILED);
							eventList.add(eventNotification);					

						} else {
							if (isHeartbeatTrapPdu(snmpPDU, snmpMe)) {
								LOGGER.debug("if it is heartbeat pdu");
								eventNotification = translateAlarm
										.translateHeartbeat(snmpPDU,
												snmpMe.getSourceType());
							} else {
								LOGGER.debug("it is trap pdu");

								if(snmpPDU != null || snmpMe.getSourceType()!=null)
								{
									try{
										eventNotification = translateAlarm.translateAlarm(snmpPDU, snmpMe.getSourceType());

									}catch(Exception e)
									{
										LOGGER.error("Exception while translateAlarm"+e.getMessage());
									}
								}

							}

							final boolean trapDropped = (eventNotification.getTranslateResult() == TranslateResult.DROP_ALARM)
									|| (eventNotification.getTranslateResult() == TranslateResult.DROP_AND_SYNCH);
							if (trapDropped) {
								LOGGER.info("Dropping the trap in SnmpSupervisionPduTranslationProcessor:"+trapDropped);
								eventNotification = null;
							}
							if ((eventNotification != null) && !trapDropped) {
								LOGGER.debug("Processing the trap");

								eventNotification=applyManagedElementData(eventNotification, snmpMe);

								eventList.add(eventNotification);
							} else if (!trapDropped) {
								eventNotification.setManagedObjectInstance(snmpMe.getFdn());
								eventNotification.setSpecificProblem(TRAP_TRANSLATION_FAILED);
								eventNotification.setProbableCause("Internal Error");
								eventNotification.setSeverity(Constants.SEV_MAJOR);
								eventNotification.setSourceType(snmpMe.getSourceType());
								eventNotification.setFmEventType(Constants.NOTIF_TYPE_ERROR);
								eventNotification.setEventType("1");

								eventNotification.setTimeZone("UTC");
								eventNotification.setExternalEventId("");
								eventNotification.addAdditionalAttribute(AdditionalAttrConstants.ADDITIONAL_ATTRIBUTES,
										TRAP_TRANSLATION_FAILED);
								eventList.add(eventNotification);
								LOGGER.debug("!dropped over");
							}
							if (eventNotification.getTranslateResult() == TranslateResult.DROP_AND_SYNCH
									|| eventNotification.getTranslateResult() == TranslateResult.FORWARD_AND_SYNCH /*|| eventNotification.getTranslateResult() == TranslateResult.FORWARD_ALARM*/) {
								LOGGER.debug("setting translate result");

								final ClearAllEventsNotification clearAllNotification = new ClearAllEventsNotification();
								clearAllNotification.setManagedObjectInstance(snmpMe.getFdn());

								eventList.add(clearAllNotification);
								exchange.getIn().setBody(eventList);
								LOGGER.debug("sent for the model translator");
							}else if(eventNotification.getTranslateResult()==TranslateResult.FORWARD_ALARM){
								eventList.add(eventNotification);
								exchange.getIn().setBody(eventList);
								LOGGER.debug("forwarding alarm is done");
							}

						}
					}
				}
				LOGGER.debug("Event Notification :" + eventNotification.toString());
			}
		} catch (Exception e) {
			LOGGER.error("Exception while sending Event Notification::::"+ e.getMessage());
		}


	}

	/**
	 * Set managed object instance, source type and event agent id for
	 * notification TODO: necessary to do all and/or can it be done in
	 * pushAlarm?
	 * 
	 * @param notifObj
	 * @return notifObj
	 */
	public EventNotification applyManagedElementData(
			final EventNotification notifObj, final SNMPManagedElement me) {
		final EventNotification en1 = (EventNotification) notifObj;

		try{

			LOGGER.debug("applyManagedElementData Called");

			Boolean value=  (notifObj instanceof EventNotification);
			LOGGER.debug("value is"+value);
			if (value) {
				LOGGER.debug("true");
				final EventNotification en = (EventNotification) notifObj;
				// apply default attributes
				//final String MOI = me.getFdn().replace(torFdnAttr, " ").trim();
				final String MOI=me.getFdn();
				final String setMOI = notifObj.getManagedObjectInstance();
				if (!MOI.equals(setMOI)) {
					String newMOI;
					if (!setMOI.equals("")) {
						newMOI = MOI + "," + setMOI;
					} else {
						newMOI = MOI;
					}
					en.setManagedObjectInstance(newMOI);
				}

				if (en.getSourceType().equals("")) {
					en.setSourceType(me.getSourceType());
				}
				if (en.getEventAgentId().equals("")) {
				}

			}

			else {
				LOGGER.debug("no need to do anything");
			}
			LOGGER.debug("Appply the manged Element To Forward Alarm :"+notifObj.toString());
			//return notifObj;
			return en1;
		}catch (Exception e) {
			e.printStackTrace();
			return en1;
		}
	}

	@SuppressWarnings("rawtypes")
	public boolean isHeartbeatTrapPdu(final SnmpPDU pdu,
			final SNMPManagedElement me) {

		try{
			LOGGER.debug("inside isHeartbeatTrapPdu method:::"+pdu.toString()+"source type is "+me.getSourceType());

			final String oids = mibCachingInterface.getComSupervisionOID(me
					.getSourceType());
			final StringTokenizer tok = new StringTokenizer(oids, ",");
			final Set<Object> oidset = new HashSet<Object>();
			while (tok.hasMoreElements()) {

				oidset.add(tok.nextElement());
			}
			LOGGER.debug(" Going to Check the SNMP PDU Type and made the Eventnotification:::::");
			final SnmpOID SnmpTrapOID = new SnmpOID(SNMPTRAPOID);
			if (pdu.getCommand() == SnmpAPI.TRP2_REQ_MSG) { // SNMP V2 /v3
				LOGGER.debug("SnmpAPI.TRP2_REQ_MSG  This Checkinh here >>>>>>>>>>2");
				final Vector varBindings = pdu.getVariableBindings();
				for (int i = 0; i < varBindings.size(); ++i) {
					final SnmpVarBind varBind = (SnmpVarBind) varBindings.get(i);
					if (varBind.getObjectID().equals(SnmpTrapOID)) {
						final SnmpVar var = varBind.getVariable();
						LOGGER.debug("varbing we get is::: "+var.toString());
						if (oidset.contains(var.toString())) {
							return true;
						}
					}
				}
			} else if (pdu.getCommand() == SnmpAPI.TRP_REQ_MSG) { // SNMP V1
				LOGGER.debug("SnmpAPI.TRP_REQ_MS  This Checkinh here >>>>>>>>>>2");
				final StringBuffer trapIdentifier = new StringBuffer(pdu
						.getEnterprise().toString());
				trapIdentifier.append('.');
				trapIdentifier.append(6);
				trapIdentifier.append('.');
				trapIdentifier.append(pdu.getSpecificType());
				if (oidset.contains(trapIdentifier.toString())) {
					return true;
				}
			}
		}
		catch(Exception e){
			LOGGER.error("Exception while heartbeat PDU::"+e.getMessage());
		}
		return false;

	}

	@SuppressWarnings("deprecation")
	public boolean handleInform(final SnmpPDU pdu, final SNMPManagedElement me) {
		LOGGER.debug(" handleInform method is being called::::");
		SnmpAPI api;
		SnmpPDU responsePdu;
		SnmpSession session = null;
		boolean informProcessed = false;
		try {
			api = new SnmpAPI();
			session = new SnmpSession(api);
			session.setPeername(me.getIpAddress());
			session.setRemotePort(me.getPort());
			session.open();
		} catch (SnmpException e) {
			return informProcessed;
		}
		try {
			responsePdu = new SnmpPDU();
			responsePdu.setCommand(SnmpAPI.GET_RSP_MSG);
			responsePdu.setErrindex(0);
			responsePdu.setErrstat(SnmpAPI.SNMP_ERR_NOERROR);
			responsePdu.setReqid(pdu.getReqid());
			Vector bindings = pdu.getVariableBindings();

			Iterator iter = bindings.iterator();
			while (iter.hasNext()) {
				final SnmpVarBind varBinding = (SnmpVarBind) iter.next();
				LOGGER.debug("VarVinding we get from netsim :"+varBinding.toString());
				responsePdu.addVariableBinding(varBinding);
			}
			if ((pdu.getVersion() == SnmpAPI.SNMP_VERSION_2C)
					&& (pdu.getCommand() == SnmpAPI.INFORM_REQ_MSG)) {

				session.setCommunity(me.getCommunityString());
				responsePdu.setVersion(SnmpTarget.VERSION2C);
				session.send(responsePdu);
				LOGGER.debug("Session send Sucessfuly IN handleInform method::: :");

				informProcessed = true;
			}
		} catch (SnmpException e) {
			informProcessed = false;
			LOGGER.error("Exception in inform processing:::"+e.toString());

		} finally {
			session.close();
			session = null;
			if (api != null) {

				api.close();
				api = null;

			}
		}

		return informProcessed;
	}

}
