package com.rosen.homecontrol.fragment;

import android.content.SharedPreferences;
import android.os.Bundle;
import androidx.preference.EditTextPreference;
import androidx.preference.ListPreference;
import androidx.preference.Preference;
import androidx.preference.PreferenceFragmentCompat;

import com.rosen.homecontrol.R;
import com.rosen.homecontrol.constant.Constant;
import com.rosen.homecontrol.model.Storage;

class ConfigurationFragment : PreferenceFragmentCompat(),
        SharedPreferences.OnSharedPreferenceChangeListener {


        override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.preferences)

        setPreferenceOnChange(Constant.AP_IP_ADDRESS_STORAGE)
        setPreferenceOnChange(Constant.AP_PORT_STORAGE)
        setPreferenceOnChange(Constant.AP_WIFI_PASSWORD_STORAGE)
        setPreferenceOnChange(Constant.AP_SSID_STORAGE)
        setPreferenceOnChange(Constant.STA_IP_ADDRESS_STORAGE)
        setPreferenceOnChange(Constant.STA_PORT_STORAGE)
        setPreferenceOnChange(Constant.STA_WIFI_PASSWORD_STORAGE)
        setPreferenceOnChange(Constant.STA_SSID_STORAGE)

        }

        private fun setPreferenceOnChange(storage: Storage) {
                val (key, default) = storage
                val preference: EditTextPreference? = findPreference(key)
                preference?.summaryProvider = Preference.SummaryProvider<EditTextPreference> { pref ->
                        val value = pref.text
                        if (value.isNullOrBlank()) default else value
                }
        }

        override fun onResume() {
                super.onResume()
                preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
        }

        override fun onPause() {
                super.onPause()
                preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
        }

        override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences?, key: String?) {
        }

 }