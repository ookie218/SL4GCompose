package com.ookie.sl4gcompose.data

interface AppContainer {
    val artistRepository: ArtistRepository
}

class SL4GAppContainer: AppContainer {
    //WILL CAUSE VIEWMODEL TO NOT INSTANTIATE IF NOT INITIATED BY LAZY
    override val artistRepository: ArtistRepository by lazy {
        ArtistRepositoryImplementation()
    }
}