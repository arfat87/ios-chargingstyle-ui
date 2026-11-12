package com.standbyplus.core.navigation

sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Dashboard : Screen("dashboard")
    object ClockStudio : Screen("clock_studio")
    object WidgetStudio : Screen("widget_studio")
    object PhotoStudio : Screen("photo_studio")
    object NightMode : Screen("night_mode")
    object ThemeStore : Screen("theme_store")
    object Automation : Screen("automation")
    object GeneralSettings : Screen("general_settings")
}
