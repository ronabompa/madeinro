package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentProductsBinding
import com.android.madeinro.databinding.FragmentShopppingCartBinding
import com.android.madeinro.entities.Product
import com.android.madeinro.repositories.DataExample
import com.android.madeinro.ui.adapters.ProductsAdminAdapter
import java.util.*
import kotlin.collections.ArrayList


class ShopppingCartFragment : Fragment(), View.OnClickListener {
    private var _shoppingCartBinding: FragmentShopppingCartBinding? = null
    private val shoppingCartBinding get() = _shoppingCartBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _shoppingCartBinding = FragmentShopppingCartBinding.inflate(inflater,container, false)

        //setupRecyclerView()
        //addDataSet()

        // BUTTON Back
        shoppingCartBinding.shoppingCartBackButton.setOnClickListener {
            onClick(shoppingCartBinding.shoppingCartBackButton)
        }

        // BUTTON Add Product
        shoppingCartBinding.shoppingCartSendButton.setOnClickListener {
            //send the list
            //onClick(shoppingCartBinding.newProductAdminFloatingActionButton)
        }

        return shoppingCartBinding.root
    }

    override fun onClick(v: View) {
        if(v == shoppingCartBinding.shoppingCartBackButton) {
            Navigation.findNavController(v).navigate(R.id.action_shopppingCartFragment_to_mapsUserFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _shoppingCartBinding = null
    }

    private fun addDataSet(){
        val data = DataExample.getProductsList()
    }

}