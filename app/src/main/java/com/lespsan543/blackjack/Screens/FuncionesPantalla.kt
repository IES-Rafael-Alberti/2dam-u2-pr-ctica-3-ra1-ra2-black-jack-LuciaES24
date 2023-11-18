package com.lespsan543.cartas.Screens

import android.annotation.SuppressLint
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.lespsan543.cartas.Clases.Baraja
import com.lespsan543.cartas.Clases.Carta

@Preview(showBackground = true)
@Composable
fun Inicio(){
    var carta by remember { mutableStateOf(Carta("CERO","ABAJO",0,0,0)) }

    PantallaCartas(carta = carta,
        onChange = { carta = Baraja.dameCarta()}
    )
}

@Composable
fun PantallaCartas(
    carta: Carta,
    onChange : (Carta) -> Unit
){
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(painter = painterResource(id = recuperarId(carta)),
            contentDescription = "Carta boca abajo")
        Row(Modifier.padding(top = 24.dp)) {
            Button(onClick = { onChange(carta)},
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

@SuppressLint("DiscouragedApi")
@Composable
fun recuperarId(carta: Carta): Int {
    val context = LocalContext.current
    val nombre = carta.palo.toLowerCase()+carta.idDrawable
    return context.resources.getIdentifier(nombre, "drawable", context.packageName)
}