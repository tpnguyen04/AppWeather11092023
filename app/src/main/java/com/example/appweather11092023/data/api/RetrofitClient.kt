package com.example.appweather11092023.data.api

import com.example.appweather11092023.common.AppCommon
import com.google.gson.GsonBuilder
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

object RetrofitClient {


    private val retrofit: Retrofit = createRetrofit()
    private val apiService = retrofit.create(ApiService::class.java)

    fun getRetrofit() = retrofit
    fun getApiService() = apiService

    private fun createRetrofit(): Retrofit {
        val okHttpClient = OkHttpClient.Builder()
            .connectTimeout(AppCommon.TIME_REQUEST_DEFAULT, TimeUnit.SECONDS)
            .readTimeout(AppCommon.TIME_REQUEST_DEFAULT, TimeUnit.SECONDS)
            .writeTimeout(AppCommon.TIME_REQUEST_DEFAULT, TimeUnit.SECONDS)
            .build()
        val gson = GsonBuilder().setLenient().create()
        val retrofit = Retrofit.Builder()
            .baseUrl(AppCommon.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .client(okHttpClient)
            .build()
        return retrofit
    }
}