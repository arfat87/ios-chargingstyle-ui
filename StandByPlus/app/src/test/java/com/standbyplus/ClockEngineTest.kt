package com.standbyplus

import com.standbyplus.engine.clock.ClockEngine
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotNull
import org.junit.Before
import org.junit.Test

class ClockEngineTest {

    private lateinit var clockEngine: ClockEngine

    @Before
    fun setUp() {
        clockEngine = ClockEngine()
    }

    @Test
    fun testGetCityTime_returnsValidTime() {
        val (timeStr, period) = clockEngine.getCityTime("America/New_York", is24Hour = false)
        assertNotNull(timeStr)
        assertNotNull(period)
        assertEquals(5, timeStr.length) // "HH:MM"
    }

    @Test
    fun testGetCityTime_24HourFormat() {
        val (timeStr, _) = clockEngine.getCityTime("Europe/London", is24Hour = true)
        assertEquals(5, timeStr.length)
        val hours = timeStr.substring(0, 2).toInt()
        assert(hours in 0..23)
    }
}
