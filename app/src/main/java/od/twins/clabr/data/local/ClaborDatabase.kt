package od.twins.clabr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import od.twins.clabr.data.local.dao.GameDao
import od.twins.clabr.models.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
abstract class ClaborDatabase : RoomDatabase() {

    abstract fun getGameDao(): GameDao
}