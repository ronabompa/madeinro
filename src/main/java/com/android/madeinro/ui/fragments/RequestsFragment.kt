package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentRequestsBinding
import com.android.madeinro.databinding.FragmentUsersBinding
import com.android.madeinro.entities.User
import com.android.madeinro.repositories.DataExample
import com.android.madeinro.ui.adapters.UserListAdminAdapter
import java.util.*
import kotlin.collections.ArrayList

class RequestsFragment : Fragment(), View.OnClickListener {
    private var _requestsBinging: FragmentRequestsBinding? = null
    private val requestsBinging get() = _requestsBinging!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _requestsBinging = FragmentRequestsBinding.inflate(inflater,container, false)

        // BUTTON Back
        requestsBinging.reuqestsSellerBackButton.setOnClickListener {
            onClick(requestsBinging.reuqestsSellerBackButton)
        }

        return requestsBinging.root
    }

    override fun onClick(v: View) {
        if(v == requestsBinging.reuqestsSellerBackButton) {
            Navigation.findNavController(v).navigate(R.id.action_requestsFragment_to_sellerHomeFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _requestsBinging = null
    }

}