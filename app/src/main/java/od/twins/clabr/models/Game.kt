package od.twins.clabr.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.squareup.moshi.JsonClass
import od.twins.clabr.data.local.converter.CountModelConverter
import od.twins.clabr.data.local.converter.PlayerConverter
import od.twins.clabr.models.Game.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
@JsonClass(generateAdapter = true)
data class Game(
    @PrimaryKey(autoGenerate = true)
    val gameId: Long,
    val points: Int,
    val typeGame: GameType,
    val limit: LimitPoints,
    val timeStart: Long,
    val timeEnd: Long?,
    @TypeConverters(PlayerConverter::class)
    val playerList: List<String>?,
    @TypeConverters(CountModelConverter::class)
    val pointList: List<CountModel>?,
) {
    companion object {
        const val TABLE_NAME = "game_table"
    }
}