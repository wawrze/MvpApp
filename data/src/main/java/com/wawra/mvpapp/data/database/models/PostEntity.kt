package com.wawra.mvpapp.data.database.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.wawra.mvpapp.domain.FORMATTER
import com.wawra.mvpapp.domain.models.Post
import java.time.LocalDate

@Entity(tableName = "post")
data class PostEntity(
    val title: String,
    @PrimaryKey val orderId: Long,
    val modificationDate: String,
    val imageUrl: String,
    val description: String,
    val linkUrl: String
) {
    constructor(post: Post) : this(
        post.title,
        post.orderId,
        FORMATTER.format(post.modificationDate),
        post.imageUrl,
        post.description,
        post.linkUrl
    )

    fun toDomain() = Post(
        title,
        orderId,
        LocalDate.parse(modificationDate, FORMATTER),
        imageUrl,
        description.substringBefore("http"),
        linkUrl
    )
}
