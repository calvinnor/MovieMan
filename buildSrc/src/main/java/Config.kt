object Plugins {

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"

    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinAnnotationProcessor = "kotlin-kapt"
    const val plugin_GoogleServices = "com.google.gms.google-services"
    const val plugin_fabric = "io.fabric"
    const val safeArgs = "androidx.navigation.safeargs.kotlin"

    const val dependencyUpdates = "com.github.ben-manes.versions"

    const val plugin_gradleTools = "com.android.tools.build:gradle:${Versions.gradleTools}"
    const val plugin_gradleKotlin =
        "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
    const val plugin_gradleGoogleServices =
        "com.google.gms:google-services:${Versions.plugin_Firebase}"
    const val plugin_safeArgs =
        "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.navigationComponent}"
    const val plugin_fabricGradle = "io.fabric.tools:gradle:${Versions.plugin_fabricCrashlytics}"
}

object Config {

    const val release_minifyEnabled = true
    const val debug_minifyEnabled = false
}

object Modules {

    const val app = ":app"
    const val core = ":core"
    const val data = ":data"
    const val movie = ":movie"

    val allModules = arrayOf(app, core, data, movie)
}

object App {

    const val compileSdk = 29
    const val targetSdk = compileSdk
    const val minSdk = 21

    const val appId = "com.calvinnor.movieman"
    const val appVersion = "1.0"
    const val appCode = 1
}

object Versions {

    // Gradle
    const val gradleTools = "4.0.0"
    const val dependencyUpdates = "0.28.0"

    // Kotlin
    const val kotlinVersion = "1.3.72"

    // AndroidX
    const val appCompat = "1.1.0"
    const val recyclerView = "1.1.0"
    const val swipeRefreshLayout = "1.1.0"
    const val materialComponents = "1.1.0"
    const val constraintLayout = "1.1.3"
    const val palette = "1.0.0"
    const val navigationComponent = "2.3.0"

    // ViewModel + LiveData
    const val arch_viewModelLiveData = "2.2.0"
    const val arch_coreTesting = "2.1.0"
    const val arch_Room = "2.2.5"
    const val arch_RoomCoroutines = "2.1.0-alpha04"

    // Moshi
    const val moshi = "1.9.3"
    const val moshiRetrofitConverter = "2.9.0"

    // Coroutines
    const val coroutines = "1.3.2"

    // Retrofit
    const val retrofit = "2.9.0"
    const val retrofitCoroutinesAdapter = "0.9.2"

    // Stetho
    const val stetho = "1.5.1"

    // Glide
    const val glide = "4.11.0"

    // Koin
    const val koin = "2.0.1"

    // KTX
    const val ktx_Core = "1.3.0"
    const val ktx_Fragment = "1.2.5"
    const val ktx_Palette = "1.0.0"
    const val ktx_Collections = "1.1.0"
    const val ktx_LifecycleViewModel = "2.1.0"
    const val ktx_ReactiveStreams = ktx_LifecycleViewModel

    // Firebase
    const val plugin_Firebase = "4.3.3"
    const val deps_Firebase = "17.4.4"

    // Crashlytics
    const val plugin_fabricCrashlytics = "1.31.2"
    const val deps_fabricCrashlytics = "2.10.1"

    // Testing
    const val test_JUnit = "4.13"

    // Mockito
    const val mockitoKotlin = "2.1.0"

    // Android Testing
    const val androidTest_runner = "1.1.1"
    const val androidTest_espresso = "3.2.0"
}

object Dependencies {

    // Jars
    val jarLibs = mapOf("dir" to "libs", "include" to listOf("*.jar"))

    // Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"

    // AndroidX
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerView = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:${Versions.swipeRefreshLayout}"
    const val materialComponents =
        "com.google.android.material:material:${Versions.materialComponents}"
    const val palette = "androidx.palette:palette:${Versions.palette}"

    // ViewModel + Lifecycle
    const val arch_ViewModelLiveData =
        "androidx.lifecycle:lifecycle-extensions:${Versions.arch_viewModelLiveData}"

    // Room
    const val arch_Room = "androidx.room:room-runtime:${Versions.arch_Room}"
    const val arch_RoomCoroutines = "androidx.room:room-coroutines:${Versions.arch_RoomCoroutines}"
    const val kapt_Room = "androidx.room:room-compiler:${Versions.arch_Room}"

    // Navigation
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigationComponent}"
    const val navigationUi =
        "androidx.navigation:navigation-ui-ktx:${Versions.navigationComponent}"

    // Moshi
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val moshiRetrofitConverter =
        "com.squareup.retrofit2:converter-moshi:${Versions.moshiRetrofitConverter}"

    // Coroutines
    const val coroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

    // Retrofit
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitCoroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.retrofitCoroutinesAdapter}"

    // Stetho
    const val stetho = "com.facebook.stetho:stetho:${Versions.stetho}"
    const val stethoInterceptor = "com.facebook.stetho:stetho-okhttp3:${Versions.stetho}"

    // Glide
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // Koin
    const val koin = "org.koin:koin-android:${Versions.koin}"
    const val koinAndroidX = "org.koin:koin-androidx-scope:${Versions.koin}"
    const val koinViewModel = "org.koin:koin-androidx-viewmodel:${Versions.koin}"

    // KTX
    const val ktx_Core = "androidx.core:core-ktx:${Versions.ktx_Core}"
    const val ktx_Fragment = "androidx.fragment:fragment-ktx:${Versions.ktx_Fragment}"
    const val ktx_Palette = "androidx.palette:palette-ktx:${Versions.ktx_Palette}"
    const val ktx_Collections = "androidx.collection:collection-ktx:${Versions.ktx_Collections}"
    const val ktx_LifecycleViewModel =
        "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.ktx_LifecycleViewModel}"
    const val ktx_ReactiveStreams =
        "androidx.lifecycle:lifecycle-reactivestreams-ktx:${Versions.ktx_ReactiveStreams}"

    // Firebase
    const val firebase_Core = "com.google.firebase:firebase-core:${Versions.deps_Firebase}"

    // Crashlytics
    const val firebase_Crashlytics =
        "com.crashlytics.sdk.android:crashlytics:${Versions.deps_fabricCrashlytics}"

    // Testing
    const val test_JUnit = "junit:junit:${Versions.test_JUnit}"
    const val test_JUnitRunner = "androidx.test.ext.junit.runners.AndroidJUnit4"
    const val test_LiveData = "androidx.arch.core:core-testing:${Versions.arch_coreTesting}"
    const val test_Room = "androidx.room:room-testing:${Versions.arch_Room}"
    const val test_coRoutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"

    // Mockito
    const val test_MockitoKotlin =
        "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"

    // Android Testing
    const val androidTest_runner = "androidx.test.ext:junit:${Versions.androidTest_runner}"
    const val androidTest_espresso =
        "androidx.test.espresso:espresso-core:${Versions.androidTest_espresso}"

}
