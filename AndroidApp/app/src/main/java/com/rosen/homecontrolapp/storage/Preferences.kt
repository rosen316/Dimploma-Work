package com.rosen.homecontrolapp.storage

import android.content.Context
import android.content.SharedPreferences
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.preference.PreferenceManager
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.rosen.homecontrolapp.constant.commandLogs
import com.rosen.homecontrolapp.constant.Constant
import com.rosen.homecontrolapp.constant.devices
import com.rosen.homecontrolapp.model.CommandLog
import com.rosen.homecontrolapp.model.Device
import com.rosen.homecontrolapp.model.Storage

class Preferences(ctx: Context) {

    val preferences: SharedPreferences = PreferenceManager.getDefaultSharedPreferences(ctx)

    private fun getSharedPreferencesValue(storage: Storage): String {
        val value = preferences.getString(storage.key, storage.default)!!
        return if (value.isBlank()) storage.default else value
    }

    private fun changeSharedPreferencesValue(key : String, newValue : String) {
        preferences.edit().putString(key, newValue).apply()
    }


    fun getIpAddressSta(): String = getSharedPreferencesValue(Constant.STA_IP_ADDRESS_STORAGE)
    fun getPortSta(): String = getSharedPreferencesValue(Constant.STA_PORT_STORAGE)
    fun getPasswordSta(): String = getSharedPreferencesValue(Constant.STA_WIFI_PASSWORD_STORAGE)
    fun getSsidSta(): String = getSharedPreferencesValue(Constant.STA_SSID_STORAGE)
    fun getIpAddressAp(): String = getSharedPreferencesValue(Constant.AP_IP_ADDRESS_STORAGE)
    fun getPortAp(): String = getSharedPreferencesValue(Constant.AP_PORT_STORAGE)
    fun getPasswordAp(): String = getSharedPreferencesValue(Constant.AP_WIFI_PASSWORD_STORAGE)
    fun getSsidAp(): String = getSharedPreferencesValue(Constant.AP_SSID_STORAGE)

    fun getDevices(): ArrayList<Device>{
        if(Gson().fromJson<ArrayList<Device>>(getSharedPreferencesValue(Constant.DEVICE_STORAGE), object : TypeToken<ArrayList<Device>>() {}.type) == null) return ArrayList<Device>()
        else return Gson().fromJson<ArrayList<Device>>(getSharedPreferencesValue(Constant.DEVICE_STORAGE), object : TypeToken<ArrayList<Device>>() {}.type)
    }

    fun getLogs(): ArrayList<CommandLog>{
        if( Gson().fromJson<ArrayList<CommandLog>>(getSharedPreferencesValue(Constant.COMMANDS_LOG_STORAGE), object : TypeToken<ArrayList<CommandLog>>() {}.type) == null)  return ArrayList<CommandLog>()
        else return Gson().fromJson<ArrayList<CommandLog>>(getSharedPreferencesValue(Constant.COMMANDS_LOG_STORAGE), object : TypeToken<ArrayList<CommandLog>>() {}.type)
    }


    fun saveDevice(device: Device?)
    {
        devices = getDevices()
        for(i in 0..devices.size-1)
        {
            if(devices[i].id == device!!.id)
            {
                devices[i] = device
            }
        }
        saveDevices()
    }

    fun addDevice(device: Device){
        devices = getDevices()
        devices.add(device)
        saveDevices()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun addLog(log: CommandLog){
        commandLogs = getLogs()
        commandLogs.add(log)
        saveLogs()
    }

    fun saveDevices(){
        val jsonString = Gson().toJson(devices)
        changeSharedPreferencesValue("devices", jsonString)
    }

    @RequiresApi(Build.VERSION_CODES.O)
    fun saveLogs(){
        val jsonString = Gson().toJson(commandLogs)
        changeSharedPreferencesValue("command_logs", jsonString)
    }

    fun generateDeviceId() : Int
    {
        val deviceId = getSharedPreferencesValue(Constant.DEVICE_ID_STORAGE).toInt() + 1
        changeSharedPreferencesValue(Constant.DEVICE_ID_STORAGE.key, deviceId.toString())
        return deviceId
    }

    fun loadDevice(device: Device){
        changeSharedPreferencesValue("sta_ssid", device.sta_ssid)
        changeSharedPreferencesValue("sta_wifi_password", device.sta_password )
        changeSharedPreferencesValue("sta_ip_address", device.sta_ip)
        changeSharedPreferencesValue("sta_port", device.sta_port )
        changeSharedPreferencesValue("ap_ssid", device.ap_ssid)
        changeSharedPreferencesValue("ap_wifi_password", device.ap_password)
        changeSharedPreferencesValue("ap_ip_address", device.ap_ip )
        changeSharedPreferencesValue("ap_port", device.ap_port)
    }


}
