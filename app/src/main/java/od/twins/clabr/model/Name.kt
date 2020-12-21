package od.twins.clabr.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.squareup.moshi.JsonClass
import od.twins.clabr.model.Name.Companion.TABLE_NAME

@Entity(tableName = TABLE_NAME)
@JsonClass(generateAdapter = true)
data class Name(
    @PrimaryKey(autoGenerate = true)
    val nameId: Long,
    val value: String,
) {
    constructor(name: String) : this(0, name)

    companion object {
        const val TABLE_NAME = "name_table"
    }
}