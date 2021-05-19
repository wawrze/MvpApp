package com.wawra.mvpapp.di.modules

import com.wawra.mvpapp.ui.sample.SampleFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [PresentationModule::class])
    abstract fun bindsSampleFragment(): SampleFragment?
}
