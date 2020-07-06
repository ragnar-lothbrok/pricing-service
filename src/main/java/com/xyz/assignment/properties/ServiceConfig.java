package com.xyz.assignment.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * This contains service level configuration.
 */
@Component
@ConfigurationProperties("com.xyz.assignment.pricingservice")
public class ServiceConfig {

    private String actuatorEndpointsUserPassword;
    private String actuatorEndpointsUser;

    public String getActuatorEndpointsUserPassword() {
        return actuatorEndpointsUserPassword;
    }

    public void setActuatorEndpointsUserPassword(String actuatorEndpointsUserPassword) {
        this.actuatorEndpointsUserPassword = actuatorEndpointsUserPassword;
    }

    public String getActuatorEndpointsUser() {
        return actuatorEndpointsUser;
    }

    public void setActuatorEndpointsUser(String actuatorEndpointsUser) {
        this.actuatorEndpointsUser = actuatorEndpointsUser;
    }
}
