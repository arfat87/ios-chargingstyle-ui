package com.standbyplus.app

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager
import android.os.Build
import com.standbyplus.data.local.StandByDatabase
import com.standbyplus.services.charging.ChargingReceiver

class StandByApplication : Application() {

    lateinit var database: StandByDatabase
        private set

    override fun onCreate() {
        super.onCreate()
        database = StandByDatabase.getInstance(this)
        createNotificationChannel()
    }

    private fun createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                ChargingReceiver.CHANNEL_ID,
                "StandBy+ Charging Notification",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Shows actionable notification when phone connects to charger"
                enableVibration(true)
            }
            val manager = getSystemService(NotificationManager::class.java)
            manager.createNotificationChannel(channel)
        }
    }
}
