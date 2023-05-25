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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.ui.theme.MainGreen
import com.example.mocogm.ui.theme.OffBlack
import com.example.mocogm.R

// Neuer Eintrag Auswahl Gesucht oder Gefunden //

@Composable
fun GesuchtGefundenTabs(onClickToGefunden: () -> Unit, onClickToGesucht: () -> Unit) {
    Column(Modifier.fillMaxWidth()) {

        ClickableBoxes(
            onClickToGesucht, fraction = 0.5f, color = MainBlue, text = "Gesucht", iconDrawable = painterResource(id = R.drawable.lupe),
                iconDesc = "Lupe",
                modifier = Modifier
                    .size(180.dp)
                    .padding(40.dp, 20.dp, 10.dp, 0.dp)
        )

        ClickableBoxes(
            onClickToGefunden, fraction = 1.0f, color = MainGreen, text = "Gefunden", iconDrawable = painterResource(id = R.drawable.box),
                iconDesc = "Box",
                modifier = Modifier
                    .size(180.dp)
                    .padding(55.dp, 20.dp, 10.dp, 0.dp)
        )
    }
}


@Composable
fun HinweisLayout(color: Color, text: String, iconDrawable: Painter, iconDesc: String, modifier: Modifier, woGesucht: String, onClickNav: () -> Unit, buttonColor: Color) {

    Column() {
        ClickableBoxes(color = color, fraction = 0.5f, text = text, iconDrawable = iconDrawable, iconDesc = iconDesc, modifier = modifier) // box oben mit entspr farbe etc
        HinweisBox(woGesucht, onClickNav, buttonColor)
    }
}

// FRAGMENTE


@Composable
fun ClickableBoxes(
    onClickToHinweis: () -> Unit = {}, // schon initialisiert, weil wir diese Boxen wiederbenutzen wollen, wenn wir schon beim Hinweis sind.
    color: Color,                        // somit muss das dann da nicht mehr übergeben werden
    fraction: Float,
    text: String,
    iconDrawable: Painter,
    iconDesc: String,
    modifier: Modifier
) {
    Box(
        Modifier
            .fillMaxWidth()
            .fillMaxHeight(fraction)
            .background(color)
            .clickable(onClick = onClickToHinweis),
        contentAlignment = Alignment.Center
    ) {
        Column(
            modifier = Modifier
                .padding(),
        ) {
            Text(text, fontSize = 50.sp)
            Image(painter = iconDrawable, contentDescription = iconDesc, modifier) // image resource etc wird übergeben
        }
    }
}


@Composable
fun HinweisBox(woGesucht: String, onClickNav: () -> Unit, buttonColor: Color) {

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
            HinweisText(woGesucht)
            HinweisBestaetigung(woGesucht)
            ButtonSucheBeginnen(onClickNav, buttonColor)
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

@Composable
fun HinweisBestaetigung(woSchonGesucht: String) {

    Row {
        val checkedStateJava = remember { mutableStateOf(false) }

        Checkbox(
            checked = checkedStateJava.value,
            onCheckedChange = { checkedStateJava.value = it },

        )
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(10.dp, 0.dp, 30.dp, 0.dp),
        ) {
            Text(
                "Ich habe bereits unter “${woSchonGesucht}” nach diesem Gegenstand gesucht, habe ihn dort aber leider nicht gefunden.",
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