package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentCustomSettingsBinding

class CustomSettingsFragment : Fragment(), View.OnClickListener {

    private var _customSettingsBinging: FragmentCustomSettingsBinding? = null
    private val customSettingsBinging get() = _customSettingsBinging!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _customSettingsBinging = FragmentCustomSettingsBinding.inflate(inflater,container, false)

        // BUTTON Back
        customSettingsBinging.customSettingsBackButton.setOnClickListener {
            onClick(customSettingsBinging.customSettingsBackButton)
        }

        // BUTTON Edit Profile
        customSettingsBinging.editProfileSettingsTextView.setOnClickListener {
            onClick(customSettingsBinging.editProfileSettingsTextView)
        }

        // BUTTON Delete Profile
        customSettingsBinging.deleteProfileSettingsTextView.setOnClickListener {
            onClick(customSettingsBinging.deleteProfileSettingsTextView)
        }

        // BUTTON Suggestions to Admin
        customSettingsBinging.suggestionsSettingsTextView.setOnClickListener {
            onClick(customSettingsBinging.suggestionsSettingsTextView)
        }

        // BUTTON Log Out Account
        customSettingsBinging.logOutSettingsTextView.setOnClickListener {
            onClick(customSettingsBinging.logOutSettingsTextView)
        }


        return customSettingsBinging.root
    }

    override fun onClick(v: View) {
        if(v == customSettingsBinging.customSettingsBackButton) {
            Navigation.findNavController(v).navigate(R.id.action_customSettingsFragment_to_mapsUserFragment)
        } else if(v == customSettingsBinging.editProfileSettingsTextView) {
            Navigation.findNavController(v).navigate(R.id.action_customSettingsFragment_to_profileFragment)
        } else if(v == customSettingsBinging.suggestionsSettingsTextView) {
            Navigation.findNavController(v).navigate(R.id.action_customSettingsFragment_to_suggestionAdminFragment)
        } else if(v == customSettingsBinging.logOutSettingsTextView) {
            Navigation.findNavController(v).navigate(R.id.action_customSettingsFragment_to_loginFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _customSettingsBinging = null
    }
}