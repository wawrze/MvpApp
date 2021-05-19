package com.wawra.mvpapp.di.modules

import com.wawra.mvpapp.domain.usecases.base.UseCaseFactory
import com.wawra.mvpapp.presentation.main.SamplePresenter
import dagger.Module
import dagger.Provides

@Module
class PresentationModule {
    @Provides
    fun provideSamplePresenter(useCaseFactory: UseCaseFactory) = SamplePresenter(useCaseFactory)
}
