package com.ookie.sl4gcompose.data

import com.ookie.sl4gcompose.model.Album

/**
 * Repository that fetch Album Objects list.
 */

interface AlbumRepository {

    suspend fun getAlbums() : List<Album>

}