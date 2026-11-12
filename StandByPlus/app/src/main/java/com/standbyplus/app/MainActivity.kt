package com.standbyplus.app

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.standbyplus.core.design.StandByPlusTheme
import com.standbyplus.core.navigation.StandByNavGraph

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            StandByPlusTheme {
                StandByNavGraph()
            }
        }
    }
}
