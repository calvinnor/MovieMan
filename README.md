# MovieMan
[![GitHub release (latest by date)](https://img.shields.io/github/v/release/CalvinNor/MovieMan?label=Download)](https://github.com/CalvinNor/MovieMan/releases)
[![CircleCI](https://circleci.com/gh/CalvinNor/MovieMan.svg?style=svg)](https://circleci.com/gh/CalvinNor/MovieMan)

An open-source Android app for viewing Movies / TV information.

![](https://www.themoviedb.org/assets/1/v4/logos/408x161-powered-by-rectangle-green-bb4301c10ddc749b4e79463811a68afebeae66ef43d17bcfd8ff0e60ded7ce99.png "TMDB")

Introduction:
=====

>  MovieMan is the materialization of how (I think) the Architecture of a good Android Application should be

MovieMan is my sandbox project, where test my ideas, ideal architectures and play around with libraries. <br>It's pretty close to a full-fledged application, complete with Network Requests, Local DB, Material Design and Test Cases.

It contains some bells and whistles like **Kotlin**, **Coroutines**, **Jetpack** & **AndroidX**.
<br>
It's **Modular**, has **Dependency Injection**, and closely follows **DRY and SOLID** principles.
<br>It's also backed by CircleCI to build and run test cases.

MovieMan is built to look good. I'm no designer or UX expert, and will borrow inspiration from common Google applications. You will find Fade / Material Transitions at some places, just cause they're easy to do and maintain :)

I’ll keep adding features based on what the TMDB API provides.
<br>Writing duplicate screens and code for API-completion purposes is nowhere on my TODO list

> The aim is to make MovieMan feature-complete in terms of a variety of features, all while learning new things and implementing useful cases.

Screenshots & Gifs:
=====
<p>
  <img src="https://i.imgur.com/kIHwAhw.png" width="250" style="margin:2px"/>
  <img src="https://i.imgur.com/tFUjnrV.png" width="250" style="margin:2px"/>
  <img src="https://user-images.githubusercontent.com/33203243/54878796-65e2e280-4e57-11e9-8108-11a5bf3a0c2e.gif" width="250" style="margin:2px"/>
</p>

Downloads:
=====

Download the latest APK [here](https://github.com/CalvinNor/MovieMan/releases/download/0.1/app-release.apk) or browse releases on [Releases](https://github.com/CalvinNor/MovieMan/releases)

Build Instructions:
=====

1. Clone the Project

2. Grab an API key from TMDB and add 'TMDB_API_KEY' to your Environment variables. Instructions can be found in **Properties.kt**

3. Add the following to your Environment variables after generating a keystore. Or optionally, remove the signing config from app's **build.gradle.kts**
	1. MM_PASSWORD
   	2. MM_ALIAS
   	3. MM_PASSWORD

4. Gradle Sync and Rebuild

Design Decisions & Dependencies:
=====

### [Kotlin](https://kotlinlang.org/):

I :heart: Kotlin. It's a breath of fresh air when coming from Java, and makes code so much nicer to read and write. It has OOTB support for Lambdas, Extension Functions, DSLs and a vast stdlib. JetBrains actively maintains and releases stable versions every month. All my projects (including this one) will be 100% Kotlin.

### [JetPack](https://developer.android.com/jetpack) - Architecture Components & AndroidX:
Would be a loss to build an application without these libraries. With Google advocating MVVM, and these libraries working so flawlessly with each other, it really leaves you no choice.
<br>**Room** - Database Layer
<br>**ViewModel** - Data Preservation across configuration changes
<br>**Lifecycle** - Handling annoying issues with Activities / Fragments namely when pushing data during false states
<br>**Navigation** - Handling Intent / Fragment Transactions, isolating sources from destinations and easy argument passing!
<br>**AndroidX, Material Components** - For embracing Material Design and backporting API features to minSdk

### [Koin](https://insert-koin.io/) - Dependency Injection:
Sick and tired of Dagger in Production, and annoyed by it slowing down my build, I turned to a substitute. [Kodein](https://github.com/Kodein-Framework/Kodein-DI) seemed to be the recommended (and a more established) library for Android, however I chose Koin for it's sheer simplicity. It also made strides in performance in v2.0, which makes it my current choice for DI without code generation.

### [Moshi](https://github.com/square/moshi) + [Retrofit](https://square.github.io/retrofit/) - Networking:
A very simple choice when it comes to using REST APIs. I prefer Moshi over Gson because Moshi throws correct exceptions, and has good support for Kotlin. It also doesn't come with a Date Formatter by default, forcing you to handle UTC yourself.
<br>See [Moshi vs Gson](https://www.reddit.com/r/androiddev/comments/684flw/why_use_moshi_over_gson/) for more info about this decison.

### [JUnit4](https://junit.org/junit4/) + [Mockito](https://site.mockito.org/) - Unit Testing:
A gold combination for unit testing and mocking dependencies. Since the app follows SOLID, dependent layers can be easily mocked and tested in isolation.
<br>I'm using [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin) for a simpler DSL (and not writing ``when`` because ‘when’ is a Kotlin keyword)

### [CircleCI](https://circleci.com/) - Continuous Integration:
I chose CircleCI because of it's easy setup and configuration. Also because it provides free build times (1000 minutes) per month. I use CircleCI to build the app and run all it's Unit Test Cases.

### Multi Modular:
Separation of Concerns and eventually supporting Instant Apps and Dynamic Delivery.
<br>Modules are split by feature, with some base modules like core and data. Multi-modular applications force you to isolate components and prevent easy imports / dependencies between them.
<br>Such applications can build faster too, since Gradle caches the unchanged modules, and will only build the changed ones (and the modules dependent on the changed module if a public API is touched)
<br> See [How Yelp Modularized the Android App](https://engineeringblog.yelp.com/2018/06/how-yelp-modularized-the-android-app.html)

### Other Gems

- [Kotlin Coroutines](https://kotlinlang.org/docs/reference/coroutines-overview.html)
- [Gradle Kotlin DSL](https://docs.gradle.org/current/userguide/kotlin_dsl.html)
- [Dependency Updates Plugin](https://github.com/ben-manes/gradle-versions-plugin)

# Contributions:

Contributions in any form are welcome.
<br>Just make sure to have fun and learn new things :)
