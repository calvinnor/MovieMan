plugins {
    id(Plugins.androidApplication)
    id(Plugins.kotlinAndroid)
    id(Plugins.kotlinAndroidExtensions)
    id(Plugins.kotlinAnnotationProcessor)
}

android {
    compileSdkVersion(App.compileSdk)

    defaultConfig {
        applicationId = App.appId

        minSdkVersion(App.minSdk)
        targetSdkVersion(App.targetSdk)

        versionCode = App.appCode
        versionName = App.appVersion

        testInstrumentationRunner = Dependencies.test_JUnitRunner

        /* To create an API key, go to "https://www.themoviedb.org/settings/api" after creating an account */
        /* Create a Properties.kt file in buildSrc/src/main/java/ and paste the following code:

        object TMDB {
            const val BASE_URL = "https://api.themoviedb.org/"
            const val API_KEY = "yourApiKeyGoesHere"
        }

        */

        buildConfigField("String", "API_KEY", "\"${TMDB.API_KEY}\"")
        buildConfigField("String", "BASE_URL", "\"${TMDB.BASE_URL}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }
}

dependencies {
    implementation(fileTree(Dependencies.jarLibs))

    // Kotlin
    implementation(Dependencies.kotlinStdlib)

    // AndroidX
    implementation(Dependencies.appCompat)
    implementation(Dependencies.coreKtx)
    implementation(Dependencies.constraintLayout)

    // Gson
    implementation(Dependencies.gson)
    implementation(Dependencies.gsonRetrofitConverter)

    // Coroutines
    implementation(Dependencies.coroutinesCore)
    implementation(Dependencies.coroutinesAndroid)

    // Retrofit
    implementation(Dependencies.retrofit)
    implementation(Dependencies.retrofitCoroutinesAdapter)

    // Stetho
    implementation(Dependencies.stetho)
    implementation(Dependencies.stethoInterceptor)

    // Glide
    implementation(Dependencies.glide)

    // Koin
    implementation(Dependencies.koin)
    implementation(Dependencies.koinAndroidX)
    implementation(Dependencies.koinViewModel)

    // Testing
    testImplementation(Dependencies.test_JUnit)

    // Android Testing
    androidTestImplementation(Dependencies.androidTest_runner)
    androidTestImplementation(Dependencies.androidTest_espresso)

}
