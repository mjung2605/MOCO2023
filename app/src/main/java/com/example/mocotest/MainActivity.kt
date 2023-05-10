package com.example.mocotest

import android.content.res.Resources.Theme
import androidx.compose.foundation.shape.*
import android.os.Bundle
import android.widget.HorizontalScrollView
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.*
import androidx.compose.foundation.gestures.scrollable
import androidx.compose.foundation.layout.*

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.relocation.bringIntoViewRequester
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.ButtonDefaults.buttonColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.ActivityNavigator
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mocotest.ui.theme.*
import org.intellij.lang.annotations.JdkConstants.TabLayoutPolicy


import kotlinx.coroutines.launch
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment
import java.time.format.TextStyle

sealed class Screen(val route: String) {

    object TitleScreen: Screen("home")
    object LogIn: Screen("log_in")
    object SignUp: Screen("sign_up")

    object MainScreenTabBlue: Screen("main_entrys_gesucht") // List von Einträgen (Gesucht/Gefunden)
    object MainScreenTabGreen: Screen("main_entrys_gefunden")

    object PersonalEntrysBlue: Screen("personal_entrys_gesucht")
    object PersonalEntrysGreen: Screen("personal_entrys_gefunden")

    object HinweisGefunden: Screen("hinweis_gefunden")
    object HinweisGesucht: Screen("hinweis_gesucht")
    object GesuchtGefundenTabs: Screen ("gesucht_gefunden_auswahl")

    object ItemGefunden: Screen ("item_gefunden")
    object ItemGesucht: Screen ("item_gesucht")


    object MainDetailedEntryBlue: Screen("main_detail_screen_blue")
    object MainDetailedEntryGreen: Screen("main_detail_screen_green")
    object PersonalDetailedEntryBlue: Screen("personal_detail_blue")
    object PersonalDetailedEntryGreen: Screen("personal_detail_green")

    object PrivateChat: Screen("direct_message")

}

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOCOTestTheme {
                val navController = rememberNavController()
                NavApplicationHost(navController)


            }
        }
    }
}

// Composable to store NavHost
@Composable
fun NavApplicationHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.TitleScreen.route) {
        // define all possible destinations
        composable(Screen.TitleScreen.route) { TitleScreen(navController) }

        composable(Screen.LogIn.route) { LogInScreen(navController) }
        composable(Screen.SignUp.route) { SignUpScreen(navController) }

        composable(Screen.MainScreenTabBlue.route) { MainEntryListBlueTab(navController) }
        composable(Screen.MainScreenTabGreen.route) { MainEntryListGreenTab(navController) }

        composable(Screen.PersonalEntrysBlue.route) { PersonalEntryListBlueTab(navController) }
        composable(Screen.PersonalEntrysGreen.route) { PersonalEntryListGreenTab(navController) }

        composable(Screen.HinweisGefunden.route) { HinweisGefunden(navController) }
        composable(Screen.HinweisGesucht.route) { HinweisGesucht(navController) }
        composable(Screen.GesuchtGefundenTabs.route) { GesuchtGefundenTabs(navController) }

        composable(Screen.ItemGesucht.route) { ItemGesucht(navController) }
        composable(Screen.ItemGefunden.route) { ItemGefunden(navController) }

        composable(Screen.MainDetailedEntryBlue.route) { DetailedEntryBlue(navController) }
        composable(Screen.MainDetailedEntryGreen.route) { DetailedEntryGreen(navController) }

        // TODO: Navigation zu detailierten Einträgen von Liste aus (bei konkreter Implementierung der Einträge)
        composable(Screen.PersonalDetailedEntryBlue.route) { DetailedEntryBluePersonal(navController) }
        composable(Screen.PersonalDetailedEntryGreen.route) { DetailedEntryGreenPersonal(navController) }

        composable(Screen.PrivateChat.route) { PrivateChat(navController) }

    }
}

//////////////////////////
///// FINAL SCREENS //////
//////////////////////////

