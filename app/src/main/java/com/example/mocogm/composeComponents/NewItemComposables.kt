package com.example.mocogm.composeComponents

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.addPathNodes
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mocogm.R
import com.example.mocogm.ui.theme.*
import java.io.File

@Composable
fun NewItem(
    color: Color,
    iconDrawable: Painter,
    iconDesc: String,
    text: String,
    onClickNav: () -> Unit
) {
    Column() {
        NewItemHeader(color, iconDrawable, iconDesc, text)
        ContentEingaben(onClickNav, color)
    }
}

@Composable
fun NewItemHeader(
    color: Color,
    iconDrawable: Painter,
    iconDesc: String,
    text: String
) { // zeigt, ob wir gerade ein gesuchtes oder ein gefundenes Item eintragen

    Box(                    // Gesucht-Box mit Lupe
        modifier = Modifier
            .fillMaxWidth()
            .height(144.dp)
            .background(color),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                painter = iconDrawable, contentDescription = iconDesc, modifier = Modifier
                    .size(size = 63.dp)
            )

            Text(
                text = text,
                color = OffBlack,
                style = TextStyle(
                    fontSize = 45.sp
                ), modifier = Modifier
                    .width(width = 265.dp)
                    .height(200.dp)
                    .padding(start = 25.dp)
                    .wrapContentHeight()
            )
        }
    }
}

@Composable
fun ContentEingaben(onClickNav: () -> Unit, color: Color) {
    Column {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = OffWhite)
        ) {
            Column(
                modifier = Modifier
                    .verticalScroll(rememberScrollState())
                    .padding(start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(15.dp)
            ) {
                AddPhoto()
                AddTitel()
                AddDesc()
                AddLocation()
                AddLocationDesc()
                ButtonFinishEntry(onClickNav, color)
            }
        }
    }
}

@Composable
fun AddPhoto() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(OffWhite)
    )
    Box(                        //Graue Box mit "Add Photo"
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(width = 172.dp)
            .height(height = 172.dp)
            .background(LightGray)
    ) {
        Text(
            text = "add photo",
            color = OffBlack,
            style = TextStyle(
                fontSize = 30.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
        )
        TODO("Hier kommt Kamera rein")
        //CameraView(outputDirectory = File("storage/emulated/0/Pictures/"), executor = , onImageCaptured = , onError = )
    }
}

@Composable
fun AddTitel() {
    Box( // titeleingabe. placeholder = ich suche... oder ich habe gefunden...
        modifier = Modifier
            .border(1.dp, OffBlack)
            .padding(start = 5.dp)
    ) {
        Text( // TODO: make outlined textfield
            text = "Ich habe gefunden...",
            color = DarkGray,
            style = TextStyle(
                fontSize = 30.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun AddDesc() {
    Box(
        modifier = Modifier
            .border(1.dp, OffBlack)
            .padding(5.dp)
    ) {
        Text( // TODO: make outlined textfield
            text = "Jorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.",
            color = DarkGray,
            style = TextStyle(
                fontSize = 15.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
        )
    }
}

@Composable
fun AddLocation() {

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(140.dp)
            .background(color = OffWhite),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier,
            horizontalArrangement = Arrangement.spacedBy(32.dp),
            verticalAlignment = Alignment.CenterVertically,

            ) {
            Image(
                painter = painterResource(id = R.drawable.placeholder),
                contentDescription = "placeholder 1",
                modifier = Modifier
                    .size(size = 72.dp)
            )
            Box(
                contentAlignment = Alignment.Center,
                modifier = Modifier
                    .background(color = MainGreen)
                    .padding(
                        start = 32.dp,
                        top = 10.dp,
                        end = 32.dp,
                        bottom = 10.dp
                    )
            ) {
                Text(
                    text = "Standort auswählen",
                    color = OffBlack,
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier
                )
            }
        }
    }
}

@Composable
fun AddLocationDesc() {

    Box( // TODO: make outlined textfield
        modifier = Modifier
            .border(1.dp, OffBlack)
            .padding(start = 5.dp, top = 16.dp)
    ) {
        Text(
            text = "Schreibe hier, wo du den Gegenstand gefunden hast...",
            color = DarkGray,
            style = TextStyle(
                fontSize = 15.sp
            ),
            modifier = Modifier
                .fillMaxWidth()
                .height(256.dp)
        )
    }
}

@Composable
fun ButtonFinishEntry(onClickNav: () -> Unit, color: Color) {
    Box(
        modifier = Modifier
            .background(OffWhite)
            .fillMaxWidth()
            .height(120.dp)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = onClickNav,
            modifier = Modifier
                .background(color)
                .width(width = 260.dp)
                .height(height = 74.dp),
            colors = ButtonDefaults.buttonColors(backgroundColor = color)
        ) {
            Text(
                text = "Fundstück melden",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = OffBlack
                ),
            )
        }
    }
}