package com.standbyplus.feature.standby.widgets

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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.domain.model.CalendarItem
import com.standbyplus.engine.clock.TimeSnapshot

@Composable
fun CalendarWidgetView(
    timeSnapshot: TimeSnapshot,
    events: List<CalendarItem>,
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    val textColor = if (isNightRedMode) NightRed else Color.White
    val subTextColor = if (isNightRedMode) Color(0xFFA31B14) else SecondaryText
    val headerRed = if (isNightRedMode) NightRed else Color(0xFFFF453A)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Date Badge Header
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Column(
                modifier = Modifier
                    .clip(RoundedCornerShape(14.dp))
                    .background(if (isNightRedMode) Color(0xFF280707) else Color(0xFF1E212B))
                    .border(1.dp, if (isNightRedMode) Color(0xFF4A0A0A) else Color(0xFF333845), RoundedCornerShape(14.dp))
                    .padding(horizontal = 14.dp, vertical = 6.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = timeSnapshot.monthName.take(3).uppercase(),
                    color = headerRed,
                    fontSize = 12.sp,
                    fontWeight = FontWeight.ExtraBold,
                    letterSpacing = 1.sp
                )
                Text(
                    text = "${timeSnapshot.dayNumber}",
                    color = textColor,
                    fontSize = 32.sp,
                    fontWeight = FontWeight.ExtraBold,
                    lineHeight = 32.sp
                )
            }

            Column {
                Text(
                    text = timeSnapshot.dayName,
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = "2 Events Scheduled",
                    fontSize = 13.sp,
                    color = subTextColor
                )
            }
        }

        // Agenda Items
        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            events.take(2).forEach { event ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isNightRedMode) Color(0xFF1E0505) else Color(0xFF191B24))
                        .padding(8.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Box(
                        modifier = Modifier
                            .width(4.dp)
                            .height(26.dp)
                            .clip(RoundedCornerShape(2.dp))
                            .background(if (isNightRedMode) NightRed else Color(0xFF30D158))
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Column {
                        Text(
                            text = event.title,
                            fontSize = 13.sp,
                            fontWeight = FontWeight.SemiBold,
                            color = textColor,
                            maxLines = 1
                        )
                        Text(
                            text = event.time,
                            fontSize = 11.sp,
                            color = subTextColor
                        )
                    }
                }
            }
        }
    }
}
