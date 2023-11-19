package com.lespsan543.navegacin.Model

sealed class Routes(val route:String) {
    object PantallaInicio : Routes("PantallaInicio")
    object Pantalla1Jugador : Routes("Pantalla1Jugador")

    object PedirJugador1 : Routes("PedirJugador1")
    object PedirJugador2 : Routes("PedirJugador2/{nombreJugador1}"){
        fun createRoute(nombreJugador1:String) = "PedirJugador2/$nombreJugador1"
    }
    object PantallaJugador1 : Routes("PantallaJugador1/{nombreJugadores}"){
        fun createRoute(nombreJugadores:String) = "PantallaJugador1/$nombreJugadores"
    }
    object PantallaJugador2 : Routes("PantallaJugador2"){
        fun createRoute(nombreJugadores:String) = "PantallaJugador2/$nombreJugadores"
    }
}