package com.android.madeinro.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.madeinro.databinding.ItemListSuggestionsBinding
import com.android.madeinro.databinding.ItemListUsersBinding
import com.android.madeinro.entities.SuggestionAdmin
import com.android.madeinro.entities.User
import com.android.madeinro.repositories.DataExample
import com.squareup.picasso.Picasso

class SuggestionsListAdapter : RecyclerView.Adapter<SuggestionsListAdapter.SuggestionListViewHolder>() {

    private var suggestionsList = DataExample.getSuggestionsAdminList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SuggestionListViewHolder {
        return SuggestionListViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: SuggestionListViewHolder, position: Int) {
        val currentProduct = suggestionsList[position]
        holder.bind(currentProduct)
    }

    override fun getItemCount(): Int = suggestionsList.size
    

    class SuggestionListViewHolder (val binding: ItemListSuggestionsBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bind(suggestionAdmin: SuggestionAdmin) {
            binding.suggestion = suggestionAdmin
            binding.executePendingBindings()
        }

        companion object {
            fun from(parent: ViewGroup): SuggestionListViewHolder {
                val layoutInflater = LayoutInflater.from(parent.context)
                val binding = ItemListSuggestionsBinding.inflate(layoutInflater, parent, false)
                return SuggestionListViewHolder(binding)
            }
        }
    }

}
