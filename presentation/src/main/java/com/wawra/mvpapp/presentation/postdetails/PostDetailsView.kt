package com.wawra.mvpapp.presentation.postdetails

interface PostDetailsView {
    fun loadUrl(linkUrl: String)
    fun hideLoading()
    fun showLoading()
    fun close()
}
