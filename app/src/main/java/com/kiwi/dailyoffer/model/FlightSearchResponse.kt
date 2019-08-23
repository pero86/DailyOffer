package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class FlightSearchResponse(
    @SerializedName("data")
    val `data`: List<Data>,
    @SerializedName("connections")
    val connections: List<Any>,
    @SerializedName("currency")
    val currency: String,
    @SerializedName("currency_rate")
    val currencyRate: Double,
    @SerializedName("search_params")
    val searchParams: SearchParamsX,
    @SerializedName("time")
    val time: Int
)