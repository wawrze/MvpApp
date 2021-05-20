package com.wawra.mvpapp.presentation.postdetails

import java.io.Serializable

data class PostDetailsPresentationModel(
    val linkUrl: String,
    var startUrl: String? = null,
    var linkLoaded: Boolean = false,
    var previousUrl: String? = null,
    var activeUrl: String? = linkUrl
) : Serializable
