package com.ookie.sl4gcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ookie.sl4gcompose.R
import com.ookie.sl4gcompose.model.Artist


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
            // TODO: Rounded Image corners
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = artist.name,
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
        "Ookie Ebron",
        null,
        R.drawable.ookieprofilepic,
        "test")
    )

}
