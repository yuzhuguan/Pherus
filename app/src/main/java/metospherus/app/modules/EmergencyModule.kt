package metospherus.app.modules

import android.content.Intent
import android.content.res.Configuration
import android.location.Address
import android.location.Geocoder
import android.net.Uri
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.ArrowForwardIos
import androidx.compose.material.icons.rounded.ErrorOutline
import androidx.compose.material.icons.rounded.PanoramaPhotosphereSelect
import androidx.compose.material.icons.rounded.Phone
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.content.ContextCompat.startActivity
import androidx.navigation.NavHostController
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal
import metospherus.app.ui.theme.PherusTheme
import okhttp3.OkHttpClient
import okhttp3.Request

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun EmergencyModule(
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
    accountType: String,
) {
    val contextCurrent = LocalContext.current
    val userdb = baseLocal.userdb.collectAsState().value
    val emergencyDetails by remember { mutableStateOf(userdb?.emergencyContacts) }
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
                    Text(
                        text = "Get help faster",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(start = 10.dp, bottom = 10.dp, end = 10.dp)
                    )

                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        horizontalArrangement = Arrangement.spacedBy(10.dp)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1.0F)
                        ) {
                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = {
                                    try {
                                        val intent = Intent(Intent.ACTION_DIAL)
                                        intent.data = Uri.parse("tel:${emergencyDetails?.policeNumber}")
                                        contextCurrent.startActivity(intent)
                                    } catch (e: Exception) {
                                        println("DialIntent" + "Error opening dialer: ${e.message}")
                                    }
                                },
                                shape = RoundedCornerShape(35)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            Icons.Rounded.PanoramaPhotosphereSelect,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                        Column {
                                            Text(text = "Call The")
                                            Text(text = "Police")
                                        }
                                    }
                                    Icon(
                                        Icons.Rounded.ArrowForwardIos,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterEnd)
                                            .size(18.dp)
                                    )
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .weight(1.0F)
                        ) {
                            ElevatedCard(
                                modifier = Modifier.fillMaxWidth(),
                                onClick = { /*TODO*/ },
                                shape = RoundedCornerShape(35)
                            ) {
                                Box(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(15.dp)
                                ) {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Icon(
                                            Icons.Rounded.Phone, contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                        Column {
                                            Text(text = "Emergency")
                                            Text(text = "Sharing")
                                        }
                                    }
                                    Icon(
                                        Icons.Rounded.ArrowForwardIos,
                                        contentDescription = null,
                                        modifier = Modifier
                                            .align(alignment = Alignment.CenterEnd)
                                            .size(18.dp)
                                    )
                                }
                            }
                        }
                    }

                    Text(
                        text = "Gender Based Violence Report",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp)
                    )

                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(35)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Rounded.ErrorOutline, contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column {
                                    Text(text = "Report Gender Based Violence")
                                    Text(
                                        text = "Chat To Your local Police",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp,
                                        lineHeight = 12.sp,
                                    )
                                }
                            }
                            Icon(
                                Icons.Rounded.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterEnd)
                                    .size(18.dp)
                            )
                        }
                    }

                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(35)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {
                            Row(
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Icon(
                                    Icons.Rounded.ErrorOutline, contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary
                                )
                                Column {
                                    Text(text = "Report Gender Based Violence")
                                    Text(
                                        text = "Talk To A Lawyer",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp,
                                        lineHeight = 12.sp,
                                    )
                                }
                            }
                            Icon(
                                Icons.Rounded.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .align(alignment = Alignment.CenterEnd)
                                    .size(18.dp)
                            )
                        }
                    }

                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(15)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(end = 20.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    Icons.Rounded.ErrorOutline, contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(18.dp)
                                )

                                Column(
                                    modifier = Modifier.weight(1.0F)
                                ) {
                                    Text(text = "Emergency Alert (GBV)")
                                    Text(
                                        text = "Press the volume up quickly 5 times or more this will alert all available resources closest to your location with live feedbacks and location.",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp,
                                        lineHeight = 12.sp,
                                        modifier = Modifier.padding(end = 10.dp)
                                    )
                                }
                            }

                            Icon(
                                Icons.Rounded.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp)
                                    .align(Alignment.CenterEnd)
                            )
                        }
                    }

                    Text(
                        text = "Health Emergency",
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        modifier = Modifier.padding(10.dp)
                    )

                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(15)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(end = 20.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    Icons.Rounded.ErrorOutline, contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(18.dp)
                                )

                                Column(
                                    modifier = Modifier.weight(1.0F)
                                ) {
                                    Text(text = "Emergency Alert (Health)")
                                    Text(
                                        text = "Press the volume down quickly 5 times or more this will alert all available resources closest to your location with live feedbacks and location.",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp,
                                        lineHeight = 12.sp,
                                        modifier = Modifier.padding(end = 10.dp)
                                    )
                                }
                            }

                            Icon(
                                Icons.Rounded.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp)
                                    .align(Alignment.CenterEnd)
                            )
                        }
                    }

                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, top = 10.dp, end = 10.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(15)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(15.dp)
                        ) {
                            Row(
                                modifier = Modifier.padding(end = 20.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp),
                                verticalAlignment = Alignment.Top
                            ) {
                                Icon(
                                    Icons.Rounded.ErrorOutline, contentDescription = null,
                                    tint = MaterialTheme.colorScheme.primary,
                                    modifier = Modifier
                                        .size(18.dp)
                                )

                                Column(
                                    modifier = Modifier.weight(1.0F)
                                ) {
                                    Text(text = "Crisis alerts")
                                    Text(
                                        text = "This system will activate a loud alert and send a notification to inform you about any ongoing crisis in your area. It provides essential updates and live information to ensure your safety. While the initial alert may startle you, it serves to trigger the natural 'fight or flight' response in all humans.",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 12.sp,
                                        lineHeight = 12.sp,
                                        modifier = Modifier.padding(end = 10.dp)
                                    )
                                }
                            }

                            Icon(
                                Icons.Rounded.ArrowForwardIos,
                                contentDescription = null,
                                modifier = Modifier
                                    .size(18.dp)
                                    .align(Alignment.CenterEnd)
                            )
                        }
                    }
                }

                /** Footer  **/
                item {
                    Column(
                        modifier = Modifier
                            .padding(20.dp)
                            .fillMaxWidth()
                            .align(alignment = Alignment.Center),
                        verticalArrangement = Arrangement.Center,
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = "Powered by Pherus",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            letterSpacing = 8.sp,
                            modifier = Modifier
                        )
                    }
                }
            }
        }
    }
}