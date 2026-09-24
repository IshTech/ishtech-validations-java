# Known Issues

Confirmed issues that are not fixed yet.

## 1. Release CI run fails although the release is published
**Status:** open
**Impact:** the CI run for a GitHub release is red even when the release reaches Maven Central
**Affects:** CI release runs (`.github/workflows/cicd.yml`, step "Publish to Maven Central Sonatype")

Description: `central-publishing-maven-plugin` (with `waitUntil=published`) waits at most 30 minutes for Sonatype Central to publish the deployment, then fails with "Polling for <id> timed out before the deployment completed". Sonatype Central can take longer; the deployment is still published.

Steps to reproduce: publish a GitHub release; see the CI run for `v1.0.0` (Sonatype Central took about 36 minutes).

Likely cause: the plugin's default maximum wait time of 1800 seconds.

Suggested fix: raise `waitMaxTime` in the plugin configuration in `pom.xml` (for example to 3600), or wait only until the deployment is validated.
