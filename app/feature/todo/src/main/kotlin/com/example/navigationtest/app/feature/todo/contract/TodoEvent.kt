package com.example.navigationtest.app.feature.todo.contract

import com.example.navigationtest.app.core.util.Event

internal interface TodoEvent : Event {
    data object LoadTodos : TodoEvent
}
