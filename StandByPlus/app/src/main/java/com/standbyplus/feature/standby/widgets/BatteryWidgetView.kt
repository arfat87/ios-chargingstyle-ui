package com.standbyplus.feature.standby.widgets

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.standbyplus.core.design.AccentGreen
import com.standbyplus.core.design.NightRed
import com.standbyplus.core.design.SecondaryText

@Composable
fun BatteryWidgetView(
    batteryLevel: Int,
    isCharging: Boolean,
    chargingWatts: Int,
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    val textColor = if (isNightRedMode) NightRed else Color.White
    val subTextColor = if (isNightRedMode) Color(0xFFA31B14) else SecondaryText
    val chargeGreen = if (isNightRedMode) NightRed else AccentGreen

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null,
                    tint = chargeGreen
                )
                Text(
                    text = if (isCharging) "Charging" else "On Battery",
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
            }

            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(20.dp))
                    .background(if (isNightRedMode) Color(0xFF2E0707) else Color(0xFF132B1A))
                    .padding(horizontal = 8.dp, vertical = 4.dp)
            ) {
                Text(
                    text = if (isCharging) "${chargingWatts}W ULTRA" else "NORMAL",
                    fontSize = 11.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = chargeGreen
                )
            }
        }

        // Center Ring & Percentage
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Box(
                modifier = Modifier.size(90.dp),
                contentAlignment = Alignment.Center
            ) {
                Canvas(modifier = Modifier.fillMaxSize()) {
                    val strokeW = 18f
                    val sweep = (batteryLevel / 100f) * 360f

                    // Background track
                    drawCircle(
                        color = if (isNightRedMode) Color(0xFF2E0707) else Color(0xFF222632),
                        style = Stroke(width = strokeW)
                    )

                    // Active Arc
                    drawArc(
                        color = chargeGreen,
                        startAngle = -90f,
                        sweepAngle = sweep,
                        useCenter = false,
                        style = Stroke(width = strokeW, cap = StrokeCap.Round)
                    )
                }

                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null,
                    tint = chargeGreen,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(20.dp))

            Column {
                Text(
                    text = "$batteryLevel%",
                    fontSize = 38.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = textColor,
                    lineHeight = 38.sp
                )
                Text(
                    text = if (isCharging) "28m until full" else "12h remaining",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = subTextColor
                )
            }
        }

        // Footer Health Info
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(text = "Optimal Battery Health", fontSize = 11.sp, color = subTextColor)
            Text(text = "28°C Normal", fontSize = 11.sp, color = subTextColor)
        }
    }
}
