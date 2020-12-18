package od.twins.clabr.ui.home

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import od.twins.clabr.models.GameSetModel

class HomeViewModel @ViewModelInject constructor() : ViewModel() {
    val gameSetList = MutableLiveData<List<GameSetModel>>()

    fun getHistoryList() {
        val list = listOf(
            GameSetModel(timeStart = 515151515),
            GameSetModel(timeStart = 77777),
            GameSetModel(timeStart = 99999)
        )
        gameSetList.value = list
    }
}