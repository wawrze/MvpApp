package com.wawra.mvpapp.domain.models

import java.io.Serializable
import java.time.LocalDate

data class Post(
    val title: String,
    val orderId: Long,
    val modificationDate: LocalDate,
    val imageUrl: String,
    val description: String,
    val linkUrl: String
) : Serializable
