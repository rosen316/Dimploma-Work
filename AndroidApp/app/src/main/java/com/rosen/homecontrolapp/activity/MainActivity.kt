package com.rosen.homecontrolapp.activity

import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.preference.PreferenceManager
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.constant.Constant
import com.rosen.homecontrolapp.constant.devices
import com.rosen.homecontrolapp.constant.espConnector
import com.rosen.homecontrolapp.model.Device
import com.rosen.homecontrolapp.model.DeviceMode
import com.rosen.homecontrolapp.model.Storage
import com.rosen.homecontrolapp.service.EspConnector
import com.rosen.homecontrolapp.storage.Preferences

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val prefs = Preferences(this)
        devices = prefs.getDevices()
        val listView =  findViewById<ListView>(R.id.device_list)
        val arrayAdaper = ArrayAdapter<Device>(this, android.R.layout.simple_list_item_1, devices!!)
        val addBtn = findViewById<FloatingActionButton>(R.id.addDeviceButton)

        listView.adapter = arrayAdaper

        listView.setOnItemClickListener{parent, view, position, id ->
            val element = parent.getItemAtPosition(position)
            intent = Intent(this, DeviceActivity::class.java)
            intent.putExtra("Device Id", (element as Device).id)
            startActivity(intent)
        }

        addBtn.setOnClickListener {
            var dialog = AddDeviceDialog()
            dialog.show(supportFragmentManager, "addDeviceDialog")
        }

    }

    override fun onResume() {
        super.onResume()
        adapter.n
    }
}