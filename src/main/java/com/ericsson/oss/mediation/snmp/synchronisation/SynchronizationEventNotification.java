package com.ericsson.oss.mediation.snmp.synchronisation;


import com.ericsson.oss.mediation.translator.model.EventNotification;




/**
* Represents a synchronization event.
* Synchronization events are sent during synchronization, one synchronization
* event for each active event in the supervised equipment. Synchronization events
* contain the same attributes as a normal event (see {@link EventNotification}),
* plus a few attributes that is necessary/available only for synchronization.
*/
public class SynchronizationEventNotification extends EventNotification {
private boolean isAcknowledged = false;
private String operator = "";
private String ackTime = "";
private String ackTimeZone = "";

/**
* Sets the event's acknowledge status.
*
* @param isAcknowledged a boolean that should be true if the event has been
*                       acknowledged, false otherwise.
*
* @see #isAcknowledged()
*/
public void setAcknowledged(final boolean isAcknowledged) {
 this.isAcknowledged = isAcknowledged;
}

/**
* Gets the event's acknowledge status.
*
* @return true if this event has been acknowledged, false if not.
*
* @see #setAcknowledged(boolean)
*/
public boolean isAcknowledged() {
 return this.isAcknowledged;
}

/**
* Sets the name of the operator/user/function that has acknowledged the
* event (if the event is in an acknowledged state).
*
* @param operator the name of the operator/user/function that acknowledged
*                 the event
*
* @see #getAckStatusOperator()
*/
public void setAckStatusOperator(final String operator) {
 this.operator = operator;
}

/**
* Gets the name of the operator/user/function that has acknowledged the
* event (if the event is in an acknowledged state).
*
* @return a String, containing the ackStatusOperator name.
*
* @see #setAckStatusOperator(String)
*/
public String getAckStatusOperator() {
 return this.operator;
}

/**
* Sets the time when the event was acknowledged (if the event is in an
* acknowledged state). {@link #setAckTimeZone(String)} should be used to set
* the the time zone for the time.
*
* @param ackTime a String containing the time when the event was
*                acknowledged, in the format YYYYMMDDhhss.
*
* @see #getAckTime()
* @see #getAckTimeZone()
* @see #setAckTimeZone(String)
*/
public void setAckTime(final String ackTime) {
 this.ackTime = ackTime;
}

/**
* Gets the time when the event was acknowledged (if the event is in an
* acknowledged state). {@link #getAckTimeZone()} should be used to get the
* time zone of the time returned.
*
* @return a String containing the value of the attribute ackTime, in the
*         format YYYYMMDDhhss.
*
* @see #setAckTime(String)
* @see #getAckTimeZone()
* @see #setAckTimeZone(String)
*/
public String getAckTime() {
 return this.ackTime;
}

/**
* Sets the time zone for the acknowledge time (if the event is in an
* acknowledged state).
*
* @param timeZone the timezone the acknowledge time is given in, as a string
*                 with the format: "GMT", "UTC", "CET", etc
*
* @see #getAckTimeZone()
* @see #getAckTime()
* @see #setAckTime(String)
*/
public void setAckTimeZone(final String timeZone) {
 this.ackTimeZone = timeZone;
}

/**
* Gets the time zone for the acknowledge time (if the event is in an
* acknowledged state).
*
* @return the timezone the acknowledge time is given in, as a string with
*         the format: "GMT", "UTC", "CET".
*
* @see #setAckTimeZone(String)
* @see #getAckTime()
* @see #setAckTime(String)
*/
public String getAckTimeZone() {
 return this.ackTimeZone;
}
}
