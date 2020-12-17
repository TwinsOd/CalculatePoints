package od.twins.clabr.ui.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_user.view.*
import od.twins.clabr.R
import od.twins.clabr.models.GameSetModel

class HistoryAdapter(var gameSets: ArrayList<GameSetModel>) :
    RecyclerView.Adapter<HistoryAdapter.HistoryHolder>() {

    fun updateUsers(newGameSets: List<GameSetModel>) {
        gameSets.clear()
        gameSets.addAll(newGameSets)
        notifyDataSetChanged()
    }

    fun addNewGame(gameSetModel: GameSetModel) {
        gameSets.add(0, gameSetModel)
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, p1: Int) = HistoryHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false)
    )

    override fun getItemCount() = gameSets.size
    override fun onBindViewHolder(holder: HistoryHolder, position: Int) {
        holder.bind(gameSets[position])
    }


    class HistoryHolder(view: View) : RecyclerView.ViewHolder(view) {
        private val startTimeView = view.start_time_view

        fun bind(gameSetModel: GameSetModel) {
            startTimeView.text = "L" + gameSetModel.timeStart
        }
    }
}