// Start //
// TODO: nicht clickable, sondern Timer/Delay beim Starten der App
@Composable
fun TitleScreen(navController: NavHostController) {
    Box(
        Modifier
            .background(brush = Brush.verticalGradient(listOf(MainBlue, MainGreen)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {
        Box(Modifier.clickable { navController.navigate(Screen.LogIn.route) }) {
            Image(painterResource(id = R.drawable.goldmine_2_3x), "gm_logo",
                Modifier
                    .size(400.dp)
                    )
        }
    }
}


// TODO: Anmeldung mit Google-Konto etc. möglich machen?
@Composable
fun LogInScreen(navController: NavHostController) {

    // Einloggen mit E-Mail, Passwort

    Box( // Hintergrund
        Modifier
            .background(brush = Brush.verticalGradient(listOf(MainBlue, MainGreen)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column(
            Modifier
                .width(230.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {

            // LOG IN Überschrift
            Text(text = "LOG IN", Modifier.padding(5.dp),
                fontSize = 50.sp,
                color = OffBlack)

            // Textboxen für E-Mail + Paswort

            // OutlinedTextField(value = , onValueChange = ) TODO: Bei Implementierung dies benutzen!

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(
                        Brush.horizontalGradient(listOf(OffWhite, OffWhite)),
                        alpha = 0.3f
                    )

            ) {// Alpha: Deckkraft
                Text("E-Mail", Modifier.padding(start = 7.dp, top = 5.dp), color = DarkGray)
            }

            Box(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(
                        Brush.horizontalGradient(listOf(OffWhite, OffWhite)),
                        alpha = 0.3f
                    )

            ) {// Alpha: Deckkraft
                Text("Passwort", Modifier.padding(start = 7.dp, top = 5.dp), color = DarkGray)
            }

            // Einloggen-Button navigiert zu Main Liste

            Box(Modifier.padding(10.dp)) {
                Button(onClick = {navController.navigate(Screen.MainScreenTabBlue.route)},
                    colors = buttonColors(backgroundColor = MainBlue)) {
                    Text(text = "Einloggen")
                }
            }

            // Registrierung möglich: navigiert zum Sign Up Screen

            Box(Modifier.padding(20.dp), contentAlignment = Alignment.Center) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Noch keinen Account?", color = OffBlack, textAlign = TextAlign.Center)
                    Text("Registriere dich hier!", Modifier.clickable { navController.navigate(Screen.SignUp.route) },
                        color = OffBlack, textDecoration = TextDecoration.Underline, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

@Composable
fun SignUpScreen(navController: NavHostController) {

    Box( // Hintergrund
        Modifier
            .background(brush = Brush.verticalGradient(listOf(MainBlue, MainGreen)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column(
            Modifier
                .width(230.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {

            // SIGN UP Überschrift
            Text(text = "SIGN UP", Modifier.padding(5.dp),
                fontSize = 50.sp,
                color = OffBlack)

            // Textbox für Nutzername
            // OutlinedTextField(value = , onValueChange = ) TODO: Bei Implementierung dies benutzen!
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(
                        Brush.horizontalGradient(listOf(OffWhite, OffWhite)),
                        alpha = 0.3f
                    )

            ) {// Alpha: Deckkraft
                Text("Nutzername", Modifier.padding(start = 7.dp, top = 5.dp), color = DarkGray)
            }

            // Textbox für E-Mail
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(
                        Brush.horizontalGradient(listOf(OffWhite, OffWhite)),
                        alpha = 0.3f
                    )

            ) {
                Text("E-Mail", Modifier.padding(start = 7.dp, top = 5.dp), color = DarkGray)
            }

            // Textbox für Passwort
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(
                        Brush.horizontalGradient(listOf(OffWhite, OffWhite)),
                        alpha = 0.3f
                    )

            ) {// Alpha: Deckkraft
                Text("Passwort", Modifier.padding(start = 7.dp, top = 5.dp), color = DarkGray)
            }

            // Textbox für Passwort wiederholen
            Box(
                Modifier
                    .fillMaxWidth()
                    .height(30.dp)
                    .background(
                        Brush.horizontalGradient(listOf(OffWhite, OffWhite)),
                        alpha = 0.3f
                    )

            ) {// Alpha: Deckkraft
                Text("Passwort wiederholen", Modifier.padding(start = 7.dp, top = 5.dp), color = DarkGray)
            }
            // TODO bei Accountimplementierung: Passwortstärke feststellen?

            // Account erstellen Button -> führt zur Seite der persönlichen Einträge ?
            Box(Modifier.padding(10.dp)) {
                Button(onClick = {navController.navigate(Screen.PersonalEntrysBlue.route)},
                    colors = buttonColors(backgroundColor = MainGreen)) {
                    Text(text = "Account erstellen")
                }
            }

            Box(Modifier.padding(top = 20.dp), contentAlignment = Alignment.Center) {
                Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
                    Text("Du hast schon einen Account?", color = OffBlack, textAlign = TextAlign.Center)
                    Text("Melde dich hier an!", Modifier.clickable { navController.navigate(Screen.LogIn.route) },
                        color = OffBlack, textDecoration = TextDecoration.Underline, textAlign = TextAlign.Center)
                }
            }
        }
    }
}

// Listen der Einträge //
// TODO UI: Highlighten, welcher Tab angeklickt ist
@Composable
fun MainEntryListBlueTab(navController: NavHostController) {

    Column() {
        TopBar(text = "Goldmine", navController)
        MainTabs("Gesucht", "Gefunden", navController, Screen.MainScreenTabGreen.route, Screen.MainScreenTabBlue.route)
        ListOfEntrysLayoutMain(justForDisplayDesc = "Hier könnte Ihr Text stehen!!!", MainBlue)
        BottomBar(navController)
    }
}

@Composable
fun MainEntryListGreenTab(navController: NavHostController) {

    Column() {
        TopBar(text = "Goldmine", navController)
        MainTabs("Gesucht", "Gefunden", navController, Screen.MainScreenTabGreen.route, Screen.MainScreenTabBlue.route)
        ListOfEntrysLayoutMain(justForDisplayDesc = "Hier könnte Ihr Text stehen!!!", MainGreen)
        BottomBar(navController)
    }
}

// TODO UI: Mülleimer/Delete Button schöner machen
@Composable
fun PersonalEntryListBlueTab(navController: NavHostController) {

    Column() {
        TopBar(text = "Persönliches", navController)
        MainTabs(leftText = "Gesucht", rightText = "Gefunden", navController, Screen.PersonalEntrysGreen.route, Screen.PersonalEntrysBlue.route)
        ListOfEntrysLayoutPersonal(justForDisplayDesc = "Hier könnte Ihr Text stehen!", MainBlue)
        BottomBar(navController)
    }
}

@Composable
fun PersonalEntryListGreenTab(navController: NavHostController) {

    Column() {
        TopBar(text = "Persönliches", navController)
        MainTabs(leftText = "Gesucht", rightText = "Gefunden", navController, Screen.PersonalEntrysGreen.route, Screen.PersonalEntrysBlue.route)
        ListOfEntrysLayoutPersonal(justForDisplayDesc = "Hier könnte Ihr Text stehen!", MainGreen)
        BottomBar(navController)
    }
}

// Neuer Eintrag Auswahl Gesucht Gefunden //

@Composable
fun GesuchtGefundenTabs(navController: NavHostController) {
    Column(Modifier.fillMaxWidth()) {

// Gesucht Box
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(MainBlue)
                .clickable { navController.navigate(Screen.HinweisGesucht.route) },
            contentAlignment = Alignment.Center
        )
        {
            Column(modifier = Modifier
                .padding())
            {
                Text("Gesucht", fontSize = 50.sp)
                Image(
                    painter = painterResource(id = R.drawable.lupe),
                    contentDescription = "Lupe",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(40.dp, 20.dp, 10.dp, 0.dp),
                )
            }
        }
// Gefunden Box
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(MainGreen)
                //.padding(85.dp, 100.dp)
                .clickable { navController.navigate(Screen.HinweisGefunden.route) },
            contentAlignment = Alignment.Center
        ) {
            Column(modifier = Modifier
                .padding(),
            ) {
                Text("Gefunden", fontSize = 50.sp)
                Image(
                    painter = painterResource(id = R.drawable.box),
                    contentDescription = "Lupe",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(55.dp, 20.dp, 10.dp, 0.dp),
                )
            }
        }
    }

}

@Composable
fun HinweisGefunden(navController: NavHostController) {
    Column  (modifier= Modifier
        .fillMaxSize()
    )
// Box oben Gefunden
    {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(MainGreen),
                contentAlignment = Alignment.Center
        )
        {
            Column() {
                Text("Gefunden", fontSize = 50.sp)
                Image(
                    painter = painterResource(id = R.drawable.box),
                    contentDescription = "Lupe",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(55.dp, 20.dp, 10.dp, 0.dp),
                )
            }

        }
//Hinweis
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .padding(30.dp, 10.dp)
        ) {
            Text("Hinweis", fontSize = 50.sp)

        }
//Hinweis Text
        Column(Modifier.padding(30.dp, 0.dp)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)
                    ,
            ) {
                Text(
                    "Bitte stelle sicher, dass dein gefundener Gegenstand nicht bereits unter “Gesucht” eingestellt wurde.",
                    fontSize = 20.sp
                )
            }

            //Bestaetigung
            Row { val checkedStateJava = remember { mutableStateOf(false) }

                Checkbox(
                    checked = checkedStateJava.value,
                    onCheckedChange = { checkedStateJava.value = it },
                    //          modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .padding(10.dp, 0.dp, 30.dp, 0.dp),
                ) {
                    androidx.compose.material.Text("Ich habe bereits unter “Gesucht” nach diesem Gegenstand gesucht, habe ihn dort aber leider nicht gefunden.", fontSize = 15.sp)
                }
            }

            Button(onClick = { navController.navigate(Screen.ItemGefunden.route) }, colors = ButtonDefaults.textButtonColors(MainGreen)
            ) {
                Text("Suche starten", fontSize = 20.sp, color = OffBlack)
            }
        }

    }
}

@Composable
fun HinweisGesucht(navController: NavHostController) {
    Column  (modifier= Modifier
        .fillMaxSize()
    )
    {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(MainBlue),
            contentAlignment = Alignment.Center
        )
        {
            Column() {
                Text("Gesucht", fontSize = 50.sp)
                Image(
                    painter = painterResource(id = R.drawable.lupe),
                    contentDescription = "Lupe",
                    modifier = Modifier
                        .size(180.dp)
                        .padding(40.dp, 20.dp, 10.dp, 0.dp)
                )
            }
        }
//Hinweis
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
                .padding(30.dp, 10.dp)

        ) {
            Text("Hinweis", fontSize = 50.sp)
        }
//Hinweis Text
        Column(Modifier.padding(30.dp, 0.dp)) {
            Box(
                Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(0.3f)

            ) {
                Text(
                    "Bitte stelle sicher, dass dein gesuchter Gegenstand nicht bereits unter “Gefunden” eingestellt wurde.",
                    fontSize = 20.sp
                )
            }



//Bestaetigung
            Row { val checkedStateJava = remember { mutableStateOf(false) }

                Checkbox(
                    checked = checkedStateJava.value,
                    onCheckedChange = { checkedStateJava.value = it },
                    //          modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)
                )
                Box(
                    Modifier
                        .fillMaxWidth()
                        .fillMaxHeight(0.5f)
                        .padding(10.dp, 0.dp, 30.dp, 0.dp),
                ) {
                    androidx.compose.material.Text("Ich habe bereits unter “Gefunden” nach meinem Gegenstand gesucht, habe ihn dort aber leider nicht gefunden.", fontSize = 15.sp)
                }
            }

            Button(onClick = { navController.navigate(Screen.ItemGesucht.route) }, colors = ButtonDefaults.textButtonColors(MainBlue)
            ) {
                Text("Suche starten", fontSize = 20.sp, color = OffBlack)
            }
        }
    }
}

// Neuen Eintrag erstellen

@Composable
fun ItemGesucht(navController: NavHostController){
    Column(){
        Box(                    // Gesucht-Box mit Lupe
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp)
                .background(MainBlue),
            contentAlignment = Alignment.Center
        ){
            Row (
                modifier = Modifier
                    .padding(30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(id = R.drawable.lupe),
                    contentDescription = "lupe 1",
                    modifier = Modifier
                        .size(size = 63.dp)
                )
                Text(
                    text = "Gesucht ",
                    color = OffBlack,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 45.sp
                    ), modifier = Modifier
                        .width(width = 265.dp)
                        .height(200.dp)
                        .padding(start = 25.dp)
                        .wrapContentHeight()
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(OffWhite)
                .padding(start = 20.dp, end = 20.dp)            // Padding am "Add photo"
        ) {
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(15.dp)

            ) {

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .background(OffWhite)
                    ){}
                    Box(                        //Graue Box mit "Add Photo"
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .width(172.dp)
                            .height(172.dp)
                            .background(LightGray)

                    ) {
                        Text(
                            text = "add photo",
                            color = OffBlack,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 30.sp
                            ),
                            textAlign = TextAlign.Center,
                        )
                    }
                }
                item {
                    Box( // "Ich suche... - Box"
                        modifier = Modifier
                            .border(1.dp, OffBlack)
                            .padding(start = 5.dp)
                    ) {
                        Text(
                            text = "Ich suche...",
                            color = DarkGray,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 30.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .border(1.dp, OffBlack)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "Jorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.",
                            color = DarkGray,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(140.dp)
                            .background(OffWhite),
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
                                    .background(MainBlue)
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
                                    style = androidx.compose.ui.text.TextStyle(
                                        fontSize = 15.sp
                                    ),
                                )
                            }
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .border(1.dp, OffBlack)
                            .padding(start = 5.dp, top = 16.dp)
                    ) {
                        Text(
                            text = "Schreibe hier, wo du deinen Gegenstand verloren hast...",
                            color = DarkGray,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(256.dp)
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .background(color = OffWhite)
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(MainBlue)
                                .width(width = 260.dp)
                                .height(height = 74.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Suche beginnen",
                                Modifier.clickable { navController.navigate(Screen.MainScreenTabBlue.route) },
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 20.sp,
                                    color = OffBlack
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun ItemGefunden(navController: NavHostController){
    Column(
        modifier = Modifier
    ){
        Box(                    // Gesucht-Box mit Lupe
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp)
                .background(MainGreen),
            contentAlignment = Alignment.Center
        ){
            Row (
                modifier = Modifier
                    .padding(30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically,
            ){
                Image(
                    painter = painterResource(id = R.drawable.box),
                    contentDescription = "lupe 1",
                    modifier = Modifier
                        .size(size = 63.dp)
                )
                Text(
                    text = "Gefunden ",
                    color = OffBlack,
                    style = androidx.compose.ui.text.TextStyle(
                        fontSize = 45.sp
                    ),
                    modifier = Modifier
                        .width(width = 265.dp)
                        .height(200.dp)
                        .padding(start = 25.dp)
                        .wrapContentHeight()
                )
            }
        }
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .background(color = OffWhite)
                .padding(start = 20.dp, end = 20.dp)            // Padding am "Add photo"
        ) {
            LazyColumn(
                modifier = Modifier,
                verticalArrangement = Arrangement.spacedBy(15.dp)

            ) {

                item {
                    Box(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(10.dp)
                            .background(OffWhite)
                    ){}
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
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 30.sp
                            ),
                            textAlign = TextAlign.Center,
                            modifier = Modifier

                        )
                    }
                }
                item {
                    Box( // "Ich suche... - Box"
                        modifier = Modifier
                            .border(1.dp, OffBlack)
                            .padding(start = 5.dp)
                    ) {
                        Text(
                            text = "Ich habe gefunden...",
                            color = DarkGray,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 30.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .border(1.dp, OffBlack)
                            .padding(5.dp)
                    ) {
                        Text(
                            text = "Jorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.",
                            color = DarkGray,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                        )
                    }
                }
                item {
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
                                    style = androidx.compose.ui.text.TextStyle(
                                        fontSize = 15.sp
                                    ),
                                    modifier = Modifier
                                )
                            }
                        }
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .border(1.dp, OffBlack)
                            .padding(start = 5.dp, top = 16.dp)
                    ) {
                        Text(
                            text = "Schreibe hier, wo du den Gegenstand gefunden hast...",
                            color = DarkGray,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(256.dp)
                        )
                    }
                }
                item {
                    Box(
                        modifier = Modifier
                            .background(OffWhite)
                            .fillMaxWidth()
                            .height(120.dp)
                            .padding(10.dp),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .background(MainGreen)
                                .width(width = 260.dp)
                                .height(height = 74.dp),
                            contentAlignment = Alignment.Center
                        ) {
                            Text(text = "Fundstück melden",
                                Modifier.clickable { navController.navigate(Screen.MainScreenTabGreen.route) },
                                style = androidx.compose.ui.text.TextStyle(
                                    fontSize = 20.sp,
                                    color = OffBlack
                                ),
                            )
                        }
                    }
                }
            }
        }
    }
}


