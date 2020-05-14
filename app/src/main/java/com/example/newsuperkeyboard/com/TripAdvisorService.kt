package com.example.newsuperkeyboard.com

import com.example.newsuperkeyboard.dto.reponse.ApiResponseRestaurant
import kotlinx.coroutines.Deferred
import retrofit2.http.GET
import retrofit2.http.Query


interface TripAdvisorService {

    @GET("/restaurants/list-by-latlng")
    fun getRestaurants(
        @Query("limit") limit: String,
        @Query("currency") currency: String,
        @Query("distance") distance: String,
        @Query("lunit") lunit: String,
        @Query("lang") lang: String,
        @Query("latitude") latitude: String,
        @Query("longitude") longitude: String,
        @Query("combined_food") combined_food: String
    ): Deferred<ApiResponseRestaurant>
}