package com.android.madeinro.ui.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.madeinro.databinding.ItemListAdminProductsBinding
import com.android.madeinro.databinding.ItemListUsersBinding
import com.android.madeinro.entities.Product
import com.android.madeinro.entities.User
import com.android.madeinro.repositories.DataExample
import com.squareup.picasso.Picasso

class UserListAdminAdapter(var usersList: ArrayList<User>): RecyclerView.Adapter<UserListAdminAdapter.UserListAdminViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserListAdminViewHolder {
        return UserListAdminViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: UserListAdminViewHolder, position: Int) {
        val currentProduct = usersList[position]
        holder.bind(currentProduct)
        Picasso.with(holder.binding.userAdminImage.context).load(currentProduct.photo).into(holder.binding.userAdminImage)
    }

    override fun getItemCount(): Int = usersList.size

    fun filterList(filterList: ArrayList<User>) {
        usersList = filterList
        this.notifyDataSetChanged()
    }

    class UserListAdminViewHolder(val binding: ItemListUsersBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(user: User) {
            binding.user = user
            binding.executePendingBindings()

        }

        companion object {
            fun from(parent: ViewGroup): UserListAdminViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListUsersBinding.inflate(layoutInflater, parent, false)
                return UserListAdminViewHolder(binding)
            }
        }
    }
}
