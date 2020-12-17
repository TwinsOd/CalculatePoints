package od.twins.clabr.ui.calculate

import android.content.DialogInterface
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import od.twins.clabr.R
import od.twins.clabr.utils.onCreateListDialog


class TwoTwoCalculateFragment : Fragment(), View.OnClickListener {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_two_two_calculate, container, false)
        root.findViewById<ImageView>(R.id.more_1).setOnClickListener(this)
        root.findViewById<ImageView>(R.id.more_2).setOnClickListener(this)
        root.findViewById<ImageView>(R.id.save_points_view).setOnClickListener(this)

        return root
    }

    override fun onClick(p0: View?) {
        when (p0?.id) {
            R.id.more_1 -> {
                onCreateListDialog(
                    p0.context,
                    R.array.game_case_list,
                    DialogInterface.OnClickListener { p0, p1 ->
                        Toast.makeText(context, "Click $p1", Toast.LENGTH_LONG).show()
                    })?.show()
            }
            R.id.more_2 -> {
                onCreateListDialog(
                    p0.context,
                    R.array.game_case_list,
                    DialogInterface.OnClickListener { p0, p1 ->
                        Toast.makeText(context, "Click $p1", Toast.LENGTH_LONG).show()
                    })?.show()
            }
            R.id.save_points_view -> {
                Toast.makeText(p0.context, "In development", Toast.LENGTH_LONG).show()
            }
        }
    }
}
