package com.rosen.homecontrolapp.constant


import com.rosen.homecontrolapp.model.CommandLog
import com.rosen.homecontrolapp.model.Device
import com.rosen.homecontrolapp.service.EspConnector

lateinit var espConnector: EspConnector
var devices = ArrayList<Device>()
var commandLogs = ArrayList<CommandLog>()