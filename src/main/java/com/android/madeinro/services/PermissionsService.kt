package com.android.madeinro.services

import android.content.Context
import android.Manifest
import androidx.fragment.app.Fragment
import com.android.madeinro.Constants.REQUEST_CODE_PERMISSION_LOCATION
import com.vmadalin.easypermissions.EasyPermissions

object PermissionsService {

    fun checkLocationPermission(context: Context) =
        EasyPermissions.hasPermissions(
            context,
            Manifest.permission.ACCESS_FINE_LOCATION
        )

    fun requestLocationPermission(fragment: Fragment) {
        EasyPermissions.requestPermissions(
            fragment,
            "Solicitam accesarea locatiei dvs. pentru bunul mers al aplicatiei. Daca nu ne acordati accesul, aplicatia nu va functiona.",
            REQUEST_CODE_PERMISSION_LOCATION,
            Manifest.permission.ACCESS_FINE_LOCATION
        )
    }

}