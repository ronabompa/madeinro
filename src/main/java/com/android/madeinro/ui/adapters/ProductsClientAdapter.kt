package com.android.madeinro.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.android.madeinro.databinding.ItemListClientProductsBinding
import com.android.madeinro.entities.Product
import com.android.madeinro.repositories.DataExample
import com.squareup.picasso.Picasso

class ProductsClientAdapter: RecyclerView.Adapter<ProductsClientAdapter.ProductsClientViewHolder>() {

    private var productsList = DataExample.getProductsList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsClientViewHolder {
        return ProductsClientViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductsClientViewHolder, position: Int) {
        val currentProduct = productsList[position]
        holder.bind(currentProduct)
        Picasso.with(holder.binding.productClientImage.context).load(currentProduct.icon)
            .into(holder.binding.productClientImage)

    }

    override fun getItemCount(): Int = productsList.size


    class ProductsClientViewHolder(val binding: ItemListClientProductsBinding): RecyclerView.ViewHolder(binding.root) {

        init{
            itemView.setOnClickListener{v: View ->
                val position: Int = bindingAdapterPosition
                Toast.makeText(itemView.context, "You clicked on item # ${position +1}", Toast.LENGTH_SHORT).show()
            }
        }

        fun bind(product: Product){
            binding.product = product
            binding.executePendingBindings()
        }

        companion object{
            fun from(parent: ViewGroup): ProductsClientViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListClientProductsBinding.inflate(layoutInflater, parent, false)
                return  ProductsClientViewHolder(binding)
            }
        }
    }

}