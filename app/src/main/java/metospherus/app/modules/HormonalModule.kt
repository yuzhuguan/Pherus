package metospherus.app.modules

import android.content.res.Configuration
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import metospherus.app.R
import metospherus.app.ui.theme.PherusTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HormonalModule() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            }, navigationIcon = {
                IconButton(
                    onClick = {
                        // navController.navigate(accountType)
                    },
                    modifier = Modifier.size(48.dp),
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.rouded_arrow),
                        contentDescription = "Navigate back to Home"
                    )
                }
            })
        }
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .padding(paddingValues = it)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Text(text = "Settings screen")
                }
            }
        }
    }
}

@Preview(
    name = "HormonalModule", showSystemUi = true, showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewHormonalModule() {
    PherusTheme {
        HormonalModule()
    }
}