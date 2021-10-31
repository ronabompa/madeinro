package com.android.madeinro.entities

import com.google.maps.model.LatLng

data class Ad(var id: Long, var name: String, var products: List<Product>, var location: LatLng,
              var program: String)
