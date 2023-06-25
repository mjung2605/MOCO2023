package com.example.mocogm.gebuendelteDaten

import androidx.navigation.NavController
import com.example.mocogm.ComposableType
import com.example.mocogm.R
import com.example.mocogm.Screen
import com.example.mocogm.ui.theme.MainGreen


interface NewItem: ComposableType {

    val color: androidx.compose.ui.graphics.Color
    val iconDrawableID: Int
    val iconDesc: String
    val typeTitle: String
    val onClickAdd: () -> Unit
    val onClickNav: () -> Unit
    val titlePlaceholder: String
}

data class NewItemGesucht(val navController: NavController): NewItem {
    override val color = MainGreen
    override val iconDrawableID = R.drawable.box
    override val iconDesc = "box"
    override val typeTitle = "Gesucht"
    override val onClickAdd = {}
    override val onClickNav =
        // TODO() add new item to database
        // for now only navigates to the tab the Item is added to.
        // when implementing database, observe states and display loading symbol as needed
        {navController.navigate(
            if (color==MainGreen) Screen.EntryListPersonalGreen.route else Screen.EntryListPersonalBlue.route
        )}
    override val titlePlaceholder = "Ich suche..."
}

data class NewItemGefunden(val navController: NavController): NewItem {
    override val color = MainGreen
    override val iconDrawableID = R.drawable.box
    override val iconDesc = "box"
    override val typeTitle = "Gefunden"
    override val onClickAdd = {}
    override val onClickNav ={
            navController.navigate(
                if (color == MainGreen) Screen.EntryListPersonalGreen.route else Screen.EntryListPersonalBlue.route
        )}
    override val titlePlaceholder = "Ich habe gefunden..."
}

