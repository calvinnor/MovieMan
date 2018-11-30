plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kotlinAnnotationProcessor)
}

android {
    compileSdkVersion(App.compileSdk)

    defaultConfig {

        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)

        applicationId = App.appId
        versionCode = App.appCode
        versionName = App.appVersion
    }
}

dependencies {

    implementation(project(Modules.core))

}
