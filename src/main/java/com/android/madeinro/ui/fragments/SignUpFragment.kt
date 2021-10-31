package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.Navigation
import com.android.madeinro.R
import com.android.madeinro.databinding.FragmentSignUpBinding
import com.android.madeinro.services.SignUpService

class SignUpFragment : Fragment(), View.OnClickListener {

    private var _signUpBinding: FragmentSignUpBinding? = null
    private val signUpBinding get() = _signUpBinding!!
    private val signUpService: SignUpService = SignUpService()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _signUpBinding = FragmentSignUpBinding.inflate(inflater,container, false)

        // BUTTON sign up
        signUpBinding.signUpButton.setOnClickListener{
            val resultValidateEmail = signUpService.validateEmail(signUpBinding.emailSignUpEditText.text.toString())
            val resultValidatePassword = signUpService.validatePassword(signUpBinding.passwordSignUpEditText.text.toString(),signUpBinding.passwordCheckSignUpEditText.text.toString())

            if(resultValidateEmail.equals("Success")) {
                if (resultValidatePassword.equals("Success")) {
                        // register
                } else {
                    Toast.makeText(context, resultValidatePassword, Toast.LENGTH_SHORT).show()
                }
                Toast.makeText(context,resultValidateEmail, Toast.LENGTH_SHORT).show()
            }
        }

        signUpBinding.backSignUpButton.setOnClickListener{
            onClick(signUpBinding.backSignUpButton)
        }
        return signUpBinding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _signUpBinding = null
    }

    override fun onClick(v: View) {
        Navigation.findNavController(v).navigate(R.id.action_signUpFragment_to_loginFragment)
    }

}