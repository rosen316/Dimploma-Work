package com.rosen.homecontrolapp.activity

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.preference.Preference
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.constant.devices
import com.rosen.homecontrolapp.model.Device
import com.rosen.homecontrolapp.storage.Preferences

class AddDeviceDialog: DialogFragment() {

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
            dismiss()
        }

        return rootView
    }
}