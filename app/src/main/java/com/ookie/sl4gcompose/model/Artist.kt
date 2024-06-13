package com.ookie.sl4gcompose.model


/**
 * Single Constructor defines Artist Object
 */

data class Artist(
    val id: Int,
    val name: String,
    val nickname: String?,
    val imageResourceId: Int,
    val artistBio: String)