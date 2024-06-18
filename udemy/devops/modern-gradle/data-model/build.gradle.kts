// A clean gradle build file should start with a plugin block
plugins {
    id("java-library") // Specifies a Java library module. Also applies id("java") which adds core Java functionality
}

java {
    // This is an extension (Added by java-library above, if we remove it, this will not get recognized)
    toolchain.languageVersion.set(JavaLanguageVersion.of(13))
}