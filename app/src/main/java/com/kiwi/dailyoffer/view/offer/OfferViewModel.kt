package com.kiwi.dailyoffer.view.offer

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.kiwi.dailyoffer.model.Data
import com.kiwi.dailyoffer.repository.FlightsSearchRepository
import com.kiwi.dailyoffer.utils.AbstractViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import khronos.Dates
import java.util.*

class OfferViewModel(private val position: Int,private val flightSearchRepository: FlightsSearchRepository) : AbstractViewModel() {
    val uiData = MutableLiveData<ResultUIModel>()

    fun getFlightData() {
        launch {
            flightSearchRepository.getFlights()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe({ data ->
                    uiData.value = ResultUIModel(getFlightData(getPositionInArray(position),data))
                }, { err ->
                    uiData.value = ResultUIModel(error = err)
                })
        }
    }

    fun printPosition() {
        Log.d("OfferViewModel","position: $position")
    }

    fun getFlightData(positionInsideList: Int, routesCache : List<Data>): Data? {
        if (routesCache.isEmpty() || routesCache.size < positionInsideList) return null
        else return routesCache[positionInsideList]
    }

    private fun getPositionInArray(position: Int) : Int {
        val numberOfFlights = flightSearchRepository.getNumberOfTotalFlights()

        if (numberOfFlights > 35) {
            val cal = Calendar.getInstance()
            cal.time = Dates.today
            val datOfTheWeek = cal.get(Calendar.DAY_OF_WEEK)

            return (position * 7) + datOfTheWeek-1
        }

        return position
    }

}

data class ResultUIModel(val flightInfo: Data? = null, val error: Throwable? = null)