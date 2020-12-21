package od.twins.clabr.data.local.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import od.twins.clabr.data.models.CountModel
import java.util.*

class CountModelConverter {
    var adapter: JsonAdapter<List<CountModel>>

    init {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, CountModel::class.java)
        adapter = moshi.adapter(type)
    }

    @TypeConverter
    fun stringToList(data: String?): List<CountModel>? {
        if (data == null) {
            return Collections.emptyList()
        }

        return adapter.fromJson(data)
    }

    @TypeConverter
    fun listToString(list: List<CountModel>?): String? {
        return adapter.toJson(list)
    }
}