package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentSuggestionAdminBinding

class SuggestionAdminFragment : Fragment(), View.OnClickListener {

    private var _suggestionAdminBinding: FragmentSuggestionAdminBinding? = null
    private val suggestionAdminBinding get() = _suggestionAdminBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _suggestionAdminBinding = FragmentSuggestionAdminBinding.inflate(inflater,container, false)

        // BUTTON Back
        suggestionAdminBinding.backSuggestionAdminButton.setOnClickListener {
            onClick(suggestionAdminBinding.backSuggestionAdminButton)
        }

        // BUTTON Send
        suggestionAdminBinding.sendSuggestionAdminButton.setOnClickListener {
            onClick(suggestionAdminBinding.sendSuggestionAdminButton)
        }

        return suggestionAdminBinding.root
    }

    override fun onClick(v: View) {
        if(v == suggestionAdminBinding.backSuggestionAdminButton || v == suggestionAdminBinding.sendSuggestionAdminButton) {
            Navigation.findNavController(v).navigate(R.id.action_suggestionAdminFragment_to_customSettingsFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _suggestionAdminBinding = null
    }
}