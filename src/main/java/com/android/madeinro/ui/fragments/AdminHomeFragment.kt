package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentAdminHomeBinding
import com.android.madeinro.ui.adapters.SuggestionsListAdapter
import com.android.madeinro.ui.adapters.UserListAdminAdapter

class AdminHomeFragment : Fragment(), View.OnClickListener {

    private var _adminHomeBinding: FragmentAdminHomeBinding? = null
    private val adminHomeBinding get() = _adminHomeBinding!!
    private val suggestionsAdapter by lazy { SuggestionsListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _adminHomeBinding = FragmentAdminHomeBinding.inflate(inflater,container, false)

        setupRecyclerView()

        // BUTTON Log Out
        adminHomeBinding.logOutAdminButton.setOnClickListener {
            onClick(adminHomeBinding.logOutAdminButton)
        }

        // BUTTON Edit Products
        adminHomeBinding.crudProductsButton.setOnClickListener {
            onClick(adminHomeBinding.crudProductsButton)
        }

        // BUTTON Edit Users
        adminHomeBinding.crudUsersButton.setOnClickListener {
            onClick(adminHomeBinding.crudUsersButton)
        }

        return adminHomeBinding.root
    }

    override fun onClick(v: View) {
        if(v == adminHomeBinding.logOutAdminButton) {
            Navigation.findNavController(v).navigate(R.id.action_adminHomeFragment_to_loginFragment)
        } else if(v == adminHomeBinding.crudProductsButton){
            Navigation.findNavController(v).navigate(R.id.action_adminHomeFragment_to_productsFragment)
        } else if(v == adminHomeBinding.crudUsersButton) {
            Navigation.findNavController(v).navigate(R.id.action_adminHomeFragment_to_usersFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _adminHomeBinding = null
    }

    private fun setupRecyclerView(){
        adminHomeBinding.suggestionsAdminRecyclerView.adapter = suggestionsAdapter
        adminHomeBinding.suggestionsAdminRecyclerView.layoutManager =  LinearLayoutManager(requireContext())

    }
}