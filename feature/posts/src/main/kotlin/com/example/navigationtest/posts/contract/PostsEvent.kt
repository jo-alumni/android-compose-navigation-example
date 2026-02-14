package com.example.navigationtest.posts.contract

import com.example.navigationtest.core.util.Event

internal interface PostsEvent : Event {
    data class ShowSnackbar(val text: String) : PostsEvent
}
