package com.example.mocogm.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Checkbox
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.rememberNavController
import com.example.mocogm.ui.theme.OffBlack

import com.example.mocogm.gebuendelteDaten.GGBoxes
import com.example.mocogm.gebuendelteDaten.GGefunden
import com.example.mocogm.gebuendelteDaten.GGesucht
import com.example.mocogm.gebuendelteDaten.Hinweis


@Composable
fun GesuchtGefundenBoxen(blueBox: GGBoxes, greenBox: GGBoxes) {

    Column(Modifier.fillMaxWidth()) {
        GesuchtBox(fraction = 0.5f, blueBoxGG = blueBox)
        GefundenBox(fraction = 1.0f, greenBoxGG = greenBox)
    }
}


@Composable
fun HinweisLayout(boxGG: GGBoxes, boxHinweis: Hinweis) {

    Column() {
        ClickableBoxes(fraction = 0.5f, boxGG) // box oben mit entspr farbe etc
        HinweisBox(boxHinweis)
    }
}

// FRAGMENTE


@Composable
fun ClickableBoxes(
    fraction: Float,
    boxGG: GGBoxes
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction)
            .background(boxGG.bgColor)
            .clickable(onClick = boxGG.onClick),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding()
                .align(Alignment.Center),
        ) {
            Text(boxGG.titleText, fontSize = 50.sp)
            Image(painter = painterResource(id = boxGG.iconDrawableID), contentDescription = boxGG.iconDesc,
            modifier =
            (if(boxGG is GGefunden)
                Modifier
                    .size(70.dp) // größe des Box-icons (grün)
                    .align(Alignment.CenterHorizontally)

            else
                Modifier // Größe des Lupen-Icons (blau)
                    .size(70.dp)
                    .align(Alignment.CenterHorizontally)))

        }
    }
}


@Composable
fun GesuchtBox(fraction: Float, blueBoxGG: GGBoxes) {
    ClickableBoxes(fraction, blueBoxGG)
}

@Composable
fun GefundenBox(fraction: Float, greenBoxGG: GGBoxes) {
    ClickableBoxes(fraction, greenBoxGG)
}


@Composable
fun HinweisBox(boxHinweis: Hinweis) {

    Column() {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .padding(30.dp, 10.dp)
        ) {
            Text("Hinweis", fontSize = 50.sp)
        }

        Column(Modifier.padding(30.dp, 0.dp)) {
            HinweisText(boxHinweis.woGesucht)
            HinweisBestaetigung(boxHinweis.woGesucht)
            ButtonSucheBeginnen(boxHinweis.onClickButton, boxHinweis.buttonColor)
        }
    }
}

@Composable
fun HinweisText(woGesucht: String) {

    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(0.3f),
    ) {
        Text(
            "Bitte stelle sicher, dass dein gesuchter Gegenstand nicht bereits unter “${woGesucht}” eingestellt wurde.",
            fontSize = 20.sp
        )
    }
}

@Preview
@Composable
fun HinweisBestaetigung(woGesucht: String = "") {

    Row {

        val checkedStateJava = remember { mutableStateOf(false) }
        Checkbox(
            checked = checkedStateJava.value,
            onCheckedChange = {
                checkedStateJava.value = it
                              },
        )
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(10.dp, 0.dp, 30.dp, 0.dp),
        ) {
            Text(
                "Ich habe bereits unter “${woGesucht}” nach diesem Gegenstand gesucht, habe ihn dort aber leider nicht gefunden.",
                fontSize = 15.sp
            )
        }
    }
}

@Composable
fun ButtonSucheBeginnen(onClickNav: () -> Unit, buttonColor: Color) {

    Button(
        onClick = onClickNav,
        colors = ButtonDefaults.textButtonColors(
            buttonColor
        )
    ) {
        Text("Suche starten", fontSize = 20.sp, color = OffBlack)
    }
}