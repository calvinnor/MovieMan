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

}
