package org.example.capability;

import org.sonatype.nexus.capability.CapabilityConfigurationSupport;
import java.util.Map;

import static org.example.capability.SecurityCapabilityKey.API_URL;


public class SecurityCapabilityConfiguration extends CapabilityConfigurationSupport {
    private String apiUrl;

    SecurityCapabilityConfiguration(Map<String, String> properties) {
        apiUrl = properties.getOrDefault(API_URL.propertyKey(), API_URL.defaultValue());
    }

    public String getApiUrl() {
        return apiUrl;
    }

}
