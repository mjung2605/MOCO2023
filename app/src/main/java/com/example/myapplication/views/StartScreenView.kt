package com.example.myapplication.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.Screen
import com.example.myapplication.ui.theme.MainBlue
import com.example.myapplication.ui.theme.MainGreen

@Composable
fun StartScreen(navController: NavHostController) {
    val onClickStartTitle: () -> Unit = { navController.navigate(Screen.ListScreen.route) }
    Box(
        modifier = Modifier
            .background(brush = Brush.verticalGradient(listOf(MainBlue, MainGreen)))
            .clickable { (navController.navigate("listItems")) }
            .fillMaxSize(),
        contentAlignment = androidx.compose.ui.Alignment.Center
    ) {
        Image(
            painter = painterResource(id = R.drawable.logox),
            contentDescription = "gm_logo",
            modifier = Modifier.size(400.dp)


        )


    }
}






