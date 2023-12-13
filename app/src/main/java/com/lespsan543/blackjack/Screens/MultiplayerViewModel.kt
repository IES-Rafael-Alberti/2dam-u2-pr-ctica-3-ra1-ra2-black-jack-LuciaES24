package com.lespsan543.blackjack.Screens

import Carta
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lespsan543.blackjack.data.Jugador

class MultiplayerViewModel : ViewModel() {
    private val _pantallaAnterior = MutableLiveData<String>()
    val pantallaAnterior : LiveData<String> = _pantallaAnterior

    private val _jugador1 = MutableLiveData<Jugador>()

    private val _jugador2 = MutableLiveData<Jugador>()

    private val _pantallaActual = MutableLiveData<String>()
    val pantallaActual : LiveData<String> = _pantallaActual

    private val _nombreJugador1 = MutableLiveData<String>()
    val nombreJugador1 : LiveData<String> = _nombreJugador1

    private val _fichasJugador1 = MutableLiveData<Int>()

    private val _nombreJugador2 = MutableLiveData<String>()
    val nombreJugador2 : LiveData<String> = _nombreJugador2

    private val _fichasJugador2 = MutableLiveData<Int>()

    private val _actualizaCartas = MutableLiveData<Boolean>()
    val actualizaCartas : LiveData<Boolean> = _actualizaCartas

    private val _plantarseJ1 = MutableLiveData<Boolean>()

    private val _plantarseJ2 = MutableLiveData<Boolean>()

    init {
        Baraja.crearBaraja()
        _jugador1.value = Jugador("",0)
        _jugador2.value = Jugador("",0)
        _jugador1.value!!.cartas.add(Baraja.dameCarta())
        _jugador2.value!!.cartas.add(Baraja.dameCarta())
    }

    fun getCartasJugador(jugador: Int): MutableList<Carta> {
        var listaCartas = mutableListOf<Carta>()
        if (jugador == 1){
            listaCartas = _jugador1.value!!.cartas
        }else if (jugador == 2){
            listaCartas = _jugador2.value!!.cartas
        }
        return listaCartas
    }

     private fun actualizarCartas(){
        if (_actualizaCartas.value == null || _actualizaCartas.value == true){
            _actualizaCartas.value = false
        }else if (_actualizaCartas.value == false){
            _actualizaCartas.value = true
        }
    }

    fun cambiarPantalla(pantalla:String){
        _pantallaActual.value = pantalla
    }

    fun getNombreJugador(jugador: Int):String?{
        var nombre : String? = ""
        if (jugador == 1){
            nombre = _nombreJugador1.value
        }else if (jugador == 2){
            nombre = _nombreJugador2.value
        }
        return nombre
    }

    fun cambiandoNombre(jugador: Int, nombre: String){
        if (jugador == 1){
            _nombreJugador1.value = nombre
        }else if (jugador == 2){
            _nombreJugador2.value = nombre
        }
    }

    fun cambiarDatosJugador(jugador: Int){
        if (jugador == 1){
            _jugador1.value!!.nombre = _nombreJugador1.value!!
            _jugador1.value!!.fichas = _fichasJugador1.value!!
        }else if (jugador == 2){
            _jugador2.value!!.nombre = _nombreJugador2.value!!
            _jugador2.value!!.fichas = _fichasJugador2.value!!
        }
    }

    fun cambiarFichasJugador(jugador: Int, fichas:Int){
        if (jugador == 1){
            _fichasJugador1.value = fichas
        }else if (jugador == 2){
            _fichasJugador2.value = fichas
        }
    }

    fun cambiarPlantarseJugador(jugador: Int){
        if (jugador == 1){
            _jugador1.value!!.plantarse = true
            _plantarseJ1.value = true
            cambiarPantalla("Jugador2")
        }else if (jugador == 2){
            _jugador2.value!!.plantarse = true
            _plantarseJ2.value = true
            cambiarPantalla("Jugador1")
        }

        comprobarFinJuego()

    }

    fun cambiarPantallaAnterior(pantalla:String){
        _pantallaAnterior.value = pantalla
    }

    fun anadirCartaAJugador(jugador: Int) : Int{
        var puntos = 0
        if (jugador == 1){
            _jugador1.value!!.cartas.add(Baraja.dameCarta())
            puntos = _jugador1.value!!.contarPuntos()
        }else if (jugador == 2){
            _jugador2.value!!.cartas.add(Baraja.dameCarta())
            puntos = _jugador2.value!!.contarPuntos()
        }
        actualizarCartas()

        if (puntos>=21 && _plantarseJ1.value != true){
            _plantarseJ1.value = true
        }else if (puntos >=21 && _plantarseJ2.value != true){
            _plantarseJ2.value = true
        }

        comprobarFinJuego()

        return puntos
    }

    private fun comprobarFinJuego(){
        if (_plantarseJ1.value == true && _plantarseJ2.value == true){
            cambiarPantalla("PantallaFinal")
        }
    }

    fun getJugadorGanador() : String{
        var ganador = ""
        if (_jugador1.value!!.contarPuntos()<=21 && _jugador1.value!!.contarPuntos()>_jugador2.value!!.contarPuntos()){
            ganador = "¡¡El ganador es ${_jugador1.value!!.nombre}!!"
        }else if(_jugador2.value!!.contarPuntos()<=21){
            ganador = "¡¡El ganador es ${_jugador2.value!!.nombre}!!"
        }else if (_jugador1.value!!.contarPuntos()==_jugador2.value!!.contarPuntos()){
            ganador = "Empate"
        }
        return ganador
    }

    fun reset(){
        _jugador1.value!!.nombre = ""
        _jugador2.value!!.nombre = ""
        _jugador1.value!!.fichas = 0
        _jugador2.value!!.fichas = 0
        _jugador1.value!!.cartas.clear()
        _jugador2.value!!.cartas.clear()
        _nombreJugador1.value = ""
        _nombreJugador2.value = ""
        _plantarseJ1.value = false
        _plantarseJ2.value = false
        _fichasJugador1.value = 0
        _fichasJugador2.value = 0
        _pantallaAnterior.value = "PedirJugador1"
        _pantallaActual.value = "PedirJugador1"
    }
}