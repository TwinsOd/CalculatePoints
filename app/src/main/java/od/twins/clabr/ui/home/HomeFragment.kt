package od.twins.clabr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import od.twins.clabr.R

class HomeFragment : Fragment() {
    private val homeViewModel: HomeViewModel by viewModels()
    private val historyAdapter: HistoryAdapter = HistoryAdapter(arrayListOf())

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        create_view.setOnClickListener { v ->
            Navigation.findNavController(v).navigate(R.id.action_navigation_home_to_newGameFragment)
        }

        history_list_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

//        homeViewModel = ViewModelProvider(this).get(HomeViewModel::class.java)
        homeViewModel.gameSetList.observe(viewLifecycleOwner, Observer {
            historyAdapter.updateUsers(it)
        })
        homeViewModel.getHistoryList()
    }
}
