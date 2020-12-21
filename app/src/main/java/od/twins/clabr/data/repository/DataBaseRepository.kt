package od.twins.clabr.data.repository

import kotlinx.coroutines.flow.Flow
import od.twins.clabr.data.local.dao.GameDao
import od.twins.clabr.data.local.dao.NameDao
import od.twins.clabr.model.Game
import od.twins.clabr.model.Name
import javax.inject.Inject

class DataBaseRepository @Inject constructor(
    private val gameDao: GameDao,
    private val namesDao: NameDao,
) {

    val allGames: Flow<List<Game>> = gameDao.getAll()

    suspend fun insertGame(game: Game) {
        gameDao.insert(game)
    }

    fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }

    val allNames: Flow<List<Name>> = namesDao.getAll()

    suspend fun insertName(name: Name) {
        namesDao.insert(name)
    }
}