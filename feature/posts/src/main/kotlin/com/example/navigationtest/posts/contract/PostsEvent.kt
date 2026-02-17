package com.example.navigationtest.posts.contract

import com.example.navigationtest.core.util.Event

internal interface PostsEvent : Event {
    sealed interface ShowSnackbar : PostsEvent {
        data object Success : ShowSnackbar
        data object Error : ShowSnackbar
    }
}
