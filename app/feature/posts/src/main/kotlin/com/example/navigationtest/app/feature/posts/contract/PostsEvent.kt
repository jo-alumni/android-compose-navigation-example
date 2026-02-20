package com.example.navigationtest.app.feature.posts.contract

import com.example.navigationtest.app.core.util.Event

internal interface PostsEvent : Event {
    sealed interface ShowSnackbar : PostsEvent {
        data object Success : ShowSnackbar
        data object Error : ShowSnackbar
    }
}
