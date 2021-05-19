package com.wawra.mvpapp.di.modules

import com.wawra.mvpapp.ui.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {
    @ContributesAndroidInjector
    abstract fun bindsMainActivity(): MainActivity?
}
