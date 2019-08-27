package com.kiwi.dailyoffer.di

import android.app.Application
import android.util.Log
import com.bumptech.glide.Glide
import com.bumptech.glide.GlideBuilder
import com.bumptech.glide.load.engine.cache.DiskCache
import com.kiwi.dailyoffer.R
import com.kiwi.dailyoffer.repository.FlightsDatasource
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import okhttp3.Cache
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit


val remoteDatasourceModule = module {

    single<OkHttpClient> { createOkHttpClient() }
    single<FlightsDatasource> { createWebService(get(), androidApplication().getString(R.string.SERVER_URL)) }
    single<Cache> { provideOkHttpCache(get()) }
    single<Picasso> { providePicasso(get(),get()) }
}


fun createOkHttpClient(): OkHttpClient {
    val httpLoggingInterceptor = HttpLoggingInterceptor()
    httpLoggingInterceptor.level = HttpLoggingInterceptor.Level.BASIC
    return OkHttpClient.Builder()
        .connectTimeout(60L, TimeUnit.SECONDS)
        .readTimeout(60L, TimeUnit.SECONDS)
        .addInterceptor(httpLoggingInterceptor).build()
}

inline fun <reified T> createWebService(okHttpClient: OkHttpClient, url: String): T {
    val retrofit = Retrofit.Builder()
        .baseUrl(url)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build()
    return retrofit.create(T::class.java)
}

fun providePicasso(application: Application, okHttpClient: OkHttpClient): Picasso {
    return Picasso.Builder(application)
        .downloader(OkHttp3Downloader(okHttpClient))
        .listener { picasso, uri, e -> Log.e("Picasso", "Failed to load image: %s", e) }
        .build()
}

fun provideOkHttpCache(application: Application): Cache {
    val cacheSize = 50 * 1024 * 1024 // 10 MiB
    return Cache(application.cacheDir, cacheSize.toLong())
}