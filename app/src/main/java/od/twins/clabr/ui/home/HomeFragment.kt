package od.twins.clabr.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.ExperimentalCoroutinesApi
import od.twins.clabr.R

@ExperimentalCoroutinesApi
@AndroidEntryPoint
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

        rules_layout.setOnClickListener {
            Toast.makeText(
                context,
                getString(R.string.in_development),
                Toast.LENGTH_SHORT
            ).show()
        }

        history_list_view.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = historyAdapter
        }

        homeViewModel.gameList.observe(viewLifecycleOwner) {
            list_is_empty.visibility = if (it.isNullOrEmpty()) View.VISIBLE else View.GONE
            history_list_view.visibility = if (!it.isNullOrEmpty()) View.VISIBLE else View.GONE
            historyAdapter.updateUsers(it)
        }
    }
}
