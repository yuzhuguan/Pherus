package metospherus.app.packages

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import coil.compose.rememberAsyncImagePainter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import metospherus.app.database.FirebaseDatabaseLocal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OptionsDialogModules(
    closeSelection: () -> Unit,
    viewModel: FirebaseDatabaseLocal,
    snackbarHostState: SnackbarHostState,
    scope: CoroutineScope,
) {
    var showInformation by remember { mutableStateOf(false) }
    var valuePosition by remember { mutableIntStateOf(0) }
    val appModulesView = viewModel.modulesdb.collectAsState().value

    Dialog(
        onDismissRequest = closeSelection,
        properties = DialogProperties(
            dismissOnBackPress = true,
            dismissOnClickOutside = true,
            usePlatformDefaultWidth = false
        )
    ) {
        ElevatedCard(
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 15.dp, end = 15.dp),
            shape = RoundedCornerShape(20.dp),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
        ) {
            BoxWithConstraints(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(5.dp)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(5.dp),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                        Text(
                            text = "Health Modules",
                            fontWeight = FontWeight.Bold,
                            fontSize = 23.sp,
                            textAlign = TextAlign.Start,
                            modifier = Modifier.weight(1f)
                        )
                        val selectedCount = appModulesView.count { it.enabled!! }
                        val totalSize = appModulesView.size

                        Text(
                            text = buildAnnotatedString {
                                withStyle(
                                    style = SpanStyle(
                                        fontSize = 28.sp,
                                        color = MaterialTheme.colorScheme.primary
                                    )
                                ) {
                                    append("$selectedCount")
                                }
                                withStyle(style = SpanStyle(fontSize = 16.sp)) {
                                    append("/$totalSize")
                                }
                            },
                            fontWeight = FontWeight.Bold,
                            textAlign = TextAlign.End,
                            modifier = Modifier.weight(1f)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp),
                        verticalArrangement = Arrangement.SpaceBetween,
                        horizontalArrangement = Arrangement.SpaceBetween,
                        userScrollEnabled = true
                    ) {
                        items(appModulesView.size) { moduleValue ->
                            val options = appModulesView[moduleValue]
                            ElevatedCard(
                                onClick = {
                                    viewModel.currentUserUid?.uid?.let {
                                        if (options.name != "General Health" && options.name != "SOS") {
                                            viewModel.updateUserModuleSelection(
                                                options.key.toString(),
                                                !options.enabled!!
                                            )
                                            closeSelection()
                                        } else {
                                          scope.launch {
                                                snackbarHostState.showSnackbar("${options.name.uppercase()} Can't be Unselected")
                                          }
                                        }
                                    } ?: run {
                                        scope.launch {
                                            snackbarHostState.showSnackbar("Please Login In Order to Use this Health Modules")
                                        }
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp),
                                shape = RoundedCornerShape(12.dp),
                                elevation = CardDefaults.cardElevation(if (options.enabled!!) 10.dp else 0.dp),
                                colors = CardDefaults.cardColors(if (options.enabled) MaterialTheme.colorScheme.primary else Color.Unspecified)
                            ) {
                                Column(
                                    modifier = Modifier
                                        .fillMaxSize()
                                        .padding(5.dp),
                                    horizontalAlignment = Alignment.CenterHorizontally,
                                    verticalArrangement = Arrangement.Center
                                ) {
                                    Row(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(end = 2.dp),
                                        horizontalArrangement = Arrangement.End,
                                        verticalAlignment = Alignment.CenterVertically
                                    ) {
                                        OutlinedIconButton(
                                            onClick = {
                                                showInformation = true
                                                valuePosition = moduleValue
                                            },
                                            modifier = Modifier
                                                .size(20.dp)
                                        ) {
                                            Icon(
                                                Icons.Rounded.Info,
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .size(18.dp)
                                                    .shadow(
                                                        3.dp,
                                                        shape = RoundedCornerShape(100.dp),
                                                        ambientColor = MaterialTheme.colorScheme.background,
                                                        spotColor = MaterialTheme.colorScheme.primary,
                                                    ),
                                                tint = MaterialTheme.colorScheme.background
                                            )
                                        }
                                    }
                                    Icon(
                                        rememberAsyncImagePainter(model = options.icon),
                                        contentDescription = options.description,
                                        modifier = Modifier
                                            .size(35.dp)
                                            .padding(1.dp),
                                        tint = Color.Unspecified
                                    )
                                    Spacer(modifier = Modifier.height(8.dp))
                                    Text(
                                        text = options.name!!,
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 13.sp,
                                        textAlign = TextAlign.Center
                                    )
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    if (showInformation) {
        Dialog(
            onDismissRequest = {
                showInformation = false
            },
            properties = DialogProperties(
                dismissOnBackPress = true,
                dismissOnClickOutside = true,
                usePlatformDefaultWidth = false
            )
        ) {
            val options = appModulesView[valuePosition]
            ElevatedCard(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 10.dp, end = 10.dp),
                shape = RoundedCornerShape(10.dp),
                colors = CardDefaults.cardColors(MaterialTheme.colorScheme.background)
            ) {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(10.dp),
                    horizontalAlignment = Alignment.Start,
                    verticalArrangement = Arrangement.Center
                ) {
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    ElevatedCard(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(0.dp),
                        shape = RoundedCornerShape(10.dp),
                        elevation = CardDefaults.cardElevation(if (options.enabled!!) 10.dp else 0.dp),
                        colors = CardDefaults.cardColors(if (options.enabled) MaterialTheme.colorScheme.primary else Color.Unspecified)
                    ) {
                        Text(
                            text = options.name!!,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(20.dp)
                        )
                    }
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = options.name!!,
                        fontWeight = FontWeight.Bold,
                        fontSize = 18.sp,
                        textAlign = TextAlign.Center
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                    Text(
                        text = options.description!!,
                        fontWeight = FontWeight.Light,
                        fontSize = 16.sp,
                        textAlign = TextAlign.Start
                    )
                    Spacer(modifier = Modifier.padding(top = 10.dp))
                }
            }
        }
    }
}