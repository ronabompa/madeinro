package com.android.madeinro.entities

data class User(var id: Long, var firstName: String, var lastName: String, var badge: String,
                var email: String, var password: String,
                var phoneNumer: String?, var photo: Int, var favorites: List<Product>?, var ads: List<Ad>?)
