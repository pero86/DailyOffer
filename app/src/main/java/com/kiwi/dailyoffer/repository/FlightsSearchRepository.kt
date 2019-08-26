package com.kiwi.dailyoffer.repository

import android.text.format.DateFormat
import android.text.format.DateUtils
import android.util.Log
import com.kiwi.dailyoffer.model.FlightSearchResponse
import com.kiwi.dailyoffer.utils.Utils
import io.reactivex.Completable
import khronos.toString
import org.koin.android.ext.android.inject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.http.Query
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

interface FlightsSearchRepository {
    fun searchFlights(flyFrom : String, flyTo : String? = null, dateFrom: Date, dateTo: Date, sortBy: String? = null, limit: Int? = null) : Completable
}

class FlightsSearchRepositoryImpl(private val flightsDatasource: FlightsDatasource, private val dateFormat: String = "dd/MM/YYYY"): FlightsSearchRepository {
    override fun searchFlights(
        flyFrom: String,
        flyTo: String?,
        dateFrom: Date,
        dateTo: Date,
        sortBy: String?,
        limit: Int?
    ) : Completable = flightsDatasource.flights(flyFrom,flyTo, dateFrom.toString(dateFormat),dateTo.toString(dateFormat))
        .doOnSuccess { Log.d("*******", "today: " + dateFrom + " formatted " + dateFrom.toString(dateFormat)
                + " dateT " + dateTo + " formatted: " + Utils.toKiwiDateFormat(dateTo)) }
        //.doOnError { Log.d("pro",dateFrom.toString(dateFormat)) }
        //.doOnSuccess {  }
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