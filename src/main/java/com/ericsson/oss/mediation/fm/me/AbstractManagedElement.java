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
 *----------------------------------------------------------------------------
 */
package com.ericsson.oss.mediation.fm.me;


/* this class holds the properties of the managed element.*/
 

public class AbstractManagedElement {
	private String fdn;
	private String sourceType;
	private int heartbeatTimeout=120;
	private int communicationTimeout;
	private boolean deltaSynchSupported;
	private boolean sourceSynchSupported;
	private boolean syncOnCommitFailureClear;
	private boolean acknowledgeSupported;
	private boolean subordinateObjectSyncSupported;
	private String filterInfo;
	private boolean clearAllBehavior;



	/**
	 * 
	 */
	public AbstractManagedElement() {
		super();
	}

	/**
	 * @return the fdn
	 */
	public String getFdn() {
		return fdn;
	}

	/**
	 * @param fdn
	 *            the fdn to set
	 */
	public void setFdn(final String fdn) {
		this.fdn = fdn;
	}



/**
	 * @return the sourceType
	 */
	public String getSourceType() {
		return sourceType;
	}

	/**
	 * @param sourceType the sourceType to set
	 */
	public void setSourceType(final String sourceType) {
		this.sourceType = sourceType;
	}

	/**
	 * @return the heartbeatTimeout
	 */
	public int getHeartbeatTimeout() {
		return heartbeatTimeout;
	}

	/**
	 * @param heartbeatTimeout
	 *            the heartbeatTimeout to set
	 */
	public void setHeartbeatTimeout(final int heartbeatTimeout) {
		this.heartbeatTimeout = heartbeatTimeout;
	}

	/**
	 * @return the communicationTimeout
	 */
	public int getCommunicationTimeout() {
		return communicationTimeout;
	}

	/**
	 * @param communicationTimeout
	 *            the communicationTimeout to set
	 */
	public void setCommunicationTimeout(final int communicationTimeout) {
		this.communicationTimeout = communicationTimeout;
	}

	/**
	 * @return the deltaSynchSupported
	 */
	public boolean isDeltaSynchSupported() {
		return deltaSynchSupported;
	}

	/**
	 * @param deltaSynchSupported
	 *            the deltaSynchSupported to set
	 */
	public void setDeltaSynchSupported(final boolean deltaSynchSupported) {
		this.deltaSynchSupported = deltaSynchSupported;
	}

	/**
	 * @return the sourceSynchSupported
	 */
	public boolean isSourceSynchSupported() {
		return sourceSynchSupported;
	}

	/**
	 * @param sourceSynchSupported
	 *            the sourceSynchSupported to set
	 */
	public void setSourceSynchSupported(final boolean sourceSynchSupported) {
		this.sourceSynchSupported = sourceSynchSupported;
	}

	/**
	 * @return the syncOnCommitFailureClear
	 */
	public boolean isSyncOnCommitFailureClear() {
		return syncOnCommitFailureClear;
	}

	/**
	 * @param syncOnCommitFailureClear
	 *            the syncOnCommitFailureClear to set
	 */
	public void setSyncOnCommitFailureClear(final boolean syncOnCommitFailureClear) {
		this.syncOnCommitFailureClear = syncOnCommitFailureClear;
	}

	/**
	 * @return the acknowledgeSupported
	 */
	public boolean isAcknowledgeSupported() {
		return acknowledgeSupported;
	}

	/**
	 * @param acknowledgeSupported
	 *            the acknowledgeSupported to set
	 */
	public void setAcknowledgeSupported(final boolean acknowledgeSupported) {
		this.acknowledgeSupported = acknowledgeSupported;
	}

	/**
	 * @return the subordinateObjectSyncSupported
	 */
	public boolean isSubordinateObjectSyncSupported() {
		return subordinateObjectSyncSupported;
	}

	/**
	 * @param subordinateObjectSyncSupported
	 *            the subordinateObjectSyncSupported to set
	 */
	public void setSubordinateObjectSyncSupported(
			final boolean subordinateObjectSyncSupported) {
		this.subordinateObjectSyncSupported = subordinateObjectSyncSupported;
	}

	/**
	 * @return the filterInfo
	 */
	public String getFilterInfo() {
		return filterInfo;
	}

	/**
	 * @param filterInfo
	 *            the filterInfo to set
	 */
	public void setFilterInfo(final String filterInfo) {
		this.filterInfo = filterInfo;
	}

	/**
	 * @return the clearAllBehavior
	 */
	public boolean isClearAllBehavior() {
		return clearAllBehavior;
	}

	/**
	 * @param clearAllBehavior
	 *            the clearAllBehavior to set
	 */
	public void setClearAllBehavior(final boolean clearAllBehavior) {
		this.clearAllBehavior = clearAllBehavior;
	}

}
