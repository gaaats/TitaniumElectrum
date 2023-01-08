package com.game.space.sho.api

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName


@Keep
data class ResponseDevilHost(
    @SerializedName("geoLolo")
    val geoLolo: String,
    @SerializedName("viewLolo")
    val viewLolo: String,
    @SerializedName("appsCheckerLolo")
    val appsCheckerLolo: String,
)