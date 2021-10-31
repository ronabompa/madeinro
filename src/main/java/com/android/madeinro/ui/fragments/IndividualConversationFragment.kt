package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentIndividualConversationBinding

class IndividualConversationFragment : Fragment(), View.OnClickListener {

    private var _individualConversationBinding: FragmentIndividualConversationBinding? = null
    private val individualConversationBinding get() = _individualConversationBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _individualConversationBinding = FragmentIndividualConversationBinding.inflate(inflater,container, false)

        // BUTTON Back
        individualConversationBinding.individualConversationBackButton.setOnClickListener {
            onClick(individualConversationBinding.individualConversationBackButton)
        }

        return individualConversationBinding.root
    }

    override fun onClick(v: View) {
        if(v == individualConversationBinding.individualConversationBackButton) {
            Navigation.findNavController(v).navigate(R.id.action_individualConversationFragment_to_chatFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _individualConversationBinding = null
    }
}