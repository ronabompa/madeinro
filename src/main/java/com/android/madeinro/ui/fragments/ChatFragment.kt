package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentChatBinding

class ChatFragment : Fragment(), View.OnClickListener {

    private var _chatBinding: FragmentChatBinding? = null
    private val chatBinding get() = _chatBinding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _chatBinding = FragmentChatBinding.inflate(inflater,container, false)

        // BUTTON Back
        chatBinding.chatBackButton.setOnClickListener {
            onClick(chatBinding.chatBackButton)
        }

        // BUTTON Newu Conversation
        chatBinding.newChatFloatingActionButton.setOnClickListener {
            onClick(chatBinding.newChatFloatingActionButton)
        }

        return chatBinding.root
    }

    override fun onClick(v: View) {
        if(v == chatBinding.chatBackButton) {
            Navigation.findNavController(v).navigate(R.id.action_chatFragment_to_mapsUserFragment)
        } else if(v == chatBinding.newChatFloatingActionButton){
            Navigation.findNavController(v).navigate(R.id.action_chatFragment_to_individualConversationFragment)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _chatBinding = null
    }

}