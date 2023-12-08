package com.lespsan543.cartas.Screens

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.lespsan543.blackjack.R
import com.lespsan543.cartas.Clases.Carta
import com.lespsan543.blackjack.data.Routes


@Composable
fun PantallaPrincipal(navController: NavHostController) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Color(20, 102, 11)),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally) {
        Button(onClick = {  },
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
        "corazon1" ->  R.drawable.corazon1
        "corazon2" ->  R.drawable.corazon2
        "corazon3" ->  R.drawable.corazon3
        "corazon4" ->  R.drawable.corazon4
        "corazon5" ->  R.drawable.corazon5
        "corazon6" ->  R.drawable.corazon6
        "corazon7" ->  R.drawable.corazon7
        "corazon8" ->  R.drawable.corazon8
        "corazon9" ->  R.drawable.corazon9
        "corazon10" ->  R.drawable.corazon10
        "corazon11" ->  R.drawable.corazon11
        "corazon12" ->  R.drawable.corazon12
        "corazon13" ->  R.drawable.corazon13
        "pica1" ->  R.drawable.pica1
        "pica2" ->  R.drawable.pica2
        "pica3" ->  R.drawable.pica3
        "pica4" ->  R.drawable.pica4
        "pica5" ->  R.drawable.pica5
        "pica6" ->  R.drawable.pica6
        "pica7" ->  R.drawable.pica7
        "pica8" ->  R.drawable.pica8
        "pica9" ->  R.drawable.pica9
        "pica10" ->  R.drawable.pica10
        "pica11" ->  R.drawable.pica11
        "pica12" ->  R.drawable.pica12
        "pica13" ->  R.drawable.pica13
        "rombo1" ->  R.drawable.rombo1
        "rombo2" ->  R.drawable.rombo2
        "rombo3" ->  R.drawable.rombo3
        "rombo4" ->  R.drawable.rombo4
        "rombo5" ->  R.drawable.rombo5
        "rombo6" ->  R.drawable.rombo6
        "rombo7" ->  R.drawable.rombo7
        "rombo8" ->  R.drawable.rombo8
        "rombo9" ->  R.drawable.rombo9
        "rombo10" ->  R.drawable.rombo10
        "rombo11" ->  R.drawable.rombo11
        "rombo12" ->  R.drawable.rombo12
        "rombo13" ->  R.drawable.rombo13
        "trebol1" ->  R.drawable.trebol1
        "trebol2" ->  R.drawable.trebol2
        "trebol3" ->  R.drawable.trebol3
        "trebol4" ->  R.drawable.trebol4
        "trebol5" ->  R.drawable.trebol5
        "trebol6" ->  R.drawable.trebol6
        "trebol7" ->  R.drawable.trebol7
        "trebol8" ->  R.drawable.trebol8
        "trebol9" ->  R.drawable.trebol9
        "trebol10" ->  R.drawable.trebol10
        "trebol11" ->  R.drawable.trebol11
        "trebol12" ->  R.drawable.trebol12
        "trebol13" ->  R.drawable.trebol13
        else -> R.drawable.abajo0
    }
}