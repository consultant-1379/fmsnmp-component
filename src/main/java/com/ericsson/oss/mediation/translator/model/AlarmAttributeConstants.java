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
package com.ericsson.oss.mediation.translator.model;

import java.util.HashMap;
import java.util.Map;

import com.ericsson.oss.mediation.translator.model.EventNotification;

public class AlarmAttributeConstants {
	// to avoid pmd
	private AlarmAttributeConstants()
	{
		
	}	

	public static final int ADD_ATTRIBUTES = 0;
	public static final int ADDITIONAL_ATTRIBUTES = Integer.valueOf(ADD_ATTRIBUTES);
	private static final Map<String, Integer> additionalTextAttributes = new HashMap<String, Integer>();

	
	static{
		additionalTextAttributes.put("AddtionalText",ADDITIONAL_ATTRIBUTES);
		additionalTextAttributes.put("AlarmActiveLastSequenceNo",ADDITIONAL_ATTRIBUTES);
		additionalTextAttributes.put("eriAlarmNObjAdditionalText",ADDITIONAL_ATTRIBUTES);
		additionalTextAttributes.put("eriAlarmNObjMoreAdditionalText",ADDITIONAL_ATTRIBUTES);
		additionalTextAttributes.put("eriAlarmAlertLastSequenceNo",ADDITIONAL_ATTRIBUTES);
		additionalTextAttributes.put("AddtionalText",ADDITIONAL_ATTRIBUTES);
	
	}
	
	public static boolean setadditionalTextAttributes(final String attributeName,
			final String attributeValue, final EventNotification notif, StringBuffer additionaltextBuffer){
		boolean status = false;
		if(attributeName !=null){
			status= true;
			
			additionaltextBuffer.append(attributeName + ":" + attributeValue
					+ "\n");
		}
		
		return status;
	}
}
