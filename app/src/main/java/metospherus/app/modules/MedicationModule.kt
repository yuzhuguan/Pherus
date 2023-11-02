package metospherus.app.modules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Delete
import androidx.compose.material.icons.rounded.Edit
import androidx.compose.material.icons.rounded.HdrOnSelect
import androidx.compose.material.icons.rounded.Panorama
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal

@Composable
@OptIn(ExperimentalMaterial3Api::class)
fun MedicationModule(
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
    accountType: String,
) {
    var showBottomSheet by remember { mutableStateOf(false) }
    val userdb = baseLocal.userdb.collectAsState().value
    val accountdb by remember { mutableStateOf(userdb?.accountInformation) }

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
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    showBottomSheet = true
                },
                shape = RoundedCornerShape(45)
            ) {
                Icon(Icons.Rounded.Add, contentDescription = null)
            }
        }
    ) {
        BoxWithConstraints(
            modifier = Modifier
                .padding(paddingValues = it)
                .fillMaxSize()
        ) {
            LazyColumn(modifier = Modifier.fillMaxSize()) {
                item {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        onClick = { /*TODO*/ },
                        shape = RoundedCornerShape(35)
                    ) {
                        Box(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(30.dp)
                        ) {
                            Column {
                                Text(
                                    text = accountdb?.accountName.toString(),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 23.sp
                                )
                                Text(
                                    text = accountdb?.accountEmail.toString(),
                                    fontWeight = FontWeight.Normal
                                )
                            }

                            Row(
                                modifier = Modifier.align(alignment = Alignment.TopEnd)
                            ) {
                                Column(
                                    verticalArrangement = Arrangement.Top,
                                    horizontalAlignment = Alignment.End
                                ) {
                                    Row {
                                        Text(
                                            text = "Medicines : ",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 15.sp
                                        )
                                        Text(
                                            text = "12",
                                            fontWeight = FontWeight.Normal,
                                            fontSize = 15.sp
                                        )
                                    }
                                    Column(
                                        modifier = Modifier,
                                        verticalArrangement = Arrangement.Center,
                                        horizontalAlignment = Alignment.CenterHorizontally
                                    ) {
                                        Row(
                                            modifier = Modifier
                                                .padding(bottom = 5.dp),
                                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                        }

                                        Row(
                                            modifier = Modifier
                                                .padding(bottom = 5.dp),
                                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                        }

                                        Row(
                                            modifier = Modifier
                                                .padding(bottom = 5.dp),
                                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                        }

                                        Row(
                                            modifier = Modifier,
                                            horizontalArrangement = Arrangement.spacedBy(5.dp),
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                            Spacer(
                                                modifier = Modifier
                                                    .size(10.dp)
                                                    .background(
                                                        MaterialTheme.colorScheme.primary,
                                                        RoundedCornerShape(15)
                                                    )
                                            )
                                        }
                                    }
                                }
                            }
                        }
                    }
                }

                items(5) {
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp),
                        shape = RoundedCornerShape(30)
                    ) {
                        Row(
                            modifier = Modifier.padding(10.dp)
                        ) {
                            Icon(
                                Icons.Rounded.HdrOnSelect, contentDescription = null,
                                modifier = Modifier
                                    .size(60.dp)
                                    .padding(5.dp)
                            )

                            Column(
                                modifier = Modifier.fillMaxWidth()
                            ) {
                                Row(
                                    modifier = Modifier.fillMaxWidth()
                                ) {
                                    Box(
                                        modifier = Modifier.fillMaxWidth()
                                    ) {
                                        Column {
                                            Text(
                                                text = "Medicine Name",
                                                fontWeight = FontWeight.SemiBold
                                            )
                                            Text(text = "Period taken")
                                        }

                                        Row(
                                            modifier = Modifier.align(
                                                alignment = Alignment.TopEnd
                                            ),
                                            horizontalArrangement = Arrangement.spacedBy(5.dp)
                                        ) {
                                            IconButton(
                                                onClick = { /*TODO*/ }) {
                                                Icon(
                                                    Icons.Rounded.Edit,
                                                    contentDescription = null
                                                )
                                            }
                                            IconButton(
                                                onClick = { /*TODO*/ }) {
                                                Icon(
                                                    Icons.Rounded.Delete,
                                                    contentDescription = null
                                                )
                                            }
                                        }
                                    }
                                }

                                Row(
                                    modifier = Modifier.fillMaxWidth(),
                                    horizontalArrangement = Arrangement.spacedBy(5.dp),
                                    verticalAlignment = Alignment.CenterVertically
                                ) {
                                    ElevatedAssistChip(onClick = { /*TODO*/ }, label = {
                                        Text(text = "12:30 PM")
                                    },
                                        shape = RoundedCornerShape(100)
                                    )

                                    ElevatedAssistChip(onClick = { /*TODO*/ }, label = {
                                        Text(text = "12:30 PM")
                                    },
                                        shape = RoundedCornerShape(100)
                                    )
                                }
                            }
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

        if (showBottomSheet) {
            Dialog(
                onDismissRequest = {
                    showBottomSheet = false
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                )
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(start = 10.dp, end = 10.dp),
                ) {
                    ElevatedCard(
                        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background),
                        shape = RoundedCornerShape(10)
                    ) {
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                        ) {
                            Text(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                text = "Medication Reminder",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 23.sp
                            )
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                horizontalArrangement = Arrangement.spacedBy(5.dp)
                            ) {
                                OutlinedCard(
                                    modifier = Modifier.size(55.dp),
                                    onClick = { /*TODO*/ },
                                    shape = RoundedCornerShape(20)
                                ) {
                                    Icon(Icons.Rounded.Panorama, contentDescription = null)
                                }

                                OutlinedCard(
                                    modifier = Modifier.size(55.dp),
                                    onClick = { /*TODO*/ },
                                    shape = RoundedCornerShape(20)
                                ) {
                                    Icon(Icons.Rounded.Panorama, contentDescription = null)
                                }

                                OutlinedCard(
                                    modifier = Modifier.size(55.dp),
                                    onClick = { /*TODO*/ },
                                    shape = RoundedCornerShape(20)
                                ) {
                                    Icon(Icons.Rounded.Panorama, contentDescription = null)
                                }

                                OutlinedCard(
                                    modifier = Modifier.size(55.dp),
                                    onClick = { /*TODO*/ },
                                    shape = RoundedCornerShape(20)
                                ) {
                                    Icon(Icons.Rounded.Panorama, contentDescription = null)
                                }
                            }

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = "", onValueChange = {},
                                label = {
                                    Text(text = "Medicine Name")
                                },
                                shape = RoundedCornerShape(20)
                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = "", onValueChange = {},
                                maxLines = 6,
                                label = {
                                    Text(text = "Medicine Notes")
                                },
                                shape = RoundedCornerShape(20)
                            )

                            OutlinedTextField(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                value = "", onValueChange = {},
                                label = {
                                    Text(text = "Time Reminder")
                                },
                                shape = RoundedCornerShape(20)
                            )

                            OutlinedButton(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                onClick = { /*TODO*/ }) {
                                Text(text = "Save")
                            }
                        }
                    }
                }
            }
        }
    }
}