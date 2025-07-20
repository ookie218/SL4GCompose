package com.ookie.sl4gcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Mail
import androidx.compose.material.icons.filled.MusicVideo
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.Mail
import androidx.compose.material.icons.outlined.MusicVideo
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.ookie.sl4gcompose.model.TabItem
import com.ookie.sl4gcompose.ui.screens.ContactScreen
import com.ookie.sl4gcompose.ui.screens.HomeScreen
import com.ookie.sl4gcompose.ui.screens.MediaScreen
import com.ookie.sl4gcompose.ui.theme.SL4GComposeTheme

class MainActivity : ComponentActivity() {
    @OptIn(ExperimentalFoundationApi::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // TODO: Follow same coding practices - Make into it's own class / repository and list
        val tabItems = listOf(
            TabItem(
                title = "Home",
                route = "Home",
                unselectedIcon = Icons.Outlined.Home,
                selectedIcon = Icons.Filled.Home
            ),
//            TabItem(
//                title = "Artists",
//                route = "Artists",
//                unselectedIcon = Icons.Outlined.Person,
//                selectedIcon = Icons.Filled.Person
//            ),
            TabItem(
                title = "Media",
                route = "Media",
                unselectedIcon = Icons.Outlined.MusicVideo,
                selectedIcon = Icons.Filled.MusicVideo
            ),
            TabItem(
                title = "Email",
                route = "Email",
                unselectedIcon = Icons.Outlined.Mail,
                selectedIcon = Icons.Filled.Mail
            )
        )
        setContent {
            SL4GComposeTheme {

                val navController = rememberNavController()
                NavHost(navController = navController, startDestination = tabItems[0].route) {
                    composable(tabItems[0].route) {
                        HomeScreen()
                    }
//                    composable(tabItems[1].route) {
//                        ArtistScreen()
//                    }
                    composable(tabItems[1].route) {
                        MediaScreen()
                    }
                    composable(tabItems[2].route) {
                        ContactScreen()
                    }
                }

                // TODO: All of this needs to be extracted to a class - not in main activity (at least in it's own function)
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

                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.primary
                ) {
                    Scaffold (
                        bottomBar = {
                            NavigationBar {
                                tabItems.forEachIndexed { index, tabItem ->
                                    NavigationBarItem(
                                        selected = index == selectedTabIndex, //If (it) index is same as what's on tab item,
                                        onClick = {
                                                    selectedTabIndex = index
                                            navController.navigate(tabItem.route)
                                                  },
                                        icon = {
                                            Icon(
                                                imageVector = if(index == selectedTabIndex) {
                                                    tabItem.selectedIcon
                                                } else {
                                                    tabItem.unselectedIcon
                                                },
                                                contentDescription = tabItem.title)
                                        },
                                        label = { Text(text = tabItem.title) }
                                    )
                                }
                            }
                        }
                    ) { paddedValues ->  //Specified to not use generic "it"
                        //Box is used a wrapper to make sure content in screen is not cut off
                        Box(modifier = Modifier.padding(paddedValues)) {
                            HorizontalPager(
                                state = pagerState,
                                Modifier.fillMaxSize(1f)) { pageIndex ->
                                when(pageIndex){
                                    0 -> HomeScreen()
//                                    1 -> ArtistScreen()
                                    1 -> MediaScreen()
                                    2 -> ContactScreen()
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    SL4GComposeTheme {

    }
}