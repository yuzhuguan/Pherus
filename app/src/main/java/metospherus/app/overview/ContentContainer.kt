package metospherus.app.overview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.HeartBroken
import androidx.compose.material.icons.rounded.PlayArrow
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentContainer(
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
    accountType: String
) {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            }, navigationIcon = {
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
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .size(50.dp),
                        value = "", onValueChange = {},
                        placeholder = {
                            Text(text = "Search...")
                        },
                        leadingIcon = {
                            Icon(Icons.Rounded.Search, contentDescription = null)
                        },
                        shape = RoundedCornerShape(30)
                    )
                }

                items(6) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxSize()
                            .wrapContentHeight()
                            .padding(10.dp),
                        onClick = { /*TODO*/ }) {
                            Column {
                                Box {
                                    Image(
                                        rememberAsyncImagePainter("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .height(200.dp)
                                            .fillMaxHeight(),
                                        contentScale = ContentScale.Crop
                                    )
                                    OutlinedIconButton(
                                        modifier = Modifier
                                            .size(60.dp)
                                            .align(alignment = Alignment.Center),
                                        onClick = { /*TODO*/ },
                                        border = BorderStroke(width = 5.dp,
                                            color = MaterialTheme.colorScheme.onSurfaceVariant)
                                    ) {
                                        Icon(
                                            Icons.Rounded.PlayArrow,
                                            contentDescription = null,
                                            modifier = Modifier.size(45.dp),
                                            tint = MaterialTheme.colorScheme.onSurfaceVariant
                                        )
                                    }
                                }
                                Row(
                                    modifier = Modifier.padding(5.dp)
                                ) {
                                    Card(
                                        modifier = Modifier.size(45.dp),
                                        shape = RoundedCornerShape(35)
                                    ) {
                                            Image(
                                                rememberAsyncImagePainter("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
                                                contentDescription = null,
                                                contentScale = ContentScale.Crop
                                            )
                                    }
                                    Column(
                                        modifier = Modifier.padding(start = 5.dp, end = 5.dp, bottom = 5.dp)
                                    ) {
                                        Text(text = "Content Title Creator Name",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 16.sp,
                                            lineHeight = 16.sp)
                                        Row(
                                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Text(text = "Creators Name",
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 12.sp,
                                                lineHeight = 12.sp)
                                            Text(text = ".")
                                            Text(text = "657k Views",
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 12.sp,
                                                lineHeight = 12.sp
                                            )
                                            Text(text = ".")
                                            Text(text = "19 hours ago",
                                                fontWeight = FontWeight.Normal,
                                                fontSize = 12.sp,
                                                lineHeight = 12.sp)
                                        }

                                        Row(
                                            modifier = Modifier.padding(top = 5.dp, end = 5.dp, bottom = 5.dp),
                                            horizontalArrangement = Arrangement.Start,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            IconButton(onClick = { /*TODO*/ }) {
                                                Icon(imageVector = ImageVector.vectorResource(id = R.drawable.rounded_messages), contentDescription = null)
                                            }
                                            ElevatedCard(
                                                modifier = Modifier.padding(start = 2.dp),
                                            ) {
                                                Text(text = "Latest Comments by username so and so ",
                                                    modifier = Modifier.padding(5.dp))
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
}