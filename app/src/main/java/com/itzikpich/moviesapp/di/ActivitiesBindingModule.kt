package com.itzikpich.moviesapp.di

import com.itzikpich.moviesapp.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivitiesBindingModule {

    @ContributesAndroidInjector
    abstract fun mainActivity(): MainActivity

}