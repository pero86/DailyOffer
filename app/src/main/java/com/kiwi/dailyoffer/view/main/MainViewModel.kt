package com.kiwi.dailyoffer.view.main

import androidx.lifecycle.MutableLiveData
import com.kiwi.dailyoffer.model.FlightSearchResponse
import com.kiwi.dailyoffer.repository.FlightsSearchRepository
import com.kiwi.dailyoffer.utils.AbstractViewModel
import com.kiwi.dailyoffer.utils.SingleLiveEvent
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import khronos.Dates
import khronos.plus
import khronos.week

class MainViewModel(private val flightSearchRepository: FlightsSearchRepository) : AbstractViewModel() {

    val searchEvent = SingleLiveEvent<SearchEvent>()
    val result = MutableLiveData<ResultModel>()

    fun searchFlights() {

        launch {
            searchEvent.value = SearchEvent(isLoading = true)

            val today = Dates.tomorrow
            val weekFromNow = today.plus(1.week)

            flightSearchRepository.searchFlights("BTS", null,today,weekFromNow,null,null)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe ({
                    searchEvent.postValue(
                        SearchEvent(
                            isSuccess = true,
                            isLoading = false
                        )
                    )
                    result.value = ResultModel()
                }, {
                        err -> searchEvent.postValue(
                    SearchEvent(
                        error = err,
                        isSuccess = false,
                        isLoading = false
                    )
                )
                })
        }
    }


}

data class ResultModel(val results : FlightSearchResponse? = null, val error: Throwable? = null)
data class SearchEvent(val isLoading: Boolean = false, val isSuccess: Boolean = false, val error: Throwable? = null)