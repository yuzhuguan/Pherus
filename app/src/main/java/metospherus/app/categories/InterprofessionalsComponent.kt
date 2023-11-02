package metospherus.app.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun InterprofessionalsComponent(
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
    accountType: String
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
            val parentWeight = maxWidth
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    OutlinedTextField(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .size(50.dp),
                        value = "", onValueChange = {},
                        placeholder = {
                            Text(text = "Search For Medical Professional")
                        },
                        leadingIcon = {
                            Icon(Icons.Rounded.Search, contentDescription = null)
                        },
                        shape = RoundedCornerShape(30)
                    )
                }

                item {
                    LazyHorizontalGrid(
                        rows = GridCells.Fixed(1),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, bottom = 10.dp)
                            .wrapContentHeight()
                            .height(30.dp)
                            .widthIn(max = parentWeight)
                            .heightIn(max = parentHeight),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        items(6) {
                            ElevatedAssistChip(
                                modifier = Modifier,
                                onClick = { /*TODO*/ },
                                label = {
                                    Text(text = "General Doctor")
                                },
                                shape = RoundedCornerShape(100)
                            )
                        }
                    }
                }

                items(count = 3) {
                    ElevatedCard(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(20),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)) {
                        Row {
                            Card(
                                modifier = Modifier.padding(5.dp),
                                shape = RoundedCornerShape(30),
                                border = BorderStroke(
                                    2.dp, MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Image(
                                    rememberAsyncImagePainter("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .height(50.dp)
                                        .width(50.dp)
                                )
                            }

                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp, end = 5.dp, top = 5.dp, bottom = 5.dp),
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Row {
                                        Text(text = "Dr. Akari Mizunashi ",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 16.sp,
                                            lineHeight = 16.sp)
                                    }
                                    Text(text = "verified",
                                        fontWeight = FontWeight.Light,
                                        fontSize = 8.sp,
                                        lineHeight = 8.sp,
                                        modifier = Modifier
                                            .align(Alignment.TopEnd)
                                            .padding(end = 0.dp))
                                }
                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Text(text = "General Doctor",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp,
                                        lineHeight = 12.sp)
                                    Spacer(modifier = Modifier.padding(10.dp))
                                    Text(text = "513m",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp,
                                        lineHeight = 12.sp)
                                }
                                Text(text = "Message reference subject notes and descriptive display of part of it",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp)
                            }
                        }
                    }
                }
            }
        }
    }
}