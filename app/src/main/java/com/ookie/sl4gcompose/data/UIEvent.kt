package com.ookie.sl4gcompose.data


sealed class UIEvent {
    data class NameChanged(val name: String) : UIEvent()
    data class NumberChanged(val number: String) : UIEvent()
    data class MessageChanged(val message: String) : UIEvent()

    object SubmitButtonClicked : UIEvent()
}