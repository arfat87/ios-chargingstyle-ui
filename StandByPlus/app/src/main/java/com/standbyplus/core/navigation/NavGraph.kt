package com.standbyplus.core.navigation

import android.content.Intent
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.standbyplus.app.StandByActivity
import com.standbyplus.domain.model.ClockStyle
import com.standbyplus.domain.model.WidgetItem
import com.standbyplus.feature.automation.AutomationScreen
import com.standbyplus.feature.clockstudio.ClockStudioScreen
import com.standbyplus.feature.dashboard.DashboardScreen
import com.standbyplus.feature.nightmode.NightModeScreen
import com.standbyplus.feature.onboarding.OnboardingScreen
import com.standbyplus.feature.onboarding.SplashScreen
import com.standbyplus.feature.photostudio.PhotoStudioScreen
import com.standbyplus.feature.settings.GeneralSettingsScreen
import com.standbyplus.feature.themestore.ThemeStoreScreen
import com.standbyplus.feature.widgetstudio.WidgetStudioScreen

@Composable
fun StandByNavGraph(
    navController: NavHostController = rememberNavController(),
    onClockStyleChanged: (ClockStyle) -> Unit = {},
    onWidgetChanged: (WidgetItem) -> Unit = {}
) {
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = Screen.Splash.route
    ) {
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateNext = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onFinish = {
                    navController.navigate(Screen.Dashboard.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }

        composable(Screen.Dashboard.route) {
            DashboardScreen(
                onStartStandBy = {
                    val intent = Intent(context, StandByActivity::class.java)
                    context.startActivity(intent)
                },
                onNavigateClockStudio = { navController.navigate(Screen.ClockStudio.route) },
                onNavigateWidgetStudio = { navController.navigate(Screen.WidgetStudio.route) },
                onNavigatePhotoStudio = { navController.navigate(Screen.PhotoStudio.route) },
                onNavigateNightMode = { navController.navigate(Screen.NightMode.route) },
                onNavigateThemeStore = { navController.navigate(Screen.ThemeStore.route) },
                onNavigateAutomation = { navController.navigate(Screen.Automation.route) },
                onNavigateSettings = { navController.navigate(Screen.GeneralSettings.route) }
            )
        }

        composable(Screen.ClockStudio.route) {
            ClockStudioScreen(
                onSelectClock = { onClockStyleChanged(it) },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.WidgetStudio.route) {
            WidgetStudioScreen(
                onSelectWidget = { onWidgetChanged(it) },
                onNavigateBack = { navController.popBackStack() }
            )
        }

        composable(Screen.PhotoStudio.route) {
            PhotoStudioScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.NightMode.route) {
            NightModeScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.ThemeStore.route) {
            ThemeStoreScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.Automation.route) {
            AutomationScreen(onNavigateBack = { navController.popBackStack() })
        }

        composable(Screen.GeneralSettings.route) {
            GeneralSettingsScreen(onNavigateBack = { navController.popBackStack() })
        }
    }
}
