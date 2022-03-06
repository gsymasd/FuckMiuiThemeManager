buildscript {
    var kotlin_version: String by extra
    kotlin_version = "1.6.10"
    repositories {
        google()
        mavenCentral()
    }

    dependencies {
        classpath("com.android.tools.build:gradle:7.1.2")
        classpath(kotlin("gradle-plugin", kotlin_version))
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}