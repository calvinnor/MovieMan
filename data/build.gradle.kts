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
}

dependencies {
    implementation(fileTree(Dependencies.jarLibs))

    implementation(project(Modules.core))

    // Room
    api(Dependencies.arch_Room)
    api(Dependencies.arch_RoomCoroutines)
    kapt(Dependencies.kapt_Room)

    // Testing
    testImplementation(Dependencies.test_Room)

}