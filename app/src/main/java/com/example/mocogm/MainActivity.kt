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
    object DetailedEntry : Screen("detail_entry")
    object PrivateChat : Screen("direct_message")

}

////// HELFER KLASSEN/INTERFACES, UM DATEN ZU BÜNDELN /////


///
interface ComposableType

///START PAGES///
interface LogSignType: ComposableType {

    val heading: String
    val onClickNavigate: () -> Unit
    val onClickLogSignIn: () -> Unit
    val onClickGoToOther: () -> Unit
    val buttonText: String
    val buttonColor: androidx.compose.ui.graphics.Color
    val textZeile1: String
    val textZeile2: String

}

data class StartTitle(val navController: NavHostController): ComposableType {
    val onClickStartTitle: () -> Unit = { navController.navigate(Screen.LogIn.route) }
}

data class StartSignUp(val navController: NavHostController): LogSignType {
    override val heading = "SIGN UP"
    override val onClickNavigate = { navController.navigate(Screen.EntryListMainBlue.route) }
    override val onClickLogSignIn = {} // muss in den composables mit zugriff auf VM initialisiert werden
    override val onClickGoToOther = { navController.navigate(Screen.LogIn.route) }
    override val buttonText = "Account erstellen"
    override val buttonColor = MainGreen
    override val textZeile1 = "Du besitzt schon einen Account?"
    override val textZeile2 = "Melde dich hier an!"
}

data class StartLogIn(val navController: NavHostController): LogSignType {
    override val heading = "LOG IN"
    override val onClickNavigate = { navController.navigate(Screen.EntryListMainBlue.route) }
    override val onClickLogSignIn = {} // muss in den composables mit zugriff auf VM initialisiert werden
    override val onClickGoToOther = { navController.navigate((Screen.SignUp.route)) }
    override val buttonText = "Einloggen"
    override val buttonColor = MainBlue
    override val textZeile1 = "Du hast noch keinen Account?"
    override val textZeile2 = "Registriere dich hier!"
}

//////

interface GGBoxes: ComposableType {

    val titleText: String
    val bgColor: androidx.compose.ui.graphics.Color
    val iconDesc: String
    val iconDrawableID: Int
    val onClick: () -> Unit
}

data class GGefunden(val navController: NavHostController): GGBoxes {
    override val titleText = "Gefunden"
    override val bgColor = MainGreen
    override val iconDesc = "box"
    override val iconDrawableID = R.drawable.box
    override val onClick = { navController.navigate(Screen.HinweisGefunden.route) }
}

data class GGesucht(val navController: NavHostController): GGBoxes {
    override val titleText = "Gesucht"
    override val bgColor = MainBlue
    override val iconDesc = "lupe"
    override val iconDrawableID = R.drawable.lupe
    override val onClick = { navController.navigate(Screen.HinweisGesucht.route) }
}

interface Hinweis: ComposableType {

    val woGesucht: String
    val onClickButton: () -> Unit
    val buttonColor: androidx.compose.ui.graphics.Color

}

data class HinweisGesucht(val navController: NavHostController): Hinweis {

    override val woGesucht = "Gefunden"
    override val onClickButton = { navController.navigate(Screen.NeuesItemBlue.route) }
    override val buttonColor = MainBlue

}

data class HinweisGefunden(val navController: NavHostController): Hinweis {

    override val woGesucht = "Gesucht"
    override val onClickButton = { navController.navigate(Screen.NeuesItemGreen.route) }
    override val buttonColor = MainGreen

}

//////////

interface MainLayout: ComposableType {

    val pageTitle: String
    val colorBorder: androidx.compose.ui.graphics.Color

}

abstract class MainInterface(private val navController: NavHostController): ComposableType {

    val onClickHome: () -> Unit = { navController.navigate(Screen.EntryListMainBlue.route) }
    val onClickPersonal: () -> Unit = {}
    val onClickChat: () -> Unit = {}

    val onClickTabGreen: () -> Unit = {}
    val onClickTabBlue: () -> Unit = {}

    val onClickAddEntry: () -> Unit = {}
}

data class MainTabsBlue(val navController: NavHostController): MainInterface(navController), MainLayout {

    override val pageTitle: String = "GoldMine"
    override val colorBorder = MainBlue

}

data class MainTabsGreen(val navController: NavHostController): MainInterface(navController), MainLayout {

    override val pageTitle: String = "GoldMine"
    override val colorBorder = MainGreen

}

data class PersonalTabsBlue(val navController: NavHostController): MainInterface(navController), MainLayout {

    override val pageTitle: String = "Persönliches"
    override val colorBorder = MainBlue

}

data class PersonalTabsGreen(val navController: NavHostController): MainInterface(navController), MainLayout {

    override val pageTitle: String = "Persönliches"
    override val colorBorder = MainGreen

}



