package com.ericsson.snmp.cache.lookup;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.ericsson.oss.mediation.cacheapibean.MibCachingInterfaceBean;

public class CacheBeanLookUp {
	private static final Logger LOGGER = LoggerFactory
			.getLogger(CacheBeanLookUp.class);

	private CacheBeanLookUp() {

	}

	public static MibCachingInterfaceBean getMIBCacheBean_Reference() {
		MibCachingInterfaceBean bean = null;

		if(bean==null)
		{
			LOGGER.info("calling MibCachingInterface bean");
			try{
				bean=new MibCachingInterfaceBean();
			}
			catch (Exception e) {
				LOGGER.error("Unable to create  MibCachingInterfaceBean Instance "+e.getMessage());
			}
			
			LOGGER.info("Bean object:::"+bean);
		}
		return bean;
	}
}
