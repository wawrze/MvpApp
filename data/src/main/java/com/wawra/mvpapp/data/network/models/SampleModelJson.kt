package com.wawra.mvpapp.data.network.models

import com.google.gson.annotations.SerializedName
import com.wawra.mvpapp.domain.models.SampleModel

data class SampleModelJson(
    @SerializedName("id") val id: Long? = null,
    @SerializedName("name") val name: String? = null
) {
    constructor(sampleModel: SampleModel) : this(sampleModel.id, sampleModel.name)

    fun toDomain() = SampleModel(id ?: -1, name ?: "")
}
