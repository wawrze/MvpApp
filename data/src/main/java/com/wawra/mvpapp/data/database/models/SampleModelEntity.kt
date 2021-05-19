package com.wawra.mvpapp.data.database.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wawra.mvpapp.domain.models.SampleModel

@Entity(tableName = "sample")
data class SampleModelEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Long = 0,
    @ColumnInfo(name = "name") val name: String = ""
) {
    constructor(sampleModel: SampleModel) : this(sampleModel.id, sampleModel.name)

    fun toDomain() = SampleModel(id, name)
}
