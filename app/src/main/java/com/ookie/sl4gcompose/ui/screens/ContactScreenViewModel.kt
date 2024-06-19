package com.ookie.sl4gcompose.ui.screens

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel

class ContactScreenViewModel: ViewModel() {

    lateinit var name: String
    lateinit var phoneNumber: String
    lateinit var messageBody: String

    fun validateMessage() {
        nameValidate()
        phoneNumberValidate()
        messageBodyValidate()
    }

    private fun nameValidate(): Boolean {
        // TODO: this also needs to ensure it is only alpha characters
        return name.isNotEmpty()
    }

    private fun phoneNumberValidate(): Boolean {
        // TODO: this also needs to ensure it is only numerical characters
        return phoneNumber.isNotEmpty()
    }

    private fun messageBodyValidate(): Boolean {
        return messageBody.isNotEmpty()
    }


}