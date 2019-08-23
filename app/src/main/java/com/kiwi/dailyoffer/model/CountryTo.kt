package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class CountryTo(
    @SerializedName("code")
    val code: String,
    @SerializedName("name")
    val name: String
)