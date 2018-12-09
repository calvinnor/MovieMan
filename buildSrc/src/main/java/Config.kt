object Plugins {

    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val androidFeature = "com.android.feature"

    const val kotlinAndroid = "kotlin-android"
    const val kotlinAndroidExtensions = "kotlin-android-extensions"
    const val kotlinAnnotationProcessor = "kotlin-kapt"

    const val dependencyUpdates = "com.github.ben-manes.versions"

    const val gradleTools = "com.android.tools.build:gradle:${Versions.gradleTools}"
    const val gradleKotlin = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Versions.kotlinVersion}"
}

object Config {

    const val release_minifyEnabled = true
}

object Modules {

    const val app = ":app"
    const val core = ":core"
    const val movie = ":movie"

    val allModules = arrayOf(app, core, movie)
}

object App {

    const val compileSdk = 28
    const val targetSdk = compileSdk
    const val minSdk = 21

    const val appId = "com.calvinnor.movieman"
    const val appVersion = "1.0"
    const val appCode = 1
}

object Versions {

    // Gradle
    const val gradleTools = "3.3.0-rc02"
    const val dependencyUpdates = "0.20.0"

    // Kotlin
    const val kotlinVersion = "1.3.10"

    // AndroidX
    const val appCompat = "1.0.2"
    const val coreKtx = "1.0.1"
    const val constraintLayout = "1.1.3"

    // Gson
    const val gson = "2.8.5"
    const val gsonRetrofitConverter = "2.4.0"

    // Coroutines
    const val coroutines = "1.0.1"

    // Retrofit
    const val retrofit = "2.5.0"
    const val retrofitCoroutinesAdapter = "0.9.2"

    // Stetho
    const val stetho = "1.5.0"

    // Glide
    const val glide = "4.8.0"

    // Koin
    const val koin = "1.0.2"

    // Testing
    const val test_JUnit = "4.12"

    // Android Testing
    const val androidTest_runner = "1.0.0"
    const val androidTest_espresso = "3.1.0"
}

object Dependencies {

    // Jars
    val jarLibs = mapOf("dir" to "libs", "include" to listOf("*.jar"))

    // Kotlin
    const val kotlinStdlib = "org.jetbrains.kotlin:kotlin-stdlib-jdk7:${Versions.kotlinVersion}"

    // AndroidX
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val coreKtx = "androidx.core:core-ktx:${Versions.coreKtx}"
    const val constraintLayout = "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"

    // Gson
    const val gson = "com.google.code.gson:gson:${Versions.gson}"
    const val gsonRetrofitConverter = "com.squareup.retrofit2:converter-gson:${Versions.gsonRetrofitConverter}"

    // Coroutines
    const val coroutinesCore = "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val coroutinesAndroid = "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"

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

    // Testing
    const val test_JUnit = "junit:junit:${Versions.test_JUnit}"
    const val test_JUnitRunner = "androidx.test.ext.junit.runners.AndroidJUnit4"

    // Android Testing
    const val androidTest_runner = "androidx.test.ext:junit:${Versions.androidTest_runner}"
    const val androidTest_espresso = "androidx.test.espresso:espresso-core:${Versions.androidTest_espresso}"

}
