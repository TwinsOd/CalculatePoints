package od.twins.clabr.data.models

import com.squareup.moshi.JsonClass
import od.twins.clabr.model.GameMode

@JsonClass(generateAdapter = true)
data class GameSetting(
    var gameMode: GameMode,
    var pointLimit: Int,
    var penaltyForSeriousOfBeits: Int,
    var length_of_series_of_beits: Int,
)