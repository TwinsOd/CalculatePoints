package od.twins.clabr.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*
import od.twins.clabr.R
import od.twins.clabr.model.Game

class HistoryAdapter(var game: ArrayList<Game>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    fun updateUsers(newGameSets: List<Game>) {
        game.clear()
        game.addAll(newGameSets)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = HistoryHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    )

    override fun getItemCount() = game.size

    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(game[position])
    }


    class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val startTimeView = view.start_time_view

        fun bind(game: Game) {
            startTimeView.text = "L" + game.gameId
        }
    }
}