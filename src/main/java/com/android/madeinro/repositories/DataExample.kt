package com.android.madeinro.repositories

import androidx.databinding.BindingAdapter
import com.android.madeinro.R
import com.android.madeinro.entities.Order
import com.android.madeinro.entities.Product
import com.android.madeinro.entities.SuggestionAdmin
import com.android.madeinro.entities.User

class DataExample {

    companion object{

        fun getProductsList(): ArrayList<Product>{
            val list = ArrayList<Product>()
            list.add(
                Product(
                    1,
                    "Afine",
                    "Fructe",
                    R.drawable.icon_afine,
                    10.0,
                    "caseloră",
                    null
                ))
            list.add(
                Product(
                    2,
                    "Zmeura",
                    "Fructe",
                    R.drawable.icon_zmeura,
                    12.0,
                    "caseloră",
                    null
                ))
            list.add(
                Product(
                    3,
                    "Rosii",
                    "Legume",
                    R.drawable.icon_rosii,
                    3.0,
                    "kg",
                    null
                ))
            list.add(
                Product(
                    4,
                    "Usturoi",
                    "Legume",
                    R.drawable.icon_usturoi,
                    2.5,
                    "kg",
                    null
                ))
            list.add(
                Product(
                    5,
                    "Ardei",
                    "Legume",
                    R.drawable.icon_ardei,
                    4.0,
                    "kg",
                    null
                ))
            list.add(
                Product(
                    6,
                    "Struguri",
                    "Legume",
                    R.drawable.icon_struguri,
                    5.0,
                    "kg",
                    null
                ))
            list.add(
                Product(
                    7,
                    "Cirese",
                    "Legume",
                    R.drawable.icon_cirese,
                    15.0,
                    "kg",
                    null
                ))
            list.add(
                Product(
                    8,
                    "Pepene verde",
                    "Legume",
                    R.drawable.icon_lubenita,
                    2.00,
                    "kg",
                    null
                ))
            list.add(
                Product(
                    9,
                    "Piersici",
                    "Legume",
                    R.drawable.icon_caise,
                    6.0,
                    "kg",
                    null
                ))
            list.add(
                Product(
                    10,
                    "Vinete",
                    "Legume",
                    R.drawable.icon_vinete,
                    6.0,
                    "kg",
                    null
                ))
            list.add(
                Product(
                    11,
                    "Morcovi",
                    "Legume",
                    R.drawable.icon_morcovi,
                    4.5,
                    "kg",
                    null
                ))
            list.add(
                Product(
                    12,
                    "Rosii",
                    "Legume",
                    R.drawable.icon_mere,
                    3.5,
                    "kg",
                    null
                ))
            return list
        }

        fun getUsersList(): ArrayList<User>{
            val list = ArrayList<User>()
            list.add(
                User(
                    1,
                    "Lucian",
                    "Negru",
                    "client",
                    "l.negru@gmail.com",
                    "password",
                    null,
                    R.drawable.poza_profil_1,
                    null,
                    null,
                ))
            list.add(
                User(
                    2,
                    "Elena",
                    "Preda",
                    "client",
                    "e.preda@gmail.com",
                    "password",
                    null,
                    R.drawable.poza_profil_3,
                    null,
                    null,
                ))
            list.add(
                User(
                    3,
                    "Ion",
                    "Vasile",
                    "comerciant",
                    "i.vasile@gmail.com",
                    "password",
                    null,
                    R.drawable.poza_profil_1,
                    null,
                    null,
                ))
            list.add(
                User(
                    4,
                    "Florin",
                    "Ionescu",
                    "comerciant",
                    "f.ionescu@gmail.com",
                    "password",
                    null,
                    R.drawable.poza_profil_2,
                    null,
                    null,
                ))
            list.add(
                User(
                    5,
                    "Marin",
                    "Dobrescu",
                    "comerciant",
                    "m.dobrescu@gmail.com",
                    "password",
                    null,
                    R.drawable.poza_profil_2,
                    null,
                    null,
                ))
            list.add(
                User(
                    6,
                    "Constantin",
                    "Stan",
                    "client",
                    "c.stan@gmail.com",
                    "password",
                    null,
                    R.drawable.poza_profil_1,
                    null,
                    null,
                ))
            return list
        }

        fun getSuggestionsAdminList(): ArrayList<SuggestionAdmin>{
            val list = ArrayList<SuggestionAdmin>()
            list.add(
                SuggestionAdmin(
                    1,
                    "Adaugare produse",
                    "Va rog adaugati și piersici în baza de date, vreau să comercializez.",
                    "Florin",
                    "Ionescu",
                    "26.06.2021"
                ))
            list.add(
                SuggestionAdmin(
                    2,
                    "Furt de cireșe",
                    "Marin Dobrescu nu mi-a calculat bine la cireșe",
                    "Constantin",
                    "Stan",
                    "30.06.2021"
                ))

            return list
        }

        fun getOrdersList(): ArrayList<Order>{
            val list = ArrayList<Order>()
            list.add(
                Order(
                    1,
                    "Marin Dobrescu",
                    "Constantin Stan",
                    getProductsList(),
                    "comandă trimisă",
                    36.0
                ))
            return list
        }

    }


}