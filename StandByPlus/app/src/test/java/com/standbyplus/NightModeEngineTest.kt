package com.standbyplus

import com.standbyplus.domain.model.NightModeConfig
import com.standbyplus.engine.nightmode.NightModeEngine
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test

class NightModeEngineTest {

    private lateinit var nightModeEngine: NightModeEngine

    @Before
    fun setUp() {
        nightModeEngine = NightModeEngine()
    }

    @Test
    fun testIsNightActive_whenDisabled_returnsFalse() {
        val config = NightModeConfig(enabled = false)
        assertFalse(nightModeEngine.isNightActive(config))
    }

    @Test
    fun testIsNightActive_whenAutoScheduleOff_returnsTrueIfEnabled() {
        val config = NightModeConfig(enabled = true, autoSchedule = false)
        assertTrue(nightModeEngine.isNightActive(config))
    }
}
