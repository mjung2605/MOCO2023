// MainActivity.kt
package com.example.myapplication

import ListItemScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.BlendMode.Companion.Screen
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.myapplication.viewModels.AuthViewModel
import com.example.myapplication.viewModels.ListViewModel
import com.example.myapplication.views.StartScreen
import com.example.myapplication.views.*
import com.google.android.gms.actions.ItemListIntents


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

       /*     //ComposeCameraX {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                NewItemGefundenScreen(viewModel = ItemViewModel())
            }*/
            //}
         //window.statusBarColor = getColor(R.color.black)
           // NewItemGefundenScreen(viewModel)
            //NewItemGesuchtScreen(viewModel)
            //SignNewItemSearch()
          //  StartScreen(navController)
            //LoginScreen(viewModel = AuthViewModel())

        }
    }
}
// Composable to store NavHost
@Composable
fun NavApplicationHost(navController: NavHostController, authViewModel: AuthViewModel, itemViewModel: ItemViewModel, itemListViewModel: ListViewModel) {


    NavHost(navController = navController, startDestination = "start") {
        // define all possible destinations
        composable("start") {
                StartScreen(navController)
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

// Split Preview
@Preview(showBackground = true)
@Composable
fun DefaultPreview() {

    val navController = rememberNavController()
    NavApplicationHost(navController,  viewModel(), viewModel(), viewModel())
}