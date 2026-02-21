package com.example.navigationtest.app.feature.todo

import androidx.lifecycle.ViewModel
import com.example.navigationtest.app.feature.todo.contract.TodoEvent
import com.example.navigationtest.app.feature.todo.contract.TodoState
import com.example.navigationtest.domain.repository.TodoRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.Container
import org.orbitmvi.orbit.ContainerHost
import org.orbitmvi.orbit.viewmodel.container
import javax.inject.Inject

@HiltViewModel
internal class TodoViewModel @Inject constructor(
    private val todoRepository: TodoRepository,
) : ViewModel(), ContainerHost<TodoState, TodoEvent> {
    override val container: Container<TodoState, TodoEvent> = container(
        initialState = TodoState(
            todos = emptyList(),
        ),
    ) {
        // intent {
        coroutineScope {
            launch {
                todoRepository.getAll().collect { todos ->
                    reduce {
                        state.copy(todos = todos)
                    }
                }
            }
        }
        // }
    }
}
