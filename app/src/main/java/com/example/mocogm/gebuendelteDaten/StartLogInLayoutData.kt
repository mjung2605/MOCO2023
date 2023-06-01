package com.example.mocogm.gebuendelteDaten

import androidx.navigation.NavHostController
import com.example.mocogm.ComposableType
import com.example.mocogm.Screen
import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.ui.theme.MainGreen


///////////////START PAGES////////////
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