package com.example.broadcastreceiver

import android.content.IntentFilter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val br = MyReceiver()
        val filter = IntentFilter("com.example.startedservicedownload.PATH")
        registerReceiver(br, filter)
    }

    fun changePath(path: String?) {
        if (path != null)
            pathView.text = path
    }
}