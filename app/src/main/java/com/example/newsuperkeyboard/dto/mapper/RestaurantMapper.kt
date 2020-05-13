package com.example.newsuperkeyboard.dto.mapper

import com.example.newsuperkeyboard.dto.ApiRestaurant
import com.example.newsuperkeyboard.model.Restaurant

class RestaurantMapper {

    fun map(apiRestaurantList: List<ApiRestaurant>): List<Restaurant> {
        return apiRestaurantList.map {
            Restaurant(
                it.name,
                it.webUrl
            )
        }
    }
}