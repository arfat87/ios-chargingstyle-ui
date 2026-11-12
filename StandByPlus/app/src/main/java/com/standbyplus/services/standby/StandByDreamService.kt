package com.standbyplus.services.standby

import android.service.dreams.DreamService
import androidx.compose.ui.platform.ComposeView
import com.standbyplus.core.design.StandByPlusTheme
import com.standbyplus.data.preferences.StandByDataStore
import com.standbyplus.domain.model.ClockStyle
import com.standbyplus.domain.model.WidgetItem
import com.standbyplus.engine.clock.ClockEngine
import com.standbyplus.engine.nightmode.NightModeEngine
import com.standbyplus.feature.standby.StandByScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StandByDreamService : DreamService() {

    private val clockEngine = ClockEngine()
    private val nightModeEngine = NightModeEngine()
    private lateinit var dataStore: StandByDataStore

    override fun onAttachedToWindow() {
        super.onAttachedToWindow()
        isInteractive = true
        isFullscreen = true
        isScreenBright = true

        dataStore = StandByDataStore(this)

        val composeView = ComposeView(this).apply {
            CoroutineScope(Dispatchers.Main).launch {
                val clockStyle = dataStore.clockStyleFlow.first()
                val rightWidget = dataStore.rightWidgetFlow.first()
                val is24Hour = dataStore.is24HourFlow.first()
                val showSeconds = dataStore.showSecondsFlow.first()
                val nightConfig = dataStore.nightModeConfigFlow.first()

                val isNightActive = nightModeEngine.isNightActive(nightConfig)

                setContent {
                    StandByPlusTheme(isNightRedMode = isNightActive && nightConfig.redMonochrome) {
                        StandByScreen(
                            clockEngine = clockEngine,
                            nightModeEngine = nightModeEngine,
                            initialClockStyle = clockStyle,
                            initialWidget = rightWidget,
                            is24Hour = is24Hour,
                            showSeconds = showSeconds,
                            isNightRedMode = isNightActive && nightConfig.redMonochrome,
                            burnInProtection = nightConfig.pixelShiftEnabled
                        )
                    }
                }
            }
        }

        setContentView(composeView)
    }
}
