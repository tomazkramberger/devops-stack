import jenkins.model.Jenkins
import hudson.security.ACL

// Go into quiet mode
Jenkins.instance.doQuietDown()

// Wake up after an async wait
Thread.start {
  // doCancelQuietDown requires admin privileges
  ACL.impersonate(ACL.SYSTEM)

  // Sleep 5 minutes, in milliseconds
  Thread.sleep(5 * 60 * 1000)
  Jenkins.instance.doCancelQuietDown()
}