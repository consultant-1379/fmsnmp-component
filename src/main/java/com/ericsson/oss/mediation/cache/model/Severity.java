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
package com.ericsson.oss.mediation.cache.model;

public class Severity {

	public static final int _INDETERMINATE = 0;
	public static final int _CRITICAL = 1;
	public static final int _MAJOR = 2;
	public static final int _MINOR = 3;
	public static final int _WARNING = 4;
	public static final int _CLEARED = 5;

	private  int value = _INDETERMINATE;

	/**
	 * Perceived severity indeterminate.
	 */
	public static final Severity INDETERMINATE = new Severity(_INDETERMINATE);

	/**
	 * Perceived severity critical.
	 */
	public static final Severity CRITICAL = new Severity(_CRITICAL);

	/**
	 * Perceived severity major.
	 */
	public static final Severity MAJOR = new Severity(_MAJOR);

	/**
	 * Perceived severity minor.
	 */
	public static final Severity MINOR = new Severity(_MINOR);

	/**
	 * Perceived severity warning.
	 */
	public static final Severity WARNING = new Severity(_WARNING);

	/**
	 * Perceived severity cleared.
	 */
	public static final Severity CLEARED = new Severity(_CLEARED);

	public int getValue() {
		return value;
	}

	public Severity(final int i) {
		value = i;
	}
}
