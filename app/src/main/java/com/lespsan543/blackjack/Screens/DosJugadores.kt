package com.lespsan543.blackjack.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import com.lespsan543.cartas.Clases.Baraja
import com.lespsan543.cartas.Screens.GenerarCartas
import com.lespsan543.cartas.Screens.recuperarId
import com.lespsan543.navegacin.Model.Routes

@Composable
fun Multiplayer(navController: NavHostController){
    var pantallaMultiPlayer by rememberSaveable { mutableStateOf("PedirJugador1") }

    var jugador1 by remember { mutableStateOf(Jugador("",0)) }
    var jugador2 by remember { mutableStateOf(Jugador("",0)) }

    if (pantallaMultiPlayer == "PedirJugador1"){
        PedirJugador1(jugador1 = jugador1,
            pantalla = pantallaMultiPlayer,
            changeScreen = {pantallaMultiPlayer = "PedirJugador2"})
    }else if (pantallaMultiPlayer == "PedirJugador2"){
        PedirJugador2(jugador2 = jugador2,
            pantalla = pantallaMultiPlayer,
            changeScreen = {pantallaMultiPlayer = "Jugador1"})
    }else if (pantallaMultiPlayer == "Jugador1"){
        Jugador1(jugador = jugador1,
            screen = pantallaMultiPlayer,
            changeScreen = {pantallaMultiPlayer = "Jugador2"})

    }else if (pantallaMultiPlayer == "Jugador2"){

    }


}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedirJugador1(jugador1 : Jugador,
                  pantalla : String,
                  changeScreen : (String) -> Unit){
    var nombreJugador1 by rememberSaveable { mutableStateOf("") }
    var fichas by rememberSaveable { mutableStateOf(0) }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Jugador 1",
            fontSize = 28.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = nombreJugador1,
            onValueChange = {nombreJugador1=it},
            label = { Text(text = "Nombre del Jugador 1", color = Color.White)},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                textColor = Color.White)
        )
        Spacer(modifier = Modifier.height(23.dp))
        Text(text = "Elija cuantas fichas quiere apostar", color = Color.White)
        Spacer(modifier = Modifier.height(10.dp))
        Image(painter = painterResource(id = R.drawable.ficha),
            contentDescription = "Ficha")
        Spacer(modifier = Modifier.height(18.dp))
        Row {
            for (i in 0..2){
                Button(onClick = { fichas = i },
                    modifier = Modifier
                        .height(40.dp)
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
                        .height(40.dp)
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
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { jugador1.nombre = nombreJugador1
                         jugador1.fichas = fichas
                         changeScreen(pantalla)},
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
fun PedirJugador2(jugador2:Jugador,
                  pantalla : String,
                  changeScreen : (String) -> Unit){
    var fichas by rememberSaveable { mutableStateOf(0) }
    var nombreJugador2 by rememberSaveable { mutableStateOf("") }
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(
            text = "Jugador 2",
            fontSize = 28.sp,
            color = Color.White,
        )
        Spacer(modifier = Modifier.height(50.dp))
        OutlinedTextField(
            value = nombreJugador2,
            onValueChange = {nombreJugador2=it},
            label = { Text(text = "Nombre del jugador 2", color = Color.White)},
            colors = TextFieldDefaults.outlinedTextFieldColors(
                focusedBorderColor = Color.White,
                unfocusedBorderColor = Color.White,
                textColor = Color.White)
        )
        Spacer(modifier = Modifier.height(23.dp))
        Text(text = "Elija cuantas fichas quiere apostar", color = Color.White)
        Spacer(modifier = Modifier.height(10.dp))
        Image(painter = painterResource(id = R.drawable.ficha),
            contentDescription = "Ficha")
        Spacer(modifier = Modifier.height(18.dp))
        Row {
            for (i in 0..2){
                Button(onClick = { fichas = i },
                    modifier = Modifier
                        .height(40.dp)
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
                        .height(40.dp)
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
        Spacer(modifier = Modifier.height(40.dp))
        Button(onClick = { jugador2.nombre = nombreJugador2
            jugador2.fichas = fichas
            changeScreen(pantalla)},
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

@Composable
fun Jugador1(jugador: Jugador,
             screen : String,
             changeScreen : (String) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = R.drawable.abajo0),
            contentDescription = "Carta")
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            GenerarCartas(jugador = jugador)
        }
        Spacer(modifier = Modifier.height(20.dp))
        Row {
            Button(onClick = { var cartaNueva = Baraja.dameCarta()
                             jugador.cartas.add(cartaNueva)},
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .padding(end = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black),
                border = BorderStroke(2.dp, Color.Black),
                shape = CutCornerShape(10.dp)
            ) {
                Text(text = "Dame carta")
            }
            Button(onClick = { Baraja.crearBaraja()},
                modifier = Modifier
                    .height(50.dp)
                    .width(150.dp)
                    .padding(start = 5.dp),
                colors = ButtonDefaults.buttonColors(
                    containerColor = Color.White,
                    contentColor = Color.Black),
                border = BorderStroke(2.dp, Color.Black),
                shape = CutCornerShape(10.dp)
            ) {
                Text(text = "Reiniciar")
            }
        }
    }
}
