package com.standbyplus.feature.dashboard

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AutoAwesome
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Image
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.Schedule
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.filled.Tune
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.standbyplus.core.design.AccentBlue
import com.standbyplus.core.design.AccentGreen
import com.standbyplus.core.design.AccentOrange
import com.standbyplus.core.design.BackgroundOled
import com.standbyplus.core.design.NightRed
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.core.design.SurfaceDark
import com.standbyplus.core.design.SurfaceSecondary

@Composable
fun DashboardScreen(
    onStartStandBy: () -> Unit,
    onNavigateClockStudio: () -> Unit,
    onNavigateWidgetStudio: () -> Unit,
    onNavigatePhotoStudio: () -> Unit,
    onNavigateNightMode: () -> Unit,
    onNavigateThemeStore: () -> Unit,
    onNavigateAutomation: () -> Unit,
    onNavigateSettings: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundOled)
            .verticalScroll(rememberScrollState())
            .padding(20.dp)
    ) {
        // App Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(
                    text = "StandBy+",
                    fontSize = 28.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = Color.White
                )
                Text(
                    text = "Your Phone. Your Smart Display.",
                    fontSize = 13.sp,
                    color = SecondaryText
                )
            }

            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(50))
                    .background(SurfaceDark)
                    .clickable { onNavigateSettings() },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Settings,
                    contentDescription = "Settings",
                    tint = Color.White,
                    modifier = Modifier.size(20.dp)
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Main CTA: Start StandBy
        Button(
            onClick = onStartStandBy,
            modifier = Modifier
                .fillMaxWidth()
                .height(64.dp),
            shape = RoundedCornerShape(20.dp),
            colors = ButtonDefaults.buttonColors(containerColor = AccentOrange)
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = Color.Black,
                    modifier = Modifier.size(28.dp)
                )
                Spacer(modifier = Modifier.width(8.dp))
                Text(
                    text = "Start StandBy Display",
                    color = Color.Black,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        // Charging & Automation Status Banner
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(20.dp))
                .background(SurfaceDark)
                .border(1.dp, Color(0xFF282C38), RoundedCornerShape(20.dp))
                .padding(16.dp)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(14.dp)
            ) {
                Box(
                    modifier = Modifier
                        .size(48.dp)
                        .clip(RoundedCornerShape(14.dp))
                        .background(Color(0xFF132B1A)),
                    contentAlignment = Alignment.Center
                ) {
                    Icon(
                        imageVector = Icons.Default.Bolt,
                        contentDescription = null,
                        tint = AccentGreen,
                        modifier = Modifier.size(26.dp)
                    )
                }

                Column {
                    Text(
                        text = "Dock & Charging Trigger Active",
                        fontSize = 15.sp,
                        fontWeight = FontWeight.Bold,
                        color = Color.White
                    )
                    Text(
                        text = "Auto-launches in landscape when plugged in",
                        fontSize = 12.sp,
                        color = SecondaryText
                    )
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "Studio & Customization",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold,
            color = Color.White
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Grid of Studio shortcuts
        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StudioCard(
                    title = "Clock Studio",
                    subtitle = "Digital, Analog, Flip",
                    icon = Icons.Default.Schedule,
                    tint = AccentOrange,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateClockStudio
                )
                StudioCard(
                    title = "Widget Studio",
                    subtitle = "Weather, Calendar, Battery",
                    icon = Icons.Default.Widgets,
                    tint = AccentBlue,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateWidgetStudio
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StudioCard(
                    title = "Photo Studio",
                    subtitle = "Albums & Slideshow",
                    icon = Icons.Default.Image,
                    tint = AccentGreen,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigatePhotoStudio
                )
                StudioCard(
                    title = "Night Mode",
                    subtitle = "Red Shift & Burn-In",
                    icon = Icons.Default.Nightlight,
                    tint = NightRed,
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateNightMode
                )
            }

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                StudioCard(
                    title = "Theme Store",
                    subtitle = "AMOLED, Neon, Retro",
                    icon = Icons.Default.AutoAwesome,
                    tint = Color(0xFFFFD60A),
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateThemeStore
                )
                StudioCard(
                    title = "Automation",
                    subtitle = "Triggers & Schedule",
                    icon = Icons.Default.Tune,
                    tint = Color(0xFFBF5AF2),
                    modifier = Modifier.weight(1f),
                    onClick = onNavigateAutomation
                )
            }
        }

        Spacer(modifier = Modifier.height(30.dp))
    }
}

@Composable
private fun StudioCard(
    title: String,
    subtitle: String,
    icon: ImageVector,
    tint: Color,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Box(
        modifier = modifier
            .clip(RoundedCornerShape(20.dp))
            .background(SurfaceDark)
            .border(1.dp, Color(0xFF282C38), RoundedCornerShape(20.dp))
            .clickable { onClick() }
            .padding(16.dp)
    ) {
        Column {
            Box(
                modifier = Modifier
                    .size(40.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(SurfaceSecondary),
                contentAlignment = Alignment.Center
            ) {
                Icon(imageVector = icon, contentDescription = null, tint = tint, modifier = Modifier.size(22.dp))
            }
            Spacer(modifier = Modifier.height(14.dp))
            Text(text = title, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
            Text(text = subtitle, fontSize = 11.sp, color = SecondaryText, maxLines = 1)
        }
    }
}
