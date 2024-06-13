package com.ookie.sl4gcompose.data

import com.ookie.sl4gcompose.model.Artist

/**
 * Repository that fetch Artist Objects list. This to maintain good architecture principles.
 * No Database or Server Implementation in this application as of now
 */

interface ArtistRepository {

    suspend fun getArtists() : List<Artist>

}