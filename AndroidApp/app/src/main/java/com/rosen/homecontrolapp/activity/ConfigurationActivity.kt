package com.rosen.homecontrolapp.activity

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.constant.Constant
import com.rosen.homecontrolapp.fragment.ConfigurationFragment

class ConfigurationActivity : AppCompatActivity(), SharedPreferences.OnSharedPreferenceChangeListener {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cofiguration)
        //setSupportActionBar(findViewById(R.id.toolbar_configuration))

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
    }

    override fun onResume() {
        super.onResume()
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        PreferenceManager.getDefaultSharedPreferences(this)
                .registerOnSharedPreferenceChangeListener(this)
    }

    override fun onSharedPreferenceChanged(sharedPreferences: SharedPreferences, key: String) {
        println("KEY: $key")
        println("KEY: $key")
        println("KEY: $key")
        println("KEY: $key")
        println("KEY: $key")

        when(key){
            "ap_ssid" ->  espConnector.sendConfigChangeRequest(ssidAp = sharedPreferences.getString(key, Constant.AP_SSID_STORAGE.default))
            "ap_wifi_password" -> espConnector.sendConfigChangeRequest(passwordAp = sharedPreferences.getString(key, Constant.AP_WIFI_PASSWORD_STORAGE.default))
            "ap_port" -> espConnector.sendConfigChangeRequest(portAp = sharedPreferences.getString(key, Constant.AP_PORT_STORAGE.default))
            "sta_ssid" -> espConnector.sendConfigChangeRequest(ssidSta = sharedPreferences.getString(key, Constant.STA_SSID_STORAGE.default))
            "sta_wifi_password" -> espConnector.sendConfigChangeRequest(passwordSta = sharedPreferences.getString(key, Constant.STA_WIFI_PASSWORD_STORAGE.default))
            "sta_port" -> espConnector.sendConfigChangeRequest(portSta = sharedPreferences.getString(key, Constant.STA_PORT_STORAGE.default))
        }
    }

}

//    override fun onKeyDown(keyCode: Int, event: KeyEvent): Boolean {
//        if (keyCode == KeyEvent.KEYCODE_BACK) {
//            finish()
//        }
//        return true
//    }

//    override fun onCreateOptionsMenu(menu: Menu): Boolean {
//        val inflater = menuInflater
//        inflater.inflate(R.menu.toolbar_main_menu, menu)
//        return true
//    }

//    override fun onOptionsItemSelected(item: MenuItem): Boolean =
//            when (item.itemId) {
//                android.R.id.home -> {
//                    finish()
//                    true
//                }
//                else -> super.onOptionsItemSelected(item)
//            }