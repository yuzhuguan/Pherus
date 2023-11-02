package metospherus.app.overview

import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.PickVisualMediaRequest
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
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
import androidx.compose.material.icons.filled.EmojiPeople
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedCard
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import coil.compose.rememberAsyncImagePainter
import com.maxkeppeker.sheets.core.models.base.IconSource
import com.maxkeppeker.sheets.core.models.base.rememberUseCaseState
import com.maxkeppeler.sheets.input.InputDialog
import com.maxkeppeler.sheets.input.models.InputHeader
import com.maxkeppeler.sheets.input.models.InputSelection
import com.maxkeppeler.sheets.input.models.InputTextField
import com.maxkeppeler.sheets.input.models.InputTextFieldType
import com.maxkeppeler.sheets.input.models.ValidationResult
import metospherus.app.R
import metospherus.app.database.FirebaseDatabaseLocal
import metospherus.app.database.LocalDatabase.ListOfResources
import metospherus.app.database.PreferenceManagerLocal
import metospherus.app.packages.ProfileInformationHolders

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ProfileContainer(
    navController: NavHostController,
    baseLocal: FirebaseDatabaseLocal,
    accountType: String,
) {
    val context = LocalContext.current
    val userdb = baseLocal.userdb.collectAsState().value
    val userAccountInfor = userdb?.accountInformation
    val userMedicalInfor = userdb?.accountMedicalInformation

    val accountTypeValue = PreferenceManagerLocal.getLiveData("accountType", "")
    var showDialogProfile by remember { mutableStateOf(false) }
    var titleHolder by remember { mutableStateOf("") }
    var initialValue by remember { mutableStateOf("") }
    var databaseKey by remember { mutableStateOf("") }

    val listOfInformations = listOf(
        ListOfResources(
            title = "Preferred Name",
            value = userAccountInfor?.accountName.toString()
        ) {
            databaseKey = "accountInformation/accountName"
        },
        ListOfResources(
            title = "Email Address",
            value = userAccountInfor?.accountEmail.toString()
        ) {
            databaseKey = "accountInformation/accountEmail"
        },
        ListOfResources(
            title = "Primary Mobile Number",
            value = userAccountInfor?.accountPhoneNumber.toString()
        ) {
            databaseKey = "accountInformation/accountPhoneNumber"
        },
    )

    val listOfMedicalInformation = listOf(
        ListOfResources(
            title = "Blood Type",
            value = userMedicalInfor?.bloodType.toString()
        ) {
            databaseKey = "accountMedicalInformation/bloodType"
        },
        ListOfResources(
            title = "Allergies",
            value = userMedicalInfor?.allergies.toString()
        ) {
            databaseKey = "accountMedicalInformation/allergies"
        },
        ListOfResources(
            title = "Medications",
            value = userMedicalInfor?.medications.toString()
        ) {
            databaseKey = "accountMedicalInformation/medications"
        },
        ListOfResources(
            title = "Medical Notes",
            value = userMedicalInfor?.medicalNotes.toString()
        ) {
            databaseKey = "accountMedicalInformation/medicalNotes"
        },
        ListOfResources(
            title = "Organ Donor",
            value = userMedicalInfor?.organDonor.toString()
        ) {
            databaseKey = "accountMedicalInformation/organDonor"
        },
        ListOfResources(
            title = "HIV Status",
            value = userMedicalInfor?.hivStatus.toString()
        ) {
            databaseKey = "accountMedicalInformation/hivStatus"
        },
    )

    val imagePicked = remember { mutableStateOf<Uri?>(null) }
    val pickMedia = rememberLauncherForActivityResult(
        contract = ActivityResultContracts.PickVisualMedia()
    ) { result ->
        try {
            if (result != null) {
                imagePicked.value = result
            }
            println("PhotoPicker Selected URI: $result")
        } catch (e: Exception) {
            println("PhotoPicker No media selected ${e.message}")
        }
    }

    Scaffold(
        modifier = Modifier.fillMaxSize(),
        topBar = {
            TopAppBar(
                title = { Text(text = "Personal Info") },
                modifier = Modifier.fillMaxWidth(),
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
                },
            )
        }) { paddingValue ->
        BoxWithConstraints(
            modifier = Modifier
                .padding(paddingValues = paddingValue)
                .fillMaxSize()
        ) {
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                userScrollEnabled = true
            ) {
                item {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Column(
                            modifier = Modifier
                                .weight(0.7f)
                        ) {
                            OutlinedCard(
                                onClick = {
                                    pickMedia.launch(PickVisualMediaRequest(ActivityResultContracts.PickVisualMedia.ImageOnly))
                                },
                                shape = RoundedCornerShape(100),
                                border = BorderStroke(
                                    2.dp, MaterialTheme.colorScheme.primary
                                )
                            ) {
                                Image(
                                    rememberAsyncImagePainter(userAccountInfor?.accountAvatar.toString()),
                                    contentDescription = null,
                                    modifier = Modifier
                                        .size(80.dp),
                                    alignment = Alignment.Center,
                                    contentScale = ContentScale.Crop
                                )
                                imagePicked.value?.let { uri ->
                                    baseLocal.currentUserUid?.let { userUid ->
                                        baseLocal.UploadImage(
                                            uri = uri,
                                            userUid = userUid.uid,
                                            path = "accountAvatar",
                                            context = context,
                                            accountType = accountTypeValue.value.toString().lowercase(),
                                            viewModel = baseLocal
                                        )
                                    }
                                }
                            }
                        }

                        Column(
                            modifier = Modifier
                                .weight(1.3f)
                                .padding(5.dp)
                        ) {
                            Text(
                                text = "Your profile info in Pherus services",
                                fontWeight = FontWeight.ExtraBold,
                                fontSize = 23.sp
                            )
                            Text(
                                text = "Your profile info in Pherus services is only available to you and the paticipants in the systems that you grant access to your information eg Hospital , medical personal , pharmacy etc.",
                                fontWeight = FontWeight.Light,
                                fontSize = 12.sp,
                                lineHeight = 12.sp,
                                softWrap = true
                            )
                        }
                    }

                    Divider(
                        modifier = Modifier
                            .padding(top = 5.dp, bottom = 5.dp)
                            .fillMaxWidth()
                    )
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                    ) {
                        Text(
                            text = "Basic information",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 23.sp
                        )
                        Text(
                            text = "This information can at times be visible to other participants that you associate with.",
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            softWrap = true
                        )
                    }
                }

                items(listOfInformations.size) { index ->
                    val listValue = listOfInformations[index]
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        ProfileInformationHolders(
                            title = listValue.title,
                            inValue = listValue.value,
                            onClickListener = {
                                listValue.onClick()
                                showDialogProfile = true
                                titleHolder = listValue.title
                                initialValue = listValue.value
                            }
                        )
                    }
                }

                item {
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp, bottom = 5.dp)
                    ) {
                        Text(
                            text = "Medical information",
                            fontWeight = FontWeight.ExtraBold,
                            fontSize = 23.sp
                        )
                        Text(
                            text = "This information is private and only shared under patient and medical personal confidentiality.",
                            fontWeight = FontWeight.Light,
                            fontSize = 12.sp,
                            lineHeight = 12.sp,
                            softWrap = true
                        )
                    }
                }

                items(listOfMedicalInformation.size) { index ->
                    val listValue = listOfMedicalInformation[index]
                    Column(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(start = 10.dp, end = 10.dp)
                    ) {
                        ProfileInformationHolders(
                            title = listValue.title,
                            inValue = listValue.value,
                            onClickListener = {
                                listValue.onClick()
                                showDialogProfile = true
                                titleHolder = listValue.title
                                initialValue = listValue.value
                            }
                        )
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

        if (showDialogProfile) {
            UpdateProfileValue(
                title = titleHolder,
                initialValue = initialValue,
                closeSelection = {
                    showDialogProfile = false
                }
            ) { value ->
                baseLocal.updateFirebaseProfile(
                    accountType = userAccountInfor!!.accountType,
                    key = databaseKey,
                    value = value,
                ) {
                    showDialogProfile = false
                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
internal fun UpdateProfileValue(
    title: String?,
    initialValue: String?,
    closeSelection: () -> Unit,
    onValueReturn: (String) -> Unit,
) {
    val inputOptions = listOf(
        InputTextField(
            type = InputTextFieldType.OUTLINED,
            text = initialValue,
            header = InputHeader(
                title = title,
                icon = IconSource(Icons.Filled.EmojiPeople)
            ),
            shape = RoundedCornerShape(100), singleLine = true,
            validationListener = { value ->
                if ((value?.length ?: 0) >= 3) ValidationResult.Valid
                else ValidationResult.Invalid("Needs to be at least 3 letters long")
            }
        )
    )

    InputDialog(
        state = rememberUseCaseState(visible = true, onCloseRequest = { closeSelection() }),
        selection = InputSelection(
            input = inputOptions,
            onPositiveClick = { result ->
                onValueReturn(result.getString("0").toString())
            },
        )
    )
}