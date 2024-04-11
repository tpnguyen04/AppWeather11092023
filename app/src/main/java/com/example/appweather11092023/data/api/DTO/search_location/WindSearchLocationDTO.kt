package com.example.appweather11092023.data.api.DTO.search_location


import com.google.gson.annotations.SerializedName

data class WindSearchLocationDTO(
    @SerializedName("deg")
    val deg: Int,
    @SerializedName("gust")
    val gust: Double,
    @SerializedName("speed")
    val speed: Double
)