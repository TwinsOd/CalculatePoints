package od.twins.clabr.ui.newGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.google.android.material.button.MaterialButtonToggleGroup
import od.twins.clabr.R
import od.twins.clabr.data.models.GameType
import od.twins.clabr.data.models.LimitPoints

class NewGameFragment : Fragment() {
    val TAG = "NewGameFragment"
    lateinit var gameType: GameType
    lateinit var pointLimit: LimitPoints

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root: View = inflater.inflate(R.layout.fragment_new_game, container, false)
        val nextView: Button = root.findViewById(R.id.next_view)
        nextView.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.action_newGameFragment_to_namesFragment)
        }

        val typeGameView: MaterialButtonToggleGroup =
            root.findViewById(R.id.type_game_toggle_button)
        typeGameView.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked)
                run {
                    when (checkedId) {
                        R.id.two_button -> gameType = GameType.TWO
                        R.id.three_button -> gameType = GameType.THREE
                        R.id.four_button -> gameType = GameType.FOUR
                        R.id.pair_button -> gameType = GameType.TWO_ON_TWO
                    }
                }
        }
        val pointLimitView: MaterialButtonToggleGroup =
            root.findViewById(R.id.point_limit_toggle_button)
        pointLimitView.addOnButtonCheckedListener { group, checkedId, isChecked ->
            if (isChecked)
                run {
                    when (checkedId) {
                        R.id.small_game -> pointLimit = LimitPoints.SMALL
                        R.id.big_game -> pointLimit = LimitPoints.BIG
                    }
                }
        }
        return root
    }
}
