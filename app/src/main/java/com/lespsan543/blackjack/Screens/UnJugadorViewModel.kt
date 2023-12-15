package com.lespsan543.blackjack.Screens

import Carta
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lespsan543.blackjack.data.Jugador

/**
 * @param _jugador guarda al jugador con sus datos
 * @param _pantallaActual1J guarda la pantalla en la que se encuentra el juego actualmente
 * @param pantallaActual1J guarda el valor de _pantallaActual
 * @param _actualizaCartas1J booleano que actualiza las cartas en la pantalla del jugador
 * @param actualizaCartas1J guarda el valor de _actualizaCartas
 * @param _plantarse booleano que guarda si el jugador se ha plantado o no
 * @param plantarse guarda el valor de _plantarse
 * @param puntosMaquina puntos que tiene la máquina
 *
 */
class UnJugadorViewModel : ViewModel() {
    private val _jugador = MutableLiveData<Jugador>()

    private val _pantallaActual1J = MutableLiveData<String>()
    val pantallaActual1J : LiveData<String> = _pantallaActual1J

    private val _actualizaCartas1J = MutableLiveData<Boolean>()
    val actualizaCartas1J : LiveData<Boolean> = _actualizaCartas1J

    private val _plantarse = MutableLiveData<Boolean>()
    val plantarse : LiveData<Boolean> = _plantarse

    private val _showDialog = MutableLiveData<Boolean>()
    val showDialog : LiveData<Boolean> = _showDialog

    private val puntosMaquina = 21

    /**
     * Al iniciar el juego se crea la baraja, se inician los valores del jugador
     * y se añade una carta al mismo
     */
    init {
        Baraja.crearBaraja()
        _jugador.value = Jugador("", 0)
        _jugador.value!!.cartas.add(Baraja.dameCarta())
    }

    /**
    * Cambia el valor de la variable _pantallaActual1J
    * @param pantalla pantalla que se quiere cambiar
    */
    fun cambiarPantalla(pantalla:String){
        _pantallaActual1J.value = pantalla
    }

    /**
     * Cierra el diálogo de la máquina
     */
    fun cerrarPantalla(){
        _showDialog.value = false
    }

    /**
     * Devuelve la lista de cartas del jugador
     * @return lista de cartas
     */
    fun getCartasJugador(): MutableList<Carta> {
        return _jugador.value!!.cartas
    }

    /**
     * Actualiza la variable _actualizaCartas1J para que se muestren los cambios en la pantalla
     */
    private fun actualizarCartas(){
        if (_actualizaCartas1J.value == true){
            _actualizaCartas1J.value = false
        }else if (_actualizaCartas1J.value == false){
            _actualizaCartas1J.value = true
        }
    }

    /**
     * Añade una carta al jugador
     */
    fun anadirCartaAJugador(){
        _jugador.value!!.cartas.add(Baraja.dameCarta())
        actualizarCartas()

        //Comprobamos si el jugador se ha pasado de los puntos
        if (pJugador()>=21 && _plantarse.value != true){
            cambiarPantalla("PantallaFinal")
        }

    }

    /**
     * Cuenta los puntos del jugador
     * @return Puntos del jugador
     */
    fun pJugador():Int{
        return _jugador.value!!.contarPuntos()
    }

    /**
     * Hace que el jugador se plante
     * Cambia la pantalla a la del fin del juego
     */
    fun cambiarPlantarseJugador(){
        _jugador.value!!.plantarse = true
        _plantarse.value = true

        cambiarPantalla("PantallaFinal")
    }

    /**
     * Obtiene el jugador ganador de la partida o el empate
     * @return cadena con el resultado de la partida
     */
    fun getJugadorGanador() : String{
        var ganador = ""
        if (_jugador.value!!.contarPuntos()==21){
            ganador = "Empate"
        }else if(puntosMaquina<_jugador.value!!.contarPuntos() || _jugador.value!!.contarPuntos()<21 && _plantarse.value == true){
            ganador = "¡¡Gana la máquina!!"
        }
        return ganador
    }

    /**
     * Resetea las todas las variables del juego
     */
    fun reset(){
        _jugador.value!!.nombre = ""
        _jugador.value!!.fichas = 0
        _jugador.value!!.cartas.clear()
        _plantarse.value = false
        _pantallaActual1J.value = "PedirJugador1"
    }
}