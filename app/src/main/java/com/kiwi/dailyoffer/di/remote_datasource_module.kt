package com.kiwi.dailyoffer.di

import com.kiwi.dailyoffer.R
import com.kiwi.dailyoffer.repository.FlightsDatasource
import com.kiwi.dailyoffer.repository.WeatherDatasource
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

/*    // single instance of HelloRepository
    single<HelloRepository> { HelloRepositoryImpl() }

    // MyViewModel ViewModel
    viewModel { MyViewModel(get()) }*/
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

/*
val weatherModule = applicationContext {

    // ViewModel for Search View
    viewModel { SearchViewModel(get(), get()) }

    // ViewModel for Result View
    viewModel { ResultViewModel(get(), get()) }

    // ViewModel for Detail View
    viewModel { params -> DetailViewModel(params["id"],get(), get()) }

    // Weather Data Repository
    bean { WeatherRepositoryImpl(get()) as WeatherRepository }
}

val rxModule = applicationContext {
    // provided components
    bean { ApplicationSchedulerProvider() as SchedulerProvider }
}

// Gather all app modules
val weatherApp = listOf(weatherModule, rxModule)
*/
