package com.wawra.mvpapp.presentation.posts

interface PostsView {
    fun showPosts(posts: List<PostDto>)
    fun hidePostList()
    fun showErrorContent()
    fun hideErrorContent()
    fun showEmptyContent()
    fun hideEmptyContent()
    fun showErrorMessage()
    fun showDetails(linkUrl: String, postIndex: Int?)
    fun showLoading()
    fun hideLoading()
}
