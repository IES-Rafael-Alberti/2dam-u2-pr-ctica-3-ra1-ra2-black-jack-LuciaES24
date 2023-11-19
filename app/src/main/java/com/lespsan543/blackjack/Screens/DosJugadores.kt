package com.lespsan543.blackjack.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lespsan543.blackjack.Clases.Jugador
import com.lespsan543.navegacin.Model.Routes

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedirJugador1(navController: NavHostController){
    var nombreJugador1 by rememberSaveable { mutableStateOf("") }
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
        Button(onClick = { navController.navigate(Routes.PedirJugador2.createRoute(nombreJugador1+" ")) },
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
fun PedirJugador2(navController: NavHostController, nombreJugador1:String){
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
        Button(onClick = { navController.navigate(Routes.PantallaJugador1.createRoute(nombreJugador1+nombreJugador2)) },
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
fun PantallaJugador1(navController: NavHostController, nombreJugadores:String){
    val jugador1 by rememberSaveable { mutableStateOf(Jugador(nombreJugadores.split(" ")[1])) }
}

@Composable
fun PantallaJugador2(navController: NavHostController, nombreJugadores:String){
    val jugador2 by rememberSaveable { mutableStateOf(Jugador(nombreJugadores.split(" ")[2])) }
}