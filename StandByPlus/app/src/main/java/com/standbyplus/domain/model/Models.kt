package com.standbyplus.domain.model

enum class ClockStyle(val displayName: String) {
    DIGITAL("Digital Bold"),
    ANALOG("Analog Sweep"),
    FLIP("Retro Flip 3D"),
    MINIMAL("Minimal Clean"),
    WORLD("World Clocks")
}

enum class WidgetItem(val displayName: String) {
    WEATHER("Weather Hub"),
    CALENDAR("Calendar & Agenda"),
    BATTERY("Battery & Charging"),
    MUSIC("Music Player"),
    PHOTO("Photo Slideshow"),
    REMINDERS("Bedside Reminders")
}

data class NightModeConfig(
    val enabled: Boolean = true,
    val redMonochrome: Boolean = true,
    val autoSchedule: Boolean = true,
    val startHour: Int = 22,
    val startMinute: Int = 0,
    val endHour: Int = 7,
    val endMinute: Int = 0,
    val brightnessPercent: Int = 40,
    val pixelShiftEnabled: Boolean = true,
    val disableSeconds: Boolean = true
)

data class UserLayout(
    val id: String = "default_layout",
    val name: String = "Bedside Classic",
    val clockStyle: ClockStyle = ClockStyle.DIGITAL,
    val leftWidget: WidgetItem = WidgetItem.WEATHER,
    val rightWidget: WidgetItem = WidgetItem.CALENDAR,
    val is24Hour: Boolean = false,
    val showSeconds: Boolean = true
)

data class WeatherInfo(
    val city: String = "New Delhi",
    val temperature: Int = 24,
    val condition: String = "Clear Sky",
    val highTemp: Int = 28,
    val lowTemp: Int = 18,
    val humidity: Int = 52
)

data class CalendarItem(
    val id: Long = 1,
    val title: String = "StandBy+ Night Session",
    val time: String = "10:00 PM - 10:30 PM",
    val tagColorHex: String = "#30D158"
)

data class MusicState(
    val isPlaying: Boolean = true,
    val title: String = "Midnight Lofi Ambience",
    val artist: String = "Bedside Collective",
    val album: String = "StandBy Sessions",
    val durationSeconds: Int = 210,
    val progressSeconds: Int = 74
)

data class AutomationRule(
    val activateOnCharging: Boolean = true,
    val requireLandscape: Boolean = true,
    val exitWhenUnplugged: Boolean = false,
    val showNotificationFallback: Boolean = true,
    val launchScreensaver: Boolean = true
)
