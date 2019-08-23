package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class RouteX(
    @SerializedName("aTime")
    val aTime: Int,
    @SerializedName("aTimeUTC")
    val aTimeUTC: Int,
    @SerializedName("airline")
    val airline: String,
    @SerializedName("bags_recheck_required")
    val bagsRecheckRequired: Boolean,
    @SerializedName("cityFrom")
    val cityFrom: String,
    @SerializedName("cityTo")
    val cityTo: String,
    @SerializedName("dTime")
    val dTime: Int,
    @SerializedName("dTimeUTC")
    val dTimeUTC: Int,
    @SerializedName("flight_no")
    val flightNo: Int,
    @SerializedName("flyFrom")
    val flyFrom: String,
    @SerializedName("flyTo")
    val flyTo: String,
    @SerializedName("id")
    val id: String,
    @SerializedName("latFrom")
    val latFrom: Double,
    @SerializedName("latTo")
    val latTo: Double,
    @SerializedName("lngFrom")
    val lngFrom: Double,
    @SerializedName("lngTo")
    val lngTo: Double,
    @SerializedName("mapIdfrom")
    val mapIdfrom: String,
    @SerializedName("mapIdto")
    val mapIdto: String,
    @SerializedName("operating_carrier")
    val operatingCarrier: String,
    @SerializedName("price")
    val price: Double,
    @SerializedName("return")
    val returnX: Int,
    @SerializedName("source")
    val source: Any
)