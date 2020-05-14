package com.example.newsuperkeyboard

import android.location.Location
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.newsuperkeyboard.com.TripAdvisorProvider
import com.example.newsuperkeyboard.model.Restaurant
import com.google.android.gms.location.FusedLocationProviderClient

class MainViewModel : ViewModel() {

    lateinit var fusedLocationClient: FusedLocationProviderClient

    private val restaurantsMutableLiveData = MutableLiveData<List<Restaurant>>()
    val restaurantsLiveData = restaurantsMutableLiveData

    fun requestRestaurant(restaurantType: String = "all") {
        fusedLocationClient.lastLocation
            .addOnSuccessListener { location: Location? ->
                location?.let { mLocation ->
                    TripAdvisorProvider.getRestaurants(
                        latitude = mLocation.latitude.toString(),
                        longitude = mLocation.longitude.toString(),
                        combined_food = restaurantType
                    ) { success, result ->
                        if (success) {
                            restaurantsMutableLiveData.value = result
                        }
                    }
                }

            }
            .addOnFailureListener {
                it.printStackTrace()
            }
    }
}