///////////// HELFERKLASSEN ENDE //////////////////

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
            EntryList(
                topText = "GoldMine",
                onClickHome = { navController.navigate(Screen.EntryListMainBlue.route) },
                onClickProfile = { navController.navigate(Screen.EntryListPersonalBlue.route) },
                onClickBlue = { navController.navigate(Screen.EntryListMainBlue.route) },
                onClickGreen = { navController.navigate(Screen.EntryListMainGreen.route) },
                onClickAddEntry = { navController.navigate(Screen.GesuchtGefundenTabs.route) },
                colorBorder = MainBlue,
                entryTitle = "IPhone",
                description = "Habe in der Bibliothek ein IPhone gefunden. Rose Gold, mit durchsichtiger Handyhülle. Es lag bei den Selbstverbuchungsautomaten. Ich habe es an der Servicetheke abgegeben, wo es eine Woche liegen bleibt, bis es hinten zu den Fundsachen gelegt wird. Abholung nur mit MultiCa",
                onClickDetailEntry = { navController.navigate(Screen.DetailedEntry.route) },
                isPersonal = false
            )
        }

        composable(Screen.EntryListMainGreen.route) {
            EntryList(
                topText = "GoldMine",
                onClickHome = { navController.navigate(Screen.EntryListMainBlue.route) },
                onClickProfile = { navController.navigate(Screen.EntryListPersonalBlue.route) },
                onClickBlue = { navController.navigate(Screen.EntryListMainBlue.route) },
                onClickGreen = { navController.navigate(Screen.EntryListMainGreen.route) },
                onClickAddEntry = { navController.navigate(Screen.GesuchtGefundenTabs.route) },
                colorBorder = MainGreen,
                entryTitle = "IPhone",
                description = "Habe in der Bibliothek ein IPhone gefunden. Rose Gold, mit durchsichtiger Handyhülle. Es lag bei den Selbstverbuchungsautomaten. Ich habe es an der Servicetheke abgegeben, wo es eine Woche liegen bleibt, bis es hinten zu den Fundsachen gelegt wird. Abholung nur mit MultiCa",
                onClickDetailEntry = { navController.navigate(Screen.DetailedEntry.route) },
                isPersonal = false
            )
        }

        composable(Screen.EntryListPersonalBlue.route) {
            EntryList(
                topText = "Persönliches",
                onClickHome = { navController.navigate(Screen.EntryListMainBlue.route) },
                onClickProfile = { navController.navigate(Screen.EntryListPersonalBlue.route) },
                onClickBlue = { navController.navigate(Screen.EntryListMainBlue.route) },
                onClickGreen = { navController.navigate(Screen.EntryListPersonalGreen.route) },
                onClickAddEntry = { navController.navigate(Screen.GesuchtGefundenTabs.route) },
                colorBorder = MainBlue,
                entryTitle = "IPhone",
                description = "Habe in der Bibliothek ein IPhone gefunden. Rose Gold, mit durchsichtiger Handyhülle. Es lag bei den Selbstverbuchungsautomaten. Ich habe es an der Servicetheke abgegeben, wo es eine Woche liegen bleibt, bis es hinten zu den Fundsachen gelegt wird. Abholung nur mit MultiCa",
                onClickDetailEntry = { navController.navigate(Screen.DetailedEntry.route) },
                isPersonal = true
                // ondelete hinzufügen
            )
        }

        composable(Screen.EntryListPersonalGreen.route) {
            EntryList(
                topText = "Persönliches",
                onClickHome = { navController.navigate(Screen.EntryListMainBlue.route) },
                onClickProfile = { navController.navigate(Screen.EntryListPersonalBlue.route) },
                onClickBlue = { navController.navigate(Screen.EntryListPersonalBlue.route) },
                onClickGreen = { navController.navigate(Screen.EntryListPersonalGreen.route) },
                onClickAddEntry = { navController.navigate(Screen.GesuchtGefundenTabs.route) },
                colorBorder = MainGreen,
                entryTitle = "IPhone",
                description = "Habe in der Bibliothek ein IPhone gefunden. Rose Gold, mit durchsichtiger Handyhülle. Es lag bei den Selbstverbuchungsautomaten. Ich habe es an der Servicetheke abgegeben, wo es eine Woche liegen bleibt, bis es hinten zu den Fundsachen gelegt wird. Abholung nur mit MultiCa",
                onClickDetailEntry = { navController.navigate(Screen.DetailedEntry.route) },
                isPersonal = true
                // ondelete hinzufügen
            )
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
                color = MainBlue,
                iconDrawable = painterResource(id = R.drawable.lupe),
                iconDesc = "lupe",
                text = "Gesucht",
                onClickNav = { navController.navigate(Screen.EntryListPersonalBlue.route) })
        }

        composable(Screen.NeuesItemGreen.route) {
            NewItem(
                color = MainGreen,
                iconDrawable = painterResource(id = R.drawable.box),
                iconDesc = "box",
                text = "Gefunden",
                onClickNav = { navController.navigate(Screen.EntryListPersonalBlue.route) })
        }


        // for show: Detailed Entry von der MainPage
        composable(Screen.DetailedEntry.route) {
            DetailedEntry(
                titel = "IPhone",
                color = MainBlue,
                onClickAction = { navController.navigate(Screen.PrivateChat.route) },
                buttonText = "Nachricht",
                iconDrawable = painterResource(id = R.drawable.baseline_chat_bubble_outline_24),
                iconDesc = "message_bubble"
            )
        }

        composable(Screen.PrivateChat.route) {
            PrivateChat(
                { navController.navigate(Screen.EntryListMainBlue.route) },
                { navController.navigate(Screen.EntryListPersonalBlue.route) },
                { navController.navigate(Screen.GesuchtGefundenTabs.route) })
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