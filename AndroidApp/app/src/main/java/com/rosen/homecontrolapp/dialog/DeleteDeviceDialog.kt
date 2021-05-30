package com.rosen.homecontrolapp.dialog

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import com.rosen.homecontrolapp.R
import com.rosen.homecontrolapp.activity.MainActivity
import com.rosen.homecontrolapp.constant.devices
import java.lang.ClassCastException

class DeleteDeviceDialog: DialogFragment(){

    interface OnDeviceDeletedListener{
        fun deviceDeleted(yes: Boolean)
    }

    lateinit var deviceDeletedListener: OnDeviceDeletedListener

    override fun onAttach(context: Context) {
        super.onAttach(context)
        try {
            deviceDeletedListener = context as OnDeviceDeletedListener
        }catch(e: ClassCastException){
            e.printStackTrace()
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {


        val rootView: View = inflater.inflate(R.layout.fragment_delete_device, container, false)

        val yesBtn = rootView.findViewById<Button>(R.id.yesButton)
        val noBtn = rootView.findViewById<Button>(R.id.noButton)


        yesBtn.setOnClickListener(){
            deviceDeletedListener.deviceDeleted(true)
            dismiss()
        }

        noBtn.setOnClickListener(){
            dismiss()
        }
        return rootView
    }


}