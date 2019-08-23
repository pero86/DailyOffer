package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class Conversion(
    @SerializedName("EUR")
    val eUR: Int,
    @SerializedName("PLN")
    val pLN: Int
)