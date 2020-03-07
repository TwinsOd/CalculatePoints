package od.twins.clabr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import od.twins.clabr.R
import od.twins.clabr.data.models.SetGameModel

class HomeFragment : Fragment() {
    private lateinit var homeViewModel: HomeViewModel
    private val historyAdapter: HistoryAdapter = HistoryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        homeViewModel = ViewModelProviders.of(this).get(HomeViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_home, container, false)

        val textView: TextView = root.findViewById(R.id.create_view)
        textView.setOnClickListener { _ ->
            Navigation.findNavController(root)
                .navigate(R.id.action_navigation_home_to_newGameFragment)
        }

        val historyListView: RecyclerView = root.findViewById(R.id.history_list_view);
        historyListView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

        homeViewModel.text.observe(viewLifecycleOwner, Observer {
            //            textView.text = it
        })

        historyAdapter.addNewGame(SetGameModel(timeStart = 515151515))
        historyAdapter.addNewGame(SetGameModel(timeStart = 989898989))
        return root
    }
}
