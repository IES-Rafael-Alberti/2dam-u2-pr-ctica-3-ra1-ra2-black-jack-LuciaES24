package com.lespsan543.cartas.Clases

import android.util.Log

class Baraja {

    var listaCartas = ArrayList<Carta>()

    companion object{
        var baraja = Baraja()


        fun crearBaraja() : ArrayList<Carta> {
            val listaNaipes = Naipes.values()
            for (i in 0..13-1){
                baraja.listaCartas.add(Carta(listaNaipes[i].toString(),Palos.CORAZONES.toString(),listaNaipes[i].valorMin,listaNaipes[i].valorMax,i+1))
            }
            for (i in 0..13-1){
                baraja.listaCartas.add(Carta(listaNaipes[i].toString(),Palos.TREBOLES.toString(),listaNaipes[i].valorMin,listaNaipes[i].valorMax,i+1))

            }
            for (i in 0..13-1){
                baraja.listaCartas.add(Carta(listaNaipes[i].toString(),Palos.PICAS.toString(),listaNaipes[i].valorMin,listaNaipes[i].valorMax,i+1))
            }
            for (i in 0..13-1){
                baraja.listaCartas.add(Carta(listaNaipes[i].toString(),Palos.DIAMANTES.toString(),listaNaipes[i].valorMin,listaNaipes[i].valorMax,i+1))
            }
            return baraja.listaCartas
        }

        fun barajar(){
            baraja.listaCartas.shuffle()
        }

        fun dameCarta() : Carta{
            barajar()
            if (baraja.listaCartas.size != 0){
                val ultimaCarta = baraja.listaCartas.last()
                baraja.listaCartas.dropLast(baraja.listaCartas.size)
                return ultimaCarta
            }else{
                return Carta("CERO","ABAJO",0,0,0)
            }
        }
    }
}