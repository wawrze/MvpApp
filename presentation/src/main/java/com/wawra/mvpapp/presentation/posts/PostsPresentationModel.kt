package com.wawra.mvpapp.presentation.posts

import java.io.Serializable

data class PostsPresentationModel(var state: PresenterState = PresenterState.Initial) : Serializable
