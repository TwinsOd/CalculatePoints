package od.twins.clabr.ui.names

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import od.twins.clabr.data.repository.DataBaseRepository
import od.twins.clabr.model.Name

class NamesViewModel @ViewModelInject constructor(
    private val dataBaseRepository: DataBaseRepository
) : ViewModel() {
    val nameList: LiveData<List<Name>> = dataBaseRepository.allNames.asLiveData()


    private fun insertName(name: String) {
        viewModelScope.launch {
            dataBaseRepository.insertName(Name(name))
        }
    }

    fun checkFill(text1: String, text2: String, text3: String, text4: String): Boolean {
        if (!isNameValid(text1))
            return false
        if (!isNameValid(text2))
            return false
        if (!isNameValid(text3))
            return false
        if (!isNameValid(text4))
            return false
        return true
    }

    private fun isNameValid(name: String): Boolean {
        return name.isNotBlank() && name.length > 1
    }

    fun saveNames(list: List<String>, listOf: List<String>) {
        listOf.map { name ->
            if (!list.contains(name)) {
                insertName(name)
            }
        }
    }
}