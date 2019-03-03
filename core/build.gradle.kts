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
    api(Dependencies.constraintLayout)
    api(Dependencies.recyclerView)
    api(Dependencies.materialComponents)
    api(Dependencies.palette)

    // KTX
    api(Dependencies.ktx_Core)
    api(Dependencies.ktx_Fragment)
    api(Dependencies.ktx_Palette)
    api(Dependencies.ktx_Collections)
    api(Dependencies.ktx_LifecycleViewModel)
    api(Dependencies.ktx_ReactiveStreams)

    // Architecture Components
    api(Dependencies.arch_ViewModelLiveData)

    // Navigation
    api(Dependencies.navigationFragment)
    api(Dependencies.navigationUi)

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

    // Android Testing
    androidTestImplementation(Dependencies.androidTest_runner)
    androidTestImplementation(Dependencies.androidTest_espresso)

}
