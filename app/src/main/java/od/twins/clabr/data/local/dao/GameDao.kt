package od.twins.clabr.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow
import od.twins.clabr.models.Game

@Dao
interface GameDao {
    @Query("SELECT * FROM ${Game.TABLE_NAME}")
    fun getAll(): Flow<List<Game>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(assessorDB: Game)

    @Query("DELETE FROM ${Game.TABLE_NAME}")
    suspend fun deleteAll()
}