package com.rosen.homecontrolapp.activity

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.constant.commandLogs
import com.rosen.homecontrolapp.constant.devices
import com.rosen.homecontrolapp.model.CommandLog
import com.rosen.homecontrolapp.model.Device
import com.rosen.homecontrolapp.storage.Preferences


class CommandLogActivity : AppCompatActivity() {

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_command_log)

        val prefs = Preferences(this)

        commandLogs = prefs.getLogs()
        val commandLogs_info = commandLogs.map { it.get_info() }.toMutableList()
        val listView = findViewById<ListView>(R.id.LogListView)
        val arrayAdaper = ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, commandLogs_info)
        listView.adapter = arrayAdaper

    }
}
