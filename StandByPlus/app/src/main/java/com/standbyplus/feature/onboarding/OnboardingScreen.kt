package com.standbyplus.feature.onboarding

import androidx.compose.animation.AnimatedVisibility
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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Bolt
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Nightlight
import androidx.compose.material.icons.filled.ScreenRotation
import androidx.compose.material.icons.filled.Widgets
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.standbyplus.core.design.AccentBlue
import com.standbyplus.core.design.AccentGreen
import com.standbyplus.core.design.AccentOrange
import com.standbyplus.core.design.BackgroundOled
import com.standbyplus.core.design.NightRed
import com.standbyplus.core.design.SecondaryText
import com.standbyplus.core.design.SurfaceDark
import kotlinx.coroutines.delay

@Composable
fun SplashScreen(onNavigateNext: () -> Unit) {
    LaunchedEffect(Unit) {
        delay(1200L)
        onNavigateNext()
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundOled),
        contentAlignment = Alignment.Center
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Box(
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(22.dp))
                    .background(SurfaceDark),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = Icons.Default.Bolt,
                    contentDescription = null,
                    tint = AccentOrange,
                    modifier = Modifier.size(46.dp)
                )
            }

            Spacer(modifier = Modifier.height(18.dp))

            Text(
                text = "StandBy+",
                fontSize = 32.sp,
                fontWeight = FontWeight.ExtraBold,
                color = Color.White
            )

            Text(
                text = "Your Phone. Your Smart Display.",
                fontSize = 14.sp,
                color = SecondaryText,
                modifier = Modifier.padding(top = 4.dp)
            )
        }
    }
}

data class OnboardingStep(
    val title: String,
    val description: String,
    val icon: ImageVector,
    val accentColor: Color
)

@Composable
fun OnboardingScreen(onFinish: () -> Unit) {
    val steps = listOf(
        OnboardingStep(
            "Welcome to StandBy+",
            "Transforms your Android phone into a high-end bedside smart display while charging.",
            Icons.Default.Bolt,
            AccentOrange
        ),
        OnboardingStep(
            "Dynamic Clocks & Widgets",
            "Customizable 3D Analog, Retro Flip, and Digital clocks paired with Live Weather, Calendar, and Music.",
            Icons.Default.Widgets,
            AccentBlue
        ),
        OnboardingStep(
            "Automatic Dock Activation",
            "Automatically detects when your phone is connected to a charger and positioned in landscape orientation.",
            Icons.Default.ScreenRotation,
            AccentGreen
        ),
        OnboardingStep(
            "Red Monochrome Night Mode",
            "Extra-dim bedside illumination with AMOLED black and micro pixel-shifting to protect your OLED screen.",
            Icons.Default.Nightlight,
            NightRed
        ),
        OnboardingStep(
            "Ready to Experience StandBy+",
            "Place your phone on your bedside stand and tap Start StandBy.",
            Icons.Default.Check,
            AccentGreen
        )
    )

    var currentStep by remember { mutableIntStateOf(0) }
    val step = steps[currentStep]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(BackgroundOled)
            .padding(28.dp),
        verticalArrangement = Arrangement.SpaceBetween,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Step Indicators
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            steps.indices.forEach { index ->
                Box(
                    modifier = Modifier
                        .weight(1f)
                        .height(4.dp)
                        .clip(RoundedCornerShape(2.dp))
                        .background(if (index <= currentStep) step.accentColor else Color(0xFF282C38))
                )
            }
        }

        // Center Graphic & Text
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.padding(horizontal = 16.dp)
        ) {
            Box(
                modifier = Modifier
                    .size(96.dp)
                    .clip(RoundedCornerShape(28.dp))
                    .background(SurfaceDark),
                contentAlignment = Alignment.Center
            ) {
                Icon(
                    imageVector = step.icon,
                    contentDescription = null,
                    tint = step.accentColor,
                    modifier = Modifier.size(52.dp)
                )
            }

            Spacer(modifier = Modifier.height(28.dp))

            Text(
                text = step.title,
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = Color.White,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            Text(
                text = step.description,
                fontSize = 15.sp,
                color = SecondaryText,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )
        }

        // Navigation Buttons
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            if (currentStep > 0) {
                OutlinedButton(
                    onClick = { currentStep-- },
                    modifier = Modifier.weight(1f),
                    shape = RoundedCornerShape(16.dp)
                ) {
                    Text(text = "Back", color = Color.White)
                }
            }

            Button(
                onClick = {
                    if (currentStep < steps.size - 1) {
                        currentStep++
                    } else {
                        onFinish()
                    }
                },
                modifier = Modifier.weight(1f),
                shape = RoundedCornerShape(16.dp),
                colors = ButtonDefaults.buttonColors(containerColor = step.accentColor)
            ) {
                Text(
                    text = if (currentStep == steps.size - 1) "Get Started" else "Continue",
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}
