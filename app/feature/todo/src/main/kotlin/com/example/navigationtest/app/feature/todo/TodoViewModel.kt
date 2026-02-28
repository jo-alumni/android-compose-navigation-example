package com.example.navigationtest.app.feature.todo

import androidx.lifecycle.ViewModel
import com.example.navigationtest.app.feature.todo.contract.TodoEvent
import com.example.navigationtest.app.feature.todo.contract.TodoState
import com.example.navigationtest.core.domain.entity.Todo
import com.example.navigationtest.core.domain.repository.TodoRepository
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
        initialState = TodoState(),
    ) {
        coroutineScope {
            launch {
                todoRepository.getDone().collect { todos ->
                    reduce {
                        state.copy(doneTodos = todos)
                    }
                    postSideEffect(TodoEvent.LoadTodos)
                }
            }

            launch {
                todoRepository.getNotDone().collect { todos ->
                    reduce {
                        state.copy(notDoneTodos = todos)
                    }
                    postSideEffect(TodoEvent.LoadTodos)
                }

            }
        }
    }

    fun changeInput(input: String) {
        intent {
            reduce {
                state.copy(input = input)
            }
        }
    }

    fun registerTodo() {
        intent {
            if (state.input.isBlank()) return@intent
            todoRepository.upsert(
                Todo(
                    id = 0L,
                    content = state.input,
                    isDone = false,
                ),
            )
            reduce {
                state.copy(input = "")
            }
        }
    }
}
