package com.rosen.homecontrol.storage;

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager
import com.rosen.homecontrol.constant.Constant
import com.rosen.homecontrol.model.Storage

class Preferences(ctx: Context) {

    private val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)

    private fun getSharedPreferencesValue(storage: Storage): String {
        val (key, default) = storage
        val value = preferences.getString(key, default)!!
        return if (value.isBlank()) default else value
    }

    fun getIpAddressSta(): String = getSharedPreferencesValue(Constant.STA_IP_ADDRESS_STORAGE)
    fun getPortSta(): String = getSharedPreferencesValue(Constant.STA_PORT_STORAGE)
    fun getPasswordSta(): String = getSharedPreferencesValue(Constant.STA_WIFI_PASSWORD_STORAGE)
    fun getSsidSta(): String = getSharedPreferencesValue(Constant.STA_SSID_STORAGE)
    fun getIpAddressAp(): String = getSharedPreferencesValue(Constant.AP_IP_ADDRESS_STORAGE)
    fun getPortAp(): String = getSharedPreferencesValue(Constant.AP_PORT_STORAGE)
    fun getPasswordAp(): String = getSharedPreferencesValue(Constant.AP_WIFI_PASSWORD_STORAGE)
    fun getSsidAp(): String = getSharedPreferencesValue(Constant.AP_SSID_STORAGE)


}
