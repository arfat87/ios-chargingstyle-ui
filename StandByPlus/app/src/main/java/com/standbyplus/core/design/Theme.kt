package com.standbyplus.core.design

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorScheme = darkColorScheme(
    primary = AccentBlue,
    secondary = AccentOrange,
    tertiary = AccentGreen,
    background = BackgroundOled,
    surface = SurfaceDark,
    surfaceVariant = SurfaceSecondary,
    onPrimary = BackgroundOled,
    onSecondary = BackgroundOled,
    onTertiary = BackgroundOled,
    onBackground = PrimaryText,
    onSurface = PrimaryText,
    onSurfaceVariant = SecondaryText,
    outline = BorderSubtle
)

private val NightRedColorScheme = darkColorScheme(
    primary = NightRed,
    secondary = NightRed,
    tertiary = NightRed,
    background = Color(0xFF0A0202),
    surface = Color(0xFF1E0404),
    surfaceVariant = Color(0xFF280707),
    onPrimary = Color.Black,
    onBackground = NightRed,
    onSurface = NightRed,
    onSurfaceVariant = Color(0xFFA31B14),
    outline = Color(0xFF4A0A0A)
)

@Composable
fun StandByPlusTheme(
    isNightRedMode: Boolean = false,
    content: @Composable () -> Unit
) {
    val colorScheme = if (isNightRedMode) NightRedColorScheme else DarkColorScheme

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}
