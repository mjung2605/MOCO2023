package com.example.mocogm.gebuendelteDaten

import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import com.example.mocogm.ComposableType
import com.example.mocogm.R
import com.example.mocogm.Screen
import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.ui.theme.MainGreen

// ziemlich redundant. Wir können auch vllt einfach innerhalb des Composables eine Abfrage machen ob der current user der
// owner des Items ist und entsprechend den Button displayen

interface DetailedEntryLayout: ComposableType

class DetailedEntryMain(private val navController: NavController): DetailedEntryLayout

class DetailedEntryPersonal(private val navController: NavController): DetailedEntryLayout {
    val onClickAction = {
        // TODO: Zugriff auf ViewModel und Löschen des Eintrags von DB
        navController.navigate(navController.currentBackStackEntry!!.id) // geht zurück zu Seite wo Nutzer herkam
    }
}

