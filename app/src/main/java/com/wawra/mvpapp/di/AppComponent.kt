package com.wawra.mvpapp.di

import com.wawra.mvpapp.MvpApp
import com.wawra.mvpapp.di.modules.*
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        ActivityBuilder::class,
        AndroidSupportInjectionModule::class,
        AppModule::class,
        DbModule::class,
        FragmentBuilder::class,
        NetworkModule::class,
        PresentationModule::class,
        RepositoryModule::class
    ]
)
interface AppComponent : AndroidInjector<MvpApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: MvpApp): Builder

        fun build(): AppComponent
    }
}
