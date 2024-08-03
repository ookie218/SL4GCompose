package com.ookie.sl4gcompose.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
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

    val sl4gEmail = "switchinlanes4god@gmail.com"
    val context = LocalContext.current

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
            keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
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
                Log.i("Ebron", "${MessageState(name,phoneNumber, messageBody)}")
                MessageState(
                    name = name,
                    phoneNumber = phoneNumber,
                    messageBody = messageBody
                )
                Log.i("Ebron", "${MessageState(name,phoneNumber, messageBody)}")
                Log.i("Ebron", "${contactScreenViewModel.validateMessage(MessageState(name,phoneNumber, messageBody))}")
                //contactScreenViewModel.onEvent(UIEvent.SubmitButtonClicked)
                if (contactScreenViewModel.validateMessage(MessageState(name,phoneNumber, messageBody))) {
                    Log.i("Ebron", "${contactScreenViewModel.validateMessage(MessageState(name,phoneNumber, messageBody))}")
                    //Construct and launch email
                    val intent = Intent(Intent.ACTION_SENDTO)

                    // only email apps should handle this
                    intent.setData(Uri.parse("mailto:"))

                    //In order to populate the "To:" field, this must be an Array of strings, not just a string itself!
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(sl4gEmail))
                    intent.putExtra(Intent.EXTRA_PHONE_NUMBER, phoneNumber)
                    intent.putExtra(Intent.EXTRA_TEXT, "Name: $name \nPhone: $phoneNumber \n \n$messageBody")
                    startActivity(context, intent, null)
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