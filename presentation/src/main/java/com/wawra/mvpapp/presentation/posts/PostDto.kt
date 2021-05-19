package com.wawra.mvpapp.presentation.posts

data class PostDto(
    val id: Long,
    val title: String,
    val description: String,
    val date: String,
    val imageUrl: String
)