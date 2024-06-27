package com.ookie.sl4gcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.MusicVideo
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.LibraryMusic
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.MusicVideo
import androidx.compose.material.icons.outlined.Person
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.ookie.sl4gcompose.data.ArtistRepositoryImplementation
import com.ookie.sl4gcompose.model.TabItem
import com.ookie.sl4gcompose.ui.screens.ArtistScreen
import com.ookie.sl4gcompose.ui.screens.ArtistScreenViewModel
import com.ookie.sl4gcompose.ui.screens.ContactScreen
import com.ookie.sl4gcompose.ui.screens.HomeScreen
import com.ookie.sl4gcompose.ui.screens.MediaScreen
import com.ookie.sl4gcompose.ui.theme.SL4GComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val tabItems = listOf(
            TabItem(
                title = "Home",
                unselectedIcon = Icons.Outlined.Home,
                selectedIcon = Icons.Filled.Home
            ),
            TabItem(
                title = "Artists",
                unselectedIcon = Icons.Outlined.Person,
                selectedIcon = Icons.Filled.Person
            ),
            TabItem(
                title = "Media",
                unselectedIcon = Icons.Outlined.MusicVideo,
                selectedIcon = Icons.Filled.MusicVideo
            ),
            TabItem(
                title = "Email",
                unselectedIcon = Icons.Outlined.Mail,
                selectedIcon = Icons.Filled.Mail
            )
        )
        setContent {
            SL4GComposeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    var selectedTabIndex by remember {
                        mutableIntStateOf(0)
                    }
                    val pagerState = rememberPagerState {
                        tabItems.size
                    }
                    LaunchedEffect(key1 = selectedTabIndex ) {
                        pagerState.animateScrollToPage(selectedTabIndex)
                    }
                    LaunchedEffect(key1 = pagerState.currentPage, pagerState.isScrollInProgress) {
                        if (!pagerState.isScrollInProgress) {
                            selectedTabIndex = pagerState.currentPage
                        }
                    }
                    Column (
                        Modifier.fillMaxSize()
                    ){
                        TabRow(selectedTabIndex = selectedTabIndex) {
                            tabItems.forEachIndexed { index, tabItem ->
                                Tab(
                                    selected = index == selectedTabIndex, //If (it) index is same as what's on tab item
                                    onClick = { selectedTabIndex = index },
                                    text = {
                                        Text(text = tabItem.title)
                                    },
                                    icon = {
                                        Icon(
                                            imageVector = if(index == selectedTabIndex) {
                                                tabItem.selectedIcon
                                            } else {
                                                tabItem.unselectedIcon
                                            },
                                            contentDescription = tabItem.title)
                                    }
                                )
                            }
                        }
                        HorizontalPager(
                            state = pagerState,
                            Modifier.fillMaxSize(1f)) {
                            when(it){
                                0 -> HomeScreen()
                                1 -> ArtistScreen()
                                2 -> MediaScreen()
                                3 -> ContactScreen()
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SL4GComposeTheme {
        Greeting("Android")
    }
}