package com.example.mocogm

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.example.mocogm.ui.theme.*
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.mocogm.ui.theme.MOCOGMTheme
import com.example.mocogm.composeComponents.*
import android.Manifest
import android.content.pm.PackageManager
import android.net.Uri
import android.util.Log
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import coil.compose.rememberImagePainter
import java.io.File
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

sealed class Screen(val route: String) {

    // alle möglichen Screens
    object TitleScreen: Screen("home")
    object LogIn: Screen("log_in")
    object SignUp: Screen("sign_up")


    // mit beobachter kann dies bestimmt noch vereinfacht werden, indem wir einfach beobachten, was gerade übergeben wurde
    object EntryListMainGreen: Screen("entry_list_main_green")
    object EntryListMainBlue: Screen("entry_list_main_blue")
    object EntryListPersonalGreen: Screen("entry_list_personal_green")
    object EntryListPersonalBlue: Screen("entry_list_personal_blue")

    object GesuchtGefundenTabs: Screen ("gesucht_gefunden_auswahl")
    object HinweisGefunden: Screen("hinweis_gefunden")
    object HinweisGesucht: Screen("hinweis_gesucht")

    object NeuesItemGreen: Screen ("item_new_green")
    object NeuesItemBlue: Screen("item_new_blue")
    object DetailedEntry: Screen("detail_entry")
    object PrivateChat: Screen("direct_message")

}

class MainActivity : ComponentActivity() {

    private lateinit var outputDirectory: File
    private lateinit var cameraExecutor: ExecutorService

    private var shouldShowCamera: MutableState<Boolean> = mutableStateOf(false)

    private lateinit var photoUri: Uri
    private var shouldShowPhoto: MutableState<Boolean> = mutableStateOf(false)

    private val requestPermissionLauncher = registerForActivityResult(
        ActivityResultContracts.RequestPermission()
    ) { isGranted ->
        if (isGranted) {
            Log.i("kilo", "Permission granted")
            shouldShowCamera.value = true
        } else {
            Log.i("kilo", "Permission denied")
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MOCOGMTheme() {
                val navController = rememberNavController()
                NavApplicationHost(navController)
            }

            if (shouldShowCamera.value) {
                CameraView(
                    outputDirectory = outputDirectory,
                    executor = cameraExecutor,
                    onImageCaptured = ::handleImageCapture
                ) { Log.e("kilo", "View error:", it) }
            }

            if (shouldShowPhoto.value) {
                Image(
                    painter = rememberImagePainter(photoUri),
                    contentDescription = null,
                    modifier = Modifier.fillMaxSize()
                )
            }
        }

        requestCameraPermission()

        outputDirectory = getOutputDirectory()
        cameraExecutor = Executors.newSingleThreadExecutor()
    }

    private fun requestCameraPermission() {
        when {
            ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) == PackageManager.PERMISSION_GRANTED -> {
                Log.i("kilo", "Permission previously granted")
                shouldShowCamera.value = true
            }

            ActivityCompat.shouldShowRequestPermissionRationale(
                this,
                Manifest.permission.CAMERA
            ) -> Log.i("kilo", "Show camera permissions dialog")

            else -> requestPermissionLauncher.launch(Manifest.permission.CAMERA)
        }
    }

    private fun handleImageCapture(uri: Uri) {
        Log.i("kilo", "Image captured: $uri")
        shouldShowCamera.value = false

        photoUri = uri
        shouldShowPhoto.value = true
    }

    private fun getOutputDirectory(): File {
        val mediaDir = externalMediaDirs.firstOrNull()?.let {
            File(it, resources.getString(R.string.app_name)).apply { mkdirs() }
        }

        return if (mediaDir != null && mediaDir.exists()) mediaDir else filesDir
    }

    override fun onDestroy() {
        super.onDestroy()
        cameraExecutor.shutdown()
    }

}

