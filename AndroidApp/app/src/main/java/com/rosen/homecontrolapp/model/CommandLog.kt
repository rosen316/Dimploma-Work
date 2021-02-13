package com.rosen.homecontrolapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.time.format.FormatStyle

@RequiresApi(Build.VERSION_CODES.O)
class CommandLog(val deviceName: String, val command: String) {

    var timestamp: String = DateTimeFormatter.ofLocalizedDateTime(FormatStyle.SHORT).format(LocalDateTime.now())
    
    fun get_info(): String{
        return "$deviceName          $command          $timestamp"
    }
}