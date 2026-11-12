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
import androidx.compose.material.icons.filled.WbSunny
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
import com.standbyplus.core.design.AccentBlue
import com.standbyplus.core.design.AccentOrange
import com.standbyplus.core.design.NightRed
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.domain.model.WeatherInfo

@Composable
fun WeatherWidgetView(
    weather: WeatherInfo,
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    val textColor = if (isNightRedMode) NightRed else Color.White
    val subTextColor = if (isNightRedMode) Color(0xFFA31B14) else SecondaryText
    val iconColor = if (isNightRedMode) NightRed else AccentOrange

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Top Header
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.Top
        ) {
            Column {
                Text(
                    text = weather.city,
                    fontSize = 17.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor
                )
                Text(
                    text = weather.condition,
                    fontSize = 13.sp,
                    fontWeight = FontWeight.Normal,
                    color = subTextColor
                )
            }

            Column(horizontalAlignment = Alignment.End) {
                Text(
                    text = "${weather.temperature}°",
                    fontSize = 44.sp,
                    fontWeight = FontWeight.ExtraBold,
                    color = textColor,
                    lineHeight = 44.sp
                )
                Text(
                    text = "H:${weather.highTemp}° L:${weather.lowTemp}°",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = subTextColor
                )
            }
        }

        // Stats Card Pill
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .clip(RoundedCornerShape(12.dp))
                .background(if (isNightRedMode) Color(0xFF240606) else Color(0xFF1E212B))
                .padding(horizontal = 12.dp, vertical = 8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Row(verticalAlignment = Alignment.CenterVertically) {
                    Icon(
                        imageVector = Icons.Default.WbSunny,
                        contentDescription = null,
                        tint = iconColor
                    )
                    Text(
                        text = " UV 4 Moderate",
                        fontSize = 12.sp,
                        fontWeight = FontWeight.Medium,
                        color = subTextColor
                    )
                }

                Text(
                    text = "${weather.humidity}% Humidity",
                    fontSize = 12.sp,
                    fontWeight = FontWeight.Medium,
                    color = if (isNightRedMode) NightRed else AccentBlue
                )
            }
        }

        // Hourly Preview
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            listOf(
                Triple("Now", 24, "Clear"),
                Triple("10 PM", 23, "Clear"),
                Triple("11 PM", 22, "Cloudy"),
                Triple("12 AM", 21, "Clear")
            ).forEach { (time, temp, _) ->
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(text = time, fontSize = 11.sp, color = subTextColor, fontWeight = FontWeight.SemiBold)
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(text = "${temp}°", fontSize = 14.sp, color = textColor, fontWeight = FontWeight.Bold)
                }
            }
        }
    }
}
