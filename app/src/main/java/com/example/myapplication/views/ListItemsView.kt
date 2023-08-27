import android.content.ClipData.Item
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp

import com.example.myapplication.R
import com.example.myapplication.models.FirebaseObject
import com.example.myapplication.ui.theme.MainBlue
import com.example.myapplication.viewModels.ListViewModel



@Composable
fun ListItemScreen(viewModel: ListViewModel) {
    Column(modifier = Modifier.fillMaxSize()) {
        // Header mit Logo
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .background(color = MainBlue)
        ) {
            Image(
                painter = painterResource(id = R.drawable.logo), // Logo-Bild
                contentDescription = "logo",
                modifier = Modifier.fillMaxSize(),
            )
        }

       /* // Liste der Firebase-Objekte
        LazyColumn {
            items(viewModel.firebaseObjects.value) { firebaseObject ->
                FirebaseObjectItem(firebaseObject)
            }
        }*/


        // Bottom Bar mit rundem Button
        BottomAppBar(
            modifier = Modifier.fillMaxWidth(),
        ) {
            IconButton(
                onClick = { /* Aktion ausführen, wenn der Button gedrückt wird */ },
                modifier = Modifier
                    .padding(16.dp)
                    .clip(CircleShape)
                    .background(MaterialTheme.colorScheme.secondary)
            ) {
                Icon(
                    painter = painterResource(id = R.drawable.lupe),
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
        // Foto des Firebase-Objekts
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
