package com.example.broadcastreceiver

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import androidx.lifecycle.MutableLiveData

class MyReceiver : BroadcastReceiver() {
    val message = MutableLiveData<String>()

    override fun onReceive(context: Context, intent: Intent) {
        message.value = if (intent.hasExtra("path"))
            intent.getStringExtra("path")
        else
            "Failed"
    }
}
