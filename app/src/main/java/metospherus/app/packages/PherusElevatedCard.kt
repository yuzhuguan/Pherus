package metospherus.app.packages

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import metospherus.app.database.LocalDatabase

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun PherusElevatedCard(
    item: LocalDatabase.CategoryService,
    onClickListener: () -> Unit,
) {
    ElevatedCard(
        onClick = onClickListener,
        modifier = Modifier
            .fillMaxWidth()
            .padding(2.dp)
            .wrapContentHeight(),
        shape = RoundedCornerShape(35)
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(2.dp)
        ) {
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    rememberAsyncImagePainter(item.img),
                    contentDescription = null,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp)
                        .height(35.dp),
                    contentScale = ContentScale.Inside
                )
                Text(
                    modifier = Modifier.fillMaxWidth().padding(5.dp),
                    text = item.name,
                    fontWeight = FontWeight.ExtraBold,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Center,
                    lineHeight = 8.sp
                )
            }
        }
    }
}