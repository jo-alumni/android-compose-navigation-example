package com.example.navigationtest.home.contract

import com.example.navigationtest.core.util.Event

internal interface HomeEvent : Event {
    data class ShowSnackbar(val text: String) : HomeEvent
}
