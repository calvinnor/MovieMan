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
    api(Dependencies.kotlinStdlib)

    // AndroidX
    api(Dependencies.appCompat)
    api(Dependencies.coreKtx)
    api(Dependencies.constraintLayout)

    // Architecture Components
    api(Dependencies.arch_ViewModelLiveData)
    api(Dependencies.arch_Room)
    api(Dependencies.arch_RoomCoroutines)
    kapt(Dependencies.kapt_Room)

    // Gson
    api(Dependencies.gson)
    implementation(Dependencies.gsonRetrofitConverter)

    // Coroutines
    api(Dependencies.coroutinesCore)
    api(Dependencies.coroutinesAndroid)

    // Retrofit
    api(Dependencies.retrofit)
    implementation(Dependencies.retrofitCoroutinesAdapter)

    // Stetho
    implementation(Dependencies.stetho)
    implementation(Dependencies.stethoInterceptor)

    // Glide
    implementation(Dependencies.glide)

    // Koin
    api(Dependencies.koin)
    api(Dependencies.koinAndroidX)
    api(Dependencies.koinViewModel)

    // Testing
    testImplementation(Dependencies.test_JUnit)
    testImplementation(Dependencies.test_LiveData)
    testImplementation(Dependencies.test_Room)

    // Android Testing
    androidTestImplementation(Dependencies.androidTest_runner)
    androidTestImplementation(Dependencies.androidTest_espresso)

}
