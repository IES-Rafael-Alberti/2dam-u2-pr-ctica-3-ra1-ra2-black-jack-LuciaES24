package com.lespsan543.blackjack.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lespsan543.blackjack.Clases.Jugador
import com.lespsan543.blackjack.R
import com.lespsan543.cartas.Screens.recuperarId
import com.lespsan543.navegacin.Model.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedirJugador1(navController: NavHostController){
    var nombreJugador1 by rememberSaveable { mutableStateOf("") }
    var fichas by rememberSaveable { mutableStateOf(0) }
    var jugadores by rememberSaveable { mutableStateOf(mutableListOf<Jugador>()) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = nombreJugador1,
            onValueChange = {nombreJugador1=it},
            label = { Text(text = "Introduzca el nombre del Jugador 1")}
        )
        Text(text = "Elija cuantas fichas quiere apostar")
        Image(painter = painterResource(id = R.drawable.ficha),
            contentDescription = "Ficha")
        Row {
            for (i in 0..2){
                Button(onClick = { fichas = i },
                    modifier = Modifier
                        .height(30.dp)
                        .width(70.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black),
                    border = BorderStroke(2.dp, Color.Black),
                    shape = CutCornerShape(10.dp)) {
                    Text(text = "$i")
                }
            }
        }
        Row {
            for (i in 3..5){
                Button(onClick = { fichas = i },
                    modifier = Modifier
                        .height(30.dp)
                        .width(70.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black),
                    border = BorderStroke(2.dp, Color.Black),
                    shape = CutCornerShape(10.dp)) {
                    Text(text = "$i")
                }
            }
        }
        Button(onClick = { jugadores.add(Jugador(nombreJugador1,fichas))
            navController.navigate(Routes.PedirJugador2.createRoute(jugadores)) },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            shape = CutCornerShape(10.dp)
        ) {
            Text(text = "Siguiente", fontSize = 22.sp)
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedirJugador2(navController: NavHostController, jugadores:MutableList<Jugador>){
    var fichas by rememberSaveable { mutableStateOf(0) }
    var nombreJugador2 by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        OutlinedTextField(
            value = nombreJugador2,
            onValueChange = {nombreJugador2=it},
            label = { Text(text = "Introduzca el nombre del Jugador 2")}
        )
        Text(text = "Elija cuantas fichas quiere apostar")
        Image(painter = painterResource(id = R.drawable.ficha),
            contentDescription = "Ficha")
        Row {
            for (i in 0..2){
                Button(onClick = { fichas = i },
                    modifier = Modifier
                        .height(30.dp)
                        .width(70.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black),
                    border = BorderStroke(2.dp, Color.Black),
                    shape = CutCornerShape(10.dp)) {
                    Text(text = "$i")
                }
            }
        }
        Row {
            for (i in 3..5){
                Button(onClick = { fichas = i },
                    modifier = Modifier
                        .height(30.dp)
                        .width(70.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Color.White,
                        contentColor = Color.Black),
                    border = BorderStroke(2.dp, Color.Black),
                    shape = CutCornerShape(10.dp)) {
                    Text(text = "$i")
                }
            }
        }
        Button(onClick = { jugadores.add(Jugador(nombreJugador2,fichas))
            navController.navigate(Routes.PantallaMultiJugador.createRoute(jugadores)) },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            shape = CutCornerShape(10.dp)
        ) {
            Text(text = "Empezar partida", fontSize = 22.sp)
        }
    }
}

@Composable
fun PantallaMultiJugador(navController: NavHostController, jugadores:MutableList<Jugador>){
    val jugador1 by rememberSaveable { mutableStateOf(jugadores[0]) }
    val jugador2 by rememberSaveable { mutableStateOf(jugadores[1]) }
}
