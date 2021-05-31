package com.rosen.homecontrolapp.activity

import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.constant.devices
import com.rosen.homecontrolapp.dialog.AddDeviceDialog
import com.rosen.homecontrolapp.dialog.DeleteDeviceDialog
import com.rosen.homecontrolapp.model.Device
import com.rosen.homecontrolapp.storage.Preferences

class MainActivity : AppCompatActivity(), AddDeviceDialog.OnDeviceAddedListener {

    lateinit var arrayAdapter: ArrayAdapter<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar((findViewById(R.id.toolbar_logs)))

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val mChannel = NotificationChannel("1", "channel1", NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
            notificationManager.createNotificationChannel(mChannel)
        }

        val prefs = Preferences(this)
        devices = prefs.getDevices()

        val listView =  findViewById<ListView>(R.id.device_list)
        var deviceNames = devices.map {it.name}
        arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, deviceNames)
        //arrayAdapter = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, prefs.getDevices().map {it.name})
        listView.adapter = arrayAdapter

        listView.setOnItemClickListener{parent, view, position, id ->
            val deviceName = parent.getItemAtPosition(position)
            val device = devices.find { it.name.equals(deviceName)}
            intent = Intent(this, DeviceActivity::class.java)
            intent.putExtra("Device Id", device!!.id)
            startActivity(intent)
        }

        val addBtn = findViewById<FloatingActionButton>(R.id.addDeviceButton)
        addBtn.setOnClickListener {
            AddDeviceDialog().show(supportFragmentManager, "addDeviceDialog")
        }
    }

    override fun deviceAdded(yes: Boolean) {
        //arrayAdapter.notifyDataSetChanged()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.toolbar_main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean =
            when (item.itemId) {
                R.id.action_logs -> {
                    val intent = Intent(this, CommandLogActivity::class.java)
                    startActivity(intent)
                    true
                }
                else -> super.onOptionsItemSelected(item)
            }
}