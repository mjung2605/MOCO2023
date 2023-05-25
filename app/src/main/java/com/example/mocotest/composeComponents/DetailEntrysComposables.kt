package com.example.mocogmfinalui.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mocogmfinalui.R
import com.example.mocogmfinalui.ui.theme.LightGray
import com.example.mocogmfinalui.ui.theme.OffBlack
import com.example.mocogmfinalui.ui.theme.OffWhite


@Composable
fun DetailedEntry(
    titel: String,
    color: Color,
    onClickAction: () -> Unit,
    buttonText: String,
    iconDrawable: Painter,
    iconDesc: String
) {
    DetailedEntryContent(titel)

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box( // container für button
            Modifier.padding(bottom = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            ButtonDeleteOrMessage(
                color,
                onClickAction,
                buttonText,
                iconDrawable,
                iconDesc
            )
        }
    }
}

// Button für Eintrag auf der Main-Seite: man soll die Person, die das Objekt eingestellt hat, anschreiben können
// Button auf persönlicher Seite: man kann eigene Einträge löschen
// noch hinzufügen: übergabe Beschreibung, getMaps, getPicture
@Composable
fun ButtonDeleteOrMessage(
    color: Color,
    onClickAction: () -> Unit,
    buttonText: String,
    iconDrawable: Painter,
    iconDesc: String
) {
    Button(onClick = onClickAction,
        modifier = Modifier
            .width(width = 260.dp)
            .height(height = 74.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = color)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                iconDrawable,
                iconDesc,
                modifier = Modifier
                    .size(30.dp)
                    .padding(top = 5.dp)
            )
            Spacer(Modifier.padding(start = 10.dp))
            Text(
                text = buttonText,
                style = TextStyle(
                    fontSize = 20.sp,
                    color = OffBlack
                )
            )
        }
    }
}

@Composable
fun DetailedEntryContent(titel: String) { // noch Foto und Maps hinzufügen

    Column(Modifier.background(OffWhite)) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Photo()
                TitelUndBeschreibung(titel)
                MapsUndBeschreibung()
            }
        }
    }
}

@Composable
fun Photo() {
    Box(
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .fillMaxWidth()
            .height(400.dp)
            .background(LightGray) // Platzhalter-Farbe

    ) {
        // Hier sieht man normalerweise das Foto
        // TODO()
    }
}

@Composable
fun TitelUndBeschreibung(titel: String) {
    Column() {

        // Titel des Fundstücks
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = titel,
                modifier = Modifier
                    .fillMaxWidth(),
                color = OffBlack,
                style = TextStyle(
                    fontSize = 30.sp
                )
            )
        }
        // Fundstück Beschreibung
        Box(
            modifier = Modifier
                .padding(start = 20.dp, end = 20.dp)
        ) {
            Text(
                text = "So sieht der Gegenstand aus: Jorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.",
                modifier = Modifier
                    .fillMaxWidth(),
                color = OffBlack,
                style = TextStyle(
                    fontSize = 15.sp
                )
            )
        }
    }
}

@Composable
fun MapsUndBeschreibung() {

    // Maps
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
            .fillMaxWidth()
            .height(140.dp)
            .background(color = LightGray),
        contentAlignment = Alignment.Center
    ) {
        Row(
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = "placeholder 1",
                modifier = Modifier
                    .size(size = 72.dp)
            )
        }
    }

    // Ortsbeschreibung
    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp, bottom = 140.dp)
    ) {
        Text(
            text = "Hier habe ich den Gegenstand gefunden: Dorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.",
            color = OffBlack,
            style = TextStyle(
                fontSize = 15.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(160.dp)
        )
    }
}