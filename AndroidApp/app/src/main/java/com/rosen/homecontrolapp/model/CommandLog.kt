package com.rosen.homecontrolapp.model

import android.os.Build
import androidx.annotation.RequiresApi
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

class ActivityLog {
    @RequiresApi(Build.VERSION_CODES.O)
    var timestamp = LocalDateTime.now()


    @RequiresApi(Build.VERSION_CODES.O)
    fun get_formated_time(): String { return ((DateTimeFormatter.ofPattern("HH:mm:ss dd/mm/yyyy").format(timestamp)))}

}