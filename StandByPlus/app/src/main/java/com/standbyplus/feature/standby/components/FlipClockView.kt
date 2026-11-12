package com.standbyplus.feature.standby.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.standbyplus.core.design.NightRed
import com.standbyplus.engine.clock.TimeSnapshot

@Composable
fun FlipClockView(
    timeSnapshot: TimeSnapshot,
    is24Hour: Boolean,
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    val displayHours = if (is24Hour) timeSnapshot.formattedTime24.split(":")[0] else timeSnapshot.formattedTime12.split(":")[0]
    val displayMinutes = timeSnapshot.formattedTime12.split(":")[1]

    Row(
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically
    ) {
        FlipCard(value = displayHours, label = "HOURS", isNightRedMode = isNightRedMode)

        Spacer(modifier = Modifier.width(12.dp))

        // Center Colon Dots
        Column(
            verticalArrangement = Arrangement.spacedBy(16.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(50))
                    .background(if (isNightRedMode) NightRed else Color.White)
            )
            Box(
                modifier = Modifier
                    .size(10.dp)
                    .clip(RoundedCornerShape(50))
                    .background(if (isNightRedMode) NightRed else Color.White)
            )
        }

        Spacer(modifier = Modifier.width(12.dp))

        FlipCard(value = displayMinutes, label = "MINUTES", isNightRedMode = isNightRedMode)

        if (!is24Hour) {
            Spacer(modifier = Modifier.width(8.dp))
            Box(
                modifier = Modifier
                    .padding(bottom = 16.dp)
                    .align(Alignment.Bottom)
                    .clip(RoundedCornerShape(8.dp))
                    .background(if (isNightRedMode) Color(0xFF280707) else Color(0xFF22252F))
                    .border(1.dp, if (isNightRedMode) Color(0xFF4A0A0A) else Color(0xFF333845), RoundedCornerShape(8.dp))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = timeSnapshot.period,
                    color = if (isNightRedMode) NightRed else Color(0xFFA0A4AE),
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun FlipCard(
    value: String,
    label: String,
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    val cardBg = if (isNightRedMode) Color(0xFF1B0404) else Color(0xFF161820)
    val cardBorder = if (isNightRedMode) Color(0xFF420B0B) else Color(0xFF2A2D3A)
    val textColor = if (isNightRedMode) NightRed else Color(0xFFF0F2F6)
    val seamColor = if (isNightRedMode) Color(0xFF0D0202) else Color(0xFF0E0F14)

    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
    ) {
        Box(
            modifier = Modifier
                .width(110.dp)
                .height(130.dp)
                .clip(RoundedCornerShape(16.dp))
                .background(cardBg)
                .border(1.dp, cardBorder, RoundedCornerShape(16.dp)),
            contentAlignment = Alignment.Center
        ) {
            // Text Digits
            Text(
                text = value,
                color = textColor,
                fontSize = 72.sp,
                fontWeight = FontWeight.ExtraBold,
                fontFamily = FontFamily.SansSerif
            )

            // Center Horizontal Split Seam
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(seamColor)
            )
        }

        Spacer(modifier = Modifier.height(6.dp))

        Text(
            text = label,
            fontSize = 11.sp,
            fontWeight = FontWeight.Bold,
            letterSpacing = 1.sp,
            color = if (isNightRedMode) Color(0xFFA31B14) else Color(0xFF757A88)
        )
    }
}
