package com.rosen.homecontrolapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.Settings
import android.view.Menu
import android.view.MenuItem
import android.widget.Button
import android.widget.Switch
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.constant.devices
import com.rosen.homecontrolapp.storage.Preferences
import com.rosen.homecontrolapp.constant.espConnector
import com.rosen.homecontrolapp.model.CommandLog
import com.rosen.homecontrolapp.model.Device
import com.rosen.homecontrolapp.model.DeviceMode
import com.rosen.homecontrolapp.service.EspConnector


class DeviceActivity : AppCompatActivity() {

    var device: Device? = null
    var deviceId: Int = 0

    @RequiresApi(Build.VERSION_CODES.O)
    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        val preferences = Preferences(this)
        deviceId = intent.getIntExtra("Device Id", 0)
        device = devices.find { it.id.equals(deviceId) }
        Preferences(this).loadDevice(device as Device)

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_device)
        setSupportActionBar((findViewById(R.id.toolbar_device)))
        supportActionBar?.setTitle(device?.name)
        val relayStatusTextView = findViewById<TextView>(R.id.statusVariable)
        val modeTextView = findViewById<TextView>(R.id.modeVariable)

        val relaySwitch = findViewById<Switch>(R.id.relay_switch)
        val btnAP = findViewById<Button>(R.id.buttonSwitchAp)
        val btnSTA = findViewById<Button>(R.id.buttonSwitchSta)

        espConnector = EspConnector(this@DeviceActivity)

        when(device!!.relayOn){
            true -> { relayStatusTextView.setText("ON")
                      relaySwitch.isChecked = true }
            false -> relayStatusTextView.setText("OFF")

        }

        when(device!!.mode){
            DeviceMode.ACCESS_POINT -> {
                espConnector.changeUrl("http://${preferences.getIpAddressAp()}")
                modeTextView.setText("Access Point")
            }
            DeviceMode.STATION -> {
                espConnector.changeUrl("http://${preferences.getIpAddressSta()}")
                modeTextView.setText("Station")
            }
        }

        relaySwitch.setOnCheckedChangeListener{_, isChecked ->
            if(isChecked) {
                espConnector.sendLedRequest(state = "on")
                device!!.relayOn = true
                relayStatusTextView.setText("ON")
                preferences.saveDevice(device)
                preferences.addLog(CommandLog(deviceName = device!!.name, command = "Relay ON"))
            }
            else {
                device!!.relayOn = false
                relayStatusTextView.setText("OFF")
                preferences.saveDevice(device)
                preferences.addLog(CommandLog(deviceName = device!!.name, command = "Relay OFF"))
            }
        }


        btnAP.setOnClickListener {
            espConnector.sendSwitchRequest("ap")
            espConnector.changeUrl("http://${preferences.getIpAddressAp()}")
            device!!.mode = DeviceMode.ACCESS_POINT
            preferences.saveDevice(device)
            modeTextView.setText("Access Point")
            preferences.addLog(CommandLog(deviceName = device!!.name, command = "Mode to AP"))

        }
        btnSTA.setOnClickListener {
            espConnector.sendSwitchRequest("sta")
            espConnector.changeUrl("http://${preferences.getIpAddressSta()}")
            device!!.mode = DeviceMode.STATION
            preferences.saveDevice(device)
            modeTextView.setText("Station")
            preferences.addLog(CommandLog(deviceName = device!!.name, command = "Mode to STA"))
        }
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_device_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.action_configuration -> {
                    val intent = Intent(this, ConfigurationActivity::class.java)
                    intent.putExtra("Device Id", deviceId)
                    startActivity(intent)
                    true
                }
                R.id.action_wifi -> {
                    val intent = Intent(Settings.ACTION_WIFI_SETTINGS)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }

}