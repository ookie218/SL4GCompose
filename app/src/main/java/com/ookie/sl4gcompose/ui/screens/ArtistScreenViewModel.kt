package com.ookie.sl4gcompose.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProvider.AndroidViewModelFactory.Companion.APPLICATION_KEY
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.ookie.sl4gcompose.SL4GApplication
import com.ookie.sl4gcompose.data.ArtistRepository
import com.ookie.sl4gcompose.data.ArtistRepositoryImplementation
import com.ookie.sl4gcompose.model.Artist
import kotlinx.coroutines.launch

class ArtistScreenViewModel : ViewModel() {

    private val artistRepository = ArtistRepositoryImplementation()

    init {
       getArtsitData()
    }

    var artistList: List<Artist> = emptyList()

     fun getArtsitData(): List<Artist> {
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