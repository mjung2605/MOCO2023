package com.example.mocogmfinalui.composeComponents

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mocogmfinalui.R
import com.example.mocogmfinalui.ui.theme.*



@Composable // manche Sachen sind hier initialisiert, damit sie nicht als Parameter übergeben werden müssen, wenn wir sie nicht brauchen;
// z.B. wenn wir mit StartScreen einen TitleScreen machen wollen, brauchen wir ja nicht den Text oder die button variablen des login screens. Beim Aufrufen des Loginscreens müssen diese aber natürlich überschrieben werden.
fun StartScreen(isTitleScreen: Boolean, onClickStartTitle: () -> Unit = {}, isLogInScreen: Boolean, onClickAction: () -> Unit = {}, buttonText: String = "", buttonColor: Color = Color.Black, onClickGoToOther: () -> Unit = {}, textZeile1: String = "", textZeile2: String = "") {

    Box( // Hintergrund-Gradient
        Modifier
            .background(brush = Brush.verticalGradient(listOf(MainBlue, MainGreen)))
            .fillMaxSize())

    Box( // transparent box für content alignment
        Modifier
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {

        if(!isTitleScreen) LogInSignUpScreen(isLogInScreen, onClickAction, buttonText, buttonColor, onClickGoToOther, textZeile1, textZeile2)
        else TitleScreen(onClickStartTitle)
    }
}

// Start //
// TODO: nicht clickable, sondern Zeit zum Laden der Liste beim Starten der App
@Composable
fun TitleScreen(onClickStart: () -> Unit) {

    Box(Modifier.clickable(onClick = onClickStart)) {
        Image(
            painterResource(id = R.drawable.goldmine_2_3x), "gm_logo",
            Modifier
                .size(400.dp)
        )
    }
}

@Composable
fun LogInSignUpScreen(isLogInScreen: Boolean, onClickAction: () -> Unit = {}, buttonText: String, buttonColor: Color, onClickGoToOther: () -> Unit = {}, textZeile1: String, textZeile2: String) {

    Box( // Hintergrund
        Modifier
            .background(brush = Brush.verticalGradient(listOf(MainBlue, MainGreen)))
            .fillMaxSize(),
        contentAlignment = Alignment.Center) {

        Column(
            Modifier
                .width(230.dp),
            horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(10.dp)) {

            LogSignTextFields(if (isLogInScreen) "LOG IN" else "SIGN UP", isLogInScreen)
            LogSignButtonAction(textZeile1, textZeile2, onClickAction, buttonText, buttonColor, onClickGoToOther)
        }
    }
}

@Composable
fun LogSignTextFields(text: String, isLogInScreen: Boolean) {
    // LOG IN/SIGN UP Überschrift
    Text(text, Modifier.padding(5.dp),
        fontSize = 50.sp,
        color = OffBlack
    )
    if(isLogInScreen) LogInTextBoxes() else SignUpTextBoxes()
}

@Composable
fun LogInTextBoxes() { // onClickGoToOther navigiert entweder zu register/sign up oder zu log in

    // OutlinedTextField(value = , onValueChange = ) TODO: Bei Implementierung dies benutzen

    // Textbox für E-Mail
    LogSignSingleTextbox(placeholder = "E-Mail")

    // Textbox für Passwort
    LogSignSingleTextbox(placeholder = "Passwort")
}


@Composable
fun SignUpTextBoxes() {

    // Textbox für Nutzername
    // OutlinedTextField(value = , onValueChange = ) TODO: Bei Implementierung dies benutzen!
    LogSignSingleTextbox(placeholder = "Nutzername")

    // Textbox für E-Mail
    LogSignSingleTextbox(placeholder = "E-Mail")

    // Textbox für Passwort
    LogSignSingleTextbox(placeholder = "Passwort")

    // Textbox für Passwort wiederholen
    LogSignSingleTextbox(placeholder = "Passwort wiederholen")
}

@Composable
fun LogSignSingleTextbox(placeholder: String) { // TODO: Umsetzung mit OutlinedTextField
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
}

@Composable
fun LogSignButtonAction(textZeile1: String, textZeile2: String, onClickAction: () -> Unit, buttonText: String, buttonColor: Color, onClickGoToOther: () -> Unit) {
    Column(horizontalAlignment = Alignment.CenterHorizontally) {
        FinishLogSign(onClickAction, buttonText, buttonColor)
        LogSignMoveToOther(textZeile1, textZeile2, onClickGoToOther)
    }
}

@Composable
fun FinishLogSign(onClickAction: () -> Unit, text: String, color: Color) {
    Box(Modifier.padding(10.dp)) {
        Button(onClick = onClickAction,
            colors = ButtonDefaults.buttonColors(backgroundColor = color)
        ) {
            Text(text = text)
        }
    }
}

@Composable
fun LogSignMoveToOther(textZeile1: String, textZeile2: String, onClickGoToOther: () -> Unit) {
    Box(Modifier.padding(top = 20.dp), contentAlignment = Alignment.Center) {
        Column(verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally) {
            Text(textZeile1, color = OffBlack, textAlign = TextAlign.Center)
            Text(textZeile2, Modifier.clickable(onClick = onClickGoToOther),
                color = OffBlack, textDecoration = TextDecoration.Underline, textAlign = TextAlign.Center)
        }
    }
}