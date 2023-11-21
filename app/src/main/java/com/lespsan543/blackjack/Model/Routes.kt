package com.lespsan543.navegacin.Model


sealed class Routes(val route:String) {
    object PantallaInicio : Routes("PantallaInicio")
    object Pantalla1Jugador : Routes("Pantalla1Jugador")

    object Multiplayer : Routes("MultiPlayer")

}