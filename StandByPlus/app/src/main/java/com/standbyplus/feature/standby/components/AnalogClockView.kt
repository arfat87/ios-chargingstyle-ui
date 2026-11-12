package com.standbyplus.feature.standby.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.unit.dp
import com.standbyplus.core.design.AccentOrange
import com.standbyplus.core.design.NightRed
import com.standbyplus.engine.clock.TimeSnapshot
import kotlin.math.cos
import kotlin.math.sin

@Composable
fun AnalogClockView(
    timeSnapshot: TimeSnapshot,
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    val dialColor = if (isNightRedMode) Color(0xFF140303) else Color(0xFF12141C)
    val rimColor = if (isNightRedMode) Color(0xFF3B0B0B) else Color(0xFF282B36)
    val handColor = if (isNightRedMode) NightRed else Color.White
    val tickColor = if (isNightRedMode) Color(0xFFA31B14) else Color(0xFF8E8E93)
    val secondHandColor = if (isNightRedMode) NightRed else AccentOrange

    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        contentAlignment = Alignment.Center
    ) {
        Canvas(modifier = Modifier.fillMaxSize()) {
            val center = Offset(size.width / 2f, size.height / 2f)
            val radius = (size.minDimension / 2f) * 0.88f

            // Clock Face Dial
            drawCircle(color = dialColor, radius = radius, center = center)
            drawCircle(
                color = rimColor,
                radius = radius,
                center = center,
                style = Stroke(width = 6f)
            )

            // 12 Hour Ticks
            for (i in 0 until 12) {
                val angleRad = Math.toRadians((i * 30.0) - 90.0)
                val isMajor = i % 3 == 0
                val tickLength = if (isMajor) radius * 0.16f else radius * 0.09f
                val strokeWidth = if (isMajor) 5f else 2.5f

                val startX = (center.x + (radius - tickLength) * cos(angleRad)).toFloat()
                val startY = (center.y + (radius - tickLength) * sin(angleRad)).toFloat()
                val endX = (center.x + (radius - 12f) * cos(angleRad)).toFloat()
                val endY = (center.y + (radius - 12f) * sin(angleRad)).toFloat()

                drawLine(
                    color = if (isMajor) handColor else tickColor,
                    start = Offset(startX, startY),
                    end = Offset(endX, endY),
                    strokeWidth = strokeWidth,
                    cap = StrokeCap.Round
                )
            }

            // Hour Hand
            val hourAngleRad = Math.toRadians((timeSnapshot.hourAngle - 90.0))
            val hourLength = radius * 0.52f
            drawLine(
                color = handColor,
                start = center,
                end = Offset(
                    (center.x + hourLength * cos(hourAngleRad)).toFloat(),
                    (center.y + hourLength * sin(hourAngleRad)).toFloat()
                ),
                strokeWidth = 9f,
                cap = StrokeCap.Round
            )

            // Minute Hand
            val minAngleRad = Math.toRadians((timeSnapshot.minuteAngle - 90.0))
            val minLength = radius * 0.74f
            drawLine(
                color = handColor,
                start = center,
                end = Offset(
                    (center.x + minLength * cos(minAngleRad)).toFloat(),
                    (center.y + minLength * sin(minAngleRad)).toFloat()
                ),
                strokeWidth = 5.5f,
                cap = StrokeCap.Round
            )

            // Second Hand (Sweeping smoothly)
            val secAngleRad = Math.toRadians((timeSnapshot.secondAngle - 90.0))
            val secLength = radius * 0.86f
            val secTailLength = radius * 0.2f
            drawLine(
                color = secondHandColor,
                start = Offset(
                    (center.x - secTailLength * cos(secAngleRad)).toFloat(),
                    (center.y - secTailLength * sin(secAngleRad)).toFloat()
                ),
                end = Offset(
                    (center.x + secLength * cos(secAngleRad)).toFloat(),
                    (center.y + secLength * sin(secAngleRad)).toFloat()
                ),
                strokeWidth = 3f,
                cap = StrokeCap.Round
            )

            // Center Pin Cap
            drawCircle(color = handColor, radius = 8f, center = center)
            drawCircle(color = secondHandColor, radius = 5f, center = center)
        }
    }
}
