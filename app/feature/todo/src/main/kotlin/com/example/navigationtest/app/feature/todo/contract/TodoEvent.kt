package com.example.navigationtest.app.feature.todo.contract

import com.example.navigationtest.app.core.util.Event

internal interface TodoEvent : Event {
    sealed interface ShowSnackbar : TodoEvent {
        data object Success : ShowSnackbar
        data object Error : ShowSnackbar
    }
}
