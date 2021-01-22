package com.rosen.homecontrolapp.model

import android.app.Application
import android.content.Context
import android.content.ContextWrapper
import com.rosen.homecontrolapp.storage.Preferences

class Device(@Transient val context: Context,
             var name: String = "",
             var ap_ip: String = "",
             var ap_ssid: String = "",
             var ap_password: String = "",
             var ap_port: String = "",
             var sta_ip: String = "",
             var sta_ssid: String = "",
             var sta_password: String = "",
             var sta_port: String = "", )
{
    @Transient private val preferences = Preferences(context)
    val id : Int = preferences.generateDeviceId()
    var relayOn : Boolean = false
    var mode  = DeviceMode.ACCESS_POINT
}