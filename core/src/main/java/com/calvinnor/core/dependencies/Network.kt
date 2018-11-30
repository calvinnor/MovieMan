package com.calvinnor.core.dependencies

import com.calvinnor.core.BuildConfig
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.CallAdapter
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

private const val BASE_URL = "baseUrl"
private const val USE_INTERCEPTOR = "useInterceptor"

private const val API_KEY = "api_key"

val networkModule = module {

    /** [kotlinx.coroutines.Deferred] support for Retrofit **/
    single<CallAdapter.Factory> { CoroutineCallAdapterFactory() }

    /** Stetho Interceptor for Network Traffic **/
    single<Interceptor> { StethoInterceptor() }

    /** Base URL for Retrofit **/
    single(name = BASE_URL) { BuildConfig.BASE_URL }

    /** Use the Stetho interceptor **/
    single(name = USE_INTERCEPTOR) { BuildConfig.DEBUG }

    /** OkHttp client for Retrofit **/
    single<OkHttpClient> {
        OkHttpClient.Builder().apply {
            addInterceptor { chain ->
                val originalRequest = chain.request()

                val urlWithQueryParams = originalRequest.url().newBuilder()
                    .addQueryParameter(API_KEY, BuildConfig.API_KEY)
                    .build()

                chain.proceed(
                    originalRequest.newBuilder().url(urlWithQueryParams).build()
                )
            }

            if (get(name = USE_INTERCEPTOR)) addNetworkInterceptor(get())

        }.build()
    }

    /** Gson **/
    single { Gson() }

    /** Gson parsing for Retrofit **/
    single<Converter.Factory> { GsonConverterFactory.create(get()) }

    /** Retrofit **/
    single<Retrofit> {
        Retrofit.Builder().apply {
            baseUrl(get<String>(name = BASE_URL))
            client(get())
            addCallAdapterFactory(get())
            addConverterFactory(get())

        }.build()
    }
}
