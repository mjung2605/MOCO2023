// MainActivity.kt
package com.example.myapplication

import ListItemScreen
import LoginScreen
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ListItem

import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewModels.AuthViewModel
import com.example.myapplication.viewModels.ListViewModel

import com.example.myapplication.views.SignNewItemSearch
import com.example.myapplication.views.StartScreen

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
            window.statusBarColor = getColor(R.color.black)
        //    NewItemGefundenScreen(viewModel)
         //   NewItemGesuchtScreen(viewModel)
         //   SignNewItemSearch()
          //  StartScreen()
            //LoginScreen(viewModel = AuthViewModel())
            ListItemScreen(viewModel = ListViewModel())
        }
    }
}
