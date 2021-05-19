package com.wawra.mvpapp.domain.repositories

import com.wawra.mvpapp.domain.models.Post
import io.reactivex.Single

interface PostsNetworkRepository {
    fun getPosts(): Single<List<Post>>
}
