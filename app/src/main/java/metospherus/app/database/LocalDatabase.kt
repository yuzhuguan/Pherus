package metospherus.app.database

import android.Manifest
import android.annotation.SuppressLint
import android.content.Context
import android.content.pm.PackageManager
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.net.ConnectivityManager
import android.os.Bundle
import androidx.annotation.Keep
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.HelpCenter
import androidx.compose.material.icons.rounded.Logout
import androidx.compose.material.icons.rounded.PermMedia
import androidx.compose.material.icons.rounded.Settings
import androidx.compose.material.icons.rounded.Stream
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.core.app.ActivityCompat
import androidx.navigation.NavHostController
import java.text.SimpleDateFormat
import java.util.Locale

object LocalDatabase {
    @Keep
    data class CurrentUserDatabase(
        val accountInformation: AccountInformation = AccountInformation(),
        val accountMedicalInformation: AccountMedicalInformation = AccountMedicalInformation(),
        val systemReservedInformation: SystemReservedInformation = SystemReservedInformation(),
        val accountPatientModules: AccountPatientModules = AccountPatientModules(),
        val emergencyContacts: EmergencyContacts = EmergencyContacts(),
    )

    @Keep
    data class EmergencyContacts(
        val policeNumber: String? = "911",
    )

    @Keep
    data class AccountPatientModules(
        var key: String? = null,
        val name: String? = null,
        val icon: String? = null,
        val description: String? = null,
        val enabled: Boolean? = false,
    ) {
        constructor() : this(
            key = null,
            name = null,
            icon = null,
            description = null,
            enabled = false
        )
    }

    @Keep
    data class AccountMedicalInformation(
        val bloodType: String? = "Unknown",
        val allergies: String? = "Unknown",
        val medications: String? = "Unknown",
        val medicalNotes: String? = "Unknown",
        val organDonor: String? = "Unknown",
        val hivStatus: String? = "Unknown",
    )

    @Keep
    data class AccountInformation(
        val accountEmail: String? = null,
        val accountAvatar: String? = null,
        val accountName: String? = null,
        val accountPhoneNumber: String? = null,
        val accountType: String? = null,
        val accountLocation: String? = "Unknown location",
    )

    @Keep
    data class SystemReservedInformation(
        val dateOfRegistration: String? = null,
        val accountUserUid: String? = null,
    )

    @Keep
    data class CrowdFundingInformation(
        val name: String? = null,
        val goal: String? = null,
        val banner: String? = null,
        val description: String? = null,
        val verified: Verified = Verified(),
    )

    @Keep
    data class Verified(
        val accepted: Boolean? = false,
        val accesskey: Long? = 0,
    )

    @Keep
    data class SystemSettings(
        val title: String? = null,
        val description: String? = null,
        val enabled: Boolean,
    )

    @Keep
    data class DrawerDatabase(
        val title: String? = null,
        val description: String? = null,
        val icon: ImageVector? = null,
        val onClick: (NavHostController, FirebaseDatabaseLocal) -> Unit,
    )

    @Keep
    var drawerItems = listOf(
        DrawerDatabase(
            title = "General Information",
            description = "Pherus Medical Center",
            icon = Icons.Rounded.Favorite
        ) { navController, baseAuth ->
            if (baseAuth.currentUserUid != null) {
                navController.navigate("profile")
            } else {
                navController.navigate("login")
            }
        },
        DrawerDatabase(
            title = "News and Events",
            description = "Pherus Medical Center",
            icon = Icons.Rounded.Stream
        ) { navController, _ ->
            navController.navigate("contents")
        },
        DrawerDatabase(
            title = "Research and Studies",
            description = "Pherus Medical Center",
            icon = Icons.Rounded.PermMedia
        ) { navController, _ ->
            navController.navigate("research")
        },
        DrawerDatabase(
            title = "Payments & Subscriptions",
            description = "Pherus Medical Center",
            icon = Icons.Rounded.AccountCircle
        ) { navController, baseAuth ->
            if (baseAuth.currentUserUid != null) {
                navController.navigate("subscriptions")
            }
        },
        DrawerDatabase(
            title = "Donations and Revenue",
            description = "Pherus Medical Center",
            icon = Icons.Rounded.AccountCircle
        ) { navController, baseAuth ->

        },
        DrawerDatabase(
            title = "Settings",
            description = "Pherus Medical Center",
            icon = Icons.Rounded.Settings
        ) { navController, _ ->
            navController.navigate("settings")
        },
        DrawerDatabase(
            title = "Help & Feedback",
            description = "Pherus Medical Center",
            icon = Icons.Rounded.HelpCenter
        ) { navController, baseAuth ->

        },
        DrawerDatabase(
            title = "Logout",
            description = "Pherus Medical Center",
            icon = Icons.Rounded.Logout
        ) { navController, baseAuth ->
            if (baseAuth.currentUserUid != null) {
                //baseAuth.logout()
            }
        },
    )

    @Keep
    data class ListOfResources(
        val title: String,
        val value: String,
        val onClick: () -> Unit,
    )

    @Keep
    data class CategoryService(
        var id: Long = 0,
        val name: String,
        val img: String,
        val count: Int,
    ) {
        constructor() : this(0, "", "", 0)
    }

    @SuppressLint("SimpleDateFormat")
    fun getCurrentDate(): String? {
        val startTimestamp = System.currentTimeMillis() / 1000
        val dateFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        return dateFormat.format(startTimestamp * 1000)
    }

    fun isNetworkConnected(context: Context): Boolean {
        val connectivityManager =
            context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        val activeNetworkInfo = connectivityManager.activeNetworkInfo
        return activeNetworkInfo != null && activeNetworkInfo.isConnected
    }

    @Keep
    data class LocationData(val latitude: Double, val longitude: Double)
}