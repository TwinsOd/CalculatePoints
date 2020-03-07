package od.twins.clabr.ui.newGame

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import od.twins.clabr.R

class NewGameFragment : Fragment() {

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

        return root
    }
}
