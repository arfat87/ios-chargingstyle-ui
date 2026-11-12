package com.standbyplus.engine.clock

import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import java.util.TimeZone

data class TimeSnapshot(
    val calendar: Calendar,
    val hours24: Int,
    val hours12: Int,
    val minutes: Int,
    val seconds: Int,
    val period: String,
    val dayName: String,
    val monthName: String,
    val dayNumber: Int,
    val formattedTime12: String,
    val formattedTime24: String,
    val formattedSeconds: String,
    val hourAngle: Float,
    val minuteAngle: Float,
    val secondAngle: Float
)

class ClockEngine {

    val timeFlow: Flow<TimeSnapshot> = flow {
        val dayFormat = SimpleDateFormat("EEEE", Locale.getDefault())
        val monthFormat = SimpleDateFormat("MMMM", Locale.getDefault())

        while (true) {
            val cal = Calendar.getInstance()
            val hours24 = cal.get(Calendar.HOUR_OF_DAY)
            val hours12Raw = cal.get(Calendar.HOUR)
            val hours12 = if (hours12Raw == 0) 12 else hours12Raw
            val minutes = cal.get(Calendar.MINUTE)
            val seconds = cal.get(Calendar.SECOND)
            val millis = cal.get(Calendar.MILLISECOND)
            val period = if (cal.get(Calendar.AM_PM) == Calendar.AM) "AM" else "PM"

            val pad = { n: Int -> n.toString().padStart(2, '0') }
            val formattedTime12 = "${pad(hours12)}:${pad(minutes)}"
            val formattedTime24 = "${pad(hours24)}:${pad(minutes)}"
            val formattedSeconds = pad(seconds)

            // Smooth sweep angles
            val secProgress = seconds + (millis / 1000f)
            val minProgress = minutes + (secProgress / 60f)
            val hourProgress = (hours24 % 12) + (minProgress / 60f)

            val secondAngle = (secProgress / 60f) * 360f
            val minuteAngle = (minProgress / 60f) * 360f
            val hourAngle = (hourProgress / 12f) * 360f

            emit(
                TimeSnapshot(
                    calendar = cal,
                    hours24 = hours24,
                    hours12 = hours12,
                    minutes = minutes,
                    seconds = seconds,
                    period = period,
                    dayName = dayFormat.format(cal.time),
                    monthName = monthFormat.format(cal.time),
                    dayNumber = cal.get(Calendar.DAY_OF_MONTH),
                    formattedTime12 = formattedTime12,
                    formattedTime24 = formattedTime24,
                    formattedSeconds = formattedSeconds,
                    hourAngle = hourAngle,
                    minuteAngle = minuteAngle,
                    secondAngle = secondAngle
                )
            )

            // Update smoothly (every 100ms for fluid second sweep)
            delay(100L)
        }
    }

    fun getCityTime(timeZoneId: String, is24Hour: Boolean): Pair<String, String> {
        val cal = Calendar.getInstance(TimeZone.getTimeZone(timeZoneId))
        val pad = { n: Int -> n.toString().padStart(2, '0') }
        val hours24 = cal.get(Calendar.HOUR_OF_DAY)
        val hours12Raw = cal.get(Calendar.HOUR)
        val hours12 = if (hours12Raw == 0) 12 else hours12Raw
        val minutes = cal.get(Calendar.MINUTE)
        val period = if (cal.get(Calendar.AM_PM) == Calendar.AM) "AM" else "PM"

        val timeStr = if (is24Hour) "${pad(hours24)}:${pad(minutes)}" else "${pad(hours12)}:${pad(minutes)}"
        return Pair(timeStr, period)
    }
}
