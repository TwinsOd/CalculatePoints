package od.twins.clabr.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import od.twins.clabr.models.Game.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
@JsonClass(generateAdapter = true)
data class Game(
    @PrimaryKey(autoGenerate = true)
    val gameId: Long,
    val points: Int
) {
    companion object {
        const val TABLE_NAME = "game_table"
    }
}