// Detailierte Einträge

@Composable
fun DetailedEntryGreen(navController: NavHostController) {
    DetailedEntryMain(navController = navController, titel = "Iphone7", color = MainGreen)
}

@Composable
fun DetailedEntryBlue(navController: NavHostController) {
    DetailedEntryMain(navController = navController, titel = "Geldbeutel", color = MainBlue)
}

@Composable
fun DetailedEntryGreenPersonal(navController: NavHostController) {
    DetailedEntryPersonal(navController = navController, "Handy", MainGreen)
}

@Composable
fun DetailedEntryBluePersonal(navController: NavHostController) {
    DetailedEntryPersonal(navController = navController, titel = "Schlüssel", color = MainBlue)
}


// Chatfunktion

@Composable
fun PrivateChat(navController: NavHostController) {
    Column() {

        TopBar(text = "[username]", navController = navController)
        PrivateChatLayout(navController = navController)
        BottomBar(navController = navController)
    }
}



/////////////////////////////////////////
////////// SCREEN FRAGMENTS /////////////
/////////////////////////////////////////



// TODO: alignment der Nachrichten fixen (bei Implementierung des Chats) (--> je nach message rechts oder links)
@Composable
fun PrivateChatLayout(navController: NavHostController) {
    
    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(20.dp),

    ) {
        Box(modifier = Modifier.fillMaxWidth()) {
            Card(modifier = Modifier
                .width(250.dp)
                .height(70.dp),
                backgroundColor = MainGreen,
                shape = RoundedCornerShape(10.dp, 10.dp, 10.dp, 0.dp)
            ) {
                Text("Diese Nachricht kommt von einem anderen Nutzer!", Modifier.padding(15.dp), color = OffBlack, fontSize = 16.sp)
            }
        }



        Box(
            Modifier
                .fillMaxWidth()
                .padding(top = 15.dp),
        contentAlignment = Alignment.CenterEnd) {

            Card(modifier = Modifier
                .width(250.dp)
                .height(70.dp),
                backgroundColor = MainBlue,
                shape = RoundedCornerShape(10.dp, 10.dp, 0.dp, 10.dp)
            ) {
                Text("Diese Nachricht kommt von mir selber!", Modifier.padding(15.dp), color = OffBlack, fontSize = 16.sp)
            }
        }
    }

}

