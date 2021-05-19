package com.wawra.mvpapp.presentation.postdetails

import java.io.Serializable

data class PostDetailsPresentationModel(
    val linkUrl: String,
    var linkLoaded: Boolean = false
) : Serializable
