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
import com.android.madeinro.databinding.FragmentSellerHomeBinding
import com.android.madeinro.ui.adapters.OrdersUserAdapter
import com.android.madeinro.ui.adapters.SuggestionsListAdapter

class SellerHomeFragment : Fragment(), View.OnClickListener {

    private var _sellerHomeBinding: FragmentSellerHomeBinding? = null
    private val sellerHomeBinding get() = _sellerHomeBinding!!
    private val sellerAdapter by lazy { OrdersUserAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _sellerHomeBinding = FragmentSellerHomeBinding.inflate(inflater,container, false)

       setupRecyclerView()

        // BUTTON Log Out
        sellerHomeBinding.logOutSellerButton.setOnClickListener {
            onClick(sellerHomeBinding.logOutSellerButton)
        }

        // BUTTON history ads
        sellerHomeBinding.istoricSolicitariButton.setOnClickListener {
            onClick(sellerHomeBinding.istoricSolicitariButton)
        }

        // BUTTON ADD ads
        sellerHomeBinding.gestionareAnunturiButton.setOnClickListener {
            onClick(sellerHomeBinding.gestionareAnunturiButton)
        }

        return sellerHomeBinding.root
    }

    override fun onClick(v: View) {
        if(v == sellerHomeBinding.logOutSellerButton) {
            Navigation.findNavController(v).navigate(R.id.action_sellerHomeFragment_to_loginFragment)
        } else if(v == sellerHomeBinding.istoricSolicitariButton){
            Navigation.findNavController(v).navigate(R.id.action_sellerHomeFragment_to_requestsFragment)
        } else if(v == sellerHomeBinding.gestionareAnunturiButton) {
            Navigation.findNavController(v).navigate(R.id.action_sellerHomeFragment_to_adsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _sellerHomeBinding = null
    }

    private fun setupRecyclerView(){
        sellerHomeBinding.solicitariNoiRecyclerView.adapter = sellerAdapter
        sellerHomeBinding.solicitariNoiRecyclerView.layoutManager =  LinearLayoutManager(requireContext())

    }
}