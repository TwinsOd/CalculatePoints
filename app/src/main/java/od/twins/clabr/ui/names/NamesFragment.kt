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
import kotlinx.android.synthetic.main.fragment_names.*
import od.twins.clabr.R
import od.twins.clabr.models.GameType
import od.twins.clabr.models.LimitPoints
import od.twins.clabr.ui.names.dummy.DummyContent
import od.twins.clabr.ui.names.dummy.DummyContent.DummyItem
import od.twins.clabr.ui.startSettings.ARG_GAME_TYPE
import od.twins.clabr.ui.startSettings.ARG_POINT_LIMIT


private const val TAG = "NamesFragment"

class NamesFragment : Fragment(), View.OnFocusChangeListener {
    private var listener: OnListFragmentInteractionListener? = null
    private var gameType: GameType? = null
    private var pointLimit: LimitPoints? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            gameType = it.get(ARG_GAME_TYPE) as GameType
            pointLimit = it.get(ARG_POINT_LIMIT) as LimitPoints
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

        names_list.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = NameRecyclerViewAdapter(DummyContent.ITEMS, listener)
        }

        next_view.setOnClickListener { v ->
            when (gameType) {
                GameType.TWO, GameType.THREE, GameType.FOUR ->
                    Toast.makeText(view.context, "In development", Toast.LENGTH_LONG).show()
                GameType.TWO_ON_TWO -> {
                    Navigation.findNavController(v)
                        .navigate(R.id.action_namesFragment_to_twoTwoCalculateFragment)
                }
            }
        }

        player_1.editText?.onFocusChangeListener = this
        player_2.editText?.onFocusChangeListener = this
//        val player3Layout: TextInputLayout = root.findViewById(R.id.player_3)
//        val player4Layout: TextInputLayout = root.findViewById(R.id.player_4)
//        val player1editText = TextInputEditText(player1Layout.context);

        Log.i(TAG, "onCreateView gameType=$gameType")

        when (gameType) {
            GameType.TWO -> {
                player_3.visibility = GONE
                player_4.visibility = GONE
            }
            GameType.THREE -> player_4.visibility = GONE
            GameType.TWO_ON_TWO -> {
                //выделить по групам
//                player1Layout.boxStrokeColor = resources.getColor(R.color.light_grey)
//                player2Layout.boxStrokeColor = resources.getColor(R.color.blue_grey)
//                player3Layout.boxStrokeColor = resources.getColor(R.color.light_grey)
//                player4Layout.boxStrokeColor = resources.getColor(R.color.blue_grey)
            }
        }
    }

    interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        fun onListFragmentInteraction(item: DummyItem?)
    }

    override fun onFocusChange(v: View?, hasFocus: Boolean) {
//        Log.i(TAG, "onFocusChange")
//        Toast.makeText(v?.context, "onFocusChange", Toast.LENGTH_LONG).show()
//        when(v?.id){
//            R.id.player_1 -> player1Layout.editText?.setText("Misha")
//            R.id.player_2 -> player1Layout.editText?.setText("YAN")
//        }
        Log.i(TAG, "onFocusChange")
    }
}
