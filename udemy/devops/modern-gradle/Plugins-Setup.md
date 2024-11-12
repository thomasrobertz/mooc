```
you said it is unconventional to define custom plugins in the project/gradle folder with a settings.gradle.kts file.
how is it done conventionally?
```

Defining custom plugins in a Gradle project is a common practice, but the convention usually involves integrating these plugins directly within the project's standard structure or publishing them to a repository for easier reuse across multiple projects. Here's how it's typically done:

### 1. **Direct Integration in `build.gradle` or `build.gradle.kts`**

For smaller projects or when a plugin is tightly coupled with a single project, you might directly include the plugin code in the `buildSrc` directory. This approach doesn't require publishing the plugin and makes it immediately available to all build scripts within the project.

- **Create `buildSrc`**: Inside your project root, create a directory named `buildSrc`. This directory is treated as an included build by Gradle.
- **Setup Plugin Code**:
  - Inside `buildSrc`, create the necessary directory structure for a Gradle plugin, typically involving at least a `src/main/groovy` (for Groovy based plugins) or `src/main/kotlin` (for Kotlin based plugins) directory for your plugin source code.
  - Add a `build.gradle` or `build.gradle.kts` inside `buildSrc` to manage dependencies and configuration for your plugin.

Here is an example structure for `buildSrc` with Kotlin:
```plaintext
buildSrc/
│
├── src/
│   └── main/
│       ├── kotlin/
│       │   └── MyPlugin.kt
│       └── resources/
│           └── META-INF/gradle-plugins/
│               └── my-plugin.properties
└── build.gradle.kts
```

And an example `build.gradle.kts` inside `buildSrc`:
```kotlin
plugins {
    `kotlin-dsl`
}

repositories {
    gradlePluginPortal()
}

dependencies {
    implementation("org.jetbrains.kotlin:kotlin-stdlib")
}
```

### 2. **Publishing to a Local or Remote Repository**

For larger projects or when you intend to share your plugin across multiple projects, you might opt to publish your plugin to a local repository (like an Artifactory within your organization) or a public repository.

- **Create a Separate Plugin Project**: Create a standalone Gradle project for your plugin. This project will have its own `build.gradle` or `build.gradle.kts` file.
- **Publishing Configuration**: Configure this project to publish your plugin artifact to a Maven or Ivy repository. You would typically use the `maven-publish` or `ivy-publish` plugin for this purpose.
  
Example of publishing a plugin using `build.gradle.kts`:
```kotlin
plugins {
    `maven-publish`
}

group = "com.example"
version = "0.0.1"

publishing {
    repositories {
        maven {
            url = uri("http://myrepository.com/repo")
        }
    }
    publications {
        create<MavenPublication>("mavenJava") {
            from(components["java"])
        }
    }
}
```

### 3. **Using the Plugin**

Once the plugin is published or included in `buildSrc`, you can apply it in any Gradle build script like so:
```kotlin
plugins {
    id("my-plugin") version "0.0.1"
}
```
or if it's in `buildSrc`, simply:
```kotlin
plugins {
    id("my-plugin")
}
```

This approach is more maintainable and aligns with Gradle's conventions for building, managing, and distributing custom build logic. It makes the plugins easily reusable and versionable, and keeps the project configurations clean and standardized.