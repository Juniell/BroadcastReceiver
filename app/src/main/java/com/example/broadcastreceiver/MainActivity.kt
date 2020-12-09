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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestForPermission()

        val br = MyReceiver()
        val filter = IntentFilter("com.example.startedservicedownload.PATH")
        registerReceiver(br, filter)
    }

    fun changeView(uri: String?) {
        if (uri == "null" || uri == null)
            pathView.setText(R.string.failed)
        else {
            pathView.text = uri
            Glide.with(this).load(uri).into(imageView)
        }
    }

    private fun requestForPermission() {
        if (!hasPermission()) {
            val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE)
            requestPermissions(permissions, 1)
        }
    }

    private fun hasPermission() = ContextCompat.checkSelfPermission(
        this,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    ) == PackageManager.PERMISSION_GRANTED
}