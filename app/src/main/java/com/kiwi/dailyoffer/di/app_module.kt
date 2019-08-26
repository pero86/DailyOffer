package com.kiwi.dailyoffer.di

import com.kiwi.dailyoffer.R
import com.kiwi.dailyoffer.repository.FlightsSearchRepository
import com.kiwi.dailyoffer.repository.FlightsSearchRepositoryImpl
import com.kiwi.dailyoffer.ui.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.android.viewmodel.dsl.viewModel
import org.koin.dsl.module

val appModule = module {

    single<FlightsSearchRepository> { FlightsSearchRepositoryImpl(get(),androidContext().getString(
        R.string.date_format)) }

    viewModel { MainViewModel(get()) }

}