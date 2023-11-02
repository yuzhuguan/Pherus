package metospherus.app.overview

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyHorizontalGrid
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.rememberLazyGridState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Add
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Extension
import androidx.compose.material.icons.rounded.Info
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.ElevatedAssistChip
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FabPosition
import androidx.compose.material3.FilledTonalIconButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalDrawerSheet
import androidx.compose.material3.ModalNavigationDrawer
import androidx.compose.material3.NavigationDrawerItem
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedCard
import androidx.compose.material3.OutlinedIconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberDrawerState
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import androidx.compose.ui.window.DialogProperties
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import coil.compose.rememberAsyncImagePainter
import coil.request.ImageRequest
import kotlinx.coroutines.launch
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal
import metospherus.app.database.LocalDatabase.drawerItems
import metospherus.app.packages.AutoSlidingCarousel
import metospherus.app.packages.HealthModulesCard
import metospherus.app.packages.HomeTitleContainer
import metospherus.app.packages.OptionsDialogModules
import metospherus.app.packages.PherusBottomSheetModel
import metospherus.app.packages.PherusElevatedCard

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeContainer(
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
) {
    val coroutineScope = rememberCoroutineScope()
    val snackbarHostState = remember { SnackbarHostState() }
    val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
    var showDialogProfile by remember { mutableStateOf(false) }
    var showDialogMenu by remember { mutableStateOf(false) }
    var showBottomSheetState by remember { mutableStateOf(false) }
    var showModulesDialog by remember { mutableStateOf(false) }
    val categoryBottomSheet by remember { mutableStateOf("") }
    val sheetState = rememberModalBottomSheetState()

    val userdb = baseLocal.userdb.collectAsState().value
    val servicedb = baseLocal.servicedb.collectAsState().value
    val moduledb = baseLocal.patientEnabledModulesdb.collectAsState().value
    val crowdFund = baseLocal.fundingdb.collectAsState().value

    val images = listOf(
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
        "https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg",
    )

    ModalNavigationDrawer(
        modifier = Modifier.fillMaxSize(),
        drawerState = drawerState,
        drawerContent = {
            ModalDrawerSheet(
                drawerTonalElevation = 0.dp,
                drawerContainerColor = MaterialTheme.colorScheme.background,
                modifier = Modifier
                    .wrapContentWidth()
                    .padding(end = 130.dp),
                drawerShape = RoundedCornerShape(topEnd = 0.dp, bottomEnd = 0.dp)
            ) {
                Column(
                    modifier = Modifier.padding(10.dp)
                ) {
                    Text(
                        text = stringResource(id = R.string.app_name),
                        fontWeight = FontWeight.ExtraBold,
                        fontSize = 34.sp,
                        letterSpacing = 10.sp,
                        fontFamily = FontFamily.Monospace
                    )
                    Text(
                        text = "A comprehensive medical system for with you in mind.",
                        fontWeight = FontWeight.Light,
                        fontSize = 12.sp
                    )
                }
                Spacer(modifier = Modifier.padding(5.dp))
                Divider()
                Spacer(modifier = Modifier.padding(5.dp))
                LazyColumn {
                    items(items = drawerItems.chunked(3)) { rowItems ->
                        rowItems.forEachIndexed { index, item ->
                            NavigationDrawerItem(
                                label = {
                                    Text(text = item.title.toString())
                                },
                                icon = {
                                    Icon(item.icon!!, contentDescription = null)
                                },
                                selected = false,
                                onClick = {
                                    item.onClick(navController, baseLocal)
                                    coroutineScope.launch { drawerState.close() }
                                }
                            )

                            if ((index + 1) % 3 == 0 && index < drawerItems.size - 1) {
                                Divider(
                                    modifier = Modifier.padding(bottom = 10.dp)
                                )
                            }
                        }
                    }
                }
            }
        },
        gesturesEnabled = true
    ) {
        Scaffold(
            modifier = Modifier.fillMaxSize(),
            topBar = {
                TopAppBar(
                    title = { Text(text = stringResource(id = R.string.app_name)) },
                    modifier = Modifier.fillMaxWidth(),
                    navigationIcon = {
                        IconButton(
                            onClick = {
                                coroutineScope.launch {
                                    drawerState.apply {
                                        if (isClosed) open() else close()
                                    }
                                }
                            },
                            modifier = Modifier.size(48.dp),
                        ) {
                            Icon(
                                imageVector = ImageVector.vectorResource(id = R.drawable.rounded_menu),
                                contentDescription = "Drawer Menu"
                            )
                        }
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
                                if (baseLocal.currentUserUid != null) {
                                    showDialogProfile = true
                                } else {
                                    navController.navigate(route = "login")
                                }
                            }
                        ) {
                            Card(
                                shape = RoundedCornerShape(100),
                                border = BorderStroke(
                                    2.dp, MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Image(
                                    rememberAsyncImagePainter(userdb?.accountInformation?.accountAvatar.toString()),
                                    contentDescription = null,
                                    contentScale = ContentScale.Crop,
                                    modifier = Modifier
                                        .height(23.dp)
                                        .width(23.dp)
                                )
                            }
                        }
                    }
                )
            },
            snackbarHost = {
                SnackbarHost(
                    hostState = snackbarHostState,
                    modifier = Modifier.fillMaxSize()
                ) { snackbarData ->
                    Box(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(10.dp),
                        contentAlignment = Alignment.BottomCenter,
                    ) {
                        Snackbar(
                            snackbarData = snackbarData,
                            modifier = Modifier.fillMaxWidth(),
                            containerColor = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(20)
                        )
                    }
                }
            },
            floatingActionButton = {
                FloatingActionButton(
                    onClick = { showDialogMenu = true },
                    shape = RoundedCornerShape(40)
                ) {
                    Icon(Icons.Rounded.Add, contentDescription = "")
                }
            },
            floatingActionButtonPosition = FabPosition.End
        ) { paddingValue ->
            BoxWithConstraints(
                modifier = Modifier
                    .padding(paddingValues = paddingValue)
                    .fillMaxSize()
            ) {
                val parentHeight = maxHeight
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    userScrollEnabled = true
                ) {
                    /** Advertisement Card **/
                    item {
                        ElevatedCard(
                            onClick = { /*TODO*/ },
                            modifier = Modifier
                                .fillMaxSize()
                                .padding(start = 10.dp, end = 10.dp)
                        ) {
                            Box(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(0.dp)
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
                                Column(
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .background(
                                            MaterialTheme.colorScheme.background,
                                            shape = RoundedCornerShape(10)
                                        )
                                        .align(alignment = Alignment.TopStart)
                                ) {
                                    Text(
                                        text = "Pherus Medical System|",
                                        modifier = Modifier.padding(
                                            start = 5.dp,
                                            end = 5.dp,
                                            top = 5.dp
                                        )
                                    )
                                    Text(
                                        text = "medictor atra vialter|",
                                        modifier = Modifier.padding(
                                            start = 5.dp,
                                            end = 5.dp,
                                            bottom = 5.dp
                                        )
                                    )
                                }
                            }
                        }
                        Spacer(modifier = Modifier.padding(5.dp))
                    }

                    /** Medical Content By Medical Doctors
                    item {
                    LazyHorizontalGrid(
                    rows = GridCells.Fixed(1),
                    state = rememberLazyGridState(),
                    modifier = Modifier
                    .fillMaxWidth()
                    .height(80.dp)
                    .width(80.dp)
                    .widthIn(max = parentMaxWidth)
                    .heightIn(max = parentHeight, min = 70.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                    ) {
                    items(5) {
                    ElevatedCard(
                    onClick = {},
                    modifier = Modifier
                    .padding(2.dp)
                    .width(80.dp)
                    .heightIn(max = parentHeight)
                    ) {
                    Box(
                    modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .padding(0.dp)
                    ) {
                    Image(
                    rememberAsyncImagePainter("https://firebasestorage.googleapis.com/v0/b/metospherus-kit.appspot.com/o/participants%2Fgeneralprofiles%2FgeneralProfiles.png?alt=media&token=c101c2cd-2656-42b9-a69a-06d3522d9389"),
                    contentDescription = null,
                    modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight(),
                    contentScale = ContentScale.Crop
                    )
                    Column(
                    Modifier
                    .padding(0.dp)
                    .fillMaxWidth()
                    .wrapContentHeight()
                    .background(
                    MaterialTheme.colorScheme.background,
                    shape = RoundedCornerShape(10)
                    )
                    .align(alignment = Alignment.BottomStart)
                    ) {
                    Text(
                    text = "Breast Surgery [Dr. R.Jartus]",
                    fontWeight = FontWeight.Bold,
                    fontSize = 8.sp,
                    textAlign = TextAlign.Start,
                    lineHeight = 8.sp,
                    modifier = Modifier.padding(2.dp)
                    )
                    LinearProgressIndicator(
                    progress = 0.50f,
                    color = MaterialTheme.colorScheme.primary,
                    modifier = Modifier
                    .fillMaxWidth()
                    )
                    }
                    }
                    }
                    }
                    }
                    Spacer(modifier = Modifier.padding(5.dp))
                    }
                     **/

                    /** Categories  **/
                    item {
                        HomeTitleContainer(title = "Categories")
                        LazyVerticalGrid(
                            columns = GridCells.Fixed(4),
                            state = rememberLazyGridState(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .heightIn(max = parentHeight),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(servicedb.size) { index ->
                                val item = servicedb[index]
                                PherusElevatedCard(
                                    item = item,
                                    onClickListener = {
                                        navController.navigate(item.name.lowercase())
                                    })
                            }
                        }
                    }

                    /** Health Modules  **/
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .wrapContentHeight()
                                .padding(start = 10.dp, end = 10.dp),
                            horizontalArrangement = Arrangement.SpaceBetween,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Health Modules",
                                fontWeight = FontWeight.Bold,
                                fontSize = 18.sp,
                                maxLines = 1,
                                modifier = Modifier
                            )
                            ElevatedAssistChip(
                                leadingIcon = {
                                    Icon(Icons.Rounded.Extension, contentDescription = null)
                                },
                                onClick = {
                                    showModulesDialog = true
                                },
                                label = {
                                    Text(
                                        text = "Add Module",
                                        fontWeight = FontWeight.Bold,
                                        fontSize = 11.sp,
                                    )
                                },
                                shape = RoundedCornerShape(100)
                            )
                        }

                        LazyVerticalGrid(
                            columns = GridCells.Fixed(4),
                            state = rememberLazyGridState(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .wrapContentHeight()
                                .heightIn(max = parentHeight),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(moduledb.size) { index ->
                                val items = moduledb[index]
                                HealthModulesCard(
                                    items = items
                                ) {
                                    if (items.name.toString()
                                            .contains("General Health", ignoreCase = true)
                                    ) {
                                        navController.navigate(route = "general")
                                    }
                                    if (items.name.toString().contains("sos", ignoreCase = true)) {
                                        navController.navigate(route = "sos")
                                    }
                                    if (items.name.toString()
                                            .contains("medication", ignoreCase = true)
                                    ) {
                                        navController.navigate(route = "medication")
                                    }
                                    if (items.name.toString()
                                            .contains("Periods Tracker", ignoreCase = true)
                                    ) {
                                        navController.navigate(route = "period")
                                    }
                                }
                            }
                        }
                    }

                    /** Crowd Funding  **/
                    item {
                        HomeTitleContainer(title = "CrowdFunding")

                        LazyHorizontalGrid(
                            rows = GridCells.Fixed(1),
                            state = rememberLazyGridState(),
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(10.dp)
                                .height(180.dp)
                                .heightIn(max = parentHeight),
                            horizontalArrangement = Arrangement.SpaceBetween
                        ) {
                            items(crowdFund.size) { index ->
                                val item = crowdFund[index]
                                ElevatedCard(
                                    onClick = {},
                                    modifier = Modifier
                                        .width(200.dp)
                                        .fillMaxWidth()
                                ) {
                                    Column(
                                        modifier = Modifier.fillMaxSize()
                                    ) {
                                        Box(
                                            modifier = Modifier.fillMaxSize()
                                        ) {
                                            Image(
                                                rememberAsyncImagePainter("https://cdn.pixabay.com/photo/2015/04/23/22/00/tree-736885__340.jpg"),
                                                contentDescription = null,
                                                modifier = Modifier
                                                    .fillMaxSize()
                                                    .height(180.dp)
                                                    .fillMaxHeight(),
                                                contentScale = ContentScale.Crop
                                            )

                                            Column(
                                                Modifier
                                                    .padding(5.dp)
                                            ) {
                                                Text(
                                                    text = item.name.toString(),
                                                    fontWeight = FontWeight.ExtraBold,
                                                    modifier = Modifier
                                                )
                                                Text(
                                                    text = item.description.toString(),
                                                    fontWeight = FontWeight.Light,
                                                    fontSize = 8.sp,
                                                    lineHeight = 8.sp,
                                                    maxLines = 6,
                                                    softWrap = true
                                                )
                                                Text(
                                                    text = buildAnnotatedString {
                                                        withStyle(style = SpanStyle(fontSize = 16.sp)) {
                                                            append("52$")
                                                        }
                                                        withStyle(
                                                            style = SpanStyle(
                                                                fontSize = 28.sp,
                                                                color = MaterialTheme.colorScheme.primary
                                                            )
                                                        ) {
                                                            append("/100$")
                                                        }
                                                    },
                                                    fontWeight = FontWeight.Bold,
                                                    textAlign = TextAlign.End,
                                                    modifier = Modifier.weight(1f)
                                                )
                                            }

                                            IconButton(
                                                modifier = Modifier
                                                    .align(alignment = Alignment.BottomEnd),
                                                onClick = { /*TODO*/ }) {
                                                Text(
                                                    modifier = Modifier.blur(1.dp),
                                                    text = "health funds",
                                                    fontWeight = FontWeight.Light,
                                                    fontSize = 8.sp,
                                                    lineHeight = 8.sp
                                                )
                                                Icon(
                                                    modifier = Modifier
                                                        .size(23.dp),
                                                    painter = painterResource(id = R.drawable.logo),
                                                    contentDescription = null,
                                                    tint = Color.Unspecified
                                                )
                                            }
                                        }
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
        }

        if (showBottomSheetState) {
            PherusBottomSheetModel(
                title = categoryBottomSheet,
                onDismissListener = {
                    showBottomSheetState = false
                },
                onBottomSheetState = sheetState
            )
        }

        if (showModulesDialog) {
            OptionsDialogModules(
                closeSelection = {
                    showModulesDialog = false
                },
                viewModel = baseLocal,
                snackbarHostState = snackbarHostState,
                scope = coroutineScope
            )
        }

        if (showDialogProfile) {
            Dialog(
                onDismissRequest = {
                    showDialogProfile = false
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
                        Card(
                            modifier = Modifier.padding(10.dp),
                            shape = RoundedCornerShape(15)
                        ) {
                            Column(
                                modifier = Modifier.fillMaxWidth(),
                                horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.Center
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    horizontalArrangement = Arrangement.SpaceBetween,
                                    verticalAlignment = Alignment.Top
                                ) {
                                    OutlinedCard(
                                        shape = RoundedCornerShape(100),
                                        border = BorderStroke(
                                            2.dp, MaterialTheme.colorScheme.primary
                                        )
                                    ) {
                                        Image(
                                            rememberAsyncImagePainter(userdb?.accountInformation?.accountAvatar.toString()),
                                            contentDescription = null,
                                            modifier = Modifier
                                                .size(80.dp),
                                            alignment = Alignment.Center,
                                            contentScale = ContentScale.Crop
                                        )
                                    }

                                    OutlinedIconButton(
                                        onClick = {
                                            showDialogProfile = false
                                        }, border = BorderStroke(
                                            2.dp, MaterialTheme.colorScheme.primary
                                        )
                                    ) {
                                        Icon(
                                            imageVector = Icons.Rounded.Close,
                                            contentDescription = null,
                                            tint = MaterialTheme.colorScheme.primary
                                        )
                                    }
                                }

                                Text(
                                    text = userdb?.accountInformation?.accountName.toString(),
                                    fontWeight = FontWeight.ExtraBold,
                                    fontSize = 23.sp
                                )

                                Text(
                                    text = userdb?.accountInformation?.accountEmail.toString(),
                                    fontWeight = FontWeight.SemiBold
                                )

                                Row(
                                    modifier = Modifier.padding(top = 10.dp, bottom = 20.dp)
                                ) {
                                    OutlinedButton(
                                        onClick = {
                                            showDialogProfile = false
                                            navController.navigate("profile")
                                        },
                                        shape = RoundedCornerShape(20)
                                    ) {
                                        Text(text = "Manage Your Pherus Account")
                                    }
                                }
                            }
                        }

                        Text(
                            text = stringResource(id = R.string.app_name),
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Bold,
                            fontSize = 18.sp,
                            softWrap = true,
                            maxLines = 1,
                            textDecoration = TextDecoration.LineThrough,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 10.dp, bottom = 10.dp),
                        )
                    }
                }
            }
        }

        if (showDialogMenu) {
            Dialog(
                onDismissRequest = {
                    showDialogMenu = false
                },
                properties = DialogProperties(
                    dismissOnBackPress = true,
                    dismissOnClickOutside = true,
                    usePlatformDefaultWidth = false
                )
            ) {
                BoxWithConstraints(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(10.dp),
                ) {
                    Column(
                        modifier = Modifier
                            .align(alignment = Alignment.BottomCenter)
                            .fillMaxWidth()
                            .background(
                                MaterialTheme.colorScheme.background,
                                shape = RoundedCornerShape(10)
                            )
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth().padding(10.dp)
                        ) {
                            Row {
                                IconButton(onClick = {
                                    showDialogMenu = false
                                }) {
                                    Icon(Icons.Rounded.Close, contentDescription = null)
                                }
                            }

                            Card(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(5.dp)
                            ) {
                                Row(
                                    modifier = Modifier
                                        .fillMaxWidth().padding(5.dp),
                                    horizontalArrangement = Arrangement.spacedBy(5.dp)
                                ) {
                                    Icon(Icons.Rounded.Info, contentDescription = null)
                                    Text(text = "We combined all required resources to one access point to easily add information to your databse",
                                        fontWeight = FontWeight.Normal,
                                        fontSize = 15.sp,
                                        lineHeight = 15.sp)
                                }
                            }

                            Text(text = "Molographic")
                            Text(text = "Molographic")
                            Text(text = "Molographic")
                            Text(text = "Molographic")
                            Text(text = "Molographic")
                            Text(text = "Molographic")
                            Text(text = "Molographic")
                        }
                    }
                }
            }
        }
    }
}