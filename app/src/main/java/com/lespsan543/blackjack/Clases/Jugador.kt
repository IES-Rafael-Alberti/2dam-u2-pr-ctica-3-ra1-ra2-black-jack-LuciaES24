package com.lespsan543.blackjack.Clases

import com.lespsan543.cartas.Clases.Carta

class Jugador {
    var cartas = mutableListOf<Carta>()

    var plantarse = false

    fun contarPuntos():Int{
        var contaAs = 0
        var contaPuntos = 0
        for (carta in cartas){
            contaPuntos+=carta.puntosMin
            if (carta.nombre=="AS"){
                contaAs++
            }
        }

        var contador = 0
        if (contaAs != 0 && contaPuntos<=10){
            while (contador != contaAs){
                contador++
                contaPuntos-=1
                contaPuntos+=11
            }

        }
        return contaPuntos
    }
}