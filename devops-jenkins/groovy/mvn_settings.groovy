import jenkins.model.*
import org.jenkinsci.plugins.configfiles.maven.*
import org.jenkinsci.plugins.configfiles.maven.security.*

def store = Jenkins.instance.getExtensionList('org.jenkinsci.plugins.configfiles.GlobalConfigFiles')[0]

println("Setting maven settings xml")

def configId =  'our_settings'
def configName = 'myMavenConfig for jenkins automation example'
def configComment = 'Global Maven Settings'
def configContent  = '''<?xml version="1.0" encoding="UTF-8"?>
<settings xmlns="http://maven.apache.org/SETTINGS/1.0.0"
          xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
          xsi:schemaLocation="http://maven.apache.org/SETTINGS/1.0.0
                          https://maven.apache.org/xsd/settings-1.0.0.xsd">
    <localRepository/>
    <interactiveMode/>
    <usePluginRegistry/>
    <offline/>
    <pluginGroups/>
    <servers>
        <server>
            <id>local-snapshots</id>
            <username>admin</username>
            <password>admin123</password>
        </server>
        <server>
            <id>local-releases</id>
            <username>admin</username>
            <password>admin123</password>
        </server>
        <server>
            <id>local</id>
            <username>admin</username>
            <password>admin123</password>
        </server>
    </servers>
    <mirrors/>
    <proxies/>
    <profiles>
        <profile>
            <id>local</id>
            <repositories>
                <repository>
                    <id>releases</id>
                    <name>Releases</name>
                    <url>https://nexus:8081/repository/maven-public/</url>
                </repository>
                <repository>
                    <id>snapshots</id>
                    <name>Snapshots</name>
                    <url>https://nexus:8081/repository/maven-public/</url>
                </repository>
            </repositories>
        </profile>
    </profiles>
</settings>
'''

def globalConfig = new GlobalMavenSettingsConfig(configId, configName, configComment, configContent, false, null)
store.save(globalConfig)