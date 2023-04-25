package com.example.mocotest

import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import com.example.mocotest.ui.theme.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clipToBounds
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.mocotest.ui.theme.*


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // call Comps from below
            MOCOTestTheme() {
                TopBar()
                GesuchtGefundenTabs()
            }
        }
    }

// TODO(Startseite)

}


@Composable
fun TopBar() {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(SecondaryBlue)
    ) {
    }
}

@Composable
fun BottomNavigationDefaults() {



}

@Composable
fun GesuchtGefundenTabs() {
    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.SpaceBetween) {

        Box(
            Modifier
                .width(200.dp)
                .height(50.dp)
                .background(MainBlue)
                .padding(65.dp, 13.dp) // zentriert text
        ) {
            Text("Gesucht") // zum Dekorierer
        }

        Box(
            Modifier
                .width(200.dp)
                .height(50.dp)
                .background(MainGreen)
                .padding(62.dp, 13.dp)
        ) {
            Text("Gefunden")
        }
    }

}

@Composable
fun ListEntryMainPage() {
    Box(
        Modifier
            .padding(20.dp)
            .fillMaxWidth()
            .height(110.dp)
    ) {
        Row() {
            Box(
                Modifier
                    .width(72.dp)
                    .height(72.dp)
                    .background(Color.Gray)
            ) { }// Bild des Fundstückes
            Column(Modifier.padding(start = 12.dp)) {
                Box(
                    Modifier
                        .background(Color.Gray)
                        .fillMaxWidth()
                        .height(18.dp)
                ) // Titel des Fundstückes


                Box(
                    Modifier
                        .padding(top = 10.dp)
                        .background(Color.Gray)
                        .fillMaxSize()
                ) // Beschreibung


            }

        }

    }
}

@Composable
fun GetGrafik() {
    Image(painter = painterResource(R.drawable.ic_launcher_background), contentDescription = "TestBild")

}

@Composable
fun ListMainPage() { // die Auflistung der Fundsachen
    Column() {
        ListEntryMainPage()
        ListEntryMainPage()
        ListEntryMainPage()
        ListEntryMainPage()
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    MOCOTestTheme {
        Column {
            TopBar()
            GesuchtGefundenTabs()
            ListMainPage()
        }

    }
}

