plugins {
    id(Plugins.androidLibrary)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kotlinAnnotationProcessor)
}

android {
    compileSdkVersion(App.compileSdk)

    defaultConfig {

        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)

        testInstrumentationRunner = Dependencies.test_JUnitRunner
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {
    implementation(fileTree(Dependencies.jarLibs))

    implementation(project(Modules.core))

    // Room
    api(Dependencies.arch_Room)
    api(Dependencies.arch_RoomCoroutines)
    kapt(Dependencies.kapt_Room)

    // Moshi Codegen
    kapt(Dependencies.moshiKotlin)

    // Testing
    testImplementation(Dependencies.test_Room)

}
