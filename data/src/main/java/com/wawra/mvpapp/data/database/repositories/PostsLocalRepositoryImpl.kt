package com.wawra.mvpapp.data.database.repositories

import com.wawra.mvpapp.data.database.daos.PostDao
import com.wawra.mvpapp.data.database.models.PostEntity
import com.wawra.mvpapp.domain.models.Post
import com.wawra.mvpapp.domain.repositories.PostsLocalRepository
import io.reactivex.Single
import javax.inject.Inject

class PostsLocalRepositoryImpl @Inject constructor(
    private val postDao: PostDao
) : PostsLocalRepository {

    override fun savePosts(posts: List<Post>): Single<Boolean> = postDao
        .insertPosts(posts.map { PostEntity(it) })
        .map { it.size == posts.size }

    override fun getPosts(): Single<List<Post>> = postDao.getPosts()
        .map { posts -> posts.map { it.toDomain() } }

    override fun deletePosts(): Single<Boolean> = postDao.deleteAll()
        .map { true }
        .onErrorReturn { false }
}