// Button für Eintrag auf der Main-Seite:
// man soll die Person, die das Objekt eingestellt hat, anschreiben können
@Composable
fun DetailedEntryButtonMessage(color: Color, navController: NavHostController) {

    Button( {navController.navigate(Screen.PrivateChat.route) }, // Button zum privaten Chat
        modifier = Modifier
            .background(color)
            .width(width = 260.dp)
            .height(height = 60.dp),
            shape = RoundedCornerShape(4.dp)
    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = R.drawable.baseline_chat_bubble_outline_24),
                "message_bubble",
                modifier = Modifier
                    .size(30.dp)
                    .padding(top = 5.dp))
            Spacer(Modifier.padding(start = 10.dp))
            Text(text = "Nachricht schreiben",
                Modifier.clickable { navController.navigate(Screen.MainScreenTabGreen.route) },
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 20.sp,
                    color = OffBlack
                )
            )
        }
    }
}

// Button auf persönlicher Seite: man kann eigene Einträge löschen
@Composable
fun DetailedEntryButtonDelete(color: Color, navController: NavHostController) {
    Button( { navController.navigate(Screen.PersonalEntrysBlue.route) }, // TODO: dieser Code ist nur temporary, bis wir Löschen implementieren
        modifier = Modifier
            .background(color)
            .width(width = 260.dp)
            .height(height = 74.dp),
        shape = RoundedCornerShape(4.dp),

    ) {
        Row(verticalAlignment = Alignment.CenterVertically) {
            Image(painterResource(id = R.drawable.baseline_chat_bubble_outline_24),
                "message_bubble",
                modifier = Modifier
                    .size(30.dp)
                    .padding(top = 5.dp))
            Spacer(Modifier.padding(start = 10.dp))
            Text(text = "Nachricht schreiben",
                Modifier.clickable { navController.navigate(Screen.MainScreenTabGreen.route) },
                style = androidx.compose.ui.text.TextStyle(
                    fontSize = 20.sp,
                    color = OffBlack
                )
            )
        }
    }
}


