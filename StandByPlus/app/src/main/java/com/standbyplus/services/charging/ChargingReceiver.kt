package com.standbyplus.services.charging

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.os.BatteryManager
import android.os.Build
import androidx.core.app.NotificationCompat
import com.standbyplus.app.R
import com.standbyplus.app.StandByActivity
import com.standbyplus.data.preferences.StandByDataStore
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class ChargingReceiver : BroadcastReceiver() {

    companion object {
        const val CHANNEL_ID = "standby_charging_channel"
        const val NOTIFICATION_ID = 2026
    }

    override fun onReceive(context: Context, intent: Intent) {
        val action = intent.action ?: return
        val dataStore = StandByDataStore(context)

        CoroutineScope(Dispatchers.IO).launch {
            val autoStart = dataStore.autoStartChargingFlow.first()

            if (action == Intent.ACTION_POWER_CONNECTED && autoStart) {
                showChargingNotification(context)
            } else if (action == Intent.ACTION_POWER_DISCONNECTED) {
                val notificationManager =
                    context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.cancel(NOTIFICATION_ID)
            }
        }
    }

    private fun showChargingNotification(context: Context) {
        val notificationManager =
            context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(
                CHANNEL_ID,
                "StandBy+ Charging Notification",
                NotificationManager.IMPORTANCE_HIGH
            ).apply {
                description = "Notifies when device is connected to charger"
                enableVibration(true)
                setShowBadge(true)
            }
            notificationManager.createNotificationChannel(channel)
        }

        val launchIntent = Intent(context, StandByActivity::class.java).apply {
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
            putExtra("LAUNCHED_FROM_CHARGING", true)
        }

        val pendingIntent = PendingIntent.getActivity(
            context,
            0,
            launchIntent,
            PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE
        )

        val notification = NotificationCompat.Builder(context, CHANNEL_ID)
            .setSmallIcon(android.R.drawable.ic_lock_idle_charging)
            .setContentTitle(context.getString(R.string.charging_ready_title))
            .setContentText(context.getString(R.string.charging_ready_desc))
            .setPriority(NotificationCompat.PRIORITY_MAX)
            .setCategory(NotificationCompat.CATEGORY_ALARM)
            .setFullScreenIntent(pendingIntent, false)
            .setContentIntent(pendingIntent)
            .setAutoCancel(true)
            .addAction(
                android.R.drawable.ic_media_play,
                context.getString(R.string.open_standby),
                pendingIntent
            )
            .build()

        notificationManager.notify(NOTIFICATION_ID, notification)
    }
}
