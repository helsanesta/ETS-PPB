package com.example.ets_ppb

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.toRoute
import com.example.ets_ppb.layoutScreen.LoginScreen
import com.example.ets_ppb.layoutScreen.ScheduleOfTrains
import com.example.ets_ppb.layoutScreen.SearchTrainSchedule
import com.example.ets_ppb.ui.theme.ETSPPBTheme
import kotlinx.serialization.Serializable

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ETSPPBTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(
                        navController = navController,
                        startDestination = ScreenA
                    ) {
                        composable<ScreenA> {
                            //GreetingA(navController, "Android")
                            LoginScreen(navController)
                        }
                        composable<ScreenB> {
                            val args = it.toRoute<ScreenB>()
                            SearchTrainSchedule(navController, args)
                        }
                        composable<ScreenC>{
                            val args = it.toRoute<ScreenC>()
                            ScheduleOfTrains(navController, args)
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object ScreenA

@Serializable
data class ScreenB(
    val name: String,
)

@Serializable
data class ScreenC(
    val departureStation: String,
    val arrivalStation: String,
    val totalPassangers: Int,
    val dateDeparture: String
)