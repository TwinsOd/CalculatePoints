package od.twins.clabr.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import od.twins.clabr.model.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM ${Game.TABLE_NAME}")
    fun getAll(): Flow<List<Game>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(game: Game)

    @Query("DELETE FROM ${Game.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete
    fun deleteGame(game: Game)
}