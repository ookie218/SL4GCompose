package com.ookie.sl4gcompose.ui.screens

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.ViewModel
import com.ookie.sl4gcompose.data.ContactScreenUIState
import com.ookie.sl4gcompose.data.UIEvent
import com.ookie.sl4gcompose.model.MessageState

const val SL4G_EMAIL = "switchinlanes4god@gmail.com"
const val OOKIE_EMAIL = "ookieebron@gmail.com"

class ContactScreenViewModel: ViewModel() {

    var contactScreenUIState = mutableStateOf(ContactScreenUIState())
    var intent = Intent(Intent.ACTION_SENDTO)

    //These will be updated with rememberSavable calls on Screen
    var name: String = ""
    var phoneNumber: String = ""
    var messageBody: String = ""

    fun validateMessage(messageState: MessageState) : Boolean {
        Log.i("Ebron", "name: ${messageState.name}")
        Log.i("Ebron", "phoneNumber: ${messageState.phoneNumber}")
        Log.i("Ebron", "messageBody: ${messageState.messageBody}")

        return nameValidate(messageState.name) &&
        phoneNumberValidate(messageState.phoneNumber.toString()) &&
        messageBodyValidate(messageState.messageBody)
    }

    fun onEvent(uiEvent: UIEvent) {
        when (uiEvent) {
            is UIEvent.NameChanged -> {
                contactScreenUIState.value = contactScreenUIState.value.copy(
                    name = uiEvent.name
                )
            }

            is UIEvent.NumberChanged -> {
                contactScreenUIState.value = contactScreenUIState.value.copy(
                    name = uiEvent.number
                )
            }

            is UIEvent.MessageChanged -> {
                contactScreenUIState.value = contactScreenUIState.value.copy(
                    name = uiEvent.message
                )
            }

            is UIEvent.SubmitButtonClicked -> {
                //validateMessage()

//                //Construct and launch email
//                val intent = Intent(Intent.ACTION_SENDTO)
//                intent.setData(Uri.parse("mailto:")) // only email apps should handle this
//                intent.putExtra(Intent.EXTRA_EMAIL, sl4gEmail)
//
//                // TODO: Format this default Email better
//                intent.putExtra(Intent.EXTRA_PHONE_NUMBER, uiEvent.phoneNumber)
//                intent.putExtra(Intent.EXTRA_TEXT, phoneNumber + messageBody)
//
                if (validateMessage(MessageState(name,phoneNumber, messageBody))) {

                    //Construct and launch email
                    //intent = Intent(Intent.ACTION_SENDTO)

                    // only email apps should handle this
                    intent.setData(Uri.parse("mailto:"))

                    //In order to populate the "To:" field, this must be an Array of strings, not just a string itself!
                    intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(SL4G_EMAIL))
                    intent.putExtra(Intent.EXTRA_BCC, arrayOf(OOKIE_EMAIL))
                    intent.putExtra(Intent.EXTRA_PHONE_NUMBER, phoneNumber)
                    intent.putExtra(Intent.EXTRA_TEXT, "Name: $name \nPhone: $phoneNumber \n \n$messageBody")
                }

            }
        }
    }


    private fun nameValidate(name: String): Boolean {
        // TODO: this also needs to ensure it is only alpha characters
        return name.isNotBlank() && !name.isDigitsOnly()
    }

    private fun phoneNumberValidate(phoneNumber: String): Boolean {
        return phoneNumber.isNotBlank() && phoneNumber.isDigitsOnly()
    }

    private fun messageBodyValidate(messageBody: String): Boolean {
        return messageBody.isNotBlank()
    }

}