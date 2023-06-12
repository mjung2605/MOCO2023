package com.example.mocogm.gebuendelteDaten

import androidx.navigation.NavHostController
import com.example.mocogm.ComposableType
import com.example.mocogm.Screen

import com.example.mocogm.ui.theme.MainBlue
import com.example.mocogm.ui.theme.MainGreen

interface MainLayout: ComposableType {

    val pageTitle: String
    val colorBorder: androidx.compose.ui.graphics.Color

}

abstract class MainUserInterface(private val navController: NavHostController): MainLayout {

    val onClickHome: () -> Unit = { navController.navigate(Screen.EntryListMainBlue.route) }
    val onClickPersonal: () -> Unit = { navController.navigate(Screen.EntryListPersonalBlue.route) }
    val onClickChat: () -> Unit = { navController.navigate(Screen.PrivateChat.route) }


    val onClickDetailEntry: (String) -> Unit = { it ->
        navController.navigate(Screen.DetailedEntry.getDetailScreenRoute(it)) }

    abstract val onClickTabGreen: () -> Unit // andere implementierung, je nachdem, ob wir auf main oder personal sind
    abstract val onClickTabBlue: () -> Unit

    val onClickAddEntry: () -> Unit = {navController.navigate(Screen.GesuchtGefundenTabs.route)}
}


abstract class MainTabs(private val navController: NavHostController): MainUserInterface(navController), MainLayout {
    override val onClickTabGreen: () -> Unit = { navController.navigate(Screen.EntryListMainGreen.route) }
    override val onClickTabBlue: () -> Unit = { navController.navigate(Screen.EntryListMainBlue.route) }

    override val pageTitle: String = "GoldMine"
}

abstract class PersonalTabs(private val navController: NavHostController): MainUserInterface(navController), MainLayout {
    override val onClickTabGreen: () -> Unit = { navController.navigate(Screen.EntryListPersonalGreen.route) }
    override val onClickTabBlue: () -> Unit = { navController.navigate(Screen.EntryListPersonalBlue.route) }

    val onClickDelete: () -> Unit = {

        // TODO: delete entry from database: soll in composable gelöst werden
        navController.navigate(navController.currentBackStackEntry!!.id) // geht zurück?

    }
    override val pageTitle: String = "Persönliches"
}

data class MainTabsBlue(val navController: NavHostController): MainTabs(navController), MainLayout {
    override val colorBorder = MainBlue
}

data class MainTabsGreen(val navController: NavHostController): MainTabs(navController), MainLayout {
    override val colorBorder = MainGreen
}

data class PersonalTabsBlue(val navController: NavHostController): PersonalTabs(navController), MainLayout {
    override val colorBorder = MainBlue
}

data class PersonalTabsGreen(val navController: NavHostController): PersonalTabs(navController), MainLayout {
    override val colorBorder = MainGreen
}