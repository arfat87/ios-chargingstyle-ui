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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MusicNote
import androidx.compose.material.icons.filled.Pause
import androidx.compose.material.icons.filled.PlayArrow
import androidx.compose.material.icons.filled.SkipNext
import androidx.compose.material.icons.filled.SkipPrevious
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.standbyplus.core.design.NightRed
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.domain.model.MusicState

@Composable
fun MusicWidgetView(
    musicState: MusicState,
    isNightRedMode: Boolean,
    modifier: Modifier = Modifier
) {
    var isPlaying by remember { mutableStateOf(musicState.isPlaying) }

    val textColor = if (isNightRedMode) NightRed else Color.White
    val subTextColor = if (isNightRedMode) Color(0xFFA31B14) else SecondaryText
    val accentRed = if (isNightRedMode) NightRed else Color(0xFFFF375F)

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        // Track Header with Album Icon
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Box(
                modifier = Modifier
                    .size(54.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .background(if (isNightRedMode) Color(0xFF2E0707) else Color(0xFF242735)),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.MusicNote,
                    contentDescription = null,
                    tint = accentRed,
                    modifier = Modifier.size(28.dp)
                )
            }

            Spacer(modifier = Modifier.width(14.dp))

            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = musicState.title,
                    fontSize = 15.sp,
                    fontWeight = FontWeight.Bold,
                    color = textColor,
                    maxLines = 1
                )
                Text(
                    text = "${musicState.artist} • ${musicState.album}",
                    fontSize = 12.sp,
                    color = subTextColor,
                    maxLines = 1
                )
            }
        }

        // Progress Bar
        Column {
            Box(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .clip(RoundedCornerShape(2.dp))
                    .background(if (isNightRedMode) Color(0xFF280707) else Color(0xFF262936))
            ) {
                Box(
                    modifier = Modifier
                        .fillMaxWidth(0.38f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(if (isNightRedMode) NightRed else Color.White)
                )
            }
            Spacer(modifier = Modifier.height(4.dp))
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(text = "1:14", fontSize = 10.sp, color = subTextColor)
                Text(text = "-2:16", fontSize = 10.sp, color = subTextColor)
            }
        }

        // Playback Controls
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Icon(
                imageVector = Icons.Default.SkipPrevious,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(28.dp)
            )

            Spacer(modifier = Modifier.width(28.dp))

            Box(
                modifier = Modifier
                    .size(44.dp)
                    .clip(RoundedCornerShape(50))
                    .background(textColor)
                    .clickable { isPlaying = !isPlaying },
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = if (isPlaying) Icons.Default.Pause else Icons.Default.PlayArrow,
                    contentDescription = null,
                    tint = if (isNightRedMode) Color.Black else Color(0xFF14161B),
                    modifier = Modifier.size(24.dp)
                )
            }

            Spacer(modifier = Modifier.width(28.dp))

            Icon(
                imageVector = Icons.Default.SkipNext,
                contentDescription = null,
                tint = textColor,
                modifier = Modifier.size(28.dp)
            )
        }
    }
}
