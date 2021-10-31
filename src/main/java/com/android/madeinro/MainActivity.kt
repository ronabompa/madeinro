package com.android.madeinro

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.android.madeinro.services.PermissionsService
import com.google.firebase.firestore.FirebaseFirestore

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val db: FirebaseFirestore = FirebaseFirestore.getInstance()

        val navigationHost = supportFragmentManager.findFragmentById(R.id.navHostFragment) as NavHostFragment
        val navController = navigationHost.navController

        if(PermissionsService.checkLocationPermission(this)) {
            navController.navigate(R.id.action_permissionsFragment_to_mapsFragment)
        }

    }
}
