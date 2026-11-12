package com.standbyplus.feature.nightmode

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Slider
import androidx.compose.material3.SliderDefaults
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.standbyplus.core.design.BackgroundOled
import com.standbyplus.core.design.NightRed
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.core.design.SurfaceDark

@Composable
fun NightModeScreen(onNavigateBack: () -> Unit) {
    var redMonochrome by remember { mutableStateOf(true) }
    var autoSchedule by remember { mutableStateOf(true) }
    var burnInProtection by remember { mutableStateOf(true) }
    var brightness by remember { mutableFloatStateOf(40f) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundOled)
            .padding(20.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            IconButton(onClick = onNavigateBack) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back", tint = Color.White)
            }
            Text(
                text = "Night Mode Settings",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "BEDSIDE NIGHT ILLUMINATION",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SecondaryText,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(18.dp))
                .background(SurfaceDark)
                .border(1.dp, Color(0xFF282C38), RoundedCornerShape(18.dp))
                .padding(16.dp)
        ) {
            Column(verticalArrangement = Arrangement.spacedBy(16.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Apple Red Shift Monochrome", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Shifts display to warm red tint for sleep", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = redMonochrome,
                        onCheckedChange = { redMonochrome = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = NightRed, checkedTrackColor = Color(0xFF2E0707))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Automatic Night Schedule", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Active between 10:00 PM and 7:00 AM", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = autoSchedule,
                        onCheckedChange = { autoSchedule = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = NightRed, checkedTrackColor = Color(0xFF2E0707))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "AMOLED Burn-In Protection", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Subtle 2px shift every 60s prevents screen wear", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = burnInProtection,
                        onCheckedChange = { burnInProtection = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = NightRed, checkedTrackColor = Color(0xFF2E0707))
                    )
                }

                Column {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(text = "Ultra-Dim Level", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "${brightness.toInt()}%", fontSize = 14.sp, fontWeight = FontWeight.Bold, color = NightRed)
                    }
                    Slider(
                        value = brightness,
                        onValueChange = { brightness = it },
                        valueRange = 10f..100f,
                        colors = SliderDefaults.colors(thumbColor = NightRed, activeTrackColor = NightRed)
                    )
                }
            }
        }
    }
}
