package metospherus.app.overview

import android.content.res.Configuration
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
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import metospherus.app.R
import metospherus.app.packages.HomeTitleContainer
import metospherus.app.ui.theme.PherusTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DoctorsContainer() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            },
                actions = {
                    IconButton(onClick = {
                    }) {
                        Icon(
                            imageVector = Icons.Rounded.Notifications,
                            contentDescription = "Drawer Menu"
                        )
                    }
                    IconButton(
                        onClick = {

                        }
                    ) {
                        Card(
                            shape = RoundedCornerShape(100),
                            border = BorderStroke(
                                2.dp, MaterialTheme.colorScheme.primary
                            )
                        ) {
                            Image(
                                rememberAsyncImagePainter("https://cdn-gdhon.nitrocdn.com/fHALTxgWxEwvBAyZzppjiVEjtHjWaagT/assets/images/optimized/rev-4f89ccb/cadabra.studio/wp-content/uploads/elementor/thumbs/healthcare-app-design-01-psfj54zoarbp8avewi99v205oxq87ap72k4dj0mysg.png"),
                                contentDescription = null,
                                contentScale = ContentScale.Crop,
                                modifier = Modifier
                                    .height(23.dp)
                                    .width(23.dp)
                            )
                        }
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .heightIn(max = parentHeight)
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        ElevatedCard(
                            modifier = Modifier
                                .weight(1.0F),
                            onClick = { /*TODO*/ }) {
                            Text(text = "12")
                            Text(text = "Appointments")
                        }

                        ElevatedCard(
                            modifier = Modifier
                                .weight(1.0F),
                            onClick = { /*TODO*/ }) {
                            Text(text = "12")
                            Text(text = "Communication")
                        }

                        ElevatedCard(
                            modifier = Modifier
                                .weight(1.0F),
                            onClick = { /*TODO*/ }) {
                            Text(text = "12")
                            Text(text = "Patients")
                        }

                        ElevatedCard(
                            modifier = Modifier
                                .weight(1.0F),
                            onClick = { /*TODO*/ }) {
                            Text(text = "12")
                            Text(text = "Patients")
                        }
                    }
                }

                item {
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp))
                    HomeTitleContainer(title = "Communications")
                    Spacer(modifier = Modifier
                        .fillMaxWidth()
                        .height(10.dp))
                }

                items(count = 6) {
                    ElevatedCard(
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(35),
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)) {
                        Row {
                            Card(
                                modifier = Modifier.padding(5.dp),
                                shape = RoundedCornerShape(100),
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
                                    .padding(5.dp),
                                verticalArrangement = Arrangement.spacedBy(2.dp)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                ) {
                                    Row {
                                        Text(text = "Sender Name Holder",
                                            fontWeight = FontWeight.ExtraBold,
                                            fontSize = 12.sp,
                                            lineHeight = 12.sp)
                                    }
                                    Text(text = "12:45 PM Aug",
                                        fontWeight = FontWeight.Light,
                                        fontSize = 8.sp,
                                        lineHeight = 8.sp,
                                        modifier = Modifier.align(Alignment.TopEnd)
                                            .padding(end = 20.dp))
                                }
                                Text(text = "Message reference or subject",
                                    fontWeight = FontWeight.Normal,
                                    fontSize = 12.sp,
                                    lineHeight = 12.sp)
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

@Preview(
    name = "DoctorsContainer",
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL,
    wallpaper = Wallpapers.GREEN_DOMINATED_EXAMPLE, showSystemUi = true, showBackground = true
)
@Composable
private fun PreviewDoctorsContainer() {
    PherusTheme {
        DoctorsContainer()
    }
}