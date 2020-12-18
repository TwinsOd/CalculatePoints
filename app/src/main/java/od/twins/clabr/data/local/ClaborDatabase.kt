package od.twins.clabr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import od.twins.clabr.data.local.converter.CountModelConverter
import od.twins.clabr.data.local.converter.PlayerConverter
import od.twins.clabr.data.local.dao.GameDao
import od.twins.clabr.models.Game

@Database(entities = [Game::class], version = 1, exportSchema = false)
@TypeConverters(CountModelConverter::class, PlayerConverter::class)
abstract class ClaborDatabase : RoomDatabase() {

    abstract fun getGameDao(): GameDao
}