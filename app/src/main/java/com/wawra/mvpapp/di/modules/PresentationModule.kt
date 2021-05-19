package com.wawra.mvpapp.di.modules

import com.wawra.mvpapp.domain.usecases.base.UseCaseFactory
import com.wawra.mvpapp.presentation.posts.PostsPresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun providePostsPresenter(useCaseFactory: UseCaseFactory) = PostsPresenter(useCaseFactory)
}
