plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kotlinAnnotationProcessor)
}

android {
    compileSdkVersion(App.compileSdk)

    signingConfigs {
        getByName("debug") {
            storeFile = rootProject.file("app/keys/debug.jks")
            storePassword = "debug123"
            keyAlias = "debug"
            keyPassword = "debug123"
        }
    }

    defaultConfig {

        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)

        applicationId = App.appId
        versionCode = App.appCode
        versionName = App.appVersion
    }

    buildTypes {
        getByName("debug") {
            signingConfig = signingConfigs.getByName("debug")
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-debug"
        }
    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.data))

    implementation(project(Modules.movie))

}
