package metospherus.app.modules

import android.content.res.Configuration
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CardElevation
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
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.Wallpapers
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import metospherus.app.R
import metospherus.app.ui.theme.PherusTheme

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PeriodsTrackerModule() {
    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(title = {
                Text(text = stringResource(id = R.string.app_name))
            }, navigationIcon = {
                IconButton(
                    onClick = {
                        // navController.navigate(accountType)
                    },
                    modifier = Modifier.size(48.dp),
                ) {
                    Icon(
                        imageVector = ImageVector.vectorResource(id = R.drawable.rouded_arrow),
                        contentDescription = "Navigate back to Home"
                    )
                }
            },
                actions = {
                    IconButton(
                        onClick = {
                            // navController.navigate(accountType)
                        },
                        modifier = Modifier.size(48.dp),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.rounded_notifications),
                            contentDescription = null
                        )
                    }

                    IconButton(
                        onClick = {
                            // navController.navigate(accountType)
                        },
                        modifier = Modifier.size(48.dp),
                    ) {
                        Icon(
                            imageVector = ImageVector.vectorResource(id = R.drawable.rounded_folder),
                            contentDescription = null
                        )
                    }

                    ElevatedAssistChip(
                        shape = RoundedCornerShape(100),
                        onClick = { /*TODO*/ },
                        label = {
                            Text(text = "May 2023")
                        })
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
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ) {
                        Text(
                            text = "Personal Period\nTracker",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 32.sp,
                            lineHeight = 32.sp
                        )
                    }
                }

                item {
                    Row(
                        modifier = Modifier.fillMaxWidth(),
                        horizontalArrangement = Arrangement.Center,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            ElevatedCard(
                                modifier = Modifier.size(300.dp),
                                shape = RoundedCornerShape(100),
                                onClick = { /*TODO*/ }) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .fillMaxHeight(),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Text(text = "Period")
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 46.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                append("8")
                                            }
                                            Spacer(modifier = Modifier.padding(1.dp))
                                            withStyle(style = SpanStyle(fontSize = 23.sp)) {
                                                append("days left")
                                            }
                                        },
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.Center,
                                        modifier = Modifier
                                    )
                                    ElevatedAssistChip(
                                        shape = RoundedCornerShape(100),
                                        onClick = { /*TODO*/ },
                                        label = {
                                            Text(text = "+72 hours")
                                        })
                                }
                            }
                        }
                    }
                }

                item {
                    Column(
                        modifier = Modifier.padding(10.dp),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(5.dp)
                    ) {
                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(100),
                            onClick = { /*TODO*/ }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Pregnancy Chance",
                                    fontWeight = FontWeight.Normal
                                )
                                ElevatedAssistChip(
                                    shape = RoundedCornerShape(100),
                                    onClick = { /*TODO*/ },
                                    label = {
                                        Text(text = "low")
                                    })
                            }
                        }

                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(100),
                            onClick = { /*TODO*/ }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Longest cycle length",
                                    fontWeight = FontWeight.Normal
                                )
                                ElevatedAssistChip(
                                    shape = RoundedCornerShape(100),
                                    onClick = { /*TODO*/ },
                                    label = {
                                        Text(text = "23 days")
                                    })
                            }
                        }

                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(100),
                            onClick = { /*TODO*/ }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Shortest cycle length",
                                    fontWeight = FontWeight.Normal
                                )
                                ElevatedAssistChip(
                                    shape = RoundedCornerShape(100),
                                    onClick = { /*TODO*/ },
                                    label = {
                                        Text(text = "18 days")
                                    })
                            }
                        }

                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(100),
                            onClick = { /*TODO*/ }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Cramps Intensity",
                                    fontWeight = FontWeight.Normal
                                )
                                Column {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                    }
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 18.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                append("2")
                                            }
                                            Spacer(modifier = Modifier.padding(1.dp))
                                            withStyle(style = SpanStyle(fontSize = 12.sp)) {
                                                append("/5")
                                            }
                                        },
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }

                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(100),
                            onClick = { /*TODO*/ }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Flow Intensity",
                                    fontWeight = FontWeight.Normal
                                )
                                Column {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                    }
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 18.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                append("2")
                                            }
                                            Spacer(modifier = Modifier.padding(1.dp))
                                            withStyle(style = SpanStyle(fontSize = 12.sp)) {
                                                append("/5")
                                            }
                                        },
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier
                                    )
                                }
                            }
                        }

                        ElevatedCard(
                            modifier = Modifier.fillMaxWidth(),
                            shape = RoundedCornerShape(100),
                            onClick = { /*TODO*/ }) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                horizontalArrangement = Arrangement.SpaceBetween,
                                verticalAlignment = Alignment.CenterVertically
                            ) {
                                Text(
                                    text = "Moods",
                                    fontWeight = FontWeight.Normal
                                )
                                Column {
                                    Row(
                                        horizontalArrangement = Arrangement.spacedBy(5.dp),
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                        Box(
                                            modifier = Modifier
                                                .size(20.dp)
                                                .background(
                                                    MaterialTheme.colorScheme.primary,
                                                    shape = RoundedCornerShape(20)
                                                )
                                        )
                                    }
                                    Text(
                                        text = buildAnnotatedString {
                                            withStyle(
                                                style = SpanStyle(
                                                    fontSize = 18.sp,
                                                    color = MaterialTheme.colorScheme.primary
                                                )
                                            ) {
                                                append("2")
                                            }
                                            Spacer(modifier = Modifier.padding(1.dp))
                                            withStyle(style = SpanStyle(fontSize = 12.sp)) {
                                                append("/5")
                                            }
                                        },
                                        fontWeight = FontWeight.Bold,
                                        textAlign = TextAlign.End,
                                        modifier = Modifier
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

@Preview(
    name = "PeriodsTrackerModule", showSystemUi = true, showBackground = true,
    wallpaper = Wallpapers.BLUE_DOMINATED_EXAMPLE,
    uiMode = Configuration.UI_MODE_NIGHT_YES or Configuration.UI_MODE_TYPE_NORMAL
)
@Composable
private fun PreviewPeriodsTrackerModule() {
    PherusTheme {
        PeriodsTrackerModule()
    }
}