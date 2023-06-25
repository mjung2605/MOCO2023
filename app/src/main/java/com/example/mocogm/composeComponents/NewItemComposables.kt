package com.example.mocogm.composeComponents

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mocogm.R
import com.example.mocogm.gebuendelteDaten.NewItem
import com.example.mocogm.models.ItemModel
import com.example.mocogm.models.Type

import com.example.mocogm.ui.theme.*
import com.example.mocogm.viewmodels.ItemViewModel
import com.google.firebase.auth.FirebaseUser

@Composable
fun NewItem(layoutData: NewItem, viewModel: ItemViewModel, currentUser: FirebaseUser) {
    Column() {
        NewItemHeader(layoutData)
        ContentEingaben(layoutData, viewModel, currentUser)
    }
}

@Composable
fun NewItemHeader(layoutData: NewItem) { // zeigt, ob wir gerade ein gesuchtes oder ein gefundenes Item eintragen

    Box(                    // Gesucht-Box mit Lupe
        modifier = Modifier
            .fillMaxWidth()
            .height(144.dp)
            .background(layoutData.color),
        contentAlignment = Alignment.Center
    ) {
        Row(
            modifier = Modifier
                .padding(30.dp),
            horizontalArrangement = Arrangement.Center,
            verticalAlignment = Alignment.CenterVertically,
        ) {

            Image(
                painter = painterResource(id = layoutData.iconDrawableID), contentDescription = layoutData.iconDesc, modifier = Modifier
                    .size(size = 63.dp)
            )

            Text(
                text = layoutData.typeTitle,
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
fun ContentEingaben(layoutData: NewItem, viewModel: ItemViewModel, currentUser: FirebaseUser) {


    // TODO: MAPS UND KAMERA IMPLEMENTIERUNG
    var pictureValue by rememberSaveable { mutableStateOf("") } // TODO: wie nehmen wir Bilder auf? Wie speichern wir diese in dieser Box?
    var titleValue by rememberSaveable { mutableStateOf("") } //
    var descValue by rememberSaveable { mutableStateOf("") }
    var mapValue by rememberSaveable { mutableStateOf("") } // TODO: wie speichern wir Orte ab? was ist die Default Anzeige?



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
                AddPhoto(pictureValue) { pictureValue = it }
                AddTitel(layoutData, titleValue) { titleValue = it }
                AddDesc(descValue) { descValue = it }
                AddLocation(mapValue) { mapValue = it }
                // LOCATION DESC GELÖSCHT. EIN FELD FÜR EINE BESCHREIBUNG REICHT

                ButtonFinishEntry(onClickAdd = {viewModel.addItem(
                    type = if (layoutData.typeTitle == "Gefunden") Type.GEFUNDEN else Type.GESUCHT,
                    titleValue, descValue, pictureValue, mapValue, currentUser
                )}, onClickNav = {layoutData.onClickNav()} , layoutData.color)
            }
        }
    }
}

@Composable
fun AddPhoto(pictureValue: String, onValueChange: (String)->Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(10.dp)
            .background(OffWhite)
    )
    Box(                        //Graue Box mit "Add Photo" TODO() Implementierung Foto hinzufügen
        contentAlignment = Alignment.Center,
        modifier = Modifier
            .width(width = 172.dp)
            .height(height = 172.dp)
            .background(LightGray)
    ) {// als Platzhalter für Fotoimplementierung:
        OutlinedTextField(value = pictureValue, onValueChange = onValueChange, placeholder = { Text(
            text = "Foto hinzufügen"
        )})

        /*
        Text(
            text = "add photo",
            color = OffBlack,
            style = TextStyle(
                fontSize = 30.sp
            ),
            textAlign = TextAlign.Center,
            modifier = Modifier
        )

         */
    }
}

@Composable
fun AddTitel(layoutData: NewItem, titleValue: String, onValueChange: (String) -> Unit) {
    OutlinedTextField(value = titleValue, onValueChange = onValueChange, placeholder = {Text(text = layoutData.titlePlaceholder)})
}


@Composable
fun AddDesc(descValue: String, onDescValueChange: (String) -> Unit) {
    Box(
        modifier = Modifier
            .border(1.dp, OffBlack)
            .padding(5.dp)
    ) {
        OutlinedTextField(value = descValue, onValueChange = onDescValueChange, modifier = Modifier.height(70.dp), placeholder = { Text(
            text = "Füge eine Beschreibung hinzu..."
        )})
    }
}

@Composable
fun AddLocation(mapValue: String, onMapValueChange: (String) -> Unit) { // TODO richtige Kartenimplementierung adden

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
                OutlinedTextField(value = mapValue, onValueChange = onMapValueChange, placeholder = { Text(
                    text = "Standort hinzufügen"
                )})
                /*
                Text( // wird mal button
                    text = "Standort auswählen",
                    color = OffBlack,
                    style = TextStyle(
                        fontSize = 15.sp
                    ),
                    modifier = Modifier
                )
                */
            }
        }
    }
}

@Composable
fun ButtonFinishEntry(onClickAdd: () -> Unit, onClickNav: () -> Unit, color: Color) {
    Box(
        modifier = Modifier
            .background(OffWhite)
            .fillMaxWidth()
            .height(120.dp)
            .padding(10.dp),
        contentAlignment = Alignment.Center
    ) {
        Button(
            onClick = {
                onClickAdd()
                onClickNav()
                      },
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
                )
            )
        }
    }
}
