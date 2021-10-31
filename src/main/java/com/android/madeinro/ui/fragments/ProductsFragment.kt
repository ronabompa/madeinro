package com.android.madeinro.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentProductsBinding
import com.android.madeinro.entities.Product
import com.android.madeinro.repositories.DataExample
import com.android.madeinro.ui.adapters.ProductsAdminAdapter
import java.util.*
import kotlin.collections.ArrayList

class ProductsFragment : Fragment(), View.OnClickListener {

    private var _productsBinding: FragmentProductsBinding? = null
    private val productsBinding get() = _productsBinding!!
    private val productsAdapter by lazy { ProductsAdminAdapter(DataExample.getProductsList()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _productsBinding = FragmentProductsBinding.inflate(inflater,container, false)

        setupRecyclerView()
        addDataSet()

        // SEARCH VIEW
        productsBinding.productsAdminSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })

        // BUTTON Back
        productsBinding.backProductsAdminButton.setOnClickListener {
            onClick(productsBinding.backProductsAdminButton)
        }

        // BUTTON Add Product
        productsBinding.newProductAdminFloatingActionButton.setOnClickListener {
            onClick(productsBinding.newProductAdminFloatingActionButton)
        }

        return productsBinding.root
    }

    override fun onClick(v: View) {
        if(v == productsBinding.backProductsAdminButton) {
            Navigation.findNavController(v).navigate(R.id.action_productsFragment_to_adminHomeFragment)
        } else if(v == productsBinding.newProductAdminFloatingActionButton){
            Navigation.findNavController(v).navigate(R.id.action_productsFragment_to_newProductFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _productsBinding = null
    }

    private fun addDataSet(){
        val data = DataExample.getProductsList()
    }

    private fun setupRecyclerView(){
        productsBinding.productsAdminRecyclerView.adapter = productsAdapter
        productsBinding.productsAdminRecyclerView.layoutManager =  LinearLayoutManager(requireContext())

    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<Product> = ArrayList()

        for (item in DataExample.getProductsList()) {
            if (item.name.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(context, "Nu s-a găsit în listă", Toast.LENGTH_SHORT).show()
        } else {
            productsAdapter.filterList(filteredlist)
        }
    }
}
