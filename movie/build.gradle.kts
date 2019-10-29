plugins {
    id(Plugins.androidFeature)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kotlinAnnotationProcessor)
    id(Plugins.safeArgs)
}

android {
    compileSdkVersion(App.compileSdk)

    defaultConfig {

        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)

    }
}

dependencies {

    implementation(project(Modules.core))
    implementation(project(Modules.data))

    // TODO: Move to a Gradle script to be imported from all features

    // Testing
    testImplementation(Dependencies.test_JUnit)
    testImplementation(Dependencies.test_LiveData)
    testImplementation(Dependencies.test_coRoutines)
    testImplementation(Dependencies.test_MockitoKotlin)

    // Android Testing
    androidTestImplementation(Dependencies.androidTest_runner)
    androidTestImplementation(Dependencies.androidTest_espresso)

}
