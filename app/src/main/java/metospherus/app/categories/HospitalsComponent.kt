package metospherus.app.categories

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal
import metospherus.app.packages.AutoSlidingCarousel

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun HospitalsComponent(
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
    accountType: String,
) {

    val images = listOf(
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
    )

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
                }

                items(5) {
                    ElevatedCard(
                        onClick = { /*TODO*/ }, modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                        ) {
                            Card(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                AutoSlidingCarousel(
                                    itemsCount = images.size,
                                    itemContent = { index ->
                                        AsyncImage(
                                            model = ImageRequest.Builder(LocalContext.current)
                                                .data(images[index])
                                                .build(),
                                            contentDescription = null,
                                            contentScale = ContentScale.Crop,
                                            modifier = Modifier.height(150.dp)
                                        )
                                    },
                                )
                            }
                            Column(
                                modifier = Modifier.padding(5.dp),
                                verticalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                Text(text = "Uro Care Hospital",
                                    fontWeight = FontWeight.Bold,
                                    fontSize = 20.sp)
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    ElevatedAssistChip(
                                        shape = RoundedCornerShape(100),
                                        onClick = { /*TODO*/ }, label = {
                                            Text(text = "Website")
                                        })
                                    ElevatedAssistChip(
                                        shape = RoundedCornerShape(100),
                                        onClick = { /*TODO*/ }, label = {
                                            Text(text = "Contact")
                                        })
                                    ElevatedAssistChip(
                                        shape = RoundedCornerShape(100),
                                        onClick = { /*TODO*/ }, label = {
                                            Text(text = "Call")
                                        })
                                    ElevatedAssistChip(
                                        shape = RoundedCornerShape(100),
                                        onClick = { /*TODO*/ }, label = {
                                            Text(text = "Message")
                                        })
                                }
                                Row(
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Text(text = "Rating",
                                        fontWeight = FontWeight.Light,
                                        fontSize = 12.sp)
                                    Text(text = "4.2/5.5",
                                        fontWeight = FontWeight.Light,
                                        fontSize = 12.sp)
                                }
                                Text(text = "Kampala , Uganda")
                                Text(
                                    text = "Uro Care Hospital is a private, specialized healthcare facility in Uganda. It is a specialists' hospital and diagnostic centre focusing primarily on the areas of urology and nephrology. The facility is owned, operated and administered by Ugandan healthcare professionals.",
                                    fontWeight = FontWeight.Light,
                                    fontSize = 13.sp,
                                    lineHeight = 13.sp
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}