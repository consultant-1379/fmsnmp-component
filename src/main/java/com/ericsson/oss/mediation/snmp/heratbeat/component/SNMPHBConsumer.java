/**
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.ericsson.oss.mediation.snmp.heratbeat.component;

import java.util.concurrent.ExecutorService;

import org.apache.camel.Processor;
import org.apache.camel.impl.DefaultConsumer;

import com.ericsson.oss.mediation.snmp.heratbeat.HBDispatcher;

/**
 * The MyConsumer.
 */
public class SNMPHBConsumer extends DefaultConsumer{

	private final SNMPHBEndpoint endpoint;
	private ExecutorService executor;
	private ExecutorService pool;
	private HBDispatcher dispatcher;
    public SNMPHBConsumer(SNMPHBEndpoint endpoint, Processor processor) {
        super(endpoint, processor);
        this.endpoint = endpoint;
        System.out.println("com.ericsson.oss.mediation.snmp.heratbeat.component:HBConsumer");
    }
    

    @Override
    protected void doStart() throws Exception {
        super.doStart();
        executor = endpoint.getCamelContext().getExecutorServiceManager().newSingleThreadExecutor(this, "Single_Thread");
        pool = endpoint.getCamelContext().getExecutorServiceManager().newThreadPool(this, "Thread_Pool",100, 500);
        setDispatcher(new HBDispatcher(pool,endpoint));
        executor.execute(getDispatcher());
    }

    @Override
    protected void doStop() throws Exception {
    	dispatcher.terminate();
        // shutdown the thread pool
    	endpoint.getCamelContext().getExecutorServiceManager().shutdown(executor);
    	endpoint.getCamelContext().getExecutorServiceManager().shutdown(pool);
        super.doStop();
    }


	/**
	 * @return the dispatcher
	 */
	public HBDispatcher getDispatcher() {
		return dispatcher;
	}


	/**
	 * @param dispatcher the dispatcher to set
	 */
	public void setDispatcher(HBDispatcher dispatcher) {
		this.dispatcher = dispatcher;
	}

}
