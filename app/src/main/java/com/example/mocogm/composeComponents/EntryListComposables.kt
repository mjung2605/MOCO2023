package com.example.mocogm.composeComponents

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.example.mocogm.R
import com.example.mocogm.gebuendelteDaten.MainLayout
import com.example.mocogm.gebuendelteDaten.MainUserInterface
import com.example.mocogm.gebuendelteDaten.PersonalTabs
import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.ui.theme.MainGreen


@Composable
fun ListOfEntrys() {


}

@Composable
fun EntryList(mainLayoutData: MainUserInterface) {
    Column() {
        TopBar(mainLayoutData.pageTitle, mainLayoutData)
        MainTabs(mainLayoutData)
        ListOfEntrysLayout(mainLayoutData)
        BottomBar(mainLayoutData)
    }
}

// alles die gleichen Einträge für Visualisierung. später mit MVVM Implementierung mit observern etc
@Composable
fun ListOfEntrysLayout(layoutData: MainUserInterface) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ListEntryLayout(layoutData)
        ListEntryLayout(layoutData)
        ListEntryLayout(layoutData)
        ListEntryLayout(layoutData)
        ListEntryLayout(layoutData)
        ListEntryLayout(layoutData)
        ListEntryLayout(layoutData)
        ListEntryLayout(layoutData)
    }
}



// Screen: Main Page
@Composable
fun MainTabs(mainLayoutData: MainUserInterface) {

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        // Gesucht Box
        TabBox( { mainLayoutData.onClickTabBlue }, MainBlue, fraction = 0.5f)
        // Gefunden Box
        TabBox( { mainLayoutData.onClickTabGreen }, MainGreen, fraction = 1.0f)
    }
}

@Composable
fun TabBox(onClick: () -> Unit, boxColor: Color, fraction: Float) { // fraction ist wichtig für das Alignment (split in middle)

    Box(
        Modifier
            .fillMaxWidth(fraction) // 1 Tab ist die halbe Breite lang
            .height(50.dp)
            .background(boxColor)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center

    ) {
        Text(
            if(boxColor == MainGreen) "Gefunden" else "Gesucht"
        )
    }
}

@Composable
fun ListEntryLayout(mainLayoutData: MainUserInterface) {
    Box(
        Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .fillMaxWidth()
            .height(90.dp)
            .clickable(onClick = {} ) // TODO("Go to Detailed Entry")
    ) {
        Row {
            ListPreviewPicture(mainLayoutData.colorBorder)
            ListEntryTextContent(mainLayoutData, "Titel", "Beschreibung")
        }
    }
}


@Composable
fun ListPreviewPicture(colorBorder: Color) {
    Box(
        Modifier // Bild
            .border(BorderStroke(2.dp, colorBorder), RoundedCornerShape(3.dp))
            .width(85.dp)
            .height(85.dp)
        // serves as Container (so that picture content isn't outsite border corners)
    ) {
        Box(
            modifier = Modifier
                .padding(2.dp)
                .fillMaxSize()
                .background(Color.Gray)
        ) // Platzhalter: Bild des Fundstückes
    }
}

@Composable
fun ListEntryTextContent(mainLayoutData: MainLayout, entryTitle: String, description: String) {
    Column(Modifier.padding(start = 12.dp)) { // Titel + Text
        Box(
            Modifier
                .fillMaxWidth()
                .height(20.dp)
        ) {
            Text(text = entryTitle) // Titel des Fundstückes
        }
        Row() {
            Box(
                Modifier
                    .padding(top = 10.dp)
                    .fillMaxSize()
            ) {
                Row {
                    Box(Modifier.padding(end=30.dp)) {
                        Text(text = description.take(70) + "...")
                    }
                    if(mainLayoutData is PersonalTabs) { // add delete Button
                        ListEntryDelete(mainLayoutData.onClickDelete)
                    }
                }
            }
        }
    }
}

@Composable
fun ListEntryDelete(onClickDelete: () -> Unit) {
    Box() {
        Image(
            painter = painterResource(id = R.drawable.outline_delete_48),
            contentDescription = "delete_icon",
            Modifier
                .fillMaxSize()
                .clickable(onClick = onClickDelete)
        )
    }
}