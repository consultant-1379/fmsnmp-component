package com.ericsson.oss.mediation.cacheapi;

import com.ericsson.oss.mediation.cacheapibean.MibCachingInterfaceBean;

public class TestMIbCache {


	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MibCachingInterfaceBean cachingInterface=null;
		
		try
		{
			
			 cachingInterface=new MibCachingInterfaceBean();
			
			cachingInterface.readFileFromModelService();
		}
		catch (Exception e) {
			e.printStackTrace();
			// TODO: handle exception
		}
		
		System.out.println(" OID VALUE ARE" +cachingInterface.getComSupervisionOID("WPP"));
		
		System.out.println(" ATT "+cachingInterface.getAttributeName(".1.3.6.1.4.1.193.82.1.8.1.2","WPP"));
		
	}

}