@Composable
fun DetailedEntryMain(navController: NavHostController, titel: String, color: Color) {

    Column(Modifier.background(OffWhite)){
        Box(
            modifier = Modifier
                .fillMaxWidth(),
        contentAlignment = Alignment.BottomCenter
        ) {
            LazyColumn(
                // contentPadding = ,
                // modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)

            ) {

                item {
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
                item {
                    Box( // Titel des Fundstücks
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = titel,
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = OffBlack,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 30.sp
                            )
                        )
                    }
                }
                item {// Fundstück Beschreibung
                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = "So sieht der Gegenstand aus: Jorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.",
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = OffBlack,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp
                            )
                        )
                    }
                }
                item {// Maps Standort
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
                item {// Ortsbeschreibung
                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, bottom = 140.dp)
                    ) {
                        Text(
                            text = "Hier habe ich den Gegenstand gefunden: Dorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.",
                            color = OffBlack,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )
                    }
                }

            }

            Box(
                Modifier.padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                DetailedEntryButtonMessage(color, navController)
            }
        }
    }

}

@Composable
fun DetailedEntryPersonal(navController: NavHostController, titel: String, color: Color) {

    Column(Modifier.background(OffWhite)){
        Box(
            modifier = Modifier
                .fillMaxWidth(),
            contentAlignment = Alignment.BottomCenter
        ) {
            LazyColumn(
                // contentPadding = ,
                // modifier = Modifier.padding(start = 20.dp, end = 20.dp),
                verticalArrangement = Arrangement.spacedBy(25.dp)

            ) {

                item {
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
                item {
                    Box( // Titel des Fundstücks
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = titel,
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = OffBlack,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 30.sp
                            )
                        )
                    }
                }
                item {// Fundstück Beschreibung
                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp)
                    ) {
                        Text(
                            text = "So sieht der Gegenstand aus: Jorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.",
                            modifier = Modifier
                                .fillMaxWidth(),
                            color = OffBlack,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp
                            )
                        )
                    }
                }
                item {// Maps Standort
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
                item {// Ortsbeschreibung
                    Box(
                        modifier = Modifier
                            .padding(start = 20.dp, end = 20.dp, bottom = 140.dp)
                    ) {
                        Text(
                            text = "Hier habe ich den Gegenstand gefunden: Dorem ipsum dolor sit amet, consectetur adipiscing elit. Nunc vulputate libero et velit interdum, ac aliquet odio mattis. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Curabitur tempus urna at turpis condimentum lobortis. Ut commodo efficitur neque.",
                            color = OffBlack,
                            style = androidx.compose.ui.text.TextStyle(
                                fontSize = 15.sp
                            ),
                            modifier = Modifier
                                .fillMaxWidth()
                                .height(160.dp)
                        )
                    }
                }

            }

            Box(
                Modifier.padding(bottom = 20.dp),
                contentAlignment = Alignment.Center
            ) {
                DetailedEntryButtonDelete(color, navController)
            }
        }
    }

}

