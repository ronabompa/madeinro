package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentAdsBinding

class AdsFragment : Fragment(), View.OnClickListener {

    private var _adsBinging: FragmentAdsBinding? = null
    private val adsBinging get() = _adsBinging!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _adsBinging = FragmentAdsBinding.inflate(inflater,container, false)

        // BUTTON Back
        adsBinging.backAdsUserButton.setOnClickListener {
            onClick(adsBinging.backAdsUserButton)
        }

        // BUTTON New Ad
        adsBinging.newAdUserFloatingActionButton.setOnClickListener {
            onClick(adsBinging.newAdUserFloatingActionButton)
        }

        return adsBinging.root
    }

    override fun onClick(v: View) {
        if(v == adsBinging.backAdsUserButton) {
            Navigation.findNavController(v).navigate(R.id.action_adsFragment_to_sellerHomeFragment)
        } else if(v == adsBinging.newAdUserFloatingActionButton) {
            Navigation.findNavController(v).navigate(R.id.action_adsFragment_to_newAdFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _adsBinging = null
    }
}