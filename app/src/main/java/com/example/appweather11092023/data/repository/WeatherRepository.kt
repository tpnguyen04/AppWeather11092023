package com.example.appweather11092023.data.repository

import com.example.appweather11092023.common.AppCommon
import com.example.appweather11092023.data.api.DTO.search_location.search_location_DTO
import com.example.appweather11092023.data.api.RetrofitClient
import retrofit2.Call

class WeatherRepository() {
    private val apiService = RetrofitClient.getApiService()

    suspend fun getWeatherSearchLocation(cityName: String): Call<search_location_DTO> {
        return apiService.getWeatherSearchLocation(
            appID = AppCommon.APP_ID,
            units = AppCommon.UNITS,
            q = cityName
        )
    }
}