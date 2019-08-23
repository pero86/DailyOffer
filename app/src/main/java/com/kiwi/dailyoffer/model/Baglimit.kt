package com.kiwi.dailyoffer.model


import com.google.gson.annotations.SerializedName

data class Baglimit(
    @SerializedName("hand_height")
    val handHeight: Int,
    @SerializedName("hand_length")
    val handLength: Int,
    @SerializedName("hand_weight")
    val handWeight: Int,
    @SerializedName("hand_width")
    val handWidth: Int,
    @SerializedName("hold_dimensions_sum")
    val holdDimensionsSum: Int,
    @SerializedName("hold_height")
    val holdHeight: Int,
    @SerializedName("hold_length")
    val holdLength: Int,
    @SerializedName("hold_weight")
    val holdWeight: Int,
    @SerializedName("hold_width")
    val holdWidth: Int
)