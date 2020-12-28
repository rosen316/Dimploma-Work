package com.rosen.homecontrol.activity

import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.preference.PreferenceManager
import com.rosen.homecontrol.EspConnector
import com.rosen.homecontrol.R
import com.rosen.homecontrol.constant.Constant
import com.rosen.homecontrol.storage.Preferences


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar((findViewById(R.id.toolbar_main)))
        val preferences = Preferences(this)

        lateinit var espConnector: EspConnector
        var btnON = findViewById(R.id.buttonOn) as Button
        var btnOFF = findViewById(R.id.buttonOff) as Button
        var btnAP = findViewById(R.id.buttonSwitchAp) as Button
        var btnSTA = findViewById(R.id.buttonSwitchSta) as Button

        espConnector = EspConnector(this@MainActivity)

        btnON.setOnClickListener(){
            Toast.makeText(this, "ON", Toast.LENGTH_LONG ).show()
            espConnector.sendLedRequest(state = "on")
        }

        btnOFF.setOnClickListener(){
            Toast.makeText(this, "OFF", Toast.LENGTH_SHORT ).show()
            espConnector.sendLedRequest(state = "off")

        }
        btnAP.setOnClickListener(){
            Toast.makeText(this, "AP MODE", Toast.LENGTH_SHORT ).show()
            espConnector.sendSwitchRequest("ap")
            val url = "http://${preferences.getIpAddressAp()}"
            println(preferences.getPasswordSta())
            espConnector.changeUrl(url)


        }
        btnSTA.setOnClickListener(){
            Toast.makeText(this, "STA MODE", Toast.LENGTH_SHORT ).show()
            espConnector.sendSwitchRequest("sta")
            val url = "http://${preferences.getIpAddressSta()}"
            println(preferences.getPasswordAp())
            espConnector.changeUrl(url)

        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.action_configuration -> {
                    Toast.makeText(this, "CLICKED", Toast.LENGTH_SHORT ).show()
                    val intent = Intent(this, ConfigurationActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}