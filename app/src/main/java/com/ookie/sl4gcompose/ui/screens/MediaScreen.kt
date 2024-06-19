package com.ookie.sl4gcompose.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Text
import com.ookie.sl4gcompose.R

@Composable
fun MediaScreen(mediaScreenViewModel: MediaScreenViewModel = viewModel()) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp),
    ) {
        Text(
                text = stringResource(id = R.string.group_name_full),
        style = TextStyle(Color.Black)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(id = R.string.discography_tag),
            style = TextStyle(Color.Black)
        )
        // TODO: Album Carousel / with names above albums
        Spacer(modifier = Modifier.padding(16.dp))
        Text(
            text = stringResource(id = R.string.music_videos_tag),
            style = TextStyle(Color.Black)
        )
        Text(
            text = stringResource(id = R.string.referenceYoutubeTag),
            style = TextStyle(Color.Black)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        // TODO: Youtube integration / maybe spotify?


    }

}

@Preview(showBackground = true)
@Composable
fun MediaScreenPreview() {
    MediaScreen()
}