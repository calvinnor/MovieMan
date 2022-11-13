plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAnnotationProcessor)
}

android {
    compileSdk = App.compileSdk

    buildFeatures {
        viewBinding = true
    }

    defaultConfig {
        minSdk = App.minSdk
        targetSdk = App.targetSdk
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(project(Modules.core))
    implementation(project(Modules.data))

    implementation(Dependencies.preferenceKtx)
}
