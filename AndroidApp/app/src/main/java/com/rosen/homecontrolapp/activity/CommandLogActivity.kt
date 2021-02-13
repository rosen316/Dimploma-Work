package com.rosen.homecontrolapp.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.constant.devices
import com.rosen.homecontrolapp.model.Device


class CommandLogActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_log)

        val listView = findViewById<ListView>(R.id.device_list)
        val arrayAdaper = ArrayAdapter<Device>(this, android.R.layout.simple_list_item_1, devices!!)

        listView.adapter = arrayAdaper

        listView.setOnItemClickListener { parent, view, position, id ->
            val element = parent.getItemAtPosition(position)
            intent = Intent(this, DeviceActivity::class.java)
            intent.putExtra("Device Id", (element as Device).id)
            startActivity(intent)
        }

    }
}
