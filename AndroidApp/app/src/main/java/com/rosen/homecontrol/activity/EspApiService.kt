package com.rosen.homecontrol

import android.content.Context
import android.util.Log
import android.widget.Toast
import com.rosen.homecontrol.storage.Preferences
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.http.GET
import retrofit2.http.Query

private interface EspApiService {
    @GET("/led")
    fun ledState(@Query("state") ledState: String): Call<Void>
    @GET("/switch")
    fun switchMode(@Query("mode") newMode: String): Call<Void>
}

class EspConnector(context: Context) {

    private val preferences = Preferences(context)
    private var service: EspApiService? = null

    init {
        val url = "http://192.168.4.1"
        try {
            val retrofit = Retrofit.Builder().baseUrl(url).build()
            service = retrofit.create(EspApiService::class.java)
        } catch (e: IllegalArgumentException) {
        }

    }

    fun changeUrl(url: String){
        try {
            val retrofit = Retrofit.Builder().baseUrl(url).build()
            service = retrofit.create(EspApiService::class.java)
        } catch (e: IllegalArgumentException) {
        }
    }

    fun sendLedRequest(state: String) {
    if (service == null) return
    val request = (service as EspApiService).ledState(state)
    request.enqueue(object : Callback<Void> {
        override fun onResponse(call: Call<Void>, response: Response<Void>) {
            Log.i("Response", response.code().toString())
        }

        override fun onFailure(call: Call<Void>, t: Throwable) {
            Log.e("Failure", t.message.toString())
        }
    })
}


    fun sendSwitchRequest(mode: String) {
        if (service == null) return
        val request = (service as EspApiService).switchMode(mode)
        request.enqueue(object : Callback<Void> {
            override fun onResponse(call: Call<Void>, response: Response<Void>) {
                Log.i("Response", response.code().toString())
            }

            override fun onFailure(call: Call<Void>, t: Throwable) {
                Log.e("Failure", t.message.toString())
            }
        })
    }



}
