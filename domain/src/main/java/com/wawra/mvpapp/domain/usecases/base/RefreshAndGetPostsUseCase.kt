package com.wawra.mvpapp.domain.usecases.base

import com.wawra.mvpapp.domain.models.Post
import com.wawra.mvpapp.domain.repositories.PostsLocalRepository
import com.wawra.mvpapp.domain.repositories.PostsNetworkRepository
import com.wawra.mvpapp.domain.usecases.base.executor.ExecutionThread
import com.wawra.mvpapp.domain.usecases.base.executor.PostExecutionThread
import io.reactivex.Single

class RefreshAndGetPostsUseCase(
    private val postsNetworkRepository: PostsNetworkRepository,
    private val postsLocalRepository: PostsLocalRepository,
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Post>>(executionThread, postExecutionThread) {

    override fun createSingle(): Single<List<Post>> = postsNetworkRepository.getPosts()
        .flatMap { savePosts(it) }
        .flatMap { postsLocalRepository.getPosts() }

    private fun savePosts(posts: List<Post>) = postsLocalRepository.deletePosts()
        .flatMap { postsLocalRepository.savePosts(posts) }
}
