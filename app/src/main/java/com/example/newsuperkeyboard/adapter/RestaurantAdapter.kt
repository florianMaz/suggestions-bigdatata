package com.example.newsuperkeyboard.adapter

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.widget.AppCompatImageButton
import androidx.appcompat.widget.AppCompatImageView
import androidx.appcompat.widget.AppCompatTextView
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.newsuperkeyboard.R
import com.example.newsuperkeyboard.model.Restaurant
import com.squareup.picasso.Picasso

class RestaurantAdapter(private val context: Context) : RecyclerView.Adapter<RestaurantAdapter.RestaurantViewHolder>() {

    var restaurants = listOf<Restaurant>()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RestaurantViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_restaurant, parent, false)
        return RestaurantViewHolder(view)
    }

    override fun getItemCount() =  restaurants.size

    override fun onBindViewHolder(holderRestaurant: RestaurantViewHolder, position: Int) {
        Log.d("COUCOU", "position=$position, name=${restaurants[position].name}")
        holderRestaurant.txvName.text = restaurants[position].name
        holderRestaurant.btn.setOnClickListener {
            val browserIntent = Intent(Intent.ACTION_VIEW).apply {
                data = Uri.parse(restaurants[position].url)
            }
            startActivity(context, browserIntent, null)
        }
        Picasso
            .with(context)
            .load(restaurants[position].image)
            .into(holderRestaurant.imv)
    }

    class RestaurantViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        val txvName: AppCompatTextView = itemView.findViewById(R.id.txv_restaurant)
        val imv: AppCompatImageView = itemView.findViewById(R.id.imv_restaurant)
        val btn: AppCompatImageButton = itemView.findViewById(R.id.btn_open)
    }
}