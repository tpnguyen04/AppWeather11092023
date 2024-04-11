package com.example.appweather11092023.data.api.DTO.search_location


import com.google.gson.annotations.SerializedName

data class WeatherSearchLocationDTO(
    @SerializedName("description")
    val description: String,
    @SerializedName("icon")
    val icon: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: String
)