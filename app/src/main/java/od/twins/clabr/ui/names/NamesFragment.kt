package od.twins.clabr.ui.names

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textfield.TextInputLayout
import od.twins.clabr.R
import od.twins.clabr.data.models.GameType
import od.twins.clabr.data.models.LimitPoints
import od.twins.clabr.ui.names.dummy.DummyContent
import od.twins.clabr.ui.names.dummy.DummyContent.DummyItem
import od.twins.clabr.ui.newGame.ARG_GAME_TYPE
import od.twins.clabr.ui.newGame.ARG_POINT_LIMIT


private const val TAG = "NamesFragment"

class NamesFragment : Fragment() {
    private var listener: OnListFragmentInteractionListener? = null
    private var gameType: GameType? = null
    private var pointLimit: LimitPoints? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameType = it.get(ARG_GAME_TYPE) as GameType
            pointLimit = it.get(ARG_POINT_LIMIT) as LimitPoints

            Log.i(TAG, "onCreate gameType=$gameType");
            Log.i(TAG, "onCreate pointLimit=$pointLimit");
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_names, container, false)

        val nameList: RecyclerView = root.findViewById(R.id.names_list)
        nameList.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NameRecyclerViewAdapter(DummyContent.ITEMS, listener)
        }

        val nextView: FloatingActionButton = root.findViewById(R.id.next_view)
        nextView.setOnClickListener { v ->
            when (gameType) {
                GameType.TWO, GameType.THREE, GameType.FOUR ->
                    Toast.makeText(root.context, "In development", Toast.LENGTH_LONG).show()
                GameType.TWO_ON_TWO -> {
                    Navigation.findNavController(v)
                        .navigate(R.id.action_namesFragment_to_twoTwoCalculateFragment)
                }
            }
        }

        val player1Layout: TextInputLayout = root.findViewById(R.id.player_1)
        val player2Layout: TextInputLayout = root.findViewById(R.id.player_2)
        val player3Layout: TextInputLayout = root.findViewById(R.id.player_3)
        val player4Layout: TextInputLayout = root.findViewById(R.id.player_4)
//        val player1editText = TextInputEditText(player1Layout.context);

        Log.i(TAG, "onCreateView gameType=$gameType");

        when (gameType) {
            GameType.TWO -> {
                player3Layout.visibility = GONE
                player4Layout.visibility = GONE
            }
            GameType.THREE -> player4Layout.visibility = GONE
            GameType.TWO_ON_TWO -> {
                //выделить по групам
//                player1Layout.boxStrokeColor = resources.getColor(R.color.light_grey)
//                player2Layout.boxStrokeColor = resources.getColor(R.color.blue_grey)
//                player3Layout.boxStrokeColor = resources.getColor(R.color.light_grey)
//                player4Layout.boxStrokeColor = resources.getColor(R.color.blue_grey)
            }
        }

        return root
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }
}
