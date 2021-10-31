package com.android.madeinro.ui.fragments

import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import com.android.madeinro.R

class SettingsFragment : PreferenceFragmentCompat() {

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        setPreferencesFromResource(R.xml.root_preferences, rootKey)
    }
}