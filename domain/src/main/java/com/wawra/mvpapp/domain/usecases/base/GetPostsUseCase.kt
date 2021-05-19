package com.wawra.mvpapp.domain.usecases.base

import com.wawra.mvpapp.domain.models.Post
import com.wawra.mvpapp.domain.repositories.PostsLocalRepository
import com.wawra.mvpapp.domain.usecases.base.executor.ExecutionThread
import com.wawra.mvpapp.domain.usecases.base.executor.PostExecutionThread
import io.reactivex.Single

class GetPostsUseCase(
    private val postsLocalRepository: PostsLocalRepository,
    private val refreshAndGetPostsUseCase: RefreshAndGetPostsUseCase,
    executionThread: ExecutionThread,
    postExecutionThread: PostExecutionThread
) : SingleUseCase<List<Post>>(executionThread, postExecutionThread) {

    override fun createSingle(): Single<List<Post>> = postsLocalRepository.getPosts()
        .flatMap { if (it.isEmpty()) refreshAndGetPostsUseCase.execute() else Single.just(it) }
        .onErrorResumeNext { refreshAndGetPostsUseCase.execute() }
}
