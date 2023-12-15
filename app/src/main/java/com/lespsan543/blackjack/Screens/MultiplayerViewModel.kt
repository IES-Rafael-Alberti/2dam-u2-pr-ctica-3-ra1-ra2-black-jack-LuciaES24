package com.lespsan543.blackjack.Screens

import Carta
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.lespsan543.blackjack.data.Jugador

/**
 * @param _pantallaAnterior guarda la pantalla anterior en la que se ha estado
 * @param pantallaAnterior guarda el valor de _pantallaAnterior
 * @param _jugador1 guarda al jugador 1 con sus datos
 * @param _jugador2 guarda al jugador 2 con sus datos
 * @param _pantallaActual guarda la pantalla en la que se encuentra el juego actualmente
 * @param pantallaActual guarda el valor de _pantallaActual
 * @param _nombreJugador1 guarda el nombre del jugador 1
 * @param nombreJugador1 guarda el valor de _nombreJugador1
 * @param _fichasJugador1 guarda las fichas del jugador 1
 * @param _nombreJugador2 guarda el nombre del jugador 2
 * @param nombreJugador2 guarda el valor de _nombreJugador2
 * @param _fichasJugador2 guarda las fichas del jugador 2
 * @param _actualizaCartas booleano que actualiza las cartas en la pantalla del jugador
 * @param actualizaCartas guarda el valor de _actualizaCartas
 * @param _plantarseJ1 booleano que guarda si el jugador 1 se ha plantado o no
 * @param _plantarseJ2 booleano que guarda si el jugador 2 se ha plantado o no
 */
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

    /**
     * Al iniciar el juego se crea la baraja, se inician los valores de ambos jugadores
     * y se añade una carta a cada uno
     */
    init {
        Baraja.crearBaraja()
        _jugador1.value = Jugador("",0)
        _jugador2.value = Jugador("",0)
        _jugador1.value!!.cartas.add(Baraja.dameCarta())
        _jugador2.value!!.cartas.add(Baraja.dameCarta())
    }

    /**
     * Devuelve la lista de cartas del jugador elegido
     * @param jugador jugador del que se quiere obtener las cartas (1 o 2)
     * @return lista con las cartas del jugador
     */
    fun getCartasJugador(jugador: Int): MutableList<Carta> {
        var listaCartas = mutableListOf<Carta>()
        if (jugador == 1){
            listaCartas = _jugador1.value!!.cartas
        }else if (jugador == 2){
            listaCartas = _jugador2.value!!.cartas
        }
        return listaCartas
    }

    /**
     * Actualiza la variable _actualizaCartas para que se muestren los cambios en la pantalla
     */
     private fun actualizarCartas(){
        if (_actualizaCartas.value == true){
            _actualizaCartas.value = false
        }else if (_actualizaCartas.value == false){
            _actualizaCartas.value = true
        }
    }

    /**
     * Cambia el valor de la variable _pantallaActual
     * @param pantalla pantalla que se quiere cambiar
     */
    fun cambiarPantalla(pantalla:String){
        _pantallaActual.value = pantalla
    }

    /**
     * Devuelve el nombre del jugador que se indique
     * @param jugador jugador del que se quiere obtener el nombre (1 o 2)
     * @return nombre del jugador
     */
    fun getNombreJugador(jugador: Int):String?{
        var nombre : String? = ""
        if (jugador == 1){
            nombre = _nombreJugador1.value
        }else if (jugador == 2){
            nombre = _nombreJugador2.value
        }
        return nombre
    }

    /**
     * Cambia el nombre del jugador que se indique
     * @param jugador jugador al que se quiere modificar el nombre (1 o 2)
     * @param nombre nuevo nombre del jugador
     */
    fun cambiandoNombre(jugador: Int, nombre: String){
        if (jugador == 1){
            _nombreJugador1.value = nombre
        }else if (jugador == 2){
            _nombreJugador2.value = nombre
        }
    }

    /**
     * Cambia los datos del jugador que se indica
     * @param jugador jugador al que se quiere modificar los datos (1 o 2)
     */
    fun cambiarDatosJugador(jugador: Int){
        if (jugador == 1){
            _jugador1.value!!.nombre = _nombreJugador1.value!!
            _jugador1.value!!.fichas = _fichasJugador1.value!!
        }else if (jugador == 2){
            _jugador2.value!!.nombre = _nombreJugador2.value!!
            _jugador2.value!!.fichas = _fichasJugador2.value!!
        }
    }

    /**
     * Cambia el número de fichas del jugador que se indique
     * @param jugador jugador al que se quieren modificar las fichas
     * @param fichas fichas que se quieren añadir al jugador
     */
    fun cambiarFichasJugador(jugador: Int, fichas:Int){
        if (jugador == 1){
            _fichasJugador1.value = fichas
        }else if (jugador == 2){
            _fichasJugador2.value = fichas
        }
    }

    /**
     * Hace que el jugador elegido se plante
     * @param jugador jugador que quiere plantarse
     */
    fun cambiarPlantarseJugador(jugador: Int){
        //La variable del jugador cambia a true y la del view model también
        if (jugador == 1){
            _jugador1.value!!.plantarse = true
            _plantarseJ1.value = true
        }else if (jugador == 2){
            _jugador2.value!!.plantarse = true
            _plantarseJ2.value = true
        }

        //Comprobamos si ambos jugadores se han plantado
        comprobarFinJuego()

        //Cambiamos la pantalla
        cambiarPantalla("PantallaIntermedia")
    }

    /**
     * Cambia el valor de la variable _pantallaAnterior
     * @param pantalla pantalla que se quiere cambiar
     */
    fun cambiarPantallaAnterior(pantalla:String){
        _pantallaAnterior.value = pantalla
    }

    /**
     * Añade una carta al jugador que se indique
     * @param jugador número del jugador al que se va a añadir la carta (1 o 2)
     * @return puntos del jugador que se ha indicado
     */
    fun anadirCartaAJugador(jugador: Int){
        //Añade la carta al jugador y contamos los puntos que tiene actualmente
        if (jugador == 1){
            _jugador1.value!!.cartas.add(Baraja.dameCarta())
        }else if (jugador == 2){
            _jugador2.value!!.cartas.add(Baraja.dameCarta())
        }

        //Actualizamos las cartas para que se muestren por pantalla
        actualizarCartas()

        //Si se han pasado de 21 puntos se plantan automáticamente
        if (pJugadores(jugador)>=21 && _plantarseJ1.value != true){
            _plantarseJ1.value = true
        }else if (pJugadores(jugador) >=21 && _plantarseJ2.value != true){
            _plantarseJ2.value = true
        }

        //Comprobamos si el juego ha terminado (Ambos se han plantado)
        comprobarFinJuego()
    }

    /**
     * Comprueba si ambos jugadores se han plantado
     */
    private fun comprobarFinJuego(){
        if (_plantarseJ1.value == true && _plantarseJ2.value == true){
            //Muestra la pantalla de fin de juego con el ganador
            cambiarPantalla("PantallaFinal")
        }
    }

    /**
     * Obtiene el jugador ganador de la partida o el empate
     * @return cadena con el resultado de la partida
     */
    fun getJugadorGanador() : String{
        var ganador = ""
        //Comprobamos que el jugador 1 no pasa los 21 puntos y que tiene más puntos que el 2
        if (_jugador1.value!!.contarPuntos()<=21 && _jugador1.value!!.contarPuntos()>_jugador2.value!!.contarPuntos() || _jugador1.value!!.contarPuntos()<=21 && _jugador2.value!!.contarPuntos()>21){
            ganador = "¡¡El ganador es ${_jugador1.value!!.nombre}!!"
        }//Comprobamos que el jugador 2 no pasa los 21 puntos
        else if(_jugador2.value!!.contarPuntos()<=21){
            ganador = "¡¡El ganador es ${_jugador2.value!!.nombre}!!"
        }//Comprobamos si tienen la misma puntuación o ambos se han pasado de puntos
        else if (_jugador1.value!!.contarPuntos()==_jugador2.value!!.contarPuntos() || _jugador1.value!!.contarPuntos()>21 && _jugador2.value!!.contarPuntos()>21){
            ganador = "Empate"
        }
        return ganador
    }

    /**
     * Devuelve los puntos del jugador indicado
     * @param jugador jugador del que queremos obtener los puntos (1 o 2)
     * @return puntos del jugador
     */
    fun pJugadores(jugador: Int):Int{
        var puntos = 0
        if (jugador == 1){
            puntos = _jugador1.value!!.contarPuntos()
        }else if (jugador == 2){
            puntos = _jugador2.value!!.contarPuntos()
        }
        return puntos
    }

    /**
     * Resetea las todas las variables del juego
     */
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