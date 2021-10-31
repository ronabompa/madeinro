package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentProfileBinding

class ProfileFragment : Fragment(), View.OnClickListener {
    
    private var _profileBinding: FragmentProfileBinding? = null
    private val profileBinding get() = _profileBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        _profileBinding = FragmentProfileBinding.inflate(inflater,container, false)

        // BUTTON Back
        profileBinding.backProfileButton.setOnClickListener {
            onClick(profileBinding.backProfileButton)
        }

        // BUTTON Login
        profileBinding.editProfileButton.setOnClickListener {
            onClick(profileBinding.editProfileButton)
        }

        return profileBinding.root
    }

    override fun onClick(v: View) {
        if(v == profileBinding.backProfileButton || v == profileBinding.editProfileButton) {
            Navigation.findNavController(v).navigate(R.id.action_profileFragment_to_customSettingsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _profileBinding = null
    }

}