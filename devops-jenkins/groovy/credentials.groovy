import jenkins.model.*
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.impl.*;
import com.cloudbees.plugins.credentials.common.*
import com.cloudbees.plugins.credentials.domains.*
import com.cloudbees.jenkins.plugins.sshcredentials.impl.*
import hudson.plugins.sshslaves.*;
import com.cloudbees.jenkins.plugins.awscredentials.*;

global_domain = Domain.global()
credentials_store =
    Jenkins.instance.getExtensionList(
    'com.cloudbees.plugins.credentials.SystemCredentialsProvider'
    )[0].getStore()

keyfile = "/root/.ssh/id_rsa"
basicCredentials = new BasicSSHUserPrivateKey(
    CredentialsScope.GLOBAL,
    "Root credentials",
    "root",
    new BasicSSHUserPrivateKey.FileOnMasterPrivateKeySource(keyfile),
    "",
    ""
)
credentials_store.addCredentials(global_domain, basicCredentials)