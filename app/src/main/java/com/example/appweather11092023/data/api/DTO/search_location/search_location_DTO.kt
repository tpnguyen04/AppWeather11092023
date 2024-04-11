package com.example.appweather11092023.data.api.DTO.search_location


import com.google.gson.annotations.SerializedName

data class search_location_DTO(
    @SerializedName("base")
    val base: String,
    @SerializedName("clouds")
    val clouds: CloudsSearchLocationDTO,
    @SerializedName("cod")
    val cod: Int,
    @SerializedName("coord")
    val coord: CoordSearchLocationDTO,
    @SerializedName("dt")
    val dt: Int,
    @SerializedName("id")
    val id: Int,
    @SerializedName("main")
    val main: MainSearchLocationDTO,
    @SerializedName("name")
    val name: String,
    @SerializedName("sys")
    val sys: SysSearchLocationDTO,
    @SerializedName("timezone")
    val timezone: Int,
    @SerializedName("visibility")
    val visibility: Int,
    @SerializedName("weather")
    val weather: List<WeatherSearchLocationDTO>,
    @SerializedName("wind")
    val wind: WindSearchLocationDTO
)