package com.itzikpich.moviesapp.di.modules

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.itzikpich.moviesapp.view_models.CategoryViewModel
import com.itzikpich.moviesapp.view_models.HomeViewModel
import com.itzikpich.moviesapp.view_models.MovieDetailsViewModel
import com.itzikpich.moviesapp.view_models.ViewModelFactory
import dagger.Binds
import dagger.MapKey
import dagger.Module
import dagger.multibindings.IntoMap
import java.lang.annotation.Documented
import kotlin.reflect.KClass

@Module
abstract class ViewModelModule {
    @Binds
    @IntoMap
    @ViewModelKey(HomeViewModel::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindHomeViewModel(editPlaceViewModel: HomeViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(CategoryViewModel::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindCategoryViewModel(editPlaceViewModel: CategoryViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MovieDetailsViewModel::class) // PROVIDE YOUR OWN MODELS HERE
    internal abstract fun bindMovieDetailsViewModel(editPlaceViewModel: MovieDetailsViewModel): ViewModel

    @Binds
    internal abstract fun bindViewModelFactory(factory: ViewModelFactory): ViewModelProvider.Factory
}

@MustBeDocumented
@Target(AnnotationTarget.FUNCTION)
@Retention(AnnotationRetention.RUNTIME)
@MapKey
internal annotation class ViewModelKey(val value: KClass<out ViewModel>)