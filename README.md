# Nexus3Plugin
## 介绍
项目是Nexus3的插件demo，参考snyk的Nexus3插件编写。运行在Nexus OSS 3.49.0-02 测试，如果需要运行在其他版本下，需要修改pom中nexus-plugins依赖为相应版本（https://mvnrepository.com/artifact/org.sonatype.nexus.plugins/nexus-plugins）

````<parent>
        <groupId>org.sonatype.nexus.plugins</groupId>
        <artifactId>nexus-plugins</artifactId>
        <version>3.37.3-02</version>
    </parent>
````
## 打包
Nexus3支持的插件格式为 `kar`，使用bundle打包。在pom中已经配置好了bundle的maven插件。打包时只需在pom目录下执行命令即可
````
mvn -PbuildKar clean install
````
打包完成后会在target目录中看到后缀为kar的插件包

## 部署
将kar包直接放置到Nexus服务器的 /opt/sonatype/nexus/deploy目录下即可完成插件部署，无需重启。

在Nexus后台 System/Bundles页面中，可以看到我们部署的插件。
![image](https://github.com/tiaotiao97/Nexus3Plugin/assets/20039777/6b09bf38-d0da-4af1-b6b2-7773d10c4edd)


## 使用

进入Nexus后台 System/Capabilities页面中，点击 `create capability`进入创建页面，
![image](https://github.com/tiaotiao97/Nexus3Plugin/assets/20039777/99ec2958-b199-42b7-a420-747ee4ac18db)


在插件列表中选择`Custom Security Configuration`，
![image](https://github.com/tiaotiao97/Nexus3Plugin/assets/20039777/0fe9529b-d1b2-4cc8-867b-16373e055020)


再点击创建就可以了。
![image](https://github.com/tiaotiao97/Nexus3Plugin/assets/20039777/4411e388-eb7d-45e5-8b75-837262b11efc)


创建好可以在System/Capabilities页面中看到，点进去能看到详情：
![image](https://github.com/tiaotiao97/Nexus3Plugin/assets/20039777/c1702800-9042-4925-87c2-9caeb26f20fd)

可以点击按钮进行功能的启用、停用和删除。


## 参考
https://help.sonatype.com/repomanager3/integrations/bundle-development/installing-bundles
https://github.com/snyk/nexus-snyk-security-plugin
https://docs.snyk.io/integrations/gatekeeper-plugins/nexus-repository-manager-gatekeeper-plugin
