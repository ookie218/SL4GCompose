package com.ookie.sl4gcompose.ui.screens

import android.util.Log
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.Card
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.ookie.sl4gcompose.R
import com.ookie.sl4gcompose.model.Artist
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Icon


@Composable
fun ArtistScreen(artistScreenViewModel: ArtistScreenViewModel = viewModel()) {

    val artists = artistScreenViewModel.getArtsitData()

    Column (
        modifier = Modifier
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sl4gministries_nowhite),
            contentDescription = stringResource(id = R.string.sl4g_photo_description),
            modifier = Modifier
                .size(150.dp)
                .padding(8.dp)
        )
        Text(
            text = stringResource(id = R.string.meet_the_artists),
            modifier = Modifier.fillMaxWidth(),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        LazyColumn {
            items(artists) {
                ArtistItem(it)
            }
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
    var expanded by remember { mutableStateOf(false) }

    Card (
        modifier = Modifier.padding(vertical = 4.dp)
    ) {
        Row (
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp)
                .animateContentSize(
                    animationSpec = spring(
                        dampingRatio = Spring.DampingRatioMediumBouncy,
                        stiffness = Spring.StiffnessLow
                    )
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            Image(
                painter = painterResource(id = artist.imageResourceId),
                contentDescription = artist.name,
                modifier = Modifier
                    .size(72.dp)
                    .clip(CircleShape)
            )
            Spacer(modifier = Modifier.padding(24.dp))
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(8.dp)
            ) {
                Text(
                    text = stringResource(id = artist.name.toInt()), //Pass in actual Int value to return string
                    color = Color.Black,
                    fontWeight = FontWeight.Bold
                )
                if (expanded) {
                    Text(
                        text = stringResource(artist.artistBio.toInt()),
                    )
                }
            }
            IconButton(onClick = { expanded = !expanded }) {
                Icon(
                    imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                    contentDescription = if (expanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    },
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }

}

@Preview(showBackground = true)
@Composable
fun ArtistItemPreview() {
    ArtistItem(artist = Artist(
        1,
        R.string.ookie_full_name.toString(),
        "",
        R.drawable.ookieprofilepic,
        "test")
    )

}