package org.example.capability;



import javax.inject.Inject;
import javax.inject.Named;
import java.util.Map;

import org.sonatype.nexus.capability.CapabilitySupport;



@Named(SecurityDecriptor.CAPABILITY_ID)
public class SecurityCapability extends CapabilitySupport<SecurityCapabilityConfiguration> {

    @Inject
    public SecurityCapability() {
    }

    @Override
    protected SecurityCapabilityConfiguration createConfig(Map<String, String> properties) {
        return new SecurityCapabilityConfiguration(properties);
    }


    @Override
    protected void configure(SecurityCapabilityConfiguration config) throws Exception {
        super.configure(config);
    }
}
