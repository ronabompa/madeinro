package com.android.madeinro.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.madeinro.databinding.ItemListAdminProductsBinding
import com.android.madeinro.entities.Product
import com.squareup.picasso.Picasso
import java.util.*
import kotlin.collections.ArrayList


class ProductsAdminAdapter(var productsList: ArrayList<Product>):RecyclerView.Adapter<ProductsAdminAdapter.ProductsAdminViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsAdminViewHolder {
        return ProductsAdminViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductsAdminViewHolder, position: Int) {
        val currentProduct = productsList[position]
        holder.bind(currentProduct)
        Picasso.with(holder.binding.productAdminImage.context).load(currentProduct.icon).into(holder.binding.productAdminImage)
    }

    override fun getItemCount(): Int = productsList.size

    fun filterList(filterList: ArrayList<Product>) {
        productsList = filterList
        this.notifyDataSetChanged()
    }

    class ProductsAdminViewHolder(val binding: ItemListAdminProductsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(product: Product) {
            binding.product = product
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): ProductsAdminViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListAdminProductsBinding.inflate(layoutInflater, parent, false)
                return ProductsAdminViewHolder(binding)
            }
        }
    }
}
