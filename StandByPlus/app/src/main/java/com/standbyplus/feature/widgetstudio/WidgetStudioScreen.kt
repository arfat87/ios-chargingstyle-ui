package com.standbyplus.feature.widgetstudio

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
import com.standbyplus.core.design.AccentBlue
import com.standbyplus.core.design.BackgroundOled
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.core.design.SurfaceDark
import com.standbyplus.domain.model.WidgetItem

@Composable
fun WidgetStudioScreen(
    currentWidget: WidgetItem = WidgetItem.WEATHER,
    onSelectWidget: (WidgetItem) -> Unit,
    onNavigateBack: () -> Unit
) {
    var selectedWidget by remember { mutableStateOf(currentWidget) }
    val widgets = WidgetItem.values()

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
                text = "Widget Studio",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                modifier = Modifier.padding(start = 8.dp)
            )
        }

        Spacer(modifier = Modifier.height(20.dp))

        Text(
            text = "SELECT COMPANION WIDGET",
            fontSize = 12.sp,
            fontWeight = FontWeight.Bold,
            color = SecondaryText,
            letterSpacing = 1.sp
        )

        Spacer(modifier = Modifier.height(14.dp))

        Column(verticalArrangement = Arrangement.spacedBy(12.dp)) {
            widgets.forEach { widget ->
                val isSelected = widget == selectedWidget
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(18.dp))
                        .background(SurfaceDark)
                        .border(
                            1.5.dp,
                            if (isSelected) AccentBlue else Color(0xFF282C38),
                            RoundedCornerShape(18.dp)
                        )
                        .clickable {
                            selectedWidget = widget
                            onSelectWidget(widget)
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
                                text = widget.displayName,
                                fontSize = 16.sp,
                                fontWeight = FontWeight.Bold,
                                color = if (isSelected) AccentBlue else Color.White
                            )
                            Text(
                                text = when (widget) {
                                    WidgetItem.WEATHER -> "Live temperature, conditions & hourly timeline"
                                    WidgetItem.CALENDAR -> "Today's date & schedule agenda items"
                                    WidgetItem.BATTERY -> "Charging ring, wattage & battery health"
                                    WidgetItem.MUSIC -> "Media controls & animated frequency visualizer"
                                    WidgetItem.PHOTO -> "Ambient landscape photos with clock overlay"
                                    WidgetItem.REMINDERS -> "Interactive bedside habit checklist"
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
                                    .background(AccentBlue),
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
