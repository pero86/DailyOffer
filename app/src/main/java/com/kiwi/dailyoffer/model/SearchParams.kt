package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class SearchParams(
    @SerializedName("search_params")
    val searchParams: SearchParamsX
)