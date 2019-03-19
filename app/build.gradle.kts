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

        create("release") {
            storeFile = rootProject.file("app/keys/release.jks")
            storePassword = System.getenv("MM_PASSWORD")
            keyAlias = System.getenv("MM_ALIAS")
            keyPassword = System.getenv("MM_PASSWORD")
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

            isMinifyEnabled = Config.debug_minifyEnabled
        }

        getByName("release") {
            signingConfig = signingConfigs.getByName("release")

            isMinifyEnabled = Config.release_minifyEnabled
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.data))

    implementation(project(Modules.movie))

}
