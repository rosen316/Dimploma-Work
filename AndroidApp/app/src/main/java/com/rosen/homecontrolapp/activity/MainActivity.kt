package com.rosen.homecontrolapp.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import com.rosen.homecontrolapp.EspConnector
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.storage.Preferences

lateinit var espConnector: EspConnector

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar((findViewById(R.id.toolbar_main)))
        val preferences = Preferences(this)

        var btnON = findViewById<Button>(R.id.buttonOn)
        var btnOFF = findViewById<Button>(R.id.buttonOff)
        var btnAP = findViewById<Button>(R.id.buttonSwitchAp)
        var btnSTA = findViewById<Button>(R.id.buttonSwitchSta)

        espConnector = EspConnector(this@MainActivity)

        btnON.setOnClickListener {
            Toast.makeText(this, "ON", Toast.LENGTH_LONG ).show()
            espConnector.sendLedRequest(state = "on")
        }

        btnOFF.setOnClickListener {
            Toast.makeText(this, "OFF", Toast.LENGTH_SHORT ).show()
            espConnector.sendLedRequest(state = "off")

        }
        btnAP.setOnClickListener {
            Toast.makeText(this, "AP MODE", Toast.LENGTH_SHORT ).show()
            espConnector.sendSwitchRequest("ap")
            val url = "http://${preferences.getIpAddressAp()}"
            println(preferences.getPasswordSta())
            espConnector.changeUrl(url)


        }
        btnSTA.setOnClickListener {
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