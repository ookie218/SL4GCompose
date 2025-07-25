package com.ookie.sl4gcompose.ui.screens

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.lifecycle.viewmodel.compose.viewModel
import com.ookie.sl4gcompose.R
import com.ookie.sl4gcompose.data.UIEvent
import com.ookie.sl4gcompose.model.MessageState

@Composable
fun ContactScreen(contactScreenViewModel: ContactScreenViewModel = viewModel()) {

    val context = LocalContext.current

    Column (
        modifier = Modifier
            .fillMaxWidth()
            .padding(24.dp)
            .verticalScroll(rememberScrollState()),
    ) {
        var name by rememberSaveable { mutableStateOf("") }
        var phoneNumber by rememberSaveable { mutableStateOf("") }
        var messageBody by rememberSaveable { mutableStateOf("") }

        contactScreenViewModel.name = name
        contactScreenViewModel.phoneNumber = phoneNumber
        contactScreenViewModel.messageBody = messageBody

        Image(
            painter = painterResource(id = R.drawable.sl4gministries_nowhite),
            contentDescription = stringResource(id = R.string.sl4g_photo_description),
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(200.dp)
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
            maxLines = 1,
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            maxLines = 1,
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
                MessageState(
                    name = name,
                    phoneNumber = phoneNumber,
                    messageBody = messageBody
                )
                contactScreenViewModel.onEvent(UIEvent.SubmitButtonClicked)
                if (contactScreenViewModel.intentStatusSuccessful.value != false) {
                    startActivity(context, contactScreenViewModel.intent, null)
                } else {
                    Toast.makeText(context, R.string.cannot_send_email_message, Toast.LENGTH_LONG).show()
                }
            },
            enabled = contactScreenViewModel.validateMessage(MessageState(name,phoneNumber, messageBody)),
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