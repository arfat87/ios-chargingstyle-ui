package com.standbyplus.feature.standby.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.standbyplus.core.design.AccentOrange
import com.standbyplus.core.design.NightRed
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.engine.clock.TimeSnapshot

@Composable
fun DigitalClockView(
    timeSnapshot: TimeSnapshot,
    is24Hour: Boolean,
    showSeconds: Boolean,
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    val displayHours = if (is24Hour) timeSnapshot.formattedTime24.split(":")[0] else timeSnapshot.formattedTime12.split(":")[0]
    val displayMinutes = timeSnapshot.formattedTime12.split(":")[1]

    val primaryColor = if (isNightRedMode) NightRed else Color.White
    val secondaryColor = if (isNightRedMode) Color(0xFFA31B14) else SecondaryText
    val accentColor = if (isNightRedMode) NightRed else AccentOrange

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Date & Weekday Pill
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "${timeSnapshot.dayName.uppercase()} • ${timeSnapshot.monthName.take(3).uppercase()} ${timeSnapshot.dayNumber}",
                color = accentColor,
                fontSize = 14.sp,
                fontWeight = FontWeight.Bold,
                letterSpacing = 1.sp
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        // Main Digits
        Row(
            verticalAlignment = Alignment.Bottom,
            horizontalArrangement = Arrangement.Center
        ) {
            Text(
                text = displayHours,
                fontSize = 84.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                color = primaryColor,
                lineHeight = 84.sp
            )

            Text(
                text = ":",
                fontSize = 80.sp,
                fontWeight = FontWeight.Bold,
                color = if (timeSnapshot.seconds % 2 == 0) primaryColor else primaryColor.copy(alpha = 0.35f),
                lineHeight = 80.sp,
                modifier = Modifier.padding(horizontal = 4.dp)
            )

            Text(
                text = displayMinutes,
                fontSize = 84.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif,
                color = primaryColor,
                lineHeight = 84.sp
            )

            Spacer(modifier = Modifier.width(6.dp))

            Column(
                horizontalAlignment = Alignment.Start,
                modifier = Modifier.padding(bottom = 12.dp)
            ) {
                if (!is24Hour) {
                    Text(
                        text = timeSnapshot.period,
                        fontSize = 18.sp,
                        fontWeight = FontWeight.Bold,
                        color = secondaryColor
                    )
                }
                if (showSeconds) {
                    Text(
                        text = timeSnapshot.formattedSeconds,
                        fontSize = 20.sp,
                        fontWeight = FontWeight.Bold,
                        fontFamily = FontFamily.Monospace,
                        color = accentColor
                    )
                }
            }
        }
    }
}