@Composable
fun TopBar(text: String, navController: NavHostController) { // Text ist davon abhängig ob wir gerade auf der
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
                    .clickable { navController.navigate(Screen.MainScreenTabBlue.route) }
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
                    .fillMaxHeight()
                , contentAlignment = Alignment.TopEnd

            ) {// Personal Page button
                Image(
                    painter = painterResource(id = R.drawable.outline_account_circle_48_black),
                    contentDescription = "account_circle",
                    Modifier.clickable { navController.navigate(Screen.PersonalEntrysBlue.route) }
                )
            }
        }
    }
}

@Composable
fun BottomBar(navController: NavHostController) {
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
                onClick = {navController.navigate(Screen.GesuchtGefundenTabs.route)},
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
fun MainTabs(leftText: String, rightText: String, navController: NavHostController, routeBlue: String, routeGreen: String) {

    Row(Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.Center) {
// Gesucht Box
        Box(
            Modifier
                .fillMaxWidth(0.5f) // 1 Tab ist die halbe Breite lang
                .height(50.dp)
                .background(MainBlue)
                .clickable { navController.navigate(routeGreen) },
            contentAlignment = Alignment.Center

        ) {
            Text(leftText)
        }
// Gefunden Box
        Box(
            Modifier
                .fillMaxWidth(1f)
                .height(50.dp)
                .background(MainGreen)
                .clickable { navController.navigate(routeBlue) },
            contentAlignment = Alignment.Center
        ) {
            Text(rightText)
        }
    }
}

@Composable
fun ListEntryLayoutMain( // Ein Eintrag auf der Main Page
    colorBorder: Color,
    entryTitle: String,
    description: String,
) {
    Box(
        Modifier
            .padding(top = 20.dp, start = 20.dp, end = 20.dp, bottom = 10.dp)
            .fillMaxWidth()
            .height(90.dp)
            .clickable { TODO() }
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
            .clickable { TODO() }
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
fun ListOfEntrysLayoutMain(justForDisplayDesc: String, color: Color) { // die Auflistung der Fundsachen
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // nur Platzhalter für Visualisierung//
        ListEntryLayoutMain(color, "Handy", justForDisplayDesc)
        ListEntryLayoutMain(color, "Geldbeutel", justForDisplayDesc)
        ListEntryLayoutMain(color, "Schlüssel", justForDisplayDesc)
        ListEntryLayoutMain(color, "Tasche", justForDisplayDesc)
        ListEntryLayoutMain(color, "Iphone7", justForDisplayDesc)
        ListEntryLayoutMain(color, "Iphone7", justForDisplayDesc)
        ListEntryLayoutMain(color, "Iphone7", justForDisplayDesc)
    }
}

@Composable
fun ListOfEntrysLayoutPersonal(justForDisplayDesc: String, color: Color) {
    Column(
        Modifier
            .fillMaxSize()
            .padding(bottom = 70.dp)
            .verticalScroll(rememberScrollState())
    ) {
        // nur Platzhalter für Visualisierung//
        ListEntryLayoutPersonal(color, "Handy", justForDisplayDesc)
        ListEntryLayoutPersonal(color, "Geldbeutel", justForDisplayDesc)
        ListEntryLayoutPersonal(color, "Schlüssel", justForDisplayDesc)
        ListEntryLayoutPersonal(color, "Tasche", justForDisplayDesc)
        ListEntryLayoutPersonal(color, "Iphone7", justForDisplayDesc)
        ListEntryLayoutPersonal(color, "Iphone7", justForDisplayDesc)
        ListEntryLayoutPersonal(color, "Iphone7", justForDisplayDesc)
    }
}


// Split Preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val navController = rememberNavController()

    val justForDisplayDesc =
        "Lorem ipsum dolor sit amet, consetetur sadipscing elitr, sed diam nonumy eirmod tempor invidunt ut labore et dolore magna aliquyam erat, sed diam voluptua. At vero eos et accusam et justo duo dolores et ea rebum. Stet clita kasd gubergren, no sea takimata sanctus est Lorem ipsum dolor sit amet."

    NavApplicationHost(navController)


}