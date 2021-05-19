package com.wawra.mvpapp.presentation.posts

import com.wawra.mvpapp.domain.models.Post
import java.io.Serializable

data class PostsPresentationModel(var posts: List<Post>? = null) : Serializable
