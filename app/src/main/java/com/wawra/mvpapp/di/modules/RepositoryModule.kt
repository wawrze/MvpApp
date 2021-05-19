package com.wawra.mvpapp.di.modules

import com.wawra.mvpapp.data.database.repositories.PostsLocalRepositoryImpl
import com.wawra.mvpapp.data.network.repositories.PostsNetworkRepositoryImpl
import com.wawra.mvpapp.domain.repositories.PostsLocalRepository
import com.wawra.mvpapp.domain.repositories.PostsNetworkRepository
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class RepositoryModule {

    @Provides
    @Singleton
    fun providePostsNetworkRepository(
        postsNetworkRepository: PostsNetworkRepositoryImpl
    ): PostsNetworkRepository = postsNetworkRepository

    @Provides
    @Singleton
    fun providePostsLocalRepository(
        postsLocalRepository: PostsLocalRepositoryImpl
    ): PostsLocalRepository = postsLocalRepository
}
