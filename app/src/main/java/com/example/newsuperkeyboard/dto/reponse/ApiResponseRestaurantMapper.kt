package com.example.newsuperkeyboard.dto.reponse

import com.example.newsuperkeyboard.model.Restaurant

class ApiResponseRestaurantMapper {
    fun map(response: ApiResponseRestaurant): List<Restaurant>? = response.data?.map {
        Restaurant(
            it?.name,
            it?.webUrl
        )
    }
}