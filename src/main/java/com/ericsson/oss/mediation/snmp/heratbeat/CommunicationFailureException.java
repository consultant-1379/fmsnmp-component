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
package com.ericsson.oss.mediation.snmp.heratbeat;

import java.io.IOException;
public class CommunicationFailureException extends IOException
{
	 private static final long serialVersionUID = 1L;
	 public CommunicationFailureException()
     {
     }

     public CommunicationFailureException(final String s)
     {
        super(s);
     }
}
