package od.twins.clabr.data.models

data class GameSetModel(
    val timeStart: Long
) {
    val timeEnd: Long = 0
    val typeGame: GameType = GameType.TWO_ON_TWO
    val limit: LimitPoints = LimitPoints.BIG
    val playerList: List<String> = listOf()
    val pointList: List<CountModel> = listOf()
}