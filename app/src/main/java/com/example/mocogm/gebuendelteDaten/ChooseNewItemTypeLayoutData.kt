package com.example.mocogm.gebuendelteDaten

import androidx.navigation.NavHostController
import com.example.mocogm.ComposableType
import com.example.mocogm.R
import com.example.mocogm.Screen
import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.ui.theme.MainGreen

interface GGBoxes:  ComposableType {

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
