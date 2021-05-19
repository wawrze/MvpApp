package com.wawra.mvpapp.data.network.models

import com.google.gson.annotations.SerializedName
import com.wawra.mvpapp.domain.FORMATTER
import com.wawra.mvpapp.domain.models.Post
import java.time.LocalDate

data class PostJson(
    @SerializedName("title") val title: String? = null,
    @SerializedName("orderId") val orderId: Long? = null,
    @SerializedName("modificationDate") val modificationDate: String? = null,
    @SerializedName("image_url") val imageUrl: String? = null,
    @SerializedName("description") val description: String? = null
) {
    fun toDomain() = Post(
        title ?: "",
        orderId ?: -1L,
        LocalDate.parse(modificationDate, FORMATTER),
        imageUrl ?: "",
        description?.substringBefore("http") ?: "",
        "http${description?.substringAfter("http") ?: ""}"
    )
}
