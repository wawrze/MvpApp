package com.wawra.mvpapp.domain.usecases.base

import com.wawra.mvpapp.domain.repositories.PostsLocalRepository
import com.wawra.mvpapp.domain.repositories.PostsNetworkRepository
import com.wawra.mvpapp.domain.usecases.base.executor.ExecutionThread
import com.wawra.mvpapp.domain.usecases.base.executor.PostExecutionThread
import javax.inject.Inject
import javax.inject.Provider
import javax.inject.Singleton

@Singleton
class UseCaseFactory @Inject constructor(
    private val executionThread: Provider<ExecutionThread>,
    private val postExecutionThread: Provider<PostExecutionThread>,
    private val postsLocalRepository: Provider<PostsLocalRepository>,
    private val postsNetworkRepository: Provider<PostsNetworkRepository>
) {

    fun provideGetPostsUseCase() = GetPostsUseCase(
        postsLocalRepository.get(),
        provideRefreshAndGetPostsUseCase(),
        executionThread.get(),
        postExecutionThread.get()
    )

    fun provideRefreshAndGetPostsUseCase() = RefreshAndGetPostsUseCase(
        postsNetworkRepository.get(),
        postsLocalRepository.get(),
        executionThread.get(),
        postExecutionThread.get()
    )
}
