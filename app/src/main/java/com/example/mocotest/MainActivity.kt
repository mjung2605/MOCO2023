package com.example.mocotest

import android.content.res.Resources.Theme
import androidx.compose.foundation.shape.* // NEW
import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import com.example.mocotest.ui.theme.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.*
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mocotest.ui.theme.*
import org.intellij.lang.annotations.JdkConstants.TabLayoutPolicy


import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.format.TextStyle


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent { // call Comps from below
            MOCOTestTheme {
                TopBar("Goldmine")
                MainTabs("Gesucht", "Gefunden")
                ListOfEntrysLayoutMain("lalalalala")
                BottomBar()
            }
        }
    }
}

// Titlescreen
@Composable
fun TitleScreen() {

    Box(Modifier
            .background(brush = Brush.verticalGradient(listOf(MainBlue, MainGreen)))
            .fillMaxSize(),
    contentAlignment = Alignment.Center) {
        Box() {
            Image(painterResource(id = R.drawable.goldmine_2_3x), "gm_logo",
                Modifier.size(400.dp))
        }
    }
}

@Composable
fun TopBar(text: String) { // Text ist davon abhängig ob wir gerade auf der
    // Main Page oder der Personal Page sind
    Box(
        Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(OffWhite)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) { // Aufreihung der Elemente in TopBar
            Box(
                Modifier
                    .padding(10.dp)
                    .width(35.dp)
                    .fillMaxHeight()
            ) {
                Image(
                    painter = painterResource(id = R.drawable.goldmine_2_3x_quadrat),
                    contentDescription = "GM_Logo"
                )
            }
            Box(
                Modifier
                    .padding(10.dp)
                    .requiredWidth(270.dp)
                    .fillMaxHeight(), contentAlignment = Alignment.CenterStart
            ) {

                Text(text, fontSize = 24.sp) // "Goldmine" oder "Eigene Gegenstände"
            }
            Box(
                Modifier
                    .padding(10.dp)
                    .width(50.dp)
                    .fillMaxHeight(), contentAlignment = Alignment.TopEnd
            ) {// Personal Page button
                Image(
                    painter = painterResource(id = R.drawable.outline_account_circle_48_black),
                    contentDescription = "account_circle"
                )
            }
        }
    }
}

@Composable
fun BottomBar() {
    Box(
        Modifier
            .fillMaxWidth()
            .background(OffBlack)
            .requiredHeight(140.dp), contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            Modifier
                .padding(10.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            Button(
                onClick = {}, // TODO()
                modifier = Modifier
                    .width(50.dp)
                    .height(50.dp),
                colors = buttonColors(MainBlue, OffWhite, SecondaryBlue, OffWhite),
                shape = CircleShape
            ) {
                Image(
                    painter = painterResource(id = R.drawable.round_add_48),
                    contentDescription = "add_entry_icon"
                )
            }
        }
    }
}

// Screen: Main Page
@Composable
fun MainTabs(leftText: String, rightText: String) {

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {

        Box(
            Modifier
                .requiredWidth(270.dp) // stellt zentralen Split der Boxen sicher
                .height(50.dp)
                .background(MainBlue)

        ) {
            Box(Modifier.padding(65.dp, 13.dp)) // zentriert Text innerhalb Box
            {
                Text(leftText)
            }
        }
        Box(
            Modifier
                .requiredWidth(270.dp)
                .height(50.dp)
                .background(MainGreen)
        ) {
            Box(Modifier.padding(62.dp, 13.dp))
            {
                Text(rightText)
            }
        }
    }
}

