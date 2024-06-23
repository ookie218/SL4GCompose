package com.ookie.sl4gcompose.data

import com.ookie.sl4gcompose.R
import com.ookie.sl4gcompose.model.Album

class AlbumRepositoryImplementation : AlbumRepository {
    override suspend fun getAlbums(): List<Album> {
            return listOf(
                Album(
                    id = 0,
                    albumName = R.string.album_a_road_less_traveled,
                    imageResourceId = R.drawable.aroadlesstraveledcover,
                    urlLink = R.string.album_a_road_less_traveled_url,
                ),
                Album(
                    id = 1,
                    albumName = R.string.album_water_walking,
                    imageResourceId = R.drawable.waterwalkincover,
                    urlLink = R.string.album_water_walking_url,
                ),
                Album(
                    id = 2,
                    albumName = R.string.album_no_longer_bound,
                    imageResourceId = R.drawable.nolongerboudcover,
                    urlLink = R.string.album_no_longer_bound_url,
                ),
                Album(
                    id = 3,
                    albumName = R.string.album_rhythm_of_heaven,
                    imageResourceId = R.drawable.rhythmofheavencover,
                    urlLink = R.string.album_rhythm_of_heaven_url,
                ),
                Album(
                    id = 4,
                    albumName = R.string.album_no_brakes,
                    imageResourceId = R.drawable.nobrakescover,
                    urlLink = R.string.album_no_brakes_url,
                ),
            )
    }
}