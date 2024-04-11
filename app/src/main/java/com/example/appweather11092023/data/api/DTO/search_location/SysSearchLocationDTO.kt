package com.example.appweather11092023.data.api.DTO.search_location


import com.google.gson.annotations.SerializedName

data class SysSearchLocationDTO(
    @SerializedName("country")
    val country: String,
    @SerializedName("id")
    val id: Int,
    @SerializedName("sunrise")
    val sunrise: Int,
    @SerializedName("sunset")
    val sunset: Int,
    @SerializedName("type")
    val type: Int
)