package metospherus.app.packages

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItem
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.SheetState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PherusBottomSheetModel(
    title: String,
    onDismissListener: () -> Unit,
    onBottomSheetState: SheetState,
) {
    ModalBottomSheet(
        modifier = Modifier.fillMaxWidth(),
        onDismissRequest = onDismissListener,
        sheetState = onBottomSheetState
    ) {
        LazyColumn {
            item {
                Text(text = title)
                if (title.contains("documents", ignoreCase = true)) {
                }
            }
        }
    }
}