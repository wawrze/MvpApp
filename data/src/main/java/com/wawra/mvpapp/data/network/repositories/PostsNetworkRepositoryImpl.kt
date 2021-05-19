package com.wawra.mvpapp.data.network.repositories

import com.wawra.mvpapp.data.network.ApiInterface
import com.wawra.mvpapp.domain.models.Post
import com.wawra.mvpapp.domain.repositories.PostsNetworkRepository
import io.reactivex.Single
import javax.inject.Inject

class PostsNetworkRepositoryImpl @Inject constructor(
    private val apiInterface: ApiInterface
) : PostsNetworkRepository {

    override fun getPosts(): Single<List<Post>> = apiInterface.getPosts()
        .map { posts -> posts.map { it.toDomain() } }
}