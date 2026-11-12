package com.standbyplus.feature.standby

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.standbyplus.domain.model.CalendarItem
import com.standbyplus.domain.model.ClockStyle
import com.standbyplus.domain.model.MusicState
import com.standbyplus.domain.model.WeatherInfo
import com.standbyplus.domain.model.WidgetItem
import com.standbyplus.engine.clock.ClockEngine
import com.standbyplus.engine.clock.TimeSnapshot
import com.standbyplus.engine.nightmode.NightModeEngine
import com.standbyplus.feature.standby.components.AnalogClockView
import com.standbyplus.feature.standby.components.DigitalClockView
import com.standbyplus.feature.standby.components.FlipClockView
import com.standbyplus.feature.standby.widgets.BatteryWidgetView
import com.standbyplus.feature.standby.widgets.CalendarWidgetView
import com.standbyplus.feature.standby.widgets.MusicWidgetView
import com.standbyplus.feature.standby.widgets.PhotoWidgetView
import com.standbyplus.feature.standby.widgets.RemindersWidgetView
import com.standbyplus.feature.standby.widgets.WeatherWidgetView
import java.util.Calendar

@Composable
fun StandByScreen(
    clockEngine: ClockEngine,
    nightModeEngine: NightModeEngine,
    initialClockStyle: ClockStyle = ClockStyle.DIGITAL,
    initialWidget: WidgetItem = WidgetItem.WEATHER,
    is24Hour: Boolean = false,
    showSeconds: Boolean = true,
    isNightRedMode: Boolean = false,
    burnInProtection: Boolean = true,
    modifier: Modifier = Modifier
) {
    val timeSnapshot by clockEngine.timeFlow.collectAsState(
        initial = TimeSnapshot(
            calendar = Calendar.getInstance(),
            hours24 = 22,
            hours12 = 10,
            minutes = 30,
            seconds = 0,
            period = "PM",
            dayName = "Thursday",
            monthName = "November",
            dayNumber = 12,
            formattedTime12 = "10:30",
            formattedTime24 = "22:30",
            formattedSeconds = "00",
            hourAngle = 315f,
            minuteAngle = 180f,
            secondAngle = 0f
        )
    )

    val pixelOffset by nightModeEngine.pixelShiftFlow.collectAsState(
        initial = com.standbyplus.engine.nightmode.PixelOffset(0f, 0f)
    )

    var currentClockStyle by remember { mutableStateOf(initialClockStyle) }
    var currentWidget by remember { mutableStateOf(initialWidget) }

    val clockStyles = listOf(ClockStyle.DIGITAL, ClockStyle.ANALOG, ClockStyle.FLIP)
    val widgets = listOf(WidgetItem.WEATHER, WidgetItem.CALENDAR, WidgetItem.BATTERY, WidgetItem.MUSIC, WidgetItem.PHOTO, WidgetItem.REMINDERS)

    val offsetModifier = if (burnInProtection) {
        Modifier.offset(x = pixelOffset.xDp.dp, y = pixelOffset.yDp.dp)
    } else Modifier

    val cardBg = if (isNightRedMode) Color(0xFF140303) else Color(0xFF13151D)
    val cardBorder = if (isNightRedMode) Color(0xFF3B0B0B) else Color(0xFF262936)

    Box(
        modifier = modifier
            .fillMaxSize()
            .background(if (isNightRedMode) Color(0xFF070101) else Color.Black)
            .then(offsetModifier)
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier.fillMaxSize(),
            horizontalArrangement = Arrangement.spacedBy(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Left Column: Clock Slot (Click to cycle clock styles)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(28.dp))
                    .background(cardBg)
                    .border(1.dp, cardBorder, RoundedCornerShape(28.dp))
                    .clickable {
                        val nextIdx = (clockStyles.indexOf(currentClockStyle) + 1) % clockStyles.size
                        currentClockStyle = clockStyles[nextIdx]
                    }
            ) {
                when (currentClockStyle) {
                    ClockStyle.ANALOG -> AnalogClockView(
                        timeSnapshot = timeSnapshot,
                        isNightRedMode = isNightRedMode
                    )
                    ClockStyle.FLIP -> FlipClockView(
                        timeSnapshot = timeSnapshot,
                        is24Hour = is24Hour,
                        isNightRedMode = isNightRedMode
                    )
                    else -> DigitalClockView(
                        timeSnapshot = timeSnapshot,
                        is24Hour = is24Hour,
                        showSeconds = showSeconds,
                        isNightRedMode = isNightRedMode
                    )
                }
            }

            // Right Column: Widget Slot (Click to cycle widgets)
            Box(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight()
                    .clip(RoundedCornerShape(28.dp))
                    .background(cardBg)
                    .border(1.dp, cardBorder, RoundedCornerShape(28.dp))
                    .clickable {
                        val nextIdx = (widgets.indexOf(currentWidget) + 1) % widgets.size
                        currentWidget = widgets[nextIdx]
                    }
            ) {
                when (currentWidget) {
                    WidgetItem.CALENDAR -> CalendarWidgetView(
                        timeSnapshot = timeSnapshot,
                        events = listOf(
                            CalendarItem(1, "StandBy+ Sprint Sync", "10:00 PM - 10:45 PM", "#30D158"),
                            CalendarItem(2, "Night Charging Active", "11:00 PM", "#FF9F0A")
                        ),
                        isNightRedMode = isNightRedMode
                    )
                    WidgetItem.BATTERY -> BatteryWidgetView(
                        batteryLevel = 84,
                        isCharging = true,
                        chargingWatts = 45,
                        isNightRedMode = isNightRedMode
                    )
                    WidgetItem.MUSIC -> MusicWidgetView(
                        musicState = MusicState(),
                        isNightRedMode = isNightRedMode
                    )
                    WidgetItem.PHOTO -> PhotoWidgetView(
                        timeSnapshot = timeSnapshot,
                        isNightRedMode = isNightRedMode
                    )
                    WidgetItem.REMINDERS -> RemindersWidgetView(
                        isNightRedMode = isNightRedMode
                    )
                    WidgetItem.WEATHER -> WeatherWidgetView(
                        weather = WeatherInfo(),
                        isNightRedMode = isNightRedMode
                    )
                    else -> WeatherWidgetView(
                        weather = WeatherInfo(),
                        isNightRedMode = isNightRedMode
                    )
                }
            }
        }
    }
}
