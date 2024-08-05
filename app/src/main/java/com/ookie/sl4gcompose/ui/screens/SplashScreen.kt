package com.ookie.sl4gcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.ookie.sl4gcompose.R

@Composable
fun SplashScreen() {
    Column {
        Image(
            painter = painterResource(id = R.drawable.sl4gministries_nowhite),
            contentDescription = stringResource(id = R.string.sl4g_photo_description),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .fillMaxSize()
                .padding(54.dp)
                .background(Color.White)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun SplashScreenPreview() {
    SplashScreen()
}