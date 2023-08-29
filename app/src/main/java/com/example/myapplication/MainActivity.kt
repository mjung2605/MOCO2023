// MainActivity.kt
package com.example.myapplication

import ListItemScreen
import LoginScreen
import android.app.Activity
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ListItem
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

import androidx.lifecycle.ViewModelProvider
import com.example.myapplication.viewModels.AuthViewModel
import com.example.myapplication.viewModels.ListViewModel

import com.example.myapplication.views.SignNewItemSearch
import com.example.myapplication.views.StartScreen
import java.util.jar.Manifest

class MainActivity : ComponentActivity() {

    private val authViewModel: AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            val viewModel = ViewModelProvider(this).get(HomeViewModel::class.java)

            //ComposeCameraX {
            Surface(
                modifier = Modifier.fillMaxSize()
            ) {
                NewItemGefundenScreen(viewModel = HomeViewModel())
            }
            //}



            window.statusBarColor = getColor(R.color.black)
            NewItemGefundenScreen(viewModel)
            //NewItemGesuchtScreen(viewModel)
            //   SignNewItemSearch()
            //  StartScreen()
            //LoginScreen(viewModel = AuthViewModel())
            ListItemScreen(viewModel = ListViewModel())
        }
    }
}

