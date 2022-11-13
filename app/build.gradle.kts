plugins {
    id(Plugins.androidApplication)
    id(Plugins.plugin_GoogleServices)
    id(Plugins.plugin_crashlytics)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAnnotationProcessor)
}

android {
    compileSdk = App.compileSdk

    buildFeatures {
        viewBinding = true
    }

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
        minSdk = App.minSdk
        targetSdk = App.targetSdk

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

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.data))

    implementation(project(Modules.movie))

    // Dependencies
    implementation(Dependencies.firebase_Core)
    implementation(platform(Dependencies.firebase_Bom))
    implementation(Dependencies.firebase_Crashlytics)
    implementation(Dependencies.firebase_Analytics)

}
