package com.example.mocogm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
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
    object DetailedEntry : Screen("detail_entry") // testen ob das so funktioniert lol
    object PrivateChat : Screen("direct_message")

    fun getDetailScreenRoute(itemID: String = "") = "detail_entry/$itemID"

}



interface ComposableType


class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private val itemViewModel: ItemViewModel by viewModels()
    private val itemListViewModel: ItemListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOCOGMTheme() {
                val navController = rememberNavController()
                NavApplicationHost(navController, authViewModel, itemViewModel,itemListViewModel)
            }
        }
    }
}

// Composable to store NavHost
@Composable
fun NavApplicationHost(navController: NavHostController,  authViewModel: AuthViewModel, itemViewModel: ItemViewModel, itemListViewModel: ItemListViewModel) {

    // TODO() wie handeln wir Zugriffe auf die bestehenden Items, also wie übergeben wir, welches Item aus der Liste angeklickt wurde?

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
            EntryList(MainTabsBlue(navController), itemListViewModel)
        }

        composable(Screen.EntryListMainGreen.route) {
            EntryList(MainTabsGreen(navController), itemListViewModel)
        }

        composable(Screen.EntryListPersonalBlue.route) {
            EntryList(PersonalTabsBlue(navController), itemListViewModel)
        }

        composable(Screen.EntryListPersonalGreen.route) {
            EntryList(PersonalTabsGreen(navController), itemListViewModel)
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
            NewItem(
                NewItemGesucht(navController),
                itemViewModel, authViewModel.getCurrentlyLoggedInUser()
            )
        }

        composable(Screen.NeuesItemGreen.route) {
            NewItem(
                NewItemGefunden(navController),
                itemViewModel, authViewModel.getCurrentlyLoggedInUser()
            )
        }


        // for show: Detailed Entry von der MainPageBlue
        composable(Screen.DetailedEntry.route) {
            DetailedEntry(DetailedEntryMainBlue(navController), itemViewModel, navController.currentBackStackEntry!!.arguments!!.getString("itemID")!!)
        }

        composable(Screen.PrivateChat.route) {
            PrivateChat(
                layoutData = PersonalTabsBlue(navController),
                "[anderer Benutzer]")
        }
    }
}

// Split Preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val navController = rememberNavController()
    NavApplicationHost(navController, viewModel(), viewModel(), viewModel())
}