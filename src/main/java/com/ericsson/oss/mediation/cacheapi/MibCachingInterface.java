package com.ericsson.oss.mediation.cacheapi;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Map;

import com.adventnet.snmp.snmp2.SnmpOID;
import com.ericsson.oss.mediation.cache.model.AlarmTableEntry;

public interface MibCachingInterface {
	//public void chekBean();

	void readFileFromModelService();
	 

	/**
	 * Takes the sourcetype as input and returns the comsupervisiontype either
	 * PULL OR PUSH
	 */
	String getComSupervisionType(String sourcetype);

	/**
	 * Takes the sourcetype as input and returns the comsupervisionoid
	 */
	String getComSupervisionOID(String sourcetype);

	/**
	 * Checks whether the xml contains the given OID as an OID value in an
	 * "AlarmTrapOidAS" element Primary use: to identify an incoming trap as an
	 * alarm trap
	 * 
	 * @param OID
	 *            the OID to be checked
	 * @return true if it's in the xml file; false if it's not in the xml file
	 */

	boolean isSupportedOid(String OID, String sourcetype);

	/**
	 * Get a mapping between the given OIDs and the corresponding FmAttribute
	 * Finds the "AttributeOID" element in the xml that has the given "OID"
	 * value, and returns the OID => AttributeName pairs in a map Primary use:
	 * to validate that the attribute OIDs present in an alarm traps are
	 * supported ("second checking"), and to get the fields for the OIDs found
	 * in the alarm trap
	 * 
	 * @param OIDs
	 *            the OIDs to be checked & found
	 * @return a map that contains the OID => AttributeName pairs null, if any
	 *         of the OIDs are not defined in the xml; Example: input:
	 *         ".1.3.6.1.4.1.193.144.1.3.1.8" output:
	 *         ".1.3.6.1.4.1.193.144.1.3.1.8" => "SpecificProblem")
	 */

	 Map<String, String> getOIDMappingData(Collection<String> OIDs,
			String sourcetype);

	/**
	 * Returns all the attribute OID => AttributeName pairs associated with the
	 * given SourceType from "SourceTypeSync" of the xml Primary use: to get the
	 * OIDs for which smpwalk should be used during synchronization
	 * 
	 * @param sourceType
	 *            the SourceType
	 * @return a map of all the attribute OIDs and attribute names of the given
	 *         SourceType, or null, if sourceType not found Example: input:
	 *         "STN" output: ".1.3.6.1.4.1.193.144.1.3.1.3" =>
	 *         "ManagedObjectInstance" ... => ...
	 */

	 Map<String, ArrayList<SnmpOID>> getSupportedAttributeOids(String sourceType);

	/**
	 * Get the predefined, "hardcoded" attribute values for a given trap from
	 * "AlarmTrapOidAT" of the xml Primary use: Some alarm traps don't have all
	 * the information that needs to be put into the alarm record. These
	 * additional values will be hardcoded in the Alarm Table xml.
	 * 
	 * @param OID
	 *            the OID of the alarm trap ("AlarmTrapOidAT")
	 * @return the AlarmTableEntry, containing SP, PC, ET, PerceivedSeverity,
	 *         the FmEventType (Alarm/Event), and any additional attributes, or
	 *         null, if OID not found
	 */

	AlarmTableEntry getAlarmTableEntry(String OID, String SourceType);

	/**
	 * Same as getAttributeName(String OID), except it searches under the given
	 * SourceType element(s) only.
	 * 
	 * @param OID
	 *            the OID that needs the associated attribute name
	 * @param sourceType
	 * @return the associated attribute name, or if not found, null
	 */

	String getAttributeName(String OID, String sourceType);
}
