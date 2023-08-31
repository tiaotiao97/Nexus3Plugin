package org.example.capability;


import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.nexus.capability.Capability;
import org.sonatype.nexus.capability.CapabilityReference;
import org.sonatype.nexus.capability.CapabilityRegistry;

@Named
public class SecurityCapabilityLocator {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityCapabilityLocator.class);

    private final CapabilityRegistry capabilityRegistry;

    @Inject
    public SecurityCapabilityLocator(final CapabilityRegistry capabilityRegistry) {
        this.capabilityRegistry = capabilityRegistry;
    }

    public boolean isSecurityCapabilityActive() {
        LOG.debug("List all available Nexus capabilities");
        CapabilityReference reference = capabilityRegistry.getAll().stream()
                .peek(e -> {
                    Capability capability = e.capability();
                    LOG.debug("  {}", capability);
                })
                .filter(e -> SecurityCapability.class.getSimpleName().equals(e.capability().getClass().getSimpleName()))
                .findFirst().orElse(null);
        if (reference == null) {
            LOG.debug("Custom Security Configuration capability does not exist.");
            return false;
        }

        return reference.context().isActive();
    }

    public SecurityCapabilityConfiguration getSecurityCapabilityConfiguration() {
        CapabilityReference reference = capabilityRegistry.getAll().stream()
                .filter(e -> SecurityCapability.class.getSimpleName().equals(e.capability().getClass().getSimpleName()))
                .findFirst().orElse(null);
        if (reference == null) {
            LOG.debug("Custom Security Configuration capability not created.");
            return null;
        }

        SecurityCapability securityCapability = reference.capabilityAs(SecurityCapability.class);
        return securityCapability.getConfig();
    }
}