@Composable
fun ListEntryLayoutMain( // Ein Eintrag auf der Main Page
    colorBorder: Color,
    entryTitle: String,
    description: String
) {
    // isMainPage fügt funktionalität hinzu (delete), wenn false, also wenn wir
    // auf personal page sind; später mit State Manager oder so ersetzen

    Box(
        Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .fillMaxWidth()
            .height(90.dp)
        // clickable hinzufügen
    ) {
        Row() {
            Box(
                Modifier // Bild
                    .border(BorderStroke(2.dp, colorBorder), RoundedCornerShape(3.dp))
                    .width(85.dp)
                    .height(85.dp)
                // serves as Container (so that picture Content isn't outsite border)
            ) {
                Box(
                    modifier = Modifier
                        .padding(2.dp)
                        .fillMaxSize()
                        .background(Color.Gray)
                ) // Platzhalter: Bild des Fundstückes
            }
            Column(Modifier.padding(start = 12.dp)) { // Titel + Text
                Box(
                    Modifier
                        .fillMaxWidth()
                        .height(20.dp)
                ) {
                    Text(text = entryTitle) // Titel des Fundstückes
                }
                Row() {

                        Text(text = description.take(100) + "...",
                            Modifier
                                .padding(top = 10.dp)
                                .fillMaxSize()) // Beschreibung (gekürzt)

                }

            }
        }
    }
}

@Composable
fun ListEntryLayoutPersonal( // Ein persönlicher Eintrag
    colorBorder: Color,
    entryTitle: String,
    description: String
) {
    Box(
        Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .fillMaxWidth()
            .height(90.dp)
        // clickable hinzufügen
    ) {
        Row() {
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
                        Row() {
                            Box(Modifier.padding(end=30.dp)) {
                                Text(text = description.take(70) + "...")
                            }
                            Box() {
                                Image(
                                    painter = painterResource(id = R.drawable.outline_delete_48),
                                    contentDescription = "delete_icon", Modifier.fillMaxSize()
                                )
                            }
                        }
                    }

                }

            }
        }
    }
}

@Composable
fun ListOfEntrysLayoutMain(justForDisplayDesc: String) { // die Auflistung der Fundsachen
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // nur Platzhalter für Visualisierung//
        ListEntryLayoutMain(MainBlue, "Handy", justForDisplayDesc)
        ListEntryLayoutMain(MainBlue, "Geldbeutel", justForDisplayDesc)
        ListEntryLayoutMain(MainBlue, "Schlüssel", justForDisplayDesc)
        ListEntryLayoutMain(MainBlue, "Tasche", justForDisplayDesc)
        ListEntryLayoutMain(
            colorBorder = MainBlue,
            entryTitle = "Iphone7",
            description = justForDisplayDesc
        )
        ListEntryLayoutMain(
            colorBorder = MainBlue,
            entryTitle = "Iphone7",
            description = justForDisplayDesc
        )
        ListEntryLayoutMain(
            colorBorder = MainBlue,
            entryTitle = "Iphone7",
            description = justForDisplayDesc
        )
    }
}

@Composable
fun ListOfEntrysLayoutPersonal(justForDisplayDesc: String) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // nur Platzhalter für Visualisierung//
        ListEntryLayoutPersonal(MainBlue, "Handy", justForDisplayDesc )
        ListEntryLayoutPersonal(MainBlue, "Geldbeutel", justForDisplayDesc)
        ListEntryLayoutPersonal(MainBlue, "Schlüssel", justForDisplayDesc)
        ListEntryLayoutPersonal(MainBlue, "Tasche", justForDisplayDesc)
        ListEntryLayoutPersonal(
            colorBorder = MainBlue,
            entryTitle = "Iphone7",
            description = justForDisplayDesc
        )
        ListEntryLayoutPersonal(
            colorBorder = MainBlue,
            entryTitle = "Iphone7",
            description = justForDisplayDesc
        )
        ListEntryLayoutPersonal(
            colorBorder = MainBlue,
            entryTitle = "Iphone7",
            description = justForDisplayDesc
        )
    }
}

// Split Preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val justForDisplayDesc =
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."

    // Preview Title Screen:
    TitleScreen()

    // Preview Main Page List:

    /*
    MOCOTestTheme {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            TopBar("Goldmine")
            MainTabs("Gesucht", "Gefunden")
            ListOfEntrysLayoutMain(justForDisplayDesc)
            BottomBar()
        }
    }

     */

    // Preview Personal Page List:
    /*
    MOCOTestTheme {
        Column(verticalArrangement = Arrangement.SpaceEvenly) {
            TopBar(text = "Persönliches")
            MainTabs(leftText = "Gesucht", rightText = "Gefunden")

            ListOfEntrysLayoutPersonal(justForDisplayDesc)

            BottomBar()
        }
    }

     */
}

