// HomeScreen.kt
package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MainBlue
import com.example.myapplication.ui.theme.OffBlack


import androidx.compose.ui.Modifier
import com.example.myapplication.ui.theme.LightGray
import com.example.myapplication.ui.theme.OffWhite

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewItemGesuchtScreen(viewModel: HomeViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
            .verticalScroll(rememberScrollState())
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(144.dp)
                .background(MainBlue),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.lupe),
                    contentDescription = "Box Image",
                    modifier = Modifier
                        .size(size = 63.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Gesucht",
                    color = OffBlack,
                    style = TextStyle(
                        fontSize = 45.sp
                    ),
                    modifier = Modifier
                        .width(265.dp)
                        .height(200.dp)
                        .padding(start = 25.dp)
                        .wrapContentHeight()
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))

        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier
                .size(172.dp)
                .background(LightGray)
        ) {
            Text(
                text = "Gesucht",
                color = OffBlack,
                style = TextStyle(fontSize = 45.sp),
            )
        }

        OutlinedTextField(
            value = viewModel.gesuchtTitleEingabe.value,
            onValueChange = { newTitle ->
                viewModel.gesuchtTitel(newTitle)
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            label = { Text(text = "Ich suche...") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.gesuchtBeschreibungEingabe.value,
            onValueChange = { newDescription ->
                viewModel.gesuchtBeschreibung(newDescription)
            },
            modifier = Modifier
                .padding(horizontal = 16.dp)
                .fillMaxWidth(),
            label = { Text(text = "Beschreibung hinzufügen...") }
        )

        Spacer(modifier = Modifier.height(16.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(140.dp)
                .background(color = OffWhite),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier,
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                verticalAlignment = Alignment.CenterVertically,

                ) {
                Image(
                    painter = painterResource(id = R.drawable.location),
                    contentDescription = "placeholder 1",
                    modifier = Modifier
                        .size(size = 72.dp)
                )
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier
                        .background(color = MainBlue)
                        .padding(
                            start = 32.dp,
                            top = 10.dp,
                            end = 32.dp,
                            bottom = 10.dp
                        )
                ) {
                    Text(
                        text = "Standort hinzufügen"
                    )
                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = {
                val newItem = NewItem(
                    gefundenText = viewModel.gesuchtTitleEingabe.value,
                    beschreibungText = viewModel.gesuchtBeschreibungEingabe.value,
                )

                viewModel.saveData(newItem)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp)
        ) {
            Text(text = "Objekt Suchen")
        }


    }
}

