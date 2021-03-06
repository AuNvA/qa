/*
 * CODENVY CONFIDENTIAL
 * __________________
 * 
 *  [2012] - [2013] Codenvy, S.A. 
 *  All Rights Reserved.
 * 
 * NOTICE:  All information contained herein is, and remains
 * the property of Codenvy S.A. and its suppliers,
 * if any.  The intellectual and technical concepts contained
 * herein are proprietary to Codenvy S.A.
 * and its suppliers and may be covered by U.S. and Foreign Patents,
 * patents in process, and are protected by trade secret or copyright law.
 * Dissemination of this information or reproduction of this material
 * is strictly forbidden unless prior written permission is obtained
 * from Codenvy S.A..
 */
package com.codenvy.ide.paas.appfog;

import java.util.Map;
import java.util.HashMap;
import java.util.Set;

import static org.junit.Assert.assertEquals;

/**
 * @author Dmytro Nochevnov
 *
 */
public class AppFogApplicationInfo {
    private String serverUrl;
    private Map<Property, String> applicationProperties = new HashMap<Property, String>();
    
    public enum Property {
        NAME,
        URLS,
        MEMORY,
        INSTANCES,
        INFRASTRUCTER,
        STACK,
        MODEL,
        STATUS,
        SERVICES
    }
    
    public enum ApplicationStatus {STARTED, STOPPED}

    public String getServerUrl() {
        return serverUrl;
    }

    public void setServerUrl(String serverUrl) {
        this.serverUrl = serverUrl;
    }

    public String getApplicationProperty(Property property) {
        return applicationProperties.get(property);
    }
    
    public void setApplicationProperty(String property, String value) {
        setApplicationProperty(Property.valueOf(property), value);
    }
    
    public Set<Property> getExistedPropertyNames() {
        return this.applicationProperties.keySet(); 
    }

    public void setApplicationProperty(Property name, String value) {
        this.applicationProperties.put(name, value);
    }
    
    
    /**
     * Verifications
     */
    
    public void checkServerUrl(String expectedServerUrl) {
        assertEquals(expectedServerUrl, getServerUrl());
    }
    
    public void checkApplicationProperties(AppFogApplicationInfo expectedInfo) {       
        for (Property property: this.getExistedPropertyNames()) {
            assertEquals(String.format("Verifing property %s; ", property.toString()), 
                         expectedInfo.getApplicationProperty(property), 
                         getApplicationProperty(property));
        }
    }
}
