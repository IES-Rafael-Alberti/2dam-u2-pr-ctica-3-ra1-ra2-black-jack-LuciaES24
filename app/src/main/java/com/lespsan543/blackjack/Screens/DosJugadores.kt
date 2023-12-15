package com.lespsan543.blackjack.Screens

import Carta
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lespsan543.blackjack.R
import com.lespsan543.blackjack.data.Routes

/**
 * Función que controla el modo 2 jugadores
 * @param navController para navegar entre pantallas
 * @param multiplayerViewModel view model en el que manejamos todos los datos del programa
 */
@Composable
fun Multiplayer(navController: NavHostController, multiplayerViewModel: MultiplayerViewModel){

    val pantallaActual: String by multiplayerViewModel.pantallaActual.observeAsState(initial = "PedirJugador1")
    val pantallaAnterior: String by multiplayerViewModel.pantallaAnterior.observeAsState(initial = "PedirJugador1")
    val actualizaCartas :Boolean by multiplayerViewModel.actualizaCartas.observeAsState(initial = false)

    //Controlamos que pantalla debe mostrarse
    if (pantallaActual == "PedirJugador1"){
        PedirJugador1(viewModel = multiplayerViewModel,
            pantalla = pantallaActual
        ) { multiplayerViewModel.cambiarPantalla("PedirJugador2") }
        multiplayerViewModel.cambiarPantallaAnterior("PedirJugador1")
    }else if (pantallaActual == "PedirJugador2"){
        PedirJugador2(viewModel = multiplayerViewModel,
            pantalla = pantallaActual,
            changeScreen = {multiplayerViewModel.cambiarPantalla("Jugador1")})
        multiplayerViewModel.cambiarPantallaAnterior("PedirJugador2")
    }else if (pantallaActual == "Jugador1"){
        Jugador(viewModel = multiplayerViewModel,
            jugador = 1,
            actualizaCartas = actualizaCartas,
            screen = pantallaActual,
            changeScreen = {multiplayerViewModel.cambiarPantalla("PantallaIntermedia")})
        multiplayerViewModel.cambiarPantallaAnterior("Jugador1")
    }else if (pantallaActual == "PantallaIntermedia"){
        PantallaIntermedia(screen = pantallaActual, changeScreen = {
            if (pantallaAnterior == "Jugador1"){
                multiplayerViewModel.cambiarPantalla("Jugador2")
            }else if (pantallaAnterior == "Jugador2"){
                multiplayerViewModel.cambiarPantalla("Jugador1")
            }
            multiplayerViewModel.cambiarPantallaAnterior("PantallaIntermedia") })
    }else if (pantallaActual == "Jugador2"){
        Jugador(viewModel = multiplayerViewModel,
            jugador = 2,
            actualizaCartas = actualizaCartas,
            screen = pantallaActual,
            changeScreen = {multiplayerViewModel.cambiarPantalla("PantallaIntermedia")})
        multiplayerViewModel.cambiarPantallaAnterior("Jugador2")
    }else if (pantallaActual == "PantallaFinal"){
        FinishGame(viewModel = multiplayerViewModel,
            navController = navController)
    }
}

