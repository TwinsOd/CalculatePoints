package od.twins.clabr.ui.names

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_names.*
import od.twins.clabr.AppConstants
import od.twins.clabr.R
import od.twins.clabr.data.models.GameSetting
import od.twins.clabr.model.GameMode
import od.twins.clabr.ui.startSettings.ARG_SETTINGS
import timber.log.Timber

@AndroidEntryPoint
class NamesFragment : Fragment() {
    private lateinit var gameSetting: GameSetting
    private val nameViewModel: NamesViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val json = it.get(ARG_SETTINGS) as String
            val moshi = Moshi.Builder().build()
            val jsonAdapter: JsonAdapter<GameSetting> = moshi.adapter(GameSetting::class.java)
            gameSetting = jsonAdapter.fromJson(json) ?: GameSetting(
                GameMode.TWO_ON_TWO,
                AppConstants.LIMIT_1001,
                AppConstants.PENALTY_OF_BEITS_100,
                AppConstants.LENGTH_OF_BEITS_3
            )
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_names, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_view.setOnClickListener { activity?.onBackPressed() }

        var nameList: List<String> = emptyList()
        nameViewModel.nameList.observe(viewLifecycleOwner) {
            nameList = it.map { item -> item.value }
            val adapter = ArrayAdapter(view.context, android.R.layout.simple_list_item_1, nameList)
            player_1_auto.setAdapter(adapter)
            player_2_auto.setAdapter(adapter)

            when (gameSetting.gameMode) {
                GameMode.TWO_ON_TWO -> {
                    initAutoComplete3Players(adapter)
                    initAutoComplete4Players(adapter)
                }
                GameMode.TWO -> {

                }
                GameMode.THREE -> {
                    initAutoComplete3Players(adapter)
                }
            }
        }

        next_view.setOnClickListener { v ->
            when (gameSetting.gameMode) {
                GameMode.TWO, GameMode.THREE ->
                    Toast.makeText(view.context, "In development", Toast.LENGTH_LONG).show()
                GameMode.TWO_ON_TWO -> {
                    if (nameViewModel.checkFill(
                            player_1_auto.text.toString(), player_2_auto.text.toString(),
                            player_3_auto.text.toString(), player_4_auto.text.toString()
                        )
                    ) {
                        nameViewModel.saveNames(
                            nameList, listOf(
                                player_1_auto.text.toString(), player_2_auto.text.toString(),
                                player_3_auto.text.toString(), player_4_auto.text.toString()
                            )
                        )
                        Navigation.findNavController(v)
                            .navigate(R.id.action_namesFragment_to_twoTwoCalculateFragment)
                    } else {
                        Toast.makeText(view.context, "Заполните имена", Toast.LENGTH_LONG).show()
                    }
                }
            }

            Timber.i("text %s", player_1_auto.text)
        }
    }

    private fun initAutoComplete3Players(adapter: ArrayAdapter<String>) {
        title_3.visibility = View.VISIBLE
        player_3_auto.visibility = View.VISIBLE
        player_3_auto.setAdapter(adapter)
    }

    private fun initAutoComplete4Players(adapter: ArrayAdapter<String>) {
        title_4.visibility = View.VISIBLE
        player_4_auto.visibility = View.VISIBLE
        player_4_auto.setAdapter(adapter)
    }
}
