package od.twins.clabr.data.repository

import kotlinx.coroutines.flow.Flow
import od.twins.clabr.data.local.dao.GameDao
import od.twins.clabr.models.Game
import javax.inject.Inject

class DataBaseRepository @Inject constructor(
    private val gameDao: GameDao
) {

    val allGames: Flow<List<Game>> = gameDao.getAll()

    suspend fun insertGame(game: Game) {
        gameDao.insert(game)
    }

    fun deleteGame(game: Game) {
        gameDao.deleteGame(game)
    }
}