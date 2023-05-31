package com.example.mocogm.composeComponents

import android.content.ContentValues.TAG
import android.nfc.Tag
import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mocogm.ui.theme.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.tooling.preview.Preview
import com.example.mocogm.viewmodels.AuthState
import com.example.mocogm.viewmodels.AuthViewModel
import androidx.lifecycle.*
import com.example.mocogm.*
import com.example.mocogm.R


// einfach composable type übergeben
@Composable
fun CompleteLayoutStart(viewModel: AuthViewModel, type: ComposableType) {

    StartScreen(type, viewModel)

}

@Composable
fun StartScreen(
    type: ComposableType,
    viewModel: AuthViewModel
) {

    // viewModel wird hier observed
    val loginState by viewModel.authState.observeAsState(initial = AuthState.AuthIdle)

    Box( // Hintergrund-Gradient
        Modifier
            .background(brush = Brush.verticalGradient(listOf(MainBlue, MainGreen)))
            .fillMaxSize()
    )

    Box( // transparent box für content alignment
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        // geht entweder zu titleScreen, einem Login/signUp Screen, oder displayed loading

        when(loginState) {
            is AuthState.AuthError -> {
                Box { (loginState as AuthState.AuthError).errorMessage?.let { Text(it) } }
            }
            AuthState.AuthIdle -> {
                if (type is StartTitle) TitleScreen(type)
                else if (loginState == AuthState.AuthIdle) LogInSignUpScreen(type as LogSignType, viewModel)
            }
            AuthState.AuthLoading -> {
                Box() {
                    CircularProgressIndicator(color = SecondaryBlue)
                }
            }
            AuthState.AuthSuccess -> {
                // TODO: get logged in user?
                (type as LogSignType).onClickNavigate // for now only navigates to blue main tabs
            }
        }
    }
}


// Start //
// TODO: nicht clickable, sondern Zeit zum Laden der Liste beim Starten der App
@Composable
fun TitleScreen(type: StartTitle) {

    Box(Modifier.clickable(onClick = (type).onClickStartTitle)) {
        Image(
            painterResource(id = R.drawable.goldmine_2_3x), "gm_logo",
            Modifier
                .size(400.dp)
        )
    }
}

@Composable
fun LogInSignUpScreen(type: LogSignType, viewModel: AuthViewModel) {

    var emailValue by rememberSaveable { mutableStateOf("") }
    var passwordValue by rememberSaveable { mutableStateOf("") }
    var nutzerValue by rememberSaveable { mutableStateOf("") }
    var pwConfirmValue by rememberSaveable { mutableStateOf("") }

    Box( // Hintergrund
        Modifier
            .background(brush = Brush.verticalGradient(listOf(MainBlue, MainGreen)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        Column(
            Modifier
                .width(230.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(10.dp)
        ) {
            LogSignTextFields(
                type,
                emailValue,
                onEmailChange = { newValue: String -> emailValue = newValue },
                passwordValue,
                onPasswordChange = { newValue: String -> passwordValue = newValue },
                nutzerValue,
                onNutzerChange = { newValue: String -> nutzerValue = newValue },
                pwConfirmValue,
                onPwConfirmChange = { newValue: String -> pwConfirmValue = newValue } )

            LogSignButtonAction(type, performLogSignIn = {
                if(type is StartLogIn) viewModel.onLogIn(emailValue, passwordValue)
                else if(type is StartSignUp) viewModel.onSignIn(emailValue, passwordValue, pwConfirmValue)
            })
        }
    }
}

@Composable
fun LogSignTextFields(
    type: LogSignType,
    emailValue: String,
    onEmailChange: (String) -> Unit,
    passwordValue: String,
    onPasswordChange: (String) -> Unit,
    nutzerValue: String,
    onNutzerChange: (String) -> Unit,
    pwConfirmValue: String,
    onPwConfirmChange: (String) -> Unit
) {


    // LOG IN/SIGN UP Überschrift
    Text(
        type.heading, Modifier.padding(5.dp),
        fontSize = 50.sp,
        color = OffBlack
    )
    if (type is StartLogIn) {
        // Textbox für E-Mail
        LogSignSingleTextbox(placeholder = "E-Mail", type, emailValue, onEmailChange)

        // Textbox für Passwort
        LogSignSingleTextbox(placeholder = "Passwort", type, passwordValue, onPasswordChange)

    } else { // type is StartSignUp

        // Textbox für Nutzername
        // OutlinedTextField(value = , onValueChange = ) TODO: Bei Implementierung dies benutzen!
        LogSignSingleTextbox(placeholder = "Nutzername", type, nutzerValue, onNutzerChange)

        // Textbox für E-Mail
        LogSignSingleTextbox(placeholder = "E-Mail", type, emailValue, onEmailChange)

        // Textbox für Passwort
        LogSignSingleTextbox(placeholder = "Passwort", type, passwordValue, onPasswordChange)

        // Textbox für Passwort wiederholen
        LogSignSingleTextbox(
            placeholder = "Passwort wiederholen",
            type,
            pwConfirmValue,
            onPwConfirmChange
        )
    }
}


@Composable
fun LogSignSingleTextbox(
    placeholder: String,
    type: LogSignType, textValue: String, onTextValueChange: (String) -> Unit
) { // TODO: Umsetzung mit OutlinedTextField

    OutlinedTextField(
        value = textValue,
        onValueChange = onTextValueChange,
        modifier = Modifier
            .fillMaxWidth()
            .background(Brush.horizontalGradient(listOf(OffWhite, OffWhite)), alpha = 0.3f),
        placeholder = {
            Text(
                text = placeholder
            )
        })

    /*
    Diese Box diente als UI-Platzhalter, bevor die Textfelder mit OutlinedTextField implementiert wurden.
    Box(
        Modifier
            .fillMaxWidth()
            .height(30.dp)
            .background(
                Brush.horizontalGradient(listOf(OffWhite, OffWhite)),
                alpha = 0.3f // Alpha: Deckkraft
            )
    ) {
        Text(placeholder, Modifier.padding(start = 7.dp, top = 5.dp), color = DarkGray)
    }

     */
}

@Composable
fun LogSignButtonAction(
    type: LogSignType,
    // logInState: AuthState
    performLogSignIn: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        // performs login/signup
        Box(Modifier.padding(10.dp)) {
            Button(
                onClick = type.onClickNavigate,
                colors = ButtonDefaults.buttonColors(backgroundColor = type.buttonColor)
            ) {
                Text(text = type.buttonText)
            }
        }

        // goes to other: signup/login screen respectively
        Box(Modifier.padding(top = 20.dp), contentAlignment = Alignment.Center) {
            Column(
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(type.textZeile1, color = OffBlack, textAlign = TextAlign.Center)
                Text(
                    type.textZeile2,
                    Modifier.clickable(onClick = type.onClickGoToOther),
                    color = OffBlack,
                    textDecoration = TextDecoration.Underline,
                    textAlign = TextAlign.Center
                )
            }
        }
    }
}