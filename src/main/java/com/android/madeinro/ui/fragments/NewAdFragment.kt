package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentNewAdBinding

class NewAdFragment : Fragment(), View.OnClickListener {

    private var _newAdBinging: FragmentNewAdBinding? = null
    private val newAdBinging get() = _newAdBinging!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _newAdBinging = FragmentNewAdBinding.inflate(inflater,container, false)

        // BUTTON Back
        newAdBinging.backNewAdButton.setOnClickListener {
            onClick(newAdBinging.backNewAdButton)
        }

        // BUTTON Add Add New Ad
        newAdBinging.addNewAdButton.setOnClickListener {
            onClick(newAdBinging.addNewAdButton)
        }

        // BUTTON Add New Location
        newAdBinging.addNewAdLocationButton.setOnClickListener {
            onClick(newAdBinging.addNewAdLocationButton)
        }

        // BUTTON New Product
        newAdBinging.addNewAdProductButton.setOnClickListener {
            onClick(newAdBinging.addNewAdProductButton)
        }
        return newAdBinging.root
    }

    override fun onClick(v: View) {
        if(v == newAdBinging.backNewAdButton) {
            Navigation.findNavController(v).navigate(R.id.action_newAdFragment_to_adsFragment)
        } else if(v == newAdBinging.addNewAdProductButton) {
            Navigation.findNavController(v).navigate(R.id.action_newAdFragment_to_newProductAdFragment)
        } else if(v == newAdBinging.addNewAdButton) {
            Navigation.findNavController(v).navigate(R.id.action_newAdFragment_to_adsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _newAdBinging = null
    }
}