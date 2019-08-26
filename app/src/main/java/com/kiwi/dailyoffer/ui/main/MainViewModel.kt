package com.kiwi.dailyoffer.ui.main

import android.text.format.DateUtils
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kiwi.dailyoffer.model.FlightSearchResponse
import com.kiwi.dailyoffer.repository.FlightsSearchRepository
import com.kiwi.dailyoffer.utils.AbstractViewModel
import com.kiwi.dailyoffer.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import khronos.Dates
import khronos.plus
import khronos.week
import java.lang.System.err
import java.util.*

class MainViewModel(private val flightSearchRepository: FlightsSearchRepository) : AbstractViewModel() {

    val searchEvent = SingleLiveEvent<SearchEvent>()

    fun searchFlights() {

        launch {
            searchEvent.value = SearchEvent(isLoading = true)

            val today = Dates.today
            val weekFromNow = today.plus(1.week)

            flightSearchRepository.searchFlights("BTS", null,today,weekFromNow,null,null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    searchEvent.postValue(SearchEvent(isSuccess = true, isLoading = false))
                }, {
                        err -> searchEvent.postValue(SearchEvent(error = err, isSuccess = false, isLoading = false))
                })
        }
    }


}

//data class ResultUIModel(val list: List<DailyForecastModel> = emptyList(), val error: Throwable? = null)
data class SearchEvent(val isLoading: Boolean = false, val isSuccess: Boolean = false, val error: Throwable? = null)