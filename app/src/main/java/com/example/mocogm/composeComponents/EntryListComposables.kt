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
import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.ui.theme.MainGreen


@Composable
fun ListOfEntrys() {


}

@Composable
fun EntryList(topText: String,
              onClickHome: () -> Unit, onClickProfile: () -> Unit,
              onClickBlue: () -> Unit, onClickGreen: () -> Unit,
              onClickAddEntry: () -> Unit,
    // these following parameters are for listentrylayout
              colorBorder: Color, entryTitle: String, description: String, onClickDetailEntry: () -> Unit, isPersonal: Boolean, onClickDelete: () -> Unit = {}
) {
    Column() {
        TopBar(topText, onClickHome, onClickProfile)
        MainTabs(onClickBlue, onClickGreen)
        ListOfEntrysLayout(
            colorBorder,
            entryTitle,
            description,
            onClickDetailEntry,
            isPersonal,
            onClickDelete
        )
        BottomBar(onClickAddEntry)
    }
}

// alles die gleichen Einträge für Visualisierung. später mit MVVM Implementierung mit observern etc
@Composable
fun ListOfEntrysLayout(colorBorder: Color, entryTitle: String, description: String, onClickDetailEntry: () -> Unit, isPersonal: Boolean, onClickDelete: () -> Unit = {}) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .verticalScroll(rememberScrollState())
    ) {
        ListEntryLayout(
            colorBorder,
            entryTitle,
            description,
            onClickDetailEntry,
            isPersonal, onClickDelete
        )
        ListEntryLayout(
            colorBorder,
            entryTitle,
            description,
            onClickDetailEntry,
            isPersonal, onClickDelete
        )
        ListEntryLayout(
            colorBorder,
            entryTitle,
            description,
            onClickDetailEntry,
            isPersonal, onClickDelete
        )
        ListEntryLayout(
            colorBorder,
            entryTitle,
            description,
            onClickDetailEntry,
            isPersonal, onClickDelete
        )
        ListEntryLayout(
            colorBorder,
            entryTitle,
            description,
            onClickDetailEntry,
            isPersonal, onClickDelete
        )
        ListEntryLayout(
            colorBorder,
            entryTitle,
            description,
            onClickDetailEntry,
            isPersonal, onClickDelete
        )
        ListEntryLayout(
            colorBorder,
            entryTitle,
            description,
            onClickDetailEntry,
            isPersonal, onClickDelete
        )
        ListEntryLayout(
            colorBorder,
            entryTitle,
            description,
            onClickDetailEntry,
            isPersonal, onClickDelete
        )
    }
}



// Screen: Main Page
@Composable
fun MainTabs(onClickBlue: () -> Unit, onClickGreen: () -> Unit) {

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        // Gesucht Box
        TabBox(text = "Gesucht", color = MainBlue, onClickAction = onClickBlue, fraction = 0.5f)
        // Gefunden Box
        TabBox(text = "Gefunden", color = MainGreen, onClickAction = onClickGreen, fraction = 1.0f)
    }
}

@Composable
fun TabBox(text: String, color: Color, onClickAction: () -> Unit, fraction: Float) { // fraction ist wichtig für das Alignment (split in middle)

    Box(
        Modifier
            .fillMaxWidth(fraction) // 1 Tab ist die halbe Breite lang
            .height(50.dp)
            .background(color)
            .clickable(onClick = onClickAction),
        contentAlignment = Alignment.Center

    ) {
        Text(text)
    }

}

@Composable
fun ListEntryLayout( // Ein Eintrag in der Liste
    colorBorder: Color,
    entryTitle: String,
    description: String,
    onClickDetailEntry: () -> Unit,
    isPersonal: Boolean,
    onClickDelete: () -> Unit = {} // muss nicht da sein, deswegen hier vorinitialisiert
) {
    Box(
        Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .fillMaxWidth()
            .height(90.dp)
            .clickable(onClick = onClickDetailEntry)
    ) {
        Row {
            ListPreviewPicture(colorBorder)
            ListEntryTextContent(entryTitle, description, isPersonal, onClickDelete)
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
fun ListEntryTextContent(entryTitle: String, description: String, isPersonal: Boolean, onClickDelete: () -> Unit) {
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
                    if(isPersonal) { // add delete Button
                        ListEntryDelete(onClickDelete)
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