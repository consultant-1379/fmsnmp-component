package com.ericsson.oss.mediation.snmp.synchronisation;



import java.io.Serializable;

public class ActionException extends Exception implements Serializable {

		 /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
		String reason;

			 public ActionException(String reason) {
		         this.reason = reason;
			}

			public String toString() {
			   return "Exception occurred.. " + reason;
		        }

	}


