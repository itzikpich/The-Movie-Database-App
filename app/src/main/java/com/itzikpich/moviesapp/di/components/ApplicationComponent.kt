package com.itzikpich.moviesapp.di.components

import android.app.Application
import com.itzikpich.moviesapp.MyApplication
import com.itzikpich.moviesapp.di.ActivitiesBindingModule
import com.itzikpich.moviesapp.di.modules.LocalDataSourceModule
import com.itzikpich.moviesapp.di.modules.NetworkModule
import com.itzikpich.moviesapp.di.modules.SubComponentsModule
import com.itzikpich.moviesapp.di.modules.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(modules = [
    NetworkModule::class,
    LocalDataSourceModule::class,
    SubComponentsModule::class,
    ViewModelModule::class,
    ActivitiesBindingModule::class,
    AndroidSupportInjectionModule::class
])
interface ApplicationComponent: AndroidInjector<MyApplication> {
//    This function exposes the ActivityComponent Factory
//    out of the graph so consumers
//    can use it to obtain new instances of ActivityComponent
    fun activityComponent(): ActivityComponent.Factory
//    no need to inject activity directly as it comes from activityComponent
//    fun inject(activity: MainActivity)

    @Component.Builder
    interface Builder {
        @BindsInstance
        fun create(app: Application): Builder
        fun build(): ApplicationComponent
    }

}