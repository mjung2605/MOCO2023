package com.example.myapplication

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.myapplication.models.FirebaseObject
import com.example.myapplication.ui.theme.MainBlue
import com.example.myapplication.ui.theme.MainGreen
import com.example.myapplication.viewModels.ListViewModel


@Composable
fun ListItemScreen(navController: NavHostController, viewModel: ListViewModel) {
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "logo",
                modifier = Modifier.size(40.dp)
            )
            Text(
                text = "Goldmine",
                modifier = Modifier.padding(start = 8.dp, top = 4.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            IconButton(
                onClick = { /* Aktion beim Klicken des Profil-Icons */ },
                modifier = Modifier
                    .padding(end = 16.dp)
                    .size(24.dp)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.benutzer),
                    contentDescription = "Profil"
                )
            }
        }
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(50.dp)
        ) {
            Box(
                modifier = Modifier
                    .fillMaxWidth(0.5f)
                    .background(MainGreen)
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Gefunden")
            }
            Box(
                modifier = Modifier
                    .fillMaxWidth(1f)
                    .background(MainBlue)
                    .height(50.dp),
                contentAlignment = Alignment.Center
            ) {
                Text(text = "Gesucht")
            }
        }

        // Liste der Firebase-Objekte
        /*LazyColumn {
            items(viewModel.firebaseObjects.value) { firebaseObject ->
                FirebaseObjectItem(firebaseObject)
            }
        }*/

        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            contentAlignment = Alignment.BottomCenter
        ) {
            IconButton(
                onClick = { navController.navigate("searchFound") },
                modifier = Modifier
                    .clip(CircleShape)
                    .background(MainBlue)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.plus),
                    contentDescription = null
                )
            }
        }
    }
}


@Composable
fun FirebaseObjectItem(firebaseObject: FirebaseObject) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
    ) {
        Image(
            painter = painterResource(id = R.drawable.lupe),
            contentDescription = null,
            modifier = Modifier
                .size(80.dp)
                .clip(CircleShape)
                .background(MaterialTheme.colorScheme.secondary)
        )
        Column(
            modifier = Modifier
                .padding(start = 16.dp)
                .fillMaxWidth()
        ) {
            Text(text = firebaseObject.title)
            Text(text = firebaseObject.description)
        }
    }
}