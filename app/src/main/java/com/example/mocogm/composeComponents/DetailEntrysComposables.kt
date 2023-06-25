package com.example.mocogm.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mocogm.ui.theme.LightGray
import com.example.mocogm.ui.theme.OffBlack
import com.example.mocogm.ui.theme.OffWhite
import com.example.mocogm.R
import com.example.mocogm.gebuendelteDaten.DetailedEntryPersonal
import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.viewmodels.ItemViewModel
import com.google.firebase.firestore.auth.User

/*
//
@Composable
fun DetailedEntry(layoutData: DetailedEntry, viewModel: ItemViewModel, itemID: String) {

    val item = viewModel.getItem(itemID)!!



    val type = item.type
    val titel: String = item.title
    val desc: String = item.desc
    val loc = item.loc
    val photo = item.picture
    val userEmail = item.user!!.email



    DetailedEntryContent(titel, desc, loc, photo, userEmail!!)

    Box(Modifier.fillMaxSize(), contentAlignment = Alignment.BottomCenter) {
        Box( // container für button
            Modifier.padding(bottom = 20.dp),
            contentAlignment = Alignment.Center
        ) {
            if(layoutData is DetailedEntryPersonal)
                ButtonDelete(layoutData.onClickAction)
        }
    }
}

 */

//
//

@Composable
fun ButtonDelete(onClickAction: () -> Unit) {
    Button(onClick = onClickAction,
        modifier = Modifier
            .width(width = 260.dp)
            .height(height = 74.dp),
        shape = RoundedCornerShape(4.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MainBlue)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(
                painterResource(id = R.drawable.outline_delete_48),
                "trashcan",
                modifier = Modifier
                    .size(30.dp)
                    .padding(top = 5.dp)
            )
            Spacer(Modifier.padding(start = 10.dp))
            Text(
                text = "Eintrag löschen",
                style = TextStyle(
                    fontSize = 20.sp,
                    color = OffBlack
                )
            )
        }
    }
}

//
@Composable
fun DetailedEntryContent(titel: String, desc: String, loc: String, photo: String, otherUserEmail: String) {

    Column(Modifier.background(OffWhite)) {
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            Column(modifier = Modifier.verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(25.dp)
            ) {
                Photo(photo)
                TitelUndBeschreibung(titel, desc)
                Maps(loc)
                Message(otherUserEmail)
            }
        }
    }
}

@Composable
fun Photo(photo: String) {
    // TODO bei Kameraimplementierung: fetch from Backend
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
fun TitelUndBeschreibung(titel: String, desc: String) {
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
                text = desc,
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
fun Maps(loc: String) {

    // TODO bei Maps-Implementierung: fetch from Backend
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
}

@Composable
fun Message(otherEmail: String) {


    Box(
        modifier = Modifier
            .padding(start = 20.dp, end = 20.dp)
    ) {
        Text(
            text = "Schreibe den Nutzer direkt an:\n $otherEmail",
            modifier = Modifier
                .fillMaxWidth(),
            color = OffBlack,
            style = TextStyle(
                fontSize = 15.sp
            )
        )
    }
}