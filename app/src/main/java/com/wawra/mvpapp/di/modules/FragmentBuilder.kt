package com.wawra.mvpapp.di.modules

import com.wawra.mvpapp.ui.posts.PostsFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FragmentBuilder {
    @ContributesAndroidInjector(modules = [PresentationModule::class])
    abstract fun bindsPostsFragment(): PostsFragment?
}
