package com.example.myapplication.views

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.myapplication.R
import com.example.myapplication.ui.theme.MainBlue
import com.example.myapplication.ui.theme.MainGreen
import com.example.myapplication.ui.theme.OffBlack


@Composable
fun SignNewItemSearch(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize()
            .background(MainBlue)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f),
            contentAlignment = Alignment.Center
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lupe),
                    contentDescription = "lupe",
                    modifier = Modifier.size(150.dp)
                )
                Text(
                    text = "Gesucht",
                    fontSize = 50.sp
                )
            }
        }


        Box(
            modifier = Modifier
                .fillMaxWidth()
                .fillMaxHeight()
                .background(color = Color.White),
            contentAlignment = Alignment.Center
        ) {
            Column(
                modifier = Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                Text(
                    "Bitte stelle sicher, dass dein gesuchter/gefundener Gegenstand nicht bereits eingestellt wurde.",
                    fontSize = 20.sp,
                    color = LocalContentColor.current.copy(alpha = 0.8f)
                )

                val checkedState = remember { mutableStateOf(false) }

                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    Checkbox(
                        checked = checkedState.value,
                        onCheckedChange = {
                            checkedState.value = it
                        }
                    )

                    Text(
                        text = "Ich habe bereits nachgesehen, meinen Gegenstand jedoch nicht gefunden"
                    )
                }
                Spacer(modifier = Modifier.weight(1f))

                Button(
                    onClick = {navController.navigate("search")},
                    enabled = checkedState.value,
                    modifier = Modifier
                        .fillMaxWidth(),

                    colors = ButtonDefaults.buttonColors(contentColor = OffBlack)

                ) {
                    Text("Suche starten", fontSize = 20.sp)
                }
            }
        }
    }
}
