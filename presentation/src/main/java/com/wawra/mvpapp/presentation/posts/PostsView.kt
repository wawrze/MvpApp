package com.wawra.mvpapp.presentation.posts

import com.wawra.mvpapp.domain.models.Post

interface PostsView {
    fun showPosts(posts: List<Post>)
    fun showError(throwable: Throwable)
}
