package com.standbyplus.feature.clockstudio

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
import com.standbyplus.core.design.AccentOrange
import com.standbyplus.core.design.BackgroundOled
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.core.design.SurfaceDark
import com.standbyplus.domain.model.ClockStyle

@Composable
fun ClockStudioScreen(
    currentClock: ClockStyle = ClockStyle.DIGITAL,
    onSelectClock: (ClockStyle) -> Unit,
    onNavigateBack: () -> Unit
) {
    var selectedClock by remember { mutableStateOf(currentClock) }
    val styles = ClockStyle.values()

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
                text = "Clock Studio",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "SELECT CLOCK STYLE",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SecondaryText,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            styles.forEach { style ->
                val isSelected = style == selectedClock
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(SurfaceDark)
                        .border(
                            1.5.dp,
                            if (isSelected) AccentOrange else Color(0xFF282C38),
                            RoundedCornerShape(18.dp)
                        )
                        .clickable {
                            selectedClock = style
                            onSelectClock(style)
                        }
                        .padding(18.dp)
                ) {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.SpaceBetween,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column {
                            Text(
                                text = style.displayName,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) AccentOrange else Color.White
                            )
                            Text(
                                text = when (style) {
                                    ClockStyle.DIGITAL -> "Bold typographic digits with live seconds"
                                    ClockStyle.ANALOG -> "Sweeping second hand with metallic bezel"
                                    ClockStyle.FLIP -> "Retro 3D split-flap cards"
                                    ClockStyle.MINIMAL -> "Clean modern typography"
                                    ClockStyle.WORLD -> "Multi-timezone international clocks"
                                },
                                fontSize = 12.sp,
                                color = SecondaryText,
                                modifier = Modifier.padding(top = 2.dp)
                            )
                        }

                        if (isSelected) {
                            Box(
                                modifier = Modifier
                                    .size(28.dp)
                                    .clip(RoundedCornerShape(50))
                                    .background(AccentOrange),
                                contentAlignment = Alignment.Center
                            ) {
                                Icon(
                                    imageVector = Icons.Default.Check,
                                    contentDescription = null,
                                    tint = Color.Black,
                                    modifier = Modifier.size(18.dp)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}
