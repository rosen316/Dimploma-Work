package com.rosen.homecontrolapp.dialog

import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.fragment.app.DialogFragment
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.activity.MainActivity
import com.rosen.homecontrolapp.constant.devices
import com.rosen.homecontrolapp.model.Device
import com.rosen.homecontrolapp.storage.Preferences
import java.lang.ClassCastException

class AddDeviceDialog: DialogFragment() {

    interface OnDeviceAddedListener{
        fun deviceAdded(yes: Boolean)
    }

    lateinit var deviceAddedListener: OnDeviceAddedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            deviceAddedListener = context as OnDeviceAddedListener
        }catch(e: ClassCastException){
            e.printStackTrace()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        val prefs = Preferences(this.context as Context)
        devices = prefs.getDevices()

        val rootView: View = inflater.inflate(R.layout.fragment_add_device, container, false)

        val cancelBtn = rootView.findViewById<Button>(R.id.cancelButton)
        val saveBtn = rootView.findViewById<Button>(R.id.saveButton)
        val deviceName = rootView.findViewById<EditText>(R.id.DeviceNameEditText)
        val StaSSID = rootView.findViewById<EditText>(R.id.StaSSIDEditText)
        val StaPassword = rootView.findViewById<EditText>(R.id.StaPasswordEditText)
        val StaIp = rootView.findViewById<EditText>(R.id.StaIPEditText)
        val ApSSID = rootView.findViewById<EditText>(R.id.ApSSIDEditText)
        val ApPassword = rootView.findViewById<EditText>(R.id.ApPasswordEditText)
        val ApIp = rootView.findViewById<EditText>(R.id.ApIPEditText)

        cancelBtn.setOnClickListener()
        {
            dismiss()
        }

        saveBtn.setOnClickListener()
        {
            devices.add(Device(
                    this.context as Context,
                    name = deviceName.text.toString(),
                    sta_ssid = StaSSID.text.toString(),
                    sta_password = StaPassword.text.toString(),
                    sta_ip = StaIp.text.toString(),
                    ap_ssid = ApSSID.text.toString(),
                    ap_password = ApPassword.text.toString(),
                    ap_ip = ApIp.text.toString()))
            prefs.saveDevices()

            val intent = Intent(this.context, MainActivity::class.java).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
            }
            val pendingIntent: PendingIntent = PendingIntent.getActivity(this.context, 0, intent, 0)

            val builder = NotificationCompat.Builder(this.context as Context, "1")
                    .setContentTitle("New Device Created Successfully")
                    .setContentText("${deviceName.text.toString()} ready for usage!")
                    .setSmallIcon((R.drawable.ic_launcher_foreground))
                    .setContentIntent(pendingIntent)
                    .setAutoCancel(true)

            NotificationManagerCompat.from(this.context as Context).notify(1, builder.build())

            deviceAddedListener.deviceAdded(true)
            dismiss()

        }

        return rootView
    }
}