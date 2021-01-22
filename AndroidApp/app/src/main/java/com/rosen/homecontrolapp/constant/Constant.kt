package com.rosen.homecontrolapp.constant

import com.rosen.homecontrolapp.model.Storage

object Constant {

    val DEVICE_ID_STORAGE = Storage("device_id", "0")
    val DEVICE_STORAGE = Storage("devices", "")

    val STA_SSID_STORAGE = Storage("sta_ssid", "")
    val STA_WIFI_PASSWORD_STORAGE = Storage("sta_wifi_password", "")
    val STA_IP_ADDRESS_STORAGE = Storage("sta_ip_address", "")
    val STA_PORT_STORAGE = Storage("sta_port", "")

    val AP_SSID_STORAGE = Storage("ap_ssid", "")
    val AP_WIFI_PASSWORD_STORAGE = Storage("ap_wifi_password", "")
    val AP_IP_ADDRESS_STORAGE = Storage("ap_ip_address", "")
    val AP_PORT_STORAGE = Storage("ap_port", "")


}