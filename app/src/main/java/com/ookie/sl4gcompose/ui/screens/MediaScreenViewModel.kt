package com.ookie.sl4gcompose.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ookie.sl4gcompose.data.AlbumRepositoryImplementation
import com.ookie.sl4gcompose.model.Album
import kotlinx.coroutines.launch

class MediaScreenViewModel: ViewModel() {

    private val albumRepository = AlbumRepositoryImplementation()

    init {
        getAlbumData()
    }

    var albumList: List<Album> = emptyList()

    fun getAlbumData(): List<Album> {
        viewModelScope.launch {
            try {
                albumList = albumRepository.getAlbums()
            } catch (_: Error) {
            }
        }
        return albumList
    }

}