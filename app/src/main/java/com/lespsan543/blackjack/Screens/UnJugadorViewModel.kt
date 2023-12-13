package com.lespsan543.blackjack.Screens

import Carta
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lespsan543.blackjack.data.Jugador

class UnJugadorViewModel : ViewModel() {
    private val _jugador = MutableLiveData<Jugador>()

    private val _pantallaActual = MutableLiveData<String>()
    val pantallaActual : LiveData<String> = _pantallaActual

    private val _actualizaCartas = MutableLiveData<Boolean>()
    val actualizaCartas : LiveData<Boolean> = _actualizaCartas

    private val _plantarse = MutableLiveData<Boolean>()
    val plantarse : LiveData<Boolean> = _plantarse

    private val _puntosMaquina = 21

    init {
        Baraja.crearBaraja()
        _jugador.value = Jugador("", 0)
        _jugador.value!!.cartas.add(Baraja.dameCarta())
    }

    fun cambiarPantalla(pantalla:String){
        _pantallaActual.value = pantalla
    }

    fun getCartasJugador(): MutableList<Carta> {
        return _jugador.value!!.cartas
    }

    private fun actualizarCartas(){
        if (_actualizaCartas.value == null || _actualizaCartas.value == true){
            _actualizaCartas.value = false
        }else if (_actualizaCartas.value == false){
            _actualizaCartas.value = true
        }
    }

    fun anadirCartaAJugador() : Int{
        var puntos = 0
        _jugador.value!!.cartas.add(Baraja.dameCarta())
        puntos = _jugador.value!!.contarPuntos()
        actualizarCartas()

        if (puntos>=21 && _plantarse.value != true){
            cambiarPantalla("PantallaFinal")
        }

        return puntos
    }

    fun cambiarPlantarseJugador(){
        _jugador.value!!.plantarse = true
        _plantarse.value = true

        cambiarPantalla("PantallaFinal")
    }

    fun getJugadorGanador() : String{
        var ganador = ""
        if (_jugador.value!!.contarPuntos()==21){
            ganador = "Empate"
        }else if(_puntosMaquina<_jugador.value!!.contarPuntos() || _jugador.value!!.contarPuntos()<21 && _plantarse.value == true){
            ganador = "¡¡Gana la máquina!!"
        }
        return ganador
    }
}