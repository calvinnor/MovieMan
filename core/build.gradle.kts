import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

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

        testInstrumentationRunner = Dependencies.test_JUnitRunner

        val tmdbApiKey = TMDB.getApiKeyFromEnvironmentOrProperties(gradleLocalProperties(rootDir))
        buildConfigField("String", "API_KEY", "\"${tmdbApiKey}\"")
        buildConfigField("String", "BASE_URL", "\"${TMDB.BASE_URL}\"")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = Config.release_minifyEnabled
            proguardFiles(getDefaultProguardFile("proguard-android.txt"), "proguard-rules.pro")
        }
    }

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
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
    api(Dependencies.swipeRefreshLayout)
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

    // Moshi
    api(Dependencies.moshi)
    implementation(Dependencies.moshiRetrofitConverter)

    // Coroutines
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

    // Testing
    testImplementation(Dependencies.test_JUnit)
    testImplementation(Dependencies.test_LiveData)
    testImplementation(Dependencies.test_coRoutines)
    testImplementation(Dependencies.test_MockitoKotlin)

    // Android Testing
    androidTestImplementation(Dependencies.androidTest_runner)
    androidTestImplementation(Dependencies.androidTest_espresso)

}
