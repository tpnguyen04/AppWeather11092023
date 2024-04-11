package com.example.appweather11092023

import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.bumptech.glide.Glide
import com.example.appweather11092023.common.AppCommon
import com.example.appweather11092023.data.api.ApiService
import com.example.appweather11092023.data.api.DTO.search_location.WeatherSearchLocationDTO
import com.example.appweather11092023.data.api.DTO.search_location.search_location_DTO
import com.example.appweather11092023.data.api.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.DateFormat
import java.time.Instant
import java.time.LocalDateTime
import java.time.ZoneOffset
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class MainActivity : AppCompatActivity() {

    private var editTextInputCity: EditText? = null
    private var buttonSearchCity: Button? = null
    private var textViewCountryName: TextView? = null
    private var textViewCityName: TextView? = null
    private var imageViewStatus: ImageView? = null
    private var textViewTemp: TextView? = null
    private var textViewWeatherStatus: TextView? = null
    private var textViewHumidityUnit: TextView? = null
    private var textViewCloudUnit: TextView? = null
    private var textViewWindSpeedUnit: TextView? = null
    private var textViewLastUpdatedTime: TextView? = null
    private var buttonNavigateScreen: Button? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // find view by ID
        editTextInputCity = findViewById(R.id.edit_text_input_city)
        buttonSearchCity = findViewById(R.id.button_search_city)
        textViewCountryName = findViewById(R.id.text_view_country_name)
        textViewCityName = findViewById(R.id.text_View_city_name)
        imageViewStatus = findViewById(R.id.image_view_status)
        textViewTemp = findViewById(R.id.text_view_temp)
        textViewWeatherStatus = findViewById(R.id.text_view_weather_status)
        textViewHumidityUnit = findViewById(R.id.text_view_humidity_unit)
        textViewCloudUnit = findViewById(R.id.text_view_cloud_unit)
        textViewWindSpeedUnit = findViewById(R.id.text_view_wind_speed_unit)
        textViewLastUpdatedTime = findViewById(R.id.text_view_last_updated_time)
        buttonNavigateScreen = findViewById(R.id.button_navigate_screen)

        // search city
        buttonSearchCity?.setOnClickListener {
            val inputCity = editTextInputCity?.text.toString()
            if (inputCity.isBlank()) {
                editTextInputCity?.error = "You have to input a city or state!"
            } else {val apiService = RetrofitClient.getApiService()
                apiService.getWeatherSearchLocation(AppCommon.APP_ID, AppCommon.UNITS, inputCity).enqueue(
                    object : Callback<search_location_DTO> {
                        @RequiresApi(Build.VERSION_CODES.O)
                        override fun onResponse(
                            call: Call<search_location_DTO>,
                            response: Response<search_location_DTO>
                        ) {
                            val country = response.body()?.sys?.country
                            val city = response.body()?.name
                            val temp = response.body()?.main?.temp
                            val weatherStatus = response.body()?.weather?.get(0)?.description
                            val humidity = response.body()?.main?.humidity
                            val cloud = response.body()?.clouds?.all
                            val windSpeed = response.body()?.wind?.speed

//                            Log.d("pphat", "temp: ${temp.toString()}")
//                            Log.d("pphat", "status: ${weatherStatus}")
//                            Log.d("pphat", "humidity: ${humidity.toString()}")
//                            Log.d("pphat", "cloud: ${cloud.toString()}")
//                            Log.d("pphat", "windSpeed: ${windSpeed.toString()}")
//                            Log.d("pphat", "country: ${country}")
//                            Log.d("pphat", "City: $inputCity")

                            textViewCountryName?.text = "Country name: $country"
                            textViewCityName?.text = "City name: $city"
                            textViewTemp?.text = temp?.toString()
                            textViewWeatherStatus?.text = weatherStatus
                            textViewHumidityUnit?.text = humidity.toString()
                            textViewCloudUnit?.text = cloud.toString()
                            textViewWindSpeedUnit?.text = windSpeed.toString()

                            // get weather image status
                            imageViewStatus?.let {
                                Glide
                                    .with(this@MainActivity)
                                    .load("https://openweathermap.org/img/w/" + response.body()?.weather?.getOrNull(0)?.icon.toString() + ".png")
                                    .into(it)
                            }

                            // get last updated
                            val sunrise = response.body()?.sys?.sunrise?.toLong()
                            val timezoneOffset = response.body()?.timezone?.toLong()

                            val dt = ZonedDateTime.ofInstant(sunrise?.let { it ->
                                Instant.ofEpochSecond(
                                    it
                                )
                            }, ZoneOffset.UTC)
                            val hhmm = String.format("%+03d:%02d", (timezoneOffset?.div(3600)), (timezoneOffset?.rem(
                                3600
                            )))
                            val dtWithOffset = dt.withZoneSameInstant(ZoneOffset.of(hhmm))

                            val formatter = DateTimeFormatter.ofPattern("HH:mm:ss a dd/MM/yyyy")
                            val formattedDateTime = dtWithOffset.format(formatter)

                            textViewLastUpdatedTime?.text = formattedDateTime

                        }
                        override fun onFailure(call: Call<search_location_DTO>, t: Throwable) {
                            Log.d("pphat", "On failure")
                        }
                    })

            }
        }
    }
}