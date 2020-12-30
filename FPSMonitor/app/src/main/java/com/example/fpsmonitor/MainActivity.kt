package com.example.fpsmonitor

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button

class MainActivity : AppCompatActivity() {
    companion object {
        private val TAG:String="MainActivity"
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.start).setOnClickListener {
            startMonitorFps()
        }
        findViewById<Button>(R.id.stop).setOnClickListener {
            stopMonitorFps()
        }
    }

    fun startMonitorFps() {
        FpsMonitor.startMonitor { fps ->
            Log.d(TAG, String.format("fps: %s", fps))
        }
    }

    fun stopMonitorFps() {
        FpsMonitor.stopMonitor()

    }
}