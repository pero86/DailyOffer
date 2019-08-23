package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class Routes(
    @SerializedName("route")
    val route: List<Route>
)