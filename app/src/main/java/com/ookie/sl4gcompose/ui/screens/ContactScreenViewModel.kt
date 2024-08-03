package com.ookie.sl4gcompose.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.mutableStateOf
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ookie.sl4gcompose.data.ContactScreenUIState
import com.ookie.sl4gcompose.data.UIEvent
import com.ookie.sl4gcompose.model.MessageState

const val SL4G_EMAIL = "switchinlanes4god@gmail.com"
const val OOKIE_EMAIL = "ookieebron@gmail.com"

class ContactScreenViewModel: ViewModel() {

    var contactScreenUIState = mutableStateOf(ContactScreenUIState())
    var intent = Intent(Intent.ACTION_SENDTO)
    var intentStatusSuccessful = MutableLiveData<Boolean?>()

    //These will be updated with rememberSavable calls on Screen
    var name: String = ""
    var phoneNumber: String = ""
    var messageBody: String = ""

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
                if (validateMessage(MessageState(name,phoneNumber, messageBody))) {
                    intentStatusSuccessful.value = true
                    constructEmailIntent()
                } else {
                    intentStatusSuccessful.value = false
                }
            }
        }
    }

    fun validateMessage(messageState: MessageState) : Boolean {
        return nameValidate(messageState.name) &&
                phoneNumberValidate(messageState.phoneNumber.toString()) &&
                messageBodyValidate(messageState.messageBody)
    }

    private fun constructEmailIntent() {
        // only email apps should handle this
        intent.setData(Uri.parse("mailto:"))

        //In order to populate the "To:" field, this must be an Array of strings, not just a string itself!
        intent.putExtra(Intent.EXTRA_EMAIL, arrayOf(SL4G_EMAIL))
        intent.putExtra(Intent.EXTRA_BCC, arrayOf(OOKIE_EMAIL))
        intent.putExtra(Intent.EXTRA_PHONE_NUMBER, phoneNumber)
        intent.putExtra(Intent.EXTRA_TEXT, "Name: $name \nPhone: $phoneNumber \n \n$messageBody")
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