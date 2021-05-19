package com.wawra.mvpapp.presentation.posts

import java.io.Serializable

sealed class PostsViewInteraction : Serializable {
    object Refresh : PostsViewInteraction()
    class ShowDetails(val id: Long) : PostsViewInteraction()
}
