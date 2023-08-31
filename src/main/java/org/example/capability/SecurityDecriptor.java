package org.example.capability;


import javax.inject.Named;
import javax.inject.Singleton;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.sonatype.nexus.capability.CapabilityDescriptorSupport;
import org.sonatype.nexus.capability.CapabilityType;
import org.sonatype.nexus.capability.Tag;
import org.sonatype.nexus.capability.Taggable;
import org.sonatype.nexus.formfields.FormField;
import org.sonatype.nexus.formfields.StringTextFormField;

import static org.example.capability.SecurityCapabilityKey.API_URL;


@Singleton
@Named(SecurityDecriptor.CAPABILITY_ID)
public class SecurityDecriptor extends CapabilityDescriptorSupport<SecurityCapabilityConfiguration> implements Taggable {
    static final String CAPABILITY_ID = "custom-security";
    private static final String CAPABILITY_NAME = "Custom Security Configuration";
    private static final String CAPABILITY_DESCRIPTION = "Custom sec scanner";

    private final StringTextFormField fieldApiUrl;

    public SecurityDecriptor() {
        fieldApiUrl = new StringTextFormField(API_URL.propertyKey(), "Custom Sec API URL", "", FormField.MANDATORY).withInitialValue(API_URL.defaultValue());
    }

    @Override
    public CapabilityType type() {
        return CapabilityType.capabilityType(CAPABILITY_ID);
    }

    @Override
    public String name() {
        return CAPABILITY_NAME;
    }

    @Override
    public String about() {
        return CAPABILITY_DESCRIPTION;
    }

    @Override
    public List<FormField> formFields() {
        return Arrays.asList(fieldApiUrl);
    }

    @Override
    protected SecurityCapabilityConfiguration createConfig(Map<String, String> properties) {
        return new SecurityCapabilityConfiguration(properties);
    }

    @Override
    public Set<Tag> getTags() {
        return Collections.singleton(Tag.categoryTag("Security"));
    }
}