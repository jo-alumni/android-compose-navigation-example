package com.example.navigationtest.app.feature.todo

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.hilt.lifecycle.viewmodel.compose.hiltViewModel
import com.example.navigationtest.app.core.ui.component.PostView
import com.example.navigationtest.app.core.ui.theme.AppTheme
import com.example.navigationtest.app.feature.todo.contract.TodoEvent
import com.example.navigationtest.app.feature.todo.contract.TodoState
import com.example.navigationtest.core.domain.entity.Todo
import kotlinx.coroutines.launch
import org.orbitmvi.orbit.compose.collectAsState
import org.orbitmvi.orbit.compose.collectSideEffect

@Composable
internal fun TodoRoot(
    modifier: Modifier = Modifier,
    viewModel: TodoViewModel = hiltViewModel(),
) {
    val uiState by viewModel.collectAsState()
    val lazyListState = rememberLazyListState()
    val scope = rememberCoroutineScope()

    viewModel.collectSideEffect {
        when (it) {
            is TodoEvent.LoadTodos -> {
                scope.launch { lazyListState.animateScrollToItem(0) }
            }
        }
    }

    TodoScreen(
        modifier = modifier,
        uiState = uiState,
        lazyListState = lazyListState,
        onInputValueChange = viewModel::changeInput,
        registerTodo = viewModel::registerTodo,
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun TodoScreen(
    uiState: TodoState,
    onInputValueChange: (String) -> Unit,
    registerTodo: () -> Unit,
    modifier: Modifier = Modifier,
    lazyListState: LazyListState = rememberLazyListState(),
) {
    Scaffold(
        modifier = modifier.fillMaxSize(),
        topBar = {
            CenterAlignedTopAppBar(
                title = {},
                navigationIcon = {},
            )
        },
    ) { paddingValues ->
        Column(
            modifier = Modifier.padding(paddingValues),
        ) {
            LazyColumn(
                modifier = Modifier.weight(1f),
                state = lazyListState,
            ) {
                items(items = uiState.todos, key = { it.id }) {
                    Column(modifier = Modifier.fillMaxWidth()) {
                        PostView(
                            modifier = Modifier.fillMaxWidth(),
                            name = it.id.toString(),
                            userId = "",
                            content = it.content,
                        )
                        HorizontalDivider()
                    }
                }
            }
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.Center,
            ) {
                TextField(
                    modifier = Modifier.weight(1f),
                    value = uiState.input,
                    onValueChange = onInputValueChange,
                    placeholder = { Text(text = "Todoを入力") },
                    keyboardActions = KeyboardActions(onSend = { registerTodo() }),
                    keyboardOptions = KeyboardOptions(imeAction = ImeAction.Send),
                )
            }
        }
    }
}

private class UiStateParameterProvider : PreviewParameterProvider<TodoState> {
    override val values: Sequence<TodoState> = sequenceOf(
        TodoState(
            todos = emptyList(),
            input = "",
        ),
        TodoState(
            todos = listOf(
                Todo(
                    id = 1L,
                    content = "Todo 1",
                    isDone = false,
                ),
                Todo(
                    id = 2L,
                    content = "Todo 2",
                    isDone = true,
                ),
            ),
            input = "",
        ),
        TodoState(
            todos = emptyList(),
            input = "input value",
        ),
    )
}

@Preview(showBackground = true)
@Composable
private fun TodoScreenPreview(
    @PreviewParameter(UiStateParameterProvider::class) uiState: TodoState,
) {
    AppTheme {
        TodoScreen(
            uiState = uiState,
            onInputValueChange = {},
            registerTodo = {},
        )
    }
}
