#  The Movie Database Application
This application showing 3 screen using Kotlin with mvvm as architectural design pattern, liveData as the publisher and flow to fetch data locally or remote.
navigation is provided by Android Navigation component.
First screen contains three movie categories, user can click on movie item and navigate to its details screen, user can also click on more button and navigate to a page of pressed categoty

all data is fetched from remote and saved locally, than the data is displayed on the app.

dagger graph of application: 

Application > Activity > Fragment > ViewModel > ViewModelFactory > Repository(Singleton) > LocalDataSource > RoomDatabase
                                                                                         > RemoteDataSource