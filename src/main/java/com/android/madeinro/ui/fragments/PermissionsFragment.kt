package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.android.madeinro.R
import com.android.madeinro.services.PermissionsService.checkLocationPermission
import com.android.madeinro.services.PermissionsService.requestLocationPermission
import com.android.madeinro.databinding.FragmentPermissionsBinding
import com.vmadalin.easypermissions.EasyPermissions
import com.vmadalin.easypermissions.dialogs.SettingsDialog

class PermissionsFragment : Fragment(), EasyPermissions.PermissionCallbacks {

    private var _fragmentPermissionsBinding: FragmentPermissionsBinding? = null
    private val fragmentPermissionsBinding get() = _fragmentPermissionsBinding!!

     override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // Inflate the layout for this fragment
         _fragmentPermissionsBinding = FragmentPermissionsBinding.inflate(inflater,container, false)

         // daca avem permisiunea, dorim sa navigam catre maps Fragment, daca nu sa o solicitam
         fragmentPermissionsBinding.continuaButton.setOnClickListener {
             if(checkLocationPermission(requireContext()))
             {
                 findNavController().navigate(R.id.action_permissionsFragment_to_mapsFragment)
             } else {
                 requestLocationPermission(this)
             }
         }
         return fragmentPermissionsBinding.root
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this)
    }

    override fun onPermissionsDenied(requestCode: Int, perms: List<String>) {
       if(EasyPermissions.somePermissionPermanentlyDenied(this, perms)) {
           SettingsDialog.Builder(requireActivity()).build().show()
       } else {
           requestLocationPermission(this)
       }
    }

    override fun onPermissionsGranted(requestCode: Int, perms: List<String>) {
        findNavController().navigate(R.id.action_permissionsFragment_to_mapsFragment)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _fragmentPermissionsBinding = null
    }

}

