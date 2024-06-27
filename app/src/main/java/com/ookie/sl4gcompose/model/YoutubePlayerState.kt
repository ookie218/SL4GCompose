package com.ookie.sl4gcompose.model

sealed interface YoutubePlayerState {
    object Playing : YoutubePlayerState
    object Buffering : YoutubePlayerState
    object Ended : YoutubePlayerState
}