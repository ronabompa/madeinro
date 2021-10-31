package com.android.madeinro.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.madeinro.databinding.ItemListSellerOrderBinding
import com.android.madeinro.entities.Order
import com.android.madeinro.repositories.DataExample

class OrdersUserAdapter : RecyclerView.Adapter<OrdersUserAdapter.OrderListViewHolder>() {

    private var suggestionsList = DataExample.getOrdersList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OrderListViewHolder {
        return OrderListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: OrderListViewHolder, position: Int) {
        val currentProduct = suggestionsList[position]
        holder.bind(currentProduct)
    }

    override fun getItemCount(): Int = suggestionsList.size

    class OrderListViewHolder (val binding: ItemListSellerOrderBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(order: Order) {
            binding.order = order
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): OrderListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListSellerOrderBinding.inflate(layoutInflater, parent, false)
                return OrderListViewHolder(binding)
            }
        }
    }
}
