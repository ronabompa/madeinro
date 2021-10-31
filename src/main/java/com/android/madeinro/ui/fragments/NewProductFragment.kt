package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentNewProductBinding

class NewProductFragment : Fragment(), View.OnClickListener {

    private var _newProductBinding: FragmentNewProductBinding? = null
    private val newProductBinding get() = _newProductBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _newProductBinding = FragmentNewProductBinding.inflate(inflater,container, false)

        // BUTTON Back
        newProductBinding.backNewProductAdminButton.setOnClickListener {
            onClick(newProductBinding.backNewProductAdminButton)
        }

        // BUTTON Add Product
        newProductBinding.addNewProductAdminButton.setOnClickListener {
            onClick(newProductBinding.addNewProductAdminButton)
        }

        return newProductBinding.root
    }

    override fun onClick(v: View) {
        if(v == newProductBinding.backNewProductAdminButton || v == newProductBinding.addNewProductAdminButton) {
            Navigation.findNavController(v).navigate(R.id.action_newProductFragment_to_productsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _newProductBinding = null
    }
}