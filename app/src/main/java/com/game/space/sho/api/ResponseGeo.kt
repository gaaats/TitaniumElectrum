package com.game.space.sho.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class ResponseGeo(
    @SerializedName("city")
    val city: String,
    @SerializedName("country")
    val country: String,
    @SerializedName("countryCode")
    val countryCode: String,
)