plugins {
    id ("application") // For packaging and running Java apps
}

java {
    toolchain.languageVersion.set(JavaLanguageVersion.of(13))
}