package com.rosen.homecontrolapp.constant

import com.rosen.homecontrolapp.model.Storage

object Constant {

    val STA_SSID_STORAGE = Storage("sta_ssid", "jivko")
    val STA_WIFI_PASSWORD_STORAGE = Storage("sta_wifi_password", "jivkottt")
    val STA_IP_ADDRESS_STORAGE = Storage("sta_ip_address", "192.168.0.107")
    val STA_PORT_STORAGE = Storage("sta_port", "80")

    val AP_SSID_STORAGE = Storage("ap_ssid", "Esp8266AP")
    val AP_WIFI_PASSWORD_STORAGE = Storage("ap_wifi_password", "Esp8266AP")
    val AP_IP_ADDRESS_STORAGE = Storage("ap_ip_address", "192.168.4.1")
    val AP_PORT_STORAGE = Storage("ap_port", "80")


}