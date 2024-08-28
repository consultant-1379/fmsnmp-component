package com.ericsson.oss.mediation.cache.model;

import java.io.Serializable;
import java.util.Map;

/**
 * Some alarm traps don't have all the information that needs to be put into the
 * alarm record. These additional values will be hardcoded in the xml
 * (FMAlarmTable). This class presents one entry from this Alarm Table.
 */

public class AlarmTableEntry implements Serializable {
	private static final long serialVersionUID = 1L;
	private String specificProblem;
	private String probableCause;
	private String eventType;
	private String perceivedSeverity;
	private String notificationType;

	public String getSpecificProblem() {
		return specificProblem;
	}

	public void setSpecificProblem(String specificProblem) {
		this.specificProblem = specificProblem;
	}

	public String getProbableCause() {
		return probableCause;
	}

	public void setProbableCause(String probableCause) {
		this.probableCause = probableCause;
	}

	public String getEventType() {
		return eventType;
	}

	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	public String getPerceivedSeverity() {
		return perceivedSeverity;
	}

	public void setPerceivedSeverity(String perceivedSeverity) {
		this.perceivedSeverity = perceivedSeverity;
	}

	public String getNotificationType() {
		return notificationType;
	}

	public void setNotificationType(String notificationType) {
		this.notificationType = notificationType;
	}

	public Map<String, String> getAdditionalValues() {
		return additionalValues;
	}

	public void setAdditionalValues(Map<String, String> additionalValues) {
		this.additionalValues = additionalValues;
	}

	public Map<String, String> additionalValues;

}