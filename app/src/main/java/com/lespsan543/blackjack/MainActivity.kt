package com.lespsan543.blackjack

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
import com.lespsan543.blackjack.Screens.Multiplayer
import com.lespsan543.blackjack.ui.theme.BlackJackTheme
import com.lespsan543.cartas.Clases.Baraja
import com.lespsan543.cartas.Screens.PantallaPrincipal
import com.lespsan543.navegacin.Model.Routes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BlackJackTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Baraja.crearBaraja()
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.PantallaInicio.route){
                        composable(Routes.PantallaInicio.route){ PantallaPrincipal(navController) }
                        composable(Routes.Multiplayer.route){ Multiplayer(navController) }

                    }
                }
            }
        }
    }
}
