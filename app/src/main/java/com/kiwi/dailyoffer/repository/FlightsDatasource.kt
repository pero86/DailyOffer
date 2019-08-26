package com.kiwi.dailyoffer.repository

import com.google.gson.JsonObject
import com.kiwi.dailyoffer.model.FlightSearchResponse
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FlightsDatasource {
    @GET("/flights")
    @Headers("Content-type: application/json")
    fun flightsOldWay(@Query("fly_from") flyFrom : String, @Query("fly_to") flyTo : String? = null,
                @Query("date_from") dateFrom : String, @Query("date_to") dateTo : String,
                @Query("v") version: Int = 3, @Query("sort") sortBy : String? = "price",
                @Query("partner") partner : String = "picky",
                      @Query("one_for_city") oneForCity : Int? = null,
                @Query("limit") limit : Int =45) : Call<FlightSearchResponse>

    @GET("/flights")
    @Headers("Content-type: application/json")
    fun flights(@Query("fly_from") flyFrom : String, @Query("fly_to") flyTo : String? = null,
                @Query("date_from") dateFrom : String, @Query("date_to") dateTo : String,
                @Query("v") version: Int = 3, @Query("sort") sortBy : String? = "price",
                @Query("partner") partner : String = "picky",
                @Query("one_for_city") oneForCity : Int? = null,
                @Query("limit") limit : Int =45) : Single<FlightSearchResponse>
}