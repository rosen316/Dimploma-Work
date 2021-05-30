package com.rosen.homecontrolapp.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.Preference
import androidx.preference.PreferenceManager
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.constant.Constant
import com.rosen.homecontrolapp.constant.devices
import com.rosen.homecontrolapp.fragment.ConfigurationFragment
import com.rosen.homecontrolapp.constant.espConnector
import com.rosen.homecontrolapp.model.Device
import com.rosen.homecontrolapp.storage.Preferences

class ConfigurationActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    var device: Device? = null
    var deviceId: Int = 0


    override fun onCreate(savedInstanceState: Bundle?) {

        deviceId = intent.getIntExtra("Device Id", 1)
        device = devices.find { it.id.equals(deviceId) }!!

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cofiguration)

        if (supportActionBar != null) {
            with(supportActionBar!!) {
                setDisplayHomeAsUpEnabled(true)
                setDisplayShowHomeEnabled(true)
            }
        }

        supportFragmentManager
                .beginTransaction()
                .replace(
                        R.id.fragment_container,
                        ConfigurationFragment()
                )
                .commit()

        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        finish()
    }

    override fun onStop() {
        super.onStop()
        finish()
    }

    override fun onDestroy() {
        super.onDestroy()
        PreferenceManager.getDefaultSharedPreferences(this)
                .unregisterOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        val prefs = Preferences(this)
        when (key) {
            "ap_ssid" -> {
                espConnector.sendConfigChangeRequest(ssidAp = sharedPreferences.getString(key, Constant.AP_SSID_STORAGE.default))
                device!!.ap_ssid = sharedPreferences.getString(key, Constant.AP_SSID_STORAGE.default)!!

            }
            "ap_wifi_password" -> {
                espConnector.sendConfigChangeRequest(passwordAp = sharedPreferences.getString(key, Constant.AP_WIFI_PASSWORD_STORAGE.default))
                device!!.ap_password = sharedPreferences.getString(key, Constant.AP_WIFI_PASSWORD_STORAGE.default)!!
            }
            "ap_port" -> {
                espConnector.sendConfigChangeRequest(portAp = sharedPreferences.getString(key, Constant.AP_PORT_STORAGE.default))
                device!!.ap_port = sharedPreferences.getString(key, Constant.AP_PORT_STORAGE.default)!!
            }
            "ap_ip_address" -> { //DOESNT SENT REQUEST CURRENTLY!!!!
                //espConnector.sendConfigChangeRequest(ipAdressIp = sharedPreferences.getString(key, Constant.AP_SSID_STORAGE.default))
                device!!.ap_ip = sharedPreferences.getString(key, Constant.STA_IP_ADDRESS_STORAGE.default)!!
            }

            "sta_ssid" -> {
                espConnector.sendConfigChangeRequest(ssidSta = sharedPreferences.getString(key, Constant.STA_SSID_STORAGE.default))
                device!!.sta_ssid = sharedPreferences.getString(key, Constant.STA_SSID_STORAGE.default)!!
            }
            "sta_wifi_password" -> {
                espConnector.sendConfigChangeRequest(passwordSta = sharedPreferences.getString(key, Constant.STA_WIFI_PASSWORD_STORAGE.default))
                device!!.sta_password = sharedPreferences.getString(key, Constant.STA_WIFI_PASSWORD_STORAGE.default)!!
            }
            "sta_port" -> {
                espConnector.sendConfigChangeRequest(portSta = sharedPreferences.getString(key, Constant.STA_PORT_STORAGE.default))
                device!!.sta_port = sharedPreferences.getString(key, Constant.STA_WIFI_PASSWORD_STORAGE.default)!!
            }
            else -> return
        }
        prefs.saveDevice(device)

    }
}