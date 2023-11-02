package metospherus.app.modules

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Bloodtype
import androidx.compose.material.icons.rounded.Label
import androidx.compose.material.icons.rounded.LocalPharmacy
import androidx.compose.material3.Divider
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledIconButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal
import metospherus.app.packages.HomeTitleContainer
import metospherus.app.packages.PherusElevatedCard

@SuppressLint("NewApi")
@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun GeneralHealthModule(
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
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp) ,
                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        Text(
                            modifier = Modifier.weight(1.0F),
                            text = "Health\n" + "overview",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 35.sp,
                            lineHeight = 35.sp)
                        Column(
                            modifier = Modifier.weight(1.0F),
                            verticalArrangement = Arrangement.spacedBy(5.dp)
                        ) {
                            Text(
                                text = "Upcoming appointment",
                                fontWeight = FontWeight.ExtraBold)
                            
                            ElevatedAssistChip(
                                modifier = Modifier.padding(5.dp),
                                onClick = { /*TODO*/ },
                                label = {
                                    Row(
                                        modifier = Modifier.fillMaxWidth().padding(0.dp),
                                        horizontalArrangement = Arrangement.Start
                                    ) {
                                        ElevatedCard(
                                            modifier = Modifier.size(45.dp),
                                            onClick = { /*TODO*/ }) {
                                            Column(
                                                modifier = Modifier.fillMaxWidth()
                                                    .fillMaxHeight(),
                                                horizontalAlignment = Alignment.CenterHorizontally,
                                                verticalArrangement = Arrangement.Center
                                            ) {
                                                Text(text = "13",
                                                    fontWeight = FontWeight.ExtraBold)
                                                Text(text = "Wed",
                                                    fontWeight = FontWeight.Light)
                                            }
                                        }
                                        Column(
                                            modifier = Modifier
                                                .padding(5.dp)
                                                .fillMaxWidth()
                                        ) {
                                            Text(text = "Dr. John Alexk",
                                                fontWeight = FontWeight.ExtraBold)
                                            Text(text = "9:00 AM",
                                                fontWeight = FontWeight.Light)
                                        }
                                    }
                                },
                                shape = RoundedCornerShape(30)
                            )
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            ElevatedCard(
                                modifier = Modifier.weight(1.0F),
                                onClick = { /*TODO*/ }) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Icon(Icons.Rounded.Bloodtype , contentDescription = null)
                                        Text(text = "Blood pressure")
                                    }
                                    Divider(
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(style = SpanStyle(fontSize = 16.sp)) {
                                                append("102")
                                            }
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 28.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                append("/80")
                                            }
                                            withStyle(style = SpanStyle(fontSize = 16.sp)) {
                                                append("mmHg")
                                            }
                                        },
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                            ElevatedCard(
                                modifier = Modifier.weight(1.0F),
                                onClick = { /*TODO*/ }) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Icon(Icons.Rounded.Bloodtype , contentDescription = null)
                                        Text(text = "Heart Rate")
                                    }
                                    Divider(
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 28.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                append("72")
                                            }
                                            withStyle(style = SpanStyle(fontSize = 16.sp)) {
                                                append("BPM")
                                            }
                                        },
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp),
                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                        ) {
                            ElevatedCard(
                                modifier = Modifier.weight(1.0F),
                                onClick = { /*TODO*/ }) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Icon(Icons.Rounded.Bloodtype , contentDescription = null)
                                        Text(text = "Sleep")
                                    }
                                    Divider(
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 28.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                append("/8")
                                            }
                                            withStyle(style = SpanStyle(fontSize = 16.sp)) {
                                                append("hours")
                                            }
                                        },
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }

                            ElevatedCard(
                                modifier = Modifier.weight(1.0F),
                                onClick = { /*TODO*/ }) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp)
                                    ) {
                                        Icon(Icons.Rounded.Bloodtype , contentDescription = null)
                                        Text(text = "Water")
                                    }
                                    Divider(
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 28.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                append("1.5")
                                            }
                                            withStyle(style = SpanStyle(fontSize = 16.sp)) {
                                                append("Litres")
                                            }
                                        },
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier.fillMaxWidth()
                                    )
                                }
                            }
                        }
                    }
                }

                items(6) {
                    ElevatedAssistChip(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        leadingIcon = {
                            IconButton(onClick = { /*TODO*/ }) {
                                Icon(Icons.Rounded.LocalPharmacy, contentDescription = null)
                            }
                        },
                        onClick = { /*TODO*/ }, label = {
                            Column(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp)
                            ) {
                                Text(text = "Mental Wellness")
                                Text(text = "Mental Wellness")
                            }
                        })
                }
            }
        }
    }
}