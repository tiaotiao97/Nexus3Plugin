package org.example.capability;



public enum SecurityCapabilityKey {
    API_URL("sec_api_url", "http://xxxx.com");

    private final String propertyKey;
    private final String defaultValue;

    SecurityCapabilityKey(String propertyKey, String defaultValue) {
        this.propertyKey = propertyKey;
        this.defaultValue = defaultValue;
    }

    public String propertyKey() {
        return propertyKey;
    }

    public String defaultValue() {
        return defaultValue;
    }
}
