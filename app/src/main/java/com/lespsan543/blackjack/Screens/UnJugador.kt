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
import com.lespsan543.blackjack.R

@Composable
fun UnJugador(viewModel: UnJugadorViewModel){
    val pantallaActual: String by viewModel.pantallaActual.observeAsState(initial = "Jugador")
    val plantarse : Boolean by viewModel.plantarse.observeAsState(initial = false)

    while (!plantarse){
        if (pantallaActual == "Jugador"){
            Jugador(viewModel = viewModel,
                screen = pantallaActual)
        }else if (pantallaActual == "Maquina"){
            Maquina()
        }
    }
}

@Composable
fun Jugador(viewModel:UnJugadorViewModel,
            screen : String){
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
        Text(text = "Puntos: ${viewModel.anadirCartaAJugador()}", color = Color.White, fontSize = 18.sp)
        Spacer(modifier = Modifier.height(5.dp))
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
        Button(onClick = { viewModel.cambiarPantalla(screen) },
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

@Composable
fun Maquina(){

}