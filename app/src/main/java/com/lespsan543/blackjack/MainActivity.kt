package com.lespsan543.blackjack

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.lespsan543.blackjack.Screens.PantallaJugador1
import com.lespsan543.blackjack.Screens.PantallaJugador2
import com.lespsan543.blackjack.Screens.PedirJugador1
import com.lespsan543.blackjack.Screens.PedirJugador2
import com.lespsan543.blackjack.ui.theme.BlackJackTheme
import com.lespsan543.cartas.Screens.Pantalla1Jugador
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
                    val navController = rememberNavController()
                    NavHost(navController = navController, startDestination = Routes.PantallaInicio.route){
                        composable(Routes.PantallaInicio.route){ PantallaPrincipal(navController) }
                        composable(Routes.Pantalla1Jugador.route){ Pantalla1Jugador(navController) }
                        composable(Routes.PedirJugador1.route){ PedirJugador1(navController) }
                        composable(Routes.PedirJugador2.route,
                            arguments = listOf(navArgument("nombreJugador1"){ type=
                                NavType.StringType})
                        ){backStackEntry ->
                            PedirJugador2(navController,
                                backStackEntry.arguments?.getString("nombreJugador1") ?: ""
                            )
                        }
                        composable(Routes.PantallaJugador1.route,
                            arguments = listOf(navArgument("nombreJugadores"){ type=
                                NavType.StringType})
                        ){backStackEntry ->
                            PantallaJugador1(navController,
                                backStackEntry.arguments?.getString("nombreJugadores") ?: ""
                            )
                        }
                        composable(Routes.PantallaJugador2.route,
                            arguments = listOf(navArgument("nombreJugadores"){ type=
                                NavType.StringType})
                        ){backStackEntry ->
                            PantallaJugador2(navController,
                                backStackEntry.arguments?.getString("nombreJugadores") ?: ""
                            )
                        }

                    }
                }
            }
        }
    }
}
