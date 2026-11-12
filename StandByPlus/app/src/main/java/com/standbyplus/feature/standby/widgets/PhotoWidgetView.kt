package com.standbyplus.feature.standby.widgets

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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Image
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.standbyplus.core.design.NightRed
import com.standbyplus.engine.clock.TimeSnapshot

@Composable
fun PhotoWidgetView(
    timeSnapshot: TimeSnapshot,
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .clip(RoundedCornerShape(20.dp))
            .background(if (isNightRedMode) Color(0xFF1E0505) else Color(0xFF1B202D))
    ) {
        // Landscape Photo Frame Simulator
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = timeSnapshot.formattedTime12,
                fontSize = 28.sp,
                fontWeight = FontWeight.ExtraBold,
                color = if (isNightRedMode) NightRed else Color.White
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "Yosemite Valley Sunset",
                        fontSize = 13.sp,
                        fontWeight = FontWeight.Bold,
                        color = if (isNightRedMode) NightRed else Color.White
                    )
                    Text(
                        text = "October 2026",
                        fontSize = 11.sp,
                        color = if (isNightRedMode) Color(0xFFA31B14) else Color(0xFFA0A4AE)
                    )
                }

                Icon(
                    imageVector = Icons.Default.Image,
                    contentDescription = null,
                    tint = if (isNightRedMode) NightRed else Color.White
                )
            }
        }
    }
}
