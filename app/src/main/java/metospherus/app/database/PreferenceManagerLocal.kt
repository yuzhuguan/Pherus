package metospherus.app.database

import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

object PreferenceManagerLocal {
    private const val PREFS_NAME = "localStorage"
    lateinit var sharedPreferences: SharedPreferences
    val liveDataMap = mutableMapOf<String, MutableLiveData<*>>()

    @JvmStatic
    fun init(context: Context) {
        sharedPreferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)
    }

    @JvmStatic
    inline fun <reified T> getLiveData(key: String, defaultValue: T): LiveData<T> {
        if (!liveDataMap.containsKey(key)) {
            val liveData = MutableLiveData<T>()
            liveData.value = get(key, defaultValue)
            liveDataMap[key] = liveData
        }
        return liveDataMap[key] as LiveData<T>
    }

    @JvmStatic
    inline fun <reified T> set(key: String, value: T) {
        sharedPreferences.edit { putValue(key, value) }
        (liveDataMap[key] as? MutableLiveData<T>)?.value = value
    }

    @JvmStatic
    inline fun <reified T> get(key: String, defaultValue: T): T {
        return sharedPreferences.getValue(key, defaultValue)
    }

    @JvmStatic
    inline fun <reified T> SharedPreferences.getValue(
        key: String,
        defaultValue: T
    ): T = when (T::class) {
        String::class -> getString(key, defaultValue as String) as T
        Int::class -> getInt(key, defaultValue as Int) as T
        Long::class -> getLong(key, defaultValue as Long) as T
        Float::class -> getFloat(key, defaultValue as Float) as T
        Boolean::class -> getBoolean(key, defaultValue as Boolean) as T
        else -> throw IllegalArgumentException("Unsupported type")
    }

    @JvmStatic
    inline fun <reified T> SharedPreferences.Editor.putValue(
        key: String,
        value: T
    ) {
        when (value) {
            is String -> putString(key, value)
            is Int -> putInt(key, value)
            is Long -> putLong(key, value)
            is Float -> putFloat(key, value)
            is Boolean -> putBoolean(key, value)
            else -> throw IllegalArgumentException("Unsupported type")
        }
    }
}