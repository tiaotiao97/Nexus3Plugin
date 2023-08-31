package org.example;


import javax.annotation.Nullable;
import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Provider;
import javax.inject.Singleton;

import org.example.capability.SecurityCapabilityConfiguration;
import org.example.capability.SecurityCapabilityLocator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


@Named
@Singleton
public class ConfigurationHelper {
    private static final Logger LOG = LoggerFactory.getLogger(ConfigurationHelper.class);

    @Inject
    private Provider<SecurityCapabilityLocator> locatorProvider;

    @Nullable
    public SecurityCapabilityConfiguration getConfiguration() {
        SecurityCapabilityLocator locator = locatorProvider.get();

        if (locator == null) {
            return null;
        } else {
            return locator.getSecurityCapabilityConfiguration();
        }
    }

    public boolean isCapabilityEnabled() {
        SecurityCapabilityLocator locator = locatorProvider.get();

        if (locator == null) {
            return false;
        }

        return locator.isSecurityCapabilityActive();
    }
}