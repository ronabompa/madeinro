package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentUsersBinding
import com.android.madeinro.entities.Product
import com.android.madeinro.entities.User
import com.android.madeinro.repositories.DataExample
import com.android.madeinro.ui.adapters.ProductsGuestAdapter
import com.android.madeinro.ui.adapters.UserListAdminAdapter
import java.util.*
import kotlin.collections.ArrayList

class UsersFragment : Fragment(), View.OnClickListener  {

    private var _usersBinging: FragmentUsersBinding? = null
    private val usersBinging get() = _usersBinging!!
    private val usersAdapter by lazy { UserListAdminAdapter(DataExample.getUsersList()) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _usersBinging = FragmentUsersBinding.inflate(inflater,container, false)
        setupRecyclerView()

        // SEARCH VIEW
        usersBinging.usersAdminSearchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filter(newText)
                return false
            }
        })

        // BUTTON Back
        usersBinging.backUsersAdminButton.setOnClickListener {
            onClick(usersBinging.backUsersAdminButton)
        }

        return usersBinging.root
    }

    override fun onClick(v: View) {
        if(v == usersBinging.backUsersAdminButton) {
            Navigation.findNavController(v).navigate(R.id.action_usersFragment_to_adminHomeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _usersBinging = null
    }

    private fun filter(text: String) {
        val filteredlist: ArrayList<User> = ArrayList()

        for (item in DataExample.getUsersList()) {
            if (item.lastName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault())) ||
                item.firstName.lowercase(Locale.getDefault()).contains(text.lowercase(Locale.getDefault()))) {
                filteredlist.add(item)
            }
        }
        if (filteredlist.isEmpty()) {
            Toast.makeText(context, "Nu s-a găsit în listă", Toast.LENGTH_SHORT).show()
        } else {
            usersAdapter.filterList(filteredlist)
        }
    }

    private fun setupRecyclerView(){
        usersBinging.usersAdminRecyclerView.adapter = usersAdapter
        usersBinging.usersAdminRecyclerView.layoutManager =  LinearLayoutManager(requireContext())
    }

}