package od.twins.clabr.data.local.converter

import androidx.room.TypeConverter
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import com.squareup.moshi.Types
import java.util.*

class PlayerConverter {
    var adapter: JsonAdapter<List<String>>

    init {
        val moshi = Moshi.Builder().build()
        val type = Types.newParameterizedType(List::class.java, String::class.java)
        adapter = moshi.adapter(type)
    }

    @TypeConverter
    fun stringToList(data: String?): List<String>? {
        if (data == null) {
            return Collections.emptyList()
        }

        return adapter.fromJson(data)
    }

    @TypeConverter
    fun listToString(list: List<String>?): String? {
        return adapter.toJson(list)
    }
}