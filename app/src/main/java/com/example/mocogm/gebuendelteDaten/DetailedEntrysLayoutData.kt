package com.example.mocogm.gebuendelteDaten

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.mocogm.ComposableType
import com.example.mocogm.R
import com.example.mocogm.Screen
import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.ui.theme.MainGreen

interface DetailedEntry: ComposableType {
    // TODO() Eigenschaften des Items selbst später bei MVVM Implementierung alle mit observen des ViewModels/Models lösen

    val colorBorder: Color

    //
    val buttonColor: Color
    val buttonText: String
    val onClickAction: () -> Unit
    val iconDrawableID: Int
    val iconDesc: String

}

abstract class DetailedEntryMain(private val navController: NavController): DetailedEntry {

    override val buttonColor = MainGreen // Button für Message ist immer Grün
    override val buttonText = "Nachricht"

    override val onClickAction = { navController.navigate(Screen.PrivateChat.route) }
    /* TODO: mehr als einfache Navigation machen: es muss festgestellt werden, an wen die Nachricht geht.
    Außerdem müssen wir gucken, wie wir handlen, wenn ein User angeschrieben wird.
     */

    override val iconDrawableID: Int = R.drawable.baseline_chat_bubble_outline_24
    override val iconDesc: String = "message-bubble"

}

abstract class DetailedEntryPersonal(private val navController: NavController): DetailedEntry {

    override val buttonColor = MainBlue // Button für Message ist immer Grün
    override val buttonText = "Eintrag löschen"

    override val onClickAction = {
        // TODO: Zugriff auf ViewModel und Löschen des Eintrags von DB
        navController.navigate(navController.currentBackStackEntry!!.id) // geht zurück zu Seite wo Nutzer herkam
    }
    override val iconDrawableID: Int = R.drawable.outline_delete_48
    override val iconDesc: String = "trashcan"

}

data class DetailedEntryMainBlue(val navController: NavController): DetailedEntryMain(navController) {

    override val colorBorder: Color = MainBlue
}

data class DetailedEntryMainGreen(val navController: NavController): DetailedEntryMain(navController) {

    override val colorBorder: Color = MainGreen
}

data class DetailedEntryPersonalBlue(val navController: NavController): DetailedEntryMain(navController) {

    override val colorBorder: Color = MainBlue
}

data class DetailedEntryPersonalGreen(val navController: NavController): DetailedEntryMain(navController) {

    override val colorBorder: Color = MainGreen
}
