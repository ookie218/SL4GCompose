package com.ookie.sl4gcompose.ui.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ookie.sl4gcompose.R
import com.ookie.sl4gcompose.model.Message

@Composable
fun ContactScreen(contactScreenViewModel: ContactScreenViewModel = viewModel()) {
    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp),
    ) {

        var name by rememberSaveable { mutableStateOf("") }
        var phoneNumber by rememberSaveable { mutableStateOf("") }
        var messageBody by rememberSaveable { mutableStateOf("") }


        Image(
            painter = painterResource(id = R.drawable.sl4gministriesdefault),
            contentDescription = stringResource(id = R.string.sl4g_photo_description),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(id = R.string.contactPrompt)
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(id = R.string.name_heading)
        )
        TextField(
            value = name,
            onValueChange = {name = it },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(id = R.string.phone_number_heading)
        )
        TextField(
            value = phoneNumber,
            onValueChange = { phoneNumber = it },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Text(
            text = stringResource(id = R.string.message_heading)
        )
        TextField(
            value = messageBody,
            onValueChange = { messageBody = it },
            modifier = Modifier
                .fillMaxWidth()
        )
        Spacer(modifier = Modifier.padding(8.dp))
        Button(
            onClick = {
                Message(
                    name = name,
                    phoneNumber = phoneNumber,
                    messageBody = messageBody
                )
                contactScreenViewModel.validateMessage()
            },
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
        ) {
            Text(text = stringResource(id = R.string.submit_button_label))
        }

    }

}

@Preview(showBackground = true)
@Composable
fun ContactScreenPreview() {
    ContactScreen()
}