package metospherus.app.packages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun PreferenceBoolen() {
    Box(Modifier.fillMaxWidth()) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1.0f),
                    verticalArrangement = Arrangement.spacedBy(8.dp)
                ){
                    Text(
                        text = "Setting Turn on Feature",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        softWrap = true
                    )
                    Text(
                        modifier = Modifier.width(300.dp),
                        text = "Description of feature being turned onDescription of feature being turned onDescription of feature being turned onDescription of feature being turned onDescription of feature being turned on",
                        fontWeight = FontWeight.Light,
                        fontSize = 11.sp,
                        maxLines = 1,
                        softWrap = true
                    )
                }
                Switch(checked = false, onCheckedChange = {

                })
            }
        }
    }
}

@Composable
fun MaterialCheckBox(
    title: String,
    description: String,
    checked: Boolean = false,
    onCheckedChange: (Boolean) -> Unit
) {
    Box(Modifier.fillMaxWidth()) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 15.dp, vertical = 8.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column(
                    modifier = Modifier.weight(1.0f),
                    verticalArrangement = Arrangement.spacedBy(5.dp)
                ){
                    Text(
                        text = title,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        fontFamily = FontFamily.Monospace,
                        maxLines = 1,
                        softWrap = true
                    )
                    Text(
                        modifier = Modifier.width(300.dp),
                        text = description,
                        fontWeight = FontWeight.Light,
                        fontSize = 11.sp,
                        lineHeight = 11.sp,
                        maxLines = 3,
                        softWrap = true
                    )
                }
                Switch(checked = checked, onCheckedChange = onCheckedChange)
            }
        }
    }
}

@Preview(name = "PreferenceBoolen")
@Composable
private fun PreviewPreferenceBoolen() {
    PreferenceBoolen()
}