# Globes Articles Application
This application showing 2 screen using Kotlin with mvvm as architectural design pattern, liveData as the publisher and viewPager2 for showing different data source,
navigation is now provided by Android Navigation component.
First screen contains my name, the current time, a navigation button and empty TextView that shows the latest pressed item's title from screen two
in order to show the last item title we are using a shared view model between the fragments and thr activity.
Second screen contains tab layout fragment with FragmentStateAdapter which build BaseSingeTabFragment, this fragment showing Globes xml data source fetched from network using Retrofit.  
using kotlin coroutines for async data fetch.
