package com.example.goldmine

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goldmine.ui.theme.GoldmineTheme
import com.example.goldmine.ui.theme.Purple80
import com.example.goldmine.ui.theme.PurpleGrey40
import androidx.compose.foundation.Image
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.TextUnit
import com.example.goldmine.ui.theme.MainBlue
import com.example.goldmine.ui.theme.MainGreen


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GoldmineTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background){


            }
        }
    }
}

}

@Composable
fun GesuchtGefundenTabs() {
    Column(Modifier.fillMaxWidth()) {

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(MainBlue)
                .padding(100.dp,100.dp)// zentriert text
        )
        {
            Column(modifier = Modifier
                .padding(0.dp,0.dp,0.dp,0.dp),)
            {
             Text("Gesucht", fontSize = 50.sp)
             Image(
                painter = painterResource(id = R.drawable.lupe1),
                contentDescription = "Lupe",
                modifier = Modifier
                    .size(180.dp)
                    .padding(45.dp,20.dp,10.dp,0.dp),
            )
         }
        }

        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(1f)
                .background(MainGreen)
                .padding(85.dp,100.dp),
        ) {
            Column(modifier = Modifier
                .padding(0.dp,0.dp,0.dp,0.dp),
            ) {
                Text("Gefunden", fontSize = 50.sp)
                Image(
                    painter = painterResource(id = R.drawable.box),
                    contentDescription = "Lupe",
                    modifier = Modifier
                        .size(200.dp)
                        .padding(40.dp,20.dp,10.dp,0.dp),
                )
            }
        }
    }

}


fun Text(text: String, modfier: Modifier, fontSize: TextUnit) {

}

@Preview(showBackground = true)
@Composable
fun GesuchtGefundenPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally){
      /*  CustomItem(weight = 1f, color = PurpleGrey40)
        CustomItem (weight = 1f, color= Purple80)*/
        GesuchtGefundenTabs()
    }
}
