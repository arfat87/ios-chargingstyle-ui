package com.standbyplus.app

import android.os.Bundle
import android.view.WindowManager
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.standbyplus.core.design.StandByPlusTheme
import com.standbyplus.data.preferences.StandByDataStore
import com.standbyplus.engine.clock.ClockEngine
import com.standbyplus.engine.nightmode.NightModeEngine
import com.standbyplus.feature.standby.StandByScreen
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class StandByActivity : ComponentActivity() {

    private val clockEngine = ClockEngine()
    private val nightModeEngine = NightModeEngine()
    private lateinit var dataStore: StandByDataStore

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataStore = StandByDataStore(this)

        // Keep screen awake while active on charger/stand
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)

        // Immersive edge-to-edge fullscreen
        WindowCompat.setDecorFitsSystemWindows(window, false)
        val insetsController = WindowCompat.getInsetsController(window, window.decorView)
        insetsController.systemBarsBehavior =
            WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        insetsController.hide(WindowInsetsCompat.Type.systemBars())

        CoroutineScope(Dispatchers.Main).launch {
            val clockStyle = dataStore.clockStyleFlow.first()
            val leftWidget = dataStore.leftWidgetFlow.first()
            val rightWidget = dataStore.rightWidgetFlow.first()
            val is24Hour = dataStore.is24HourFlow.first()
            val showSeconds = dataStore.showSecondsFlow.first()
            val nightConfig = dataStore.nightModeConfigFlow.first()

            val isNightActive = nightModeEngine.isNightActive(nightConfig)

            // Adjust window brightness if in night mode
            if (isNightActive && nightConfig.brightnessPercent in 10..100) {
                val params = window.attributes
                params.screenBrightness = (nightConfig.brightnessPercent / 100f).coerceIn(0.1f, 1.0f)
                window.attributes = params
            }

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
}
