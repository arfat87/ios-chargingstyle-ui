package com.standbyplus.feature.settings

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
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
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
import com.standbyplus.core.design.AccentBlue
import com.standbyplus.core.design.BackgroundOled
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.core.design.SurfaceDark

@Composable
fun GeneralSettingsScreen(onNavigateBack: () -> Unit) {
    var is24Hour by remember { mutableStateOf(false) }
    var showSeconds by remember { mutableStateOf(true) }
    var hapticFeedback by remember { mutableStateOf(true) }

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
                text = "General Settings",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "DISPLAY & TIME PREFERENCES",
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
                        Text(text = "24-Hour Military Time", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Switch from 12-hour AM/PM format", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = is24Hour,
                        onCheckedChange = { is24Hour = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = AccentBlue, checkedTrackColor = Color(0xFF1E283A))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Show Live Seconds", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Smooth realtime second updates", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = showSeconds,
                        onCheckedChange = { showSeconds = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = AccentBlue, checkedTrackColor = Color(0xFF1E283A))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Haptic Tactile Feedback", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Vibrates on widget tap and clock flip", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = hapticFeedback,
                        onCheckedChange = { hapticFeedback = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = AccentBlue, checkedTrackColor = Color(0xFF1E283A))
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "ABOUT STANDBY+",
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
            Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
                Text(text = "StandBy+ Premium Edition", fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                Text(text = "Version 1.0.0 (Build 2026.11)", fontSize = 13.sp, color = SecondaryText)
                Text(text = "Architecture: Clean Architecture + MVVM + Jetpack Compose", fontSize = 12.sp, color = SecondaryText)
                Text(text = "Native Android ScreenSaver & Charging Automation", fontSize = 12.sp, color = SecondaryText)
            }
        }
    }
}
