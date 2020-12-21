package od.twins.clabr.utils

import android.content.Context
import androidx.preference.PreferenceManager
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import od.twins.clabr.AppConstants.Companion.LENGTH_OF_BEITS_3
import od.twins.clabr.AppConstants.Companion.LIMIT_1001
import od.twins.clabr.AppConstants.Companion.PENALTY_OF_BEITS_100
import od.twins.clabr.data.models.GameSetting
import od.twins.clabr.model.GameMode


class PrefUtil {
    companion object {

        private const val GAME_SETTINGS = "com.clabor.game_settings"

        fun getGameSettings(context: Context): GameSetting {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            val json = preferences.getString(GAME_SETTINGS, null)
            if (json.isNullOrEmpty()) {
                return GameSetting(
                    GameMode.TWO_ON_TWO,
                    LIMIT_1001,
                    PENALTY_OF_BEITS_100,
                    LENGTH_OF_BEITS_3
                )
            }

            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<GameSetting> = moshi.adapter(GameSetting::class.java)
            return jsonAdapter.fromJson(json) ?: GameSetting(
                GameMode.TWO_ON_TWO,
                LIMIT_1001,
                PENALTY_OF_BEITS_100,
                LENGTH_OF_BEITS_3
            )
        }

        fun setGameSettings(json: String, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putString(GAME_SETTINGS, json)
            editor.apply()
        }


        private const val DARK_MODE = "com.clabor.dark_mode"

        fun getDarkMode(context: Context): Boolean {
            val preferences = PreferenceManager.getDefaultSharedPreferences(context)
            return preferences.getBoolean(DARK_MODE, true)
        }

        fun setDarkMode(isDarkMode: Boolean, context: Context) {
            val editor = PreferenceManager.getDefaultSharedPreferences(context).edit()
            editor.putBoolean(DARK_MODE, isDarkMode)
            editor.apply()
        }
    }
}