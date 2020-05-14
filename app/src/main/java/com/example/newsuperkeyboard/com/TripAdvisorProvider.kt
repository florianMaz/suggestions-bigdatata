package com.example.newsuperkeyboard.com

import com.example.newsuperkeyboard.Constants
import com.example.newsuperkeyboard.dto.reponse.ApiResponseRestaurant
import com.example.newsuperkeyboard.dto.reponse.ApiResponseRestaurantMapper
import com.example.newsuperkeyboard.model.Restaurant
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory

object TripAdvisorProvider {

    private var service: TripAdvisorService

    init {
        service = Retrofit.Builder()
            .baseUrl(Constants.URL_TRIP_ADVISOR)
            .client(createOkHttpClient())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .addConverterFactory(MoshiConverterFactory.create())
            .build()
            .create(TripAdvisorService::class.java)
    }

    private fun createOkHttpClient(): OkHttpClient {
        val logging = HttpLoggingInterceptor()
        logging.level = HttpLoggingInterceptor.Level.BODY
        return OkHttpClient.Builder()
            .addInterceptor(logging)
            .addInterceptor { chain ->
                var request = chain.request()
                request = request.newBuilder()
                    .addHeader("x-rapidapi-host", "tripadvisor1.p.rapidapi.com")
                    .addHeader("x-rapidapi-key", "f579cefca5msh8bcbecd3ca8d071p1764f0jsn0781a022e162")
                    .build()
                chain.proceed(request)
            }
            .build()
    }

    fun getRestaurants(
        limit: String = "3",
        currency: String = "EUR",
        distance: String = "2",
        lunit: String = "km",
        lang: String = "fr_FR",
        latitude: String = "48.866667",
        longitude: String = "2.333333",
        combined_food: String = "5379",
        listener: (success: Boolean, result: List<Restaurant>?) -> Unit
    ) {
        CoroutineScope(Dispatchers.IO).launch {
            val result: ApiResponseRestaurant
            try {
                result = service.getRestaurants(
                    limit,
                    currency,
                    distance,
                    lunit,
                    lang,
                    latitude,
                    longitude,
                    combined_food).await()
                withContext(Dispatchers.Main) {
                    listener.invoke(true, ApiResponseRestaurantMapper().map(result))
                }
            } catch (e: Exception) {
                withContext(Dispatchers.Main) {
                    listener.invoke(false, null)
                    e.printStackTrace()
                }
            }
        }
    }
}