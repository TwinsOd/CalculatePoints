package od.twins.clabr.ui.startSettings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_start_settings.*
import od.twins.clabr.AppConstants.Companion.LENGTH_OF_BEITS_1
import od.twins.clabr.AppConstants.Companion.LENGTH_OF_BEITS_3
import od.twins.clabr.AppConstants.Companion.LENGTH_OF_BEITS_5
import od.twins.clabr.AppConstants.Companion.LIMIT_1001
import od.twins.clabr.AppConstants.Companion.LIMIT_501
import od.twins.clabr.AppConstants.Companion.PENALTY_OF_BEITS_100
import od.twins.clabr.AppConstants.Companion.PENALTY_OF_BEITS_200
import od.twins.clabr.AppConstants.Companion.PENALTY_OF_BEITS_300
import od.twins.clabr.AppConstants.Companion.PENALTY_OF_BEITS_50
import od.twins.clabr.R
import od.twins.clabr.data.models.GameSetting
import od.twins.clabr.model.GameMode
import od.twins.clabr.utils.PrefUtil

const val ARG_SETTINGS = "arg_settings"

@AndroidEntryPoint
class StartSettingsGameFragment : Fragment() {
    private lateinit var gameSetting: GameSetting
    private val startSettingsViewModel: StartSettingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context?.let {
            gameSetting = PrefUtil.getGameSettings(it)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_start_settings, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        updateUI()

        next_view.setOnClickListener { v ->
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<GameSetting> = moshi.adapter(GameSetting::class.java)
            val json = jsonAdapter.toJson(gameSetting)
            PrefUtil.setGameSettings(json, v.context)

            val bundle = Bundle()
            bundle.putSerializable(ARG_SETTINGS, json)
            Navigation.findNavController(v)
                .navigate(R.id.action_newGameFragment_to_namesFragment, bundle)
        }
        type_game_toggle_button.addOnButtonCheckedListener { _, checkedId, isChecked ->
            if (isChecked)
                run {
                    when (checkedId) {
                        R.id.two_button -> gameSetting.gameMode = GameMode.TWO
                        R.id.three_button -> gameSetting.gameMode = GameMode.THREE
//                        R.id.four_button -> gameSetting.gameType = GameType.FOUR
                        R.id.pair_button -> gameSetting.gameMode = GameMode.TWO_ON_TWO
                    }
                }
        }
        point_limit_toggle_button.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked)
                run {
                    when (checkedId) {
                        R.id.small_game -> gameSetting.pointLimit = LIMIT_501
                        R.id.big_game -> gameSetting.pointLimit = LIMIT_1001
                    }
                }
        }
    }

    private fun updateUI() {
        when (gameSetting.gameMode) {
            GameMode.TWO_ON_TWO -> type_game_toggle_button.check(R.id.pair_button)
            GameMode.TWO -> type_game_toggle_button.check(R.id.two_button)
            GameMode.THREE -> type_game_toggle_button.check(R.id.three_button)
        }

        when (gameSetting.pointLimit) {
            LIMIT_501 -> point_limit_toggle_button.check(R.id.small_game)
            LIMIT_1001 -> point_limit_toggle_button.check(R.id.big_game)
        }

        when (gameSetting.penaltyForSeriousOfBeits) {
            PENALTY_OF_BEITS_50 -> penalty_for_beits.check(R.id.pints_50)
            PENALTY_OF_BEITS_100 -> penalty_for_beits.check(R.id.pints_100)
            PENALTY_OF_BEITS_200 -> penalty_for_beits.check(R.id.pints_200)
            PENALTY_OF_BEITS_300 -> penalty_for_beits.check(R.id.pints_300)
        }

        when (gameSetting.length_of_series_of_beits) {
            LENGTH_OF_BEITS_1 -> length_of_series_of_beits.check(R.id.length_of_series_of_beits_1)
            LENGTH_OF_BEITS_3 -> length_of_series_of_beits.check(R.id.length_of_series_of_beits_3)
            LENGTH_OF_BEITS_5 -> length_of_series_of_beits.check(R.id.length_of_series_of_beits_5)
        }
    }
}
