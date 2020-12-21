package od.twins.clabr.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import od.twins.clabr.data.local.converter.CountModelConverter
import od.twins.clabr.data.local.converter.PlayerConverter
import od.twins.clabr.data.local.dao.GameDao
import od.twins.clabr.data.local.dao.NameDao
import od.twins.clabr.model.Game
import od.twins.clabr.model.Name

@Database(entities = [Game::class, Name::class], version = 1, exportSchema = false)
@TypeConverters(CountModelConverter::class, PlayerConverter::class)
abstract class ClaborDatabase : RoomDatabase() {

    abstract fun getGameDao(): GameDao
    abstract fun getNameDao(): NameDao
}