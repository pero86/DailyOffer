package com.kiwi.dailyoffer.repository

import com.google.gson.JsonObject
import io.reactivex.Single
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

interface FlightsDatasource {
    @GET("/flights")
    @Headers("Content-type: application/json")
    fun flights(@Query("fly_from") flyFrom : String, @Query("fly_to") flyTo : String,
                @Query("date_from") dateFrom : String, @Query("date_to") dateTo : String,
                @Query("v") version: Int = 3, @Query("sort") sortBy : String? = "price",
                @Query("partner") partner : String = "picky",
                @Query("limit") limit : Int? =5) : Call<JsonObject>
}