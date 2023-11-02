package metospherus.app.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun DocumentsComponent(
    baseLocal: FirebaseDatabaseLocal,
    navController: NavHostController,
    accountType: String,
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = {
                    Text(text = stringResource(id = R.string.app_name))
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(accountType)
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
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .size(50.dp),
                        value = "", onValueChange = {},
                        placeholder = {
                            Text(text = "Search Documents")
                        },
                        leadingIcon = {
                            Icon(Icons.Rounded.Search, contentDescription = null)
                        },
                        shape = RoundedCornerShape(30)
                    )
                }

                items(3) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(bottom = 5.dp)
                    ) {
                        Text(text = "Feb 2023",
                            fontWeight = FontWeight.Bold,
                            fontSize = 11.sp,
                            modifier = Modifier.padding(8.dp))
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(3),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 5.dp, end = 5.dp)
                                .heightIn(max = parentHeight),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(5) {
                                Card(
                                    onClick = {},
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(2.dp)
                                ) {
                                    Column(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(0.dp)
                                    ) {
                                        Text(text = "Document Title",
                                            fontWeight = FontWeight.Bold,
                                            fontSize = 12.sp,
                                            lineHeight = 12.sp,
                                            modifier = Modifier.padding(top = 5.dp, start= 5.dp))
                                        Text(text = "12th 11:40 PM",
                                            fontWeight = FontWeight.Light,
                                            fontSize = 8.sp,
                                            lineHeight = 8.sp,
                                            maxLines = 1,
                                            modifier = Modifier.padding(top = 0.dp, start= 5.dp))
                                        Text(
                                            text = "Home Screen the application translations to the default translation",
                                            fontWeight = FontWeight.Light,
                                            fontSize = 11.sp,
                                            lineHeight = 11.sp,
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .blur(
                                                    1.dp,
                                                    edgeTreatment = BlurredEdgeTreatment.Rectangle
                                                )
                                        )
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}