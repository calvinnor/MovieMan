// Top-level build file where you can add configuration options common to all sub-projects/modules.

plugins {
    id(Plugins.dependencyUpdates) version Versions.dependencyUpdates
}

buildscript {

    repositories {
        google()
        jcenter()
    }

    dependencies {
        classpath(Plugins.plugin_gradleTools)
        classpath(Plugins.plugin_gradleKotlin)
        classpath(Plugins.plugin_gradleGoogleServices)
        classpath(Plugins.plugin_safeArgs)
    }
}

allprojects {

    repositories {
        google()
        jcenter()
    }
}

tasks.register("clean", Delete::class.java) {
    delete(rootProject.buildDir)
}
