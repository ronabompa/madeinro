package com.android.madeinro.services

import android.util.Patterns
import android.util.Patterns.EMAIL_ADDRESS

class SignUpService() {

    fun validateEmail(email: String): String {
        if(email.equals("")){
            return "Va rugam, introduceti emailul!"
        } else if(!EMAIL_ADDRESS.matcher(email).matches()){
            return "Email invalid"
        } else {
            return "Success"
        }
    }

    // pentru logica din ui, daca primim succes, inregistram. Daca nu, afisam prin Toast
    fun validatePassword(password: String, passwordCheck: String): String {
        if(password.equals("")){
            return "Va rugam, introduceti parola!"
        } else if(password.length < 8){
            return "Parola trebuia sa contina min 8 caractere"
        } else if(password.equals(passwordCheck)) {
            return "Success"
        } else {
            return "Parolele difera"
        }
    }




}