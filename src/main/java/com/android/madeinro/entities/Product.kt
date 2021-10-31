package com.android.madeinro.entities

data class Product(var id: Long, var name: String, var category: String, var icon: Int,
                   var price: Double, var unit: String, var quantity: Int?)
