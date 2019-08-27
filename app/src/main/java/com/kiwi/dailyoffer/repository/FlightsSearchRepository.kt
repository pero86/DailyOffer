package com.kiwi.dailyoffer.repository

import android.util.Log
import com.kiwi.dailyoffer.utils.Utils
import io.reactivex.Completable
import khronos.toString
import java.util.*

interface FlightsSearchRepository {
    fun searchFlights(flyFrom : String, flyTo : String? = null, dateFrom: Date, dateTo: Date, sortBy: String? = null, limit: Int? = null) : Completable
}

/* Possible local data repository implementation */

class FlightsSearchRepositoryImpl(private val flightsDatasource: FlightsDatasource, private val dateFormat: String = "dd/MM/YYYY"): FlightsSearchRepository {
    override fun searchFlights(
        flyFrom: String,
        flyTo: String?,
        dateFrom: Date,
        dateTo: Date,
        sortBy: String?,
        limit: Int?
    ) : Completable = flightsDatasource.flights(flyFrom,flyTo, dateFrom.toString(dateFormat),dateTo.toString(dateFormat), oneForCity = 1)
        .doOnSuccess { Log.d("*******", "today: " + dateFrom + " formatted " + dateFrom.toString(dateFormat)
                + " dateT " + dateTo + " formatted: " + Utils.toKiwiDateFormat(dateTo)) }
        .ignoreElement()

/*    {
        flightsDatasource.flightsOldWay(flyFrom,flyTo,dateFrom.toString(dateFormat),dateTo.toString(dateFormat)).enqueue(object :
            Callback<FlightSearchResponse> {
            override fun onFailure(call: Call<FlightSearchResponse>, t: Throwable) {

            }

            override fun onResponse(call: Call<FlightSearchResponse>, response: Response<FlightSearchResponse>) {
                Log.d("Response",response.body().toString())
            }
        })
    }*/
}