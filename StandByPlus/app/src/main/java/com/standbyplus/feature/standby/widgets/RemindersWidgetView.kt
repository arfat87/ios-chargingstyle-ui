package com.standbyplus.feature.standby.widgets

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.RadioButtonUnchecked
import androidx.compose.material3.Icon
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.standbyplus.core.design.AccentGreen
import com.standbyplus.core.design.NightRed
import com.standbyplus.core.design.SecondaryText

data class ReminderItem(val id: Int, val text: String, var isDone: Boolean)

@Composable
fun RemindersWidgetView(
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    var items by remember {
        mutableStateOf(
            listOf(
                ReminderItem(1, "Set morning alarm 6:30 AM", true),
                ReminderItem(2, "Drink night glass of water", true),
                ReminderItem(3, "StandBy+ Night mode active", false)
            )
        )
    }

    val textColor = if (isNightRedMode) NightRed else Color.White
    val subTextColor = if (isNightRedMode) Color(0xFFA31B14) else SecondaryText
    val checkGreen = if (isNightRedMode) NightRed else AccentGreen

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(text = "Bedside Tasks", fontSize = 16.sp, fontWeight = FontWeight.Bold, color = textColor)
            Text(
                text = "${items.count { it.isDone }}/${items.size} Done",
                fontSize = 12.sp,
                fontWeight = FontWeight.Bold,
                color = subTextColor
            )
        }

        Column(verticalArrangement = Arrangement.spacedBy(8.dp)) {
            items.forEach { item ->
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(10.dp))
                        .background(if (isNightRedMode) Color(0xFF1E0505) else Color(0xFF191B24))
                        .clickable {
                            items = items.map {
                                if (it.id == item.id) it.copy(isDone = !it.isDone) else it
                            }
                        }
                        .padding(horizontal = 10.dp, vertical = 7.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = if (item.isDone) Icons.Default.CheckCircle else Icons.Default.RadioButtonUnchecked,
                        contentDescription = null,
                        tint = if (item.isDone) checkGreen else subTextColor,
                        modifier = Modifier.size(18.dp)
                    )

                    Spacer(modifier = Modifier.width(10.dp))

                    Text(
                        text = item.text,
                        fontSize = 13.sp,
                        color = if (item.isDone) subTextColor else textColor,
                        textDecoration = if (item.isDone) TextDecoration.LineThrough else TextDecoration.None,
                        maxLines = 1
                    )
                }
            }
        }

        Text(
            text = "Tap any reminder to toggle",
            fontSize = 11.sp,
            color = subTextColor
        )
    }
}
