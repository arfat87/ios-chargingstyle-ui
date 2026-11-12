package com.standbyplus.feature.themestore

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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Check
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import com.standbyplus.core.design.BackgroundOled
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.core.design.SurfaceDark

data class ThemeCardModel(val name: String, val tag: String, val primary: Color, val accent: Color)

@Composable
fun ThemeStoreScreen(onNavigateBack: () -> Unit) {
    val themes = listOf(
        ThemeCardModel("AMOLED Minimal", "Default", Color(0xFF08090C), Color(0xFFFFFFFF)),
        ThemeCardModel("Apple Red Shift", "Night Mode", Color(0xFF140202), Color(0xFFFF453A)),
        ThemeCardModel("Midnight Neon", "Vibrant", Color(0xFF030A14), Color(0xFF00F2FE)),
        ThemeCardModel("Retro Split-Flap", "Vintage", Color(0xFF14120E), Color(0xFFD4A373)),
        ThemeCardModel("Cyberpunk Gold", "Luxury", Color(0xFF120E02), Color(0xFFFFD60A))
    )

    var selectedTheme by remember { mutableStateOf(themes[0].name) }

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
                text = "Theme Store",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "PRESET DISPLAY PALETTES",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SecondaryText,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            themes.forEach { theme ->
                val isSelected = theme.name == selectedTheme
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(SurfaceDark)
                        .border(
                            1.5.dp,
                            if (isSelected) theme.accent else Color(0xFF282C38),
                            RoundedCornerShape(18.dp)
                        )
                        .clickable { selectedTheme = theme.name }
                        .padding(16.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Box(
                                modifier = Modifier
                                    .size(36.dp)
                                    .clip(RoundedCornerShape(10.dp))
                                    .background(theme.primary)
                                    .border(1.dp, theme.accent, RoundedCornerShape(10.dp))
                            )
                            Spacer(modifier = Modifier.padding(horizontal = 6.dp))
                            Column {
                                Text(text = theme.name, fontSize = 15.sp, fontWeight = FontWeight.Bold, color = Color.White)
                                Text(text = theme.tag, fontSize = 12.sp, color = SecondaryText)
                            }
                        }

                        if (isSelected) {
                            Icon(imageVector = Icons.Default.Check, contentDescription = null, tint = theme.accent)
                        }
                    }
                }
            }
        }
    }
}
