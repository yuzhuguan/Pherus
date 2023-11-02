package metospherus.app.database

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.webkit.MimeTypeMap
import androidx.activity.compose.ManagedActivityResultLauncher
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.rememberCoroutineScope
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.android.gms.auth.api.signin.GoogleSignIn
import com.google.android.gms.common.api.ApiException
import com.google.errorprone.annotations.Keep
import com.google.firebase.auth.AuthResult
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.GoogleAuthProvider
import com.google.firebase.database.DataSnapshot
import com.google.firebase.database.DatabaseError
import com.google.firebase.database.FirebaseDatabase
import com.google.firebase.database.ValueEventListener
import com.google.firebase.storage.FirebaseStorage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import kotlinx.coroutines.withContext
import metospherus.app.database.LocalDatabase.AccountPatientModules
import metospherus.app.database.LocalDatabase.CategoryService
import metospherus.app.database.LocalDatabase.CrowdFundingInformation
import metospherus.app.database.LocalDatabase.CurrentUserDatabase
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class FirebaseDatabaseLocal @Inject constructor(
    private val auth: FirebaseAuth? = null,
    private val store: FirebaseDatabase? = null,
    private val storage: FirebaseStorage? = null,
) : ViewModel() {
    private val _currentUserData = MutableStateFlow<List<CurrentUserDatabase>>(emptyList())
    val userdb: StateFlow<CurrentUserDatabase?> = _currentUserData
        .map { userDataList ->
            userDataList.firstOrNull()
        }
        .stateIn(viewModelScope, SharingStarted.Eagerly, null)

    private val _currentServicesData = MutableStateFlow<List<CategoryService>>(emptyList())
    val servicedb: StateFlow<List<CategoryService>> = _currentServicesData

    private val _currentModulesData = MutableStateFlow<List<AccountPatientModules>>(emptyList())
    val modulesdb: StateFlow<List<AccountPatientModules>> = _currentModulesData

    private val _enabledModulesData = MutableStateFlow<List<AccountPatientModules>>(emptyList())
    val patientEnabledModulesdb: StateFlow<List<AccountPatientModules>> = _enabledModulesData

    private val _fundingData = MutableStateFlow<List<CrowdFundingInformation>>(emptyList())
    val fundingdb: StateFlow<List<CrowdFundingInformation>> = _fundingData

    val currentUserUid: FirebaseUser?
        get() = auth?.currentUser

    @Composable
    fun rememberFirebaseAuthLauncher(
        onAuthComplete: (AuthResult) -> Unit,
        onAuthError: (ApiException) -> Unit,
    ): ManagedActivityResultLauncher<Intent, ActivityResult> {
        val scope = rememberCoroutineScope()
        return rememberLauncherForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            val task = GoogleSignIn.getSignedInAccountFromIntent(result.data)
            try {
                val account = task.getResult(ApiException::class.java)!!
                val credential = GoogleAuthProvider.getCredential(account.idToken!!, null)
                scope.launch {
                    val authResult = auth?.signInWithCredential(credential)?.await()
                    if (authResult != null) {
                        onAuthComplete(authResult)
                    }
                }
            } catch (e: ApiException) {
                onAuthError(e)
            }
        }
    }

    @Keep
    fun updateFirebaseDatabase(
        path: String?,
        value: CurrentUserDatabase,
        onSuccess: () -> Unit,
    ) {
        store?.getReference(path.toString())?.let {
            it.setValue(value).addOnSuccessListener {
                onSuccess()
            }
        }
    }

    @Keep
    fun updateUserModuleSelection(
        key: String,
        value: Boolean,
    ) {
        currentUserUid?.uid?.let { userUid ->
            store?.getReference("root/patient/$userUid/accountPatientModules/$key/enabled")?.setValue(value)
            refreshLiveData()
        }
    }

    @Keep
    fun updateFirebaseProfile(
        accountType: String?,
        key: String?,
        value: String?,
        onSuccess: () -> Unit,
    ) {
        store?.getReference("root/$accountType/${currentUserUid?.uid}/$key")?.let {
            it.setValue(value).addOnSuccessListener {
                onSuccess()
            }
        }
    }

    @Keep
    fun retrieveFirebaseDatabase(
        path: String?,
        offline: Boolean?,
        onChange: (DataSnapshot) -> Unit,
    ) {
        store?.getReference(path.toString()).let { db ->
            db?.keepSynced(offline!!)
            db?.addValueEventListener(object : ValueEventListener {
                override fun onDataChange(snapshot: DataSnapshot) {
                    onChange(snapshot)
                }

                override fun onCancelled(error: DatabaseError) {}
            })
        }
    }

    fun retrieveLiveUserData(accountType: String, offline: Boolean?) {
        currentUserUid?.uid?.let {
            retrieveFirebaseDatabase(
                path = "root/$accountType/${currentUserUid?.uid}",
                offline = offline
            ) { snapshot ->
                val modulesList = mutableListOf<CurrentUserDatabase>()
                if (snapshot.exists()) {
                    val currentUserData = snapshot.getValue(CurrentUserDatabase::class.java)
                    modulesList.clear()
                    currentUserData?.let {
                        modulesList.add(it)
                    }
                }
                _currentUserData.value = modulesList
            }
        }
    }

    fun retrieveLiveServicesData(offline: Boolean?) {
        retrieveFirebaseDatabase(
            path = "sroot/services",
            offline = offline
        ) { snapshot ->
            val modulesList = mutableListOf<CategoryService>()
            for (snapshots in snapshot.children) {
                val currentService = snapshots.getValue(CategoryService::class.java)
                currentService?.let {
                    modulesList.add(it)
                }
            }
            _currentServicesData.value = modulesList
        }
    }

    fun refreshLiveData() {
        val modulesPatientList = mutableListOf<AccountPatientModules>()
        val modulesPatientEnabledList = mutableListOf<AccountPatientModules>()

        retrieveFirebaseDatabase(
            path = "root/patient/${currentUserUid?.uid}/accountPatientModules",
            offline = true,
        ) { databaseSnapshot ->
            modulesPatientList.clear()
            modulesPatientEnabledList.clear()
            for (snaps in databaseSnapshot.children) {
                val currentPatient = snaps.getValue(AccountPatientModules::class.java)
                currentPatient?.let { valuePatient ->
                    currentPatient.key = snaps.key
                    modulesPatientList.add(valuePatient)
                    if (valuePatient.enabled == true) {
                        modulesPatientEnabledList.add(valuePatient)
                    }
                }
            }
            _enabledModulesData.value = modulesPatientEnabledList
            _currentModulesData.value = modulesPatientList
        }
    }

    fun retrieveLiveModulesData(offline: Boolean?, accountType: String) {
        retrieveFirebaseDatabase(
            path = "sroot/modules",
            offline = offline
        ) { snapshot ->
            val modulesPatientList = mutableListOf<AccountPatientModules>()
            val modulesPatientEnabledList = mutableListOf<AccountPatientModules>()

            for (snapshots in snapshot.children) {
                val currentService = snapshots.getValue(AccountPatientModules::class.java)
                currentService?.let {
                    if (currentUserUid?.uid == null) {
                        modulesPatientList.add(it)
                        if (it.enabled == true) {
                            modulesPatientEnabledList.add(it)
                        }
                    } else {
                        val key = snapshots.key
                        if (accountType.isNotEmpty() && currentUserUid != null && key != null) {
                            retrieveFirebaseDatabase(
                                path = "root/$accountType/${currentUserUid?.uid}/accountPatientModules/$key",
                                offline = true,
                            ) { dataSnapshotPrivate ->
                                if (!dataSnapshotPrivate.exists()) {
                                    dataSnapshotPrivate.ref.setValue(currentService)
                                }
                            }
                        }
                    }
                }
            }

            if (currentUserUid?.uid == null) {
                _enabledModulesData.value = modulesPatientEnabledList
                _currentModulesData.value = modulesPatientList
            }
        }

        retrieveFirebaseDatabase(
            path = "sroot/funding",
            offline = offline
        ) { snapshot ->
            val crowdFunding = mutableListOf<CrowdFundingInformation>()
            for (snapshots in snapshot.children) {
                for (snaps in snapshots.children){
                    val currentService = snaps.getValue(CrowdFundingInformation::class.java)
                    currentService?.let {
                        crowdFunding.add(it)
                    }
                }
            }
            _fundingData.value = crowdFunding
        }
    }

    private fun updateProfile(key: String, value: String) {
        val userUid = currentUserUid?.uid
        if (!userUid.isNullOrEmpty()) {
            store?.getReference(key)?.setValue(value)
        }
    }

    @Composable
    fun UploadImage(
        uri: Uri,
        userUid: String,
        path: String,
        context: Context,
        accountType: String,
        viewModel: FirebaseDatabaseLocal
    ) {
        val fileExtension = getFileExtension(uri, context)
        val filename = "$userUid.$fileExtension"
        val storageRef = storage?.reference?.child("root/$accountType/$userUid/$path/$filename")

        LaunchedEffect(uri, viewModel) {
            try {
                val downloadUri = withContext(Dispatchers.IO) {
                    val uploadTask = storageRef?.putFile(uri)?.await()
                    if (uploadTask != null) {
                        try {
                            val uriResult = storageRef.downloadUrl.await()
                            uriResult.toString()
                        } catch (e: Exception) {
                            null
                        }
                    } else {
                        null
                    }
                }

                // Check if downloadUri is not null or blank before updating the profile
                if (!downloadUri.isNullOrBlank()) {
                    viewModel.updateProfile("root/$accountType/$userUid/accountInformation/accountAvatar", downloadUri)
                }
            } catch (e: IOException) {
                // Handle the exception
            }
        }
    }

    private fun getFileExtension(uri: Uri, context: Context): String {
        val contentResolver = context.contentResolver
        val mimeTypeMap = MimeTypeMap.getSingleton()
        return mimeTypeMap.getExtensionFromMimeType(contentResolver.getType(uri)) ?: "jpg"
    }

    fun logout() {
        auth?.signOut()
    }
}