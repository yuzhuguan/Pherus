package metospherus.app.overview

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AutoGraph
import androidx.compose.material.icons.rounded.MailOutline
import androidx.compose.material3.Button
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.auth.api.signin.GoogleSignInOptions
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.list.ListDialog
import com.maxkeppeler.sheets.list.models.ListOption
import com.maxkeppeler.sheets.list.models.ListSelection
import com.maxkeppeler.sheets.state.StateDialog
import com.maxkeppeler.sheets.state.models.ProgressIndicator
import com.maxkeppeler.sheets.state.models.State
import com.maxkeppeler.sheets.state.models.StateConfig
import kotlinx.coroutines.delay
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal
import metospherus.app.database.LocalDatabase.AccountInformation
import metospherus.app.database.LocalDatabase.CurrentUserDatabase
import metospherus.app.database.LocalDatabase.SystemReservedInformation
import metospherus.app.database.LocalDatabase.getCurrentDate
import metospherus.app.database.PreferenceManagerLocal

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginContainer(
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
) {
    val currentContent = LocalContext.current
    PreferenceManagerLocal.init(currentContent)
    var valueState by remember { mutableStateOf(false) }
    var showLoadingBar by remember { mutableStateOf(false) }
    val accountType = PreferenceManagerLocal.getLiveData("accountType", "")

    println(accountType)
    var lazyMaterialSpinner by remember { mutableStateOf(false) }
    val launcher = baseLocal.rememberFirebaseAuthLauncher(
        onAuthComplete = { authResult ->
            showLoadingBar = true
            valueState = true
            accountType.observeForever { accountTypeValue ->
                if (accountTypeValue.isNotEmpty()) {
                    authResult.user?.uid?.let { currentUserId ->
                        baseLocal.retrieveFirebaseDatabase(
                            path = "root/${accountTypeValue.lowercase()}/${currentUserId}",
                            offline = true
                        ) { snapshot ->
                            showLoadingBar = true
                            valueState = true
                            if (snapshot.exists()) {
                                showLoadingBar = false
                                valueState = false
                                when (accountTypeValue) {
                                    "Patient" -> {
                                        PreferenceManagerLocal.set("currentUserAccountType", "home")
                                        navController.navigate(route = "home")
                                    }

                                    "Interprofessional" -> {
                                        PreferenceManagerLocal.set("currentUserAccountType", "doctors")
                                        navController.navigate(route = "doctors")
                                    }

                                    "Pharmacy" -> {
                                        PreferenceManagerLocal.set("currentUserAccountType", "pharmacy")
                                        navController.navigate(route = "pharmacy")
                                    }

                                    "Hospital" -> {
                                        PreferenceManagerLocal.set("currentUserAccountType", "hospital")
                                        navController.navigate(route = "hospital")
                                    }
                                }
                            } else {
                                showLoadingBar = true
                                valueState = true
                                val newAccountHashMap = CurrentUserDatabase(
                                    accountInformation = AccountInformation(
                                        accountEmail = authResult.user?.email.toString(),
                                        accountAvatar = authResult.user?.photoUrl.toString(),
                                        accountName = authResult.user?.displayName.toString(),
                                        accountPhoneNumber = authResult.user?.phoneNumber.toString(),
                                        accountType = accountTypeValue.lowercase()
                                    ),
                                    systemReservedInformation = SystemReservedInformation(
                                        dateOfRegistration = getCurrentDate().toString(),
                                        accountUserUid = authResult.user?.uid.toString()
                                    )
                                )
                                baseLocal.updateFirebaseDatabase(
                                    path = "root/${accountTypeValue.lowercase()}/${currentUserId}",
                                    value = newAccountHashMap
                                ) {
                                    showLoadingBar = false
                                    valueState = false
                                    when (accountTypeValue) {
                                        "Patient" -> {
                                            PreferenceManagerLocal.set("currentUserAccountType", "home")
                                            navController.navigate(route = "home")
                                        }

                                        "Interprofessional" -> {
                                            PreferenceManagerLocal.set("currentUserAccountType", "doctors")
                                            navController.navigate(route = "doctors")
                                        }

                                        "Pharmacy" -> {
                                            PreferenceManagerLocal.set("currentUserAccountType", "pharmacy")
                                            navController.navigate(route = "pharmacy")
                                        }

                                        "Hospital" -> {
                                            PreferenceManagerLocal.set("currentUserAccountType", "hospital")
                                            navController.navigate(route = "hospital")
                                        }
                                    }
                                }
                            }
                        }
                    }
                } else {
                    showLoadingBar = false
                    valueState = false
                    println("account type is required")
                }
            }
        },
        onAuthError = {
            valueState = false
        })
    val token = stringResource(R.string.default_web_client_id)
    val context = LocalContext.current

    Scaffold {
        BoxWithConstraints(
            modifier = Modifier.padding(paddingValues = it)
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = it,
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally,
                userScrollEnabled = true
            ) {
                item {
                    ElevatedCard(
                        onClick = {},
                        shape = RoundedCornerShape(5),
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                    ) {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                painter = painterResource(id = R.drawable.logo),
                                contentDescription = null,
                                modifier = Modifier
                                    .size(100.dp)
                                    .background(
                                        shape = RoundedCornerShape(100),
                                        color = Color(0xFFFDC49C)
                                    )
                                    .border(
                                        5.dp,
                                        color = Color(0xFFFFA86C),
                                        shape = RoundedCornerShape(100)
                                    ),
                                alignment = Alignment.Center
                            )
                        }
                        Text(
                            text = "Welcome To Pherus A Comprehensive Medical System",
                            fontStyle = FontStyle.Normal,
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 23.sp,
                            textAlign = TextAlign.Center,
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, top = 20.dp)
                        )

                        OutlinedButton(
                            onClick = {
                                lazyMaterialSpinner = true
                            },
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, top = 10.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    Icons.Rounded.MailOutline, contentDescription = null,
                                    modifier = Modifier.padding(end = 10.dp)
                                )
                                Text(text = "Google Authentication")
                            }
                        }

                        Button(
                            onClick = {},
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(start = 10.dp, end = 10.dp, top = 5.dp)
                        ) {
                            Row(
                                modifier = Modifier
                                    .fillMaxWidth(),
                                horizontalArrangement = Arrangement.Center,
                                verticalAlignment = Alignment.CenterVertically,
                            ) {
                                Icon(
                                    Icons.Rounded.AutoGraph, contentDescription = null,
                                    modifier = Modifier.padding(end = 10.dp)
                                )
                                Text(text = "Apple Authentication")
                            }
                        }

                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(5.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Text(
                                text = "Privacy Policy",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                            Text(
                                text = ".",
                                modifier = Modifier.padding(start = 5.dp, end = 5.dp)
                            )
                            Text(
                                text = "Terms of Service",
                                fontWeight = FontWeight.Bold,
                                fontSize = 12.sp
                            )
                        }
                        Spacer(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(top = 20.dp)
                        )
                    }
                }
            }
        }

        if (showLoadingBar) {
            LoadingModule(
                closeSelection = {
                    showLoadingBar = false
                },
                onRandomBoolean = valueState
            )
        }

        if (lazyMaterialSpinner) LazyMaterialSpinner(
            closeSelection = {
                lazyMaterialSpinner = false
            }
        ) { selected ->
            when {
                selected.isNotEmpty() -> {
                    PreferenceManagerLocal.set("accountType", selected)
                    valueState = true
                    showLoadingBar = true
                    launcher.launch(
                        GoogleSignIn.getClient(
                            context,
                            GoogleSignInOptions
                                .Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                                .requestIdToken(token)
                                .requestProfile()
                                .requestEmail()
                                .build()
                        ).signInIntent
                    )
                }

                else -> {
                    valueState = false
                    showLoadingBar = false
                    lazyMaterialSpinner = false
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun LoadingModule(
    closeSelection: () -> Unit,
    onRandomBoolean: Boolean,
) {
    val sheetState = rememberUseCaseState(visible = false, onCloseRequest = { closeSelection() })

    val state = remember {
        val startState =
            State.Loading(labelText = "Fetching database...", ProgressIndicator.Circular())
        mutableStateOf<State>(startState)
    }

    LaunchedEffect(Unit) {
        sheetState.show()
        delay(2000)
        if (onRandomBoolean) {
            state.value = State.Success(labelText = "Access granted successfully!")
        } else {
            state.value = State.Failure(labelText = "Access denied. Trying again.")
        }
        delay(2000)
        sheetState.hide()
    }

    StateDialog(
        state = sheetState,
        config = StateConfig(state = state.value)
    )
}

@Composable
@OptIn(ExperimentalMaterial3Api::class)
internal fun LazyMaterialSpinner(
    closeSelection: () -> Unit,
    selectedListener: (String) -> Unit,
) {
    val options = listOf(
        ListOption(
            titleText = "Patient",
            subtitleText = "A patient is a person who is receiving medical treatment from a doctor or hospital."
        ),
        ListOption(
            titleText = "Interprofessional",
            subtitleText = "An interprofessional is comprised of a multitude of one ore more professions (e.g., doctors, nurses, physicians, psychologists, therapists etc)"
        ),
        ListOption(
            titleText = "Pharmacy",
            subtitleText = "a shop or hospital dispensary where medicinal drugs are prepared or sold."
        ),
        ListOption(
            titleText = "Hospital",
            subtitleText = "an institution providing medical and surgical treatment and nursing care for sick or injured people."
        ),
    )

    ListDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
        selection = ListSelection.Single(
            showRadioButtons = true,
            options = options
        ) { index, _ ->
            selectedListener(options[index].titleText)
        },
    )
}