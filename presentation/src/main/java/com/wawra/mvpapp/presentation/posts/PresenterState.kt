package com.wawra.mvpapp.presentation.posts

import com.wawra.mvpapp.domain.models.Post
import java.io.Serializable

sealed class PresenterState : Serializable {
    object Initial : PresenterState()
    class Loading(val withRefresh: Boolean) : PresenterState()
    class Content(val posts: List<Post>) : PresenterState()
    object Error : PresenterState()
    object ErrorContent : PresenterState()
}
