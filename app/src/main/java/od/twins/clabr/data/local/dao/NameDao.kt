package od.twins.clabr.data.local.dao

import androidx.room.*
import kotlinx.coroutines.flow.Flow
import od.twins.clabr.model.Name

@Dao
interface NameDao {
    @Query("SELECT * FROM ${Name.TABLE_NAME}")
    fun getAll(): Flow<List<Name>>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(name: Name)

    @Query("DELETE FROM ${Name.TABLE_NAME}")
    suspend fun deleteAll()

    @Delete
    fun deleteGame(name: Name)
}