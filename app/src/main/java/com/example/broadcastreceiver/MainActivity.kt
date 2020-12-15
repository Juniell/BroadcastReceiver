package com.example.broadcastreceiver

import android.Manifest
import android.content.IntentFilter
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    private lateinit var br: MyReceiver
    private var msg: String? = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        br = MyReceiver()
        val filter = IntentFilter("com.example.startedservicedownload.PATH")
        registerReceiver(br, filter)

        br.message.observe(this) {
            msg = it
            changeView(it)
        }
    }

    private fun changeImageView(message: String?) {
        Glide.with(this).load(message).into(imageView)
    }

    private fun changeView(message: String?) {
        if (message == "Failed" || message == null)
            pathView.setText(R.string.failed)
        else {
            pathView.text = message
            if (hasPermission())
                changeImageView(message)
            else
                requestForPermission()
        }
    }

    private fun hasPermission() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.READ_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED

    private fun requestForPermission() {
        if (!hasPermission()) {
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            requestPermissions(permissions, 1)
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED)
            changeImageView(msg)
    }

    override fun onDestroy() {
        super.onDestroy()
        unregisterReceiver(br)
    }
}