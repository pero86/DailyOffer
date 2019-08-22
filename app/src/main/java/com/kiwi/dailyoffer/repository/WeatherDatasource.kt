package com.kiwi.dailyoffer.repository

import io.reactivex.Single
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.Query

/**
 * Weather datasource - Retrofit tagged
 */
interface WeatherDatasource {

/*    @GET("/flights")
    @Headers("Content-type: application/json")
    fun flights(@Query("v") version: Int = 3, @Query("sort") sortBy : String? = "price",
                @Query("fly_from") flyFrom : String, @Query("fly_to") flyTo : String,
                @Query("date_from") dateFrom : String, @Query("date_to") dateTo : String,
                @Query("limit") limit : Int? =5)

    @GET("/geocode")
    @Headers("Content-type: application/json")
    fun geocode(@Query("address") address: String): Single<Geocode>

    @GET("/weather")
    @Headers("Content-type: application/json")
    fun weather(@Query("lat") lat: Double?, @Query("lon") lon: Double?, @Query("lang") lang: String): Single<Weather>*/

}
