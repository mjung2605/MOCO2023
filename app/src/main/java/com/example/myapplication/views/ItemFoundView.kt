// HomeScreen.kt
package com.example.myapplication

import android.annotation.SuppressLint
import android.graphics.Color
import android.service.autofill.FieldClassification.Match
import android.widget.LinearLayout
import androidx.camera.core.Preview
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
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
import androidx.compose.material.icons.Icons
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment

import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.myapplication.ui.theme.MainGreen
import com.example.myapplication.ui.theme.OffBlack


import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.viewinterop.AndroidView
import com.example.myapplication.ui.theme.LightGray
import com.example.myapplication.ui.theme.OffWhite
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.PermissionState
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState

@OptIn(ExperimentalMaterial3Api::class, ExperimentalPermissionsApi::class)

@Composable
fun NewItemScreen(viewModel: HomeViewModel){
    val cameraPermissionState: PermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)

    MainContent(
        hasPermission = cameraPermissionState.status.isGranted,
        onRequestPermission = cameraPermissionState::launchPermissionRequest
    )
}


@Composable
private fun MainContent(
    hasPermission: Boolean,
    onRequestPermission: () -> Unit
){
    if (hasPermission){
        NewItemGefundenScreen(viewModel = HomeViewModel())
    }else{
        NoPermissionScreen(onRequestPermission)
    }
}

@Composable
fun NoPermissionScreen(
    onRequestPermission: () -> Unit
) {
    NoPermissionScreen(
        onRequestPermission = onRequestPermission
    )
}

@Composable
fun NoPermissionContent(
    onRequestPermission: () -> Unit
){
    Column (
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text= "Please grant permission to use the funcionality")
        Button(onClick= onRequestPermission){
            Text(text="grant permission")
        }
    }
}


@Composable
fun CameraScreen(
){
    CameraContent()
}


@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
private fun CameraContent(){

    val context = LocalContext.current
    val lifecycleOwner = LocalLifecycleOwner.current
    val cameraController = remember{ LifecycleCameraController(context)}

    Scaffold(modifier = Modifier.fillMaxSize()) {paddingValues: PaddingValues ->
        AndroidView(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues),
            factory = {context ->
                PreviewView(context).apply{
                    setBackgroundColor(Color.BLACK)
                    scaleType = PreviewView.ScaleType.FILL_START
                }.also {previewView ->
                    previewView.controller = cameraController
                    cameraController.bindToLifecycle(lifecycleOwner)

                }

            })

    }
}



@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun NewItemGefundenScreen(viewModel: HomeViewModel) {

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
                .background(MainGreen),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = Modifier
                    .padding(30.dp),
                horizontalArrangement = Arrangement.Center,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Image(
                    painter = painterResource(id = R.drawable.box),
                    contentDescription = "Box Image",
                    modifier = Modifier
                        .size(size = 63.dp),
                    contentScale = ContentScale.Fit
                )
                Text(
                    text = "Gefunden",
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
                text = "Kamera gefunden",
                color = OffBlack,
                style = TextStyle(fontSize = 45.sp),
            )
            CameraScreen()
            //--------------------


        }

        OutlinedTextField(
            value = viewModel.gefundenTitleEingabe.value,
            onValueChange = { newTitle ->
                viewModel.gefundenTitel(newTitle)
            },
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            label = { Text(text = "Ich habe gefunden...") }
        )

        Spacer(modifier = Modifier.height(16.dp))

        OutlinedTextField(
            value = viewModel.gefundenBeschreibungEingabe.value,
            onValueChange = { newDescription ->
                viewModel.gefundenBeschreibung(newDescription)
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
                        .background(color = MainGreen)
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
                    gefundenText = viewModel.gefundenTitleEingabe.value,
                    beschreibungText = viewModel.gefundenBeschreibungEingabe.value,
                )

                viewModel.saveData(newItem)
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(74.dp)
        ) {
            Text(text = "Fundstück melden")
        }


    }
}

