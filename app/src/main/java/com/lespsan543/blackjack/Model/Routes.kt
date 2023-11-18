package com.lespsan543.navegacin.Model

sealed class Routes(val route:String) {
    object Pantalla1 : Routes("pantalla1")
    object Pantalla2 : Routes("pantalla2")
    object Pantalla3 : Routes("pantalla3")
    object Pantalla4 : Routes("pantalla4/{edad}"){
        fun createRoute(edad:Int) = "pantalla4/$edad"
    }
    object Pantalla5 : Routes("pantalla5/{name}"){
        fun createRoute(name:String) = "pantalla5/$name"
    }
}