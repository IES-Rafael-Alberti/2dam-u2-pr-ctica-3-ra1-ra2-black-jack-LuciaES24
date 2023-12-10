package com.lespsan543.blackjack.data

import Carta


class Jugador(var nombre:String, var fichas:Int) {
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
        while (contador!=contaAs) {
            if (contaPuntos <= 11) {
                contador++
                contaPuntos -= 1
                contaPuntos += 11
            }else{
                break
            }
        }
        return contaPuntos
    }
}