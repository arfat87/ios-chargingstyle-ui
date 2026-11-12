package com.standbyplus.engine.nightmode

import com.standbyplus.domain.model.NightModeConfig
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import java.util.Calendar
import kotlin.random.Random

data class PixelOffset(val xDp: Float, val yDp: Float)

class NightModeEngine {

    fun isNightActive(config: NightModeConfig): Boolean {
        if (!config.enabled) return false
        if (!config.autoSchedule) return true

        val cal = Calendar.getInstance()
        val currentMinutes = cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE)
        val startMinutes = config.startHour * 60 + config.startMinute
        val endMinutes = config.endHour * 60 + config.endMinute

        return if (startMinutes < endMinutes) {
            // Schedule within the same calendar day (e.g., 14:00 to 18:00)
            currentMinutes in startMinutes..endMinutes
        } else {
            // Schedule crossing midnight (e.g., 22:00 to 07:00)
            currentMinutes >= startMinutes || currentMinutes < endMinutes
        }
    }

    val pixelShiftFlow: Flow<PixelOffset> = flow {
        while (true) {
            val x = (Random.nextInt(5) - 2).toFloat() // -2, -1, 0, 1, 2
            val y = (Random.nextInt(5) - 2).toFloat()
            emit(PixelOffset(x, y))
            delay(60000L) // Shift every 60 seconds to prevent OLED burn-in
        }
    }
}
