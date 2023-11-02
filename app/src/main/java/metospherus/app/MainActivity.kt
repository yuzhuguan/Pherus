package metospherus.app

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.location.Address
import android.location.Geocoder
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.telephony.TelephonyCallback
import android.telephony.TelephonyManager
import android.telephony.emergency.EmergencyNumber
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.lifecycle.lifecycleScope
import androidx.navigation.compose.rememberNavController
import com.google.android.gms.location.FusedLocationProviderClient
import com.google.android.gms.location.LocationServices
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import metospherus.app.database.FirebaseDatabaseLocal
import metospherus.app.database.NavigationLocalHost
import metospherus.app.database.PermissionHandler
import metospherus.app.database.PreferenceManagerLocal
import metospherus.app.database.emergencyLocal
import metospherus.app.ui.theme.PherusTheme
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import java.util.Locale

class MainActivity : ComponentActivity() {
    private val viewModel = FirebaseDatabaseLocal(
        Firebase.auth,
        Firebase.database,
        Firebase.storage,
    )
    private lateinit var fusedLocationClient: FusedLocationProviderClient

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PherusTheme(
                darkTheme = isSystemInDarkTheme(),
                dynamicColor = true
            ) {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    PreferenceManagerLocal.init(this)

                    PermissionHandler(
                        context = this,
                        onPermissionDenied = {}
                    )
                    LaunchedEffect(Unit, viewModel) {
                        lifecycleScope.launch {
                            delay(50)
                            window.setBackgroundDrawableResource(android.R.color.transparent)
                            runOnUiThread {
                                retrieveUserData(viewModel)
                            }
                        }
                    }
                    val viewModels = remember { mutableStateOf(viewModel) }
                    NavigationLocalHost(
                        navController = navController,
                        baseLocal = viewModels.value
                    )
                }
            }
        }
    }

    override fun onStart() {
        super.onStart()
        runOnUiThread {
            retrieveUserData(viewModel)
        }
    }

    override fun onResume() {
        super.onResume()
        runOnUiThread {
            retrieveUserData(viewModel)
        }
    }

    private fun retrieveUserData(viewModel: FirebaseDatabaseLocal) {
        PreferenceManagerLocal.init(this)
        getUserLocation(this@MainActivity, viewModel)
        val accountType = PreferenceManagerLocal.getLiveData("accountType", "")
        accountType.observeForever { value ->
            if (value.isNullOrEmpty()) {
                return@observeForever
            }
            if (viewModel.currentUserUid != null) {
                viewModel.retrieveLiveUserData(value.lowercase(), true)
                viewModel.refreshLiveData()
            }
        }
        viewModel.retrieveLiveModulesData(
            offline = true,
            accountType = if (accountType.value.toString()
                    .contains("Patient", ignoreCase = true)
            ) "patient" else ""
        )
        viewModel.retrieveLiveServicesData(offline = true)
    }

    @OptIn(DelicateCoroutinesApi::class)
    private fun getUserLocation(context: Context, viewModel: FirebaseDatabaseLocal) {
        fusedLocationClient = LocationServices.getFusedLocationProviderClient(context)
        if (ContextCompat.checkSelfPermission(
                context,
                Manifest.permission.ACCESS_FINE_LOCATION
            ) == PackageManager.PERMISSION_GRANTED
        ) {
            fusedLocationClient.lastLocation.addOnSuccessListener { location ->
                if (location != null) {
                    val latitude = location.latitude
                    val longitude = location.longitude
                    // Use a coroutine to update the user's location in the background
                    GlobalScope.launch(Dispatchers.IO) {
                        val locationName = getHumanReadableLocation(context, latitude, longitude)
                        val accountType = PreferenceManagerLocal.getLiveData("accountType", "")
                        viewModel.updateFirebaseProfile(
                            accountType = accountType.value.toString().lowercase(),
                            key = "accountInformation/accountLocation",
                            value = locationName.first
                        ) {
                            println("Account information added")
                        }

                        viewModel.updateFirebaseProfile(
                            accountType = accountType.value.toString().lowercase(),
                            key = "emergencyContacts/policeNumber",
                            value = locationName.second
                        ) {
                            println("Account information added")
                        }
                    }
                }
            }
        }
    }

    private fun getHumanReadableLocation(
        context: Context,
        latitude: Double,
        longitude: Double,
    ): Pair<String, String> {
        val geocoder = Geocoder(context, Locale.getDefault())
        var locationName = "Unknown Location"
        var emergencyNumberCode = "999"
        try {
            val addresses: MutableList<Address>? = geocoder.getFromLocation(latitude, longitude, 1)
            if (addresses!!.isNotEmpty()) {
                val address = addresses[0]
                val cityName = address.locality
                val countryName = address.countryName
                locationName = "$cityName, $countryName"
                emergencyLocal(
                    country = countryName
                ) { contact ->
                    emergencyNumberCode = contact
                }
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return Pair(locationName, emergencyNumberCode)
    }
}
