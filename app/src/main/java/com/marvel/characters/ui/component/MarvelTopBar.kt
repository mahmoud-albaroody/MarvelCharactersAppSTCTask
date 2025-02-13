import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.marvel.characters.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MarvelTopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Black),
        title = {
            Box(
                modifier = Modifier.fillMaxWidth(),
                contentAlignment = Alignment.Center
            ) {
                Image(
                    painter = painterResource(id = R.drawable.marvel_logo), // Ensure this resource exists
                    contentDescription = "Marvel Logo",
                    modifier = Modifier.height(50.dp).width(100.dp)
                )
            }
        },
        actions = {
            IconButton(onClick = {

            }) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Search",
                    tint = Color.Red
                )
            }
        }
    )
}


