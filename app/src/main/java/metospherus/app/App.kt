package metospherus.app

import android.app.Application
import com.google.firebase.FirebaseApp
import com.google.firebase.database.ktx.database
import com.google.firebase.installations.ktx.installations
import com.google.firebase.ktx.Firebase
import metospherus.app.database.PreferenceManagerLocal

class App : Application() {
    override fun onCreate() {
        super.onCreate()
        instance = this

        PreferenceManagerLocal.init(context = this)
        val presistenceEnabled = PreferenceManagerLocal.getLiveData("presistenceEnabled", true)

        Firebase.installations
        FirebaseApp.getApps(this)
        FirebaseApp.initializeApp(applicationContext)
        presistenceEnabled.observeForever { boolean ->
            Firebase.database.setPersistenceEnabled(boolean)
        }
    }

    companion object {
        lateinit var instance: App
    }
}