package com.example.myapplication


import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.runtime.Composable
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.viewModels.AuthViewModel
import com.example.myapplication.viewModels.ListViewModel
import com.example.myapplication.views.StartScreen
import com.example.myapplication.views.*


sealed class Screen(val route: String) {
    object StartScreen: Screen("start")
    object SignInScreen: Screen("signIn")
    object ListScreen: Screen("listItems")
    object AddItemScreen: Screen ("addItem")
    object SearchFoundScreen: Screen ("searchFound")
    object SignNewSearchScreen: Screen ("signSearch")
    object SignNewFoundScreen: Screen ("signFound")
    object NewFoundScreen: Screen ("found")
    object NewSearchScreen: Screen ("search")
    object LoginScreen: Screen ("login")
}
class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()
    private val itemViewModel: ItemViewModel by viewModels()
    private val listViewModel: ListViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
            val navController = rememberNavController()
            NavApplicationHost(navController, authViewModel, itemViewModel, listViewModel)
        }
    }
}

@Composable
fun NavApplicationHost(navController: NavHostController, authViewModel: AuthViewModel, itemViewModel: ItemViewModel, itemListViewModel: ListViewModel) {


    NavHost(navController = navController, startDestination = "start") {

        composable("start") {
            StartScreen(navController)
        }
        composable("login") {
            LoginScreen(
                navController,
                viewModel = AuthViewModel()
            )
        }
        composable("listItems"){
            ListItemScreen(
                navController,
                viewModel = ListViewModel()
            )
        }
        composable("searchFound"){
            SearchFoundScreen(navController)
        }
        composable("signSearch"){
            SignNewItemSearch(navController)
        }
        composable("signFound"){
            SignNewItemFound(navController)
        }
        composable("found"){
            NewItemGefundenScreen(navController, ItemViewModel())
        }
        composable("search"){
            NewItemGesuchtScreen(navController, ItemViewModel())
        }
    }
}
