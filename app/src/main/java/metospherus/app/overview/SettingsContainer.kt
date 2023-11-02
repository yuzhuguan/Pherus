package metospherus.app.overview

import android.content.res.Configuration
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import metospherus.app.R
import metospherus.app.database.LocalDatabase
import metospherus.app.database.LocalDatabase.SystemSettings
import metospherus.app.packages.MaterialCheckBox
import metospherus.app.ui.theme.PherusTheme

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun SettingsContainer() {

    val settingsList = mutableListOf(
        SystemSettings(
            title = "Experimental Settings",
            description = "By enabling experimental settings this will By enabling experimental settings this will By enabling experimental settings this will",
            enabled = false
        ),
        SystemSettings(
            title = "Experimental Settings",
            description = "By enabling experimental settings this will By enabling experimental settings this will",
            enabled = false
        ),
        SystemSettings(
            title = "Experimental Settings",
            description = "By enabling experimental settings this will By enabling experimental settings this will By enabling experimental settings this will By enabling experimental settings this will",
            enabled = false
        ),
        SystemSettings(
            title = "Experimental Settings",
            description = "By enabling experimental settings this will By enabling experimental settings this will By enabling experimental settings this will",
            enabled = false
        ),
    )
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
            val parentHeight = maxHeight
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    Text(text = "Settings screen")
                }

                items(settingsList.size) { index ->
                    val items = settingsList[index]

                    MaterialCheckBox(
                        title = items.title.toString(),
                        description = items.description.toString(),
                        checked = items.enabled,
                        onCheckedChange = {
                            // Check
                        }
                    )
                }
            }
        }
    }
}

@Preview(
    name = "SettingsContainer", showSystemUi = true, showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewSettingsContainer() {
    PherusTheme {
        SettingsContainer()
    }
}