package com.ookie.sl4gcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Text
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.wear.compose.material.Icon
import com.ookie.sl4gcompose.R
import com.ookie.sl4gcompose.model.Artist
import com.ookie.sl4gcompose.ui.theme.Typography
import kotlinx.coroutines.launch

@Composable
fun HomeScreen(modifier: Modifier = Modifier, homeScreenViewModel: HomeScreenViewModel = viewModel()) {

    val artists = homeScreenViewModel.getArtistData()

    Column (
        modifier = Modifier
            .padding(8.dp)
            .verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Image(
            painter = painterResource(id = R.drawable.sl4gministries_nowhite),
            contentDescription = stringResource(id = R.string.sl4g_photo_description),
            modifier.size(200.dp)
        )
        Text(
            text = stringResource(id = R.string.sl4g_bio),
            modifier = Modifier.padding(16.dp),
            fontSize = 16.sp,
            style = Typography.bodyMedium,
            fontFamily = FontFamily.Serif,
        )
        Text(
            text = stringResource(id = R.string.artists),
            modifier = Modifier.fillMaxWidth()
                .padding(0.dp, 16.dp),
            fontSize = 16.sp,
            textAlign = TextAlign.Start,
            fontWeight = FontWeight.Bold
        )
        LazyRow (
            Modifier.fillMaxWidth(), // Ensure row is not unbounded - causing IllegalStateException (Infinity max width)
            state = rememberLazyListState()
        ) {
            items(artists) {
                ArtistItem(it)
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomeScreenPreview() {
    HomeScreen()
}

@Composable
fun ArtistItem(artist: Artist) {
    Column (
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        ArtistImageButton(artist)
        Text(
            text = stringResource(id = artist.name.toInt()), //Pass in actual Int value to return string
            Modifier.padding(0.dp, 8.dp),
            color = Color.Black,
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ArtistImageButton(artist: Artist) {
    val sheetState = rememberModalBottomSheetState(/* skipPartiallyExpanded = false */)
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }

    Image(
            painter = painterResource(id = artist.imageResourceId),
            contentDescription = artist.name,
            modifier = Modifier
                .size(120.dp)
                .clip(CircleShape)
                .clickable { showBottomSheet = true }

//              TODO: Make sure we understand this!!
//                .clickable { scope.launch { sheetState.hide() }.invokeOnCompletion {
//                    if (!sheetState.isVisible) {
//                        showBottomSheet = false
//                    }
//                } }
        )
    if (showBottomSheet) {
        ModalBottomSheet(
            onDismissRequest = { showBottomSheet = false },
            modifier = Modifier.fillMaxHeight(),
            sheetState = sheetState )
        {
            //Sheet Content
            Column(
                modifier = Modifier.padding(16.dp)
            ) {
                Row {
                    Image(
                        painter = painterResource(id = artist.imageResourceId),
                        contentDescription = artist.name,
                        Modifier
                            .size(100.dp)
                            .clip(CircleShape)
                    )
                    Text(
                        text = stringResource(id = artist.name.toInt()),
                        Modifier.weight(1f),
                        fontSize = 32.sp,
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                    )
                }
                Spacer(modifier = Modifier.padding(16.dp))
                Text(
                    text = stringResource(id = artist.artistBio.toInt()),
                    fontSize = 24.sp,
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
