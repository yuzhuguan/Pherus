package metospherus.app.overview

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import metospherus.app.database.FirebaseDatabaseLocal

@Composable
fun HospitalContainer(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
    accountType: String,
) {
    Box(modifier) {
        Text(text = "HospitalContainer")
    }
}