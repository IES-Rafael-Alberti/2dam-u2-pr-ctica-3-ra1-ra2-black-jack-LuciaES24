package com.lespsan543.blackjack.Screens

import Carta
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CutCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lespsan543.blackjack.R
import com.lespsan543.blackjack.data.Routes


@Composable
fun PantallaPrincipal(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Image(
            painter = painterResource(id = R.drawable.blackjack),
            contentDescription = "BlackJack",
            modifier = Modifier
                .height(300.dp)
                .width(600.dp))
        Spacer(modifier = Modifier.height(20.dp))
        Button(onClick = { navController.navigate(Routes.Pantalla1Jugador.route) },
            modifier = Modifier
                .height(80.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            shape = CutCornerShape(10.dp)
        ) {
            Text(text = "Un jugador", fontSize = 22.sp)
        }
        Spacer(modifier = Modifier.height(100.dp))
        Button(onClick = { navController.navigate(Routes.Multiplayer.route) },
            modifier = Modifier
                .height(80.dp)
                .width(200.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = Color.White,
                contentColor = Color.Black),
            border = BorderStroke(2.dp, Color.Black),
            shape = CutCornerShape(10.dp)
        ) {
            Text(text = "Dos jugadores", fontSize = 21.sp)
        }
    }
}

fun recuperarImagenCarta(carta: Carta): Int {
    val nombre = carta.palo.lowercase()+carta.idDrawable
    return when (nombre) {
        "corazones1" ->  R.drawable.corazon1
        "corazones2" ->  R.drawable.corazon2
        "corazones3" ->  R.drawable.corazon3
        "corazones4" ->  R.drawable.corazon4
        "corazones5" ->  R.drawable.corazon5
        "corazones6" ->  R.drawable.corazon6
        "corazones7" ->  R.drawable.corazon7
        "corazones8" ->  R.drawable.corazon8
        "corazones9" ->  R.drawable.corazon9
        "corazones10" ->  R.drawable.corazon10
        "corazones11" ->  R.drawable.corazon11
        "corazones12" ->  R.drawable.corazon12
        "corazones13" ->  R.drawable.corazon13
        "picas1" ->  R.drawable.pica1
        "picas2" ->  R.drawable.pica2
        "picas3" ->  R.drawable.pica3
        "picas4" ->  R.drawable.pica4
        "picas5" ->  R.drawable.pica5
        "picas6" ->  R.drawable.pica6
        "picas7" ->  R.drawable.pica7
        "picas8" ->  R.drawable.pica8
        "picas9" ->  R.drawable.pica9
        "picas10" ->  R.drawable.pica10
        "picas11" ->  R.drawable.pica11
        "picas12" ->  R.drawable.pica12
        "picas13" ->  R.drawable.pica13
        "diamantes1" ->  R.drawable.rombo1
        "diamantes2" ->  R.drawable.rombo2
        "diamantes3" ->  R.drawable.rombo3
        "diamantes4" ->  R.drawable.rombo4
        "diamantes5" ->  R.drawable.rombo5
        "diamantes6" ->  R.drawable.rombo6
        "diamantes7" ->  R.drawable.rombo7
        "diamantes8" ->  R.drawable.rombo8
        "diamantes9" ->  R.drawable.rombo9
        "diamantes10" ->  R.drawable.rombo10
        "diamantes11" ->  R.drawable.rombo11
        "diamantes12" ->  R.drawable.rombo12
        "diamantes13" ->  R.drawable.rombo13
        "treboesl1" ->  R.drawable.trebol1
        "treboles2" ->  R.drawable.trebol2
        "treboles3" ->  R.drawable.trebol3
        "treboles4" ->  R.drawable.trebol4
        "treboles5" ->  R.drawable.trebol5
        "treboles6" ->  R.drawable.trebol6
        "treboles7" ->  R.drawable.trebol7
        "treboles8" ->  R.drawable.trebol8
        "treboles9" ->  R.drawable.trebol9
        "treboles10" ->  R.drawable.trebol10
        "treboles11" ->  R.drawable.trebol11
        "treboles12" ->  R.drawable.trebol12
        "treboles13" ->  R.drawable.trebol13
        else -> R.drawable.abajo0
    }
}