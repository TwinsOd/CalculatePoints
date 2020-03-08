package od.twins.clabr.data.models

data class GameSetModel(
    val timeStart: Long
) {
    val timeEnd: Long = 0
    val typeGame: Int = -1
    val limit: Int = 1
    val playerList: List<String> = listOf()
    val pointList: List<CountModel> = listOf()
}