package com.ookie.sl4gcompose.ui.screens


import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.ookie.sl4gcompose.R
import com.ookie.sl4gcompose.model.Album
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.PlayerConstants
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.views.YouTubePlayerView

@Composable
fun MediaScreen(mediaScreenViewModel: MediaScreenViewModel = viewModel()) {

    val albums = mediaScreenViewModel.getAlbumData()

    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .verticalScroll(rememberScrollState()),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        MediaGroupNameHeader()

        Spacer(modifier = Modifier.padding(16.dp))

        DiscographySectionHeader()
        DiscographySubsectionText()
        Spacer(modifier = Modifier.padding(8.dp))
        AlbumDiscographyCarousel(albums)

        Spacer(modifier = Modifier.padding(16.dp))

        MediaPlayerSection()

        Spacer(modifier = Modifier.padding(8.dp))
    }

}

@Composable
fun MediaTextHeaders(title: Int, fontSize: TextUnit  = 24.sp, fontWeight: FontWeight?,  modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = title),
        fontSize = fontSize,
        fontWeight = FontWeight.Bold,
        style = TextStyle(Color.Black)
    )
}

@Composable
fun MediaGroupNameHeader() {
    MediaTextHeaders(title = R.string.sl4g_name_full , fontSize = 32.sp, fontWeight = FontWeight.Bold)
}

@Composable
fun DiscographySectionHeader() {
    MediaTextHeaders(title = R.string.discography_tag, fontWeight = FontWeight.Bold)
}

@Composable
fun DiscographySubsectionText() {
    MediaTextHeaders(title = R.string.discography_sub_tag, fontSize = 8.sp, fontWeight = FontWeight.Normal)
}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun AlbumListItems(albums: List<Album>) {

    val uriHandler = LocalUriHandler.current

    HorizontalPager(state = rememberPagerState {
        albums.size
    }) { index ->
        val albumUriString = stringResource(id = albums[index].urlLink)
        Column (
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .size(400.dp)
        ) {
            MediaTextHeaders(
                title = albums[index].albumName,
                fontSize = 12.sp,
                fontWeight = FontWeight.Normal,
                modifier = Modifier
            )
            AsyncImage(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp)
                    .clip(RoundedCornerShape(24.dp))
                    .clickable {
                        uriHandler.openUri(albumUriString)
                    },
                model = ImageRequest.Builder(LocalContext.current)
                    .data(albums[index].imageResourceId)
                    .build(),
                contentDescription = "${albums[index].albumName}",
                contentScale = ContentScale.Crop
            )
        }
    }

}

@Composable
fun AlbumDiscographyCarousel(
    albumList: List<Album>,
    modifier: Modifier = Modifier
) {
    AlbumListItems(albums = albumList)
}

@Composable
fun MediaPlayerSection() {

    val youtubeUrl = "0taaCWMbJz0"

    MediaTextHeaders(title = R.string.music_videos_tag, fontWeight = FontWeight.Normal)
    Text(
        text = stringResource(id = R.string.referenceYoutubeTag),
        style = TextStyle(Color.Black)
    )

    /***
     * Youtube API that was used in previous version of app is deprecated.
     * WIll look into Webview or Spotify API for substitute
     */

    YouTubeVideoPlayer(youtubeUrl, LocalLifecycleOwner.current)

}

@Composable
fun YouTubeVideoPlayer(
    url: String,
    lifecycleOwner: LifecycleOwner,
    isPlaying: (Boolean) -> Unit = {},
    isLoading: (Boolean) -> Unit = {},
    onVideoEnded: () -> Unit = {}
) {
    AndroidView(
        factory = {
            YouTubePlayerView(it).apply {
                lifecycleOwner.lifecycle.addObserver(this)

                addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                    override fun onReady(youTubePlayer: YouTubePlayer) {
                        youTubePlayer.cueVideo(url, 0f)
                        PlayerConstants.PlayerState.UNSTARTED
                    }
                    override fun onStateChange(
                        youTubePlayer: YouTubePlayer,
                        state: PlayerConstants.PlayerState
                    ) {
                        super.onStateChange(youTubePlayer, state)
                        when(state){
                            PlayerConstants.PlayerState.BUFFERING -> {
                                isLoading.invoke(true)
                                isPlaying.invoke(false)
                            }
                            PlayerConstants.PlayerState.PLAYING -> {
                                isLoading.invoke(false)
                                isPlaying.invoke(true)
                            }
                            PlayerConstants.PlayerState.ENDED -> {
                                isPlaying.invoke(false)
                                isLoading.invoke(false)
                                onVideoEnded.invoke()
                            }
                            else -> {}
                        }
                    }
                })
            }
        }
    )
}


@Preview(showBackground = true)
@Composable
fun MediaScreenPreview() {
    MediaScreen()
}