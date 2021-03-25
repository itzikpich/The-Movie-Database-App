package com.itzikpich.moviesapp

import android.app.Application
import com.itzikpich.moviesapp.di.components.DaggerApplicationComponent

class MyApplication: Application() {

    val appComponent = DaggerApplicationComponent.builder().create(this).build()

}