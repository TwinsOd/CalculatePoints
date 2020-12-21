package od.twins.clabr

import android.app.Application
import androidx.appcompat.app.AppCompatDelegate
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import od.twins.clabr.utils.PrefUtil
import timber.log.Timber

@HiltAndroidApp
class ClaborApp : Application() {
    private val applicationScope = CoroutineScope(Dispatchers.Default)

    override fun onCreate() {
        super.onCreate()
        applicationScope.launch {
            Timber.plant(Timber.DebugTree())
        }

        // Get UI mode and set
        val mode = if (PrefUtil.getDarkMode(this)) {
            AppCompatDelegate.MODE_NIGHT_YES
        } else {
            AppCompatDelegate.MODE_NIGHT_NO
        }

        AppCompatDelegate.setDefaultNightMode(mode)
    }
}