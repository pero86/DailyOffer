package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class SearchParamsX(
    @SerializedName("flyFrom_type")
    val flyFromType: String,
    @SerializedName("to_type")
    val toType: String
)