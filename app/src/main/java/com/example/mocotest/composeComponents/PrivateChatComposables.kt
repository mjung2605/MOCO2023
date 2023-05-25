package com.example.mocogmfinalui.composeComponents

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mocogmfinalui.ui.theme.MainBlue
import com.example.mocogmfinalui.ui.theme.MainGreen
import com.example.mocogmfinalui.ui.theme.OffBlack

@Composable
fun PrivateChat(onClickHome: () -> Unit, onClickProfile: () -> Unit, onClickAddEntry: () -> Unit) {
    Column() {
        TopBar(text = "[username]", onClickHome, onClickProfile)
        PrivateChatLayout(true, "hi")
        BottomBar(onClickAddEntry)
    }
}

// TODO: alignment der Nachrichten fixen (bei Implementierung des Chats) (--> je nach message rechts oder links)
@Composable
fun PrivateChatLayout(isByOtherUser: Boolean, messageContent: String) {

    Column(modifier = Modifier
        .fillMaxWidth()
        .fillMaxHeight()
        .padding(20.dp),
        horizontalAlignment = if(isByOtherUser) Alignment.Start else Alignment.End
        ) {
        TextBubble(isByOtherUser, messageContent)
    }
}

@Composable
fun TextBubble(isByOtherUser: Boolean, messageContent: String) { // isByOtherUser has effect on which side the corner (text bubble "") isn't rounded on & if bubble is leftAligned or right aligned

    Box(modifier = Modifier.fillMaxWidth()) {
        Card(modifier = Modifier
            .width(250.dp)
            .height(70.dp),
            backgroundColor = if(isByOtherUser) MainGreen else MainBlue,
            shape = if(isByOtherUser) RoundedCornerShape(10.dp, 10.dp, 10.dp, 0.dp) else RoundedCornerShape(10.dp, 10.dp, 0.dp, 10.dp)
        ) {
            Text(messageContent, Modifier.padding(15.dp), color = OffBlack, fontSize = 16.sp)
        }
    }
}