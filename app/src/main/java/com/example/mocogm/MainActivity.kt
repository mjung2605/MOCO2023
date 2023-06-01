package com.example.mocogm

import android.graphics.Color
import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.painter.Painter
import com.example.mocogm.ui.theme.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
//import com.example.mocogm.ComposableType.StartBlue.navController
import com.example.mocogm.ui.theme.MOCOGMTheme
import com.example.mocogm.composeComponents.*
import com.example.mocogm.gebuendelteDaten.*
import com.example.mocogm.viewmodels.*

sealed class Screen(val route: String) {

    // alle möglichen Screens
    object TitleScreen : Screen("home")
    object LogIn : Screen("log_in")
    object SignUp : Screen("sign_up")


    // mit beobachter kann dies bestimmt noch vereinfacht werden, indem wir einfach beobachten, was gerade übergeben wurde
    object EntryListMainGreen : Screen("entry_list_main_green")
    object EntryListMainBlue : Screen("entry_list_main_blue")
    object EntryListPersonalGreen : Screen("entry_list_personal_green")
    object EntryListPersonalBlue : Screen("entry_list_personal_blue")

    object GesuchtGefundenTabs : Screen("gesucht_gefunden_auswahl")
    object HinweisGefunden : Screen("hinweis_gefunden")
    object HinweisGesucht : Screen("hinweis_gesucht")

    object NeuesItemGreen : Screen("item_new_green")
    object NeuesItemBlue : Screen("item_new_blue")
    object DetailedEntry : Screen("detail_entry") // TODO() ausführen in mmain/personal; blue/green?
    object PrivateChat : Screen("direct_message")

}

interface ComposableType


class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOCOGMTheme() {
                val navController = rememberNavController()
                NavApplicationHost(navController, authViewModel)
            }
        }
    }
}

// Composable to store NavHost
@Composable
fun NavApplicationHost(navController: NavHostController, authViewModel: AuthViewModel) {

    // Datenbündel


    NavHost(navController = navController, startDestination = Screen.TitleScreen.route) {
        // define all possible destinations

        composable(Screen.TitleScreen.route) {
            CompleteLayoutStart(
                type = StartTitle(navController),
                viewModel = authViewModel
            )
        }

        composable(Screen.LogIn.route) {
            CompleteLayoutStart(
                type = StartLogIn(navController),
                viewModel = authViewModel
            )
        }

        composable(Screen.SignUp.route) {
            CompleteLayoutStart(
                type = StartSignUp(navController),
                viewModel = authViewModel
            )
        }

        composable(Screen.EntryListMainBlue.route) {
            EntryList(MainTabsBlue(navController))
        }

        composable(Screen.EntryListMainGreen.route) {
            EntryList(MainTabsGreen(navController))
        }

        composable(Screen.EntryListPersonalBlue.route) {
            EntryList(PersonalTabsBlue(navController))
        }

        composable(Screen.EntryListPersonalGreen.route) {
            EntryList(PersonalTabsGreen(navController))
        }

        composable(Screen.GesuchtGefundenTabs.route) {
            GesuchtGefundenBoxen(GGesucht(navController), GGefunden(navController))
        }

        composable(Screen.HinweisGefunden.route) {
            HinweisLayout(GGefunden(navController), HinweisGefunden(navController))
        }
        composable(Screen.HinweisGesucht.route) {
            HinweisLayout(GGesucht(navController), HinweisGesucht(navController))
        }

        composable(Screen.NeuesItemBlue.route) {
            NewItem(NewItemGesucht(navController))
        }

        composable(Screen.NeuesItemGreen.route) {
            NewItem(NewItemGefunden(navController))
        }

        // for show: Detailed Entry von der MainPageBlue
        composable(Screen.DetailedEntry.route) {
            DetailedEntry(DetailedEntryMainBlue(navController))
        }

        composable(Screen.PrivateChat.route) {
            PrivateChat(layoutData = PersonalTabsBlue(navController), "[anderer Benutzer]")
        }
    }
}

// Split Preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val navController = rememberNavController()
    NavApplicationHost(navController, viewModel())
}