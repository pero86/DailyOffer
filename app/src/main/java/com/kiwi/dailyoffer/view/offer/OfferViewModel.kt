package com.kiwi.dailyoffer.view.offer

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kiwi.dailyoffer.model.Data
import com.kiwi.dailyoffer.repository.FlightsSearchRepository
import com.kiwi.dailyoffer.utils.AbstractViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import khronos.Dates
import java.util.*

class OfferViewModel(private val flightSearchRepository: FlightsSearchRepository) : AbstractViewModel() {
    val uiData = MutableLiveData<ResultUIModel>()

    fun getFlightData(position: Int) {
        uiData.value = ResultUIModel(flightSearchRepository.getFlightDataNew(getPositionInArray(position)))

//        launch {
//            flightSearchRepository.getFlightData(getPositionInArray(position))
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribeOn(Schedulers.io())
//                .subscribe({ data ->
//                    uiData.value = ResultUIModel(data)
//                }, { err ->
//                    //uiData.value = ResultUIModel(error = err)
//                })
//        }
    }

    fun printPosition() {
        //Log.d("OfferViewModel","position: $position")
    }

    private fun getPositionInArray(position: Int) : Int {
        val numberOfFlights = flightSearchRepository.getNumberOfTotalFlights()

        if (numberOfFlights > 35) {
            val cal = Calendar.getInstance()
            cal.time = Dates.today
            val datOfTheWeek = cal.get(Calendar.DAY_OF_WEEK)

            return (position * 6) + (position-1) + datOfTheWeek
        }

        return position
    }


}

data class ResultUIModel(val flightInfo: Data?, val error: Throwable? = null)