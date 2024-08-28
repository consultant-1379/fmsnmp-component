package com.ericsson.oss.mediation.cache.model;

import java.io.Serializable;

public class RiaModel implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String supervisionOID = null;
	private String supervisionType = "PULL";

	/**
	 * @return the comSupervisionOID
	 */
	public String getComSupervisionOID() {
		return supervisionOID;
	}

	/**
	 * @param comSupervisionOID
	 *            the comSupervisionOID to set
	 */
	public void setComSupervisionOID(final String comSupervisionOID) {
		this.supervisionOID = comSupervisionOID;
	}

	/**
	 * @return the comSupervisionType
	 */
	public String getComSupervisionType() {
		return supervisionType;
	}

	/**
	 * @param comSupervisionType
	 *            the comSupervisionType to set
	 */
	public void setComSupervisionType(final String comSupervisionType) {
		this.supervisionType = comSupervisionType;
	}

}