/**
 * Muestra la pantalla que pide los datos del jugador 1
 * @param viewModel view model en el que manejamos todos los datos del programa
 * @param pantalla pantalla actual en la que se encuentra en programa
 * @param changeScreen función que cambia a la nueva pantalla
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedirJugador1(
    viewModel: MultiplayerViewModel,
    pantalla: String,
    changeScreen: (String) -> Unit){
    val nombreJugador1: String by viewModel.nombreJugador1.observeAsState(initial = "")

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
            onValueChange = {viewModel.cambiandoNombre(1,it)},
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
            for (i in 1..3){
                Button(onClick = { viewModel.cambiarFichasJugador(1,i) },
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
            for (i in 4..6){
                Button(onClick = { viewModel.cambiarFichasJugador(1,i) },
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
        Button(onClick = { viewModel.cambiarDatosJugador(1)
                           changeScreen(pantalla) },
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            shape = CutCornerShape(10.dp),
            enabled = nombreJugador1 != ""
        ) {
            Text(text = "Siguiente", fontSize = 22.sp)
        }
    }
}

/**
 * Muestra la pantalla que pide los datos del jugador 2
 * @param viewModel view model en el que manejamos todos los datos del programa
 * @param pantalla pantalla actual en la que se encuentra en programa
 * @param changeScreen función que cambia a la nueva pantalla
 */
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PedirJugador2(viewModel: MultiplayerViewModel,
                  pantalla : String,
                  changeScreen : (String) -> Unit){
    val nombreJugador2 : String by viewModel.nombreJugador2.observeAsState(initial = "")
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
            onValueChange = {viewModel.cambiandoNombre(2,it)},
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
            for (i in 1..3){
                Button(onClick = { viewModel.cambiarFichasJugador(2,i) },
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
            for (i in 4..6){
                Button(onClick = { viewModel.cambiarFichasJugador(2,i) },
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
        Button(onClick = { viewModel.cambiarDatosJugador(2)
                           changeScreen(pantalla)},
            modifier = Modifier
                .height(50.dp)
                .width(150.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            shape = CutCornerShape(10.dp),
            enabled = nombreJugador2 != ""
        ) {
            Text(text = "Siguiente", fontSize = 22.sp)
        }
    }
}

/**
 * Muestra la pantalla de la ronda dependiendo del jugador
 * @param viewModel view model en el que manejamos todos los datos del programa
 * @param actualizaCartas booleano que se encarga de actualizar las cartas automáticamente en la pantalla
 * @param jugador será 1 o 2 dependiendo del jugador al que le toque jugar
 * @param screen pantalla actual en la que se encuentra en programa
 * @param changeScreen función que cambia a la nueva pantalla
 */
@Composable
fun Jugador(viewModel: MultiplayerViewModel,
            actualizaCartas : Boolean,
            jugador: Int,
            screen : String,
            changeScreen : (String) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Turno de ${viewModel.getNombreJugador(jugador)}", color = Color.White, fontSize = 23.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Image(painter = painterResource(id = R.drawable.abajo0),
            contentDescription = "Carta")
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Puntos: ${viewModel.pJugadores(jugador)}", color = Color.White, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(5.dp))
        LazyRow(horizontalArrangement = Arrangement.spacedBy((-75).dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(20.dp)){
            items(viewModel.getCartasJugador(jugador)){
                ImagenCarta(carta = it)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Button(onClick = { viewModel.anadirCartaAJugador(jugador) },
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
            Button(onClick = { viewModel.cambiarPlantarseJugador(jugador)
                               changeScreen(screen)},
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
                Text(text = "Plantarse")
            }
        }
        Spacer(modifier = Modifier.height(25.dp))
        Button(onClick = { changeScreen(screen) },
            modifier = Modifier
                .height(40.dp)
                .width(130.dp)
                .align(Alignment.End),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            shape = CutCornerShape(10.dp)
        ) {
            Text(text = "Siguiente")
        }
    }
}

/**
 * Muestra una pantalla intermedia para que ambos jugadores no puedan ver los puntos del otro
 * @param screen pantalla actual en la que se encuentra en programa
 * @param changeScreen función que cambia a la nueva pantalla
 */
@Composable
fun PantallaIntermedia(
    screen : String,
    changeScreen : (String) -> Unit) {
    Column (
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pasa el móvil al siguiente jugador",
            fontSize = 25.sp,
            color = Color.White
        )
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { changeScreen(screen) },
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
            Text(text = "Siguiente", fontSize = 22.sp)
        }
    }
}

/**
 * Muestra la pantalla de fin de juego con el ganador
 * @param viewModel view model en el que manejamos todos los datos del programa
 * @param navController para navegar entre pantallas
 */
@Composable
fun FinishGame(viewModel: MultiplayerViewModel,
               navController: NavHostController){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Text(text = viewModel.getJugadorGanador(),
            fontSize = 40.sp,
            color = Color.White)
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = "Cartas de ${viewModel.getNombreJugador(1)} || Puntos: ${viewModel.pJugadores(1)}",
            fontSize = 20.sp,
            color = Color.White)
        LazyRow(horizontalArrangement = Arrangement.spacedBy((-75).dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(20.dp)){
            items(viewModel.getCartasJugador(1)){
                ImagenCarta(carta = it)
            }
        }
        Text(text = "Cartas de ${viewModel.getNombreJugador(2)} || Puntos: ${viewModel.pJugadores(2)}",
            fontSize = 20.sp,
            color = Color.White)
        LazyRow(horizontalArrangement = Arrangement.spacedBy((-75).dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(20.dp)){
            items(viewModel.getCartasJugador(2)){
                ImagenCarta(carta = it)
            }
        }
        Button(onClick = { navController.navigate(Routes.PantallaInicio.route)
                           viewModel.reset() },
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
            Text(text = "Inicio", fontSize = 22.sp)
        }
    }
}

/**
 * Genera la imagen de la carta de la que se recupere el identificador
 * @param carta carta de la que vamos a obtener el identificador para mostrarla
 */
@Composable
fun ImagenCarta(carta:Carta){
    Image(
        painter = painterResource(id = recuperarImagenCarta(carta = carta)),
        contentDescription = "Carta")
}
