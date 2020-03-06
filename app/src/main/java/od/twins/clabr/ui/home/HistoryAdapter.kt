package od.twins.clabr.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*
import od.twins.clabr.R
import od.twins.clabr.data.models.SetGameModel

class HistoryAdapter(var games: ArrayList<SetGameModel>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    fun updateUsers(newGames: List<SetGameModel>) {
        games.clear()
        games.addAll(newGames)
        notifyDataSetChanged()
    }

    fun addNewGame(gameModel: SetGameModel) {
        games.add(0, gameModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = HistoryHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    )

    override fun getItemCount() = games.size
    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(games[position])
    }


    class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val startTimeView = view.start_time_view

        fun bind(gameModel: SetGameModel) {
            startTimeView.text = "L" + gameModel.timeStart
        }
    }
}