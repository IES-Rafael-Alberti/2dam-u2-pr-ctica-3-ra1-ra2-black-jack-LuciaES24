package com.lespsan543.navegacin.Model

import com.lespsan543.blackjack.Clases.Jugador

sealed class Routes(val route:String) {
    object PantallaInicio : Routes("PantallaInicio")
    object Pantalla1Jugador : Routes("Pantalla1Jugador")

    object PedirJugador1 : Routes("PedirJugador1")
    object PedirJugador2 : Routes("PedirJugador2/{jugadores}"){
        fun createRoute(jugadores:MutableList<Jugador>) = "PedirJugador2/$jugadores"
    }
    object PantallaMultiJugador : Routes("PantallaMultiJugador/{jugadores}"){
        fun createRoute(jugadores:MutableList<Jugador>) = "PantallaMultiJugador/$jugadores"
    }

}