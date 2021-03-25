package com.itzikpich.moviesapp.di.components

import com.itzikpich.moviesapp.MainActivity
import com.itzikpich.moviesapp.di.scopes.ActivityScope
import com.itzikpich.moviesapp.views.CategoryFragment
import com.itzikpich.moviesapp.views.HomeFragment
import com.itzikpich.moviesapp.views.MovieDetailsFragment
import dagger.Component
import dagger.Subcomponent

@ActivityScope
@Subcomponent
interface ActivityComponent {

    // Factory that is used to create instances of this sub component
    @Subcomponent.Factory
    interface Factory {
        fun create(): ActivityComponent
    }

    /** This tells Dagger that MainActivity requests injection from ActivityComponent
     * so that this sub component graph needs to satisfy all the dependencies of the
     * fields that MainActivity is injecting
     */

    fun inject(activity: MainActivity)
    fun inject(fragment: HomeFragment)
    fun inject(fragment: MovieDetailsFragment)
    fun inject(fragment: CategoryFragment)

}