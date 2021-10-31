package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentNewProductAdBinding

class NewProductAdFragment : Fragment(), View.OnClickListener {

    private var _newProductAdBinding: FragmentNewProductAdBinding? = null
    private val newProductAdBinding get() = _newProductAdBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _newProductAdBinding = FragmentNewProductAdBinding.inflate(inflater,container, false)

        // BUTTON Back
        newProductAdBinding.backNewProductAdButton.setOnClickListener {
            onClick(newProductAdBinding.backNewProductAdButton)
        }

        // BUTTON Add New Product to Ad
        newProductAdBinding.newProductAdAddButton.setOnClickListener {
            onClick(newProductAdBinding.newProductAdAddButton)
        }

        return newProductAdBinding.root
    }

    override fun onClick(v: View) {
        if(v == newProductAdBinding.backNewProductAdButton || v == newProductAdBinding.newProductAdAddButton) {
            Navigation.findNavController(v).navigate(R.id.action_newProductAdFragment_to_newAdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _newProductAdBinding = null
    }
}