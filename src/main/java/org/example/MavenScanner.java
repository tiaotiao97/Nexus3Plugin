package org.example;


import javax.annotation.Nonnull;
import javax.inject.Inject;
import javax.inject.Named;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.sonatype.nexus.repository.maven.MavenPathParser;
import org.sonatype.nexus.repository.storage.AssetStore;
import org.sonatype.nexus.repository.view.Context;
import org.sonatype.nexus.repository.view.Payload;


@Named
public class MavenScanner {
    private static final Logger LOG = LoggerFactory.getLogger(MavenScanner.class);

    private final AssetStore assetStore;
    private final MavenPathParser mavenPathParser;

    @Inject
    public MavenScanner(final AssetStore assetStore, final MavenPathParser mavenPathParser) {
        this.assetStore = assetStore;
        this.mavenPathParser = mavenPathParser;
    }

    Object scan(@Nonnull Context context, Payload payload, String organizationId) {
        return null;
    }
}
