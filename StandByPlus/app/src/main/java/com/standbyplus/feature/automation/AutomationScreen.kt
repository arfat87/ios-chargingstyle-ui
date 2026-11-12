package com.standbyplus.feature.automation

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
import com.standbyplus.core.design.AccentGreen
import com.standbyplus.core.design.BackgroundOled
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.core.design.SurfaceDark

@Composable
fun AutomationScreen(onNavigateBack: () -> Unit) {
    var activateOnCharging by remember { mutableStateOf(true) }
    var requireLandscape by remember { mutableStateOf(true) }
    var exitOnUnplugged by remember { mutableStateOf(false) }
    var enableScreensaver by remember { mutableStateOf(true) }

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
                text = "Automation Rules",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "CHARGING & TILT TRIGGERS",
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
                        Text(text = "Activate on Charger Connected", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Detects wireless, MagSafe & cable charging", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = activateOnCharging,
                        onCheckedChange = { activateOnCharging = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = AccentGreen, checkedTrackColor = Color(0xFF132B1A))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Require Landscape Orientation", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Only opens when phone is tilted horizontally", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = requireLandscape,
                        onCheckedChange = { requireLandscape = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = AccentGreen, checkedTrackColor = Color(0xFF132B1A))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Exit when Unplugged", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Automatically closes StandBy on disconnect", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = exitOnUnplugged,
                        onCheckedChange = { exitOnUnplugged = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = AccentGreen, checkedTrackColor = Color(0xFF132B1A))
                    )
                }

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = "Android Screensaver (Daydream)", fontSize = 15.sp, fontWeight = FontWeight.SemiBold, color = Color.White)
                        Text(text = "Registers StandBy+ as system charging display", fontSize = 12.sp, color = SecondaryText)
                    }
                    Switch(
                        checked = enableScreensaver,
                        onCheckedChange = { enableScreensaver = it },
                        colors = SwitchDefaults.colors(checkedThumbColor = AccentGreen, checkedTrackColor = Color(0xFF132B1A))
                    )
                }
            }
        }
    }
}
