package com.ericsson.oss.mediation.snmp.heratbeat.component;

import java.util.Map;

import org.apache.camel.Endpoint;
import org.apache.camel.impl.DefaultComponent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * A custom component using {@link org.apache.camel.spi.ExecutorServiceManager} to create a thread pool.
 *
 * @version $Revision: 356 $
 */
public class SNMPHBComponent extends DefaultComponent {

    private static final Logger LOG = LoggerFactory.getLogger(SNMPHBComponent.class);
    public static final String routeId = "heartbeatsupervisionRoute";

    protected Endpoint createEndpoint(String uri, String remaining, Map<String, Object> parameters) throws Exception {
        Endpoint endpoint = new SNMPHBEndpoint(uri, this);
        setProperties(endpoint, parameters);
        return endpoint;
    }


    

}
