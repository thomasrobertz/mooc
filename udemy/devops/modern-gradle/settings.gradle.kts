// Recommended to set root project name (also is a SonarLint rule)
rootProject.name = "modern-gradle"

pluginManagement {
    // Add plugins repos just like in dependency management
    repositories.gradlePluginPortal()

    // Add custom repos, include builds,...
}

// Dependency management
dependencyResolutionManagement {
    repositories.mavenCentral()
    repositories.google()

    /* Adding custom repos could look like:
    repositories.maven("https://my.repo/repo") {
        credentials.username = "youruser"
        credentials.password = "passwd"
    }
    */

    // Usually components downloaded from repos are binaries.
    // In gradle we can also build components:
    // includeBuild("../your-other-project") // Where to find components, need to add actual components too
}

// Gradle projects consist of subprojects by default. Here we include them
include("app")
include("business-logic")
include("data-model")