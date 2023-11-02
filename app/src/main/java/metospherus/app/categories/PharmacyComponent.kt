package metospherus.app.categories

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
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
fun PharmacyComponent(
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
                            Text(text = "Search by name")
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

                item {
                    LazyVerticalStaggeredGrid(
                        columns = StaggeredGridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                            .heightIn(max = parentHeight),
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        items(5) {
                            ElevatedCard(
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
                                    Image(
                                        rememberAsyncImagePainter("https://firebasestorage.googleapis.com/v0/b/metospherus-kit.appspot.com/o/participants%2Fgeneralprofiles%2FgeneralProfiles.png?alt=media&token=c101c2cd-2656-42b9-a69a-06d3522d9389"),
                                        contentDescription = null,
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(0.dp)
                                            .height(65.dp),
                                        contentScale = ContentScale.Crop
                                    )
                                    Text(
                                        text = "Home Screen",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 12.sp,
                                        modifier = Modifier.padding(start = 5.dp , bottom = 1.dp, end = 5.dp)
                                    )
                                    Text(
                                        text = "Medicine description and usage basic information and basic description",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 8.sp,
                                        lineHeight = 8.sp,
                                        modifier = Modifier.padding(start = 5.dp , bottom = 5.dp, end = 5.dp)
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