// Composable to store NavHost
@Composable
fun NavApplicationHost(navController: NavHostController) {

    NavHost(navController = navController, startDestination = Screen.TitleScreen.route) {
        // define all possible destinations

        composable(Screen.TitleScreen.route) { StartScreen(isTitleScreen =  true, onClickStartTitle = { navController.navigate(Screen.LogIn.route) }, isLogInScreen = false) }
        composable(Screen.LogIn.route) { StartScreen(isTitleScreen = false, isLogInScreen = true, onClickAction = {navController.navigate(Screen.EntryListMainBlue.route)}, buttonText = "Einloggen", buttonColor = MainBlue, onClickGoToOther =  {navController.navigate(Screen.SignUp.route)}, textZeile1 = "Du hast noch keinen Account?", textZeile2 = "Registriere dich hier!") }
        composable(Screen.SignUp.route) { StartScreen(false, isLogInScreen = false, onClickAction = {navController.navigate(Screen.EntryListMainBlue.route)}, buttonText = "Account erstellen", buttonColor = MainGreen, onClickGoToOther = {navController.navigate(Screen.LogIn.route)}, textZeile1 = "Du besitzt schon einen Account?", textZeile2 = "Melde dich hier an!") }

        composable(Screen.EntryListMainBlue.route) { EntryList(
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
        ) }

        composable(Screen.EntryListMainGreen.route) {EntryList(
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
        ) }

        composable(Screen.EntryListPersonalBlue.route) { EntryList(
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
        ) }

        composable(Screen.EntryListPersonalGreen.route) {EntryList(
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
        ) }

        composable(Screen.GesuchtGefundenTabs.route) { GesuchtGefundenTabs(onClickToGesucht = {navController.navigate(Screen.HinweisGesucht.route)}, onClickToGefunden = {navController.navigate(Screen.HinweisGefunden.route)} ) }

        composable(Screen.HinweisGefunden.route) { HinweisLayout(
            color = MainGreen, text = "Gefunden",
            iconDrawable = painterResource(id = R.drawable.box), iconDesc = "box", modifier = Modifier
                .size(180.dp)
                .padding(55.dp, 20.dp, 10.dp, 0.dp), "Gefunden", { navController.navigate(Screen.NeuesItemGreen.route) }, MainGreen) }

        composable(Screen.HinweisGesucht.route) { HinweisLayout(color = MainBlue, text = "Gesucht",
            iconDrawable = painterResource (id = R.drawable.lupe), iconDesc = "lupe", modifier = Modifier
                .size(180.dp)
                .padding(40.dp, 20.dp, 10.dp, 0.dp), "Gesucht", { navController.navigate(Screen.NeuesItemBlue.route) }, MainBlue) }


        composable(Screen.NeuesItemBlue.route) { NewItem(color = MainBlue, iconDrawable = painterResource(id = R.drawable.lupe), iconDesc = "lupe", text = "Gesucht", onClickNav = { navController.navigate(Screen.EntryListPersonalBlue.route) } ) }

        composable(Screen.NeuesItemGreen.route) { NewItem(color = MainGreen, iconDrawable = painterResource(id = R.drawable.box), iconDesc = "box", text = "Gefunden", onClickNav = { navController.navigate(Screen.EntryListPersonalBlue.route) } ) }


        // for show: Detailed Entry von der MainPage
        composable(Screen.DetailedEntry.route) { DetailedEntry(
            titel = "IPhone",
            color = MainBlue,
            onClickAction = { navController.navigate(Screen.PrivateChat.route) },
            buttonText = "Nachricht",
            iconDrawable = painterResource(id = R.drawable.baseline_chat_bubble_outline_24),
            iconDesc = "message_bubble") }

        composable(Screen.PrivateChat.route) { PrivateChat( { navController.navigate(Screen.EntryListMainBlue.route) }, { navController.navigate(Screen.EntryListPersonalBlue.route) }, { navController.navigate(Screen.GesuchtGefundenTabs.route) } ) }

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