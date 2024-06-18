plugins {
    id("my-java-library")
}

dependencies {
    // Declaration for project internal dependency.
    // The business-logic module depends on the data-model dependency.
    // Now we should get Code completion etc. in IntelliJ for MessageModel.
    implementation(project(":data-model"))

    // Declaration for external dependency
    implementation("org.apache.commons:commons-lang3:3.12.0")
    // Now we should get Code completion etc. in IntelliJ for e.g. StringUtils.
}