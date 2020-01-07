# Al-Matar-Task
This repo for Al-Matar Android Task using www.themoviedb.org

## <b>Task Description: </b> <br />
* Need to show list of movies returned from api. <br />
* Sort this list of movies from newest to oldet movies. <br />
* Cache the movies list and every movie details to show it from Room DB in case no internet connection. <br />


| Home Screen                    | Movie Details Screen              | 
|:------------------------------:|:---------------------------------:|
|![](https://raw.githubusercontent.com/Mina-Mikhail/Al-Matar-Task/master/Shots/Home%20Screen.jpg) | ![](https://raw.githubusercontent.com/Mina-Mikhail/Al-Matar-Task/master/Shots/Movie%20Details%20Screen.jpg) |

## <b>Project Architecture: </b> <br />
I have used MVVM to build my project.<br />


## <b>Built With: </b> <br />
* [Glide](https://github.com/bumptech/glide) - For image lodaing <br />
* [Retrofit2](https://github.com/square/retrofit) - For network calls <br />
* [Gson](https://github.com/google/gson) - Gson is a Java library that can be used to convert Java Objects into their JSON representation <br />
* [RXJava](https://github.com/ReactiveX/RxJava) - To handle network calls alongside with  Retrofit2<br />
* [Rounded ImageView](https://github.com/vinc3m1/RoundedImageView) - To display images <br />
* [Uber autoDispose](https://github.com/uber/AutoDispose) - Automatic binding+disposal of RxJava 2 streams<br />
* [Room](https://developer.android.com/jetpack/androidx/releases/room) - Persistence library provides an abstraction layer over SQLite <br />
* [Dagger](https://github.com/google/dagger) - A fast dependency injector for Java and Android. <br />
* [Android SpinKit](https://github.com/ybq/Android-SpinKit) - Android loading animations <br />
* [Data Binding](https://developer.android.com/topic/libraries/data-binding) - Support library that allows you to bind UI components in your layouts to data sources in your app <br />
* [PhotoView](https://github.com/chrisbanes/PhotoView) - For zooming image views <br />
* [Butterknife](https://github.com/JakeWharton/butterknife) - For bind views <br />
* [SimpleRatingBar](https://github.com/ome450901/SimpleRatingBar) - For Rating Bar <br />


## <b>To Do in Future: </b> <br />
* Use Android Navigation Component Library.<br />
* Apply some Unit Tests.<br />
* Kotlin Version (will be in a seperate branch).<br />

## License

    Copyright (C) 2020 Mina Mikhail

    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at

       http://www.apache.org/licenses/LICENSE-2.0

    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitations under the License.
