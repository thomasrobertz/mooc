plugins {
    id("my-application")
}

// Set the main entry class
application {
    mainClass.set("com.example.MyApplication")
}

dependencies {
    implementation(project(":data-model"))
    implementation(project(":business-logic"))
}