package com.example.goldmine

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedSuggestionChip
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.goldmine.ui.theme.MainBlue
import com.example.goldmine.ui.theme.OffBlack
import com.example.goldmine.ui.theme.Purple40
import com.example.goldmine.ui.theme.Purple80
import com.example.goldmine.ui.theme.PurpleGrey40

class GesuchtHinweisActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gesucht_hinweis2)
    }
}




@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HinweisGesucht() {
    Column  (modifier= Modifier
        .fillMaxSize(1f)
        .padding(30.dp, 10.dp)
            )
    {
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .background(MainBlue)
                .padding(70.dp, 100.dp)// zentriert text
        )
        {
            androidx.compose.material3.Text("Gesucht", fontSize = 50.sp)
            Image(
                painter = painterResource(id = R.drawable.lupe1),
                contentDescription = "Lupe",
                modifier = Modifier
                    .size(180.dp)
                    .padding(40.dp, 70.dp, 10.dp, 0.dp), //zentriert Bild
            )
        }
//Hinweis
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.25f)
               // .padding(30.dp, 30.dp, 30.dp, 0.dp),
        ) {
            androidx.compose.material3.Text("Hinweis", fontSize = 50.sp)
            //androidx.compose.material3.Text("Bitte stelle sicher, dass dein gesuchter Gegenstand nicht bereits unter “Gesucht” eingestellt wurde.", fontSize = 20.sp)
        }
//Hinweis Text
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.3f)
              //  .padding(30.dp, 0.dp),
        ) {
            androidx.compose.material3.Text(
                "Bitte stelle sicher, dass dein gesuchter Gegenstand nicht bereits unter “Gefunden” eingestellt wurde.",
                fontSize = 20.sp
            )
        }



//Bestaetigung
    Row { val checkedStateJava = remember { mutableStateOf(false) }

        Checkbox(
            checked = checkedStateJava.value,
            onCheckedChange = { checkedStateJava.value = it },
  //          modifier = Modifier.padding(20.dp,0.dp,20.dp,0.dp)
        )
        Box(
            Modifier
                .fillMaxWidth()
                .fillMaxHeight(0.5f)
                .padding(10.dp, 0.dp,30.dp,0.dp),
        ) {
            androidx.compose.material3.Text("Ich habe bereits unter “Gefunden” nach meinem Gegenstand gesucht, habe ihn dort aber leider nicht gefunden.", fontSize = 15.sp)
        }
      }

/*        Button(
            colors = ButtonDefaults.buttonColors(MainBlue),
            onClick = { *//* ... *//* },
            // Uses ButtonDefaults.ContentPadding by default
            contentPadding = PaddingValues(
                start = 150.dp,
                top = 12.dp,
                end = 150.dp,
                bottom = 12.dp

            )
        ) {
            // Inner content including an icon and a text label
            Icon(
                Icons.Filled.Search,
                contentDescription = "Suche Starten",
                modifier = Modifier.size(60.dp)
            )
            Spacer(Modifier.size(ButtonDefaults.IconSpacing))
            Text("Gegenstand suchen")
        }*/

        Button(onClick = { /* Do something! */ }, colors = ButtonDefaults.textButtonColors(MainBlue)
        ) {
            Text("Suche starten", fontSize = 20.sp, color = OffBlack)
        }





    }//Column
}//fun

fun Text(s: String, fontSize: TextUnit, color: Color) {


}

fun Text(text: String) {


}

fun Text(text: String, fontSize: TextUnit) {

}



@Preview(showBackground = true)
@Composable
fun HinweisGesuchtPreview() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally){
        /*  CustomItem(weight = 1f, color = PurpleGrey40)
          CustomItem (weight = 1f, color= Purple80)*/

        HinweisGesucht()


    }
}
