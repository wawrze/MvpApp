package com.wawra.mvpapp.domain.repositories

import com.wawra.mvpapp.domain.models.Post
import io.reactivex.Single

interface PostsLocalRepository {
    fun savePosts(posts: List<Post>): Single<Boolean>
    fun getPosts(): Single<List<Post>>
    fun deletePosts(): Single<Boolean>
}