package com.example.mocogm.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mocogm.ui.theme.*
import com.example.mocogm.R

@Preview
@Composable
fun TopBar(text: String = "GoldMine", onClickHome: () -> Unit = {}, onClickChat: () -> Unit = {}, onClickProfile: () -> Unit = {})  { // Text ist davon abhängig ob wir gerade auf der Main Page oder der Personal Page sind
    Box(
        Modifier
            .fillMaxWidth()
            .height(55.dp)
            .background(OffWhite)
    ) {
        Row(horizontalArrangement = Arrangement.SpaceEvenly, verticalAlignment = Alignment.CenterVertically) { // Aufreihung der Elemente in TopBar
            Box(
                Modifier
                    .padding(10.dp)
                    .width(35.dp)
                    .fillMaxHeight()
                    .clickable(onClick = onClickHome)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.goldmine_2_3x_quadrat),
                    contentDescription = "GM_Logo"
                )
            }
            Box(
                Modifier
                    .requiredWidth(270.dp)
                    .padding(start = 10.dp)
                    .fillMaxHeight(), contentAlignment = Alignment.CenterStart
            ) {

                Text(text, fontSize = 24.sp) // "Goldmine" oder "Eigene Gegenstände"
            }

            Box( // Chat Overview Button
                Modifier
                    .size(39.dp)
                    //.width(40.dp)
                  //  .padding(2.dp)
                    .padding(top = 5.dp, end = 7.dp),
                    //.padding(10.dp)
                    //.fillMaxHeight()
                // ,contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.baseline_chat_bubble_outline_24),
                    contentDescription = "chat_bubble",
                    Modifier.clickable(onClick = onClickChat)
                )
            }
            Box( // Personal Page button
                Modifier
                    .width(50.dp)
                    .padding(end = 10.dp)
                    //.fillMaxHeight(),
                ,contentAlignment = Alignment.TopEnd
            ) {
                Image(
                    painter = painterResource(id = R.drawable.outline_account_circle_48_black),
                    contentDescription = "account_circle",
                    Modifier.clickable(onClick = onClickProfile)
                )
            }
        }
    }
}

@Composable
fun BottomBar(onClickAddEntry: () -> Unit) {
    Box(
        Modifier
            .fillMaxWidth()
            .background(OffBlack)
            .requiredHeight(140.dp), contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            Modifier
                .padding(10.dp)
                .fillMaxHeight()
                .fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) {
            AddEntryButton(onClickAddEntry)
        }
    }
}

// gehört zu bottom bar
@Composable
fun AddEntryButton(onClickAddEntry: () -> Unit) {

    Button(
        onClick = onClickAddEntry,
        modifier = Modifier
            .width(50.dp)
            .height(50.dp),
        colors = ButtonDefaults.buttonColors(MainBlue, OffWhite, SecondaryBlue, OffWhite),
        shape = CircleShape
    ) {
        Image(
            painter = painterResource(id = R.drawable.round_add_48),
            contentDescription = "add_entry_icon"
        )
    }
}