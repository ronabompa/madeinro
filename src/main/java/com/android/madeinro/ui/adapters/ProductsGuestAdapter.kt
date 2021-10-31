package com.android.madeinro.ui.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.android.madeinro.databinding.ItemListGuestProdutsBinding
import com.android.madeinro.entities.Product
import com.android.madeinro.repositories.DataExample
import com.squareup.picasso.Picasso


class ProductsGuestAdapter: RecyclerView.Adapter<ProductsGuestAdapter.ProductsGuestViewHolder>() {

    private var productsList = DataExample.getProductsList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ProductsGuestViewHolder {
        return ProductsGuestViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ProductsGuestViewHolder, position: Int) {
        val currentProduct = productsList[position]
        holder.bind(currentProduct)
        Picasso.with(holder.binding.productUserImage.context).load(currentProduct.icon)
            .into(holder.binding.productUserImage)

    }

    override fun getItemCount(): Int = productsList.size

    class ProductsGuestViewHolder(val binding: ItemListGuestProdutsBinding): RecyclerView.ViewHolder(binding.root) {

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
            fun from(parent: ViewGroup): ProductsGuestViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListGuestProdutsBinding.inflate(layoutInflater, parent, false)
                return  ProductsGuestViewHolder(binding)
            }
        }
    }

}