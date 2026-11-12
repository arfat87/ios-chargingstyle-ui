package com.standbyplus.data.preferences

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.standbyplus.domain.model.ClockStyle
import com.standbyplus.domain.model.NightModeConfig
import com.standbyplus.domain.model.WidgetItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "standby_settings")

class StandByDataStore(private val context: Context) {

    companion object {
        val KEY_CLOCK_STYLE = stringPreferencesKey("clock_style")
        val KEY_LEFT_WIDGET = stringPreferencesKey("left_widget")
        val KEY_RIGHT_WIDGET = stringPreferencesKey("right_widget")
        val KEY_IS_24_HOUR = booleanPreferencesKey("is_24_hour")
        val KEY_SHOW_SECONDS = booleanPreferencesKey("show_seconds")
        val KEY_NIGHT_MODE_ENABLED = booleanPreferencesKey("night_mode_enabled")
        val KEY_NIGHT_RED_MODE = booleanPreferencesKey("night_red_mode")
        val KEY_NIGHT_BRIGHTNESS = intPreferencesKey("night_brightness")
        val KEY_BURN_IN_PROTECTION = booleanPreferencesKey("burn_in_protection")
        val KEY_AUTO_START_CHARGING = booleanPreferencesKey("auto_start_charging")
        val KEY_REQUIRE_LANDSCAPE = booleanPreferencesKey("require_landscape")
        val KEY_ONBOARDING_COMPLETED = booleanPreferencesKey("onboarding_completed")
    }

    val clockStyleFlow: Flow<ClockStyle> = context.dataStore.data.map { prefs ->
        val raw = prefs[KEY_CLOCK_STYLE] ?: ClockStyle.DIGITAL.name
        runCatching { ClockStyle.valueOf(raw) }.getOrDefault(ClockStyle.DIGITAL)
    }

    val leftWidgetFlow: Flow<WidgetItem> = context.dataStore.data.map { prefs ->
        val raw = prefs[KEY_LEFT_WIDGET] ?: WidgetItem.WEATHER.name
        runCatching { WidgetItem.valueOf(raw) }.getOrDefault(WidgetItem.WEATHER)
    }

    val rightWidgetFlow: Flow<WidgetItem> = context.dataStore.data.map { prefs ->
        val raw = prefs[KEY_RIGHT_WIDGET] ?: WidgetItem.CALENDAR.name
        runCatching { WidgetItem.valueOf(raw) }.getOrDefault(WidgetItem.CALENDAR)
    }

    val is24HourFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_IS_24_HOUR] ?: false
    }

    val showSecondsFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_SHOW_SECONDS] ?: true
    }

    val nightModeConfigFlow: Flow<NightModeConfig> = context.dataStore.data.map { prefs ->
        NightModeConfig(
            enabled = prefs[KEY_NIGHT_MODE_ENABLED] ?: true,
            redMonochrome = prefs[KEY_NIGHT_RED_MODE] ?: true,
            brightnessPercent = prefs[KEY_NIGHT_BRIGHTNESS] ?: 40,
            pixelShiftEnabled = prefs[KEY_BURN_IN_PROTECTION] ?: true
        )
    }

    val isOnboardingCompletedFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_ONBOARDING_COMPLETED] ?: false
    }

    val autoStartChargingFlow: Flow<Boolean> = context.dataStore.data.map { prefs ->
        prefs[KEY_AUTO_START_CHARGING] ?: true
    }

    suspend fun setClockStyle(style: ClockStyle) {
        context.dataStore.edit { it[KEY_CLOCK_STYLE] = style.name }
    }

    suspend fun setWidgets(left: WidgetItem, right: WidgetItem) {
        context.dataStore.edit {
            it[KEY_LEFT_WIDGET] = left.name
            it[KEY_RIGHT_WIDGET] = right.name
        }
    }

    suspend fun set24Hour(enabled: Boolean) {
        context.dataStore.edit { it[KEY_IS_24_HOUR] = enabled }
    }

    suspend fun setShowSeconds(enabled: Boolean) {
        context.dataStore.edit { it[KEY_SHOW_SECONDS] = enabled }
    }

    suspend fun setNightModeEnabled(enabled: Boolean) {
        context.dataStore.edit { it[KEY_NIGHT_MODE_ENABLED] = enabled }
    }

    suspend fun setNightRedMode(enabled: Boolean) {
        context.dataStore.edit { it[KEY_NIGHT_RED_MODE] = enabled }
    }

    suspend fun setNightBrightness(brightness: Int) {
        context.dataStore.edit { it[KEY_NIGHT_BRIGHTNESS] = brightness }
    }

    suspend fun setBurnInProtection(enabled: Boolean) {
        context.dataStore.edit { it[KEY_BURN_IN_PROTECTION] = enabled }
    }

    suspend fun setOnboardingCompleted(completed: Boolean) {
        context.dataStore.edit { it[KEY_ONBOARDING_COMPLETED] = completed }
    }

    suspend fun setAutoStartCharging(enabled: Boolean) {
        context.dataStore.edit { it[KEY_AUTO_START_CHARGING] = enabled }
    }
}
