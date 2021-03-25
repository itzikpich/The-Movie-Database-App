package com.itzikpich.moviesapp.di.modules

import com.itzikpich.moviesapp.di.components.ActivityComponent
import dagger.Module

// The "sub components" attribute in the @Module annotation tells Dagger what
// Sub components are children of the Component this module is included in.
@Module(subcomponents = [ActivityComponent::class])
class SubComponentsModule {
}