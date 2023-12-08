package com.lespsan543.blackjack.data


sealed class Routes(val route:String) {
    object PantallaInicio : Routes("PantallaInicio")
    object Pantalla1Jugador : Routes("Pantalla1Jugador")

    object Multiplayer : Routes("MultiPlayer")

}