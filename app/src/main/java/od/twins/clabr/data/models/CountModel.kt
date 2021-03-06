package od.twins.clabr.data.models

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CountModel(val id: Int, val points: List<Int>)