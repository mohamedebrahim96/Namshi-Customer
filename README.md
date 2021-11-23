<h1 align="center">Namshi Customer</h1>

<p align="center">
<img alt="Build Status" src="https://github.com/mohamedebrahim96/Namshi-Customer/workflows/Android%20CI/badge.svg"/>
<img alt="Namshi Customer App" src="https://img.shields.io/github/v/release/mohamedebrahim96/Namshi-Customer?color=7885FF&label=Namshi-customer%20App&logo=android"/>
<img alt="mohamedebrahim96" src="https://img.shields.io/github/v/release/mohamedebrahim96/mohamedebrahim96?color=7885FF&label=mohamedebrahim96"/>
    
</p>


<p align="center">  
The Namshi shopping app is your no.1 fashion & beauty destination in Saudi Arabia, United Arab Emirates, Kuwait, Oman, Bahrain, Qatar & Iraq.
Enjoy online shopping with over 1,300 brands and more than 130,000 products across clothing, sportswear, footwear, accessories, beauty, fragrances, gift ideas and more.
Appreciate the advantages of on-the-go shopping online & use our easy filters to find exactly what you’ve been searching for, all in one online shopping destination.
</p>
</br>




<p align="center">
<img src="/wiki/screenshot.jpg"/>
</p>

## Download
Go to the [Releases](https://github.com/mohamedebrahim96/Namshi-Customer/releases) to download the latest APK.


<img src="/wiki/namshigif.gif" align="right" width="32%"/>

## Tech stack & Open-source libraries
- Minimum SDK level 21
- [Kotlin](https://kotlinlang.org/) based, [Coroutines](https://github.com/Kotlin/kotlinx.coroutines) + [Flow](https://kotlin.github.io/kotlinx.coroutines/kotlinx-coroutines-core/kotlinx.coroutines.flow/) for asynchronous.
- [Hilt](https://dagger.dev/hilt/) for dependency injection.
- JetPack
    - Lifecycle - dispose of observing data when lifecycle state changes.
    - ViewModel - UI related data holder, lifecycle aware.
    - Room Persistence - construct a database using the abstract layer.
- Architecture
    - MVVM Architecture (View - DataBinding - ViewModel - Model)
    - [Bindables](https://github.com/skydoves/bindables) - Android DataBinding kit for notifying data changes to UI layers.
    - Repository pattern
- [Retrofit2 & OkHttp3](https://github.com/square/retrofit) - construct the REST APIs and paging network data.
- [Sandwich](https://github.com/skydoves/Sandwich) - construct lightweight http API response and handling error responses.
- [Moshi](https://github.com/square/moshi/) - A modern JSON library for Kotlin and Java.
- [Glide](https://github.com/bumptech/glide)
- [WhatIf](https://github.com/skydoves/whatif) - checking nullable object and empty collections more fluently.
- [Timber](https://github.com/JakeWharton/timber) - logging.
- [Material-Components](https://github.com/material-components/material-components-android) - Material design components like ripple animation, cardView.
