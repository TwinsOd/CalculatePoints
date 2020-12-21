package od.twins.clabr.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import od.twins.clabr.data.repository.DataBaseRepository
import od.twins.clabr.model.Game

class HomeViewModel @ViewModelInject constructor(
    dataBaseRepository: DataBaseRepository
) : ViewModel() {
    val gameList: LiveData<List<Game>> = dataBaseRepository.allGames.asLiveData()

}