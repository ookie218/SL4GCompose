package com.ookie.sl4gcompose.ui.screens

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ookie.sl4gcompose.R
import com.ookie.sl4gcompose.model.Artist
import androidx.lifecycle.viewmodel.compose.viewModel


@Composable
fun ArtistScreen(artistScreenViewModel: ArtistScreenViewModel = viewModel()) {

        val artists = artistScreenViewModel.getArtsitData()
        Log.i("screen TAG", "$artists")

        LazyColumn {
            items(artists) {
                ArtistItem(it)
            }
        }
    }

@Preview(showBackground = true)
@Composable
fun ArtistScreenPreview() {
    ArtistScreen()
}



/**
 * Individual List item for Artists being displayed in LazyColumn
 * */

@Composable
fun ArtistItem(artist: Artist) {
    Row (
        modifier = Modifier
            .background(Color.White)
            .fillMaxWidth()
            .padding(24.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.Center
    ){
        Image(
            painter = painterResource(id = artist.imageResourceId),
            contentDescription = artist.name,
            Modifier.size(72.dp)
            // TODO: Rounded Image corners
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(id = artist.name.toInt()), //Pass in actual Int value to return string
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.weight(1f)
        )

    }

}

@Preview(showBackground = true)
@Composable
fun ArtistItemPreview() {
    ArtistItem(artist = Artist(
        1,
        R.string.ron_full_name.toString(),
        "",
        R.drawable.ookieprofilepic,
        "test")
    )

}