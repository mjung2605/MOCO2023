package com.example.mocogm.composeComponents

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mocogm.R
import com.example.mocogm.gebuendelteDaten.MainLayout
import com.example.mocogm.gebuendelteDaten.MainUserInterface
import com.example.mocogm.gebuendelteDaten.PersonalTabs
import com.example.mocogm.models.Item
import com.example.mocogm.models.ItemModel
import com.example.mocogm.ui.theme.LightGray
import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.ui.theme.MainGreen
import com.example.mocogm.viewmodels.ItemListViewModel
import com.example.mocogm.viewmodels.ItemViewModel



@Composable
fun EntryList(layoutData: MainUserInterface, viewModel: ItemListViewModel) {
    Column() {
        TopBar(layoutData.pageTitle, layoutData)
        MainTabs(layoutData)
        ListOfEntrysLayout(layoutData, viewModel)
        BottomBar(layoutData)
    }
}

// alles die gleichen Einträge für Visualisierung. später mit MVVM Implementierung mit observern etc


@Composable
fun ListOfEntrysLayout(layoutData: MainUserInterface, viewModel: ItemListViewModel = ItemListViewModel()) {

    val itemList: List<ItemModel>? by viewModel.itemList.observeAsState()
    val selectedItem: ItemModel? by viewModel.selectedItem.observeAsState()

    if(itemList==null) {
        Box(Modifier.fillMaxSize(), contentAlignment = Alignment.Center) {

            Text(text = "Noch keine Fundsachen vorhanden...", color = LightGray)
            
        }
    } else {
        LazyColumn {
            items(itemList!!.size) { item ->
                ListEntryLayout(
                    layoutData,
                    onClick = {
                        viewModel.onItemClicked(itemList!![item]) // setzt das Item, was angeklickt wurde, als selected item
                        layoutData.onClickDetailEntry(selectedItem!!.itemId) // übergibt itemID des ausgewählten Itemsnan den navController, navigiert zu detailed entry screen
                    }
                )
            }
        }
    }
}


@Composable
fun ListEntryLayout(layoutData: MainUserInterface, onClick: () -> Unit) {
    Box(
        Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .fillMaxWidth()
            .height(90.dp)
            .clickable(onClick = onClick) // TODO("Go to Detailed Entry")
    ) {
        Row {
            ListPreviewPicture(MainGreen)
            ListEntryTextContent(layoutData, "Titel", "Beschreibung")
        }
    }
}

@Composable
fun ListPreviewPicture(colorBorder: Color) {

    // TODO bei Kameraimplementierung: fetch from Backend

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
        {

        }
    }
}

@Composable
fun ListEntryTextContent(layoutData: MainUserInterface, entryTitle: String, description: String) {
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

                    if(layoutData is PersonalTabs) { // add delete Button
                        ListEntryDelete(layoutData.onClickDelete)
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


// Screen: Main Page
@Composable
fun MainTabs(layoutData: MainUserInterface) {

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
        // Gesucht Box
        TabBox( { layoutData.onClickTabBlue }, MainBlue, fraction = 0.5f)
        // Gefunden Box
        TabBox( { layoutData.onClickTabGreen }, MainGreen, fraction = 1.0f)
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