package com.wawra.mvpapp.presentation.posts

interface PostsView {
    fun showPosts(posts: List<PostDto>)
    fun showError(throwable: Throwable)
    fun showDetails(linkUrl: String, postIndex: Int?)
    fun showLoading()
    fun hideLoading()
}
