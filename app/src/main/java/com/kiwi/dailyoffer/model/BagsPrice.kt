package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class BagsPrice(
    @SerializedName("hand")
    val hand: Double,
    @SerializedName("1")
    val x1: Double,
    @SerializedName("2")
    val x2: Double
)