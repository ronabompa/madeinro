package com.android.madeinro.entities

import com.google.maps.model.LatLng

data class Order(var id: Long, var nameSeller: String, var nameBuyer: String, var orderedProducts: List<Product>,
                 var status: String, var price: Double)
