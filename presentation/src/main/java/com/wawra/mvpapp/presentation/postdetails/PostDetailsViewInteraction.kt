package com.wawra.mvpapp.presentation.postdetails

import java.io.Serializable

sealed class PostDetailsViewInteraction : Serializable {
    class LinkLoading(val isLoaded: Boolean) : PostDetailsViewInteraction()
    class ChangeActiveUrl(val url: String?) : PostDetailsViewInteraction()
    object BackPressed : PostDetailsViewInteraction()
}
