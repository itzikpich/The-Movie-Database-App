package com.itzikpich.moviesapp.views

import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.itzikpich.moviesapp.MainActivity
import javax.inject.Inject

open class BaseFragment(layoutRes: Int) : Fragment(layoutRes) {


    val TAG = this::class.java.simpleName

    val mainActivity get() = activity as MainActivity

    @Inject
    lateinit var factory: ViewModelProvider.Factory
}