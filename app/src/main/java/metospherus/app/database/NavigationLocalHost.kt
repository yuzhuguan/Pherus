package metospherus.app.database

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import metospherus.app.categories.DocumentsComponent
import metospherus.app.categories.HospitalsComponent
import metospherus.app.categories.InterprofessionalsComponent
import metospherus.app.categories.LaboratoryComponent
import metospherus.app.categories.PatientsComponent
import metospherus.app.categories.PharmacyComponent
import metospherus.app.modules.EmergencyModule
import metospherus.app.modules.GeneralHealthModule
import metospherus.app.modules.MedicationModule
import metospherus.app.modules.PeriodsTrackerModule
import metospherus.app.overview.ContentContainer
import metospherus.app.overview.DoctorsContainer
import metospherus.app.overview.HomeContainer
import metospherus.app.overview.HospitalContainer
import metospherus.app.overview.LoginContainer
import metospherus.app.overview.PaymentsContainer
import metospherus.app.overview.PharmacyContainer
import metospherus.app.overview.ProfileContainer
import metospherus.app.overview.ResearchContainer
import metospherus.app.overview.SettingsContainer

@Composable
fun NavigationLocalHost(navController: NavHostController, baseLocal: FirebaseDatabaseLocal) {
    val currentContent =  LocalContext.current
    PreferenceManagerLocal.init(currentContent)
    val navControllerHost= remember { mutableStateOf(navController) }
    val baseLocalHost = remember { mutableStateOf(baseLocal) }
    val accountType = remember { mutableStateOf(PreferenceManagerLocal.getLiveData("currentUserAccountType", "home").value.toString()) }

    Scaffold(
        modifier = Modifier.fillMaxSize()
    ) {
        NavHost(
            modifier = Modifier
                .padding(paddingValues = it)
                .fillMaxSize(),
            navController = navControllerHost.value,
            startDestination = accountType.value
        ) {
            composable(route = "home") {
                HomeContainer(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value
                )
            }
            composable(route = "login") {
                LoginContainer(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value
                )
            }

            composable(route = "settings") {
                SettingsContainer()
            }

            composable(route = "doctors") {
                DoctorsContainer()
            }

            composable(route = "pharmacy") {
                PharmacyContainer()
            }

            composable(route = "hospital") {
                HospitalContainer(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "delivery") {

            }

            composable(route = "profile") {
                ProfileContainer(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "documents") {
                DocumentsComponent(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "interprofessional") {
                InterprofessionalsComponent(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "pharmacy") {
                PharmacyComponent(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "contents") {
                ContentContainer(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "subscriptions") {
                PaymentsContainer(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "research") {
                ResearchContainer(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "patients") {
                PatientsComponent(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "hospitals") {
                HospitalsComponent(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "laboratory") {
                LaboratoryComponent(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "general") {
                GeneralHealthModule(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "sos") {
                EmergencyModule(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "medication") {
                MedicationModule(
                    navController = navControllerHost.value,
                    baseLocal = baseLocalHost.value,
                    accountType = accountType.value
                )
            }

            composable(route = "period") {
                PeriodsTrackerModule()
            }
        }
    }
}