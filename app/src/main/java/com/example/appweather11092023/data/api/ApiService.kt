package com.example.appweather11092023.data.api

import com.example.appweather11092023.data.api.DTO.search_location.search_location_DTO
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {
    // get weather from search location
    @GET("data/2.5/weather?")
    fun getWeatherSearchLocation(
        @Query("appid") appID: String,
        @Query("units") units: String,
        @Query("q") q: String
    ): Call<search_location_DTO>
}