package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentLoginBinding
import com.android.madeinro.databinding.FragmentMapsBinding

class LoginFragment : Fragment(), View.OnClickListener {

    private var _loginBinding: FragmentLoginBinding? = null
    private val loginBinding get() = _loginBinding!!

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        _loginBinding = FragmentLoginBinding.inflate(inflater,container, false)

        // BUTTON Back
        loginBinding.backLoginButton.setOnClickListener {
            onClick(loginBinding.backLoginButton)
        }

        // BUTTON Login
        loginBinding.loginButton.setOnClickListener {
            onClick(loginBinding.loginButton)
        }

        // BUTTON Sign Up
        loginBinding.signUpFromLoginButton.setOnClickListener {
            onClick(loginBinding.signUpFromLoginButton)
        }
        return loginBinding.root
    }

    override fun onClick(v: View) {
        if(v == loginBinding.backLoginButton) {
            Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_mapsFragment)
        } else if(v == loginBinding.signUpFromLoginButton){
            Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_signUpFragment)
        } else if(v == loginBinding.loginButton) {
            if(loginBinding.emailLoginEditText.text.toString().equals("u") && loginBinding.passwordLoginEditText.text.toString().equals("u")) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_mapsUserFragment)
            } else if(loginBinding.emailLoginEditText.text.toString().equals("a") && loginBinding.passwordLoginEditText.text.toString().equals("a")) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_adminHomeFragment)
            } else if(loginBinding.emailLoginEditText.text.toString().equals("c") && loginBinding.passwordLoginEditText.text.toString().equals("c")) {
                Navigation.findNavController(v).navigate(R.id.action_loginFragment_to_sellerHomeFragment)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _loginBinding = null
    }
}