package com.ookie.sl4gcompose.data

import com.ookie.sl4gcompose.R
import com.ookie.sl4gcompose.model.Artist

class ArtistRepositoryImplementation : ArtistRepository {

    override suspend fun getArtists(): List<Artist> {
        return listOf(
            Artist(
                id = 0,
                name = R.string.deane_full_name.toString(),
                nickname = null,
                imageResourceId = R.drawable.deaneprofilepic,
                artistBio = R.string.deane_bio.toString(),
            ),
            Artist(
                id = 1,
                name = R.string.ron_full_name.toString(),
                nickname = null,
                imageResourceId = R.drawable.ronprofilepic,
                artistBio = R.string.ron_bio.toString(),
            ),
            Artist(
                id = 2,
                name = R.string.ookie_full_name.toString(),
                nickname = R.string.ookie_nick_name.toString(),
                imageResourceId = R.drawable.ookieprofilepic,
                artistBio = R.string.ookie_bio.toString(),
            ),
            Artist(
                id = 3,
                name = R.string.sam_full_name.toString(),
                nickname = R.string.sam_nick_name.toString(),
                imageResourceId = R.drawable.samprofilepic,
                artistBio = R.string.sam_bio.toString(),
            ),
            Artist(
                id = 4,
                name = R.string.juju_full_name.toString(),
                nickname = R.string.juju_nick_name.toString(),
                imageResourceId = R.drawable.jujuprofilepic,
                artistBio = R.string.juju_bio.toString(),
            ),
            Artist(
                id = 5,
                name = R.string.jmask_full_name.toString(),
                nickname = R.string.jmask_nick_name.toString(),
                imageResourceId = R.drawable.jmaskprofilepic,
                artistBio = R.string.jmask_bio.toString(),
            )
        )
    }

}