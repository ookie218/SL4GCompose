package com.ookie.sl4gcompose.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ookie.sl4gcompose.data.ArtistRepositoryImplementation
import com.ookie.sl4gcompose.model.Artist
import kotlinx.coroutines.launch

class HomeScreenViewModel: ViewModel() {

    private val artistRepository = ArtistRepositoryImplementation()

    init {
        getArtistData()
    }

    var artistList: List<Artist> = emptyList()

    fun getArtistData(): List<Artist> {
        Log.i("TAG", "$artistList")
        viewModelScope.launch {
            try {
                artistList = artistRepository.getArtists()
            } catch (_: Error) {
                Log.i("TAG", "$artistList")
            }
        }
        return artistList
    }

}