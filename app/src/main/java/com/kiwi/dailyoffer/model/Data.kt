package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class Data(
    @SerializedName("aTime")
    val aTime: Int,
    @SerializedName("aTimeUTC")
    val aTimeUTC: Int,
    @SerializedName("baglimit")
    val baglimit: Baglimit,
    @SerializedName("bags_price")
    val bagsPrice: BagsPrice,
    @SerializedName("booking_token")
    val bookingToken: String,
    @SerializedName("cityFrom")
    val cityFrom: String,
    @SerializedName("cityTo")
    val cityTo: String,
    @SerializedName("conversion")
    val conversion: Conversion,
    @SerializedName("countryFrom")
    val countryFrom: CountryFrom,
    @SerializedName("countryTo")
    val countryTo: CountryTo,
    @SerializedName("dTime")
    val dTime: Int,
    @SerializedName("dTimeUTC")
    val dTimeUTC: Int,
    @SerializedName("deep_link")
    val deepLink: String,
    @SerializedName("distance")
    val distance: Double,
    @SerializedName("fly_duration")
    val flyDuration: String,
    @SerializedName("flyFrom")
    val flyFrom: String,
    @SerializedName("flyTo")
    val flyTo: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("mapIdfrom")
    val mapIdfrom: String,
    @SerializedName("mapIdto")
    val mapIdto: String,
    @SerializedName("nightsInDest")
    val nightsInDest: Int,
    @SerializedName("p1")
    val p1: Int,
    @SerializedName("p2")
    val p2: Int,
    @SerializedName("price")
    val price: Int,
    @SerializedName("return_duration")
    val returnDuration: String,
    @SerializedName("route")
    val route: List<RouteX>,
    @SerializedName("throw_away_ticketing")
    val throwAwayTicketing: Boolean,
    @SerializedName("type_flights")
    val typeFlights: List<String>,
    @SerializedName("virtual_interlining")
    val virtualInterlining: Boolean
)