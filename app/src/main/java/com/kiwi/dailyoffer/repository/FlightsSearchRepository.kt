package com.kiwi.dailyoffer.repository

import android.util.Log
import com.kiwi.dailyoffer.model.Data
import com.kiwi.dailyoffer.model.FlightSearchResponse
import com.kiwi.dailyoffer.utils.Utils
import io.reactivex.Completable
import io.reactivex.Maybe
import io.reactivex.Observable
import io.reactivex.Single
import khronos.toString
import java.util.*

interface FlightsSearchRepository {
    fun searchFlights(flyFrom : String, flyTo : String? = null, dateFrom: Date, dateTo: Date, sortBy: String? = null, limit: Int? = null) : Completable
    fun getFlights(): Single<List<Data>>
    fun getFlightData(positionInsideList : Int): Maybe<Data?>
    fun getFlightDataNew(positionInsideList: Int): Data?
    fun getNumberOfTotalFlights(): Int
}

/* Possible local data repository implementation */

class FlightsSearchRepositoryImpl(private val flightsDatasource: FlightsDatasource, private val dateFormat: String = "dd/MM/YYYY"): FlightsSearchRepository {
    val routesCache = arrayListOf<Data>()
    //var cache : FlightSearchResponse?  = null

    override fun searchFlights(
        flyFrom: String,
        flyTo: String?,
        dateFrom: Date,
        dateTo: Date,
        sortBy: String?,
        limit: Int?
    ) : Completable = flightsDatasource.flights(flyFrom,flyTo, dateFrom.toString(dateFormat),dateTo.toString(dateFormat), oneForCity = 1)
        //.map { it.data.map { it.flyFrom ?: throw IllegalStateException("No Location data") } } // moznost ked by podla gps sa hladalo tak rozne mesta
        .doOnSuccess {
                t: FlightSearchResponse? ->
                routesCache.clear()
                t?.data?.map { routesCache.add(it) }
        }
        .doOnSuccess { Log.d("*******", "today: " + dateFrom + " formatted " + dateFrom.toString(dateFormat)
                + " dateT " + dateTo + " formatted: " + Utils.toKiwiDateFormat(dateTo))

                }
        .ignoreElement()

    override fun getFlights(): Single<List<Data>> = Single.just(routesCache)

    override fun getFlightData(positionInsideList: Int): Maybe<Data?> {
        if (routesCache.isEmpty() || routesCache.size < positionInsideList) return Maybe.empty()
        else return Maybe.just(routesCache[positionInsideList])
    }
    override fun getFlightDataNew(positionInsideList: Int): Data? {
        if (routesCache.isEmpty() || routesCache.size < positionInsideList) return null
        else return routesCache[positionInsideList]
    }

    override fun getNumberOfTotalFlights(): Int = routesCache.size
}