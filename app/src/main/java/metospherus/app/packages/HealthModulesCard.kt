package metospherus.app.packages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import metospherus.app.database.LocalDatabase.AccountPatientModules

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HealthModulesCard(
    items: AccountPatientModules,
    onClickModuleListener: () -> Unit
) {
    ElevatedCard(
        onClick = onClickModuleListener,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center
        ) {
            Icon(
                rememberAsyncImagePainter(items.icon),
                contentDescription = items.description,
                modifier = Modifier
                    .fillMaxWidth()
                    .size(45.dp)
                    .padding(5.dp),
                tint = Color.Unspecified
            )
            Text(
                text = items.name.toString(),
                fontWeight = FontWeight.Bold,
                fontSize = 8.sp,
                lineHeight = 8.sp,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .padding(5.dp)
                    .fillMaxWidth()
            )
        }
    }
}