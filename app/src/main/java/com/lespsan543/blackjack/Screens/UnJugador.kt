package com.lespsan543.blackjack.Screens

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.navigation.NavHostController
import com.lespsan543.blackjack.R
import com.lespsan543.blackjack.data.Routes

/**
 * Función que controla el modo 1 jugador contra la máquina
 * @param navController para navegar entre pantallas
 * @param viewModel view model en el que manejamos todos los datos del programa
 */
@Composable
fun UnJugador(navController: NavHostController, viewModel: UnJugadorViewModel){
    val pantallaActual: String by viewModel.pantallaActual1J.observeAsState(initial = "Jugador")
    val plantarse : Boolean by viewModel.plantarse.observeAsState(initial = false)
    val actualizaCartas :Boolean by viewModel.actualizaCartas1J.observeAsState(initial = false)
    val showDialog : Boolean by viewModel.showDialog.observeAsState(initial = true)

    if (pantallaActual == "Jugador"){
        Jugador(viewModel = viewModel,
            actualizaCartas = actualizaCartas,
            screen = pantallaActual,
            changeScreen = {viewModel.cambiarPantalla("Maquina")})
    }else if (pantallaActual == "Maquina"){
        Maquina(viewModel = viewModel,
            screen = pantallaActual,
            changeScreen = {viewModel.cambiarPantalla("Jugador")},
            show = showDialog)
    }else{
        FinishGame1J(viewModel = viewModel, navController = navController)
    }
}

/**
 * Muestra la pantalla de la ronda del jugador
 * @param viewModel view model en el que manejamos todos los datos del programa
 * @param actualizaCartas booleano que se encarga de actualizar las cartas automáticamente en la pantalla
 * @param screen pantalla actual en la que se encuentra en programa
 * @param changeScreen función que cambia a la nueva pantalla
 */
@Composable
fun Jugador(
    viewModel: UnJugadorViewModel,
    actualizaCartas: Boolean,
    screen: String,
    changeScreen : (String) -> Unit){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Text(text = "Tu turno", color = Color.White, fontSize = 23.sp)
        Spacer(modifier = Modifier.height(20.dp))
        Image(painter = painterResource(id = R.drawable.abajo0),
            contentDescription = "Carta")
        Spacer(modifier = Modifier.height(5.dp))
        Spacer(modifier = Modifier.height(5.dp))
        Text(text = "Puntos: ${viewModel.pJugador()}", color = Color.White, fontSize = 18.sp)
        LazyRow(horizontalArrangement = Arrangement.spacedBy((-75).dp),
            verticalAlignment = Alignment.CenterVertically,
            contentPadding = PaddingValues(20.dp)
        ){
            items(viewModel.getCartasJugador()){
                ImagenCarta(carta = it)
            }
        }
        Spacer(modifier = Modifier.height(10.dp))
        Row {
            Button(onClick = { viewModel.anadirCartaAJugador() },
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
            Button(onClick = { viewModel.cambiarPlantarseJugador()
                viewModel.cambiarPantalla(screen)},
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
 * Muestra un diálogo de que la máquina está jugado
 * @param viewModel view model en el que manejamos todos los datos del programa
 * @param screen pantalla actual en la que se encuentra en programa
 * @param changeScreen función que cambia a la nueva pantalla
 * @param show booleano que determina que se muestre el diálogo en la pantalla
 */
@Composable
fun Maquina(viewModel: UnJugadorViewModel,
            screen: String,
            changeScreen : (String) -> Unit,
            show : Boolean){
    Dialog(onDismissRequest = { show }) {
        Column(modifier = Modifier
            .background(Color(20, 102, 11))
            .padding(top = 15.dp, bottom = 20.dp)
            .fillMaxWidth()
            .height(200.dp),
            horizontalAlignment = Alignment.CenterHorizontally )  {
            Text(
                text = "Está jugando la máquina",
                fontSize = 25.sp,
                color = Color.White
            )
            Text(text = "Pulsa aquí para que sea tu turno", color = Color.White)
            Button(onClick = { changeScreen(screen)
                viewModel.cerrarPantalla()}) {
                Text(text = "Siguiente")
            }

        }
    }
}

/**
 * Muestra la pantalla de fin de juego con el ganador
 * @param viewModel view model en el que manejamos todos los datos del programa
 * @param navController para navegar entre pantallas
 */
@Composable
fun FinishGame1J(viewModel: UnJugadorViewModel,
               navController: NavHostController){
    Column (modifier = Modifier
        .fillMaxSize()
        .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally)
    {
        Image(
            painter = painterResource(id = R.drawable.blackjack),
            contentDescription = "BlackJack",
            modifier = Modifier
                .height(300.dp)
                .width(600.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Text(text = viewModel.getJugadorGanador(),
            fontSize = 30.sp,
            color = Color.White)
        Spacer(modifier = Modifier.height(40.dp))
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