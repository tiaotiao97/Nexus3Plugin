package org.example;

import com.sun.org.slf4j.internal.Logger;
import com.sun.org.slf4j.internal.LoggerFactory;

import javax.inject.Inject;
import javax.inject.Named;
import javax.inject.Singleton;
import org.sonatype.nexus.repository.Repository;
import org.sonatype.nexus.repository.http.HttpResponses;
import org.sonatype.nexus.repository.maven.MavenPath;
import org.sonatype.nexus.repository.maven.MavenPathParser;
import org.sonatype.nexus.repository.view.Context;
import org.sonatype.nexus.repository.view.Response;
import org.sonatype.nexus.repository.view.handlers.ContributedHandler;
import javax.annotation.Nonnull;

@Named
@Singleton
public class ScannerHandler implements ContributedHandler {
    private static final Logger LOG = LoggerFactory.getLogger(ScannerHandler.class);

    private final MavenScanner mavenScanner;
    private final MavenPathParser mavenPathParser;
    private final ConfigurationHelper configurationHelper;

    @Inject
    public ScannerHandler(final MavenScanner mavenScanner, final MavenPathParser mavenPathParser, final ConfigurationHelper configurationHelper) {
        this.mavenScanner = mavenScanner;
        this.mavenPathParser = mavenPathParser;
        this.configurationHelper = configurationHelper;
    }

    @Nonnull
    @Override
    public Response handle(@Nonnull Context context) throws Exception {
        if(!configurationHelper.isCapabilityEnabled()){
            LOG.debug("Custom sec plugin not enabled.");
            return context.proceed();
        }
        //获取仓库类
        Repository repository = context.getRepository();

        //获取仓库类型(maven2、npm、pip)
        String repositoryFormat = repository.getFormat().getValue();

        switch (repositoryFormat){
            case "maven2":{
                //扫描逻辑
                Object mavenPathAttribute = context.getAttributes().get(MavenPath.class.getName());
                MavenPath mavenPath = (MavenPath) mavenPathAttribute;
                MavenPath parsedMavenPath = mavenPathParser.parsePath(mavenPath.getPath());
                MavenPath.Coordinates coordinates = parsedMavenPath.getCoordinates();
                String groupId = coordinates.getGroupId();
                String artifactId = coordinates.getArtifactId();
                String version = coordinates.getVersion();
                LOG.debug(coordinates.getGroupId(), coordinates.getArtifactId(), coordinates.getVersion());
                LOG.debug(context.getRequest().getPayload().toString());
                try{
                    LOG.debug("安全扫描中..");
                    mavenScanner.scan(context, context.getRequest().getPayload(), "org.test.sec");
                }catch (RuntimeException e){
                    LOG.error(e.getMessage());
                }
                //拦截
                Response response = context.proceed();
                System.out.println(response.getPayload().openInputStream().toString());
                return HttpResponses.forbidden("deny by custom rules...."+groupId+":"+artifactId+"@"+version);
//                break;
            }
            case "npm": {
                //扫描逻辑
                break;
            }
            case "pip": {
                //扫描逻辑
                break;
            }
            default:
                return context.proceed();

        }
        //放行

        return context.proceed();
    }

}