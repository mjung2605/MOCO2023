package com.example.mocogm.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.interaction.collectIsPressedAsState
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mocogm.ui.theme.*
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import com.example.mocogm.models.AuthState
import com.example.mocogm.viewmodels.AuthViewModel
import androidx.lifecycle.*
import com.example.mocogm.*
import com.example.mocogm.R
import com.example.mocogm.gebuendelteDaten.LogSignType
import com.example.mocogm.gebuendelteDaten.StartLogIn
import com.example.mocogm.gebuendelteDaten.StartSignUp
import com.example.mocogm.gebuendelteDaten.StartTitle
import com.example.mocogm.models.UserRepository


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
        if (type is StartTitle) TitleScreen(type)
        else LogInSignUpScreen(type as LogSignType, viewModel)
    }
}


// Start //
// TODO: nicht clickable, sondern Zeit zum Laden der Liste beim Starten der App
@Composable
fun TitleScreen(type: StartTitle) {

    Box(Modifier.clickable(onClick = type.onClickStartTitle)) {
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
    var pwConfirmValue by rememberSaveable { mutableStateOf("") }

    val loginState by viewModel.authState.observeAsState(initial = AuthState.AuthIdle)

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
                pwConfirmValue,
                onPwConfirmChange = { newValue: String -> pwConfirmValue = newValue } )

            LogSignButtonAction(type, performLogSignIn =
            {
                if(type is StartLogIn) viewModel.logInWithEmail(emailValue, passwordValue)
                else viewModel.signUpWithEmail(emailValue, passwordValue, pwConfirmValue)
            }, type.onClickNavigate)
        }
    }


    /*
    when(loginState) {

        is AuthState.AuthError -> {
            Box { (loginState as AuthState.AuthError).errorMessage?.let { Text(it) } }
        }

        AuthState.AuthLoading -> {
            Box() {
                CircularProgressIndicator(color = SecondaryBlue)
            }
        }
        AuthState.AuthSuccess -> {
            (type).onClickNavigate // for now only navigates to blue main tabs
        }
        else -> { } // authidle -> do nothing
    }

     */

}

@Composable
fun LogSignTextFields(
    type: LogSignType,
    emailValue: String,
    onEmailChange: (String) -> Unit,
    passwordValue: String,
    onPasswordChange: (String) -> Unit,
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
        LogSignSingleTextbox(placeholder = "E-Mail", emailValue, onEmailChange)

        // Textbox für Passwort
        LogSignSingleTextbox(placeholder = "Passwort", passwordValue, onPasswordChange)

    } else { // type is StartSignUp

        // Textbox für E-Mail
        LogSignSingleTextbox(placeholder = "E-Mail", emailValue, onEmailChange)

        // Textbox für Passwort
        LogSignSingleTextbox(placeholder = "Passwort", passwordValue, onPasswordChange)

        // Textbox für Passwort wiederholen
        LogSignSingleTextbox(
            placeholder = "Passwort wiederholen",
            pwConfirmValue,
            onPwConfirmChange
        )
    }
}


@Composable
fun LogSignSingleTextbox(
    placeholder: String,
    textValue: String, onTextValueChange: (String) -> Unit
) {

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
        },
        visualTransformation = if(placeholder == "Passwort" || placeholder == "Passwort wiederholen") PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = if(placeholder == "Passwort" || placeholder == "Passwort wiederholen") KeyboardOptions(keyboardType = KeyboardType.Password) else KeyboardOptions(keyboardType = KeyboardType.Text)

    )

}

@Composable
fun LogSignButtonAction(
    type: LogSignType,
    // logInState: AuthState
    performLogSignIn: () -> Unit,
    onClickNav: () -> Unit
) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {

        // performs login/signup
        Box(Modifier.padding(10.dp)) {
            Button(
                onClick = {
                    performLogSignIn()
                    onClickNav()
                },
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
