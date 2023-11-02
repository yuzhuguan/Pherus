package metospherus.app.overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.lazy.staggeredgrid.rememberLazyStaggeredGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.BookmarkAdd
import androidx.compose.material.icons.rounded.NearMe
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
fun ResearchContainer(
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
    accountType: String,
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
                            Text(text = "Search For Medical Studies...")
                        },
                        leadingIcon = {
                            Icon(Icons.Rounded.Search, contentDescription = null)
                        },
                        shape = RoundedCornerShape(30)
                    )
                }

                items(5) {
                    ElevatedCard(
                        modifier = Modifier
                            .padding(5.dp)
                            .fillMaxWidth(),
                        onClick = { /*TODO*/ }) {
                        Box(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(start = 5.dp, bottom = 5.dp, top = 5.dp, end = 110.dp)
                            ) {
                                Text(text = "Context creator the some text",
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 16.sp,
                                    lineHeight = 16.sp)

                                Text(text = "Please share some sample code and refer to the point where you wish for the button to be placed. Again, if you do not want it to be tied to a specific Composable, use the all-father of state management in declarative paradigms",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 11.sp,
                                    lineHeight = 11.sp)
                            }
                            ElevatedCard(
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterEnd),
                            ) {
                                Image(
                                    rememberAsyncImagePainter("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .height(80.dp)
                                        .width(100.dp),
                                    contentScale = ContentScale.Crop
                                )
                            }
                        }
                        Box {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                horizontalArrangement = Arrangement.Start,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(text = "1 day ago",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp)
                                Text(text = " . ")
                                Text(text = "7 min read",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp)
                                Spacer(modifier = Modifier.padding(20.dp))
                            }

                            Row(
                                modifier = Modifier.align(alignment = Alignment.CenterEnd),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Rounded.BookmarkAdd, contentDescription = null)
                                }
                                IconButton(onClick = { /*TODO*/ }) {
                                    Icon(Icons.Rounded.NearMe, contentDescription = null)
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}